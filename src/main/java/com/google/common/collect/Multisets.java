package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
public final class Multisets {
    private static final Ordering<Multiset.Entry<?>> DECREASING_COUNT_ORDERING = new Ordering<Multiset.Entry<?>>() {
        public int compare(Multiset.Entry<?> entry, Multiset.Entry<?> entry2) {
            return Ints.compare(entry2.getCount(), entry.getCount());
        }
    };

    static abstract class AbstractEntry<E> implements Multiset.Entry<E> {
        AbstractEntry() {
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof Multiset.Entry)) {
                return false;
            }
            Multiset.Entry entry = (Multiset.Entry) obj;
            return getCount() == entry.getCount() && Objects.equal(getElement(), entry.getElement());
        }

        public int hashCode() {
            Object element = getElement();
            return (element == null ? 0 : element.hashCode()) ^ getCount();
        }

        public String toString() {
            String valueOf = String.valueOf(getElement());
            int count = getCount();
            if (count == 1) {
                return valueOf;
            }
            return valueOf + " x " + count;
        }
    }

    static abstract class ElementSet<E> extends Sets.ImprovedAbstractSet<E> {
        ElementSet() {
        }

        /* access modifiers changed from: package-private */
        public abstract Multiset<E> a();

        public void clear() {
            a().clear();
        }

        public boolean contains(Object obj) {
            return a().contains(obj);
        }

        public boolean containsAll(Collection<?> collection) {
            return a().containsAll(collection);
        }

        public boolean isEmpty() {
            return a().isEmpty();
        }

        public Iterator<E> iterator() {
            return new TransformedIterator<Multiset.Entry<E>, E>(a().entrySet().iterator()) {
                /* access modifiers changed from: package-private */
                public E a(Multiset.Entry<E> entry) {
                    return entry.getElement();
                }
            };
        }

        public boolean remove(Object obj) {
            return a().remove(obj, Integer.MAX_VALUE) > 0;
        }

        public int size() {
            return a().entrySet().size();
        }
    }

    static abstract class EntrySet<E> extends Sets.ImprovedAbstractSet<Multiset.Entry<E>> {
        EntrySet() {
        }

        /* access modifiers changed from: package-private */
        public abstract Multiset<E> a();

        public void clear() {
            a().clear();
        }

        public boolean contains(@Nullable Object obj) {
            if (!(obj instanceof Multiset.Entry)) {
                return false;
            }
            Multiset.Entry entry = (Multiset.Entry) obj;
            return entry.getCount() > 0 && a().count(entry.getElement()) == entry.getCount();
        }

        public boolean remove(Object obj) {
            if (obj instanceof Multiset.Entry) {
                Multiset.Entry entry = (Multiset.Entry) obj;
                Object element = entry.getElement();
                int count = entry.getCount();
                if (count != 0) {
                    return a().setCount(element, count, 0);
                }
            }
            return false;
        }
    }

    private static final class FilteredMultiset<E> extends AbstractMultiset<E> {
        final Multiset<E> a;
        final Predicate<? super E> b;

        FilteredMultiset(Multiset<E> multiset, Predicate<? super E> predicate) {
            this.a = (Multiset) Preconditions.checkNotNull(multiset);
            this.b = (Predicate) Preconditions.checkNotNull(predicate);
        }

        /* access modifiers changed from: package-private */
        public Iterator<Multiset.Entry<E>> a() {
            throw new AssertionError("should never be called");
        }

        public int add(@Nullable E e, int i) {
            Preconditions.checkArgument(this.b.apply(e), "Element %s does not match predicate %s", (Object) e, (Object) this.b);
            return this.a.add(e, i);
        }

        /* access modifiers changed from: package-private */
        public int b() {
            return elementSet().size();
        }

        /* access modifiers changed from: package-private */
        public Set<E> c() {
            return Sets.filter(this.a.elementSet(), this.b);
        }

        public void clear() {
            elementSet().clear();
        }

        public int count(@Nullable Object obj) {
            int count = this.a.count(obj);
            if (count <= 0 || !this.b.apply(obj)) {
                return 0;
            }
            return count;
        }

        /* access modifiers changed from: package-private */
        public Set<Multiset.Entry<E>> createEntrySet() {
            return Sets.filter(this.a.entrySet(), new Predicate<Multiset.Entry<E>>() {
                public boolean apply(Multiset.Entry<E> entry) {
                    return FilteredMultiset.this.b.apply(entry.getElement());
                }
            });
        }

        public UnmodifiableIterator<E> iterator() {
            return Iterators.filter(this.a.iterator(), this.b);
        }

        public int remove(@Nullable Object obj, int i) {
            CollectPreconditions.a(i, "occurrences");
            if (i == 0) {
                return count(obj);
            }
            if (contains(obj)) {
                return this.a.remove(obj, i);
            }
            return 0;
        }
    }

    static class ImmutableEntry<E> extends AbstractEntry<E> implements Serializable {
        private static final long serialVersionUID = 0;
        private final int count;
        @Nullable
        private final E element;

        ImmutableEntry(@Nullable E e, int i) {
            this.element = e;
            this.count = i;
            CollectPreconditions.a(i, "count");
        }

        public final int getCount() {
            return this.count;
        }

        @Nullable
        public final E getElement() {
            return this.element;
        }

        public ImmutableEntry<E> nextInBucket() {
            return null;
        }
    }

    static final class MultisetIteratorImpl<E> implements Iterator<E> {
        private boolean canRemove;
        private Multiset.Entry<E> currentEntry;
        private final Iterator<Multiset.Entry<E>> entryIterator;
        private int laterCount;
        private final Multiset<E> multiset;
        private int totalCount;

        MultisetIteratorImpl(Multiset<E> multiset2, Iterator<Multiset.Entry<E>> it) {
            this.multiset = multiset2;
            this.entryIterator = it;
        }

        public boolean hasNext() {
            return this.laterCount > 0 || this.entryIterator.hasNext();
        }

        public E next() {
            if (hasNext()) {
                if (this.laterCount == 0) {
                    this.currentEntry = this.entryIterator.next();
                    int count = this.currentEntry.getCount();
                    this.laterCount = count;
                    this.totalCount = count;
                }
                this.laterCount--;
                this.canRemove = true;
                return this.currentEntry.getElement();
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            CollectPreconditions.a(this.canRemove);
            if (this.totalCount == 1) {
                this.entryIterator.remove();
            } else {
                this.multiset.remove(this.currentEntry.getElement());
            }
            this.totalCount--;
            this.canRemove = false;
        }
    }

    static class UnmodifiableMultiset<E> extends ForwardingMultiset<E> implements Serializable {
        private static final long serialVersionUID = 0;
        final Multiset<? extends E> a;
        transient Set<E> b;
        transient Set<Multiset.Entry<E>> c;

        UnmodifiableMultiset(Multiset<? extends E> multiset) {
            this.a = multiset;
        }

        /* access modifiers changed from: package-private */
        public Set<E> a() {
            return Collections.unmodifiableSet(this.a.elementSet());
        }

        public int add(E e, int i) {
            throw new UnsupportedOperationException();
        }

        public boolean add(E e) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            throw new UnsupportedOperationException();
        }

        /* access modifiers changed from: protected */
        /* renamed from: d */
        public Multiset<E> delegate() {
            return this.a;
        }

        public Set<E> elementSet() {
            Set<E> set = this.b;
            if (set != null) {
                return set;
            }
            Set<E> a2 = a();
            this.b = a2;
            return a2;
        }

        public Set<Multiset.Entry<E>> entrySet() {
            Set<Multiset.Entry<E>> set = this.c;
            if (set != null) {
                return set;
            }
            Set<Multiset.Entry<E>> unmodifiableSet = Collections.unmodifiableSet(this.a.entrySet());
            this.c = unmodifiableSet;
            return unmodifiableSet;
        }

        public Iterator<E> iterator() {
            return Iterators.unmodifiableIterator(this.a.iterator());
        }

        public int remove(Object obj, int i) {
            throw new UnsupportedOperationException();
        }

        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public int setCount(E e, int i) {
            throw new UnsupportedOperationException();
        }

        public boolean setCount(E e, int i, int i2) {
            throw new UnsupportedOperationException();
        }
    }

    private Multisets() {
    }

    static <E> int a(Multiset<E> multiset, E e, int i) {
        CollectPreconditions.a(i, "count");
        int count = multiset.count(e);
        int i2 = i - count;
        if (i2 > 0) {
            multiset.add(e, i2);
        } else if (i2 < 0) {
            multiset.remove(e, -i2);
        }
        return count;
    }

    static int a(Iterable<?> iterable) {
        if (iterable instanceof Multiset) {
            return ((Multiset) iterable).elementSet().size();
        }
        return 11;
    }

    static <E> Iterator<E> a(Multiset<E> multiset) {
        return new MultisetIteratorImpl(multiset, multiset.entrySet().iterator());
    }

    static boolean a(Multiset<?> multiset, @Nullable Object obj) {
        if (obj == multiset) {
            return true;
        }
        if (obj instanceof Multiset) {
            Multiset multiset2 = (Multiset) obj;
            if (multiset.size() == multiset2.size() && multiset.entrySet().size() == multiset2.entrySet().size()) {
                for (Multiset.Entry entry : multiset2.entrySet()) {
                    if (multiset.count(entry.getElement()) != entry.getCount()) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    static <E> boolean a(Multiset<E> multiset, E e, int i, int i2) {
        CollectPreconditions.a(i, "oldCount");
        CollectPreconditions.a(i2, "newCount");
        if (multiset.count(e) != i) {
            return false;
        }
        multiset.setCount(e, i2);
        return true;
    }

    static <E> boolean a(Multiset<E> multiset, Collection<? extends E> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        if (collection instanceof Multiset) {
            for (Multiset.Entry next : b(collection).entrySet()) {
                multiset.add(next.getElement(), next.getCount());
            }
            return true;
        }
        Iterators.addAll(multiset, collection.iterator());
        return true;
    }

    static int b(Multiset<?> multiset) {
        long j = 0;
        for (Multiset.Entry<?> count : multiset.entrySet()) {
            j += (long) count.getCount();
        }
        return Ints.saturatedCast(j);
    }

    static <T> Multiset<T> b(Iterable<T> iterable) {
        return (Multiset) iterable;
    }

    static boolean b(Multiset<?> multiset, Collection<?> collection) {
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        return multiset.elementSet().removeAll(collection);
    }

    static boolean c(Multiset<?> multiset, Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        return multiset.elementSet().retainAll(collection);
    }

    @CanIgnoreReturnValue
    public static boolean containsOccurrences(Multiset<?> multiset, Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        for (Multiset.Entry next : multiset2.entrySet()) {
            if (multiset.count(next.getElement()) < next.getCount()) {
                return false;
            }
        }
        return true;
    }

    @Beta
    public static <E> ImmutableMultiset<E> copyHighestCountFirst(Multiset<E> multiset) {
        return ImmutableMultiset.a(DECREASING_COUNT_ORDERING.immutableSortedCopy(multiset.entrySet()));
    }

    @Beta
    public static <E> Multiset<E> difference(final Multiset<E> multiset, final Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        return new AbstractMultiset<E>() {
            /* access modifiers changed from: package-private */
            public Iterator<Multiset.Entry<E>> a() {
                final Iterator it = multiset.entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>() {
                    /* access modifiers changed from: protected */
                    /* renamed from: b */
                    public Multiset.Entry<E> computeNext() {
                        while (it.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it.next();
                            Object element = entry.getElement();
                            int count = entry.getCount() - multiset2.count(element);
                            if (count > 0) {
                                return Multisets.immutableEntry(element, count);
                            }
                        }
                        return (Multiset.Entry) a();
                    }
                };
            }

            /* access modifiers changed from: package-private */
            public int b() {
                return Iterators.size(a());
            }

            public int count(@Nullable Object obj) {
                int count = multiset.count(obj);
                if (count == 0) {
                    return 0;
                }
                return Math.max(0, count - multiset2.count(obj));
            }
        };
    }

    @Beta
    public static <E> Multiset<E> filter(Multiset<E> multiset, Predicate<? super E> predicate) {
        if (!(multiset instanceof FilteredMultiset)) {
            return new FilteredMultiset(multiset, predicate);
        }
        FilteredMultiset filteredMultiset = (FilteredMultiset) multiset;
        return new FilteredMultiset(filteredMultiset.a, Predicates.and(filteredMultiset.b, predicate));
    }

    public static <E> Multiset.Entry<E> immutableEntry(@Nullable E e, int i) {
        return new ImmutableEntry(e, i);
    }

    public static <E> Multiset<E> intersection(final Multiset<E> multiset, final Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        return new AbstractMultiset<E>() {
            /* access modifiers changed from: package-private */
            public Iterator<Multiset.Entry<E>> a() {
                final Iterator it = multiset.entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>() {
                    /* access modifiers changed from: protected */
                    /* renamed from: b */
                    public Multiset.Entry<E> computeNext() {
                        while (it.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it.next();
                            Object element = entry.getElement();
                            int min = Math.min(entry.getCount(), multiset2.count(element));
                            if (min > 0) {
                                return Multisets.immutableEntry(element, min);
                            }
                        }
                        return (Multiset.Entry) a();
                    }
                };
            }

            /* access modifiers changed from: package-private */
            public int b() {
                return elementSet().size();
            }

            /* access modifiers changed from: package-private */
            public Set<E> c() {
                return Sets.intersection(multiset.elementSet(), multiset2.elementSet());
            }

            public int count(Object obj) {
                int count = multiset.count(obj);
                if (count == 0) {
                    return 0;
                }
                return Math.min(count, multiset2.count(obj));
            }
        };
    }

    @CanIgnoreReturnValue
    public static boolean removeOccurrences(Multiset<?> multiset, Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        Iterator<Multiset.Entry<?>> it = multiset.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Multiset.Entry next = it.next();
            int count = multiset2.count(next.getElement());
            if (count >= next.getCount()) {
                it.remove();
            } else if (count > 0) {
                multiset.remove(next.getElement(), count);
            }
            z = true;
        }
        return z;
    }

    @CanIgnoreReturnValue
    public static boolean removeOccurrences(Multiset<?> multiset, Iterable<?> iterable) {
        if (iterable instanceof Multiset) {
            return removeOccurrences(multiset, (Multiset<?>) (Multiset) iterable);
        }
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(iterable);
        boolean z = false;
        for (Object remove : iterable) {
            z |= multiset.remove(remove);
        }
        return z;
    }

    @CanIgnoreReturnValue
    public static boolean retainOccurrences(Multiset<?> multiset, Multiset<?> multiset2) {
        return retainOccurrencesImpl(multiset, multiset2);
    }

    private static <E> boolean retainOccurrencesImpl(Multiset<E> multiset, Multiset<?> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        Iterator<Multiset.Entry<E>> it = multiset.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Multiset.Entry next = it.next();
            int count = multiset2.count(next.getElement());
            if (count == 0) {
                it.remove();
            } else if (count < next.getCount()) {
                multiset.setCount(next.getElement(), count);
            }
            z = true;
        }
        return z;
    }

    @Beta
    public static <E> Multiset<E> sum(final Multiset<? extends E> multiset, final Multiset<? extends E> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        return new AbstractMultiset<E>() {
            /* access modifiers changed from: package-private */
            public Iterator<Multiset.Entry<E>> a() {
                final Iterator it = multiset.entrySet().iterator();
                final Iterator it2 = multiset2.entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>() {
                    /* access modifiers changed from: protected */
                    /* renamed from: b */
                    public Multiset.Entry<E> computeNext() {
                        if (it.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it.next();
                            Object element = entry.getElement();
                            return Multisets.immutableEntry(element, entry.getCount() + multiset2.count(element));
                        }
                        while (it2.hasNext()) {
                            Multiset.Entry entry2 = (Multiset.Entry) it2.next();
                            Object element2 = entry2.getElement();
                            if (!multiset.contains(element2)) {
                                return Multisets.immutableEntry(element2, entry2.getCount());
                            }
                        }
                        return (Multiset.Entry) a();
                    }
                };
            }

            /* access modifiers changed from: package-private */
            public int b() {
                return elementSet().size();
            }

            /* access modifiers changed from: package-private */
            public Set<E> c() {
                return Sets.union(multiset.elementSet(), multiset2.elementSet());
            }

            public boolean contains(@Nullable Object obj) {
                return multiset.contains(obj) || multiset2.contains(obj);
            }

            public int count(Object obj) {
                return multiset.count(obj) + multiset2.count(obj);
            }

            public boolean isEmpty() {
                return multiset.isEmpty() && multiset2.isEmpty();
            }

            public int size() {
                return IntMath.saturatedAdd(multiset.size(), multiset2.size());
            }
        };
    }

    @Beta
    public static <E> Multiset<E> union(final Multiset<? extends E> multiset, final Multiset<? extends E> multiset2) {
        Preconditions.checkNotNull(multiset);
        Preconditions.checkNotNull(multiset2);
        return new AbstractMultiset<E>() {
            /* access modifiers changed from: package-private */
            public Iterator<Multiset.Entry<E>> a() {
                final Iterator it = multiset.entrySet().iterator();
                final Iterator it2 = multiset2.entrySet().iterator();
                return new AbstractIterator<Multiset.Entry<E>>() {
                    /* access modifiers changed from: protected */
                    /* renamed from: b */
                    public Multiset.Entry<E> computeNext() {
                        if (it.hasNext()) {
                            Multiset.Entry entry = (Multiset.Entry) it.next();
                            Object element = entry.getElement();
                            return Multisets.immutableEntry(element, Math.max(entry.getCount(), multiset2.count(element)));
                        }
                        while (it2.hasNext()) {
                            Multiset.Entry entry2 = (Multiset.Entry) it2.next();
                            Object element2 = entry2.getElement();
                            if (!multiset.contains(element2)) {
                                return Multisets.immutableEntry(element2, entry2.getCount());
                            }
                        }
                        return (Multiset.Entry) a();
                    }
                };
            }

            /* access modifiers changed from: package-private */
            public int b() {
                return elementSet().size();
            }

            /* access modifiers changed from: package-private */
            public Set<E> c() {
                return Sets.union(multiset.elementSet(), multiset2.elementSet());
            }

            public boolean contains(@Nullable Object obj) {
                return multiset.contains(obj) || multiset2.contains(obj);
            }

            public int count(Object obj) {
                return Math.max(multiset.count(obj), multiset2.count(obj));
            }

            public boolean isEmpty() {
                return multiset.isEmpty() && multiset2.isEmpty();
            }
        };
    }

    @Deprecated
    public static <E> Multiset<E> unmodifiableMultiset(ImmutableMultiset<E> immutableMultiset) {
        return (Multiset) Preconditions.checkNotNull(immutableMultiset);
    }

    public static <E> Multiset<E> unmodifiableMultiset(Multiset<? extends E> multiset) {
        return ((multiset instanceof UnmodifiableMultiset) || (multiset instanceof ImmutableMultiset)) ? multiset : new UnmodifiableMultiset((Multiset) Preconditions.checkNotNull(multiset));
    }

    @Beta
    public static <E> SortedMultiset<E> unmodifiableSortedMultiset(SortedMultiset<E> sortedMultiset) {
        return new UnmodifiableSortedMultiset((SortedMultiset) Preconditions.checkNotNull(sortedMultiset));
    }
}

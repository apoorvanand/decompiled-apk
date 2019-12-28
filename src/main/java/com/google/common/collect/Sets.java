package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
public final class Sets {

    private static final class CartesianSet<E> extends ForwardingCollection<List<E>> implements Set<List<E>> {
        private final transient ImmutableList<ImmutableSet<E>> axes;
        private final transient CartesianList<E> delegate;

        private CartesianSet(ImmutableList<ImmutableSet<E>> immutableList, CartesianList<E> cartesianList) {
            this.axes = immutableList;
            this.delegate = cartesianList;
        }

        static <E> Set<List<E>> a(List<? extends Set<? extends E>> list) {
            ImmutableList.Builder builder = new ImmutableList.Builder(list.size());
            for (Set copyOf : list) {
                ImmutableSet copyOf2 = ImmutableSet.copyOf(copyOf);
                if (copyOf2.isEmpty()) {
                    return ImmutableSet.of();
                }
                builder.add((Object) copyOf2);
            }
            final ImmutableList build = builder.build();
            return new CartesianSet(build, new CartesianList(new ImmutableList<List<E>>() {
                /* access modifiers changed from: package-private */
                public boolean a() {
                    return true;
                }

                public List<E> get(int i) {
                    return ((ImmutableSet) build.get(i)).asList();
                }

                public int size() {
                    return build.size();
                }
            }));
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public Collection<List<E>> delegate() {
            return this.delegate;
        }

        public boolean equals(@Nullable Object obj) {
            return obj instanceof CartesianSet ? this.axes.equals(((CartesianSet) obj).axes) : super.equals(obj);
        }

        public int hashCode() {
            int i = 1;
            int size = size() - 1;
            for (int i2 = 0; i2 < this.axes.size(); i2++) {
                size = ~(~(size * 31));
            }
            UnmodifiableIterator<ImmutableSet<E>> it = this.axes.iterator();
            while (it.hasNext()) {
                Set next = it.next();
                i = ~(~((i * 31) + ((size() / next.size()) * next.hashCode())));
            }
            return ~(~(i + size));
        }
    }

    @GwtIncompatible
    static class DescendingSet<E> extends ForwardingNavigableSet<E> {
        private final NavigableSet<E> forward;

        DescendingSet(NavigableSet<E> navigableSet) {
            this.forward = navigableSet;
        }

        private static <T> Ordering<T> reverse(Comparator<T> comparator) {
            return Ordering.from(comparator).reverse();
        }

        public E ceiling(E e) {
            return this.forward.floor(e);
        }

        public Comparator<? super E> comparator() {
            Comparator comparator = this.forward.comparator();
            return comparator == null ? Ordering.natural().reverse() : reverse(comparator);
        }

        /* access modifiers changed from: protected */
        /* renamed from: d */
        public NavigableSet<E> delegate() {
            return this.forward;
        }

        public Iterator<E> descendingIterator() {
            return this.forward.iterator();
        }

        public NavigableSet<E> descendingSet() {
            return this.forward;
        }

        public E first() {
            return this.forward.last();
        }

        public E floor(E e) {
            return this.forward.ceiling(e);
        }

        public NavigableSet<E> headSet(E e, boolean z) {
            return this.forward.tailSet(e, z).descendingSet();
        }

        public SortedSet<E> headSet(E e) {
            return c(e);
        }

        public E higher(E e) {
            return this.forward.lower(e);
        }

        public Iterator<E> iterator() {
            return this.forward.descendingIterator();
        }

        public E last() {
            return this.forward.first();
        }

        public E lower(E e) {
            return this.forward.higher(e);
        }

        public E pollFirst() {
            return this.forward.pollLast();
        }

        public E pollLast() {
            return this.forward.pollFirst();
        }

        public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
            return this.forward.subSet(e2, z2, e, z).descendingSet();
        }

        public SortedSet<E> subSet(E e, E e2) {
            return a(e, e2);
        }

        public NavigableSet<E> tailSet(E e, boolean z) {
            return this.forward.headSet(e, z).descendingSet();
        }

        public SortedSet<E> tailSet(E e) {
            return d(e);
        }

        public Object[] toArray() {
            return g();
        }

        public <T> T[] toArray(T[] tArr) {
            return a(tArr);
        }

        public String toString() {
            return f();
        }
    }

    @GwtIncompatible
    private static class FilteredNavigableSet<E> extends FilteredSortedSet<E> implements NavigableSet<E> {
        FilteredNavigableSet(NavigableSet<E> navigableSet, Predicate<? super E> predicate) {
            super(navigableSet, predicate);
        }

        /* access modifiers changed from: package-private */
        public NavigableSet<E> a() {
            return (NavigableSet) this.a;
        }

        public E ceiling(E e) {
            return Iterables.getFirst(tailSet(e, true), null);
        }

        public Iterator<E> descendingIterator() {
            return Iterators.filter(a().descendingIterator(), this.b);
        }

        public NavigableSet<E> descendingSet() {
            return Sets.filter(a().descendingSet(), this.b);
        }

        @Nullable
        public E floor(E e) {
            return Iterators.getNext(headSet(e, true).descendingIterator(), null);
        }

        public NavigableSet<E> headSet(E e, boolean z) {
            return Sets.filter(a().headSet(e, z), this.b);
        }

        public E higher(E e) {
            return Iterables.getFirst(tailSet(e, false), null);
        }

        public E last() {
            return descendingIterator().next();
        }

        @Nullable
        public E lower(E e) {
            return Iterators.getNext(headSet(e, false).descendingIterator(), null);
        }

        public E pollFirst() {
            return Iterables.a(a(), this.b);
        }

        public E pollLast() {
            return Iterables.a(a().descendingSet(), this.b);
        }

        public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
            return Sets.filter(a().subSet(e, z, e2, z2), this.b);
        }

        public NavigableSet<E> tailSet(E e, boolean z) {
            return Sets.filter(a().tailSet(e, z), this.b);
        }
    }

    private static class FilteredSet<E> extends Collections2.FilteredCollection<E> implements Set<E> {
        FilteredSet(Set<E> set, Predicate<? super E> predicate) {
            super(set, predicate);
        }

        public boolean equals(@Nullable Object obj) {
            return Sets.a((Set<?>) this, obj);
        }

        public int hashCode() {
            return Sets.a(this);
        }
    }

    private static class FilteredSortedSet<E> extends FilteredSet<E> implements SortedSet<E> {
        FilteredSortedSet(SortedSet<E> sortedSet, Predicate<? super E> predicate) {
            super(sortedSet, predicate);
        }

        public Comparator<? super E> comparator() {
            return ((SortedSet) this.a).comparator();
        }

        public E first() {
            return iterator().next();
        }

        public SortedSet<E> headSet(E e) {
            return new FilteredSortedSet(((SortedSet) this.a).headSet(e), this.b);
        }

        public E last() {
            SortedSet sortedSet = (SortedSet) this.a;
            while (true) {
                E last = sortedSet.last();
                if (this.b.apply(last)) {
                    return last;
                }
                sortedSet = sortedSet.headSet(last);
            }
        }

        public SortedSet<E> subSet(E e, E e2) {
            return new FilteredSortedSet(((SortedSet) this.a).subSet(e, e2), this.b);
        }

        public SortedSet<E> tailSet(E e) {
            return new FilteredSortedSet(((SortedSet) this.a).tailSet(e), this.b);
        }
    }

    static abstract class ImprovedAbstractSet<E> extends AbstractSet<E> {
        ImprovedAbstractSet() {
        }

        public boolean removeAll(Collection<?> collection) {
            return Sets.a((Set<?>) this, collection);
        }

        public boolean retainAll(Collection<?> collection) {
            return super.retainAll((Collection) Preconditions.checkNotNull(collection));
        }
    }

    private static final class PowerSet<E> extends AbstractSet<Set<E>> {
        final ImmutableMap<E, Integer> a;

        PowerSet(Set<E> set) {
            this.a = Maps.a(set);
            Preconditions.checkArgument(this.a.size() <= 30, "Too many elements to create power set: %s > 30", this.a.size());
        }

        public boolean contains(@Nullable Object obj) {
            if (!(obj instanceof Set)) {
                return false;
            }
            return this.a.keySet().containsAll((Set) obj);
        }

        public boolean equals(@Nullable Object obj) {
            return obj instanceof PowerSet ? this.a.equals(((PowerSet) obj).a) : super.equals(obj);
        }

        public int hashCode() {
            return this.a.keySet().hashCode() << (this.a.size() - 1);
        }

        public boolean isEmpty() {
            return false;
        }

        public Iterator<Set<E>> iterator() {
            return new AbstractIndexedListIterator<Set<E>>(size()) {
                /* access modifiers changed from: protected */
                /* renamed from: b */
                public Set<E> a(int i) {
                    return new SubSet(PowerSet.this.a, i);
                }
            };
        }

        public int size() {
            return 1 << this.a.size();
        }

        public String toString() {
            return "powerSet(" + this.a + ")";
        }
    }

    public static abstract class SetView<E> extends AbstractSet<E> {
        private SetView() {
        }

        @CanIgnoreReturnValue
        public <S extends Set<E>> S copyInto(S s) {
            s.addAll(this);
            return s;
        }

        public ImmutableSet<E> immutableCopy() {
            return ImmutableSet.copyOf(this);
        }

        public abstract UnmodifiableIterator<E> iterator();
    }

    private static final class SubSet<E> extends AbstractSet<E> {
        /* access modifiers changed from: private */
        public final ImmutableMap<E, Integer> inputSet;
        /* access modifiers changed from: private */
        public final int mask;

        SubSet(ImmutableMap<E, Integer> immutableMap, int i) {
            this.inputSet = immutableMap;
            this.mask = i;
        }

        public boolean contains(@Nullable Object obj) {
            Integer num = this.inputSet.get(obj);
            if (num != null) {
                if (((1 << num.intValue()) & this.mask) != 0) {
                    return true;
                }
            }
            return false;
        }

        public Iterator<E> iterator() {
            return new UnmodifiableIterator<E>() {
                final ImmutableList<E> a = SubSet.this.inputSet.keySet().asList();
                int b = SubSet.this.mask;

                public boolean hasNext() {
                    return this.b != 0;
                }

                public E next() {
                    int numberOfTrailingZeros = Integer.numberOfTrailingZeros(this.b);
                    if (numberOfTrailingZeros != 32) {
                        this.b &= ~(1 << numberOfTrailingZeros);
                        return this.a.get(numberOfTrailingZeros);
                    }
                    throw new NoSuchElementException();
                }
            };
        }

        public int size() {
            return Integer.bitCount(this.mask);
        }
    }

    @GwtIncompatible
    static final class UnmodifiableNavigableSet<E> extends ForwardingSortedSet<E> implements Serializable, NavigableSet<E> {
        private static final long serialVersionUID = 0;
        private final NavigableSet<E> delegate;
        private transient UnmodifiableNavigableSet<E> descendingSet;

        UnmodifiableNavigableSet(NavigableSet<E> navigableSet) {
            this.delegate = (NavigableSet) Preconditions.checkNotNull(navigableSet);
        }

        /* access modifiers changed from: protected */
        /* renamed from: c */
        public SortedSet<E> delegate() {
            return Collections.unmodifiableSortedSet(this.delegate);
        }

        public E ceiling(E e) {
            return this.delegate.ceiling(e);
        }

        public Iterator<E> descendingIterator() {
            return Iterators.unmodifiableIterator(this.delegate.descendingIterator());
        }

        public NavigableSet<E> descendingSet() {
            UnmodifiableNavigableSet<E> unmodifiableNavigableSet = this.descendingSet;
            if (unmodifiableNavigableSet != null) {
                return unmodifiableNavigableSet;
            }
            UnmodifiableNavigableSet<E> unmodifiableNavigableSet2 = new UnmodifiableNavigableSet<>(this.delegate.descendingSet());
            this.descendingSet = unmodifiableNavigableSet2;
            unmodifiableNavigableSet2.descendingSet = this;
            return unmodifiableNavigableSet2;
        }

        public E floor(E e) {
            return this.delegate.floor(e);
        }

        public NavigableSet<E> headSet(E e, boolean z) {
            return Sets.unmodifiableNavigableSet(this.delegate.headSet(e, z));
        }

        public E higher(E e) {
            return this.delegate.higher(e);
        }

        public E lower(E e) {
            return this.delegate.lower(e);
        }

        public E pollFirst() {
            throw new UnsupportedOperationException();
        }

        public E pollLast() {
            throw new UnsupportedOperationException();
        }

        public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
            return Sets.unmodifiableNavigableSet(this.delegate.subSet(e, z, e2, z2));
        }

        public NavigableSet<E> tailSet(E e, boolean z) {
            return Sets.unmodifiableNavigableSet(this.delegate.tailSet(e, z));
        }
    }

    private Sets() {
    }

    static int a(Set<?> set) {
        Iterator<?> it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i = ~(~(i + (next != null ? next.hashCode() : 0)));
        }
        return i;
    }

    static boolean a(Set<?> set, @Nullable Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                return set.size() == set2.size() && set.containsAll(set2);
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    static boolean a(Set<?> set, Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        return (!(collection instanceof Set) || collection.size() <= set.size()) ? a(set, collection.iterator()) : Iterators.removeAll(set.iterator(), collection);
    }

    static boolean a(Set<?> set, Iterator<?> it) {
        boolean z = false;
        while (it.hasNext()) {
            z |= set.remove(it.next());
        }
        return z;
    }

    public static <B> Set<List<B>> cartesianProduct(List<? extends Set<? extends B>> list) {
        return CartesianSet.a(list);
    }

    public static <B> Set<List<B>> cartesianProduct(Set<? extends B>... setArr) {
        return cartesianProduct(Arrays.asList(setArr));
    }

    public static <E extends Enum<E>> EnumSet<E> complementOf(Collection<E> collection) {
        if (collection instanceof EnumSet) {
            return EnumSet.complementOf((EnumSet) collection);
        }
        Preconditions.checkArgument(!collection.isEmpty(), "collection is empty; use the other version of this method");
        return makeComplementByHand(collection, ((Enum) collection.iterator().next()).getDeclaringClass());
    }

    public static <E extends Enum<E>> EnumSet<E> complementOf(Collection<E> collection, Class<E> cls) {
        Preconditions.checkNotNull(collection);
        return collection instanceof EnumSet ? EnumSet.complementOf((EnumSet) collection) : makeComplementByHand(collection, cls);
    }

    public static <E> SetView<E> difference(final Set<E> set, final Set<?> set2) {
        Preconditions.checkNotNull(set, "set1");
        Preconditions.checkNotNull(set2, "set2");
        final Predicate<T> not = Predicates.not(Predicates.in(set2));
        return new SetView<E>() {
            public boolean contains(Object obj) {
                return set.contains(obj) && !set2.contains(obj);
            }

            public boolean isEmpty() {
                return set2.containsAll(set);
            }

            public UnmodifiableIterator<E> iterator() {
                return Iterators.filter(set.iterator(), not);
            }

            public int size() {
                return Iterators.size(iterator());
            }
        };
    }

    @GwtIncompatible
    public static <E> NavigableSet<E> filter(NavigableSet<E> navigableSet, Predicate<? super E> predicate) {
        if (!(navigableSet instanceof FilteredSet)) {
            return new FilteredNavigableSet((NavigableSet) Preconditions.checkNotNull(navigableSet), (Predicate) Preconditions.checkNotNull(predicate));
        }
        FilteredSet filteredSet = (FilteredSet) navigableSet;
        return new FilteredNavigableSet((NavigableSet) filteredSet.a, Predicates.and(filteredSet.b, predicate));
    }

    public static <E> Set<E> filter(Set<E> set, Predicate<? super E> predicate) {
        if (set instanceof SortedSet) {
            return filter((SortedSet) set, predicate);
        }
        if (!(set instanceof FilteredSet)) {
            return new FilteredSet((Set) Preconditions.checkNotNull(set), (Predicate) Preconditions.checkNotNull(predicate));
        }
        FilteredSet filteredSet = (FilteredSet) set;
        return new FilteredSet((Set) filteredSet.a, Predicates.and(filteredSet.b, predicate));
    }

    public static <E> SortedSet<E> filter(SortedSet<E> sortedSet, Predicate<? super E> predicate) {
        if (!(sortedSet instanceof FilteredSet)) {
            return new FilteredSortedSet((SortedSet) Preconditions.checkNotNull(sortedSet), (Predicate) Preconditions.checkNotNull(predicate));
        }
        FilteredSet filteredSet = (FilteredSet) sortedSet;
        return new FilteredSortedSet((SortedSet) filteredSet.a, Predicates.and(filteredSet.b, predicate));
    }

    @GwtCompatible(serializable = true)
    public static <E extends Enum<E>> ImmutableSet<E> immutableEnumSet(E e, E... eArr) {
        return ImmutableEnumSet.a(EnumSet.of(e, eArr));
    }

    @GwtCompatible(serializable = true)
    public static <E extends Enum<E>> ImmutableSet<E> immutableEnumSet(Iterable<E> iterable) {
        if (iterable instanceof ImmutableEnumSet) {
            return (ImmutableEnumSet) iterable;
        }
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            return collection.isEmpty() ? ImmutableSet.of() : ImmutableEnumSet.a(EnumSet.copyOf(collection));
        }
        Iterator<E> it = iterable.iterator();
        if (!it.hasNext()) {
            return ImmutableSet.of();
        }
        EnumSet of = EnumSet.of((Enum) it.next());
        Iterators.addAll(of, it);
        return ImmutableEnumSet.a(of);
    }

    public static <E> SetView<E> intersection(final Set<E> set, final Set<?> set2) {
        Preconditions.checkNotNull(set, "set1");
        Preconditions.checkNotNull(set2, "set2");
        final Predicate<T> in = Predicates.in(set2);
        return new SetView<E>() {
            public boolean contains(Object obj) {
                return set.contains(obj) && set2.contains(obj);
            }

            public boolean containsAll(Collection<?> collection) {
                return set.containsAll(collection) && set2.containsAll(collection);
            }

            public boolean isEmpty() {
                return !iterator().hasNext();
            }

            public UnmodifiableIterator<E> iterator() {
                return Iterators.filter(set.iterator(), in);
            }

            public int size() {
                return Iterators.size(iterator());
            }
        };
    }

    private static <E extends Enum<E>> EnumSet<E> makeComplementByHand(Collection<E> collection, Class<E> cls) {
        EnumSet<E> allOf = EnumSet.allOf(cls);
        allOf.removeAll(collection);
        return allOf;
    }

    public static <E> Set<E> newConcurrentHashSet() {
        return Collections.newSetFromMap(new ConcurrentHashMap());
    }

    public static <E> Set<E> newConcurrentHashSet(Iterable<? extends E> iterable) {
        Set<E> newConcurrentHashSet = newConcurrentHashSet();
        Iterables.addAll(newConcurrentHashSet, iterable);
        return newConcurrentHashSet;
    }

    @GwtIncompatible
    public static <E> CopyOnWriteArraySet<E> newCopyOnWriteArraySet() {
        return new CopyOnWriteArraySet<>();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Iterable<? extends E>, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @com.google.common.annotations.GwtIncompatible
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <E> java.util.concurrent.CopyOnWriteArraySet<E> newCopyOnWriteArraySet(java.lang.Iterable<? extends E> r1) {
        /*
            boolean r0 = r1 instanceof java.util.Collection
            if (r0 == 0) goto L_0x0009
            java.util.Collection r1 = com.google.common.collect.Collections2.a(r1)
            goto L_0x000d
        L_0x0009:
            java.util.ArrayList r1 = com.google.common.collect.Lists.newArrayList(r1)
        L_0x000d:
            java.util.concurrent.CopyOnWriteArraySet r0 = new java.util.concurrent.CopyOnWriteArraySet
            r0.<init>(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Sets.newCopyOnWriteArraySet(java.lang.Iterable):java.util.concurrent.CopyOnWriteArraySet");
    }

    public static <E extends Enum<E>> EnumSet<E> newEnumSet(Iterable<E> iterable, Class<E> cls) {
        EnumSet<E> noneOf = EnumSet.noneOf(cls);
        Iterables.addAll(noneOf, iterable);
        return noneOf;
    }

    public static <E> HashSet<E> newHashSet() {
        return new HashSet<>();
    }

    public static <E> HashSet<E> newHashSet(Iterable<? extends E> iterable) {
        return iterable instanceof Collection ? new HashSet<>(Collections2.a(iterable)) : newHashSet(iterable.iterator());
    }

    public static <E> HashSet<E> newHashSet(Iterator<? extends E> it) {
        HashSet<E> newHashSet = newHashSet();
        Iterators.addAll(newHashSet, it);
        return newHashSet;
    }

    public static <E> HashSet<E> newHashSet(E... eArr) {
        HashSet<E> newHashSetWithExpectedSize = newHashSetWithExpectedSize(eArr.length);
        Collections.addAll(newHashSetWithExpectedSize, eArr);
        return newHashSetWithExpectedSize;
    }

    public static <E> HashSet<E> newHashSetWithExpectedSize(int i) {
        return new HashSet<>(Maps.a(i));
    }

    public static <E> Set<E> newIdentityHashSet() {
        return Collections.newSetFromMap(Maps.newIdentityHashMap());
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet() {
        return new LinkedHashSet<>();
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            return new LinkedHashSet<>(Collections2.a(iterable));
        }
        LinkedHashSet<E> newLinkedHashSet = newLinkedHashSet();
        Iterables.addAll(newLinkedHashSet, iterable);
        return newLinkedHashSet;
    }

    public static <E> LinkedHashSet<E> newLinkedHashSetWithExpectedSize(int i) {
        return new LinkedHashSet<>(Maps.a(i));
    }

    @Deprecated
    public static <E> Set<E> newSetFromMap(Map<E, Boolean> map) {
        return Collections.newSetFromMap(map);
    }

    public static <E extends Comparable> TreeSet<E> newTreeSet() {
        return new TreeSet<>();
    }

    public static <E extends Comparable> TreeSet<E> newTreeSet(Iterable<? extends E> iterable) {
        TreeSet<E> newTreeSet = newTreeSet();
        Iterables.addAll(newTreeSet, iterable);
        return newTreeSet;
    }

    public static <E> TreeSet<E> newTreeSet(Comparator<? super E> comparator) {
        return new TreeSet<>((Comparator) Preconditions.checkNotNull(comparator));
    }

    @GwtCompatible(serializable = false)
    public static <E> Set<Set<E>> powerSet(Set<E> set) {
        return new PowerSet(set);
    }

    @GwtIncompatible
    @Beta
    public static <K extends Comparable<? super K>> NavigableSet<K> subSet(NavigableSet<K> navigableSet, Range<K> range) {
        boolean z = true;
        if (navigableSet.comparator() != null && navigableSet.comparator() != Ordering.natural() && range.hasLowerBound() && range.hasUpperBound()) {
            Preconditions.checkArgument(navigableSet.comparator().compare(range.lowerEndpoint(), range.upperEndpoint()) <= 0, "set is using a custom comparator which is inconsistent with the natural ordering.");
        }
        if (range.hasLowerBound() && range.hasUpperBound()) {
            K lowerEndpoint = range.lowerEndpoint();
            boolean z2 = range.lowerBoundType() == BoundType.CLOSED;
            K upperEndpoint = range.upperEndpoint();
            if (range.upperBoundType() != BoundType.CLOSED) {
                z = false;
            }
            return navigableSet.subSet(lowerEndpoint, z2, upperEndpoint, z);
        } else if (range.hasLowerBound()) {
            K lowerEndpoint2 = range.lowerEndpoint();
            if (range.lowerBoundType() != BoundType.CLOSED) {
                z = false;
            }
            return navigableSet.tailSet(lowerEndpoint2, z);
        } else if (!range.hasUpperBound()) {
            return (NavigableSet) Preconditions.checkNotNull(navigableSet);
        } else {
            K upperEndpoint2 = range.upperEndpoint();
            if (range.upperBoundType() != BoundType.CLOSED) {
                z = false;
            }
            return navigableSet.headSet(upperEndpoint2, z);
        }
    }

    public static <E> SetView<E> symmetricDifference(final Set<? extends E> set, final Set<? extends E> set2) {
        Preconditions.checkNotNull(set, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new SetView<E>() {
            public boolean contains(Object obj) {
                return set2.contains(obj) ^ set.contains(obj);
            }

            public boolean isEmpty() {
                return set.equals(set2);
            }

            public UnmodifiableIterator<E> iterator() {
                final Iterator it = set.iterator();
                final Iterator it2 = set2.iterator();
                return new AbstractIterator<E>() {
                    public E computeNext() {
                        while (it.hasNext()) {
                            E next = it.next();
                            if (!set2.contains(next)) {
                                return next;
                            }
                        }
                        while (it2.hasNext()) {
                            E next2 = it2.next();
                            if (!set.contains(next2)) {
                                return next2;
                            }
                        }
                        return a();
                    }
                };
            }

            public int size() {
                return Iterators.size(iterator());
            }
        };
    }

    @GwtIncompatible
    public static <E> NavigableSet<E> synchronizedNavigableSet(NavigableSet<E> navigableSet) {
        return Synchronized.a(navigableSet);
    }

    public static <E> SetView<E> union(final Set<? extends E> set, final Set<? extends E> set2) {
        Preconditions.checkNotNull(set, "set1");
        Preconditions.checkNotNull(set2, "set2");
        final SetView<? extends E> difference = difference(set2, set);
        return new SetView<E>() {
            public boolean contains(Object obj) {
                return set.contains(obj) || set2.contains(obj);
            }

            public <S extends Set<E>> S copyInto(S s) {
                s.addAll(set);
                s.addAll(set2);
                return s;
            }

            public ImmutableSet<E> immutableCopy() {
                return new ImmutableSet.Builder().addAll((Iterable) set).addAll((Iterable) set2).build();
            }

            public boolean isEmpty() {
                return set.isEmpty() && set2.isEmpty();
            }

            public UnmodifiableIterator<E> iterator() {
                return Iterators.unmodifiableIterator(Iterators.concat(set.iterator(), difference.iterator()));
            }

            public int size() {
                return IntMath.saturatedAdd(set.size(), difference.size());
            }
        };
    }

    @GwtIncompatible
    public static <E> NavigableSet<E> unmodifiableNavigableSet(NavigableSet<E> navigableSet) {
        return ((navigableSet instanceof ImmutableSortedSet) || (navigableSet instanceof UnmodifiableNavigableSet)) ? navigableSet : new UnmodifiableNavigableSet(navigableSet);
    }
}

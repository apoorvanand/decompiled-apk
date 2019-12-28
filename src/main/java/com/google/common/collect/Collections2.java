package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.math.IntMath;
import com.google.common.math.LongMath;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

@GwtCompatible
public final class Collections2 {
    static final Joiner a = Joiner.on(", ").useForNull("null");

    static class FilteredCollection<E> extends AbstractCollection<E> {
        final Collection<E> a;
        final Predicate<? super E> b;

        FilteredCollection(Collection<E> collection, Predicate<? super E> predicate) {
            this.a = collection;
            this.b = predicate;
        }

        /* access modifiers changed from: package-private */
        public FilteredCollection<E> a(Predicate<? super E> predicate) {
            return new FilteredCollection<>(this.a, Predicates.and(this.b, predicate));
        }

        public boolean add(E e) {
            Preconditions.checkArgument(this.b.apply(e));
            return this.a.add(e);
        }

        public boolean addAll(Collection<? extends E> collection) {
            for (Object apply : collection) {
                Preconditions.checkArgument(this.b.apply(apply));
            }
            return this.a.addAll(collection);
        }

        public void clear() {
            Iterables.removeIf(this.a, this.b);
        }

        public boolean contains(@Nullable Object obj) {
            if (Collections2.a((Collection<?>) this.a, obj)) {
                return this.b.apply(obj);
            }
            return false;
        }

        public boolean containsAll(Collection<?> collection) {
            return Collections2.a((Collection<?>) this, collection);
        }

        public boolean isEmpty() {
            return !Iterables.any(this.a, this.b);
        }

        public Iterator<E> iterator() {
            return Iterators.filter(this.a.iterator(), this.b);
        }

        public boolean remove(Object obj) {
            return contains(obj) && this.a.remove(obj);
        }

        public boolean removeAll(Collection<?> collection) {
            return Iterables.removeIf(this.a, Predicates.and(this.b, Predicates.in(collection)));
        }

        public boolean retainAll(Collection<?> collection) {
            return Iterables.removeIf(this.a, Predicates.and(this.b, Predicates.not(Predicates.in(collection))));
        }

        public int size() {
            return Iterators.size(iterator());
        }

        public Object[] toArray() {
            return Lists.newArrayList(iterator()).toArray();
        }

        public <T> T[] toArray(T[] tArr) {
            return Lists.newArrayList(iterator()).toArray(tArr);
        }
    }

    private static final class OrderedPermutationCollection<E> extends AbstractCollection<List<E>> {
        final ImmutableList<E> a;
        final Comparator<? super E> b;
        final int c;

        OrderedPermutationCollection(Iterable<E> iterable, Comparator<? super E> comparator) {
            this.a = Ordering.from(comparator).immutableSortedCopy(iterable);
            this.b = comparator;
            this.c = calculateSize(this.a, comparator);
        }

        private static <E> int calculateSize(List<E> list, Comparator<? super E> comparator) {
            long j = 1;
            int i = 1;
            int i2 = 1;
            while (i < list.size()) {
                if (comparator.compare(list.get(i - 1), list.get(i)) < 0) {
                    j *= LongMath.binomial(i, i2);
                    i2 = 0;
                    if (!Collections2.isPositiveInt(j)) {
                        return Integer.MAX_VALUE;
                    }
                }
                i++;
                i2++;
            }
            long binomial = j * LongMath.binomial(i, i2);
            if (!Collections2.isPositiveInt(binomial)) {
                return Integer.MAX_VALUE;
            }
            return (int) binomial;
        }

        public boolean contains(@Nullable Object obj) {
            if (!(obj instanceof List)) {
                return false;
            }
            return Collections2.isPermutation(this.a, (List) obj);
        }

        public boolean isEmpty() {
            return false;
        }

        public Iterator<List<E>> iterator() {
            return new OrderedPermutationIterator(this.a, this.b);
        }

        public int size() {
            return this.c;
        }

        public String toString() {
            return "orderedPermutationCollection(" + this.a + ")";
        }
    }

    private static final class OrderedPermutationIterator<E> extends AbstractIterator<List<E>> {
        List<E> a;
        final Comparator<? super E> b;

        OrderedPermutationIterator(List<E> list, Comparator<? super E> comparator) {
            this.a = Lists.newArrayList(list);
            this.b = comparator;
        }

        /* access modifiers changed from: package-private */
        public int a(int i) {
            E e = this.a.get(i);
            for (int size = this.a.size() - 1; size > i; size--) {
                if (this.b.compare(e, this.a.get(size)) < 0) {
                    return size;
                }
            }
            throw new AssertionError("this statement should be unreachable");
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public List<E> computeNext() {
            List<E> list = this.a;
            if (list == null) {
                return (List) a();
            }
            ImmutableList<E> copyOf = ImmutableList.copyOf(list);
            c();
            return copyOf;
        }

        /* access modifiers changed from: package-private */
        public void c() {
            int d = d();
            if (d == -1) {
                this.a = null;
                return;
            }
            Collections.swap(this.a, d, a(d));
            Collections.reverse(this.a.subList(d + 1, this.a.size()));
        }

        /* access modifiers changed from: package-private */
        public int d() {
            for (int size = this.a.size() - 2; size >= 0; size--) {
                if (this.b.compare(this.a.get(size), this.a.get(size + 1)) < 0) {
                    return size;
                }
            }
            return -1;
        }
    }

    private static final class PermutationCollection<E> extends AbstractCollection<List<E>> {
        final ImmutableList<E> a;

        PermutationCollection(ImmutableList<E> immutableList) {
            this.a = immutableList;
        }

        public boolean contains(@Nullable Object obj) {
            if (!(obj instanceof List)) {
                return false;
            }
            return Collections2.isPermutation(this.a, (List) obj);
        }

        public boolean isEmpty() {
            return false;
        }

        public Iterator<List<E>> iterator() {
            return new PermutationIterator(this.a);
        }

        public int size() {
            return IntMath.factorial(this.a.size());
        }

        public String toString() {
            return "permutations(" + this.a + ")";
        }
    }

    private static class PermutationIterator<E> extends AbstractIterator<List<E>> {
        final List<E> a;
        final int[] b;
        final int[] c;
        int d = Integer.MAX_VALUE;

        PermutationIterator(List<E> list) {
            this.a = new ArrayList(list);
            int size = list.size();
            this.b = new int[size];
            this.c = new int[size];
            Arrays.fill(this.b, 0);
            Arrays.fill(this.c, 1);
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public List<E> computeNext() {
            if (this.d <= 0) {
                return (List) a();
            }
            ImmutableList<E> copyOf = ImmutableList.copyOf(this.a);
            c();
            return copyOf;
        }

        /* access modifiers changed from: package-private */
        public void c() {
            this.d = this.a.size() - 1;
            if (this.d != -1) {
                int i = 0;
                while (true) {
                    int[] iArr = this.b;
                    int i2 = this.d;
                    int i3 = iArr[i2] + this.c[i2];
                    if (i3 >= 0) {
                        if (i3 != i2 + 1) {
                            Collections.swap(this.a, (i2 - iArr[i2]) + i, (i2 - i3) + i);
                            this.b[this.d] = i3;
                            return;
                        } else if (i2 != 0) {
                            i++;
                        } else {
                            return;
                        }
                    }
                    d();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void d() {
            int[] iArr = this.c;
            int i = this.d;
            iArr[i] = -iArr[i];
            this.d = i - 1;
        }
    }

    static class TransformedCollection<F, T> extends AbstractCollection<T> {
        final Collection<F> a;
        final Function<? super F, ? extends T> b;

        TransformedCollection(Collection<F> collection, Function<? super F, ? extends T> function) {
            this.a = (Collection) Preconditions.checkNotNull(collection);
            this.b = (Function) Preconditions.checkNotNull(function);
        }

        public void clear() {
            this.a.clear();
        }

        public boolean isEmpty() {
            return this.a.isEmpty();
        }

        public Iterator<T> iterator() {
            return Iterators.transform(this.a.iterator(), this.b);
        }

        public int size() {
            return this.a.size();
        }
    }

    private Collections2() {
    }

    static String a(final Collection<?> collection) {
        StringBuilder a2 = a(collection.size());
        a2.append('[');
        a.appendTo(a2, (Iterable<?>) Iterables.transform(collection, new Function<Object, Object>() {
            public Object apply(Object obj) {
                return obj == collection ? "(this Collection)" : obj;
            }
        }));
        a2.append(']');
        return a2.toString();
    }

    static StringBuilder a(int i) {
        CollectPreconditions.a(i, "size");
        return new StringBuilder((int) Math.min(((long) i) * 8, 1073741824));
    }

    static <T> Collection<T> a(Iterable<T> iterable) {
        return (Collection) iterable;
    }

    static boolean a(Collection<?> collection, @Nullable Object obj) {
        Preconditions.checkNotNull(collection);
        try {
            return collection.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    static boolean a(Collection<?> collection, Collection<?> collection2) {
        return Iterables.all(collection2, Predicates.in(collection));
    }

    static boolean b(Collection<?> collection, @Nullable Object obj) {
        Preconditions.checkNotNull(collection);
        try {
            return collection.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public static <E> Collection<E> filter(Collection<E> collection, Predicate<? super E> predicate) {
        return collection instanceof FilteredCollection ? ((FilteredCollection) collection).a(predicate) : new FilteredCollection((Collection) Preconditions.checkNotNull(collection), (Predicate) Preconditions.checkNotNull(predicate));
    }

    /* access modifiers changed from: private */
    public static boolean isPermutation(List<?> list, List<?> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        return HashMultiset.create(list).equals(HashMultiset.create(list2));
    }

    /* access modifiers changed from: private */
    public static boolean isPositiveInt(long j) {
        return j >= 0 && j <= 2147483647L;
    }

    @Beta
    public static <E extends Comparable<? super E>> Collection<List<E>> orderedPermutations(Iterable<E> iterable) {
        return orderedPermutations(iterable, Ordering.natural());
    }

    @Beta
    public static <E> Collection<List<E>> orderedPermutations(Iterable<E> iterable, Comparator<? super E> comparator) {
        return new OrderedPermutationCollection(iterable, comparator);
    }

    @Beta
    public static <E> Collection<List<E>> permutations(Collection<E> collection) {
        return new PermutationCollection(ImmutableList.copyOf(collection));
    }

    public static <F, T> Collection<T> transform(Collection<F> collection, Function<? super F, T> function) {
        return new TransformedCollection(collection, function);
    }
}

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableSortedSet<E> extends ImmutableSortedSetFauxverideShim<E> implements SortedIterable<E>, NavigableSet<E> {
    final transient Comparator<? super E> b;
    @GwtIncompatible
    @LazyInit
    transient ImmutableSortedSet<E> c;

    public static final class Builder<E> extends ImmutableSet.Builder<E> {
        private final Comparator<? super E> comparator;

        public Builder(Comparator<? super E> comparator2) {
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator2);
        }

        @CanIgnoreReturnValue
        public Builder<E> add(E e) {
            super.add((Object) e);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> add(E... eArr) {
            super.add((Object[]) eArr);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> iterable) {
            super.addAll((Iterable) iterable);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> it) {
            super.addAll((Iterator) it);
            return this;
        }

        public ImmutableSortedSet<E> build() {
            ImmutableSortedSet<E> a = ImmutableSortedSet.a(this.comparator, this.b, (E[]) this.a);
            this.b = a.size();
            return a;
        }
    }

    private static class SerializedForm<E> implements Serializable {
        private static final long serialVersionUID = 0;
        final Comparator<? super E> a;
        final Object[] b;

        public SerializedForm(Comparator<? super E> comparator, Object[] objArr) {
            this.a = comparator;
            this.b = objArr;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return new Builder(this.a).add(this.b).build();
        }
    }

    ImmutableSortedSet(Comparator<? super E> comparator) {
        this.b = comparator;
    }

    static int a(Comparator<?> comparator, Object obj, Object obj2) {
        return comparator.compare(obj, obj2);
    }

    static <E> ImmutableSortedSet<E> a(Comparator<? super E> comparator, int i, E... eArr) {
        if (i == 0) {
            return a(comparator);
        }
        ObjectArrays.b(eArr, i);
        Arrays.sort(eArr, 0, i, comparator);
        int i2 = 1;
        for (int i3 = 1; i3 < i; i3++) {
            E e = eArr[i3];
            if (comparator.compare(e, eArr[i2 - 1]) != 0) {
                eArr[i2] = e;
                i2++;
            }
        }
        Arrays.fill(eArr, i2, i, (Object) null);
        return new RegularImmutableSortedSet(ImmutableList.b(eArr, i2), comparator);
    }

    static <E> RegularImmutableSortedSet<E> a(Comparator<? super E> comparator) {
        return Ordering.natural().equals(comparator) ? RegularImmutableSortedSet.a : new RegularImmutableSortedSet<>(ImmutableList.of(), comparator);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Iterable<? extends E> iterable) {
        return copyOf(Ordering.natural(), iterable);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Collection<? extends E> collection) {
        return copyOf(Ordering.natural(), collection);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Comparator<? super E> comparator, Iterable<? extends E> iterable) {
        Preconditions.checkNotNull(comparator);
        if (SortedIterables.hasSameComparator(comparator, iterable) && (iterable instanceof ImmutableSortedSet)) {
            ImmutableSortedSet<E> immutableSortedSet = (ImmutableSortedSet) iterable;
            if (!immutableSortedSet.a()) {
                return immutableSortedSet;
            }
        }
        Object[] a = Iterables.a(iterable);
        return a(comparator, a.length, (E[]) a);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Comparator<? super E> comparator, Collection<? extends E> collection) {
        return copyOf(comparator, collection);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Comparator<? super E> comparator, Iterator<? extends E> it) {
        return new Builder(comparator).addAll((Iterator) it).build();
    }

    public static <E> ImmutableSortedSet<E> copyOf(Iterator<? extends E> it) {
        return copyOf(Ordering.natural(), it);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> copyOf(E[] eArr) {
        return a(Ordering.natural(), eArr.length, (E[]) (Object[]) eArr.clone());
    }

    public static <E> ImmutableSortedSet<E> copyOfSorted(SortedSet<E> sortedSet) {
        Comparator<? super E> comparator = SortedIterables.comparator(sortedSet);
        ImmutableList<E> copyOf = ImmutableList.copyOf(sortedSet);
        return copyOf.isEmpty() ? a(comparator) : new RegularImmutableSortedSet(copyOf, comparator);
    }

    public static <E extends Comparable<?>> Builder<E> naturalOrder() {
        return new Builder<>(Ordering.natural());
    }

    public static <E> ImmutableSortedSet<E> of() {
        return RegularImmutableSortedSet.a;
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e) {
        return new RegularImmutableSortedSet(ImmutableList.of(e), Ordering.natural());
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e, E e2) {
        return a(Ordering.natural(), 2, (E[]) new Comparable[]{e, e2});
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e, E e2, E e3) {
        return a(Ordering.natural(), 3, (E[]) new Comparable[]{e, e2, e3});
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e, E e2, E e3, E e4) {
        return a(Ordering.natural(), 4, (E[]) new Comparable[]{e, e2, e3, e4});
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e, E e2, E e3, E e4, E e5) {
        return a(Ordering.natural(), 5, (E[]) new Comparable[]{e, e2, e3, e4, e5});
    }

    public static <E extends Comparable<? super E>> ImmutableSortedSet<E> of(E e, E e2, E e3, E e4, E e5, E e6, E... eArr) {
        Comparable[] comparableArr = new Comparable[(eArr.length + 6)];
        comparableArr[0] = e;
        comparableArr[1] = e2;
        comparableArr[2] = e3;
        comparableArr[3] = e4;
        comparableArr[4] = e5;
        comparableArr[5] = e6;
        System.arraycopy(eArr, 0, comparableArr, 6, eArr.length);
        return a(Ordering.natural(), comparableArr.length, (E[]) comparableArr);
    }

    public static <E> Builder<E> orderedBy(Comparator<E> comparator) {
        return new Builder<>(comparator);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Use SerializedForm");
    }

    public static <E extends Comparable<?>> Builder<E> reverseOrder() {
        return new Builder<>(Ordering.natural().reverse());
    }

    /* access modifiers changed from: package-private */
    public abstract int a(@Nullable Object obj);

    /* access modifiers changed from: package-private */
    public int a(Object obj, Object obj2) {
        return a((Comparator<?>) this.b, obj, obj2);
    }

    /* access modifiers changed from: package-private */
    public abstract ImmutableSortedSet<E> a(E e, boolean z);

    /* access modifiers changed from: package-private */
    public abstract ImmutableSortedSet<E> a(E e, boolean z, E e2, boolean z2);

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public ImmutableSortedSet<E> b() {
        return new DescendingImmutableSortedSet(this);
    }

    /* access modifiers changed from: package-private */
    public abstract ImmutableSortedSet<E> b(E e, boolean z);

    @GwtIncompatible
    public E ceiling(E e) {
        return Iterables.getFirst(tailSet(e, true), null);
    }

    public Comparator<? super E> comparator() {
        return this.b;
    }

    @GwtIncompatible
    public abstract UnmodifiableIterator<E> descendingIterator();

    @GwtIncompatible
    public ImmutableSortedSet<E> descendingSet() {
        ImmutableSortedSet<E> immutableSortedSet = this.c;
        if (immutableSortedSet != null) {
            return immutableSortedSet;
        }
        ImmutableSortedSet<E> b2 = b();
        this.c = b2;
        b2.c = this;
        return b2;
    }

    public E first() {
        return iterator().next();
    }

    @GwtIncompatible
    public E floor(E e) {
        return Iterators.getNext(headSet(e, true).descendingIterator(), null);
    }

    public ImmutableSortedSet<E> headSet(E e) {
        return headSet(e, false);
    }

    @GwtIncompatible
    public ImmutableSortedSet<E> headSet(E e, boolean z) {
        return b(Preconditions.checkNotNull(e), z);
    }

    @GwtIncompatible
    public E higher(E e) {
        return Iterables.getFirst(tailSet(e, false), null);
    }

    public abstract UnmodifiableIterator<E> iterator();

    public E last() {
        return descendingIterator().next();
    }

    @GwtIncompatible
    public E lower(E e) {
        return Iterators.getNext(headSet(e, false).descendingIterator(), null);
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    @Deprecated
    public final E pollFirst() {
        throw new UnsupportedOperationException();
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    @Deprecated
    public final E pollLast() {
        throw new UnsupportedOperationException();
    }

    public ImmutableSortedSet<E> subSet(E e, E e2) {
        return subSet(e, true, e2, false);
    }

    @GwtIncompatible
    public ImmutableSortedSet<E> subSet(E e, boolean z, E e2, boolean z2) {
        Preconditions.checkNotNull(e);
        Preconditions.checkNotNull(e2);
        Preconditions.checkArgument(this.b.compare(e, e2) <= 0);
        return a(e, z, e2, z2);
    }

    public ImmutableSortedSet<E> tailSet(E e) {
        return tailSet(e, true);
    }

    @GwtIncompatible
    public ImmutableSortedSet<E> tailSet(E e, boolean z) {
        return a(Preconditions.checkNotNull(e), z);
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this.b, toArray());
    }
}

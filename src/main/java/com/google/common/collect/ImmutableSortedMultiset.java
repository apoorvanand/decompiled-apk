package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

@GwtIncompatible
public abstract class ImmutableSortedMultiset<E> extends ImmutableSortedMultisetFauxverideShim<E> implements SortedMultiset<E> {
    @LazyInit
    transient ImmutableSortedMultiset<E> a;

    public static class Builder<E> extends ImmutableMultiset.Builder<E> {
        public Builder(Comparator<? super E> comparator) {
            super(TreeMultiset.create((Comparator) Preconditions.checkNotNull(comparator)));
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

        @CanIgnoreReturnValue
        public Builder<E> addCopies(E e, int i) {
            super.addCopies(e, i);
            return this;
        }

        public ImmutableSortedMultiset<E> build() {
            return ImmutableSortedMultiset.copyOfSorted((SortedMultiset) this.a);
        }

        @CanIgnoreReturnValue
        public Builder<E> setCount(E e, int i) {
            super.setCount(e, i);
            return this;
        }
    }

    private static final class SerializedForm<E> implements Serializable {
        final Comparator<? super E> a;
        final E[] b;
        final int[] c;

        SerializedForm(SortedMultiset<E> sortedMultiset) {
            this.a = sortedMultiset.comparator();
            int size = sortedMultiset.entrySet().size();
            this.b = (Object[]) new Object[size];
            this.c = new int[size];
            int i = 0;
            for (Multiset.Entry next : sortedMultiset.entrySet()) {
                this.b[i] = next.getElement();
                this.c[i] = next.getCount();
                i++;
            }
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            int length = this.b.length;
            Builder builder = new Builder(this.a);
            for (int i = 0; i < length; i++) {
                builder.addCopies((Object) this.b[i], this.c[i]);
            }
            return builder.build();
        }
    }

    ImmutableSortedMultiset() {
    }

    static <E> ImmutableSortedMultiset<E> a(Comparator<? super E> comparator) {
        return Ordering.natural().equals(comparator) ? RegularImmutableSortedMultiset.b : new RegularImmutableSortedMultiset(comparator);
    }

    public static <E> ImmutableSortedMultiset<E> copyOf(Iterable<? extends E> iterable) {
        return copyOf(Ordering.natural(), iterable);
    }

    public static <E> ImmutableSortedMultiset<E> copyOf(Comparator<? super E> comparator, Iterable<? extends E> iterable) {
        Collection entrySet;
        if (iterable instanceof ImmutableSortedMultiset) {
            ImmutableSortedMultiset immutableSortedMultiset = (ImmutableSortedMultiset) iterable;
            if (comparator.equals(immutableSortedMultiset.comparator())) {
                if (!immutableSortedMultiset.a()) {
                    return immutableSortedMultiset;
                }
                entrySet = immutableSortedMultiset.entrySet().asList();
                return copyOfSortedEntries(comparator, entrySet);
            }
        }
        ArrayList<E> newArrayList = Lists.newArrayList(iterable);
        TreeMultiset create = TreeMultiset.create((Comparator) Preconditions.checkNotNull(comparator));
        Iterables.addAll(create, newArrayList);
        entrySet = create.entrySet();
        return copyOfSortedEntries(comparator, entrySet);
    }

    public static <E> ImmutableSortedMultiset<E> copyOf(Comparator<? super E> comparator, Iterator<? extends E> it) {
        Preconditions.checkNotNull(comparator);
        return new Builder(comparator).addAll((Iterator) it).build();
    }

    public static <E> ImmutableSortedMultiset<E> copyOf(Iterator<? extends E> it) {
        return copyOf(Ordering.natural(), it);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> copyOf(E[] eArr) {
        return copyOf(Ordering.natural(), Arrays.asList(eArr));
    }

    public static <E> ImmutableSortedMultiset<E> copyOfSorted(SortedMultiset<E> sortedMultiset) {
        return copyOfSortedEntries(sortedMultiset.comparator(), Lists.newArrayList(sortedMultiset.entrySet()));
    }

    private static <E> ImmutableSortedMultiset<E> copyOfSortedEntries(Comparator<? super E> comparator, Collection<Multiset.Entry<E>> collection) {
        if (collection.isEmpty()) {
            return a(comparator);
        }
        ImmutableList.Builder builder = new ImmutableList.Builder(collection.size());
        long[] jArr = new long[(collection.size() + 1)];
        int i = 0;
        for (Multiset.Entry next : collection) {
            builder.add(next.getElement());
            int i2 = i + 1;
            jArr[i2] = jArr[i] + ((long) next.getCount());
            i = i2;
        }
        return new RegularImmutableSortedMultiset(new RegularImmutableSortedSet(builder.build(), comparator), jArr, 0, collection.size());
    }

    public static <E extends Comparable<?>> Builder<E> naturalOrder() {
        return new Builder<>(Ordering.natural());
    }

    public static <E> ImmutableSortedMultiset<E> of() {
        return RegularImmutableSortedMultiset.b;
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> of(E e) {
        return new RegularImmutableSortedMultiset((RegularImmutableSortedSet) ImmutableSortedSet.of(e), new long[]{0, 1}, 0, 1);
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> of(E e, E e2) {
        return copyOf(Ordering.natural(), Arrays.asList(new Comparable[]{e, e2}));
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> of(E e, E e2, E e3) {
        return copyOf(Ordering.natural(), Arrays.asList(new Comparable[]{e, e2, e3}));
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> of(E e, E e2, E e3, E e4) {
        return copyOf(Ordering.natural(), Arrays.asList(new Comparable[]{e, e2, e3, e4}));
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> of(E e, E e2, E e3, E e4, E e5) {
        return copyOf(Ordering.natural(), Arrays.asList(new Comparable[]{e, e2, e3, e4, e5}));
    }

    public static <E extends Comparable<? super E>> ImmutableSortedMultiset<E> of(E e, E e2, E e3, E e4, E e5, E e6, E... eArr) {
        ArrayList newArrayListWithCapacity = Lists.newArrayListWithCapacity(eArr.length + 6);
        Collections.addAll(newArrayListWithCapacity, new Comparable[]{e, e2, e3, e4, e5, e6});
        Collections.addAll(newArrayListWithCapacity, eArr);
        return copyOf(Ordering.natural(), newArrayListWithCapacity);
    }

    public static <E> Builder<E> orderedBy(Comparator<E> comparator) {
        return new Builder<>(comparator);
    }

    public static <E extends Comparable<?>> Builder<E> reverseOrder() {
        return new Builder<>(Ordering.natural().reverse());
    }

    public final Comparator<? super E> comparator() {
        return elementSet().comparator();
    }

    public ImmutableSortedMultiset<E> descendingMultiset() {
        ImmutableSortedMultiset<E> immutableSortedMultiset = this.a;
        if (immutableSortedMultiset == null) {
            immutableSortedMultiset = isEmpty() ? a(Ordering.from(comparator()).reverse()) : new DescendingImmutableSortedMultiset<>(this);
            this.a = immutableSortedMultiset;
        }
        return immutableSortedMultiset;
    }

    public abstract ImmutableSortedSet<E> elementSet();

    public abstract ImmutableSortedMultiset<E> headMultiset(E e, BoundType boundType);

    @CanIgnoreReturnValue
    @Deprecated
    public final Multiset.Entry<E> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    @Deprecated
    public final Multiset.Entry<E> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    public ImmutableSortedMultiset<E> subMultiset(E e, BoundType boundType, E e2, BoundType boundType2) {
        Preconditions.checkArgument(comparator().compare(e, e2) <= 0, "Expected lowerBound <= upperBound but %s > %s", (Object) e, (Object) e2);
        return tailMultiset(e, boundType).headMultiset(e2, boundType2);
    }

    public abstract ImmutableSortedMultiset<E> tailMultiset(E e, BoundType boundType);

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}

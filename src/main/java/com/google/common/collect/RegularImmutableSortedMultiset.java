package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;
import java.util.Comparator;
import javax.annotation.Nullable;

@GwtIncompatible
final class RegularImmutableSortedMultiset<E> extends ImmutableSortedMultiset<E> {
    private static final long[] ZERO_CUMULATIVE_COUNTS = {0};
    static final ImmutableSortedMultiset<Comparable> b = new RegularImmutableSortedMultiset(Ordering.natural());
    private final transient long[] cumulativeCounts;
    private final transient RegularImmutableSortedSet<E> elementSet;
    private final transient int length;
    private final transient int offset;

    RegularImmutableSortedMultiset(RegularImmutableSortedSet<E> regularImmutableSortedSet, long[] jArr, int i, int i2) {
        this.elementSet = regularImmutableSortedSet;
        this.cumulativeCounts = jArr;
        this.offset = i;
        this.length = i2;
    }

    RegularImmutableSortedMultiset(Comparator<? super E> comparator) {
        this.elementSet = ImmutableSortedSet.a(comparator);
        this.cumulativeCounts = ZERO_CUMULATIVE_COUNTS;
        this.offset = 0;
        this.length = 0;
    }

    private int getCount(int i) {
        long[] jArr = this.cumulativeCounts;
        int i2 = this.offset;
        return (int) (jArr[(i2 + i) + 1] - jArr[i2 + i]);
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedMultiset<E> a(int i, int i2) {
        Preconditions.checkPositionIndexes(i, i2, this.length);
        return i == i2 ? a(comparator()) : (i == 0 && i2 == this.length) ? this : new RegularImmutableSortedMultiset(this.elementSet.a(i, i2), this.cumulativeCounts, this.offset + i, i2 - i);
    }

    /* access modifiers changed from: package-private */
    public Multiset.Entry<E> a(int i) {
        return Multisets.immutableEntry(this.elementSet.asList().get(i), getCount(i));
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.offset > 0 || this.length < this.cumulativeCounts.length - 1;
    }

    public int count(@Nullable Object obj) {
        int a = this.elementSet.a(obj);
        if (a >= 0) {
            return getCount(a);
        }
        return 0;
    }

    public ImmutableSortedSet<E> elementSet() {
        return this.elementSet;
    }

    public Multiset.Entry<E> firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return a(0);
    }

    public ImmutableSortedMultiset<E> headMultiset(E e, BoundType boundType) {
        return a(0, this.elementSet.c(e, Preconditions.checkNotNull(boundType) == BoundType.CLOSED));
    }

    public Multiset.Entry<E> lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return a(this.length - 1);
    }

    public int size() {
        long[] jArr = this.cumulativeCounts;
        int i = this.offset;
        return Ints.saturatedCast(jArr[this.length + i] - jArr[i]);
    }

    public ImmutableSortedMultiset<E> tailMultiset(E e, BoundType boundType) {
        return a(this.elementSet.d(e, Preconditions.checkNotNull(boundType) == BoundType.CLOSED), this.length);
    }
}

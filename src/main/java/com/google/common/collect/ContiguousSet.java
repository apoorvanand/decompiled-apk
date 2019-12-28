package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSortedSet;
import java.lang.Comparable;
import java.util.NoSuchElementException;

@GwtCompatible(emulated = true)
public abstract class ContiguousSet<C extends Comparable> extends ImmutableSortedSet<C> {
    final DiscreteDomain<C> a;

    ContiguousSet(DiscreteDomain<C> discreteDomain) {
        super(Ordering.natural());
        this.a = discreteDomain;
    }

    @Deprecated
    public static <E> ImmutableSortedSet.Builder<E> builder() {
        throw new UnsupportedOperationException();
    }

    public static <C extends Comparable> ContiguousSet<C> create(Range<C> range, DiscreteDomain<C> discreteDomain) {
        Preconditions.checkNotNull(range);
        Preconditions.checkNotNull(discreteDomain);
        try {
            Range<C> intersection = !range.hasLowerBound() ? range.intersection(Range.atLeast(discreteDomain.minValue())) : range;
            if (!range.hasUpperBound()) {
                intersection = intersection.intersection(Range.atMost(discreteDomain.maxValue()));
            }
            return intersection.isEmpty() || Range.a((Comparable) range.b.a(discreteDomain), (Comparable) range.c.b(discreteDomain)) > 0 ? new EmptyContiguousSet(discreteDomain) : new RegularContiguousSet(intersection, discreteDomain);
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract ContiguousSet<C> b(C c, boolean z);

    /* access modifiers changed from: package-private */
    public abstract ContiguousSet<C> a(C c, boolean z, C c2, boolean z2);

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public abstract ContiguousSet<C> a(C c, boolean z);

    public ContiguousSet<C> headSet(C c) {
        return b((Comparable) Preconditions.checkNotNull(c), false);
    }

    @GwtIncompatible
    public ContiguousSet<C> headSet(C c, boolean z) {
        return b((Comparable) Preconditions.checkNotNull(c), z);
    }

    public abstract ContiguousSet<C> intersection(ContiguousSet<C> contiguousSet);

    public abstract Range<C> range();

    public abstract Range<C> range(BoundType boundType, BoundType boundType2);

    public ContiguousSet<C> subSet(C c, C c2) {
        Preconditions.checkNotNull(c);
        Preconditions.checkNotNull(c2);
        Preconditions.checkArgument(comparator().compare(c, c2) <= 0);
        return a(c, true, c2, false);
    }

    @GwtIncompatible
    public ContiguousSet<C> subSet(C c, boolean z, C c2, boolean z2) {
        Preconditions.checkNotNull(c);
        Preconditions.checkNotNull(c2);
        Preconditions.checkArgument(comparator().compare(c, c2) <= 0);
        return a(c, z, c2, z2);
    }

    public ContiguousSet<C> tailSet(C c) {
        return a((Comparable) Preconditions.checkNotNull(c), true);
    }

    @GwtIncompatible
    public ContiguousSet<C> tailSet(C c, boolean z) {
        return a((Comparable) Preconditions.checkNotNull(c), z);
    }

    public String toString() {
        return range().toString();
    }
}

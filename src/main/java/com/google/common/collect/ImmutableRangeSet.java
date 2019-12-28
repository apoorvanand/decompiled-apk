package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.SortedLists;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

@GwtIncompatible
@Beta
public final class ImmutableRangeSet<C extends Comparable> extends AbstractRangeSet<C> implements Serializable {
    private static final ImmutableRangeSet<Comparable<?>> ALL = new ImmutableRangeSet<>(ImmutableList.of(Range.all()));
    private static final ImmutableRangeSet<Comparable<?>> EMPTY = new ImmutableRangeSet<>(ImmutableList.of());
    @LazyInit
    private transient ImmutableRangeSet<C> complement;
    /* access modifiers changed from: private */
    public final transient ImmutableList<Range<C>> ranges;

    private final class AsSet extends ImmutableSortedSet<C> {
        /* access modifiers changed from: private */
        public final DiscreteDomain<C> domain;
        private transient Integer size;

        AsSet(DiscreteDomain<C> discreteDomain) {
            super(Ordering.natural());
            this.domain = discreteDomain;
        }

        /* access modifiers changed from: package-private */
        public int a(Object obj) {
            if (!contains(obj)) {
                return -1;
            }
            Comparable comparable = (Comparable) obj;
            long j = 0;
            Iterator it = ImmutableRangeSet.this.ranges.iterator();
            while (it.hasNext()) {
                Range range = (Range) it.next();
                if (range.contains(comparable)) {
                    return Ints.saturatedCast(j + ((long) ContiguousSet.create(range, this.domain).a((Object) comparable)));
                }
                j += (long) ContiguousSet.create(range, this.domain).size();
            }
            throw new AssertionError("impossible");
        }

        /* access modifiers changed from: package-private */
        public ImmutableSortedSet<C> a(Range<C> range) {
            return ImmutableRangeSet.this.subRangeSet(range).asSet(this.domain);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public ImmutableSortedSet<C> b(C c, boolean z) {
            return a(Range.upTo(c, BoundType.a(z)));
        }

        /* access modifiers changed from: package-private */
        public ImmutableSortedSet<C> a(C c, boolean z, C c2, boolean z2) {
            return (z || z2 || Range.a((Comparable) c, (Comparable) c2) != 0) ? a(Range.range(c, BoundType.a(z), c2, BoundType.a(z2))) : ImmutableSortedSet.of();
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return ImmutableRangeSet.this.ranges.a();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public ImmutableSortedSet<C> a(C c, boolean z) {
            return a(Range.downTo(c, BoundType.a(z)));
        }

        public boolean contains(@Nullable Object obj) {
            if (obj == null) {
                return false;
            }
            try {
                return ImmutableRangeSet.this.contains((Comparable) obj);
            } catch (ClassCastException unused) {
                return false;
            }
        }

        @GwtIncompatible("NavigableSet")
        public UnmodifiableIterator<C> descendingIterator() {
            return new AbstractIterator<C>() {
                final Iterator<Range<C>> a = ImmutableRangeSet.this.ranges.reverse().iterator();
                Iterator<C> b = Iterators.a();

                /* access modifiers changed from: protected */
                /* renamed from: b */
                public C computeNext() {
                    C next;
                    while (true) {
                        if (!this.b.hasNext()) {
                            if (!this.a.hasNext()) {
                                next = a();
                                break;
                            }
                            this.b = ContiguousSet.create(this.a.next(), AsSet.this.domain).descendingIterator();
                        } else {
                            next = this.b.next();
                            break;
                        }
                    }
                    return (Comparable) next;
                }
            };
        }

        public UnmodifiableIterator<C> iterator() {
            return new AbstractIterator<C>() {
                final Iterator<Range<C>> a = ImmutableRangeSet.this.ranges.iterator();
                Iterator<C> b = Iterators.a();

                /* access modifiers changed from: protected */
                /* renamed from: b */
                public C computeNext() {
                    C next;
                    while (true) {
                        if (!this.b.hasNext()) {
                            if (!this.a.hasNext()) {
                                next = a();
                                break;
                            }
                            this.b = ContiguousSet.create(this.a.next(), AsSet.this.domain).iterator();
                        } else {
                            next = this.b.next();
                            break;
                        }
                    }
                    return (Comparable) next;
                }
            };
        }

        public int size() {
            Integer num = this.size;
            if (num == null) {
                long j = 0;
                Iterator it = ImmutableRangeSet.this.ranges.iterator();
                while (it.hasNext()) {
                    j += (long) ContiguousSet.create((Range) it.next(), this.domain).size();
                    if (j >= 2147483647L) {
                        break;
                    }
                }
                num = Integer.valueOf(Ints.saturatedCast(j));
                this.size = num;
            }
            return num.intValue();
        }

        public String toString() {
            return ImmutableRangeSet.this.ranges.toString();
        }

        /* access modifiers changed from: package-private */
        public Object writeReplace() {
            return new AsSetSerializedForm(ImmutableRangeSet.this.ranges, this.domain);
        }
    }

    private static class AsSetSerializedForm<C extends Comparable> implements Serializable {
        private final DiscreteDomain<C> domain;
        private final ImmutableList<Range<C>> ranges;

        AsSetSerializedForm(ImmutableList<Range<C>> immutableList, DiscreteDomain<C> discreteDomain) {
            this.ranges = immutableList;
            this.domain = discreteDomain;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return new ImmutableRangeSet(this.ranges).asSet(this.domain);
        }
    }

    public static class Builder<C extends Comparable<?>> {
        private final RangeSet<C> rangeSet = TreeRangeSet.create();

        @CanIgnoreReturnValue
        public Builder<C> add(Range<C> range) {
            if (range.isEmpty()) {
                throw new IllegalArgumentException("range must not be empty, but was " + range);
            } else if (!this.rangeSet.complement().encloses(range)) {
                for (Range next : this.rangeSet.asRanges()) {
                    Preconditions.checkArgument(!next.isConnected(range) || next.intersection(range).isEmpty(), "Ranges may not overlap, but received %s and %s", (Object) next, (Object) range);
                }
                throw new AssertionError("should have thrown an IAE above");
            } else {
                this.rangeSet.add(range);
                return this;
            }
        }

        @CanIgnoreReturnValue
        public Builder<C> addAll(RangeSet<C> rangeSet2) {
            for (Range<C> add : rangeSet2.asRanges()) {
                add(add);
            }
            return this;
        }

        public ImmutableRangeSet<C> build() {
            return ImmutableRangeSet.copyOf(this.rangeSet);
        }
    }

    private final class ComplementRanges extends ImmutableList<Range<C>> {
        private final boolean positiveBoundedAbove;
        private final boolean positiveBoundedBelow;
        private final int size;

        ComplementRanges() {
            this.positiveBoundedBelow = ((Range) ImmutableRangeSet.this.ranges.get(0)).hasLowerBound();
            this.positiveBoundedAbove = ((Range) Iterables.getLast(ImmutableRangeSet.this.ranges)).hasUpperBound();
            int size2 = ImmutableRangeSet.this.ranges.size() - 1;
            size2 = this.positiveBoundedBelow ? size2 + 1 : size2;
            this.size = this.positiveBoundedAbove ? size2 + 1 : size2;
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x0035  */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.common.collect.Range<C> get(int r4) {
            /*
                r3 = this;
                int r0 = r3.size
                com.google.common.base.Preconditions.checkElementIndex(r4, r0)
                boolean r0 = r3.positiveBoundedBelow
                if (r0 == 0) goto L_0x001d
                if (r4 != 0) goto L_0x0010
                com.google.common.collect.Cut r0 = com.google.common.collect.Cut.d()
                goto L_0x002b
            L_0x0010:
                com.google.common.collect.ImmutableRangeSet r0 = com.google.common.collect.ImmutableRangeSet.this
                com.google.common.collect.ImmutableList r0 = r0.ranges
                int r1 = r4 + -1
                java.lang.Object r0 = r0.get(r1)
                goto L_0x0027
            L_0x001d:
                com.google.common.collect.ImmutableRangeSet r0 = com.google.common.collect.ImmutableRangeSet.this
                com.google.common.collect.ImmutableList r0 = r0.ranges
                java.lang.Object r0 = r0.get(r4)
            L_0x0027:
                com.google.common.collect.Range r0 = (com.google.common.collect.Range) r0
                com.google.common.collect.Cut<C> r0 = r0.c
            L_0x002b:
                boolean r1 = r3.positiveBoundedAbove
                if (r1 == 0) goto L_0x003a
                int r1 = r3.size
                int r1 = r1 + -1
                if (r4 != r1) goto L_0x003a
                com.google.common.collect.Cut r4 = com.google.common.collect.Cut.e()
                goto L_0x004d
            L_0x003a:
                com.google.common.collect.ImmutableRangeSet r1 = com.google.common.collect.ImmutableRangeSet.this
                com.google.common.collect.ImmutableList r1 = r1.ranges
                boolean r2 = r3.positiveBoundedBelow
                r2 = r2 ^ 1
                int r4 = r4 + r2
                java.lang.Object r4 = r1.get(r4)
                com.google.common.collect.Range r4 = (com.google.common.collect.Range) r4
                com.google.common.collect.Cut<C> r4 = r4.b
            L_0x004d:
                com.google.common.collect.Range r4 = com.google.common.collect.Range.a(r0, r4)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ImmutableRangeSet.ComplementRanges.get(int):com.google.common.collect.Range");
        }

        public int size() {
            return this.size;
        }
    }

    private static final class SerializedForm<C extends Comparable> implements Serializable {
        private final ImmutableList<Range<C>> ranges;

        SerializedForm(ImmutableList<Range<C>> immutableList) {
            this.ranges = immutableList;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.ranges.isEmpty() ? ImmutableRangeSet.of() : this.ranges.equals(ImmutableList.of(Range.all())) ? ImmutableRangeSet.a() : new ImmutableRangeSet(this.ranges);
        }
    }

    ImmutableRangeSet(ImmutableList<Range<C>> immutableList) {
        this.ranges = immutableList;
    }

    private ImmutableRangeSet(ImmutableList<Range<C>> immutableList, ImmutableRangeSet<C> immutableRangeSet) {
        this.ranges = immutableList;
        this.complement = immutableRangeSet;
    }

    static <C extends Comparable> ImmutableRangeSet<C> a() {
        return ALL;
    }

    public static <C extends Comparable<?>> Builder<C> builder() {
        return new Builder<>();
    }

    public static <C extends Comparable> ImmutableRangeSet<C> copyOf(RangeSet<C> rangeSet) {
        Preconditions.checkNotNull(rangeSet);
        if (rangeSet.isEmpty()) {
            return of();
        }
        if (rangeSet.encloses(Range.all())) {
            return a();
        }
        if (rangeSet instanceof ImmutableRangeSet) {
            ImmutableRangeSet<C> immutableRangeSet = (ImmutableRangeSet) rangeSet;
            if (!immutableRangeSet.b()) {
                return immutableRangeSet;
            }
        }
        return new ImmutableRangeSet<>(ImmutableList.copyOf(rangeSet.asRanges()));
    }

    private ImmutableList<Range<C>> intersectRanges(final Range<C> range) {
        if (this.ranges.isEmpty() || range.isEmpty()) {
            return ImmutableList.of();
        }
        if (range.encloses(span())) {
            return this.ranges;
        }
        final int binarySearch = range.hasLowerBound() ? SortedLists.binarySearch(this.ranges, Range.b(), range.b, SortedLists.KeyPresentBehavior.FIRST_AFTER, SortedLists.KeyAbsentBehavior.NEXT_HIGHER) : 0;
        final int binarySearch2 = (range.hasUpperBound() ? SortedLists.binarySearch(this.ranges, Range.a(), range.c, SortedLists.KeyPresentBehavior.FIRST_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER) : this.ranges.size()) - binarySearch;
        return binarySearch2 == 0 ? ImmutableList.of() : new ImmutableList<Range<C>>() {
            /* access modifiers changed from: package-private */
            public boolean a() {
                return true;
            }

            public Range<C> get(int i) {
                Preconditions.checkElementIndex(i, binarySearch2);
                return (i == 0 || i == binarySearch2 + -1) ? ((Range) ImmutableRangeSet.this.ranges.get(i + binarySearch)).intersection(range) : (Range) ImmutableRangeSet.this.ranges.get(i + binarySearch);
            }

            public int size() {
                return binarySearch2;
            }
        };
    }

    public static <C extends Comparable> ImmutableRangeSet<C> of() {
        return EMPTY;
    }

    public static <C extends Comparable> ImmutableRangeSet<C> of(Range<C> range) {
        Preconditions.checkNotNull(range);
        return range.isEmpty() ? of() : range.equals(Range.all()) ? a() : new ImmutableRangeSet<>(ImmutableList.of(range));
    }

    @Deprecated
    public void add(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void addAll(RangeSet<C> rangeSet) {
        throw new UnsupportedOperationException();
    }

    public ImmutableSet<Range<C>> asDescendingSetOfRanges() {
        return this.ranges.isEmpty() ? ImmutableSet.of() : new RegularImmutableSortedSet(this.ranges.reverse(), Range.a.reverse());
    }

    public ImmutableSet<Range<C>> asRanges() {
        return this.ranges.isEmpty() ? ImmutableSet.of() : new RegularImmutableSortedSet(this.ranges, Range.a);
    }

    public ImmutableSortedSet<C> asSet(DiscreteDomain<C> discreteDomain) {
        Preconditions.checkNotNull(discreteDomain);
        if (isEmpty()) {
            return ImmutableSortedSet.of();
        }
        Range<C> canonical = span().canonical(discreteDomain);
        if (canonical.hasLowerBound()) {
            if (!canonical.hasUpperBound()) {
                try {
                    discreteDomain.maxValue();
                } catch (NoSuchElementException unused) {
                    throw new IllegalArgumentException("Neither the DiscreteDomain nor this range set are bounded above");
                }
            }
            return new AsSet(discreteDomain);
        }
        throw new IllegalArgumentException("Neither the DiscreteDomain nor this range set are bounded below");
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.ranges.a();
    }

    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    public ImmutableRangeSet<C> complement() {
        ImmutableRangeSet<C> of;
        ImmutableRangeSet<C> immutableRangeSet = this.complement;
        if (immutableRangeSet != null) {
            return immutableRangeSet;
        }
        if (this.ranges.isEmpty()) {
            of = a();
        } else if (this.ranges.size() != 1 || !((Range) this.ranges.get(0)).equals(Range.all())) {
            ImmutableRangeSet<C> immutableRangeSet2 = new ImmutableRangeSet<>(new ComplementRanges(), this);
            this.complement = immutableRangeSet2;
            return immutableRangeSet2;
        } else {
            of = of();
        }
        this.complement = of;
        return of;
    }

    public /* bridge */ /* synthetic */ boolean contains(Comparable comparable) {
        return super.contains(comparable);
    }

    public boolean encloses(Range<C> range) {
        int binarySearch = SortedLists.binarySearch(this.ranges, Range.a(), range.b, Ordering.natural(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        return binarySearch != -1 && ((Range) this.ranges.get(binarySearch)).encloses(range);
    }

    public /* bridge */ /* synthetic */ boolean enclosesAll(RangeSet rangeSet) {
        return super.enclosesAll(rangeSet);
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public boolean intersects(Range<C> range) {
        int binarySearch = SortedLists.binarySearch(this.ranges, Range.a(), range.b, Ordering.natural(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
        if (binarySearch < this.ranges.size() && ((Range) this.ranges.get(binarySearch)).isConnected(range) && !((Range) this.ranges.get(binarySearch)).intersection(range).isEmpty()) {
            return true;
        }
        if (binarySearch > 0) {
            int i = binarySearch - 1;
            return ((Range) this.ranges.get(i)).isConnected(range) && !((Range) this.ranges.get(i)).intersection(range).isEmpty();
        }
    }

    public boolean isEmpty() {
        return this.ranges.isEmpty();
    }

    public Range<C> rangeContaining(C c) {
        int binarySearch = SortedLists.binarySearch(this.ranges, Range.a(), Cut.b(c), Ordering.natural(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        if (binarySearch == -1) {
            return null;
        }
        Range<C> range = (Range) this.ranges.get(binarySearch);
        if (range.contains(c)) {
            return range;
        }
        return null;
    }

    @Deprecated
    public void remove(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void removeAll(RangeSet<C> rangeSet) {
        throw new UnsupportedOperationException();
    }

    public Range<C> span() {
        if (!this.ranges.isEmpty()) {
            Cut<C> cut = ((Range) this.ranges.get(0)).b;
            ImmutableList<Range<C>> immutableList = this.ranges;
            return Range.a(cut, ((Range) immutableList.get(immutableList.size() - 1)).c);
        }
        throw new NoSuchElementException();
    }

    public ImmutableRangeSet<C> subRangeSet(Range<C> range) {
        if (!isEmpty()) {
            Range span = span();
            if (range.encloses(span)) {
                return this;
            }
            if (range.isConnected(span)) {
                return new ImmutableRangeSet<>(intersectRanges(range));
            }
        }
        return of();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this.ranges);
    }
}

package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.SortedLists;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

@GwtIncompatible
@Beta
public class ImmutableRangeMap<K extends Comparable<?>, V> implements RangeMap<K, V>, Serializable {
    private static final ImmutableRangeMap<Comparable<?>, Object> EMPTY = new ImmutableRangeMap<>(ImmutableList.of(), ImmutableList.of());
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public final transient ImmutableList<Range<K>> ranges;
    private final transient ImmutableList<V> values;

    public static final class Builder<K extends Comparable<?>, V> {
        private final RangeSet<K> keyRanges = TreeRangeSet.create();
        private final RangeMap<K, V> rangeMap = TreeRangeMap.create();

        public ImmutableRangeMap<K, V> build() {
            Map<Range<K>, V> asMapOfRanges = this.rangeMap.asMapOfRanges();
            ImmutableList.Builder builder = new ImmutableList.Builder(asMapOfRanges.size());
            ImmutableList.Builder builder2 = new ImmutableList.Builder(asMapOfRanges.size());
            for (Map.Entry next : asMapOfRanges.entrySet()) {
                builder.add(next.getKey());
                builder2.add(next.getValue());
            }
            return new ImmutableRangeMap<>(builder.build(), builder2.build());
        }

        @CanIgnoreReturnValue
        public Builder<K, V> put(Range<K> range, V v) {
            Preconditions.checkNotNull(range);
            Preconditions.checkNotNull(v);
            Preconditions.checkArgument(!range.isEmpty(), "Range must not be empty, but was %s", (Object) range);
            if (!this.keyRanges.complement().encloses(range)) {
                for (Map.Entry next : this.rangeMap.asMapOfRanges().entrySet()) {
                    Range range2 = (Range) next.getKey();
                    if (range2.isConnected(range) && !range2.intersection(range).isEmpty()) {
                        throw new IllegalArgumentException("Overlapping ranges: range " + range + " overlaps with entry " + next);
                    }
                }
            }
            this.keyRanges.add(range);
            this.rangeMap.put(range, v);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> putAll(RangeMap<K, ? extends V> rangeMap2) {
            for (Map.Entry next : rangeMap2.asMapOfRanges().entrySet()) {
                put((Range) next.getKey(), next.getValue());
            }
            return this;
        }
    }

    private static class SerializedForm<K extends Comparable<?>, V> implements Serializable {
        private static final long serialVersionUID = 0;
        private final ImmutableMap<Range<K>, V> mapOfRanges;

        SerializedForm(ImmutableMap<Range<K>, V> immutableMap) {
            this.mapOfRanges = immutableMap;
        }

        /* access modifiers changed from: package-private */
        public Object a() {
            Builder builder = new Builder();
            UnmodifiableIterator<Map.Entry<Range<K>, V>> it = this.mapOfRanges.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                builder.put((Range) next.getKey(), next.getValue());
            }
            return builder.build();
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.mapOfRanges.isEmpty() ? ImmutableRangeMap.of() : a();
        }
    }

    ImmutableRangeMap(ImmutableList<Range<K>> immutableList, ImmutableList<V> immutableList2) {
        this.ranges = immutableList;
        this.values = immutableList2;
    }

    public static <K extends Comparable<?>, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> copyOf(RangeMap<K, ? extends V> rangeMap) {
        if (rangeMap instanceof ImmutableRangeMap) {
            return (ImmutableRangeMap) rangeMap;
        }
        Map<Range<K>, ? extends V> asMapOfRanges = rangeMap.asMapOfRanges();
        ImmutableList.Builder builder = new ImmutableList.Builder(asMapOfRanges.size());
        ImmutableList.Builder builder2 = new ImmutableList.Builder(asMapOfRanges.size());
        for (Map.Entry next : asMapOfRanges.entrySet()) {
            builder.add(next.getKey());
            builder2.add(next.getValue());
        }
        return new ImmutableRangeMap<>(builder.build(), builder2.build());
    }

    public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> of() {
        return EMPTY;
    }

    public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> of(Range<K> range, V v) {
        return new ImmutableRangeMap<>(ImmutableList.of(range), ImmutableList.of(v));
    }

    public ImmutableMap<Range<K>, V> asDescendingMapOfRanges() {
        return this.ranges.isEmpty() ? ImmutableMap.of() : new ImmutableSortedMap(new RegularImmutableSortedSet(this.ranges.reverse(), Range.a.reverse()), this.values.reverse());
    }

    public ImmutableMap<Range<K>, V> asMapOfRanges() {
        return this.ranges.isEmpty() ? ImmutableMap.of() : new ImmutableSortedMap(new RegularImmutableSortedSet(this.ranges, Range.a), this.values);
    }

    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof RangeMap) {
            return asMapOfRanges().equals(((RangeMap) obj).asMapOfRanges());
        }
        return false;
    }

    @Nullable
    public V get(K k) {
        int binarySearch = SortedLists.binarySearch(this.ranges, Range.a(), Cut.b(k), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        if (binarySearch != -1 && ((Range) this.ranges.get(binarySearch)).contains(k)) {
            return this.values.get(binarySearch);
        }
        return null;
    }

    @Nullable
    public Map.Entry<Range<K>, V> getEntry(K k) {
        int binarySearch = SortedLists.binarySearch(this.ranges, Range.a(), Cut.b(k), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        if (binarySearch == -1) {
            return null;
        }
        Range range = (Range) this.ranges.get(binarySearch);
        if (range.contains(k)) {
            return Maps.immutableEntry(range, this.values.get(binarySearch));
        }
        return null;
    }

    public int hashCode() {
        return asMapOfRanges().hashCode();
    }

    @Deprecated
    public void put(Range<K> range, V v) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void putAll(RangeMap<K, V> rangeMap) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void remove(Range<K> range) {
        throw new UnsupportedOperationException();
    }

    public Range<K> span() {
        if (!this.ranges.isEmpty()) {
            ImmutableList<Range<K>> immutableList = this.ranges;
            return Range.a(((Range) this.ranges.get(0)).b, ((Range) immutableList.get(immutableList.size() - 1)).c);
        }
        throw new NoSuchElementException();
    }

    public ImmutableRangeMap<K, V> subRangeMap(final Range<K> range) {
        if (((Range) Preconditions.checkNotNull(range)).isEmpty()) {
            return of();
        }
        if (this.ranges.isEmpty() || range.encloses(span())) {
            return this;
        }
        final int binarySearch = SortedLists.binarySearch(this.ranges, Range.b(), range.b, SortedLists.KeyPresentBehavior.FIRST_AFTER, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
        int binarySearch2 = SortedLists.binarySearch(this.ranges, Range.a(), range.c, SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
        if (binarySearch >= binarySearch2) {
            return of();
        }
        final int i = binarySearch2 - binarySearch;
        final Range<K> range2 = range;
        return new ImmutableRangeMap<K, V>(new ImmutableList<Range<K>>() {
            /* access modifiers changed from: package-private */
            public boolean a() {
                return true;
            }

            public Range<K> get(int i) {
                Preconditions.checkElementIndex(i, i);
                return (i == 0 || i == i + -1) ? ((Range) ImmutableRangeMap.this.ranges.get(i + binarySearch)).intersection(range) : (Range) ImmutableRangeMap.this.ranges.get(i + binarySearch);
            }

            public int size() {
                return i;
            }
        }, this.values.subList(binarySearch, binarySearch2)) {
            public /* bridge */ /* synthetic */ Map asDescendingMapOfRanges() {
                return ImmutableRangeMap.super.asDescendingMapOfRanges();
            }

            public /* bridge */ /* synthetic */ Map asMapOfRanges() {
                return ImmutableRangeMap.super.asMapOfRanges();
            }

            public ImmutableRangeMap<K, V> subRangeMap(Range<K> range) {
                return range2.isConnected(range) ? this.subRangeMap(range.intersection(range2)) : ImmutableRangeMap.of();
            }
        };
    }

    public String toString() {
        return asMapOfRanges().toString();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(asMapOfRanges());
    }
}

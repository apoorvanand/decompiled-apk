package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;

@GwtIncompatible
@Beta
public final class TreeRangeMap<K extends Comparable, V> implements RangeMap<K, V> {
    private static final RangeMap EMPTY_SUB_RANGE_MAP = new RangeMap() {
        public Map<Range, Object> asDescendingMapOfRanges() {
            return Collections.emptyMap();
        }

        public Map<Range, Object> asMapOfRanges() {
            return Collections.emptyMap();
        }

        public void clear() {
        }

        @Nullable
        public Object get(Comparable comparable) {
            return null;
        }

        @Nullable
        public Map.Entry<Range, Object> getEntry(Comparable comparable) {
            return null;
        }

        public void put(Range range, Object obj) {
            Preconditions.checkNotNull(range);
            throw new IllegalArgumentException("Cannot insert range " + range + " into an empty subRangeMap");
        }

        public void putAll(RangeMap rangeMap) {
            if (!rangeMap.asMapOfRanges().isEmpty()) {
                throw new IllegalArgumentException("Cannot putAll(nonEmptyRangeMap) into an empty subRangeMap");
            }
        }

        public void remove(Range range) {
            Preconditions.checkNotNull(range);
        }

        public Range span() {
            throw new NoSuchElementException();
        }

        public RangeMap subRangeMap(Range range) {
            Preconditions.checkNotNull(range);
            return this;
        }
    };
    /* access modifiers changed from: private */
    public final NavigableMap<Cut<K>, RangeMapEntry<K, V>> entriesByLowerBound = Maps.newTreeMap();

    private final class AsMapOfRanges extends Maps.IteratorBasedAbstractMap<Range<K>, V> {
        final Iterable<Map.Entry<Range<K>, V>> a;

        AsMapOfRanges(Iterable<RangeMapEntry<K, V>> iterable) {
            this.a = iterable;
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<Range<K>, V>> b() {
            return this.a.iterator();
        }

        public boolean containsKey(@Nullable Object obj) {
            return get(obj) != null;
        }

        public V get(@Nullable Object obj) {
            if (!(obj instanceof Range)) {
                return null;
            }
            Range range = (Range) obj;
            RangeMapEntry rangeMapEntry = (RangeMapEntry) TreeRangeMap.this.entriesByLowerBound.get(range.b);
            if (rangeMapEntry == null || !rangeMapEntry.getKey().equals(range)) {
                return null;
            }
            return rangeMapEntry.getValue();
        }

        public int size() {
            return TreeRangeMap.this.entriesByLowerBound.size();
        }
    }

    private static final class RangeMapEntry<K extends Comparable, V> extends AbstractMapEntry<Range<K>, V> {
        private final Range<K> range;
        private final V value;

        RangeMapEntry(Cut<K> cut, Cut<K> cut2, V v) {
            this(Range.a(cut, cut2), v);
        }

        RangeMapEntry(Range<K> range2, V v) {
            this.range = range2;
            this.value = v;
        }

        /* access modifiers changed from: package-private */
        public Cut<K> a() {
            return this.range.b;
        }

        /* access modifiers changed from: package-private */
        public Cut<K> b() {
            return this.range.c;
        }

        public boolean contains(K k) {
            return this.range.contains(k);
        }

        public Range<K> getKey() {
            return this.range;
        }

        public V getValue() {
            return this.value;
        }
    }

    private class SubRangeMap implements RangeMap<K, V> {
        /* access modifiers changed from: private */
        public final Range<K> subRange;

        class SubRangeMapAsMap extends AbstractMap<Range<K>, V> {
            SubRangeMapAsMap() {
            }

            /* access modifiers changed from: private */
            public boolean removeEntryIf(Predicate<? super Map.Entry<Range<K>, V>> predicate) {
                ArrayList<Range> newArrayList = Lists.newArrayList();
                for (Map.Entry entry : entrySet()) {
                    if (predicate.apply(entry)) {
                        newArrayList.add(entry.getKey());
                    }
                }
                for (Range remove : newArrayList) {
                    TreeRangeMap.this.remove(remove);
                }
                return !newArrayList.isEmpty();
            }

            /* access modifiers changed from: package-private */
            public Iterator<Map.Entry<Range<K>, V>> a() {
                if (SubRangeMap.this.subRange.isEmpty()) {
                    return Iterators.a();
                }
                final Iterator it = TreeRangeMap.this.entriesByLowerBound.tailMap((Cut) MoreObjects.firstNonNull(TreeRangeMap.this.entriesByLowerBound.floorKey(SubRangeMap.this.subRange.b), SubRangeMap.this.subRange.b), true).values().iterator();
                return new AbstractIterator<Map.Entry<Range<K>, V>>() {
                    /* access modifiers changed from: protected */
                    /* renamed from: b */
                    public Map.Entry<Range<K>, V> computeNext() {
                        while (it.hasNext()) {
                            RangeMapEntry rangeMapEntry = (RangeMapEntry) it.next();
                            if (rangeMapEntry.a().compareTo(SubRangeMap.this.subRange.c) >= 0) {
                                return (Map.Entry) a();
                            }
                            if (rangeMapEntry.b().compareTo(SubRangeMap.this.subRange.b) > 0) {
                                return Maps.immutableEntry(rangeMapEntry.getKey().intersection(SubRangeMap.this.subRange), rangeMapEntry.getValue());
                            }
                        }
                        return (Map.Entry) a();
                    }
                };
            }

            public void clear() {
                SubRangeMap.this.clear();
            }

            public boolean containsKey(Object obj) {
                return get(obj) != null;
            }

            public Set<Map.Entry<Range<K>, V>> entrySet() {
                return new Maps.EntrySet<Range<K>, V>() {
                    /* access modifiers changed from: package-private */
                    public Map<Range<K>, V> a() {
                        return SubRangeMapAsMap.this;
                    }

                    public boolean isEmpty() {
                        return !iterator().hasNext();
                    }

                    public Iterator<Map.Entry<Range<K>, V>> iterator() {
                        return SubRangeMapAsMap.this.a();
                    }

                    public boolean retainAll(Collection<?> collection) {
                        return SubRangeMapAsMap.this.removeEntryIf(Predicates.not(Predicates.in(collection)));
                    }

                    public int size() {
                        return Iterators.size(iterator());
                    }
                };
            }

            public V get(Object obj) {
                RangeMapEntry rangeMapEntry;
                V v;
                try {
                    if (obj instanceof Range) {
                        Range range = (Range) obj;
                        if (SubRangeMap.this.subRange.encloses(range)) {
                            if (!range.isEmpty()) {
                                if (range.b.compareTo(SubRangeMap.this.subRange.b) == 0) {
                                    Map.Entry<K, V> floorEntry = TreeRangeMap.this.entriesByLowerBound.floorEntry(range.b);
                                    if (floorEntry != null) {
                                        v = floorEntry.getValue();
                                    } else {
                                        rangeMapEntry = null;
                                        if (rangeMapEntry != null && rangeMapEntry.getKey().isConnected(SubRangeMap.this.subRange) && rangeMapEntry.getKey().intersection(SubRangeMap.this.subRange).equals(range)) {
                                            return rangeMapEntry.getValue();
                                        }
                                    }
                                } else {
                                    v = TreeRangeMap.this.entriesByLowerBound.get(range.b);
                                }
                                rangeMapEntry = (RangeMapEntry) v;
                                return rangeMapEntry.getValue();
                            }
                        }
                    }
                } catch (ClassCastException unused) {
                }
                return null;
            }

            public Set<Range<K>> keySet() {
                return new Maps.KeySet<Range<K>, V>(this) {
                    public boolean remove(@Nullable Object obj) {
                        return SubRangeMapAsMap.this.remove(obj) != null;
                    }

                    public boolean retainAll(Collection<?> collection) {
                        return SubRangeMapAsMap.this.removeEntryIf(Predicates.compose(Predicates.not(Predicates.in(collection)), Maps.a()));
                    }
                };
            }

            public V remove(Object obj) {
                V v = get(obj);
                if (v == null) {
                    return null;
                }
                TreeRangeMap.this.remove((Range) obj);
                return v;
            }

            public Collection<V> values() {
                return new Maps.Values<Range<K>, V>(this) {
                    public boolean removeAll(Collection<?> collection) {
                        return SubRangeMapAsMap.this.removeEntryIf(Predicates.compose(Predicates.in(collection), Maps.b()));
                    }

                    public boolean retainAll(Collection<?> collection) {
                        return SubRangeMapAsMap.this.removeEntryIf(Predicates.compose(Predicates.not(Predicates.in(collection)), Maps.b()));
                    }
                };
            }
        }

        SubRangeMap(Range<K> range) {
            this.subRange = range;
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [java.util.Map<com.google.common.collect.Range<K>, V>, com.google.common.collect.TreeRangeMap$SubRangeMap$1] */
        public Map<Range<K>, V> asDescendingMapOfRanges() {
            return new TreeRangeMap<K, V>.SubRangeMap.SubRangeMapAsMap() {
                /* access modifiers changed from: package-private */
                public Iterator<Map.Entry<Range<K>, V>> a() {
                    if (SubRangeMap.this.subRange.isEmpty()) {
                        return Iterators.a();
                    }
                    final Iterator it = TreeRangeMap.this.entriesByLowerBound.headMap(SubRangeMap.this.subRange.c, false).descendingMap().values().iterator();
                    return new AbstractIterator<Map.Entry<Range<K>, V>>() {
                        /* access modifiers changed from: protected */
                        /* renamed from: b */
                        public Map.Entry<Range<K>, V> computeNext() {
                            if (!it.hasNext()) {
                                return (Map.Entry) a();
                            }
                            RangeMapEntry rangeMapEntry = (RangeMapEntry) it.next();
                            return rangeMapEntry.b().compareTo(SubRangeMap.this.subRange.b) <= 0 ? (Map.Entry) a() : Maps.immutableEntry(rangeMapEntry.getKey().intersection(SubRangeMap.this.subRange), rangeMapEntry.getValue());
                        }
                    };
                }
            };
        }

        public Map<Range<K>, V> asMapOfRanges() {
            return new SubRangeMapAsMap();
        }

        public void clear() {
            TreeRangeMap.this.remove(this.subRange);
        }

        public boolean equals(@Nullable Object obj) {
            if (obj instanceof RangeMap) {
                return asMapOfRanges().equals(((RangeMap) obj).asMapOfRanges());
            }
            return false;
        }

        @Nullable
        public V get(K k) {
            if (this.subRange.contains(k)) {
                return TreeRangeMap.this.get(k);
            }
            return null;
        }

        @Nullable
        public Map.Entry<Range<K>, V> getEntry(K k) {
            Map.Entry entry;
            if (!this.subRange.contains(k) || (entry = TreeRangeMap.this.getEntry(k)) == null) {
                return null;
            }
            return Maps.immutableEntry(((Range) entry.getKey()).intersection(this.subRange), entry.getValue());
        }

        public int hashCode() {
            return asMapOfRanges().hashCode();
        }

        public void put(Range<K> range, V v) {
            Preconditions.checkArgument(this.subRange.encloses(range), "Cannot put range %s into a subRangeMap(%s)", (Object) range, (Object) this.subRange);
            TreeRangeMap.this.put(range, v);
        }

        public void putAll(RangeMap<K, V> rangeMap) {
            if (!rangeMap.asMapOfRanges().isEmpty()) {
                Range<K> span = rangeMap.span();
                Preconditions.checkArgument(this.subRange.encloses(span), "Cannot putAll rangeMap with span %s into a subRangeMap(%s)", (Object) span, (Object) this.subRange);
                TreeRangeMap.this.putAll(rangeMap);
            }
        }

        public void remove(Range<K> range) {
            if (range.isConnected(this.subRange)) {
                TreeRangeMap.this.remove(range.intersection(this.subRange));
            }
        }

        public Range<K> span() {
            Cut<C> cut;
            Map.Entry<K, V> floorEntry = TreeRangeMap.this.entriesByLowerBound.floorEntry(this.subRange.b);
            if (floorEntry == null || ((RangeMapEntry) floorEntry.getValue()).b().compareTo(this.subRange.b) <= 0) {
                cut = (Cut) TreeRangeMap.this.entriesByLowerBound.ceilingKey(this.subRange.b);
                if (cut == null || cut.compareTo(this.subRange.c) >= 0) {
                    throw new NoSuchElementException();
                }
            } else {
                cut = this.subRange.b;
            }
            Map.Entry<K, V> lowerEntry = TreeRangeMap.this.entriesByLowerBound.lowerEntry(this.subRange.c);
            if (lowerEntry != null) {
                return Range.a(cut, ((RangeMapEntry) lowerEntry.getValue()).b().compareTo(this.subRange.c) >= 0 ? this.subRange.c : ((RangeMapEntry) lowerEntry.getValue()).b());
            }
            throw new NoSuchElementException();
        }

        public RangeMap<K, V> subRangeMap(Range<K> range) {
            return !range.isConnected(this.subRange) ? TreeRangeMap.this.emptySubRangeMap() : TreeRangeMap.this.subRangeMap(range.intersection(this.subRange));
        }

        public String toString() {
            return asMapOfRanges().toString();
        }
    }

    private TreeRangeMap() {
    }

    public static <K extends Comparable, V> TreeRangeMap<K, V> create() {
        return new TreeRangeMap<>();
    }

    /* access modifiers changed from: private */
    public RangeMap<K, V> emptySubRangeMap() {
        return EMPTY_SUB_RANGE_MAP;
    }

    private void putRangeMapEntry(Cut<K> cut, Cut<K> cut2, V v) {
        this.entriesByLowerBound.put(cut, new RangeMapEntry(cut, cut2, v));
    }

    public Map<Range<K>, V> asDescendingMapOfRanges() {
        return new AsMapOfRanges(this.entriesByLowerBound.descendingMap().values());
    }

    public Map<Range<K>, V> asMapOfRanges() {
        return new AsMapOfRanges(this.entriesByLowerBound.values());
    }

    public void clear() {
        this.entriesByLowerBound.clear();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof RangeMap) {
            return asMapOfRanges().equals(((RangeMap) obj).asMapOfRanges());
        }
        return false;
    }

    @Nullable
    public V get(K k) {
        Map.Entry entry = getEntry(k);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    @Nullable
    public Map.Entry<Range<K>, V> getEntry(K k) {
        Map.Entry<Cut<K>, RangeMapEntry<K, V>> floorEntry = this.entriesByLowerBound.floorEntry(Cut.b(k));
        if (floorEntry == null || !floorEntry.getValue().contains(k)) {
            return null;
        }
        return floorEntry.getValue();
    }

    public int hashCode() {
        return asMapOfRanges().hashCode();
    }

    public void put(Range<K> range, V v) {
        if (!range.isEmpty()) {
            Preconditions.checkNotNull(v);
            remove(range);
            this.entriesByLowerBound.put(range.b, new RangeMapEntry(range, v));
        }
    }

    public void putAll(RangeMap<K, V> rangeMap) {
        for (Map.Entry next : rangeMap.asMapOfRanges().entrySet()) {
            put((Range) next.getKey(), next.getValue());
        }
    }

    public void remove(Range<K> range) {
        if (!range.isEmpty()) {
            Map.Entry<Cut<K>, RangeMapEntry<K, V>> lowerEntry = this.entriesByLowerBound.lowerEntry(range.b);
            if (lowerEntry != null) {
                RangeMapEntry value = lowerEntry.getValue();
                if (value.b().compareTo(range.b) > 0) {
                    if (value.b().compareTo(range.c) > 0) {
                        putRangeMapEntry(range.c, value.b(), lowerEntry.getValue().getValue());
                    }
                    putRangeMapEntry(value.a(), range.b, lowerEntry.getValue().getValue());
                }
            }
            Map.Entry<Cut<K>, RangeMapEntry<K, V>> lowerEntry2 = this.entriesByLowerBound.lowerEntry(range.c);
            if (lowerEntry2 != null) {
                RangeMapEntry value2 = lowerEntry2.getValue();
                if (value2.b().compareTo(range.c) > 0) {
                    putRangeMapEntry(range.c, value2.b(), lowerEntry2.getValue().getValue());
                    this.entriesByLowerBound.remove(range.b);
                }
            }
            this.entriesByLowerBound.subMap(range.b, range.c).clear();
        }
    }

    public Range<K> span() {
        Map.Entry<Cut<K>, RangeMapEntry<K, V>> firstEntry = this.entriesByLowerBound.firstEntry();
        Map.Entry<Cut<K>, RangeMapEntry<K, V>> lastEntry = this.entriesByLowerBound.lastEntry();
        if (firstEntry != null) {
            return Range.a(firstEntry.getValue().getKey().b, lastEntry.getValue().getKey().c);
        }
        throw new NoSuchElementException();
    }

    public RangeMap<K, V> subRangeMap(Range<K> range) {
        return range.equals(Range.all()) ? this : new SubRangeMap(range);
    }

    public String toString() {
        return this.entriesByLowerBound.values().toString();
    }
}

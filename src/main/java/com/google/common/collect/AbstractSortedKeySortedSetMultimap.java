package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Collection;
import java.util.SortedMap;
import java.util.SortedSet;

@GwtCompatible
abstract class AbstractSortedKeySortedSetMultimap<K, V> extends AbstractSortedSetMultimap<K, V> {
    AbstractSortedKeySortedSetMultimap(SortedMap<K, Collection<V>> sortedMap) {
        super(sortedMap);
    }

    public SortedMap<K, Collection<V>> asMap() {
        return (SortedMap) super.asMap();
    }

    public SortedSet<K> keySet() {
        return (SortedSet) super.keySet();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: m */
    public SortedMap<K, Collection<V>> e() {
        return (SortedMap) super.e();
    }
}

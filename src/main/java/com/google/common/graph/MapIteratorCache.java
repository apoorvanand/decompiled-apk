package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

class MapIteratorCache<K, V> {
    /* access modifiers changed from: private */
    public final Map<K, V> backingMap;
    /* access modifiers changed from: private */
    @Nullable
    public transient Map.Entry<K, V> entrySetCache;

    MapIteratorCache(Map<K, V> map) {
        this.backingMap = (Map) Preconditions.checkNotNull(map);
    }

    /* access modifiers changed from: protected */
    public V a(@Nullable Object obj) {
        Map.Entry<K, V> entry = this.entrySetCache;
        if (entry == null || entry.getKey() != obj) {
            return null;
        }
        return entry.getValue();
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.entrySetCache = null;
    }

    public void clear() {
        a();
        this.backingMap.clear();
    }

    public final boolean containsKey(@Nullable Object obj) {
        return a(obj) != null || this.backingMap.containsKey(obj);
    }

    public V get(@Nullable Object obj) {
        V a = a(obj);
        return a != null ? a : getWithoutCaching(obj);
    }

    public final V getWithoutCaching(@Nullable Object obj) {
        return this.backingMap.get(obj);
    }

    @CanIgnoreReturnValue
    public V put(@Nullable K k, @Nullable V v) {
        a();
        return this.backingMap.put(k, v);
    }

    @CanIgnoreReturnValue
    public V remove(@Nullable Object obj) {
        a();
        return this.backingMap.remove(obj);
    }

    public final Set<K> unmodifiableKeySet() {
        return new AbstractSet<K>() {
            public boolean contains(@Nullable Object obj) {
                return MapIteratorCache.this.containsKey(obj);
            }

            public UnmodifiableIterator<K> iterator() {
                final Iterator it = MapIteratorCache.this.backingMap.entrySet().iterator();
                return new UnmodifiableIterator<K>() {
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    public K next() {
                        Map.Entry entry = (Map.Entry) it.next();
                        Map.Entry unused = MapIteratorCache.this.entrySetCache = entry;
                        return entry.getKey();
                    }
                };
            }

            public int size() {
                return MapIteratorCache.this.backingMap.size();
            }
        };
    }
}

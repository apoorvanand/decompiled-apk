package com.google.common.graph;

import java.util.Map;
import javax.annotation.Nullable;

class MapRetrievalCache<K, V> extends MapIteratorCache<K, V> {
    @Nullable
    private transient CacheEntry<K, V> cacheEntry1;
    @Nullable
    private transient CacheEntry<K, V> cacheEntry2;

    private static final class CacheEntry<K, V> {
        final K a;
        final V b;

        CacheEntry(K k, V v) {
            this.a = k;
            this.b = v;
        }
    }

    MapRetrievalCache(Map<K, V> map) {
        super(map);
    }

    private void addToCache(CacheEntry<K, V> cacheEntry) {
        this.cacheEntry2 = this.cacheEntry1;
        this.cacheEntry1 = cacheEntry;
    }

    private void addToCache(K k, V v) {
        addToCache(new CacheEntry(k, v));
    }

    /* access modifiers changed from: protected */
    public V a(@Nullable Object obj) {
        V a = super.a(obj);
        if (a != null) {
            return a;
        }
        CacheEntry<K, V> cacheEntry = this.cacheEntry1;
        if (cacheEntry != null && cacheEntry.a == obj) {
            return cacheEntry.b;
        }
        CacheEntry<K, V> cacheEntry3 = this.cacheEntry2;
        if (cacheEntry3 == null || cacheEntry3.a != obj) {
            return null;
        }
        addToCache(cacheEntry3);
        return cacheEntry3.b;
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        this.cacheEntry1 = null;
        this.cacheEntry2 = null;
    }

    public V get(@Nullable Object obj) {
        V a = a(obj);
        if (a != null) {
            return a;
        }
        V withoutCaching = getWithoutCaching(obj);
        if (withoutCaching != null) {
            addToCache(obj, withoutCaching);
        }
        return withoutCaching;
    }
}

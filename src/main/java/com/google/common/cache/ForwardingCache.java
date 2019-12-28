package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ForwardingObject;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;

@GwtIncompatible
public abstract class ForwardingCache<K, V> extends ForwardingObject implements Cache<K, V> {

    public static abstract class SimpleForwardingCache<K, V> extends ForwardingCache<K, V> {
        private final Cache<K, V> delegate;
    }

    protected ForwardingCache() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Cache<K, V> delegate();

    public ConcurrentMap<K, V> asMap() {
        return delegate().asMap();
    }

    public void cleanUp() {
        delegate().cleanUp();
    }

    public V get(K k, Callable<? extends V> callable) {
        return delegate().get(k, callable);
    }

    public ImmutableMap<K, V> getAllPresent(Iterable<?> iterable) {
        return delegate().getAllPresent(iterable);
    }

    @Nullable
    public V getIfPresent(Object obj) {
        return delegate().getIfPresent(obj);
    }

    public void invalidate(Object obj) {
        delegate().invalidate(obj);
    }

    public void invalidateAll() {
        delegate().invalidateAll();
    }

    public void invalidateAll(Iterable<?> iterable) {
        delegate().invalidateAll(iterable);
    }

    public void put(K k, V v) {
        delegate().put(k, v);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        delegate().putAll(map);
    }

    public long size() {
        return delegate().size();
    }

    public CacheStats stats() {
        return delegate().stats();
    }
}

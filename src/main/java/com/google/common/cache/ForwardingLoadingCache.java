package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableMap;

@GwtIncompatible
public abstract class ForwardingLoadingCache<K, V> extends ForwardingCache<K, V> implements LoadingCache<K, V> {

    public static abstract class SimpleForwardingLoadingCache<K, V> extends ForwardingLoadingCache<K, V> {
        private final LoadingCache<K, V> delegate;
    }

    protected ForwardingLoadingCache() {
    }

    public V apply(K k) {
        return b().apply(k);
    }

    /* access modifiers changed from: protected */
    public abstract LoadingCache<K, V> b();

    public V get(K k) {
        return b().get(k);
    }

    public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) {
        return b().getAll(iterable);
    }

    public V getUnchecked(K k) {
        return b().getUnchecked(k);
    }

    public void refresh(K k) {
        b().refresh(k);
    }
}

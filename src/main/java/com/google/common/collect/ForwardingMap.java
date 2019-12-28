package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
public abstract class ForwardingMap<K, V> extends ForwardingObject implements Map<K, V> {

    @Beta
    protected abstract class StandardEntrySet extends Maps.EntrySet<K, V> {
        public StandardEntrySet() {
        }
    }

    @Beta
    protected class StandardKeySet extends Maps.KeySet<K, V> {
        public StandardKeySet() {
            super(ForwardingMap.this);
        }
    }

    @Beta
    protected class StandardValues extends Maps.Values<K, V> {
        public StandardValues() {
            super(ForwardingMap.this);
        }
    }

    protected ForwardingMap() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Map<K, V> delegate();

    public void clear() {
        delegate().clear();
    }

    public boolean containsKey(@Nullable Object obj) {
        return delegate().containsKey(obj);
    }

    public boolean containsValue(@Nullable Object obj) {
        return delegate().containsValue(obj);
    }

    /* access modifiers changed from: protected */
    public String d() {
        return Maps.a((Map<?, ?>) this);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return delegate().entrySet();
    }

    public boolean equals(@Nullable Object obj) {
        return obj == this || delegate().equals(obj);
    }

    public V get(@Nullable Object obj) {
        return delegate().get(obj);
    }

    public int hashCode() {
        return delegate().hashCode();
    }

    public boolean isEmpty() {
        return delegate().isEmpty();
    }

    public Set<K> keySet() {
        return delegate().keySet();
    }

    @CanIgnoreReturnValue
    public V put(K k, V v) {
        return delegate().put(k, v);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        delegate().putAll(map);
    }

    @CanIgnoreReturnValue
    public V remove(Object obj) {
        return delegate().remove(obj);
    }

    public int size() {
        return delegate().size();
    }

    public Collection<V> values() {
        return delegate().values();
    }
}

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import java.util.Map;
import javax.annotation.Nullable;

@GwtCompatible
public abstract class ForwardingMapEntry<K, V> extends ForwardingObject implements Map.Entry<K, V> {
    protected ForwardingMapEntry() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Map.Entry<K, V> delegate();

    /* access modifiers changed from: protected */
    public boolean a(@Nullable Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return Objects.equal(getKey(), entry.getKey()) && Objects.equal(getValue(), entry.getValue());
    }

    public boolean equals(@Nullable Object obj) {
        return delegate().equals(obj);
    }

    public K getKey() {
        return delegate().getKey();
    }

    public V getValue() {
        return delegate().getValue();
    }

    public int hashCode() {
        return delegate().hashCode();
    }

    public V setValue(V v) {
        return delegate().setValue(v);
    }
}

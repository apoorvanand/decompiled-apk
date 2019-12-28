package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
public abstract class ForwardingSetMultimap<K, V> extends ForwardingMultimap<K, V> implements SetMultimap<K, V> {
    /* access modifiers changed from: protected */
    public abstract SetMultimap<K, V> a();

    public Set<Map.Entry<K, V>> entries() {
        return a().entries();
    }

    public Set<V> get(@Nullable K k) {
        return a().get(k);
    }

    @CanIgnoreReturnValue
    public Set<V> removeAll(@Nullable Object obj) {
        return a().removeAll(obj);
    }

    @CanIgnoreReturnValue
    public Set<V> replaceValues(K k, Iterable<? extends V> iterable) {
        return a().replaceValues(k, iterable);
    }
}

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.List;
import javax.annotation.Nullable;

@GwtCompatible
public abstract class ForwardingListMultimap<K, V> extends ForwardingMultimap<K, V> implements ListMultimap<K, V> {
    protected ForwardingListMultimap() {
    }

    /* access modifiers changed from: protected */
    public abstract ListMultimap<K, V> a();

    public List<V> get(@Nullable K k) {
        return a().get(k);
    }

    @CanIgnoreReturnValue
    public List<V> removeAll(@Nullable Object obj) {
        return a().removeAll(obj);
    }

    @CanIgnoreReturnValue
    public List<V> replaceValues(K k, Iterable<? extends V> iterable) {
        return a().replaceValues(k, iterable);
    }
}

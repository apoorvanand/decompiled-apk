package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

@GwtCompatible
abstract class AbstractListMultimap<K, V> extends AbstractMapBasedMultimap<K, V> implements ListMultimap<K, V> {
    private static final long serialVersionUID = 6588350623831699109L;

    protected AbstractListMultimap(Map<K, Collection<V>> map) {
        super(map);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract List<V> c();

    public Map<K, Collection<V>> asMap() {
        return super.asMap();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public List<V> d() {
        return ImmutableList.of();
    }

    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    public List<V> get(@Nullable K k) {
        return (List) super.get(k);
    }

    @CanIgnoreReturnValue
    public boolean put(@Nullable K k, @Nullable V v) {
        return super.put(k, v);
    }

    @CanIgnoreReturnValue
    public List<V> removeAll(@Nullable Object obj) {
        return (List) super.removeAll(obj);
    }

    @CanIgnoreReturnValue
    public List<V> replaceValues(@Nullable K k, Iterable<? extends V> iterable) {
        return (List) super.replaceValues(k, iterable);
    }
}

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
abstract class AbstractSetMultimap<K, V> extends AbstractMapBasedMultimap<K, V> implements SetMultimap<K, V> {
    private static final long serialVersionUID = 7431625294878419160L;

    protected AbstractSetMultimap(Map<K, Collection<V>> map) {
        super(map);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract Set<V> c();

    public Map<K, Collection<V>> asMap() {
        return super.asMap();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public Set<V> d() {
        return ImmutableSet.of();
    }

    public Set<Map.Entry<K, V>> entries() {
        return (Set) super.entries();
    }

    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    public Set<V> get(@Nullable K k) {
        return (Set) super.get(k);
    }

    @CanIgnoreReturnValue
    public boolean put(@Nullable K k, @Nullable V v) {
        return super.put(k, v);
    }

    @CanIgnoreReturnValue
    public Set<V> removeAll(@Nullable Object obj) {
        return (Set) super.removeAll(obj);
    }

    @CanIgnoreReturnValue
    public Set<V> replaceValues(@Nullable K k, Iterable<? extends V> iterable) {
        return (Set) super.replaceValues(k, iterable);
    }
}

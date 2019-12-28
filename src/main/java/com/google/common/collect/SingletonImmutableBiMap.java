package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.util.Map;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
final class SingletonImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    final transient K a;
    final transient V c;
    @RetainedWith
    @LazyInit
    transient ImmutableBiMap<V, K> d;

    SingletonImmutableBiMap(K k, V v) {
        CollectPreconditions.a((Object) k, (Object) v);
        this.a = k;
        this.c = v;
    }

    private SingletonImmutableBiMap(K k, V v, ImmutableBiMap<V, K> immutableBiMap) {
        this.a = k;
        this.c = v;
        this.d = immutableBiMap;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<K> c() {
        return ImmutableSet.of(this.a);
    }

    public boolean containsKey(@Nullable Object obj) {
        return this.a.equals(obj);
    }

    public boolean containsValue(@Nullable Object obj) {
        return this.c.equals(obj);
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<Map.Entry<K, V>> e() {
        return ImmutableSet.of(Maps.immutableEntry(this.a, this.c));
    }

    public V get(@Nullable Object obj) {
        if (this.a.equals(obj)) {
            return this.c;
        }
        return null;
    }

    public ImmutableBiMap<V, K> inverse() {
        ImmutableBiMap<V, K> immutableBiMap = this.d;
        if (immutableBiMap != null) {
            return immutableBiMap;
        }
        SingletonImmutableBiMap singletonImmutableBiMap = new SingletonImmutableBiMap(this.c, this.a, this);
        this.d = singletonImmutableBiMap;
        return singletonImmutableBiMap;
    }

    public int size() {
        return 1;
    }
}

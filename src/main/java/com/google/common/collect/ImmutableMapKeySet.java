package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ImmutableSet;
import com.google.j2objc.annotations.Weak;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
final class ImmutableMapKeySet<K, V> extends ImmutableSet.Indexed<K> {
    @Weak
    private final ImmutableMap<K, V> map;

    @GwtIncompatible
    private static class KeySetSerializedForm<K> implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableMap<K, ?> a;

        KeySetSerializedForm(ImmutableMap<K, ?> immutableMap) {
            this.a = immutableMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.a.keySet();
        }
    }

    ImmutableMapKeySet(ImmutableMap<K, V> immutableMap) {
        this.map = immutableMap;
    }

    /* access modifiers changed from: package-private */
    public K a(int i) {
        return ((Map.Entry) this.map.entrySet().asList().get(i)).getKey();
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return true;
    }

    public boolean contains(@Nullable Object obj) {
        return this.map.containsKey(obj);
    }

    public UnmodifiableIterator<K> iterator() {
        return this.map.a();
    }

    public int size() {
        return this.map.size();
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public Object writeReplace() {
        return new KeySetSerializedForm(this.map);
    }
}

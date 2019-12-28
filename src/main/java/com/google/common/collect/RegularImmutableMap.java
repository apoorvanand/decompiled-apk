package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMapEntry;
import com.google.common.collect.ImmutableMapEntrySet;
import com.google.common.collect.ImmutableSet;
import com.google.j2objc.annotations.Weak;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    private static final double MAX_LOAD_FACTOR = 1.2d;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public final transient Map.Entry<K, V>[] entries;
    private final transient int mask;
    private final transient ImmutableMapEntry<K, V>[] table;

    @GwtCompatible(emulated = true)
    private static final class KeySet<K, V> extends ImmutableSet.Indexed<K> {
        @Weak
        private final RegularImmutableMap<K, V> map;

        @GwtIncompatible
        private static class SerializedForm<K> implements Serializable {
            private static final long serialVersionUID = 0;
            final ImmutableMap<K, ?> a;

            SerializedForm(ImmutableMap<K, ?> immutableMap) {
                this.a = immutableMap;
            }

            /* access modifiers changed from: package-private */
            public Object readResolve() {
                return this.a.keySet();
            }
        }

        KeySet(RegularImmutableMap<K, V> regularImmutableMap) {
            this.map = regularImmutableMap;
        }

        /* access modifiers changed from: package-private */
        public K a(int i) {
            return this.map.entries[i].getKey();
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }

        public boolean contains(Object obj) {
            return this.map.containsKey(obj);
        }

        public int size() {
            return this.map.size();
        }

        /* access modifiers changed from: package-private */
        @GwtIncompatible
        public Object writeReplace() {
            return new SerializedForm(this.map);
        }
    }

    @GwtCompatible(emulated = true)
    private static final class Values<K, V> extends ImmutableList<V> {
        @Weak
        final RegularImmutableMap<K, V> a;

        @GwtIncompatible
        private static class SerializedForm<V> implements Serializable {
            private static final long serialVersionUID = 0;
            final ImmutableMap<?, V> a;

            SerializedForm(ImmutableMap<?, V> immutableMap) {
                this.a = immutableMap;
            }

            /* access modifiers changed from: package-private */
            public Object readResolve() {
                return this.a.values();
            }
        }

        Values(RegularImmutableMap<K, V> regularImmutableMap) {
            this.a = regularImmutableMap;
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }

        public V get(int i) {
            return this.a.entries[i].getValue();
        }

        public int size() {
            return this.a.size();
        }

        /* access modifiers changed from: package-private */
        @GwtIncompatible
        public Object writeReplace() {
            return new SerializedForm(this.a);
        }
    }

    private RegularImmutableMap(Map.Entry<K, V>[] entryArr, ImmutableMapEntry<K, V>[] immutableMapEntryArr, int i) {
        this.entries = entryArr;
        this.table = immutableMapEntryArr;
        this.mask = i;
    }

    static <K, V> RegularImmutableMap<K, V> a(int i, Map.Entry<K, V>[] entryArr) {
        ImmutableMapEntry immutableMapEntry;
        Preconditions.checkPositionIndex(i, entryArr.length);
        Map.Entry<K, V>[] a = i == entryArr.length ? entryArr : ImmutableMapEntry.a(i);
        int a2 = Hashing.a(i, MAX_LOAD_FACTOR);
        ImmutableMapEntry[] a3 = ImmutableMapEntry.a(a2);
        int i2 = a2 - 1;
        for (int i3 = 0; i3 < i; i3++) {
            ImmutableMapEntry immutableMapEntry2 = entryArr[i3];
            K key = immutableMapEntry2.getKey();
            V value = immutableMapEntry2.getValue();
            CollectPreconditions.a((Object) key, (Object) value);
            int a4 = Hashing.a(key.hashCode()) & i2;
            ImmutableMapEntry immutableMapEntry3 = a3[a4];
            if (immutableMapEntry3 == null) {
                immutableMapEntry = (immutableMapEntry2 instanceof ImmutableMapEntry) && immutableMapEntry2.c() ? immutableMapEntry2 : new ImmutableMapEntry(key, value);
            } else {
                immutableMapEntry = new ImmutableMapEntry.NonTerminalImmutableMapEntry(key, value, immutableMapEntry3);
            }
            a3[a4] = immutableMapEntry;
            a[i3] = immutableMapEntry;
            a((Object) key, (Map.Entry<?, ?>) immutableMapEntry, (ImmutableMapEntry<?, ?>) immutableMapEntry3);
        }
        return new RegularImmutableMap<>(a, a3, i2);
    }

    static <K, V> RegularImmutableMap<K, V> a(Map.Entry<K, V>... entryArr) {
        return a(entryArr.length, entryArr);
    }

    @Nullable
    static <V> V a(@Nullable Object obj, ImmutableMapEntry<?, V>[] immutableMapEntryArr, int i) {
        if (obj == null) {
            return null;
        }
        for (ImmutableMapEntry<?, V> immutableMapEntry = immutableMapEntryArr[i & Hashing.a(obj.hashCode())]; immutableMapEntry != null; immutableMapEntry = immutableMapEntry.a()) {
            if (obj.equals(immutableMapEntry.getKey())) {
                return immutableMapEntry.getValue();
            }
        }
        return null;
    }

    static void a(Object obj, Map.Entry<?, ?> entry, @Nullable ImmutableMapEntry<?, ?> immutableMapEntry) {
        while (immutableMapEntry != null) {
            a(!obj.equals(immutableMapEntry.getKey()), "key", entry, immutableMapEntry);
            immutableMapEntry = immutableMapEntry.a();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<K> c() {
        return new KeySet(this);
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<Map.Entry<K, V>> e() {
        return new ImmutableMapEntrySet.RegularEntrySet(this, this.entries);
    }

    /* access modifiers changed from: package-private */
    public ImmutableCollection<V> f() {
        return new Values(this);
    }

    public V get(@Nullable Object obj) {
        return a(obj, (ImmutableMapEntry<?, V>[]) this.table, this.mask);
    }

    public int size() {
        return this.entries.length;
    }
}

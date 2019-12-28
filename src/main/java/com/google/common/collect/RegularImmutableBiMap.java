package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMapEntry;
import com.google.common.collect.ImmutableMapEntrySet;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
class RegularImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    static final RegularImmutableBiMap<Object, Object> a = new RegularImmutableBiMap((ImmutableMapEntry<K, V>[]) null, (ImmutableMapEntry<K, V>[]) null, (Map.Entry[]) ImmutableMap.b, 0, 0);
    /* access modifiers changed from: private */
    public final transient Map.Entry<K, V>[] entries;
    /* access modifiers changed from: private */
    public final transient int hashCode;
    @RetainedWith
    @LazyInit
    private transient ImmutableBiMap<V, K> inverse;
    private final transient ImmutableMapEntry<K, V>[] keyTable;
    /* access modifiers changed from: private */
    public final transient int mask;
    /* access modifiers changed from: private */
    public final transient ImmutableMapEntry<K, V>[] valueTable;

    private final class Inverse extends ImmutableBiMap<V, K> {

        final class InverseEntrySet extends ImmutableMapEntrySet<V, K> {
            InverseEntrySet() {
            }

            /* access modifiers changed from: package-private */
            public ImmutableMap<V, K> b() {
                return Inverse.this;
            }

            /* access modifiers changed from: package-private */
            public boolean c() {
                return true;
            }

            /* access modifiers changed from: package-private */
            public ImmutableList<Map.Entry<V, K>> d() {
                return new ImmutableAsList<Map.Entry<V, K>>() {
                    /* access modifiers changed from: package-private */
                    public ImmutableCollection<Map.Entry<V, K>> b() {
                        return InverseEntrySet.this;
                    }

                    public Map.Entry<V, K> get(int i) {
                        Map.Entry entry = RegularImmutableBiMap.this.entries[i];
                        return Maps.immutableEntry(entry.getValue(), entry.getKey());
                    }
                };
            }

            public int hashCode() {
                return RegularImmutableBiMap.this.hashCode;
            }

            public UnmodifiableIterator<Map.Entry<V, K>> iterator() {
                return asList().iterator();
            }
        }

        private Inverse() {
        }

        /* access modifiers changed from: package-private */
        public boolean b() {
            return false;
        }

        /* access modifiers changed from: package-private */
        public ImmutableSet<Map.Entry<V, K>> e() {
            return new InverseEntrySet();
        }

        public K get(@Nullable Object obj) {
            if (!(obj == null || RegularImmutableBiMap.this.valueTable == null)) {
                for (ImmutableMapEntry immutableMapEntry = RegularImmutableBiMap.this.valueTable[Hashing.a(obj.hashCode()) & RegularImmutableBiMap.this.mask]; immutableMapEntry != null; immutableMapEntry = immutableMapEntry.b()) {
                    if (obj.equals(immutableMapEntry.getValue())) {
                        return immutableMapEntry.getKey();
                    }
                }
            }
            return null;
        }

        public ImmutableBiMap<K, V> inverse() {
            return RegularImmutableBiMap.this;
        }

        public int size() {
            return inverse().size();
        }

        /* access modifiers changed from: package-private */
        public Object writeReplace() {
            return new InverseSerializedForm(RegularImmutableBiMap.this);
        }
    }

    private static class InverseSerializedForm<K, V> implements Serializable {
        private static final long serialVersionUID = 1;
        private final ImmutableBiMap<K, V> forward;

        InverseSerializedForm(ImmutableBiMap<K, V> immutableBiMap) {
            this.forward = immutableBiMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.forward.inverse();
        }
    }

    private RegularImmutableBiMap(ImmutableMapEntry<K, V>[] immutableMapEntryArr, ImmutableMapEntry<K, V>[] immutableMapEntryArr2, Map.Entry<K, V>[] entryArr, int i, int i2) {
        this.keyTable = immutableMapEntryArr;
        this.valueTable = immutableMapEntryArr2;
        this.entries = entryArr;
        this.mask = i;
        this.hashCode = i2;
    }

    static <K, V> RegularImmutableBiMap<K, V> a(int i, Map.Entry<K, V>[] entryArr) {
        ImmutableMapEntry immutableMapEntry;
        int i2 = i;
        Map.Entry<K, V>[] entryArr2 = entryArr;
        Preconditions.checkPositionIndex(i2, entryArr2.length);
        int a2 = Hashing.a(i2, 1.2d);
        int i3 = a2 - 1;
        ImmutableMapEntry[] a3 = ImmutableMapEntry.a(a2);
        ImmutableMapEntry[] a4 = ImmutableMapEntry.a(a2);
        ImmutableMapEntry[] a5 = i2 == entryArr2.length ? entryArr2 : ImmutableMapEntry.a(i);
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            Map.Entry<K, V> entry = entryArr2[i4];
            K key = entry.getKey();
            V value = entry.getValue();
            CollectPreconditions.a((Object) key, (Object) value);
            int hashCode2 = key.hashCode();
            int hashCode3 = value.hashCode();
            int a6 = Hashing.a(hashCode2) & i3;
            int a7 = Hashing.a(hashCode3) & i3;
            ImmutableMapEntry immutableMapEntry2 = a3[a6];
            RegularImmutableMap.a((Object) key, (Map.Entry<?, ?>) entry, (ImmutableMapEntry<?, ?>) immutableMapEntry2);
            ImmutableMapEntry immutableMapEntry3 = a4[a7];
            checkNoConflictInValueBucket(value, entry, immutableMapEntry3);
            if (immutableMapEntry3 == null && immutableMapEntry2 == null) {
                immutableMapEntry = (entry instanceof ImmutableMapEntry) && ((ImmutableMapEntry) entry).c() ? (ImmutableMapEntry) entry : new ImmutableMapEntry(key, value);
            } else {
                immutableMapEntry = new ImmutableMapEntry.NonTerminalImmutableBiMapEntry(key, value, immutableMapEntry2, immutableMapEntry3);
            }
            a3[a6] = immutableMapEntry;
            a4[a7] = immutableMapEntry;
            a5[i4] = immutableMapEntry;
            i5 += hashCode2 ^ hashCode3;
            i4++;
            i2 = i;
        }
        return new RegularImmutableBiMap(a3, a4, a5, i3, i5);
    }

    static <K, V> RegularImmutableBiMap<K, V> a(Map.Entry<K, V>... entryArr) {
        return a(entryArr.length, entryArr);
    }

    private static void checkNoConflictInValueBucket(Object obj, Map.Entry<?, ?> entry, @Nullable ImmutableMapEntry<?, ?> immutableMapEntry) {
        while (immutableMapEntry != null) {
            a(!obj.equals(immutableMapEntry.getValue()), "value", entry, immutableMapEntry);
            immutableMapEntry = immutableMapEntry.b();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<Map.Entry<K, V>> e() {
        return isEmpty() ? ImmutableSet.of() : new ImmutableMapEntrySet.RegularEntrySet(this, this.entries);
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return true;
    }

    @Nullable
    public V get(@Nullable Object obj) {
        ImmutableMapEntry<K, V>[] immutableMapEntryArr = this.keyTable;
        if (immutableMapEntryArr == null) {
            return null;
        }
        return RegularImmutableMap.a(obj, (ImmutableMapEntry<?, V>[]) immutableMapEntryArr, this.mask);
    }

    public int hashCode() {
        return this.hashCode;
    }

    public ImmutableBiMap<V, K> inverse() {
        if (isEmpty()) {
            return ImmutableBiMap.of();
        }
        ImmutableBiMap<V, K> immutableBiMap = this.inverse;
        if (immutableBiMap != null) {
            return immutableBiMap;
        }
        Inverse inverse2 = new Inverse();
        this.inverse = inverse2;
        return inverse2;
    }

    public int size() {
        return this.entries.length;
    }
}

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.RetainedWith;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
public final class HashBiMap<K, V> extends Maps.IteratorBasedAbstractMap<K, V> implements BiMap<K, V>, Serializable {
    private static final double LOAD_FACTOR = 1.0d;
    @GwtIncompatible
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public transient BiEntry<K, V> firstInKeyInsertionOrder;
    private transient BiEntry<K, V>[] hashTableKToV;
    private transient BiEntry<K, V>[] hashTableVToK;
    @RetainedWith
    private transient BiMap<V, K> inverse;
    private transient BiEntry<K, V> lastInKeyInsertionOrder;
    private transient int mask;
    /* access modifiers changed from: private */
    public transient int modCount;
    /* access modifiers changed from: private */
    public transient int size;

    private static final class BiEntry<K, V> extends ImmutableEntry<K, V> {
        final int a;
        final int b;
        @Nullable
        BiEntry<K, V> c;
        @Nullable
        BiEntry<K, V> d;
        @Nullable
        BiEntry<K, V> e;
        @Nullable
        BiEntry<K, V> f;

        BiEntry(K k, int i, V v, int i2) {
            super(k, v);
            this.a = i;
            this.b = i2;
        }
    }

    private final class Inverse extends AbstractMap<V, K> implements BiMap<V, K>, Serializable {

        private final class InverseKeySet extends Maps.KeySet<V, K> {
            InverseKeySet() {
                super(Inverse.this);
            }

            public Iterator<V> iterator() {
                return new HashBiMap<K, V>.Itr<V>() {
                    {
                        HashBiMap hashBiMap = HashBiMap.this;
                    }

                    /* access modifiers changed from: package-private */
                    public V b(BiEntry<K, V> biEntry) {
                        return biEntry.h;
                    }
                };
            }

            public boolean remove(@Nullable Object obj) {
                BiEntry b = HashBiMap.this.seekByValue(obj, Hashing.a(obj));
                if (b == null) {
                    return false;
                }
                HashBiMap.this.delete(b);
                return true;
            }
        }

        private Inverse() {
        }

        /* access modifiers changed from: package-private */
        public BiMap<K, V> a() {
            return HashBiMap.this;
        }

        public void clear() {
            a().clear();
        }

        public boolean containsKey(@Nullable Object obj) {
            return a().containsValue(obj);
        }

        public Set<Map.Entry<V, K>> entrySet() {
            return new Maps.EntrySet<V, K>() {
                /* access modifiers changed from: package-private */
                public Map<V, K> a() {
                    return Inverse.this;
                }

                public Iterator<Map.Entry<V, K>> iterator() {
                    return new HashBiMap<K, V>.Itr<Map.Entry<V, K>>() {

                        /* renamed from: com.google.common.collect.HashBiMap$Inverse$1$1$InverseEntry */
                        class InverseEntry extends AbstractMapEntry<V, K> {
                            BiEntry<K, V> a;

                            InverseEntry(BiEntry<K, V> biEntry) {
                                this.a = biEntry;
                            }

                            public V getKey() {
                                return this.a.h;
                            }

                            public K getValue() {
                                return this.a.g;
                            }

                            public K setValue(K k) {
                                K k2 = this.a.g;
                                int a2 = Hashing.a((Object) k);
                                if (a2 == this.a.a && Objects.equal(k, k2)) {
                                    return k;
                                }
                                Preconditions.checkArgument(HashBiMap.this.seekByKey(k, a2) == null, "value already present: %s", (Object) k);
                                HashBiMap.this.delete(this.a);
                                BiEntry<K, V> biEntry = new BiEntry<>(k, a2, this.a.h, this.a.b);
                                this.a = biEntry;
                                HashBiMap.this.insert(biEntry, (BiEntry<K, V>) null);
                                AnonymousClass1 r6 = AnonymousClass1.this;
                                r6.d = HashBiMap.this.modCount;
                                return k2;
                            }
                        }

                        {
                            HashBiMap hashBiMap = HashBiMap.this;
                        }

                        /* access modifiers changed from: package-private */
                        /* renamed from: a */
                        public Map.Entry<V, K> b(BiEntry<K, V> biEntry) {
                            return new InverseEntry(biEntry);
                        }
                    };
                }
            };
        }

        public K forcePut(@Nullable V v, @Nullable K k) {
            return HashBiMap.this.putInverse(v, k, true);
        }

        public K get(@Nullable Object obj) {
            return Maps.b(HashBiMap.this.seekByValue(obj, Hashing.a(obj)));
        }

        public BiMap<K, V> inverse() {
            return a();
        }

        public Set<V> keySet() {
            return new InverseKeySet();
        }

        public K put(@Nullable V v, @Nullable K k) {
            return HashBiMap.this.putInverse(v, k, false);
        }

        public K remove(@Nullable Object obj) {
            BiEntry b = HashBiMap.this.seekByValue(obj, Hashing.a(obj));
            if (b == null) {
                return null;
            }
            HashBiMap.this.delete(b);
            b.f = null;
            b.e = null;
            return b.g;
        }

        public int size() {
            return HashBiMap.this.size;
        }

        public Set<K> values() {
            return a().keySet();
        }

        /* access modifiers changed from: package-private */
        public Object writeReplace() {
            return new InverseSerializedForm(HashBiMap.this);
        }
    }

    private static final class InverseSerializedForm<K, V> implements Serializable {
        private final HashBiMap<K, V> bimap;

        InverseSerializedForm(HashBiMap<K, V> hashBiMap) {
            this.bimap = hashBiMap;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return this.bimap.inverse();
        }
    }

    abstract class Itr<T> implements Iterator<T> {
        BiEntry<K, V> b = HashBiMap.this.firstInKeyInsertionOrder;
        BiEntry<K, V> c = null;
        int d = HashBiMap.this.modCount;

        Itr() {
        }

        /* access modifiers changed from: package-private */
        public abstract T b(BiEntry<K, V> biEntry);

        public boolean hasNext() {
            if (HashBiMap.this.modCount == this.d) {
                return this.b != null;
            }
            throw new ConcurrentModificationException();
        }

        public T next() {
            if (hasNext()) {
                BiEntry<K, V> biEntry = this.b;
                this.b = biEntry.e;
                this.c = biEntry;
                return b(biEntry);
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (HashBiMap.this.modCount == this.d) {
                CollectPreconditions.a(this.c != null);
                HashBiMap.this.delete(this.c);
                this.d = HashBiMap.this.modCount;
                this.c = null;
                return;
            }
            throw new ConcurrentModificationException();
        }
    }

    private final class KeySet extends Maps.KeySet<K, V> {
        KeySet() {
            super(HashBiMap.this);
        }

        public Iterator<K> iterator() {
            return new HashBiMap<K, V>.Itr<K>() {
                {
                    HashBiMap hashBiMap = HashBiMap.this;
                }

                /* access modifiers changed from: package-private */
                public K b(BiEntry<K, V> biEntry) {
                    return biEntry.g;
                }
            };
        }

        public boolean remove(@Nullable Object obj) {
            BiEntry a2 = HashBiMap.this.seekByKey(obj, Hashing.a(obj));
            if (a2 == null) {
                return false;
            }
            HashBiMap.this.delete(a2);
            a2.f = null;
            a2.e = null;
            return true;
        }
    }

    private HashBiMap(int i) {
        init(i);
    }

    public static <K, V> HashBiMap<K, V> create() {
        return create(16);
    }

    public static <K, V> HashBiMap<K, V> create(int i) {
        return new HashBiMap<>(i);
    }

    public static <K, V> HashBiMap<K, V> create(Map<? extends K, ? extends V> map) {
        HashBiMap<K, V> create = create(map.size());
        create.putAll(map);
        return create;
    }

    private BiEntry<K, V>[] createTable(int i) {
        return new BiEntry[i];
    }

    /* access modifiers changed from: private */
    public void delete(BiEntry<K, V> biEntry) {
        BiEntry<K, V> biEntry2;
        int i = biEntry.a & this.mask;
        BiEntry<K, V> biEntry3 = null;
        BiEntry<K, V> biEntry4 = null;
        for (BiEntry<K, V> biEntry5 = this.hashTableKToV[i]; biEntry5 != biEntry; biEntry5 = biEntry5.c) {
            biEntry4 = biEntry5;
        }
        if (biEntry4 == null) {
            this.hashTableKToV[i] = biEntry.c;
        } else {
            biEntry4.c = biEntry.c;
        }
        int i2 = biEntry.b & this.mask;
        BiEntry<K, V> biEntry6 = this.hashTableVToK[i2];
        while (true) {
            BiEntry<K, V> biEntry7 = biEntry3;
            biEntry3 = biEntry6;
            biEntry2 = biEntry7;
            if (biEntry3 == biEntry) {
                break;
            }
            biEntry6 = biEntry3.d;
        }
        if (biEntry2 == null) {
            this.hashTableVToK[i2] = biEntry.d;
        } else {
            biEntry2.d = biEntry.d;
        }
        if (biEntry.f == null) {
            this.firstInKeyInsertionOrder = biEntry.e;
        } else {
            biEntry.f.e = biEntry.e;
        }
        if (biEntry.e == null) {
            this.lastInKeyInsertionOrder = biEntry.f;
        } else {
            biEntry.e.f = biEntry.f;
        }
        this.size--;
        this.modCount++;
    }

    private void init(int i) {
        CollectPreconditions.a(i, "expectedSize");
        int a = Hashing.a(i, LOAD_FACTOR);
        this.hashTableKToV = createTable(a);
        this.hashTableVToK = createTable(a);
        this.firstInKeyInsertionOrder = null;
        this.lastInKeyInsertionOrder = null;
        this.size = 0;
        this.mask = a - 1;
        this.modCount = 0;
    }

    /* access modifiers changed from: private */
    public void insert(BiEntry<K, V> biEntry, @Nullable BiEntry<K, V> biEntry2) {
        int i = biEntry.a & this.mask;
        BiEntry<K, V>[] biEntryArr = this.hashTableKToV;
        biEntry.c = biEntryArr[i];
        biEntryArr[i] = biEntry;
        int i2 = biEntry.b & this.mask;
        BiEntry<K, V>[] biEntryArr2 = this.hashTableVToK;
        biEntry.d = biEntryArr2[i2];
        biEntryArr2[i2] = biEntry;
        if (biEntry2 == null) {
            BiEntry<K, V> biEntry3 = this.lastInKeyInsertionOrder;
            biEntry.f = biEntry3;
            biEntry.e = null;
            if (biEntry3 == null) {
                this.firstInKeyInsertionOrder = biEntry;
            } else {
                biEntry3.e = biEntry;
            }
        } else {
            biEntry.f = biEntry2.f;
            if (biEntry.f == null) {
                this.firstInKeyInsertionOrder = biEntry;
            } else {
                biEntry.f.e = biEntry;
            }
            biEntry.e = biEntry2.e;
            if (biEntry.e != null) {
                biEntry.e.f = biEntry;
                this.size++;
                this.modCount++;
            }
        }
        this.lastInKeyInsertionOrder = biEntry;
        this.size++;
        this.modCount++;
    }

    private V put(@Nullable K k, @Nullable V v, boolean z) {
        int a = Hashing.a((Object) k);
        int a2 = Hashing.a((Object) v);
        BiEntry seekByKey = seekByKey(k, a);
        if (seekByKey != null && a2 == seekByKey.b && Objects.equal(v, seekByKey.h)) {
            return v;
        }
        BiEntry seekByValue = seekByValue(v, a2);
        if (seekByValue != null) {
            if (z) {
                delete(seekByValue);
            } else {
                throw new IllegalArgumentException("value already present: " + v);
            }
        }
        BiEntry biEntry = new BiEntry(k, a, v, a2);
        if (seekByKey != null) {
            delete(seekByKey);
            insert(biEntry, seekByKey);
            seekByKey.f = null;
            seekByKey.e = null;
            rehashIfNecessary();
            return seekByKey.h;
        }
        insert(biEntry, (BiEntry) null);
        rehashIfNecessary();
        return null;
    }

    /* access modifiers changed from: private */
    @Nullable
    public K putInverse(@Nullable V v, @Nullable K k, boolean z) {
        int a = Hashing.a((Object) v);
        int a2 = Hashing.a((Object) k);
        BiEntry seekByValue = seekByValue(v, a);
        if (seekByValue != null && a2 == seekByValue.a && Objects.equal(k, seekByValue.g)) {
            return k;
        }
        BiEntry seekByKey = seekByKey(k, a2);
        if (seekByKey != null) {
            if (z) {
                delete(seekByKey);
            } else {
                throw new IllegalArgumentException("value already present: " + k);
            }
        }
        if (seekByValue != null) {
            delete(seekByValue);
        }
        insert(new BiEntry(k, a2, v, a), seekByKey);
        if (seekByKey != null) {
            seekByKey.f = null;
            seekByKey.e = null;
        }
        rehashIfNecessary();
        return Maps.b(seekByValue);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        init(16);
        Serialization.a(this, objectInputStream, Serialization.a(objectInputStream));
    }

    private void rehashIfNecessary() {
        BiEntry<K, V>[] biEntryArr = this.hashTableKToV;
        if (Hashing.a(this.size, biEntryArr.length, LOAD_FACTOR)) {
            int length = biEntryArr.length * 2;
            this.hashTableKToV = createTable(length);
            this.hashTableVToK = createTable(length);
            this.mask = length - 1;
            this.size = 0;
            for (BiEntry<K, V> biEntry = this.firstInKeyInsertionOrder; biEntry != null; biEntry = biEntry.e) {
                insert(biEntry, biEntry);
            }
            this.modCount++;
        }
    }

    /* access modifiers changed from: private */
    public BiEntry<K, V> seekByKey(@Nullable Object obj, int i) {
        for (BiEntry<K, V> biEntry = this.hashTableKToV[this.mask & i]; biEntry != null; biEntry = biEntry.c) {
            if (i == biEntry.a && Objects.equal(obj, biEntry.g)) {
                return biEntry;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public BiEntry<K, V> seekByValue(@Nullable Object obj, int i) {
        for (BiEntry<K, V> biEntry = this.hashTableVToK[this.mask & i]; biEntry != null; biEntry = biEntry.d) {
            if (i == biEntry.b && Objects.equal(obj, biEntry.h)) {
                return biEntry;
            }
        }
        return null;
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        Serialization.a(this, objectOutputStream);
    }

    /* access modifiers changed from: package-private */
    public Iterator<Map.Entry<K, V>> b() {
        return new HashBiMap<K, V>.Itr<Map.Entry<K, V>>() {

            /* renamed from: com.google.common.collect.HashBiMap$1$MapEntry */
            class MapEntry extends AbstractMapEntry<K, V> {
                BiEntry<K, V> a;

                MapEntry(BiEntry<K, V> biEntry) {
                    this.a = biEntry;
                }

                public K getKey() {
                    return this.a.g;
                }

                public V getValue() {
                    return this.a.h;
                }

                public V setValue(V v) {
                    V v2 = this.a.h;
                    int a2 = Hashing.a((Object) v);
                    if (a2 == this.a.b && Objects.equal(v, v2)) {
                        return v;
                    }
                    Preconditions.checkArgument(HashBiMap.this.seekByValue(v, a2) == null, "value already present: %s", (Object) v);
                    HashBiMap.this.delete(this.a);
                    BiEntry<K, V> biEntry = new BiEntry<>(this.a.g, this.a.a, v, a2);
                    HashBiMap.this.insert(biEntry, this.a);
                    BiEntry<K, V> biEntry2 = this.a;
                    biEntry2.f = null;
                    biEntry2.e = null;
                    AnonymousClass1 r6 = AnonymousClass1.this;
                    r6.d = HashBiMap.this.modCount;
                    if (AnonymousClass1.this.c == this.a) {
                        AnonymousClass1.this.c = biEntry;
                    }
                    this.a = biEntry;
                    return v2;
                }
            }

            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public Map.Entry<K, V> b(BiEntry<K, V> biEntry) {
                return new MapEntry(biEntry);
            }
        };
    }

    public void clear() {
        this.size = 0;
        Arrays.fill(this.hashTableKToV, (Object) null);
        Arrays.fill(this.hashTableVToK, (Object) null);
        this.firstInKeyInsertionOrder = null;
        this.lastInKeyInsertionOrder = null;
        this.modCount++;
    }

    public boolean containsKey(@Nullable Object obj) {
        return seekByKey(obj, Hashing.a(obj)) != null;
    }

    public boolean containsValue(@Nullable Object obj) {
        return seekByValue(obj, Hashing.a(obj)) != null;
    }

    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @CanIgnoreReturnValue
    public V forcePut(@Nullable K k, @Nullable V v) {
        return put(k, v, true);
    }

    @Nullable
    public V get(@Nullable Object obj) {
        return Maps.c(seekByKey(obj, Hashing.a(obj)));
    }

    public BiMap<V, K> inverse() {
        BiMap<V, K> biMap = this.inverse;
        if (biMap != null) {
            return biMap;
        }
        Inverse inverse2 = new Inverse();
        this.inverse = inverse2;
        return inverse2;
    }

    public Set<K> keySet() {
        return new KeySet();
    }

    @CanIgnoreReturnValue
    public V put(@Nullable K k, @Nullable V v) {
        return put(k, v, false);
    }

    @CanIgnoreReturnValue
    public V remove(@Nullable Object obj) {
        BiEntry seekByKey = seekByKey(obj, Hashing.a(obj));
        if (seekByKey == null) {
            return null;
        }
        delete(seekByKey);
        seekByKey.f = null;
        seekByKey.e = null;
        return seekByKey.h;
    }

    public int size() {
        return this.size;
    }

    public Set<V> values() {
        return inverse().keySet();
    }
}

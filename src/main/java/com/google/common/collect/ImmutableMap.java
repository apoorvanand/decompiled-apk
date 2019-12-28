package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableMap<K, V> implements Serializable, Map<K, V> {
    static final Map.Entry<?, ?>[] b = new Map.Entry[0];
    @LazyInit
    private transient ImmutableSet<Map.Entry<K, V>> entrySet;
    @LazyInit
    private transient ImmutableSet<K> keySet;
    @LazyInit
    private transient ImmutableSetMultimap<K, V> multimapView;
    @LazyInit
    private transient ImmutableCollection<V> values;

    public static class Builder<K, V> {
        Comparator<? super V> a;
        ImmutableMapEntry<K, V>[] b;
        int c;
        boolean d;

        public Builder() {
            this(4);
        }

        Builder(int i) {
            this.b = new ImmutableMapEntry[i];
            this.c = 0;
            this.d = false;
        }

        private void ensureCapacity(int i) {
            ImmutableMapEntry<K, V>[] immutableMapEntryArr = this.b;
            if (i > immutableMapEntryArr.length) {
                this.b = (ImmutableMapEntry[]) ObjectArrays.a((T[]) immutableMapEntryArr, ImmutableCollection.Builder.a(immutableMapEntryArr.length, i));
                this.d = false;
            }
        }

        public ImmutableMap<K, V> build() {
            int i = this.c;
            boolean z = false;
            switch (i) {
                case 0:
                    return ImmutableMap.of();
                case 1:
                    return ImmutableMap.of(this.b[0].getKey(), this.b[0].getValue());
                default:
                    if (this.a != null) {
                        if (this.d) {
                            this.b = (ImmutableMapEntry[]) ObjectArrays.a((T[]) this.b, i);
                        }
                        Arrays.sort(this.b, 0, this.c, Ordering.from(this.a).onResultOf(Maps.b()));
                    }
                    if (this.c == this.b.length) {
                        z = true;
                    }
                    this.d = z;
                    return RegularImmutableMap.a(this.c, this.b);
            }
        }

        @CanIgnoreReturnValue
        @Beta
        public Builder<K, V> orderEntriesByValue(Comparator<? super V> comparator) {
            Preconditions.checkState(this.a == null, "valueComparator was already set");
            this.a = (Comparator) Preconditions.checkNotNull(comparator, "valueComparator");
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> put(K k, V v) {
            ensureCapacity(this.c + 1);
            ImmutableMapEntry<K, V> a2 = ImmutableMap.a(k, v);
            ImmutableMapEntry<K, V>[] immutableMapEntryArr = this.b;
            int i = this.c;
            this.c = i + 1;
            immutableMapEntryArr[i] = a2;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            return put(entry.getKey(), entry.getValue());
        }

        @CanIgnoreReturnValue
        @Beta
        public Builder<K, V> putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            if (iterable instanceof Collection) {
                ensureCapacity(this.c + ((Collection) iterable).size());
            }
            for (Map.Entry put : iterable) {
                put(put);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
            return putAll(map.entrySet());
        }
    }

    static abstract class IteratorBasedImmutableMap<K, V> extends ImmutableMap<K, V> {
        IteratorBasedImmutableMap() {
        }

        /* access modifiers changed from: package-private */
        public abstract UnmodifiableIterator<Map.Entry<K, V>> d();

        /* access modifiers changed from: package-private */
        public ImmutableSet<Map.Entry<K, V>> e() {
            return new ImmutableMapEntrySet<K, V>() {
                /* access modifiers changed from: package-private */
                public ImmutableMap<K, V> b() {
                    return IteratorBasedImmutableMap.this;
                }

                public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
                    return IteratorBasedImmutableMap.this.d();
                }
            };
        }

        public /* bridge */ /* synthetic */ Set entrySet() {
            return ImmutableMap.super.entrySet();
        }

        public /* bridge */ /* synthetic */ Set keySet() {
            return ImmutableMap.super.keySet();
        }

        public /* bridge */ /* synthetic */ Collection values() {
            return ImmutableMap.super.values();
        }
    }

    private final class MapViewOfValuesAsSingletonSets extends IteratorBasedImmutableMap<K, ImmutableSet<V>> {
        private MapViewOfValuesAsSingletonSets() {
        }

        /* access modifiers changed from: package-private */
        public boolean b() {
            return ImmutableMap.this.b();
        }

        public boolean containsKey(@Nullable Object obj) {
            return ImmutableMap.this.containsKey(obj);
        }

        /* access modifiers changed from: package-private */
        public UnmodifiableIterator<Map.Entry<K, ImmutableSet<V>>> d() {
            final UnmodifiableIterator it = ImmutableMap.this.entrySet().iterator();
            return new UnmodifiableIterator<Map.Entry<K, ImmutableSet<V>>>() {
                public boolean hasNext() {
                    return it.hasNext();
                }

                public Map.Entry<K, ImmutableSet<V>> next() {
                    final Map.Entry entry = (Map.Entry) it.next();
                    return new AbstractMapEntry<K, ImmutableSet<V>>() {
                        public K getKey() {
                            return entry.getKey();
                        }

                        public ImmutableSet<V> getValue() {
                            return ImmutableSet.of(entry.getValue());
                        }
                    };
                }
            };
        }

        /* access modifiers changed from: package-private */
        public boolean g() {
            return ImmutableMap.this.g();
        }

        public ImmutableSet<V> get(@Nullable Object obj) {
            Object obj2 = ImmutableMap.this.get(obj);
            if (obj2 == null) {
                return null;
            }
            return ImmutableSet.of(obj2);
        }

        public int hashCode() {
            return ImmutableMap.this.hashCode();
        }

        public ImmutableSet<K> keySet() {
            return ImmutableMap.this.keySet();
        }

        public int size() {
            return ImmutableMap.this.size();
        }
    }

    static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private final Object[] keys;
        private final Object[] values;

        SerializedForm(ImmutableMap<?, ?> immutableMap) {
            this.keys = new Object[immutableMap.size()];
            this.values = new Object[immutableMap.size()];
            UnmodifiableIterator<Map.Entry<?, ?>> it = immutableMap.entrySet().iterator();
            int i = 0;
            while (it.hasNext()) {
                Map.Entry next = it.next();
                this.keys[i] = next.getKey();
                this.values[i] = next.getValue();
                i++;
            }
        }

        /* access modifiers changed from: package-private */
        public Object a(Builder<Object, Object> builder) {
            int i = 0;
            while (true) {
                Object[] objArr = this.keys;
                if (i >= objArr.length) {
                    return builder.build();
                }
                builder.put(objArr[i], this.values[i]);
                i++;
            }
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return a(new Builder(this.keys.length));
        }
    }

    ImmutableMap() {
    }

    static <K, V> ImmutableMapEntry<K, V> a(K k, V v) {
        return new ImmutableMapEntry<>(k, v);
    }

    static void a(boolean z, String str, Map.Entry<?, ?> entry, Map.Entry<?, ?> entry2) {
        if (!z) {
            throw new IllegalArgumentException("Multiple entries with same " + str + ": " + entry + " and " + entry2);
        }
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    @Beta
    public static <K, V> ImmutableMap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        Map.Entry[] entryArr = (Map.Entry[]) Iterables.a(iterable, (T[]) b);
        switch (entryArr.length) {
            case 0:
                return of();
            case 1:
                Map.Entry entry = entryArr[0];
                return of(entry.getKey(), entry.getValue());
            default:
                return RegularImmutableMap.a((Map.Entry<K, V>[]) entryArr);
        }
    }

    public static <K, V> ImmutableMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        if ((map instanceof ImmutableMap) && !(map instanceof ImmutableSortedMap)) {
            ImmutableMap<K, V> immutableMap = (ImmutableMap) map;
            if (!immutableMap.b()) {
                return immutableMap;
            }
        } else if (map instanceof EnumMap) {
            return copyOfEnumMap((EnumMap) map);
        }
        return copyOf(map.entrySet());
    }

    private static <K extends Enum<K>, V> ImmutableMap<K, V> copyOfEnumMap(EnumMap<K, ? extends V> enumMap) {
        EnumMap enumMap2 = new EnumMap(enumMap);
        for (Map.Entry entry : enumMap2.entrySet()) {
            CollectPreconditions.a(entry.getKey(), entry.getValue());
        }
        return ImmutableEnumMap.a(enumMap2);
    }

    public static <K, V> ImmutableMap<K, V> of() {
        return ImmutableBiMap.of();
    }

    public static <K, V> ImmutableMap<K, V> of(K k, V v) {
        return ImmutableBiMap.of(k, v);
    }

    public static <K, V> ImmutableMap<K, V> of(K k, V v, K k2, V v2) {
        return RegularImmutableMap.a((Map.Entry<K, V>[]) new Map.Entry[]{a(k, v), a(k2, v2)});
    }

    public static <K, V> ImmutableMap<K, V> of(K k, V v, K k2, V v2, K k3, V v3) {
        return RegularImmutableMap.a((Map.Entry<K, V>[]) new Map.Entry[]{a(k, v), a(k2, v2), a(k3, v3)});
    }

    public static <K, V> ImmutableMap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        return RegularImmutableMap.a((Map.Entry<K, V>[]) new Map.Entry[]{a(k, v), a(k2, v2), a(k3, v3), a(k4, v4)});
    }

    public static <K, V> ImmutableMap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return RegularImmutableMap.a((Map.Entry<K, V>[]) new Map.Entry[]{a(k, v), a(k2, v2), a(k3, v3), a(k4, v4), a(k5, v5)});
    }

    /* access modifiers changed from: package-private */
    public UnmodifiableIterator<K> a() {
        final UnmodifiableIterator it = entrySet().iterator();
        return new UnmodifiableIterator<K>() {
            public boolean hasNext() {
                return it.hasNext();
            }

            public K next() {
                return ((Map.Entry) it.next()).getKey();
            }
        };
    }

    public ImmutableSetMultimap<K, V> asMultimap() {
        if (isEmpty()) {
            return ImmutableSetMultimap.of();
        }
        ImmutableSetMultimap<K, V> immutableSetMultimap = this.multimapView;
        if (immutableSetMultimap != null) {
            return immutableSetMultimap;
        }
        ImmutableSetMultimap<K, V> immutableSetMultimap2 = new ImmutableSetMultimap<>(new MapViewOfValuesAsSingletonSets(), size(), (Comparator) null);
        this.multimapView = immutableSetMultimap2;
        return immutableSetMultimap2;
    }

    /* access modifiers changed from: package-private */
    public abstract boolean b();

    /* access modifiers changed from: package-private */
    public ImmutableSet<K> c() {
        return isEmpty() ? ImmutableSet.of() : new ImmutableMapKeySet(this);
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean containsKey(@Nullable Object obj) {
        return get(obj) != null;
    }

    public boolean containsValue(@Nullable Object obj) {
        return values().contains(obj);
    }

    /* access modifiers changed from: package-private */
    public abstract ImmutableSet<Map.Entry<K, V>> e();

    public ImmutableSet<Map.Entry<K, V>> entrySet() {
        ImmutableSet<Map.Entry<K, V>> immutableSet = this.entrySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<Map.Entry<K, V>> e = e();
        this.entrySet = e;
        return e;
    }

    public boolean equals(@Nullable Object obj) {
        return Maps.d(this, obj);
    }

    /* access modifiers changed from: package-private */
    public ImmutableCollection<V> f() {
        return new ImmutableMapValues(this);
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        return false;
    }

    public abstract V get(@Nullable Object obj);

    public int hashCode() {
        return Sets.a(entrySet());
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public ImmutableSet<K> keySet() {
        ImmutableSet<K> immutableSet = this.keySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<K> c = c();
        this.keySet = c;
        return c;
    }

    @CanIgnoreReturnValue
    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return Maps.a((Map<?, ?>) this);
    }

    public ImmutableCollection<V> values() {
        ImmutableCollection<V> immutableCollection = this.values;
        if (immutableCollection != null) {
            return immutableCollection;
        }
        ImmutableCollection<V> f = f();
        this.values = f;
        return f;
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this);
    }
}

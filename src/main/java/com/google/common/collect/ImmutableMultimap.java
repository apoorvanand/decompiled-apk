package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Serialization;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.Weak;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
public abstract class ImmutableMultimap<K, V> extends AbstractMultimap<K, V> implements Serializable {
    private static final long serialVersionUID = 0;
    final transient ImmutableMap<K, ? extends ImmutableCollection<V>> b;
    final transient int c;

    public static class Builder<K, V> {
        Multimap<K, V> a;
        Comparator<? super K> b;
        Comparator<? super V> c;

        public Builder() {
            this(MultimapBuilder.linkedHashKeys().arrayListValues().build());
        }

        Builder(Multimap<K, V> multimap) {
            this.a = multimap;
        }

        public ImmutableMultimap<K, V> build() {
            if (this.c != null) {
                Iterator<Collection<V>> it = this.a.asMap().values().iterator();
                while (it.hasNext()) {
                    Collections.sort((List) it.next(), this.c);
                }
            }
            if (this.b != null) {
                ListMultimap<K, V> build = MultimapBuilder.linkedHashKeys().arrayListValues().build();
                for (E e : Ordering.from(this.b).a().immutableSortedCopy(this.a.asMap().entrySet())) {
                    build.putAll(e.getKey(), (Iterable) e.getValue());
                }
                this.a = build;
            }
            return ImmutableMultimap.copyOf(this.a);
        }

        @CanIgnoreReturnValue
        public Builder<K, V> orderKeysBy(Comparator<? super K> comparator) {
            this.b = (Comparator) Preconditions.checkNotNull(comparator);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> orderValuesBy(Comparator<? super V> comparator) {
            this.c = (Comparator) Preconditions.checkNotNull(comparator);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> put(K k, V v) {
            CollectPreconditions.a((Object) k, (Object) v);
            this.a.put(k, v);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            return put(entry.getKey(), entry.getValue());
        }

        @CanIgnoreReturnValue
        public Builder<K, V> putAll(Multimap<? extends K, ? extends V> multimap) {
            for (Map.Entry next : multimap.asMap().entrySet()) {
                putAll(next.getKey(), (Iterable) next.getValue());
            }
            return this;
        }

        @CanIgnoreReturnValue
        @Beta
        public Builder<K, V> putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            for (Map.Entry put : iterable) {
                put(put);
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> putAll(K k, Iterable<? extends V> iterable) {
            if (k != null) {
                Collection<V> collection = this.a.get(k);
                for (Object next : iterable) {
                    CollectPreconditions.a((Object) k, (Object) next);
                    collection.add(next);
                }
                return this;
            }
            throw new NullPointerException("null key in entry: null=" + Iterables.toString(iterable));
        }

        @CanIgnoreReturnValue
        public Builder<K, V> putAll(K k, V... vArr) {
            return putAll(k, Arrays.asList(vArr));
        }
    }

    private static class EntryCollection<K, V> extends ImmutableCollection<Map.Entry<K, V>> {
        private static final long serialVersionUID = 0;
        @Weak
        final ImmutableMultimap<K, V> a;

        EntryCollection(ImmutableMultimap<K, V> immutableMultimap) {
            this.a = immutableMultimap;
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return this.a.a();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return this.a.containsEntry(entry.getKey(), entry.getValue());
        }

        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return this.a.h();
        }

        public int size() {
            return this.a.size();
        }
    }

    @GwtIncompatible
    static class FieldSettersHolder {
        static final Serialization.FieldSetter<ImmutableMultimap> a = Serialization.a(ImmutableMultimap.class, "map");
        static final Serialization.FieldSetter<ImmutableMultimap> b = Serialization.a(ImmutableMultimap.class, "size");
        static final Serialization.FieldSetter<ImmutableSetMultimap> c = Serialization.a(ImmutableSetMultimap.class, "emptySet");

        FieldSettersHolder() {
        }
    }

    private abstract class Itr<T> extends UnmodifiableIterator<T> {
        final Iterator<Map.Entry<K, Collection<V>>> b;
        K c;
        Iterator<V> d;

        private Itr() {
            this.b = ImmutableMultimap.this.asMap().entrySet().iterator();
            this.c = null;
            this.d = Iterators.a();
        }

        /* access modifiers changed from: package-private */
        public abstract T b(K k, V v);

        public boolean hasNext() {
            return this.b.hasNext() || this.d.hasNext();
        }

        public T next() {
            if (!this.d.hasNext()) {
                Map.Entry next = this.b.next();
                this.c = next.getKey();
                this.d = ((Collection) next.getValue()).iterator();
            }
            return b(this.c, this.d.next());
        }
    }

    class Keys extends ImmutableMultiset<K> {
        Keys() {
        }

        /* access modifiers changed from: package-private */
        public Multiset.Entry<K> a(int i) {
            Map.Entry entry = (Map.Entry) ImmutableMultimap.this.b.entrySet().asList().get(i);
            return Multisets.immutableEntry(entry.getKey(), ((Collection) entry.getValue()).size());
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }

        public boolean contains(@Nullable Object obj) {
            return ImmutableMultimap.this.containsKey(obj);
        }

        public int count(@Nullable Object obj) {
            Collection collection = (Collection) ImmutableMultimap.this.b.get(obj);
            if (collection == null) {
                return 0;
            }
            return collection.size();
        }

        public Set<K> elementSet() {
            return ImmutableMultimap.this.keySet();
        }

        public int size() {
            return ImmutableMultimap.this.size();
        }
    }

    private static final class Values<K, V> extends ImmutableCollection<V> {
        private static final long serialVersionUID = 0;
        @Weak
        private final transient ImmutableMultimap<K, V> multimap;

        Values(ImmutableMultimap<K, V> immutableMultimap) {
            this.multimap = immutableMultimap;
        }

        /* access modifiers changed from: package-private */
        @GwtIncompatible
        public int a(Object[] objArr, int i) {
            UnmodifiableIterator<? extends ImmutableCollection<V>> it = this.multimap.b.values().iterator();
            while (it.hasNext()) {
                i = ((ImmutableCollection) it.next()).a(objArr, i);
            }
            return i;
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }

        public boolean contains(@Nullable Object obj) {
            return this.multimap.containsValue(obj);
        }

        public UnmodifiableIterator<V> iterator() {
            return this.multimap.g();
        }

        public int size() {
            return this.multimap.size();
        }
    }

    ImmutableMultimap(ImmutableMap<K, ? extends ImmutableCollection<V>> immutableMap, int i) {
        this.b = immutableMap;
        this.c = i;
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public static <K, V> ImmutableMultimap<K, V> copyOf(Multimap<? extends K, ? extends V> multimap) {
        if (multimap instanceof ImmutableMultimap) {
            ImmutableMultimap<K, V> immutableMultimap = (ImmutableMultimap) multimap;
            if (!immutableMultimap.a()) {
                return immutableMultimap;
            }
        }
        return ImmutableListMultimap.copyOf(multimap);
    }

    @Beta
    public static <K, V> ImmutableMultimap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        return ImmutableListMultimap.copyOf(iterable);
    }

    public static <K, V> ImmutableMultimap<K, V> of() {
        return ImmutableListMultimap.of();
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k, V v) {
        return ImmutableListMultimap.of(k, v);
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k, V v, K k2, V v2) {
        return ImmutableListMultimap.of(k, v, k2, v2);
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k, V v, K k2, V v2, K k3, V v3) {
        return ImmutableListMultimap.of(k, v, k2, v2, k3, v3);
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        return ImmutableListMultimap.of(k, v, k2, v2, k3, v3, k4, v4);
    }

    public static <K, V> ImmutableMultimap<K, V> of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return ImmutableListMultimap.of(k, v, k2, v2, k3, v3, k4, v4, k5, v5);
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.b.b();
    }

    public ImmutableMap<K, Collection<V>> asMap() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public ImmutableCollection<Map.Entry<K, V>> j() {
        return new EntryCollection(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public UnmodifiableIterator<Map.Entry<K, V>> h() {
        return new ImmutableMultimap<K, V>.Itr<Map.Entry<K, V>>() {
            /* access modifiers changed from: package-private */
            /* renamed from: a */
            public Map.Entry<K, V> b(K k, V v) {
                return Maps.immutableEntry(k, v);
            }
        };
    }

    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    public /* bridge */ /* synthetic */ boolean containsEntry(Object obj, Object obj2) {
        return super.containsEntry(obj, obj2);
    }

    public boolean containsKey(@Nullable Object obj) {
        return this.b.containsKey(obj);
    }

    public boolean containsValue(@Nullable Object obj) {
        return obj != null && super.containsValue(obj);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public ImmutableMultiset<K> k() {
        return new Keys();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public ImmutableCollection<V> l() {
        return new Values(this);
    }

    public ImmutableCollection<Map.Entry<K, V>> entries() {
        return (ImmutableCollection) super.entries();
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public abstract ImmutableCollection<V> get(K k);

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    /* access modifiers changed from: package-private */
    public Map<K, Collection<V>> i() {
        throw new AssertionError("should never be called");
    }

    public abstract ImmutableMultimap<V, K> inverse();

    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    public ImmutableSet<K> keySet() {
        return this.b.keySet();
    }

    public ImmutableMultiset<K> keys() {
        return (ImmutableMultiset) super.keys();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: m */
    public UnmodifiableIterator<V> g() {
        return new ImmutableMultimap<K, V>.Itr<V>() {
            /* access modifiers changed from: package-private */
            public V b(K k, V v) {
                return v;
            }
        };
    }

    @CanIgnoreReturnValue
    @Deprecated
    public boolean put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    @Deprecated
    public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    @Deprecated
    public boolean putAll(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    @Deprecated
    public boolean remove(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    @Deprecated
    public ImmutableCollection<V> removeAll(Object obj) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    @Deprecated
    public ImmutableCollection<V> replaceValues(K k, Iterable<? extends V> iterable) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return this.c;
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public ImmutableCollection<V> values() {
        return (ImmutableCollection) super.values();
    }
}

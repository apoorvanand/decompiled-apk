package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
abstract class AbstractMultimap<K, V> implements Multimap<K, V> {
    private transient Map<K, Collection<V>> asMap;
    private transient Collection<Map.Entry<K, V>> entries;
    private transient Set<K> keySet;
    private transient Multiset<K> keys;
    private transient Collection<V> values;

    private class Entries extends Multimaps.Entries<K, V> {
        private Entries() {
        }

        /* access modifiers changed from: package-private */
        public Multimap<K, V> a() {
            return AbstractMultimap.this;
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return AbstractMultimap.this.h();
        }
    }

    private class EntrySet extends AbstractMultimap<K, V>.Entries implements Set<Map.Entry<K, V>> {
        private EntrySet() {
            super();
        }

        public boolean equals(@Nullable Object obj) {
            return Sets.a((Set<?>) this, obj);
        }

        public int hashCode() {
            return Sets.a(this);
        }
    }

    class Values extends AbstractCollection<V> {
        Values() {
        }

        public void clear() {
            AbstractMultimap.this.clear();
        }

        public boolean contains(@Nullable Object obj) {
            return AbstractMultimap.this.containsValue(obj);
        }

        public Iterator<V> iterator() {
            return AbstractMultimap.this.g();
        }

        public int size() {
            return AbstractMultimap.this.size();
        }
    }

    AbstractMultimap() {
    }

    public Map<K, Collection<V>> asMap() {
        Map<K, Collection<V>> map = this.asMap;
        if (map != null) {
            return map;
        }
        Map<K, Collection<V>> i = i();
        this.asMap = i;
        return i;
    }

    public boolean containsEntry(@Nullable Object obj, @Nullable Object obj2) {
        Collection collection = (Collection) asMap().get(obj);
        return collection != null && collection.contains(obj2);
    }

    public boolean containsValue(@Nullable Object obj) {
        for (Collection contains : asMap().values()) {
            if (contains.contains(obj)) {
                return true;
            }
        }
        return false;
    }

    public Collection<Map.Entry<K, V>> entries() {
        Collection<Map.Entry<K, V>> collection = this.entries;
        if (collection != null) {
            return collection;
        }
        Collection<Map.Entry<K, V>> j = j();
        this.entries = j;
        return j;
    }

    public boolean equals(@Nullable Object obj) {
        return Multimaps.a(this, obj);
    }

    /* access modifiers changed from: package-private */
    public Set<K> f() {
        return new Maps.KeySet(asMap());
    }

    /* access modifiers changed from: package-private */
    public Iterator<V> g() {
        return Maps.b(entries().iterator());
    }

    /* access modifiers changed from: package-private */
    public abstract Iterator<Map.Entry<K, V>> h();

    public int hashCode() {
        return asMap().hashCode();
    }

    /* access modifiers changed from: package-private */
    public abstract Map<K, Collection<V>> i();

    public boolean isEmpty() {
        return size() == 0;
    }

    /* access modifiers changed from: package-private */
    public Collection<Map.Entry<K, V>> j() {
        return this instanceof SetMultimap ? new EntrySet() : new Entries();
    }

    /* access modifiers changed from: package-private */
    public Multiset<K> k() {
        return new Multimaps.Keys(this);
    }

    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        Set<K> f = f();
        this.keySet = f;
        return f;
    }

    public Multiset<K> keys() {
        Multiset<K> multiset = this.keys;
        if (multiset != null) {
            return multiset;
        }
        Multiset<K> k = k();
        this.keys = k;
        return k;
    }

    /* access modifiers changed from: package-private */
    public Collection<V> l() {
        return new Values();
    }

    @CanIgnoreReturnValue
    public boolean put(@Nullable K k, @Nullable V v) {
        return get(k).add(v);
    }

    @CanIgnoreReturnValue
    public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
        boolean z = false;
        for (Map.Entry next : multimap.entries()) {
            z |= put(next.getKey(), next.getValue());
        }
        return z;
    }

    @CanIgnoreReturnValue
    public boolean putAll(@Nullable K k, Iterable<? extends V> iterable) {
        Preconditions.checkNotNull(iterable);
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            return !collection.isEmpty() && get(k).addAll(collection);
        }
        Iterator<? extends V> it = iterable.iterator();
        return it.hasNext() && Iterators.addAll(get(k), it);
    }

    @CanIgnoreReturnValue
    public boolean remove(@Nullable Object obj, @Nullable Object obj2) {
        Collection collection = (Collection) asMap().get(obj);
        return collection != null && collection.remove(obj2);
    }

    @CanIgnoreReturnValue
    public Collection<V> replaceValues(@Nullable K k, Iterable<? extends V> iterable) {
        Preconditions.checkNotNull(iterable);
        Collection<V> removeAll = removeAll(k);
        putAll(k, iterable);
        return removeAll;
    }

    public String toString() {
        return asMap().toString();
    }

    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        Collection<V> l = l();
        this.values = l;
        return l;
    }
}

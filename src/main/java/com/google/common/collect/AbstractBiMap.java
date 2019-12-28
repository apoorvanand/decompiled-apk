package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.RetainedWith;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
abstract class AbstractBiMap<K, V> extends ForwardingMap<K, V> implements BiMap<K, V>, Serializable {
    @GwtIncompatible
    private static final long serialVersionUID = 0;
    @RetainedWith
    transient AbstractBiMap<V, K> a;
    /* access modifiers changed from: private */
    public transient Map<K, V> delegate;
    private transient Set<Map.Entry<K, V>> entrySet;
    private transient Set<K> keySet;
    private transient Set<V> valueSet;

    class BiMapEntry extends ForwardingMapEntry<K, V> {
        private final Map.Entry<K, V> delegate;

        BiMapEntry(Map.Entry<K, V> entry) {
            this.delegate = entry;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Map.Entry<K, V> delegate() {
            return this.delegate;
        }

        public V setValue(V v) {
            Preconditions.checkState(AbstractBiMap.this.entrySet().contains(this), "entry no longer in map");
            if (Objects.equal(v, getValue())) {
                return v;
            }
            Preconditions.checkArgument(!AbstractBiMap.this.containsValue(v), "value already present: %s", (Object) v);
            V value = this.delegate.setValue(v);
            Preconditions.checkState(Objects.equal(v, AbstractBiMap.this.get(getKey())), "entry no longer in map");
            AbstractBiMap.this.updateInverseMap(getKey(), true, value, v);
            return value;
        }
    }

    private class EntrySet extends ForwardingSet<Map.Entry<K, V>> {
        final Set<Map.Entry<K, V>> a;

        private EntrySet() {
            this.a = AbstractBiMap.this.delegate.entrySet();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Set<Map.Entry<K, V>> delegate() {
            return this.a;
        }

        public void clear() {
            AbstractBiMap.this.clear();
        }

        public boolean contains(Object obj) {
            return Maps.a(delegate(), obj);
        }

        public boolean containsAll(Collection<?> collection) {
            return a(collection);
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return AbstractBiMap.this.b();
        }

        public boolean remove(Object obj) {
            if (!this.a.contains(obj)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            AbstractBiMap.this.a.delegate.remove(entry.getValue());
            this.a.remove(entry);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            return c(collection);
        }

        public boolean retainAll(Collection<?> collection) {
            return d(collection);
        }

        public Object[] toArray() {
            return g();
        }

        public <T> T[] toArray(T[] tArr) {
            return a(tArr);
        }
    }

    static class Inverse<K, V> extends AbstractBiMap<K, V> {
        @GwtIncompatible
        private static final long serialVersionUID = 0;

        Inverse(Map<K, V> map, AbstractBiMap<V, K> abstractBiMap) {
            super(map, abstractBiMap);
        }

        @GwtIncompatible
        private void readObject(ObjectInputStream objectInputStream) {
            objectInputStream.defaultReadObject();
            a((AbstractBiMap) objectInputStream.readObject());
        }

        @GwtIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(inverse());
        }

        /* access modifiers changed from: package-private */
        public K a(K k) {
            return this.a.b(k);
        }

        /* access modifiers changed from: package-private */
        public V b(V v) {
            return this.a.a(v);
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ Object delegate() {
            return AbstractBiMap.super.delegate();
        }

        /* access modifiers changed from: package-private */
        @GwtIncompatible
        public Object readResolve() {
            return inverse().inverse();
        }

        public /* bridge */ /* synthetic */ Collection values() {
            return AbstractBiMap.super.values();
        }
    }

    private class KeySet extends ForwardingSet<K> {
        private KeySet() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Set<K> delegate() {
            return AbstractBiMap.this.delegate.keySet();
        }

        public void clear() {
            AbstractBiMap.this.clear();
        }

        public Iterator<K> iterator() {
            return Maps.a(AbstractBiMap.this.entrySet().iterator());
        }

        public boolean remove(Object obj) {
            if (!contains(obj)) {
                return false;
            }
            Object unused = AbstractBiMap.this.removeFromBothMaps(obj);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            return c(collection);
        }

        public boolean retainAll(Collection<?> collection) {
            return d(collection);
        }
    }

    private class ValueSet extends ForwardingSet<V> {
        final Set<V> a;

        private ValueSet() {
            this.a = AbstractBiMap.this.a.keySet();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Set<V> delegate() {
            return this.a;
        }

        public Iterator<V> iterator() {
            return Maps.b(AbstractBiMap.this.entrySet().iterator());
        }

        public Object[] toArray() {
            return g();
        }

        public <T> T[] toArray(T[] tArr) {
            return a(tArr);
        }

        public String toString() {
            return f();
        }
    }

    private AbstractBiMap(Map<K, V> map, AbstractBiMap<V, K> abstractBiMap) {
        this.delegate = map;
        this.a = abstractBiMap;
    }

    AbstractBiMap(Map<K, V> map, Map<V, K> map2) {
        a(map, map2);
    }

    private V putInBothMaps(@Nullable K k, @Nullable V v, boolean z) {
        a(k);
        b(v);
        boolean containsKey = containsKey(k);
        if (containsKey && Objects.equal(v, get(k))) {
            return v;
        }
        if (z) {
            inverse().remove(v);
        } else {
            Preconditions.checkArgument(!containsValue(v), "value already present: %s", (Object) v);
        }
        V put = this.delegate.put(k, v);
        updateInverseMap(k, containsKey, put, v);
        return put;
    }

    /* access modifiers changed from: private */
    @CanIgnoreReturnValue
    public V removeFromBothMaps(Object obj) {
        V remove = this.delegate.remove(obj);
        removeFromInverseMap(remove);
        return remove;
    }

    /* access modifiers changed from: private */
    public void removeFromInverseMap(V v) {
        this.a.delegate.remove(v);
    }

    /* access modifiers changed from: private */
    public void updateInverseMap(K k, boolean z, V v, V v2) {
        if (z) {
            removeFromInverseMap(v);
        }
        this.a.delegate.put(v2, k);
    }

    /* access modifiers changed from: package-private */
    public AbstractBiMap<V, K> a(Map<V, K> map) {
        return new Inverse(map, this);
    }

    /* access modifiers changed from: package-private */
    @CanIgnoreReturnValue
    public K a(@Nullable K k) {
        return k;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Map<K, V> delegate() {
        return this.delegate;
    }

    /* access modifiers changed from: package-private */
    public void a(AbstractBiMap<V, K> abstractBiMap) {
        this.a = abstractBiMap;
    }

    /* access modifiers changed from: package-private */
    public void a(Map<K, V> map, Map<V, K> map2) {
        boolean z = true;
        Preconditions.checkState(this.delegate == null);
        Preconditions.checkState(this.a == null);
        Preconditions.checkArgument(map.isEmpty());
        Preconditions.checkArgument(map2.isEmpty());
        if (map == map2) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.delegate = map;
        this.a = a(map2);
    }

    /* access modifiers changed from: package-private */
    @CanIgnoreReturnValue
    public V b(@Nullable V v) {
        return v;
    }

    /* access modifiers changed from: package-private */
    public Iterator<Map.Entry<K, V>> b() {
        final Iterator<Map.Entry<K, V>> it = this.delegate.entrySet().iterator();
        return new Iterator<Map.Entry<K, V>>() {
            Map.Entry<K, V> a;

            public boolean hasNext() {
                return it.hasNext();
            }

            public Map.Entry<K, V> next() {
                this.a = (Map.Entry) it.next();
                return new BiMapEntry(this.a);
            }

            public void remove() {
                CollectPreconditions.a(this.a != null);
                V value = this.a.getValue();
                it.remove();
                AbstractBiMap.this.removeFromInverseMap(value);
            }
        };
    }

    public void clear() {
        this.delegate.clear();
        this.a.delegate.clear();
    }

    public boolean containsValue(@Nullable Object obj) {
        return this.a.containsKey(obj);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        EntrySet entrySet2 = new EntrySet();
        this.entrySet = entrySet2;
        return entrySet2;
    }

    @CanIgnoreReturnValue
    public V forcePut(@Nullable K k, @Nullable V v) {
        return putInBothMaps(k, v, true);
    }

    public BiMap<V, K> inverse() {
        return this.a;
    }

    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set != null) {
            return set;
        }
        KeySet keySet2 = new KeySet();
        this.keySet = keySet2;
        return keySet2;
    }

    @CanIgnoreReturnValue
    public V put(@Nullable K k, @Nullable V v) {
        return putInBothMaps(k, v, false);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry next : map.entrySet()) {
            put(next.getKey(), next.getValue());
        }
    }

    @CanIgnoreReturnValue
    public V remove(@Nullable Object obj) {
        if (containsKey(obj)) {
            return removeFromBothMaps(obj);
        }
        return null;
    }

    public Set<V> values() {
        Set<V> set = this.valueSet;
        if (set != null) {
            return set;
        }
        ValueSet valueSet2 = new ValueSet();
        this.valueSet = valueSet2;
        return valueSet2;
    }
}

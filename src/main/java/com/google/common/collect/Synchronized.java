package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.j2objc.annotations.RetainedWith;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
final class Synchronized {

    private static class SynchronizedAsMap<K, V> extends SynchronizedMap<K, Collection<V>> {
        private static final long serialVersionUID = 0;
        transient Set<Map.Entry<K, Collection<V>>> a;
        transient Collection<Collection<V>> b;

        SynchronizedAsMap(Map<K, Collection<V>> map, @Nullable Object obj) {
            super(map, obj);
        }

        public boolean containsValue(Object obj) {
            return values().contains(obj);
        }

        public Set<Map.Entry<K, Collection<V>>> entrySet() {
            Set<Map.Entry<K, Collection<V>>> set;
            synchronized (this.h) {
                if (this.a == null) {
                    this.a = new SynchronizedAsMapEntries(c().entrySet(), this.h);
                }
                set = this.a;
            }
            return set;
        }

        public Collection<V> get(Object obj) {
            Collection<V> a2;
            synchronized (this.h) {
                Collection collection = (Collection) super.get(obj);
                a2 = collection == null ? null : Synchronized.typePreservingCollection(collection, this.h);
            }
            return a2;
        }

        public Collection<Collection<V>> values() {
            Collection<Collection<V>> collection;
            synchronized (this.h) {
                if (this.b == null) {
                    this.b = new SynchronizedAsMapValues(c().values(), this.h);
                }
                collection = this.b;
            }
            return collection;
        }
    }

    private static class SynchronizedAsMapEntries<K, V> extends SynchronizedSet<Map.Entry<K, Collection<V>>> {
        private static final long serialVersionUID = 0;

        SynchronizedAsMapEntries(Set<Map.Entry<K, Collection<V>>> set, @Nullable Object obj) {
            super(set, obj);
        }

        public boolean contains(Object obj) {
            boolean a;
            synchronized (this.h) {
                a = Maps.a(c(), obj);
            }
            return a;
        }

        public boolean containsAll(Collection<?> collection) {
            boolean a;
            synchronized (this.h) {
                a = Collections2.a((Collection<?>) c(), collection);
            }
            return a;
        }

        public boolean equals(Object obj) {
            boolean a;
            if (obj == this) {
                return true;
            }
            synchronized (this.h) {
                a = Sets.a((Set<?>) c(), obj);
            }
            return a;
        }

        public Iterator<Map.Entry<K, Collection<V>>> iterator() {
            return new TransformedIterator<Map.Entry<K, Collection<V>>, Map.Entry<K, Collection<V>>>(super.iterator()) {
                /* access modifiers changed from: package-private */
                public Map.Entry<K, Collection<V>> a(final Map.Entry<K, Collection<V>> entry) {
                    return new ForwardingMapEntry<K, Collection<V>>() {
                        /* access modifiers changed from: protected */
                        /* renamed from: a */
                        public Map.Entry<K, Collection<V>> delegate() {
                            return entry;
                        }

                        public Collection<V> getValue() {
                            return Synchronized.typePreservingCollection((Collection) entry.getValue(), SynchronizedAsMapEntries.this.h);
                        }
                    };
                }
            };
        }

        public boolean remove(Object obj) {
            boolean b;
            synchronized (this.h) {
                b = Maps.b(c(), obj);
            }
            return b;
        }

        public boolean removeAll(Collection<?> collection) {
            boolean removeAll;
            synchronized (this.h) {
                removeAll = Iterators.removeAll(c().iterator(), collection);
            }
            return removeAll;
        }

        public boolean retainAll(Collection<?> collection) {
            boolean retainAll;
            synchronized (this.h) {
                retainAll = Iterators.retainAll(c().iterator(), collection);
            }
            return retainAll;
        }

        public Object[] toArray() {
            Object[] a;
            synchronized (this.h) {
                a = ObjectArrays.a((Collection<?>) c());
            }
            return a;
        }

        public <T> T[] toArray(T[] tArr) {
            T[] a;
            synchronized (this.h) {
                a = ObjectArrays.a((Collection<?>) c(), tArr);
            }
            return a;
        }
    }

    private static class SynchronizedAsMapValues<V> extends SynchronizedCollection<Collection<V>> {
        private static final long serialVersionUID = 0;

        SynchronizedAsMapValues(Collection<Collection<V>> collection, @Nullable Object obj) {
            super(collection, obj);
        }

        public Iterator<Collection<V>> iterator() {
            return new TransformedIterator<Collection<V>, Collection<V>>(super.iterator()) {
                /* access modifiers changed from: package-private */
                public Collection<V> a(Collection<V> collection) {
                    return Synchronized.typePreservingCollection(collection, SynchronizedAsMapValues.this.h);
                }
            };
        }
    }

    @VisibleForTesting
    static class SynchronizedBiMap<K, V> extends SynchronizedMap<K, V> implements BiMap<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        @RetainedWith
        private transient BiMap<V, K> inverse;
        private transient Set<V> valueSet;

        private SynchronizedBiMap(BiMap<K, V> biMap, @Nullable Object obj, @Nullable BiMap<V, K> biMap2) {
            super(biMap, obj);
            this.inverse = biMap2;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public BiMap<K, V> c() {
            return (BiMap) super.c();
        }

        public V forcePut(K k, V v) {
            V forcePut;
            synchronized (this.h) {
                forcePut = c().forcePut(k, v);
            }
            return forcePut;
        }

        public BiMap<V, K> inverse() {
            BiMap<V, K> biMap;
            synchronized (this.h) {
                if (this.inverse == null) {
                    this.inverse = new SynchronizedBiMap(c().inverse(), this.h, this);
                }
                biMap = this.inverse;
            }
            return biMap;
        }

        public Set<V> values() {
            Set<V> set;
            synchronized (this.h) {
                if (this.valueSet == null) {
                    this.valueSet = Synchronized.a(c().values(), this.h);
                }
                set = this.valueSet;
            }
            return set;
        }
    }

    @VisibleForTesting
    static class SynchronizedCollection<E> extends SynchronizedObject implements Collection<E> {
        private static final long serialVersionUID = 0;

        private SynchronizedCollection(Collection<E> collection, @Nullable Object obj) {
            super(collection, obj);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public Collection<E> c() {
            return (Collection) super.c();
        }

        public boolean add(E e) {
            boolean add;
            synchronized (this.h) {
                add = c().add(e);
            }
            return add;
        }

        public boolean addAll(Collection<? extends E> collection) {
            boolean addAll;
            synchronized (this.h) {
                addAll = c().addAll(collection);
            }
            return addAll;
        }

        public void clear() {
            synchronized (this.h) {
                c().clear();
            }
        }

        public boolean contains(Object obj) {
            boolean contains;
            synchronized (this.h) {
                contains = c().contains(obj);
            }
            return contains;
        }

        public boolean containsAll(Collection<?> collection) {
            boolean containsAll;
            synchronized (this.h) {
                containsAll = c().containsAll(collection);
            }
            return containsAll;
        }

        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.h) {
                isEmpty = c().isEmpty();
            }
            return isEmpty;
        }

        public Iterator<E> iterator() {
            return c().iterator();
        }

        public boolean remove(Object obj) {
            boolean remove;
            synchronized (this.h) {
                remove = c().remove(obj);
            }
            return remove;
        }

        public boolean removeAll(Collection<?> collection) {
            boolean removeAll;
            synchronized (this.h) {
                removeAll = c().removeAll(collection);
            }
            return removeAll;
        }

        public boolean retainAll(Collection<?> collection) {
            boolean retainAll;
            synchronized (this.h) {
                retainAll = c().retainAll(collection);
            }
            return retainAll;
        }

        public int size() {
            int size;
            synchronized (this.h) {
                size = c().size();
            }
            return size;
        }

        public Object[] toArray() {
            Object[] array;
            synchronized (this.h) {
                array = c().toArray();
            }
            return array;
        }

        public <T> T[] toArray(T[] tArr) {
            T[] array;
            synchronized (this.h) {
                array = c().toArray(tArr);
            }
            return array;
        }
    }

    private static final class SynchronizedDeque<E> extends SynchronizedQueue<E> implements Deque<E> {
        private static final long serialVersionUID = 0;

        SynchronizedDeque(Deque<E> deque, @Nullable Object obj) {
            super(deque, obj);
        }

        public void addFirst(E e) {
            synchronized (this.h) {
                d().addFirst(e);
            }
        }

        public void addLast(E e) {
            synchronized (this.h) {
                d().addLast(e);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public Deque<E> d() {
            return (Deque) super.c();
        }

        public Iterator<E> descendingIterator() {
            Iterator<E> descendingIterator;
            synchronized (this.h) {
                descendingIterator = d().descendingIterator();
            }
            return descendingIterator;
        }

        public E getFirst() {
            E first;
            synchronized (this.h) {
                first = d().getFirst();
            }
            return first;
        }

        public E getLast() {
            E last;
            synchronized (this.h) {
                last = d().getLast();
            }
            return last;
        }

        public boolean offerFirst(E e) {
            boolean offerFirst;
            synchronized (this.h) {
                offerFirst = d().offerFirst(e);
            }
            return offerFirst;
        }

        public boolean offerLast(E e) {
            boolean offerLast;
            synchronized (this.h) {
                offerLast = d().offerLast(e);
            }
            return offerLast;
        }

        public E peekFirst() {
            E peekFirst;
            synchronized (this.h) {
                peekFirst = d().peekFirst();
            }
            return peekFirst;
        }

        public E peekLast() {
            E peekLast;
            synchronized (this.h) {
                peekLast = d().peekLast();
            }
            return peekLast;
        }

        public E pollFirst() {
            E pollFirst;
            synchronized (this.h) {
                pollFirst = d().pollFirst();
            }
            return pollFirst;
        }

        public E pollLast() {
            E pollLast;
            synchronized (this.h) {
                pollLast = d().pollLast();
            }
            return pollLast;
        }

        public E pop() {
            E pop;
            synchronized (this.h) {
                pop = d().pop();
            }
            return pop;
        }

        public void push(E e) {
            synchronized (this.h) {
                d().push(e);
            }
        }

        public E removeFirst() {
            E removeFirst;
            synchronized (this.h) {
                removeFirst = d().removeFirst();
            }
            return removeFirst;
        }

        public boolean removeFirstOccurrence(Object obj) {
            boolean removeFirstOccurrence;
            synchronized (this.h) {
                removeFirstOccurrence = d().removeFirstOccurrence(obj);
            }
            return removeFirstOccurrence;
        }

        public E removeLast() {
            E removeLast;
            synchronized (this.h) {
                removeLast = d().removeLast();
            }
            return removeLast;
        }

        public boolean removeLastOccurrence(Object obj) {
            boolean removeLastOccurrence;
            synchronized (this.h) {
                removeLastOccurrence = d().removeLastOccurrence(obj);
            }
            return removeLastOccurrence;
        }
    }

    @GwtIncompatible
    private static class SynchronizedEntry<K, V> extends SynchronizedObject implements Map.Entry<K, V> {
        private static final long serialVersionUID = 0;

        SynchronizedEntry(Map.Entry<K, V> entry, @Nullable Object obj) {
            super(entry, obj);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public Map.Entry<K, V> c() {
            return (Map.Entry) super.c();
        }

        public boolean equals(Object obj) {
            boolean equals;
            synchronized (this.h) {
                equals = c().equals(obj);
            }
            return equals;
        }

        public K getKey() {
            K key;
            synchronized (this.h) {
                key = c().getKey();
            }
            return key;
        }

        public V getValue() {
            V value;
            synchronized (this.h) {
                value = c().getValue();
            }
            return value;
        }

        public int hashCode() {
            int hashCode;
            synchronized (this.h) {
                hashCode = c().hashCode();
            }
            return hashCode;
        }

        public V setValue(V v) {
            V value;
            synchronized (this.h) {
                value = c().setValue(v);
            }
            return value;
        }
    }

    private static class SynchronizedList<E> extends SynchronizedCollection<E> implements List<E> {
        private static final long serialVersionUID = 0;

        SynchronizedList(List<E> list, @Nullable Object obj) {
            super(list, obj);
        }

        public void add(int i, E e) {
            synchronized (this.h) {
                c().add(i, e);
            }
        }

        public boolean addAll(int i, Collection<? extends E> collection) {
            boolean addAll;
            synchronized (this.h) {
                addAll = c().addAll(i, collection);
            }
            return addAll;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public List<E> c() {
            return (List) super.c();
        }

        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.h) {
                equals = c().equals(obj);
            }
            return equals;
        }

        public E get(int i) {
            E e;
            synchronized (this.h) {
                e = c().get(i);
            }
            return e;
        }

        public int hashCode() {
            int hashCode;
            synchronized (this.h) {
                hashCode = c().hashCode();
            }
            return hashCode;
        }

        public int indexOf(Object obj) {
            int indexOf;
            synchronized (this.h) {
                indexOf = c().indexOf(obj);
            }
            return indexOf;
        }

        public int lastIndexOf(Object obj) {
            int lastIndexOf;
            synchronized (this.h) {
                lastIndexOf = c().lastIndexOf(obj);
            }
            return lastIndexOf;
        }

        public ListIterator<E> listIterator() {
            return c().listIterator();
        }

        public ListIterator<E> listIterator(int i) {
            return c().listIterator(i);
        }

        public E remove(int i) {
            E remove;
            synchronized (this.h) {
                remove = c().remove(i);
            }
            return remove;
        }

        public E set(int i, E e) {
            E e2;
            synchronized (this.h) {
                e2 = c().set(i, e);
            }
            return e2;
        }

        public List<E> subList(int i, int i2) {
            List<E> a;
            synchronized (this.h) {
                a = Synchronized.list(c().subList(i, i2), this.h);
            }
            return a;
        }
    }

    private static class SynchronizedListMultimap<K, V> extends SynchronizedMultimap<K, V> implements ListMultimap<K, V> {
        private static final long serialVersionUID = 0;

        SynchronizedListMultimap(ListMultimap<K, V> listMultimap, @Nullable Object obj) {
            super(listMultimap, obj);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public ListMultimap<K, V> c() {
            return (ListMultimap) super.c();
        }

        public List<V> get(K k) {
            List<V> a;
            synchronized (this.h) {
                a = Synchronized.list(c().get(k), this.h);
            }
            return a;
        }

        public List<V> removeAll(Object obj) {
            List<V> removeAll;
            synchronized (this.h) {
                removeAll = c().removeAll(obj);
            }
            return removeAll;
        }

        public List<V> replaceValues(K k, Iterable<? extends V> iterable) {
            List<V> replaceValues;
            synchronized (this.h) {
                replaceValues = c().replaceValues(k, iterable);
            }
            return replaceValues;
        }
    }

    private static class SynchronizedMap<K, V> extends SynchronizedObject implements Map<K, V> {
        private static final long serialVersionUID = 0;
        transient Set<K> c;
        transient Collection<V> d;
        transient Set<Map.Entry<K, V>> e;

        SynchronizedMap(Map<K, V> map, @Nullable Object obj) {
            super(map, obj);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public Map<K, V> c() {
            return (Map) super.c();
        }

        public void clear() {
            synchronized (this.h) {
                c().clear();
            }
        }

        public boolean containsKey(Object obj) {
            boolean containsKey;
            synchronized (this.h) {
                containsKey = c().containsKey(obj);
            }
            return containsKey;
        }

        public boolean containsValue(Object obj) {
            boolean containsValue;
            synchronized (this.h) {
                containsValue = c().containsValue(obj);
            }
            return containsValue;
        }

        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set;
            synchronized (this.h) {
                if (this.e == null) {
                    this.e = Synchronized.a(c().entrySet(), this.h);
                }
                set = this.e;
            }
            return set;
        }

        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.h) {
                equals = c().equals(obj);
            }
            return equals;
        }

        public V get(Object obj) {
            V v;
            synchronized (this.h) {
                v = c().get(obj);
            }
            return v;
        }

        public int hashCode() {
            int hashCode;
            synchronized (this.h) {
                hashCode = c().hashCode();
            }
            return hashCode;
        }

        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.h) {
                isEmpty = c().isEmpty();
            }
            return isEmpty;
        }

        public Set<K> keySet() {
            Set<K> set;
            synchronized (this.h) {
                if (this.c == null) {
                    this.c = Synchronized.a(c().keySet(), this.h);
                }
                set = this.c;
            }
            return set;
        }

        public V put(K k, V v) {
            V put;
            synchronized (this.h) {
                put = c().put(k, v);
            }
            return put;
        }

        public void putAll(Map<? extends K, ? extends V> map) {
            synchronized (this.h) {
                c().putAll(map);
            }
        }

        public V remove(Object obj) {
            V remove;
            synchronized (this.h) {
                remove = c().remove(obj);
            }
            return remove;
        }

        public int size() {
            int size;
            synchronized (this.h) {
                size = c().size();
            }
            return size;
        }

        public Collection<V> values() {
            Collection<V> collection;
            synchronized (this.h) {
                if (this.d == null) {
                    this.d = Synchronized.collection(c().values(), this.h);
                }
                collection = this.d;
            }
            return collection;
        }
    }

    private static class SynchronizedMultimap<K, V> extends SynchronizedObject implements Multimap<K, V> {
        private static final long serialVersionUID = 0;
        transient Set<K> a;
        transient Collection<V> b;
        transient Collection<Map.Entry<K, V>> c;
        transient Map<K, Collection<V>> d;
        transient Multiset<K> e;

        SynchronizedMultimap(Multimap<K, V> multimap, @Nullable Object obj) {
            super(multimap, obj);
        }

        public Map<K, Collection<V>> asMap() {
            Map<K, Collection<V>> map;
            synchronized (this.h) {
                if (this.d == null) {
                    this.d = new SynchronizedAsMap(c().asMap(), this.h);
                }
                map = this.d;
            }
            return map;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public Multimap<K, V> c() {
            return (Multimap) super.c();
        }

        public void clear() {
            synchronized (this.h) {
                c().clear();
            }
        }

        public boolean containsEntry(Object obj, Object obj2) {
            boolean containsEntry;
            synchronized (this.h) {
                containsEntry = c().containsEntry(obj, obj2);
            }
            return containsEntry;
        }

        public boolean containsKey(Object obj) {
            boolean containsKey;
            synchronized (this.h) {
                containsKey = c().containsKey(obj);
            }
            return containsKey;
        }

        public boolean containsValue(Object obj) {
            boolean containsValue;
            synchronized (this.h) {
                containsValue = c().containsValue(obj);
            }
            return containsValue;
        }

        public Collection<Map.Entry<K, V>> entries() {
            Collection<Map.Entry<K, V>> collection;
            synchronized (this.h) {
                if (this.c == null) {
                    this.c = Synchronized.typePreservingCollection(c().entries(), this.h);
                }
                collection = this.c;
            }
            return collection;
        }

        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.h) {
                equals = c().equals(obj);
            }
            return equals;
        }

        public Collection<V> get(K k) {
            Collection<V> a2;
            synchronized (this.h) {
                a2 = Synchronized.typePreservingCollection(c().get(k), this.h);
            }
            return a2;
        }

        public int hashCode() {
            int hashCode;
            synchronized (this.h) {
                hashCode = c().hashCode();
            }
            return hashCode;
        }

        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (this.h) {
                isEmpty = c().isEmpty();
            }
            return isEmpty;
        }

        public Set<K> keySet() {
            Set<K> set;
            synchronized (this.h) {
                if (this.a == null) {
                    this.a = Synchronized.typePreservingSet(c().keySet(), this.h);
                }
                set = this.a;
            }
            return set;
        }

        public Multiset<K> keys() {
            Multiset<K> multiset;
            synchronized (this.h) {
                if (this.e == null) {
                    this.e = Synchronized.a(c().keys(), this.h);
                }
                multiset = this.e;
            }
            return multiset;
        }

        public boolean put(K k, V v) {
            boolean put;
            synchronized (this.h) {
                put = c().put(k, v);
            }
            return put;
        }

        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            boolean putAll;
            synchronized (this.h) {
                putAll = c().putAll(multimap);
            }
            return putAll;
        }

        public boolean putAll(K k, Iterable<? extends V> iterable) {
            boolean putAll;
            synchronized (this.h) {
                putAll = c().putAll(k, iterable);
            }
            return putAll;
        }

        public boolean remove(Object obj, Object obj2) {
            boolean remove;
            synchronized (this.h) {
                remove = c().remove(obj, obj2);
            }
            return remove;
        }

        public Collection<V> removeAll(Object obj) {
            Collection<V> removeAll;
            synchronized (this.h) {
                removeAll = c().removeAll(obj);
            }
            return removeAll;
        }

        public Collection<V> replaceValues(K k, Iterable<? extends V> iterable) {
            Collection<V> replaceValues;
            synchronized (this.h) {
                replaceValues = c().replaceValues(k, iterable);
            }
            return replaceValues;
        }

        public int size() {
            int size;
            synchronized (this.h) {
                size = c().size();
            }
            return size;
        }

        public Collection<V> values() {
            Collection<V> collection;
            synchronized (this.h) {
                if (this.b == null) {
                    this.b = Synchronized.collection(c().values(), this.h);
                }
                collection = this.b;
            }
            return collection;
        }
    }

    private static class SynchronizedMultiset<E> extends SynchronizedCollection<E> implements Multiset<E> {
        private static final long serialVersionUID = 0;
        transient Set<E> a;
        transient Set<Multiset.Entry<E>> b;

        SynchronizedMultiset(Multiset<E> multiset, @Nullable Object obj) {
            super(multiset, obj);
        }

        public int add(E e, int i) {
            int add;
            synchronized (this.h) {
                add = c().add(e, i);
            }
            return add;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public Multiset<E> c() {
            return (Multiset) super.c();
        }

        public int count(Object obj) {
            int count;
            synchronized (this.h) {
                count = c().count(obj);
            }
            return count;
        }

        public Set<E> elementSet() {
            Set<E> set;
            synchronized (this.h) {
                if (this.a == null) {
                    this.a = Synchronized.typePreservingSet(c().elementSet(), this.h);
                }
                set = this.a;
            }
            return set;
        }

        public Set<Multiset.Entry<E>> entrySet() {
            Set<Multiset.Entry<E>> set;
            synchronized (this.h) {
                if (this.b == null) {
                    this.b = Synchronized.typePreservingSet(c().entrySet(), this.h);
                }
                set = this.b;
            }
            return set;
        }

        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.h) {
                equals = c().equals(obj);
            }
            return equals;
        }

        public int hashCode() {
            int hashCode;
            synchronized (this.h) {
                hashCode = c().hashCode();
            }
            return hashCode;
        }

        public int remove(Object obj, int i) {
            int remove;
            synchronized (this.h) {
                remove = c().remove(obj, i);
            }
            return remove;
        }

        public int setCount(E e, int i) {
            int count;
            synchronized (this.h) {
                count = c().setCount(e, i);
            }
            return count;
        }

        public boolean setCount(E e, int i, int i2) {
            boolean count;
            synchronized (this.h) {
                count = c().setCount(e, i, i2);
            }
            return count;
        }
    }

    @GwtIncompatible
    @VisibleForTesting
    static class SynchronizedNavigableMap<K, V> extends SynchronizedSortedMap<K, V> implements NavigableMap<K, V> {
        private static final long serialVersionUID = 0;
        transient NavigableSet<K> a;
        transient NavigableMap<K, V> b;
        transient NavigableSet<K> f;

        SynchronizedNavigableMap(NavigableMap<K, V> navigableMap, @Nullable Object obj) {
            super(navigableMap, obj);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public NavigableMap<K, V> d() {
            return (NavigableMap) super.c();
        }

        public Map.Entry<K, V> ceilingEntry(K k) {
            Map.Entry<K, V> a2;
            synchronized (this.h) {
                a2 = Synchronized.nullableSynchronizedEntry(d().ceilingEntry(k), this.h);
            }
            return a2;
        }

        public K ceilingKey(K k) {
            K ceilingKey;
            synchronized (this.h) {
                ceilingKey = d().ceilingKey(k);
            }
            return ceilingKey;
        }

        public NavigableSet<K> descendingKeySet() {
            synchronized (this.h) {
                if (this.a == null) {
                    NavigableSet<K> a2 = Synchronized.a(d().descendingKeySet(), this.h);
                    this.a = a2;
                    return a2;
                }
                NavigableSet<K> navigableSet = this.a;
                return navigableSet;
            }
        }

        public NavigableMap<K, V> descendingMap() {
            synchronized (this.h) {
                if (this.b == null) {
                    NavigableMap<K, V> a2 = Synchronized.a(d().descendingMap(), this.h);
                    this.b = a2;
                    return a2;
                }
                NavigableMap<K, V> navigableMap = this.b;
                return navigableMap;
            }
        }

        public Map.Entry<K, V> firstEntry() {
            Map.Entry<K, V> a2;
            synchronized (this.h) {
                a2 = Synchronized.nullableSynchronizedEntry(d().firstEntry(), this.h);
            }
            return a2;
        }

        public Map.Entry<K, V> floorEntry(K k) {
            Map.Entry<K, V> a2;
            synchronized (this.h) {
                a2 = Synchronized.nullableSynchronizedEntry(d().floorEntry(k), this.h);
            }
            return a2;
        }

        public K floorKey(K k) {
            K floorKey;
            synchronized (this.h) {
                floorKey = d().floorKey(k);
            }
            return floorKey;
        }

        public NavigableMap<K, V> headMap(K k, boolean z) {
            NavigableMap<K, V> a2;
            synchronized (this.h) {
                a2 = Synchronized.a(d().headMap(k, z), this.h);
            }
            return a2;
        }

        public SortedMap<K, V> headMap(K k) {
            return headMap(k, false);
        }

        public Map.Entry<K, V> higherEntry(K k) {
            Map.Entry<K, V> a2;
            synchronized (this.h) {
                a2 = Synchronized.nullableSynchronizedEntry(d().higherEntry(k), this.h);
            }
            return a2;
        }

        public K higherKey(K k) {
            K higherKey;
            synchronized (this.h) {
                higherKey = d().higherKey(k);
            }
            return higherKey;
        }

        public Set<K> keySet() {
            return navigableKeySet();
        }

        public Map.Entry<K, V> lastEntry() {
            Map.Entry<K, V> a2;
            synchronized (this.h) {
                a2 = Synchronized.nullableSynchronizedEntry(d().lastEntry(), this.h);
            }
            return a2;
        }

        public Map.Entry<K, V> lowerEntry(K k) {
            Map.Entry<K, V> a2;
            synchronized (this.h) {
                a2 = Synchronized.nullableSynchronizedEntry(d().lowerEntry(k), this.h);
            }
            return a2;
        }

        public K lowerKey(K k) {
            K lowerKey;
            synchronized (this.h) {
                lowerKey = d().lowerKey(k);
            }
            return lowerKey;
        }

        public NavigableSet<K> navigableKeySet() {
            synchronized (this.h) {
                if (this.f == null) {
                    NavigableSet<K> a2 = Synchronized.a(d().navigableKeySet(), this.h);
                    this.f = a2;
                    return a2;
                }
                NavigableSet<K> navigableSet = this.f;
                return navigableSet;
            }
        }

        public Map.Entry<K, V> pollFirstEntry() {
            Map.Entry<K, V> a2;
            synchronized (this.h) {
                a2 = Synchronized.nullableSynchronizedEntry(d().pollFirstEntry(), this.h);
            }
            return a2;
        }

        public Map.Entry<K, V> pollLastEntry() {
            Map.Entry<K, V> a2;
            synchronized (this.h) {
                a2 = Synchronized.nullableSynchronizedEntry(d().pollLastEntry(), this.h);
            }
            return a2;
        }

        public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
            NavigableMap<K, V> a2;
            synchronized (this.h) {
                a2 = Synchronized.a(d().subMap(k, z, k2, z2), this.h);
            }
            return a2;
        }

        public SortedMap<K, V> subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }

        public NavigableMap<K, V> tailMap(K k, boolean z) {
            NavigableMap<K, V> a2;
            synchronized (this.h) {
                a2 = Synchronized.a(d().tailMap(k, z), this.h);
            }
            return a2;
        }

        public SortedMap<K, V> tailMap(K k) {
            return tailMap(k, true);
        }
    }

    @GwtIncompatible
    @VisibleForTesting
    static class SynchronizedNavigableSet<E> extends SynchronizedSortedSet<E> implements NavigableSet<E> {
        private static final long serialVersionUID = 0;
        transient NavigableSet<E> a;

        SynchronizedNavigableSet(NavigableSet<E> navigableSet, @Nullable Object obj) {
            super(navigableSet, obj);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public NavigableSet<E> e() {
            return (NavigableSet) super.e();
        }

        public E ceiling(E e) {
            E ceiling;
            synchronized (this.h) {
                ceiling = e().ceiling(e);
            }
            return ceiling;
        }

        public Iterator<E> descendingIterator() {
            return e().descendingIterator();
        }

        public NavigableSet<E> descendingSet() {
            synchronized (this.h) {
                if (this.a == null) {
                    NavigableSet<E> a2 = Synchronized.a(e().descendingSet(), this.h);
                    this.a = a2;
                    return a2;
                }
                NavigableSet<E> navigableSet = this.a;
                return navigableSet;
            }
        }

        public E floor(E e) {
            E floor;
            synchronized (this.h) {
                floor = e().floor(e);
            }
            return floor;
        }

        public NavigableSet<E> headSet(E e, boolean z) {
            NavigableSet<E> a2;
            synchronized (this.h) {
                a2 = Synchronized.a(e().headSet(e, z), this.h);
            }
            return a2;
        }

        public SortedSet<E> headSet(E e) {
            return headSet(e, false);
        }

        public E higher(E e) {
            E higher;
            synchronized (this.h) {
                higher = e().higher(e);
            }
            return higher;
        }

        public E lower(E e) {
            E lower;
            synchronized (this.h) {
                lower = e().lower(e);
            }
            return lower;
        }

        public E pollFirst() {
            E pollFirst;
            synchronized (this.h) {
                pollFirst = e().pollFirst();
            }
            return pollFirst;
        }

        public E pollLast() {
            E pollLast;
            synchronized (this.h) {
                pollLast = e().pollLast();
            }
            return pollLast;
        }

        public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
            NavigableSet<E> a2;
            synchronized (this.h) {
                a2 = Synchronized.a(e().subSet(e, z, e2, z2), this.h);
            }
            return a2;
        }

        public SortedSet<E> subSet(E e, E e2) {
            return subSet(e, true, e2, false);
        }

        public NavigableSet<E> tailSet(E e, boolean z) {
            NavigableSet<E> a2;
            synchronized (this.h) {
                a2 = Synchronized.a(e().tailSet(e, z), this.h);
            }
            return a2;
        }

        public SortedSet<E> tailSet(E e) {
            return tailSet(e, true);
        }
    }

    static class SynchronizedObject implements Serializable {
        @GwtIncompatible
        private static final long serialVersionUID = 0;
        final Object g;
        final Object h;

        SynchronizedObject(Object obj, @Nullable Object obj2) {
            this.g = Preconditions.checkNotNull(obj);
            this.h = obj2 == null ? this : obj2;
        }

        @GwtIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) {
            synchronized (this.h) {
                objectOutputStream.defaultWriteObject();
            }
        }

        /* access modifiers changed from: package-private */
        public Object c() {
            return this.g;
        }

        public String toString() {
            String obj;
            synchronized (this.h) {
                obj = this.g.toString();
            }
            return obj;
        }
    }

    private static class SynchronizedQueue<E> extends SynchronizedCollection<E> implements Queue<E> {
        private static final long serialVersionUID = 0;

        SynchronizedQueue(Queue<E> queue, @Nullable Object obj) {
            super(queue, obj);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: d */
        public Queue<E> c() {
            return (Queue) super.c();
        }

        public E element() {
            E element;
            synchronized (this.h) {
                element = c().element();
            }
            return element;
        }

        public boolean offer(E e) {
            boolean offer;
            synchronized (this.h) {
                offer = c().offer(e);
            }
            return offer;
        }

        public E peek() {
            E peek;
            synchronized (this.h) {
                peek = c().peek();
            }
            return peek;
        }

        public E poll() {
            E poll;
            synchronized (this.h) {
                poll = c().poll();
            }
            return poll;
        }

        public E remove() {
            E remove;
            synchronized (this.h) {
                remove = c().remove();
            }
            return remove;
        }
    }

    private static class SynchronizedRandomAccessList<E> extends SynchronizedList<E> implements RandomAccess {
        private static final long serialVersionUID = 0;

        SynchronizedRandomAccessList(List<E> list, @Nullable Object obj) {
            super(list, obj);
        }
    }

    static class SynchronizedSet<E> extends SynchronizedCollection<E> implements Set<E> {
        private static final long serialVersionUID = 0;

        SynchronizedSet(Set<E> set, @Nullable Object obj) {
            super(set, obj);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: e */
        public Set<E> c() {
            return (Set) super.c();
        }

        public boolean equals(Object obj) {
            boolean equals;
            if (obj == this) {
                return true;
            }
            synchronized (this.h) {
                equals = c().equals(obj);
            }
            return equals;
        }

        public int hashCode() {
            int hashCode;
            synchronized (this.h) {
                hashCode = c().hashCode();
            }
            return hashCode;
        }
    }

    private static class SynchronizedSetMultimap<K, V> extends SynchronizedMultimap<K, V> implements SetMultimap<K, V> {
        private static final long serialVersionUID = 0;
        transient Set<Map.Entry<K, V>> f;

        SynchronizedSetMultimap(SetMultimap<K, V> setMultimap, @Nullable Object obj) {
            super(setMultimap, obj);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public SetMultimap<K, V> c() {
            return (SetMultimap) super.c();
        }

        public Set<Map.Entry<K, V>> entries() {
            Set<Map.Entry<K, V>> set;
            synchronized (this.h) {
                if (this.f == null) {
                    this.f = Synchronized.a(c().entries(), this.h);
                }
                set = this.f;
            }
            return set;
        }

        public Set<V> get(K k) {
            Set<V> a;
            synchronized (this.h) {
                a = Synchronized.a(c().get(k), this.h);
            }
            return a;
        }

        public Set<V> removeAll(Object obj) {
            Set<V> removeAll;
            synchronized (this.h) {
                removeAll = c().removeAll(obj);
            }
            return removeAll;
        }

        public Set<V> replaceValues(K k, Iterable<? extends V> iterable) {
            Set<V> replaceValues;
            synchronized (this.h) {
                replaceValues = c().replaceValues(k, iterable);
            }
            return replaceValues;
        }
    }

    static class SynchronizedSortedMap<K, V> extends SynchronizedMap<K, V> implements SortedMap<K, V> {
        private static final long serialVersionUID = 0;

        SynchronizedSortedMap(SortedMap<K, V> sortedMap, @Nullable Object obj) {
            super(sortedMap, obj);
        }

        public Comparator<? super K> comparator() {
            Comparator<? super K> comparator;
            synchronized (this.h) {
                comparator = c().comparator();
            }
            return comparator;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: d */
        public SortedMap<K, V> c() {
            return (SortedMap) super.c();
        }

        public K firstKey() {
            K firstKey;
            synchronized (this.h) {
                firstKey = c().firstKey();
            }
            return firstKey;
        }

        public SortedMap<K, V> headMap(K k) {
            SortedMap<K, V> a;
            synchronized (this.h) {
                a = Synchronized.a(c().headMap(k), this.h);
            }
            return a;
        }

        public K lastKey() {
            K lastKey;
            synchronized (this.h) {
                lastKey = c().lastKey();
            }
            return lastKey;
        }

        public SortedMap<K, V> subMap(K k, K k2) {
            SortedMap<K, V> a;
            synchronized (this.h) {
                a = Synchronized.a(c().subMap(k, k2), this.h);
            }
            return a;
        }

        public SortedMap<K, V> tailMap(K k) {
            SortedMap<K, V> a;
            synchronized (this.h) {
                a = Synchronized.a(c().tailMap(k), this.h);
            }
            return a;
        }
    }

    static class SynchronizedSortedSet<E> extends SynchronizedSet<E> implements SortedSet<E> {
        private static final long serialVersionUID = 0;

        SynchronizedSortedSet(SortedSet<E> sortedSet, @Nullable Object obj) {
            super(sortedSet, obj);
        }

        public Comparator<? super E> comparator() {
            Comparator<? super E> comparator;
            synchronized (this.h) {
                comparator = e().comparator();
            }
            return comparator;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: d */
        public SortedSet<E> e() {
            return (SortedSet) super.c();
        }

        public E first() {
            E first;
            synchronized (this.h) {
                first = e().first();
            }
            return first;
        }

        public SortedSet<E> headSet(E e) {
            SortedSet<E> a;
            synchronized (this.h) {
                a = Synchronized.sortedSet(e().headSet(e), this.h);
            }
            return a;
        }

        public E last() {
            E last;
            synchronized (this.h) {
                last = e().last();
            }
            return last;
        }

        public SortedSet<E> subSet(E e, E e2) {
            SortedSet<E> a;
            synchronized (this.h) {
                a = Synchronized.sortedSet(e().subSet(e, e2), this.h);
            }
            return a;
        }

        public SortedSet<E> tailSet(E e) {
            SortedSet<E> a;
            synchronized (this.h) {
                a = Synchronized.sortedSet(e().tailSet(e), this.h);
            }
            return a;
        }
    }

    private static class SynchronizedSortedSetMultimap<K, V> extends SynchronizedSetMultimap<K, V> implements SortedSetMultimap<K, V> {
        private static final long serialVersionUID = 0;

        SynchronizedSortedSetMultimap(SortedSetMultimap<K, V> sortedSetMultimap, @Nullable Object obj) {
            super(sortedSetMultimap, obj);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: d */
        public SortedSetMultimap<K, V> c() {
            return (SortedSetMultimap) super.c();
        }

        public SortedSet<V> get(K k) {
            SortedSet<V> a;
            synchronized (this.h) {
                a = Synchronized.sortedSet(c().get(k), this.h);
            }
            return a;
        }

        public SortedSet<V> removeAll(Object obj) {
            SortedSet<V> removeAll;
            synchronized (this.h) {
                removeAll = c().removeAll(obj);
            }
            return removeAll;
        }

        public SortedSet<V> replaceValues(K k, Iterable<? extends V> iterable) {
            SortedSet<V> replaceValues;
            synchronized (this.h) {
                replaceValues = c().replaceValues(k, iterable);
            }
            return replaceValues;
        }

        public Comparator<? super V> valueComparator() {
            Comparator<? super V> valueComparator;
            synchronized (this.h) {
                valueComparator = c().valueComparator();
            }
            return valueComparator;
        }
    }

    private Synchronized() {
    }

    static <K, V> BiMap<K, V> a(BiMap<K, V> biMap, @Nullable Object obj) {
        return ((biMap instanceof SynchronizedBiMap) || (biMap instanceof ImmutableBiMap)) ? biMap : new SynchronizedBiMap(biMap, obj, (BiMap) null);
    }

    static <K, V> ListMultimap<K, V> a(ListMultimap<K, V> listMultimap, @Nullable Object obj) {
        return ((listMultimap instanceof SynchronizedListMultimap) || (listMultimap instanceof ImmutableListMultimap)) ? listMultimap : new SynchronizedListMultimap(listMultimap, obj);
    }

    static <K, V> Multimap<K, V> a(Multimap<K, V> multimap, @Nullable Object obj) {
        return ((multimap instanceof SynchronizedMultimap) || (multimap instanceof ImmutableMultimap)) ? multimap : new SynchronizedMultimap(multimap, obj);
    }

    static <E> Multiset<E> a(Multiset<E> multiset, @Nullable Object obj) {
        return ((multiset instanceof SynchronizedMultiset) || (multiset instanceof ImmutableMultiset)) ? multiset : new SynchronizedMultiset(multiset, obj);
    }

    static <K, V> SetMultimap<K, V> a(SetMultimap<K, V> setMultimap, @Nullable Object obj) {
        return ((setMultimap instanceof SynchronizedSetMultimap) || (setMultimap instanceof ImmutableSetMultimap)) ? setMultimap : new SynchronizedSetMultimap(setMultimap, obj);
    }

    static <K, V> SortedSetMultimap<K, V> a(SortedSetMultimap<K, V> sortedSetMultimap, @Nullable Object obj) {
        return sortedSetMultimap instanceof SynchronizedSortedSetMultimap ? sortedSetMultimap : new SynchronizedSortedSetMultimap(sortedSetMultimap, obj);
    }

    static <E> Deque<E> a(Deque<E> deque, @Nullable Object obj) {
        return new SynchronizedDeque(deque, obj);
    }

    @GwtIncompatible
    static <K, V> NavigableMap<K, V> a(NavigableMap<K, V> navigableMap) {
        return a(navigableMap, (Object) null);
    }

    @GwtIncompatible
    static <K, V> NavigableMap<K, V> a(NavigableMap<K, V> navigableMap, @Nullable Object obj) {
        return new SynchronizedNavigableMap(navigableMap, obj);
    }

    @GwtIncompatible
    static <E> NavigableSet<E> a(NavigableSet<E> navigableSet) {
        return a(navigableSet, (Object) null);
    }

    @GwtIncompatible
    static <E> NavigableSet<E> a(NavigableSet<E> navigableSet, @Nullable Object obj) {
        return new SynchronizedNavigableSet(navigableSet, obj);
    }

    static <E> Queue<E> a(Queue<E> queue, @Nullable Object obj) {
        return queue instanceof SynchronizedQueue ? queue : new SynchronizedQueue(queue, obj);
    }

    @VisibleForTesting
    static <E> Set<E> a(Set<E> set, @Nullable Object obj) {
        return new SynchronizedSet(set, obj);
    }

    static <K, V> SortedMap<K, V> a(SortedMap<K, V> sortedMap, @Nullable Object obj) {
        return new SynchronizedSortedMap(sortedMap, obj);
    }

    /* access modifiers changed from: private */
    public static <E> Collection<E> collection(Collection<E> collection, @Nullable Object obj) {
        return new SynchronizedCollection(collection, obj);
    }

    /* access modifiers changed from: private */
    public static <E> List<E> list(List<E> list, @Nullable Object obj) {
        return list instanceof RandomAccess ? new SynchronizedRandomAccessList(list, obj) : new SynchronizedList(list, obj);
    }

    /* access modifiers changed from: private */
    @GwtIncompatible
    public static <K, V> Map.Entry<K, V> nullableSynchronizedEntry(@Nullable Map.Entry<K, V> entry, @Nullable Object obj) {
        if (entry == null) {
            return null;
        }
        return new SynchronizedEntry(entry, obj);
    }

    /* access modifiers changed from: private */
    public static <E> SortedSet<E> sortedSet(SortedSet<E> sortedSet, @Nullable Object obj) {
        return new SynchronizedSortedSet(sortedSet, obj);
    }

    /* access modifiers changed from: private */
    public static <E> Collection<E> typePreservingCollection(Collection<E> collection, @Nullable Object obj) {
        return collection instanceof SortedSet ? sortedSet((SortedSet) collection, obj) : collection instanceof Set ? a((Set) collection, obj) : collection instanceof List ? list((List) collection, obj) : collection(collection, obj);
    }

    /* access modifiers changed from: private */
    public static <E> Set<E> typePreservingSet(Set<E> set, @Nullable Object obj) {
        return set instanceof SortedSet ? sortedSet((SortedSet) set, obj) : a(set, obj);
    }
}

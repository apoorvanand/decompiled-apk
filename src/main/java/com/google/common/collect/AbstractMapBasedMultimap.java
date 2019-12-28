package com.google.common.collect;

import WrappedCollection.WrappedIterator;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
abstract class AbstractMapBasedMultimap<K, V> extends AbstractMultimap<K, V> implements Serializable {
    private static final long serialVersionUID = 2447537837011683357L;
    /* access modifiers changed from: private */
    public transient Map<K, Collection<V>> map;
    private transient int totalSize;

    private class AsMap extends Maps.ViewCachingAbstractMap<K, Collection<V>> {
        final transient Map<K, Collection<V>> a;

        class AsMapEntries extends Maps.EntrySet<K, Collection<V>> {
            AsMapEntries() {
            }

            /* access modifiers changed from: package-private */
            public Map<K, Collection<V>> a() {
                return AsMap.this;
            }

            public boolean contains(Object obj) {
                return Collections2.a((Collection<?>) AsMap.this.a.entrySet(), obj);
            }

            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                return new AsMapIterator();
            }

            public boolean remove(Object obj) {
                if (!contains(obj)) {
                    return false;
                }
                AbstractMapBasedMultimap.this.removeValuesForKey(((Map.Entry) obj).getKey());
                return true;
            }
        }

        class AsMapIterator implements Iterator<Map.Entry<K, Collection<V>>> {
            final Iterator<Map.Entry<K, Collection<V>>> a = AsMap.this.a.entrySet().iterator();
            Collection<V> b;

            AsMapIterator() {
            }

            public boolean hasNext() {
                return this.a.hasNext();
            }

            public Map.Entry<K, Collection<V>> next() {
                Map.Entry next = this.a.next();
                this.b = (Collection) next.getValue();
                return AsMap.this.a(next);
            }

            public void remove() {
                this.a.remove();
                AbstractMapBasedMultimap.b(AbstractMapBasedMultimap.this, this.b.size());
                this.b.clear();
            }
        }

        AsMap(Map<K, Collection<V>> map) {
            this.a = map;
        }

        /* access modifiers changed from: package-private */
        public Map.Entry<K, Collection<V>> a(Map.Entry<K, Collection<V>> entry) {
            K key = entry.getKey();
            return Maps.immutableEntry(key, AbstractMapBasedMultimap.this.a(key, entry.getValue()));
        }

        public void clear() {
            if (this.a == AbstractMapBasedMultimap.this.map) {
                AbstractMapBasedMultimap.this.clear();
            } else {
                Iterators.b(new AsMapIterator());
            }
        }

        public boolean containsKey(Object obj) {
            return Maps.b((Map<?, ?>) this.a, obj);
        }

        /* access modifiers changed from: protected */
        public Set<Map.Entry<K, Collection<V>>> createEntrySet() {
            return new AsMapEntries();
        }

        public boolean equals(@Nullable Object obj) {
            return this == obj || this.a.equals(obj);
        }

        public Collection<V> get(Object obj) {
            Collection collection = (Collection) Maps.a(this.a, obj);
            if (collection == null) {
                return null;
            }
            return AbstractMapBasedMultimap.this.a(obj, collection);
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public Set<K> keySet() {
            return AbstractMapBasedMultimap.this.keySet();
        }

        public Collection<V> remove(Object obj) {
            Collection remove = this.a.remove(obj);
            if (remove == null) {
                return null;
            }
            Collection<V> c = AbstractMapBasedMultimap.this.c();
            c.addAll(remove);
            AbstractMapBasedMultimap.b(AbstractMapBasedMultimap.this, remove.size());
            remove.clear();
            return c;
        }

        public int size() {
            return this.a.size();
        }

        public String toString() {
            return this.a.toString();
        }
    }

    private abstract class Itr<T> implements Iterator<T> {
        final Iterator<Map.Entry<K, Collection<V>>> b;
        K c = null;
        Collection<V> d = null;
        Iterator<V> e = Iterators.c();

        Itr() {
            this.b = AbstractMapBasedMultimap.this.map.entrySet().iterator();
        }

        /* access modifiers changed from: package-private */
        public abstract T a(K k, V v);

        public boolean hasNext() {
            return this.b.hasNext() || this.e.hasNext();
        }

        public T next() {
            if (!this.e.hasNext()) {
                Map.Entry next = this.b.next();
                this.c = next.getKey();
                this.d = (Collection) next.getValue();
                this.e = this.d.iterator();
            }
            return a(this.c, this.e.next());
        }

        public void remove() {
            this.e.remove();
            if (this.d.isEmpty()) {
                this.b.remove();
            }
            AbstractMapBasedMultimap.b(AbstractMapBasedMultimap.this);
        }
    }

    private class KeySet extends Maps.KeySet<K, Collection<V>> {
        KeySet(Map<K, Collection<V>> map) {
            super(map);
        }

        public void clear() {
            Iterators.b(iterator());
        }

        public boolean containsAll(Collection<?> collection) {
            return c().keySet().containsAll(collection);
        }

        public boolean equals(@Nullable Object obj) {
            return this == obj || c().keySet().equals(obj);
        }

        public int hashCode() {
            return c().keySet().hashCode();
        }

        public Iterator<K> iterator() {
            final Iterator it = c().entrySet().iterator();
            return new Iterator<K>() {
                Map.Entry<K, Collection<V>> a;

                public boolean hasNext() {
                    return it.hasNext();
                }

                public K next() {
                    this.a = (Map.Entry) it.next();
                    return this.a.getKey();
                }

                public void remove() {
                    CollectPreconditions.a(this.a != null);
                    Collection value = this.a.getValue();
                    it.remove();
                    AbstractMapBasedMultimap.b(AbstractMapBasedMultimap.this, value.size());
                    value.clear();
                }
            };
        }

        public boolean remove(Object obj) {
            int i;
            Collection collection = (Collection) c().remove(obj);
            if (collection != null) {
                i = collection.size();
                collection.clear();
                AbstractMapBasedMultimap.b(AbstractMapBasedMultimap.this, i);
            } else {
                i = 0;
            }
            return i > 0;
        }
    }

    @GwtIncompatible
    class NavigableAsMap extends AbstractMapBasedMultimap<K, V>.SortedAsMap implements NavigableMap<K, Collection<V>> {
        NavigableAsMap(NavigableMap<K, Collection<V>> navigableMap) {
            super(navigableMap);
        }

        /* access modifiers changed from: package-private */
        public Map.Entry<K, Collection<V>> a(Iterator<Map.Entry<K, Collection<V>>> it) {
            if (!it.hasNext()) {
                return null;
            }
            Map.Entry next = it.next();
            Collection c2 = AbstractMapBasedMultimap.this.c();
            c2.addAll((Collection) next.getValue());
            it.remove();
            return Maps.immutableEntry(next.getKey(), AbstractMapBasedMultimap.this.a(c2));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public NavigableSet<K> createKeySet() {
            return new NavigableKeySet(d());
        }

        public Map.Entry<K, Collection<V>> ceilingEntry(K k) {
            Map.Entry ceilingEntry = d().ceilingEntry(k);
            if (ceilingEntry == null) {
                return null;
            }
            return a(ceilingEntry);
        }

        public K ceilingKey(K k) {
            return d().ceilingKey(k);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: d_ */
        public NavigableMap<K, Collection<V>> d() {
            return (NavigableMap) super.d();
        }

        public NavigableSet<K> descendingKeySet() {
            return descendingMap().navigableKeySet();
        }

        public NavigableMap<K, Collection<V>> descendingMap() {
            return new NavigableAsMap(d().descendingMap());
        }

        public Map.Entry<K, Collection<V>> firstEntry() {
            Map.Entry firstEntry = d().firstEntry();
            if (firstEntry == null) {
                return null;
            }
            return a(firstEntry);
        }

        public Map.Entry<K, Collection<V>> floorEntry(K k) {
            Map.Entry floorEntry = d().floorEntry(k);
            if (floorEntry == null) {
                return null;
            }
            return a(floorEntry);
        }

        public K floorKey(K k) {
            return d().floorKey(k);
        }

        public NavigableMap<K, Collection<V>> headMap(K k) {
            return headMap(k, false);
        }

        public NavigableMap<K, Collection<V>> headMap(K k, boolean z) {
            return new NavigableAsMap(d().headMap(k, z));
        }

        public Map.Entry<K, Collection<V>> higherEntry(K k) {
            Map.Entry higherEntry = d().higherEntry(k);
            if (higherEntry == null) {
                return null;
            }
            return a(higherEntry);
        }

        public K higherKey(K k) {
            return d().higherKey(k);
        }

        public NavigableSet<K> keySet() {
            return (NavigableSet) super.keySet();
        }

        public Map.Entry<K, Collection<V>> lastEntry() {
            Map.Entry lastEntry = d().lastEntry();
            if (lastEntry == null) {
                return null;
            }
            return a(lastEntry);
        }

        public Map.Entry<K, Collection<V>> lowerEntry(K k) {
            Map.Entry lowerEntry = d().lowerEntry(k);
            if (lowerEntry == null) {
                return null;
            }
            return a(lowerEntry);
        }

        public K lowerKey(K k) {
            return d().lowerKey(k);
        }

        public NavigableSet<K> navigableKeySet() {
            return keySet();
        }

        public Map.Entry<K, Collection<V>> pollFirstEntry() {
            return a(entrySet().iterator());
        }

        public Map.Entry<K, Collection<V>> pollLastEntry() {
            return a(descendingMap().entrySet().iterator());
        }

        public NavigableMap<K, Collection<V>> subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }

        public NavigableMap<K, Collection<V>> subMap(K k, boolean z, K k2, boolean z2) {
            return new NavigableAsMap(d().subMap(k, z, k2, z2));
        }

        public NavigableMap<K, Collection<V>> tailMap(K k) {
            return tailMap(k, true);
        }

        public NavigableMap<K, Collection<V>> tailMap(K k, boolean z) {
            return new NavigableAsMap(d().tailMap(k, z));
        }
    }

    @GwtIncompatible
    class NavigableKeySet extends AbstractMapBasedMultimap<K, V>.SortedKeySet implements NavigableSet<K> {
        NavigableKeySet(NavigableMap<K, Collection<V>> navigableMap) {
            super(navigableMap);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public NavigableMap<K, Collection<V>> b() {
            return (NavigableMap) super.b();
        }

        public K ceiling(K k) {
            return b().ceilingKey(k);
        }

        public Iterator<K> descendingIterator() {
            return descendingSet().iterator();
        }

        public NavigableSet<K> descendingSet() {
            return new NavigableKeySet(b().descendingMap());
        }

        public K floor(K k) {
            return b().floorKey(k);
        }

        public NavigableSet<K> headSet(K k) {
            return headSet(k, false);
        }

        public NavigableSet<K> headSet(K k, boolean z) {
            return new NavigableKeySet(b().headMap(k, z));
        }

        public K higher(K k) {
            return b().higherKey(k);
        }

        public K lower(K k) {
            return b().lowerKey(k);
        }

        public K pollFirst() {
            return Iterators.a(iterator());
        }

        public K pollLast() {
            return Iterators.a(descendingIterator());
        }

        public NavigableSet<K> subSet(K k, K k2) {
            return subSet(k, true, k2, false);
        }

        public NavigableSet<K> subSet(K k, boolean z, K k2, boolean z2) {
            return new NavigableKeySet(b().subMap(k, z, k2, z2));
        }

        public NavigableSet<K> tailSet(K k) {
            return tailSet(k, true);
        }

        public NavigableSet<K> tailSet(K k, boolean z) {
            return new NavigableKeySet(b().tailMap(k, z));
        }
    }

    private class RandomAccessWrappedList extends AbstractMapBasedMultimap<K, V>.WrappedList implements RandomAccess {
        RandomAccessWrappedList(K k, @Nullable List<V> list, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k, list, wrappedCollection);
        }
    }

    private class SortedAsMap extends AbstractMapBasedMultimap<K, V>.AsMap implements SortedMap<K, Collection<V>> {
        SortedSet<K> d;

        SortedAsMap(SortedMap<K, Collection<V>> sortedMap) {
            super(sortedMap);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public SortedSet<K> createKeySet() {
            return new SortedKeySet(d());
        }

        public Comparator<? super K> comparator() {
            return d().comparator();
        }

        /* access modifiers changed from: package-private */
        public SortedMap<K, Collection<V>> d() {
            return (SortedMap) this.a;
        }

        public K firstKey() {
            return d().firstKey();
        }

        public SortedMap<K, Collection<V>> headMap(K k) {
            return new SortedAsMap(d().headMap(k));
        }

        public SortedSet<K> keySet() {
            SortedSet<K> sortedSet = this.d;
            if (sortedSet != null) {
                return sortedSet;
            }
            SortedSet<K> c = createKeySet();
            this.d = c;
            return c;
        }

        public K lastKey() {
            return d().lastKey();
        }

        public SortedMap<K, Collection<V>> subMap(K k, K k2) {
            return new SortedAsMap(d().subMap(k, k2));
        }

        public SortedMap<K, Collection<V>> tailMap(K k) {
            return new SortedAsMap(d().tailMap(k));
        }
    }

    private class SortedKeySet extends AbstractMapBasedMultimap<K, V>.KeySet implements SortedSet<K> {
        SortedKeySet(SortedMap<K, Collection<V>> sortedMap) {
            super(sortedMap);
        }

        /* access modifiers changed from: package-private */
        public SortedMap<K, Collection<V>> b() {
            return (SortedMap) super.c();
        }

        public Comparator<? super K> comparator() {
            return b().comparator();
        }

        public K first() {
            return b().firstKey();
        }

        public SortedSet<K> headSet(K k) {
            return new SortedKeySet(b().headMap(k));
        }

        public K last() {
            return b().lastKey();
        }

        public SortedSet<K> subSet(K k, K k2) {
            return new SortedKeySet(b().subMap(k, k2));
        }

        public SortedSet<K> tailSet(K k) {
            return new SortedKeySet(b().tailMap(k));
        }
    }

    private class WrappedCollection extends AbstractCollection<V> {
        final K b;
        Collection<V> c;
        final AbstractMapBasedMultimap<K, V>.WrappedCollection d;
        final Collection<V> e;

        class WrappedIterator implements Iterator<V> {
            final Iterator<V> a;
            final Collection<V> b = WrappedCollection.this.c;

            WrappedIterator() {
                this.a = AbstractMapBasedMultimap.this.iteratorOrListIterator(WrappedCollection.this.c);
            }

            WrappedIterator(Iterator<V> it) {
                this.a = it;
            }

            /* access modifiers changed from: package-private */
            public void a() {
                WrappedCollection.this.a();
                if (WrappedCollection.this.c != this.b) {
                    throw new ConcurrentModificationException();
                }
            }

            /* access modifiers changed from: package-private */
            public Iterator<V> b() {
                a();
                return this.a;
            }

            public boolean hasNext() {
                a();
                return this.a.hasNext();
            }

            public V next() {
                a();
                return this.a.next();
            }

            public void remove() {
                this.a.remove();
                AbstractMapBasedMultimap.b(AbstractMapBasedMultimap.this);
                WrappedCollection.this.b();
            }
        }

        WrappedCollection(K k, @Nullable Collection<V> collection, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            this.b = k;
            this.c = collection;
            this.d = wrappedCollection;
            this.e = wrappedCollection == null ? null : wrappedCollection.e();
        }

        /* access modifiers changed from: package-private */
        public void a() {
            Collection<V> collection;
            AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection = this.d;
            if (wrappedCollection != null) {
                wrappedCollection.a();
                if (this.d.e() != this.e) {
                    throw new ConcurrentModificationException();
                }
            } else if (this.c.isEmpty() && (collection = (Collection) AbstractMapBasedMultimap.this.map.get(this.b)) != null) {
                this.c = collection;
            }
        }

        public boolean add(V v) {
            a();
            boolean isEmpty = this.c.isEmpty();
            boolean add = this.c.add(v);
            if (add) {
                AbstractMapBasedMultimap.c(AbstractMapBasedMultimap.this);
                if (isEmpty) {
                    d();
                }
            }
            return add;
        }

        public boolean addAll(Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = this.c.addAll(collection);
            if (addAll) {
                AbstractMapBasedMultimap.a(AbstractMapBasedMultimap.this, this.c.size() - size);
                if (size == 0) {
                    d();
                }
            }
            return addAll;
        }

        /* access modifiers changed from: package-private */
        public void b() {
            AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection = this.d;
            if (wrappedCollection != null) {
                wrappedCollection.b();
            } else if (this.c.isEmpty()) {
                AbstractMapBasedMultimap.this.map.remove(this.b);
            }
        }

        /* access modifiers changed from: package-private */
        public K c() {
            return this.b;
        }

        public void clear() {
            int size = size();
            if (size != 0) {
                this.c.clear();
                AbstractMapBasedMultimap.b(AbstractMapBasedMultimap.this, size);
                b();
            }
        }

        public boolean contains(Object obj) {
            a();
            return this.c.contains(obj);
        }

        public boolean containsAll(Collection<?> collection) {
            a();
            return this.c.containsAll(collection);
        }

        /* access modifiers changed from: package-private */
        public void d() {
            AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection = this.d;
            if (wrappedCollection != null) {
                wrappedCollection.d();
            } else {
                AbstractMapBasedMultimap.this.map.put(this.b, this.c);
            }
        }

        /* access modifiers changed from: package-private */
        public Collection<V> e() {
            return this.c;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            a();
            return this.c.equals(obj);
        }

        /* access modifiers changed from: package-private */
        public AbstractMapBasedMultimap<K, V>.WrappedCollection f() {
            return this.d;
        }

        public int hashCode() {
            a();
            return this.c.hashCode();
        }

        public Iterator<V> iterator() {
            a();
            return new WrappedIterator();
        }

        public boolean remove(Object obj) {
            a();
            boolean remove = this.c.remove(obj);
            if (remove) {
                AbstractMapBasedMultimap.b(AbstractMapBasedMultimap.this);
                b();
            }
            return remove;
        }

        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean removeAll = this.c.removeAll(collection);
            if (removeAll) {
                AbstractMapBasedMultimap.a(AbstractMapBasedMultimap.this, this.c.size() - size);
                b();
            }
            return removeAll;
        }

        public boolean retainAll(Collection<?> collection) {
            Preconditions.checkNotNull(collection);
            int size = size();
            boolean retainAll = this.c.retainAll(collection);
            if (retainAll) {
                AbstractMapBasedMultimap.a(AbstractMapBasedMultimap.this, this.c.size() - size);
                b();
            }
            return retainAll;
        }

        public int size() {
            a();
            return this.c.size();
        }

        public String toString() {
            a();
            return this.c.toString();
        }
    }

    private class WrappedList extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements List<V> {

        private class WrappedListIterator extends AbstractMapBasedMultimap<K, V>.WrappedIterator implements ListIterator<V> {
            WrappedListIterator() {
                super();
            }

            public WrappedListIterator(int i) {
                super(WrappedList.this.g().listIterator(i));
            }

            private ListIterator<V> getDelegateListIterator() {
                return (ListIterator) b();
            }

            public void add(V v) {
                boolean isEmpty = WrappedList.this.isEmpty();
                getDelegateListIterator().add(v);
                AbstractMapBasedMultimap.c(AbstractMapBasedMultimap.this);
                if (isEmpty) {
                    WrappedList.this.d();
                }
            }

            public boolean hasPrevious() {
                return getDelegateListIterator().hasPrevious();
            }

            public int nextIndex() {
                return getDelegateListIterator().nextIndex();
            }

            public V previous() {
                return getDelegateListIterator().previous();
            }

            public int previousIndex() {
                return getDelegateListIterator().previousIndex();
            }

            public void set(V v) {
                getDelegateListIterator().set(v);
            }
        }

        WrappedList(K k, @Nullable List<V> list, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k, list, wrappedCollection);
        }

        public void add(int i, V v) {
            a();
            boolean isEmpty = e().isEmpty();
            g().add(i, v);
            AbstractMapBasedMultimap.c(AbstractMapBasedMultimap.this);
            if (isEmpty) {
                d();
            }
        }

        public boolean addAll(int i, Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = g().addAll(i, collection);
            if (addAll) {
                AbstractMapBasedMultimap.a(AbstractMapBasedMultimap.this, e().size() - size);
                if (size == 0) {
                    d();
                }
            }
            return addAll;
        }

        /* access modifiers changed from: package-private */
        public List<V> g() {
            return (List) e();
        }

        public V get(int i) {
            a();
            return g().get(i);
        }

        public int indexOf(Object obj) {
            a();
            return g().indexOf(obj);
        }

        public int lastIndexOf(Object obj) {
            a();
            return g().lastIndexOf(obj);
        }

        public ListIterator<V> listIterator() {
            a();
            return new WrappedListIterator();
        }

        public ListIterator<V> listIterator(int i) {
            a();
            return new WrappedListIterator(i);
        }

        public V remove(int i) {
            a();
            V remove = g().remove(i);
            AbstractMapBasedMultimap.b(AbstractMapBasedMultimap.this);
            b();
            return remove;
        }

        public V set(int i, V v) {
            a();
            return g().set(i, v);
        }

        public List<V> subList(int i, int i2) {
            a();
            return AbstractMapBasedMultimap.this.wrapList(c(), g().subList(i, i2), f() == null ? this : f());
        }
    }

    @GwtIncompatible
    class WrappedNavigableSet extends AbstractMapBasedMultimap<K, V>.WrappedSortedSet implements NavigableSet<V> {
        WrappedNavigableSet(K k, @Nullable NavigableSet<V> navigableSet, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k, navigableSet, wrappedCollection);
        }

        private NavigableSet<V> wrap(NavigableSet<V> navigableSet) {
            return new WrappedNavigableSet(this.b, navigableSet, f() == null ? this : f());
        }

        public V ceiling(V v) {
            return h().ceiling(v);
        }

        public Iterator<V> descendingIterator() {
            return new WrappedCollection.WrappedIterator(h().descendingIterator());
        }

        public NavigableSet<V> descendingSet() {
            return wrap(h().descendingSet());
        }

        public V floor(V v) {
            return h().floor(v);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: g */
        public NavigableSet<V> h() {
            return (NavigableSet) super.h();
        }

        public NavigableSet<V> headSet(V v, boolean z) {
            return wrap(h().headSet(v, z));
        }

        public V higher(V v) {
            return h().higher(v);
        }

        public V lower(V v) {
            return h().lower(v);
        }

        public V pollFirst() {
            return Iterators.a(iterator());
        }

        public V pollLast() {
            return Iterators.a(descendingIterator());
        }

        public NavigableSet<V> subSet(V v, boolean z, V v2, boolean z2) {
            return wrap(h().subSet(v, z, v2, z2));
        }

        public NavigableSet<V> tailSet(V v, boolean z) {
            return wrap(h().tailSet(v, z));
        }
    }

    private class WrappedSet extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements Set<V> {
        WrappedSet(K k, @Nullable Set<V> set) {
            super(k, set, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null);
        }

        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean a2 = Sets.a((Set<?>) (Set) this.c, collection);
            if (a2) {
                AbstractMapBasedMultimap.a(AbstractMapBasedMultimap.this, this.c.size() - size);
                b();
            }
            return a2;
        }
    }

    private class WrappedSortedSet extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements SortedSet<V> {
        WrappedSortedSet(K k, @Nullable SortedSet<V> sortedSet, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k, sortedSet, wrappedCollection);
        }

        public Comparator<? super V> comparator() {
            return h().comparator();
        }

        public V first() {
            a();
            return h().first();
        }

        /* access modifiers changed from: package-private */
        public SortedSet<V> h() {
            return (SortedSet) e();
        }

        public SortedSet<V> headSet(V v) {
            a();
            return new WrappedSortedSet(c(), h().headSet(v), f() == null ? this : f());
        }

        public V last() {
            a();
            return h().last();
        }

        public SortedSet<V> subSet(V v, V v2) {
            a();
            return new WrappedSortedSet(c(), h().subSet(v, v2), f() == null ? this : f());
        }

        public SortedSet<V> tailSet(V v) {
            a();
            return new WrappedSortedSet(c(), h().tailSet(v), f() == null ? this : f());
        }
    }

    protected AbstractMapBasedMultimap(Map<K, Collection<V>> map2) {
        Preconditions.checkArgument(map2.isEmpty());
        this.map = map2;
    }

    static /* synthetic */ int a(AbstractMapBasedMultimap abstractMapBasedMultimap, int i) {
        int i2 = abstractMapBasedMultimap.totalSize + i;
        abstractMapBasedMultimap.totalSize = i2;
        return i2;
    }

    static /* synthetic */ int b(AbstractMapBasedMultimap abstractMapBasedMultimap) {
        int i = abstractMapBasedMultimap.totalSize;
        abstractMapBasedMultimap.totalSize = i - 1;
        return i;
    }

    static /* synthetic */ int b(AbstractMapBasedMultimap abstractMapBasedMultimap, int i) {
        int i2 = abstractMapBasedMultimap.totalSize - i;
        abstractMapBasedMultimap.totalSize = i2;
        return i2;
    }

    static /* synthetic */ int c(AbstractMapBasedMultimap abstractMapBasedMultimap) {
        int i = abstractMapBasedMultimap.totalSize;
        abstractMapBasedMultimap.totalSize = i + 1;
        return i;
    }

    private Collection<V> getOrCreateCollection(@Nullable K k) {
        Collection<V> collection = this.map.get(k);
        if (collection != null) {
            return collection;
        }
        Collection<V> a = a(k);
        this.map.put(k, a);
        return a;
    }

    /* access modifiers changed from: private */
    public Iterator<V> iteratorOrListIterator(Collection<V> collection) {
        return collection instanceof List ? ((List) collection).listIterator() : collection.iterator();
    }

    /* access modifiers changed from: private */
    public void removeValuesForKey(Object obj) {
        Collection collection = (Collection) Maps.c(this.map, obj);
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            this.totalSize -= size;
        }
    }

    /* access modifiers changed from: private */
    public List<V> wrapList(@Nullable K k, List<V> list, @Nullable AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
        return list instanceof RandomAccess ? new RandomAccessWrappedList(k, list, wrappedCollection) : new WrappedList(k, list, wrappedCollection);
    }

    /* access modifiers changed from: package-private */
    public Collection<V> a(@Nullable K k) {
        return c();
    }

    /* access modifiers changed from: package-private */
    public Collection<V> a(@Nullable K k, Collection<V> collection) {
        return collection instanceof SortedSet ? new WrappedSortedSet(k, (SortedSet) collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null) : collection instanceof Set ? new WrappedSet(k, (Set) collection) : collection instanceof List ? wrapList(k, (List) collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null) : new WrappedCollection(k, collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null);
    }

    /* access modifiers changed from: package-private */
    public Collection<V> a(Collection<V> collection) {
        return collection instanceof SortedSet ? Collections.unmodifiableSortedSet((SortedSet) collection) : collection instanceof Set ? Collections.unmodifiableSet((Set) collection) : collection instanceof List ? Collections.unmodifiableList((List) collection) : Collections.unmodifiableCollection(collection);
    }

    /* access modifiers changed from: package-private */
    public final void a(Map<K, Collection<V>> map2) {
        this.map = map2;
        this.totalSize = 0;
        for (Collection next : map2.values()) {
            Preconditions.checkArgument(!next.isEmpty());
            this.totalSize += next.size();
        }
    }

    /* access modifiers changed from: package-private */
    public abstract Collection<V> c();

    public void clear() {
        for (Collection<V> clear : this.map.values()) {
            clear.clear();
        }
        this.map.clear();
        this.totalSize = 0;
    }

    public boolean containsKey(@Nullable Object obj) {
        return this.map.containsKey(obj);
    }

    /* access modifiers changed from: package-private */
    public Collection<V> d() {
        return a(c());
    }

    /* access modifiers changed from: package-private */
    public Map<K, Collection<V>> e() {
        return this.map;
    }

    public Collection<Map.Entry<K, V>> entries() {
        return super.entries();
    }

    /* access modifiers changed from: package-private */
    public Set<K> f() {
        Map<K, Collection<V>> map2 = this.map;
        return map2 instanceof SortedMap ? new SortedKeySet((SortedMap) map2) : new KeySet(map2);
    }

    /* access modifiers changed from: package-private */
    public Iterator<V> g() {
        return new AbstractMapBasedMultimap<K, V>.Itr<V>() {
            /* access modifiers changed from: package-private */
            public V a(K k, V v) {
                return v;
            }
        };
    }

    public Collection<V> get(@Nullable K k) {
        Collection collection = this.map.get(k);
        if (collection == null) {
            collection = a(k);
        }
        return a(k, collection);
    }

    /* access modifiers changed from: package-private */
    public Iterator<Map.Entry<K, V>> h() {
        return new AbstractMapBasedMultimap<K, V>.Itr<Map.Entry<K, V>>() {
            /* access modifiers changed from: package-private */
            /* renamed from: b */
            public Map.Entry<K, V> a(K k, V v) {
                return Maps.immutableEntry(k, v);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public Map<K, Collection<V>> i() {
        Map<K, Collection<V>> map2 = this.map;
        return map2 instanceof SortedMap ? new SortedAsMap((SortedMap) map2) : new AsMap(map2);
    }

    public boolean put(@Nullable K k, @Nullable V v) {
        Collection collection = this.map.get(k);
        if (collection == null) {
            Collection a = a(k);
            if (a.add(v)) {
                this.totalSize++;
                this.map.put(k, a);
                return true;
            }
            throw new AssertionError("New Collection violated the Collection spec");
        } else if (!collection.add(v)) {
            return false;
        } else {
            this.totalSize++;
            return true;
        }
    }

    public Collection<V> removeAll(@Nullable Object obj) {
        Collection remove = this.map.remove(obj);
        if (remove == null) {
            return d();
        }
        Collection c = c();
        c.addAll(remove);
        this.totalSize -= remove.size();
        remove.clear();
        return a(c);
    }

    public Collection<V> replaceValues(@Nullable K k, Iterable<? extends V> iterable) {
        Iterator<? extends V> it = iterable.iterator();
        if (!it.hasNext()) {
            return removeAll(k);
        }
        Collection orCreateCollection = getOrCreateCollection(k);
        Collection c = c();
        c.addAll(orCreateCollection);
        this.totalSize -= orCreateCollection.size();
        orCreateCollection.clear();
        while (it.hasNext()) {
            if (orCreateCollection.add(it.next())) {
                this.totalSize++;
            }
        }
        return a(c);
    }

    public int size() {
        return this.totalSize;
    }

    public Collection<V> values() {
        return super.values();
    }
}

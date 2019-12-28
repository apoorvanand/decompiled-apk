package androidx.collection;

import androidx.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

abstract class MapCollections<K, V> {
    @Nullable
    MapCollections<K, V>.EntrySet b;
    @Nullable
    MapCollections<K, V>.KeySet c;
    @Nullable
    MapCollections<K, V>.ValuesCollection d;

    final class ArrayIterator<T> implements Iterator<T> {
        final int a;
        int b;
        int c;
        boolean d = false;

        ArrayIterator(int i) {
            this.a = i;
            this.b = MapCollections.this.a();
        }

        public boolean hasNext() {
            return this.c < this.b;
        }

        public T next() {
            if (hasNext()) {
                T a2 = MapCollections.this.a(this.c, this.a);
                this.c++;
                this.d = true;
                return a2;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (this.d) {
                this.c--;
                this.b--;
                this.d = false;
                MapCollections.this.a(this.c);
                return;
            }
            throw new IllegalStateException();
        }
    }

    final class EntrySet implements Set<Map.Entry<K, V>> {
        EntrySet() {
        }

        public boolean add(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends Map.Entry<K, V>> collection) {
            int a2 = MapCollections.this.a();
            for (Map.Entry entry : collection) {
                MapCollections.this.a(entry.getKey(), entry.getValue());
            }
            return a2 != MapCollections.this.a();
        }

        public void clear() {
            MapCollections.this.c();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            int a2 = MapCollections.this.a(entry.getKey());
            if (a2 < 0) {
                return false;
            }
            return ContainerHelpers.equal(MapCollections.this.a(a2, 1), entry.getValue());
        }

        public boolean containsAll(Collection<?> collection) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object obj) {
            return MapCollections.equalsSetHelper(this, obj);
        }

        public int hashCode() {
            int i = 0;
            for (int a2 = MapCollections.this.a() - 1; a2 >= 0; a2--) {
                Object a3 = MapCollections.this.a(a2, 0);
                Object a4 = MapCollections.this.a(a2, 1);
                i += (a3 == null ? 0 : a3.hashCode()) ^ (a4 == null ? 0 : a4.hashCode());
            }
            return i;
        }

        public boolean isEmpty() {
            return MapCollections.this.a() == 0;
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new MapIterator();
        }

        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public int size() {
            return MapCollections.this.a();
        }

        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        public <T> T[] toArray(T[] tArr) {
            throw new UnsupportedOperationException();
        }
    }

    final class KeySet implements Set<K> {
        KeySet() {
        }

        public boolean add(K k) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            MapCollections.this.c();
        }

        public boolean contains(Object obj) {
            return MapCollections.this.a(obj) >= 0;
        }

        public boolean containsAll(Collection<?> collection) {
            return MapCollections.containsAllHelper(MapCollections.this.b(), collection);
        }

        public boolean equals(Object obj) {
            return MapCollections.equalsSetHelper(this, obj);
        }

        public int hashCode() {
            int i = 0;
            for (int a2 = MapCollections.this.a() - 1; a2 >= 0; a2--) {
                Object a3 = MapCollections.this.a(a2, 0);
                i += a3 == null ? 0 : a3.hashCode();
            }
            return i;
        }

        public boolean isEmpty() {
            return MapCollections.this.a() == 0;
        }

        public Iterator<K> iterator() {
            return new ArrayIterator(0);
        }

        public boolean remove(Object obj) {
            int a2 = MapCollections.this.a(obj);
            if (a2 < 0) {
                return false;
            }
            MapCollections.this.a(a2);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            return MapCollections.removeAllHelper(MapCollections.this.b(), collection);
        }

        public boolean retainAll(Collection<?> collection) {
            return MapCollections.retainAllHelper(MapCollections.this.b(), collection);
        }

        public int size() {
            return MapCollections.this.a();
        }

        public Object[] toArray() {
            return MapCollections.this.toArrayHelper(0);
        }

        public <T> T[] toArray(T[] tArr) {
            return MapCollections.this.toArrayHelper(tArr, 0);
        }
    }

    final class MapIterator implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {
        int a;
        int b;
        boolean c = false;

        MapIterator() {
            this.a = MapCollections.this.a() - 1;
            this.b = -1;
        }

        public boolean equals(Object obj) {
            if (!this.c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            } else if (!(obj instanceof Map.Entry)) {
                return false;
            } else {
                Map.Entry entry = (Map.Entry) obj;
                return ContainerHelpers.equal(entry.getKey(), MapCollections.this.a(this.b, 0)) && ContainerHelpers.equal(entry.getValue(), MapCollections.this.a(this.b, 1));
            }
        }

        public K getKey() {
            if (this.c) {
                return MapCollections.this.a(this.b, 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public V getValue() {
            if (this.c) {
                return MapCollections.this.a(this.b, 1);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public boolean hasNext() {
            return this.b < this.a;
        }

        public int hashCode() {
            if (this.c) {
                int i = 0;
                Object a2 = MapCollections.this.a(this.b, 0);
                Object a3 = MapCollections.this.a(this.b, 1);
                int hashCode = a2 == null ? 0 : a2.hashCode();
                if (a3 != null) {
                    i = a3.hashCode();
                }
                return hashCode ^ i;
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public Map.Entry<K, V> next() {
            if (hasNext()) {
                this.b++;
                this.c = true;
                return this;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (this.c) {
                MapCollections.this.a(this.b);
                this.b--;
                this.a--;
                this.c = false;
                return;
            }
            throw new IllegalStateException();
        }

        public V setValue(V v) {
            if (this.c) {
                return MapCollections.this.a(this.b, v);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public String toString() {
            return getKey() + "=" + getValue();
        }
    }

    final class ValuesCollection implements Collection<V> {
        ValuesCollection() {
        }

        public boolean add(V v) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            MapCollections.this.c();
        }

        public boolean contains(Object obj) {
            return MapCollections.this.b(obj) >= 0;
        }

        public boolean containsAll(Collection<?> collection) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public boolean isEmpty() {
            return MapCollections.this.a() == 0;
        }

        public Iterator<V> iterator() {
            return new ArrayIterator(1);
        }

        public boolean remove(Object obj) {
            int b = MapCollections.this.b(obj);
            if (b < 0) {
                return false;
            }
            MapCollections.this.a(b);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            int a2 = MapCollections.this.a();
            int i = 0;
            boolean z = false;
            while (i < a2) {
                if (collection.contains(MapCollections.this.a(i, 1))) {
                    MapCollections.this.a(i);
                    i--;
                    a2--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        public boolean retainAll(Collection<?> collection) {
            int a2 = MapCollections.this.a();
            int i = 0;
            boolean z = false;
            while (i < a2) {
                if (!collection.contains(MapCollections.this.a(i, 1))) {
                    MapCollections.this.a(i);
                    i--;
                    a2--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        public int size() {
            return MapCollections.this.a();
        }

        public Object[] toArray() {
            return MapCollections.this.toArrayHelper(1);
        }

        public <T> T[] toArray(T[] tArr) {
            return MapCollections.this.toArrayHelper(tArr, 1);
        }
    }

    MapCollections() {
    }

    public static <K, V> boolean containsAllHelper(Map<K, V> map, Collection<?> collection) {
        for (Object containsKey : collection) {
            if (!map.containsKey(containsKey)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean equalsSetHelper(Set<T> set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                return set.size() == set2.size() && set.containsAll(set2);
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public static <K, V> boolean removeAllHelper(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        for (Object remove : collection) {
            map.remove(remove);
        }
        return size != map.size();
    }

    public static <K, V> boolean retainAllHelper(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator<K> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    /* access modifiers changed from: protected */
    public abstract int a();

    /* access modifiers changed from: protected */
    public abstract int a(Object obj);

    /* access modifiers changed from: protected */
    public abstract Object a(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract V a(int i, V v);

    /* access modifiers changed from: protected */
    public abstract void a(int i);

    /* access modifiers changed from: protected */
    public abstract void a(K k, V v);

    /* access modifiers changed from: protected */
    public abstract int b(Object obj);

    /* access modifiers changed from: protected */
    public abstract Map<K, V> b();

    /* access modifiers changed from: protected */
    public abstract void c();

    public Set<Map.Entry<K, V>> getEntrySet() {
        if (this.b == null) {
            this.b = new EntrySet();
        }
        return this.b;
    }

    public Set<K> getKeySet() {
        if (this.c == null) {
            this.c = new KeySet();
        }
        return this.c;
    }

    public Collection<V> getValues() {
        if (this.d == null) {
            this.d = new ValuesCollection();
        }
        return this.d;
    }

    public Object[] toArrayHelper(int i) {
        int a = a();
        Object[] objArr = new Object[a];
        for (int i2 = 0; i2 < a; i2++) {
            objArr[i2] = a(i2, i);
        }
        return objArr;
    }

    public <T> T[] toArrayHelper(T[] tArr, int i) {
        int a = a();
        if (tArr.length < a) {
            tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), a);
        }
        for (int i2 = 0; i2 < a; i2++) {
            tArr[i2] = a(i2, i);
        }
        if (tArr.length > a) {
            tArr[a] = null;
        }
        return tArr;
    }
}

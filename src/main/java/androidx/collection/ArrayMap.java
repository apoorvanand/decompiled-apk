package androidx.collection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class ArrayMap<K, V> extends SimpleArrayMap<K, V> implements Map<K, V> {
    @Nullable
    MapCollections<K, V> a;

    public ArrayMap() {
    }

    public ArrayMap(int i) {
        super(i);
    }

    public ArrayMap(SimpleArrayMap simpleArrayMap) {
        super(simpleArrayMap);
    }

    private MapCollections<K, V> getCollection() {
        if (this.a == null) {
            this.a = new MapCollections<K, V>() {
                /* access modifiers changed from: protected */
                public int a() {
                    return ArrayMap.this.h;
                }

                /* access modifiers changed from: protected */
                public int a(Object obj) {
                    return ArrayMap.this.indexOfKey(obj);
                }

                /* access modifiers changed from: protected */
                public Object a(int i, int i2) {
                    return ArrayMap.this.g[(i << 1) + i2];
                }

                /* access modifiers changed from: protected */
                public V a(int i, V v) {
                    return ArrayMap.this.setValueAt(i, v);
                }

                /* access modifiers changed from: protected */
                public void a(int i) {
                    ArrayMap.this.removeAt(i);
                }

                /* access modifiers changed from: protected */
                public void a(K k, V v) {
                    ArrayMap.this.put(k, v);
                }

                /* access modifiers changed from: protected */
                public int b(Object obj) {
                    return ArrayMap.this.a(obj);
                }

                /* access modifiers changed from: protected */
                public Map<K, V> b() {
                    return ArrayMap.this;
                }

                /* access modifiers changed from: protected */
                public void c() {
                    ArrayMap.this.clear();
                }
            };
        }
        return this.a;
    }

    public boolean containsAll(@NonNull Collection<?> collection) {
        return MapCollections.containsAllHelper(this, collection);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return getCollection().getEntrySet();
    }

    public Set<K> keySet() {
        return getCollection().getKeySet();
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        ensureCapacity(this.h + map.size());
        for (Map.Entry next : map.entrySet()) {
            put(next.getKey(), next.getValue());
        }
    }

    public boolean removeAll(@NonNull Collection<?> collection) {
        return MapCollections.removeAllHelper(this, collection);
    }

    public boolean retainAll(@NonNull Collection<?> collection) {
        return MapCollections.retainAllHelper(this, collection);
    }

    public Collection<V> values() {
        return getCollection().getValues();
    }
}

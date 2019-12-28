package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;

@GwtIncompatible
public abstract class ForwardingNavigableMap<K, V> extends ForwardingSortedMap<K, V> implements NavigableMap<K, V> {

    @Beta
    protected class StandardDescendingMap extends Maps.DescendingMap<K, V> {
        public StandardDescendingMap() {
        }

        /* access modifiers changed from: package-private */
        public NavigableMap<K, V> b() {
            return ForwardingNavigableMap.this;
        }

        /* access modifiers changed from: protected */
        public Iterator<Map.Entry<K, V>> c() {
            return new Iterator<Map.Entry<K, V>>() {
                private Map.Entry<K, V> nextOrNull = StandardDescendingMap.this.b().lastEntry();
                private Map.Entry<K, V> toRemove = null;

                public boolean hasNext() {
                    return this.nextOrNull != null;
                }

                public Map.Entry<K, V> next() {
                    if (hasNext()) {
                        try {
                            return this.nextOrNull;
                        } finally {
                            this.toRemove = this.nextOrNull;
                            this.nextOrNull = StandardDescendingMap.this.b().lowerEntry(this.nextOrNull.getKey());
                        }
                    } else {
                        throw new NoSuchElementException();
                    }
                }

                public void remove() {
                    CollectPreconditions.a(this.toRemove != null);
                    StandardDescendingMap.this.b().remove(this.toRemove.getKey());
                    this.toRemove = null;
                }
            };
        }
    }

    @Beta
    protected class StandardNavigableKeySet extends Maps.NavigableKeySet<K, V> {
        public StandardNavigableKeySet() {
            super(ForwardingNavigableMap.this);
        }
    }

    protected ForwardingNavigableMap() {
    }

    /* access modifiers changed from: protected */
    public abstract NavigableMap<K, V> b();

    public Map.Entry<K, V> ceilingEntry(K k) {
        return b().ceilingEntry(k);
    }

    public K ceilingKey(K k) {
        return b().ceilingKey(k);
    }

    public NavigableSet<K> descendingKeySet() {
        return b().descendingKeySet();
    }

    public NavigableMap<K, V> descendingMap() {
        return b().descendingMap();
    }

    public Map.Entry<K, V> firstEntry() {
        return b().firstEntry();
    }

    public Map.Entry<K, V> floorEntry(K k) {
        return b().floorEntry(k);
    }

    public K floorKey(K k) {
        return b().floorKey(k);
    }

    public NavigableMap<K, V> headMap(K k, boolean z) {
        return b().headMap(k, z);
    }

    public Map.Entry<K, V> higherEntry(K k) {
        return b().higherEntry(k);
    }

    public K higherKey(K k) {
        return b().higherKey(k);
    }

    public Map.Entry<K, V> lastEntry() {
        return b().lastEntry();
    }

    public Map.Entry<K, V> lowerEntry(K k) {
        return b().lowerEntry(k);
    }

    public K lowerKey(K k) {
        return b().lowerKey(k);
    }

    public NavigableSet<K> navigableKeySet() {
        return b().navigableKeySet();
    }

    public Map.Entry<K, V> pollFirstEntry() {
        return b().pollFirstEntry();
    }

    public Map.Entry<K, V> pollLastEntry() {
        return b().pollLastEntry();
    }

    public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
        return b().subMap(k, z, k2, z2);
    }

    public NavigableMap<K, V> tailMap(K k, boolean z) {
        return b().tailMap(k, z);
    }
}

package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
@Deprecated
@Beta
public final class MapConstraints {

    static class ConstrainedAsMapEntries<K, V> extends ForwardingSet<Map.Entry<K, Collection<V>>> {
        /* access modifiers changed from: private */
        public final MapConstraint<? super K, ? super V> constraint;
        private final Set<Map.Entry<K, Collection<V>>> entries;

        ConstrainedAsMapEntries(Set<Map.Entry<K, Collection<V>>> set, MapConstraint<? super K, ? super V> mapConstraint) {
            this.entries = set;
            this.constraint = mapConstraint;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Set<Map.Entry<K, Collection<V>>> delegate() {
            return this.entries;
        }

        public boolean contains(Object obj) {
            return Maps.a(delegate(), obj);
        }

        public boolean containsAll(Collection<?> collection) {
            return a(collection);
        }

        public boolean equals(@Nullable Object obj) {
            return e(obj);
        }

        public int hashCode() {
            return e();
        }

        public Iterator<Map.Entry<K, Collection<V>>> iterator() {
            return new TransformedIterator<Map.Entry<K, Collection<V>>, Map.Entry<K, Collection<V>>>(this.entries.iterator()) {
                /* access modifiers changed from: package-private */
                public Map.Entry<K, Collection<V>> a(Map.Entry<K, Collection<V>> entry) {
                    return MapConstraints.constrainedAsMapEntry(entry, ConstrainedAsMapEntries.this.constraint);
                }
            };
        }

        public boolean remove(Object obj) {
            return Maps.b(delegate(), obj);
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

    private static class ConstrainedAsMapValues<K, V> extends ForwardingCollection<Collection<V>> {
        final Collection<Collection<V>> a;
        final Set<Map.Entry<K, Collection<V>>> b;

        ConstrainedAsMapValues(Collection<Collection<V>> collection, Set<Map.Entry<K, Collection<V>>> set) {
            this.a = collection;
            this.b = set;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public Collection<Collection<V>> delegate() {
            return this.a;
        }

        public boolean contains(Object obj) {
            return a(obj);
        }

        public boolean containsAll(Collection<?> collection) {
            return a(collection);
        }

        public Iterator<Collection<V>> iterator() {
            final Iterator<Map.Entry<K, Collection<V>>> it = this.b.iterator();
            return new Iterator<Collection<V>>() {
                public boolean hasNext() {
                    return it.hasNext();
                }

                public Collection<V> next() {
                    return (Collection) ((Map.Entry) it.next()).getValue();
                }

                public void remove() {
                    it.remove();
                }
            };
        }

        public boolean remove(Object obj) {
            return b(obj);
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

    private static class ConstrainedEntries<K, V> extends ForwardingCollection<Map.Entry<K, V>> {
        final MapConstraint<? super K, ? super V> a;
        final Collection<Map.Entry<K, V>> b;

        ConstrainedEntries(Collection<Map.Entry<K, V>> collection, MapConstraint<? super K, ? super V> mapConstraint) {
            this.b = collection;
            this.a = mapConstraint;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public Collection<Map.Entry<K, V>> delegate() {
            return this.b;
        }

        public boolean contains(Object obj) {
            return Maps.a(delegate(), obj);
        }

        public boolean containsAll(Collection<?> collection) {
            return a(collection);
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new TransformedIterator<Map.Entry<K, V>, Map.Entry<K, V>>(this.b.iterator()) {
                /* access modifiers changed from: package-private */
                public Map.Entry<K, V> a(Map.Entry<K, V> entry) {
                    return MapConstraints.constrainedEntry(entry, ConstrainedEntries.this.a);
                }
            };
        }

        public boolean remove(Object obj) {
            return Maps.b(delegate(), obj);
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

    static class ConstrainedEntrySet<K, V> extends ConstrainedEntries<K, V> implements Set<Map.Entry<K, V>> {
        ConstrainedEntrySet(Set<Map.Entry<K, V>> set, MapConstraint<? super K, ? super V> mapConstraint) {
            super(set, mapConstraint);
        }

        public boolean equals(@Nullable Object obj) {
            return Sets.a((Set<?>) this, obj);
        }

        public int hashCode() {
            return Sets.a(this);
        }
    }

    private static class ConstrainedListMultimap<K, V> extends ConstrainedMultimap<K, V> implements ListMultimap<K, V> {
        ConstrainedListMultimap(ListMultimap<K, V> listMultimap, MapConstraint<? super K, ? super V> mapConstraint) {
            super(listMultimap, mapConstraint);
        }

        public List<V> get(K k) {
            return (List) super.get(k);
        }

        public List<V> removeAll(Object obj) {
            return (List) super.removeAll(obj);
        }

        public List<V> replaceValues(K k, Iterable<? extends V> iterable) {
            return (List) super.replaceValues(k, iterable);
        }
    }

    static class ConstrainedMap<K, V> extends ForwardingMap<K, V> {
        final MapConstraint<? super K, ? super V> a;
        private final Map<K, V> delegate;
        private transient Set<Map.Entry<K, V>> entrySet;

        ConstrainedMap(Map<K, V> map, MapConstraint<? super K, ? super V> mapConstraint) {
            this.delegate = (Map) Preconditions.checkNotNull(map);
            this.a = (MapConstraint) Preconditions.checkNotNull(mapConstraint);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Map<K, V> delegate() {
            return this.delegate;
        }

        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set = this.entrySet;
            if (set != null) {
                return set;
            }
            Set<Map.Entry<K, V>> a2 = MapConstraints.constrainedEntrySet(this.delegate.entrySet(), this.a);
            this.entrySet = a2;
            return a2;
        }

        @CanIgnoreReturnValue
        public V put(K k, V v) {
            this.a.checkKeyValue(k, v);
            return this.delegate.put(k, v);
        }

        public void putAll(Map<? extends K, ? extends V> map) {
            this.delegate.putAll(MapConstraints.checkMap(map, this.a));
        }
    }

    private static class ConstrainedMultimap<K, V> extends ForwardingMultimap<K, V> implements Serializable {
        final MapConstraint<? super K, ? super V> a;
        final Multimap<K, V> b;
        transient Collection<Map.Entry<K, V>> c;
        transient Map<K, Collection<V>> d;

        public ConstrainedMultimap(Multimap<K, V> multimap, MapConstraint<? super K, ? super V> mapConstraint) {
            this.b = (Multimap) Preconditions.checkNotNull(multimap);
            this.a = (MapConstraint) Preconditions.checkNotNull(mapConstraint);
        }

        public Map<K, Collection<V>> asMap() {
            Map<K, Collection<V>> map = this.d;
            if (map != null) {
                return map;
            }
            final Map<K, Collection<V>> asMap = this.b.asMap();
            AnonymousClass1AsMap r1 = new ForwardingMap<K, Collection<V>>() {
                Set<Map.Entry<K, Collection<V>>> a;
                Collection<Collection<V>> b;

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Map<K, Collection<V>> delegate() {
                    return asMap;
                }

                public boolean containsValue(Object obj) {
                    return values().contains(obj);
                }

                public Set<Map.Entry<K, Collection<V>>> entrySet() {
                    Set<Map.Entry<K, Collection<V>>> set = this.a;
                    if (set != null) {
                        return set;
                    }
                    Set<Map.Entry<K, Collection<V>>> b2 = MapConstraints.constrainedAsMapEntries(asMap.entrySet(), ConstrainedMultimap.this.a);
                    this.a = b2;
                    return b2;
                }

                public Collection<V> get(Object obj) {
                    try {
                        Collection<V> collection = ConstrainedMultimap.this.get(obj);
                        if (collection.isEmpty()) {
                            return null;
                        }
                        return collection;
                    } catch (ClassCastException unused) {
                        return null;
                    }
                }

                public Collection<Collection<V>> values() {
                    Collection<Collection<V>> collection = this.b;
                    if (collection != null) {
                        return collection;
                    }
                    ConstrainedAsMapValues constrainedAsMapValues = new ConstrainedAsMapValues(delegate().values(), entrySet());
                    this.b = constrainedAsMapValues;
                    return constrainedAsMapValues;
                }
            };
            this.d = r1;
            return r1;
        }

        /* access modifiers changed from: protected */
        public Multimap<K, V> delegate() {
            return this.b;
        }

        public Collection<Map.Entry<K, V>> entries() {
            Collection<Map.Entry<K, V>> collection = this.c;
            if (collection != null) {
                return collection;
            }
            Collection<Map.Entry<K, V>> a2 = MapConstraints.constrainedEntries(this.b.entries(), this.a);
            this.c = a2;
            return a2;
        }

        public Collection<V> get(final K k) {
            return Constraints.a(this.b.get(k), new Constraint<V>() {
                public V checkElement(V v) {
                    ConstrainedMultimap.this.a.checkKeyValue(k, v);
                    return v;
                }
            });
        }

        public boolean put(K k, V v) {
            this.a.checkKeyValue(k, v);
            return this.b.put(k, v);
        }

        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            boolean z = false;
            for (Map.Entry next : multimap.entries()) {
                z |= put(next.getKey(), next.getValue());
            }
            return z;
        }

        public boolean putAll(K k, Iterable<? extends V> iterable) {
            return this.b.putAll(k, MapConstraints.checkValues(k, iterable, this.a));
        }

        public Collection<V> replaceValues(K k, Iterable<? extends V> iterable) {
            return this.b.replaceValues(k, MapConstraints.checkValues(k, iterable, this.a));
        }
    }

    private MapConstraints() {
    }

    /* access modifiers changed from: private */
    public static <K, V> Map<K, V> checkMap(Map<? extends K, ? extends V> map, MapConstraint<? super K, ? super V> mapConstraint) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            mapConstraint.checkKeyValue(entry.getKey(), entry.getValue());
        }
        return linkedHashMap;
    }

    /* access modifiers changed from: private */
    public static <K, V> Collection<V> checkValues(K k, Iterable<? extends V> iterable, MapConstraint<? super K, ? super V> mapConstraint) {
        ArrayList<E> newArrayList = Lists.newArrayList(iterable);
        for (E checkKeyValue : newArrayList) {
            mapConstraint.checkKeyValue(k, checkKeyValue);
        }
        return newArrayList;
    }

    /* access modifiers changed from: private */
    public static <K, V> Set<Map.Entry<K, Collection<V>>> constrainedAsMapEntries(Set<Map.Entry<K, Collection<V>>> set, MapConstraint<? super K, ? super V> mapConstraint) {
        return new ConstrainedAsMapEntries(set, mapConstraint);
    }

    /* access modifiers changed from: private */
    public static <K, V> Map.Entry<K, Collection<V>> constrainedAsMapEntry(final Map.Entry<K, Collection<V>> entry, final MapConstraint<? super K, ? super V> mapConstraint) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkNotNull(mapConstraint);
        return new ForwardingMapEntry<K, Collection<V>>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Map.Entry<K, Collection<V>> delegate() {
                return entry;
            }

            public Collection<V> getValue() {
                return Constraints.a((Collection) entry.getValue(), new Constraint<V>() {
                    public V checkElement(V v) {
                        mapConstraint.checkKeyValue(AnonymousClass2.this.getKey(), v);
                        return v;
                    }
                });
            }
        };
    }

    /* access modifiers changed from: private */
    public static <K, V> Collection<Map.Entry<K, V>> constrainedEntries(Collection<Map.Entry<K, V>> collection, MapConstraint<? super K, ? super V> mapConstraint) {
        return collection instanceof Set ? constrainedEntrySet((Set) collection, mapConstraint) : new ConstrainedEntries(collection, mapConstraint);
    }

    /* access modifiers changed from: private */
    public static <K, V> Map.Entry<K, V> constrainedEntry(final Map.Entry<K, V> entry, final MapConstraint<? super K, ? super V> mapConstraint) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkNotNull(mapConstraint);
        return new ForwardingMapEntry<K, V>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Map.Entry<K, V> delegate() {
                return entry;
            }

            public V setValue(V v) {
                mapConstraint.checkKeyValue(getKey(), v);
                return entry.setValue(v);
            }
        };
    }

    /* access modifiers changed from: private */
    public static <K, V> Set<Map.Entry<K, V>> constrainedEntrySet(Set<Map.Entry<K, V>> set, MapConstraint<? super K, ? super V> mapConstraint) {
        return new ConstrainedEntrySet(set, mapConstraint);
    }

    public static <K, V> ListMultimap<K, V> constrainedListMultimap(ListMultimap<K, V> listMultimap, MapConstraint<? super K, ? super V> mapConstraint) {
        return new ConstrainedListMultimap(listMultimap, mapConstraint);
    }

    public static <K, V> Map<K, V> constrainedMap(Map<K, V> map, MapConstraint<? super K, ? super V> mapConstraint) {
        return new ConstrainedMap(map, mapConstraint);
    }
}

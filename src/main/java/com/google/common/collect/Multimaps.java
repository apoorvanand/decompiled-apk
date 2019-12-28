package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.Weak;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
public final class Multimaps {

    static final class AsMap<K, V> extends Maps.ViewCachingAbstractMap<K, Collection<V>> {
        /* access modifiers changed from: private */
        @Weak
        public final Multimap<K, V> multimap;

        class EntrySet extends Maps.EntrySet<K, Collection<V>> {
            EntrySet() {
            }

            /* access modifiers changed from: package-private */
            public Map<K, Collection<V>> a() {
                return AsMap.this;
            }

            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                return Maps.a(AsMap.this.multimap.keySet(), new Function<K, Collection<V>>() {
                    public Collection<V> apply(K k) {
                        return AsMap.this.multimap.get(k);
                    }
                });
            }

            public boolean remove(Object obj) {
                if (!contains(obj)) {
                    return false;
                }
                AsMap.this.a(((Map.Entry) obj).getKey());
                return true;
            }
        }

        AsMap(Multimap<K, V> multimap2) {
            this.multimap = (Multimap) Preconditions.checkNotNull(multimap2);
        }

        /* access modifiers changed from: package-private */
        public void a(Object obj) {
            this.multimap.keySet().remove(obj);
        }

        public void clear() {
            this.multimap.clear();
        }

        public boolean containsKey(Object obj) {
            return this.multimap.containsKey(obj);
        }

        /* access modifiers changed from: protected */
        public Set<Map.Entry<K, Collection<V>>> createEntrySet() {
            return new EntrySet();
        }

        public Collection<V> get(Object obj) {
            if (containsKey(obj)) {
                return this.multimap.get(obj);
            }
            return null;
        }

        public boolean isEmpty() {
            return this.multimap.isEmpty();
        }

        public Set<K> keySet() {
            return this.multimap.keySet();
        }

        public Collection<V> remove(Object obj) {
            if (containsKey(obj)) {
                return this.multimap.removeAll(obj);
            }
            return null;
        }

        public int size() {
            return this.multimap.keySet().size();
        }
    }

    private static class CustomListMultimap<K, V> extends AbstractListMultimap<K, V> {
        @GwtIncompatible
        private static final long serialVersionUID = 0;
        transient Supplier<? extends List<V>> a;

        CustomListMultimap(Map<K, Collection<V>> map, Supplier<? extends List<V>> supplier) {
            super(map);
            this.a = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @GwtIncompatible
        private void readObject(ObjectInputStream objectInputStream) {
            objectInputStream.defaultReadObject();
            this.a = (Supplier) objectInputStream.readObject();
            a((Map) objectInputStream.readObject());
        }

        @GwtIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.a);
            objectOutputStream.writeObject(e());
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public List<V> c() {
            return (List) this.a.get();
        }
    }

    private static class CustomMultimap<K, V> extends AbstractMapBasedMultimap<K, V> {
        @GwtIncompatible
        private static final long serialVersionUID = 0;
        transient Supplier<? extends Collection<V>> a;

        CustomMultimap(Map<K, Collection<V>> map, Supplier<? extends Collection<V>> supplier) {
            super(map);
            this.a = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @GwtIncompatible
        private void readObject(ObjectInputStream objectInputStream) {
            objectInputStream.defaultReadObject();
            this.a = (Supplier) objectInputStream.readObject();
            a((Map) objectInputStream.readObject());
        }

        @GwtIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.a);
            objectOutputStream.writeObject(e());
        }

        /* access modifiers changed from: protected */
        public Collection<V> c() {
            return (Collection) this.a.get();
        }
    }

    private static class CustomSetMultimap<K, V> extends AbstractSetMultimap<K, V> {
        @GwtIncompatible
        private static final long serialVersionUID = 0;
        transient Supplier<? extends Set<V>> a;

        CustomSetMultimap(Map<K, Collection<V>> map, Supplier<? extends Set<V>> supplier) {
            super(map);
            this.a = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @GwtIncompatible
        private void readObject(ObjectInputStream objectInputStream) {
            objectInputStream.defaultReadObject();
            this.a = (Supplier) objectInputStream.readObject();
            a((Map) objectInputStream.readObject());
        }

        @GwtIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.a);
            objectOutputStream.writeObject(e());
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Set<V> c() {
            return (Set) this.a.get();
        }
    }

    private static class CustomSortedSetMultimap<K, V> extends AbstractSortedSetMultimap<K, V> {
        @GwtIncompatible
        private static final long serialVersionUID = 0;
        transient Supplier<? extends SortedSet<V>> a;
        transient Comparator<? super V> b;

        CustomSortedSetMultimap(Map<K, Collection<V>> map, Supplier<? extends SortedSet<V>> supplier) {
            super(map);
            this.a = (Supplier) Preconditions.checkNotNull(supplier);
            this.b = ((SortedSet) supplier.get()).comparator();
        }

        @GwtIncompatible
        private void readObject(ObjectInputStream objectInputStream) {
            objectInputStream.defaultReadObject();
            this.a = (Supplier) objectInputStream.readObject();
            this.b = ((SortedSet) this.a.get()).comparator();
            a((Map) objectInputStream.readObject());
        }

        @GwtIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.a);
            objectOutputStream.writeObject(e());
        }

        /* access modifiers changed from: protected */
        /* renamed from: n */
        public SortedSet<V> c() {
            return (SortedSet) this.a.get();
        }

        public Comparator<? super V> valueComparator() {
            return this.b;
        }
    }

    static abstract class Entries<K, V> extends AbstractCollection<Map.Entry<K, V>> {
        Entries() {
        }

        /* access modifiers changed from: package-private */
        public abstract Multimap<K, V> a();

        public void clear() {
            a().clear();
        }

        public boolean contains(@Nullable Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return a().containsEntry(entry.getKey(), entry.getValue());
        }

        public boolean remove(@Nullable Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            return a().remove(entry.getKey(), entry.getValue());
        }

        public int size() {
            return a().size();
        }
    }

    static class Keys<K, V> extends AbstractMultiset<K> {
        @Weak
        final Multimap<K, V> b;

        class KeysEntrySet extends Multisets.EntrySet<K> {
            KeysEntrySet() {
            }

            /* access modifiers changed from: package-private */
            public Multiset<K> a() {
                return Keys.this;
            }

            public boolean contains(@Nullable Object obj) {
                if (!(obj instanceof Multiset.Entry)) {
                    return false;
                }
                Multiset.Entry entry = (Multiset.Entry) obj;
                Collection collection = Keys.this.b.asMap().get(entry.getElement());
                return collection != null && collection.size() == entry.getCount();
            }

            public boolean isEmpty() {
                return Keys.this.b.isEmpty();
            }

            public Iterator<Multiset.Entry<K>> iterator() {
                return Keys.this.a();
            }

            public boolean remove(@Nullable Object obj) {
                if (!(obj instanceof Multiset.Entry)) {
                    return false;
                }
                Multiset.Entry entry = (Multiset.Entry) obj;
                Collection collection = Keys.this.b.asMap().get(entry.getElement());
                if (collection == null || collection.size() != entry.getCount()) {
                    return false;
                }
                collection.clear();
                return true;
            }

            public int size() {
                return Keys.this.b();
            }
        }

        Keys(Multimap<K, V> multimap) {
            this.b = multimap;
        }

        /* access modifiers changed from: package-private */
        public Iterator<Multiset.Entry<K>> a() {
            return new TransformedIterator<Map.Entry<K, Collection<V>>, Multiset.Entry<K>>(this.b.asMap().entrySet().iterator()) {
                /* access modifiers changed from: package-private */
                public Multiset.Entry<K> a(final Map.Entry<K, Collection<V>> entry) {
                    return new Multisets.AbstractEntry<K>() {
                        public int getCount() {
                            return ((Collection) entry.getValue()).size();
                        }

                        public K getElement() {
                            return entry.getKey();
                        }
                    };
                }
            };
        }

        /* access modifiers changed from: package-private */
        public int b() {
            return this.b.asMap().size();
        }

        public void clear() {
            this.b.clear();
        }

        public boolean contains(@Nullable Object obj) {
            return this.b.containsKey(obj);
        }

        public int count(@Nullable Object obj) {
            Collection collection = (Collection) Maps.a(this.b.asMap(), obj);
            if (collection == null) {
                return 0;
            }
            return collection.size();
        }

        /* access modifiers changed from: package-private */
        public Set<Multiset.Entry<K>> createEntrySet() {
            return new KeysEntrySet();
        }

        public Set<K> elementSet() {
            return this.b.keySet();
        }

        public Iterator<K> iterator() {
            return Maps.a(this.b.entries().iterator());
        }

        public int remove(@Nullable Object obj, int i) {
            CollectPreconditions.a(i, "occurrences");
            if (i == 0) {
                return count(obj);
            }
            Collection collection = (Collection) Maps.a(this.b.asMap(), obj);
            if (collection == null) {
                return 0;
            }
            int size = collection.size();
            if (i >= size) {
                collection.clear();
            } else {
                Iterator it = collection.iterator();
                for (int i2 = 0; i2 < i; i2++) {
                    it.next();
                    it.remove();
                }
            }
            return size;
        }
    }

    private static class MapMultimap<K, V> extends AbstractMultimap<K, V> implements SetMultimap<K, V>, Serializable {
        private static final long serialVersionUID = 7845222491160860175L;
        final Map<K, V> a;

        MapMultimap(Map<K, V> map) {
            this.a = (Map) Preconditions.checkNotNull(map);
        }

        public void clear() {
            this.a.clear();
        }

        public boolean containsEntry(Object obj, Object obj2) {
            return this.a.entrySet().contains(Maps.immutableEntry(obj, obj2));
        }

        public boolean containsKey(Object obj) {
            return this.a.containsKey(obj);
        }

        public boolean containsValue(Object obj) {
            return this.a.containsValue(obj);
        }

        public Set<Map.Entry<K, V>> entries() {
            return this.a.entrySet();
        }

        public Set<V> get(final K k) {
            return new Sets.ImprovedAbstractSet<V>() {
                public Iterator<V> iterator() {
                    return new Iterator<V>() {
                        int a;

                        public boolean hasNext() {
                            return this.a == 0 && MapMultimap.this.a.containsKey(k);
                        }

                        public V next() {
                            if (hasNext()) {
                                this.a++;
                                return MapMultimap.this.a.get(k);
                            }
                            throw new NoSuchElementException();
                        }

                        public void remove() {
                            boolean z = true;
                            if (this.a != 1) {
                                z = false;
                            }
                            CollectPreconditions.a(z);
                            this.a = -1;
                            MapMultimap.this.a.remove(k);
                        }
                    };
                }

                public int size() {
                    return MapMultimap.this.a.containsKey(k) ? 1 : 0;
                }
            };
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<K, V>> h() {
            return this.a.entrySet().iterator();
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        /* access modifiers changed from: package-private */
        public Map<K, Collection<V>> i() {
            return new AsMap(this);
        }

        public Set<K> keySet() {
            return this.a.keySet();
        }

        public boolean put(K k, V v) {
            throw new UnsupportedOperationException();
        }

        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            throw new UnsupportedOperationException();
        }

        public boolean putAll(K k, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        public boolean remove(Object obj, Object obj2) {
            return this.a.entrySet().remove(Maps.immutableEntry(obj, obj2));
        }

        public Set<V> removeAll(Object obj) {
            HashSet hashSet = new HashSet(2);
            if (!this.a.containsKey(obj)) {
                return hashSet;
            }
            hashSet.add(this.a.remove(obj));
            return hashSet;
        }

        public Set<V> replaceValues(K k, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        public int size() {
            return this.a.size();
        }

        public Collection<V> values() {
            return this.a.values();
        }
    }

    private static final class TransformedEntriesListMultimap<K, V1, V2> extends TransformedEntriesMultimap<K, V1, V2> implements ListMultimap<K, V2> {
        TransformedEntriesListMultimap(ListMultimap<K, V1> listMultimap, Maps.EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
            super(listMultimap, entryTransformer);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public List<V2> b(K k, Collection<V1> collection) {
            return Lists.transform((List) collection, Maps.a(this.b, k));
        }

        public List<V2> get(K k) {
            return b(k, this.a.get(k));
        }

        public List<V2> removeAll(Object obj) {
            return b(obj, this.a.removeAll(obj));
        }

        public List<V2> replaceValues(K k, Iterable<? extends V2> iterable) {
            throw new UnsupportedOperationException();
        }
    }

    private static class TransformedEntriesMultimap<K, V1, V2> extends AbstractMultimap<K, V2> {
        final Multimap<K, V1> a;
        final Maps.EntryTransformer<? super K, ? super V1, V2> b;

        TransformedEntriesMultimap(Multimap<K, V1> multimap, Maps.EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
            this.a = (Multimap) Preconditions.checkNotNull(multimap);
            this.b = (Maps.EntryTransformer) Preconditions.checkNotNull(entryTransformer);
        }

        /* access modifiers changed from: package-private */
        public Collection<V2> b(K k, Collection<V1> collection) {
            Function<? super V1, V2> a2 = Maps.a(this.b, k);
            return collection instanceof List ? Lists.transform((List) collection, a2) : Collections2.transform(collection, a2);
        }

        public void clear() {
            this.a.clear();
        }

        public boolean containsKey(Object obj) {
            return this.a.containsKey(obj);
        }

        public Collection<V2> get(K k) {
            return b(k, this.a.get(k));
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<K, V2>> h() {
            return Iterators.transform(this.a.entries().iterator(), Maps.b(this.b));
        }

        /* access modifiers changed from: package-private */
        public Map<K, Collection<V2>> i() {
            return Maps.transformEntries(this.a.asMap(), new Maps.EntryTransformer<K, Collection<V1>, Collection<V2>>() {
                public Collection<V2> transformEntry(K k, Collection<V1> collection) {
                    return TransformedEntriesMultimap.this.b(k, collection);
                }
            });
        }

        public boolean isEmpty() {
            return this.a.isEmpty();
        }

        public Set<K> keySet() {
            return this.a.keySet();
        }

        public Multiset<K> keys() {
            return this.a.keys();
        }

        /* access modifiers changed from: package-private */
        public Collection<V2> l() {
            return Collections2.transform(this.a.entries(), Maps.a(this.b));
        }

        public boolean put(K k, V2 v2) {
            throw new UnsupportedOperationException();
        }

        public boolean putAll(Multimap<? extends K, ? extends V2> multimap) {
            throw new UnsupportedOperationException();
        }

        public boolean putAll(K k, Iterable<? extends V2> iterable) {
            throw new UnsupportedOperationException();
        }

        public boolean remove(Object obj, Object obj2) {
            return get(obj).remove(obj2);
        }

        public Collection<V2> removeAll(Object obj) {
            return b(obj, this.a.removeAll(obj));
        }

        public Collection<V2> replaceValues(K k, Iterable<? extends V2> iterable) {
            throw new UnsupportedOperationException();
        }

        public int size() {
            return this.a.size();
        }
    }

    private static class UnmodifiableListMultimap<K, V> extends UnmodifiableMultimap<K, V> implements ListMultimap<K, V> {
        private static final long serialVersionUID = 0;

        UnmodifiableListMultimap(ListMultimap<K, V> listMultimap) {
            super(listMultimap);
        }

        public ListMultimap<K, V> delegate() {
            return (ListMultimap) super.delegate();
        }

        public List<V> get(K k) {
            return Collections.unmodifiableList(delegate().get(k));
        }

        public List<V> removeAll(Object obj) {
            throw new UnsupportedOperationException();
        }

        public List<V> replaceValues(K k, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }
    }

    private static class UnmodifiableMultimap<K, V> extends ForwardingMultimap<K, V> implements Serializable {
        private static final long serialVersionUID = 0;
        final Multimap<K, V> a;
        transient Collection<Map.Entry<K, V>> b;
        transient Multiset<K> c;
        transient Set<K> d;
        transient Collection<V> e;
        transient Map<K, Collection<V>> f;

        UnmodifiableMultimap(Multimap<K, V> multimap) {
            this.a = (Multimap) Preconditions.checkNotNull(multimap);
        }

        public Map<K, Collection<V>> asMap() {
            Map<K, Collection<V>> map = this.f;
            if (map != null) {
                return map;
            }
            Map<K, Collection<V>> unmodifiableMap = Collections.unmodifiableMap(Maps.transformValues(this.a.asMap(), new Function<Collection<V>, Collection<V>>() {
                public Collection<V> apply(Collection<V> collection) {
                    return Multimaps.unmodifiableValueCollection(collection);
                }
            }));
            this.f = unmodifiableMap;
            return unmodifiableMap;
        }

        public void clear() {
            throw new UnsupportedOperationException();
        }

        /* access modifiers changed from: protected */
        public Multimap<K, V> delegate() {
            return this.a;
        }

        public Collection<Map.Entry<K, V>> entries() {
            Collection<Map.Entry<K, V>> collection = this.b;
            if (collection != null) {
                return collection;
            }
            Collection<Map.Entry<K, V>> b2 = Multimaps.unmodifiableEntries(this.a.entries());
            this.b = b2;
            return b2;
        }

        public Collection<V> get(K k) {
            return Multimaps.unmodifiableValueCollection(this.a.get(k));
        }

        public Set<K> keySet() {
            Set<K> set = this.d;
            if (set != null) {
                return set;
            }
            Set<K> unmodifiableSet = Collections.unmodifiableSet(this.a.keySet());
            this.d = unmodifiableSet;
            return unmodifiableSet;
        }

        public Multiset<K> keys() {
            Multiset<K> multiset = this.c;
            if (multiset != null) {
                return multiset;
            }
            Multiset<K> unmodifiableMultiset = Multisets.unmodifiableMultiset(this.a.keys());
            this.c = unmodifiableMultiset;
            return unmodifiableMultiset;
        }

        public boolean put(K k, V v) {
            throw new UnsupportedOperationException();
        }

        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            throw new UnsupportedOperationException();
        }

        public boolean putAll(K k, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        public boolean remove(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        public Collection<V> removeAll(Object obj) {
            throw new UnsupportedOperationException();
        }

        public Collection<V> replaceValues(K k, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        public Collection<V> values() {
            Collection<V> collection = this.e;
            if (collection != null) {
                return collection;
            }
            Collection<V> unmodifiableCollection = Collections.unmodifiableCollection(this.a.values());
            this.e = unmodifiableCollection;
            return unmodifiableCollection;
        }
    }

    private static class UnmodifiableSetMultimap<K, V> extends UnmodifiableMultimap<K, V> implements SetMultimap<K, V> {
        private static final long serialVersionUID = 0;

        UnmodifiableSetMultimap(SetMultimap<K, V> setMultimap) {
            super(setMultimap);
        }

        public SetMultimap<K, V> delegate() {
            return (SetMultimap) super.delegate();
        }

        public Set<Map.Entry<K, V>> entries() {
            return Maps.a(delegate().entries());
        }

        public Set<V> get(K k) {
            return Collections.unmodifiableSet(delegate().get(k));
        }

        public Set<V> removeAll(Object obj) {
            throw new UnsupportedOperationException();
        }

        public Set<V> replaceValues(K k, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }
    }

    private static class UnmodifiableSortedSetMultimap<K, V> extends UnmodifiableSetMultimap<K, V> implements SortedSetMultimap<K, V> {
        private static final long serialVersionUID = 0;

        UnmodifiableSortedSetMultimap(SortedSetMultimap<K, V> sortedSetMultimap) {
            super(sortedSetMultimap);
        }

        public SortedSetMultimap<K, V> delegate() {
            return (SortedSetMultimap) super.delegate();
        }

        public SortedSet<V> get(K k) {
            return Collections.unmodifiableSortedSet(delegate().get(k));
        }

        public SortedSet<V> removeAll(Object obj) {
            throw new UnsupportedOperationException();
        }

        public SortedSet<V> replaceValues(K k, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        public Comparator<? super V> valueComparator() {
            return delegate().valueComparator();
        }
    }

    private Multimaps() {
    }

    static boolean a(Multimap<?, ?> multimap, @Nullable Object obj) {
        if (obj == multimap) {
            return true;
        }
        if (obj instanceof Multimap) {
            return multimap.asMap().equals(((Multimap) obj).asMap());
        }
        return false;
    }

    @Beta
    public static <K, V> Map<K, List<V>> asMap(ListMultimap<K, V> listMultimap) {
        return listMultimap.asMap();
    }

    @Beta
    public static <K, V> Map<K, Collection<V>> asMap(Multimap<K, V> multimap) {
        return multimap.asMap();
    }

    @Beta
    public static <K, V> Map<K, Set<V>> asMap(SetMultimap<K, V> setMultimap) {
        return setMultimap.asMap();
    }

    @Beta
    public static <K, V> Map<K, SortedSet<V>> asMap(SortedSetMultimap<K, V> sortedSetMultimap) {
        return sortedSetMultimap.asMap();
    }

    public static <K, V> Multimap<K, V> filterEntries(Multimap<K, V> multimap, Predicate<? super Map.Entry<K, V>> predicate) {
        Preconditions.checkNotNull(predicate);
        return multimap instanceof SetMultimap ? filterEntries((SetMultimap) multimap, predicate) : multimap instanceof FilteredMultimap ? filterFiltered((FilteredMultimap) multimap, predicate) : new FilteredEntryMultimap((Multimap) Preconditions.checkNotNull(multimap), predicate);
    }

    public static <K, V> SetMultimap<K, V> filterEntries(SetMultimap<K, V> setMultimap, Predicate<? super Map.Entry<K, V>> predicate) {
        Preconditions.checkNotNull(predicate);
        return setMultimap instanceof FilteredSetMultimap ? filterFiltered((FilteredSetMultimap) setMultimap, predicate) : new FilteredEntrySetMultimap((SetMultimap) Preconditions.checkNotNull(setMultimap), predicate);
    }

    private static <K, V> Multimap<K, V> filterFiltered(FilteredMultimap<K, V> filteredMultimap, Predicate<? super Map.Entry<K, V>> predicate) {
        return new FilteredEntryMultimap(filteredMultimap.unfiltered(), Predicates.and(filteredMultimap.entryPredicate(), predicate));
    }

    private static <K, V> SetMultimap<K, V> filterFiltered(FilteredSetMultimap<K, V> filteredSetMultimap, Predicate<? super Map.Entry<K, V>> predicate) {
        return new FilteredEntrySetMultimap(filteredSetMultimap.unfiltered(), Predicates.and(filteredSetMultimap.entryPredicate(), predicate));
    }

    public static <K, V> ListMultimap<K, V> filterKeys(ListMultimap<K, V> listMultimap, Predicate<? super K> predicate) {
        if (!(listMultimap instanceof FilteredKeyListMultimap)) {
            return new FilteredKeyListMultimap(listMultimap, predicate);
        }
        FilteredKeyListMultimap filteredKeyListMultimap = (FilteredKeyListMultimap) listMultimap;
        return new FilteredKeyListMultimap(filteredKeyListMultimap.unfiltered(), Predicates.and(filteredKeyListMultimap.b, predicate));
    }

    public static <K, V> Multimap<K, V> filterKeys(Multimap<K, V> multimap, Predicate<? super K> predicate) {
        if (multimap instanceof SetMultimap) {
            return filterKeys((SetMultimap) multimap, predicate);
        }
        if (multimap instanceof ListMultimap) {
            return filterKeys((ListMultimap) multimap, predicate);
        }
        if (!(multimap instanceof FilteredKeyMultimap)) {
            return multimap instanceof FilteredMultimap ? filterFiltered((FilteredMultimap) multimap, Maps.a(predicate)) : new FilteredKeyMultimap(multimap, predicate);
        }
        FilteredKeyMultimap filteredKeyMultimap = (FilteredKeyMultimap) multimap;
        return new FilteredKeyMultimap(filteredKeyMultimap.a, Predicates.and(filteredKeyMultimap.b, predicate));
    }

    public static <K, V> SetMultimap<K, V> filterKeys(SetMultimap<K, V> setMultimap, Predicate<? super K> predicate) {
        if (!(setMultimap instanceof FilteredKeySetMultimap)) {
            return setMultimap instanceof FilteredSetMultimap ? filterFiltered((FilteredSetMultimap) setMultimap, Maps.a(predicate)) : new FilteredKeySetMultimap(setMultimap, predicate);
        }
        FilteredKeySetMultimap filteredKeySetMultimap = (FilteredKeySetMultimap) setMultimap;
        return new FilteredKeySetMultimap(filteredKeySetMultimap.unfiltered(), Predicates.and(filteredKeySetMultimap.b, predicate));
    }

    public static <K, V> Multimap<K, V> filterValues(Multimap<K, V> multimap, Predicate<? super V> predicate) {
        return filterEntries(multimap, Maps.b(predicate));
    }

    public static <K, V> SetMultimap<K, V> filterValues(SetMultimap<K, V> setMultimap, Predicate<? super V> predicate) {
        return filterEntries(setMultimap, Maps.b(predicate));
    }

    public static <K, V> SetMultimap<K, V> forMap(Map<K, V> map) {
        return new MapMultimap(map);
    }

    public static <K, V> ImmutableListMultimap<K, V> index(Iterable<V> iterable, Function<? super V, K> function) {
        return index(iterable.iterator(), function);
    }

    public static <K, V> ImmutableListMultimap<K, V> index(Iterator<V> it, Function<? super V, K> function) {
        Preconditions.checkNotNull(function);
        ImmutableListMultimap.Builder builder = ImmutableListMultimap.builder();
        while (it.hasNext()) {
            V next = it.next();
            Preconditions.checkNotNull(next, it);
            builder.put(function.apply(next), next);
        }
        return builder.build();
    }

    @CanIgnoreReturnValue
    public static <K, V, M extends Multimap<K, V>> M invertFrom(Multimap<? extends V, ? extends K> multimap, M m) {
        Preconditions.checkNotNull(m);
        for (Map.Entry next : multimap.entries()) {
            m.put(next.getValue(), next.getKey());
        }
        return m;
    }

    public static <K, V> ListMultimap<K, V> newListMultimap(Map<K, Collection<V>> map, Supplier<? extends List<V>> supplier) {
        return new CustomListMultimap(map, supplier);
    }

    public static <K, V> Multimap<K, V> newMultimap(Map<K, Collection<V>> map, Supplier<? extends Collection<V>> supplier) {
        return new CustomMultimap(map, supplier);
    }

    public static <K, V> SetMultimap<K, V> newSetMultimap(Map<K, Collection<V>> map, Supplier<? extends Set<V>> supplier) {
        return new CustomSetMultimap(map, supplier);
    }

    public static <K, V> SortedSetMultimap<K, V> newSortedSetMultimap(Map<K, Collection<V>> map, Supplier<? extends SortedSet<V>> supplier) {
        return new CustomSortedSetMultimap(map, supplier);
    }

    public static <K, V> ListMultimap<K, V> synchronizedListMultimap(ListMultimap<K, V> listMultimap) {
        return Synchronized.a(listMultimap, (Object) null);
    }

    public static <K, V> Multimap<K, V> synchronizedMultimap(Multimap<K, V> multimap) {
        return Synchronized.a(multimap, (Object) null);
    }

    public static <K, V> SetMultimap<K, V> synchronizedSetMultimap(SetMultimap<K, V> setMultimap) {
        return Synchronized.a(setMultimap, (Object) null);
    }

    public static <K, V> SortedSetMultimap<K, V> synchronizedSortedSetMultimap(SortedSetMultimap<K, V> sortedSetMultimap) {
        return Synchronized.a(sortedSetMultimap, (Object) null);
    }

    public static <K, V1, V2> ListMultimap<K, V2> transformEntries(ListMultimap<K, V1> listMultimap, Maps.EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        return new TransformedEntriesListMultimap(listMultimap, entryTransformer);
    }

    public static <K, V1, V2> Multimap<K, V2> transformEntries(Multimap<K, V1> multimap, Maps.EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        return new TransformedEntriesMultimap(multimap, entryTransformer);
    }

    public static <K, V1, V2> ListMultimap<K, V2> transformValues(ListMultimap<K, V1> listMultimap, Function<? super V1, V2> function) {
        Preconditions.checkNotNull(function);
        return transformEntries(listMultimap, Maps.a(function));
    }

    public static <K, V1, V2> Multimap<K, V2> transformValues(Multimap<K, V1> multimap, Function<? super V1, V2> function) {
        Preconditions.checkNotNull(function);
        return transformEntries(multimap, Maps.a(function));
    }

    /* access modifiers changed from: private */
    public static <K, V> Collection<Map.Entry<K, V>> unmodifiableEntries(Collection<Map.Entry<K, V>> collection) {
        return collection instanceof Set ? Maps.a((Set) collection) : new Maps.UnmodifiableEntries(Collections.unmodifiableCollection(collection));
    }

    @Deprecated
    public static <K, V> ListMultimap<K, V> unmodifiableListMultimap(ImmutableListMultimap<K, V> immutableListMultimap) {
        return (ListMultimap) Preconditions.checkNotNull(immutableListMultimap);
    }

    public static <K, V> ListMultimap<K, V> unmodifiableListMultimap(ListMultimap<K, V> listMultimap) {
        return ((listMultimap instanceof UnmodifiableListMultimap) || (listMultimap instanceof ImmutableListMultimap)) ? listMultimap : new UnmodifiableListMultimap(listMultimap);
    }

    @Deprecated
    public static <K, V> Multimap<K, V> unmodifiableMultimap(ImmutableMultimap<K, V> immutableMultimap) {
        return (Multimap) Preconditions.checkNotNull(immutableMultimap);
    }

    public static <K, V> Multimap<K, V> unmodifiableMultimap(Multimap<K, V> multimap) {
        return ((multimap instanceof UnmodifiableMultimap) || (multimap instanceof ImmutableMultimap)) ? multimap : new UnmodifiableMultimap(multimap);
    }

    @Deprecated
    public static <K, V> SetMultimap<K, V> unmodifiableSetMultimap(ImmutableSetMultimap<K, V> immutableSetMultimap) {
        return (SetMultimap) Preconditions.checkNotNull(immutableSetMultimap);
    }

    public static <K, V> SetMultimap<K, V> unmodifiableSetMultimap(SetMultimap<K, V> setMultimap) {
        return ((setMultimap instanceof UnmodifiableSetMultimap) || (setMultimap instanceof ImmutableSetMultimap)) ? setMultimap : new UnmodifiableSetMultimap(setMultimap);
    }

    public static <K, V> SortedSetMultimap<K, V> unmodifiableSortedSetMultimap(SortedSetMultimap<K, V> sortedSetMultimap) {
        return sortedSetMultimap instanceof UnmodifiableSortedSetMultimap ? sortedSetMultimap : new UnmodifiableSortedSetMultimap(sortedSetMultimap);
    }

    /* access modifiers changed from: private */
    public static <V> Collection<V> unmodifiableValueCollection(Collection<V> collection) {
        return collection instanceof SortedSet ? Collections.unmodifiableSortedSet((SortedSet) collection) : collection instanceof Set ? Collections.unmodifiableSet((Set) collection) : collection instanceof List ? Collections.unmodifiableList((List) collection) : Collections.unmodifiableCollection(collection);
    }
}

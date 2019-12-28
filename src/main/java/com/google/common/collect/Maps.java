package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Converter;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.RetainedWith;
import com.google.j2objc.annotations.Weak;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
public final class Maps {
    static final Joiner.MapJoiner a = Collections2.a.withKeyValueSeparator("=");

    private static abstract class AbstractFilteredMap<K, V> extends ViewCachingAbstractMap<K, V> {
        final Map<K, V> a;
        final Predicate<? super Map.Entry<K, V>> b;

        AbstractFilteredMap(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate) {
            this.a = map;
            this.b = predicate;
        }

        /* access modifiers changed from: package-private */
        public Collection<V> a() {
            return new FilteredMapValues(this, this.a, this.b);
        }

        /* access modifiers changed from: package-private */
        public boolean a(@Nullable Object obj, @Nullable V v) {
            return this.b.apply(Maps.immutableEntry(obj, v));
        }

        public boolean containsKey(Object obj) {
            return this.a.containsKey(obj) && a(obj, this.a.get(obj));
        }

        public V get(Object obj) {
            V v = this.a.get(obj);
            if (v == null || !a(obj, v)) {
                return null;
            }
            return v;
        }

        public boolean isEmpty() {
            return entrySet().isEmpty();
        }

        public V put(K k, V v) {
            Preconditions.checkArgument(a(k, v));
            return this.a.put(k, v);
        }

        public void putAll(Map<? extends K, ? extends V> map) {
            for (Map.Entry next : map.entrySet()) {
                Preconditions.checkArgument(a(next.getKey(), next.getValue()));
            }
            this.a.putAll(map);
        }

        public V remove(Object obj) {
            if (containsKey(obj)) {
                return this.a.remove(obj);
            }
            return null;
        }
    }

    private static class AsMapView<K, V> extends ViewCachingAbstractMap<K, V> {
        final Function<? super K, V> a;
        private final Set<K> set;

        AsMapView(Set<K> set2, Function<? super K, V> function) {
            this.set = (Set) Preconditions.checkNotNull(set2);
            this.a = (Function) Preconditions.checkNotNull(function);
        }

        /* access modifiers changed from: package-private */
        public Collection<V> a() {
            return Collections2.transform(this.set, this.a);
        }

        /* access modifiers changed from: package-private */
        public Set<K> b() {
            return this.set;
        }

        public void clear() {
            b().clear();
        }

        public boolean containsKey(@Nullable Object obj) {
            return b().contains(obj);
        }

        /* access modifiers changed from: protected */
        public Set<Map.Entry<K, V>> createEntrySet() {
            return new EntrySet<K, V>() {
                /* access modifiers changed from: package-private */
                public Map<K, V> a() {
                    return AsMapView.this;
                }

                public Iterator<Map.Entry<K, V>> iterator() {
                    return Maps.a(AsMapView.this.b(), AsMapView.this.a);
                }
            };
        }

        public Set<K> createKeySet() {
            return Maps.removeOnlySet(b());
        }

        public V get(@Nullable Object obj) {
            if (Collections2.a((Collection<?>) b(), obj)) {
                return this.a.apply(obj);
            }
            return null;
        }

        public V remove(@Nullable Object obj) {
            if (b().remove(obj)) {
                return this.a.apply(obj);
            }
            return null;
        }

        public int size() {
            return b().size();
        }
    }

    private static final class BiMapConverter<A, B> extends Converter<A, B> implements Serializable {
        private static final long serialVersionUID = 0;
        private final BiMap<A, B> bimap;

        BiMapConverter(BiMap<A, B> biMap) {
            this.bimap = (BiMap) Preconditions.checkNotNull(biMap);
        }

        private static <X, Y> Y convert(BiMap<X, Y> biMap, X x) {
            Y y = biMap.get(x);
            Preconditions.checkArgument(y != null, "No non-null mapping present for input: %s", (Object) x);
            return y;
        }

        /* access modifiers changed from: protected */
        public A a(B b) {
            return convert(this.bimap.inverse(), b);
        }

        /* access modifiers changed from: protected */
        public B b(A a) {
            return convert(this.bimap, a);
        }

        public boolean equals(@Nullable Object obj) {
            if (obj instanceof BiMapConverter) {
                return this.bimap.equals(((BiMapConverter) obj).bimap);
            }
            return false;
        }

        public int hashCode() {
            return this.bimap.hashCode();
        }

        public String toString() {
            return "Maps.asConverter(" + this.bimap + ")";
        }
    }

    @GwtIncompatible
    static abstract class DescendingMap<K, V> extends ForwardingMap<K, V> implements NavigableMap<K, V> {
        private transient Comparator<? super K> comparator;
        private transient Set<Map.Entry<K, V>> entrySet;
        private transient NavigableSet<K> navigableKeySet;

        DescendingMap() {
        }

        private static <T> Ordering<T> reverse(Comparator<T> comparator2) {
            return Ordering.from(comparator2).reverse();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public final Map<K, V> delegate() {
            return b();
        }

        /* access modifiers changed from: package-private */
        public abstract NavigableMap<K, V> b();

        /* access modifiers changed from: package-private */
        public abstract Iterator<Map.Entry<K, V>> c();

        public Map.Entry<K, V> ceilingEntry(K k) {
            return b().floorEntry(k);
        }

        public K ceilingKey(K k) {
            return b().floorKey(k);
        }

        public Comparator<? super K> comparator() {
            Comparator<? super K> comparator2 = this.comparator;
            if (comparator2 != null) {
                return comparator2;
            }
            Comparator comparator3 = b().comparator();
            if (comparator3 == null) {
                comparator3 = Ordering.natural();
            }
            Ordering reverse = reverse(comparator3);
            this.comparator = reverse;
            return reverse;
        }

        public NavigableSet<K> descendingKeySet() {
            return b().navigableKeySet();
        }

        public NavigableMap<K, V> descendingMap() {
            return b();
        }

        /* access modifiers changed from: package-private */
        public Set<Map.Entry<K, V>> e() {
            return new EntrySet<K, V>() {
                /* access modifiers changed from: package-private */
                public Map<K, V> a() {
                    return DescendingMap.this;
                }

                public Iterator<Map.Entry<K, V>> iterator() {
                    return DescendingMap.this.c();
                }
            };
        }

        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set = this.entrySet;
            if (set != null) {
                return set;
            }
            Set<Map.Entry<K, V>> e = e();
            this.entrySet = e;
            return e;
        }

        public Map.Entry<K, V> firstEntry() {
            return b().lastEntry();
        }

        public K firstKey() {
            return b().lastKey();
        }

        public Map.Entry<K, V> floorEntry(K k) {
            return b().ceilingEntry(k);
        }

        public K floorKey(K k) {
            return b().ceilingKey(k);
        }

        public NavigableMap<K, V> headMap(K k, boolean z) {
            return b().tailMap(k, z).descendingMap();
        }

        public SortedMap<K, V> headMap(K k) {
            return headMap(k, false);
        }

        public Map.Entry<K, V> higherEntry(K k) {
            return b().lowerEntry(k);
        }

        public K higherKey(K k) {
            return b().lowerKey(k);
        }

        public Set<K> keySet() {
            return navigableKeySet();
        }

        public Map.Entry<K, V> lastEntry() {
            return b().firstEntry();
        }

        public K lastKey() {
            return b().firstKey();
        }

        public Map.Entry<K, V> lowerEntry(K k) {
            return b().higherEntry(k);
        }

        public K lowerKey(K k) {
            return b().higherKey(k);
        }

        public NavigableSet<K> navigableKeySet() {
            NavigableSet<K> navigableSet = this.navigableKeySet;
            if (navigableSet != null) {
                return navigableSet;
            }
            NavigableKeySet navigableKeySet2 = new NavigableKeySet(this);
            this.navigableKeySet = navigableKeySet2;
            return navigableKeySet2;
        }

        public Map.Entry<K, V> pollFirstEntry() {
            return b().pollLastEntry();
        }

        public Map.Entry<K, V> pollLastEntry() {
            return b().pollFirstEntry();
        }

        public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
            return b().subMap(k2, z2, k, z).descendingMap();
        }

        public SortedMap<K, V> subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }

        public NavigableMap<K, V> tailMap(K k, boolean z) {
            return b().headMap(k, z).descendingMap();
        }

        public SortedMap<K, V> tailMap(K k) {
            return tailMap(k, true);
        }

        public String toString() {
            return d();
        }

        public Collection<V> values() {
            return new Values(this);
        }
    }

    private enum EntryFunction implements Function<Map.Entry<?, ?>, Object> {
        KEY {
            @Nullable
            public Object apply(Map.Entry<?, ?> entry) {
                return entry.getKey();
            }
        },
        VALUE {
            @Nullable
            public Object apply(Map.Entry<?, ?> entry) {
                return entry.getValue();
            }
        }
    }

    static abstract class EntrySet<K, V> extends Sets.ImprovedAbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        /* access modifiers changed from: package-private */
        public abstract Map<K, V> a();

        public void clear() {
            a().clear();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object a = Maps.a(a(), key);
            if (Objects.equal(a, entry.getValue())) {
                return a != null || a().containsKey(key);
            }
            return false;
        }

        public boolean isEmpty() {
            return a().isEmpty();
        }

        public boolean remove(Object obj) {
            if (contains(obj)) {
                return a().keySet().remove(((Map.Entry) obj).getKey());
            }
            return false;
        }

        public boolean removeAll(Collection<?> collection) {
            try {
                return super.removeAll((Collection) Preconditions.checkNotNull(collection));
            } catch (UnsupportedOperationException unused) {
                return Sets.a((Set<?>) this, collection.iterator());
            }
        }

        public boolean retainAll(Collection<?> collection) {
            try {
                return super.retainAll((Collection) Preconditions.checkNotNull(collection));
            } catch (UnsupportedOperationException unused) {
                HashSet newHashSetWithExpectedSize = Sets.newHashSetWithExpectedSize(collection.size());
                for (Object next : collection) {
                    if (contains(next)) {
                        newHashSetWithExpectedSize.add(((Map.Entry) next).getKey());
                    }
                }
                return a().keySet().retainAll(newHashSetWithExpectedSize);
            }
        }

        public int size() {
            return a().size();
        }
    }

    public interface EntryTransformer<K, V1, V2> {
        V2 transformEntry(@Nullable K k, @Nullable V1 v1);
    }

    static final class FilteredEntryBiMap<K, V> extends FilteredEntryMap<K, V> implements BiMap<K, V> {
        @RetainedWith
        private final BiMap<V, K> inverse;

        FilteredEntryBiMap(BiMap<K, V> biMap, Predicate<? super Map.Entry<K, V>> predicate) {
            super(biMap, predicate);
            this.inverse = new FilteredEntryBiMap(biMap.inverse(), inversePredicate(predicate), this);
        }

        private FilteredEntryBiMap(BiMap<K, V> biMap, Predicate<? super Map.Entry<K, V>> predicate, BiMap<V, K> biMap2) {
            super(biMap, predicate);
            this.inverse = biMap2;
        }

        private static <K, V> Predicate<Map.Entry<V, K>> inversePredicate(final Predicate<? super Map.Entry<K, V>> predicate) {
            return new Predicate<Map.Entry<V, K>>() {
                public boolean apply(Map.Entry<V, K> entry) {
                    return predicate.apply(Maps.immutableEntry(entry.getValue(), entry.getKey()));
                }
            };
        }

        /* access modifiers changed from: package-private */
        public BiMap<K, V> b() {
            return (BiMap) this.a;
        }

        public V forcePut(@Nullable K k, @Nullable V v) {
            Preconditions.checkArgument(a(k, v));
            return b().forcePut(k, v);
        }

        public BiMap<V, K> inverse() {
            return this.inverse;
        }

        public Set<V> values() {
            return this.inverse.keySet();
        }
    }

    static class FilteredEntryMap<K, V> extends AbstractFilteredMap<K, V> {
        final Set<Map.Entry<K, V>> c;

        private class EntrySet extends ForwardingSet<Map.Entry<K, V>> {
            private EntrySet() {
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Set<Map.Entry<K, V>> delegate() {
                return FilteredEntryMap.this.c;
            }

            public Iterator<Map.Entry<K, V>> iterator() {
                return new TransformedIterator<Map.Entry<K, V>, Map.Entry<K, V>>(FilteredEntryMap.this.c.iterator()) {
                    /* access modifiers changed from: package-private */
                    public Map.Entry<K, V> a(final Map.Entry<K, V> entry) {
                        return new ForwardingMapEntry<K, V>() {
                            /* access modifiers changed from: protected */
                            /* renamed from: a */
                            public Map.Entry<K, V> delegate() {
                                return entry;
                            }

                            public V setValue(V v) {
                                Preconditions.checkArgument(FilteredEntryMap.this.a(getKey(), v));
                                return super.setValue(v);
                            }
                        };
                    }
                };
            }
        }

        class KeySet extends KeySet<K, V> {
            KeySet() {
                super(FilteredEntryMap.this);
            }

            private boolean removeIf(Predicate<? super K> predicate) {
                return Iterables.removeIf(FilteredEntryMap.this.a.entrySet(), Predicates.and(FilteredEntryMap.this.b, Maps.a(predicate)));
            }

            public boolean remove(Object obj) {
                if (!FilteredEntryMap.this.containsKey(obj)) {
                    return false;
                }
                FilteredEntryMap.this.a.remove(obj);
                return true;
            }

            public boolean removeAll(Collection<?> collection) {
                return removeIf(Predicates.in(collection));
            }

            public boolean retainAll(Collection<?> collection) {
                return removeIf(Predicates.not(Predicates.in(collection)));
            }

            public Object[] toArray() {
                return Lists.newArrayList(iterator()).toArray();
            }

            public <T> T[] toArray(T[] tArr) {
                return Lists.newArrayList(iterator()).toArray(tArr);
            }
        }

        FilteredEntryMap(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate) {
            super(map, predicate);
            this.c = Sets.filter(map.entrySet(), this.b);
        }

        /* access modifiers changed from: protected */
        public Set<Map.Entry<K, V>> createEntrySet() {
            return new EntrySet();
        }

        /* access modifiers changed from: package-private */
        public Set<K> createKeySet() {
            return new KeySet();
        }
    }

    @GwtIncompatible
    private static class FilteredEntryNavigableMap<K, V> extends AbstractNavigableMap<K, V> {
        /* access modifiers changed from: private */
        public final Predicate<? super Map.Entry<K, V>> entryPredicate;
        private final Map<K, V> filteredDelegate;
        /* access modifiers changed from: private */
        public final NavigableMap<K, V> unfiltered;

        FilteredEntryNavigableMap(NavigableMap<K, V> navigableMap, Predicate<? super Map.Entry<K, V>> predicate) {
            this.unfiltered = (NavigableMap) Preconditions.checkNotNull(navigableMap);
            this.entryPredicate = predicate;
            this.filteredDelegate = new FilteredEntryMap(navigableMap, predicate);
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<K, V>> a() {
            return Iterators.filter(this.unfiltered.descendingMap().entrySet().iterator(), this.entryPredicate);
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<K, V>> b() {
            return Iterators.filter(this.unfiltered.entrySet().iterator(), this.entryPredicate);
        }

        public void clear() {
            this.filteredDelegate.clear();
        }

        public Comparator<? super K> comparator() {
            return this.unfiltered.comparator();
        }

        public boolean containsKey(@Nullable Object obj) {
            return this.filteredDelegate.containsKey(obj);
        }

        public NavigableMap<K, V> descendingMap() {
            return Maps.filterEntries(this.unfiltered.descendingMap(), this.entryPredicate);
        }

        public Set<Map.Entry<K, V>> entrySet() {
            return this.filteredDelegate.entrySet();
        }

        @Nullable
        public V get(@Nullable Object obj) {
            return this.filteredDelegate.get(obj);
        }

        public NavigableMap<K, V> headMap(K k, boolean z) {
            return Maps.filterEntries(this.unfiltered.headMap(k, z), this.entryPredicate);
        }

        public boolean isEmpty() {
            return !Iterables.any(this.unfiltered.entrySet(), this.entryPredicate);
        }

        public NavigableSet<K> navigableKeySet() {
            return new NavigableKeySet<K, V>(this) {
                public boolean removeAll(Collection<?> collection) {
                    return Iterators.removeIf(FilteredEntryNavigableMap.this.unfiltered.entrySet().iterator(), Predicates.and(FilteredEntryNavigableMap.this.entryPredicate, Maps.a(Predicates.in(collection))));
                }

                public boolean retainAll(Collection<?> collection) {
                    return Iterators.removeIf(FilteredEntryNavigableMap.this.unfiltered.entrySet().iterator(), Predicates.and(FilteredEntryNavigableMap.this.entryPredicate, Maps.a(Predicates.not(Predicates.in(collection)))));
                }
            };
        }

        public Map.Entry<K, V> pollFirstEntry() {
            return (Map.Entry) Iterables.a(this.unfiltered.entrySet(), this.entryPredicate);
        }

        public Map.Entry<K, V> pollLastEntry() {
            return (Map.Entry) Iterables.a(this.unfiltered.descendingMap().entrySet(), this.entryPredicate);
        }

        public V put(K k, V v) {
            return this.filteredDelegate.put(k, v);
        }

        public void putAll(Map<? extends K, ? extends V> map) {
            this.filteredDelegate.putAll(map);
        }

        public V remove(@Nullable Object obj) {
            return this.filteredDelegate.remove(obj);
        }

        public int size() {
            return this.filteredDelegate.size();
        }

        public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
            return Maps.filterEntries(this.unfiltered.subMap(k, z, k2, z2), this.entryPredicate);
        }

        public NavigableMap<K, V> tailMap(K k, boolean z) {
            return Maps.filterEntries(this.unfiltered.tailMap(k, z), this.entryPredicate);
        }

        public Collection<V> values() {
            return new FilteredMapValues(this, this.unfiltered, this.entryPredicate);
        }
    }

    private static class FilteredEntrySortedMap<K, V> extends FilteredEntryMap<K, V> implements SortedMap<K, V> {

        class SortedKeySet extends FilteredEntryMap<K, V>.KeySet implements SortedSet<K> {
            SortedKeySet() {
                super();
            }

            public Comparator<? super K> comparator() {
                return FilteredEntrySortedMap.this.b().comparator();
            }

            public K first() {
                return FilteredEntrySortedMap.this.firstKey();
            }

            public SortedSet<K> headSet(K k) {
                return (SortedSet) FilteredEntrySortedMap.this.headMap(k).keySet();
            }

            public K last() {
                return FilteredEntrySortedMap.this.lastKey();
            }

            public SortedSet<K> subSet(K k, K k2) {
                return (SortedSet) FilteredEntrySortedMap.this.subMap(k, k2).keySet();
            }

            public SortedSet<K> tailSet(K k) {
                return (SortedSet) FilteredEntrySortedMap.this.tailMap(k).keySet();
            }
        }

        FilteredEntrySortedMap(SortedMap<K, V> sortedMap, Predicate<? super Map.Entry<K, V>> predicate) {
            super(sortedMap, predicate);
        }

        /* access modifiers changed from: package-private */
        public SortedMap<K, V> b() {
            return (SortedMap) this.a;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public SortedSet<K> createKeySet() {
            return new SortedKeySet();
        }

        public Comparator<? super K> comparator() {
            return b().comparator();
        }

        public K firstKey() {
            return keySet().iterator().next();
        }

        public SortedMap<K, V> headMap(K k) {
            return new FilteredEntrySortedMap(b().headMap(k), this.b);
        }

        public SortedSet<K> keySet() {
            return (SortedSet) super.keySet();
        }

        public K lastKey() {
            SortedMap b = b();
            while (true) {
                K lastKey = b.lastKey();
                if (a(lastKey, this.a.get(lastKey))) {
                    return lastKey;
                }
                b = b().headMap(lastKey);
            }
        }

        public SortedMap<K, V> subMap(K k, K k2) {
            return new FilteredEntrySortedMap(b().subMap(k, k2), this.b);
        }

        public SortedMap<K, V> tailMap(K k) {
            return new FilteredEntrySortedMap(b().tailMap(k), this.b);
        }
    }

    private static class FilteredKeyMap<K, V> extends AbstractFilteredMap<K, V> {
        final Predicate<? super K> c;

        FilteredKeyMap(Map<K, V> map, Predicate<? super K> predicate, Predicate<? super Map.Entry<K, V>> predicate2) {
            super(map, predicate2);
            this.c = predicate;
        }

        public boolean containsKey(Object obj) {
            return this.a.containsKey(obj) && this.c.apply(obj);
        }

        /* access modifiers changed from: protected */
        public Set<Map.Entry<K, V>> createEntrySet() {
            return Sets.filter(this.a.entrySet(), this.b);
        }

        /* access modifiers changed from: package-private */
        public Set<K> createKeySet() {
            return Sets.filter(this.a.keySet(), this.c);
        }
    }

    private static final class FilteredMapValues<K, V> extends Values<K, V> {
        final Map<K, V> a;
        final Predicate<? super Map.Entry<K, V>> b;

        FilteredMapValues(Map<K, V> map, Map<K, V> map2, Predicate<? super Map.Entry<K, V>> predicate) {
            super(map);
            this.a = map2;
            this.b = predicate;
        }

        private boolean removeIf(Predicate<? super V> predicate) {
            return Iterables.removeIf(this.a.entrySet(), Predicates.and(this.b, Maps.b(predicate)));
        }

        public boolean remove(Object obj) {
            return Iterables.a(this.a.entrySet(), Predicates.and(this.b, Maps.b(Predicates.equalTo(obj)))) != null;
        }

        public boolean removeAll(Collection<?> collection) {
            return removeIf(Predicates.in(collection));
        }

        public boolean retainAll(Collection<?> collection) {
            return removeIf(Predicates.not(Predicates.in(collection)));
        }

        public Object[] toArray() {
            return Lists.newArrayList(iterator()).toArray();
        }

        public <T> T[] toArray(T[] tArr) {
            return Lists.newArrayList(iterator()).toArray(tArr);
        }
    }

    static abstract class IteratorBasedAbstractMap<K, V> extends AbstractMap<K, V> {
        IteratorBasedAbstractMap() {
        }

        /* access modifiers changed from: package-private */
        public abstract Iterator<Map.Entry<K, V>> b();

        public void clear() {
            Iterators.b(b());
        }

        public Set<Map.Entry<K, V>> entrySet() {
            return new EntrySet<K, V>() {
                /* access modifiers changed from: package-private */
                public Map<K, V> a() {
                    return IteratorBasedAbstractMap.this;
                }

                public Iterator<Map.Entry<K, V>> iterator() {
                    return IteratorBasedAbstractMap.this.b();
                }
            };
        }

        public abstract int size();
    }

    static class KeySet<K, V> extends Sets.ImprovedAbstractSet<K> {
        @Weak
        final Map<K, V> d;

        KeySet(Map<K, V> map) {
            this.d = (Map) Preconditions.checkNotNull(map);
        }

        /* access modifiers changed from: package-private */
        public Map<K, V> c() {
            return this.d;
        }

        public void clear() {
            c().clear();
        }

        public boolean contains(Object obj) {
            return c().containsKey(obj);
        }

        public boolean isEmpty() {
            return c().isEmpty();
        }

        public Iterator<K> iterator() {
            return Maps.a(c().entrySet().iterator());
        }

        public boolean remove(Object obj) {
            if (!contains(obj)) {
                return false;
            }
            c().remove(obj);
            return true;
        }

        public int size() {
            return c().size();
        }
    }

    static class MapDifferenceImpl<K, V> implements MapDifference<K, V> {
        final Map<K, V> a;
        final Map<K, V> b;
        final Map<K, V> c;
        final Map<K, MapDifference.ValueDifference<V>> d;

        MapDifferenceImpl(Map<K, V> map, Map<K, V> map2, Map<K, V> map3, Map<K, MapDifference.ValueDifference<V>> map4) {
            this.a = Maps.unmodifiableMap(map);
            this.b = Maps.unmodifiableMap(map2);
            this.c = Maps.unmodifiableMap(map3);
            this.d = Maps.unmodifiableMap(map4);
        }

        public boolean areEqual() {
            return this.a.isEmpty() && this.b.isEmpty() && this.d.isEmpty();
        }

        public Map<K, MapDifference.ValueDifference<V>> entriesDiffering() {
            return this.d;
        }

        public Map<K, V> entriesInCommon() {
            return this.c;
        }

        public Map<K, V> entriesOnlyOnLeft() {
            return this.a;
        }

        public Map<K, V> entriesOnlyOnRight() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MapDifference)) {
                return false;
            }
            MapDifference mapDifference = (MapDifference) obj;
            return entriesOnlyOnLeft().equals(mapDifference.entriesOnlyOnLeft()) && entriesOnlyOnRight().equals(mapDifference.entriesOnlyOnRight()) && entriesInCommon().equals(mapDifference.entriesInCommon()) && entriesDiffering().equals(mapDifference.entriesDiffering());
        }

        public int hashCode() {
            return Objects.hashCode(entriesOnlyOnLeft(), entriesOnlyOnRight(), entriesInCommon(), entriesDiffering());
        }

        public String toString() {
            if (areEqual()) {
                return "equal";
            }
            StringBuilder sb = new StringBuilder("not equal");
            if (!this.a.isEmpty()) {
                sb.append(": only on left=");
                sb.append(this.a);
            }
            if (!this.b.isEmpty()) {
                sb.append(": only on right=");
                sb.append(this.b);
            }
            if (!this.d.isEmpty()) {
                sb.append(": value differences=");
                sb.append(this.d);
            }
            return sb.toString();
        }
    }

    @GwtIncompatible
    private static final class NavigableAsMapView<K, V> extends AbstractNavigableMap<K, V> {
        private final Function<? super K, V> function;
        private final NavigableSet<K> set;

        NavigableAsMapView(NavigableSet<K> navigableSet, Function<? super K, V> function2) {
            this.set = (NavigableSet) Preconditions.checkNotNull(navigableSet);
            this.function = (Function) Preconditions.checkNotNull(function2);
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<K, V>> a() {
            return descendingMap().entrySet().iterator();
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<K, V>> b() {
            return Maps.a(this.set, this.function);
        }

        public void clear() {
            this.set.clear();
        }

        public Comparator<? super K> comparator() {
            return this.set.comparator();
        }

        public NavigableMap<K, V> descendingMap() {
            return Maps.asMap(this.set.descendingSet(), this.function);
        }

        @Nullable
        public V get(@Nullable Object obj) {
            if (Collections2.a((Collection<?>) this.set, obj)) {
                return this.function.apply(obj);
            }
            return null;
        }

        public NavigableMap<K, V> headMap(K k, boolean z) {
            return Maps.asMap(this.set.headSet(k, z), this.function);
        }

        public NavigableSet<K> navigableKeySet() {
            return Maps.removeOnlyNavigableSet(this.set);
        }

        public int size() {
            return this.set.size();
        }

        public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
            return Maps.asMap(this.set.subSet(k, z, k2, z2), this.function);
        }

        public NavigableMap<K, V> tailMap(K k, boolean z) {
            return Maps.asMap(this.set.tailSet(k, z), this.function);
        }
    }

    @GwtIncompatible
    static class NavigableKeySet<K, V> extends SortedKeySet<K, V> implements NavigableSet<K> {
        NavigableKeySet(NavigableMap<K, V> navigableMap) {
            super(navigableMap);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public NavigableMap<K, V> c() {
            return (NavigableMap) this.d;
        }

        public K ceiling(K k) {
            return c().ceilingKey(k);
        }

        public Iterator<K> descendingIterator() {
            return descendingSet().iterator();
        }

        public NavigableSet<K> descendingSet() {
            return c().descendingKeySet();
        }

        public K floor(K k) {
            return c().floorKey(k);
        }

        public NavigableSet<K> headSet(K k, boolean z) {
            return c().headMap(k, z).navigableKeySet();
        }

        public SortedSet<K> headSet(K k) {
            return headSet(k, false);
        }

        public K higher(K k) {
            return c().higherKey(k);
        }

        public K lower(K k) {
            return c().lowerKey(k);
        }

        public K pollFirst() {
            return Maps.b(c().pollFirstEntry());
        }

        public K pollLast() {
            return Maps.b(c().pollLastEntry());
        }

        public NavigableSet<K> subSet(K k, boolean z, K k2, boolean z2) {
            return c().subMap(k, z, k2, z2).navigableKeySet();
        }

        public SortedSet<K> subSet(K k, K k2) {
            return subSet(k, true, k2, false);
        }

        public NavigableSet<K> tailSet(K k, boolean z) {
            return c().tailMap(k, z).navigableKeySet();
        }

        public SortedSet<K> tailSet(K k) {
            return tailSet(k, true);
        }
    }

    private static class SortedAsMapView<K, V> extends AsMapView<K, V> implements SortedMap<K, V> {
        SortedAsMapView(SortedSet<K> sortedSet, Function<? super K, V> function) {
            super(sortedSet, function);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public SortedSet<K> b() {
            return (SortedSet) super.b();
        }

        public Comparator<? super K> comparator() {
            return b().comparator();
        }

        public K firstKey() {
            return b().first();
        }

        public SortedMap<K, V> headMap(K k) {
            return Maps.asMap(b().headSet(k), this.a);
        }

        public Set<K> keySet() {
            return Maps.removeOnlySortedSet(b());
        }

        public K lastKey() {
            return b().last();
        }

        public SortedMap<K, V> subMap(K k, K k2) {
            return Maps.asMap(b().subSet(k, k2), this.a);
        }

        public SortedMap<K, V> tailMap(K k) {
            return Maps.asMap(b().tailSet(k), this.a);
        }
    }

    static class SortedKeySet<K, V> extends KeySet<K, V> implements SortedSet<K> {
        SortedKeySet(SortedMap<K, V> sortedMap) {
            super(sortedMap);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public SortedMap<K, V> c() {
            return (SortedMap) super.c();
        }

        public Comparator<? super K> comparator() {
            return c().comparator();
        }

        public K first() {
            return c().firstKey();
        }

        public SortedSet<K> headSet(K k) {
            return new SortedKeySet(c().headMap(k));
        }

        public K last() {
            return c().lastKey();
        }

        public SortedSet<K> subSet(K k, K k2) {
            return new SortedKeySet(c().subMap(k, k2));
        }

        public SortedSet<K> tailSet(K k) {
            return new SortedKeySet(c().tailMap(k));
        }
    }

    static class SortedMapDifferenceImpl<K, V> extends MapDifferenceImpl<K, V> implements SortedMapDifference<K, V> {
        SortedMapDifferenceImpl(SortedMap<K, V> sortedMap, SortedMap<K, V> sortedMap2, SortedMap<K, V> sortedMap3, SortedMap<K, MapDifference.ValueDifference<V>> sortedMap4) {
            super(sortedMap, sortedMap2, sortedMap3, sortedMap4);
        }

        public SortedMap<K, MapDifference.ValueDifference<V>> entriesDiffering() {
            return (SortedMap) super.entriesDiffering();
        }

        public SortedMap<K, V> entriesInCommon() {
            return (SortedMap) super.entriesInCommon();
        }

        public SortedMap<K, V> entriesOnlyOnLeft() {
            return (SortedMap) super.entriesOnlyOnLeft();
        }

        public SortedMap<K, V> entriesOnlyOnRight() {
            return (SortedMap) super.entriesOnlyOnRight();
        }
    }

    static class TransformedEntriesMap<K, V1, V2> extends IteratorBasedAbstractMap<K, V2> {
        final Map<K, V1> a;
        final EntryTransformer<? super K, ? super V1, V2> b;

        TransformedEntriesMap(Map<K, V1> map, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
            this.a = (Map) Preconditions.checkNotNull(map);
            this.b = (EntryTransformer) Preconditions.checkNotNull(entryTransformer);
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<K, V2>> b() {
            return Iterators.transform(this.a.entrySet().iterator(), Maps.b(this.b));
        }

        public void clear() {
            this.a.clear();
        }

        public boolean containsKey(Object obj) {
            return this.a.containsKey(obj);
        }

        public V2 get(Object obj) {
            V1 v1 = this.a.get(obj);
            if (v1 != null || this.a.containsKey(obj)) {
                return this.b.transformEntry(obj, v1);
            }
            return null;
        }

        public Set<K> keySet() {
            return this.a.keySet();
        }

        public V2 remove(Object obj) {
            if (this.a.containsKey(obj)) {
                return this.b.transformEntry(obj, this.a.remove(obj));
            }
            return null;
        }

        public int size() {
            return this.a.size();
        }

        public Collection<V2> values() {
            return new Values(this);
        }
    }

    @GwtIncompatible
    private static class TransformedEntriesNavigableMap<K, V1, V2> extends TransformedEntriesSortedMap<K, V1, V2> implements NavigableMap<K, V2> {
        TransformedEntriesNavigableMap(NavigableMap<K, V1> navigableMap, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
            super(navigableMap, entryTransformer);
        }

        @Nullable
        private Map.Entry<K, V2> transformEntry(@Nullable Map.Entry<K, V1> entry) {
            if (entry == null) {
                return null;
            }
            return Maps.a(this.b, entry);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public NavigableMap<K, V1> c() {
            return (NavigableMap) super.c();
        }

        public Map.Entry<K, V2> ceilingEntry(K k) {
            return transformEntry(c().ceilingEntry(k));
        }

        public K ceilingKey(K k) {
            return c().ceilingKey(k);
        }

        public NavigableSet<K> descendingKeySet() {
            return c().descendingKeySet();
        }

        public NavigableMap<K, V2> descendingMap() {
            return Maps.transformEntries(c().descendingMap(), this.b);
        }

        public Map.Entry<K, V2> firstEntry() {
            return transformEntry(c().firstEntry());
        }

        public Map.Entry<K, V2> floorEntry(K k) {
            return transformEntry(c().floorEntry(k));
        }

        public K floorKey(K k) {
            return c().floorKey(k);
        }

        public NavigableMap<K, V2> headMap(K k) {
            return headMap(k, false);
        }

        public NavigableMap<K, V2> headMap(K k, boolean z) {
            return Maps.transformEntries(c().headMap(k, z), this.b);
        }

        public Map.Entry<K, V2> higherEntry(K k) {
            return transformEntry(c().higherEntry(k));
        }

        public K higherKey(K k) {
            return c().higherKey(k);
        }

        public Map.Entry<K, V2> lastEntry() {
            return transformEntry(c().lastEntry());
        }

        public Map.Entry<K, V2> lowerEntry(K k) {
            return transformEntry(c().lowerEntry(k));
        }

        public K lowerKey(K k) {
            return c().lowerKey(k);
        }

        public NavigableSet<K> navigableKeySet() {
            return c().navigableKeySet();
        }

        public Map.Entry<K, V2> pollFirstEntry() {
            return transformEntry(c().pollFirstEntry());
        }

        public Map.Entry<K, V2> pollLastEntry() {
            return transformEntry(c().pollLastEntry());
        }

        public NavigableMap<K, V2> subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }

        public NavigableMap<K, V2> subMap(K k, boolean z, K k2, boolean z2) {
            return Maps.transformEntries(c().subMap(k, z, k2, z2), this.b);
        }

        public NavigableMap<K, V2> tailMap(K k) {
            return tailMap(k, true);
        }

        public NavigableMap<K, V2> tailMap(K k, boolean z) {
            return Maps.transformEntries(c().tailMap(k, z), this.b);
        }
    }

    static class TransformedEntriesSortedMap<K, V1, V2> extends TransformedEntriesMap<K, V1, V2> implements SortedMap<K, V2> {
        TransformedEntriesSortedMap(SortedMap<K, V1> sortedMap, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
            super(sortedMap, entryTransformer);
        }

        /* access modifiers changed from: protected */
        public SortedMap<K, V1> c() {
            return (SortedMap) this.a;
        }

        public Comparator<? super K> comparator() {
            return c().comparator();
        }

        public K firstKey() {
            return c().firstKey();
        }

        public SortedMap<K, V2> headMap(K k) {
            return Maps.transformEntries(c().headMap(k), this.b);
        }

        public K lastKey() {
            return c().lastKey();
        }

        public SortedMap<K, V2> subMap(K k, K k2) {
            return Maps.transformEntries(c().subMap(k, k2), this.b);
        }

        public SortedMap<K, V2> tailMap(K k) {
            return Maps.transformEntries(c().tailMap(k), this.b);
        }
    }

    private static class UnmodifiableBiMap<K, V> extends ForwardingMap<K, V> implements BiMap<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        final Map<K, V> a;
        final BiMap<? extends K, ? extends V> b;
        @RetainedWith
        BiMap<V, K> c;
        transient Set<V> d;

        UnmodifiableBiMap(BiMap<? extends K, ? extends V> biMap, @Nullable BiMap<V, K> biMap2) {
            this.a = Collections.unmodifiableMap(biMap);
            this.b = biMap;
            this.c = biMap2;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Map<K, V> delegate() {
            return this.a;
        }

        public V forcePut(K k, V v) {
            throw new UnsupportedOperationException();
        }

        public BiMap<V, K> inverse() {
            BiMap<V, K> biMap = this.c;
            if (biMap != null) {
                return biMap;
            }
            UnmodifiableBiMap unmodifiableBiMap = new UnmodifiableBiMap(this.b.inverse(), this);
            this.c = unmodifiableBiMap;
            return unmodifiableBiMap;
        }

        public Set<V> values() {
            Set<V> set = this.d;
            if (set != null) {
                return set;
            }
            Set<V> unmodifiableSet = Collections.unmodifiableSet(this.b.values());
            this.d = unmodifiableSet;
            return unmodifiableSet;
        }
    }

    static class UnmodifiableEntries<K, V> extends ForwardingCollection<Map.Entry<K, V>> {
        private final Collection<Map.Entry<K, V>> entries;

        UnmodifiableEntries(Collection<Map.Entry<K, V>> collection) {
            this.entries = collection;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public Collection<Map.Entry<K, V>> delegate() {
            return this.entries;
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return Maps.c(this.entries.iterator());
        }

        public Object[] toArray() {
            return g();
        }

        public <T> T[] toArray(T[] tArr) {
            return a(tArr);
        }
    }

    static class UnmodifiableEntrySet<K, V> extends UnmodifiableEntries<K, V> implements Set<Map.Entry<K, V>> {
        UnmodifiableEntrySet(Set<Map.Entry<K, V>> set) {
            super(set);
        }

        public boolean equals(@Nullable Object obj) {
            return Sets.a((Set<?>) this, obj);
        }

        public int hashCode() {
            return Sets.a(this);
        }
    }

    @GwtIncompatible
    static class UnmodifiableNavigableMap<K, V> extends ForwardingSortedMap<K, V> implements Serializable, NavigableMap<K, V> {
        private final NavigableMap<K, ? extends V> delegate;
        private transient UnmodifiableNavigableMap<K, V> descendingMap;

        UnmodifiableNavigableMap(NavigableMap<K, ? extends V> navigableMap) {
            this.delegate = navigableMap;
        }

        UnmodifiableNavigableMap(NavigableMap<K, ? extends V> navigableMap, UnmodifiableNavigableMap<K, V> unmodifiableNavigableMap) {
            this.delegate = navigableMap;
            this.descendingMap = unmodifiableNavigableMap;
        }

        /* access modifiers changed from: protected */
        /* renamed from: c */
        public SortedMap<K, V> delegate() {
            return Collections.unmodifiableSortedMap(this.delegate);
        }

        public Map.Entry<K, V> ceilingEntry(K k) {
            return Maps.unmodifiableOrNull(this.delegate.ceilingEntry(k));
        }

        public K ceilingKey(K k) {
            return this.delegate.ceilingKey(k);
        }

        public NavigableSet<K> descendingKeySet() {
            return Sets.unmodifiableNavigableSet(this.delegate.descendingKeySet());
        }

        public NavigableMap<K, V> descendingMap() {
            UnmodifiableNavigableMap<K, V> unmodifiableNavigableMap = this.descendingMap;
            if (unmodifiableNavigableMap != null) {
                return unmodifiableNavigableMap;
            }
            UnmodifiableNavigableMap<K, V> unmodifiableNavigableMap2 = new UnmodifiableNavigableMap<>(this.delegate.descendingMap(), this);
            this.descendingMap = unmodifiableNavigableMap2;
            return unmodifiableNavigableMap2;
        }

        public Map.Entry<K, V> firstEntry() {
            return Maps.unmodifiableOrNull(this.delegate.firstEntry());
        }

        public Map.Entry<K, V> floorEntry(K k) {
            return Maps.unmodifiableOrNull(this.delegate.floorEntry(k));
        }

        public K floorKey(K k) {
            return this.delegate.floorKey(k);
        }

        public NavigableMap<K, V> headMap(K k, boolean z) {
            return Maps.unmodifiableNavigableMap(this.delegate.headMap(k, z));
        }

        public SortedMap<K, V> headMap(K k) {
            return headMap(k, false);
        }

        public Map.Entry<K, V> higherEntry(K k) {
            return Maps.unmodifiableOrNull(this.delegate.higherEntry(k));
        }

        public K higherKey(K k) {
            return this.delegate.higherKey(k);
        }

        public Set<K> keySet() {
            return navigableKeySet();
        }

        public Map.Entry<K, V> lastEntry() {
            return Maps.unmodifiableOrNull(this.delegate.lastEntry());
        }

        public Map.Entry<K, V> lowerEntry(K k) {
            return Maps.unmodifiableOrNull(this.delegate.lowerEntry(k));
        }

        public K lowerKey(K k) {
            return this.delegate.lowerKey(k);
        }

        public NavigableSet<K> navigableKeySet() {
            return Sets.unmodifiableNavigableSet(this.delegate.navigableKeySet());
        }

        public final Map.Entry<K, V> pollFirstEntry() {
            throw new UnsupportedOperationException();
        }

        public final Map.Entry<K, V> pollLastEntry() {
            throw new UnsupportedOperationException();
        }

        public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
            return Maps.unmodifiableNavigableMap(this.delegate.subMap(k, z, k2, z2));
        }

        public SortedMap<K, V> subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }

        public NavigableMap<K, V> tailMap(K k, boolean z) {
            return Maps.unmodifiableNavigableMap(this.delegate.tailMap(k, z));
        }

        public SortedMap<K, V> tailMap(K k) {
            return tailMap(k, true);
        }
    }

    static class ValueDifferenceImpl<V> implements MapDifference.ValueDifference<V> {
        private final V left;
        private final V right;

        private ValueDifferenceImpl(@Nullable V v, @Nullable V v2) {
            this.left = v;
            this.right = v2;
        }

        static <V> MapDifference.ValueDifference<V> a(@Nullable V v, @Nullable V v2) {
            return new ValueDifferenceImpl(v, v2);
        }

        public boolean equals(@Nullable Object obj) {
            if (!(obj instanceof MapDifference.ValueDifference)) {
                return false;
            }
            MapDifference.ValueDifference valueDifference = (MapDifference.ValueDifference) obj;
            return Objects.equal(this.left, valueDifference.leftValue()) && Objects.equal(this.right, valueDifference.rightValue());
        }

        public int hashCode() {
            return Objects.hashCode(this.left, this.right);
        }

        public V leftValue() {
            return this.left;
        }

        public V rightValue() {
            return this.right;
        }

        public String toString() {
            return "(" + this.left + ", " + this.right + ")";
        }
    }

    static class Values<K, V> extends AbstractCollection<V> {
        @Weak
        final Map<K, V> c;

        Values(Map<K, V> map) {
            this.c = (Map) Preconditions.checkNotNull(map);
        }

        /* access modifiers changed from: package-private */
        public final Map<K, V> a() {
            return this.c;
        }

        public void clear() {
            a().clear();
        }

        public boolean contains(@Nullable Object obj) {
            return a().containsValue(obj);
        }

        public boolean isEmpty() {
            return a().isEmpty();
        }

        public Iterator<V> iterator() {
            return Maps.b(a().entrySet().iterator());
        }

        public boolean remove(Object obj) {
            try {
                return super.remove(obj);
            } catch (UnsupportedOperationException unused) {
                for (Map.Entry entry : a().entrySet()) {
                    if (Objects.equal(obj, entry.getValue())) {
                        a().remove(entry.getKey());
                        return true;
                    }
                }
                return false;
            }
        }

        public boolean removeAll(Collection<?> collection) {
            try {
                return super.removeAll((Collection) Preconditions.checkNotNull(collection));
            } catch (UnsupportedOperationException unused) {
                HashSet newHashSet = Sets.newHashSet();
                for (Map.Entry entry : a().entrySet()) {
                    if (collection.contains(entry.getValue())) {
                        newHashSet.add(entry.getKey());
                    }
                }
                return a().keySet().removeAll(newHashSet);
            }
        }

        public boolean retainAll(Collection<?> collection) {
            try {
                return super.retainAll((Collection) Preconditions.checkNotNull(collection));
            } catch (UnsupportedOperationException unused) {
                HashSet newHashSet = Sets.newHashSet();
                for (Map.Entry entry : a().entrySet()) {
                    if (collection.contains(entry.getValue())) {
                        newHashSet.add(entry.getKey());
                    }
                }
                return a().keySet().retainAll(newHashSet);
            }
        }

        public int size() {
            return a().size();
        }
    }

    @GwtCompatible
    static abstract class ViewCachingAbstractMap<K, V> extends AbstractMap<K, V> {
        private transient Set<Map.Entry<K, V>> entrySet;
        private transient Set<K> keySet;
        private transient Collection<V> values;

        ViewCachingAbstractMap() {
        }

        /* access modifiers changed from: package-private */
        public Collection<V> a() {
            return new Values(this);
        }

        /* access modifiers changed from: package-private */
        public abstract Set<Map.Entry<K, V>> createEntrySet();

        /* access modifiers changed from: package-private */
        public Set<K> createKeySet() {
            return new KeySet(this);
        }

        public Set<Map.Entry<K, V>> entrySet() {
            Set<Map.Entry<K, V>> set = this.entrySet;
            if (set != null) {
                return set;
            }
            Set<Map.Entry<K, V>> createEntrySet = createEntrySet();
            this.entrySet = createEntrySet;
            return createEntrySet;
        }

        public Set<K> keySet() {
            Set<K> set = this.keySet;
            if (set != null) {
                return set;
            }
            Set<K> createKeySet = createKeySet();
            this.keySet = createKeySet;
            return createKeySet;
        }

        public Collection<V> values() {
            Collection<V> collection = this.values;
            if (collection != null) {
                return collection;
            }
            Collection<V> a = a();
            this.values = a;
            return a;
        }
    }

    private Maps() {
    }

    static int a(int i) {
        if (i < 3) {
            CollectPreconditions.a(i, "expectedSize");
            return i + 1;
        } else if (i < 1073741824) {
            return (int) ((((float) i) / 0.75f) + 1.0f);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    static <K> Function<Map.Entry<K, ?>, K> a() {
        return EntryFunction.KEY;
    }

    static <K, V1, V2> Function<Map.Entry<K, V1>, V2> a(final EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        Preconditions.checkNotNull(entryTransformer);
        return new Function<Map.Entry<K, V1>, V2>() {
            public V2 apply(Map.Entry<K, V1> entry) {
                return entryTransformer.transformEntry(entry.getKey(), entry.getValue());
            }
        };
    }

    static <K, V1, V2> Function<V1, V2> a(final EntryTransformer<? super K, V1, V2> entryTransformer, final K k) {
        Preconditions.checkNotNull(entryTransformer);
        return new Function<V1, V2>() {
            public V2 apply(@Nullable V1 v1) {
                return entryTransformer.transformEntry(k, v1);
            }
        };
    }

    static <K> Predicate<Map.Entry<K, ?>> a(Predicate<? super K> predicate) {
        return Predicates.compose(predicate, a());
    }

    static <E> ImmutableMap<E, Integer> a(Collection<E> collection) {
        ImmutableMap.Builder builder = new ImmutableMap.Builder(collection.size());
        int i = 0;
        for (E put : collection) {
            builder.put(put, Integer.valueOf(i));
            i++;
        }
        return builder.build();
    }

    static <K, V1, V2> EntryTransformer<K, V1, V2> a(final Function<? super V1, V2> function) {
        Preconditions.checkNotNull(function);
        return new EntryTransformer<K, V1, V2>() {
            public V2 transformEntry(K k, V1 v1) {
                return function.apply(v1);
            }
        };
    }

    static <V> V a(Map<?, V> map, @Nullable Object obj) {
        Preconditions.checkNotNull(map);
        try {
            return map.get(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return null;
        }
    }

    static String a(Map<?, ?> map) {
        StringBuilder a2 = Collections2.a(map.size());
        a2.append('{');
        a.appendTo(a2, map);
        a2.append('}');
        return a2.toString();
    }

    static <E> Comparator<? super E> a(@Nullable Comparator<? super E> comparator) {
        return comparator != null ? comparator : Ordering.natural();
    }

    static <K, V> Iterator<K> a(Iterator<Map.Entry<K, V>> it) {
        return Iterators.transform(it, a());
    }

    static <K, V> Iterator<Map.Entry<K, V>> a(Set<K> set, final Function<? super K, V> function) {
        return new TransformedIterator<K, Map.Entry<K, V>>(set.iterator()) {
            /* access modifiers changed from: package-private */
            /* renamed from: b */
            public Map.Entry<K, V> a(K k) {
                return Maps.immutableEntry(k, function.apply(k));
            }
        };
    }

    static <V2, K, V1> Map.Entry<K, V2> a(final EntryTransformer<? super K, ? super V1, V2> entryTransformer, final Map.Entry<K, V1> entry) {
        Preconditions.checkNotNull(entryTransformer);
        Preconditions.checkNotNull(entry);
        return new AbstractMapEntry<K, V2>() {
            public K getKey() {
                return entry.getKey();
            }

            public V2 getValue() {
                return entryTransformer.transformEntry(entry.getKey(), entry.getValue());
            }
        };
    }

    static <K, V> Map.Entry<K, V> a(final Map.Entry<? extends K, ? extends V> entry) {
        Preconditions.checkNotNull(entry);
        return new AbstractMapEntry<K, V>() {
            public K getKey() {
                return entry.getKey();
            }

            public V getValue() {
                return entry.getValue();
            }
        };
    }

    static <K, V> Set<Map.Entry<K, V>> a(Set<Map.Entry<K, V>> set) {
        return new UnmodifiableEntrySet(Collections.unmodifiableSet(set));
    }

    static <K, V> boolean a(Collection<Map.Entry<K, V>> collection, Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        return collection.contains(a((Map.Entry) obj));
    }

    @Beta
    public static <A, B> Converter<A, B> asConverter(BiMap<A, B> biMap) {
        return new BiMapConverter(biMap);
    }

    public static <K, V> Map<K, V> asMap(Set<K> set, Function<? super K, V> function) {
        return new AsMapView(set, function);
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> asMap(NavigableSet<K> navigableSet, Function<? super K, V> function) {
        return new NavigableAsMapView(navigableSet, function);
    }

    public static <K, V> SortedMap<K, V> asMap(SortedSet<K> sortedSet, Function<? super K, V> function) {
        return new SortedAsMapView(sortedSet, function);
    }

    static <V> Function<Map.Entry<?, V>, V> b() {
        return EntryFunction.VALUE;
    }

    static <K, V1, V2> Function<Map.Entry<K, V1>, Map.Entry<K, V2>> b(final EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        Preconditions.checkNotNull(entryTransformer);
        return new Function<Map.Entry<K, V1>, Map.Entry<K, V2>>() {
            public Map.Entry<K, V2> apply(Map.Entry<K, V1> entry) {
                return Maps.a(entryTransformer, entry);
            }
        };
    }

    static <V> Predicate<Map.Entry<?, V>> b(Predicate<? super V> predicate) {
        return Predicates.compose(predicate, b());
    }

    @Nullable
    static <K> K b(@Nullable Map.Entry<K, ?> entry) {
        if (entry == null) {
            return null;
        }
        return entry.getKey();
    }

    static <K, V> Iterator<V> b(Iterator<Map.Entry<K, V>> it) {
        return Iterators.transform(it, b());
    }

    static <K, V> boolean b(Collection<Map.Entry<K, V>> collection, Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        return collection.remove(a((Map.Entry) obj));
    }

    static boolean b(Map<?, ?> map, Object obj) {
        Preconditions.checkNotNull(map);
        try {
            return map.containsKey(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    static <K, V> UnmodifiableIterator<Map.Entry<K, V>> c(final Iterator<Map.Entry<K, V>> it) {
        return new UnmodifiableIterator<Map.Entry<K, V>>() {
            public boolean hasNext() {
                return it.hasNext();
            }

            public Map.Entry<K, V> next() {
                return Maps.a((Map.Entry) it.next());
            }
        };
    }

    @Nullable
    static <V> V c(@Nullable Map.Entry<?, V> entry) {
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    static <V> V c(Map<?, V> map, Object obj) {
        Preconditions.checkNotNull(map);
        try {
            return map.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return null;
        }
    }

    static boolean d(Map<?, ?> map, Object obj) {
        if (map == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return map.entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    public static <K, V> MapDifference<K, V> difference(Map<? extends K, ? extends V> map, Map<? extends K, ? extends V> map2) {
        return map instanceof SortedMap ? difference((SortedMap) map, map2) : difference(map, map2, Equivalence.equals());
    }

    public static <K, V> MapDifference<K, V> difference(Map<? extends K, ? extends V> map, Map<? extends K, ? extends V> map2, Equivalence<? super V> equivalence) {
        Preconditions.checkNotNull(equivalence);
        LinkedHashMap newLinkedHashMap = newLinkedHashMap();
        LinkedHashMap linkedHashMap = new LinkedHashMap(map2);
        LinkedHashMap newLinkedHashMap2 = newLinkedHashMap();
        LinkedHashMap newLinkedHashMap3 = newLinkedHashMap();
        doDifference(map, map2, equivalence, newLinkedHashMap, linkedHashMap, newLinkedHashMap2, newLinkedHashMap3);
        return new MapDifferenceImpl(newLinkedHashMap, linkedHashMap, newLinkedHashMap2, newLinkedHashMap3);
    }

    public static <K, V> SortedMapDifference<K, V> difference(SortedMap<K, ? extends V> sortedMap, Map<? extends K, ? extends V> map) {
        Preconditions.checkNotNull(sortedMap);
        Preconditions.checkNotNull(map);
        Comparator<? super E> a2 = a(sortedMap.comparator());
        TreeMap<K, V> newTreeMap = newTreeMap(a2);
        TreeMap<K, V> newTreeMap2 = newTreeMap(a2);
        newTreeMap2.putAll(map);
        TreeMap<K, V> newTreeMap3 = newTreeMap(a2);
        TreeMap<K, V> newTreeMap4 = newTreeMap(a2);
        doDifference(sortedMap, map, Equivalence.equals(), newTreeMap, newTreeMap2, newTreeMap3, newTreeMap4);
        return new SortedMapDifferenceImpl(newTreeMap, newTreeMap2, newTreeMap3, newTreeMap4);
    }

    private static <K, V> void doDifference(Map<? extends K, ? extends V> map, Map<? extends K, ? extends V> map2, Equivalence<? super V> equivalence, Map<K, V> map3, Map<K, V> map4, Map<K, V> map5, Map<K, MapDifference.ValueDifference<V>> map6) {
        for (Map.Entry next : map.entrySet()) {
            Object key = next.getKey();
            Object value = next.getValue();
            if (map2.containsKey(key)) {
                V remove = map4.remove(key);
                if (equivalence.equivalent(value, remove)) {
                    map5.put(key, value);
                } else {
                    map6.put(key, ValueDifferenceImpl.a(value, remove));
                }
            } else {
                map3.put(key, value);
            }
        }
    }

    public static <K, V> BiMap<K, V> filterEntries(BiMap<K, V> biMap, Predicate<? super Map.Entry<K, V>> predicate) {
        Preconditions.checkNotNull(biMap);
        Preconditions.checkNotNull(predicate);
        return biMap instanceof FilteredEntryBiMap ? filterFiltered((FilteredEntryBiMap) biMap, predicate) : new FilteredEntryBiMap(biMap, predicate);
    }

    public static <K, V> Map<K, V> filterEntries(Map<K, V> map, Predicate<? super Map.Entry<K, V>> predicate) {
        Preconditions.checkNotNull(predicate);
        return map instanceof AbstractFilteredMap ? filterFiltered((AbstractFilteredMap) map, predicate) : new FilteredEntryMap((Map) Preconditions.checkNotNull(map), predicate);
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> filterEntries(NavigableMap<K, V> navigableMap, Predicate<? super Map.Entry<K, V>> predicate) {
        Preconditions.checkNotNull(predicate);
        return navigableMap instanceof FilteredEntryNavigableMap ? filterFiltered((FilteredEntryNavigableMap) navigableMap, predicate) : new FilteredEntryNavigableMap((NavigableMap) Preconditions.checkNotNull(navigableMap), predicate);
    }

    public static <K, V> SortedMap<K, V> filterEntries(SortedMap<K, V> sortedMap, Predicate<? super Map.Entry<K, V>> predicate) {
        Preconditions.checkNotNull(predicate);
        return sortedMap instanceof FilteredEntrySortedMap ? filterFiltered((FilteredEntrySortedMap) sortedMap, predicate) : new FilteredEntrySortedMap((SortedMap) Preconditions.checkNotNull(sortedMap), predicate);
    }

    private static <K, V> BiMap<K, V> filterFiltered(FilteredEntryBiMap<K, V> filteredEntryBiMap, Predicate<? super Map.Entry<K, V>> predicate) {
        return new FilteredEntryBiMap(filteredEntryBiMap.b(), Predicates.and(filteredEntryBiMap.b, predicate));
    }

    private static <K, V> Map<K, V> filterFiltered(AbstractFilteredMap<K, V> abstractFilteredMap, Predicate<? super Map.Entry<K, V>> predicate) {
        return new FilteredEntryMap(abstractFilteredMap.a, Predicates.and(abstractFilteredMap.b, predicate));
    }

    @GwtIncompatible
    private static <K, V> NavigableMap<K, V> filterFiltered(FilteredEntryNavigableMap<K, V> filteredEntryNavigableMap, Predicate<? super Map.Entry<K, V>> predicate) {
        return new FilteredEntryNavigableMap(filteredEntryNavigableMap.unfiltered, Predicates.and(filteredEntryNavigableMap.entryPredicate, predicate));
    }

    private static <K, V> SortedMap<K, V> filterFiltered(FilteredEntrySortedMap<K, V> filteredEntrySortedMap, Predicate<? super Map.Entry<K, V>> predicate) {
        return new FilteredEntrySortedMap(filteredEntrySortedMap.b(), Predicates.and(filteredEntrySortedMap.b, predicate));
    }

    public static <K, V> BiMap<K, V> filterKeys(BiMap<K, V> biMap, Predicate<? super K> predicate) {
        Preconditions.checkNotNull(predicate);
        return filterEntries(biMap, a(predicate));
    }

    public static <K, V> Map<K, V> filterKeys(Map<K, V> map, Predicate<? super K> predicate) {
        Preconditions.checkNotNull(predicate);
        Predicate<Map.Entry<K, ?>> a2 = a(predicate);
        return map instanceof AbstractFilteredMap ? filterFiltered((AbstractFilteredMap) map, a2) : new FilteredKeyMap((Map) Preconditions.checkNotNull(map), predicate, a2);
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> filterKeys(NavigableMap<K, V> navigableMap, Predicate<? super K> predicate) {
        return filterEntries(navigableMap, a(predicate));
    }

    public static <K, V> SortedMap<K, V> filterKeys(SortedMap<K, V> sortedMap, Predicate<? super K> predicate) {
        return filterEntries(sortedMap, a(predicate));
    }

    public static <K, V> BiMap<K, V> filterValues(BiMap<K, V> biMap, Predicate<? super V> predicate) {
        return filterEntries(biMap, b(predicate));
    }

    public static <K, V> Map<K, V> filterValues(Map<K, V> map, Predicate<? super V> predicate) {
        return filterEntries(map, b(predicate));
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> filterValues(NavigableMap<K, V> navigableMap, Predicate<? super V> predicate) {
        return filterEntries(navigableMap, b(predicate));
    }

    public static <K, V> SortedMap<K, V> filterValues(SortedMap<K, V> sortedMap, Predicate<? super V> predicate) {
        return filterEntries(sortedMap, b(predicate));
    }

    @GwtIncompatible
    public static ImmutableMap<String, String> fromProperties(Properties properties) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String str = (String) propertyNames.nextElement();
            builder.put(str, properties.getProperty(str));
        }
        return builder.build();
    }

    @GwtCompatible(serializable = true)
    public static <K, V> Map.Entry<K, V> immutableEntry(@Nullable K k, @Nullable V v) {
        return new ImmutableEntry(k, v);
    }

    @GwtCompatible(serializable = true)
    @Beta
    public static <K extends Enum<K>, V> ImmutableMap<K, V> immutableEnumMap(Map<K, ? extends V> map) {
        if (map instanceof ImmutableEnumMap) {
            return (ImmutableEnumMap) map;
        }
        if (map.isEmpty()) {
            return ImmutableMap.of();
        }
        for (Map.Entry next : map.entrySet()) {
            Preconditions.checkNotNull(next.getKey());
            Preconditions.checkNotNull(next.getValue());
        }
        return ImmutableEnumMap.a(new EnumMap(map));
    }

    public static <K, V> ConcurrentMap<K, V> newConcurrentMap() {
        return new MapMaker().makeMap();
    }

    public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Class<K> cls) {
        return new EnumMap<>((Class) Preconditions.checkNotNull(cls));
    }

    public static <K extends Enum<K>, V> EnumMap<K, V> newEnumMap(Map<K, ? extends V> map) {
        return new EnumMap<>(map);
    }

    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }

    public static <K, V> HashMap<K, V> newHashMap(Map<? extends K, ? extends V> map) {
        return new HashMap<>(map);
    }

    public static <K, V> HashMap<K, V> newHashMapWithExpectedSize(int i) {
        return new HashMap<>(a(i));
    }

    public static <K, V> IdentityHashMap<K, V> newIdentityHashMap() {
        return new IdentityHashMap<>();
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap(Map<? extends K, ? extends V> map) {
        return new LinkedHashMap<>(map);
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMapWithExpectedSize(int i) {
        return new LinkedHashMap<>(a(i));
    }

    public static <K extends Comparable, V> TreeMap<K, V> newTreeMap() {
        return new TreeMap<>();
    }

    public static <C, K extends C, V> TreeMap<K, V> newTreeMap(@Nullable Comparator<C> comparator) {
        return new TreeMap<>(comparator);
    }

    public static <K, V> TreeMap<K, V> newTreeMap(SortedMap<K, ? extends V> sortedMap) {
        return new TreeMap<>(sortedMap);
    }

    /* access modifiers changed from: private */
    @GwtIncompatible
    public static <E> NavigableSet<E> removeOnlyNavigableSet(final NavigableSet<E> navigableSet) {
        return new ForwardingNavigableSet<E>() {
            public boolean add(E e) {
                throw new UnsupportedOperationException();
            }

            public boolean addAll(Collection<? extends E> collection) {
                throw new UnsupportedOperationException();
            }

            /* access modifiers changed from: protected */
            /* renamed from: d */
            public NavigableSet<E> delegate() {
                return navigableSet;
            }

            public NavigableSet<E> descendingSet() {
                return Maps.removeOnlyNavigableSet(super.descendingSet());
            }

            public NavigableSet<E> headSet(E e, boolean z) {
                return Maps.removeOnlyNavigableSet(super.headSet(e, z));
            }

            public SortedSet<E> headSet(E e) {
                return Maps.removeOnlySortedSet(super.headSet(e));
            }

            public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
                return Maps.removeOnlyNavigableSet(super.subSet(e, z, e2, z2));
            }

            public SortedSet<E> subSet(E e, E e2) {
                return Maps.removeOnlySortedSet(super.subSet(e, e2));
            }

            public NavigableSet<E> tailSet(E e, boolean z) {
                return Maps.removeOnlyNavigableSet(super.tailSet(e, z));
            }

            public SortedSet<E> tailSet(E e) {
                return Maps.removeOnlySortedSet(super.tailSet(e));
            }
        };
    }

    /* access modifiers changed from: private */
    public static <E> Set<E> removeOnlySet(final Set<E> set) {
        return new ForwardingSet<E>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Set<E> delegate() {
                return set;
            }

            public boolean add(E e) {
                throw new UnsupportedOperationException();
            }

            public boolean addAll(Collection<? extends E> collection) {
                throw new UnsupportedOperationException();
            }
        };
    }

    /* access modifiers changed from: private */
    public static <E> SortedSet<E> removeOnlySortedSet(final SortedSet<E> sortedSet) {
        return new ForwardingSortedSet<E>() {
            public boolean add(E e) {
                throw new UnsupportedOperationException();
            }

            public boolean addAll(Collection<? extends E> collection) {
                throw new UnsupportedOperationException();
            }

            /* access modifiers changed from: protected */
            /* renamed from: c */
            public SortedSet<E> delegate() {
                return sortedSet;
            }

            public SortedSet<E> headSet(E e) {
                return Maps.removeOnlySortedSet(super.headSet(e));
            }

            public SortedSet<E> subSet(E e, E e2) {
                return Maps.removeOnlySortedSet(super.subSet(e, e2));
            }

            public SortedSet<E> tailSet(E e) {
                return Maps.removeOnlySortedSet(super.tailSet(e));
            }
        };
    }

    @GwtIncompatible
    @Beta
    public static <K extends Comparable<? super K>, V> NavigableMap<K, V> subMap(NavigableMap<K, V> navigableMap, Range<K> range) {
        boolean z = true;
        if (navigableMap.comparator() != null && navigableMap.comparator() != Ordering.natural() && range.hasLowerBound() && range.hasUpperBound()) {
            Preconditions.checkArgument(navigableMap.comparator().compare(range.lowerEndpoint(), range.upperEndpoint()) <= 0, "map is using a custom comparator which is inconsistent with the natural ordering.");
        }
        if (range.hasLowerBound() && range.hasUpperBound()) {
            K lowerEndpoint = range.lowerEndpoint();
            boolean z2 = range.lowerBoundType() == BoundType.CLOSED;
            K upperEndpoint = range.upperEndpoint();
            if (range.upperBoundType() != BoundType.CLOSED) {
                z = false;
            }
            return navigableMap.subMap(lowerEndpoint, z2, upperEndpoint, z);
        } else if (range.hasLowerBound()) {
            K lowerEndpoint2 = range.lowerEndpoint();
            if (range.lowerBoundType() != BoundType.CLOSED) {
                z = false;
            }
            return navigableMap.tailMap(lowerEndpoint2, z);
        } else if (!range.hasUpperBound()) {
            return (NavigableMap) Preconditions.checkNotNull(navigableMap);
        } else {
            K upperEndpoint2 = range.upperEndpoint();
            if (range.upperBoundType() != BoundType.CLOSED) {
                z = false;
            }
            return navigableMap.headMap(upperEndpoint2, z);
        }
    }

    public static <K, V> BiMap<K, V> synchronizedBiMap(BiMap<K, V> biMap) {
        return Synchronized.a(biMap, (Object) null);
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> synchronizedNavigableMap(NavigableMap<K, V> navigableMap) {
        return Synchronized.a(navigableMap);
    }

    public static <K, V> ImmutableMap<K, V> toMap(Iterable<K> iterable, Function<? super K, V> function) {
        return toMap(iterable.iterator(), function);
    }

    public static <K, V> ImmutableMap<K, V> toMap(Iterator<K> it, Function<? super K, V> function) {
        Preconditions.checkNotNull(function);
        LinkedHashMap newLinkedHashMap = newLinkedHashMap();
        while (it.hasNext()) {
            K next = it.next();
            newLinkedHashMap.put(next, function.apply(next));
        }
        return ImmutableMap.copyOf(newLinkedHashMap);
    }

    public static <K, V1, V2> Map<K, V2> transformEntries(Map<K, V1> map, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        return new TransformedEntriesMap(map, entryTransformer);
    }

    @GwtIncompatible
    public static <K, V1, V2> NavigableMap<K, V2> transformEntries(NavigableMap<K, V1> navigableMap, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        return new TransformedEntriesNavigableMap(navigableMap, entryTransformer);
    }

    public static <K, V1, V2> SortedMap<K, V2> transformEntries(SortedMap<K, V1> sortedMap, EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        return new TransformedEntriesSortedMap(sortedMap, entryTransformer);
    }

    public static <K, V1, V2> Map<K, V2> transformValues(Map<K, V1> map, Function<? super V1, V2> function) {
        return transformEntries(map, a(function));
    }

    @GwtIncompatible
    public static <K, V1, V2> NavigableMap<K, V2> transformValues(NavigableMap<K, V1> navigableMap, Function<? super V1, V2> function) {
        return transformEntries(navigableMap, a(function));
    }

    public static <K, V1, V2> SortedMap<K, V2> transformValues(SortedMap<K, V1> sortedMap, Function<? super V1, V2> function) {
        return transformEntries(sortedMap, a(function));
    }

    @CanIgnoreReturnValue
    public static <K, V> ImmutableMap<K, V> uniqueIndex(Iterable<V> iterable, Function<? super V, K> function) {
        return uniqueIndex(iterable.iterator(), function);
    }

    @CanIgnoreReturnValue
    public static <K, V> ImmutableMap<K, V> uniqueIndex(Iterator<V> it, Function<? super V, K> function) {
        Preconditions.checkNotNull(function);
        ImmutableMap.Builder builder = ImmutableMap.builder();
        while (it.hasNext()) {
            V next = it.next();
            builder.put(function.apply(next), next);
        }
        try {
            return builder.build();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage() + ". To index multiple values under a key, use Multimaps.index.");
        }
    }

    public static <K, V> BiMap<K, V> unmodifiableBiMap(BiMap<? extends K, ? extends V> biMap) {
        return new UnmodifiableBiMap(biMap, (BiMap) null);
    }

    /* access modifiers changed from: private */
    public static <K, V> Map<K, V> unmodifiableMap(Map<K, ? extends V> map) {
        return map instanceof SortedMap ? Collections.unmodifiableSortedMap((SortedMap) map) : Collections.unmodifiableMap(map);
    }

    @GwtIncompatible
    public static <K, V> NavigableMap<K, V> unmodifiableNavigableMap(NavigableMap<K, ? extends V> navigableMap) {
        Preconditions.checkNotNull(navigableMap);
        return navigableMap instanceof UnmodifiableNavigableMap ? navigableMap : new UnmodifiableNavigableMap(navigableMap);
    }

    /* access modifiers changed from: private */
    @Nullable
    public static <K, V> Map.Entry<K, V> unmodifiableOrNull(@Nullable Map.Entry<K, ? extends V> entry) {
        if (entry == null) {
            return null;
        }
        return a(entry);
    }
}

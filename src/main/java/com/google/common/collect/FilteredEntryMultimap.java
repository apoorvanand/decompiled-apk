package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
class FilteredEntryMultimap<K, V> extends AbstractMultimap<K, V> implements FilteredMultimap<K, V> {
    final Multimap<K, V> a;
    final Predicate<? super Map.Entry<K, V>> b;

    class AsMap extends Maps.ViewCachingAbstractMap<K, Collection<V>> {
        AsMap() {
        }

        /* access modifiers changed from: package-private */
        public Collection<Collection<V>> a() {
            return new Maps.Values<K, Collection<V>>() {
                public boolean remove(@Nullable Object obj) {
                    if (!(obj instanceof Collection)) {
                        return false;
                    }
                    Collection collection = (Collection) obj;
                    Iterator<Map.Entry<K, Collection<V>>> it = FilteredEntryMultimap.this.a.asMap().entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry next = it.next();
                        Collection a2 = FilteredEntryMultimap.a((Collection) next.getValue(), new ValuePredicate(next.getKey()));
                        if (!a2.isEmpty() && collection.equals(a2)) {
                            if (a2.size() == ((Collection) next.getValue()).size()) {
                                it.remove();
                                return true;
                            }
                            a2.clear();
                            return true;
                        }
                    }
                    return false;
                }

                public boolean removeAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.a(Maps.b(Predicates.in(collection)));
                }

                public boolean retainAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.a(Maps.b(Predicates.not(Predicates.in(collection))));
                }
            };
        }

        public void clear() {
            FilteredEntryMultimap.this.clear();
        }

        public boolean containsKey(@Nullable Object obj) {
            return get(obj) != null;
        }

        /* access modifiers changed from: package-private */
        public Set<Map.Entry<K, Collection<V>>> createEntrySet() {
            return new Maps.EntrySet<K, Collection<V>>() {
                /* access modifiers changed from: package-private */
                public Map<K, Collection<V>> a() {
                    return AsMap.this;
                }

                public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                    return new AbstractIterator<Map.Entry<K, Collection<V>>>() {
                        final Iterator<Map.Entry<K, Collection<V>>> a = FilteredEntryMultimap.this.a.asMap().entrySet().iterator();

                        /* access modifiers changed from: protected */
                        /* renamed from: b */
                        public Map.Entry<K, Collection<V>> computeNext() {
                            while (this.a.hasNext()) {
                                Map.Entry next = this.a.next();
                                Object key = next.getKey();
                                Collection a2 = FilteredEntryMultimap.a((Collection) next.getValue(), new ValuePredicate(key));
                                if (!a2.isEmpty()) {
                                    return Maps.immutableEntry(key, a2);
                                }
                            }
                            return (Map.Entry) a();
                        }
                    };
                }

                public boolean removeAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.a(Predicates.in(collection));
                }

                public boolean retainAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.a(Predicates.not(Predicates.in(collection)));
                }

                public int size() {
                    return Iterators.size(iterator());
                }
            };
        }

        /* access modifiers changed from: package-private */
        public Set<K> createKeySet() {
            return new Maps.KeySet<K, Collection<V>>() {
                public boolean remove(@Nullable Object obj) {
                    return AsMap.this.remove(obj) != null;
                }

                public boolean removeAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.a(Maps.a(Predicates.in(collection)));
                }

                public boolean retainAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.a(Maps.a(Predicates.not(Predicates.in(collection))));
                }
            };
        }

        public Collection<V> get(@Nullable Object obj) {
            Collection collection = FilteredEntryMultimap.this.a.asMap().get(obj);
            if (collection == null) {
                return null;
            }
            Collection<V> a2 = FilteredEntryMultimap.a(collection, new ValuePredicate(obj));
            if (a2.isEmpty()) {
                return null;
            }
            return a2;
        }

        public Collection<V> remove(@Nullable Object obj) {
            Collection collection = FilteredEntryMultimap.this.a.asMap().get(obj);
            if (collection == null) {
                return null;
            }
            ArrayList newArrayList = Lists.newArrayList();
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (FilteredEntryMultimap.this.satisfies(obj, next)) {
                    it.remove();
                    newArrayList.add(next);
                }
            }
            if (newArrayList.isEmpty()) {
                return null;
            }
            return FilteredEntryMultimap.this.a instanceof SetMultimap ? Collections.unmodifiableSet(Sets.newLinkedHashSet(newArrayList)) : Collections.unmodifiableList(newArrayList);
        }
    }

    class Keys extends Multimaps.Keys<K, V> {
        Keys() {
            super(FilteredEntryMultimap.this);
        }

        public Set<Multiset.Entry<K>> entrySet() {
            return new Multisets.EntrySet<K>() {
                private boolean removeEntriesIf(final Predicate<? super Multiset.Entry<K>> predicate) {
                    return FilteredEntryMultimap.this.a(new Predicate<Map.Entry<K, Collection<V>>>() {
                        public boolean apply(Map.Entry<K, Collection<V>> entry) {
                            return predicate.apply(Multisets.immutableEntry(entry.getKey(), entry.getValue().size()));
                        }
                    });
                }

                /* access modifiers changed from: package-private */
                public Multiset<K> a() {
                    return Keys.this;
                }

                public Iterator<Multiset.Entry<K>> iterator() {
                    return Keys.this.a();
                }

                public boolean removeAll(Collection<?> collection) {
                    return removeEntriesIf(Predicates.in(collection));
                }

                public boolean retainAll(Collection<?> collection) {
                    return removeEntriesIf(Predicates.not(Predicates.in(collection)));
                }

                public int size() {
                    return FilteredEntryMultimap.this.keySet().size();
                }
            };
        }

        public int remove(@Nullable Object obj, int i) {
            CollectPreconditions.a(i, "occurrences");
            if (i == 0) {
                return count(obj);
            }
            Collection collection = FilteredEntryMultimap.this.a.asMap().get(obj);
            int i2 = 0;
            if (collection == null) {
                return 0;
            }
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                if (FilteredEntryMultimap.this.satisfies(obj, it.next()) && (i2 = i2 + 1) <= i) {
                    it.remove();
                }
            }
            return i2;
        }
    }

    final class ValuePredicate implements Predicate<V> {
        private final K key;

        ValuePredicate(K k) {
            this.key = k;
        }

        public boolean apply(@Nullable V v) {
            return FilteredEntryMultimap.this.satisfies(this.key, v);
        }
    }

    FilteredEntryMultimap(Multimap<K, V> multimap, Predicate<? super Map.Entry<K, V>> predicate) {
        this.a = (Multimap) Preconditions.checkNotNull(multimap);
        this.b = (Predicate) Preconditions.checkNotNull(predicate);
    }

    static <E> Collection<E> a(Collection<E> collection, Predicate<? super E> predicate) {
        return collection instanceof Set ? Sets.filter((Set) collection, predicate) : Collections2.filter(collection, predicate);
    }

    /* access modifiers changed from: private */
    public boolean satisfies(K k, V v) {
        return this.b.apply(Maps.immutableEntry(k, v));
    }

    /* access modifiers changed from: package-private */
    public Collection<V> a() {
        return this.a instanceof SetMultimap ? Collections.emptySet() : Collections.emptyList();
    }

    /* access modifiers changed from: package-private */
    public boolean a(Predicate<? super Map.Entry<K, Collection<V>>> predicate) {
        Iterator<Map.Entry<K, Collection<V>>> it = this.a.asMap().entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Map.Entry next = it.next();
            Object key = next.getKey();
            Collection a2 = a((Collection) next.getValue(), new ValuePredicate(key));
            if (!a2.isEmpty() && predicate.apply(Maps.immutableEntry(key, a2))) {
                if (a2.size() == ((Collection) next.getValue()).size()) {
                    it.remove();
                } else {
                    a2.clear();
                }
                z = true;
            }
        }
        return z;
    }

    public void clear() {
        entries().clear();
    }

    public boolean containsKey(@Nullable Object obj) {
        return asMap().get(obj) != null;
    }

    public Predicate<? super Map.Entry<K, V>> entryPredicate() {
        return this.b;
    }

    public Collection<V> get(K k) {
        return a(this.a.get(k), new ValuePredicate(k));
    }

    /* access modifiers changed from: package-private */
    public Iterator<Map.Entry<K, V>> h() {
        throw new AssertionError("should never be called");
    }

    /* access modifiers changed from: package-private */
    public Map<K, Collection<V>> i() {
        return new AsMap();
    }

    /* access modifiers changed from: package-private */
    public Collection<Map.Entry<K, V>> j() {
        return a(this.a.entries(), this.b);
    }

    /* access modifiers changed from: package-private */
    public Multiset<K> k() {
        return new Keys();
    }

    public Set<K> keySet() {
        return asMap().keySet();
    }

    /* access modifiers changed from: package-private */
    public Collection<V> l() {
        return new FilteredMultimapValues(this);
    }

    public Collection<V> removeAll(@Nullable Object obj) {
        return (Collection) MoreObjects.firstNonNull(asMap().remove(obj), a());
    }

    public int size() {
        return entries().size();
    }

    public Multimap<K, V> unfiltered() {
        return this.a;
    }
}

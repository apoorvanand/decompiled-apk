package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
class FilteredKeyMultimap<K, V> extends AbstractMultimap<K, V> implements FilteredMultimap<K, V> {
    final Multimap<K, V> a;
    final Predicate<? super K> b;

    static class AddRejectingList<K, V> extends ForwardingList<V> {
        final K a;

        AddRejectingList(K k) {
            this.a = k;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public List<V> delegate() {
            return Collections.emptyList();
        }

        public void add(int i, V v) {
            Preconditions.checkPositionIndex(i, 0);
            throw new IllegalArgumentException("Key does not satisfy predicate: " + this.a);
        }

        public boolean add(V v) {
            add(0, v);
            return true;
        }

        @CanIgnoreReturnValue
        public boolean addAll(int i, Collection<? extends V> collection) {
            Preconditions.checkNotNull(collection);
            Preconditions.checkPositionIndex(i, 0);
            throw new IllegalArgumentException("Key does not satisfy predicate: " + this.a);
        }

        public boolean addAll(Collection<? extends V> collection) {
            addAll(0, collection);
            return true;
        }
    }

    static class AddRejectingSet<K, V> extends ForwardingSet<V> {
        final K a;

        AddRejectingSet(K k) {
            this.a = k;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Set<V> delegate() {
            return Collections.emptySet();
        }

        public boolean add(V v) {
            throw new IllegalArgumentException("Key does not satisfy predicate: " + this.a);
        }

        public boolean addAll(Collection<? extends V> collection) {
            Preconditions.checkNotNull(collection);
            throw new IllegalArgumentException("Key does not satisfy predicate: " + this.a);
        }
    }

    class Entries extends ForwardingCollection<Map.Entry<K, V>> {
        Entries() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public Collection<Map.Entry<K, V>> delegate() {
            return Collections2.filter(FilteredKeyMultimap.this.a.entries(), FilteredKeyMultimap.this.entryPredicate());
        }

        public boolean remove(@Nullable Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (!FilteredKeyMultimap.this.a.containsKey(entry.getKey()) || !FilteredKeyMultimap.this.b.apply(entry.getKey())) {
                return false;
            }
            return FilteredKeyMultimap.this.a.remove(entry.getKey(), entry.getValue());
        }
    }

    FilteredKeyMultimap(Multimap<K, V> multimap, Predicate<? super K> predicate) {
        this.a = (Multimap) Preconditions.checkNotNull(multimap);
        this.b = (Predicate) Preconditions.checkNotNull(predicate);
    }

    /* access modifiers changed from: package-private */
    public Collection<V> a() {
        return this.a instanceof SetMultimap ? ImmutableSet.of() : ImmutableList.of();
    }

    public void clear() {
        keySet().clear();
    }

    public boolean containsKey(@Nullable Object obj) {
        if (this.a.containsKey(obj)) {
            return this.b.apply(obj);
        }
        return false;
    }

    public Predicate<? super Map.Entry<K, V>> entryPredicate() {
        return Maps.a(this.b);
    }

    /* access modifiers changed from: package-private */
    public Set<K> f() {
        return Sets.filter(this.a.keySet(), this.b);
    }

    public Collection<V> get(K k) {
        return this.b.apply(k) ? this.a.get(k) : this.a instanceof SetMultimap ? new AddRejectingSet(k) : new AddRejectingList(k);
    }

    /* access modifiers changed from: package-private */
    public Iterator<Map.Entry<K, V>> h() {
        throw new AssertionError("should never be called");
    }

    /* access modifiers changed from: package-private */
    public Map<K, Collection<V>> i() {
        return Maps.filterKeys(this.a.asMap(), this.b);
    }

    /* access modifiers changed from: package-private */
    public Collection<Map.Entry<K, V>> j() {
        return new Entries();
    }

    /* access modifiers changed from: package-private */
    public Multiset<K> k() {
        return Multisets.filter(this.a.keys(), this.b);
    }

    /* access modifiers changed from: package-private */
    public Collection<V> l() {
        return new FilteredMultimapValues(this);
    }

    public Collection<V> removeAll(Object obj) {
        return containsKey(obj) ? this.a.removeAll(obj) : a();
    }

    public int size() {
        int i = 0;
        for (Collection size : asMap().values()) {
            i += size.size();
        }
        return i;
    }

    public Multimap<K, V> unfiltered() {
        return this.a;
    }
}

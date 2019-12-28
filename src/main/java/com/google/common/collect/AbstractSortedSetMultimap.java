package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.SortedSet;
import javax.annotation.Nullable;

@GwtCompatible
abstract class AbstractSortedSetMultimap<K, V> extends AbstractSetMultimap<K, V> implements SortedSetMultimap<K, V> {
    private static final long serialVersionUID = 430848587173315748L;

    protected AbstractSortedSetMultimap(Map<K, Collection<V>> map) {
        super(map);
    }

    public Map<K, Collection<V>> asMap() {
        return super.asMap();
    }

    public SortedSet<V> get(@Nullable K k) {
        return (SortedSet) super.get((Object) k);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: n */
    public abstract SortedSet<V> c();

    /* access modifiers changed from: package-private */
    /* renamed from: o */
    public SortedSet<V> d() {
        return valueComparator() == null ? Collections.unmodifiableSortedSet(c()) : ImmutableSortedSet.a(valueComparator());
    }

    @CanIgnoreReturnValue
    public SortedSet<V> removeAll(@Nullable Object obj) {
        return (SortedSet) super.removeAll(obj);
    }

    @CanIgnoreReturnValue
    public SortedSet<V> replaceValues(@Nullable K k, Iterable<? extends V> iterable) {
        return (SortedSet) super.replaceValues((Object) k, (Iterable) iterable);
    }

    public Collection<V> values() {
        return super.values();
    }
}

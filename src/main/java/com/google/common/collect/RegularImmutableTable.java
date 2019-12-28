package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Table;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import javax.annotation.Nullable;

@GwtCompatible
abstract class RegularImmutableTable<R, C, V> extends ImmutableTable<R, C, V> {

    private final class CellSet extends ImmutableSet.Indexed<Table.Cell<R, C, V>> {
        private CellSet() {
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return false;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public Table.Cell<R, C, V> a(int i) {
            return RegularImmutableTable.this.a(i);
        }

        public boolean contains(@Nullable Object obj) {
            if (!(obj instanceof Table.Cell)) {
                return false;
            }
            Table.Cell cell = (Table.Cell) obj;
            Object obj2 = RegularImmutableTable.this.get(cell.getRowKey(), cell.getColumnKey());
            return obj2 != null && obj2.equals(cell.getValue());
        }

        public int size() {
            return RegularImmutableTable.this.size();
        }
    }

    private final class Values extends ImmutableList<V> {
        private Values() {
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }

        public V get(int i) {
            return RegularImmutableTable.this.b(i);
        }

        public int size() {
            return RegularImmutableTable.this.size();
        }
    }

    RegularImmutableTable() {
    }

    static <R, C, V> RegularImmutableTable<R, C, V> a(ImmutableList<Table.Cell<R, C, V>> immutableList, ImmutableSet<R> immutableSet, ImmutableSet<C> immutableSet2) {
        return ((long) immutableList.size()) > (((long) immutableSet.size()) * ((long) immutableSet2.size())) / 2 ? new DenseImmutableTable(immutableList, immutableSet, immutableSet2) : new SparseImmutableTable(immutableList, immutableSet, immutableSet2);
    }

    static <R, C, V> RegularImmutableTable<R, C, V> a(Iterable<Table.Cell<R, C, V>> iterable) {
        return forCellsInternal(iterable, (Comparator) null, (Comparator) null);
    }

    static <R, C, V> RegularImmutableTable<R, C, V> a(List<Table.Cell<R, C, V>> list, @Nullable final Comparator<? super R> comparator, @Nullable final Comparator<? super C> comparator2) {
        Preconditions.checkNotNull(list);
        if (!(comparator == null && comparator2 == null)) {
            Collections.sort(list, new Comparator<Table.Cell<R, C, V>>() {
                public int compare(Table.Cell<R, C, V> cell, Table.Cell<R, C, V> cell2) {
                    Comparator comparator = comparator;
                    int compare = comparator == null ? 0 : comparator.compare(cell.getRowKey(), cell2.getRowKey());
                    if (compare != 0) {
                        return compare;
                    }
                    Comparator comparator2 = comparator2;
                    if (comparator2 == null) {
                        return 0;
                    }
                    return comparator2.compare(cell.getColumnKey(), cell2.getColumnKey());
                }
            });
        }
        return forCellsInternal(list, comparator, comparator2);
    }

    private static final <R, C, V> RegularImmutableTable<R, C, V> forCellsInternal(Iterable<Table.Cell<R, C, V>> iterable, @Nullable Comparator<? super R> comparator, @Nullable Comparator<? super C> comparator2) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        ImmutableList<E> copyOf = ImmutableList.copyOf(iterable);
        for (Table.Cell next : iterable) {
            linkedHashSet.add(next.getRowKey());
            linkedHashSet2.add(next.getColumnKey());
        }
        return a(copyOf, comparator == null ? ImmutableSet.copyOf(linkedHashSet) : ImmutableSet.copyOf(Ordering.from(comparator).immutableSortedCopy(linkedHashSet)), comparator2 == null ? ImmutableSet.copyOf(linkedHashSet2) : ImmutableSet.copyOf(Ordering.from(comparator2).immutableSortedCopy(linkedHashSet2)));
    }

    /* access modifiers changed from: package-private */
    public abstract Table.Cell<R, C, V> a(int i);

    /* access modifiers changed from: package-private */
    public abstract V b(int i);

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public final ImmutableSet<Table.Cell<R, C, V>> a() {
        return isEmpty() ? ImmutableSet.of() : new CellSet();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public final ImmutableCollection<V> c() {
        return isEmpty() ? ImmutableList.of() : new Values();
    }
}

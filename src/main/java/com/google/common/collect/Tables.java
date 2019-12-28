package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import javax.annotation.Nullable;

@GwtCompatible
public final class Tables {
    private static final Function<? extends Map<?, ?>, ? extends Map<?, ?>> UNMODIFIABLE_WRAPPER = new Function<Map<Object, Object>, Map<Object, Object>>() {
        public Map<Object, Object> apply(Map<Object, Object> map) {
            return Collections.unmodifiableMap(map);
        }
    };

    static abstract class AbstractCell<R, C, V> implements Table.Cell<R, C, V> {
        AbstractCell() {
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Table.Cell)) {
                return false;
            }
            Table.Cell cell = (Table.Cell) obj;
            return Objects.equal(getRowKey(), cell.getRowKey()) && Objects.equal(getColumnKey(), cell.getColumnKey()) && Objects.equal(getValue(), cell.getValue());
        }

        public int hashCode() {
            return Objects.hashCode(getRowKey(), getColumnKey(), getValue());
        }

        public String toString() {
            return "(" + getRowKey() + "," + getColumnKey() + ")=" + getValue();
        }
    }

    static final class ImmutableCell<R, C, V> extends AbstractCell<R, C, V> implements Serializable {
        private static final long serialVersionUID = 0;
        private final C columnKey;
        private final R rowKey;
        private final V value;

        ImmutableCell(@Nullable R r, @Nullable C c, @Nullable V v) {
            this.rowKey = r;
            this.columnKey = c;
            this.value = v;
        }

        public C getColumnKey() {
            return this.columnKey;
        }

        public R getRowKey() {
            return this.rowKey;
        }

        public V getValue() {
            return this.value;
        }
    }

    private static class TransformedTable<R, C, V1, V2> extends AbstractTable<R, C, V2> {
        final Table<R, C, V1> a;
        final Function<? super V1, V2> b;

        TransformedTable(Table<R, C, V1> table, Function<? super V1, V2> function) {
            this.a = (Table) Preconditions.checkNotNull(table);
            this.b = (Function) Preconditions.checkNotNull(function);
        }

        /* access modifiers changed from: package-private */
        public Iterator<Table.Cell<R, C, V2>> b() {
            return Iterators.transform(this.a.cellSet().iterator(), e());
        }

        /* access modifiers changed from: package-private */
        public Collection<V2> c() {
            return Collections2.transform(this.a.values(), this.b);
        }

        public void clear() {
            this.a.clear();
        }

        public Map<R, V2> column(C c) {
            return Maps.transformValues(this.a.column(c), this.b);
        }

        public Set<C> columnKeySet() {
            return this.a.columnKeySet();
        }

        public Map<C, Map<R, V2>> columnMap() {
            return Maps.transformValues(this.a.columnMap(), new Function<Map<R, V1>, Map<R, V2>>() {
                public Map<R, V2> apply(Map<R, V1> map) {
                    return Maps.transformValues(map, TransformedTable.this.b);
                }
            });
        }

        public boolean contains(Object obj, Object obj2) {
            return this.a.contains(obj, obj2);
        }

        /* access modifiers changed from: package-private */
        public Function<Table.Cell<R, C, V1>, Table.Cell<R, C, V2>> e() {
            return new Function<Table.Cell<R, C, V1>, Table.Cell<R, C, V2>>() {
                public Table.Cell<R, C, V2> apply(Table.Cell<R, C, V1> cell) {
                    return Tables.immutableCell(cell.getRowKey(), cell.getColumnKey(), TransformedTable.this.b.apply(cell.getValue()));
                }
            };
        }

        public V2 get(Object obj, Object obj2) {
            if (contains(obj, obj2)) {
                return this.b.apply(this.a.get(obj, obj2));
            }
            return null;
        }

        public V2 put(R r, C c, V2 v2) {
            throw new UnsupportedOperationException();
        }

        public void putAll(Table<? extends R, ? extends C, ? extends V2> table) {
            throw new UnsupportedOperationException();
        }

        public V2 remove(Object obj, Object obj2) {
            if (contains(obj, obj2)) {
                return this.b.apply(this.a.remove(obj, obj2));
            }
            return null;
        }

        public Map<C, V2> row(R r) {
            return Maps.transformValues(this.a.row(r), this.b);
        }

        public Set<R> rowKeySet() {
            return this.a.rowKeySet();
        }

        public Map<R, Map<C, V2>> rowMap() {
            return Maps.transformValues(this.a.rowMap(), new Function<Map<C, V1>, Map<C, V2>>() {
                public Map<C, V2> apply(Map<C, V1> map) {
                    return Maps.transformValues(map, TransformedTable.this.b);
                }
            });
        }

        public int size() {
            return this.a.size();
        }
    }

    private static class TransposeTable<C, R, V> extends AbstractTable<C, R, V> {
        private static final Function<Table.Cell<?, ?, ?>, Table.Cell<?, ?, ?>> TRANSPOSE_CELL = new Function<Table.Cell<?, ?, ?>, Table.Cell<?, ?, ?>>() {
            public Table.Cell<?, ?, ?> apply(Table.Cell<?, ?, ?> cell) {
                return Tables.immutableCell(cell.getColumnKey(), cell.getRowKey(), cell.getValue());
            }
        };
        final Table<R, C, V> a;

        TransposeTable(Table<R, C, V> table) {
            this.a = (Table) Preconditions.checkNotNull(table);
        }

        /* access modifiers changed from: package-private */
        public Iterator<Table.Cell<C, R, V>> b() {
            return Iterators.transform(this.a.cellSet().iterator(), TRANSPOSE_CELL);
        }

        public void clear() {
            this.a.clear();
        }

        public Map<C, V> column(R r) {
            return this.a.row(r);
        }

        public Set<R> columnKeySet() {
            return this.a.rowKeySet();
        }

        public Map<R, Map<C, V>> columnMap() {
            return this.a.rowMap();
        }

        public boolean contains(@Nullable Object obj, @Nullable Object obj2) {
            return this.a.contains(obj2, obj);
        }

        public boolean containsColumn(@Nullable Object obj) {
            return this.a.containsRow(obj);
        }

        public boolean containsRow(@Nullable Object obj) {
            return this.a.containsColumn(obj);
        }

        public boolean containsValue(@Nullable Object obj) {
            return this.a.containsValue(obj);
        }

        public V get(@Nullable Object obj, @Nullable Object obj2) {
            return this.a.get(obj2, obj);
        }

        public V put(C c, R r, V v) {
            return this.a.put(r, c, v);
        }

        public void putAll(Table<? extends C, ? extends R, ? extends V> table) {
            this.a.putAll(Tables.transpose(table));
        }

        public V remove(@Nullable Object obj, @Nullable Object obj2) {
            return this.a.remove(obj2, obj);
        }

        public Map<R, V> row(C c) {
            return this.a.column(c);
        }

        public Set<C> rowKeySet() {
            return this.a.columnKeySet();
        }

        public Map<C, Map<R, V>> rowMap() {
            return this.a.columnMap();
        }

        public int size() {
            return this.a.size();
        }

        public Collection<V> values() {
            return this.a.values();
        }
    }

    static final class UnmodifiableRowSortedMap<R, C, V> extends UnmodifiableTable<R, C, V> implements RowSortedTable<R, C, V> {
        private static final long serialVersionUID = 0;

        public UnmodifiableRowSortedMap(RowSortedTable<R, ? extends C, ? extends V> rowSortedTable) {
            super(rowSortedTable);
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public RowSortedTable<R, C, V> delegate() {
            return (RowSortedTable) super.delegate();
        }

        public SortedSet<R> rowKeySet() {
            return Collections.unmodifiableSortedSet(delegate().rowKeySet());
        }

        public SortedMap<R, Map<C, V>> rowMap() {
            return Collections.unmodifiableSortedMap(Maps.transformValues(delegate().rowMap(), Tables.unmodifiableWrapper()));
        }
    }

    private static class UnmodifiableTable<R, C, V> extends ForwardingTable<R, C, V> implements Serializable {
        private static final long serialVersionUID = 0;
        final Table<? extends R, ? extends C, ? extends V> a;

        UnmodifiableTable(Table<? extends R, ? extends C, ? extends V> table) {
            this.a = (Table) Preconditions.checkNotNull(table);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Table<R, C, V> delegate() {
            return this.a;
        }

        public Set<Table.Cell<R, C, V>> cellSet() {
            return Collections.unmodifiableSet(super.cellSet());
        }

        public void clear() {
            throw new UnsupportedOperationException();
        }

        public Map<R, V> column(@Nullable C c) {
            return Collections.unmodifiableMap(super.column(c));
        }

        public Set<C> columnKeySet() {
            return Collections.unmodifiableSet(super.columnKeySet());
        }

        public Map<C, Map<R, V>> columnMap() {
            return Collections.unmodifiableMap(Maps.transformValues(super.columnMap(), Tables.unmodifiableWrapper()));
        }

        public V put(@Nullable R r, @Nullable C c, @Nullable V v) {
            throw new UnsupportedOperationException();
        }

        public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
            throw new UnsupportedOperationException();
        }

        public V remove(@Nullable Object obj, @Nullable Object obj2) {
            throw new UnsupportedOperationException();
        }

        public Map<C, V> row(@Nullable R r) {
            return Collections.unmodifiableMap(super.row(r));
        }

        public Set<R> rowKeySet() {
            return Collections.unmodifiableSet(super.rowKeySet());
        }

        public Map<R, Map<C, V>> rowMap() {
            return Collections.unmodifiableMap(Maps.transformValues(super.rowMap(), Tables.unmodifiableWrapper()));
        }

        public Collection<V> values() {
            return Collections.unmodifiableCollection(super.values());
        }
    }

    private Tables() {
    }

    static boolean a(Table<?, ?, ?> table, @Nullable Object obj) {
        if (obj == table) {
            return true;
        }
        if (obj instanceof Table) {
            return table.cellSet().equals(((Table) obj).cellSet());
        }
        return false;
    }

    public static <R, C, V> Table.Cell<R, C, V> immutableCell(@Nullable R r, @Nullable C c, @Nullable V v) {
        return new ImmutableCell(r, c, v);
    }

    @Beta
    public static <R, C, V> Table<R, C, V> newCustomTable(Map<R, Map<C, V>> map, Supplier<? extends Map<C, V>> supplier) {
        Preconditions.checkArgument(map.isEmpty());
        Preconditions.checkNotNull(supplier);
        return new StandardTable(map, supplier);
    }

    @Beta
    public static <R, C, V1, V2> Table<R, C, V2> transformValues(Table<R, C, V1> table, Function<? super V1, V2> function) {
        return new TransformedTable(table, function);
    }

    public static <R, C, V> Table<C, R, V> transpose(Table<R, C, V> table) {
        return table instanceof TransposeTable ? ((TransposeTable) table).a : new TransposeTable(table);
    }

    @Beta
    public static <R, C, V> RowSortedTable<R, C, V> unmodifiableRowSortedTable(RowSortedTable<R, ? extends C, ? extends V> rowSortedTable) {
        return new UnmodifiableRowSortedMap(rowSortedTable);
    }

    public static <R, C, V> Table<R, C, V> unmodifiableTable(Table<? extends R, ? extends C, ? extends V> table) {
        return new UnmodifiableTable(table);
    }

    /* access modifiers changed from: private */
    public static <K, V> Function<Map<K, V>, Map<K, V>> unmodifiableWrapper() {
        return UNMODIFIABLE_WRAPPER;
    }
}

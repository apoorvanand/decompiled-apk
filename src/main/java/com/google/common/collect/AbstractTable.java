package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Table;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
abstract class AbstractTable<R, C, V> implements Table<R, C, V> {
    private transient Set<Table.Cell<R, C, V>> cellSet;
    private transient Collection<V> values;

    class CellSet extends AbstractSet<Table.Cell<R, C, V>> {
        CellSet() {
        }

        public void clear() {
            AbstractTable.this.clear();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Table.Cell)) {
                return false;
            }
            Table.Cell cell = (Table.Cell) obj;
            Map map = (Map) Maps.a(AbstractTable.this.rowMap(), cell.getRowKey());
            return map != null && Collections2.a((Collection<?>) map.entrySet(), (Object) Maps.immutableEntry(cell.getColumnKey(), cell.getValue()));
        }

        public Iterator<Table.Cell<R, C, V>> iterator() {
            return AbstractTable.this.b();
        }

        public boolean remove(@Nullable Object obj) {
            if (!(obj instanceof Table.Cell)) {
                return false;
            }
            Table.Cell cell = (Table.Cell) obj;
            Map map = (Map) Maps.a(AbstractTable.this.rowMap(), cell.getRowKey());
            return map != null && Collections2.b(map.entrySet(), Maps.immutableEntry(cell.getColumnKey(), cell.getValue()));
        }

        public int size() {
            return AbstractTable.this.size();
        }
    }

    class Values extends AbstractCollection<V> {
        Values() {
        }

        public void clear() {
            AbstractTable.this.clear();
        }

        public boolean contains(Object obj) {
            return AbstractTable.this.containsValue(obj);
        }

        public Iterator<V> iterator() {
            return AbstractTable.this.d();
        }

        public int size() {
            return AbstractTable.this.size();
        }
    }

    AbstractTable() {
    }

    /* access modifiers changed from: package-private */
    public Set<Table.Cell<R, C, V>> a() {
        return new CellSet();
    }

    /* access modifiers changed from: package-private */
    public abstract Iterator<Table.Cell<R, C, V>> b();

    /* access modifiers changed from: package-private */
    public Collection<V> c() {
        return new Values();
    }

    public Set<Table.Cell<R, C, V>> cellSet() {
        Set<Table.Cell<R, C, V>> set = this.cellSet;
        if (set != null) {
            return set;
        }
        Set<Table.Cell<R, C, V>> a = a();
        this.cellSet = a;
        return a;
    }

    public void clear() {
        Iterators.b(cellSet().iterator());
    }

    public Set<C> columnKeySet() {
        return columnMap().keySet();
    }

    public boolean contains(@Nullable Object obj, @Nullable Object obj2) {
        Map map = (Map) Maps.a(rowMap(), obj);
        return map != null && Maps.b((Map<?, ?>) map, obj2);
    }

    public boolean containsColumn(@Nullable Object obj) {
        return Maps.b((Map<?, ?>) columnMap(), obj);
    }

    public boolean containsRow(@Nullable Object obj) {
        return Maps.b((Map<?, ?>) rowMap(), obj);
    }

    public boolean containsValue(@Nullable Object obj) {
        for (Map containsValue : rowMap().values()) {
            if (containsValue.containsValue(obj)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public Iterator<V> d() {
        return new TransformedIterator<Table.Cell<R, C, V>, V>(cellSet().iterator()) {
            /* access modifiers changed from: package-private */
            public V a(Table.Cell<R, C, V> cell) {
                return cell.getValue();
            }
        };
    }

    public boolean equals(@Nullable Object obj) {
        return Tables.a(this, obj);
    }

    public V get(@Nullable Object obj, @Nullable Object obj2) {
        Map map = (Map) Maps.a(rowMap(), obj);
        if (map == null) {
            return null;
        }
        return Maps.a(map, obj2);
    }

    public int hashCode() {
        return cellSet().hashCode();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @CanIgnoreReturnValue
    public V put(R r, C c, V v) {
        return row(r).put(c, v);
    }

    public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        for (Table.Cell next : table.cellSet()) {
            put(next.getRowKey(), next.getColumnKey(), next.getValue());
        }
    }

    @CanIgnoreReturnValue
    public V remove(@Nullable Object obj, @Nullable Object obj2) {
        Map map = (Map) Maps.a(rowMap(), obj);
        if (map == null) {
            return null;
        }
        return Maps.c(map, obj2);
    }

    public Set<R> rowKeySet() {
        return rowMap().keySet();
    }

    public String toString() {
        return rowMap().toString();
    }

    public Collection<V> values() {
        Collection<V> collection = this.values;
        if (collection != null) {
            return collection;
        }
        Collection<V> c = c();
        this.values = c;
        return c;
    }
}

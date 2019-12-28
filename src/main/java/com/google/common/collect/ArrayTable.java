package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
@Beta
public final class ArrayTable<R, C, V> extends AbstractTable<R, C, V> implements Serializable {
    private static final long serialVersionUID = 0;
    private final V[][] array;
    /* access modifiers changed from: private */
    public final ImmutableMap<C, Integer> columnKeyToIndex;
    /* access modifiers changed from: private */
    public final ImmutableList<C> columnList;
    private transient ArrayTable<R, C, V>.ColumnMap columnMap;
    /* access modifiers changed from: private */
    public final ImmutableMap<R, Integer> rowKeyToIndex;
    /* access modifiers changed from: private */
    public final ImmutableList<R> rowList;
    private transient ArrayTable<R, C, V>.RowMap rowMap;

    private static abstract class ArrayMap<K, V> extends Maps.IteratorBasedAbstractMap<K, V> {
        private final ImmutableMap<K, Integer> keyIndex;

        private ArrayMap(ImmutableMap<K, Integer> immutableMap) {
            this.keyIndex = immutableMap;
        }

        /* access modifiers changed from: package-private */
        public K a(int i) {
            return this.keyIndex.keySet().asList().get(i);
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public abstract V a(int i, V v);

        /* access modifiers changed from: package-private */
        public abstract String a();

        /* access modifiers changed from: package-private */
        @Nullable
        public abstract V b(int i);

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<K, V>> b() {
            return new AbstractIndexedListIterator<Map.Entry<K, V>>(size()) {
                /* access modifiers changed from: protected */
                /* renamed from: b */
                public Map.Entry<K, V> a(final int i) {
                    return new AbstractMapEntry<K, V>() {
                        public K getKey() {
                            return ArrayMap.this.a(i);
                        }

                        public V getValue() {
                            return ArrayMap.this.b(i);
                        }

                        public V setValue(V v) {
                            return ArrayMap.this.a(i, v);
                        }
                    };
                }
            };
        }

        public void clear() {
            throw new UnsupportedOperationException();
        }

        public boolean containsKey(@Nullable Object obj) {
            return this.keyIndex.containsKey(obj);
        }

        public V get(@Nullable Object obj) {
            Integer num = this.keyIndex.get(obj);
            if (num == null) {
                return null;
            }
            return b(num.intValue());
        }

        public boolean isEmpty() {
            return this.keyIndex.isEmpty();
        }

        public Set<K> keySet() {
            return this.keyIndex.keySet();
        }

        public V put(K k, V v) {
            Integer num = this.keyIndex.get(k);
            if (num != null) {
                return a(num.intValue(), v);
            }
            throw new IllegalArgumentException(a() + " " + k + " not in " + this.keyIndex.keySet());
        }

        public V remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        public int size() {
            return this.keyIndex.size();
        }
    }

    private class Column extends ArrayMap<R, V> {
        final int a;

        Column(int i) {
            super(ArrayTable.this.rowKeyToIndex);
            this.a = i;
        }

        /* access modifiers changed from: package-private */
        public V a(int i, V v) {
            return ArrayTable.this.set(i, this.a, v);
        }

        /* access modifiers changed from: package-private */
        public String a() {
            return "Row";
        }

        /* access modifiers changed from: package-private */
        public V b(int i) {
            return ArrayTable.this.at(i, this.a);
        }
    }

    private class ColumnMap extends ArrayMap<C, Map<R, V>> {
        private ColumnMap() {
            super(ArrayTable.this.columnKeyToIndex);
        }

        /* access modifiers changed from: package-private */
        public String a() {
            return "Column";
        }

        /* access modifiers changed from: package-private */
        public Map<R, V> a(int i, Map<R, V> map) {
            throw new UnsupportedOperationException();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public Map<R, V> b(int i) {
            return new Column(i);
        }

        public Map<R, V> put(C c, Map<R, V> map) {
            throw new UnsupportedOperationException();
        }
    }

    private class Row extends ArrayMap<C, V> {
        final int a;

        Row(int i) {
            super(ArrayTable.this.columnKeyToIndex);
            this.a = i;
        }

        /* access modifiers changed from: package-private */
        public V a(int i, V v) {
            return ArrayTable.this.set(this.a, i, v);
        }

        /* access modifiers changed from: package-private */
        public String a() {
            return "Column";
        }

        /* access modifiers changed from: package-private */
        public V b(int i) {
            return ArrayTable.this.at(this.a, i);
        }
    }

    private class RowMap extends ArrayMap<R, Map<C, V>> {
        private RowMap() {
            super(ArrayTable.this.rowKeyToIndex);
        }

        /* access modifiers changed from: package-private */
        public String a() {
            return "Row";
        }

        /* access modifiers changed from: package-private */
        public Map<C, V> a(int i, Map<C, V> map) {
            throw new UnsupportedOperationException();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public Map<C, V> b(int i) {
            return new Row(i);
        }

        public Map<C, V> put(R r, Map<C, V> map) {
            throw new UnsupportedOperationException();
        }
    }

    private ArrayTable(ArrayTable<R, C, V> arrayTable) {
        this.rowList = arrayTable.rowList;
        this.columnList = arrayTable.columnList;
        this.rowKeyToIndex = arrayTable.rowKeyToIndex;
        this.columnKeyToIndex = arrayTable.columnKeyToIndex;
        V[][] vArr = (Object[][]) Array.newInstance(Object.class, new int[]{this.rowList.size(), this.columnList.size()});
        this.array = vArr;
        eraseAll();
        for (int i = 0; i < this.rowList.size(); i++) {
            V[][] vArr2 = arrayTable.array;
            System.arraycopy(vArr2[i], 0, vArr[i], 0, vArr2[i].length);
        }
    }

    private ArrayTable(Table<R, C, V> table) {
        this(table.rowKeySet(), table.columnKeySet());
        putAll(table);
    }

    private ArrayTable(Iterable<? extends R> iterable, Iterable<? extends C> iterable2) {
        this.rowList = ImmutableList.copyOf(iterable);
        this.columnList = ImmutableList.copyOf(iterable2);
        Preconditions.checkArgument(!this.rowList.isEmpty());
        Preconditions.checkArgument(!this.columnList.isEmpty());
        this.rowKeyToIndex = Maps.a(this.rowList);
        this.columnKeyToIndex = Maps.a(this.columnList);
        this.array = (Object[][]) Array.newInstance(Object.class, new int[]{this.rowList.size(), this.columnList.size()});
        eraseAll();
    }

    public static <R, C, V> ArrayTable<R, C, V> create(Table<R, C, V> table) {
        return table instanceof ArrayTable ? new ArrayTable<>((ArrayTable) table) : new ArrayTable<>(table);
    }

    public static <R, C, V> ArrayTable<R, C, V> create(Iterable<? extends R> iterable, Iterable<? extends C> iterable2) {
        return new ArrayTable<>(iterable, iterable2);
    }

    public V at(int i, int i2) {
        Preconditions.checkElementIndex(i, this.rowList.size());
        Preconditions.checkElementIndex(i2, this.columnList.size());
        return this.array[i][i2];
    }

    /* access modifiers changed from: package-private */
    public Iterator<Table.Cell<R, C, V>> b() {
        return new AbstractIndexedListIterator<Table.Cell<R, C, V>>(size()) {
            /* access modifiers changed from: protected */
            /* renamed from: b */
            public Table.Cell<R, C, V> a(final int i) {
                return new Tables.AbstractCell<R, C, V>() {
                    final int a = (i / ArrayTable.this.columnList.size());
                    final int b = (i % ArrayTable.this.columnList.size());

                    public C getColumnKey() {
                        return ArrayTable.this.columnList.get(this.b);
                    }

                    public R getRowKey() {
                        return ArrayTable.this.rowList.get(this.a);
                    }

                    public V getValue() {
                        return ArrayTable.this.at(this.a, this.b);
                    }
                };
            }
        };
    }

    public Set<Table.Cell<R, C, V>> cellSet() {
        return super.cellSet();
    }

    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    public Map<R, V> column(C c) {
        Preconditions.checkNotNull(c);
        Integer num = this.columnKeyToIndex.get(c);
        return num == null ? ImmutableMap.of() : new Column(num.intValue());
    }

    public ImmutableList<C> columnKeyList() {
        return this.columnList;
    }

    public ImmutableSet<C> columnKeySet() {
        return this.columnKeyToIndex.keySet();
    }

    public Map<C, Map<R, V>> columnMap() {
        ArrayTable<R, C, V>.ColumnMap columnMap2 = this.columnMap;
        if (columnMap2 != null) {
            return columnMap2;
        }
        ArrayTable<R, C, V>.ColumnMap columnMap3 = new ColumnMap();
        this.columnMap = columnMap3;
        return columnMap3;
    }

    public boolean contains(@Nullable Object obj, @Nullable Object obj2) {
        return containsRow(obj) && containsColumn(obj2);
    }

    public boolean containsColumn(@Nullable Object obj) {
        return this.columnKeyToIndex.containsKey(obj);
    }

    public boolean containsRow(@Nullable Object obj) {
        return this.rowKeyToIndex.containsKey(obj);
    }

    public boolean containsValue(@Nullable Object obj) {
        for (V[] vArr : this.array) {
            for (V equal : r0[r3]) {
                if (Objects.equal(obj, equal)) {
                    return true;
                }
            }
        }
        return false;
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @CanIgnoreReturnValue
    public V erase(@Nullable Object obj, @Nullable Object obj2) {
        Integer num = this.rowKeyToIndex.get(obj);
        Integer num2 = this.columnKeyToIndex.get(obj2);
        if (num == null || num2 == null) {
            return null;
        }
        return set(num.intValue(), num2.intValue(), (Object) null);
    }

    public void eraseAll() {
        for (V[] fill : this.array) {
            Arrays.fill(fill, (Object) null);
        }
    }

    public V get(@Nullable Object obj, @Nullable Object obj2) {
        Integer num = this.rowKeyToIndex.get(obj);
        Integer num2 = this.columnKeyToIndex.get(obj2);
        if (num == null || num2 == null) {
            return null;
        }
        return at(num.intValue(), num2.intValue());
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public boolean isEmpty() {
        return false;
    }

    @CanIgnoreReturnValue
    public V put(R r, C c, @Nullable V v) {
        Preconditions.checkNotNull(r);
        Preconditions.checkNotNull(c);
        Integer num = this.rowKeyToIndex.get(r);
        boolean z = true;
        Preconditions.checkArgument(num != null, "Row %s not in %s", (Object) r, (Object) this.rowList);
        Integer num2 = this.columnKeyToIndex.get(c);
        if (num2 == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "Column %s not in %s", (Object) c, (Object) this.columnList);
        return set(num.intValue(), num2.intValue(), v);
    }

    public void putAll(Table<? extends R, ? extends C, ? extends V> table) {
        super.putAll(table);
    }

    @CanIgnoreReturnValue
    @Deprecated
    public V remove(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    public Map<C, V> row(R r) {
        Preconditions.checkNotNull(r);
        Integer num = this.rowKeyToIndex.get(r);
        return num == null ? ImmutableMap.of() : new Row(num.intValue());
    }

    public ImmutableList<R> rowKeyList() {
        return this.rowList;
    }

    public ImmutableSet<R> rowKeySet() {
        return this.rowKeyToIndex.keySet();
    }

    public Map<R, Map<C, V>> rowMap() {
        ArrayTable<R, C, V>.RowMap rowMap2 = this.rowMap;
        if (rowMap2 != null) {
            return rowMap2;
        }
        ArrayTable<R, C, V>.RowMap rowMap3 = new RowMap();
        this.rowMap = rowMap3;
        return rowMap3;
    }

    @CanIgnoreReturnValue
    public V set(int i, int i2, @Nullable V v) {
        Preconditions.checkElementIndex(i, this.rowList.size());
        Preconditions.checkElementIndex(i2, this.columnList.size());
        V[][] vArr = this.array;
        V v2 = vArr[i][i2];
        vArr[i][i2] = v;
        return v2;
    }

    public int size() {
        return this.rowList.size() * this.columnList.size();
    }

    @GwtIncompatible
    public V[][] toArray(Class<V> cls) {
        V[][] vArr = (Object[][]) Array.newInstance(cls, new int[]{this.rowList.size(), this.columnList.size()});
        for (int i = 0; i < this.rowList.size(); i++) {
            V[][] vArr2 = this.array;
            System.arraycopy(vArr2[i], 0, vArr[i], 0, vArr2[i].length);
        }
        return vArr;
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public Collection<V> values() {
        return super.values();
    }
}

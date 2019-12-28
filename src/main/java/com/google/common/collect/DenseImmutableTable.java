package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import java.lang.reflect.Array;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@GwtCompatible
@Immutable
final class DenseImmutableTable<R, C, V> extends RegularImmutableTable<R, C, V> {
    private final int[] cellColumnIndices;
    private final int[] cellRowIndices;
    /* access modifiers changed from: private */
    public final int[] columnCounts = new int[this.columnKeyToIndex.size()];
    /* access modifiers changed from: private */
    public final ImmutableMap<C, Integer> columnKeyToIndex;
    private final ImmutableMap<C, Map<R, V>> columnMap;
    /* access modifiers changed from: private */
    public final int[] rowCounts = new int[this.rowKeyToIndex.size()];
    /* access modifiers changed from: private */
    public final ImmutableMap<R, Integer> rowKeyToIndex;
    private final ImmutableMap<R, Map<C, V>> rowMap;
    /* access modifiers changed from: private */
    public final V[][] values;

    private final class Column extends ImmutableArrayMap<R, V> {
        private final int columnIndex;

        Column(int i) {
            super(DenseImmutableTable.this.columnCounts[i]);
            this.columnIndex = i;
        }

        /* access modifiers changed from: package-private */
        public V a(int i) {
            return DenseImmutableTable.this.values[i][this.columnIndex];
        }

        /* access modifiers changed from: package-private */
        public boolean b() {
            return true;
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap<R, Integer> e_() {
            return DenseImmutableTable.this.rowKeyToIndex;
        }
    }

    private final class ColumnMap extends ImmutableArrayMap<C, Map<R, V>> {
        private ColumnMap() {
            super(DenseImmutableTable.this.columnCounts.length);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public Map<R, V> a(int i) {
            return new Column(i);
        }

        /* access modifiers changed from: package-private */
        public boolean b() {
            return false;
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap<C, Integer> e_() {
            return DenseImmutableTable.this.columnKeyToIndex;
        }
    }

    private static abstract class ImmutableArrayMap<K, V> extends ImmutableMap.IteratorBasedImmutableMap<K, V> {
        private final int size;

        ImmutableArrayMap(int i) {
            this.size = i;
        }

        private boolean isFull() {
            return this.size == e_().size();
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public abstract V a(int i);

        /* access modifiers changed from: package-private */
        public ImmutableSet<K> c() {
            return isFull() ? e_().keySet() : super.c();
        }

        /* access modifiers changed from: package-private */
        public K c(int i) {
            return e_().keySet().asList().get(i);
        }

        /* access modifiers changed from: package-private */
        public UnmodifiableIterator<Map.Entry<K, V>> d() {
            return new AbstractIterator<Map.Entry<K, V>>() {
                private int index = -1;
                private final int maxIndex = ImmutableArrayMap.this.e_().size();

                /* access modifiers changed from: protected */
                /* renamed from: b */
                public Map.Entry<K, V> computeNext() {
                    int i = this.index;
                    while (true) {
                        this.index = i + 1;
                        int i2 = this.index;
                        if (i2 >= this.maxIndex) {
                            return (Map.Entry) a();
                        }
                        Object a2 = ImmutableArrayMap.this.a(i2);
                        if (a2 != null) {
                            return Maps.immutableEntry(ImmutableArrayMap.this.c(this.index), a2);
                        }
                        i = this.index;
                    }
                }
            };
        }

        /* access modifiers changed from: package-private */
        public abstract ImmutableMap<K, Integer> e_();

        public V get(@Nullable Object obj) {
            Integer num = (Integer) e_().get(obj);
            if (num == null) {
                return null;
            }
            return a(num.intValue());
        }

        public int size() {
            return this.size;
        }
    }

    private final class Row extends ImmutableArrayMap<C, V> {
        private final int rowIndex;

        Row(int i) {
            super(DenseImmutableTable.this.rowCounts[i]);
            this.rowIndex = i;
        }

        /* access modifiers changed from: package-private */
        public V a(int i) {
            return DenseImmutableTable.this.values[this.rowIndex][i];
        }

        /* access modifiers changed from: package-private */
        public boolean b() {
            return true;
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap<C, Integer> e_() {
            return DenseImmutableTable.this.columnKeyToIndex;
        }
    }

    private final class RowMap extends ImmutableArrayMap<R, Map<C, V>> {
        private RowMap() {
            super(DenseImmutableTable.this.rowCounts.length);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public Map<C, V> a(int i) {
            return new Row(i);
        }

        /* access modifiers changed from: package-private */
        public boolean b() {
            return false;
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap<R, Integer> e_() {
            return DenseImmutableTable.this.rowKeyToIndex;
        }
    }

    DenseImmutableTable(ImmutableList<Table.Cell<R, C, V>> immutableList, ImmutableSet<R> immutableSet, ImmutableSet<C> immutableSet2) {
        this.values = (Object[][]) Array.newInstance(Object.class, new int[]{immutableSet.size(), immutableSet2.size()});
        this.rowKeyToIndex = Maps.a(immutableSet);
        this.columnKeyToIndex = Maps.a(immutableSet2);
        int[] iArr = new int[immutableList.size()];
        int[] iArr2 = new int[immutableList.size()];
        for (int i = 0; i < immutableList.size(); i++) {
            Table.Cell cell = (Table.Cell) immutableList.get(i);
            Object rowKey = cell.getRowKey();
            Object columnKey = cell.getColumnKey();
            int intValue = this.rowKeyToIndex.get(rowKey).intValue();
            int intValue2 = this.columnKeyToIndex.get(columnKey).intValue();
            Preconditions.checkArgument(this.values[intValue][intValue2] == null, "duplicate key: (%s, %s)", rowKey, columnKey);
            this.values[intValue][intValue2] = cell.getValue();
            int[] iArr3 = this.rowCounts;
            iArr3[intValue] = iArr3[intValue] + 1;
            int[] iArr4 = this.columnCounts;
            iArr4[intValue2] = iArr4[intValue2] + 1;
            iArr[i] = intValue;
            iArr2[i] = intValue2;
        }
        this.cellRowIndices = iArr;
        this.cellColumnIndices = iArr2;
        this.rowMap = new RowMap();
        this.columnMap = new ColumnMap();
    }

    /* access modifiers changed from: package-private */
    public Table.Cell<R, C, V> a(int i) {
        int i2 = this.cellRowIndices[i];
        int i3 = this.cellColumnIndices[i];
        return a(rowKeySet().asList().get(i2), columnKeySet().asList().get(i3), this.values[i2][i3]);
    }

    /* access modifiers changed from: package-private */
    public V b(int i) {
        return this.values[this.cellRowIndices[i]][this.cellColumnIndices[i]];
    }

    public ImmutableMap<C, Map<R, V>> columnMap() {
        return this.columnMap;
    }

    /* access modifiers changed from: package-private */
    public ImmutableTable.SerializedForm e() {
        return ImmutableTable.SerializedForm.a(this, this.cellRowIndices, this.cellColumnIndices);
    }

    public V get(@Nullable Object obj, @Nullable Object obj2) {
        Integer num = this.rowKeyToIndex.get(obj);
        Integer num2 = this.columnKeyToIndex.get(obj2);
        if (num == null || num2 == null) {
            return null;
        }
        return this.values[num.intValue()][num2.intValue()];
    }

    public ImmutableMap<R, Map<C, V>> rowMap() {
        return this.rowMap;
    }

    public int size() {
        return this.cellRowIndices.length;
    }
}

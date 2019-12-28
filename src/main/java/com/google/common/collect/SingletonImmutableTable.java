package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import java.util.Map;

@GwtCompatible
class SingletonImmutableTable<R, C, V> extends ImmutableTable<R, C, V> {
    final R a;
    final C b;
    final V c;

    SingletonImmutableTable(Table.Cell<R, C, V> cell) {
        this(cell.getRowKey(), cell.getColumnKey(), cell.getValue());
    }

    SingletonImmutableTable(R r, C c2, V v) {
        this.a = Preconditions.checkNotNull(r);
        this.b = Preconditions.checkNotNull(c2);
        this.c = Preconditions.checkNotNull(v);
    }

    public ImmutableMap<R, V> column(C c2) {
        Preconditions.checkNotNull(c2);
        return containsColumn(c2) ? ImmutableMap.of(this.a, this.c) : ImmutableMap.of();
    }

    public ImmutableMap<C, Map<R, V>> columnMap() {
        return ImmutableMap.of(this.b, ImmutableMap.of(this.a, this.c));
    }

    /* access modifiers changed from: package-private */
    public ImmutableTable.SerializedForm e() {
        return ImmutableTable.SerializedForm.a(this, new int[]{0}, new int[]{0});
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public ImmutableSet<Table.Cell<R, C, V>> a() {
        return ImmutableSet.of(a(this.a, this.b, this.c));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public ImmutableCollection<V> c() {
        return ImmutableSet.of(this.c);
    }

    public ImmutableMap<R, Map<C, V>> rowMap() {
        return ImmutableMap.of(this.a, ImmutableMap.of(this.b, this.c));
    }

    public int size() {
        return 1;
    }
}

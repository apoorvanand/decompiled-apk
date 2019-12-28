package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import javax.annotation.Nullable;

@GwtCompatible(serializable = true)
public class TreeBasedTable<R, C, V> extends StandardRowSortedTable<R, C, V> {
    private static final long serialVersionUID = 0;
    private final Comparator<? super C> columnComparator;

    private static class Factory<C, V> implements Supplier<TreeMap<C, V>>, Serializable {
        private static final long serialVersionUID = 0;
        final Comparator<? super C> a;

        Factory(Comparator<? super C> comparator) {
            this.a = comparator;
        }

        public TreeMap<C, V> get() {
            return new TreeMap<>(this.a);
        }
    }

    private class TreeRow extends StandardTable<R, C, V>.Row implements SortedMap<C, V> {
        @Nullable
        final C d;
        @Nullable
        final C e;
        transient SortedMap<C, V> f;

        TreeRow(TreeBasedTable treeBasedTable, R r) {
            this(r, (Object) null, (Object) null);
        }

        TreeRow(R r, C c, @Nullable C c2) {
            super(r);
            this.d = c;
            this.e = c2;
            Preconditions.checkArgument(c == null || c2 == null || a(c, c2) <= 0);
        }

        /* access modifiers changed from: package-private */
        public int a(Object obj, Object obj2) {
            return comparator().compare(obj, obj2);
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
            r0 = r1.d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:5:0x000c, code lost:
            r0 = r1.e;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a(@javax.annotation.Nullable java.lang.Object r2) {
            /*
                r1 = this;
                if (r2 == 0) goto L_0x0018
                C r0 = r1.d
                if (r0 == 0) goto L_0x000c
                int r0 = r1.a(r0, r2)
                if (r0 > 0) goto L_0x0018
            L_0x000c:
                C r0 = r1.e
                if (r0 == 0) goto L_0x0016
                int r2 = r1.a(r0, r2)
                if (r2 <= 0) goto L_0x0018
            L_0x0016:
                r2 = 1
                goto L_0x0019
            L_0x0018:
                r2 = 0
            L_0x0019:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.TreeBasedTable.TreeRow.a(java.lang.Object):boolean");
        }

        public Comparator<? super C> comparator() {
            return TreeBasedTable.this.columnComparator();
        }

        public boolean containsKey(Object obj) {
            return a(obj) && super.containsKey(obj);
        }

        /* access modifiers changed from: package-private */
        public void d() {
            if (e() != null && this.f.isEmpty()) {
                TreeBasedTable.this.a.remove(this.a);
                this.f = null;
                this.b = null;
            }
        }

        /* access modifiers changed from: package-private */
        public SortedMap<C, V> e() {
            SortedMap<C, V> sortedMap = this.f;
            if (sortedMap == null || (sortedMap.isEmpty() && TreeBasedTable.this.a.containsKey(this.a))) {
                this.f = (SortedMap) TreeBasedTable.this.a.get(this.a);
            }
            return this.f;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: f */
        public SortedMap<C, V> a() {
            return (SortedMap) super.a();
        }

        public C firstKey() {
            if (a() != null) {
                return a().firstKey();
            }
            throw new NoSuchElementException();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: g */
        public SortedMap<C, V> c() {
            SortedMap<C, V> e2 = e();
            if (e2 == null) {
                return null;
            }
            C c = this.d;
            if (c != null) {
                e2 = e2.tailMap(c);
            }
            C c2 = this.e;
            return c2 != null ? e2.headMap(c2) : e2;
        }

        public SortedMap<C, V> headMap(C c) {
            Preconditions.checkArgument(a(Preconditions.checkNotNull(c)));
            return new TreeRow(this.a, this.d, c);
        }

        public SortedSet<C> keySet() {
            return new Maps.SortedKeySet(this);
        }

        public C lastKey() {
            if (a() != null) {
                return a().lastKey();
            }
            throw new NoSuchElementException();
        }

        public V put(C c, V v) {
            Preconditions.checkArgument(a(Preconditions.checkNotNull(c)));
            return super.put(c, v);
        }

        public SortedMap<C, V> subMap(C c, C c2) {
            Preconditions.checkArgument(a(Preconditions.checkNotNull(c)) && a(Preconditions.checkNotNull(c2)));
            return new TreeRow(this.a, c, c2);
        }

        public SortedMap<C, V> tailMap(C c) {
            Preconditions.checkArgument(a(Preconditions.checkNotNull(c)));
            return new TreeRow(this.a, c, this.e);
        }
    }

    TreeBasedTable(Comparator<? super R> comparator, Comparator<? super C> comparator2) {
        super(new TreeMap(comparator), new Factory(comparator2));
        this.columnComparator = comparator2;
    }

    public static <R extends Comparable, C extends Comparable, V> TreeBasedTable<R, C, V> create() {
        return new TreeBasedTable<>(Ordering.natural(), Ordering.natural());
    }

    public static <R, C, V> TreeBasedTable<R, C, V> create(TreeBasedTable<R, C, ? extends V> treeBasedTable) {
        TreeBasedTable<R, C, V> treeBasedTable2 = new TreeBasedTable<>(treeBasedTable.rowComparator(), treeBasedTable.columnComparator());
        treeBasedTable2.putAll(treeBasedTable);
        return treeBasedTable2;
    }

    public static <R, C, V> TreeBasedTable<R, C, V> create(Comparator<? super R> comparator, Comparator<? super C> comparator2) {
        Preconditions.checkNotNull(comparator);
        Preconditions.checkNotNull(comparator2);
        return new TreeBasedTable<>(comparator, comparator2);
    }

    public /* bridge */ /* synthetic */ Set cellSet() {
        return super.cellSet();
    }

    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    public /* bridge */ /* synthetic */ Map column(Object obj) {
        return super.column(obj);
    }

    public Comparator<? super C> columnComparator() {
        return this.columnComparator;
    }

    public /* bridge */ /* synthetic */ Set columnKeySet() {
        return super.columnKeySet();
    }

    public /* bridge */ /* synthetic */ Map columnMap() {
        return super.columnMap();
    }

    public /* bridge */ /* synthetic */ boolean contains(Object obj, Object obj2) {
        return super.contains(obj, obj2);
    }

    public /* bridge */ /* synthetic */ boolean containsColumn(Object obj) {
        return super.containsColumn(obj);
    }

    public /* bridge */ /* synthetic */ boolean containsRow(Object obj) {
        return super.containsRow(obj);
    }

    public /* bridge */ /* synthetic */ boolean containsValue(Object obj) {
        return super.containsValue(obj);
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    /* access modifiers changed from: package-private */
    public Iterator<C> g() {
        final Comparator columnComparator2 = columnComparator();
        final UnmodifiableIterator mergeSorted = Iterators.mergeSorted(Iterables.transform(this.a.values(), new Function<Map<C, V>, Iterator<C>>() {
            public Iterator<C> apply(Map<C, V> map) {
                return map.keySet().iterator();
            }
        }), columnComparator2);
        return new AbstractIterator<C>() {
            C a;

            /* access modifiers changed from: protected */
            public C computeNext() {
                boolean z;
                while (mergeSorted.hasNext()) {
                    C next = mergeSorted.next();
                    C c2 = this.a;
                    if (c2 == null || columnComparator2.compare(next, c2) != 0) {
                        z = false;
                        continue;
                    } else {
                        z = true;
                        continue;
                    }
                    if (!z) {
                        this.a = next;
                        return this.a;
                    }
                }
                this.a = null;
                return a();
            }
        };
    }

    public /* bridge */ /* synthetic */ Object get(Object obj, Object obj2) {
        return super.get(obj, obj2);
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    public /* bridge */ /* synthetic */ Object put(Object obj, Object obj2, Object obj3) {
        return super.put(obj, obj2, obj3);
    }

    public /* bridge */ /* synthetic */ void putAll(Table table) {
        super.putAll(table);
    }

    public /* bridge */ /* synthetic */ Object remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    public SortedMap<C, V> row(R r) {
        return new TreeRow(this, r);
    }

    public Comparator<? super R> rowComparator() {
        return rowKeySet().comparator();
    }

    public SortedSet<R> rowKeySet() {
        return super.rowKeySet();
    }

    public SortedMap<R, Map<C, V>> rowMap() {
        return super.rowMap();
    }

    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public /* bridge */ /* synthetic */ Collection values() {
        return super.values();
    }
}

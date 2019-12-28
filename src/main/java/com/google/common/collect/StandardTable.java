package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible
class StandardTable<R, C, V> extends AbstractTable<R, C, V> implements Serializable {
    private static final long serialVersionUID = 0;
    @GwtTransient
    final Map<R, Map<C, V>> a;
    @GwtTransient
    final Supplier<? extends Map<C, V>> b;
    private transient Set<C> columnKeySet;
    private transient StandardTable<R, C, V>.ColumnMap columnMap;
    private transient Map<R, Map<C, V>> rowMap;

    private class CellIterator implements Iterator<Table.Cell<R, C, V>> {
        final Iterator<Map.Entry<R, Map<C, V>>> a;
        Map.Entry<R, Map<C, V>> b;
        Iterator<Map.Entry<C, V>> c;

        private CellIterator() {
            this.a = StandardTable.this.a.entrySet().iterator();
            this.c = Iterators.c();
        }

        public boolean hasNext() {
            return this.a.hasNext() || this.c.hasNext();
        }

        public Table.Cell<R, C, V> next() {
            if (!this.c.hasNext()) {
                this.b = this.a.next();
                this.c = this.b.getValue().entrySet().iterator();
            }
            Map.Entry next = this.c.next();
            return Tables.immutableCell(this.b.getKey(), next.getKey(), next.getValue());
        }

        public void remove() {
            this.c.remove();
            if (this.b.getValue().isEmpty()) {
                this.a.remove();
            }
        }
    }

    private class Column extends Maps.ViewCachingAbstractMap<R, V> {
        final C a;

        private class EntrySet extends Sets.ImprovedAbstractSet<Map.Entry<R, V>> {
            private EntrySet() {
            }

            public void clear() {
                Column.this.a(Predicates.alwaysTrue());
            }

            public boolean contains(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                return StandardTable.this.containsMapping(entry.getKey(), Column.this.a, entry.getValue());
            }

            public boolean isEmpty() {
                return !StandardTable.this.containsColumn(Column.this.a);
            }

            public Iterator<Map.Entry<R, V>> iterator() {
                return new EntrySetIterator();
            }

            public boolean remove(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                return StandardTable.this.removeMapping(entry.getKey(), Column.this.a, entry.getValue());
            }

            public boolean retainAll(Collection<?> collection) {
                return Column.this.a(Predicates.not(Predicates.in(collection)));
            }

            public int size() {
                int i = 0;
                for (Map<C, V> containsKey : StandardTable.this.a.values()) {
                    if (containsKey.containsKey(Column.this.a)) {
                        i++;
                    }
                }
                return i;
            }
        }

        private class EntrySetIterator extends AbstractIterator<Map.Entry<R, V>> {
            final Iterator<Map.Entry<R, Map<C, V>>> a;

            private EntrySetIterator() {
                this.a = StandardTable.this.a.entrySet().iterator();
            }

            /* access modifiers changed from: protected */
            /* renamed from: b */
            public Map.Entry<R, V> computeNext() {
                while (this.a.hasNext()) {
                    final Map.Entry next = this.a.next();
                    if (((Map) next.getValue()).containsKey(Column.this.a)) {
                        return new AbstractMapEntry<R, V>() {
                            public R getKey() {
                                return next.getKey();
                            }

                            public V getValue() {
                                return ((Map) next.getValue()).get(Column.this.a);
                            }

                            public V setValue(V v) {
                                return ((Map) next.getValue()).put(Column.this.a, Preconditions.checkNotNull(v));
                            }
                        };
                    }
                }
                return (Map.Entry) a();
            }
        }

        private class KeySet extends Maps.KeySet<R, V> {
            KeySet() {
                super(Column.this);
            }

            public boolean contains(Object obj) {
                return StandardTable.this.contains(obj, Column.this.a);
            }

            public boolean remove(Object obj) {
                return StandardTable.this.remove(obj, Column.this.a) != null;
            }

            public boolean retainAll(Collection<?> collection) {
                return Column.this.a(Maps.a(Predicates.not(Predicates.in(collection))));
            }
        }

        private class Values extends Maps.Values<R, V> {
            Values() {
                super(Column.this);
            }

            public boolean remove(Object obj) {
                return obj != null && Column.this.a(Maps.b(Predicates.equalTo(obj)));
            }

            public boolean removeAll(Collection<?> collection) {
                return Column.this.a(Maps.b(Predicates.in(collection)));
            }

            public boolean retainAll(Collection<?> collection) {
                return Column.this.a(Maps.b(Predicates.not(Predicates.in(collection))));
            }
        }

        Column(C c) {
            this.a = Preconditions.checkNotNull(c);
        }

        /* access modifiers changed from: package-private */
        public Collection<V> a() {
            return new Values();
        }

        /* access modifiers changed from: package-private */
        @CanIgnoreReturnValue
        public boolean a(Predicate<? super Map.Entry<R, V>> predicate) {
            Iterator<Map.Entry<R, Map<C, V>>> it = StandardTable.this.a.entrySet().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map.Entry next = it.next();
                Map map = (Map) next.getValue();
                Object obj = map.get(this.a);
                if (obj != null && predicate.apply(Maps.immutableEntry(next.getKey(), obj))) {
                    map.remove(this.a);
                    z = true;
                    if (map.isEmpty()) {
                        it.remove();
                    }
                }
            }
            return z;
        }

        public boolean containsKey(Object obj) {
            return StandardTable.this.contains(obj, this.a);
        }

        /* access modifiers changed from: package-private */
        public Set<Map.Entry<R, V>> createEntrySet() {
            return new EntrySet();
        }

        /* access modifiers changed from: package-private */
        public Set<R> createKeySet() {
            return new KeySet();
        }

        public V get(Object obj) {
            return StandardTable.this.get(obj, this.a);
        }

        public V put(R r, V v) {
            return StandardTable.this.put(r, this.a, v);
        }

        public V remove(Object obj) {
            return StandardTable.this.remove(obj, this.a);
        }
    }

    private class ColumnKeyIterator extends AbstractIterator<C> {
        final Map<C, V> a;
        final Iterator<Map<C, V>> b;
        Iterator<Map.Entry<C, V>> c;

        private ColumnKeyIterator() {
            this.a = (Map) StandardTable.this.b.get();
            this.b = StandardTable.this.a.values().iterator();
            this.c = Iterators.a();
        }

        /* access modifiers changed from: protected */
        public C computeNext() {
            while (true) {
                if (this.c.hasNext()) {
                    Map.Entry next = this.c.next();
                    if (!this.a.containsKey(next.getKey())) {
                        this.a.put(next.getKey(), next.getValue());
                        return next.getKey();
                    }
                } else if (!this.b.hasNext()) {
                    return a();
                } else {
                    this.c = this.b.next().entrySet().iterator();
                }
            }
        }
    }

    private class ColumnKeySet extends StandardTable<R, C, V>.TableSet<C> {
        private ColumnKeySet() {
            super();
        }

        public boolean contains(Object obj) {
            return StandardTable.this.containsColumn(obj);
        }

        public Iterator<C> iterator() {
            return StandardTable.this.g();
        }

        public boolean remove(Object obj) {
            boolean z = false;
            if (obj == null) {
                return false;
            }
            Iterator<Map<C, V>> it = StandardTable.this.a.values().iterator();
            while (it.hasNext()) {
                Map next = it.next();
                if (next.keySet().remove(obj)) {
                    z = true;
                    if (next.isEmpty()) {
                        it.remove();
                    }
                }
            }
            return z;
        }

        public boolean removeAll(Collection<?> collection) {
            Preconditions.checkNotNull(collection);
            Iterator<Map<C, V>> it = StandardTable.this.a.values().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map next = it.next();
                if (Iterators.removeAll(next.keySet().iterator(), collection)) {
                    z = true;
                    if (next.isEmpty()) {
                        it.remove();
                    }
                }
            }
            return z;
        }

        public boolean retainAll(Collection<?> collection) {
            Preconditions.checkNotNull(collection);
            Iterator<Map<C, V>> it = StandardTable.this.a.values().iterator();
            boolean z = false;
            while (it.hasNext()) {
                Map next = it.next();
                if (next.keySet().retainAll(collection)) {
                    z = true;
                    if (next.isEmpty()) {
                        it.remove();
                    }
                }
            }
            return z;
        }

        public int size() {
            return Iterators.size(iterator());
        }
    }

    private class ColumnMap extends Maps.ViewCachingAbstractMap<C, Map<R, V>> {

        class ColumnMapEntrySet extends StandardTable<R, C, V>.TableSet<Map.Entry<C, Map<R, V>>> {
            ColumnMapEntrySet() {
                super();
            }

            public boolean contains(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                if (!StandardTable.this.containsColumn(entry.getKey())) {
                    return false;
                }
                return ColumnMap.this.get(entry.getKey()).equals(entry.getValue());
            }

            public Iterator<Map.Entry<C, Map<R, V>>> iterator() {
                return Maps.a(StandardTable.this.columnKeySet(), new Function<C, Map<R, V>>() {
                    public Map<R, V> apply(C c) {
                        return StandardTable.this.column(c);
                    }
                });
            }

            public boolean remove(Object obj) {
                if (!contains(obj)) {
                    return false;
                }
                Map unused = StandardTable.this.removeColumn(((Map.Entry) obj).getKey());
                return true;
            }

            public boolean removeAll(Collection<?> collection) {
                Preconditions.checkNotNull(collection);
                return Sets.a((Set<?>) this, collection.iterator());
            }

            public boolean retainAll(Collection<?> collection) {
                Preconditions.checkNotNull(collection);
                Iterator it = Lists.newArrayList(StandardTable.this.columnKeySet().iterator()).iterator();
                boolean z = false;
                while (it.hasNext()) {
                    Object next = it.next();
                    if (!collection.contains(Maps.immutableEntry(next, StandardTable.this.column(next)))) {
                        Map unused = StandardTable.this.removeColumn(next);
                        z = true;
                    }
                }
                return z;
            }

            public int size() {
                return StandardTable.this.columnKeySet().size();
            }
        }

        private class ColumnMapValues extends Maps.Values<C, Map<R, V>> {
            ColumnMapValues() {
                super(ColumnMap.this);
            }

            public boolean remove(Object obj) {
                for (Map.Entry entry : ColumnMap.this.entrySet()) {
                    if (((Map) entry.getValue()).equals(obj)) {
                        Map unused = StandardTable.this.removeColumn(entry.getKey());
                        return true;
                    }
                }
                return false;
            }

            public boolean removeAll(Collection<?> collection) {
                Preconditions.checkNotNull(collection);
                Iterator it = Lists.newArrayList(StandardTable.this.columnKeySet().iterator()).iterator();
                boolean z = false;
                while (it.hasNext()) {
                    Object next = it.next();
                    if (collection.contains(StandardTable.this.column(next))) {
                        Map unused = StandardTable.this.removeColumn(next);
                        z = true;
                    }
                }
                return z;
            }

            public boolean retainAll(Collection<?> collection) {
                Preconditions.checkNotNull(collection);
                Iterator it = Lists.newArrayList(StandardTable.this.columnKeySet().iterator()).iterator();
                boolean z = false;
                while (it.hasNext()) {
                    Object next = it.next();
                    if (!collection.contains(StandardTable.this.column(next))) {
                        Map unused = StandardTable.this.removeColumn(next);
                        z = true;
                    }
                }
                return z;
            }
        }

        private ColumnMap() {
        }

        /* access modifiers changed from: package-private */
        public Collection<Map<R, V>> a() {
            return new ColumnMapValues();
        }

        public boolean containsKey(Object obj) {
            return StandardTable.this.containsColumn(obj);
        }

        public Set<Map.Entry<C, Map<R, V>>> createEntrySet() {
            return new ColumnMapEntrySet();
        }

        public Map<R, V> get(Object obj) {
            if (StandardTable.this.containsColumn(obj)) {
                return StandardTable.this.column(obj);
            }
            return null;
        }

        public Set<C> keySet() {
            return StandardTable.this.columnKeySet();
        }

        public Map<R, V> remove(Object obj) {
            if (StandardTable.this.containsColumn(obj)) {
                return StandardTable.this.removeColumn(obj);
            }
            return null;
        }
    }

    class Row extends Maps.IteratorBasedAbstractMap<C, V> {
        final R a;
        Map<C, V> b;

        Row(R r) {
            this.a = Preconditions.checkNotNull(r);
        }

        /* access modifiers changed from: package-private */
        public Map<C, V> a() {
            Map<C, V> map = this.b;
            if (map != null && (!map.isEmpty() || !StandardTable.this.a.containsKey(this.a))) {
                return this.b;
            }
            Map<C, V> c2 = c();
            this.b = c2;
            return c2;
        }

        /* access modifiers changed from: package-private */
        public Iterator<Map.Entry<C, V>> b() {
            Map a2 = a();
            if (a2 == null) {
                return Iterators.c();
            }
            final Iterator it = a2.entrySet().iterator();
            return new Iterator<Map.Entry<C, V>>() {
                public boolean hasNext() {
                    return it.hasNext();
                }

                public Map.Entry<C, V> next() {
                    final Map.Entry entry = (Map.Entry) it.next();
                    return new ForwardingMapEntry<C, V>() {
                        /* access modifiers changed from: protected */
                        /* renamed from: a */
                        public Map.Entry<C, V> delegate() {
                            return entry;
                        }

                        public boolean equals(Object obj) {
                            return a(obj);
                        }

                        public V setValue(V v) {
                            return super.setValue(Preconditions.checkNotNull(v));
                        }
                    };
                }

                public void remove() {
                    it.remove();
                    Row.this.d();
                }
            };
        }

        /* access modifiers changed from: package-private */
        public Map<C, V> c() {
            return StandardTable.this.a.get(this.a);
        }

        public void clear() {
            Map a2 = a();
            if (a2 != null) {
                a2.clear();
            }
            d();
        }

        public boolean containsKey(Object obj) {
            Map a2 = a();
            return (obj == null || a2 == null || !Maps.b((Map<?, ?>) a2, obj)) ? false : true;
        }

        /* access modifiers changed from: package-private */
        public void d() {
            if (a() != null && this.b.isEmpty()) {
                StandardTable.this.a.remove(this.a);
                this.b = null;
            }
        }

        public V get(Object obj) {
            Map a2 = a();
            if (obj == null || a2 == null) {
                return null;
            }
            return Maps.a(a2, obj);
        }

        public V put(C c2, V v) {
            Preconditions.checkNotNull(c2);
            Preconditions.checkNotNull(v);
            Map<C, V> map = this.b;
            return (map == null || map.isEmpty()) ? StandardTable.this.put(this.a, c2, v) : this.b.put(c2, v);
        }

        public V remove(Object obj) {
            Map a2 = a();
            if (a2 == null) {
                return null;
            }
            V c2 = Maps.c(a2, obj);
            d();
            return c2;
        }

        public int size() {
            Map a2 = a();
            if (a2 == null) {
                return 0;
            }
            return a2.size();
        }
    }

    class RowMap extends Maps.ViewCachingAbstractMap<R, Map<C, V>> {

        class EntrySet extends StandardTable<R, C, V>.TableSet<Map.Entry<R, Map<C, V>>> {
            EntrySet() {
                super();
            }

            public boolean contains(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                return entry.getKey() != null && (entry.getValue() instanceof Map) && Collections2.a((Collection<?>) StandardTable.this.a.entrySet(), (Object) entry);
            }

            public Iterator<Map.Entry<R, Map<C, V>>> iterator() {
                return Maps.a(StandardTable.this.a.keySet(), new Function<R, Map<C, V>>() {
                    public Map<C, V> apply(R r) {
                        return StandardTable.this.row(r);
                    }
                });
            }

            public boolean remove(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                return entry.getKey() != null && (entry.getValue() instanceof Map) && StandardTable.this.a.entrySet().remove(entry);
            }

            public int size() {
                return StandardTable.this.a.size();
            }
        }

        RowMap() {
        }

        public boolean containsKey(Object obj) {
            return StandardTable.this.containsRow(obj);
        }

        /* access modifiers changed from: protected */
        public Set<Map.Entry<R, Map<C, V>>> createEntrySet() {
            return new EntrySet();
        }

        public Map<C, V> get(Object obj) {
            if (StandardTable.this.containsRow(obj)) {
                return StandardTable.this.row(obj);
            }
            return null;
        }

        public Map<C, V> remove(Object obj) {
            if (obj == null) {
                return null;
            }
            return StandardTable.this.a.remove(obj);
        }
    }

    private abstract class TableSet<T> extends Sets.ImprovedAbstractSet<T> {
        private TableSet() {
        }

        public void clear() {
            StandardTable.this.a.clear();
        }

        public boolean isEmpty() {
            return StandardTable.this.a.isEmpty();
        }
    }

    StandardTable(Map<R, Map<C, V>> map, Supplier<? extends Map<C, V>> supplier) {
        this.a = map;
        this.b = supplier;
    }

    /* access modifiers changed from: private */
    public boolean containsMapping(Object obj, Object obj2, Object obj3) {
        return obj3 != null && obj3.equals(get(obj, obj2));
    }

    private Map<C, V> getOrCreate(R r) {
        Map<C, V> map = this.a.get(r);
        if (map != null) {
            return map;
        }
        Map<C, V> map2 = (Map) this.b.get();
        this.a.put(r, map2);
        return map2;
    }

    /* access modifiers changed from: private */
    @CanIgnoreReturnValue
    public Map<R, V> removeColumn(Object obj) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<Map.Entry<R, Map<C, V>>> it = this.a.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            Object remove = ((Map) next.getValue()).remove(obj);
            if (remove != null) {
                linkedHashMap.put(next.getKey(), remove);
                if (((Map) next.getValue()).isEmpty()) {
                    it.remove();
                }
            }
        }
        return linkedHashMap;
    }

    /* access modifiers changed from: private */
    public boolean removeMapping(Object obj, Object obj2, Object obj3) {
        if (!containsMapping(obj, obj2, obj3)) {
            return false;
        }
        remove(obj, obj2);
        return true;
    }

    /* access modifiers changed from: package-private */
    public Iterator<Table.Cell<R, C, V>> b() {
        return new CellIterator();
    }

    public Set<Table.Cell<R, C, V>> cellSet() {
        return super.cellSet();
    }

    public void clear() {
        this.a.clear();
    }

    public Map<R, V> column(C c) {
        return new Column(c);
    }

    public Set<C> columnKeySet() {
        Set<C> set = this.columnKeySet;
        if (set != null) {
            return set;
        }
        ColumnKeySet columnKeySet2 = new ColumnKeySet();
        this.columnKeySet = columnKeySet2;
        return columnKeySet2;
    }

    public Map<C, Map<R, V>> columnMap() {
        StandardTable<R, C, V>.ColumnMap columnMap2 = this.columnMap;
        if (columnMap2 != null) {
            return columnMap2;
        }
        StandardTable<R, C, V>.ColumnMap columnMap3 = new ColumnMap();
        this.columnMap = columnMap3;
        return columnMap3;
    }

    public boolean contains(@Nullable Object obj, @Nullable Object obj2) {
        return (obj == null || obj2 == null || !super.contains(obj, obj2)) ? false : true;
    }

    public boolean containsColumn(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        for (Map<C, V> b2 : this.a.values()) {
            if (Maps.b((Map<?, ?>) b2, obj)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsRow(@Nullable Object obj) {
        return obj != null && Maps.b((Map<?, ?>) this.a, obj);
    }

    public boolean containsValue(@Nullable Object obj) {
        return obj != null && super.containsValue(obj);
    }

    /* access modifiers changed from: package-private */
    public Map<R, Map<C, V>> f() {
        return new RowMap();
    }

    /* access modifiers changed from: package-private */
    public Iterator<C> g() {
        return new ColumnKeyIterator();
    }

    public V get(@Nullable Object obj, @Nullable Object obj2) {
        if (obj == null || obj2 == null) {
            return null;
        }
        return super.get(obj, obj2);
    }

    public boolean isEmpty() {
        return this.a.isEmpty();
    }

    @CanIgnoreReturnValue
    public V put(R r, C c, V v) {
        Preconditions.checkNotNull(r);
        Preconditions.checkNotNull(c);
        Preconditions.checkNotNull(v);
        return getOrCreate(r).put(c, v);
    }

    @CanIgnoreReturnValue
    public V remove(@Nullable Object obj, @Nullable Object obj2) {
        Map map;
        if (obj == null || obj2 == null || (map = (Map) Maps.a(this.a, obj)) == null) {
            return null;
        }
        V remove = map.remove(obj2);
        if (map.isEmpty()) {
            this.a.remove(obj);
        }
        return remove;
    }

    public Map<C, V> row(R r) {
        return new Row(r);
    }

    public Set<R> rowKeySet() {
        return rowMap().keySet();
    }

    public Map<R, Map<C, V>> rowMap() {
        Map<R, Map<C, V>> map = this.rowMap;
        if (map != null) {
            return map;
        }
        Map<R, Map<C, V>> f = f();
        this.rowMap = f;
        return f;
    }

    public int size() {
        int i = 0;
        for (Map<C, V> size : this.a.values()) {
            i += size.size();
        }
        return i;
    }

    public Collection<V> values() {
        return super.values();
    }
}

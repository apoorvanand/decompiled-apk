package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
public final class LinkedHashMultimap<K, V> extends AbstractSetMultimap<K, V> {
    private static final int DEFAULT_KEY_CAPACITY = 16;
    private static final int DEFAULT_VALUE_SET_CAPACITY = 2;
    @GwtIncompatible
    private static final long serialVersionUID = 1;
    @VisibleForTesting
    transient int a = 2;
    /* access modifiers changed from: private */
    public transient ValueEntry<K, V> multimapHeaderEntry;

    @VisibleForTesting
    static final class ValueEntry<K, V> extends ImmutableEntry<K, V> implements ValueSetLink<K, V> {
        final int a;
        @Nullable
        ValueEntry<K, V> b;
        ValueSetLink<K, V> c;
        ValueSetLink<K, V> d;
        ValueEntry<K, V> e;
        ValueEntry<K, V> f;

        ValueEntry(@Nullable K k, @Nullable V v, int i, @Nullable ValueEntry<K, V> valueEntry) {
            super(k, v);
            this.a = i;
            this.b = valueEntry;
        }

        /* access modifiers changed from: package-private */
        public boolean a(@Nullable Object obj, int i) {
            return this.a == i && Objects.equal(getValue(), obj);
        }

        public ValueEntry<K, V> getPredecessorInMultimap() {
            return this.e;
        }

        public ValueSetLink<K, V> getPredecessorInValueSet() {
            return this.c;
        }

        public ValueEntry<K, V> getSuccessorInMultimap() {
            return this.f;
        }

        public ValueSetLink<K, V> getSuccessorInValueSet() {
            return this.d;
        }

        public void setPredecessorInMultimap(ValueEntry<K, V> valueEntry) {
            this.e = valueEntry;
        }

        public void setPredecessorInValueSet(ValueSetLink<K, V> valueSetLink) {
            this.c = valueSetLink;
        }

        public void setSuccessorInMultimap(ValueEntry<K, V> valueEntry) {
            this.f = valueEntry;
        }

        public void setSuccessorInValueSet(ValueSetLink<K, V> valueSetLink) {
            this.d = valueSetLink;
        }
    }

    @VisibleForTesting
    final class ValueSet extends Sets.ImprovedAbstractSet<V> implements ValueSetLink<K, V> {
        @VisibleForTesting
        ValueEntry<K, V>[] a;
        /* access modifiers changed from: private */
        public ValueSetLink<K, V> firstEntry;
        private final K key;
        private ValueSetLink<K, V> lastEntry;
        /* access modifiers changed from: private */
        public int modCount = 0;
        private int size = 0;

        ValueSet(K k, int i) {
            this.key = k;
            this.firstEntry = this;
            this.lastEntry = this;
            this.a = new ValueEntry[Hashing.a(i, 1.0d)];
        }

        private int mask() {
            return this.a.length - 1;
        }

        private void rehashIfNecessary() {
            if (Hashing.a(this.size, this.a.length, 1.0d)) {
                ValueEntry<K, V>[] valueEntryArr = new ValueEntry[(this.a.length * 2)];
                this.a = valueEntryArr;
                int length = valueEntryArr.length - 1;
                for (ValueSetLink valueSetLink = this.firstEntry; valueSetLink != this; valueSetLink = valueSetLink.getSuccessorInValueSet()) {
                    ValueEntry<K, V> valueEntry = (ValueEntry) valueSetLink;
                    int i = valueEntry.a & length;
                    valueEntry.b = valueEntryArr[i];
                    valueEntryArr[i] = valueEntry;
                }
            }
        }

        public boolean add(@Nullable V v) {
            int a2 = Hashing.a((Object) v);
            int mask = mask() & a2;
            ValueEntry<K, V> valueEntry = this.a[mask];
            for (ValueEntry<K, V> valueEntry2 = valueEntry; valueEntry2 != null; valueEntry2 = valueEntry2.b) {
                if (valueEntry2.a(v, a2)) {
                    return false;
                }
            }
            ValueEntry<K, V> valueEntry3 = new ValueEntry<>(this.key, v, a2, valueEntry);
            LinkedHashMultimap.succeedsInValueSet(this.lastEntry, valueEntry3);
            LinkedHashMultimap.succeedsInValueSet(valueEntry3, this);
            LinkedHashMultimap.succeedsInMultimap(LinkedHashMultimap.this.multimapHeaderEntry.getPredecessorInMultimap(), valueEntry3);
            LinkedHashMultimap.succeedsInMultimap(valueEntry3, LinkedHashMultimap.this.multimapHeaderEntry);
            this.a[mask] = valueEntry3;
            this.size++;
            this.modCount++;
            rehashIfNecessary();
            return true;
        }

        public void clear() {
            Arrays.fill(this.a, (Object) null);
            this.size = 0;
            for (ValueSetLink<K, V> valueSetLink = this.firstEntry; valueSetLink != this; valueSetLink = valueSetLink.getSuccessorInValueSet()) {
                LinkedHashMultimap.deleteFromMultimap((ValueEntry) valueSetLink);
            }
            LinkedHashMultimap.succeedsInValueSet(this, this);
            this.modCount++;
        }

        public boolean contains(@Nullable Object obj) {
            int a2 = Hashing.a(obj);
            for (ValueEntry<K, V> valueEntry = this.a[mask() & a2]; valueEntry != null; valueEntry = valueEntry.b) {
                if (valueEntry.a(obj, a2)) {
                    return true;
                }
            }
            return false;
        }

        public ValueSetLink<K, V> getPredecessorInValueSet() {
            return this.lastEntry;
        }

        public ValueSetLink<K, V> getSuccessorInValueSet() {
            return this.firstEntry;
        }

        public Iterator<V> iterator() {
            return new Iterator<V>() {
                ValueSetLink<K, V> a = ValueSet.this.firstEntry;
                ValueEntry<K, V> b;
                int c = ValueSet.this.modCount;

                private void checkForComodification() {
                    if (ValueSet.this.modCount != this.c) {
                        throw new ConcurrentModificationException();
                    }
                }

                public boolean hasNext() {
                    checkForComodification();
                    return this.a != ValueSet.this;
                }

                public V next() {
                    if (hasNext()) {
                        ValueEntry<K, V> valueEntry = (ValueEntry) this.a;
                        V value = valueEntry.getValue();
                        this.b = valueEntry;
                        this.a = valueEntry.getSuccessorInValueSet();
                        return value;
                    }
                    throw new NoSuchElementException();
                }

                public void remove() {
                    checkForComodification();
                    CollectPreconditions.a(this.b != null);
                    ValueSet.this.remove(this.b.getValue());
                    this.c = ValueSet.this.modCount;
                    this.b = null;
                }
            };
        }

        @CanIgnoreReturnValue
        public boolean remove(@Nullable Object obj) {
            int a2 = Hashing.a(obj);
            int mask = mask() & a2;
            ValueEntry<K, V> valueEntry = this.a[mask];
            ValueEntry<K, V> valueEntry2 = null;
            while (true) {
                ValueEntry<K, V> valueEntry3 = valueEntry2;
                valueEntry2 = valueEntry;
                ValueEntry<K, V> valueEntry4 = valueEntry3;
                if (valueEntry2 == null) {
                    return false;
                }
                if (valueEntry2.a(obj, a2)) {
                    if (valueEntry4 == null) {
                        this.a[mask] = valueEntry2.b;
                    } else {
                        valueEntry4.b = valueEntry2.b;
                    }
                    LinkedHashMultimap.deleteFromValueSet(valueEntry2);
                    LinkedHashMultimap.deleteFromMultimap(valueEntry2);
                    this.size--;
                    this.modCount++;
                    return true;
                }
                valueEntry = valueEntry2.b;
            }
        }

        public void setPredecessorInValueSet(ValueSetLink<K, V> valueSetLink) {
            this.lastEntry = valueSetLink;
        }

        public void setSuccessorInValueSet(ValueSetLink<K, V> valueSetLink) {
            this.firstEntry = valueSetLink;
        }

        public int size() {
            return this.size;
        }
    }

    private interface ValueSetLink<K, V> {
        ValueSetLink<K, V> getPredecessorInValueSet();

        ValueSetLink<K, V> getSuccessorInValueSet();

        void setPredecessorInValueSet(ValueSetLink<K, V> valueSetLink);

        void setSuccessorInValueSet(ValueSetLink<K, V> valueSetLink);
    }

    private LinkedHashMultimap(int i, int i2) {
        super(new LinkedHashMap(i));
        CollectPreconditions.a(i2, "expectedValuesPerKey");
        this.a = i2;
        this.multimapHeaderEntry = new ValueEntry<>(null, null, 0, (ValueEntry) null);
        ValueEntry<K, V> valueEntry = this.multimapHeaderEntry;
        succeedsInMultimap(valueEntry, valueEntry);
    }

    public static <K, V> LinkedHashMultimap<K, V> create() {
        return new LinkedHashMultimap<>(16, 2);
    }

    public static <K, V> LinkedHashMultimap<K, V> create(int i, int i2) {
        return new LinkedHashMultimap<>(Maps.a(i), Maps.a(i2));
    }

    public static <K, V> LinkedHashMultimap<K, V> create(Multimap<? extends K, ? extends V> multimap) {
        LinkedHashMultimap<K, V> create = create(multimap.keySet().size(), 2);
        create.putAll(multimap);
        return create;
    }

    /* access modifiers changed from: private */
    public static <K, V> void deleteFromMultimap(ValueEntry<K, V> valueEntry) {
        succeedsInMultimap(valueEntry.getPredecessorInMultimap(), valueEntry.getSuccessorInMultimap());
    }

    /* access modifiers changed from: private */
    public static <K, V> void deleteFromValueSet(ValueSetLink<K, V> valueSetLink) {
        succeedsInValueSet(valueSetLink.getPredecessorInValueSet(), valueSetLink.getSuccessorInValueSet());
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        this.multimapHeaderEntry = new ValueEntry<>(null, null, 0, (ValueEntry) null);
        ValueEntry<K, V> valueEntry = this.multimapHeaderEntry;
        succeedsInMultimap(valueEntry, valueEntry);
        this.a = 2;
        int readInt = objectInputStream.readInt();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < readInt; i++) {
            Object readObject = objectInputStream.readObject();
            linkedHashMap.put(readObject, a(readObject));
        }
        int readInt2 = objectInputStream.readInt();
        for (int i2 = 0; i2 < readInt2; i2++) {
            Object readObject2 = objectInputStream.readObject();
            ((Collection) linkedHashMap.get(readObject2)).add(objectInputStream.readObject());
        }
        a(linkedHashMap);
    }

    /* access modifiers changed from: private */
    public static <K, V> void succeedsInMultimap(ValueEntry<K, V> valueEntry, ValueEntry<K, V> valueEntry2) {
        valueEntry.setSuccessorInMultimap(valueEntry2);
        valueEntry2.setPredecessorInMultimap(valueEntry);
    }

    /* access modifiers changed from: private */
    public static <K, V> void succeedsInValueSet(ValueSetLink<K, V> valueSetLink, ValueSetLink<K, V> valueSetLink2) {
        valueSetLink.setSuccessorInValueSet(valueSetLink2);
        valueSetLink2.setPredecessorInValueSet(valueSetLink);
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(keySet().size());
        for (Object writeObject : keySet()) {
            objectOutputStream.writeObject(writeObject);
        }
        objectOutputStream.writeInt(size());
        for (Map.Entry entry : entries()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    /* access modifiers changed from: package-private */
    public Collection<V> a(K k) {
        return new ValueSet(k, this.a);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public Set<V> c() {
        return new LinkedHashSet(this.a);
    }

    public /* bridge */ /* synthetic */ Map asMap() {
        return super.asMap();
    }

    public void clear() {
        super.clear();
        ValueEntry<K, V> valueEntry = this.multimapHeaderEntry;
        succeedsInMultimap(valueEntry, valueEntry);
    }

    public /* bridge */ /* synthetic */ boolean containsEntry(Object obj, Object obj2) {
        return super.containsEntry(obj, obj2);
    }

    public /* bridge */ /* synthetic */ boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    public /* bridge */ /* synthetic */ boolean containsValue(Object obj) {
        return super.containsValue(obj);
    }

    public Set<Map.Entry<K, V>> entries() {
        return super.entries();
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    /* access modifiers changed from: package-private */
    public Iterator<V> g() {
        return Maps.b(h());
    }

    public /* bridge */ /* synthetic */ Set get(Object obj) {
        return super.get(obj);
    }

    /* access modifiers changed from: package-private */
    public Iterator<Map.Entry<K, V>> h() {
        return new Iterator<Map.Entry<K, V>>() {
            ValueEntry<K, V> a = LinkedHashMultimap.this.multimapHeaderEntry.f;
            ValueEntry<K, V> b;

            public boolean hasNext() {
                return this.a != LinkedHashMultimap.this.multimapHeaderEntry;
            }

            public Map.Entry<K, V> next() {
                if (hasNext()) {
                    ValueEntry<K, V> valueEntry = this.a;
                    this.b = valueEntry;
                    this.a = valueEntry.f;
                    return valueEntry;
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                CollectPreconditions.a(this.b != null);
                LinkedHashMultimap.this.remove(this.b.getKey(), this.b.getValue());
                this.b = null;
            }
        };
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    public Set<K> keySet() {
        return super.keySet();
    }

    public /* bridge */ /* synthetic */ Multiset keys() {
        return super.keys();
    }

    public /* bridge */ /* synthetic */ boolean put(Object obj, Object obj2) {
        return super.put(obj, obj2);
    }

    public /* bridge */ /* synthetic */ boolean putAll(Multimap multimap) {
        return super.putAll(multimap);
    }

    public /* bridge */ /* synthetic */ boolean putAll(Object obj, Iterable iterable) {
        return super.putAll(obj, iterable);
    }

    public /* bridge */ /* synthetic */ boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    public /* bridge */ /* synthetic */ Set removeAll(Object obj) {
        return super.removeAll(obj);
    }

    @CanIgnoreReturnValue
    public Set<V> replaceValues(@Nullable K k, Iterable<? extends V> iterable) {
        return super.replaceValues((Object) k, (Iterable) iterable);
    }

    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public Collection<V> values() {
        return super.values();
    }
}

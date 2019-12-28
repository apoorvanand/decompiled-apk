package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
public class LinkedListMultimap<K, V> extends AbstractMultimap<K, V> implements ListMultimap<K, V>, Serializable {
    @GwtIncompatible
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public transient Node<K, V> head;
    /* access modifiers changed from: private */
    public transient Map<K, KeyList<K, V>> keyToKeyList;
    /* access modifiers changed from: private */
    public transient int modCount;
    /* access modifiers changed from: private */
    public transient int size;
    /* access modifiers changed from: private */
    public transient Node<K, V> tail;

    private class DistinctKeyIterator implements Iterator<K> {
        final Set<K> a;
        Node<K, V> b;
        Node<K, V> c;
        int d;

        private DistinctKeyIterator() {
            this.a = Sets.newHashSetWithExpectedSize(LinkedListMultimap.this.keySet().size());
            this.b = LinkedListMultimap.this.head;
            this.d = LinkedListMultimap.this.modCount;
        }

        private void checkForConcurrentModification() {
            if (LinkedListMultimap.this.modCount != this.d) {
                throw new ConcurrentModificationException();
            }
        }

        public boolean hasNext() {
            checkForConcurrentModification();
            return this.b != null;
        }

        public K next() {
            Node<K, V> node;
            checkForConcurrentModification();
            LinkedListMultimap.checkElement(this.b);
            this.c = this.b;
            this.a.add(this.c.a);
            do {
                this.b = this.b.c;
                node = this.b;
                if (node == null || this.a.add(node.a)) {
                }
                this.b = this.b.c;
                node = this.b;
                break;
            } while (this.a.add(node.a));
            return this.c.a;
        }

        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.a(this.c != null);
            LinkedListMultimap.this.removeAllNodes(this.c.a);
            this.c = null;
            this.d = LinkedListMultimap.this.modCount;
        }
    }

    private static class KeyList<K, V> {
        Node<K, V> a;
        Node<K, V> b;
        int c = 1;

        KeyList(Node<K, V> node) {
            this.a = node;
            this.b = node;
            node.f = null;
            node.e = null;
        }
    }

    private static final class Node<K, V> extends AbstractMapEntry<K, V> {
        final K a;
        V b;
        Node<K, V> c;
        Node<K, V> d;
        Node<K, V> e;
        Node<K, V> f;

        Node(@Nullable K k, @Nullable V v) {
            this.a = k;
            this.b = v;
        }

        public K getKey() {
            return this.a;
        }

        public V getValue() {
            return this.b;
        }

        public V setValue(@Nullable V v) {
            V v2 = this.b;
            this.b = v;
            return v2;
        }
    }

    private class NodeIterator implements ListIterator<Map.Entry<K, V>> {
        int a;
        Node<K, V> b;
        Node<K, V> c;
        Node<K, V> d;
        int e = LinkedListMultimap.this.modCount;

        NodeIterator(int i) {
            int size = LinkedListMultimap.this.size();
            Preconditions.checkPositionIndex(i, size);
            if (i < size / 2) {
                this.b = LinkedListMultimap.this.head;
                while (true) {
                    int i2 = i - 1;
                    if (i <= 0) {
                        break;
                    }
                    next();
                    i = i2;
                }
            } else {
                this.d = LinkedListMultimap.this.tail;
                this.a = size;
                while (true) {
                    int i3 = i + 1;
                    if (i >= size) {
                        break;
                    }
                    previous();
                    i = i3;
                }
            }
            this.c = null;
        }

        private void checkForConcurrentModification() {
            if (LinkedListMultimap.this.modCount != this.e) {
                throw new ConcurrentModificationException();
            }
        }

        /* access modifiers changed from: package-private */
        public void a(V v) {
            Preconditions.checkState(this.c != null);
            this.c.b = v;
        }

        public void add(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            checkForConcurrentModification();
            return this.b != null;
        }

        public boolean hasPrevious() {
            checkForConcurrentModification();
            return this.d != null;
        }

        @CanIgnoreReturnValue
        public Node<K, V> next() {
            checkForConcurrentModification();
            LinkedListMultimap.checkElement(this.b);
            Node<K, V> node = this.b;
            this.c = node;
            this.d = node;
            this.b = node.c;
            this.a++;
            return this.c;
        }

        public int nextIndex() {
            return this.a;
        }

        @CanIgnoreReturnValue
        public Node<K, V> previous() {
            checkForConcurrentModification();
            LinkedListMultimap.checkElement(this.d);
            Node<K, V> node = this.d;
            this.c = node;
            this.b = node;
            this.d = node.d;
            this.a--;
            return this.c;
        }

        public int previousIndex() {
            return this.a - 1;
        }

        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.a(this.c != null);
            Node<K, V> node = this.c;
            if (node != this.b) {
                this.d = node.d;
                this.a--;
            } else {
                this.b = node.c;
            }
            LinkedListMultimap.this.removeNode(this.c);
            this.c = null;
            this.e = LinkedListMultimap.this.modCount;
        }

        public void set(Map.Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }
    }

    private class ValueForKeyIterator implements ListIterator<V> {
        final Object a;
        int b;
        Node<K, V> c;
        Node<K, V> d;
        Node<K, V> e;

        ValueForKeyIterator(Object obj) {
            this.a = obj;
            KeyList keyList = (KeyList) LinkedListMultimap.this.keyToKeyList.get(obj);
            this.c = keyList == null ? null : keyList.a;
        }

        public ValueForKeyIterator(Object obj, @Nullable int i) {
            KeyList keyList = (KeyList) LinkedListMultimap.this.keyToKeyList.get(obj);
            int i2 = keyList == null ? 0 : keyList.c;
            Preconditions.checkPositionIndex(i, i2);
            if (i < i2 / 2) {
                this.c = keyList == null ? null : keyList.a;
                while (true) {
                    int i3 = i - 1;
                    if (i <= 0) {
                        break;
                    }
                    next();
                    i = i3;
                }
            } else {
                this.e = keyList == null ? null : keyList.b;
                this.b = i2;
                while (true) {
                    int i4 = i + 1;
                    if (i >= i2) {
                        break;
                    }
                    previous();
                    i = i4;
                }
            }
            this.a = obj;
            this.d = null;
        }

        public void add(V v) {
            this.e = LinkedListMultimap.this.addNode(this.a, v, this.c);
            this.b++;
            this.d = null;
        }

        public boolean hasNext() {
            return this.c != null;
        }

        public boolean hasPrevious() {
            return this.e != null;
        }

        @CanIgnoreReturnValue
        public V next() {
            LinkedListMultimap.checkElement(this.c);
            Node<K, V> node = this.c;
            this.d = node;
            this.e = node;
            this.c = node.e;
            this.b++;
            return this.d.b;
        }

        public int nextIndex() {
            return this.b;
        }

        @CanIgnoreReturnValue
        public V previous() {
            LinkedListMultimap.checkElement(this.e);
            Node<K, V> node = this.e;
            this.d = node;
            this.c = node;
            this.e = node.f;
            this.b--;
            return this.d.b;
        }

        public int previousIndex() {
            return this.b - 1;
        }

        public void remove() {
            CollectPreconditions.a(this.d != null);
            Node<K, V> node = this.d;
            if (node != this.c) {
                this.e = node.f;
                this.b--;
            } else {
                this.c = node.e;
            }
            LinkedListMultimap.this.removeNode(this.d);
            this.d = null;
        }

        public void set(V v) {
            Preconditions.checkState(this.d != null);
            this.d.b = v;
        }
    }

    LinkedListMultimap() {
        this.keyToKeyList = Maps.newHashMap();
    }

    private LinkedListMultimap(int i) {
        this.keyToKeyList = new HashMap(i);
    }

    private LinkedListMultimap(Multimap<? extends K, ? extends V> multimap) {
        this(multimap.keySet().size());
        putAll(multimap);
    }

    /* access modifiers changed from: private */
    @CanIgnoreReturnValue
    public Node<K, V> addNode(@Nullable K k, @Nullable V v, @Nullable Node<K, V> node) {
        Map<K, KeyList<K, V>> map;
        KeyList keyList;
        Node<K, V> node2 = new Node<>(k, v);
        if (this.head == null) {
            this.tail = node2;
            this.head = node2;
            map = this.keyToKeyList;
            keyList = new KeyList(node2);
        } else {
            if (node == null) {
                Node<K, V> node3 = this.tail;
                node3.c = node2;
                node2.d = node3;
                this.tail = node2;
                KeyList keyList2 = this.keyToKeyList.get(k);
                if (keyList2 == null) {
                    map = this.keyToKeyList;
                    keyList = new KeyList(node2);
                } else {
                    keyList2.c++;
                    Node<K, V> node4 = keyList2.b;
                    node4.e = node2;
                    node2.f = node4;
                    keyList2.b = node2;
                }
            } else {
                this.keyToKeyList.get(k).c++;
                node2.d = node.d;
                node2.f = node.f;
                node2.c = node;
                node2.e = node;
                if (node.f == null) {
                    this.keyToKeyList.get(k).a = node2;
                } else {
                    node.f.e = node2;
                }
                if (node.d == null) {
                    this.head = node2;
                } else {
                    node.d.c = node2;
                }
                node.d = node2;
                node.f = node2;
            }
            this.size++;
            return node2;
        }
        map.put(k, keyList);
        this.modCount++;
        this.size++;
        return node2;
    }

    /* access modifiers changed from: private */
    public static void checkElement(@Nullable Object obj) {
        if (obj == null) {
            throw new NoSuchElementException();
        }
    }

    public static <K, V> LinkedListMultimap<K, V> create() {
        return new LinkedListMultimap<>();
    }

    public static <K, V> LinkedListMultimap<K, V> create(int i) {
        return new LinkedListMultimap<>(i);
    }

    public static <K, V> LinkedListMultimap<K, V> create(Multimap<? extends K, ? extends V> multimap) {
        return new LinkedListMultimap<>(multimap);
    }

    private List<V> getCopy(@Nullable Object obj) {
        return Collections.unmodifiableList(Lists.newArrayList(new ValueForKeyIterator(obj)));
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        this.keyToKeyList = Maps.newLinkedHashMap();
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    /* access modifiers changed from: private */
    public void removeAllNodes(@Nullable Object obj) {
        Iterators.b(new ValueForKeyIterator(obj));
    }

    /* access modifiers changed from: private */
    public void removeNode(Node<K, V> node) {
        if (node.d != null) {
            node.d.c = node.c;
        } else {
            this.head = node.c;
        }
        if (node.c != null) {
            node.c.d = node.d;
        } else {
            this.tail = node.d;
        }
        if (node.f == null && node.e == null) {
            this.keyToKeyList.remove(node.a).c = 0;
            this.modCount++;
        } else {
            KeyList keyList = this.keyToKeyList.get(node.a);
            keyList.c--;
            if (node.f == null) {
                keyList.a = node.e;
            } else {
                node.f.e = node.e;
            }
            if (node.e == null) {
                keyList.b = node.f;
            } else {
                node.e.f = node.f;
            }
        }
        this.size--;
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        for (Map.Entry entry : entries()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public List<V> l() {
        return new AbstractSequentialList<V>() {
            public ListIterator<V> listIterator(int i) {
                final NodeIterator nodeIterator = new NodeIterator(i);
                return new TransformedListIterator<Map.Entry<K, V>, V>(nodeIterator) {
                    /* access modifiers changed from: package-private */
                    public V a(Map.Entry<K, V> entry) {
                        return entry.getValue();
                    }

                    public void set(V v) {
                        nodeIterator.a(v);
                    }
                };
            }

            public int size() {
                return LinkedListMultimap.this.size;
            }
        };
    }

    public /* bridge */ /* synthetic */ Map asMap() {
        return super.asMap();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public List<Map.Entry<K, V>> j() {
        return new AbstractSequentialList<Map.Entry<K, V>>() {
            public ListIterator<Map.Entry<K, V>> listIterator(int i) {
                return new NodeIterator(i);
            }

            public int size() {
                return LinkedListMultimap.this.size;
            }
        };
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.keyToKeyList.clear();
        this.size = 0;
        this.modCount++;
    }

    public /* bridge */ /* synthetic */ boolean containsEntry(Object obj, Object obj2) {
        return super.containsEntry(obj, obj2);
    }

    public boolean containsKey(@Nullable Object obj) {
        return this.keyToKeyList.containsKey(obj);
    }

    public boolean containsValue(@Nullable Object obj) {
        return values().contains(obj);
    }

    public List<Map.Entry<K, V>> entries() {
        return (List) super.entries();
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    /* access modifiers changed from: package-private */
    public Set<K> f() {
        return new Sets.ImprovedAbstractSet<K>() {
            public boolean contains(Object obj) {
                return LinkedListMultimap.this.containsKey(obj);
            }

            public Iterator<K> iterator() {
                return new DistinctKeyIterator();
            }

            public boolean remove(Object obj) {
                return !LinkedListMultimap.this.removeAll(obj).isEmpty();
            }

            public int size() {
                return LinkedListMultimap.this.keyToKeyList.size();
            }
        };
    }

    public List<V> get(@Nullable final K k) {
        return new AbstractSequentialList<V>() {
            public ListIterator<V> listIterator(int i) {
                return new ValueForKeyIterator(k, i);
            }

            public int size() {
                KeyList keyList = (KeyList) LinkedListMultimap.this.keyToKeyList.get(k);
                if (keyList == null) {
                    return 0;
                }
                return keyList.c;
            }
        };
    }

    /* access modifiers changed from: package-private */
    public Iterator<Map.Entry<K, V>> h() {
        throw new AssertionError("should never be called");
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    /* access modifiers changed from: package-private */
    public Map<K, Collection<V>> i() {
        return new Multimaps.AsMap(this);
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public /* bridge */ /* synthetic */ Set keySet() {
        return super.keySet();
    }

    public /* bridge */ /* synthetic */ Multiset keys() {
        return super.keys();
    }

    @CanIgnoreReturnValue
    public boolean put(@Nullable K k, @Nullable V v) {
        addNode(k, v, (Node) null);
        return true;
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

    @CanIgnoreReturnValue
    public List<V> removeAll(@Nullable Object obj) {
        List<V> copy = getCopy(obj);
        removeAllNodes(obj);
        return copy;
    }

    @CanIgnoreReturnValue
    public List<V> replaceValues(@Nullable K k, Iterable<? extends V> iterable) {
        List<V> copy = getCopy(k);
        ValueForKeyIterator valueForKeyIterator = new ValueForKeyIterator(k);
        Iterator<? extends V> it = iterable.iterator();
        while (valueForKeyIterator.hasNext() && it.hasNext()) {
            valueForKeyIterator.next();
            valueForKeyIterator.set(it.next());
        }
        while (valueForKeyIterator.hasNext()) {
            valueForKeyIterator.next();
            valueForKeyIterator.remove();
        }
        while (it.hasNext()) {
            valueForKeyIterator.add(it.next());
        }
        return copy;
    }

    public int size() {
        return this.size;
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public List<V> values() {
        return (List) super.values();
    }
}

package com.google.gson.internal;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedHashTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    private static final Comparator<Comparable> NATURAL_ORDER = new Comparator<Comparable>() {
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };
    static final /* synthetic */ boolean g = (!LinkedHashTreeMap.class.desiredAssertionStatus());
    Comparator<? super K> a;
    Node<K, V>[] b;
    final Node<K, V> c;
    int d;
    int e;
    private LinkedHashTreeMap<K, V>.EntrySet entrySet;
    int f;
    private LinkedHashTreeMap<K, V>.KeySet keySet;

    static final class AvlBuilder<K, V> {
        private int leavesSkipped;
        private int leavesToSkip;
        private int size;
        private Node<K, V> stack;

        AvlBuilder() {
        }

        /* access modifiers changed from: package-private */
        public Node<K, V> a() {
            Node<K, V> node = this.stack;
            if (node.a == null) {
                return node;
            }
            throw new IllegalStateException();
        }

        /* access modifiers changed from: package-private */
        public void a(int i) {
            this.leavesToSkip = ((Integer.highestOneBit(i) * 2) - 1) - i;
            this.size = 0;
            this.leavesSkipped = 0;
            this.stack = null;
        }

        /* access modifiers changed from: package-private */
        public void a(Node<K, V> node) {
            node.c = null;
            node.a = null;
            node.b = null;
            node.i = 1;
            int i = this.leavesToSkip;
            if (i > 0) {
                int i2 = this.size;
                if ((i2 & 1) == 0) {
                    this.size = i2 + 1;
                    this.leavesToSkip = i - 1;
                    this.leavesSkipped++;
                }
            }
            node.a = this.stack;
            this.stack = node;
            this.size++;
            int i3 = this.leavesToSkip;
            if (i3 > 0) {
                int i4 = this.size;
                if ((i4 & 1) == 0) {
                    this.size = i4 + 1;
                    this.leavesToSkip = i3 - 1;
                    this.leavesSkipped++;
                }
            }
            int i5 = 4;
            while (true) {
                int i6 = i5 - 1;
                if ((this.size & i6) == i6) {
                    int i7 = this.leavesSkipped;
                    if (i7 == 0) {
                        Node<K, V> node2 = this.stack;
                        Node<K, V> node3 = node2.a;
                        Node<K, V> node4 = node3.a;
                        node3.a = node4.a;
                        this.stack = node3;
                        node3.b = node4;
                        node3.c = node2;
                        node3.i = node2.i + 1;
                        node4.a = node3;
                        node2.a = node3;
                    } else {
                        if (i7 == 1) {
                            Node<K, V> node5 = this.stack;
                            Node<K, V> node6 = node5.a;
                            this.stack = node6;
                            node6.c = node5;
                            node6.i = node5.i + 1;
                            node5.a = node6;
                        } else if (i7 != 2) {
                        }
                        this.leavesSkipped = 0;
                    }
                    i5 *= 2;
                } else {
                    return;
                }
            }
        }
    }

    static class AvlIterator<K, V> {
        private Node<K, V> stackTop;

        AvlIterator() {
        }

        /* access modifiers changed from: package-private */
        public void a(Node<K, V> node) {
            Node<K, V> node2 = null;
            while (true) {
                Node<K, V> node3 = node2;
                node2 = node;
                Node<K, V> node4 = node3;
                if (node2 != null) {
                    node2.a = node4;
                    node = node2.b;
                } else {
                    this.stackTop = node4;
                    return;
                }
            }
        }

        public Node<K, V> next() {
            Node<K, V> node = this.stackTop;
            if (node == null) {
                return null;
            }
            Node<K, V> node2 = node.a;
            node.a = null;
            Node<K, V> node3 = node.c;
            while (true) {
                Node<K, V> node4 = node2;
                node2 = node3;
                Node<K, V> node5 = node4;
                if (node2 != null) {
                    node2.a = node5;
                    node3 = node2.b;
                } else {
                    this.stackTop = node5;
                    return node;
                }
            }
        }
    }

    final class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        public void clear() {
            LinkedHashTreeMap.this.clear();
        }

        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && LinkedHashTreeMap.this.a((Map.Entry<?, ?>) (Map.Entry) obj) != null;
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new LinkedHashTreeMap<K, V>.LinkedTreeMapIterator<Map.Entry<K, V>>() {
                {
                    LinkedHashTreeMap linkedHashTreeMap = LinkedHashTreeMap.this;
                }

                public Map.Entry<K, V> next() {
                    return a();
                }
            };
        }

        public boolean remove(Object obj) {
            Node a2;
            if (!(obj instanceof Map.Entry) || (a2 = LinkedHashTreeMap.this.a((Map.Entry<?, ?>) (Map.Entry) obj)) == null) {
                return false;
            }
            LinkedHashTreeMap.this.a(a2, true);
            return true;
        }

        public int size() {
            return LinkedHashTreeMap.this.d;
        }
    }

    final class KeySet extends AbstractSet<K> {
        KeySet() {
        }

        public void clear() {
            LinkedHashTreeMap.this.clear();
        }

        public boolean contains(Object obj) {
            return LinkedHashTreeMap.this.containsKey(obj);
        }

        public Iterator<K> iterator() {
            return new LinkedHashTreeMap<K, V>.LinkedTreeMapIterator<K>() {
                {
                    LinkedHashTreeMap linkedHashTreeMap = LinkedHashTreeMap.this;
                }

                public K next() {
                    return a().f;
                }
            };
        }

        public boolean remove(Object obj) {
            return LinkedHashTreeMap.this.b(obj) != null;
        }

        public int size() {
            return LinkedHashTreeMap.this.d;
        }
    }

    private abstract class LinkedTreeMapIterator<T> implements Iterator<T> {
        Node<K, V> b = LinkedHashTreeMap.this.c.d;
        Node<K, V> c = null;
        int d = LinkedHashTreeMap.this.e;

        LinkedTreeMapIterator() {
        }

        /* access modifiers changed from: package-private */
        public final Node<K, V> a() {
            Node<K, V> node = this.b;
            if (node == LinkedHashTreeMap.this.c) {
                throw new NoSuchElementException();
            } else if (LinkedHashTreeMap.this.e == this.d) {
                this.b = node.d;
                this.c = node;
                return node;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        public final boolean hasNext() {
            return this.b != LinkedHashTreeMap.this.c;
        }

        public final void remove() {
            Node<K, V> node = this.c;
            if (node != null) {
                LinkedHashTreeMap.this.a(node, true);
                this.c = null;
                this.d = LinkedHashTreeMap.this.e;
                return;
            }
            throw new IllegalStateException();
        }
    }

    static final class Node<K, V> implements Map.Entry<K, V> {
        Node<K, V> a;
        Node<K, V> b;
        Node<K, V> c;
        Node<K, V> d;
        Node<K, V> e;
        final K f;
        final int g;
        V h;
        int i;

        Node() {
            this.f = null;
            this.g = -1;
            this.e = this;
            this.d = this;
        }

        Node(Node<K, V> node, K k, int i2, Node<K, V> node2, Node<K, V> node3) {
            this.a = node;
            this.f = k;
            this.g = i2;
            this.i = 1;
            this.d = node2;
            this.e = node3;
            node3.d = this;
            node2.e = this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:14:0x0031 A[ORIG_RETURN, RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r4) {
            /*
                r3 = this;
                boolean r0 = r4 instanceof java.util.Map.Entry
                r1 = 0
                if (r0 == 0) goto L_0x0032
                java.util.Map$Entry r4 = (java.util.Map.Entry) r4
                K r0 = r3.f
                if (r0 != 0) goto L_0x0012
                java.lang.Object r0 = r4.getKey()
                if (r0 != 0) goto L_0x0032
                goto L_0x001c
            L_0x0012:
                java.lang.Object r2 = r4.getKey()
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0032
            L_0x001c:
                V r0 = r3.h
                if (r0 != 0) goto L_0x0027
                java.lang.Object r4 = r4.getValue()
                if (r4 != 0) goto L_0x0032
                goto L_0x0031
            L_0x0027:
                java.lang.Object r4 = r4.getValue()
                boolean r4 = r0.equals(r4)
                if (r4 == 0) goto L_0x0032
            L_0x0031:
                r1 = 1
            L_0x0032:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.LinkedHashTreeMap.Node.equals(java.lang.Object):boolean");
        }

        public Node<K, V> first() {
            Node<K, V> node = this;
            for (Node<K, V> node2 = this.b; node2 != null; node2 = node2.b) {
                node = node2;
            }
            return node;
        }

        public K getKey() {
            return this.f;
        }

        public V getValue() {
            return this.h;
        }

        public int hashCode() {
            K k = this.f;
            int i2 = 0;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.h;
            if (v != null) {
                i2 = v.hashCode();
            }
            return hashCode ^ i2;
        }

        public Node<K, V> last() {
            Node<K, V> node = this;
            for (Node<K, V> node2 = this.c; node2 != null; node2 = node2.c) {
                node = node2;
            }
            return node;
        }

        public V setValue(V v) {
            V v2 = this.h;
            this.h = v;
            return v2;
        }

        public String toString() {
            return this.f + "=" + this.h;
        }
    }

    public LinkedHashTreeMap() {
        this(NATURAL_ORDER);
    }

    public LinkedHashTreeMap(Comparator<? super K> comparator) {
        this.d = 0;
        this.e = 0;
        this.a = comparator == null ? NATURAL_ORDER : comparator;
        this.c = new Node<>();
        this.b = new Node[16];
        Node<K, V>[] nodeArr = this.b;
        this.f = (nodeArr.length / 2) + (nodeArr.length / 4);
    }

    static <K, V> Node<K, V>[] a(Node<K, V>[] nodeArr) {
        int length = nodeArr.length;
        Node<K, V>[] nodeArr2 = new Node[(length * 2)];
        AvlIterator avlIterator = new AvlIterator();
        AvlBuilder avlBuilder = new AvlBuilder();
        AvlBuilder avlBuilder2 = new AvlBuilder();
        for (int i = 0; i < length; i++) {
            Node<K, V> node = nodeArr[i];
            if (node != null) {
                avlIterator.a(node);
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    Node next = avlIterator.next();
                    if (next == null) {
                        break;
                    } else if ((next.g & length) == 0) {
                        i2++;
                    } else {
                        i3++;
                    }
                }
                avlBuilder.a(i2);
                avlBuilder2.a(i3);
                avlIterator.a(node);
                while (true) {
                    Node next2 = avlIterator.next();
                    if (next2 == null) {
                        break;
                    } else if ((next2.g & length) == 0) {
                        avlBuilder.a(next2);
                    } else {
                        avlBuilder2.a(next2);
                    }
                }
                Node<K, V> node2 = null;
                nodeArr2[i] = i2 > 0 ? avlBuilder.a() : null;
                int i4 = i + length;
                if (i3 > 0) {
                    node2 = avlBuilder2.a();
                }
                nodeArr2[i4] = node2;
            }
        }
        return nodeArr2;
    }

    private void doubleCapacity() {
        this.b = a(this.b);
        Node<K, V>[] nodeArr = this.b;
        this.f = (nodeArr.length / 2) + (nodeArr.length / 4);
    }

    private boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private void rebalance(Node<K, V> node, boolean z) {
        while (node != null) {
            Node<K, V> node2 = node.b;
            Node<K, V> node3 = node.c;
            int i = 0;
            int i2 = node2 != null ? node2.i : 0;
            int i3 = node3 != null ? node3.i : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                Node<K, V> node4 = node3.b;
                Node<K, V> node5 = node3.c;
                int i5 = node5 != null ? node5.i : 0;
                if (node4 != null) {
                    i = node4.i;
                }
                int i6 = i - i5;
                if (i6 != -1 && (i6 != 0 || z)) {
                    if (g || i6 == 1) {
                        rotateRight(node3);
                    } else {
                        throw new AssertionError();
                    }
                }
                rotateLeft(node);
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                Node<K, V> node6 = node2.b;
                Node<K, V> node7 = node2.c;
                int i7 = node7 != null ? node7.i : 0;
                if (node6 != null) {
                    i = node6.i;
                }
                int i8 = i - i7;
                if (i8 != 1 && (i8 != 0 || z)) {
                    if (g || i8 == -1) {
                        rotateLeft(node2);
                    } else {
                        throw new AssertionError();
                    }
                }
                rotateRight(node);
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                node.i = i2 + 1;
                if (z) {
                    return;
                }
            } else if (g || i4 == -1 || i4 == 1) {
                node.i = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            } else {
                throw new AssertionError();
            }
            node = node.a;
        }
    }

    private void replaceInParent(Node<K, V> node, Node<K, V> node2) {
        Node<K, V> node3 = node.a;
        node.a = null;
        if (node2 != null) {
            node2.a = node3;
        }
        if (node3 == null) {
            int i = node.g;
            Node<K, V>[] nodeArr = this.b;
            nodeArr[i & (nodeArr.length - 1)] = node2;
        } else if (node3.b == node) {
            node3.b = node2;
        } else if (g || node3.c == node) {
            node3.c = node2;
        } else {
            throw new AssertionError();
        }
    }

    private void rotateLeft(Node<K, V> node) {
        Node<K, V> node2 = node.b;
        Node<K, V> node3 = node.c;
        Node<K, V> node4 = node3.b;
        Node<K, V> node5 = node3.c;
        node.c = node4;
        if (node4 != null) {
            node4.a = node;
        }
        replaceInParent(node, node3);
        node3.b = node;
        node.a = node3;
        int i = 0;
        node.i = Math.max(node2 != null ? node2.i : 0, node4 != null ? node4.i : 0) + 1;
        int i2 = node.i;
        if (node5 != null) {
            i = node5.i;
        }
        node3.i = Math.max(i2, i) + 1;
    }

    private void rotateRight(Node<K, V> node) {
        Node<K, V> node2 = node.b;
        Node<K, V> node3 = node.c;
        Node<K, V> node4 = node2.b;
        Node<K, V> node5 = node2.c;
        node.b = node5;
        if (node5 != null) {
            node5.a = node;
        }
        replaceInParent(node, node2);
        node2.c = node;
        node.a = node2;
        int i = 0;
        node.i = Math.max(node3 != null ? node3.i : 0, node5 != null ? node5.i : 0) + 1;
        int i2 = node.i;
        if (node4 != null) {
            i = node4.i;
        }
        node2.i = Math.max(i2, i) + 1;
    }

    private static int secondaryHash(int i) {
        int i2 = i ^ ((i >>> 20) ^ (i >>> 12));
        return (i2 >>> 4) ^ ((i2 >>> 7) ^ i2);
    }

    private Object writeReplace() {
        return new LinkedHashMap(this);
    }

    /* access modifiers changed from: package-private */
    public Node<K, V> a(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return a(obj, false);
        } catch (ClassCastException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Node<K, V> a(K k, boolean z) {
        int i;
        Node<K, V> node;
        Node<K, V> node2;
        Comparator<? super K> comparator = this.a;
        Node<K, V>[] nodeArr = this.b;
        int secondaryHash = secondaryHash(k.hashCode());
        int length = (nodeArr.length - 1) & secondaryHash;
        Node<K, V> node3 = nodeArr[length];
        if (node3 != null) {
            Comparable comparable = comparator == NATURAL_ORDER ? (Comparable) k : null;
            while (true) {
                int compareTo = comparable != null ? comparable.compareTo(node3.f) : comparator.compare(k, node3.f);
                if (compareTo == 0) {
                    return node3;
                }
                Node<K, V> node4 = compareTo < 0 ? node3.b : node3.c;
                if (node4 == null) {
                    node = node3;
                    i = compareTo;
                    break;
                }
                node3 = node4;
            }
        } else {
            node = node3;
            i = 0;
        }
        if (!z) {
            return null;
        }
        Node<K, V> node5 = this.c;
        if (node != null) {
            node2 = new Node<>(node, k, secondaryHash, node5, node5.e);
            if (i < 0) {
                node.b = node2;
            } else {
                node.c = node2;
            }
            rebalance(node, true);
        } else if (comparator != NATURAL_ORDER || (k instanceof Comparable)) {
            node2 = new Node<>(node, k, secondaryHash, node5, node5.e);
            nodeArr[length] = node2;
        } else {
            throw new ClassCastException(k.getClass().getName() + " is not Comparable");
        }
        int i2 = this.d;
        this.d = i2 + 1;
        if (i2 > this.f) {
            doubleCapacity();
        }
        this.e++;
        return node2;
    }

    /* access modifiers changed from: package-private */
    public Node<K, V> a(Map.Entry<?, ?> entry) {
        Node<K, V> a2 = a((Object) entry.getKey());
        if (a2 != null && equal(a2.h, entry.getValue())) {
            return a2;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void a(Node<K, V> node, boolean z) {
        int i;
        if (z) {
            node.e.d = node.d;
            node.d.e = node.e;
            node.e = null;
            node.d = null;
        }
        Node<K, V> node2 = node.b;
        Node<K, V> node3 = node.c;
        Node<K, V> node4 = node.a;
        int i2 = 0;
        if (node2 == null || node3 == null) {
            if (node2 != null) {
                replaceInParent(node, node2);
                node.b = null;
            } else if (node3 != null) {
                replaceInParent(node, node3);
                node.c = null;
            } else {
                replaceInParent(node, (Node<K, V>) null);
            }
            rebalance(node4, false);
            this.d--;
            this.e++;
            return;
        }
        Node<K, V> last = node2.i > node3.i ? node2.last() : node3.first();
        a(last, false);
        Node<K, V> node5 = node.b;
        if (node5 != null) {
            i = node5.i;
            last.b = node5;
            node5.a = last;
            node.b = null;
        } else {
            i = 0;
        }
        Node<K, V> node6 = node.c;
        if (node6 != null) {
            i2 = node6.i;
            last.c = node6;
            node6.a = last;
            node.c = null;
        }
        last.i = Math.max(i, i2) + 1;
        replaceInParent(node, last);
    }

    /* access modifiers changed from: package-private */
    public Node<K, V> b(Object obj) {
        Node<K, V> a2 = a(obj);
        if (a2 != null) {
            a(a2, true);
        }
        return a2;
    }

    public void clear() {
        Arrays.fill(this.b, (Object) null);
        this.d = 0;
        this.e++;
        Node<K, V> node = this.c;
        Node<K, V> node2 = node.d;
        while (node2 != node) {
            Node<K, V> node3 = node2.d;
            node2.e = null;
            node2.d = null;
            node2 = node3;
        }
        node.e = node;
        node.d = node;
    }

    public boolean containsKey(Object obj) {
        return a(obj) != null;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        LinkedHashTreeMap<K, V>.EntrySet entrySet2 = this.entrySet;
        if (entrySet2 != null) {
            return entrySet2;
        }
        LinkedHashTreeMap<K, V>.EntrySet entrySet3 = new EntrySet();
        this.entrySet = entrySet3;
        return entrySet3;
    }

    public V get(Object obj) {
        Node a2 = a(obj);
        if (a2 != null) {
            return a2.h;
        }
        return null;
    }

    public Set<K> keySet() {
        LinkedHashTreeMap<K, V>.KeySet keySet2 = this.keySet;
        if (keySet2 != null) {
            return keySet2;
        }
        LinkedHashTreeMap<K, V>.KeySet keySet3 = new KeySet();
        this.keySet = keySet3;
        return keySet3;
    }

    public V put(K k, V v) {
        if (k != null) {
            Node a2 = a(k, true);
            V v2 = a2.h;
            a2.h = v;
            return v2;
        }
        throw new NullPointerException("key == null");
    }

    public V remove(Object obj) {
        Node b2 = b(obj);
        if (b2 != null) {
            return b2.h;
        }
        return null;
    }

    public int size() {
        return this.d;
    }
}

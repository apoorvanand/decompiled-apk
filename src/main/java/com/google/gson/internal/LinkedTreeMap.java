package com.google.gson.internal;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    private static final Comparator<Comparable> NATURAL_ORDER = new Comparator<Comparable>() {
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };
    static final /* synthetic */ boolean f = (!LinkedTreeMap.class.desiredAssertionStatus());
    Comparator<? super K> a;
    Node<K, V> b;
    int c;
    int d;
    final Node<K, V> e;
    private LinkedTreeMap<K, V>.EntrySet entrySet;
    private LinkedTreeMap<K, V>.KeySet keySet;

    class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        EntrySet() {
        }

        public void clear() {
            LinkedTreeMap.this.clear();
        }

        public boolean contains(Object obj) {
            return (obj instanceof Map.Entry) && LinkedTreeMap.this.a((Map.Entry<?, ?>) (Map.Entry) obj) != null;
        }

        public Iterator<Map.Entry<K, V>> iterator() {
            return new LinkedTreeMap<K, V>.LinkedTreeMapIterator<Map.Entry<K, V>>() {
                {
                    LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
                }

                public Map.Entry<K, V> next() {
                    return a();
                }
            };
        }

        public boolean remove(Object obj) {
            Node a2;
            if (!(obj instanceof Map.Entry) || (a2 = LinkedTreeMap.this.a((Map.Entry<?, ?>) (Map.Entry) obj)) == null) {
                return false;
            }
            LinkedTreeMap.this.a(a2, true);
            return true;
        }

        public int size() {
            return LinkedTreeMap.this.c;
        }
    }

    final class KeySet extends AbstractSet<K> {
        KeySet() {
        }

        public void clear() {
            LinkedTreeMap.this.clear();
        }

        public boolean contains(Object obj) {
            return LinkedTreeMap.this.containsKey(obj);
        }

        public Iterator<K> iterator() {
            return new LinkedTreeMap<K, V>.LinkedTreeMapIterator<K>() {
                {
                    LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
                }

                public K next() {
                    return a().f;
                }
            };
        }

        public boolean remove(Object obj) {
            return LinkedTreeMap.this.b(obj) != null;
        }

        public int size() {
            return LinkedTreeMap.this.c;
        }
    }

    private abstract class LinkedTreeMapIterator<T> implements Iterator<T> {
        Node<K, V> b = LinkedTreeMap.this.e.d;
        Node<K, V> c = null;
        int d = LinkedTreeMap.this.d;

        LinkedTreeMapIterator() {
        }

        /* access modifiers changed from: package-private */
        public final Node<K, V> a() {
            Node<K, V> node = this.b;
            if (node == LinkedTreeMap.this.e) {
                throw new NoSuchElementException();
            } else if (LinkedTreeMap.this.d == this.d) {
                this.b = node.d;
                this.c = node;
                return node;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        public final boolean hasNext() {
            return this.b != LinkedTreeMap.this.e;
        }

        public final void remove() {
            Node<K, V> node = this.c;
            if (node != null) {
                LinkedTreeMap.this.a(node, true);
                this.c = null;
                this.d = LinkedTreeMap.this.d;
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
        V g;
        int h;

        Node() {
            this.f = null;
            this.e = this;
            this.d = this;
        }

        Node(Node<K, V> node, K k, Node<K, V> node2, Node<K, V> node3) {
            this.a = node;
            this.f = k;
            this.h = 1;
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
                V r0 = r3.g
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.LinkedTreeMap.Node.equals(java.lang.Object):boolean");
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
            return this.g;
        }

        public int hashCode() {
            K k = this.f;
            int i = 0;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.g;
            if (v != null) {
                i = v.hashCode();
            }
            return hashCode ^ i;
        }

        public Node<K, V> last() {
            Node<K, V> node = this;
            for (Node<K, V> node2 = this.c; node2 != null; node2 = node2.c) {
                node = node2;
            }
            return node;
        }

        public V setValue(V v) {
            V v2 = this.g;
            this.g = v;
            return v2;
        }

        public String toString() {
            return this.f + "=" + this.g;
        }
    }

    public LinkedTreeMap() {
        this(NATURAL_ORDER);
    }

    public LinkedTreeMap(Comparator<? super K> comparator) {
        this.c = 0;
        this.d = 0;
        this.e = new Node<>();
        this.a = comparator == null ? NATURAL_ORDER : comparator;
    }

    private boolean equal(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private void rebalance(Node<K, V> node, boolean z) {
        while (node != null) {
            Node<K, V> node2 = node.b;
            Node<K, V> node3 = node.c;
            int i = 0;
            int i2 = node2 != null ? node2.h : 0;
            int i3 = node3 != null ? node3.h : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                Node<K, V> node4 = node3.b;
                Node<K, V> node5 = node3.c;
                int i5 = node5 != null ? node5.h : 0;
                if (node4 != null) {
                    i = node4.h;
                }
                int i6 = i - i5;
                if (i6 != -1 && (i6 != 0 || z)) {
                    if (f || i6 == 1) {
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
                int i7 = node7 != null ? node7.h : 0;
                if (node6 != null) {
                    i = node6.h;
                }
                int i8 = i - i7;
                if (i8 != 1 && (i8 != 0 || z)) {
                    if (f || i8 == -1) {
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
                node.h = i2 + 1;
                if (z) {
                    return;
                }
            } else if (f || i4 == -1 || i4 == 1) {
                node.h = Math.max(i2, i3) + 1;
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
            this.b = node2;
        } else if (node3.b == node) {
            node3.b = node2;
        } else if (f || node3.c == node) {
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
        node.h = Math.max(node2 != null ? node2.h : 0, node4 != null ? node4.h : 0) + 1;
        int i2 = node.h;
        if (node5 != null) {
            i = node5.h;
        }
        node3.h = Math.max(i2, i) + 1;
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
        node.h = Math.max(node3 != null ? node3.h : 0, node5 != null ? node5.h : 0) + 1;
        int i2 = node.h;
        if (node4 != null) {
            i = node4.h;
        }
        node2.h = Math.max(i2, i) + 1;
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
        Comparator<? super K> comparator = this.a;
        Node<K, V> node2 = this.b;
        if (node2 != null) {
            Comparable comparable = comparator == NATURAL_ORDER ? (Comparable) k : null;
            while (true) {
                i = comparable != null ? comparable.compareTo(node2.f) : comparator.compare(k, node2.f);
                if (i == 0) {
                    return node2;
                }
                Node<K, V> node3 = i < 0 ? node2.b : node2.c;
                if (node3 == null) {
                    break;
                }
                node2 = node3;
            }
        } else {
            i = 0;
        }
        if (!z) {
            return null;
        }
        Node<K, V> node4 = this.e;
        if (node2 != null) {
            node = new Node<>(node2, k, node4, node4.e);
            if (i < 0) {
                node2.b = node;
            } else {
                node2.c = node;
            }
            rebalance(node2, true);
        } else if (comparator != NATURAL_ORDER || (k instanceof Comparable)) {
            node = new Node<>(node2, k, node4, node4.e);
            this.b = node;
        } else {
            throw new ClassCastException(k.getClass().getName() + " is not Comparable");
        }
        this.c++;
        this.d++;
        return node;
    }

    /* access modifiers changed from: package-private */
    public Node<K, V> a(Map.Entry<?, ?> entry) {
        Node<K, V> a2 = a((Object) entry.getKey());
        if (a2 != null && equal(a2.g, entry.getValue())) {
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
            this.c--;
            this.d++;
            return;
        }
        Node<K, V> last = node2.h > node3.h ? node2.last() : node3.first();
        a(last, false);
        Node<K, V> node5 = node.b;
        if (node5 != null) {
            i = node5.h;
            last.b = node5;
            node5.a = last;
            node.b = null;
        } else {
            i = 0;
        }
        Node<K, V> node6 = node.c;
        if (node6 != null) {
            i2 = node6.h;
            last.c = node6;
            node6.a = last;
            node.c = null;
        }
        last.h = Math.max(i, i2) + 1;
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
        this.b = null;
        this.c = 0;
        this.d++;
        Node<K, V> node = this.e;
        node.e = node;
        node.d = node;
    }

    public boolean containsKey(Object obj) {
        return a(obj) != null;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        LinkedTreeMap<K, V>.EntrySet entrySet2 = this.entrySet;
        if (entrySet2 != null) {
            return entrySet2;
        }
        LinkedTreeMap<K, V>.EntrySet entrySet3 = new EntrySet();
        this.entrySet = entrySet3;
        return entrySet3;
    }

    public V get(Object obj) {
        Node a2 = a(obj);
        if (a2 != null) {
            return a2.g;
        }
        return null;
    }

    public Set<K> keySet() {
        LinkedTreeMap<K, V>.KeySet keySet2 = this.keySet;
        if (keySet2 != null) {
            return keySet2;
        }
        LinkedTreeMap<K, V>.KeySet keySet3 = new KeySet();
        this.keySet = keySet3;
        return keySet3;
    }

    public V put(K k, V v) {
        if (k != null) {
            Node a2 = a(k, true);
            V v2 = a2.g;
            a2.g = v;
            return v2;
        }
        throw new NullPointerException("key == null");
    }

    public V remove(Object obj) {
        Node b2 = b(obj);
        if (b2 != null) {
            return b2.g;
        }
        return null;
    }

    public int size() {
        return this.c;
    }
}

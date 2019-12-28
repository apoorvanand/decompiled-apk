package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

final class DirectedGraphConnections<N, V> implements GraphConnections<N, V> {
    private static final Object PRED = new Object();
    /* access modifiers changed from: private */
    public final Map<N, Object> adjacentNodeValues;
    /* access modifiers changed from: private */
    public int predecessorCount;
    /* access modifiers changed from: private */
    public int successorCount;

    private static final class PredAndSucc {
        /* access modifiers changed from: private */
        public final Object successorValue;

        PredAndSucc(Object obj) {
            this.successorValue = obj;
        }
    }

    private DirectedGraphConnections(Map<N, Object> map, int i, int i2) {
        this.adjacentNodeValues = (Map) Preconditions.checkNotNull(map);
        this.predecessorCount = Graphs.a(i);
        this.successorCount = Graphs.a(i2);
        Preconditions.checkState(i <= map.size() && i2 <= map.size());
    }

    static <N, V> DirectedGraphConnections<N, V> a() {
        return new DirectedGraphConnections<>(new HashMap(4, 1.0f), 0, 0);
    }

    static <N, V> DirectedGraphConnections<N, V> a(Set<N> set, Map<N, V> map) {
        HashMap hashMap = new HashMap();
        hashMap.putAll(map);
        for (N next : set) {
            Object put = hashMap.put(next, PRED);
            if (put != null) {
                hashMap.put(next, new PredAndSucc(put));
            }
        }
        return new DirectedGraphConnections<>(ImmutableMap.copyOf(hashMap), set.size(), map.size());
    }

    /* access modifiers changed from: private */
    public static boolean isPredecessor(@Nullable Object obj) {
        return obj == PRED || (obj instanceof PredAndSucc);
    }

    /* access modifiers changed from: private */
    public static boolean isSuccessor(@Nullable Object obj) {
        return (obj == PRED || obj == null) ? false : true;
    }

    public void addPredecessor(N n, V v) {
        Object put = this.adjacentNodeValues.put(n, PRED);
        if (put != null) {
            if (put instanceof PredAndSucc) {
                this.adjacentNodeValues.put(n, put);
                return;
            } else if (put != PRED) {
                this.adjacentNodeValues.put(n, new PredAndSucc(put));
            } else {
                return;
            }
        }
        int i = this.predecessorCount + 1;
        this.predecessorCount = i;
        Graphs.b(i);
    }

    public V addSuccessor(N n, V v) {
        V put = this.adjacentNodeValues.put(n, v);
        if (put != null) {
            if (put instanceof PredAndSucc) {
                this.adjacentNodeValues.put(n, new PredAndSucc(v));
                return ((PredAndSucc) put).successorValue;
            } else if (put != PRED) {
                return put;
            } else {
                this.adjacentNodeValues.put(n, new PredAndSucc(v));
            }
        }
        int i = this.successorCount + 1;
        this.successorCount = i;
        Graphs.b(i);
        return null;
    }

    public Set<N> adjacentNodes() {
        return Collections.unmodifiableSet(this.adjacentNodeValues.keySet());
    }

    public Set<N> predecessors() {
        return new AbstractSet<N>() {
            public boolean contains(@Nullable Object obj) {
                return DirectedGraphConnections.isPredecessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }

            public UnmodifiableIterator<N> iterator() {
                final Iterator it = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                return new AbstractIterator<N>() {
                    /* access modifiers changed from: protected */
                    public N computeNext() {
                        while (it.hasNext()) {
                            Map.Entry entry = (Map.Entry) it.next();
                            if (DirectedGraphConnections.isPredecessor(entry.getValue())) {
                                return entry.getKey();
                            }
                        }
                        return a();
                    }
                };
            }

            public int size() {
                return DirectedGraphConnections.this.predecessorCount;
            }
        };
    }

    public void removePredecessor(Object obj) {
        Object obj2 = this.adjacentNodeValues.get(obj);
        if (obj2 == PRED) {
            this.adjacentNodeValues.remove(obj);
        } else if (obj2 instanceof PredAndSucc) {
            this.adjacentNodeValues.put(obj, ((PredAndSucc) obj2).successorValue);
        } else {
            return;
        }
        int i = this.predecessorCount - 1;
        this.predecessorCount = i;
        Graphs.a(i);
    }

    public V removeSuccessor(Object obj) {
        V v;
        V v2 = this.adjacentNodeValues.get(obj);
        if (v2 == null || v2 == (v = PRED)) {
            return null;
        }
        if (v2 instanceof PredAndSucc) {
            this.adjacentNodeValues.put(obj, v);
            int i = this.successorCount - 1;
            this.successorCount = i;
            Graphs.a(i);
            return ((PredAndSucc) v2).successorValue;
        }
        this.adjacentNodeValues.remove(obj);
        int i2 = this.successorCount - 1;
        this.successorCount = i2;
        Graphs.a(i2);
        return v2;
    }

    public Set<N> successors() {
        return new AbstractSet<N>() {
            public boolean contains(@Nullable Object obj) {
                return DirectedGraphConnections.isSuccessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }

            public UnmodifiableIterator<N> iterator() {
                final Iterator it = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                return new AbstractIterator<N>() {
                    /* access modifiers changed from: protected */
                    public N computeNext() {
                        while (it.hasNext()) {
                            Map.Entry entry = (Map.Entry) it.next();
                            if (DirectedGraphConnections.isSuccessor(entry.getValue())) {
                                return entry.getKey();
                            }
                        }
                        return a();
                    }
                };
            }

            public int size() {
                return DirectedGraphConnections.this.successorCount;
            }
        };
    }

    public V value(Object obj) {
        V v = this.adjacentNodeValues.get(obj);
        if (v == PRED) {
            return null;
        }
        return v instanceof PredAndSucc ? ((PredAndSucc) v).successorValue : v;
    }
}

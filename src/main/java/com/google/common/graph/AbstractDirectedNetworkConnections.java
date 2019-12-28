package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.math.IntMath;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

abstract class AbstractDirectedNetworkConnections<N, E> implements NetworkConnections<N, E> {
    protected final Map<E, N> a;
    protected final Map<E, N> b;
    /* access modifiers changed from: private */
    public int selfLoopCount;

    protected AbstractDirectedNetworkConnections(Map<E, N> map, Map<E, N> map2, int i) {
        this.a = (Map) Preconditions.checkNotNull(map);
        this.b = (Map) Preconditions.checkNotNull(map2);
        this.selfLoopCount = Graphs.a(i);
        Preconditions.checkState(i <= map.size() && i <= map2.size());
    }

    public void addInEdge(E e, N n, boolean z) {
        boolean z2 = true;
        if (z) {
            int i = this.selfLoopCount + 1;
            this.selfLoopCount = i;
            Graphs.b(i);
        }
        if (this.a.put(e, n) != null) {
            z2 = false;
        }
        Preconditions.checkState(z2);
    }

    public void addOutEdge(E e, N n) {
        Preconditions.checkState(this.b.put(e, n) == null);
    }

    public Set<N> adjacentNodes() {
        return Sets.union(predecessors(), successors());
    }

    public Set<E> inEdges() {
        return Collections.unmodifiableSet(this.a.keySet());
    }

    public Set<E> incidentEdges() {
        return new AbstractSet<E>() {
            public boolean contains(@Nullable Object obj) {
                return AbstractDirectedNetworkConnections.this.a.containsKey(obj) || AbstractDirectedNetworkConnections.this.b.containsKey(obj);
            }

            public UnmodifiableIterator<E> iterator() {
                return Iterators.unmodifiableIterator((AbstractDirectedNetworkConnections.this.selfLoopCount == 0 ? Iterables.concat(AbstractDirectedNetworkConnections.this.a.keySet(), AbstractDirectedNetworkConnections.this.b.keySet()) : Sets.union(AbstractDirectedNetworkConnections.this.a.keySet(), AbstractDirectedNetworkConnections.this.b.keySet())).iterator());
            }

            public int size() {
                return IntMath.saturatedAdd(AbstractDirectedNetworkConnections.this.a.size(), AbstractDirectedNetworkConnections.this.b.size() - AbstractDirectedNetworkConnections.this.selfLoopCount);
            }
        };
    }

    public N oppositeNode(Object obj) {
        return Preconditions.checkNotNull(this.b.get(obj));
    }

    public Set<E> outEdges() {
        return Collections.unmodifiableSet(this.b.keySet());
    }

    public N removeInEdge(Object obj, boolean z) {
        if (z) {
            int i = this.selfLoopCount - 1;
            this.selfLoopCount = i;
            Graphs.a(i);
        }
        return Preconditions.checkNotNull(this.a.remove(obj));
    }

    public N removeOutEdge(Object obj) {
        return Preconditions.checkNotNull(this.b.remove(obj));
    }
}

package com.google.common.graph;

import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

abstract class AbstractUndirectedNetworkConnections<N, E> implements NetworkConnections<N, E> {
    protected final Map<E, N> a;

    protected AbstractUndirectedNetworkConnections(Map<E, N> map) {
        this.a = (Map) Preconditions.checkNotNull(map);
    }

    public void addInEdge(E e, N n, boolean z) {
        if (!z) {
            addOutEdge(e, n);
        }
    }

    public void addOutEdge(E e, N n) {
        Preconditions.checkState(this.a.put(e, n) == null);
    }

    public Set<E> inEdges() {
        return incidentEdges();
    }

    public Set<E> incidentEdges() {
        return Collections.unmodifiableSet(this.a.keySet());
    }

    public N oppositeNode(Object obj) {
        return Preconditions.checkNotNull(this.a.get(obj));
    }

    public Set<E> outEdges() {
        return incidentEdges();
    }

    public Set<N> predecessors() {
        return adjacentNodes();
    }

    public N removeInEdge(Object obj, boolean z) {
        if (!z) {
            return removeOutEdge(obj);
        }
        return null;
    }

    public N removeOutEdge(Object obj) {
        return Preconditions.checkNotNull(this.a.remove(obj));
    }

    public Set<N> successors() {
        return adjacentNodes();
    }
}

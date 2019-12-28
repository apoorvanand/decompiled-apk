package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

final class UndirectedGraphConnections<N, V> implements GraphConnections<N, V> {
    private final Map<N, V> adjacentNodeValues;

    private UndirectedGraphConnections(Map<N, V> map) {
        this.adjacentNodeValues = (Map) Preconditions.checkNotNull(map);
    }

    static <N, V> UndirectedGraphConnections<N, V> a() {
        return new UndirectedGraphConnections<>(new HashMap(2, 1.0f));
    }

    static <N, V> UndirectedGraphConnections<N, V> a(Map<N, V> map) {
        return new UndirectedGraphConnections<>(ImmutableMap.copyOf(map));
    }

    public void addPredecessor(N n, V v) {
        addSuccessor(n, v);
    }

    public V addSuccessor(N n, V v) {
        return this.adjacentNodeValues.put(n, v);
    }

    public Set<N> adjacentNodes() {
        return Collections.unmodifiableSet(this.adjacentNodeValues.keySet());
    }

    public Set<N> predecessors() {
        return adjacentNodes();
    }

    public void removePredecessor(Object obj) {
        removeSuccessor(obj);
    }

    public V removeSuccessor(Object obj) {
        return this.adjacentNodeValues.remove(obj);
    }

    public Set<N> successors() {
        return adjacentNodes();
    }

    public V value(Object obj) {
        return this.adjacentNodeValues.get(obj);
    }
}

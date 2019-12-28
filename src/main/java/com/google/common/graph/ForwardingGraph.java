package com.google.common.graph;

import java.util.Set;

abstract class ForwardingGraph<N> extends AbstractGraph<N> {
    ForwardingGraph() {
    }

    public Set<N> adjacentNodes(Object obj) {
        return b().adjacentNodes(obj);
    }

    public boolean allowsSelfLoops() {
        return b().allowsSelfLoops();
    }

    /* access modifiers changed from: protected */
    public abstract Graph<N> b();

    public int degree(Object obj) {
        return b().degree(obj);
    }

    public Set<EndpointPair<N>> edges() {
        return b().edges();
    }

    public int inDegree(Object obj) {
        return b().inDegree(obj);
    }

    public boolean isDirected() {
        return b().isDirected();
    }

    public ElementOrder<N> nodeOrder() {
        return b().nodeOrder();
    }

    public Set<N> nodes() {
        return b().nodes();
    }

    public int outDegree(Object obj) {
        return b().outDegree(obj);
    }

    public Set<N> predecessors(Object obj) {
        return b().predecessors(obj);
    }

    public Set<N> successors(Object obj) {
        return b().successors(obj);
    }
}

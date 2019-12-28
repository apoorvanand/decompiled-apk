package com.google.common.graph;

import com.google.common.graph.GraphConstants;

final class ConfigurableMutableGraph<N> extends ForwardingGraph<N> implements MutableGraph<N> {
    private final MutableValueGraph<N, GraphConstants.Presence> backingValueGraph;

    ConfigurableMutableGraph(AbstractGraphBuilder<? super N> abstractGraphBuilder) {
        this.backingValueGraph = new ConfigurableMutableValueGraph(abstractGraphBuilder);
    }

    public boolean addNode(N n) {
        return this.backingValueGraph.addNode(n);
    }

    /* access modifiers changed from: protected */
    public Graph<N> b() {
        return this.backingValueGraph;
    }

    public boolean putEdge(N n, N n2) {
        return this.backingValueGraph.putEdgeValue(n, n2, GraphConstants.Presence.EDGE_EXISTS) == null;
    }

    public boolean removeEdge(Object obj, Object obj2) {
        return this.backingValueGraph.removeEdge(obj, obj2) != null;
    }

    public boolean removeNode(Object obj) {
        return this.backingValueGraph.removeNode(obj);
    }
}

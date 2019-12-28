package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

final class ConfigurableMutableValueGraph<N, V> extends ConfigurableValueGraph<N, V> implements MutableValueGraph<N, V> {
    ConfigurableMutableValueGraph(AbstractGraphBuilder<? super N> abstractGraphBuilder) {
        super(abstractGraphBuilder);
    }

    @CanIgnoreReturnValue
    private GraphConnections<N, V> addNodeInternal(N n) {
        GraphConnections<N, V> newConnections = newConnections();
        Preconditions.checkState(this.a.put(n, newConnections) == null);
        return newConnections;
    }

    private GraphConnections<N, V> newConnections() {
        return isDirected() ? DirectedGraphConnections.a() : UndirectedGraphConnections.a();
    }

    @CanIgnoreReturnValue
    public boolean addNode(N n) {
        Preconditions.checkNotNull(n, "node");
        if (b(n)) {
            return false;
        }
        addNodeInternal(n);
        return true;
    }

    @CanIgnoreReturnValue
    public V putEdgeValue(N n, N n2, V v) {
        Preconditions.checkNotNull(n, "nodeU");
        Preconditions.checkNotNull(n2, "nodeV");
        Preconditions.checkNotNull(v, "value");
        if (!allowsSelfLoops()) {
            Preconditions.checkArgument(!n.equals(n2), "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.", (Object) n);
        }
        GraphConnections graphConnections = (GraphConnections) this.a.get(n);
        if (graphConnections == null) {
            graphConnections = addNodeInternal(n);
        }
        V addSuccessor = graphConnections.addSuccessor(n2, v);
        GraphConnections graphConnections2 = (GraphConnections) this.a.get(n2);
        if (graphConnections2 == null) {
            graphConnections2 = addNodeInternal(n2);
        }
        graphConnections2.addPredecessor(n, v);
        if (addSuccessor == null) {
            long j = this.b + 1;
            this.b = j;
            Graphs.b(j);
        }
        return addSuccessor;
    }

    @CanIgnoreReturnValue
    public V removeEdge(Object obj, Object obj2) {
        Preconditions.checkNotNull(obj, "nodeU");
        Preconditions.checkNotNull(obj2, "nodeV");
        GraphConnections graphConnections = (GraphConnections) this.a.get(obj);
        GraphConnections graphConnections2 = (GraphConnections) this.a.get(obj2);
        if (graphConnections == null || graphConnections2 == null) {
            return null;
        }
        V removeSuccessor = graphConnections.removeSuccessor(obj2);
        if (removeSuccessor != null) {
            graphConnections2.removePredecessor(obj);
            long j = this.b - 1;
            this.b = j;
            Graphs.a(j);
        }
        return removeSuccessor;
    }

    @CanIgnoreReturnValue
    public boolean removeNode(Object obj) {
        Preconditions.checkNotNull(obj, "node");
        GraphConnections graphConnections = (GraphConnections) this.a.get(obj);
        if (graphConnections == null) {
            return false;
        }
        if (allowsSelfLoops() && graphConnections.removeSuccessor(obj) != null) {
            graphConnections.removePredecessor(obj);
            this.b--;
        }
        for (Object withoutCaching : graphConnections.successors()) {
            ((GraphConnections) this.a.getWithoutCaching(withoutCaching)).removePredecessor(obj);
            this.b--;
        }
        if (isDirected()) {
            for (Object withoutCaching2 : graphConnections.predecessors()) {
                Preconditions.checkState(((GraphConnections) this.a.getWithoutCaching(withoutCaching2)).removeSuccessor(obj) != null);
                this.b--;
            }
        }
        this.a.remove(obj);
        Graphs.a(this.b);
        return true;
    }
}

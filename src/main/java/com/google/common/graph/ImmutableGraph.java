package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.graph.GraphConstants;
import java.util.Set;

@Beta
public abstract class ImmutableGraph<N> extends ForwardingGraph<N> {

    static class ValueBackedImpl<N, V> extends ImmutableGraph<N> {
        protected final ValueGraph<N, V> a;

        ValueBackedImpl(AbstractGraphBuilder<? super N> abstractGraphBuilder, ImmutableMap<N, GraphConnections<N, V>> immutableMap, long j) {
            this.a = new ConfigurableValueGraph(abstractGraphBuilder, immutableMap, j);
        }

        /* access modifiers changed from: protected */
        public Graph<N> b() {
            return this.a;
        }
    }

    ImmutableGraph() {
    }

    private static <N> GraphConnections<N, GraphConstants.Presence> connectionsOf(Graph<N> graph, N n) {
        Function constant = Functions.constant(GraphConstants.Presence.EDGE_EXISTS);
        return graph.isDirected() ? DirectedGraphConnections.a(graph.predecessors(n), Maps.asMap(graph.successors(n), constant)) : UndirectedGraphConnections.a(Maps.asMap(graph.adjacentNodes(n), constant));
    }

    public static <N> ImmutableGraph<N> copyOf(Graph<N> graph) {
        return graph instanceof ImmutableGraph ? (ImmutableGraph) graph : new ValueBackedImpl(GraphBuilder.from(graph), getNodeConnections(graph), (long) graph.edges().size());
    }

    @Deprecated
    public static <N> ImmutableGraph<N> copyOf(ImmutableGraph<N> immutableGraph) {
        return (ImmutableGraph) Preconditions.checkNotNull(immutableGraph);
    }

    private static <N> ImmutableMap<N, GraphConnections<N, GraphConstants.Presence>> getNodeConnections(Graph<N> graph) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (N next : graph.nodes()) {
            builder.put(next, connectionsOf(graph, next));
        }
        return builder.build();
    }

    public /* bridge */ /* synthetic */ Set adjacentNodes(Object obj) {
        return super.adjacentNodes(obj);
    }

    public /* bridge */ /* synthetic */ boolean allowsSelfLoops() {
        return super.allowsSelfLoops();
    }

    public /* bridge */ /* synthetic */ int degree(Object obj) {
        return super.degree(obj);
    }

    public /* bridge */ /* synthetic */ Set edges() {
        return super.edges();
    }

    public /* bridge */ /* synthetic */ int inDegree(Object obj) {
        return super.inDegree(obj);
    }

    public /* bridge */ /* synthetic */ boolean isDirected() {
        return super.isDirected();
    }

    public /* bridge */ /* synthetic */ ElementOrder nodeOrder() {
        return super.nodeOrder();
    }

    public /* bridge */ /* synthetic */ Set nodes() {
        return super.nodes();
    }

    public /* bridge */ /* synthetic */ int outDegree(Object obj) {
        return super.outDegree(obj);
    }

    public /* bridge */ /* synthetic */ Set predecessors(Object obj) {
        return super.predecessors(obj);
    }

    public /* bridge */ /* synthetic */ Set successors(Object obj) {
        return super.successors(obj);
    }
}

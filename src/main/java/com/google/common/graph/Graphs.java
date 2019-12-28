package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@Beta
public final class Graphs {

    private enum NodeVisitState {
        PENDING,
        COMPLETE
    }

    private static class TransposedGraph<N> extends AbstractGraph<N> {
        /* access modifiers changed from: private */
        public final Graph<N> graph;

        TransposedGraph(Graph<N> graph2) {
            this.graph = graph2;
        }

        /* access modifiers changed from: protected */
        public long a() {
            return (long) this.graph.edges().size();
        }

        public Set<N> adjacentNodes(Object obj) {
            return this.graph.adjacentNodes(obj);
        }

        public boolean allowsSelfLoops() {
            return this.graph.allowsSelfLoops();
        }

        public boolean isDirected() {
            return this.graph.isDirected();
        }

        public ElementOrder<N> nodeOrder() {
            return this.graph.nodeOrder();
        }

        public Set<N> nodes() {
            return this.graph.nodes();
        }

        public Set<N> predecessors(Object obj) {
            return this.graph.successors(obj);
        }

        public Set<N> successors(Object obj) {
            return this.graph.predecessors(obj);
        }
    }

    private static class TransposedNetwork<N, E> extends AbstractNetwork<N, E> {
        /* access modifiers changed from: private */
        public final Network<N, E> network;

        TransposedNetwork(Network<N, E> network2) {
            this.network = network2;
        }

        public Set<E> adjacentEdges(Object obj) {
            return this.network.adjacentEdges(obj);
        }

        public Set<N> adjacentNodes(Object obj) {
            return this.network.adjacentNodes(obj);
        }

        public boolean allowsParallelEdges() {
            return this.network.allowsParallelEdges();
        }

        public boolean allowsSelfLoops() {
            return this.network.allowsSelfLoops();
        }

        public ElementOrder<E> edgeOrder() {
            return this.network.edgeOrder();
        }

        public Set<E> edges() {
            return this.network.edges();
        }

        public Set<E> edgesConnecting(Object obj, Object obj2) {
            return this.network.edgesConnecting(obj2, obj);
        }

        public Set<E> inEdges(Object obj) {
            return this.network.outEdges(obj);
        }

        public Set<E> incidentEdges(Object obj) {
            return this.network.incidentEdges(obj);
        }

        public EndpointPair<N> incidentNodes(Object obj) {
            EndpointPair<N> incidentNodes = this.network.incidentNodes(obj);
            return EndpointPair.a(this.network, incidentNodes.nodeV(), incidentNodes.nodeU());
        }

        public boolean isDirected() {
            return this.network.isDirected();
        }

        public ElementOrder<N> nodeOrder() {
            return this.network.nodeOrder();
        }

        public Set<N> nodes() {
            return this.network.nodes();
        }

        public Set<E> outEdges(Object obj) {
            return this.network.inEdges(obj);
        }

        public Set<N> predecessors(Object obj) {
            return this.network.successors(obj);
        }

        public Set<N> successors(Object obj) {
            return this.network.predecessors(obj);
        }
    }

    private static class TransposedValueGraph<N, V> extends AbstractValueGraph<N, V> {
        /* access modifiers changed from: private */
        public final ValueGraph<N, V> graph;

        TransposedValueGraph(ValueGraph<N, V> valueGraph) {
            this.graph = valueGraph;
        }

        /* access modifiers changed from: protected */
        public long a() {
            return (long) this.graph.edges().size();
        }

        public Set<N> adjacentNodes(Object obj) {
            return this.graph.adjacentNodes(obj);
        }

        public boolean allowsSelfLoops() {
            return this.graph.allowsSelfLoops();
        }

        public V edgeValue(Object obj, Object obj2) {
            return this.graph.edgeValue(obj2, obj);
        }

        public V edgeValueOrDefault(Object obj, Object obj2, @Nullable V v) {
            return this.graph.edgeValueOrDefault(obj2, obj, v);
        }

        public boolean isDirected() {
            return this.graph.isDirected();
        }

        public ElementOrder<N> nodeOrder() {
            return this.graph.nodeOrder();
        }

        public Set<N> nodes() {
            return this.graph.nodes();
        }

        public Set<N> predecessors(Object obj) {
            return this.graph.successors(obj);
        }

        public Set<N> successors(Object obj) {
            return this.graph.predecessors(obj);
        }
    }

    private Graphs() {
    }

    @CanIgnoreReturnValue
    static int a(int i) {
        Preconditions.checkArgument(i >= 0, "Not true that %s is non-negative.", i);
        return i;
    }

    @CanIgnoreReturnValue
    static long a(long j) {
        Preconditions.checkArgument(j >= 0, "Not true that %s is non-negative.", j);
        return j;
    }

    @CanIgnoreReturnValue
    static int b(int i) {
        Preconditions.checkArgument(i > 0, "Not true that %s is positive.", i);
        return i;
    }

    @CanIgnoreReturnValue
    static long b(long j) {
        Preconditions.checkArgument(j > 0, "Not true that %s is positive.", j);
        return j;
    }

    private static boolean canTraverseWithoutReusingEdge(Graph<?> graph, Object obj, @Nullable Object obj2) {
        return graph.isDirected() || !Objects.equal(obj2, obj);
    }

    public static <N> MutableGraph<N> copyOf(Graph<N> graph) {
        MutableGraph<N1> build = GraphBuilder.from(graph).expectedNodeCount(graph.nodes().size()).build();
        for (N addNode : graph.nodes()) {
            build.addNode(addNode);
        }
        for (EndpointPair next : graph.edges()) {
            build.putEdge(next.nodeU(), next.nodeV());
        }
        return build;
    }

    public static <N, E> MutableNetwork<N, E> copyOf(Network<N, E> network) {
        MutableNetwork<N1, E1> build = NetworkBuilder.from(network).expectedNodeCount(network.nodes().size()).expectedEdgeCount(network.edges().size()).build();
        for (N addNode : network.nodes()) {
            build.addNode(addNode);
        }
        for (E next : network.edges()) {
            EndpointPair<N> incidentNodes = network.incidentNodes(next);
            build.addEdge(incidentNodes.nodeU(), incidentNodes.nodeV(), next);
        }
        return build;
    }

    public static <N, V> MutableValueGraph<N, V> copyOf(ValueGraph<N, V> valueGraph) {
        MutableValueGraph<N1, V1> build = ValueGraphBuilder.from(valueGraph).expectedNodeCount(valueGraph.nodes().size()).build();
        for (N addNode : valueGraph.nodes()) {
            build.addNode(addNode);
        }
        for (EndpointPair next : valueGraph.edges()) {
            build.putEdgeValue(next.nodeU(), next.nodeV(), valueGraph.edgeValue(next.nodeU(), next.nodeV()));
        }
        return build;
    }

    public static boolean equivalent(@Nullable Graph<?> graph, @Nullable Graph<?> graph2) {
        if (graph == graph2) {
            return true;
        }
        return graph != null && graph2 != null && graph.isDirected() == graph2.isDirected() && graph.nodes().equals(graph2.nodes()) && graph.edges().equals(graph2.edges());
    }

    public static boolean equivalent(@Nullable Network<?, ?> network, @Nullable Network<?, ?> network2) {
        if (network == network2) {
            return true;
        }
        if (network == null || network2 == null || network.isDirected() != network2.isDirected() || !network.nodes().equals(network2.nodes()) || !network.edges().equals(network2.edges())) {
            return false;
        }
        for (Object next : network.edges()) {
            if (!network.incidentNodes(next).equals(network2.incidentNodes(next))) {
                return false;
            }
        }
        return true;
    }

    public static boolean equivalent(@Nullable ValueGraph<?, ?> valueGraph, @Nullable ValueGraph<?, ?> valueGraph2) {
        if (valueGraph == valueGraph2) {
            return true;
        }
        if (valueGraph == null || valueGraph2 == null || valueGraph.isDirected() != valueGraph2.isDirected() || !valueGraph.nodes().equals(valueGraph2.nodes()) || !valueGraph.edges().equals(valueGraph2.edges())) {
            return false;
        }
        for (EndpointPair next : valueGraph.edges()) {
            if (!valueGraph.edgeValue(next.nodeU(), next.nodeV()).equals(valueGraph2.edgeValue(next.nodeU(), next.nodeV()))) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasCycle(Graph<?> graph) {
        int size = graph.edges().size();
        if (size == 0) {
            return false;
        }
        if (!graph.isDirected() && size >= graph.nodes().size()) {
            return true;
        }
        HashMap newHashMapWithExpectedSize = Maps.newHashMapWithExpectedSize(graph.nodes().size());
        for (Object subgraphHasCycle : graph.nodes()) {
            if (subgraphHasCycle(graph, newHashMapWithExpectedSize, subgraphHasCycle, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasCycle(Network<?, ?> network) {
        if (network.isDirected() || !network.allowsParallelEdges() || network.edges().size() <= network.asGraph().edges().size()) {
            return hasCycle(network.asGraph());
        }
        return true;
    }

    public static <N> MutableGraph<N> inducedSubgraph(Graph<N> graph, Iterable<? extends N> iterable) {
        MutableGraph<N1> build = GraphBuilder.from(graph).build();
        for (Object addNode : iterable) {
            build.addNode(addNode);
        }
        for (N1 next : build.nodes()) {
            for (N next2 : graph.successors(next)) {
                if (build.nodes().contains(next2)) {
                    build.putEdge(next, next2);
                }
            }
        }
        return build;
    }

    public static <N, E> MutableNetwork<N, E> inducedSubgraph(Network<N, E> network, Iterable<? extends N> iterable) {
        MutableNetwork<N1, E1> build = NetworkBuilder.from(network).build();
        for (Object addNode : iterable) {
            build.addNode(addNode);
        }
        for (N1 next : build.nodes()) {
            for (E next2 : network.outEdges(next)) {
                N adjacentNode = network.incidentNodes(next2).adjacentNode(next);
                if (build.nodes().contains(adjacentNode)) {
                    build.addEdge(next, adjacentNode, next2);
                }
            }
        }
        return build;
    }

    public static <N, V> MutableValueGraph<N, V> inducedSubgraph(ValueGraph<N, V> valueGraph, Iterable<? extends N> iterable) {
        MutableValueGraph<N1, V1> build = ValueGraphBuilder.from(valueGraph).build();
        for (Object addNode : iterable) {
            build.addNode(addNode);
        }
        for (N1 next : build.nodes()) {
            for (N next2 : valueGraph.successors(next)) {
                if (build.nodes().contains(next2)) {
                    build.putEdgeValue(next, next2, valueGraph.edgeValue(next, next2));
                }
            }
        }
        return build;
    }

    public static <N> Set<N> reachableNodes(Graph<N> graph, Object obj) {
        Preconditions.checkArgument(graph.nodes().contains(obj), "Node %s is not an element of this graph.", obj);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayDeque arrayDeque = new ArrayDeque();
        linkedHashSet.add(obj);
        arrayDeque.add(obj);
        while (!arrayDeque.isEmpty()) {
            for (N next : graph.successors(arrayDeque.remove())) {
                if (linkedHashSet.add(next)) {
                    arrayDeque.add(next);
                }
            }
        }
        return Collections.unmodifiableSet(linkedHashSet);
    }

    private static boolean subgraphHasCycle(Graph<?> graph, Map<Object, NodeVisitState> map, Object obj, @Nullable Object obj2) {
        NodeVisitState nodeVisitState = map.get(obj);
        if (nodeVisitState == NodeVisitState.COMPLETE) {
            return false;
        }
        if (nodeVisitState == NodeVisitState.PENDING) {
            return true;
        }
        map.put(obj, NodeVisitState.PENDING);
        for (Object next : graph.successors(obj)) {
            if (canTraverseWithoutReusingEdge(graph, next, obj2) && subgraphHasCycle(graph, map, next, obj)) {
                return true;
            }
        }
        map.put(obj, NodeVisitState.COMPLETE);
        return false;
    }

    public static <N> Graph<N> transitiveClosure(Graph<N> graph) {
        MutableGraph<N1> build = GraphBuilder.from(graph).allowsSelfLoops(true).build();
        if (graph.isDirected()) {
            for (N next : graph.nodes()) {
                for (N putEdge : reachableNodes(graph, next)) {
                    build.putEdge(next, putEdge);
                }
            }
        } else {
            HashSet hashSet = new HashSet();
            for (N next2 : graph.nodes()) {
                if (!hashSet.contains(next2)) {
                    Set<N> reachableNodes = reachableNodes(graph, next2);
                    hashSet.addAll(reachableNodes);
                    int i = 1;
                    for (N next3 : reachableNodes) {
                        int i2 = i + 1;
                        for (T putEdge2 : Iterables.limit(reachableNodes, i)) {
                            build.putEdge(next3, putEdge2);
                        }
                        i = i2;
                    }
                }
            }
        }
        return build;
    }

    public static <N> Graph<N> transpose(Graph<N> graph) {
        return !graph.isDirected() ? graph : graph instanceof TransposedGraph ? ((TransposedGraph) graph).graph : new TransposedGraph(graph);
    }

    public static <N, E> Network<N, E> transpose(Network<N, E> network) {
        return !network.isDirected() ? network : network instanceof TransposedNetwork ? ((TransposedNetwork) network).network : new TransposedNetwork(network);
    }

    public static <N, V> ValueGraph<N, V> transpose(ValueGraph<N, V> valueGraph) {
        return !valueGraph.isDirected() ? valueGraph : valueGraph instanceof TransposedValueGraph ? ((TransposedValueGraph) valueGraph).graph : new TransposedValueGraph(valueGraph);
    }
}

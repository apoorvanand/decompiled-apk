package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.graph.ImmutableGraph;
import javax.annotation.Nullable;

@Beta
public final class ImmutableValueGraph<N, V> extends ImmutableGraph.ValueBackedImpl<N, V> implements ValueGraph<N, V> {
    private ImmutableValueGraph(ValueGraph<N, V> valueGraph) {
        super(ValueGraphBuilder.from(valueGraph), getNodeConnections(valueGraph), (long) valueGraph.edges().size());
    }

    private static <N, V> GraphConnections<N, V> connectionsOf(final ValueGraph<N, V> valueGraph, final N n) {
        AnonymousClass1 r0 = new Function<N, V>() {
            public V apply(N n) {
                return valueGraph.edgeValue(n, n);
            }
        };
        return valueGraph.isDirected() ? DirectedGraphConnections.a(valueGraph.predecessors(n), Maps.asMap(valueGraph.successors(n), r0)) : UndirectedGraphConnections.a(Maps.asMap(valueGraph.adjacentNodes(n), r0));
    }

    @Deprecated
    public static <N, V> ImmutableValueGraph<N, V> copyOf(ImmutableValueGraph<N, V> immutableValueGraph) {
        return (ImmutableValueGraph) Preconditions.checkNotNull(immutableValueGraph);
    }

    public static <N, V> ImmutableValueGraph<N, V> copyOf(ValueGraph<N, V> valueGraph) {
        return valueGraph instanceof ImmutableValueGraph ? (ImmutableValueGraph) valueGraph : new ImmutableValueGraph<>(valueGraph);
    }

    private static <N, V> ImmutableMap<N, GraphConnections<N, V>> getNodeConnections(ValueGraph<N, V> valueGraph) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (N next : valueGraph.nodes()) {
            builder.put(next, connectionsOf(valueGraph, next));
        }
        return builder.build();
    }

    public V edgeValue(Object obj, Object obj2) {
        return this.a.edgeValue(obj, obj2);
    }

    public V edgeValueOrDefault(Object obj, Object obj2, @Nullable V v) {
        return this.a.edgeValueOrDefault(obj, obj2, v);
    }

    public String toString() {
        return this.a.toString();
    }
}

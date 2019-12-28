package com.google.common.graph;

import com.google.common.base.Preconditions;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.Nullable;

class ConfigurableValueGraph<N, V> extends AbstractValueGraph<N, V> {
    protected final MapIteratorCache<N, GraphConnections<N, V>> a;
    private final boolean allowsSelfLoops;
    protected long b;
    private final boolean isDirected;
    private final ElementOrder<N> nodeOrder;

    ConfigurableValueGraph(AbstractGraphBuilder<? super N> abstractGraphBuilder) {
        this(abstractGraphBuilder, abstractGraphBuilder.c.a(abstractGraphBuilder.d.or(10).intValue()), 0);
    }

    ConfigurableValueGraph(AbstractGraphBuilder<? super N> abstractGraphBuilder, Map<N, GraphConnections<N, V>> map, long j) {
        this.isDirected = abstractGraphBuilder.a;
        this.allowsSelfLoops = abstractGraphBuilder.b;
        this.nodeOrder = abstractGraphBuilder.c.a();
        this.a = map instanceof TreeMap ? new MapRetrievalCache<>(map) : new MapIteratorCache<>(map);
        this.b = Graphs.a(j);
    }

    /* access modifiers changed from: protected */
    public long a() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public final GraphConnections<N, V> a(Object obj) {
        GraphConnections<N, V> graphConnections = this.a.get(obj);
        if (graphConnections != null) {
            return graphConnections;
        }
        Preconditions.checkNotNull(obj);
        throw new IllegalArgumentException(String.format("Node %s is not an element of this graph.", new Object[]{obj}));
    }

    public Set<N> adjacentNodes(Object obj) {
        return a(obj).adjacentNodes();
    }

    public boolean allowsSelfLoops() {
        return this.allowsSelfLoops;
    }

    /* access modifiers changed from: protected */
    public final boolean b(@Nullable Object obj) {
        return this.a.containsKey(obj);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000b, code lost:
        r2 = r2.value(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public V edgeValueOrDefault(java.lang.Object r2, java.lang.Object r3, @javax.annotation.Nullable V r4) {
        /*
            r1 = this;
            com.google.common.graph.MapIteratorCache<N, com.google.common.graph.GraphConnections<N, V>> r0 = r1.a
            java.lang.Object r2 = r0.get(r2)
            com.google.common.graph.GraphConnections r2 = (com.google.common.graph.GraphConnections) r2
            if (r2 != 0) goto L_0x000b
            return r4
        L_0x000b:
            java.lang.Object r2 = r2.value(r3)
            if (r2 != 0) goto L_0x0012
            return r4
        L_0x0012:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.ConfigurableValueGraph.edgeValueOrDefault(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public boolean isDirected() {
        return this.isDirected;
    }

    public ElementOrder<N> nodeOrder() {
        return this.nodeOrder;
    }

    public Set<N> nodes() {
        return this.a.unmodifiableKeySet();
    }

    public Set<N> predecessors(Object obj) {
        return a(obj).predecessors();
    }

    public Set<N> successors(Object obj) {
        return a(obj).successors();
    }
}

package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.Nullable;

class ConfigurableNetwork<N, E> extends AbstractNetwork<N, E> {
    protected final MapIteratorCache<N, NetworkConnections<N, E>> a;
    private final boolean allowsParallelEdges;
    private final boolean allowsSelfLoops;
    protected final MapIteratorCache<E, N> b;
    private final ElementOrder<E> edgeOrder;
    private final boolean isDirected;
    private final ElementOrder<N> nodeOrder;

    ConfigurableNetwork(NetworkBuilder<? super N, ? super E> networkBuilder) {
        this(networkBuilder, networkBuilder.c.a(((Integer) networkBuilder.d.or(10)).intValue()), networkBuilder.f.a(networkBuilder.g.or(20).intValue()));
    }

    ConfigurableNetwork(NetworkBuilder<? super N, ? super E> networkBuilder, Map<N, NetworkConnections<N, E>> map, Map<E, N> map2) {
        this.isDirected = networkBuilder.a;
        this.allowsParallelEdges = networkBuilder.e;
        this.allowsSelfLoops = networkBuilder.b;
        this.nodeOrder = networkBuilder.c.a();
        this.edgeOrder = networkBuilder.f.a();
        this.a = map instanceof TreeMap ? new MapRetrievalCache<>(map) : new MapIteratorCache<>(map);
        this.b = new MapIteratorCache<>(map2);
    }

    /* access modifiers changed from: protected */
    public final NetworkConnections<N, E> a(Object obj) {
        NetworkConnections<N, E> networkConnections = this.a.get(obj);
        if (networkConnections != null) {
            return networkConnections;
        }
        Preconditions.checkNotNull(obj);
        throw new IllegalArgumentException(String.format("Node %s is not an element of this graph.", new Object[]{obj}));
    }

    public Set<N> adjacentNodes(Object obj) {
        return a(obj).adjacentNodes();
    }

    public boolean allowsParallelEdges() {
        return this.allowsParallelEdges;
    }

    public boolean allowsSelfLoops() {
        return this.allowsSelfLoops;
    }

    /* access modifiers changed from: protected */
    public final N b(Object obj) {
        N n = this.b.get(obj);
        if (n != null) {
            return n;
        }
        Preconditions.checkNotNull(obj);
        throw new IllegalArgumentException(String.format("Edge %s is not an element of this graph.", new Object[]{obj}));
    }

    /* access modifiers changed from: protected */
    public final boolean c(@Nullable Object obj) {
        return this.a.containsKey(obj);
    }

    /* access modifiers changed from: protected */
    public final boolean d(@Nullable Object obj) {
        return this.b.containsKey(obj);
    }

    public ElementOrder<E> edgeOrder() {
        return this.edgeOrder;
    }

    public Set<E> edges() {
        return this.b.unmodifiableKeySet();
    }

    public Set<E> edgesConnecting(Object obj, Object obj2) {
        NetworkConnections a2 = a(obj);
        if (!this.allowsSelfLoops && obj == obj2) {
            return ImmutableSet.of();
        }
        Preconditions.checkArgument(c(obj2), "Node %s is not an element of this graph.", obj2);
        return a2.edgesConnecting(obj2);
    }

    public Set<E> inEdges(Object obj) {
        return a(obj).inEdges();
    }

    public Set<E> incidentEdges(Object obj) {
        return a(obj).incidentEdges();
    }

    public EndpointPair<N> incidentNodes(Object obj) {
        Object b2 = b(obj);
        return EndpointPair.a(this, b2, this.a.get(b2).oppositeNode(obj));
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

    public Set<E> outEdges(Object obj) {
        return a(obj).outEdges();
    }

    public Set<N> predecessors(Object obj) {
        return a(obj).predecessors();
    }

    public Set<N> successors(Object obj) {
        return a(obj).successors();
    }
}

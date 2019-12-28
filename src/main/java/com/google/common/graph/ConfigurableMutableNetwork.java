package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;

final class ConfigurableMutableNetwork<N, E> extends ConfigurableNetwork<N, E> implements MutableNetwork<N, E> {
    ConfigurableMutableNetwork(NetworkBuilder<? super N, ? super E> networkBuilder) {
        super(networkBuilder);
    }

    @CanIgnoreReturnValue
    private NetworkConnections<N, E> addNodeInternal(N n) {
        NetworkConnections<N, E> newConnections = newConnections();
        Preconditions.checkState(this.a.put(n, newConnections) == null);
        return newConnections;
    }

    private NetworkConnections<N, E> newConnections() {
        return isDirected() ? allowsParallelEdges() ? DirectedMultiNetworkConnections.a() : DirectedNetworkConnections.a() : allowsParallelEdges() ? UndirectedMultiNetworkConnections.a() : UndirectedNetworkConnections.a();
    }

    @CanIgnoreReturnValue
    public boolean addEdge(N n, N n2, E e) {
        Preconditions.checkNotNull(n, "nodeU");
        Preconditions.checkNotNull(n2, "nodeV");
        Preconditions.checkNotNull(e, "edge");
        boolean z = false;
        if (d(e)) {
            EndpointPair incidentNodes = incidentNodes(e);
            EndpointPair a = EndpointPair.a(this, n, n2);
            Preconditions.checkArgument(incidentNodes.equals(a), "Edge %s already exists between the following nodes: %s, so it cannot be reused to connect the following nodes: %s.", e, incidentNodes, a);
            return false;
        }
        NetworkConnections networkConnections = (NetworkConnections) this.a.get(n);
        if (!allowsParallelEdges()) {
            if (networkConnections == null || !networkConnections.successors().contains(n2)) {
                z = true;
            }
            Preconditions.checkArgument(z, "Nodes %s and %s are already connected by a different edge. To construct a graph that allows parallel edges, call allowsParallelEdges(true) on the Builder.", (Object) n, (Object) n2);
        }
        boolean equals = n.equals(n2);
        if (!allowsSelfLoops()) {
            Preconditions.checkArgument(!equals, "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.", (Object) n);
        }
        if (networkConnections == null) {
            networkConnections = addNodeInternal(n);
        }
        networkConnections.addOutEdge(e, n2);
        NetworkConnections networkConnections2 = (NetworkConnections) this.a.get(n2);
        if (networkConnections2 == null) {
            networkConnections2 = addNodeInternal(n2);
        }
        networkConnections2.addInEdge(e, n, equals);
        this.b.put(e, n);
        return true;
    }

    @CanIgnoreReturnValue
    public boolean addNode(N n) {
        Preconditions.checkNotNull(n, "node");
        if (c(n)) {
            return false;
        }
        addNodeInternal(n);
        return true;
    }

    @CanIgnoreReturnValue
    public boolean removeEdge(Object obj) {
        Preconditions.checkNotNull(obj, "edge");
        Object obj2 = this.b.get(obj);
        boolean z = false;
        if (obj2 == null) {
            return false;
        }
        NetworkConnections networkConnections = (NetworkConnections) this.a.get(obj2);
        Object oppositeNode = networkConnections.oppositeNode(obj);
        NetworkConnections networkConnections2 = (NetworkConnections) this.a.get(oppositeNode);
        networkConnections.removeOutEdge(obj);
        if (allowsSelfLoops() && obj2.equals(oppositeNode)) {
            z = true;
        }
        networkConnections2.removeInEdge(obj, z);
        this.b.remove(obj);
        return true;
    }

    @CanIgnoreReturnValue
    public boolean removeNode(Object obj) {
        Preconditions.checkNotNull(obj, "node");
        NetworkConnections networkConnections = (NetworkConnections) this.a.get(obj);
        if (networkConnections == null) {
            return false;
        }
        Iterator it = ImmutableList.copyOf(networkConnections.incidentEdges()).iterator();
        while (it.hasNext()) {
            removeEdge(it.next());
        }
        this.a.remove(obj);
        return true;
    }
}

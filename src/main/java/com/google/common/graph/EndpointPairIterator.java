package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.Set;

abstract class EndpointPairIterator<N> extends AbstractIterator<EndpointPair<N>> {
    protected N a;
    protected Iterator<N> b;
    private final Graph<N> graph;
    private final Iterator<N> nodeIterator;

    private static final class Directed<N> extends EndpointPairIterator<N> {
        private Directed(Graph<N> graph) {
            super(graph);
        }

        /* access modifiers changed from: protected */
        /* renamed from: c */
        public EndpointPair<N> computeNext() {
            while (!this.b.hasNext()) {
                if (!b()) {
                    return (EndpointPair) a();
                }
            }
            return EndpointPair.ordered(this.a, this.b.next());
        }
    }

    private static final class Undirected<N> extends EndpointPairIterator<N> {
        private Set<N> visitedNodes;

        private Undirected(Graph<N> graph) {
            super(graph);
            this.visitedNodes = Sets.newHashSetWithExpectedSize(graph.nodes().size());
        }

        /* access modifiers changed from: protected */
        /* renamed from: c */
        public EndpointPair<N> computeNext() {
            while (true) {
                if (this.b.hasNext()) {
                    Object next = this.b.next();
                    if (!this.visitedNodes.contains(next)) {
                        return EndpointPair.unordered(this.a, next);
                    }
                } else {
                    this.visitedNodes.add(this.a);
                    if (!b()) {
                        this.visitedNodes = null;
                        return (EndpointPair) a();
                    }
                }
            }
        }
    }

    private EndpointPairIterator(Graph<N> graph2) {
        this.a = null;
        this.b = ImmutableSet.of().iterator();
        this.graph = graph2;
        this.nodeIterator = graph2.nodes().iterator();
    }

    static <N> EndpointPairIterator<N> a(Graph<N> graph2) {
        return graph2.isDirected() ? new Directed(graph2) : new Undirected(graph2);
    }

    /* access modifiers changed from: protected */
    public final boolean b() {
        Preconditions.checkState(!this.b.hasNext());
        if (!this.nodeIterator.hasNext()) {
            return false;
        }
        this.a = this.nodeIterator.next();
        this.b = this.graph.successors(this.a).iterator();
        return true;
    }
}

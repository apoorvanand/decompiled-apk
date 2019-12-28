package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

@Beta
public final class GraphBuilder<N> extends AbstractGraphBuilder<N> {
    private GraphBuilder(boolean z) {
        super(z);
    }

    private <N1 extends N> GraphBuilder<N1> cast() {
        return this;
    }

    public static GraphBuilder<Object> directed() {
        return new GraphBuilder<>(true);
    }

    public static <N> GraphBuilder<N> from(Graph<N> graph) {
        return new GraphBuilder(graph.isDirected()).allowsSelfLoops(graph.allowsSelfLoops()).nodeOrder(graph.nodeOrder());
    }

    public static GraphBuilder<Object> undirected() {
        return new GraphBuilder<>(false);
    }

    public GraphBuilder<N> allowsSelfLoops(boolean z) {
        this.b = z;
        return this;
    }

    public <N1 extends N> MutableGraph<N1> build() {
        return new ConfigurableMutableGraph(this);
    }

    public GraphBuilder<N> expectedNodeCount(int i) {
        this.d = Optional.of(Integer.valueOf(Graphs.a(i)));
        return this;
    }

    public <N1 extends N> GraphBuilder<N1> nodeOrder(ElementOrder<N1> elementOrder) {
        GraphBuilder<N1> cast = cast();
        cast.c = (ElementOrder) Preconditions.checkNotNull(elementOrder);
        return cast;
    }
}

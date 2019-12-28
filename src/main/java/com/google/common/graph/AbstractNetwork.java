package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.math.IntMath;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

@Beta
public abstract class AbstractNetwork<N, E> implements Network<N, E> {
    private Map<E, EndpointPair<N>> edgeIncidentNodesMap() {
        return Maps.asMap(edges(), new Function<E, EndpointPair<N>>() {
            public EndpointPair<N> apply(E e) {
                return AbstractNetwork.this.incidentNodes(e);
            }
        });
    }

    public Set<E> adjacentEdges(Object obj) {
        EndpointPair incidentNodes = incidentNodes(obj);
        return Sets.difference(Sets.union(incidentEdges(incidentNodes.nodeU()), incidentEdges(incidentNodes.nodeV())), ImmutableSet.of(obj));
    }

    public Graph<N> asGraph() {
        return new AbstractGraph<N>() {
            public Set<N> adjacentNodes(Object obj) {
                return AbstractNetwork.this.adjacentNodes(obj);
            }

            public boolean allowsSelfLoops() {
                return AbstractNetwork.this.allowsSelfLoops();
            }

            public Set<EndpointPair<N>> edges() {
                return AbstractNetwork.this.allowsParallelEdges() ? super.edges() : new AbstractSet<EndpointPair<N>>() {
                    public boolean contains(@Nullable Object obj) {
                        if (!(obj instanceof EndpointPair)) {
                            return false;
                        }
                        EndpointPair endpointPair = (EndpointPair) obj;
                        return AnonymousClass1.this.isDirected() == endpointPair.isOrdered() && AnonymousClass1.this.nodes().contains(endpointPair.nodeU()) && AnonymousClass1.this.successors(endpointPair.nodeU()).contains(endpointPair.nodeV());
                    }

                    public Iterator<EndpointPair<N>> iterator() {
                        return Iterators.transform(AbstractNetwork.this.edges().iterator(), new Function<E, EndpointPair<N>>() {
                            public EndpointPair<N> apply(E e) {
                                return AbstractNetwork.this.incidentNodes(e);
                            }
                        });
                    }

                    public int size() {
                        return AbstractNetwork.this.edges().size();
                    }
                };
            }

            public boolean isDirected() {
                return AbstractNetwork.this.isDirected();
            }

            public ElementOrder<N> nodeOrder() {
                return AbstractNetwork.this.nodeOrder();
            }

            public Set<N> nodes() {
                return AbstractNetwork.this.nodes();
            }

            public Set<N> predecessors(Object obj) {
                return AbstractNetwork.this.predecessors(obj);
            }

            public Set<N> successors(Object obj) {
                return AbstractNetwork.this.successors(obj);
            }
        };
    }

    public int degree(Object obj) {
        int size;
        Set edgesConnecting;
        if (isDirected()) {
            size = inEdges(obj).size();
            edgesConnecting = outEdges(obj);
        } else {
            size = incidentEdges(obj).size();
            edgesConnecting = edgesConnecting(obj, obj);
        }
        return IntMath.saturatedAdd(size, edgesConnecting.size());
    }

    public int inDegree(Object obj) {
        return isDirected() ? inEdges(obj).size() : degree(obj);
    }

    public int outDegree(Object obj) {
        return isDirected() ? outEdges(obj).size() : degree(obj);
    }

    public String toString() {
        return String.format("%s, nodes: %s, edges: %s", new Object[]{String.format("isDirected: %s, allowsParallelEdges: %s, allowsSelfLoops: %s", new Object[]{Boolean.valueOf(isDirected()), Boolean.valueOf(allowsParallelEdges()), Boolean.valueOf(allowsSelfLoops())}), nodes(), edgeIncidentNodesMap()});
    }
}

package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

final class DirectedMultiNetworkConnections<N, E> extends AbstractDirectedNetworkConnections<N, E> {
    @LazyInit
    private transient Reference<Multiset<N>> predecessorsReference;
    @LazyInit
    private transient Reference<Multiset<N>> successorsReference;

    private DirectedMultiNetworkConnections(Map<E, N> map, Map<E, N> map2, int i) {
        super(map, map2, i);
    }

    static <N, E> DirectedMultiNetworkConnections<N, E> a() {
        return new DirectedMultiNetworkConnections<>(new HashMap(2, 1.0f), new HashMap(2, 1.0f), 0);
    }

    static <N, E> DirectedMultiNetworkConnections<N, E> a(Map<E, N> map, Map<E, N> map2, int i) {
        return new DirectedMultiNetworkConnections<>(ImmutableMap.copyOf(map), ImmutableMap.copyOf(map2), i);
    }

    @Nullable
    private static <T> T getReference(@Nullable Reference<T> reference) {
        if (reference == null) {
            return null;
        }
        return reference.get();
    }

    private Multiset<N> predecessorsMultiset() {
        Multiset<N> multiset = (Multiset) getReference(this.predecessorsReference);
        if (multiset != null) {
            return multiset;
        }
        HashMultiset create = HashMultiset.create(this.a.values());
        this.predecessorsReference = new SoftReference(create);
        return create;
    }

    /* access modifiers changed from: private */
    public Multiset<N> successorsMultiset() {
        Multiset<N> multiset = (Multiset) getReference(this.successorsReference);
        if (multiset != null) {
            return multiset;
        }
        HashMultiset create = HashMultiset.create(this.b.values());
        this.successorsReference = new SoftReference(create);
        return create;
    }

    public void addInEdge(E e, N n, boolean z) {
        super.addInEdge(e, n, z);
        Multiset multiset = (Multiset) getReference(this.predecessorsReference);
        if (multiset != null) {
            Preconditions.checkState(multiset.add(n));
        }
    }

    public void addOutEdge(E e, N n) {
        super.addOutEdge(e, n);
        Multiset multiset = (Multiset) getReference(this.successorsReference);
        if (multiset != null) {
            Preconditions.checkState(multiset.add(n));
        }
    }

    public Set<E> edgesConnecting(final Object obj) {
        return new MultiEdgesConnecting<E>(this.b, obj) {
            public int size() {
                return DirectedMultiNetworkConnections.this.successorsMultiset().count(obj);
            }
        };
    }

    public Set<N> predecessors() {
        return Collections.unmodifiableSet(predecessorsMultiset().elementSet());
    }

    public N removeInEdge(Object obj, boolean z) {
        N removeInEdge = super.removeInEdge(obj, z);
        Multiset multiset = (Multiset) getReference(this.predecessorsReference);
        if (multiset != null) {
            Preconditions.checkState(multiset.remove(removeInEdge));
        }
        return removeInEdge;
    }

    public N removeOutEdge(Object obj) {
        N removeOutEdge = super.removeOutEdge(obj);
        Multiset multiset = (Multiset) getReference(this.successorsReference);
        if (multiset != null) {
            Preconditions.checkState(multiset.remove(removeOutEdge));
        }
        return removeOutEdge;
    }

    public Set<N> successors() {
        return Collections.unmodifiableSet(successorsMultiset().elementSet());
    }
}

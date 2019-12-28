package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Iterator;

@GwtCompatible(serializable = true)
final class ReverseNaturalOrdering extends Ordering<Comparable> implements Serializable {
    static final ReverseNaturalOrdering a = new ReverseNaturalOrdering();
    private static final long serialVersionUID = 0;

    private ReverseNaturalOrdering() {
    }

    private Object readResolve() {
        return a;
    }

    public int compare(Comparable comparable, Comparable comparable2) {
        Preconditions.checkNotNull(comparable);
        if (comparable == comparable2) {
            return 0;
        }
        return comparable2.compareTo(comparable);
    }

    public <E extends Comparable> E max(E e, E e2) {
        return (Comparable) NaturalOrdering.a.min(e, e2);
    }

    public <E extends Comparable> E max(E e, E e2, E e3, E... eArr) {
        return (Comparable) NaturalOrdering.a.min(e, e2, e3, eArr);
    }

    public <E extends Comparable> E max(Iterable<E> iterable) {
        return (Comparable) NaturalOrdering.a.min(iterable);
    }

    public <E extends Comparable> E max(Iterator<E> it) {
        return (Comparable) NaturalOrdering.a.min(it);
    }

    public <E extends Comparable> E min(E e, E e2) {
        return (Comparable) NaturalOrdering.a.max(e, e2);
    }

    public <E extends Comparable> E min(E e, E e2, E e3, E... eArr) {
        return (Comparable) NaturalOrdering.a.max(e, e2, e3, eArr);
    }

    public <E extends Comparable> E min(Iterable<E> iterable) {
        return (Comparable) NaturalOrdering.a.max(iterable);
    }

    public <E extends Comparable> E min(Iterator<E> it) {
        return (Comparable) NaturalOrdering.a.max(it);
    }

    public <S extends Comparable> Ordering<S> reverse() {
        return Ordering.natural();
    }

    public String toString() {
        return "Ordering.natural().reverse()";
    }
}

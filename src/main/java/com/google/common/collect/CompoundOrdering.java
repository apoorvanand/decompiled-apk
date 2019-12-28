package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Comparator;

@GwtCompatible(serializable = true)
final class CompoundOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;
    final ImmutableList<Comparator<? super T>> a;

    CompoundOrdering(Iterable<? extends Comparator<? super T>> iterable) {
        this.a = ImmutableList.copyOf(iterable);
    }

    CompoundOrdering(Comparator<? super T> comparator, Comparator<? super T> comparator2) {
        this.a = ImmutableList.of(comparator, comparator2);
    }

    public int compare(T t, T t2) {
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            int compare = ((Comparator) this.a.get(i)).compare(t, t2);
            if (compare != 0) {
                return compare;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CompoundOrdering) {
            return this.a.equals(((CompoundOrdering) obj).a);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return "Ordering.compound(" + this.a + ")";
    }
}

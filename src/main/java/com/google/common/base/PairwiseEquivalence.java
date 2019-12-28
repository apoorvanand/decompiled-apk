package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Iterator;
import javax.annotation.Nullable;

@GwtCompatible(serializable = true)
final class PairwiseEquivalence<T> extends Equivalence<Iterable<T>> implements Serializable {
    private static final long serialVersionUID = 1;
    final Equivalence<? super T> a;

    PairwiseEquivalence(Equivalence<? super T> equivalence) {
        this.a = (Equivalence) Preconditions.checkNotNull(equivalence);
    }

    /* access modifiers changed from: protected */
    public int a(Iterable<T> iterable) {
        int i = 78721;
        for (T hash : iterable) {
            i = (i * 24943) + this.a.hash(hash);
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public boolean a(Iterable<T> iterable, Iterable<T> iterable2) {
        Iterator<T> it = iterable.iterator();
        Iterator<T> it2 = iterable2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            if (!this.a.equivalent(it.next(), it2.next())) {
                return false;
            }
        }
        return !it.hasNext() && !it2.hasNext();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof PairwiseEquivalence) {
            return this.a.equals(((PairwiseEquivalence) obj).a);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() ^ 1185147655;
    }

    public String toString() {
        return this.a + ".pairwise()";
    }
}

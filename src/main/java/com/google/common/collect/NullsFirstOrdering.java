package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import javax.annotation.Nullable;

@GwtCompatible(serializable = true)
final class NullsFirstOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;
    final Ordering<? super T> a;

    NullsFirstOrdering(Ordering<? super T> ordering) {
        this.a = ordering;
    }

    public int compare(@Nullable T t, @Nullable T t2) {
        if (t == t2) {
            return 0;
        }
        if (t == null) {
            return -1;
        }
        if (t2 == null) {
            return 1;
        }
        return this.a.compare(t, t2);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof NullsFirstOrdering) {
            return this.a.equals(((NullsFirstOrdering) obj).a);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() ^ 957692532;
    }

    public <S extends T> Ordering<S> nullsFirst() {
        return this;
    }

    public <S extends T> Ordering<S> nullsLast() {
        return this.a.nullsLast();
    }

    public <S extends T> Ordering<S> reverse() {
        return this.a.reverse().nullsLast();
    }

    public String toString() {
        return this.a + ".nullsFirst()";
    }
}

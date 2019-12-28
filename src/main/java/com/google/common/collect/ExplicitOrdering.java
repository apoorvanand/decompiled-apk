package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Ordering;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Nullable;

@GwtCompatible(serializable = true)
final class ExplicitOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;
    final ImmutableMap<T, Integer> a;

    ExplicitOrdering(ImmutableMap<T, Integer> immutableMap) {
        this.a = immutableMap;
    }

    ExplicitOrdering(List<T> list) {
        this(Maps.a(list));
    }

    private int rank(T t) {
        Integer num = this.a.get(t);
        if (num != null) {
            return num.intValue();
        }
        throw new Ordering.IncomparableValueException(t);
    }

    public int compare(T t, T t2) {
        return rank(t) - rank(t2);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof ExplicitOrdering) {
            return this.a.equals(((ExplicitOrdering) obj).a);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public String toString() {
        return "Ordering.explicit(" + this.a.keySet() + ")";
    }
}

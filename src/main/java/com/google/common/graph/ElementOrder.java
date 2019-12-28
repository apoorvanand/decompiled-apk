package com.google.common.graph;

import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import java.util.Comparator;
import java.util.Map;
import javax.annotation.Nullable;

@Beta
public final class ElementOrder<T> {
    @Nullable
    private final Comparator<T> comparator;
    private final Type type;

    public enum Type {
        UNORDERED,
        INSERTION,
        SORTED
    }

    private ElementOrder(Type type2, @Nullable Comparator<T> comparator2) {
        this.type = (Type) Preconditions.checkNotNull(type2);
        this.comparator = comparator2;
        Preconditions.checkState((type2 == Type.SORTED) != (comparator2 != null) ? false : true);
    }

    public static <S> ElementOrder<S> insertion() {
        return new ElementOrder<>(Type.INSERTION, (Comparator) null);
    }

    public static <S extends Comparable<? super S>> ElementOrder<S> natural() {
        return new ElementOrder<>(Type.SORTED, Ordering.natural());
    }

    public static <S> ElementOrder<S> sorted(Comparator<S> comparator2) {
        return new ElementOrder<>(Type.SORTED, comparator2);
    }

    public static <S> ElementOrder<S> unordered() {
        return new ElementOrder<>(Type.UNORDERED, (Comparator) null);
    }

    /* access modifiers changed from: package-private */
    public <T1 extends T> ElementOrder<T1> a() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public <K extends T, V> Map<K, V> a(int i) {
        switch (this.type) {
            case UNORDERED:
                return Maps.newHashMapWithExpectedSize(i);
            case INSERTION:
                return Maps.newLinkedHashMapWithExpectedSize(i);
            case SORTED:
                return Maps.newTreeMap(comparator());
            default:
                throw new AssertionError();
        }
    }

    public Comparator<T> comparator() {
        Comparator<T> comparator2 = this.comparator;
        if (comparator2 != null) {
            return comparator2;
        }
        throw new UnsupportedOperationException("This ordering does not define a comparator.");
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ElementOrder)) {
            return false;
        }
        ElementOrder elementOrder = (ElementOrder) obj;
        return this.type == elementOrder.type && Objects.equal(this.comparator, elementOrder.comparator);
    }

    public int hashCode() {
        return Objects.hashCode(this.type, this.comparator);
    }

    public String toString() {
        MoreObjects.ToStringHelper add = MoreObjects.toStringHelper((Object) this).add("type", (Object) this.type);
        Comparator<T> comparator2 = this.comparator;
        if (comparator2 != null) {
            add.add("comparator", (Object) comparator2);
        }
        return add.toString();
    }

    public Type type() {
        return this.type;
    }
}

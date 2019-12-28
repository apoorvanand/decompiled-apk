package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import javax.annotation.Nullable;

@GwtCompatible
public final class Range<C extends Comparable> implements Predicate<C>, Serializable {
    private static final Range<Comparable> ALL = new Range<>(Cut.d(), Cut.e());
    private static final Function<Range, Cut> LOWER_BOUND_FN = new Function<Range, Cut>() {
        public Cut apply(Range range) {
            return range.b;
        }
    };
    private static final Function<Range, Cut> UPPER_BOUND_FN = new Function<Range, Cut>() {
        public Cut apply(Range range) {
            return range.c;
        }
    };
    static final Ordering<Range<?>> a = new RangeLexOrdering();
    private static final long serialVersionUID = 0;
    final Cut<C> b;
    final Cut<C> c;

    private static class RangeLexOrdering extends Ordering<Range<?>> implements Serializable {
        private static final long serialVersionUID = 0;

        private RangeLexOrdering() {
        }

        public int compare(Range<?> range, Range<?> range2) {
            return ComparisonChain.start().compare((Comparable<?>) range.b, (Comparable<?>) range2.b).compare((Comparable<?>) range.c, (Comparable<?>) range2.c).result();
        }
    }

    private Range(Cut<C> cut, Cut<C> cut2) {
        this.b = (Cut) Preconditions.checkNotNull(cut);
        this.c = (Cut) Preconditions.checkNotNull(cut2);
        if (cut.compareTo(cut2) > 0 || cut == Cut.e() || cut2 == Cut.d()) {
            throw new IllegalArgumentException("Invalid range: " + toString(cut, cut2));
        }
    }

    static int a(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    static <C extends Comparable<?>> Function<Range<C>, Cut<C>> a() {
        return LOWER_BOUND_FN;
    }

    static <C extends Comparable<?>> Range<C> a(Cut<C> cut, Cut<C> cut2) {
        return new Range<>(cut, cut2);
    }

    public static <C extends Comparable<?>> Range<C> all() {
        return ALL;
    }

    public static <C extends Comparable<?>> Range<C> atLeast(C c2) {
        return a(Cut.b(c2), Cut.e());
    }

    public static <C extends Comparable<?>> Range<C> atMost(C c2) {
        return a(Cut.d(), Cut.c(c2));
    }

    static <C extends Comparable<?>> Function<Range<C>, Cut<C>> b() {
        return UPPER_BOUND_FN;
    }

    private static <T> SortedSet<T> cast(Iterable<T> iterable) {
        return (SortedSet) iterable;
    }

    public static <C extends Comparable<?>> Range<C> closed(C c2, C c3) {
        return a(Cut.b(c2), Cut.c(c3));
    }

    public static <C extends Comparable<?>> Range<C> closedOpen(C c2, C c3) {
        return a(Cut.b(c2), Cut.b(c3));
    }

    public static <C extends Comparable<?>> Range<C> downTo(C c2, BoundType boundType) {
        switch (boundType) {
            case OPEN:
                return greaterThan(c2);
            case CLOSED:
                return atLeast(c2);
            default:
                throw new AssertionError();
        }
    }

    public static <C extends Comparable<?>> Range<C> encloseAll(Iterable<C> iterable) {
        Preconditions.checkNotNull(iterable);
        if (iterable instanceof ContiguousSet) {
            return ((ContiguousSet) iterable).range();
        }
        Iterator<C> it = iterable.iterator();
        Comparable comparable = (Comparable) Preconditions.checkNotNull(it.next());
        Comparable comparable2 = comparable;
        while (it.hasNext()) {
            Comparable comparable3 = (Comparable) Preconditions.checkNotNull(it.next());
            comparable = (Comparable) Ordering.natural().min(comparable, comparable3);
            comparable2 = (Comparable) Ordering.natural().max(comparable2, comparable3);
        }
        return closed(comparable, comparable2);
    }

    public static <C extends Comparable<?>> Range<C> greaterThan(C c2) {
        return a(Cut.c(c2), Cut.e());
    }

    public static <C extends Comparable<?>> Range<C> lessThan(C c2) {
        return a(Cut.d(), Cut.b(c2));
    }

    public static <C extends Comparable<?>> Range<C> open(C c2, C c3) {
        return a(Cut.c(c2), Cut.b(c3));
    }

    public static <C extends Comparable<?>> Range<C> openClosed(C c2, C c3) {
        return a(Cut.c(c2), Cut.c(c3));
    }

    public static <C extends Comparable<?>> Range<C> range(C c2, BoundType boundType, C c3, BoundType boundType2) {
        Preconditions.checkNotNull(boundType);
        Preconditions.checkNotNull(boundType2);
        return a(boundType == BoundType.OPEN ? Cut.c(c2) : Cut.b(c2), boundType2 == BoundType.OPEN ? Cut.b(c3) : Cut.c(c3));
    }

    public static <C extends Comparable<?>> Range<C> singleton(C c2) {
        return closed(c2, c2);
    }

    private static String toString(Cut<?> cut, Cut<?> cut2) {
        StringBuilder sb = new StringBuilder(16);
        cut.a(sb);
        sb.append("..");
        cut2.b(sb);
        return sb.toString();
    }

    public static <C extends Comparable<?>> Range<C> upTo(C c2, BoundType boundType) {
        switch (boundType) {
            case OPEN:
                return lessThan(c2);
            case CLOSED:
                return atMost(c2);
            default:
                throw new AssertionError();
        }
    }

    @Deprecated
    public boolean apply(C c2) {
        return contains(c2);
    }

    public Range<C> canonical(DiscreteDomain<C> discreteDomain) {
        Preconditions.checkNotNull(discreteDomain);
        Cut<C> c2 = this.b.c(discreteDomain);
        Cut<C> c3 = this.c.c(discreteDomain);
        return (c2 == this.b && c3 == this.c) ? this : a(c2, c3);
    }

    public boolean contains(C c2) {
        Preconditions.checkNotNull(c2);
        return this.b.a(c2) && !this.c.a(c2);
    }

    public boolean containsAll(Iterable<? extends C> iterable) {
        if (Iterables.isEmpty(iterable)) {
            return true;
        }
        if (iterable instanceof SortedSet) {
            SortedSet<? extends C> cast = cast(iterable);
            Comparator<? super Object> comparator = cast.comparator();
            if (Ordering.natural().equals(comparator) || comparator == null) {
                return contains((Comparable) cast.first()) && contains((Comparable) cast.last());
            }
        }
        Iterator<? extends C> it = iterable.iterator();
        while (it.hasNext()) {
            if (!contains((Comparable) it.next())) {
                return false;
            }
        }
        return true;
    }

    public boolean encloses(Range<C> range) {
        return this.b.compareTo(range.b) <= 0 && this.c.compareTo(range.c) >= 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Range)) {
            return false;
        }
        Range range = (Range) obj;
        return this.b.equals(range.b) && this.c.equals(range.c);
    }

    public boolean hasLowerBound() {
        return this.b != Cut.d();
    }

    public boolean hasUpperBound() {
        return this.c != Cut.e();
    }

    public int hashCode() {
        return (this.b.hashCode() * 31) + this.c.hashCode();
    }

    public Range<C> intersection(Range<C> range) {
        int compareTo = this.b.compareTo(range.b);
        int compareTo2 = this.c.compareTo(range.c);
        if (compareTo >= 0 && compareTo2 <= 0) {
            return this;
        }
        if (compareTo <= 0 && compareTo2 >= 0) {
            return range;
        }
        return a(compareTo >= 0 ? this.b : range.b, compareTo2 <= 0 ? this.c : range.c);
    }

    public boolean isConnected(Range<C> range) {
        return this.b.compareTo(range.c) <= 0 && range.b.compareTo(this.c) <= 0;
    }

    public boolean isEmpty() {
        return this.b.equals(this.c);
    }

    public BoundType lowerBoundType() {
        return this.b.a();
    }

    public C lowerEndpoint() {
        return this.b.c();
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return equals(ALL) ? all() : this;
    }

    public Range<C> span(Range<C> range) {
        int compareTo = this.b.compareTo(range.b);
        int compareTo2 = this.c.compareTo(range.c);
        if (compareTo <= 0 && compareTo2 >= 0) {
            return this;
        }
        if (compareTo >= 0 && compareTo2 <= 0) {
            return range;
        }
        return a(compareTo <= 0 ? this.b : range.b, compareTo2 >= 0 ? this.c : range.c);
    }

    public String toString() {
        return toString(this.b, this.c);
    }

    public BoundType upperBoundType() {
        return this.c.b();
    }

    public C upperEndpoint() {
        return this.c.c();
    }
}

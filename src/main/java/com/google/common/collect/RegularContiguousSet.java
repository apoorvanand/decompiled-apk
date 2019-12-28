package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Collection;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
final class RegularContiguousSet<C extends Comparable> extends ContiguousSet<C> {
    private static final long serialVersionUID = 0;
    private final Range<C> range;

    @GwtIncompatible
    private static final class SerializedForm<C extends Comparable> implements Serializable {
        final Range<C> a;
        final DiscreteDomain<C> b;

        private SerializedForm(Range<C> range, DiscreteDomain<C> discreteDomain) {
            this.a = range;
            this.b = discreteDomain;
        }

        private Object readResolve() {
            return new RegularContiguousSet(this.a, this.b);
        }
    }

    RegularContiguousSet(Range<C> range2, DiscreteDomain<C> discreteDomain) {
        super(discreteDomain);
        this.range = range2;
    }

    /* access modifiers changed from: private */
    public static boolean equalsOrThrow(Comparable<?> comparable, @Nullable Comparable<?> comparable2) {
        return comparable2 != null && Range.a((Comparable) comparable, (Comparable) comparable2) == 0;
    }

    private ContiguousSet<C> intersectionInCurrentDomain(Range<C> range2) {
        return this.range.isConnected(range2) ? ContiguousSet.create(this.range.intersection(range2), this.a) : new EmptyContiguousSet(this.a);
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public int a(Object obj) {
        if (contains(obj)) {
            return (int) this.a.distance(first(), (Comparable) obj);
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public ContiguousSet<C> b(C c, boolean z) {
        return intersectionInCurrentDomain(Range.upTo(c, BoundType.a(z)));
    }

    /* access modifiers changed from: package-private */
    public ContiguousSet<C> a(C c, boolean z, C c2, boolean z2) {
        return (c.compareTo(c2) != 0 || z || z2) ? intersectionInCurrentDomain(Range.range(c, BoundType.a(z), c2, BoundType.a(z2))) : new EmptyContiguousSet(this.a);
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public ContiguousSet<C> a(C c, boolean z) {
        return intersectionInCurrentDomain(Range.downTo(c, BoundType.a(z)));
    }

    public boolean contains(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return this.range.contains((Comparable) obj);
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public boolean containsAll(Collection<?> collection) {
        return Collections2.a((Collection<?>) this, collection);
    }

    @GwtIncompatible
    public UnmodifiableIterator<C> descendingIterator() {
        return new AbstractSequentialIterator<C>(last()) {
            final C a = RegularContiguousSet.this.first();

            /* access modifiers changed from: protected */
            public C a(C c) {
                if (RegularContiguousSet.equalsOrThrow(c, this.a)) {
                    return null;
                }
                return RegularContiguousSet.this.a.previous(c);
            }
        };
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RegularContiguousSet) {
            RegularContiguousSet regularContiguousSet = (RegularContiguousSet) obj;
            if (this.a.equals(regularContiguousSet.a)) {
                return first().equals(regularContiguousSet.first()) && last().equals(regularContiguousSet.last());
            }
        }
        return super.equals(obj);
    }

    public C first() {
        return this.range.b.a(this.a);
    }

    public int hashCode() {
        return Sets.a(this);
    }

    public ContiguousSet<C> intersection(ContiguousSet<C> contiguousSet) {
        Preconditions.checkNotNull(contiguousSet);
        Preconditions.checkArgument(this.a.equals(contiguousSet.a));
        if (contiguousSet.isEmpty()) {
            return contiguousSet;
        }
        Comparable comparable = (Comparable) Ordering.natural().max(first(), contiguousSet.first());
        Comparable comparable2 = (Comparable) Ordering.natural().min(last(), contiguousSet.last());
        return comparable.compareTo(comparable2) <= 0 ? ContiguousSet.create(Range.closed(comparable, comparable2), this.a) : new EmptyContiguousSet(this.a);
    }

    public boolean isEmpty() {
        return false;
    }

    public UnmodifiableIterator<C> iterator() {
        return new AbstractSequentialIterator<C>(first()) {
            final C a = RegularContiguousSet.this.last();

            /* access modifiers changed from: protected */
            public C a(C c) {
                if (RegularContiguousSet.equalsOrThrow(c, this.a)) {
                    return null;
                }
                return RegularContiguousSet.this.a.next(c);
            }
        };
    }

    public C last() {
        return this.range.c.b(this.a);
    }

    public Range<C> range() {
        return range(BoundType.CLOSED, BoundType.CLOSED);
    }

    public Range<C> range(BoundType boundType, BoundType boundType2) {
        return Range.a(this.range.b.a(boundType, this.a), this.range.c.b(boundType2, this.a));
    }

    public int size() {
        long distance = this.a.distance(first(), last());
        if (distance >= 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return ((int) distance) + 1;
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public Object writeReplace() {
        return new SerializedForm(this.range, this.a);
    }
}

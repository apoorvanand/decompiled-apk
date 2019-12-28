package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
final class EmptyContiguousSet<C extends Comparable> extends ContiguousSet<C> {

    @GwtIncompatible
    private static final class SerializedForm<C extends Comparable> implements Serializable {
        private static final long serialVersionUID = 0;
        private final DiscreteDomain<C> domain;

        private SerializedForm(DiscreteDomain<C> discreteDomain) {
            this.domain = discreteDomain;
        }

        private Object readResolve() {
            return new EmptyContiguousSet(this.domain);
        }
    }

    EmptyContiguousSet(DiscreteDomain<C> discreteDomain) {
        super(discreteDomain);
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public int a(Object obj) {
        return -1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public ContiguousSet<C> b(C c, boolean z) {
        return this;
    }

    /* access modifiers changed from: package-private */
    public ContiguousSet<C> a(C c, boolean z, C c2, boolean z2) {
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public ImmutableList<C> asList() {
        return ImmutableList.of();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public ContiguousSet<C> a(C c, boolean z) {
        return this;
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public ImmutableSortedSet<C> b() {
        return ImmutableSortedSet.a(Ordering.natural().reverse());
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public boolean c() {
        return true;
    }

    public boolean contains(Object obj) {
        return false;
    }

    @GwtIncompatible
    public UnmodifiableIterator<C> descendingIterator() {
        return Iterators.a();
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Set) {
            return ((Set) obj).isEmpty();
        }
        return false;
    }

    public C first() {
        throw new NoSuchElementException();
    }

    public int hashCode() {
        return 0;
    }

    public ContiguousSet<C> intersection(ContiguousSet<C> contiguousSet) {
        return this;
    }

    public boolean isEmpty() {
        return true;
    }

    public UnmodifiableIterator<C> iterator() {
        return Iterators.a();
    }

    public C last() {
        throw new NoSuchElementException();
    }

    public Range<C> range() {
        throw new NoSuchElementException();
    }

    public Range<C> range(BoundType boundType, BoundType boundType2) {
        throw new NoSuchElementException();
    }

    public int size() {
        return 0;
    }

    public String toString() {
        return "[]";
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public Object writeReplace() {
        return new SerializedForm(this.a);
    }
}

package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import javax.annotation.Nullable;

@GwtIncompatible
class DescendingImmutableSortedSet<E> extends ImmutableSortedSet<E> {
    private final ImmutableSortedSet<E> forward;

    DescendingImmutableSortedSet(ImmutableSortedSet<E> immutableSortedSet) {
        super(Ordering.from(immutableSortedSet.comparator()).reverse());
        this.forward = immutableSortedSet;
    }

    /* access modifiers changed from: package-private */
    public int a(@Nullable Object obj) {
        int a = this.forward.a(obj);
        return a == -1 ? a : (size() - 1) - a;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<E> a(E e, boolean z) {
        return this.forward.headSet(e, z).descendingSet();
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<E> a(E e, boolean z, E e2, boolean z2) {
        return this.forward.subSet(e2, z2, e, z).descendingSet();
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.forward.a();
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible("NavigableSet")
    public ImmutableSortedSet<E> b() {
        throw new AssertionError("should never be called");
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<E> b(E e, boolean z) {
        return this.forward.tailSet(e, z).descendingSet();
    }

    public E ceiling(E e) {
        return this.forward.floor(e);
    }

    public boolean contains(@Nullable Object obj) {
        return this.forward.contains(obj);
    }

    @GwtIncompatible("NavigableSet")
    public UnmodifiableIterator<E> descendingIterator() {
        return this.forward.iterator();
    }

    @GwtIncompatible("NavigableSet")
    public ImmutableSortedSet<E> descendingSet() {
        return this.forward;
    }

    public E floor(E e) {
        return this.forward.ceiling(e);
    }

    public E higher(E e) {
        return this.forward.lower(e);
    }

    public UnmodifiableIterator<E> iterator() {
        return this.forward.descendingIterator();
    }

    public E lower(E e) {
        return this.forward.higher(e);
    }

    public int size() {
        return this.forward.size();
    }
}

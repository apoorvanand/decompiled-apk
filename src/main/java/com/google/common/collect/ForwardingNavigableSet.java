package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;

@GwtIncompatible
public abstract class ForwardingNavigableSet<E> extends ForwardingSortedSet<E> implements NavigableSet<E> {

    @Beta
    protected class StandardDescendingSet extends Sets.DescendingSet<E> {
        public StandardDescendingSet() {
            super(ForwardingNavigableSet.this);
        }
    }

    protected ForwardingNavigableSet() {
    }

    /* access modifiers changed from: protected */
    public SortedSet<E> a(E e, E e2) {
        return subSet(e, true, e2, false);
    }

    /* access modifiers changed from: protected */
    public SortedSet<E> c(E e) {
        return headSet(e, false);
    }

    public E ceiling(E e) {
        return delegate().ceiling(e);
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public abstract NavigableSet<E> delegate();

    /* access modifiers changed from: protected */
    public SortedSet<E> d(E e) {
        return tailSet(e, true);
    }

    public Iterator<E> descendingIterator() {
        return delegate().descendingIterator();
    }

    public NavigableSet<E> descendingSet() {
        return delegate().descendingSet();
    }

    public E floor(E e) {
        return delegate().floor(e);
    }

    public NavigableSet<E> headSet(E e, boolean z) {
        return delegate().headSet(e, z);
    }

    public E higher(E e) {
        return delegate().higher(e);
    }

    public E lower(E e) {
        return delegate().lower(e);
    }

    public E pollFirst() {
        return delegate().pollFirst();
    }

    public E pollLast() {
        return delegate().pollLast();
    }

    public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
        return delegate().subSet(e, z, e2, z2);
    }

    public NavigableSet<E> tailSet(E e, boolean z) {
        return delegate().tailSet(e, z);
    }
}

package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import javax.annotation.Nullable;

@GwtCompatible
public abstract class ForwardingSortedSet<E> extends ForwardingSet<E> implements SortedSet<E> {
    protected ForwardingSortedSet() {
    }

    private int unsafeCompare(Object obj, Object obj2) {
        Comparator comparator = comparator();
        return comparator == null ? ((Comparable) obj).compareTo(obj2) : comparator.compare(obj, obj2);
    }

    /* access modifiers changed from: protected */
    @Beta
    public SortedSet<E> a(E e, E e2) {
        return tailSet(e).headSet(e2);
    }

    /* access modifiers changed from: protected */
    @Beta
    public boolean a(@Nullable Object obj) {
        try {
            return unsafeCompare(tailSet(obj).first(), obj) == 0;
        } catch (ClassCastException | NullPointerException | NoSuchElementException unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    @Beta
    public boolean b(@Nullable Object obj) {
        try {
            Iterator it = tailSet(obj).iterator();
            if (it.hasNext() && unsafeCompare(it.next(), obj) == 0) {
                it.remove();
                return true;
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract SortedSet<E> delegate();

    public Comparator<? super E> comparator() {
        return delegate().comparator();
    }

    public E first() {
        return delegate().first();
    }

    public SortedSet<E> headSet(E e) {
        return delegate().headSet(e);
    }

    public E last() {
        return delegate().last();
    }

    public SortedSet<E> subSet(E e, E e2) {
        return delegate().subSet(e, e2);
    }

    public SortedSet<E> tailSet(E e) {
        return delegate().tailSet(e);
    }
}

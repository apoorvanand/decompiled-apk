package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.SortedMultisets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;

@GwtCompatible(emulated = true)
abstract class DescendingMultiset<E> extends ForwardingMultiset<E> implements SortedMultiset<E> {
    private transient Comparator<? super E> comparator;
    private transient NavigableSet<E> elementSet;
    private transient Set<Multiset.Entry<E>> entrySet;

    DescendingMultiset() {
    }

    /* access modifiers changed from: package-private */
    public abstract SortedMultiset<E> a();

    /* access modifiers changed from: package-private */
    public abstract Iterator<Multiset.Entry<E>> c();

    public Comparator<? super E> comparator() {
        Comparator<? super E> comparator2 = this.comparator;
        if (comparator2 != null) {
            return comparator2;
        }
        Ordering reverse = Ordering.from(a().comparator()).reverse();
        this.comparator = reverse;
        return reverse;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public Multiset<E> delegate() {
        return a();
    }

    public SortedMultiset<E> descendingMultiset() {
        return a();
    }

    /* access modifiers changed from: package-private */
    public Set<Multiset.Entry<E>> e() {
        return new Multisets.EntrySet<E>() {
            /* access modifiers changed from: package-private */
            public Multiset<E> a() {
                return DescendingMultiset.this;
            }

            public Iterator<Multiset.Entry<E>> iterator() {
                return DescendingMultiset.this.c();
            }

            public int size() {
                return DescendingMultiset.this.a().entrySet().size();
            }
        };
    }

    public NavigableSet<E> elementSet() {
        NavigableSet<E> navigableSet = this.elementSet;
        if (navigableSet != null) {
            return navigableSet;
        }
        SortedMultisets.NavigableElementSet navigableElementSet = new SortedMultisets.NavigableElementSet(this);
        this.elementSet = navigableElementSet;
        return navigableElementSet;
    }

    public Set<Multiset.Entry<E>> entrySet() {
        Set<Multiset.Entry<E>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        Set<Multiset.Entry<E>> e = e();
        this.entrySet = e;
        return e;
    }

    public Multiset.Entry<E> firstEntry() {
        return a().lastEntry();
    }

    public SortedMultiset<E> headMultiset(E e, BoundType boundType) {
        return a().tailMultiset(e, boundType).descendingMultiset();
    }

    public Iterator<E> iterator() {
        return Multisets.a(this);
    }

    public Multiset.Entry<E> lastEntry() {
        return a().firstEntry();
    }

    public Multiset.Entry<E> pollFirstEntry() {
        return a().pollLastEntry();
    }

    public Multiset.Entry<E> pollLastEntry() {
        return a().pollFirstEntry();
    }

    public SortedMultiset<E> subMultiset(E e, BoundType boundType, E e2, BoundType boundType2) {
        return a().subMultiset(e2, boundType2, e, boundType).descendingMultiset();
    }

    public SortedMultiset<E> tailMultiset(E e, BoundType boundType) {
        return a().headMultiset(e, boundType).descendingMultiset();
    }

    public Object[] toArray() {
        return g();
    }

    public <T> T[] toArray(T[] tArr) {
        return a(tArr);
    }

    public String toString() {
        return entrySet().toString();
    }
}

package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.SortedMultisets;
import java.util.Comparator;
import java.util.NavigableSet;

@GwtCompatible(emulated = true)
@Beta
public abstract class ForwardingSortedMultiset<E> extends ForwardingMultiset<E> implements SortedMultiset<E> {

    protected abstract class StandardDescendingMultiset extends DescendingMultiset<E> {
        public StandardDescendingMultiset() {
        }
    }

    protected class StandardElementSet extends SortedMultisets.NavigableElementSet<E> {
        public StandardElementSet() {
            super(ForwardingSortedMultiset.this);
        }
    }

    protected ForwardingSortedMultiset() {
    }

    /* access modifiers changed from: protected */
    public abstract SortedMultiset<E> a();

    public Comparator<? super E> comparator() {
        return a().comparator();
    }

    public SortedMultiset<E> descendingMultiset() {
        return a().descendingMultiset();
    }

    public NavigableSet<E> elementSet() {
        return (NavigableSet) super.elementSet();
    }

    public Multiset.Entry<E> firstEntry() {
        return a().firstEntry();
    }

    public SortedMultiset<E> headMultiset(E e, BoundType boundType) {
        return a().headMultiset(e, boundType);
    }

    public Multiset.Entry<E> lastEntry() {
        return a().lastEntry();
    }

    public Multiset.Entry<E> pollFirstEntry() {
        return a().pollFirstEntry();
    }

    public Multiset.Entry<E> pollLastEntry() {
        return a().pollLastEntry();
    }

    public SortedMultiset<E> subMultiset(E e, BoundType boundType, E e2, BoundType boundType2) {
        return a().subMultiset(e, boundType, e2, boundType2);
    }

    public SortedMultiset<E> tailMultiset(E e, BoundType boundType) {
        return a().tailMultiset(e, boundType);
    }
}

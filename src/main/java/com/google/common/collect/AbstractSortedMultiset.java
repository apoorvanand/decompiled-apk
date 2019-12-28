package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.SortedMultisets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
abstract class AbstractSortedMultiset<E> extends AbstractMultiset<E> implements SortedMultiset<E> {
    @GwtTransient
    final Comparator<? super E> a;
    private transient SortedMultiset<E> descendingMultiset;

    AbstractSortedMultiset() {
        this(Ordering.natural());
    }

    AbstractSortedMultiset(Comparator<? super E> comparator) {
        this.a = (Comparator) Preconditions.checkNotNull(comparator);
    }

    public Comparator<? super E> comparator() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public NavigableSet<E> c() {
        return new SortedMultisets.NavigableElementSet(this);
    }

    public SortedMultiset<E> descendingMultiset() {
        SortedMultiset<E> sortedMultiset = this.descendingMultiset;
        if (sortedMultiset != null) {
            return sortedMultiset;
        }
        SortedMultiset<E> g = g();
        this.descendingMultiset = g;
        return g;
    }

    /* access modifiers changed from: package-private */
    public abstract Iterator<Multiset.Entry<E>> e();

    public NavigableSet<E> elementSet() {
        return (NavigableSet) super.elementSet();
    }

    /* access modifiers changed from: package-private */
    public Iterator<E> f() {
        return Multisets.a(descendingMultiset());
    }

    public Multiset.Entry<E> firstEntry() {
        Iterator a2 = a();
        if (a2.hasNext()) {
            return (Multiset.Entry) a2.next();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public SortedMultiset<E> g() {
        return new DescendingMultiset<E>() {
            /* access modifiers changed from: package-private */
            public SortedMultiset<E> a() {
                return AbstractSortedMultiset.this;
            }

            /* access modifiers changed from: package-private */
            public Iterator<Multiset.Entry<E>> c() {
                return AbstractSortedMultiset.this.e();
            }

            public Iterator<E> iterator() {
                return AbstractSortedMultiset.this.f();
            }
        };
    }

    public Multiset.Entry<E> lastEntry() {
        Iterator e = e();
        if (e.hasNext()) {
            return (Multiset.Entry) e.next();
        }
        return null;
    }

    public Multiset.Entry<E> pollFirstEntry() {
        Iterator a2 = a();
        if (!a2.hasNext()) {
            return null;
        }
        Multiset.Entry entry = (Multiset.Entry) a2.next();
        Multiset.Entry<E> immutableEntry = Multisets.immutableEntry(entry.getElement(), entry.getCount());
        a2.remove();
        return immutableEntry;
    }

    public Multiset.Entry<E> pollLastEntry() {
        Iterator e = e();
        if (!e.hasNext()) {
            return null;
        }
        Multiset.Entry entry = (Multiset.Entry) e.next();
        Multiset.Entry<E> immutableEntry = Multisets.immutableEntry(entry.getElement(), entry.getCount());
        e.remove();
        return immutableEntry;
    }

    public SortedMultiset<E> subMultiset(@Nullable E e, BoundType boundType, @Nullable E e2, BoundType boundType2) {
        Preconditions.checkNotNull(boundType);
        Preconditions.checkNotNull(boundType2);
        return tailMultiset(e, boundType).headMultiset(e2, boundType2);
    }
}

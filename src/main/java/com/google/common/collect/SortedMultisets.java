package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.j2objc.annotations.Weak;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
final class SortedMultisets {

    static class ElementSet<E> extends Multisets.ElementSet<E> implements SortedSet<E> {
        @Weak
        private final SortedMultiset<E> multiset;

        ElementSet(SortedMultiset<E> sortedMultiset) {
            this.multiset = sortedMultiset;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public final SortedMultiset<E> a() {
            return this.multiset;
        }

        public Comparator<? super E> comparator() {
            return a().comparator();
        }

        public E first() {
            return SortedMultisets.getElementOrThrow(a().firstEntry());
        }

        public SortedSet<E> headSet(E e) {
            return a().headMultiset(e, BoundType.OPEN).elementSet();
        }

        public E last() {
            return SortedMultisets.getElementOrThrow(a().lastEntry());
        }

        public SortedSet<E> subSet(E e, E e2) {
            return a().subMultiset(e, BoundType.CLOSED, e2, BoundType.OPEN).elementSet();
        }

        public SortedSet<E> tailSet(E e) {
            return a().tailMultiset(e, BoundType.CLOSED).elementSet();
        }
    }

    @GwtIncompatible
    static class NavigableElementSet<E> extends ElementSet<E> implements NavigableSet<E> {
        NavigableElementSet(SortedMultiset<E> sortedMultiset) {
            super(sortedMultiset);
        }

        public E ceiling(E e) {
            return SortedMultisets.getElementOrNull(a().tailMultiset(e, BoundType.CLOSED).firstEntry());
        }

        public Iterator<E> descendingIterator() {
            return descendingSet().iterator();
        }

        public NavigableSet<E> descendingSet() {
            return new NavigableElementSet(a().descendingMultiset());
        }

        public E floor(E e) {
            return SortedMultisets.getElementOrNull(a().headMultiset(e, BoundType.CLOSED).lastEntry());
        }

        public NavigableSet<E> headSet(E e, boolean z) {
            return new NavigableElementSet(a().headMultiset(e, BoundType.a(z)));
        }

        public E higher(E e) {
            return SortedMultisets.getElementOrNull(a().tailMultiset(e, BoundType.OPEN).firstEntry());
        }

        public E lower(E e) {
            return SortedMultisets.getElementOrNull(a().headMultiset(e, BoundType.OPEN).lastEntry());
        }

        public E pollFirst() {
            return SortedMultisets.getElementOrNull(a().pollFirstEntry());
        }

        public E pollLast() {
            return SortedMultisets.getElementOrNull(a().pollLastEntry());
        }

        public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
            return new NavigableElementSet(a().subMultiset(e, BoundType.a(z), e2, BoundType.a(z2)));
        }

        public NavigableSet<E> tailSet(E e, boolean z) {
            return new NavigableElementSet(a().tailMultiset(e, BoundType.a(z)));
        }
    }

    private SortedMultisets() {
    }

    /* access modifiers changed from: private */
    public static <E> E getElementOrNull(@Nullable Multiset.Entry<E> entry) {
        if (entry == null) {
            return null;
        }
        return entry.getElement();
    }

    /* access modifiers changed from: private */
    public static <E> E getElementOrThrow(Multiset.Entry<E> entry) {
        if (entry != null) {
            return entry.getElement();
        }
        throw new NoSuchElementException();
    }
}

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.SortedLists;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
final class RegularImmutableSortedSet<E> extends ImmutableSortedSet<E> {
    static final RegularImmutableSortedSet<Comparable> a = new RegularImmutableSortedSet<>(ImmutableList.of(), Ordering.natural());
    private final transient ImmutableList<E> elements;

    RegularImmutableSortedSet(ImmutableList<E> immutableList, Comparator<? super E> comparator) {
        super(comparator);
        this.elements = immutableList;
    }

    private int unsafeBinarySearch(Object obj) {
        return Collections.binarySearch(this.elements, obj, e());
    }

    /* access modifiers changed from: package-private */
    public int a(@Nullable Object obj) {
        if (obj == null) {
            return -1;
        }
        try {
            int binarySearch = SortedLists.binarySearch(this.elements, obj, e(), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.INVERTED_INSERTION_INDEX);
            if (binarySearch >= 0) {
                return binarySearch;
            }
            return -1;
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    /* access modifiers changed from: package-private */
    public int a(Object[] objArr, int i) {
        return this.elements.a(objArr, i);
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<E> a(E e, boolean z) {
        return a(d(e, z), size());
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<E> a(E e, boolean z, E e2, boolean z2) {
        return a(e, z).b(e2, z2);
    }

    /* access modifiers changed from: package-private */
    public RegularImmutableSortedSet<E> a(int i, int i2) {
        return (i == 0 && i2 == size()) ? this : i < i2 ? new RegularImmutableSortedSet<>(this.elements.subList(i, i2), this.b) : a(this.b);
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return this.elements.a();
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<E> b() {
        Ordering reverse = Ordering.from(this.b).reverse();
        return isEmpty() ? a(reverse) : new RegularImmutableSortedSet(this.elements.reverse(), reverse);
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<E> b(E e, boolean z) {
        return a(0, c(e, z));
    }

    /* access modifiers changed from: package-private */
    public int c(E e, boolean z) {
        return SortedLists.binarySearch(this.elements, Preconditions.checkNotNull(e), comparator(), z ? SortedLists.KeyPresentBehavior.FIRST_AFTER : SortedLists.KeyPresentBehavior.FIRST_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
    }

    public E ceiling(E e) {
        int d = d(e, true);
        if (d == size()) {
            return null;
        }
        return this.elements.get(d);
    }

    public boolean contains(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return unsafeBinarySearch(obj) >= 0;
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public boolean containsAll(Collection<?> collection) {
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        if (!SortedIterables.hasSameComparator(comparator(), collection) || collection.size() <= 1) {
            return super.containsAll(collection);
        }
        PeekingIterator peekingIterator = Iterators.peekingIterator(iterator());
        Iterator<?> it = collection.iterator();
        Object next = it.next();
        while (peekingIterator.hasNext()) {
            try {
                int a2 = a(peekingIterator.peek(), (Object) next);
                if (a2 < 0) {
                    peekingIterator.next();
                } else if (a2 == 0) {
                    if (!it.hasNext()) {
                        return true;
                    }
                    next = it.next();
                } else if (a2 > 0) {
                    break;
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public int d(E e, boolean z) {
        return SortedLists.binarySearch(this.elements, Preconditions.checkNotNull(e), comparator(), z ? SortedLists.KeyPresentBehavior.FIRST_PRESENT : SortedLists.KeyPresentBehavior.FIRST_AFTER, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
    }

    /* access modifiers changed from: package-private */
    public ImmutableList<E> d() {
        return size() <= 1 ? this.elements : new ImmutableSortedAsList(this, this.elements);
    }

    @GwtIncompatible
    public UnmodifiableIterator<E> descendingIterator() {
        return this.elements.reverse().iterator();
    }

    /* access modifiers changed from: package-private */
    public Comparator<Object> e() {
        return this.b;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0034 A[Catch:{ ClassCastException | NoSuchElementException -> 0x0046 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@javax.annotation.Nullable java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 1
            if (r6 != r5) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r6 instanceof java.util.Set
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            java.util.Set r6 = (java.util.Set) r6
            int r1 = r5.size()
            int r3 = r6.size()
            if (r1 == r3) goto L_0x0017
            return r2
        L_0x0017:
            boolean r1 = r5.isEmpty()
            if (r1 == 0) goto L_0x001e
            return r0
        L_0x001e:
            java.util.Comparator r1 = r5.b
            boolean r1 = com.google.common.collect.SortedIterables.hasSameComparator(r1, r6)
            if (r1 == 0) goto L_0x0047
            java.util.Iterator r6 = r6.iterator()
            com.google.common.collect.UnmodifiableIterator r1 = r5.iterator()     // Catch:{ ClassCastException | NoSuchElementException -> 0x0046 }
        L_0x002e:
            boolean r3 = r1.hasNext()     // Catch:{ ClassCastException | NoSuchElementException -> 0x0046 }
            if (r3 == 0) goto L_0x0045
            java.lang.Object r3 = r1.next()     // Catch:{ ClassCastException | NoSuchElementException -> 0x0046 }
            java.lang.Object r4 = r6.next()     // Catch:{ ClassCastException | NoSuchElementException -> 0x0046 }
            if (r4 == 0) goto L_0x0044
            int r3 = r5.a((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ ClassCastException | NoSuchElementException -> 0x0046 }
            if (r3 == 0) goto L_0x002e
        L_0x0044:
            return r2
        L_0x0045:
            return r0
        L_0x0046:
            return r2
        L_0x0047:
            boolean r6 = r5.containsAll(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.RegularImmutableSortedSet.equals(java.lang.Object):boolean");
    }

    public E first() {
        if (!isEmpty()) {
            return this.elements.get(0);
        }
        throw new NoSuchElementException();
    }

    public E floor(E e) {
        int c = c(e, true) - 1;
        if (c == -1) {
            return null;
        }
        return this.elements.get(c);
    }

    public E higher(E e) {
        int d = d(e, false);
        if (d == size()) {
            return null;
        }
        return this.elements.get(d);
    }

    public UnmodifiableIterator<E> iterator() {
        return this.elements.iterator();
    }

    public E last() {
        if (!isEmpty()) {
            return this.elements.get(size() - 1);
        }
        throw new NoSuchElementException();
    }

    public E lower(E e) {
        int c = c(e, false) - 1;
        if (c == -1) {
            return null;
        }
        return this.elements.get(c);
    }

    public int size() {
        return this.elements.size();
    }
}

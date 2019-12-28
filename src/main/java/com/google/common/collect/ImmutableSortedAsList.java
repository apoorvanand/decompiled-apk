package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.util.Comparator;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
final class ImmutableSortedAsList<E> extends RegularImmutableAsList<E> implements SortedIterable<E> {
    ImmutableSortedAsList(ImmutableSortedSet<E> immutableSortedSet, ImmutableList<E> immutableList) {
        super(immutableSortedSet, immutableList);
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public ImmutableList<E> a(int i, int i2) {
        return new RegularImmutableSortedSet(super.a(i, i2), comparator()).asList();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public ImmutableSortedSet<E> b() {
        return (ImmutableSortedSet) super.b();
    }

    public Comparator<? super E> comparator() {
        return b().comparator();
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @GwtIncompatible
    public int indexOf(@Nullable Object obj) {
        int a = b().a(obj);
        if (a < 0 || !get(a).equals(obj)) {
            return -1;
        }
        return a;
    }

    @GwtIncompatible
    public int lastIndexOf(@Nullable Object obj) {
        return indexOf(obj);
    }
}

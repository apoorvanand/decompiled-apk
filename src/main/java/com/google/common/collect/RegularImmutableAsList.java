package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.j2objc.annotations.Weak;

@GwtCompatible(emulated = true)
class RegularImmutableAsList<E> extends ImmutableAsList<E> {
    @Weak
    private final ImmutableCollection<E> delegate;
    private final ImmutableList<? extends E> delegateList;

    RegularImmutableAsList(ImmutableCollection<E> immutableCollection, ImmutableList<? extends E> immutableList) {
        this.delegate = immutableCollection;
        this.delegateList = immutableList;
    }

    RegularImmutableAsList(ImmutableCollection<E> immutableCollection, Object[] objArr) {
        this(immutableCollection, ImmutableList.a(objArr));
    }

    /* access modifiers changed from: package-private */
    @GwtIncompatible
    public int a(Object[] objArr, int i) {
        return this.delegateList.a(objArr, i);
    }

    /* access modifiers changed from: package-private */
    public ImmutableCollection<E> b() {
        return this.delegate;
    }

    public E get(int i) {
        return this.delegateList.get(i);
    }

    public UnmodifiableListIterator<E> listIterator(int i) {
        return this.delegateList.listIterator(i);
    }
}

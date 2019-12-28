package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = true, serializable = true)
class RegularImmutableList<E> extends ImmutableList<E> {
    static final ImmutableList<Object> a = new RegularImmutableList(ObjectArrays.a);
    private final transient Object[] array;

    RegularImmutableList(Object[] objArr) {
        this.array = objArr;
    }

    /* access modifiers changed from: package-private */
    public int a(Object[] objArr, int i) {
        Object[] objArr2 = this.array;
        System.arraycopy(objArr2, 0, objArr, i, objArr2.length);
        return i + this.array.length;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public E get(int i) {
        return this.array[i];
    }

    public UnmodifiableListIterator<E> listIterator(int i) {
        Object[] objArr = this.array;
        return Iterators.a(objArr, 0, objArr.length, i);
    }

    public int size() {
        return this.array.length;
    }
}

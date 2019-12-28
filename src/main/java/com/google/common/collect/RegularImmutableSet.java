package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableSet;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
final class RegularImmutableSet<E> extends ImmutableSet.Indexed<E> {
    static final RegularImmutableSet<Object> a = new RegularImmutableSet<>(ObjectArrays.a, 0, (Object[]) null, 0);
    @VisibleForTesting
    final transient Object[] b;
    private final transient Object[] elements;
    private final transient int hashCode;
    private final transient int mask;

    RegularImmutableSet(Object[] objArr, int i, Object[] objArr2, int i2) {
        this.elements = objArr;
        this.b = objArr2;
        this.mask = i2;
        this.hashCode = i;
    }

    /* access modifiers changed from: package-private */
    public int a(Object[] objArr, int i) {
        Object[] objArr2 = this.elements;
        System.arraycopy(objArr2, 0, objArr, i, objArr2.length);
        return i + this.elements.length;
    }

    /* access modifiers changed from: package-private */
    public E a(int i) {
        return this.elements[i];
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return true;
    }

    public boolean contains(@Nullable Object obj) {
        Object[] objArr = this.b;
        if (obj == null || objArr == null) {
            return false;
        }
        int a2 = Hashing.a(obj);
        while (true) {
            int i = a2 & this.mask;
            Object obj2 = objArr[i];
            if (obj2 == null) {
                return false;
            }
            if (obj2.equals(obj)) {
                return true;
            }
            a2 = i + 1;
        }
    }

    /* access modifiers changed from: package-private */
    public ImmutableList<E> d() {
        return this.b == null ? ImmutableList.of() : new RegularImmutableAsList(this, this.elements);
    }

    public int hashCode() {
        return this.hashCode;
    }

    public int size() {
        return this.elements.length;
    }
}

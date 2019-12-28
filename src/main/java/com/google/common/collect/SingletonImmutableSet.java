package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;

@GwtCompatible(emulated = true, serializable = true)
final class SingletonImmutableSet<E> extends ImmutableSet<E> {
    final transient E a;
    @LazyInit
    private transient int cachedHashCode;

    SingletonImmutableSet(E e) {
        this.a = Preconditions.checkNotNull(e);
    }

    SingletonImmutableSet(E e, int i) {
        this.a = e;
        this.cachedHashCode = i;
    }

    /* access modifiers changed from: package-private */
    public int a(Object[] objArr, int i) {
        objArr[i] = this.a;
        return i + 1;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.cachedHashCode != 0;
    }

    public boolean contains(Object obj) {
        return this.a.equals(obj);
    }

    /* access modifiers changed from: package-private */
    public ImmutableList<E> d() {
        return ImmutableList.of(this.a);
    }

    public final int hashCode() {
        int i = this.cachedHashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = this.a.hashCode();
        this.cachedHashCode = hashCode;
        return hashCode;
    }

    public UnmodifiableIterator<E> iterator() {
        return Iterators.singletonIterator(this.a);
    }

    public int size() {
        return 1;
    }

    public String toString() {
        return '[' + this.a.toString() + ']';
    }
}

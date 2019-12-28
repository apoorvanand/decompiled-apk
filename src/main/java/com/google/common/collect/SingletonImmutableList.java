package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible(emulated = true, serializable = true)
final class SingletonImmutableList<E> extends ImmutableList<E> {
    final transient E a;

    SingletonImmutableList(E e) {
        this.a = Preconditions.checkNotNull(e);
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return false;
    }

    public E get(int i) {
        Preconditions.checkElementIndex(i, 1);
        return this.a;
    }

    public UnmodifiableIterator<E> iterator() {
        return Iterators.singletonIterator(this.a);
    }

    public int size() {
        return 1;
    }

    public ImmutableList<E> subList(int i, int i2) {
        Preconditions.checkPositionIndexes(i, i2, 1);
        return i == i2 ? ImmutableList.of() : this;
    }

    public String toString() {
        return '[' + this.a.toString() + ']';
    }
}

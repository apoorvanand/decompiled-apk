package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Queue;

@GwtCompatible
public abstract class ForwardingQueue<E> extends ForwardingCollection<E> implements Queue<E> {
    protected ForwardingQueue() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Queue<E> delegate();

    public E element() {
        return delegate().element();
    }

    @CanIgnoreReturnValue
    public boolean offer(E e) {
        return delegate().offer(e);
    }

    public E peek() {
        return delegate().peek();
    }

    @CanIgnoreReturnValue
    public E poll() {
        return delegate().poll();
    }

    @CanIgnoreReturnValue
    public E remove() {
        return delegate().remove();
    }
}

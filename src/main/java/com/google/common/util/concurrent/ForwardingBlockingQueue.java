package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ForwardingQueue;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
@CanIgnoreReturnValue
public abstract class ForwardingBlockingQueue<E> extends ForwardingQueue<E> implements BlockingQueue<E> {
    protected ForwardingBlockingQueue() {
    }

    /* access modifiers changed from: protected */
    public abstract BlockingQueue<E> c();

    public int drainTo(Collection<? super E> collection) {
        return c().drainTo(collection);
    }

    public int drainTo(Collection<? super E> collection, int i) {
        return c().drainTo(collection, i);
    }

    public boolean offer(E e, long j, TimeUnit timeUnit) {
        return c().offer(e, j, timeUnit);
    }

    public E poll(long j, TimeUnit timeUnit) {
        return c().poll(j, timeUnit);
    }

    public void put(E e) {
        c().put(e);
    }

    public int remainingCapacity() {
        return c().remainingCapacity();
    }

    public E take() {
        return c().take();
    }
}

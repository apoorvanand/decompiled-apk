package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import java.util.Collection;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
public abstract class ForwardingBlockingDeque<E> extends ForwardingDeque<E> implements BlockingDeque<E> {
    protected ForwardingBlockingDeque() {
    }

    /* access modifiers changed from: protected */
    public abstract BlockingDeque<E> c();

    public int drainTo(Collection<? super E> collection) {
        return c().drainTo(collection);
    }

    public int drainTo(Collection<? super E> collection, int i) {
        return c().drainTo(collection, i);
    }

    public boolean offer(E e, long j, TimeUnit timeUnit) {
        return c().offer(e, j, timeUnit);
    }

    public boolean offerFirst(E e, long j, TimeUnit timeUnit) {
        return c().offerFirst(e, j, timeUnit);
    }

    public boolean offerLast(E e, long j, TimeUnit timeUnit) {
        return c().offerLast(e, j, timeUnit);
    }

    public E poll(long j, TimeUnit timeUnit) {
        return c().poll(j, timeUnit);
    }

    public E pollFirst(long j, TimeUnit timeUnit) {
        return c().pollFirst(j, timeUnit);
    }

    public E pollLast(long j, TimeUnit timeUnit) {
        return c().pollLast(j, timeUnit);
    }

    public void put(E e) {
        c().put(e);
    }

    public void putFirst(E e) {
        c().putFirst(e);
    }

    public void putLast(E e) {
        c().putLast(e);
    }

    public int remainingCapacity() {
        return c().remainingCapacity();
    }

    public E take() {
        return c().take();
    }

    public E takeFirst() {
        return c().takeFirst();
    }

    public E takeLast() {
        return c().takeLast();
    }
}

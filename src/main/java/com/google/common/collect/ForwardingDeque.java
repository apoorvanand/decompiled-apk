package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Deque;
import java.util.Iterator;

@GwtIncompatible
public abstract class ForwardingDeque<E> extends ForwardingQueue<E> implements Deque<E> {
    protected ForwardingDeque() {
    }

    public void addFirst(E e) {
        d().addFirst(e);
    }

    public void addLast(E e) {
        d().addLast(e);
    }

    /* access modifiers changed from: protected */
    public abstract Deque<E> d();

    public Iterator<E> descendingIterator() {
        return d().descendingIterator();
    }

    public E getFirst() {
        return d().getFirst();
    }

    public E getLast() {
        return d().getLast();
    }

    @CanIgnoreReturnValue
    public boolean offerFirst(E e) {
        return d().offerFirst(e);
    }

    @CanIgnoreReturnValue
    public boolean offerLast(E e) {
        return d().offerLast(e);
    }

    public E peekFirst() {
        return d().peekFirst();
    }

    public E peekLast() {
        return d().peekLast();
    }

    @CanIgnoreReturnValue
    public E pollFirst() {
        return d().pollFirst();
    }

    @CanIgnoreReturnValue
    public E pollLast() {
        return d().pollLast();
    }

    @CanIgnoreReturnValue
    public E pop() {
        return d().pop();
    }

    public void push(E e) {
        d().push(e);
    }

    @CanIgnoreReturnValue
    public E removeFirst() {
        return d().removeFirst();
    }

    @CanIgnoreReturnValue
    public boolean removeFirstOccurrence(Object obj) {
        return d().removeFirstOccurrence(obj);
    }

    @CanIgnoreReturnValue
    public E removeLast() {
        return d().removeLast();
    }

    @CanIgnoreReturnValue
    public boolean removeLastOccurrence(Object obj) {
        return d().removeLastOccurrence(obj);
    }
}

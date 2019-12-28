package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ForwardingObject;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
@CanIgnoreReturnValue
public abstract class ForwardingFuture<V> extends ForwardingObject implements Future<V> {

    public static abstract class SimpleForwardingFuture<V> extends ForwardingFuture<V> {
        private final Future<V> delegate;
    }

    protected ForwardingFuture() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Future<? extends V> delegate();

    public boolean cancel(boolean z) {
        return delegate().cancel(z);
    }

    public V get() {
        return delegate().get();
    }

    public V get(long j, TimeUnit timeUnit) {
        return delegate().get(j, timeUnit);
    }

    public boolean isCancelled() {
        return delegate().isCancelled();
    }

    public boolean isDone() {
        return delegate().isDone();
    }
}

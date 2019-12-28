package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.ForwardingObject;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
@CanIgnoreReturnValue
public abstract class ForwardingExecutorService extends ForwardingObject implements ExecutorService {
    protected ForwardingExecutorService() {
    }

    /* access modifiers changed from: protected */
    public abstract ExecutorService a();

    public boolean awaitTermination(long j, TimeUnit timeUnit) {
        return a().awaitTermination(j, timeUnit);
    }

    public void execute(Runnable runnable) {
        a().execute(runnable);
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) {
        return a().invokeAll(collection);
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) {
        return a().invokeAll(collection, j, timeUnit);
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> collection) {
        return a().invokeAny(collection);
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) {
        return a().invokeAny(collection, j, timeUnit);
    }

    public boolean isShutdown() {
        return a().isShutdown();
    }

    public boolean isTerminated() {
        return a().isTerminated();
    }

    public void shutdown() {
        a().shutdown();
    }

    public List<Runnable> shutdownNow() {
        return a().shutdownNow();
    }

    public Future<?> submit(Runnable runnable) {
        return a().submit(runnable);
    }

    public <T> Future<T> submit(Runnable runnable, T t) {
        return a().submit(runnable, t);
    }

    public <T> Future<T> submit(Callable<T> callable) {
        return a().submit(callable);
    }
}

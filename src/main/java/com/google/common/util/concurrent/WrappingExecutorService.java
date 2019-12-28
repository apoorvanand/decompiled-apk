package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
@CanIgnoreReturnValue
abstract class WrappingExecutorService implements ExecutorService {
    private final ExecutorService delegate;

    protected WrappingExecutorService(ExecutorService executorService) {
        this.delegate = (ExecutorService) Preconditions.checkNotNull(executorService);
    }

    private final <T> ImmutableList<Callable<T>> wrapTasks(Collection<? extends Callable<T>> collection) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Callable a : collection) {
            builder.add((Object) a(a));
        }
        return builder.build();
    }

    /* access modifiers changed from: protected */
    public Runnable a(Runnable runnable) {
        final Callable a = a(Executors.callable(runnable, (Object) null));
        return new Runnable() {
            public void run() {
                try {
                    a.call();
                } catch (Exception e) {
                    Throwables.throwIfUnchecked(e);
                    throw new RuntimeException(e);
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public abstract <T> Callable<T> a(Callable<T> callable);

    public final boolean awaitTermination(long j, TimeUnit timeUnit) {
        return this.delegate.awaitTermination(j, timeUnit);
    }

    public final void execute(Runnable runnable) {
        this.delegate.execute(a(runnable));
    }

    public final <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) {
        return this.delegate.invokeAll(wrapTasks(collection));
    }

    public final <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) {
        return this.delegate.invokeAll(wrapTasks(collection), j, timeUnit);
    }

    public final <T> T invokeAny(Collection<? extends Callable<T>> collection) {
        return this.delegate.invokeAny(wrapTasks(collection));
    }

    public final <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) {
        return this.delegate.invokeAny(wrapTasks(collection), j, timeUnit);
    }

    public final boolean isShutdown() {
        return this.delegate.isShutdown();
    }

    public final boolean isTerminated() {
        return this.delegate.isTerminated();
    }

    public final void shutdown() {
        this.delegate.shutdown();
    }

    public final List<Runnable> shutdownNow() {
        return this.delegate.shutdownNow();
    }

    public final Future<?> submit(Runnable runnable) {
        return this.delegate.submit(a(runnable));
    }

    public final <T> Future<T> submit(Runnable runnable, T t) {
        return this.delegate.submit(a(runnable), t);
    }

    public final <T> Future<T> submit(Callable<T> callable) {
        return this.delegate.submit(a((Callable) Preconditions.checkNotNull(callable)));
    }
}

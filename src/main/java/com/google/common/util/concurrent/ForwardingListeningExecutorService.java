package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;

@GwtIncompatible
@CanIgnoreReturnValue
public abstract class ForwardingListeningExecutorService extends ForwardingExecutorService implements ListeningExecutorService {
    protected ForwardingListeningExecutorService() {
    }

    /* access modifiers changed from: protected */
    public abstract ListeningExecutorService b();

    public ListenableFuture<?> submit(Runnable runnable) {
        return b().submit(runnable);
    }

    public <T> ListenableFuture<T> submit(Runnable runnable, T t) {
        return b().submit(runnable, t);
    }

    public <T> ListenableFuture<T> submit(Callable<T> callable) {
        return b().submit(callable);
    }
}

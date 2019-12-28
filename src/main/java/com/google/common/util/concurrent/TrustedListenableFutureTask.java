package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;
import javax.annotation.Nullable;

@GwtCompatible
class TrustedListenableFutureTask<V> extends AbstractFuture.TrustedFuture<V> implements RunnableFuture<V> {
    private TrustedListenableFutureTask<V>.TrustedFutureInterruptibleTask task;

    private final class TrustedFutureInterruptibleTask extends InterruptibleTask {
        private final Callable<V> callable;

        TrustedFutureInterruptibleTask(Callable<V> callable2) {
            this.callable = (Callable) Preconditions.checkNotNull(callable2);
        }

        /* access modifiers changed from: package-private */
        public void b() {
            if (!TrustedListenableFutureTask.this.isDone()) {
                try {
                    TrustedListenableFutureTask.this.set(this.callable.call());
                } catch (Throwable th) {
                    TrustedListenableFutureTask.this.setException(th);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean c() {
            return TrustedListenableFutureTask.this.c();
        }

        public String toString() {
            return this.callable.toString();
        }
    }

    TrustedListenableFutureTask(Callable<V> callable) {
        this.task = new TrustedFutureInterruptibleTask(callable);
    }

    static <V> TrustedListenableFutureTask<V> a(Runnable runnable, @Nullable V v) {
        return new TrustedListenableFutureTask<>(Executors.callable(runnable, v));
    }

    static <V> TrustedListenableFutureTask<V> a(Callable<V> callable) {
        return new TrustedListenableFutureTask<>(callable);
    }

    /* access modifiers changed from: protected */
    public void a() {
        TrustedListenableFutureTask<V>.TrustedFutureInterruptibleTask trustedFutureInterruptibleTask;
        super.a();
        if (c() && (trustedFutureInterruptibleTask = this.task) != null) {
            trustedFutureInterruptibleTask.e();
        }
        this.task = null;
    }

    public void run() {
        TrustedListenableFutureTask<V>.TrustedFutureInterruptibleTask trustedFutureInterruptibleTask = this.task;
        if (trustedFutureInterruptibleTask != null) {
            trustedFutureInterruptibleTask.run();
        }
    }

    public String toString() {
        return super.toString() + " (delegate = " + this.task + ")";
    }
}

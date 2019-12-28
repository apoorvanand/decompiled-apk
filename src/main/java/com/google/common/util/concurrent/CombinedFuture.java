package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import javax.annotation.Nullable;

@GwtCompatible
final class CombinedFuture<V> extends AggregateFuture<Object, V> {

    private final class AsyncCallableInterruptibleTask extends CombinedFuture<V>.CombinedFutureInterruptibleTask {
        private final AsyncCallable<V> callable;

        public AsyncCallableInterruptibleTask(AsyncCallable<V> asyncCallable, Executor executor) {
            super(executor);
            this.callable = (AsyncCallable) Preconditions.checkNotNull(asyncCallable);
        }

        /* access modifiers changed from: package-private */
        public void a() {
            CombinedFuture.this.setFuture(this.callable.call());
        }
    }

    private final class CallableInterruptibleTask extends CombinedFuture<V>.CombinedFutureInterruptibleTask {
        private final Callable<V> callable;

        public CallableInterruptibleTask(Callable<V> callable2, Executor executor) {
            super(executor);
            this.callable = (Callable) Preconditions.checkNotNull(callable2);
        }

        /* access modifiers changed from: package-private */
        public void a() {
            CombinedFuture.this.set(this.callable.call());
        }
    }

    private abstract class CombinedFutureInterruptibleTask extends InterruptibleTask {
        volatile boolean b = true;
        private final Executor listenerExecutor;

        public CombinedFutureInterruptibleTask(Executor executor) {
            this.listenerExecutor = (Executor) Preconditions.checkNotNull(executor);
        }

        /* access modifiers changed from: package-private */
        public abstract void a();

        /* access modifiers changed from: package-private */
        public final void b() {
            CombinedFuture combinedFuture;
            Throwable th;
            this.b = false;
            if (!CombinedFuture.this.isDone()) {
                try {
                    a();
                    return;
                } catch (ExecutionException e) {
                    combinedFuture = CombinedFuture.this;
                    th = e.getCause();
                } catch (CancellationException unused) {
                    CombinedFuture.this.cancel(false);
                    return;
                } catch (Throwable th2) {
                    th = th2;
                    combinedFuture = CombinedFuture.this;
                }
            } else {
                return;
            }
            combinedFuture.setException(th);
        }

        /* access modifiers changed from: package-private */
        public final boolean c() {
            return CombinedFuture.this.c();
        }

        /* access modifiers changed from: package-private */
        public final void d() {
            try {
                this.listenerExecutor.execute(this);
            } catch (RejectedExecutionException e) {
                if (this.b) {
                    CombinedFuture.this.setException(e);
                }
            }
        }
    }

    private final class CombinedFutureRunningState extends AggregateFuture<Object, V>.RunningState {
        private CombinedFuture<V>.CombinedFutureInterruptibleTask task;

        CombinedFutureRunningState(ImmutableCollection<? extends ListenableFuture<? extends Object>> immutableCollection, boolean z, CombinedFuture<V>.CombinedFutureInterruptibleTask combinedFutureInterruptibleTask) {
            super(immutableCollection, z, false);
            this.task = combinedFutureInterruptibleTask;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            super.a();
            this.task = null;
        }

        /* access modifiers changed from: package-private */
        public void a(boolean z, int i, @Nullable Object obj) {
        }

        /* access modifiers changed from: package-private */
        public void b() {
            CombinedFuture<V>.CombinedFutureInterruptibleTask combinedFutureInterruptibleTask = this.task;
            if (combinedFutureInterruptibleTask != null) {
                combinedFutureInterruptibleTask.d();
            } else {
                Preconditions.checkState(CombinedFuture.this.isDone());
            }
        }

        /* access modifiers changed from: package-private */
        public void c() {
            CombinedFuture<V>.CombinedFutureInterruptibleTask combinedFutureInterruptibleTask = this.task;
            if (combinedFutureInterruptibleTask != null) {
                combinedFutureInterruptibleTask.e();
            }
        }
    }

    CombinedFuture(ImmutableCollection<? extends ListenableFuture<?>> immutableCollection, boolean z, Executor executor, AsyncCallable<V> asyncCallable) {
        a(new CombinedFutureRunningState(immutableCollection, z, new AsyncCallableInterruptibleTask(asyncCallable, executor)));
    }

    CombinedFuture(ImmutableCollection<? extends ListenableFuture<?>> immutableCollection, boolean z, Executor executor, Callable<V> callable) {
        a(new CombinedFutureRunningState(immutableCollection, z, new CallableInterruptibleTask(callable, executor)));
    }
}

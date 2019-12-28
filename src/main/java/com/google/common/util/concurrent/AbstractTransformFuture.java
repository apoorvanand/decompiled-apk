package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.errorprone.annotations.ForOverride;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import javax.annotation.Nullable;

@GwtCompatible
abstract class AbstractTransformFuture<I, O, F, T> extends AbstractFuture.TrustedFuture<O> implements Runnable {
    @Nullable
    ListenableFuture<? extends I> a;
    @Nullable
    F b;

    private static final class AsyncTransformFuture<I, O> extends AbstractTransformFuture<I, O, AsyncFunction<? super I, ? extends O>, ListenableFuture<? extends O>> {
        AsyncTransformFuture(ListenableFuture<? extends I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction) {
            super(listenableFuture, asyncFunction);
        }

        /* access modifiers changed from: package-private */
        public ListenableFuture<? extends O> a(AsyncFunction<? super I, ? extends O> asyncFunction, @Nullable I i) {
            ListenableFuture<? extends O> apply = asyncFunction.apply(i);
            Preconditions.checkNotNull(apply, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)?");
            return apply;
        }

        /* access modifiers changed from: package-private */
        public void a(ListenableFuture<? extends O> listenableFuture) {
            setFuture(listenableFuture);
        }
    }

    private static final class TransformFuture<I, O> extends AbstractTransformFuture<I, O, Function<? super I, ? extends O>, O> {
        TransformFuture(ListenableFuture<? extends I> listenableFuture, Function<? super I, ? extends O> function) {
            super(listenableFuture, function);
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public O a(Function<? super I, ? extends O> function, @Nullable I i) {
            return function.apply(i);
        }

        /* access modifiers changed from: package-private */
        public void a(@Nullable O o) {
            set(o);
        }
    }

    AbstractTransformFuture(ListenableFuture<? extends I> listenableFuture, F f) {
        this.a = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
        this.b = Preconditions.checkNotNull(f);
    }

    static <I, O> ListenableFuture<O> a(ListenableFuture<I> listenableFuture, Function<? super I, ? extends O> function) {
        Preconditions.checkNotNull(function);
        TransformFuture transformFuture = new TransformFuture(listenableFuture, function);
        listenableFuture.addListener(transformFuture, MoreExecutors.directExecutor());
        return transformFuture;
    }

    static <I, O> ListenableFuture<O> a(ListenableFuture<I> listenableFuture, Function<? super I, ? extends O> function, Executor executor) {
        Preconditions.checkNotNull(function);
        TransformFuture transformFuture = new TransformFuture(listenableFuture, function);
        listenableFuture.addListener(transformFuture, MoreExecutors.a(executor, (AbstractFuture<?>) transformFuture));
        return transformFuture;
    }

    static <I, O> ListenableFuture<O> a(ListenableFuture<I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction) {
        AsyncTransformFuture asyncTransformFuture = new AsyncTransformFuture(listenableFuture, asyncFunction);
        listenableFuture.addListener(asyncTransformFuture, MoreExecutors.directExecutor());
        return asyncTransformFuture;
    }

    static <I, O> ListenableFuture<O> a(ListenableFuture<I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction, Executor executor) {
        Preconditions.checkNotNull(executor);
        AsyncTransformFuture asyncTransformFuture = new AsyncTransformFuture(listenableFuture, asyncFunction);
        listenableFuture.addListener(asyncTransformFuture, MoreExecutors.a(executor, (AbstractFuture<?>) asyncTransformFuture));
        return asyncTransformFuture;
    }

    /* access modifiers changed from: package-private */
    @ForOverride
    @Nullable
    public abstract T a(F f, @Nullable I i);

    /* access modifiers changed from: protected */
    public final void a() {
        a((Future<?>) this.a);
        this.a = null;
        this.b = null;
    }

    /* access modifiers changed from: package-private */
    @ForOverride
    public abstract void a(@Nullable T t);

    public final void run() {
        Throwable cause;
        ListenableFuture<? extends I> listenableFuture = this.a;
        F f = this.b;
        boolean z = true;
        boolean isCancelled = isCancelled() | (listenableFuture == null);
        if (f != null) {
            z = false;
        }
        if (!isCancelled && !z) {
            this.a = null;
            this.b = null;
            try {
                try {
                    a(a(f, Futures.getDone(listenableFuture)));
                } catch (UndeclaredThrowableException e) {
                    cause = e.getCause();
                    setException(cause);
                } catch (Throwable th) {
                    setException(th);
                }
            } catch (CancellationException unused) {
                cancel(false);
            } catch (ExecutionException e2) {
                cause = e2.getCause();
                setException(cause);
            } catch (RuntimeException e3) {
                setException(e3);
            } catch (Error e4) {
                setException(e4);
            }
        }
    }
}

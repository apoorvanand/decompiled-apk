package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.CollectionFuture;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.Partially;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
@Beta
public final class Futures extends GwtFuturesCatchingSpecialization {
    private static final AsyncFunction<ListenableFuture<Object>, Object> DEREFERENCER = new AsyncFunction<ListenableFuture<Object>, Object>() {
        public ListenableFuture<Object> apply(ListenableFuture<Object> listenableFuture) {
            return listenableFuture;
        }
    };

    @GwtCompatible
    @CanIgnoreReturnValue
    @Beta
    public static final class FutureCombiner<V> {
        private final boolean allMustSucceed;
        private final ImmutableList<ListenableFuture<? extends V>> futures;

        private FutureCombiner(boolean z, ImmutableList<ListenableFuture<? extends V>> immutableList) {
            this.allMustSucceed = z;
            this.futures = immutableList;
        }

        @CanIgnoreReturnValue
        public <C> ListenableFuture<C> call(Callable<C> callable) {
            return call(callable, MoreExecutors.directExecutor());
        }

        @CanIgnoreReturnValue
        public <C> ListenableFuture<C> call(Callable<C> callable, Executor executor) {
            return new CombinedFuture((ImmutableCollection<? extends ListenableFuture<?>>) this.futures, this.allMustSucceed, executor, callable);
        }

        public <C> ListenableFuture<C> callAsync(AsyncCallable<C> asyncCallable) {
            return callAsync(asyncCallable, MoreExecutors.directExecutor());
        }

        public <C> ListenableFuture<C> callAsync(AsyncCallable<C> asyncCallable, Executor executor) {
            return new CombinedFuture((ImmutableCollection<? extends ListenableFuture<?>>) this.futures, this.allMustSucceed, executor, asyncCallable);
        }
    }

    @GwtIncompatible
    private static class MappingCheckedFuture<V, X extends Exception> extends AbstractCheckedFuture<V, X> {
        final Function<? super Exception, X> a;

        MappingCheckedFuture(ListenableFuture<V> listenableFuture, Function<? super Exception, X> function) {
            super(listenableFuture);
            this.a = (Function) Preconditions.checkNotNull(function);
        }

        /* access modifiers changed from: protected */
        public X a(Exception exc) {
            return (Exception) this.a.apply(exc);
        }
    }

    private static final class NonCancellationPropagatingFuture<V> extends AbstractFuture.TrustedFuture<V> {
        NonCancellationPropagatingFuture(final ListenableFuture<V> listenableFuture) {
            listenableFuture.addListener(new Runnable() {
                public void run() {
                    NonCancellationPropagatingFuture.this.setFuture(listenableFuture);
                }
            }, MoreExecutors.directExecutor());
        }
    }

    private Futures() {
    }

    public static <V> void addCallback(ListenableFuture<V> listenableFuture, FutureCallback<? super V> futureCallback) {
        addCallback(listenableFuture, futureCallback, MoreExecutors.directExecutor());
    }

    public static <V> void addCallback(final ListenableFuture<V> listenableFuture, final FutureCallback<? super V> futureCallback, Executor executor) {
        Preconditions.checkNotNull(futureCallback);
        listenableFuture.addListener(new Runnable() {
            public void run() {
                try {
                    futureCallback.onSuccess(Futures.getDone(listenableFuture));
                } catch (ExecutionException e) {
                    futureCallback.onFailure(e.getCause());
                } catch (RuntimeException e2) {
                    futureCallback.onFailure(e2);
                } catch (Error e3) {
                    futureCallback.onFailure(e3);
                }
            }
        }, executor);
    }

    @Beta
    public static <V> ListenableFuture<List<V>> allAsList(Iterable<? extends ListenableFuture<? extends V>> iterable) {
        return new CollectionFuture.ListFuture(ImmutableList.copyOf(iterable), true);
    }

    @SafeVarargs
    @Beta
    public static <V> ListenableFuture<List<V>> allAsList(ListenableFuture<? extends V>... listenableFutureArr) {
        return new CollectionFuture.ListFuture(ImmutableList.copyOf((E[]) listenableFutureArr), true);
    }

    @Partially.GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    public static <V, X extends Throwable> ListenableFuture<V> catching(ListenableFuture<? extends V> listenableFuture, Class<X> cls, Function<? super X, ? extends V> function) {
        return AbstractCatchingFuture.a(listenableFuture, cls, function);
    }

    @Partially.GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    public static <V, X extends Throwable> ListenableFuture<V> catching(ListenableFuture<? extends V> listenableFuture, Class<X> cls, Function<? super X, ? extends V> function, Executor executor) {
        return AbstractCatchingFuture.a(listenableFuture, cls, function, executor);
    }

    @CanIgnoreReturnValue
    @Partially.GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    public static <V, X extends Throwable> ListenableFuture<V> catchingAsync(ListenableFuture<? extends V> listenableFuture, Class<X> cls, AsyncFunction<? super X, ? extends V> asyncFunction) {
        return AbstractCatchingFuture.a(listenableFuture, cls, asyncFunction);
    }

    @CanIgnoreReturnValue
    @Partially.GwtIncompatible("AVAILABLE but requires exceptionType to be Throwable.class")
    public static <V, X extends Throwable> ListenableFuture<V> catchingAsync(ListenableFuture<? extends V> listenableFuture, Class<X> cls, AsyncFunction<? super X, ? extends V> asyncFunction, Executor executor) {
        return AbstractCatchingFuture.a(listenableFuture, cls, asyncFunction, executor);
    }

    public static <V> ListenableFuture<V> dereference(ListenableFuture<? extends ListenableFuture<? extends V>> listenableFuture) {
        return transformAsync(listenableFuture, DEREFERENCER);
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    public static <V, X extends Exception> V getChecked(Future<V> future, Class<X> cls) {
        return FuturesGetChecked.a(future, cls);
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    public static <V, X extends Exception> V getChecked(Future<V> future, Class<X> cls, long j, TimeUnit timeUnit) {
        return FuturesGetChecked.a(future, cls, j, timeUnit);
    }

    @CanIgnoreReturnValue
    public static <V> V getDone(Future<V> future) {
        Preconditions.checkState(future.isDone(), "Future was expected to be done: %s", (Object) future);
        return Uninterruptibles.getUninterruptibly(future);
    }

    @GwtIncompatible
    @CanIgnoreReturnValue
    public static <V> V getUnchecked(Future<V> future) {
        Preconditions.checkNotNull(future);
        try {
            return Uninterruptibles.getUninterruptibly(future);
        } catch (ExecutionException e) {
            wrapAndThrowUnchecked(e.getCause());
            throw new AssertionError();
        }
    }

    public static <V> ListenableFuture<V> immediateCancelledFuture() {
        return new ImmediateFuture.ImmediateCancelledFuture();
    }

    @GwtIncompatible
    public static <V, X extends Exception> CheckedFuture<V, X> immediateCheckedFuture(@Nullable V v) {
        return new ImmediateFuture.ImmediateSuccessfulCheckedFuture(v);
    }

    @GwtIncompatible
    public static <V, X extends Exception> CheckedFuture<V, X> immediateFailedCheckedFuture(X x) {
        Preconditions.checkNotNull(x);
        return new ImmediateFuture.ImmediateFailedCheckedFuture(x);
    }

    public static <V> ListenableFuture<V> immediateFailedFuture(Throwable th) {
        Preconditions.checkNotNull(th);
        return new ImmediateFuture.ImmediateFailedFuture(th);
    }

    public static <V> ListenableFuture<V> immediateFuture(@Nullable V v) {
        return v == null ? ImmediateFuture.ImmediateSuccessfulFuture.a : new ImmediateFuture.ImmediateSuccessfulFuture(v);
    }

    @GwtIncompatible
    @Beta
    public static <T> ImmutableList<ListenableFuture<T>> inCompletionOrder(Iterable<? extends ListenableFuture<? extends T>> iterable) {
        final ConcurrentLinkedQueue newConcurrentLinkedQueue = Queues.newConcurrentLinkedQueue();
        ImmutableList.Builder builder = ImmutableList.builder();
        SerializingExecutor serializingExecutor = new SerializingExecutor(MoreExecutors.directExecutor());
        for (final ListenableFuture listenableFuture : iterable) {
            SettableFuture create = SettableFuture.create();
            newConcurrentLinkedQueue.add(create);
            listenableFuture.addListener(new Runnable() {
                public void run() {
                    ((SettableFuture) newConcurrentLinkedQueue.remove()).setFuture(listenableFuture);
                }
            }, serializingExecutor);
            builder.add((Object) create);
        }
        return builder.build();
    }

    @GwtIncompatible
    public static <I, O> Future<O> lazyTransform(final Future<I> future, final Function<? super I, ? extends O> function) {
        Preconditions.checkNotNull(future);
        Preconditions.checkNotNull(function);
        return new Future<O>() {
            private O applyTransformation(I i) {
                try {
                    return function.apply(i);
                } catch (Throwable th) {
                    throw new ExecutionException(th);
                }
            }

            public boolean cancel(boolean z) {
                return future.cancel(z);
            }

            public O get() {
                return applyTransformation(future.get());
            }

            public O get(long j, TimeUnit timeUnit) {
                return applyTransformation(future.get(j, timeUnit));
            }

            public boolean isCancelled() {
                return future.isCancelled();
            }

            public boolean isDone() {
                return future.isDone();
            }
        };
    }

    @GwtIncompatible
    public static <V, X extends Exception> CheckedFuture<V, X> makeChecked(ListenableFuture<V> listenableFuture, Function<? super Exception, X> function) {
        return new MappingCheckedFuture((ListenableFuture) Preconditions.checkNotNull(listenableFuture), function);
    }

    public static <V> ListenableFuture<V> nonCancellationPropagating(ListenableFuture<V> listenableFuture) {
        return new NonCancellationPropagatingFuture(listenableFuture);
    }

    @Beta
    public static <V> ListenableFuture<List<V>> successfulAsList(Iterable<? extends ListenableFuture<? extends V>> iterable) {
        return new CollectionFuture.ListFuture(ImmutableList.copyOf(iterable), false);
    }

    @SafeVarargs
    @Beta
    public static <V> ListenableFuture<List<V>> successfulAsList(ListenableFuture<? extends V>... listenableFutureArr) {
        return new CollectionFuture.ListFuture(ImmutableList.copyOf((E[]) listenableFutureArr), false);
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> listenableFuture, Function<? super I, ? extends O> function) {
        return AbstractTransformFuture.a(listenableFuture, function);
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> listenableFuture, Function<? super I, ? extends O> function, Executor executor) {
        return AbstractTransformFuture.a(listenableFuture, function, executor);
    }

    public static <I, O> ListenableFuture<O> transformAsync(ListenableFuture<I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction) {
        return AbstractTransformFuture.a(listenableFuture, asyncFunction);
    }

    public static <I, O> ListenableFuture<O> transformAsync(ListenableFuture<I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction, Executor executor) {
        return AbstractTransformFuture.a(listenableFuture, asyncFunction, executor);
    }

    public static <V> FutureCombiner<V> whenAllComplete(Iterable<? extends ListenableFuture<? extends V>> iterable) {
        return new FutureCombiner<>(false, ImmutableList.copyOf(iterable));
    }

    @SafeVarargs
    public static <V> FutureCombiner<V> whenAllComplete(ListenableFuture<? extends V>... listenableFutureArr) {
        return new FutureCombiner<>(false, ImmutableList.copyOf((E[]) listenableFutureArr));
    }

    public static <V> FutureCombiner<V> whenAllSucceed(Iterable<? extends ListenableFuture<? extends V>> iterable) {
        return new FutureCombiner<>(true, ImmutableList.copyOf(iterable));
    }

    @SafeVarargs
    public static <V> FutureCombiner<V> whenAllSucceed(ListenableFuture<? extends V>... listenableFutureArr) {
        return new FutureCombiner<>(true, ImmutableList.copyOf((E[]) listenableFutureArr));
    }

    @GwtIncompatible
    public static <V> ListenableFuture<V> withTimeout(ListenableFuture<V> listenableFuture, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        return TimeoutFuture.a(listenableFuture, j, timeUnit, scheduledExecutorService);
    }

    @GwtIncompatible
    private static void wrapAndThrowUnchecked(Throwable th) {
        if (th instanceof Error) {
            throw new ExecutionError((Error) th);
        }
        throw new UncheckedExecutionException(th);
    }
}

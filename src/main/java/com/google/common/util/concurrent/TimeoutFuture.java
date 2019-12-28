package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;

@GwtIncompatible
final class TimeoutFuture<V> extends AbstractFuture.TrustedFuture<V> {
    /* access modifiers changed from: private */
    @Nullable
    public ListenableFuture<V> delegateRef;
    @Nullable
    private Future<?> timer;

    private static final class Fire<V> implements Runnable {
        @Nullable
        TimeoutFuture<V> a;

        Fire(TimeoutFuture<V> timeoutFuture) {
            this.a = timeoutFuture;
        }

        public void run() {
            ListenableFuture a2;
            TimeoutFuture<V> timeoutFuture = this.a;
            if (timeoutFuture != null && (a2 = timeoutFuture.delegateRef) != null) {
                this.a = null;
                if (a2.isDone()) {
                    timeoutFuture.setFuture(a2);
                    return;
                }
                try {
                    timeoutFuture.setException(new TimeoutException("Future timed out: " + a2));
                } finally {
                    a2.cancel(true);
                }
            }
        }
    }

    private TimeoutFuture(ListenableFuture<V> listenableFuture) {
        this.delegateRef = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
    }

    static <V> ListenableFuture<V> a(ListenableFuture<V> listenableFuture, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        TimeoutFuture timeoutFuture = new TimeoutFuture(listenableFuture);
        Fire fire = new Fire(timeoutFuture);
        timeoutFuture.timer = scheduledExecutorService.schedule(fire, j, timeUnit);
        listenableFuture.addListener(fire, MoreExecutors.directExecutor());
        return timeoutFuture;
    }

    /* access modifiers changed from: protected */
    public void a() {
        a((Future<?>) this.delegateRef);
        Future<?> future = this.timer;
        if (future != null) {
            future.cancel(false);
        }
        this.delegateRef = null;
        this.timer = null;
    }
}

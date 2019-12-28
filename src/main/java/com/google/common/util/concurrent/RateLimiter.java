package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.SmoothRateLimiter;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.ThreadSafe;

@GwtIncompatible
@ThreadSafe
@Beta
public abstract class RateLimiter {
    private volatile Object mutexDoNotUseDirectly;
    private final SleepingStopwatch stopwatch;

    static abstract class SleepingStopwatch {
        protected SleepingStopwatch() {
        }

        public static final SleepingStopwatch createFromSystemTimer() {
            return new SleepingStopwatch() {
                final Stopwatch a = Stopwatch.createStarted();

                /* access modifiers changed from: protected */
                public long a() {
                    return this.a.elapsed(TimeUnit.MICROSECONDS);
                }

                /* access modifiers changed from: protected */
                public void a(long j) {
                    if (j > 0) {
                        Uninterruptibles.sleepUninterruptibly(j, TimeUnit.MICROSECONDS);
                    }
                }
            };
        }

        /* access modifiers changed from: protected */
        public abstract long a();

        /* access modifiers changed from: protected */
        public abstract void a(long j);
    }

    RateLimiter(SleepingStopwatch sleepingStopwatch) {
        this.stopwatch = (SleepingStopwatch) Preconditions.checkNotNull(sleepingStopwatch);
    }

    @VisibleForTesting
    static RateLimiter a(SleepingStopwatch sleepingStopwatch, double d) {
        SmoothRateLimiter.SmoothBursty smoothBursty = new SmoothRateLimiter.SmoothBursty(sleepingStopwatch, 1.0d);
        smoothBursty.setRate(d);
        return smoothBursty;
    }

    @VisibleForTesting
    static RateLimiter a(SleepingStopwatch sleepingStopwatch, double d, long j, TimeUnit timeUnit, double d2) {
        SmoothRateLimiter.SmoothWarmingUp smoothWarmingUp = new SmoothRateLimiter.SmoothWarmingUp(sleepingStopwatch, j, timeUnit, d2);
        smoothWarmingUp.setRate(d);
        return smoothWarmingUp;
    }

    private boolean canAcquire(long j, long j2) {
        return a(j) - j2 <= j;
    }

    private static void checkPermits(int i) {
        Preconditions.checkArgument(i > 0, "Requested permits (%s) must be positive", i);
    }

    public static RateLimiter create(double d) {
        return a(SleepingStopwatch.createFromSystemTimer(), d);
    }

    public static RateLimiter create(double d, long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j >= 0, "warmupPeriod must not be negative: %s", j);
        return a(SleepingStopwatch.createFromSystemTimer(), d, j, timeUnit, 3.0d);
    }

    private Object mutex() {
        Object obj = this.mutexDoNotUseDirectly;
        if (obj == null) {
            synchronized (this) {
                obj = this.mutexDoNotUseDirectly;
                if (obj == null) {
                    obj = new Object();
                    this.mutexDoNotUseDirectly = obj;
                }
            }
        }
        return obj;
    }

    /* access modifiers changed from: package-private */
    public abstract double a();

    /* access modifiers changed from: package-private */
    public final long a(int i) {
        long a;
        checkPermits(i);
        synchronized (mutex()) {
            a = a(i, this.stopwatch.a());
        }
        return a;
    }

    /* access modifiers changed from: package-private */
    public final long a(int i, long j) {
        return Math.max(b(i, j) - j, 0);
    }

    /* access modifiers changed from: package-private */
    public abstract long a(long j);

    /* access modifiers changed from: package-private */
    public abstract void a(double d, long j);

    @CanIgnoreReturnValue
    public double acquire() {
        return acquire(1);
    }

    @CanIgnoreReturnValue
    public double acquire(int i) {
        long a = a(i);
        this.stopwatch.a(a);
        return (((double) a) * 1.0d) / ((double) TimeUnit.SECONDS.toMicros(1));
    }

    /* access modifiers changed from: package-private */
    public abstract long b(int i, long j);

    public final double getRate() {
        double a;
        synchronized (mutex()) {
            a = a();
        }
        return a;
    }

    public final void setRate(double d) {
        Preconditions.checkArgument(d > 0.0d && !Double.isNaN(d), "rate must be positive");
        synchronized (mutex()) {
            a(d, this.stopwatch.a());
        }
    }

    public String toString() {
        return String.format(Locale.ROOT, "RateLimiter[stableRate=%3.1fqps]", new Object[]{Double.valueOf(getRate())});
    }

    public boolean tryAcquire() {
        return tryAcquire(1, 0, TimeUnit.MICROSECONDS);
    }

    public boolean tryAcquire(int i) {
        return tryAcquire(i, 0, TimeUnit.MICROSECONDS);
    }

    public boolean tryAcquire(int i, long j, TimeUnit timeUnit) {
        long max = Math.max(timeUnit.toMicros(j), 0);
        checkPermits(i);
        synchronized (mutex()) {
            long a = this.stopwatch.a();
            if (!canAcquire(a, max)) {
                return false;
            }
            long a2 = a(i, a);
            this.stopwatch.a(a2);
            return true;
        }
    }

    public boolean tryAcquire(long j, TimeUnit timeUnit) {
        return tryAcquire(1, j, timeUnit);
    }
}

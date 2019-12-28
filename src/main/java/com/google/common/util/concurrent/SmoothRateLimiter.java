package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.math.LongMath;
import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
abstract class SmoothRateLimiter extends RateLimiter {
    double a;
    double b;
    double c;
    private long nextFreeTicketMicros;

    static final class SmoothBursty extends SmoothRateLimiter {
        final double d;

        SmoothBursty(RateLimiter.SleepingStopwatch sleepingStopwatch, double d2) {
            super(sleepingStopwatch);
            this.d = d2;
        }

        /* access modifiers changed from: package-private */
        public void a(double d2, double d3) {
            double d4;
            double d5 = this.b;
            this.b = this.d * d2;
            if (d5 == Double.POSITIVE_INFINITY) {
                d4 = this.b;
            } else {
                d4 = 0.0d;
                if (d5 != 0.0d) {
                    d4 = (this.a * this.b) / d5;
                }
            }
            this.a = d4;
        }

        /* access modifiers changed from: package-private */
        public double b() {
            return this.c;
        }

        /* access modifiers changed from: package-private */
        public long b(double d2, double d3) {
            return 0;
        }
    }

    static final class SmoothWarmingUp extends SmoothRateLimiter {
        private double coldFactor;
        private double slope;
        private double thresholdPermits;
        private final long warmupPeriodMicros;

        SmoothWarmingUp(RateLimiter.SleepingStopwatch sleepingStopwatch, long j, TimeUnit timeUnit, double d) {
            super(sleepingStopwatch);
            this.warmupPeriodMicros = timeUnit.toMicros(j);
            this.coldFactor = d;
        }

        private double permitsToTime(double d) {
            return this.c + (d * this.slope);
        }

        /* access modifiers changed from: package-private */
        public void a(double d, double d2) {
            double d3 = this.b;
            double d4 = this.coldFactor * d2;
            long j = this.warmupPeriodMicros;
            this.thresholdPermits = (((double) j) * 0.5d) / d2;
            this.b = this.thresholdPermits + ((((double) j) * 2.0d) / (d2 + d4));
            this.slope = (d4 - d2) / (this.b - this.thresholdPermits);
            if (d3 == Double.POSITIVE_INFINITY) {
                this.a = 0.0d;
            } else {
                this.a = d3 == 0.0d ? this.b : (this.a * this.b) / d3;
            }
        }

        /* access modifiers changed from: package-private */
        public double b() {
            return ((double) this.warmupPeriodMicros) / this.b;
        }

        /* access modifiers changed from: package-private */
        public long b(double d, double d2) {
            long j;
            double d3 = d - this.thresholdPermits;
            if (d3 > 0.0d) {
                double min = Math.min(d3, d2);
                j = (long) (((permitsToTime(d3) + permitsToTime(d3 - min)) * min) / 2.0d);
                d2 -= min;
            } else {
                j = 0;
            }
            return (long) (((double) j) + (this.c * d2));
        }
    }

    private SmoothRateLimiter(RateLimiter.SleepingStopwatch sleepingStopwatch) {
        super(sleepingStopwatch);
        this.nextFreeTicketMicros = 0;
    }

    /* access modifiers changed from: package-private */
    public final double a() {
        return ((double) TimeUnit.SECONDS.toMicros(1)) / this.c;
    }

    /* access modifiers changed from: package-private */
    public final long a(long j) {
        return this.nextFreeTicketMicros;
    }

    /* access modifiers changed from: package-private */
    public abstract void a(double d, double d2);

    /* access modifiers changed from: package-private */
    public final void a(double d, long j) {
        b(j);
        double micros = ((double) TimeUnit.SECONDS.toMicros(1)) / d;
        this.c = micros;
        a(d, micros);
    }

    /* access modifiers changed from: package-private */
    public abstract double b();

    /* access modifiers changed from: package-private */
    public abstract long b(double d, double d2);

    /* access modifiers changed from: package-private */
    public final long b(int i, long j) {
        b(j);
        long j2 = this.nextFreeTicketMicros;
        double d = (double) i;
        double min = Math.min(d, this.a);
        this.nextFreeTicketMicros = LongMath.saturatedAdd(this.nextFreeTicketMicros, b(this.a, min) + ((long) ((d - min) * this.c)));
        this.a -= min;
        return j2;
    }

    /* access modifiers changed from: package-private */
    public void b(long j) {
        long j2 = this.nextFreeTicketMicros;
        if (j > j2) {
            this.a = Math.min(this.b, this.a + (((double) (j - j2)) / b()));
            this.nextFreeTicketMicros = j;
        }
    }
}

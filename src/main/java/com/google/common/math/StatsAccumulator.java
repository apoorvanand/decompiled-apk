package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
import java.util.Iterator;

@GwtIncompatible
@Beta
public final class StatsAccumulator {
    private long count = 0;
    private double max = Double.NaN;
    private double mean = 0.0d;
    private double min = Double.NaN;
    private double sumOfSquaresOfDeltas = 0.0d;

    static double a(double d, double d2) {
        if (Doubles.isFinite(d)) {
            return d2;
        }
        if (Doubles.isFinite(d2) || d == d2) {
            return d;
        }
        return Double.NaN;
    }

    /* access modifiers changed from: package-private */
    public double a() {
        return this.sumOfSquaresOfDeltas;
    }

    public void add(double d) {
        long j = this.count;
        if (j == 0) {
            this.count = 1;
            this.mean = d;
            this.min = d;
            this.max = d;
            if (!Doubles.isFinite(d)) {
                this.sumOfSquaresOfDeltas = Double.NaN;
                return;
            }
            return;
        }
        this.count = j + 1;
        if (!Doubles.isFinite(d) || !Doubles.isFinite(this.mean)) {
            this.mean = a(this.mean, d);
            this.sumOfSquaresOfDeltas = Double.NaN;
        } else {
            double d2 = this.mean;
            double d3 = d - d2;
            this.mean = d2 + (d3 / ((double) this.count));
            this.sumOfSquaresOfDeltas += d3 * (d - this.mean);
        }
        this.min = Math.min(this.min, d);
        this.max = Math.max(this.max, d);
    }

    public void addAll(Stats stats) {
        double max2;
        if (stats.count() != 0) {
            long j = this.count;
            if (j == 0) {
                this.count = stats.count();
                this.mean = stats.mean();
                this.sumOfSquaresOfDeltas = stats.a();
                this.min = stats.min();
                max2 = stats.max();
            } else {
                this.count = j + stats.count();
                if (!Doubles.isFinite(this.mean) || !Doubles.isFinite(stats.mean())) {
                    this.mean = a(this.mean, stats.mean());
                    this.sumOfSquaresOfDeltas = Double.NaN;
                } else {
                    double mean2 = stats.mean();
                    double d = this.mean;
                    double d2 = mean2 - d;
                    this.mean = d + ((((double) stats.count()) * d2) / ((double) this.count));
                    this.sumOfSquaresOfDeltas += stats.a() + (d2 * (stats.mean() - this.mean) * ((double) stats.count()));
                }
                this.min = Math.min(this.min, stats.min());
                max2 = Math.max(this.max, stats.max());
            }
            this.max = max2;
        }
    }

    public void addAll(Iterable<? extends Number> iterable) {
        for (Number doubleValue : iterable) {
            add(doubleValue.doubleValue());
        }
    }

    public void addAll(Iterator<? extends Number> it) {
        while (it.hasNext()) {
            add(((Number) it.next()).doubleValue());
        }
    }

    public void addAll(double... dArr) {
        for (double add : dArr) {
            add(add);
        }
    }

    public void addAll(int... iArr) {
        for (int i : iArr) {
            add((double) i);
        }
    }

    public void addAll(long... jArr) {
        for (long j : jArr) {
            add((double) j);
        }
    }

    public long count() {
        return this.count;
    }

    public double max() {
        Preconditions.checkState(this.count != 0);
        return this.max;
    }

    public double mean() {
        Preconditions.checkState(this.count != 0);
        return this.mean;
    }

    public double min() {
        Preconditions.checkState(this.count != 0);
        return this.min;
    }

    public final double populationStandardDeviation() {
        return Math.sqrt(populationVariance());
    }

    public final double populationVariance() {
        Preconditions.checkState(this.count != 0);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        if (this.count == 1) {
            return 0.0d;
        }
        return DoubleUtils.e(this.sumOfSquaresOfDeltas) / ((double) this.count);
    }

    public final double sampleStandardDeviation() {
        return Math.sqrt(sampleVariance());
    }

    public final double sampleVariance() {
        Preconditions.checkState(this.count > 1);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        return DoubleUtils.e(this.sumOfSquaresOfDeltas) / ((double) (this.count - 1));
    }

    public Stats snapshot() {
        return new Stats(this.count, this.mean, this.sumOfSquaresOfDeltas, this.min, this.max);
    }

    public final double sum() {
        return this.mean * ((double) this.count);
    }
}

package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;

@GwtIncompatible
@Beta
public final class PairedStatsAccumulator {
    private double sumOfProductsOfDeltas = 0.0d;
    private final StatsAccumulator xStats = new StatsAccumulator();
    private final StatsAccumulator yStats = new StatsAccumulator();

    private static double ensureInUnitRange(double d) {
        if (d >= 1.0d) {
            return 1.0d;
        }
        if (d <= -1.0d) {
            return -1.0d;
        }
        return d;
    }

    private double ensurePositive(double d) {
        if (d > 0.0d) {
            return d;
        }
        return Double.MIN_VALUE;
    }

    public void add(double d, double d2) {
        this.xStats.add(d);
        if (!Doubles.isFinite(d) || !Doubles.isFinite(d2)) {
            this.sumOfProductsOfDeltas = Double.NaN;
        } else if (this.xStats.count() > 1) {
            this.sumOfProductsOfDeltas += (d - this.xStats.mean()) * (d2 - this.yStats.mean());
        }
        this.yStats.add(d2);
    }

    public void addAll(PairedStats pairedStats) {
        if (pairedStats.count() != 0) {
            this.xStats.addAll(pairedStats.xStats());
            this.sumOfProductsOfDeltas = this.yStats.count() == 0 ? pairedStats.a() : this.sumOfProductsOfDeltas + pairedStats.a() + ((pairedStats.xStats().mean() - this.xStats.mean()) * (pairedStats.yStats().mean() - this.yStats.mean()) * ((double) pairedStats.count()));
            this.yStats.addAll(pairedStats.yStats());
        }
    }

    public long count() {
        return this.xStats.count();
    }

    public final LinearTransformation leastSquaresFit() {
        boolean z = true;
        Preconditions.checkState(count() > 1);
        if (Double.isNaN(this.sumOfProductsOfDeltas)) {
            return LinearTransformation.forNaN();
        }
        double a = this.xStats.a();
        if (a > 0.0d) {
            return this.yStats.a() > 0.0d ? LinearTransformation.mapping(this.xStats.mean(), this.yStats.mean()).withSlope(this.sumOfProductsOfDeltas / a) : LinearTransformation.horizontal(this.yStats.mean());
        }
        if (this.yStats.a() <= 0.0d) {
            z = false;
        }
        Preconditions.checkState(z);
        return LinearTransformation.vertical(this.xStats.mean());
    }

    public final double pearsonsCorrelationCoefficient() {
        boolean z = true;
        Preconditions.checkState(count() > 1);
        if (Double.isNaN(this.sumOfProductsOfDeltas)) {
            return Double.NaN;
        }
        double a = this.xStats.a();
        double a2 = this.yStats.a();
        Preconditions.checkState(a > 0.0d);
        if (a2 <= 0.0d) {
            z = false;
        }
        Preconditions.checkState(z);
        return ensureInUnitRange(this.sumOfProductsOfDeltas / Math.sqrt(ensurePositive(a * a2)));
    }

    public double populationCovariance() {
        Preconditions.checkState(count() != 0);
        return this.sumOfProductsOfDeltas / ((double) count());
    }

    public final double sampleCovariance() {
        Preconditions.checkState(count() > 1);
        return this.sumOfProductsOfDeltas / ((double) (count() - 1));
    }

    public PairedStats snapshot() {
        return new PairedStats(this.xStats.snapshot(), this.yStats.snapshot(), this.sumOfProductsOfDeltas);
    }

    public Stats xStats() {
        return this.xStats.snapshot();
    }

    public Stats yStats() {
        return this.yStats.snapshot();
    }
}

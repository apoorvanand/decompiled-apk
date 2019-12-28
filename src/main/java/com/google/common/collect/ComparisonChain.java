package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.primitives.Booleans;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.util.Comparator;
import javax.annotation.Nullable;

@GwtCompatible
public abstract class ComparisonChain {
    /* access modifiers changed from: private */
    public static final ComparisonChain ACTIVE = new ComparisonChain() {
        /* access modifiers changed from: package-private */
        public ComparisonChain a(int i) {
            return i < 0 ? ComparisonChain.LESS : i > 0 ? ComparisonChain.GREATER : ComparisonChain.ACTIVE;
        }

        public ComparisonChain compare(double d, double d2) {
            return a(Double.compare(d, d2));
        }

        public ComparisonChain compare(float f, float f2) {
            return a(Float.compare(f, f2));
        }

        public ComparisonChain compare(int i, int i2) {
            return a(Ints.compare(i, i2));
        }

        public ComparisonChain compare(long j, long j2) {
            return a(Longs.compare(j, j2));
        }

        public ComparisonChain compare(Comparable comparable, Comparable comparable2) {
            return a(comparable.compareTo(comparable2));
        }

        public <T> ComparisonChain compare(@Nullable T t, @Nullable T t2, Comparator<T> comparator) {
            return a(comparator.compare(t, t2));
        }

        public ComparisonChain compareFalseFirst(boolean z, boolean z2) {
            return a(Booleans.compare(z, z2));
        }

        public ComparisonChain compareTrueFirst(boolean z, boolean z2) {
            return a(Booleans.compare(z2, z));
        }

        public int result() {
            return 0;
        }
    };
    /* access modifiers changed from: private */
    public static final ComparisonChain GREATER = new InactiveComparisonChain(1);
    /* access modifiers changed from: private */
    public static final ComparisonChain LESS = new InactiveComparisonChain(-1);

    private static final class InactiveComparisonChain extends ComparisonChain {
        final int a;

        InactiveComparisonChain(int i) {
            super();
            this.a = i;
        }

        public ComparisonChain compare(double d, double d2) {
            return this;
        }

        public ComparisonChain compare(float f, float f2) {
            return this;
        }

        public ComparisonChain compare(int i, int i2) {
            return this;
        }

        public ComparisonChain compare(long j, long j2) {
            return this;
        }

        public ComparisonChain compare(@Nullable Comparable comparable, @Nullable Comparable comparable2) {
            return this;
        }

        public <T> ComparisonChain compare(@Nullable T t, @Nullable T t2, @Nullable Comparator<T> comparator) {
            return this;
        }

        public ComparisonChain compareFalseFirst(boolean z, boolean z2) {
            return this;
        }

        public ComparisonChain compareTrueFirst(boolean z, boolean z2) {
            return this;
        }

        public int result() {
            return this.a;
        }
    }

    private ComparisonChain() {
    }

    public static ComparisonChain start() {
        return ACTIVE;
    }

    public abstract ComparisonChain compare(double d, double d2);

    public abstract ComparisonChain compare(float f, float f2);

    public abstract ComparisonChain compare(int i, int i2);

    public abstract ComparisonChain compare(long j, long j2);

    @Deprecated
    public final ComparisonChain compare(Boolean bool, Boolean bool2) {
        return compareFalseFirst(bool.booleanValue(), bool2.booleanValue());
    }

    public abstract ComparisonChain compare(Comparable<?> comparable, Comparable<?> comparable2);

    public abstract <T> ComparisonChain compare(@Nullable T t, @Nullable T t2, Comparator<T> comparator);

    public abstract ComparisonChain compareFalseFirst(boolean z, boolean z2);

    public abstract ComparisonChain compareTrueFirst(boolean z, boolean z2);

    public abstract int result();
}

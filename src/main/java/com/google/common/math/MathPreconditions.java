package com.google.common.math;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.math.BigInteger;
import javax.annotation.Nullable;

@GwtCompatible
@CanIgnoreReturnValue
final class MathPreconditions {
    private MathPreconditions() {
    }

    static double a(@Nullable String str, double d) {
        if (d >= 0.0d) {
            return d;
        }
        throw new IllegalArgumentException(str + " (" + d + ") must be >= 0");
    }

    static int a(@Nullable String str, int i) {
        if (i > 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " (" + i + ") must be > 0");
    }

    static long a(@Nullable String str, long j) {
        if (j > 0) {
            return j;
        }
        throw new IllegalArgumentException(str + " (" + j + ") must be > 0");
    }

    static BigInteger a(@Nullable String str, BigInteger bigInteger) {
        if (bigInteger.signum() > 0) {
            return bigInteger;
        }
        throw new IllegalArgumentException(str + " (" + bigInteger + ") must be > 0");
    }

    static void a(boolean z) {
        if (!z) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }

    static int b(@Nullable String str, int i) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " (" + i + ") must be >= 0");
    }

    static long b(@Nullable String str, long j) {
        if (j >= 0) {
            return j;
        }
        throw new IllegalArgumentException(str + " (" + j + ") must be >= 0");
    }

    static BigInteger b(@Nullable String str, BigInteger bigInteger) {
        if (bigInteger.signum() >= 0) {
            return bigInteger;
        }
        throw new IllegalArgumentException(str + " (" + bigInteger + ") must be >= 0");
    }

    static void b(boolean z) {
        if (!z) {
            throw new ArithmeticException("not in range");
        }
    }

    static void c(boolean z) {
        if (!z) {
            throw new ArithmeticException("overflow");
        }
    }
}

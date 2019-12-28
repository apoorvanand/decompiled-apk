package com.google.common.math;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.math.BigInteger;

@GwtIncompatible
final class DoubleUtils {
    private static final long ONE_BITS = Double.doubleToRawLongBits(1.0d);

    private DoubleUtils() {
    }

    static double a(BigInteger bigInteger) {
        BigInteger abs = bigInteger.abs();
        boolean z = true;
        int bitLength = abs.bitLength() - 1;
        if (bitLength < 63) {
            return (double) bigInteger.longValue();
        }
        if (bitLength > 1023) {
            return ((double) bigInteger.signum()) * Double.POSITIVE_INFINITY;
        }
        int i = (bitLength - 52) - 1;
        long longValue = abs.shiftRight(i).longValue();
        long j = (longValue >> 1) & 4503599627370495L;
        if ((longValue & 1) == 0 || ((j & 1) == 0 && abs.getLowestSetBit() >= i)) {
            z = false;
        }
        if (z) {
            j++;
        }
        return Double.longBitsToDouble(((((long) (bitLength + 1023)) << 52) + j) | (((long) bigInteger.signum()) & Long.MIN_VALUE));
    }

    static long a(double d) {
        Preconditions.checkArgument(b(d), "not a normal value");
        int exponent = Math.getExponent(d);
        long doubleToRawLongBits = Double.doubleToRawLongBits(d) & 4503599627370495L;
        return exponent == -1023 ? doubleToRawLongBits << 1 : doubleToRawLongBits | 4503599627370496L;
    }

    static boolean b(double d) {
        return Math.getExponent(d) <= 1023;
    }

    static boolean c(double d) {
        return Math.getExponent(d) >= -1022;
    }

    static double d(double d) {
        return Double.longBitsToDouble((Double.doubleToRawLongBits(d) & 4503599627370495L) | ONE_BITS);
    }

    static double e(double d) {
        Preconditions.checkArgument(!Double.isNaN(d));
        if (d > 0.0d) {
            return d;
        }
        return 0.0d;
    }
}

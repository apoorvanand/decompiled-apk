package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteOrder;
import java.util.Comparator;
import sun.misc.Unsafe;

@GwtIncompatible
public final class UnsignedBytes {
    public static final byte MAX_POWER_OF_TWO = Byte.MIN_VALUE;
    public static final byte MAX_VALUE = -1;
    private static final int UNSIGNED_MASK = 255;

    @VisibleForTesting
    static class LexicographicalComparatorHolder {
        static final String a = (LexicographicalComparatorHolder.class.getName() + "$UnsafeComparator");
        static final Comparator<byte[]> b = a();

        enum PureJavaComparator implements Comparator<byte[]> {
            INSTANCE;

            public int compare(byte[] bArr, byte[] bArr2) {
                int min = Math.min(bArr.length, bArr2.length);
                for (int i = 0; i < min; i++) {
                    int compare = UnsignedBytes.compare(bArr[i], bArr2[i]);
                    if (compare != 0) {
                        return compare;
                    }
                }
                return bArr.length - bArr2.length;
            }

            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (pure Java version)";
            }
        }

        @VisibleForTesting
        enum UnsafeComparator implements Comparator<byte[]> {
            INSTANCE;
            
            static final boolean a = false;
            static final Unsafe b = null;
            static final int c = 0;

            static {
                a = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
                b = getUnsafe();
                c = b.arrayBaseOffset(byte[].class);
                if (b.arrayIndexScale(byte[].class) != 1) {
                    throw new AssertionError();
                }
            }

            /* JADX WARNING: Code restructure failed: missing block: B:5:0x0010, code lost:
                return (sun.misc.Unsafe) java.security.AccessController.doPrivileged(new com.google.common.primitives.UnsignedBytes.LexicographicalComparatorHolder.UnsafeComparator.AnonymousClass1());
             */
            /* JADX WARNING: Code restructure failed: missing block: B:6:0x0011, code lost:
                r0 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
                throw new java.lang.RuntimeException("Could not initialize intrinsics", r0.getCause());
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0005 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            private static sun.misc.Unsafe getUnsafe() {
                /*
                    sun.misc.Unsafe r0 = sun.misc.Unsafe.getUnsafe()     // Catch:{ SecurityException -> 0x0005 }
                    return r0
                L_0x0005:
                    com.google.common.primitives.UnsignedBytes$LexicographicalComparatorHolder$UnsafeComparator$1 r0 = new com.google.common.primitives.UnsignedBytes$LexicographicalComparatorHolder$UnsafeComparator$1     // Catch:{ PrivilegedActionException -> 0x0011 }
                    r0.<init>()     // Catch:{ PrivilegedActionException -> 0x0011 }
                    java.lang.Object r0 = java.security.AccessController.doPrivileged(r0)     // Catch:{ PrivilegedActionException -> 0x0011 }
                    sun.misc.Unsafe r0 = (sun.misc.Unsafe) r0     // Catch:{ PrivilegedActionException -> 0x0011 }
                    return r0
                L_0x0011:
                    r0 = move-exception
                    java.lang.RuntimeException r1 = new java.lang.RuntimeException
                    java.lang.Throwable r0 = r0.getCause()
                    java.lang.String r2 = "Could not initialize intrinsics"
                    r1.<init>(r2, r0)
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.primitives.UnsignedBytes.LexicographicalComparatorHolder.UnsafeComparator.getUnsafe():sun.misc.Unsafe");
            }

            public int compare(byte[] bArr, byte[] bArr2) {
                int min = Math.min(bArr.length, bArr2.length);
                int i = min / 8;
                int i2 = 0;
                while (true) {
                    int i3 = i * 8;
                    if (i2 < i3) {
                        long j = (long) i2;
                        long j2 = b.getLong(bArr, ((long) c) + j);
                        long j3 = b.getLong(bArr2, ((long) c) + j);
                        if (j2 == j3) {
                            i2 += 8;
                        } else if (a) {
                            return UnsignedLongs.compare(j2, j3);
                        } else {
                            int numberOfTrailingZeros = Long.numberOfTrailingZeros(j2 ^ j3) & -8;
                            return ((int) ((j2 >>> numberOfTrailingZeros) & 255)) - ((int) ((j3 >>> numberOfTrailingZeros) & 255));
                        }
                    } else {
                        while (i3 < min) {
                            int compare = UnsignedBytes.compare(bArr[i3], bArr2[i3]);
                            if (compare != 0) {
                                return compare;
                            }
                            i3++;
                        }
                        return bArr.length - bArr2.length;
                    }
                }
            }

            public String toString() {
                return "UnsignedBytes.lexicographicalComparator() (sun.misc.Unsafe version)";
            }
        }

        LexicographicalComparatorHolder() {
        }

        static Comparator<byte[]> a() {
            try {
                return (Comparator) Class.forName(a).getEnumConstants()[0];
            } catch (Throwable unused) {
                return UnsignedBytes.a();
            }
        }
    }

    private UnsignedBytes() {
    }

    @VisibleForTesting
    static Comparator<byte[]> a() {
        return LexicographicalComparatorHolder.PureJavaComparator.INSTANCE;
    }

    @CanIgnoreReturnValue
    public static byte checkedCast(long j) {
        if ((j >> 8) == 0) {
            return (byte) ((int) j);
        }
        throw new IllegalArgumentException("Out of range: " + j);
    }

    public static int compare(byte b, byte b2) {
        return toInt(b) - toInt(b2);
    }

    public static String join(String str, byte... bArr) {
        Preconditions.checkNotNull(str);
        if (bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(bArr.length * (str.length() + 3));
        sb.append(toInt(bArr[0]));
        for (int i = 1; i < bArr.length; i++) {
            sb.append(str);
            sb.append(toString(bArr[i]));
        }
        return sb.toString();
    }

    public static Comparator<byte[]> lexicographicalComparator() {
        return LexicographicalComparatorHolder.b;
    }

    public static byte max(byte... bArr) {
        Preconditions.checkArgument(bArr.length > 0);
        int i = toInt(bArr[0]);
        for (int i2 = 1; i2 < bArr.length; i2++) {
            int i3 = toInt(bArr[i2]);
            if (i3 > i) {
                i = i3;
            }
        }
        return (byte) i;
    }

    public static byte min(byte... bArr) {
        Preconditions.checkArgument(bArr.length > 0);
        int i = toInt(bArr[0]);
        for (int i2 = 1; i2 < bArr.length; i2++) {
            int i3 = toInt(bArr[i2]);
            if (i3 < i) {
                i = i3;
            }
        }
        return (byte) i;
    }

    @CanIgnoreReturnValue
    @Beta
    public static byte parseUnsignedByte(String str) {
        return parseUnsignedByte(str, 10);
    }

    @CanIgnoreReturnValue
    @Beta
    public static byte parseUnsignedByte(String str, int i) {
        int parseInt = Integer.parseInt((String) Preconditions.checkNotNull(str), i);
        if ((parseInt >> 8) == 0) {
            return (byte) parseInt;
        }
        throw new NumberFormatException("out of range: " + parseInt);
    }

    public static byte saturatedCast(long j) {
        if (j > ((long) toInt((byte) -1))) {
            return -1;
        }
        if (j < 0) {
            return 0;
        }
        return (byte) ((int) j);
    }

    public static int toInt(byte b) {
        return b & 255;
    }

    @Beta
    public static String toString(byte b) {
        return toString(b, 10);
    }

    @Beta
    public static String toString(byte b, int i) {
        Preconditions.checkArgument(i >= 2 && i <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", i);
        return Integer.toString(toInt(b), i);
    }
}

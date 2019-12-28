package com.razorpay;

public final class R$$r_ {
    public static void G__G_(int i, int i2, boolean z, int i3, int[] iArr, int[][] iArr2, int[] iArr3) {
        int[] iArr4 = iArr;
        if (!z) {
            d__1_(iArr);
        }
        int i4 = i;
        int i5 = i2;
        int i6 = 0;
        while (i6 < i3) {
            int i7 = i4 ^ iArr4[i6];
            int i8 = iArr2[0][i7 >>> 24];
            int[] iArr5 = iArr2[1];
            i6++;
            int i9 = i5 ^ ((iArr2[2][(i7 >>> 8) & 255] ^ (i8 + iArr5[(i7 >>> 16) & 255])) + iArr2[3][i7 & 255]);
            i5 = i7;
            i4 = i9;
        }
        int i10 = iArr4[iArr4.length - 2] ^ i4;
        int i11 = iArr4[iArr4.length - 1] ^ i5;
        if (!z) {
            d__1_(iArr);
        }
        iArr3[0] = i11;
        iArr3[1] = i10;
    }

    private static void d__1_(int[] iArr) {
        for (int i = 0; i < iArr.length / 2; i++) {
            int i2 = iArr[i];
            iArr[i] = iArr[(iArr.length - i) - 1];
            iArr[(iArr.length - i) - 1] = i2;
        }
    }
}

package com.razorpay;

public final class E$_j$ {
    private static void G__G_(int[] iArr) {
        for (int i = 0; i < iArr.length / 2; i++) {
            int i2 = iArr[i];
            iArr[i] = iArr[(iArr.length - i) - 1];
            iArr[(iArr.length - i) - 1] = i2;
        }
    }

    public static int[] Q_$2$(char[] cArr, int[] iArr, boolean z) {
        int i = (cArr[0] << 16) + cArr[1];
        int i2 = (cArr[2] << 16) + cArr[3];
        if (!z) {
            G__G_(iArr);
        }
        int i3 = i2;
        int i4 = i;
        int i5 = 0;
        while (i5 < 16) {
            int i6 = i4 ^ iArr[i5];
            i5++;
            int a_$P$ = i3 ^ a_$P$(i6);
            i3 = i6;
            i4 = a_$P$;
        }
        int i7 = iArr[16] ^ i4;
        int i8 = iArr[17] ^ i3;
        int[] iArr2 = {i8, i7};
        cArr[0] = (char) (i8 >>> 16);
        cArr[1] = (char) i8;
        cArr[2] = (char) (i7 >>> 16);
        cArr[3] = (char) i7;
        if (!z) {
            G__G_(iArr);
        }
        return iArr2;
    }

    private static int a_$P$(int i) {
        Q_$2$ q_$2$ = Q_$2$.G__G_;
        return ((q_$2$.d__1_[0][i >>> 24] + q_$2$.d__1_[1][(i >>> 16) & 255]) ^ q_$2$.d__1_[2][(i >>> 8) & 255]) + q_$2$.d__1_[3][i & 255];
    }
}

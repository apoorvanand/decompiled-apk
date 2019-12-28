package com.razorpay;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.util.Arrays;

public final class D$_X_ extends FilterInputStream {
    private static final short G__G_ = ((short) ((int) ((Math.sqrt(5.0d) - 1.0d) * Math.pow(2.0d, 15.0d))));
    private int B$$W$;
    private int D$_X_;
    private int E$_6$;
    private int E$_j$;
    private int[] Q_$2$ = new int[8];
    private int[] R$$r_ = new int[8];
    private int Y$_o$;
    private int a_$P$ = 8;
    private int b__J_;
    private int[] d__1_ = new int[8];
    private int r$_Y_;

    public D$_X_(InputStream inputStream, int[] iArr, int i, byte[] bArr, int i2, int i3) {
        super(inputStream);
        long j;
        this.E$_j$ = Math.min(Math.max(i2, 5), 16);
        this.r$_Y_ = i3;
        if (i3 == 3) {
            this.R$$r_ = d__1_(bArr);
        }
        long j2 = ((((long) iArr[0]) & 4294967295L) << 32) | (4294967295L & ((long) iArr[1]));
        if (i == 0) {
            this.b__J_ = (int) j2;
            long j3 = j2 >> 3;
            short s = G__G_;
            this.B$$W$ = (int) ((((long) s) * j3) >> 32);
            this.Y$_o$ = (int) (j2 >> 32);
            j = j3 + ((long) s);
        } else {
            int i4 = (int) j2;
            this.b__J_ = i4;
            this.B$$W$ = i4 * i;
            this.Y$_o$ = i4 ^ i;
            j = j2 >> 32;
        }
        this.E$_6$ = (int) j;
        this.D$_X_ = this.in.read();
    }

    private static int[] d__1_(byte[] bArr) {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = bArr[i] & 255;
        }
        return iArr;
    }

    public final int available() {
        return 8 - this.a_$P$;
    }

    public final boolean markSupported() {
        return false;
    }

    public final int read() {
        if (this.a_$P$ == 8) {
            int i = this.D$_X_;
            if (i == -1) {
                Arrays.fill(this.Q_$2$, -1);
            } else {
                this.Q_$2$[0] = i;
                for (int i2 = 1; i2 < 8; i2++) {
                    this.Q_$2$[i2] = this.in.read();
                }
                if (this.r$_Y_ == 3) {
                    int[] iArr = this.Q_$2$;
                    System.arraycopy(iArr, 0, this.d__1_, 0, iArr.length);
                }
                int[] iArr2 = this.Q_$2$;
                int i3 = ((iArr2[0] << 24) & ViewCompat.MEASURED_STATE_MASK) + ((iArr2[1] << 16) & 16711680) + ((iArr2[2] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (iArr2[3] & 255);
                int i4 = (-16777216 & (iArr2[4] << 24)) + (16711680 & (iArr2[5] << 16)) + ((iArr2[6] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (iArr2[7] & 255);
                int i5 = 0;
                while (true) {
                    int i6 = this.E$_j$;
                    if (i5 >= i6) {
                        break;
                    }
                    short s = G__G_;
                    i4 -= ((((i6 - i5) * s) + i3) ^ ((i3 << 4) + this.Y$_o$)) ^ ((i3 >>> 5) + this.E$_6$);
                    i3 -= (((i4 << 4) + this.b__J_) ^ ((s * (i6 - i5)) + i4)) ^ ((i4 >>> 5) + this.B$$W$);
                    i5++;
                }
                int[] iArr3 = this.Q_$2$;
                iArr3[0] = i3 >>> 24;
                iArr3[1] = (i3 >> 16) & 255;
                iArr3[2] = (i3 >> 8) & 255;
                iArr3[3] = i3 & 255;
                iArr3[4] = i4 >>> 24;
                iArr3[5] = (i4 >> 16) & 255;
                iArr3[6] = (i4 >> 8) & 255;
                iArr3[7] = i4 & 255;
                if (this.r$_Y_ == 3) {
                    for (int i7 = 0; i7 < 8; i7++) {
                        int[] iArr4 = this.Q_$2$;
                        iArr4[i7] = (iArr4[i7] ^ this.R$$r_[i7]) & 255;
                    }
                    int[] iArr5 = this.d__1_;
                    System.arraycopy(iArr5, 0, this.R$$r_, 0, iArr5.length);
                }
                this.D$_X_ = this.in.read();
                if (this.D$_X_ == -1) {
                    int[] iArr6 = this.Q_$2$;
                    Arrays.fill(iArr6, 8 - iArr6[7], 8, -1);
                }
            }
            this.a_$P$ = 0;
        }
        int[] iArr7 = this.Q_$2$;
        int i8 = this.a_$P$;
        this.a_$P$ = i8 + 1;
        return iArr7[i8];
    }

    public final int read(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        int read = read();
        if (read == -1) {
            return -1;
        }
        bArr[i] = (byte) read;
        int i3 = 1;
        while (i3 < i2) {
            int read2 = read();
            if (read2 == -1) {
                return i3;
            }
            bArr[i + i3] = (byte) read2;
            i3++;
        }
        return i3;
    }

    public final long skip(long j) {
        long j2 = 0;
        while (j2 < j && read() != -1) {
            j2++;
        }
        return j2;
    }
}

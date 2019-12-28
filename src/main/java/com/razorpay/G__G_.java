package com.razorpay;

import com.google.common.base.Ascii;
import java.io.FilterInputStream;
import java.io.InputStream;

public final class G__G_ extends FilterInputStream {
    private static final int[] G__G_ = a_$P$.G__G_;
    private static final int[] Q_$2$ = a_$P$.a_$P$;
    private static final int[] R$$r_ = a_$P$.Q_$2$;
    private static final byte[] a_$P$ = a_$P$.d__1_;
    private static final int[] d__1_ = a_$P$.R$$r_;
    private final int[] B$$W$ = new int[4];
    private final int[] D$_X_;
    private int E$_6$ = Integer.MAX_VALUE;
    private final byte[] E$_j$ = new byte[16];
    private int Y$_o$ = 16;
    private final int b__J_;
    private final byte[] c__C_ = new byte[16];
    private int l_$w$ = 16;
    private final byte[][] r$_Y_;

    public G__G_(InputStream inputStream, int i, byte[] bArr, byte[][] bArr2) {
        super(inputStream);
        this.b__J_ = i;
        this.D$_X_ = a_$P$.a_$P$(bArr, i);
        this.r$_Y_ = R$$r_(bArr2);
    }

    private void R$$r_(byte[] bArr, byte[] bArr2) {
        int[] iArr = this.B$$W$;
        char c = 1;
        byte b = (bArr[0] << Ascii.CAN) | ((bArr[1] & 255) << Ascii.DLE) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        int[] iArr2 = this.D$_X_;
        iArr[0] = b ^ iArr2[0];
        iArr[1] = ((((bArr[4] << Ascii.CAN) | ((bArr[5] & 255) << Ascii.DLE)) | ((bArr[6] & 255) << 8)) | (bArr[7] & 255)) ^ iArr2[1];
        iArr[2] = ((((bArr[8] << Ascii.CAN) | ((bArr[9] & 255) << Ascii.DLE)) | ((bArr[10] & 255) << 8)) | (bArr[11] & 255)) ^ iArr2[2];
        iArr[3] = iArr2[3] ^ ((((bArr[12] << Ascii.CAN) | ((bArr[13] & 255) << Ascii.DLE)) | ((bArr[14] & 255) << 8)) | (bArr[15] & 255));
        int i = 1;
        int i2 = 4;
        while (i < this.b__J_) {
            int[] iArr3 = R$$r_;
            int[] iArr4 = this.B$$W$;
            byte[][] bArr3 = this.r$_Y_;
            int i3 = iArr3[iArr4[bArr3[0][0]] >>> 24];
            int[] iArr5 = Q_$2$;
            int i4 = i3 ^ iArr5[(iArr4[bArr3[c][0]] >>> 16) & 255];
            int[] iArr6 = G__G_;
            int i5 = iArr6[(iArr4[bArr3[2][0]] >>> 8) & 255] ^ i4;
            int[] iArr7 = d__1_;
            int i6 = iArr7[iArr4[bArr3[3][0]] & 255] ^ i5;
            int[] iArr8 = this.D$_X_;
            int i7 = i6 ^ iArr8[i2];
            int i8 = ((iArr6[(iArr4[bArr3[2][c]] >>> 8) & 255] ^ (iArr3[iArr4[bArr3[0][c]] >>> 24] ^ iArr5[(iArr4[bArr3[c][c]] >>> 16) & 255])) ^ iArr7[iArr4[bArr3[3][c]] & 255]) ^ iArr8[i2 + 1];
            int i9 = (((iArr5[(iArr4[bArr3[c][2]] >>> 16) & 255] ^ iArr3[iArr4[bArr3[0][2]] >>> 24]) ^ iArr6[(iArr4[bArr3[2][2]] >>> 8) & 255]) ^ iArr7[iArr4[bArr3[3][2]] & 255]) ^ iArr8[i2 + 2];
            iArr4[0] = i7;
            iArr4[1] = i8;
            iArr4[2] = i9;
            iArr4[3] = (((iArr3[iArr4[bArr3[0][3]] >>> 24] ^ iArr5[(iArr4[bArr3[1][3]] >>> 16) & 255]) ^ iArr6[(iArr4[bArr3[2][3]] >>> 8) & 255]) ^ iArr7[iArr4[bArr3[3][3]] & 255]) ^ iArr8[i2 + 3];
            i++;
            i2 += 4;
            c = 1;
        }
        int[] iArr9 = this.D$_X_;
        int i10 = iArr9[i2];
        byte[] bArr4 = a_$P$;
        int[] iArr10 = this.B$$W$;
        byte[][] bArr5 = this.r$_Y_;
        bArr2[0] = (byte) (bArr4[iArr10[bArr5[0][0]] >>> 24] ^ (i10 >>> 24));
        bArr2[1] = (byte) (bArr4[(iArr10[bArr5[1][0]] >>> 16) & 255] ^ (i10 >>> 16));
        bArr2[2] = (byte) (bArr4[(iArr10[bArr5[2][0]] >>> 8) & 255] ^ (i10 >>> 8));
        bArr2[3] = (byte) (i10 ^ bArr4[iArr10[bArr5[3][0]] & 255]);
        int i11 = iArr9[i2 + 1];
        bArr2[4] = (byte) (bArr4[iArr10[bArr5[0][1]] >>> 24] ^ (i11 >>> 24));
        bArr2[5] = (byte) (bArr4[(iArr10[bArr5[1][1]] >>> 16) & 255] ^ (i11 >>> 16));
        bArr2[6] = (byte) (bArr4[(iArr10[bArr5[2][1]] >>> 8) & 255] ^ (i11 >>> 8));
        bArr2[7] = (byte) (i11 ^ bArr4[iArr10[bArr5[3][1]] & 255]);
        int i12 = iArr9[i2 + 2];
        bArr2[8] = (byte) (bArr4[iArr10[bArr5[0][2]] >>> 24] ^ (i12 >>> 24));
        bArr2[9] = (byte) (bArr4[(iArr10[bArr5[1][2]] >>> 16) & 255] ^ (i12 >>> 16));
        bArr2[10] = (byte) (bArr4[(iArr10[bArr5[2][2]] >>> 8) & 255] ^ (i12 >>> 8));
        bArr2[11] = (byte) (i12 ^ bArr4[iArr10[bArr5[3][2]] & 255]);
        int i13 = iArr9[i2 + 3];
        bArr2[12] = (byte) (bArr4[iArr10[bArr5[0][3]] >>> 24] ^ (i13 >>> 24));
        bArr2[13] = (byte) (bArr4[(iArr10[bArr5[1][3]] >>> 16) & 255] ^ (i13 >>> 16));
        bArr2[14] = (byte) (bArr4[(iArr10[bArr5[2][3]] >>> 8) & 255] ^ (i13 >>> 8));
        bArr2[15] = (byte) (i13 ^ bArr4[iArr10[bArr5[3][3]] & 255]);
    }

    private static byte[][] R$$r_(byte[][] bArr) {
        byte[][] bArr2 = new byte[bArr.length][];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = new byte[bArr[i].length];
            for (int i2 = 0; i2 < bArr[i].length; i2++) {
                bArr2[i][bArr[i][i2]] = (byte) i2;
            }
        }
        return bArr2;
    }

    private int a_$P$() {
        if (this.E$_6$ == Integer.MAX_VALUE) {
            this.E$_6$ = this.in.read();
        }
        int i = 16;
        if (this.Y$_o$ == 16) {
            this.E$_j$[0] = (byte) this.E$_6$;
            int i2 = 1;
            do {
                i2 += this.in.read(this.E$_j$, i2, 16 - i2);
            } while (i2 < 16);
            R$$r_(this.E$_j$, this.c__C_);
            this.E$_6$ = this.in.read();
            this.Y$_o$ = 0;
            if (this.E$_6$ < 0) {
                i = 16 - (this.c__C_[15] & 255);
            }
            this.l_$w$ = i;
        }
        return this.l_$w$;
    }

    public final int available() {
        return this.l_$w$ - this.Y$_o$;
    }

    public final void close() {
        super.close();
    }

    public final synchronized void mark(int i) {
    }

    public final boolean markSupported() {
        return false;
    }

    public final int read() {
        a_$P$();
        int i = this.Y$_o$;
        if (i >= this.l_$w$) {
            return -1;
        }
        byte[] bArr = this.c__C_;
        this.Y$_o$ = i + 1;
        return bArr[i] & 255;
    }

    public final int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public final int read(byte[] bArr, int i, int i2) {
        int i3 = i + i2;
        int i4 = i;
        while (i4 < i3) {
            a_$P$();
            int i5 = this.Y$_o$;
            if (i5 < this.l_$w$) {
                byte[] bArr2 = this.c__C_;
                this.Y$_o$ = i5 + 1;
                bArr[i4] = bArr2[i5];
                i4++;
            } else if (i4 == i) {
                return -1;
            } else {
                return i2 - (i3 - i4);
            }
        }
        return i2;
    }

    public final synchronized void reset() {
    }

    public final long skip(long j) {
        long j2 = 0;
        while (j2 < j && read() != -1) {
            j2++;
        }
        return j2;
    }
}

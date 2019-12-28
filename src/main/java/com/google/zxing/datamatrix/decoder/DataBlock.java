package com.google.zxing.datamatrix.decoder;

import com.google.zxing.datamatrix.decoder.Version;

final class DataBlock {
    private final byte[] codewords;
    private final int numDataCodewords;

    private DataBlock(int i, byte[] bArr) {
        this.numDataCodewords = i;
        this.codewords = bArr;
    }

    static DataBlock[] a(byte[] bArr, Version version) {
        Version.ECBlocks a = version.a();
        Version.ECB[] b = a.b();
        int i = 0;
        for (Version.ECB a2 : b) {
            i += a2.a();
        }
        DataBlock[] dataBlockArr = new DataBlock[i];
        int length = b.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            Version.ECB ecb = b[i2];
            int i4 = i3;
            int i5 = 0;
            while (i5 < ecb.a()) {
                int b2 = ecb.b();
                dataBlockArr[i4] = new DataBlock(b2, new byte[(a.a() + b2)]);
                i5++;
                i4++;
            }
            i2++;
            i3 = i4;
        }
        int length2 = dataBlockArr[0].codewords.length - a.a();
        int i6 = length2 - 1;
        int i7 = 0;
        int i8 = 0;
        while (i7 < i6) {
            int i9 = i8;
            int i10 = 0;
            while (i10 < i3) {
                dataBlockArr[i10].codewords[i7] = bArr[i9];
                i10++;
                i9++;
            }
            i7++;
            i8 = i9;
        }
        boolean z = version.getVersionNumber() == 24;
        int i11 = z ? 8 : i3;
        int i12 = i8;
        int i13 = 0;
        while (i13 < i11) {
            dataBlockArr[i13].codewords[i6] = bArr[i12];
            i13++;
            i12++;
        }
        int length3 = dataBlockArr[0].codewords.length;
        while (length2 < length3) {
            int i14 = 0;
            while (i14 < i3) {
                int i15 = z ? (i14 + 8) % i3 : i14;
                dataBlockArr[i15].codewords[(!z || i15 <= 7) ? length2 : length2 - 1] = bArr[i12];
                i14++;
                i12++;
            }
            length2++;
        }
        if (i12 == bArr.length) {
            return dataBlockArr;
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.numDataCodewords;
    }

    /* access modifiers changed from: package-private */
    public byte[] b() {
        return this.codewords;
    }
}

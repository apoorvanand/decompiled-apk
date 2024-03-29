package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class Code39Writer extends OneDimensionalCodeWriter {
    private static void toIntArray(int i, int[] iArr) {
        for (int i2 = 0; i2 < 9; i2++) {
            int i3 = 1;
            if (((1 << (8 - i2)) & i) != 0) {
                i3 = 2;
            }
            iArr[i2] = i3;
        }
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) {
        if (barcodeFormat == BarcodeFormat.CODE_39) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode CODE_39, but got " + barcodeFormat);
    }

    public boolean[] encode(String str) {
        int length = str.length();
        if (length <= 80) {
            int[] iArr = new int[9];
            int i = length + 25;
            int i2 = 0;
            while (i2 < length) {
                int indexOf = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(str.charAt(i2));
                if (indexOf >= 0) {
                    toIntArray(Code39Reader.a[indexOf], iArr);
                    int i3 = i;
                    for (int i4 = 0; i4 < 9; i4++) {
                        i3 += iArr[i4];
                    }
                    i2++;
                    i = i3;
                } else {
                    throw new IllegalArgumentException("Bad contents: " + str);
                }
            }
            boolean[] zArr = new boolean[i];
            toIntArray(Code39Reader.b, iArr);
            int b = b(zArr, 0, iArr, true);
            int[] iArr2 = {1};
            int b2 = b + b(zArr, b, iArr2, false);
            for (int i5 = 0; i5 < length; i5++) {
                toIntArray(Code39Reader.a["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(str.charAt(i5))], iArr);
                int b3 = b2 + b(zArr, b2, iArr, true);
                b2 = b3 + b(zArr, b3, iArr2, false);
            }
            toIntArray(Code39Reader.b, iArr);
            b(zArr, b2, iArr, true);
            return zArr;
        }
        throw new IllegalArgumentException("Requested contents should be less than 80 digits long, but got " + length);
    }
}

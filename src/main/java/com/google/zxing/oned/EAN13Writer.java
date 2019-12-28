package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import java.util.Map;

public final class EAN13Writer extends UPCEANWriter {
    private static final int CODE_WIDTH = 95;

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) {
        if (barcodeFormat == BarcodeFormat.EAN_13) {
            return super.encode(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode EAN_13, but got " + barcodeFormat);
    }

    public boolean[] encode(String str) {
        if (str.length() == 13) {
            try {
                if (UPCEANReader.a((CharSequence) str)) {
                    int i = EAN13Reader.a[Integer.parseInt(str.substring(0, 1))];
                    boolean[] zArr = new boolean[95];
                    int b = b(zArr, 0, UPCEANReader.b, true) + 0;
                    int i2 = 1;
                    while (i2 <= 6) {
                        int i3 = i2 + 1;
                        int parseInt = Integer.parseInt(str.substring(i2, i3));
                        if (((i >> (6 - i2)) & 1) == 1) {
                            parseInt += 10;
                        }
                        b += b(zArr, b, UPCEANReader.f[parseInt], false);
                        i2 = i3;
                    }
                    int b2 = b + b(zArr, b, UPCEANReader.c, false);
                    int i4 = 7;
                    while (i4 <= 12) {
                        int i5 = i4 + 1;
                        b2 += b(zArr, b2, UPCEANReader.e[Integer.parseInt(str.substring(i4, i5))], true);
                        i4 = i5;
                    }
                    b(zArr, b2, UPCEANReader.b, true);
                    return zArr;
                }
                throw new IllegalArgumentException("Contents do not pass checksum");
            } catch (FormatException unused) {
                throw new IllegalArgumentException("Illegal contents");
            }
        } else {
            throw new IllegalArgumentException("Requested contents should be 13 digits long, but got " + str.length());
        }
    }
}

package com.google.zxing.datamatrix.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;

public final class Decoder {
    private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.DATA_MATRIX_FIELD_256);

    private void correctErrors(byte[] bArr, int i) {
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        try {
            this.rsDecoder.decode(iArr, bArr.length - i);
            for (int i3 = 0; i3 < i; i3++) {
                bArr[i3] = (byte) iArr[i3];
            }
        } catch (ReedSolomonException unused) {
            throw ChecksumException.getChecksumInstance();
        }
    }

    public DecoderResult decode(BitMatrix bitMatrix) {
        BitMatrixParser bitMatrixParser = new BitMatrixParser(bitMatrix);
        DataBlock[] a = DataBlock.a(bitMatrixParser.b(), bitMatrixParser.a());
        int i = 0;
        for (DataBlock a2 : a) {
            i += a2.a();
        }
        byte[] bArr = new byte[i];
        int length = a.length;
        for (int i2 = 0; i2 < length; i2++) {
            DataBlock dataBlock = a[i2];
            byte[] b = dataBlock.b();
            int a3 = dataBlock.a();
            correctErrors(b, a3);
            for (int i3 = 0; i3 < a3; i3++) {
                bArr[(i3 * length) + i2] = b[i3];
            }
        }
        return DecodedBitStreamParser.a(bArr);
    }

    public DecoderResult decode(boolean[][] zArr) {
        int length = zArr.length;
        BitMatrix bitMatrix = new BitMatrix(length);
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length; i2++) {
                if (zArr[i][i2]) {
                    bitMatrix.set(i2, i);
                }
            }
        }
        return decode(bitMatrix);
    }
}

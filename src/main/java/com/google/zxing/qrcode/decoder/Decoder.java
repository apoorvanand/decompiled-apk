package com.google.zxing.qrcode.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import java.util.Map;

public final class Decoder {
    private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.QR_CODE_FIELD_256);

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

    private DecoderResult decode(BitMatrixParser bitMatrixParser, Map<DecodeHintType, ?> map) {
        Version b = bitMatrixParser.b();
        ErrorCorrectionLevel a = bitMatrixParser.a().a();
        DataBlock[] a2 = DataBlock.a(bitMatrixParser.c(), b, a);
        int i = 0;
        for (DataBlock a3 : a2) {
            i += a3.a();
        }
        byte[] bArr = new byte[i];
        int length = a2.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            DataBlock dataBlock = a2[i2];
            byte[] b2 = dataBlock.b();
            int a4 = dataBlock.a();
            correctErrors(b2, a4);
            int i4 = i3;
            int i5 = 0;
            while (i5 < a4) {
                bArr[i4] = b2[i5];
                i5++;
                i4++;
            }
            i2++;
            i3 = i4;
        }
        return DecodedBitStreamParser.a(bArr, b, a, map);
    }

    public DecoderResult decode(BitMatrix bitMatrix) {
        return decode(bitMatrix, (Map<DecodeHintType, ?>) null);
    }

    public DecoderResult decode(BitMatrix bitMatrix, Map<DecodeHintType, ?> map) {
        ChecksumException e;
        BitMatrixParser bitMatrixParser = new BitMatrixParser(bitMatrix);
        FormatException formatException = null;
        try {
            return decode(bitMatrixParser, map);
        } catch (FormatException e2) {
            FormatException formatException2 = e2;
            e = null;
            formatException = formatException2;
            try {
                bitMatrixParser.d();
                bitMatrixParser.a(true);
                bitMatrixParser.b();
                bitMatrixParser.a();
                bitMatrixParser.e();
                DecoderResult decode = decode(bitMatrixParser, map);
                decode.setOther(new QRCodeDecoderMetaData(true));
                return decode;
            } catch (ChecksumException | FormatException e3) {
                if (formatException != null) {
                    throw formatException;
                } else if (e != null) {
                    throw e;
                } else {
                    throw e3;
                }
            }
        } catch (ChecksumException e4) {
            e = e4;
            bitMatrixParser.d();
            bitMatrixParser.a(true);
            bitMatrixParser.b();
            bitMatrixParser.a();
            bitMatrixParser.e();
            DecoderResult decode2 = decode(bitMatrixParser, map);
            decode2.setOther(new QRCodeDecoderMetaData(true));
            return decode2;
        }
    }

    public DecoderResult decode(boolean[][] zArr) {
        return decode(zArr, (Map<DecodeHintType, ?>) null);
    }

    public DecoderResult decode(boolean[][] zArr, Map<DecodeHintType, ?> map) {
        int length = zArr.length;
        BitMatrix bitMatrix = new BitMatrix(length);
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length; i2++) {
                if (zArr[i][i2]) {
                    bitMatrix.set(i2, i);
                }
            }
        }
        return decode(bitMatrix, map);
    }
}
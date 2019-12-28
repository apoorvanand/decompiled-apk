package com.google.zxing.pdf417.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417Common;
import com.google.zxing.pdf417.decoder.ec.ErrorCorrection;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Formatter;

public final class PDF417ScanningDecoder {
    private static final int CODEWORD_SKEW_SIZE = 2;
    private static final int MAX_EC_CODEWORDS = 512;
    private static final int MAX_ERRORS = 3;
    private static final ErrorCorrection errorCorrection = new ErrorCorrection();

    private PDF417ScanningDecoder() {
    }

    private static BoundingBox adjustBoundingBox(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn) {
        int[] c;
        if (detectionResultRowIndicatorColumn == null || (c = detectionResultRowIndicatorColumn.c()) == null) {
            return null;
        }
        int max = getMax(c);
        int i = 0;
        int i2 = 0;
        for (int i3 : c) {
            i2 += max - i3;
            if (i3 > 0) {
                break;
            }
        }
        Codeword[] b = detectionResultRowIndicatorColumn.b();
        int i4 = 0;
        while (i2 > 0 && b[i4] == null) {
            i2--;
            i4++;
        }
        for (int length = c.length - 1; length >= 0; length--) {
            i += max - c[length];
            if (c[length] > 0) {
                break;
            }
        }
        int length2 = b.length - 1;
        while (i > 0 && b[length2] == null) {
            i--;
            length2--;
        }
        return detectionResultRowIndicatorColumn.a().a(i2, i, detectionResultRowIndicatorColumn.e());
    }

    private static void adjustCodewordCount(DetectionResult detectionResult, BarcodeValue[][] barcodeValueArr) {
        int[] a = barcodeValueArr[0][1].a();
        int b = (detectionResult.b() * detectionResult.c()) - getNumberOfECCodeWords(detectionResult.d());
        if (a.length == 0) {
            if (b <= 0 || b > 928) {
                throw NotFoundException.getNotFoundInstance();
            }
            barcodeValueArr[0][1].a(b);
        } else if (a[0] != b) {
            barcodeValueArr[0][1].a(b);
        }
    }

    private static int adjustCodewordStartColumn(BitMatrix bitMatrix, int i, int i2, boolean z, int i3, int i4) {
        boolean z2 = z;
        int i5 = z ? -1 : 1;
        int i6 = i3;
        for (int i7 = 0; i7 < 2; i7++) {
            while (true) {
                if (!z2) {
                    if (i6 >= i2) {
                        break;
                    }
                } else if (i6 < i) {
                    break;
                }
                if (z2 != bitMatrix.get(i6, i4)) {
                    break;
                } else if (Math.abs(i3 - i6) > 2) {
                    return i3;
                } else {
                    i6 += i5;
                }
            }
            i5 = -i5;
            z2 = !z2;
        }
        return i6;
    }

    private static boolean checkCodewordSkew(int i, int i2, int i3) {
        return i2 + -2 <= i && i <= i3 + 2;
    }

    private static int correctErrors(int[] iArr, int[] iArr2, int i) {
        if ((iArr2 == null || iArr2.length <= (i / 2) + 3) && i >= 0 && i <= 512) {
            return errorCorrection.decode(iArr, i, iArr2);
        }
        throw ChecksumException.getChecksumInstance();
    }

    private static BarcodeValue[][] createBarcodeMatrix(DetectionResult detectionResult) {
        int h;
        BarcodeValue[][] barcodeValueArr = (BarcodeValue[][]) Array.newInstance(BarcodeValue.class, new int[]{detectionResult.c(), detectionResult.b() + 2});
        for (int i = 0; i < barcodeValueArr.length; i++) {
            for (int i2 = 0; i2 < barcodeValueArr[i].length; i2++) {
                barcodeValueArr[i][i2] = new BarcodeValue();
            }
        }
        int i3 = 0;
        for (DetectionResultColumn detectionResultColumn : detectionResult.a()) {
            if (detectionResultColumn != null) {
                for (Codeword codeword : detectionResultColumn.b()) {
                    if (codeword != null && (h = codeword.h()) >= 0 && h < barcodeValueArr.length) {
                        barcodeValueArr[h][i3].a(codeword.g());
                    }
                }
            }
            i3++;
        }
        return barcodeValueArr;
    }

    private static DecoderResult createDecoderResult(DetectionResult detectionResult) {
        BarcodeValue[][] createBarcodeMatrix = createBarcodeMatrix(detectionResult);
        adjustCodewordCount(detectionResult, createBarcodeMatrix);
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[(detectionResult.c() * detectionResult.b())];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < detectionResult.c(); i++) {
            int i2 = 0;
            while (i2 < detectionResult.b()) {
                int i3 = i2 + 1;
                int[] a = createBarcodeMatrix[i][i3].a();
                int b = (detectionResult.b() * i) + i2;
                if (a.length == 0) {
                    arrayList.add(Integer.valueOf(b));
                } else if (a.length == 1) {
                    iArr[b] = a[0];
                } else {
                    arrayList3.add(Integer.valueOf(b));
                    arrayList2.add(a);
                }
                i2 = i3;
            }
        }
        int[][] iArr2 = new int[arrayList2.size()][];
        for (int i4 = 0; i4 < iArr2.length; i4++) {
            iArr2[i4] = (int[]) arrayList2.get(i4);
        }
        return createDecoderResultFromAmbiguousValues(detectionResult.d(), iArr, PDF417Common.toIntArray(arrayList), PDF417Common.toIntArray(arrayList3), iArr2);
    }

    private static DecoderResult createDecoderResultFromAmbiguousValues(int i, int[] iArr, int[] iArr2, int[] iArr3, int[][] iArr4) {
        int[] iArr5 = new int[iArr3.length];
        int i2 = 100;
        while (true) {
            int i3 = i2 - 1;
            if (i2 > 0) {
                for (int i4 = 0; i4 < iArr5.length; i4++) {
                    iArr[iArr3[i4]] = iArr4[i4][iArr5[i4]];
                }
                try {
                    return decodeCodewords(iArr, i, iArr2);
                } catch (ChecksumException unused) {
                    if (iArr5.length != 0) {
                        int i5 = 0;
                        while (true) {
                            if (i5 >= iArr5.length) {
                                break;
                            } else if (iArr5[i5] < iArr4[i5].length - 1) {
                                iArr5[i5] = iArr5[i5] + 1;
                                break;
                            } else {
                                iArr5[i5] = 0;
                                if (i5 != iArr5.length - 1) {
                                    i5++;
                                } else {
                                    throw ChecksumException.getChecksumInstance();
                                }
                            }
                        }
                        i2 = i3;
                    } else {
                        throw ChecksumException.getChecksumInstance();
                    }
                }
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
    }

    public static DecoderResult decode(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i, int i2) {
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn;
        DetectionResultColumn detectionResultColumn;
        int i3;
        int i4;
        int i5;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2 = null;
        DetectionResult detectionResult = null;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn3 = null;
        BoundingBox boundingBox = new BoundingBox(bitMatrix, resultPoint, resultPoint2, resultPoint3, resultPoint4);
        int i6 = 0;
        while (true) {
            if (i6 >= 2) {
                detectionResultRowIndicatorColumn = detectionResultRowIndicatorColumn2;
                break;
            }
            if (resultPoint != null) {
                detectionResultRowIndicatorColumn2 = getRowIndicatorColumn(bitMatrix, boundingBox, resultPoint, true, i, i2);
            }
            detectionResultRowIndicatorColumn = detectionResultRowIndicatorColumn2;
            if (resultPoint3 != null) {
                detectionResultRowIndicatorColumn3 = getRowIndicatorColumn(bitMatrix, boundingBox, resultPoint3, false, i, i2);
            }
            detectionResult = merge(detectionResultRowIndicatorColumn, detectionResultRowIndicatorColumn3);
            if (detectionResult == null) {
                throw NotFoundException.getNotFoundInstance();
            } else if (i6 != 0 || detectionResult.e() == null || (detectionResult.e().c() >= boundingBox.c() && detectionResult.e().d() <= boundingBox.d())) {
                detectionResult.setBoundingBox(boundingBox);
            } else {
                boundingBox = detectionResult.e();
                i6++;
                detectionResultRowIndicatorColumn2 = detectionResultRowIndicatorColumn;
            }
        }
        int b = detectionResult.b() + 1;
        detectionResult.a(0, detectionResultRowIndicatorColumn);
        detectionResult.a(b, detectionResultRowIndicatorColumn3);
        boolean z = detectionResultRowIndicatorColumn != null;
        int i7 = i;
        int i8 = i2;
        for (int i9 = 1; i9 <= b; i9++) {
            int i10 = z ? i9 : b - i9;
            if (detectionResult.a(i10) == null) {
                if (i10 == 0 || i10 == b) {
                    detectionResultColumn = new DetectionResultRowIndicatorColumn(boundingBox, i10 == 0);
                } else {
                    detectionResultColumn = new DetectionResultColumn(boundingBox);
                }
                detectionResult.a(i10, detectionResultColumn);
                int i11 = -1;
                int i12 = i8;
                int i13 = -1;
                int i14 = i7;
                int c = boundingBox.c();
                while (c <= boundingBox.d()) {
                    int startColumn = getStartColumn(detectionResult, i10, c, z);
                    if (startColumn >= 0 && startColumn <= boundingBox.b()) {
                        i5 = startColumn;
                    } else if (i13 != i11) {
                        i5 = i13;
                    } else {
                        i3 = i13;
                        i4 = i12;
                        i12 = i4;
                        i13 = i3;
                        c++;
                        i11 = -1;
                    }
                    i3 = i13;
                    int i15 = i12;
                    Codeword detectCodeword = detectCodeword(bitMatrix, boundingBox.a(), boundingBox.b(), z, i5, c, i14, i15);
                    if (detectCodeword != null) {
                        detectionResultColumn.a(c, detectCodeword);
                        i14 = Math.min(i14, detectCodeword.c());
                        i12 = Math.max(i15, detectCodeword.c());
                        i13 = i5;
                        c++;
                        i11 = -1;
                    } else {
                        i4 = i15;
                        i12 = i4;
                        i13 = i3;
                        c++;
                        i11 = -1;
                    }
                }
                i7 = i14;
                i8 = i12;
            }
        }
        return createDecoderResult(detectionResult);
    }

    private static DecoderResult decodeCodewords(int[] iArr, int i, int[] iArr2) {
        if (iArr.length != 0) {
            int i2 = 1 << (i + 1);
            int correctErrors = correctErrors(iArr, iArr2, i2);
            verifyCodewordCount(iArr, i2);
            DecoderResult a = DecodedBitStreamParser.a(iArr, String.valueOf(i));
            a.setErrorsCorrected(Integer.valueOf(correctErrors));
            a.setErasures(Integer.valueOf(iArr2.length));
            return a;
        }
        throw FormatException.getFormatInstance();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0040, code lost:
        r7 = com.google.zxing.pdf417.decoder.PDF417CodewordDecoder.a(r7);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.zxing.pdf417.decoder.Codeword detectCodeword(com.google.zxing.common.BitMatrix r7, int r8, int r9, boolean r10, int r11, int r12, int r13, int r14) {
        /*
            int r11 = adjustCodewordStartColumn(r7, r8, r9, r10, r11, r12)
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            int[] r7 = getModuleBitCount(r0, r1, r2, r3, r4, r5)
            r8 = 0
            if (r7 != 0) goto L_0x0012
            return r8
        L_0x0012:
            int r9 = com.google.zxing.common.detector.MathUtils.sum(r7)
            if (r10 == 0) goto L_0x001e
            int r10 = r11 + r9
            r6 = r11
            r11 = r10
            r10 = r6
            goto L_0x0039
        L_0x001e:
            r10 = 0
        L_0x001f:
            int r12 = r7.length
            int r12 = r12 / 2
            if (r10 >= r12) goto L_0x0037
            r12 = r7[r10]
            int r0 = r7.length
            int r0 = r0 + -1
            int r0 = r0 - r10
            r0 = r7[r0]
            r7[r10] = r0
            int r0 = r7.length
            int r0 = r0 + -1
            int r0 = r0 - r10
            r7[r0] = r12
            int r10 = r10 + 1
            goto L_0x001f
        L_0x0037:
            int r10 = r11 - r9
        L_0x0039:
            boolean r9 = checkCodewordSkew(r9, r13, r14)
            if (r9 != 0) goto L_0x0040
            return r8
        L_0x0040:
            int r7 = com.google.zxing.pdf417.decoder.PDF417CodewordDecoder.a(r7)
            int r9 = com.google.zxing.pdf417.PDF417Common.getCodeword(r7)
            r12 = -1
            if (r9 != r12) goto L_0x004c
            return r8
        L_0x004c:
            com.google.zxing.pdf417.decoder.Codeword r8 = new com.google.zxing.pdf417.decoder.Codeword
            int r7 = getCodewordBucketNumber((int) r7)
            r8.<init>(r10, r11, r7, r9)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.detectCodeword(com.google.zxing.common.BitMatrix, int, int, boolean, int, int, int, int):com.google.zxing.pdf417.decoder.Codeword");
    }

    private static BarcodeMetadata getBarcodeMetadata(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) {
        BarcodeMetadata d;
        BarcodeMetadata d2;
        if (detectionResultRowIndicatorColumn == null || (d = detectionResultRowIndicatorColumn.d()) == null) {
            if (detectionResultRowIndicatorColumn2 == null) {
                return null;
            }
            return detectionResultRowIndicatorColumn2.d();
        } else if (detectionResultRowIndicatorColumn2 == null || (d2 = detectionResultRowIndicatorColumn2.d()) == null || d.a() == d2.a() || d.b() == d2.b() || d.c() == d2.c()) {
            return d;
        } else {
            return null;
        }
    }

    private static int[] getBitCountForCodeword(int i) {
        int[] iArr = new int[8];
        int i2 = 0;
        int i3 = 7;
        while (true) {
            int i4 = i & 1;
            if (i4 != i2) {
                i3--;
                if (i3 < 0) {
                    return iArr;
                }
                i2 = i4;
            }
            iArr[i3] = iArr[i3] + 1;
            i >>= 1;
        }
    }

    private static int getCodewordBucketNumber(int i) {
        return getCodewordBucketNumber(getBitCountForCodeword(i));
    }

    private static int getCodewordBucketNumber(int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }

    private static int getMax(int[] iArr) {
        int i = -1;
        for (int max : iArr) {
            i = Math.max(i, max);
        }
        return i;
    }

    private static int[] getModuleBitCount(BitMatrix bitMatrix, int i, int i2, boolean z, int i3, int i4) {
        int[] iArr = new int[8];
        int i5 = z ? 1 : -1;
        boolean z2 = z;
        int i6 = 0;
        while (true) {
            if (!z) {
                if (i3 < i) {
                    break;
                }
            } else if (i3 >= i2) {
                break;
            }
            if (i6 >= 8) {
                break;
            } else if (bitMatrix.get(i3, i4) == z2) {
                iArr[i6] = iArr[i6] + 1;
                i3 += i5;
            } else {
                i6++;
                z2 = !z2;
            }
        }
        if (i6 != 8) {
            if (z) {
                i = i2;
            }
            if (!(i3 == i && i6 == 7)) {
                return null;
            }
        }
        return iArr;
    }

    private static int getNumberOfECCodeWords(int i) {
        return 2 << i;
    }

    private static DetectionResultRowIndicatorColumn getRowIndicatorColumn(BitMatrix bitMatrix, BoundingBox boundingBox, ResultPoint resultPoint, boolean z, int i, int i2) {
        boolean z2 = z;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn = new DetectionResultRowIndicatorColumn(boundingBox, z2);
        int i3 = 0;
        while (i3 < 2) {
            int i4 = i3 == 0 ? 1 : -1;
            int x = (int) resultPoint.getX();
            int y = (int) resultPoint.getY();
            while (y <= boundingBox.d() && y >= boundingBox.c()) {
                Codeword detectCodeword = detectCodeword(bitMatrix, 0, bitMatrix.getWidth(), z, x, y, i, i2);
                if (detectCodeword != null) {
                    detectionResultRowIndicatorColumn.a(y, detectCodeword);
                    x = z2 ? detectCodeword.d() : detectCodeword.e();
                }
                y += i4;
            }
            i3++;
        }
        return detectionResultRowIndicatorColumn;
    }

    private static int getStartColumn(DetectionResult detectionResult, int i, int i2, boolean z) {
        int i3 = z ? 1 : -1;
        Codeword codeword = null;
        int i4 = i - i3;
        if (isValidBarcodeColumn(detectionResult, i4)) {
            codeword = detectionResult.a(i4).c(i2);
        }
        if (codeword != null) {
            return z ? codeword.e() : codeword.d();
        }
        Codeword a = detectionResult.a(i).a(i2);
        if (a != null) {
            return z ? a.d() : a.e();
        }
        if (isValidBarcodeColumn(detectionResult, i4)) {
            a = detectionResult.a(i4).a(i2);
        }
        if (a != null) {
            return z ? a.e() : a.d();
        }
        int i5 = 0;
        while (true) {
            i -= i3;
            if (!isValidBarcodeColumn(detectionResult, i)) {
                return z ? detectionResult.e().a() : detectionResult.e().b();
            }
            for (Codeword codeword2 : detectionResult.a(i).b()) {
                if (codeword2 != null) {
                    return (z ? codeword2.e() : codeword2.d()) + (i3 * i5 * (codeword2.e() - codeword2.d()));
                }
            }
            i5++;
        }
    }

    private static boolean isValidBarcodeColumn(DetectionResult detectionResult, int i) {
        return i >= 0 && i <= detectionResult.b() + 1;
    }

    private static DetectionResult merge(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) {
        BarcodeMetadata barcodeMetadata;
        if ((detectionResultRowIndicatorColumn == null && detectionResultRowIndicatorColumn2 == null) || (barcodeMetadata = getBarcodeMetadata(detectionResultRowIndicatorColumn, detectionResultRowIndicatorColumn2)) == null) {
            return null;
        }
        return new DetectionResult(barcodeMetadata, BoundingBox.a(adjustBoundingBox(detectionResultRowIndicatorColumn), adjustBoundingBox(detectionResultRowIndicatorColumn2)));
    }

    public static String toString(BarcodeValue[][] barcodeValueArr) {
        Formatter formatter = new Formatter();
        for (int i = 0; i < barcodeValueArr.length; i++) {
            formatter.format("Row %2d: ", new Object[]{Integer.valueOf(i)});
            for (BarcodeValue barcodeValue : barcodeValueArr[i]) {
                if (barcodeValue.a().length == 0) {
                    formatter.format("        ", (Object[]) null);
                } else {
                    formatter.format("%4d(%2d)", new Object[]{Integer.valueOf(barcodeValue.a()[0]), barcodeValue.getConfidence(barcodeValue.a()[0])});
                }
            }
            formatter.format("%n", new Object[0]);
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }

    private static void verifyCodewordCount(int[] iArr, int i) {
        if (iArr.length >= 4) {
            int i2 = iArr[0];
            if (i2 > iArr.length) {
                throw FormatException.getFormatInstance();
            } else if (i2 != 0) {
            } else {
                if (i < iArr.length) {
                    iArr[0] = iArr.length - i;
                    return;
                }
                throw FormatException.getFormatInstance();
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }
}

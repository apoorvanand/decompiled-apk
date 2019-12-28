package com.google.zxing.pdf417.decoder;

import com.google.zxing.pdf417.PDF417Common;
import java.util.Formatter;

final class DetectionResult {
    private static final int ADJUST_ROW_NUMBER_SKIP = 2;
    private final int barcodeColumnCount;
    private final BarcodeMetadata barcodeMetadata;
    private BoundingBox boundingBox;
    private final DetectionResultColumn[] detectionResultColumns = new DetectionResultColumn[(this.barcodeColumnCount + 2)];

    DetectionResult(BarcodeMetadata barcodeMetadata2, BoundingBox boundingBox2) {
        this.barcodeMetadata = barcodeMetadata2;
        this.barcodeColumnCount = barcodeMetadata2.a();
        this.boundingBox = boundingBox2;
    }

    private void adjustIndicatorColumnRowNumbers(DetectionResultColumn detectionResultColumn) {
        if (detectionResultColumn != null) {
            ((DetectionResultRowIndicatorColumn) detectionResultColumn).a(this.barcodeMetadata);
        }
    }

    private static boolean adjustRowNumber(Codeword codeword, Codeword codeword2) {
        if (codeword2 == null || !codeword2.a() || codeword2.f() != codeword.f()) {
            return false;
        }
        codeword.b(codeword2.h());
        return true;
    }

    private static int adjustRowNumberIfValid(int i, int i2, Codeword codeword) {
        if (codeword == null || codeword.a()) {
            return i2;
        }
        if (!codeword.a(i)) {
            return i2 + 1;
        }
        codeword.b(i);
        return 0;
    }

    private int adjustRowNumbers() {
        int adjustRowNumbersByRow = adjustRowNumbersByRow();
        if (adjustRowNumbersByRow == 0) {
            return 0;
        }
        for (int i = 1; i < this.barcodeColumnCount + 1; i++) {
            Codeword[] b = this.detectionResultColumns[i].b();
            for (int i2 = 0; i2 < b.length; i2++) {
                if (b[i2] != null && !b[i2].a()) {
                    adjustRowNumbers(i, i2, b);
                }
            }
        }
        return adjustRowNumbersByRow;
    }

    private void adjustRowNumbers(int i, int i2, Codeword[] codewordArr) {
        Codeword codeword = codewordArr[i2];
        Codeword[] b = this.detectionResultColumns[i - 1].b();
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        int i3 = i + 1;
        Codeword[] b2 = detectionResultColumnArr[i3] != null ? detectionResultColumnArr[i3].b() : b;
        Codeword[] codewordArr2 = new Codeword[14];
        codewordArr2[2] = b[i2];
        codewordArr2[3] = b2[i2];
        int i4 = 0;
        if (i2 > 0) {
            int i5 = i2 - 1;
            codewordArr2[0] = codewordArr[i5];
            codewordArr2[4] = b[i5];
            codewordArr2[5] = b2[i5];
        }
        if (i2 > 1) {
            int i6 = i2 - 2;
            codewordArr2[8] = codewordArr[i6];
            codewordArr2[10] = b[i6];
            codewordArr2[11] = b2[i6];
        }
        if (i2 < codewordArr.length - 1) {
            int i7 = i2 + 1;
            codewordArr2[1] = codewordArr[i7];
            codewordArr2[6] = b[i7];
            codewordArr2[7] = b2[i7];
        }
        if (i2 < codewordArr.length - 2) {
            int i8 = i2 + 2;
            codewordArr2[9] = codewordArr[i8];
            codewordArr2[12] = b[i8];
            codewordArr2[13] = b2[i8];
        }
        while (i4 < 14 && !adjustRowNumber(codeword, codewordArr2[i4])) {
            i4++;
        }
    }

    private int adjustRowNumbersByRow() {
        adjustRowNumbersFromBothRI();
        return adjustRowNumbersFromLRI() + adjustRowNumbersFromRRI();
    }

    private void adjustRowNumbersFromBothRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        if (detectionResultColumnArr[0] != null && detectionResultColumnArr[this.barcodeColumnCount + 1] != null) {
            Codeword[] b = detectionResultColumnArr[0].b();
            Codeword[] b2 = this.detectionResultColumns[this.barcodeColumnCount + 1].b();
            for (int i = 0; i < b.length; i++) {
                if (!(b[i] == null || b2[i] == null || b[i].h() != b2[i].h())) {
                    for (int i2 = 1; i2 <= this.barcodeColumnCount; i2++) {
                        Codeword codeword = this.detectionResultColumns[i2].b()[i];
                        if (codeword != null) {
                            codeword.b(b[i].h());
                            if (!codeword.a()) {
                                this.detectionResultColumns[i2].b()[i] = null;
                            }
                        }
                    }
                }
            }
        }
    }

    private int adjustRowNumbersFromLRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        if (detectionResultColumnArr[0] == null) {
            return 0;
        }
        Codeword[] b = detectionResultColumnArr[0].b();
        int i = 0;
        for (int i2 = 0; i2 < b.length; i2++) {
            if (b[i2] != null) {
                int h = b[i2].h();
                int i3 = i;
                int i4 = 0;
                for (int i5 = 1; i5 < this.barcodeColumnCount + 1 && i4 < 2; i5++) {
                    Codeword codeword = this.detectionResultColumns[i5].b()[i2];
                    if (codeword != null) {
                        i4 = adjustRowNumberIfValid(h, i4, codeword);
                        if (!codeword.a()) {
                            i3++;
                        }
                    }
                }
                i = i3;
            }
        }
        return i;
    }

    private int adjustRowNumbersFromRRI() {
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        int i = this.barcodeColumnCount;
        if (detectionResultColumnArr[i + 1] == null) {
            return 0;
        }
        Codeword[] b = detectionResultColumnArr[i + 1].b();
        int i2 = 0;
        for (int i3 = 0; i3 < b.length; i3++) {
            if (b[i3] != null) {
                int h = b[i3].h();
                int i4 = i2;
                int i5 = 0;
                for (int i6 = this.barcodeColumnCount + 1; i6 > 0 && i5 < 2; i6--) {
                    Codeword codeword = this.detectionResultColumns[i6].b()[i3];
                    if (codeword != null) {
                        i5 = adjustRowNumberIfValid(h, i5, codeword);
                        if (!codeword.a()) {
                            i4++;
                        }
                    }
                }
                i2 = i4;
            }
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public DetectionResultColumn a(int i) {
        return this.detectionResultColumns[i];
    }

    /* access modifiers changed from: package-private */
    public void a(int i, DetectionResultColumn detectionResultColumn) {
        this.detectionResultColumns[i] = detectionResultColumn;
    }

    /* access modifiers changed from: package-private */
    public DetectionResultColumn[] a() {
        adjustIndicatorColumnRowNumbers(this.detectionResultColumns[0]);
        adjustIndicatorColumnRowNumbers(this.detectionResultColumns[this.barcodeColumnCount + 1]);
        int i = PDF417Common.MAX_CODEWORDS_IN_BARCODE;
        while (true) {
            int adjustRowNumbers = adjustRowNumbers();
            if (adjustRowNumbers > 0 && adjustRowNumbers < i) {
                i = adjustRowNumbers;
            }
        }
        return this.detectionResultColumns;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.barcodeColumnCount;
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.barcodeMetadata.c();
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.barcodeMetadata.b();
    }

    /* access modifiers changed from: package-private */
    public BoundingBox e() {
        return this.boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox2) {
        this.boundingBox = boundingBox2;
    }

    public String toString() {
        Codeword codeword;
        DetectionResultColumn[] detectionResultColumnArr = this.detectionResultColumns;
        DetectionResultColumn detectionResultColumn = detectionResultColumnArr[0];
        if (detectionResultColumn == null) {
            detectionResultColumn = detectionResultColumnArr[this.barcodeColumnCount + 1];
        }
        Formatter formatter = new Formatter();
        for (int i = 0; i < detectionResultColumn.b().length; i++) {
            formatter.format("CW %3d:", new Object[]{Integer.valueOf(i)});
            for (int i2 = 0; i2 < this.barcodeColumnCount + 2; i2++) {
                DetectionResultColumn[] detectionResultColumnArr2 = this.detectionResultColumns;
                if (detectionResultColumnArr2[i2] == null || (codeword = detectionResultColumnArr2[i2].b()[i]) == null) {
                    formatter.format("    |   ", new Object[0]);
                } else {
                    formatter.format(" %3d|%3d", new Object[]{Integer.valueOf(codeword.h()), Integer.valueOf(codeword.g())});
                }
            }
            formatter.format("%n", new Object[0]);
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }
}

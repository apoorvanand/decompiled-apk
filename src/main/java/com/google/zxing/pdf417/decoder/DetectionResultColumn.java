package com.google.zxing.pdf417.decoder;

import java.util.Formatter;

class DetectionResultColumn {
    private static final int MAX_NEARBY_DISTANCE = 5;
    private final BoundingBox boundingBox;
    private final Codeword[] codewords;

    DetectionResultColumn(BoundingBox boundingBox2) {
        this.boundingBox = new BoundingBox(boundingBox2);
        this.codewords = new Codeword[((boundingBox2.d() - boundingBox2.c()) + 1)];
    }

    /* access modifiers changed from: package-private */
    public final BoundingBox a() {
        return this.boundingBox;
    }

    /* access modifiers changed from: package-private */
    public final Codeword a(int i) {
        Codeword codeword;
        Codeword codeword2;
        Codeword c = c(i);
        if (c != null) {
            return c;
        }
        for (int i2 = 1; i2 < 5; i2++) {
            int b = b(i) - i2;
            if (b >= 0 && (codeword2 = this.codewords[b]) != null) {
                return codeword2;
            }
            int b2 = b(i) + i2;
            Codeword[] codewordArr = this.codewords;
            if (b2 < codewordArr.length && (codeword = codewordArr[b2]) != null) {
                return codeword;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final void a(int i, Codeword codeword) {
        this.codewords[b(i)] = codeword;
    }

    /* access modifiers changed from: package-private */
    public final int b(int i) {
        return i - this.boundingBox.c();
    }

    /* access modifiers changed from: package-private */
    public final Codeword[] b() {
        return this.codewords;
    }

    /* access modifiers changed from: package-private */
    public final Codeword c(int i) {
        return this.codewords[b(i)];
    }

    public String toString() {
        Formatter formatter = new Formatter();
        int i = 0;
        for (Codeword codeword : this.codewords) {
            if (codeword == null) {
                formatter.format("%3d:    |   %n", new Object[]{Integer.valueOf(i)});
                i++;
            } else {
                formatter.format("%3d: %3d|%3d%n", new Object[]{Integer.valueOf(i), Integer.valueOf(codeword.h()), Integer.valueOf(codeword.g())});
                i++;
            }
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }
}

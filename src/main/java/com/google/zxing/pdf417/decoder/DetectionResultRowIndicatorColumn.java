package com.google.zxing.pdf417.decoder;

import com.google.zxing.ResultPoint;

final class DetectionResultRowIndicatorColumn extends DetectionResultColumn {
    private final boolean isLeft;

    DetectionResultRowIndicatorColumn(BoundingBox boundingBox, boolean z) {
        super(boundingBox);
        this.isLeft = z;
    }

    private void adjustIncompleteIndicatorColumnRowNumbers(BarcodeMetadata barcodeMetadata) {
        BoundingBox a = a();
        ResultPoint e = this.isLeft ? a.e() : a.f();
        ResultPoint g = this.isLeft ? a.g() : a.h();
        int b = b((int) g.getY());
        Codeword[] b2 = b();
        int i = -1;
        int i2 = 0;
        int i3 = 1;
        for (int b3 = b((int) e.getY()); b3 < b; b3++) {
            if (b2[b3] != null) {
                Codeword codeword = b2[b3];
                codeword.b();
                int h = codeword.h() - i;
                if (h == 0) {
                    i2++;
                } else {
                    if (h == 1) {
                        i3 = Math.max(i3, i2);
                        i = codeword.h();
                    } else if (codeword.h() >= barcodeMetadata.c()) {
                        b2[b3] = null;
                    } else {
                        i = codeword.h();
                    }
                    i2 = 1;
                }
            }
        }
    }

    private void removeIncorrectCodewords(Codeword[] codewordArr, BarcodeMetadata barcodeMetadata) {
        for (int i = 0; i < codewordArr.length; i++) {
            Codeword codeword = codewordArr[i];
            if (codewordArr[i] != null) {
                int g = codeword.g() % 30;
                int h = codeword.h();
                if (h <= barcodeMetadata.c()) {
                    if (!this.isLeft) {
                        h += 2;
                    }
                    switch (h % 3) {
                        case 0:
                            if ((g * 3) + 1 == barcodeMetadata.d()) {
                                break;
                            } else {
                                codewordArr[i] = null;
                                break;
                            }
                        case 1:
                            if (g / 3 != barcodeMetadata.b() || g % 3 != barcodeMetadata.e()) {
                                codewordArr[i] = null;
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (g + 1 == barcodeMetadata.a()) {
                                break;
                            } else {
                                codewordArr[i] = null;
                                break;
                            }
                    }
                } else {
                    codewordArr[i] = null;
                }
            }
        }
    }

    private void setRowNumbers() {
        for (Codeword codeword : b()) {
            if (codeword != null) {
                codeword.b();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(BarcodeMetadata barcodeMetadata) {
        Codeword[] b = b();
        setRowNumbers();
        removeIncorrectCodewords(b, barcodeMetadata);
        BoundingBox a = a();
        ResultPoint e = this.isLeft ? a.e() : a.f();
        ResultPoint g = this.isLeft ? a.g() : a.h();
        int b2 = b((int) e.getY());
        int b3 = b((int) g.getY());
        int i = -1;
        int i2 = 0;
        int i3 = 1;
        while (b2 < b3) {
            if (b[b2] != null) {
                Codeword codeword = b[b2];
                int h = codeword.h() - i;
                if (h == 0) {
                    i2++;
                } else {
                    if (h == 1) {
                        i3 = Math.max(i3, i2);
                        i = codeword.h();
                    } else if (h < 0 || codeword.h() >= barcodeMetadata.c() || h > b2) {
                        b[b2] = null;
                    } else {
                        if (i3 > 2) {
                            h *= i3 - 2;
                        }
                        boolean z = h >= b2;
                        for (int i4 = 1; i4 <= h && !z; i4++) {
                            z = b[b2 - i4] != null;
                        }
                        if (z) {
                            b[b2] = null;
                        } else {
                            i = codeword.h();
                        }
                    }
                    i2 = 1;
                }
            }
            b2++;
        }
    }

    /* access modifiers changed from: package-private */
    public int[] c() {
        int h;
        BarcodeMetadata d = d();
        if (d == null) {
            return null;
        }
        adjustIncompleteIndicatorColumnRowNumbers(d);
        int[] iArr = new int[d.c()];
        for (Codeword codeword : b()) {
            if (codeword != null && (h = codeword.h()) < iArr.length) {
                iArr[h] = iArr[h] + 1;
            }
        }
        return iArr;
    }

    /* access modifiers changed from: package-private */
    public BarcodeMetadata d() {
        Codeword[] b = b();
        BarcodeValue barcodeValue = new BarcodeValue();
        BarcodeValue barcodeValue2 = new BarcodeValue();
        BarcodeValue barcodeValue3 = new BarcodeValue();
        BarcodeValue barcodeValue4 = new BarcodeValue();
        for (Codeword codeword : b) {
            if (codeword != null) {
                codeword.b();
                int g = codeword.g() % 30;
                int h = codeword.h();
                if (!this.isLeft) {
                    h += 2;
                }
                switch (h % 3) {
                    case 0:
                        barcodeValue2.a((g * 3) + 1);
                        break;
                    case 1:
                        barcodeValue4.a(g / 3);
                        barcodeValue3.a(g % 3);
                        break;
                    case 2:
                        barcodeValue.a(g + 1);
                        break;
                }
            }
        }
        if (barcodeValue.a().length == 0 || barcodeValue2.a().length == 0 || barcodeValue3.a().length == 0 || barcodeValue4.a().length == 0 || barcodeValue.a()[0] <= 0 || barcodeValue2.a()[0] + barcodeValue3.a()[0] < 3 || barcodeValue2.a()[0] + barcodeValue3.a()[0] > 90) {
            return null;
        }
        BarcodeMetadata barcodeMetadata = new BarcodeMetadata(barcodeValue.a()[0], barcodeValue2.a()[0], barcodeValue3.a()[0], barcodeValue4.a()[0]);
        removeIncorrectCodewords(b, barcodeMetadata);
        return barcodeMetadata;
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return this.isLeft;
    }

    public String toString() {
        return "IsLeft: " + this.isLeft + 10 + super.toString();
    }
}

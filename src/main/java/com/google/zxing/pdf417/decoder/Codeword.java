package com.google.zxing.pdf417.decoder;

final class Codeword {
    private static final int BARCODE_ROW_UNKNOWN = -1;
    private final int bucket;
    private final int endX;
    private int rowNumber = -1;
    private final int startX;
    private final int value;

    Codeword(int i, int i2, int i3, int i4) {
        this.startX = i;
        this.endX = i2;
        this.bucket = i3;
        this.value = i4;
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return a(this.rowNumber);
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i) {
        return i != -1 && this.bucket == (i % 3) * 3;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.rowNumber = ((this.value / 30) * 3) + (this.bucket / 3);
    }

    /* access modifiers changed from: package-private */
    public void b(int i) {
        this.rowNumber = i;
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.endX - this.startX;
    }

    /* access modifiers changed from: package-private */
    public int d() {
        return this.startX;
    }

    /* access modifiers changed from: package-private */
    public int e() {
        return this.endX;
    }

    /* access modifiers changed from: package-private */
    public int f() {
        return this.bucket;
    }

    /* access modifiers changed from: package-private */
    public int g() {
        return this.value;
    }

    /* access modifiers changed from: package-private */
    public int h() {
        return this.rowNumber;
    }

    public String toString() {
        return this.rowNumber + "|" + this.value;
    }
}

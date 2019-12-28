package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;

final class DecodedNumeric extends DecodedObject {
    private final int firstDigit;
    private final int secondDigit;

    DecodedNumeric(int i, int i2, int i3) {
        super(i);
        if (i2 < 0 || i2 > 10 || i3 < 0 || i3 > 10) {
            throw FormatException.getFormatInstance();
        }
        this.firstDigit = i2;
        this.secondDigit = i3;
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.firstDigit;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.secondDigit;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.firstDigit == 10;
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        return this.secondDigit == 10;
    }
}

package com.google.zxing.oned.rss.expanded.decoders;

final class DecodedChar extends DecodedObject {
    private final char value;

    DecodedChar(int i, char c) {
        super(i);
        this.value = c;
    }

    /* access modifiers changed from: package-private */
    public char a() {
        return this.value;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.value == '$';
    }
}
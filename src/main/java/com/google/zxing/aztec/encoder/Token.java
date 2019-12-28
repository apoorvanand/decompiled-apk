package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;

abstract class Token {
    static final Token a = new SimpleToken((Token) null, 0, 0);
    private final Token previous;

    Token(Token token) {
        this.previous = token;
    }

    /* access modifiers changed from: package-private */
    public final Token a() {
        return this.previous;
    }

    /* access modifiers changed from: package-private */
    public final Token a(int i, int i2) {
        return new SimpleToken(this, i, i2);
    }

    /* access modifiers changed from: package-private */
    public abstract void appendTo(BitArray bitArray, byte[] bArr);

    /* access modifiers changed from: package-private */
    public final Token b(int i, int i2) {
        return new BinaryShiftToken(this, i, i2);
    }
}
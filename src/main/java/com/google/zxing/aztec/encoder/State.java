package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import java.util.LinkedList;

final class State {
    static final State a = new State(Token.a, 0, 0, 0);
    private final int binaryShiftByteCount;
    private final int bitCount;
    private final int mode;
    private final Token token;

    private State(Token token2, int i, int i2, int i3) {
        this.token = token2;
        this.mode = i;
        this.binaryShiftByteCount = i2;
        this.bitCount = i3;
    }

    /* access modifiers changed from: package-private */
    public int a() {
        return this.mode;
    }

    /* access modifiers changed from: package-private */
    public State a(int i) {
        Token token2 = this.token;
        int i2 = this.mode;
        int i3 = this.bitCount;
        if (i2 == 4 || i2 == 2) {
            int i4 = HighLevelEncoder.b[i2][0];
            int i5 = 65535 & i4;
            int i6 = i4 >> 16;
            token2 = token2.a(i5, i6);
            i3 += i6;
            i2 = 0;
        }
        int i7 = this.binaryShiftByteCount;
        State state = new State(token2, i2, this.binaryShiftByteCount + 1, i3 + ((i7 == 0 || i7 == 31) ? 18 : i7 == 62 ? 9 : 8));
        return state.binaryShiftByteCount == 2078 ? state.b(i + 1) : state;
    }

    /* access modifiers changed from: package-private */
    public State a(int i, int i2) {
        int i3 = this.bitCount;
        Token token2 = this.token;
        if (i != this.mode) {
            int i4 = HighLevelEncoder.b[this.mode][i];
            int i5 = 65535 & i4;
            int i6 = i4 >> 16;
            token2 = token2.a(i5, i6);
            i3 += i6;
        }
        int i7 = i == 2 ? 4 : 5;
        return new State(token2.a(i2, i7), i, 0, i3 + i7);
    }

    /* access modifiers changed from: package-private */
    public BitArray a(byte[] bArr) {
        LinkedList<Token> linkedList = new LinkedList<>();
        for (Token token2 = b(bArr.length).token; token2 != null; token2 = token2.a()) {
            linkedList.addFirst(token2);
        }
        BitArray bitArray = new BitArray();
        for (Token appendTo : linkedList) {
            appendTo.appendTo(bitArray, bArr);
        }
        return bitArray;
    }

    /* access modifiers changed from: package-private */
    public boolean a(State state) {
        int i;
        int i2 = this.bitCount + (HighLevelEncoder.b[this.mode][state.mode] >> 16);
        int i3 = state.binaryShiftByteCount;
        if (i3 > 0 && ((i = this.binaryShiftByteCount) == 0 || i > i3)) {
            i2 += 10;
        }
        return i2 <= state.bitCount;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.binaryShiftByteCount;
    }

    /* access modifiers changed from: package-private */
    public State b(int i) {
        int i2 = this.binaryShiftByteCount;
        return i2 == 0 ? this : new State(this.token.b(i - i2, i2), this.mode, 0, this.bitCount);
    }

    /* access modifiers changed from: package-private */
    public State b(int i, int i2) {
        Token token2 = this.token;
        int i3 = this.mode == 2 ? 4 : 5;
        return new State(token2.a(HighLevelEncoder.c[this.mode][i], i3).a(i2, 5), this.mode, 0, this.bitCount + i3 + 5);
    }

    /* access modifiers changed from: package-private */
    public int c() {
        return this.bitCount;
    }

    public String toString() {
        return String.format("%s bits=%d bytes=%d", new Object[]{HighLevelEncoder.a[this.mode], Integer.valueOf(this.bitCount), Integer.valueOf(this.binaryShiftByteCount)});
    }
}

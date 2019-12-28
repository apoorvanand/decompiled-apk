package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import kotlin.text.Typography;

final class BinaryShiftToken extends Token {
    private final short binaryShiftByteCount;
    private final short binaryShiftStart;

    BinaryShiftToken(Token token, int i, int i2) {
        super(token);
        this.binaryShiftStart = (short) i;
        this.binaryShiftByteCount = (short) i2;
    }

    public void appendTo(BitArray bitArray, byte[] bArr) {
        int i;
        int i2 = 0;
        while (true) {
            short s = this.binaryShiftByteCount;
            if (i2 < s) {
                if (i2 == 0 || (i2 == 31 && s <= 62)) {
                    int i3 = 5;
                    bitArray.appendBits(31, 5);
                    short s2 = this.binaryShiftByteCount;
                    if (s2 > 62) {
                        i = s2 - 31;
                        i3 = 16;
                    } else if (i2 == 0) {
                        bitArray.appendBits(Math.min(s2, 31), 5);
                    } else {
                        i = s2 - 31;
                    }
                    bitArray.appendBits(i, i3);
                }
                bitArray.appendBits(bArr[this.binaryShiftStart + i2], 8);
                i2++;
            } else {
                return;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("<");
        sb.append(this.binaryShiftStart);
        sb.append("::");
        sb.append((this.binaryShiftStart + this.binaryShiftByteCount) - 1);
        sb.append(Typography.greater);
        return sb.toString();
    }
}

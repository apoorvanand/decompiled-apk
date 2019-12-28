package com.google.zxing.datamatrix.encoder;

final class X12Encoder extends C40Encoder {
    X12Encoder() {
    }

    /* access modifiers changed from: package-private */
    public int a(char c, StringBuilder sb) {
        int i;
        char c2;
        if (c == 13) {
            c2 = 0;
        } else {
            if (c == '*') {
                sb.append(1);
            } else if (c == '>') {
                c2 = 2;
            } else if (c == ' ') {
                c2 = 3;
            } else {
                if (c >= '0' && c <= '9') {
                    i = (c - '0') + 4;
                } else if (c < 'A' || c > 'Z') {
                    HighLevelEncoder.c(c);
                } else {
                    i = (c - 'A') + 14;
                }
                c2 = (char) i;
            }
            return 1;
        }
        sb.append(c2);
        return 1;
    }

    /* access modifiers changed from: package-private */
    public void b(EncoderContext encoderContext, StringBuilder sb) {
        encoderContext.updateSymbolInfo();
        int dataCapacity = encoderContext.getSymbolInfo().getDataCapacity() - encoderContext.getCodewordCount();
        encoderContext.a -= sb.length();
        if (encoderContext.getRemainingCharacters() > 1 || dataCapacity > 1 || encoderContext.getRemainingCharacters() != dataCapacity) {
            encoderContext.writeCodeword(254);
        }
        if (encoderContext.getNewEncoding() < 0) {
            encoderContext.signalEncoderChange(0);
        }
    }

    public void encode(EncoderContext encoderContext) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!encoderContext.hasMoreCharacters()) {
                break;
            }
            char currentChar = encoderContext.getCurrentChar();
            encoderContext.a++;
            a(currentChar, sb);
            if (sb.length() % 3 == 0) {
                a(encoderContext, sb);
                int a = HighLevelEncoder.a(encoderContext.getMessage(), encoderContext.a, getEncodingMode());
                if (a != getEncodingMode()) {
                    encoderContext.signalEncoderChange(a);
                    break;
                }
            }
        }
        b(encoderContext, sb);
    }

    public int getEncodingMode() {
        return 3;
    }
}

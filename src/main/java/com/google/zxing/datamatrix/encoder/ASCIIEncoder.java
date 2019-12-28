package com.google.zxing.datamatrix.encoder;

final class ASCIIEncoder implements Encoder {
    ASCIIEncoder() {
    }

    private static char encodeASCIIDigits(char c, char c2) {
        if (HighLevelEncoder.a(c) && HighLevelEncoder.a(c2)) {
            return (char) (((c - '0') * 10) + (c2 - '0') + 130);
        }
        throw new IllegalArgumentException("not digits: " + c + c2);
    }

    public void encode(EncoderContext encoderContext) {
        if (HighLevelEncoder.determineConsecutiveDigitCount(encoderContext.getMessage(), encoderContext.a) >= 2) {
            encoderContext.writeCodeword(encodeASCIIDigits(encoderContext.getMessage().charAt(encoderContext.a), encoderContext.getMessage().charAt(encoderContext.a + 1)));
            encoderContext.a += 2;
            return;
        }
        char currentChar = encoderContext.getCurrentChar();
        int a = HighLevelEncoder.a(encoderContext.getMessage(), encoderContext.a, getEncodingMode());
        if (a != getEncodingMode()) {
            switch (a) {
                case 1:
                    encoderContext.writeCodeword(230);
                    encoderContext.signalEncoderChange(1);
                    return;
                case 2:
                    encoderContext.writeCodeword(239);
                    encoderContext.signalEncoderChange(2);
                    return;
                case 3:
                    encoderContext.writeCodeword(238);
                    encoderContext.signalEncoderChange(3);
                    return;
                case 4:
                    encoderContext.writeCodeword(240);
                    encoderContext.signalEncoderChange(4);
                    return;
                case 5:
                    encoderContext.writeCodeword(231);
                    encoderContext.signalEncoderChange(5);
                    return;
                default:
                    throw new IllegalStateException("Illegal mode: " + a);
            }
        } else if (HighLevelEncoder.b(currentChar)) {
            encoderContext.writeCodeword(235);
            encoderContext.writeCodeword((char) ((currentChar - 128) + 1));
            encoderContext.a++;
        } else {
            encoderContext.writeCodeword((char) (currentChar + 1));
            encoderContext.a++;
        }
    }

    public int getEncodingMode() {
        return 0;
    }
}

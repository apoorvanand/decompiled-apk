package com.google.zxing.datamatrix.encoder;

class C40Encoder implements Encoder {
    C40Encoder() {
    }

    static void a(EncoderContext encoderContext, StringBuilder sb) {
        encoderContext.writeCodewords(encodeToCodewords(sb, 0));
        sb.delete(0, 3);
    }

    private int backtrackOneCharacter(EncoderContext encoderContext, StringBuilder sb, StringBuilder sb2, int i) {
        int length = sb.length();
        sb.delete(length - i, length);
        encoderContext.a--;
        int a = a(encoderContext.getCurrentChar(), sb2);
        encoderContext.resetSymbolInfo();
        return a;
    }

    private static String encodeToCodewords(CharSequence charSequence, int i) {
        int charAt = (charSequence.charAt(i) * 1600) + (charSequence.charAt(i + 1) * '(') + charSequence.charAt(i + 2) + 1;
        return new String(new char[]{(char) (charAt / 256), (char) (charAt % 256)});
    }

    /* access modifiers changed from: package-private */
    public int a(char c, StringBuilder sb) {
        int i;
        int i2;
        char c2;
        if (c == ' ') {
            c2 = 3;
        } else {
            if (c >= '0' && c <= '9') {
                i2 = (c - '0') + 4;
            } else if (c < 'A' || c > 'Z') {
                if (c < 0 || c > 31) {
                    if (c >= '!' && c <= '/') {
                        sb.append(1);
                        i = c - '!';
                    } else if (c >= ':' && c <= '@') {
                        sb.append(1);
                        i = (c - ':') + 15;
                    } else if (c >= '[' && c <= '_') {
                        sb.append(1);
                        i = (c - '[') + 22;
                    } else if (c >= '`' && c <= 127) {
                        sb.append(2);
                        i = c - '`';
                    } else if (c >= 128) {
                        sb.append("\u0001\u001e");
                        return a((char) (c - 128), sb) + 2;
                    } else {
                        throw new IllegalArgumentException("Illegal character: " + c);
                    }
                    c = (char) i;
                } else {
                    sb.append(0);
                }
                sb.append(c);
                return 2;
            } else {
                i2 = (c - 'A') + 14;
            }
            c2 = (char) i2;
        }
        sb.append(c2);
        return 1;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006a, code lost:
        if (r8.hasMoreCharacters() == false) goto L_0x006d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0035, code lost:
        if (r8.hasMoreCharacters() != false) goto L_0x0037;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(com.google.zxing.datamatrix.encoder.EncoderContext r8, java.lang.StringBuilder r9) {
        /*
            r7 = this;
            int r0 = r9.length()
            r1 = 3
            int r0 = r0 / r1
            r2 = 1
            int r0 = r0 << r2
            int r3 = r9.length()
            int r3 = r3 % r1
            int r4 = r8.getCodewordCount()
            int r4 = r4 + r0
            r8.updateSymbolInfo(r4)
            com.google.zxing.datamatrix.encoder.SymbolInfo r0 = r8.getSymbolInfo()
            int r0 = r0.getDataCapacity()
            int r0 = r0 - r4
            r4 = 0
            r5 = 254(0xfe, float:3.56E-43)
            r6 = 2
            if (r3 != r6) goto L_0x003b
            r9.append(r4)
        L_0x0027:
            int r0 = r9.length()
            if (r0 < r1) goto L_0x0031
            a((com.google.zxing.datamatrix.encoder.EncoderContext) r8, (java.lang.StringBuilder) r9)
            goto L_0x0027
        L_0x0031:
            boolean r9 = r8.hasMoreCharacters()
            if (r9 == 0) goto L_0x006d
        L_0x0037:
            r8.writeCodeword(r5)
            goto L_0x006d
        L_0x003b:
            if (r0 != r2) goto L_0x0058
            if (r3 != r2) goto L_0x0058
        L_0x003f:
            int r0 = r9.length()
            if (r0 < r1) goto L_0x0049
            a((com.google.zxing.datamatrix.encoder.EncoderContext) r8, (java.lang.StringBuilder) r9)
            goto L_0x003f
        L_0x0049:
            boolean r9 = r8.hasMoreCharacters()
            if (r9 == 0) goto L_0x0052
            r8.writeCodeword(r5)
        L_0x0052:
            int r9 = r8.a
            int r9 = r9 - r2
            r8.a = r9
            goto L_0x006d
        L_0x0058:
            if (r3 != 0) goto L_0x0071
        L_0x005a:
            int r2 = r9.length()
            if (r2 < r1) goto L_0x0064
            a((com.google.zxing.datamatrix.encoder.EncoderContext) r8, (java.lang.StringBuilder) r9)
            goto L_0x005a
        L_0x0064:
            if (r0 > 0) goto L_0x0037
            boolean r9 = r8.hasMoreCharacters()
            if (r9 == 0) goto L_0x006d
            goto L_0x0037
        L_0x006d:
            r8.signalEncoderChange(r4)
            return
        L_0x0071:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "Unexpected case. Please report!"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.encoder.C40Encoder.b(com.google.zxing.datamatrix.encoder.EncoderContext, java.lang.StringBuilder):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0055, code lost:
        if (r2 != 1) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0057, code lost:
        if (r1 > 3) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0046, code lost:
        if (r2 > 2) goto L_0x0048;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void encode(com.google.zxing.datamatrix.encoder.EncoderContext r9) {
        /*
            r8 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
        L_0x0005:
            boolean r1 = r9.hasMoreCharacters()
            if (r1 == 0) goto L_0x0078
            char r1 = r9.getCurrentChar()
            int r2 = r9.a
            r3 = 1
            int r2 = r2 + r3
            r9.a = r2
            int r1 = r8.a((char) r1, (java.lang.StringBuilder) r0)
            int r2 = r0.length()
            r4 = 3
            int r2 = r2 / r4
            int r2 = r2 << r3
            int r5 = r9.getCodewordCount()
            int r5 = r5 + r2
            r9.updateSymbolInfo(r5)
            com.google.zxing.datamatrix.encoder.SymbolInfo r2 = r9.getSymbolInfo()
            int r2 = r2.getDataCapacity()
            int r2 = r2 - r5
            boolean r5 = r9.hasMoreCharacters()
            if (r5 != 0) goto L_0x005a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            int r6 = r0.length()
            int r6 = r6 % r4
            r7 = 2
            if (r6 != r7) goto L_0x004c
            if (r2 < r7) goto L_0x0048
            if (r2 <= r7) goto L_0x004c
        L_0x0048:
            int r1 = r8.backtrackOneCharacter(r9, r0, r5, r1)
        L_0x004c:
            int r6 = r0.length()
            int r6 = r6 % r4
            if (r6 != r3) goto L_0x0078
            if (r1 > r4) goto L_0x0057
            if (r2 != r3) goto L_0x0048
        L_0x0057:
            if (r1 <= r4) goto L_0x0078
            goto L_0x0048
        L_0x005a:
            int r1 = r0.length()
            int r1 = r1 % r4
            if (r1 != 0) goto L_0x0005
            java.lang.String r1 = r9.getMessage()
            int r2 = r9.a
            int r3 = r8.getEncodingMode()
            int r1 = com.google.zxing.datamatrix.encoder.HighLevelEncoder.a(r1, r2, r3)
            int r2 = r8.getEncodingMode()
            if (r1 == r2) goto L_0x0005
            r9.signalEncoderChange(r1)
        L_0x0078:
            r8.b(r9, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.encoder.C40Encoder.encode(com.google.zxing.datamatrix.encoder.EncoderContext):void");
    }

    public int getEncodingMode() {
        return 1;
    }
}

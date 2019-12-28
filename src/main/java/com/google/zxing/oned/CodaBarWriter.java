package com.google.zxing.oned;

public final class CodaBarWriter extends OneDimensionalCodeWriter {
    private static final char[] ALT_START_END_CHARS = {'T', 'N', '*', 'E'};
    private static final char[] CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED = {'/', ':', '+', '.'};
    private static final char DEFAULT_GUARD = START_END_CHARS[0];
    private static final char[] START_END_CHARS = {'A', 'B', 'C', 'D'};

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00f1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean[] encode(java.lang.String r11) {
        /*
            r10 = this;
            int r0 = r11.length()
            r1 = 0
            r2 = 1
            r3 = 2
            if (r0 >= r3) goto L_0x0020
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
        L_0x000e:
            char r3 = DEFAULT_GUARD
            r0.append(r3)
            r0.append(r11)
            char r11 = DEFAULT_GUARD
            r0.append(r11)
            java.lang.String r11 = r0.toString()
            goto L_0x0089
        L_0x0020:
            char r0 = r11.charAt(r1)
            char r0 = java.lang.Character.toUpperCase(r0)
            int r3 = r11.length()
            int r3 = r3 - r2
            char r3 = r11.charAt(r3)
            char r3 = java.lang.Character.toUpperCase(r3)
            char[] r4 = START_END_CHARS
            boolean r4 = com.google.zxing.oned.CodaBarReader.a(r4, r0)
            char[] r5 = START_END_CHARS
            boolean r5 = com.google.zxing.oned.CodaBarReader.a(r5, r3)
            char[] r6 = ALT_START_END_CHARS
            boolean r0 = com.google.zxing.oned.CodaBarReader.a(r6, r0)
            char[] r6 = ALT_START_END_CHARS
            boolean r3 = com.google.zxing.oned.CodaBarReader.a(r6, r3)
            if (r4 == 0) goto L_0x0066
            if (r5 == 0) goto L_0x0052
            goto L_0x0089
        L_0x0052:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Invalid start/end guards: "
            r1.<init>(r2)
            r1.append(r11)
            java.lang.String r11 = r1.toString()
            r0.<init>(r11)
            throw r0
        L_0x0066:
            if (r0 == 0) goto L_0x007f
            if (r3 == 0) goto L_0x006b
            goto L_0x0089
        L_0x006b:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Invalid start/end guards: "
            r1.<init>(r2)
            r1.append(r11)
            java.lang.String r11 = r1.toString()
            r0.<init>(r11)
            throw r0
        L_0x007f:
            if (r5 != 0) goto L_0x0160
            if (r3 != 0) goto L_0x0160
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            goto L_0x000e
        L_0x0089:
            r0 = 20
            r0 = 1
            r3 = 20
        L_0x008e:
            int r4 = r11.length()
            int r4 = r4 - r2
            if (r0 >= r4) goto L_0x00e1
            char r4 = r11.charAt(r0)
            boolean r4 = java.lang.Character.isDigit(r4)
            if (r4 != 0) goto L_0x00dc
            char r4 = r11.charAt(r0)
            r5 = 45
            if (r4 == r5) goto L_0x00dc
            char r4 = r11.charAt(r0)
            r5 = 36
            if (r4 != r5) goto L_0x00b0
            goto L_0x00dc
        L_0x00b0:
            char[] r4 = CHARS_WHICH_ARE_TEN_LENGTH_EACH_AFTER_DECODED
            char r5 = r11.charAt(r0)
            boolean r4 = com.google.zxing.oned.CodaBarReader.a(r4, r5)
            if (r4 == 0) goto L_0x00bf
            int r3 = r3 + 10
            goto L_0x00de
        L_0x00bf:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Cannot encode : '"
            r2.<init>(r3)
            char r11 = r11.charAt(r0)
            r2.append(r11)
            r11 = 39
            r2.append(r11)
            java.lang.String r11 = r2.toString()
            r1.<init>(r11)
            throw r1
        L_0x00dc:
            int r3 = r3 + 9
        L_0x00de:
            int r0 = r0 + 1
            goto L_0x008e
        L_0x00e1:
            int r0 = r11.length()
            int r0 = r0 - r2
            int r3 = r3 + r0
            boolean[] r0 = new boolean[r3]
            r3 = 0
            r4 = 0
        L_0x00eb:
            int r5 = r11.length()
            if (r3 >= r5) goto L_0x015f
            char r5 = r11.charAt(r3)
            char r5 = java.lang.Character.toUpperCase(r5)
            if (r3 == 0) goto L_0x0102
            int r6 = r11.length()
            int r6 = r6 - r2
            if (r3 != r6) goto L_0x011e
        L_0x0102:
            r6 = 42
            if (r5 == r6) goto L_0x011c
            r6 = 69
            if (r5 == r6) goto L_0x0119
            r6 = 78
            if (r5 == r6) goto L_0x0116
            r6 = 84
            if (r5 == r6) goto L_0x0113
            goto L_0x011e
        L_0x0113:
            r5 = 65
            goto L_0x011e
        L_0x0116:
            r5 = 66
            goto L_0x011e
        L_0x0119:
            r5 = 68
            goto L_0x011e
        L_0x011c:
            r5 = 67
        L_0x011e:
            r6 = 0
        L_0x011f:
            char[] r7 = com.google.zxing.oned.CodaBarReader.a
            int r7 = r7.length
            if (r6 >= r7) goto L_0x0132
            char[] r7 = com.google.zxing.oned.CodaBarReader.a
            char r7 = r7[r6]
            if (r5 != r7) goto L_0x012f
            int[] r5 = com.google.zxing.oned.CodaBarReader.b
            r5 = r5[r6]
            goto L_0x0133
        L_0x012f:
            int r6 = r6 + 1
            goto L_0x011f
        L_0x0132:
            r5 = 0
        L_0x0133:
            r6 = r4
            r4 = 0
            r7 = 1
        L_0x0136:
            r8 = 0
        L_0x0137:
            r9 = 7
            if (r4 >= r9) goto L_0x0150
            r0[r6] = r7
            int r6 = r6 + 1
            int r9 = 6 - r4
            int r9 = r5 >> r9
            r9 = r9 & r2
            if (r9 == 0) goto L_0x014b
            if (r8 != r2) goto L_0x0148
            goto L_0x014b
        L_0x0148:
            int r8 = r8 + 1
            goto L_0x0137
        L_0x014b:
            r7 = r7 ^ 1
            int r4 = r4 + 1
            goto L_0x0136
        L_0x0150:
            int r4 = r11.length()
            int r4 = r4 - r2
            if (r3 >= r4) goto L_0x015b
            r0[r6] = r1
            int r6 = r6 + 1
        L_0x015b:
            r4 = r6
            int r3 = r3 + 1
            goto L_0x00eb
        L_0x015f:
            return r0
        L_0x0160:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Invalid start/end guards: "
            r1.<init>(r2)
            r1.append(r11)
            java.lang.String r11 = r1.toString()
            r0.<init>(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.CodaBarWriter.encode(java.lang.String):boolean[]");
    }
}

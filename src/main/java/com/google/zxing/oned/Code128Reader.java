package com.google.zxing.oned;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class Code128Reader extends OneDReader {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final float MAX_AVG_VARIANCE = 0.25f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;
    static final int[][] a = {new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};

    private static int decodeCode(BitArray bitArray, int[] iArr, int i) {
        a(bitArray, i, iArr);
        float f = MAX_AVG_VARIANCE;
        int i2 = -1;
        int i3 = 0;
        while (true) {
            int[][] iArr2 = a;
            if (i3 >= iArr2.length) {
                break;
            }
            float a2 = a(iArr, iArr2[i3], (float) MAX_INDIVIDUAL_VARIANCE);
            if (a2 < f) {
                i2 = i3;
                f = a2;
            }
            i3++;
        }
        if (i2 >= 0) {
            return i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int[] findStartPattern(BitArray bitArray) {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int[] iArr = new int[6];
        int i = nextSet;
        boolean z = false;
        int i2 = 0;
        while (nextSet < size) {
            if (bitArray.get(nextSet) ^ z) {
                iArr[i2] = iArr[i2] + 1;
            } else {
                if (i2 == 5) {
                    float f = MAX_AVG_VARIANCE;
                    int i3 = -1;
                    for (int i4 = 103; i4 <= 105; i4++) {
                        float a2 = a(iArr, a[i4], (float) MAX_INDIVIDUAL_VARIANCE);
                        if (a2 < f) {
                            i3 = i4;
                            f = a2;
                        }
                    }
                    if (i3 < 0 || !bitArray.isRange(Math.max(0, i - ((nextSet - i) / 2)), i, false)) {
                        i += iArr[0] + iArr[1];
                        System.arraycopy(iArr, 2, iArr, 0, 4);
                        iArr[4] = 0;
                        iArr[5] = 0;
                        i2--;
                    } else {
                        return new int[]{i, nextSet, i3};
                    }
                } else {
                    i2++;
                }
                iArr[i2] = 1;
                z = !z;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0167, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0168, code lost:
        if (r14 == false) goto L_0x0174;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x016c, code lost:
        if (r3 != 'e') goto L_0x0171;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x016e, code lost:
        r3 = 'd';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0171, code lost:
        r3 = 'e';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0176, code lost:
        r14 = r5;
        r5 = r12;
        r15 = 6;
        r12 = r8;
        r8 = r21;
        r23 = r16;
        r16 = r9;
        r9 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00b7, code lost:
        if (r13.length() == 0) goto L_0x00b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00b9, code lost:
        r13.append("]C1");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00c0, code lost:
        r13.append(29);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00ce, code lost:
        if (r5 != false) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00e4, code lost:
        r13.append((char) r5);
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00eb, code lost:
        r12 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00fe, code lost:
        if (r13.length() == 0) goto L_0x00b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0109, code lost:
        r5 = false;
        r11 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0110, code lost:
        if (r5 != false) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0112, code lost:
        r5 = false;
        r11 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0117, code lost:
        r5 = false;
        r12 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x011c, code lost:
        r12 = r5;
        r3 = 'c';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0123, code lost:
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0125, code lost:
        r6 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0126, code lost:
        r12 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0127, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0166, code lost:
        r12 = r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.Result decodeRow(int r25, com.google.zxing.common.BitArray r26, java.util.Map<com.google.zxing.DecodeHintType, ?> r27) {
        /*
            r24 = this;
            r0 = r26
            r1 = r27
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x0012
            com.google.zxing.DecodeHintType r4 = com.google.zxing.DecodeHintType.ASSUME_GS1
            boolean r1 = r1.containsKey(r4)
            if (r1 == 0) goto L_0x0012
            r1 = 1
            goto L_0x0013
        L_0x0012:
            r1 = 0
        L_0x0013:
            int[] r4 = findStartPattern(r26)
            r5 = 2
            r6 = r4[r5]
            java.util.ArrayList r7 = new java.util.ArrayList
            r8 = 20
            r7.<init>(r8)
            byte r9 = (byte) r6
            java.lang.Byte r9 = java.lang.Byte.valueOf(r9)
            r7.add(r9)
            switch(r6) {
                case 103: goto L_0x0037;
                case 104: goto L_0x0034;
                case 105: goto L_0x0031;
                default: goto L_0x002c;
            }
        L_0x002c:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x0031:
            r12 = 99
            goto L_0x0039
        L_0x0034:
            r12 = 100
            goto L_0x0039
        L_0x0037:
            r12 = 101(0x65, float:1.42E-43)
        L_0x0039:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>(r8)
            r8 = r4[r3]
            r14 = r4[r2]
            r15 = 6
            int[] r2 = new int[r15]
            r17 = r6
            r3 = r12
            r5 = 0
            r6 = 0
            r9 = 0
            r11 = 0
            r16 = 0
            r18 = 0
            r19 = 1
            r12 = r8
            r8 = r14
            r14 = 0
        L_0x0055:
            if (r6 != 0) goto L_0x0184
            int r9 = decodeCode(r0, r2, r8)
            byte r12 = (byte) r9
            java.lang.Byte r12 = java.lang.Byte.valueOf(r12)
            r7.add(r12)
            r12 = 106(0x6a, float:1.49E-43)
            if (r9 == r12) goto L_0x0069
            r19 = 1
        L_0x0069:
            if (r9 == r12) goto L_0x0071
            int r18 = r18 + 1
            int r20 = r18 * r9
            int r17 = r17 + r20
        L_0x0071:
            r21 = r8
            r10 = 0
        L_0x0074:
            if (r10 >= r15) goto L_0x007d
            r22 = r2[r10]
            int r21 = r21 + r22
            int r10 = r10 + 1
            goto L_0x0074
        L_0x007d:
            switch(r9) {
                case 103: goto L_0x0089;
                case 104: goto L_0x0089;
                case 105: goto L_0x0089;
                default: goto L_0x0080;
            }
        L_0x0080:
            r10 = 96
            switch(r3) {
                case 99: goto L_0x012b;
                case 100: goto L_0x00d9;
                case 101: goto L_0x008e;
                default: goto L_0x0085;
            }
        L_0x0085:
            r10 = 100
            goto L_0x0166
        L_0x0089:
            com.google.zxing.FormatException r0 = com.google.zxing.FormatException.getFormatInstance()
            throw r0
        L_0x008e:
            r15 = 64
            if (r9 >= r15) goto L_0x009c
            if (r5 != r11) goto L_0x0097
            int r5 = r9 + 32
            goto L_0x00e4
        L_0x0097:
            int r5 = r9 + 32
            int r5 = r5 + 128
            goto L_0x00e4
        L_0x009c:
            if (r9 >= r10) goto L_0x00a6
            if (r5 != r11) goto L_0x00a3
            int r5 = r9 + -64
            goto L_0x00e4
        L_0x00a3:
            int r5 = r9 + 64
            goto L_0x00e4
        L_0x00a6:
            if (r9 == r12) goto L_0x00aa
            r19 = 0
        L_0x00aa:
            if (r9 == r12) goto L_0x0125
            switch(r9) {
                case 96: goto L_0x0126;
                case 97: goto L_0x0126;
                case 98: goto L_0x00d5;
                case 99: goto L_0x011c;
                case 100: goto L_0x00d1;
                case 101: goto L_0x00c7;
                case 102: goto L_0x00b1;
                default: goto L_0x00af;
            }
        L_0x00af:
            goto L_0x0126
        L_0x00b1:
            if (r1 == 0) goto L_0x0126
            int r10 = r13.length()
            if (r10 != 0) goto L_0x00c0
        L_0x00b9:
            java.lang.String r10 = "]C1"
            r13.append(r10)
            goto L_0x0126
        L_0x00c0:
            r10 = 29
            r13.append(r10)
            goto L_0x0126
        L_0x00c7:
            if (r11 != 0) goto L_0x00cc
            if (r5 == 0) goto L_0x00cc
            goto L_0x0109
        L_0x00cc:
            if (r11 == 0) goto L_0x0117
            if (r5 == 0) goto L_0x0117
            goto L_0x0112
        L_0x00d1:
            r12 = r5
            r3 = 100
            goto L_0x0127
        L_0x00d5:
            r12 = r5
            r3 = 100
            goto L_0x0123
        L_0x00d9:
            if (r9 >= r10) goto L_0x00ee
            if (r5 != r11) goto L_0x00e0
            int r5 = r9 + 32
            goto L_0x00e4
        L_0x00e0:
            int r5 = r9 + 32
            int r5 = r5 + 128
        L_0x00e4:
            char r5 = (char) r5
            r13.append(r5)
            r5 = 0
            r10 = 100
        L_0x00eb:
            r12 = 0
            goto L_0x0168
        L_0x00ee:
            if (r9 == r12) goto L_0x00f2
            r19 = 0
        L_0x00f2:
            if (r9 == r12) goto L_0x0125
            switch(r9) {
                case 96: goto L_0x0126;
                case 97: goto L_0x0126;
                case 98: goto L_0x0120;
                case 99: goto L_0x011c;
                case 100: goto L_0x0105;
                case 101: goto L_0x0101;
                case 102: goto L_0x00f8;
                default: goto L_0x00f7;
            }
        L_0x00f7:
            goto L_0x0126
        L_0x00f8:
            if (r1 == 0) goto L_0x0126
            int r10 = r13.length()
            if (r10 != 0) goto L_0x00c0
            goto L_0x00b9
        L_0x0101:
            r12 = r5
            r3 = 101(0x65, float:1.42E-43)
            goto L_0x0127
        L_0x0105:
            if (r11 != 0) goto L_0x010e
            if (r5 == 0) goto L_0x010e
        L_0x0109:
            r5 = 0
            r10 = 100
            r11 = 1
            goto L_0x00eb
        L_0x010e:
            if (r11 == 0) goto L_0x0117
            if (r5 == 0) goto L_0x0117
        L_0x0112:
            r5 = 0
            r10 = 100
            r11 = 0
            goto L_0x00eb
        L_0x0117:
            r5 = 0
            r10 = 100
            r12 = 1
            goto L_0x0168
        L_0x011c:
            r12 = r5
            r3 = 99
            goto L_0x0127
        L_0x0120:
            r12 = r5
            r3 = 101(0x65, float:1.42E-43)
        L_0x0123:
            r5 = 1
            goto L_0x0128
        L_0x0125:
            r6 = 1
        L_0x0126:
            r12 = r5
        L_0x0127:
            r5 = 0
        L_0x0128:
            r10 = 100
            goto L_0x0168
        L_0x012b:
            r10 = 100
            if (r9 >= r10) goto L_0x013c
            r12 = 10
            if (r9 >= r12) goto L_0x0138
            r12 = 48
            r13.append(r12)
        L_0x0138:
            r13.append(r9)
            goto L_0x0166
        L_0x013c:
            if (r9 == r12) goto L_0x0140
            r19 = 0
        L_0x0140:
            if (r9 == r12) goto L_0x0162
            switch(r9) {
                case 100: goto L_0x015e;
                case 101: goto L_0x015a;
                case 102: goto L_0x0146;
                default: goto L_0x0145;
            }
        L_0x0145:
            goto L_0x0166
        L_0x0146:
            if (r1 == 0) goto L_0x0166
            int r12 = r13.length()
            if (r12 != 0) goto L_0x0154
            java.lang.String r12 = "]C1"
            r13.append(r12)
            goto L_0x0166
        L_0x0154:
            r12 = 29
            r13.append(r12)
            goto L_0x0166
        L_0x015a:
            r12 = r5
            r3 = 101(0x65, float:1.42E-43)
            goto L_0x0167
        L_0x015e:
            r12 = r5
            r3 = 100
            goto L_0x0167
        L_0x0162:
            r12 = r5
            r5 = 0
            r6 = 1
            goto L_0x0168
        L_0x0166:
            r12 = r5
        L_0x0167:
            r5 = 0
        L_0x0168:
            if (r14 == 0) goto L_0x0174
            r14 = 101(0x65, float:1.42E-43)
            if (r3 != r14) goto L_0x0171
            r3 = 100
            goto L_0x0176
        L_0x0171:
            r3 = 101(0x65, float:1.42E-43)
            goto L_0x0176
        L_0x0174:
            r14 = 101(0x65, float:1.42E-43)
        L_0x0176:
            r14 = r5
            r5 = r12
            r15 = 6
            r12 = r8
            r8 = r21
            r23 = r16
            r16 = r9
            r9 = r23
            goto L_0x0055
        L_0x0184:
            int r1 = r8 - r12
            int r2 = r0.getNextUnset(r8)
            int r5 = r26.getSize()
            int r6 = r2 - r12
            r8 = 2
            int r6 = r6 / r8
            int r6 = r6 + r2
            int r5 = java.lang.Math.min(r5, r6)
            r6 = 0
            boolean r0 = r0.isRange(r2, r5, r6)
            if (r0 == 0) goto L_0x020f
            int r18 = r18 * r9
            int r17 = r17 - r18
            int r0 = r17 % 103
            if (r0 != r9) goto L_0x020a
            int r0 = r13.length()
            if (r0 == 0) goto L_0x0205
            if (r0 <= 0) goto L_0x01bc
            if (r19 == 0) goto L_0x01bc
            r2 = 99
            if (r3 != r2) goto L_0x01b7
            int r2 = r0 + -2
            goto L_0x01b9
        L_0x01b7:
            int r2 = r0 + -1
        L_0x01b9:
            r13.delete(r2, r0)
        L_0x01bc:
            r0 = 1
            r2 = r4[r0]
            r0 = 0
            r3 = r4[r0]
            int r2 = r2 + r3
            float r0 = (float) r2
            r2 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r2
            float r3 = (float) r12
            float r1 = (float) r1
            float r1 = r1 / r2
            float r3 = r3 + r1
            int r1 = r7.size()
            byte[] r2 = new byte[r1]
            r4 = 0
        L_0x01d2:
            if (r4 >= r1) goto L_0x01e3
            java.lang.Object r5 = r7.get(r4)
            java.lang.Byte r5 = (java.lang.Byte) r5
            byte r5 = r5.byteValue()
            r2[r4] = r5
            int r4 = r4 + 1
            goto L_0x01d2
        L_0x01e3:
            com.google.zxing.Result r1 = new com.google.zxing.Result
            java.lang.String r4 = r13.toString()
            r5 = 2
            com.google.zxing.ResultPoint[] r5 = new com.google.zxing.ResultPoint[r5]
            com.google.zxing.ResultPoint r6 = new com.google.zxing.ResultPoint
            r7 = r25
            float r7 = (float) r7
            r6.<init>(r0, r7)
            r0 = 0
            r5[r0] = r6
            com.google.zxing.ResultPoint r0 = new com.google.zxing.ResultPoint
            r0.<init>(r3, r7)
            r3 = 1
            r5[r3] = r0
            com.google.zxing.BarcodeFormat r0 = com.google.zxing.BarcodeFormat.CODE_128
            r1.<init>(r4, r2, r5, r0)
            return r1
        L_0x0205:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        L_0x020a:
            com.google.zxing.ChecksumException r0 = com.google.zxing.ChecksumException.getChecksumInstance()
            throw r0
        L_0x020f:
            com.google.zxing.NotFoundException r0 = com.google.zxing.NotFoundException.getNotFoundInstance()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Reader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }
}

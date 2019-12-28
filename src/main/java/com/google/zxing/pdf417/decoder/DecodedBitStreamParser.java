package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

final class DecodedBitStreamParser {
    private static final int AL = 28;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final Charset DEFAULT_ENCODING = Charset.forName("ISO-8859-1");
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900;
    private static final int LL = 27;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;

    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900);
        EXP900[1] = valueOf;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = EXP900;
            if (i < bigIntegerArr2.length) {
                bigIntegerArr2[i] = bigIntegerArr2[i - 1].multiply(valueOf);
                i++;
            } else {
                return;
            }
        }
    }

    private DecodedBitStreamParser() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004e, code lost:
        r2 = byteCompaction(r2, r6, r1, r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        r2 = textCompaction(r6, r4, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.zxing.common.DecoderResult a(int[] r6, java.lang.String r7) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            int r1 = r6.length
            r2 = 1
            int r1 = r1 << r2
            r0.<init>(r1)
            java.nio.charset.Charset r1 = DEFAULT_ENCODING
            r2 = r6[r2]
            com.google.zxing.pdf417.PDF417ResultMetadata r3 = new com.google.zxing.pdf417.PDF417ResultMetadata
            r3.<init>()
            r4 = 2
        L_0x0012:
            r5 = 0
            r5 = r6[r5]
            if (r4 >= r5) goto L_0x0068
            r5 = 913(0x391, float:1.28E-42)
            if (r2 == r5) goto L_0x0053
            switch(r2) {
                case 900: goto L_0x0023;
                case 901: goto L_0x004e;
                case 902: goto L_0x0049;
                default: goto L_0x001e;
            }
        L_0x001e:
            switch(r2) {
                case 922: goto L_0x0044;
                case 923: goto L_0x0044;
                case 924: goto L_0x004e;
                case 925: goto L_0x0041;
                case 926: goto L_0x003e;
                case 927: goto L_0x002d;
                case 928: goto L_0x0028;
                default: goto L_0x0021;
            }
        L_0x0021:
            int r4 = r4 + -1
        L_0x0023:
            int r2 = textCompaction(r6, r4, r0)
            goto L_0x005b
        L_0x0028:
            int r2 = decodeMacroBlock(r6, r4, r3)
            goto L_0x005b
        L_0x002d:
            int r2 = r4 + 1
            r1 = r6[r4]
            com.google.zxing.common.CharacterSetECI r1 = com.google.zxing.common.CharacterSetECI.getCharacterSetECIByValue(r1)
            java.lang.String r1 = r1.name()
            java.nio.charset.Charset r1 = java.nio.charset.Charset.forName(r1)
            goto L_0x005b
        L_0x003e:
            int r2 = r4 + 2
            goto L_0x005b
        L_0x0041:
            int r2 = r4 + 1
            goto L_0x005b
        L_0x0044:
            com.google.zxing.FormatException r6 = com.google.zxing.FormatException.getFormatInstance()
            throw r6
        L_0x0049:
            int r2 = numericCompaction(r6, r4, r0)
            goto L_0x005b
        L_0x004e:
            int r2 = byteCompaction(r2, r6, r1, r4, r0)
            goto L_0x005b
        L_0x0053:
            int r2 = r4 + 1
            r4 = r6[r4]
            char r4 = (char) r4
            r0.append(r4)
        L_0x005b:
            int r4 = r6.length
            if (r2 >= r4) goto L_0x0063
            int r4 = r2 + 1
            r2 = r6[r2]
            goto L_0x0012
        L_0x0063:
            com.google.zxing.FormatException r6 = com.google.zxing.FormatException.getFormatInstance()
            throw r6
        L_0x0068:
            int r6 = r0.length()
            if (r6 == 0) goto L_0x007c
            com.google.zxing.common.DecoderResult r6 = new com.google.zxing.common.DecoderResult
            java.lang.String r0 = r0.toString()
            r1 = 0
            r6.<init>(r1, r0, r1, r7)
            r6.setOther(r3)
            return r6
        L_0x007c:
            com.google.zxing.FormatException r6 = com.google.zxing.FormatException.getFormatInstance()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.a(int[], java.lang.String):com.google.zxing.common.DecoderResult");
    }

    private static int byteCompaction(int i, int[] iArr, Charset charset, int i2, StringBuilder sb) {
        int i3;
        long j;
        int i4;
        int i5;
        int i6 = i;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i7 = MACRO_PDF417_TERMINATOR;
        int i8 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
        int i9 = 928;
        long j2 = 900;
        if (i6 == BYTE_COMPACTION_MODE_LATCH) {
            int[] iArr2 = new int[6];
            int i10 = i2 + 1;
            int i11 = iArr[i2];
            boolean z = false;
            loop0:
            while (true) {
                i4 = 0;
                long j3 = 0;
                while (i3 < iArr[0] && !z) {
                    int i12 = i4 + 1;
                    iArr2[i4] = i11;
                    j3 = (j3 * j) + ((long) i11);
                    int i13 = i3 + 1;
                    i11 = iArr[i3];
                    if (i11 == TEXT_COMPACTION_MODE_LATCH || i11 == BYTE_COMPACTION_MODE_LATCH || i11 == NUMERIC_COMPACTION_MODE_LATCH || i11 == BYTE_COMPACTION_MODE_LATCH_6 || i11 == 928 || i11 == i8 || i11 == i7) {
                        i3 = i13 - 1;
                        i4 = i12;
                        i7 = MACRO_PDF417_TERMINATOR;
                        i8 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                        j = 900;
                        z = true;
                    } else if (i12 % 5 != 0 || i12 <= 0) {
                        i3 = i13;
                        i4 = i12;
                        i7 = MACRO_PDF417_TERMINATOR;
                        i8 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                        j = 900;
                    } else {
                        int i14 = 0;
                        while (i14 < 6) {
                            byteArrayOutputStream.write((byte) ((int) (j3 >> ((5 - i14) * 8))));
                            i14++;
                            i7 = MACRO_PDF417_TERMINATOR;
                            i8 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                        }
                        i10 = i13;
                        j2 = 900;
                    }
                }
            }
            if (i3 != iArr[0] || i11 >= TEXT_COMPACTION_MODE_LATCH) {
                i5 = i4;
            } else {
                i5 = i4 + 1;
                iArr2[i4] = i11;
            }
            for (int i15 = 0; i15 < i5; i15++) {
                byteArrayOutputStream.write((byte) iArr2[i15]);
            }
        } else if (i6 == BYTE_COMPACTION_MODE_LATCH_6) {
            int i16 = i2;
            int i17 = 0;
            long j4 = 0;
            boolean z2 = false;
            while (i16 < iArr[0] && !z2) {
                int i18 = i16 + 1;
                int i19 = iArr[i16];
                if (i19 < TEXT_COMPACTION_MODE_LATCH) {
                    i17++;
                    j4 = (j4 * 900) + ((long) i19);
                    i16 = i18;
                } else {
                    if (i19 != TEXT_COMPACTION_MODE_LATCH && i19 != BYTE_COMPACTION_MODE_LATCH && i19 != NUMERIC_COMPACTION_MODE_LATCH && i19 != BYTE_COMPACTION_MODE_LATCH_6 && i19 != i9) {
                        if (i19 != BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
                            if (i19 != MACRO_PDF417_TERMINATOR) {
                                i16 = i18;
                            }
                            i16 = i18 - 1;
                            z2 = true;
                        }
                    }
                    i16 = i18 - 1;
                    z2 = true;
                }
                if (i17 % 5 == 0 && i17 > 0) {
                    int i20 = 0;
                    for (int i21 = 6; i20 < i21; i21 = 6) {
                        byteArrayOutputStream.write((byte) ((int) (j4 >> ((5 - i20) * 8))));
                        i20++;
                    }
                    i17 = 0;
                    j4 = 0;
                }
                i9 = 928;
            }
            i3 = i16;
        } else {
            i3 = i2;
        }
        sb.append(new String(byteArrayOutputStream.toByteArray(), charset));
        return i3;
    }

    private static String decodeBase900toBase10(int[] iArr, int i) {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigInteger = bigInteger.add(EXP900[(i - i2) - 1].multiply(BigInteger.valueOf((long) iArr[i2])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) == '1') {
            return bigInteger2.substring(1);
        }
        throw FormatException.getFormatInstance();
    }

    private static int decodeMacroBlock(int[] iArr, int i, PDF417ResultMetadata pDF417ResultMetadata) {
        if (i + 2 <= iArr[0]) {
            int[] iArr2 = new int[2];
            int i2 = i;
            int i3 = 0;
            while (i3 < 2) {
                iArr2[i3] = iArr[i2];
                i3++;
                i2++;
            }
            pDF417ResultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(iArr2, 2)));
            StringBuilder sb = new StringBuilder();
            int textCompaction = textCompaction(iArr, i2, sb);
            pDF417ResultMetadata.setFileId(sb.toString());
            if (iArr[textCompaction] == BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
                int i4 = textCompaction + 1;
                int[] iArr3 = new int[(iArr[0] - i4)];
                boolean z = false;
                int i5 = 0;
                while (i4 < iArr[0] && !z) {
                    int i6 = i4 + 1;
                    int i7 = iArr[i4];
                    if (i7 < TEXT_COMPACTION_MODE_LATCH) {
                        iArr3[i5] = i7;
                        i4 = i6;
                        i5++;
                    } else if (i7 == MACRO_PDF417_TERMINATOR) {
                        pDF417ResultMetadata.setLastSegment(true);
                        i4 = i6 + 1;
                        z = true;
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
                pDF417ResultMetadata.setOptionalData(Arrays.copyOf(iArr3, i5));
                return i4;
            } else if (iArr[textCompaction] != MACRO_PDF417_TERMINATOR) {
                return textCompaction;
            } else {
                pDF417ResultMetadata.setLastSegment(true);
                return textCompaction + 1;
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0046, code lost:
        r6 = r4;
        r4 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004f, code lost:
        if (r6 == TEXT_COMPACTION_MODE_LATCH) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0052, code lost:
        r4 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005f, code lost:
        r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0067, code lost:
        r0.append((char) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006d, code lost:
        if (r6 == TEXT_COMPACTION_MODE_LATCH) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0091, code lost:
        if (r6 == TEXT_COMPACTION_MODE_LATCH) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a0, code lost:
        r6 = 0;
        r15 = r5;
        r5 = r4;
        r4 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b0, code lost:
        if (r6 == TEXT_COMPACTION_MODE_LATCH) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00b7, code lost:
        r6 = (char) r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00bb, code lost:
        r6 = ' ';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00c0, code lost:
        r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00c5, code lost:
        r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00ca, code lost:
        r5 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00d2, code lost:
        if (r6 == TEXT_COMPACTION_MODE_LATCH) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00d5, code lost:
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00d6, code lost:
        if (r6 == 0) goto L_0x00db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00d8, code lost:
        r0.append(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00db, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003e, code lost:
        if (r6 == TEXT_COMPACTION_MODE_LATCH) goto L_0x005f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void decodeTextCompaction(int[] r16, int[] r17, int r18, java.lang.StringBuilder r19) {
        /*
            r0 = r19
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r2 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            r3 = 0
            r4 = r1
            r5 = r2
            r2 = 0
            r1 = r18
        L_0x000c:
            if (r2 >= r1) goto L_0x00df
            r6 = r16[r2]
            int[] r7 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.AnonymousClass1.a
            int r8 = r4.ordinal()
            r7 = r7[r8]
            r8 = 28
            r9 = 27
            r10 = 32
            r11 = 913(0x391, float:1.28E-42)
            r12 = 900(0x384, float:1.261E-42)
            r13 = 29
            r14 = 26
            switch(r7) {
                case 1: goto L_0x00b3;
                case 2: goto L_0x0094;
                case 3: goto L_0x0070;
                case 4: goto L_0x0055;
                case 5: goto L_0x0041;
                case 6: goto L_0x002b;
                default: goto L_0x0029;
            }
        L_0x0029:
            goto L_0x00d5
        L_0x002b:
            if (r6 >= r13) goto L_0x0032
            char[] r4 = PUNCT_CHARS
            char r4 = r4[r6]
            goto L_0x0046
        L_0x0032:
            if (r6 != r13) goto L_0x0035
            goto L_0x005f
        L_0x0035:
            if (r6 != r11) goto L_0x003e
            r4 = r17[r2]
            char r4 = (char) r4
            r0.append(r4)
            goto L_0x0052
        L_0x003e:
            if (r6 != r12) goto L_0x0052
            goto L_0x005f
        L_0x0041:
            if (r6 >= r14) goto L_0x004a
            int r6 = r6 + 65
            char r4 = (char) r6
        L_0x0046:
            r6 = r4
            r4 = r5
            goto L_0x00d6
        L_0x004a:
            if (r6 != r14) goto L_0x004f
            r4 = r5
            goto L_0x00bb
        L_0x004f:
            if (r6 != r12) goto L_0x0052
            goto L_0x005f
        L_0x0052:
            r4 = r5
            goto L_0x00d5
        L_0x0055:
            if (r6 >= r13) goto L_0x005d
            char[] r7 = PUNCT_CHARS
            char r6 = r7[r6]
            goto L_0x00d6
        L_0x005d:
            if (r6 != r13) goto L_0x0063
        L_0x005f:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            goto L_0x00d5
        L_0x0063:
            if (r6 != r11) goto L_0x006d
            r6 = r17[r2]
        L_0x0067:
            char r6 = (char) r6
            r0.append(r6)
            goto L_0x00d5
        L_0x006d:
            if (r6 != r12) goto L_0x00d5
            goto L_0x005f
        L_0x0070:
            r7 = 25
            if (r6 >= r7) goto L_0x007a
            char[] r7 = MIXED_CHARS
            char r6 = r7[r6]
            goto L_0x00d6
        L_0x007a:
            if (r6 != r7) goto L_0x0080
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT
            goto L_0x00d5
        L_0x0080:
            if (r6 != r14) goto L_0x0083
            goto L_0x00bb
        L_0x0083:
            if (r6 != r9) goto L_0x0086
            goto L_0x00c0
        L_0x0086:
            if (r6 != r8) goto L_0x0089
            goto L_0x005f
        L_0x0089:
            if (r6 != r13) goto L_0x008c
            goto L_0x00ca
        L_0x008c:
            if (r6 != r11) goto L_0x0091
            r6 = r17[r2]
            goto L_0x0067
        L_0x0091:
            if (r6 != r12) goto L_0x00d5
            goto L_0x005f
        L_0x0094:
            if (r6 >= r14) goto L_0x0099
            int r6 = r6 + 97
            goto L_0x00b7
        L_0x0099:
            if (r6 != r14) goto L_0x009c
            goto L_0x00bb
        L_0x009c:
            if (r6 != r9) goto L_0x00a5
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r5 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA_SHIFT
        L_0x00a0:
            r6 = 0
            r15 = r5
            r5 = r4
            r4 = r15
            goto L_0x00d6
        L_0x00a5:
            if (r6 != r8) goto L_0x00a8
            goto L_0x00c5
        L_0x00a8:
            if (r6 != r13) goto L_0x00ab
            goto L_0x00ca
        L_0x00ab:
            if (r6 != r11) goto L_0x00b0
            r6 = r17[r2]
            goto L_0x0067
        L_0x00b0:
            if (r6 != r12) goto L_0x00d5
            goto L_0x005f
        L_0x00b3:
            if (r6 >= r14) goto L_0x00b9
            int r6 = r6 + 65
        L_0x00b7:
            char r6 = (char) r6
            goto L_0x00d6
        L_0x00b9:
            if (r6 != r14) goto L_0x00be
        L_0x00bb:
            r6 = 32
            goto L_0x00d6
        L_0x00be:
            if (r6 != r9) goto L_0x00c3
        L_0x00c0:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER
            goto L_0x00d5
        L_0x00c3:
            if (r6 != r8) goto L_0x00c8
        L_0x00c5:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED
            goto L_0x00d5
        L_0x00c8:
            if (r6 != r13) goto L_0x00cd
        L_0x00ca:
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r5 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
            goto L_0x00a0
        L_0x00cd:
            if (r6 != r11) goto L_0x00d2
            r6 = r17[r2]
            goto L_0x0067
        L_0x00d2:
            if (r6 != r12) goto L_0x00d5
            goto L_0x005f
        L_0x00d5:
            r6 = 0
        L_0x00d6:
            if (r6 == 0) goto L_0x00db
            r0.append(r6)
        L_0x00db:
            int r2 = r2 + 1
            goto L_0x000c
        L_0x00df:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decodeTextCompaction(int[], int[], int, java.lang.StringBuilder):void");
    }

    private static int numericCompaction(int[] iArr, int i, StringBuilder sb) {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i3 == iArr[0]) {
                z = true;
            }
            if (i4 < TEXT_COMPACTION_MODE_LATCH) {
                iArr2[i2] = i4;
                i2++;
            } else if (i4 == TEXT_COMPACTION_MODE_LATCH || i4 == BYTE_COMPACTION_MODE_LATCH || i4 == BYTE_COMPACTION_MODE_LATCH_6 || i4 == 928 || i4 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || i4 == MACRO_PDF417_TERMINATOR) {
                i3--;
                z = true;
            }
            if ((i2 % 15 == 0 || i4 == NUMERIC_COMPACTION_MODE_LATCH || z) && i2 > 0) {
                sb.append(decodeBase900toBase10(iArr2, i2));
                i2 = 0;
            }
            i = i3;
        }
        return i;
    }

    private static int textCompaction(int[] iArr, int i, StringBuilder sb) {
        int[] iArr2 = new int[((iArr[0] - i) << 1)];
        int[] iArr3 = new int[((iArr[0] - i) << 1)];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i4 < TEXT_COMPACTION_MODE_LATCH) {
                iArr2[i2] = i4 / 30;
                iArr2[i2 + 1] = i4 % 30;
                i2 += 2;
            } else if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                if (i4 != 928) {
                    switch (i4) {
                        case TEXT_COMPACTION_MODE_LATCH /*900*/:
                            iArr2[i2] = TEXT_COMPACTION_MODE_LATCH;
                            i2++;
                            break;
                        case BYTE_COMPACTION_MODE_LATCH /*901*/:
                        case NUMERIC_COMPACTION_MODE_LATCH /*902*/:
                            break;
                        default:
                            switch (i4) {
                                case MACRO_PDF417_TERMINATOR /*922*/:
                                case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /*923*/:
                                case BYTE_COMPACTION_MODE_LATCH_6 /*924*/:
                                    break;
                            }
                    }
                }
                i = i3 - 1;
                z = true;
            } else {
                iArr2[i2] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                i = i3 + 1;
                iArr3[i2] = iArr[i3];
                i2++;
            }
            i = i3;
        }
        decodeTextCompaction(iArr2, iArr3, i2, sb);
        return i;
    }
}

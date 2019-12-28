package com.google.zxing.pdf417.encoder;

import com.facebook.appevents.AppEventsConstants;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import com.google.zxing.WriterException;
import com.google.zxing.common.CharacterSetECI;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

final class PDF417HighLevelEncoder {
    private static final int BYTE_COMPACTION = 1;
    private static final Charset DEFAULT_ENCODING = Charset.forName("ISO-8859-1");
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final int LATCH_TO_BYTE = 924;
    private static final int LATCH_TO_BYTE_PADDED = 901;
    private static final int LATCH_TO_NUMERIC = 902;
    private static final int LATCH_TO_TEXT = 900;
    private static final byte[] MIXED = new byte[128];
    private static final int NUMERIC_COMPACTION = 2;
    private static final byte[] PUNCTUATION = new byte[128];
    private static final int SHIFT_TO_BYTE = 913;
    private static final int SUBMODE_ALPHA = 0;
    private static final int SUBMODE_LOWER = 1;
    private static final int SUBMODE_MIXED = 2;
    private static final int SUBMODE_PUNCTUATION = 3;
    private static final int TEXT_COMPACTION = 0;
    private static final byte[] TEXT_MIXED_RAW = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 38, Ascii.CR, 9, 44, 58, 35, 45, 46, 36, 47, 43, 37, 42, 61, 94, 0, 32, 0, 0, 0};
    private static final byte[] TEXT_PUNCTUATION_RAW = {59, 60, 62, SignedBytes.MAX_POWER_OF_TWO, 91, 92, 93, 95, 96, 126, 33, Ascii.CR, 9, 44, 58, 10, 45, 46, 36, 47, 34, 124, 42, 40, 41, 63, 123, 125, 39, 0};

    static {
        Arrays.fill(MIXED, (byte) -1);
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = TEXT_MIXED_RAW;
            if (i2 >= bArr.length) {
                break;
            }
            byte b = bArr[i2];
            if (b > 0) {
                MIXED[b] = (byte) i2;
            }
            i2++;
        }
        Arrays.fill(PUNCTUATION, (byte) -1);
        while (true) {
            byte[] bArr2 = TEXT_PUNCTUATION_RAW;
            if (i < bArr2.length) {
                byte b2 = bArr2[i];
                if (b2 > 0) {
                    PUNCTUATION[b2] = (byte) i;
                }
                i++;
            } else {
                return;
            }
        }
    }

    private PDF417HighLevelEncoder() {
    }

    static String a(String str, Compaction compaction, Charset charset) {
        CharacterSetECI characterSetECIByName;
        StringBuilder sb = new StringBuilder(str.length());
        if (charset == null) {
            charset = DEFAULT_ENCODING;
        } else if (!DEFAULT_ENCODING.equals(charset) && (characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(charset.name())) != null) {
            encodingECI(characterSetECIByName.getValue(), sb);
        }
        int length = str.length();
        if (compaction == Compaction.TEXT) {
            encodeText(str, 0, length, sb, 0);
        } else if (compaction == Compaction.BYTE) {
            byte[] bytes = str.getBytes(charset);
            encodeBinary(bytes, 0, bytes.length, 1, sb);
        } else if (compaction == Compaction.NUMERIC) {
            sb.append(902);
            encodeNumeric(str, 0, length, sb);
        } else {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (i < length) {
                int determineConsecutiveDigitCount = determineConsecutiveDigitCount(str, i);
                if (determineConsecutiveDigitCount >= 13) {
                    sb.append(902);
                    i3 = 2;
                    encodeNumeric(str, i, determineConsecutiveDigitCount, sb);
                    i += determineConsecutiveDigitCount;
                    i2 = 0;
                } else {
                    int determineConsecutiveTextCount = determineConsecutiveTextCount(str, i);
                    if (determineConsecutiveTextCount >= 5 || determineConsecutiveDigitCount == length) {
                        if (i3 != 0) {
                            sb.append(900);
                            i2 = 0;
                            i3 = 0;
                        }
                        i2 = encodeText(str, i, determineConsecutiveTextCount, sb, i2);
                        i += determineConsecutiveTextCount;
                    } else {
                        int determineConsecutiveBinaryCount = determineConsecutiveBinaryCount(str, i, charset);
                        if (determineConsecutiveBinaryCount == 0) {
                            determineConsecutiveBinaryCount = 1;
                        }
                        int i4 = determineConsecutiveBinaryCount + i;
                        byte[] bytes2 = str.substring(i, i4).getBytes(charset);
                        if (bytes2.length == 1 && i3 == 0) {
                            encodeBinary(bytes2, 0, 1, 0, sb);
                        } else {
                            encodeBinary(bytes2, 0, bytes2.length, i3, sb);
                            i2 = 0;
                            i3 = 1;
                        }
                        i = i4;
                    }
                }
            }
        }
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001a, code lost:
        r3 = r3 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int determineConsecutiveBinaryCount(java.lang.String r5, int r6, java.nio.charset.Charset r7) {
        /*
            java.nio.charset.CharsetEncoder r7 = r7.newEncoder()
            int r0 = r5.length()
            r1 = r6
        L_0x0009:
            if (r1 >= r0) goto L_0x0057
            char r2 = r5.charAt(r1)
            r3 = 0
        L_0x0010:
            r4 = 13
            if (r3 >= r4) goto L_0x0025
            boolean r2 = isDigit(r2)
            if (r2 == 0) goto L_0x0025
            int r3 = r3 + 1
            int r2 = r1 + r3
            if (r2 >= r0) goto L_0x0025
            char r2 = r5.charAt(r2)
            goto L_0x0010
        L_0x0025:
            if (r3 < r4) goto L_0x0029
            int r1 = r1 - r6
            return r1
        L_0x0029:
            char r2 = r5.charAt(r1)
            boolean r3 = r7.canEncode(r2)
            if (r3 == 0) goto L_0x0036
            int r1 = r1 + 1
            goto L_0x0009
        L_0x0036:
            com.google.zxing.WriterException r5 = new com.google.zxing.WriterException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Non-encodable character detected: "
            r6.<init>(r7)
            r6.append(r2)
            java.lang.String r7 = " (Unicode: "
            r6.append(r7)
            r6.append(r2)
            r7 = 41
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.<init>((java.lang.String) r6)
            throw r5
        L_0x0057:
            int r1 = r1 - r6
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.determineConsecutiveBinaryCount(java.lang.String, int, java.nio.charset.Charset):int");
    }

    private static int determineConsecutiveDigitCount(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        if (i < length) {
            loop0:
            while (true) {
                char charAt = charSequence.charAt(i);
                while (true) {
                    if (!isDigit(charAt) || i >= length) {
                        break loop0;
                    }
                    i2++;
                    i++;
                    if (i < length) {
                    }
                }
            }
        }
        return i2;
    }

    private static int determineConsecutiveTextCount(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = i;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            int i3 = 0;
            while (i3 < 13 && isDigit(charAt) && i2 < length) {
                i3++;
                i2++;
                if (i2 < length) {
                    charAt = charSequence.charAt(i2);
                }
            }
            if (i3 < 13) {
                if (i3 <= 0) {
                    if (!isText(charSequence.charAt(i2))) {
                        break;
                    }
                    i2++;
                }
            } else {
                return (i2 - i) - i3;
            }
        }
        return i2 - i;
    }

    private static void encodeBinary(byte[] bArr, int i, int i2, int i3, StringBuilder sb) {
        int i4;
        sb.append((i2 == 1 && i3 == 0) ? 913 : i2 % 6 == 0 ? (char) 924 : 901);
        if (i2 >= 6) {
            char[] cArr = new char[5];
            i4 = i;
            while ((i + i2) - i4 >= 6) {
                long j = 0;
                for (int i5 = 0; i5 < 6; i5++) {
                    j = (j << 8) + ((long) (bArr[i4 + i5] & 255));
                }
                for (int i6 = 0; i6 < 5; i6++) {
                    cArr[i6] = (char) ((int) (j % 900));
                    j /= 900;
                }
                for (int i7 = 4; i7 >= 0; i7--) {
                    sb.append(cArr[i7]);
                }
                i4 += 6;
            }
        } else {
            i4 = i;
        }
        while (i4 < i + i2) {
            sb.append((char) (bArr[i4] & 255));
            i4++;
        }
    }

    private static void encodeNumeric(String str, int i, int i2, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder((i2 / 3) + 1);
        BigInteger valueOf = BigInteger.valueOf(900);
        BigInteger valueOf2 = BigInteger.valueOf(0);
        int i3 = 0;
        while (i3 < i2) {
            sb2.setLength(0);
            int min = Math.min(44, i2 - i3);
            StringBuilder sb3 = new StringBuilder(AppEventsConstants.EVENT_PARAM_VALUE_YES);
            int i4 = i + i3;
            sb3.append(str.substring(i4, i4 + min));
            BigInteger bigInteger = new BigInteger(sb3.toString());
            do {
                sb2.append((char) bigInteger.mod(valueOf).intValue());
                bigInteger = bigInteger.divide(valueOf);
            } while (!bigInteger.equals(valueOf2));
            for (int length = sb2.length() - 1; length >= 0; length--) {
                sb.append(sb2.charAt(length));
            }
            i3 += min;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0099, code lost:
        if (r10 == ' ') goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009b, code lost:
        r3.append(26);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009f, code lost:
        r10 = r10 - 'A';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a1, code lost:
        r9 = (char) r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a9, code lost:
        r3.append(27);
        r8 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b5, code lost:
        r3.append(28);
        r8 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00c4, code lost:
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00c6, code lost:
        if (r7 < r1) goto L_0x0011;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c8, code lost:
        r0 = r3.length();
        r1 = 0;
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ce, code lost:
        if (r1 >= r0) goto L_0x00ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00d2, code lost:
        if ((r1 % 2) == 0) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00d4, code lost:
        r9 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00d6, code lost:
        r9 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00d7, code lost:
        if (r9 == false) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00d9, code lost:
        r7 = (char) ((r7 * 30) + r3.charAt(r1));
        r2.append(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00e5, code lost:
        r7 = r3.charAt(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00e9, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00ed, code lost:
        if ((r0 % 2) == 0) goto L_0x00f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00ef, code lost:
        r2.append((char) ((r7 * 30) + 29));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00f6, code lost:
        return r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00fa, code lost:
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x002e, code lost:
        r9 = (char) r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002f, code lost:
        r3.append(r9);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int encodeText(java.lang.CharSequence r16, int r17, int r18, java.lang.StringBuilder r19, int r20) {
        /*
            r0 = r16
            r1 = r18
            r2 = r19
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r1)
            r4 = 2
            r5 = 1
            r6 = 0
            r8 = r20
            r7 = 0
        L_0x0011:
            int r9 = r17 + r7
            char r10 = r0.charAt(r9)
            r11 = 26
            r12 = 32
            r13 = 28
            r14 = 27
            r15 = 29
            switch(r8) {
                case 0: goto L_0x0093;
                case 1: goto L_0x006e;
                case 2: goto L_0x0034;
                default: goto L_0x0024;
            }
        L_0x0024:
            boolean r9 = isPunctuation(r10)
            if (r9 == 0) goto L_0x00f7
            byte[] r9 = PUNCTUATION
            byte r9 = r9[r10]
        L_0x002e:
            char r9 = (char) r9
        L_0x002f:
            r3.append(r9)
            goto L_0x00c4
        L_0x0034:
            boolean r11 = isMixed(r10)
            if (r11 == 0) goto L_0x003f
            byte[] r9 = MIXED
            byte r9 = r9[r10]
            goto L_0x002e
        L_0x003f:
            boolean r11 = isAlphaUpper(r10)
            if (r11 == 0) goto L_0x004a
            r3.append(r13)
            goto L_0x00fa
        L_0x004a:
            boolean r11 = isAlphaLower(r10)
            if (r11 == 0) goto L_0x0051
            goto L_0x00a9
        L_0x0051:
            int r9 = r9 + 1
            if (r9 >= r1) goto L_0x0066
            char r9 = r0.charAt(r9)
            boolean r9 = isPunctuation(r9)
            if (r9 == 0) goto L_0x0066
            r8 = 3
            r9 = 25
            r3.append(r9)
            goto L_0x0011
        L_0x0066:
            r3.append(r15)
            byte[] r9 = PUNCTUATION
            byte r9 = r9[r10]
            goto L_0x002e
        L_0x006e:
            boolean r9 = isAlphaLower(r10)
            if (r9 == 0) goto L_0x007a
            if (r10 != r12) goto L_0x0077
            goto L_0x009b
        L_0x0077:
            int r10 = r10 + -97
            goto L_0x00a1
        L_0x007a:
            boolean r9 = isAlphaUpper(r10)
            if (r9 == 0) goto L_0x0084
            r3.append(r14)
            goto L_0x009f
        L_0x0084:
            boolean r9 = isMixed(r10)
            if (r9 == 0) goto L_0x008b
            goto L_0x00b5
        L_0x008b:
            r3.append(r15)
            byte[] r9 = PUNCTUATION
            byte r9 = r9[r10]
            goto L_0x002e
        L_0x0093:
            boolean r9 = isAlphaUpper(r10)
            if (r9 == 0) goto L_0x00a3
            if (r10 != r12) goto L_0x009f
        L_0x009b:
            r3.append(r11)
            goto L_0x00c4
        L_0x009f:
            int r10 = r10 + -65
        L_0x00a1:
            char r9 = (char) r10
            goto L_0x002f
        L_0x00a3:
            boolean r9 = isAlphaLower(r10)
            if (r9 == 0) goto L_0x00af
        L_0x00a9:
            r3.append(r14)
            r8 = 1
            goto L_0x0011
        L_0x00af:
            boolean r9 = isMixed(r10)
            if (r9 == 0) goto L_0x00bb
        L_0x00b5:
            r3.append(r13)
            r8 = 2
            goto L_0x0011
        L_0x00bb:
            r3.append(r15)
            byte[] r9 = PUNCTUATION
            byte r9 = r9[r10]
            goto L_0x002e
        L_0x00c4:
            int r7 = r7 + 1
            if (r7 < r1) goto L_0x0011
            int r0 = r3.length()
            r1 = 0
            r7 = 0
        L_0x00ce:
            if (r1 >= r0) goto L_0x00ec
            int r9 = r1 % 2
            if (r9 == 0) goto L_0x00d6
            r9 = 1
            goto L_0x00d7
        L_0x00d6:
            r9 = 0
        L_0x00d7:
            if (r9 == 0) goto L_0x00e5
            int r7 = r7 * 30
            char r9 = r3.charAt(r1)
            int r7 = r7 + r9
            char r7 = (char) r7
            r2.append(r7)
            goto L_0x00e9
        L_0x00e5:
            char r7 = r3.charAt(r1)
        L_0x00e9:
            int r1 = r1 + 1
            goto L_0x00ce
        L_0x00ec:
            int r0 = r0 % r4
            if (r0 == 0) goto L_0x00f6
            int r7 = r7 * 30
            int r7 = r7 + r15
            char r0 = (char) r7
            r2.append(r0)
        L_0x00f6:
            return r8
        L_0x00f7:
            r3.append(r15)
        L_0x00fa:
            r8 = 0
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.encodeText(java.lang.CharSequence, int, int, java.lang.StringBuilder, int):int");
    }

    private static void encodingECI(int i, StringBuilder sb) {
        char c;
        if (i >= 0 && i < LATCH_TO_TEXT) {
            sb.append(927);
        } else if (i < 810900) {
            sb.append(926);
            sb.append((char) ((i / LATCH_TO_TEXT) - 1));
            i %= LATCH_TO_TEXT;
        } else if (i < 811800) {
            sb.append(925);
            c = (char) (810900 - i);
            sb.append(c);
        } else {
            throw new WriterException("ECI number not in valid range from 0..811799, but was " + i);
        }
        c = (char) i;
        sb.append(c);
    }

    private static boolean isAlphaLower(char c) {
        if (c != ' ') {
            return c >= 'a' && c <= 'z';
        }
        return true;
    }

    private static boolean isAlphaUpper(char c) {
        if (c != ' ') {
            return c >= 'A' && c <= 'Z';
        }
        return true;
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isMixed(char c) {
        return MIXED[c] != -1;
    }

    private static boolean isPunctuation(char c) {
        return PUNCTUATION[c] != -1;
    }

    private static boolean isText(char c) {
        if (c == 9 || c == 10 || c == 13) {
            return true;
        }
        return c >= ' ' && c <= '~';
    }
}

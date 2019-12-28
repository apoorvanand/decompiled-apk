package com.google.zxing.datamatrix.decoder;

import com.google.common.base.Ascii;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import kotlin.text.Typography;

final class DecodedBitStreamParser {
    private static final char[] C40_BASIC_SET_CHARS = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] C40_SHIFT2_SET_CHARS = {'!', Typography.quote, '#', Typography.dollar, '%', Typography.amp, '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', Typography.less, '=', Typography.greater, '?', '@', '[', '\\', ']', '^', '_'};
    private static final char[] TEXT_BASIC_SET_CHARS = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] TEXT_SHIFT2_SET_CHARS = C40_SHIFT2_SET_CHARS;
    private static final char[] TEXT_SHIFT3_SET_CHARS = {'`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '{', '|', '}', '~', Ascii.MAX};

    private enum Mode {
        PAD_ENCODE,
        ASCII_ENCODE,
        C40_ENCODE,
        TEXT_ENCODE,
        ANSIX12_ENCODE,
        EDIFACT_ENCODE,
        BASE256_ENCODE
    }

    private DecodedBitStreamParser() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x001e  */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x0023  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.zxing.common.DecoderResult a(byte[] r6) {
        /*
            com.google.zxing.common.BitSource r0 = new com.google.zxing.common.BitSource
            r0.<init>(r6)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r2 = 100
            r1.<init>(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r3 = 0
            r2.<init>(r3)
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 1
            r3.<init>(r4)
            com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ASCII_ENCODE
        L_0x001a:
            com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r5 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ASCII_ENCODE
            if (r4 != r5) goto L_0x0023
            com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r4 = decodeAsciiSegment(r0, r1, r2)
            goto L_0x0048
        L_0x0023:
            int[] r5 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.AnonymousClass1.a
            int r4 = r4.ordinal()
            r4 = r5[r4]
            switch(r4) {
                case 1: goto L_0x0043;
                case 2: goto L_0x003f;
                case 3: goto L_0x003b;
                case 4: goto L_0x0037;
                case 5: goto L_0x0033;
                default: goto L_0x002e;
            }
        L_0x002e:
            com.google.zxing.FormatException r6 = com.google.zxing.FormatException.getFormatInstance()
            throw r6
        L_0x0033:
            decodeBase256Segment(r0, r1, r3)
            goto L_0x0046
        L_0x0037:
            decodeEdifactSegment(r0, r1)
            goto L_0x0046
        L_0x003b:
            decodeAnsiX12Segment(r0, r1)
            goto L_0x0046
        L_0x003f:
            decodeTextSegment(r0, r1)
            goto L_0x0046
        L_0x0043:
            decodeC40Segment(r0, r1)
        L_0x0046:
            com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.ASCII_ENCODE
        L_0x0048:
            com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$Mode r5 = com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.Mode.PAD_ENCODE
            if (r4 == r5) goto L_0x0052
            int r5 = r0.available()
            if (r5 > 0) goto L_0x001a
        L_0x0052:
            int r0 = r2.length()
            if (r0 <= 0) goto L_0x005b
            r1.append(r2)
        L_0x005b:
            com.google.zxing.common.DecoderResult r0 = new com.google.zxing.common.DecoderResult
            java.lang.String r1 = r1.toString()
            boolean r2 = r3.isEmpty()
            r4 = 0
            if (r2 == 0) goto L_0x0069
            r3 = r4
        L_0x0069:
            r0.<init>(r6, r1, r3, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.a(byte[]):com.google.zxing.common.DecoderResult");
    }

    private static void decodeAnsiX12Segment(BitSource bitSource, StringBuilder sb) {
        int readBits;
        int i;
        char c;
        int[] iArr = new int[3];
        while (bitSource.available() != 8 && (readBits = bitSource.readBits(8)) != 254) {
            parseTwoBytes(readBits, bitSource.readBits(8), iArr);
            for (int i2 = 0; i2 < 3; i2++) {
                int i3 = iArr[i2];
                if (i3 == 0) {
                    c = 13;
                } else if (i3 == 1) {
                    c = '*';
                } else if (i3 == 2) {
                    c = Typography.greater;
                } else if (i3 == 3) {
                    c = ' ';
                } else {
                    if (i3 < 14) {
                        i = i3 + 44;
                    } else if (i3 < 40) {
                        i = i3 + 51;
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                    c = (char) i;
                }
                sb.append(c);
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static Mode decodeAsciiSegment(BitSource bitSource, StringBuilder sb, StringBuilder sb2) {
        String str;
        boolean z = false;
        do {
            int readBits = bitSource.readBits(8);
            if (readBits == 0) {
                throw FormatException.getFormatInstance();
            } else if (readBits <= 128) {
                if (z) {
                    readBits += 128;
                }
                sb.append((char) (readBits - 1));
                return Mode.ASCII_ENCODE;
            } else if (readBits == 129) {
                return Mode.PAD_ENCODE;
            } else {
                if (readBits <= 229) {
                    int i = readBits - 130;
                    if (i < 10) {
                        sb.append('0');
                    }
                    sb.append(i);
                } else if (readBits == 230) {
                    return Mode.C40_ENCODE;
                } else {
                    if (readBits == 231) {
                        return Mode.BASE256_ENCODE;
                    }
                    if (readBits == 232) {
                        sb.append(29);
                    } else if (!(readBits == 233 || readBits == 234)) {
                        if (readBits == 235) {
                            z = true;
                        } else {
                            if (readBits == 236) {
                                str = "[)>\u001e05\u001d";
                            } else if (readBits == 237) {
                                str = "[)>\u001e06\u001d";
                            } else if (readBits == 238) {
                                return Mode.ANSIX12_ENCODE;
                            } else {
                                if (readBits == 239) {
                                    return Mode.TEXT_ENCODE;
                                }
                                if (readBits == 240) {
                                    return Mode.EDIFACT_ENCODE;
                                }
                                if (!(readBits == 241 || readBits < 242 || (readBits == 254 && bitSource.available() == 0))) {
                                    throw FormatException.getFormatInstance();
                                }
                            }
                            sb.append(str);
                            sb2.insert(0, "\u001e\u0004");
                        }
                    }
                }
            }
        } while (bitSource.available() > 0);
        return Mode.ASCII_ENCODE;
    }

    private static void decodeBase256Segment(BitSource bitSource, StringBuilder sb, Collection<byte[]> collection) {
        int byteOffset = bitSource.getByteOffset() + 1;
        int i = byteOffset + 1;
        int unrandomize255State = unrandomize255State(bitSource.readBits(8), byteOffset);
        if (unrandomize255State == 0) {
            unrandomize255State = bitSource.available() / 8;
        } else if (unrandomize255State >= 250) {
            unrandomize255State = ((unrandomize255State - 249) * 250) + unrandomize255State(bitSource.readBits(8), i);
            i++;
        }
        if (unrandomize255State >= 0) {
            byte[] bArr = new byte[unrandomize255State];
            int i2 = 0;
            while (i2 < unrandomize255State) {
                if (bitSource.available() >= 8) {
                    bArr[i2] = (byte) unrandomize255State(bitSource.readBits(8), i);
                    i2++;
                    i++;
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
            collection.add(bArr);
            try {
                sb.append(new String(bArr, "ISO8859_1"));
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException("Platform does not support required encoding: " + e);
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0031, code lost:
        r10.append((char) r6);
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
        r10.append((char) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006b, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0086, code lost:
        r3 = r3 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void decodeC40Segment(com.google.zxing.common.BitSource r9, java.lang.StringBuilder r10) {
        /*
            r0 = 3
            int[] r1 = new int[r0]
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0006:
            int r5 = r9.available()
            r6 = 8
            if (r5 != r6) goto L_0x000f
            return
        L_0x000f:
            int r5 = r9.readBits(r6)
            r7 = 254(0xfe, float:3.56E-43)
            if (r5 != r7) goto L_0x0018
            return
        L_0x0018:
            int r6 = r9.readBits(r6)
            parseTwoBytes(r5, r6, r1)
            r5 = r3
            r3 = 0
        L_0x0021:
            if (r3 >= r0) goto L_0x008e
            r6 = r1[r3]
            switch(r4) {
                case 0: goto L_0x006d;
                case 1: goto L_0x0066;
                case 2: goto L_0x003e;
                case 3: goto L_0x002d;
                default: goto L_0x0028;
            }
        L_0x0028:
            com.google.zxing.FormatException r9 = com.google.zxing.FormatException.getFormatInstance()
            throw r9
        L_0x002d:
            if (r5 == 0) goto L_0x0037
            int r6 = r6 + 224
        L_0x0031:
            char r4 = (char) r6
            r10.append(r4)
            r5 = 0
            goto L_0x006b
        L_0x0037:
            int r6 = r6 + 96
        L_0x0039:
            char r4 = (char) r6
            r10.append(r4)
            goto L_0x006b
        L_0x003e:
            char[] r4 = C40_SHIFT2_SET_CHARS
            int r7 = r4.length
            if (r6 >= r7) goto L_0x004f
            char r4 = r4[r6]
            if (r5 == 0) goto L_0x0055
            int r4 = r4 + 128
            char r4 = (char) r4
            r10.append(r4)
            r4 = 0
            goto L_0x005f
        L_0x004f:
            r4 = 27
            if (r6 != r4) goto L_0x005a
            r4 = 29
        L_0x0055:
            r10.append(r4)
            r4 = r5
            goto L_0x005f
        L_0x005a:
            r4 = 30
            if (r6 != r4) goto L_0x0061
            r4 = 1
        L_0x005f:
            r5 = r4
            goto L_0x006b
        L_0x0061:
            com.google.zxing.FormatException r9 = com.google.zxing.FormatException.getFormatInstance()
            throw r9
        L_0x0066:
            if (r5 == 0) goto L_0x0039
            int r6 = r6 + 128
            goto L_0x0031
        L_0x006b:
            r4 = 0
            goto L_0x0086
        L_0x006d:
            if (r6 >= r0) goto L_0x0072
            int r4 = r6 + 1
            goto L_0x0086
        L_0x0072:
            char[] r7 = C40_BASIC_SET_CHARS
            int r8 = r7.length
            if (r6 >= r8) goto L_0x0089
            char r6 = r7[r6]
            if (r5 == 0) goto L_0x0083
            int r6 = r6 + 128
            char r5 = (char) r6
            r10.append(r5)
            r5 = 0
            goto L_0x0086
        L_0x0083:
            r10.append(r6)
        L_0x0086:
            int r3 = r3 + 1
            goto L_0x0021
        L_0x0089:
            com.google.zxing.FormatException r9 = com.google.zxing.FormatException.getFormatInstance()
            throw r9
        L_0x008e:
            int r3 = r9.available()
            if (r3 > 0) goto L_0x0095
            return
        L_0x0095:
            r3 = r5
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.decodeC40Segment(com.google.zxing.common.BitSource, java.lang.StringBuilder):void");
    }

    private static void decodeEdifactSegment(BitSource bitSource, StringBuilder sb) {
        while (bitSource.available() > 16) {
            for (int i = 0; i < 4; i++) {
                int readBits = bitSource.readBits(6);
                if (readBits == 31) {
                    int bitOffset = 8 - bitSource.getBitOffset();
                    if (bitOffset != 8) {
                        bitSource.readBits(bitOffset);
                        return;
                    }
                    return;
                }
                if ((readBits & 32) == 0) {
                    readBits |= 64;
                }
                sb.append((char) readBits);
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0039, code lost:
        r10.append(r4);
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003e, code lost:
        r10.append(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0077, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0092, code lost:
        r3 = r3 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void decodeTextSegment(com.google.zxing.common.BitSource r9, java.lang.StringBuilder r10) {
        /*
            r0 = 3
            int[] r1 = new int[r0]
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0006:
            int r5 = r9.available()
            r6 = 8
            if (r5 != r6) goto L_0x000f
            return
        L_0x000f:
            int r5 = r9.readBits(r6)
            r7 = 254(0xfe, float:3.56E-43)
            if (r5 != r7) goto L_0x0018
            return
        L_0x0018:
            int r6 = r9.readBits(r6)
            parseTwoBytes(r5, r6, r1)
            r5 = r3
            r3 = 0
        L_0x0021:
            if (r3 >= r0) goto L_0x009a
            r6 = r1[r3]
            switch(r4) {
                case 0: goto L_0x0079;
                case 1: goto L_0x006f;
                case 2: goto L_0x0047;
                case 3: goto L_0x002d;
                default: goto L_0x0028;
            }
        L_0x0028:
            com.google.zxing.FormatException r9 = com.google.zxing.FormatException.getFormatInstance()
            throw r9
        L_0x002d:
            char[] r4 = TEXT_SHIFT3_SET_CHARS
            int r7 = r4.length
            if (r6 >= r7) goto L_0x0042
            char r4 = r4[r6]
            if (r5 == 0) goto L_0x003e
            int r4 = r4 + 128
            char r4 = (char) r4
        L_0x0039:
            r10.append(r4)
            r5 = 0
            goto L_0x0077
        L_0x003e:
            r10.append(r4)
            goto L_0x0077
        L_0x0042:
            com.google.zxing.FormatException r9 = com.google.zxing.FormatException.getFormatInstance()
            throw r9
        L_0x0047:
            char[] r4 = TEXT_SHIFT2_SET_CHARS
            int r7 = r4.length
            if (r6 >= r7) goto L_0x0058
            char r4 = r4[r6]
            if (r5 == 0) goto L_0x005e
            int r4 = r4 + 128
            char r4 = (char) r4
            r10.append(r4)
            r4 = 0
            goto L_0x0068
        L_0x0058:
            r4 = 27
            if (r6 != r4) goto L_0x0063
            r4 = 29
        L_0x005e:
            r10.append(r4)
            r4 = r5
            goto L_0x0068
        L_0x0063:
            r4 = 30
            if (r6 != r4) goto L_0x006a
            r4 = 1
        L_0x0068:
            r5 = r4
            goto L_0x0077
        L_0x006a:
            com.google.zxing.FormatException r9 = com.google.zxing.FormatException.getFormatInstance()
            throw r9
        L_0x006f:
            if (r5 == 0) goto L_0x0075
            int r6 = r6 + 128
            char r4 = (char) r6
            goto L_0x0039
        L_0x0075:
            char r4 = (char) r6
            goto L_0x003e
        L_0x0077:
            r4 = 0
            goto L_0x0092
        L_0x0079:
            if (r6 >= r0) goto L_0x007e
            int r4 = r6 + 1
            goto L_0x0092
        L_0x007e:
            char[] r7 = TEXT_BASIC_SET_CHARS
            int r8 = r7.length
            if (r6 >= r8) goto L_0x0095
            char r6 = r7[r6]
            if (r5 == 0) goto L_0x008f
            int r6 = r6 + 128
            char r5 = (char) r6
            r10.append(r5)
            r5 = 0
            goto L_0x0092
        L_0x008f:
            r10.append(r6)
        L_0x0092:
            int r3 = r3 + 1
            goto L_0x0021
        L_0x0095:
            com.google.zxing.FormatException r9 = com.google.zxing.FormatException.getFormatInstance()
            throw r9
        L_0x009a:
            int r3 = r9.available()
            if (r3 > 0) goto L_0x00a1
            return
        L_0x00a1:
            r3 = r5
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser.decodeTextSegment(com.google.zxing.common.BitSource, java.lang.StringBuilder):void");
    }

    private static void parseTwoBytes(int i, int i2, int[] iArr) {
        int i3 = ((i << 8) + i2) - 1;
        int i4 = i3 / 1600;
        iArr[0] = i4;
        int i5 = i3 - (i4 * 1600);
        int i6 = i5 / 40;
        iArr[1] = i6;
        iArr[2] = i5 - (i6 * 40);
    }

    private static int unrandomize255State(int i, int i2) {
        int i3 = i - (((i2 * 149) % 255) + 1);
        return i3 >= 0 ? i3 : i3 + 256;
    }
}

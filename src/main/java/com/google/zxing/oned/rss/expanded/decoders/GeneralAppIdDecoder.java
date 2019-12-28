package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitArray;
import kotlin.text.Typography;

final class GeneralAppIdDecoder {
    private final StringBuilder buffer = new StringBuilder();
    private final CurrentParsingState current = new CurrentParsingState();
    private final BitArray information;

    GeneralAppIdDecoder(BitArray bitArray) {
        this.information = bitArray;
    }

    static int a(BitArray bitArray, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if (bitArray.get(i + i4)) {
                i3 |= 1 << ((i2 - i4) - 1);
            }
        }
        return i3;
    }

    private DecodedChar decodeAlphanumeric(int i) {
        char c;
        int a = a(i, 5);
        if (a == 15) {
            return new DecodedChar(i + 5, Typography.dollar);
        }
        if (a >= 5 && a < 15) {
            return new DecodedChar(i + 5, (char) ((a + 48) - 5));
        }
        int a2 = a(i, 6);
        if (a2 >= 32 && a2 < 58) {
            return new DecodedChar(i + 6, (char) (a2 + 33));
        }
        switch (a2) {
            case 58:
                c = '*';
                break;
            case 59:
                c = ',';
                break;
            case 60:
                c = '-';
                break;
            case 61:
                c = '.';
                break;
            case 62:
                c = '/';
                break;
            default:
                throw new IllegalStateException("Decoding invalid alphanumeric value: " + a2);
        }
        return new DecodedChar(i + 6, c);
    }

    private DecodedChar decodeIsoIec646(int i) {
        char c;
        int a = a(i, 5);
        if (a == 15) {
            return new DecodedChar(i + 5, Typography.dollar);
        }
        if (a >= 5 && a < 15) {
            return new DecodedChar(i + 5, (char) ((a + 48) - 5));
        }
        int a2 = a(i, 7);
        if (a2 >= 64 && a2 < 90) {
            return new DecodedChar(i + 7, (char) (a2 + 1));
        }
        if (a2 >= 90 && a2 < 116) {
            return new DecodedChar(i + 7, (char) (a2 + 7));
        }
        switch (a(i, 8)) {
            case 232:
                c = '!';
                break;
            case 233:
                c = Typography.quote;
                break;
            case 234:
                c = '%';
                break;
            case 235:
                c = Typography.amp;
                break;
            case 236:
                c = '\'';
                break;
            case 237:
                c = '(';
                break;
            case 238:
                c = ')';
                break;
            case 239:
                c = '*';
                break;
            case 240:
                c = '+';
                break;
            case 241:
                c = ',';
                break;
            case 242:
                c = '-';
                break;
            case 243:
                c = '.';
                break;
            case 244:
                c = '/';
                break;
            case 245:
                c = ':';
                break;
            case 246:
                c = ';';
                break;
            case 247:
                c = Typography.less;
                break;
            case 248:
                c = '=';
                break;
            case 249:
                c = Typography.greater;
                break;
            case 250:
                c = '?';
                break;
            case 251:
                c = '_';
                break;
            case 252:
                c = ' ';
                break;
            default:
                throw FormatException.getFormatInstance();
        }
        return new DecodedChar(i + 8, c);
    }

    private DecodedNumeric decodeNumeric(int i) {
        int i2 = i + 7;
        if (i2 > this.information.getSize()) {
            int a = a(i, 4);
            return a == 0 ? new DecodedNumeric(this.information.getSize(), 10, 10) : new DecodedNumeric(this.information.getSize(), a - 1, 10);
        }
        int a2 = a(i, 7) - 8;
        return new DecodedNumeric(i2, a2 / 11, a2 % 11);
    }

    private boolean isAlphaOr646ToNumericLatch(int i) {
        int i2 = i + 3;
        if (i2 > this.information.getSize()) {
            return false;
        }
        while (i < i2) {
            if (this.information.get(i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    private boolean isAlphaTo646ToAlphaLatch(int i) {
        int i2;
        if (i + 1 > this.information.getSize()) {
            return false;
        }
        int i3 = 0;
        while (i3 < 5 && (i2 = i3 + i) < this.information.getSize()) {
            if (i3 == 2) {
                if (!this.information.get(i + 2)) {
                    return false;
                }
            } else if (this.information.get(i2)) {
                return false;
            }
            i3++;
        }
        return true;
    }

    private boolean isNumericToAlphaNumericLatch(int i) {
        int i2;
        if (i + 1 > this.information.getSize()) {
            return false;
        }
        int i3 = 0;
        while (i3 < 4 && (i2 = i3 + i) < this.information.getSize()) {
            if (this.information.get(i2)) {
                return false;
            }
            i3++;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0024, code lost:
        r6 = a(r6, 6);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isStillAlpha(int r6) {
        /*
            r5 = this;
            int r0 = r6 + 5
            com.google.zxing.common.BitArray r1 = r5.information
            int r1 = r1.getSize()
            r2 = 0
            if (r0 <= r1) goto L_0x000c
            return r2
        L_0x000c:
            r0 = 5
            int r1 = r5.a((int) r6, (int) r0)
            r3 = 1
            r4 = 16
            if (r1 < r0) goto L_0x0019
            if (r1 >= r4) goto L_0x0019
            return r3
        L_0x0019:
            int r0 = r6 + 6
            com.google.zxing.common.BitArray r1 = r5.information
            int r1 = r1.getSize()
            if (r0 <= r1) goto L_0x0024
            return r2
        L_0x0024:
            r0 = 6
            int r6 = r5.a((int) r6, (int) r0)
            if (r6 < r4) goto L_0x0030
            r0 = 63
            if (r6 >= r0) goto L_0x0030
            return r3
        L_0x0030:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.decoders.GeneralAppIdDecoder.isStillAlpha(int):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003d, code lost:
        r5 = a(r5, 8);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isStillIsoIec646(int r5) {
        /*
            r4 = this;
            int r0 = r5 + 5
            com.google.zxing.common.BitArray r1 = r4.information
            int r1 = r1.getSize()
            r2 = 0
            if (r0 <= r1) goto L_0x000c
            return r2
        L_0x000c:
            r0 = 5
            int r1 = r4.a((int) r5, (int) r0)
            r3 = 1
            if (r1 < r0) goto L_0x0019
            r0 = 16
            if (r1 >= r0) goto L_0x0019
            return r3
        L_0x0019:
            int r0 = r5 + 7
            com.google.zxing.common.BitArray r1 = r4.information
            int r1 = r1.getSize()
            if (r0 <= r1) goto L_0x0024
            return r2
        L_0x0024:
            r0 = 7
            int r0 = r4.a((int) r5, (int) r0)
            r1 = 64
            if (r0 < r1) goto L_0x0032
            r1 = 116(0x74, float:1.63E-43)
            if (r0 >= r1) goto L_0x0032
            return r3
        L_0x0032:
            int r0 = r5 + 8
            com.google.zxing.common.BitArray r1 = r4.information
            int r1 = r1.getSize()
            if (r0 <= r1) goto L_0x003d
            return r2
        L_0x003d:
            r0 = 8
            int r5 = r4.a((int) r5, (int) r0)
            r0 = 232(0xe8, float:3.25E-43)
            if (r5 < r0) goto L_0x004c
            r0 = 253(0xfd, float:3.55E-43)
            if (r5 >= r0) goto L_0x004c
            return r3
        L_0x004c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.rss.expanded.decoders.GeneralAppIdDecoder.isStillIsoIec646(int):boolean");
    }

    private boolean isStillNumeric(int i) {
        if (i + 7 > this.information.getSize()) {
            return i + 4 <= this.information.getSize();
        }
        int i2 = i;
        while (true) {
            int i3 = i + 3;
            if (i2 >= i3) {
                return this.information.get(i3);
            }
            if (this.information.get(i2)) {
                return true;
            }
            i2++;
        }
    }

    private BlockParsedResult parseAlphaBlock() {
        while (isStillAlpha(this.current.a())) {
            DecodedChar decodeAlphanumeric = decodeAlphanumeric(this.current.a());
            this.current.a(decodeAlphanumeric.e());
            if (decodeAlphanumeric.b()) {
                return new BlockParsedResult(new DecodedInformation(this.current.a(), this.buffer.toString()), true);
            }
            this.buffer.append(decodeAlphanumeric.a());
        }
        if (isAlphaOr646ToNumericLatch(this.current.a())) {
            this.current.b(3);
            this.current.d();
        } else if (isAlphaTo646ToAlphaLatch(this.current.a())) {
            if (this.current.a() + 5 < this.information.getSize()) {
                this.current.b(5);
            } else {
                this.current.a(this.information.getSize());
            }
            this.current.f();
        }
        return new BlockParsedResult(false);
    }

    private DecodedInformation parseBlocks() {
        BlockParsedResult parseAlphaBlock;
        boolean b;
        do {
            int a = this.current.a();
            parseAlphaBlock = this.current.b() ? parseAlphaBlock() : this.current.c() ? parseIsoIec646Block() : parseNumericBlock();
            b = parseAlphaBlock.b();
            if (!(a != this.current.a()) && !b) {
                break;
            }
        } while (!b);
        return parseAlphaBlock.a();
    }

    private BlockParsedResult parseIsoIec646Block() {
        while (isStillIsoIec646(this.current.a())) {
            DecodedChar decodeIsoIec646 = decodeIsoIec646(this.current.a());
            this.current.a(decodeIsoIec646.e());
            if (decodeIsoIec646.b()) {
                return new BlockParsedResult(new DecodedInformation(this.current.a(), this.buffer.toString()), true);
            }
            this.buffer.append(decodeIsoIec646.a());
        }
        if (isAlphaOr646ToNumericLatch(this.current.a())) {
            this.current.b(3);
            this.current.d();
        } else if (isAlphaTo646ToAlphaLatch(this.current.a())) {
            if (this.current.a() + 5 < this.information.getSize()) {
                this.current.b(5);
            } else {
                this.current.a(this.information.getSize());
            }
            this.current.e();
        }
        return new BlockParsedResult(false);
    }

    private BlockParsedResult parseNumericBlock() {
        while (isStillNumeric(this.current.a())) {
            DecodedNumeric decodeNumeric = decodeNumeric(this.current.a());
            this.current.a(decodeNumeric.e());
            if (decodeNumeric.c()) {
                return new BlockParsedResult(decodeNumeric.d() ? new DecodedInformation(this.current.a(), this.buffer.toString()) : new DecodedInformation(this.current.a(), this.buffer.toString(), decodeNumeric.b()), true);
            }
            this.buffer.append(decodeNumeric.a());
            if (decodeNumeric.d()) {
                return new BlockParsedResult(new DecodedInformation(this.current.a(), this.buffer.toString()), true);
            }
            this.buffer.append(decodeNumeric.b());
        }
        if (isNumericToAlphaNumericLatch(this.current.a())) {
            this.current.e();
            this.current.b(4);
        }
        return new BlockParsedResult(false);
    }

    /* access modifiers changed from: package-private */
    public int a(int i, int i2) {
        return a(this.information, i, i2);
    }

    /* access modifiers changed from: package-private */
    public DecodedInformation a(int i, String str) {
        this.buffer.setLength(0);
        if (str != null) {
            this.buffer.append(str);
        }
        this.current.a(i);
        DecodedInformation parseBlocks = parseBlocks();
        return (parseBlocks == null || !parseBlocks.b()) ? new DecodedInformation(this.current.a(), this.buffer.toString()) : new DecodedInformation(this.current.a(), this.buffer.toString(), parseBlocks.c());
    }

    /* access modifiers changed from: package-private */
    public String a(StringBuilder sb, int i) {
        String str = null;
        while (true) {
            DecodedInformation a = a(i, str);
            String a2 = FieldParser.a(a.a());
            if (a2 != null) {
                sb.append(a2);
            }
            String valueOf = a.b() ? String.valueOf(a.c()) : null;
            if (i == a.e()) {
                return sb.toString();
            }
            i = a.e();
            str = valueOf;
        }
    }
}

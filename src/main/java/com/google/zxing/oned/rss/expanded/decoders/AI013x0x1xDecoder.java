package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class AI013x0x1xDecoder extends AI01weightDecoder {
    private static final int DATE_SIZE = 16;
    private static final int HEADER_SIZE = 8;
    private static final int WEIGHT_SIZE = 20;
    private final String dateCode;
    private final String firstAIdigits;

    AI013x0x1xDecoder(BitArray bitArray, String str, String str2) {
        super(bitArray);
        this.dateCode = str2;
        this.firstAIdigits = str;
    }

    private void encodeCompressedDate(StringBuilder sb, int i) {
        int a = b().a(i, 16);
        if (a != 38400) {
            sb.append('(');
            sb.append(this.dateCode);
            sb.append(')');
            int i2 = a % 32;
            int i3 = a / 32;
            int i4 = (i3 % 12) + 1;
            int i5 = i3 / 12;
            if (i5 / 10 == 0) {
                sb.append('0');
            }
            sb.append(i5);
            if (i4 / 10 == 0) {
                sb.append('0');
            }
            sb.append(i4);
            if (i2 / 10 == 0) {
                sb.append('0');
            }
            sb.append(i2);
        }
    }

    /* access modifiers changed from: protected */
    public int a(int i) {
        return i % 100000;
    }

    /* access modifiers changed from: protected */
    public void a(StringBuilder sb, int i) {
        sb.append('(');
        sb.append(this.firstAIdigits);
        sb.append(i / 100000);
        sb.append(')');
    }

    public String parseInformation() {
        if (a().getSize() == 84) {
            StringBuilder sb = new StringBuilder();
            b(sb, 8);
            b(sb, 48, 20);
            encodeCompressedDate(sb, 68);
            return sb.toString();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}

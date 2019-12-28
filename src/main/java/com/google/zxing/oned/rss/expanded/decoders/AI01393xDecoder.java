package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class AI01393xDecoder extends AI01decoder {
    private static final int FIRST_THREE_DIGITS_SIZE = 10;
    private static final int HEADER_SIZE = 8;
    private static final int LAST_DIGIT_SIZE = 2;

    AI01393xDecoder(BitArray bitArray) {
        super(bitArray);
    }

    public String parseInformation() {
        if (a().getSize() >= 48) {
            StringBuilder sb = new StringBuilder();
            b(sb, 8);
            int a = b().a(48, 2);
            sb.append("(393");
            sb.append(a);
            sb.append(')');
            int a2 = b().a(50, 10);
            if (a2 / 100 == 0) {
                sb.append('0');
            }
            if (a2 / 10 == 0) {
                sb.append('0');
            }
            sb.append(a2);
            sb.append(b().a(60, (String) null).a());
            return sb.toString();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class AI01392xDecoder extends AI01decoder {
    private static final int HEADER_SIZE = 8;
    private static final int LAST_DIGIT_SIZE = 2;

    AI01392xDecoder(BitArray bitArray) {
        super(bitArray);
    }

    public String parseInformation() {
        if (a().getSize() >= 48) {
            StringBuilder sb = new StringBuilder();
            b(sb, 8);
            int a = b().a(48, 2);
            sb.append("(392");
            sb.append(a);
            sb.append(')');
            sb.append(b().a(50, (String) null).a());
            return sb.toString();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}

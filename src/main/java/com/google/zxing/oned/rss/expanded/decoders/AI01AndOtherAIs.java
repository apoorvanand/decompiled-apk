package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

final class AI01AndOtherAIs extends AI01decoder {
    private static final int HEADER_SIZE = 4;

    AI01AndOtherAIs(BitArray bitArray) {
        super(bitArray);
    }

    public String parseInformation() {
        StringBuilder sb = new StringBuilder();
        sb.append("(01)");
        int length = sb.length();
        sb.append(b().a(4, 4));
        a(sb, 8, length);
        return b().a(sb, 48);
    }
}

package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

final class AI01320xDecoder extends AI013x0xDecoder {
    AI01320xDecoder(BitArray bitArray) {
        super(bitArray);
    }

    /* access modifiers changed from: protected */
    public int a(int i) {
        return i < 10000 ? i : i - 10000;
    }

    /* access modifiers changed from: protected */
    public void a(StringBuilder sb, int i) {
        sb.append(i < 10000 ? "(3202)" : "(3203)");
    }
}

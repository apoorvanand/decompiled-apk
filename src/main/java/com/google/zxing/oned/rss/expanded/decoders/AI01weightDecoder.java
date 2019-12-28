package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

abstract class AI01weightDecoder extends AI01decoder {
    AI01weightDecoder(BitArray bitArray) {
        super(bitArray);
    }

    /* access modifiers changed from: protected */
    public abstract int a(int i);

    /* access modifiers changed from: protected */
    public abstract void a(StringBuilder sb, int i);

    /* access modifiers changed from: package-private */
    public final void b(StringBuilder sb, int i, int i2) {
        int a = b().a(i, i2);
        a(sb, a);
        int a2 = a(a);
        int i3 = 100000;
        for (int i4 = 0; i4 < 5; i4++) {
            if (a2 / i3 == 0) {
                sb.append('0');
            }
            i3 /= 10;
        }
        sb.append(a2);
    }
}

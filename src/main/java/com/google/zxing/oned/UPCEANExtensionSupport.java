package com.google.zxing.oned;

import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.BitArray;

final class UPCEANExtensionSupport {
    private static final int[] EXTENSION_START_PATTERN = {1, 1, 2};
    private final UPCEANExtension5Support fiveSupport = new UPCEANExtension5Support();
    private final UPCEANExtension2Support twoSupport = new UPCEANExtension2Support();

    UPCEANExtensionSupport() {
    }

    /* access modifiers changed from: package-private */
    public Result a(int i, BitArray bitArray, int i2) {
        int[] a = UPCEANReader.a(bitArray, i2, false, EXTENSION_START_PATTERN);
        try {
            return this.fiveSupport.a(i, bitArray, a);
        } catch (ReaderException unused) {
            return this.twoSupport.a(i, bitArray, a);
        }
    }
}
package com.google.zxing.common.reedsolomon;

import java.util.ArrayList;
import java.util.List;

public final class ReedSolomonEncoder {
    private final List<GenericGFPoly> cachedGenerators = new ArrayList();
    private final GenericGF field;

    public ReedSolomonEncoder(GenericGF genericGF) {
        this.field = genericGF;
        this.cachedGenerators.add(new GenericGFPoly(genericGF, new int[]{1}));
    }

    private GenericGFPoly buildGenerator(int i) {
        if (i >= this.cachedGenerators.size()) {
            List<GenericGFPoly> list = this.cachedGenerators;
            GenericGFPoly genericGFPoly = list.get(list.size() - 1);
            for (int size = this.cachedGenerators.size(); size <= i; size++) {
                GenericGF genericGF = this.field;
                genericGFPoly = genericGFPoly.b(new GenericGFPoly(genericGF, new int[]{1, genericGF.a((size - 1) + genericGF.getGeneratorBase())}));
                this.cachedGenerators.add(genericGFPoly);
            }
        }
        return this.cachedGenerators.get(i);
    }

    public void encode(int[] iArr, int i) {
        if (i != 0) {
            int length = iArr.length - i;
            if (length > 0) {
                GenericGFPoly buildGenerator = buildGenerator(i);
                int[] iArr2 = new int[length];
                System.arraycopy(iArr, 0, iArr2, 0, length);
                int[] a = new GenericGFPoly(this.field, iArr2).a(i, 1).c(buildGenerator)[1].a();
                int length2 = i - a.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    iArr[length + i2] = 0;
                }
                System.arraycopy(a, 0, iArr, length + length2, a.length);
                return;
            }
            throw new IllegalArgumentException("No data bytes provided");
        }
        throw new IllegalArgumentException("No error correction bytes");
    }
}

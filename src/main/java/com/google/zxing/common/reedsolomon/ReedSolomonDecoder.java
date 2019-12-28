package com.google.zxing.common.reedsolomon;

public final class ReedSolomonDecoder {
    private final GenericGF field;

    public ReedSolomonDecoder(GenericGF genericGF) {
        this.field = genericGF;
    }

    private int[] findErrorLocations(GenericGFPoly genericGFPoly) {
        int b = genericGFPoly.b();
        int i = 0;
        if (b == 1) {
            return new int[]{genericGFPoly.a(1)};
        }
        int[] iArr = new int[b];
        for (int i2 = 1; i2 < this.field.getSize() && i < b; i2++) {
            if (genericGFPoly.b(i2) == 0) {
                iArr[i] = this.field.c(i2);
                i++;
            }
        }
        if (i == b) {
            return iArr;
        }
        throw new ReedSolomonException("Error locator degree does not match number of roots");
    }

    private int[] findErrorMagnitudes(GenericGFPoly genericGFPoly, int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            int c = this.field.c(iArr[i]);
            int i2 = 1;
            for (int i3 = 0; i3 < length; i3++) {
                if (i != i3) {
                    int c2 = this.field.c(iArr[i3], c);
                    i2 = this.field.c(i2, (c2 & 1) == 0 ? c2 | 1 : c2 & -2);
                }
            }
            iArr2[i] = this.field.c(genericGFPoly.b(c), this.field.c(i2));
            if (this.field.getGeneratorBase() != 0) {
                iArr2[i] = this.field.c(iArr2[i], c);
            }
        }
        return iArr2;
    }

    private GenericGFPoly[] runEuclideanAlgorithm(GenericGFPoly genericGFPoly, GenericGFPoly genericGFPoly2, int i) {
        if (genericGFPoly.b() < genericGFPoly2.b()) {
            GenericGFPoly genericGFPoly3 = genericGFPoly2;
            genericGFPoly2 = genericGFPoly;
            genericGFPoly = genericGFPoly3;
        }
        GenericGFPoly a = this.field.a();
        GenericGFPoly b = this.field.b();
        do {
            GenericGFPoly genericGFPoly4 = r11;
            r11 = genericGFPoly;
            genericGFPoly = genericGFPoly4;
            GenericGFPoly genericGFPoly5 = b;
            GenericGFPoly genericGFPoly6 = a;
            a = genericGFPoly5;
            if (genericGFPoly.b() < i / 2) {
                int a2 = a.a(0);
                if (a2 != 0) {
                    int c = this.field.c(a2);
                    return new GenericGFPoly[]{a.c(c), genericGFPoly.c(c)};
                }
                throw new ReedSolomonException("sigmaTilde(0) was zero");
            } else if (!genericGFPoly.c()) {
                GenericGFPoly a3 = this.field.a();
                int c2 = this.field.c(genericGFPoly.a(genericGFPoly.b()));
                while (r11.b() >= genericGFPoly.b() && !r11.c()) {
                    int b2 = r11.b() - genericGFPoly.b();
                    int c3 = this.field.c(r11.a(r11.b()), c2);
                    a3 = a3.a(this.field.a(b2, c3));
                    r11 = r11.a(genericGFPoly.a(b2, c3));
                }
                b = a3.b(a).a(genericGFPoly6);
            } else {
                throw new ReedSolomonException("r_{i-1} was zero");
            }
        } while (r11.b() < genericGFPoly.b());
        throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
    }

    public void decode(int[] iArr, int i) {
        GenericGFPoly genericGFPoly = new GenericGFPoly(this.field, iArr);
        int[] iArr2 = new int[i];
        int i2 = 0;
        boolean z = true;
        for (int i3 = 0; i3 < i; i3++) {
            GenericGF genericGF = this.field;
            int b = genericGFPoly.b(genericGF.a(genericGF.getGeneratorBase() + i3));
            iArr2[(i - 1) - i3] = b;
            if (b != 0) {
                z = false;
            }
        }
        if (!z) {
            GenericGFPoly[] runEuclideanAlgorithm = runEuclideanAlgorithm(this.field.a(i, 1), new GenericGFPoly(this.field, iArr2), i);
            GenericGFPoly genericGFPoly2 = runEuclideanAlgorithm[0];
            GenericGFPoly genericGFPoly3 = runEuclideanAlgorithm[1];
            int[] findErrorLocations = findErrorLocations(genericGFPoly2);
            int[] findErrorMagnitudes = findErrorMagnitudes(genericGFPoly3, findErrorLocations);
            while (i2 < findErrorLocations.length) {
                int length = (iArr.length - 1) - this.field.b(findErrorLocations[i2]);
                if (length >= 0) {
                    iArr[length] = GenericGF.b(iArr[length], findErrorMagnitudes[i2]);
                    i2++;
                } else {
                    throw new ReedSolomonException("Bad error location");
                }
            }
        }
    }
}

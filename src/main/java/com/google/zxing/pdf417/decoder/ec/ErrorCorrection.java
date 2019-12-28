package com.google.zxing.pdf417.decoder.ec;

import com.google.zxing.ChecksumException;

public final class ErrorCorrection {
    private final ModulusGF field = ModulusGF.PDF417_GF;

    private int[] findErrorLocations(ModulusPoly modulusPoly) {
        int a = modulusPoly.a();
        int[] iArr = new int[a];
        int i = 0;
        for (int i2 = 1; i2 < this.field.c() && i < a; i2++) {
            if (modulusPoly.b(i2) == 0) {
                iArr[i] = this.field.c(i2);
                i++;
            }
        }
        if (i == a) {
            return iArr;
        }
        throw ChecksumException.getChecksumInstance();
    }

    private int[] findErrorMagnitudes(ModulusPoly modulusPoly, ModulusPoly modulusPoly2, int[] iArr) {
        int a = modulusPoly2.a();
        int[] iArr2 = new int[a];
        for (int i = 1; i <= a; i++) {
            iArr2[a - i] = this.field.d(i, modulusPoly2.a(i));
        }
        ModulusPoly modulusPoly3 = new ModulusPoly(this.field, iArr2);
        int length = iArr.length;
        int[] iArr3 = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            int c = this.field.c(iArr[i2]);
            iArr3[i2] = this.field.d(this.field.c(0, modulusPoly.b(c)), this.field.c(modulusPoly3.b(c)));
        }
        return iArr3;
    }

    private ModulusPoly[] runEuclideanAlgorithm(ModulusPoly modulusPoly, ModulusPoly modulusPoly2, int i) {
        if (modulusPoly.a() < modulusPoly2.a()) {
            ModulusPoly modulusPoly3 = modulusPoly2;
            modulusPoly2 = modulusPoly;
            modulusPoly = modulusPoly3;
        }
        ModulusPoly a = this.field.a();
        ModulusPoly b = this.field.b();
        while (true) {
            ModulusPoly modulusPoly4 = r11;
            r11 = modulusPoly;
            modulusPoly = modulusPoly4;
            ModulusPoly modulusPoly5 = b;
            ModulusPoly modulusPoly6 = a;
            a = modulusPoly5;
            if (modulusPoly.a() < i / 2) {
                int a2 = a.a(0);
                if (a2 != 0) {
                    int c = this.field.c(a2);
                    return new ModulusPoly[]{a.c(c), modulusPoly.c(c)};
                }
                throw ChecksumException.getChecksumInstance();
            } else if (!modulusPoly.b()) {
                ModulusPoly a3 = this.field.a();
                int c2 = this.field.c(modulusPoly.a(modulusPoly.a()));
                while (r11.a() >= modulusPoly.a() && !r11.b()) {
                    int a4 = r11.a() - modulusPoly.a();
                    int d = this.field.d(r11.a(r11.a()), c2);
                    a3 = a3.a(this.field.a(a4, d));
                    r11 = r11.b(modulusPoly.a(a4, d));
                }
                b = a3.c(a).b(modulusPoly6).c();
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
    }

    public int decode(int[] iArr, int i, int[] iArr2) {
        ModulusPoly modulusPoly = new ModulusPoly(this.field, iArr);
        int[] iArr3 = new int[i];
        int i2 = 0;
        boolean z = false;
        for (int i3 = i; i3 > 0; i3--) {
            int b = modulusPoly.b(this.field.a(i3));
            iArr3[i - i3] = b;
            if (b != 0) {
                z = true;
            }
        }
        if (!z) {
            return 0;
        }
        ModulusPoly b2 = this.field.b();
        if (iArr2 != null) {
            ModulusPoly modulusPoly2 = b2;
            for (int length : iArr2) {
                int a = this.field.a((iArr.length - 1) - length);
                ModulusGF modulusGF = this.field;
                modulusPoly2 = modulusPoly2.c(new ModulusPoly(modulusGF, new int[]{modulusGF.c(0, a), 1}));
            }
        }
        ModulusPoly[] runEuclideanAlgorithm = runEuclideanAlgorithm(this.field.a(i, 1), new ModulusPoly(this.field, iArr3), i);
        ModulusPoly modulusPoly3 = runEuclideanAlgorithm[0];
        ModulusPoly modulusPoly4 = runEuclideanAlgorithm[1];
        int[] findErrorLocations = findErrorLocations(modulusPoly3);
        int[] findErrorMagnitudes = findErrorMagnitudes(modulusPoly4, modulusPoly3, findErrorLocations);
        while (i2 < findErrorLocations.length) {
            int length2 = (iArr.length - 1) - this.field.b(findErrorLocations[i2]);
            if (length2 >= 0) {
                iArr[length2] = this.field.c(iArr[length2], findErrorMagnitudes[i2]);
                i2++;
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
        return findErrorLocations.length;
    }
}

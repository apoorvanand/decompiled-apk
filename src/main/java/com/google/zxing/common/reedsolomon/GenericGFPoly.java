package com.google.zxing.common.reedsolomon;

final class GenericGFPoly {
    private final int[] coefficients;
    private final GenericGF field;

    GenericGFPoly(GenericGF genericGF, int[] iArr) {
        if (iArr.length != 0) {
            this.field = genericGF;
            int length = iArr.length;
            if (length <= 1 || iArr[0] != 0) {
                this.coefficients = iArr;
                return;
            }
            int i = 1;
            while (i < length && iArr[i] == 0) {
                i++;
            }
            if (i == length) {
                this.coefficients = new int[]{0};
                return;
            }
            this.coefficients = new int[(length - i)];
            int[] iArr2 = this.coefficients;
            System.arraycopy(iArr, i, iArr2, 0, iArr2.length);
            return;
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public int a(int i) {
        int[] iArr = this.coefficients;
        return iArr[(iArr.length - 1) - i];
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.field.a();
        } else {
            int length = this.coefficients.length;
            int[] iArr = new int[(i + length)];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = this.field.c(this.coefficients[i3], i2);
            }
            return new GenericGFPoly(this.field, iArr);
        }
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly a(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (c()) {
            return genericGFPoly;
        } else {
            if (genericGFPoly.c()) {
                return this;
            }
            int[] iArr = this.coefficients;
            int[] iArr2 = genericGFPoly.coefficients;
            if (iArr.length > iArr2.length) {
                int[] iArr3 = iArr;
                iArr = iArr2;
                iArr2 = iArr3;
            }
            int[] iArr4 = new int[iArr2.length];
            int length = iArr2.length - iArr.length;
            System.arraycopy(iArr2, 0, iArr4, 0, length);
            for (int i = length; i < iArr2.length; i++) {
                iArr4[i] = GenericGF.b(iArr[i - length], iArr2[i]);
            }
            return new GenericGFPoly(this.field, iArr4);
        }
    }

    /* access modifiers changed from: package-private */
    public int[] a() {
        return this.coefficients;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.coefficients.length - 1;
    }

    /* access modifiers changed from: package-private */
    public int b(int i) {
        if (i == 0) {
            return a(0);
        }
        if (i == 1) {
            int i2 = 0;
            for (int b : this.coefficients) {
                i2 = GenericGF.b(i2, b);
            }
            return i2;
        }
        int[] iArr = this.coefficients;
        int i3 = iArr[0];
        int length = iArr.length;
        for (int i4 = 1; i4 < length; i4++) {
            i3 = GenericGF.b(this.field.c(i, i3), this.coefficients[i4]);
        }
        return i3;
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly b(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (c() || genericGFPoly.c()) {
            return this.field.a();
        } else {
            int[] iArr = this.coefficients;
            int length = iArr.length;
            int[] iArr2 = genericGFPoly.coefficients;
            int length2 = iArr2.length;
            int[] iArr3 = new int[((length + length2) - 1)];
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                for (int i3 = 0; i3 < length2; i3++) {
                    int i4 = i + i3;
                    iArr3[i4] = GenericGF.b(iArr3[i4], this.field.c(i2, iArr2[i3]));
                }
            }
            return new GenericGFPoly(this.field, iArr3);
        }
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly c(int i) {
        if (i == 0) {
            return this.field.a();
        }
        if (i == 1) {
            return this;
        }
        int length = this.coefficients.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.field.c(this.coefficients[i2], i);
        }
        return new GenericGFPoly(this.field, iArr);
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return this.coefficients[0] == 0;
    }

    /* access modifiers changed from: package-private */
    public GenericGFPoly[] c(GenericGFPoly genericGFPoly) {
        if (!this.field.equals(genericGFPoly.field)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (!genericGFPoly.c()) {
            GenericGFPoly a = this.field.a();
            int c = this.field.c(genericGFPoly.a(genericGFPoly.b()));
            GenericGFPoly genericGFPoly2 = a;
            GenericGFPoly genericGFPoly3 = this;
            while (genericGFPoly3.b() >= genericGFPoly.b() && !genericGFPoly3.c()) {
                int b = genericGFPoly3.b() - genericGFPoly.b();
                int c2 = this.field.c(genericGFPoly3.a(genericGFPoly3.b()), c);
                GenericGFPoly a2 = genericGFPoly.a(b, c2);
                genericGFPoly2 = genericGFPoly2.a(this.field.a(b, c2));
                genericGFPoly3 = genericGFPoly3.a(a2);
            }
            return new GenericGFPoly[]{genericGFPoly2, genericGFPoly3};
        } else {
            throw new IllegalArgumentException("Divide by 0");
        }
    }

    public String toString() {
        char c;
        StringBuilder sb = new StringBuilder(b() * 8);
        for (int b = b(); b >= 0; b--) {
            int a = a(b);
            if (a != 0) {
                if (a < 0) {
                    sb.append(" - ");
                    a = -a;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (b == 0 || a != 1) {
                    int b2 = this.field.b(a);
                    if (b2 == 0) {
                        c = '1';
                    } else if (b2 == 1) {
                        c = 'a';
                    } else {
                        sb.append("a^");
                        sb.append(b2);
                    }
                    sb.append(c);
                }
                if (b != 0) {
                    if (b == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(b);
                    }
                }
            }
        }
        return sb.toString();
    }
}

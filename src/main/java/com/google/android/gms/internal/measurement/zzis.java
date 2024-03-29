package com.google.android.gms.internal.measurement;

public final class zzis implements Cloneable {
    private static final zzir zzaor = new zzir();
    private int mSize;
    private boolean zzaos;
    private int[] zzaot;
    private zzir[] zzaou;

    zzis() {
        this(10);
    }

    private zzis(int i) {
        this.zzaos = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.zzaot = new int[idealIntArraySize];
        this.zzaou = new zzir[idealIntArraySize];
        this.mSize = 0;
    }

    private static int idealIntArraySize(int i) {
        int i2 = i << 2;
        int i3 = 4;
        while (true) {
            if (i3 >= 32) {
                break;
            }
            int i4 = (1 << i3) - 12;
            if (i2 <= i4) {
                i2 = i4;
                break;
            }
            i3++;
        }
        return i2 / 4;
    }

    private final int zzcn(int i) {
        int i2 = this.mSize - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            int i5 = this.zzaot[i4];
            if (i5 < i) {
                i3 = i4 + 1;
            } else if (i5 <= i) {
                return i4;
            } else {
                i2 = i4 - 1;
            }
        }
        return ~i3;
    }

    /* access modifiers changed from: package-private */
    public final int a() {
        return this.mSize;
    }

    /* access modifiers changed from: package-private */
    public final zzir a(int i) {
        int zzcn = zzcn(i);
        if (zzcn < 0) {
            return null;
        }
        zzir[] zzirArr = this.zzaou;
        if (zzirArr[zzcn] == zzaor) {
            return null;
        }
        return zzirArr[zzcn];
    }

    /* access modifiers changed from: package-private */
    public final void a(int i, zzir zzir) {
        int zzcn = zzcn(i);
        if (zzcn >= 0) {
            this.zzaou[zzcn] = zzir;
            return;
        }
        int i2 = ~zzcn;
        if (i2 < this.mSize) {
            zzir[] zzirArr = this.zzaou;
            if (zzirArr[i2] == zzaor) {
                this.zzaot[i2] = i;
                zzirArr[i2] = zzir;
                return;
            }
        }
        int i3 = this.mSize;
        if (i3 >= this.zzaot.length) {
            int idealIntArraySize = idealIntArraySize(i3 + 1);
            int[] iArr = new int[idealIntArraySize];
            zzir[] zzirArr2 = new zzir[idealIntArraySize];
            int[] iArr2 = this.zzaot;
            System.arraycopy(iArr2, 0, iArr, 0, iArr2.length);
            zzir[] zzirArr3 = this.zzaou;
            System.arraycopy(zzirArr3, 0, zzirArr2, 0, zzirArr3.length);
            this.zzaot = iArr;
            this.zzaou = zzirArr2;
        }
        int i4 = this.mSize;
        if (i4 - i2 != 0) {
            int[] iArr3 = this.zzaot;
            int i5 = i2 + 1;
            System.arraycopy(iArr3, i2, iArr3, i5, i4 - i2);
            zzir[] zzirArr4 = this.zzaou;
            System.arraycopy(zzirArr4, i2, zzirArr4, i5, this.mSize - i2);
        }
        this.zzaot[i2] = i;
        this.zzaou[i2] = zzir;
        this.mSize++;
    }

    /* access modifiers changed from: package-private */
    public final zzir b(int i) {
        return this.zzaou[i];
    }

    public final /* synthetic */ Object clone() {
        int i = this.mSize;
        zzis zzis = new zzis(i);
        System.arraycopy(this.zzaot, 0, zzis.zzaot, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            zzir[] zzirArr = this.zzaou;
            if (zzirArr[i2] != null) {
                zzis.zzaou[i2] = (zzir) zzirArr[i2].clone();
            }
        }
        zzis.mSize = i;
        return zzis;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzis)) {
            return false;
        }
        zzis zzis = (zzis) obj;
        int i = this.mSize;
        if (i != zzis.mSize) {
            return false;
        }
        int[] iArr = this.zzaot;
        int[] iArr2 = zzis.zzaot;
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                z = true;
                break;
            } else if (iArr[i2] != iArr2[i2]) {
                z = false;
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            zzir[] zzirArr = this.zzaou;
            zzir[] zzirArr2 = zzis.zzaou;
            int i3 = this.mSize;
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    z2 = true;
                    break;
                } else if (!zzirArr[i4].equals(zzirArr2[i4])) {
                    z2 = false;
                    break;
                } else {
                    i4++;
                }
            }
            if (z2) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = (((i * 31) + this.zzaot[i2]) * 31) + this.zzaou[i2].hashCode();
        }
        return i;
    }

    public final boolean isEmpty() {
        return this.mSize == 0;
    }
}

package com.google.android.gms.internal.vision;

final class zzes extends zzex {
    private final int zzsb;
    private final int zzsc;

    zzes(byte[] bArr, int i, int i2) {
        super(bArr);
        b(i, i + i2, bArr.length);
        this.zzsb = i;
        this.zzsc = i2;
    }

    /* access modifiers changed from: protected */
    public final void a(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.a, b(), bArr, 0, i3);
    }

    /* access modifiers changed from: protected */
    public final int b() {
        return this.zzsb;
    }

    public final int size() {
        return this.zzsc;
    }

    public final byte zzai(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.a[this.zzsb + i];
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(22);
            sb.append("Index < 0: ");
            sb.append(i);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append("Index > length: ");
        sb2.append(i);
        sb2.append(", ");
        sb2.append(size);
        throw new ArrayIndexOutOfBoundsException(sb2.toString());
    }
}

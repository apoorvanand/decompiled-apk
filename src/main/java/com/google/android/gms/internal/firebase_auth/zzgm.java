package com.google.android.gms.internal.firebase_auth;

final class zzgm extends zzgp {
    private final int zzwa;
    private final int zzwb;

    zzgm(byte[] bArr, int i, int i2) {
        super(bArr);
        b(i, i + i2, bArr.length);
        this.zzwa = i;
        this.zzwb = i2;
    }

    /* access modifiers changed from: package-private */
    public final byte a(int i) {
        return this.a[this.zzwa + i];
    }

    /* access modifiers changed from: protected */
    public final int b() {
        return this.zzwa;
    }

    public final int size() {
        return this.zzwb;
    }

    public final byte zzp(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.a[this.zzwa + i];
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

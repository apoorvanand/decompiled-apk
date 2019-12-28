package com.google.android.gms.internal.firebase_ml;

final class zzrz extends zzse {
    private final int zzbik;
    private final int zzbil;

    zzrz(byte[] bArr, int i, int i2) {
        super(bArr);
        b(i, i + i2, bArr.length);
        this.zzbik = i;
        this.zzbil = i2;
    }

    /* access modifiers changed from: package-private */
    public final byte a(int i) {
        return this.a[this.zzbik + i];
    }

    /* access modifiers changed from: protected */
    public final int b() {
        return this.zzbik;
    }

    public final int size() {
        return this.zzbil;
    }

    public final byte zzcc(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.a[this.zzbik + i];
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

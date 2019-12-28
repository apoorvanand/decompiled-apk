package com.google.android.gms.internal.clearcut;

final class zzbe extends zzbi {
    private final int zzfm;
    private final int zzfn;

    zzbe(byte[] bArr, int i, int i2) {
        super(bArr);
        b(i, i + i2, bArr.length);
        this.zzfm = i;
        this.zzfn = i2;
    }

    /* access modifiers changed from: protected */
    public final int b() {
        return this.zzfm;
    }

    public final int size() {
        return this.zzfn;
    }

    public final byte zzj(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.a[this.zzfm + i];
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

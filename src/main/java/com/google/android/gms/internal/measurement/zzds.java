package com.google.android.gms.internal.measurement;

final class zzds extends zzdz {
    private final int zzadl;
    private final int zzadm;

    zzds(byte[] bArr, int i, int i2) {
        super(bArr);
        b(i, i + i2, bArr.length);
        this.zzadl = i;
        this.zzadm = i2;
    }

    /* access modifiers changed from: package-private */
    public final byte a(int i) {
        return this.a[this.zzadl + i];
    }

    /* access modifiers changed from: protected */
    public final int b() {
        return this.zzadl;
    }

    public final int size() {
        return this.zzadm;
    }

    public final byte zzaq(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.a[this.zzadl + i];
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

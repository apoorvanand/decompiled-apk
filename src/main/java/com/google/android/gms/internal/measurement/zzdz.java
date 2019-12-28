package com.google.android.gms.internal.measurement;

import java.nio.charset.Charset;

class zzdz extends zzdw {
    protected final byte[] a;

    zzdz(byte[] bArr) {
        if (bArr != null) {
            this.a = bArr;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public byte a(int i) {
        return this.a[i];
    }

    /* access modifiers changed from: protected */
    public final int a(int i, int i2, int i3) {
        return zzez.a(i, this.a, b(), i3);
    }

    /* access modifiers changed from: protected */
    public final String a(Charset charset) {
        return new String(this.a, b(), size(), charset);
    }

    /* access modifiers changed from: package-private */
    public final void a(zzdm zzdm) {
        zzdm.zza(this.a, b(), size());
    }

    /* access modifiers changed from: package-private */
    public final boolean a(zzdp zzdp, int i, int i2) {
        if (i2 > zzdp.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 > zzdp.size()) {
            int size2 = zzdp.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzdp instanceof zzdz)) {
            return zzdp.zza(0, i2).equals(zza(0, i2));
        } else {
            zzdz zzdz = (zzdz) zzdp;
            byte[] bArr = this.a;
            byte[] bArr2 = zzdz.a;
            int b = b() + i2;
            int b2 = b();
            int b3 = zzdz.b();
            while (b2 < b) {
                if (bArr[b2] != bArr2[b3]) {
                    return false;
                }
                b2++;
                b3++;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public int b() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdp) || size() != ((zzdp) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzdz)) {
            return obj.equals(this);
        }
        zzdz zzdz = (zzdz) obj;
        int a2 = a();
        int a3 = zzdz.a();
        if (a2 == 0 || a3 == 0 || a2 == a3) {
            return a(zzdz, 0, size());
        }
        return false;
    }

    public int size() {
        return this.a.length;
    }

    public final zzdp zza(int i, int i2) {
        int b = b(0, i2, size());
        return b == 0 ? zzdp.zzadh : new zzds(this.a, b(), b);
    }

    public byte zzaq(int i) {
        return this.a[i];
    }

    public final boolean zzsb() {
        int b = b();
        return zzhy.zzf(this.a, b, size() + b);
    }
}

package com.google.android.gms.internal.firebase_auth;

import java.nio.charset.Charset;

class zzgp extends zzgq {
    protected final byte[] a;

    zzgp(byte[] bArr) {
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
        return zzht.a(i, this.a, b(), i3);
    }

    /* access modifiers changed from: protected */
    public final String a(Charset charset) {
        return new String(this.a, b(), size(), charset);
    }

    /* access modifiers changed from: package-private */
    public final void a(zzgg zzgg) {
        zzgg.zzb(this.a, b(), size());
    }

    /* access modifiers changed from: package-private */
    public final boolean a(zzgf zzgf, int i, int i2) {
        if (i2 > zzgf.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 > zzgf.size()) {
            int size2 = zzgf.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzgf instanceof zzgp)) {
            return zzgf.zzd(0, i2).equals(zzd(0, i2));
        } else {
            zzgp zzgp = (zzgp) zzgf;
            byte[] bArr = this.a;
            byte[] bArr2 = zzgp.a;
            int b = b() + i2;
            int b2 = b();
            int b3 = zzgp.b();
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
        if (!(obj instanceof zzgf) || size() != ((zzgf) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzgp)) {
            return obj.equals(this);
        }
        zzgp zzgp = (zzgp) obj;
        int a2 = a();
        int a3 = zzgp.a();
        if (a2 == 0 || a3 == 0 || a2 == a3) {
            return a(zzgp, 0, size());
        }
        return false;
    }

    public int size() {
        return this.a.length;
    }

    public final zzgf zzd(int i, int i2) {
        int b = b(0, i2, size());
        return b == 0 ? zzgf.zzvv : new zzgm(this.a, b(), b);
    }

    public final boolean zzgd() {
        int b = b();
        return zzkt.zze(this.a, b, size() + b);
    }

    public byte zzp(int i) {
        return this.a[i];
    }
}

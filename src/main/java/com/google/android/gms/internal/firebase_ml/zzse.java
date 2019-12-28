package com.google.android.gms.internal.firebase_ml;

import java.nio.charset.Charset;

class zzse extends zzsd {
    protected final byte[] a;

    zzse(byte[] bArr) {
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
        return zzte.a(i, this.a, b(), i3);
    }

    /* access modifiers changed from: protected */
    public final String a(Charset charset) {
        return new String(this.a, b(), size(), charset);
    }

    /* access modifiers changed from: package-private */
    public final void a(zzrt zzrt) {
        zzrt.zzb(this.a, b(), size());
    }

    /* access modifiers changed from: package-private */
    public final boolean a(zzru zzru, int i, int i2) {
        if (i2 > zzru.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 > zzru.size()) {
            int size2 = zzru.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzru instanceof zzse)) {
            return zzru.zzc(0, i2).equals(zzc(0, i2));
        } else {
            zzse zzse = (zzse) zzru;
            byte[] bArr = this.a;
            byte[] bArr2 = zzse.a;
            int b = b() + i2;
            int b2 = b();
            int b3 = zzse.b();
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
        if (!(obj instanceof zzru) || size() != ((zzru) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzse)) {
            return obj.equals(this);
        }
        zzse zzse = (zzse) obj;
        int a2 = a();
        int a3 = zzse.a();
        if (a2 == 0 || a3 == 0 || a2 == a3) {
            return a(zzse, 0, size());
        }
        return false;
    }

    public int size() {
        return this.a.length;
    }

    public final zzru zzc(int i, int i2) {
        int b = b(0, i2, size());
        return b == 0 ? zzru.zzbig : new zzrz(this.a, b(), b);
    }

    public byte zzcc(int i) {
        return this.a[i];
    }

    public final boolean zzom() {
        int b = b();
        return zzwc.zzf(this.a, b, size() + b);
    }
}

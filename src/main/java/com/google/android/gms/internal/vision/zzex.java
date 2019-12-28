package com.google.android.gms.internal.vision;

import java.nio.charset.Charset;

class zzex extends zzew {
    protected final byte[] a;

    zzex(byte[] bArr) {
        if (bArr != null) {
            this.a = bArr;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: protected */
    public final int a(int i, int i2, int i3) {
        return zzga.a(i, this.a, b(), i3);
    }

    /* access modifiers changed from: protected */
    public final String a(Charset charset) {
        return new String(this.a, b(), size(), charset);
    }

    /* access modifiers changed from: package-private */
    public final void a(zzen zzen) {
        zzen.zza(this.a, b(), size());
    }

    /* access modifiers changed from: protected */
    public void a(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.a, 0, bArr, 0, i3);
    }

    /* access modifiers changed from: package-private */
    public final boolean a(zzeo zzeo, int i, int i2) {
        if (i2 > zzeo.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 > zzeo.size()) {
            int size2 = zzeo.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzeo instanceof zzex)) {
            return zzeo.zzc(0, i2).equals(zzc(0, i2));
        } else {
            zzex zzex = (zzex) zzeo;
            byte[] bArr = this.a;
            byte[] bArr2 = zzex.a;
            int b = b() + i2;
            int b2 = b();
            int b3 = zzex.b();
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
        if (!(obj instanceof zzeo) || size() != ((zzeo) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzex)) {
            return obj.equals(this);
        }
        zzex zzex = (zzex) obj;
        int a2 = a();
        int a3 = zzex.a();
        if (a2 == 0 || a3 == 0 || a2 == a3) {
            return a(zzex, 0, size());
        }
        return false;
    }

    public int size() {
        return this.a.length;
    }

    public byte zzai(int i) {
        return this.a[i];
    }

    public final zzeo zzc(int i, int i2) {
        int b = b(0, i2, size());
        return b == 0 ? zzeo.zzrx : new zzes(this.a, b(), b);
    }

    public final boolean zzdl() {
        int b = b();
        return zziw.zzg(this.a, b, size() + b);
    }
}

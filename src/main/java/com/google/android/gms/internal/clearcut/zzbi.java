package com.google.android.gms.internal.clearcut;

import java.nio.charset.Charset;

class zzbi extends zzbh {
    protected final byte[] a;

    zzbi(byte[] bArr) {
        this.a = bArr;
    }

    /* access modifiers changed from: protected */
    public final int a(int i, int i2, int i3) {
        return zzci.a(i, this.a, b(), i3);
    }

    /* access modifiers changed from: protected */
    public final String a(Charset charset) {
        return new String(this.a, b(), size(), charset);
    }

    /* access modifiers changed from: package-private */
    public final void a(zzba zzba) {
        zzba.zza(this.a, b(), size());
    }

    /* access modifiers changed from: package-private */
    public final boolean a(zzbb zzbb, int i, int i2) {
        if (i2 > zzbb.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 > zzbb.size()) {
            int size2 = zzbb.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzbb instanceof zzbi)) {
            return zzbb.zza(0, i2).equals(zza(0, i2));
        } else {
            zzbi zzbi = (zzbi) zzbb;
            byte[] bArr = this.a;
            byte[] bArr2 = zzbi.a;
            int b = b() + i2;
            int b2 = b();
            int b3 = zzbi.b();
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
        if (!(obj instanceof zzbb) || size() != ((zzbb) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzbi)) {
            return obj.equals(this);
        }
        zzbi zzbi = (zzbi) obj;
        int a2 = a();
        int a3 = zzbi.a();
        if (a2 == 0 || a3 == 0 || a2 == a3) {
            return a(zzbi, 0, size());
        }
        return false;
    }

    public int size() {
        return this.a.length;
    }

    public final zzbb zza(int i, int i2) {
        int b = b(0, i2, size());
        return b == 0 ? zzbb.zzfi : new zzbe(this.a, b(), b);
    }

    public final boolean zzaa() {
        int b = b();
        return zzff.zze(this.a, b, size() + b);
    }

    public byte zzj(int i) {
        return this.a[i];
    }
}

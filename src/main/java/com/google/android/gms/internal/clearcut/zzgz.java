package com.google.android.gms.internal.clearcut;

import java.util.Arrays;

public final class zzgz extends zzfu<zzgz> implements Cloneable {
    private byte[] zzbjb = zzgb.zzse;
    private String zzbjc = "";
    private byte[][] zzbjd = zzgb.zzsd;
    private boolean zzbje = false;

    public zzgz() {
        this.a = null;
        this.b = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzgc */
    public final zzgz clone() {
        try {
            zzgz zzgz = (zzgz) super.clone();
            byte[][] bArr = this.zzbjd;
            if (bArr != null && bArr.length > 0) {
                zzgz.zzbjd = (byte[][]) bArr.clone();
            }
            return zzgz;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        if (!Arrays.equals(this.zzbjb, zzgb.zzse)) {
            a += zzfs.zzb(1, this.zzbjb);
        }
        byte[][] bArr = this.zzbjd;
        if (bArr != null && bArr.length > 0) {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                byte[][] bArr2 = this.zzbjd;
                if (i >= bArr2.length) {
                    break;
                }
                byte[] bArr3 = bArr2[i];
                if (bArr3 != null) {
                    i3++;
                    i2 += zzfs.zzh(bArr3);
                }
                i++;
            }
            a = a + i2 + (i3 * 1);
        }
        String str = this.zzbjc;
        return (str == null || str.equals("")) ? a : a + zzfs.zzb(4, this.zzbjc);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgz)) {
            return false;
        }
        zzgz zzgz = (zzgz) obj;
        if (!Arrays.equals(this.zzbjb, zzgz.zzbjb)) {
            return false;
        }
        String str = this.zzbjc;
        if (str == null) {
            if (zzgz.zzbjc != null) {
                return false;
            }
        } else if (!str.equals(zzgz.zzbjc)) {
            return false;
        }
        if (!zzfy.zza(this.zzbjd, zzgz.zzbjd)) {
            return false;
        }
        return (this.a == null || this.a.isEmpty()) ? zzgz.a == null || zzgz.a.isEmpty() : this.a.equals(zzgz.a);
    }

    public final int hashCode() {
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.zzbjb)) * 31;
        String str = this.zzbjc;
        int i = 0;
        int hashCode2 = (((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + zzfy.zza(this.zzbjd)) * 31) + 1237) * 31;
        if (this.a != null && !this.a.isEmpty()) {
            i = this.a.hashCode();
        }
        return hashCode2 + i;
    }

    public final void zza(zzfs zzfs) {
        if (!Arrays.equals(this.zzbjb, zzgb.zzse)) {
            zzfs.zza(1, this.zzbjb);
        }
        byte[][] bArr = this.zzbjd;
        if (bArr != null && bArr.length > 0) {
            int i = 0;
            while (true) {
                byte[][] bArr2 = this.zzbjd;
                if (i >= bArr2.length) {
                    break;
                }
                byte[] bArr3 = bArr2[i];
                if (bArr3 != null) {
                    zzfs.zza(2, bArr3);
                }
                i++;
            }
        }
        String str = this.zzbjc;
        if (str != null && !str.equals("")) {
            zzfs.zza(4, this.zzbjc);
        }
        super.zza(zzfs);
    }

    public final /* synthetic */ zzfu zzeo() {
        return (zzgz) clone();
    }

    public final /* synthetic */ zzfz zzep() {
        return (zzgz) clone();
    }
}

package com.google.android.gms.internal.clearcut;

public final class zzhb extends zzfu<zzhb> implements Cloneable {
    private static volatile zzhb[] zzbkd;
    private String value = "";
    private String zzbke = "";

    public zzhb() {
        this.a = null;
        this.b = -1;
    }

    public static zzhb[] zzge() {
        if (zzbkd == null) {
            synchronized (zzfy.zzrr) {
                if (zzbkd == null) {
                    zzbkd = new zzhb[0];
                }
            }
        }
        return zzbkd;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzgf */
    public final zzhb clone() {
        try {
            return (zzhb) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        String str = this.zzbke;
        if (str != null && !str.equals("")) {
            a += zzfs.zzb(1, this.zzbke);
        }
        String str2 = this.value;
        return (str2 == null || str2.equals("")) ? a : a + zzfs.zzb(2, this.value);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzhb)) {
            return false;
        }
        zzhb zzhb = (zzhb) obj;
        String str = this.zzbke;
        if (str == null) {
            if (zzhb.zzbke != null) {
                return false;
            }
        } else if (!str.equals(zzhb.zzbke)) {
            return false;
        }
        String str2 = this.value;
        if (str2 == null) {
            if (zzhb.value != null) {
                return false;
            }
        } else if (!str2.equals(zzhb.value)) {
            return false;
        }
        return (this.a == null || this.a.isEmpty()) ? zzhb.a == null || zzhb.a.isEmpty() : this.a.equals(zzhb.a);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.zzbke;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.value;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        if (this.a != null && !this.a.isEmpty()) {
            i = this.a.hashCode();
        }
        return hashCode3 + i;
    }

    public final void zza(zzfs zzfs) {
        String str = this.zzbke;
        if (str != null && !str.equals("")) {
            zzfs.zza(1, this.zzbke);
        }
        String str2 = this.value;
        if (str2 != null && !str2.equals("")) {
            zzfs.zza(2, this.value);
        }
        super.zza(zzfs);
    }

    public final /* synthetic */ zzfu zzeo() {
        return (zzhb) clone();
    }

    public final /* synthetic */ zzfz zzep() {
        return (zzhb) clone();
    }
}

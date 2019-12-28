package com.google.android.gms.internal.vision;

public final class zzdh extends zzjn<zzdh> {
    public String version = null;
    public String zzod = null;

    public zzdh() {
        this.b = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        String str = this.zzod;
        if (str != null) {
            a += zzjl.zzb(1, str);
        }
        String str2 = this.version;
        return str2 != null ? a + zzjl.zzb(2, str2) : a;
    }

    public final /* synthetic */ zzjt zza(zzjk zzjk) {
        while (true) {
            int zzdq = zzjk.zzdq();
            if (zzdq == 0) {
                return this;
            }
            if (zzdq == 10) {
                this.zzod = zzjk.readString();
            } else if (zzdq == 18) {
                this.version = zzjk.readString();
            } else if (!super.a(zzjk, zzdq)) {
                return this;
            }
        }
    }

    public final void zza(zzjl zzjl) {
        String str = this.zzod;
        if (str != null) {
            zzjl.zza(1, str);
        }
        String str2 = this.version;
        if (str2 != null) {
            zzjl.zza(2, str2);
        }
        super.zza(zzjl);
    }
}

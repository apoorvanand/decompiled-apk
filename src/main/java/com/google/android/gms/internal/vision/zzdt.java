package com.google.android.gms.internal.vision;

public final class zzdt extends zzjn<zzdt> {
    private static volatile zzdt[] zzpx;
    public zzdk zzpy = null;
    public Integer zzpz = null;
    public zzdo zzqa = null;
    private zzdj zzqb = null;

    public zzdt() {
        this.b = -1;
    }

    public static zzdt[] zzcd() {
        if (zzpx == null) {
            synchronized (zzjr.zzado) {
                if (zzpx == null) {
                    zzpx = new zzdt[0];
                }
            }
        }
        return zzpx;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        zzdk zzdk = this.zzpy;
        if (zzdk != null) {
            a += zzjl.zzb(1, (zzjt) zzdk);
        }
        Integer num = this.zzpz;
        if (num != null) {
            a += zzjl.zzi(2, num.intValue());
        }
        zzdo zzdo = this.zzqa;
        if (zzdo != null) {
            a += zzjl.zzb(16, (zzjt) zzdo);
        }
        zzdj zzdj = this.zzqb;
        return zzdj != null ? a + zzjl.zzb(17, (zzjt) zzdj) : a;
    }

    public final /* synthetic */ zzjt zza(zzjk zzjk) {
        zzjt zzjt;
        while (true) {
            int zzdq = zzjk.zzdq();
            if (zzdq == 0) {
                return this;
            }
            if (zzdq == 10) {
                if (this.zzpy == null) {
                    this.zzpy = new zzdk();
                }
                zzjt = this.zzpy;
            } else if (zzdq == 16) {
                this.zzpz = Integer.valueOf(zzjk.zzdt());
            } else if (zzdq == 130) {
                if (this.zzqa == null) {
                    this.zzqa = new zzdo();
                }
                zzjt = this.zzqa;
            } else if (zzdq == 138) {
                if (this.zzqb == null) {
                    this.zzqb = new zzdj();
                }
                zzjt = this.zzqb;
            } else if (!super.a(zzjk, zzdq)) {
                return this;
            }
            zzjk.zza(zzjt);
        }
    }

    public final void zza(zzjl zzjl) {
        zzdk zzdk = this.zzpy;
        if (zzdk != null) {
            zzjl.zza(1, (zzjt) zzdk);
        }
        Integer num = this.zzpz;
        if (num != null) {
            zzjl.zze(2, num.intValue());
        }
        zzdo zzdo = this.zzqa;
        if (zzdo != null) {
            zzjl.zza(16, (zzjt) zzdo);
        }
        zzdj zzdj = this.zzqb;
        if (zzdj != null) {
            zzjl.zza(17, (zzjt) zzdj);
        }
        super.zza(zzjl);
    }
}

package com.google.android.gms.internal.vision;

public final class zzdu extends zzjn<zzdu> {
    private zzdl zzqc = null;
    public zzdr zzqd = null;
    public zzdp zzqe = null;
    private Integer zzqf = null;

    public zzdu() {
        this.b = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        zzdl zzdl = this.zzqc;
        if (zzdl != null) {
            a += zzjl.zzb(1, (zzjt) zzdl);
        }
        zzdr zzdr = this.zzqd;
        if (zzdr != null) {
            a += zzjl.zzb(2, (zzjt) zzdr);
        }
        zzdp zzdp = this.zzqe;
        if (zzdp != null) {
            a += zzjl.zzb(3, (zzjt) zzdp);
        }
        Integer num = this.zzqf;
        return num != null ? a + zzjl.zzi(4, num.intValue()) : a;
    }

    public final /* synthetic */ zzjt zza(zzjk zzjk) {
        zzjt zzjt;
        while (true) {
            int zzdq = zzjk.zzdq();
            if (zzdq == 0) {
                return this;
            }
            if (zzdq == 10) {
                if (this.zzqc == null) {
                    this.zzqc = new zzdl();
                }
                zzjt = this.zzqc;
            } else if (zzdq == 18) {
                if (this.zzqd == null) {
                    this.zzqd = new zzdr();
                }
                zzjt = this.zzqd;
            } else if (zzdq == 26) {
                if (this.zzqe == null) {
                    this.zzqe = new zzdp();
                }
                zzjt = this.zzqe;
            } else if (zzdq == 32) {
                this.zzqf = Integer.valueOf(zzjk.zzdt());
            } else if (!super.a(zzjk, zzdq)) {
                return this;
            }
            zzjk.zza(zzjt);
        }
    }

    public final void zza(zzjl zzjl) {
        zzdl zzdl = this.zzqc;
        if (zzdl != null) {
            zzjl.zza(1, (zzjt) zzdl);
        }
        zzdr zzdr = this.zzqd;
        if (zzdr != null) {
            zzjl.zza(2, (zzjt) zzdr);
        }
        zzdp zzdp = this.zzqe;
        if (zzdp != null) {
            zzjl.zza(3, (zzjt) zzdp);
        }
        Integer num = this.zzqf;
        if (num != null) {
            zzjl.zze(4, num.intValue());
        }
        super.zza(zzjl);
    }
}

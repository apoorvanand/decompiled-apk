package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcz;

public final class zzdq extends zzjn<zzdq> {
    private zzcz.zzf.zzb zzpl;
    public Long zzpm = null;
    public Long zzpn = null;
    public Long zzpo = null;
    public Long zzpp = null;

    public zzdq() {
        this.b = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        zzcz.zzf.zzb zzb = this.zzpl;
        if (!(zzb == null || zzb == null)) {
            a += zzjl.zzi(1, zzb.zzr());
        }
        Long l = this.zzpm;
        if (l != null) {
            a += zzjl.zzd(2, l.longValue());
        }
        Long l2 = this.zzpn;
        if (l2 != null) {
            a += zzjl.zzd(3, l2.longValue());
        }
        Long l3 = this.zzpp;
        if (l3 != null) {
            a += zzjl.zzd(4, l3.longValue());
        }
        Long l4 = this.zzpo;
        return l4 != null ? a + zzjl.zzd(5, l4.longValue()) : a;
    }

    public final /* synthetic */ zzjt zza(zzjk zzjk) {
        while (true) {
            int zzdq = zzjk.zzdq();
            if (zzdq != 0) {
                if (zzdq == 8) {
                    int position = zzjk.getPosition();
                    int zzdt = zzjk.zzdt();
                    switch (zzdt) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                            this.zzpl = zzcz.zzf.zzb.zzu(zzdt);
                            break;
                        default:
                            zzjk.zzbt(position);
                            a(zzjk, zzdq);
                            break;
                    }
                } else if (zzdq == 16) {
                    this.zzpm = Long.valueOf(zzjk.zzdu());
                } else if (zzdq == 24) {
                    this.zzpn = Long.valueOf(zzjk.zzdu());
                } else if (zzdq == 32) {
                    this.zzpp = Long.valueOf(zzjk.zzdu());
                } else if (zzdq == 40) {
                    this.zzpo = Long.valueOf(zzjk.zzdu());
                } else if (!super.a(zzjk, zzdq)) {
                    return this;
                }
            } else {
                return this;
            }
        }
    }

    public final void zza(zzjl zzjl) {
        zzcz.zzf.zzb zzb = this.zzpl;
        if (!(zzb == null || zzb == null)) {
            zzjl.zze(1, zzb.zzr());
        }
        Long l = this.zzpm;
        if (l != null) {
            zzjl.zzi(2, l.longValue());
        }
        Long l2 = this.zzpn;
        if (l2 != null) {
            zzjl.zzi(3, l2.longValue());
        }
        Long l3 = this.zzpp;
        if (l3 != null) {
            zzjl.zzi(4, l3.longValue());
        }
        Long l4 = this.zzpo;
        if (l4 != null) {
            zzjl.zzi(5, l4.longValue());
        }
        super.zza(zzjl);
    }
}

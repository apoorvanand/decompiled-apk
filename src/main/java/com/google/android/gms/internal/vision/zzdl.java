package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcz;

public final class zzdl extends zzjn<zzdl> {
    private String url = null;
    private Boolean zzoi = null;
    private zzcz.zzc.zzb zzoj;
    private Long zzok = null;
    private Long zzol = null;
    private Long zzom = null;
    private String zzon = null;

    public zzdl() {
        this.b = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        String str = this.url;
        if (str != null) {
            a += zzjl.zzb(1, str);
        }
        Boolean bool = this.zzoi;
        if (bool != null) {
            bool.booleanValue();
            a += zzjl.zzav(2) + 1;
        }
        zzcz.zzc.zzb zzb = this.zzoj;
        if (!(zzb == null || zzb == null)) {
            a += zzjl.zzi(3, zzb.zzr());
        }
        Long l = this.zzok;
        if (l != null) {
            a += zzjl.zzd(4, l.longValue());
        }
        Long l2 = this.zzol;
        if (l2 != null) {
            a += zzjl.zzd(5, l2.longValue());
        }
        Long l3 = this.zzom;
        if (l3 != null) {
            a += zzjl.zzd(6, l3.longValue());
        }
        String str2 = this.zzon;
        return str2 != null ? a + zzjl.zzb(7, str2) : a;
    }

    public final /* synthetic */ zzjt zza(zzjk zzjk) {
        while (true) {
            int zzdq = zzjk.zzdq();
            if (zzdq == 0) {
                return this;
            }
            if (zzdq == 10) {
                this.url = zzjk.readString();
            } else if (zzdq != 16) {
                if (zzdq == 24) {
                    int position = zzjk.getPosition();
                    int zzdt = zzjk.zzdt();
                    switch (zzdt) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                            this.zzoj = zzcz.zzc.zzb.zzs(zzdt);
                            break;
                        default:
                            zzjk.zzbt(position);
                            a(zzjk, zzdq);
                            break;
                    }
                } else if (zzdq == 32) {
                    this.zzok = Long.valueOf(zzjk.zzdu());
                } else if (zzdq == 40) {
                    this.zzol = Long.valueOf(zzjk.zzdu());
                } else if (zzdq == 48) {
                    this.zzom = Long.valueOf(zzjk.zzdu());
                } else if (zzdq == 58) {
                    this.zzon = zzjk.readString();
                } else if (!super.a(zzjk, zzdq)) {
                    return this;
                }
            } else {
                this.zzoi = Boolean.valueOf(zzjk.zzcu());
            }
        }
    }

    public final void zza(zzjl zzjl) {
        String str = this.url;
        if (str != null) {
            zzjl.zza(1, str);
        }
        Boolean bool = this.zzoi;
        if (bool != null) {
            zzjl.zzb(2, bool.booleanValue());
        }
        zzcz.zzc.zzb zzb = this.zzoj;
        if (!(zzb == null || zzb == null)) {
            zzjl.zze(3, zzb.zzr());
        }
        Long l = this.zzok;
        if (l != null) {
            zzjl.zzi(4, l.longValue());
        }
        Long l2 = this.zzol;
        if (l2 != null) {
            zzjl.zzi(5, l2.longValue());
        }
        Long l3 = this.zzom;
        if (l3 != null) {
            zzjl.zzi(6, l3.longValue());
        }
        String str2 = this.zzon;
        if (str2 != null) {
            zzjl.zza(7, str2);
        }
        super.zza(zzjl);
    }
}

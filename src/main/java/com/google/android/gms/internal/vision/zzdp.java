package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcz;

public final class zzdp extends zzjn<zzdp> {
    public zzdq zzpi = null;
    private zzcz.zzg zzpj;
    public zzdm[] zzpk = zzdm.zzcb();

    public zzdp() {
        this.b = -1;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        zzdq zzdq = this.zzpi;
        if (zzdq != null) {
            a += zzjl.zzb(1, (zzjt) zzdq);
        }
        zzcz.zzg zzg = this.zzpj;
        if (zzg != null) {
            a += zzfe.zzc(2, (zzhf) zzg);
        }
        zzdm[] zzdmArr = this.zzpk;
        if (zzdmArr != null && zzdmArr.length > 0) {
            int i = 0;
            while (true) {
                zzdm[] zzdmArr2 = this.zzpk;
                if (i >= zzdmArr2.length) {
                    break;
                }
                zzdm zzdm = zzdmArr2[i];
                if (zzdm != null) {
                    a += zzjl.zzb(3, (zzjt) zzdm);
                }
                i++;
            }
        }
        return a;
    }

    public final /* synthetic */ zzjt zza(zzjk zzjk) {
        while (true) {
            int zzdq = zzjk.zzdq();
            if (zzdq == 0) {
                return this;
            }
            if (zzdq == 10) {
                if (this.zzpi == null) {
                    this.zzpi = new zzdq();
                }
                zzjk.zza((zzjt) this.zzpi);
            } else if (zzdq == 18) {
                this.zzpj = (zzcz.zzg) zzjk.zza(zzcz.zzg.zzbx());
            } else if (zzdq == 26) {
                int zzb = zzjw.zzb(zzjk, 26);
                zzdm[] zzdmArr = this.zzpk;
                int length = zzdmArr == null ? 0 : zzdmArr.length;
                zzdm[] zzdmArr2 = new zzdm[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzpk, 0, zzdmArr2, 0, length);
                }
                while (length < zzdmArr2.length - 1) {
                    zzdmArr2[length] = new zzdm();
                    zzjk.zza((zzjt) zzdmArr2[length]);
                    zzjk.zzdq();
                    length++;
                }
                zzdmArr2[length] = new zzdm();
                zzjk.zza((zzjt) zzdmArr2[length]);
                this.zzpk = zzdmArr2;
            } else if (!super.a(zzjk, zzdq)) {
                return this;
            }
        }
    }

    public final void zza(zzjl zzjl) {
        zzdq zzdq = this.zzpi;
        if (zzdq != null) {
            zzjl.zza(1, (zzjt) zzdq);
        }
        zzcz.zzg zzg = this.zzpj;
        if (zzg != null) {
            zzjl.zze(2, (zzhf) zzg);
        }
        zzdm[] zzdmArr = this.zzpk;
        if (zzdmArr != null && zzdmArr.length > 0) {
            int i = 0;
            while (true) {
                zzdm[] zzdmArr2 = this.zzpk;
                if (i >= zzdmArr2.length) {
                    break;
                }
                zzdm zzdm = zzdmArr2[i];
                if (zzdm != null) {
                    zzjl.zza(3, (zzjt) zzdm);
                }
                i++;
            }
        }
        super.zza(zzjl);
    }
}

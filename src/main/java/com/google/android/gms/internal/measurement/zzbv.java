package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzbk;

public final class zzbv extends zziq<zzbv> {
    private static volatile zzbv[] zzze;
    public Integer zzzf = null;
    public zzbk.zzd[] zzzg = new zzbk.zzd[0];
    public zzbk.zza[] zzzh = new zzbk.zza[0];
    private Boolean zzzi = null;
    private Boolean zzzj = null;

    public zzbv() {
        this.a = null;
        this.b = -1;
    }

    public static zzbv[] zzqx() {
        if (zzze == null) {
            synchronized (zziu.zzaov) {
                if (zzze == null) {
                    zzze = new zzbv[0];
                }
            }
        }
        return zzze;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        Integer num = this.zzzf;
        if (num != null) {
            a += zzio.zzg(1, num.intValue());
        }
        zzbk.zzd[] zzdArr = this.zzzg;
        int i = 0;
        if (zzdArr != null && zzdArr.length > 0) {
            int i2 = a;
            int i3 = 0;
            while (true) {
                zzbk.zzd[] zzdArr2 = this.zzzg;
                if (i3 >= zzdArr2.length) {
                    break;
                }
                zzbk.zzd zzd = zzdArr2[i3];
                if (zzd != null) {
                    i2 += zzee.zzc(2, (zzgi) zzd);
                }
                i3++;
            }
            a = i2;
        }
        zzbk.zza[] zzaArr = this.zzzh;
        if (zzaArr != null && zzaArr.length > 0) {
            while (true) {
                zzbk.zza[] zzaArr2 = this.zzzh;
                if (i >= zzaArr2.length) {
                    break;
                }
                zzbk.zza zza = zzaArr2[i];
                if (zza != null) {
                    a += zzee.zzc(3, (zzgi) zza);
                }
                i++;
            }
        }
        Boolean bool = this.zzzi;
        if (bool != null) {
            bool.booleanValue();
            a += zzio.zzbi(4) + 1;
        }
        Boolean bool2 = this.zzzj;
        if (bool2 == null) {
            return a;
        }
        bool2.booleanValue();
        return a + zzio.zzbi(5) + 1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbv)) {
            return false;
        }
        zzbv zzbv = (zzbv) obj;
        Integer num = this.zzzf;
        if (num == null) {
            if (zzbv.zzzf != null) {
                return false;
            }
        } else if (!num.equals(zzbv.zzzf)) {
            return false;
        }
        if (!zziu.equals(this.zzzg, zzbv.zzzg) || !zziu.equals(this.zzzh, zzbv.zzzh)) {
            return false;
        }
        Boolean bool = this.zzzi;
        if (bool == null) {
            if (zzbv.zzzi != null) {
                return false;
            }
        } else if (!bool.equals(zzbv.zzzi)) {
            return false;
        }
        Boolean bool2 = this.zzzj;
        if (bool2 == null) {
            if (zzbv.zzzj != null) {
                return false;
            }
        } else if (!bool2.equals(zzbv.zzzj)) {
            return false;
        }
        return (this.a == null || this.a.isEmpty()) ? zzbv.a == null || zzbv.a.isEmpty() : this.a.equals(zzbv.a);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        Integer num = this.zzzf;
        int i = 0;
        int hashCode2 = (((((hashCode + (num == null ? 0 : num.hashCode())) * 31) + zziu.hashCode(this.zzzg)) * 31) + zziu.hashCode(this.zzzh)) * 31;
        Boolean bool = this.zzzi;
        int hashCode3 = (hashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.zzzj;
        int hashCode4 = (hashCode3 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        if (this.a != null && !this.a.isEmpty()) {
            i = this.a.hashCode();
        }
        return hashCode4 + i;
    }

    public final /* synthetic */ zziw zza(zzil zzil) {
        while (true) {
            int zzsg = zzil.zzsg();
            if (zzsg == 0) {
                return this;
            }
            if (zzsg == 8) {
                this.zzzf = Integer.valueOf(zzil.zzta());
            } else if (zzsg == 18) {
                int zzb = zzix.zzb(zzil, 18);
                zzbk.zzd[] zzdArr = this.zzzg;
                int length = zzdArr == null ? 0 : zzdArr.length;
                zzbk.zzd[] zzdArr2 = new zzbk.zzd[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzzg, 0, zzdArr2, 0, length);
                }
                while (length < zzdArr2.length - 1) {
                    zzdArr2[length] = (zzbk.zzd) zzil.zza(zzbk.zzd.zzkj());
                    zzil.zzsg();
                    length++;
                }
                zzdArr2[length] = (zzbk.zzd) zzil.zza(zzbk.zzd.zzkj());
                this.zzzg = zzdArr2;
            } else if (zzsg == 26) {
                int zzb2 = zzix.zzb(zzil, 26);
                zzbk.zza[] zzaArr = this.zzzh;
                int length2 = zzaArr == null ? 0 : zzaArr.length;
                zzbk.zza[] zzaArr2 = new zzbk.zza[(zzb2 + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.zzzh, 0, zzaArr2, 0, length2);
                }
                while (length2 < zzaArr2.length - 1) {
                    zzaArr2[length2] = (zzbk.zza) zzil.zza(zzbk.zza.zzkj());
                    zzil.zzsg();
                    length2++;
                }
                zzaArr2[length2] = (zzbk.zza) zzil.zza(zzbk.zza.zzkj());
                this.zzzh = zzaArr2;
            } else if (zzsg == 32) {
                this.zzzi = Boolean.valueOf(zzil.zzsm());
            } else if (zzsg == 40) {
                this.zzzj = Boolean.valueOf(zzil.zzsm());
            } else if (!super.a(zzil, zzsg)) {
                return this;
            }
        }
    }

    public final void zza(zzio zzio) {
        Integer num = this.zzzf;
        if (num != null) {
            zzio.zzc(1, num.intValue());
        }
        zzbk.zzd[] zzdArr = this.zzzg;
        int i = 0;
        if (zzdArr != null && zzdArr.length > 0) {
            int i2 = 0;
            while (true) {
                zzbk.zzd[] zzdArr2 = this.zzzg;
                if (i2 >= zzdArr2.length) {
                    break;
                }
                zzbk.zzd zzd = zzdArr2[i2];
                if (zzd != null) {
                    zzio.zze(2, zzd);
                }
                i2++;
            }
        }
        zzbk.zza[] zzaArr = this.zzzh;
        if (zzaArr != null && zzaArr.length > 0) {
            while (true) {
                zzbk.zza[] zzaArr2 = this.zzzh;
                if (i >= zzaArr2.length) {
                    break;
                }
                zzbk.zza zza = zzaArr2[i];
                if (zza != null) {
                    zzio.zze(3, zza);
                }
                i++;
            }
        }
        Boolean bool = this.zzzi;
        if (bool != null) {
            zzio.zzb(4, bool.booleanValue());
        }
        Boolean bool2 = this.zzzj;
        if (bool2 != null) {
            zzio.zzb(5, bool2.booleanValue());
        }
        super.zza(zzio);
    }
}

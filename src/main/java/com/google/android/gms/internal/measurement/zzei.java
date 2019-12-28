package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzey;
import java.util.List;
import java.util.Map;

final class zzei implements zzim {
    private final zzee zzadn;

    private zzei(zzee zzee) {
        this.zzadn = (zzee) zzez.a(zzee, "output");
        this.zzadn.a = this;
    }

    public static zzei zza(zzee zzee) {
        return zzee.a != null ? zzee.a : new zzei(zzee);
    }

    public final void zza(int i, double d) {
        this.zzadn.zza(i, d);
    }

    public final void zza(int i, float f) {
        this.zzadn.zza(i, f);
    }

    public final void zza(int i, long j) {
        this.zzadn.zza(i, j);
    }

    public final void zza(int i, zzdp zzdp) {
        this.zzadn.zza(i, zzdp);
    }

    public final <K, V> void zza(int i, zzfz<K, V> zzfz, Map<K, V> map) {
        for (Map.Entry next : map.entrySet()) {
            this.zzadn.zzb(i, 2);
            this.zzadn.zzbf(zzga.a(zzfz, next.getKey(), next.getValue()));
            zzga.a(this.zzadn, zzfz, next.getKey(), next.getValue());
        }
    }

    public final void zza(int i, Object obj) {
        if (obj instanceof zzdp) {
            this.zzadn.zzb(i, (zzdp) obj);
        } else {
            this.zzadn.zzb(i, (zzgi) obj);
        }
    }

    public final void zza(int i, Object obj, zzgx zzgx) {
        this.zzadn.a(i, (zzgi) obj, zzgx);
    }

    public final void zza(int i, List<String> list) {
        int i2 = 0;
        if (list instanceof zzfp) {
            zzfp zzfp = (zzfp) list;
            while (i2 < list.size()) {
                Object zzbw = zzfp.zzbw(i2);
                if (zzbw instanceof String) {
                    this.zzadn.zzb(i, (String) zzbw);
                } else {
                    this.zzadn.zza(i, (zzdp) zzbw);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzb(i, list.get(i2));
            i2++;
        }
    }

    public final void zza(int i, List<?> list, zzgx zzgx) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, (Object) list.get(i2), zzgx);
        }
    }

    public final void zza(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbj(list.get(i4).intValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbe(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzc(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzb(int i, long j) {
        this.zzadn.zzb(i, j);
    }

    public final void zzb(int i, Object obj, zzgx zzgx) {
        zzee zzee = this.zzadn;
        zzee.zzb(i, 3);
        zzgx.zza((zzgi) obj, zzee.a);
        zzee.zzb(i, 4);
    }

    public final void zzb(int i, String str) {
        this.zzadn.zzb(i, str);
    }

    public final void zzb(int i, List<zzdp> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzadn.zza(i, list.get(i2));
        }
    }

    public final void zzb(int i, List<?> list, zzgx zzgx) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, (Object) list.get(i2), zzgx);
        }
    }

    public final void zzb(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbm(list.get(i4).intValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbh(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzf(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzb(int i, boolean z) {
        this.zzadn.zzb(i, z);
    }

    public final void zzbr(int i) {
        this.zzadn.zzb(i, 3);
    }

    public final void zzbs(int i) {
        this.zzadn.zzb(i, 4);
    }

    public final void zzc(int i, int i2) {
        this.zzadn.zzc(i, i2);
    }

    public final void zzc(int i, long j) {
        this.zzadn.zzc(i, j);
    }

    public final void zzc(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbq(list.get(i4).longValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbn(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzd(int i, int i2) {
        this.zzadn.zzd(i, i2);
    }

    public final void zzd(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbr(list.get(i4).longValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbn(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zze(int i, int i2) {
        this.zzadn.zze(i, i2);
    }

    public final void zze(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbt(list.get(i4).longValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbp(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzf(int i, int i2) {
        this.zzadn.zzf(i, i2);
    }

    public final void zzf(int i, List<Float> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzb(list.get(i4).floatValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zza(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zza(i, list.get(i2).floatValue());
            i2++;
        }
    }

    public final void zzg(int i, List<Double> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zze(list.get(i4).doubleValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzd(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zza(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    public final void zzh(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbo(list.get(i4).intValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbe(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzc(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzi(int i, long j) {
        this.zzadn.zza(i, j);
    }

    public final void zzi(int i, List<Boolean> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzr(list.get(i4).booleanValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzq(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzb(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    public final void zzj(int i, long j) {
        this.zzadn.zzc(i, j);
    }

    public final void zzj(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbk(list.get(i4).intValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbf(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzd(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzk(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbn(list.get(i4).intValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbh(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzf(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzl(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbu(list.get(i4).longValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbp(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzm(int i, int i2) {
        this.zzadn.zzf(i, i2);
    }

    public final void zzm(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbl(list.get(i4).intValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbg(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zze(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzn(int i, int i2) {
        this.zzadn.zzc(i, i2);
    }

    public final void zzn(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzadn.zzb(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzee.zzbs(list.get(i4).longValue());
            }
            this.zzadn.zzbf(i3);
            while (i2 < list.size()) {
                this.zzadn.zzbo(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzadn.zzb(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final int zztk() {
        return zzey.zzd.zzaio;
    }
}

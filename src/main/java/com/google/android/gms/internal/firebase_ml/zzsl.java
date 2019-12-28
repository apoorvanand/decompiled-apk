package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zztc;
import java.util.List;
import java.util.Map;

final class zzsl implements zzwp {
    private final zzsj zzbim;

    private zzsl(zzsj zzsj) {
        this.zzbim = (zzsj) zzte.a(zzsj, "output");
        this.zzbim.a = this;
    }

    public static zzsl zza(zzsj zzsj) {
        return zzsj.a != null ? zzsj.a : new zzsl(zzsj);
    }

    public final void zza(int i, double d) {
        this.zzbim.zza(i, d);
    }

    public final void zza(int i, float f) {
        this.zzbim.zza(i, f);
    }

    public final void zza(int i, long j) {
        this.zzbim.zza(i, j);
    }

    public final void zza(int i, zzru zzru) {
        this.zzbim.zza(i, zzru);
    }

    public final <K, V> void zza(int i, zzuf<K, V> zzuf, Map<K, V> map) {
        for (Map.Entry next : map.entrySet()) {
            this.zzbim.zzd(i, 2);
            this.zzbim.zzci(zzue.a(zzuf, next.getKey(), next.getValue()));
            zzue.a(this.zzbim, zzuf, next.getKey(), next.getValue());
        }
    }

    public final void zza(int i, Object obj) {
        if (obj instanceof zzru) {
            this.zzbim.zzb(i, (zzru) obj);
        } else {
            this.zzbim.zza(i, (zzum) obj);
        }
    }

    public final void zza(int i, Object obj, zzvc zzvc) {
        this.zzbim.a(i, (zzum) obj, zzvc);
    }

    public final void zza(int i, List<String> list) {
        int i2 = 0;
        if (list instanceof zztv) {
            zztv zztv = (zztv) list;
            while (i2 < list.size()) {
                Object raw = zztv.getRaw(i2);
                if (raw instanceof String) {
                    this.zzbim.zzb(i, (String) raw);
                } else {
                    this.zzbim.zza(i, (zzru) raw);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbim.zzb(i, list.get(i2));
            i2++;
        }
    }

    public final void zza(int i, List<?> list, zzvc zzvc) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, (Object) list.get(i2), zzvc);
        }
    }

    public final void zza(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzbim.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzsj.zzcm(list.get(i4).intValue());
            }
            this.zzbim.zzci(i3);
            while (i2 < list.size()) {
                this.zzbim.zzch(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbim.zze(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzb(int i, long j) {
        this.zzbim.zzb(i, j);
    }

    public final void zzb(int i, Object obj, zzvc zzvc) {
        zzsj zzsj = this.zzbim;
        zzsj.zzd(i, 3);
        zzvc.zza((zzum) obj, zzsj.a);
        zzsj.zzd(i, 4);
    }

    public final void zzb(int i, String str) {
        this.zzbim.zzb(i, str);
    }

    public final void zzb(int i, List<zzru> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzbim.zza(i, list.get(i2));
        }
    }

    public final void zzb(int i, List<?> list, zzvc zzvc) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, (Object) list.get(i2), zzvc);
        }
    }

    public final void zzb(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzbim.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzsj.zzcp(list.get(i4).intValue());
            }
            this.zzbim.zzci(i3);
            while (i2 < list.size()) {
                this.zzbim.zzck(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbim.zzh(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzb(int i, boolean z) {
        this.zzbim.zzb(i, z);
    }

    public final void zzc(int i, long j) {
        this.zzbim.zzc(i, j);
    }

    public final void zzc(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzbim.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzsj.zzq(list.get(i4).longValue());
            }
            this.zzbim.zzci(i3);
            while (i2 < list.size()) {
                this.zzbim.zzn(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbim.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzcv(int i) {
        this.zzbim.zzd(i, 3);
    }

    public final void zzcw(int i) {
        this.zzbim.zzd(i, 4);
    }

    public final void zzd(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzbim.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzsj.zzr(list.get(i4).longValue());
            }
            this.zzbim.zzci(i3);
            while (i2 < list.size()) {
                this.zzbim.zzn(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbim.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zze(int i, int i2) {
        this.zzbim.zze(i, i2);
    }

    public final void zze(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzbim.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzsj.zzt(list.get(i4).longValue());
            }
            this.zzbim.zzci(i3);
            while (i2 < list.size()) {
                this.zzbim.zzp(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbim.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzf(int i, int i2) {
        this.zzbim.zzf(i, i2);
    }

    public final void zzf(int i, List<Float> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzbim.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzsj.zzs(list.get(i4).floatValue());
            }
            this.zzbim.zzci(i3);
            while (i2 < list.size()) {
                this.zzbim.zzr(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbim.zza(i, list.get(i2).floatValue());
            i2++;
        }
    }

    public final void zzg(int i, int i2) {
        this.zzbim.zzg(i, i2);
    }

    public final void zzg(int i, List<Double> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzbim.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzsj.zzd(list.get(i4).doubleValue());
            }
            this.zzbim.zzci(i3);
            while (i2 < list.size()) {
                this.zzbim.zzc(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbim.zza(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    public final void zzh(int i, int i2) {
        this.zzbim.zzh(i, i2);
    }

    public final void zzh(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzbim.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzsj.zzcr(list.get(i4).intValue());
            }
            this.zzbim.zzci(i3);
            while (i2 < list.size()) {
                this.zzbim.zzch(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbim.zze(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzi(int i, long j) {
        this.zzbim.zza(i, j);
    }

    public final void zzi(int i, List<Boolean> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzbim.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzsj.zzah(list.get(i4).booleanValue());
            }
            this.zzbim.zzci(i3);
            while (i2 < list.size()) {
                this.zzbim.zzag(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbim.zzb(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    public final void zzj(int i, long j) {
        this.zzbim.zzc(i, j);
    }

    public final void zzj(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzbim.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzsj.zzcn(list.get(i4).intValue());
            }
            this.zzbim.zzci(i3);
            while (i2 < list.size()) {
                this.zzbim.zzci(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbim.zzf(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzk(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzbim.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzsj.zzcq(list.get(i4).intValue());
            }
            this.zzbim.zzci(i3);
            while (i2 < list.size()) {
                this.zzbim.zzck(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbim.zzh(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzl(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzbim.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzsj.zzu(list.get(i4).longValue());
            }
            this.zzbim.zzci(i3);
            while (i2 < list.size()) {
                this.zzbim.zzp(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbim.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzm(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzbim.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzsj.zzco(list.get(i4).intValue());
            }
            this.zzbim.zzci(i3);
            while (i2 < list.size()) {
                this.zzbim.zzcj(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbim.zzg(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzn(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzbim.zzd(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzsj.zzs(list.get(i4).longValue());
            }
            this.zzbim.zzci(i3);
            while (i2 < list.size()) {
                this.zzbim.zzo(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzbim.zzb(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzo(int i, int i2) {
        this.zzbim.zzh(i, i2);
    }

    public final int zzov() {
        return zztc.zzf.zzbmu;
    }

    public final void zzp(int i, int i2) {
        this.zzbim.zze(i, i2);
    }
}

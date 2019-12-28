package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzhs;
import java.util.List;
import java.util.Map;

final class zzhc implements zzlh {
    private final zzha zzwc;

    private zzhc(zzha zzha) {
        this.zzwc = (zzha) zzht.a(zzha, "output");
        this.zzwc.a = this;
    }

    public static zzhc zza(zzha zzha) {
        return zzha.a != null ? zzha.a : new zzhc(zzha);
    }

    public final void zza(int i, double d) {
        this.zzwc.zza(i, d);
    }

    public final void zza(int i, float f) {
        this.zzwc.zza(i, f);
    }

    public final void zza(int i, long j) {
        this.zzwc.zza(i, j);
    }

    public final void zza(int i, zzgf zzgf) {
        this.zzwc.zza(i, zzgf);
    }

    public final <K, V> void zza(int i, zzit<K, V> zzit, Map<K, V> map) {
        for (Map.Entry next : map.entrySet()) {
            this.zzwc.zze(i, 2);
            this.zzwc.zzah(zziu.a(zzit, next.getKey(), next.getValue()));
            zziu.a(this.zzwc, zzit, next.getKey(), next.getValue());
        }
    }

    public final void zza(int i, Object obj) {
        if (obj instanceof zzgf) {
            this.zzwc.zzb(i, (zzgf) obj);
        } else {
            this.zzwc.zza(i, (zzjc) obj);
        }
    }

    public final void zza(int i, Object obj, zzjs zzjs) {
        this.zzwc.a(i, (zzjc) obj, zzjs);
    }

    public final void zza(int i, String str) {
        this.zzwc.zza(i, str);
    }

    public final void zza(int i, List<String> list) {
        int i2 = 0;
        if (list instanceof zzij) {
            zzij zzij = (zzij) list;
            while (i2 < list.size()) {
                Object zzax = zzij.zzax(i2);
                if (zzax instanceof String) {
                    this.zzwc.zza(i, (String) zzax);
                } else {
                    this.zzwc.zza(i, (zzgf) zzax);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzwc.zza(i, list.get(i2));
            i2++;
        }
    }

    public final void zza(int i, List<?> list, zzjs zzjs) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, (Object) list.get(i2), zzjs);
        }
    }

    public final void zza(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzwc.zze(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzha.zzal(list.get(i4).intValue());
            }
            this.zzwc.zzah(i3);
            while (i2 < list.size()) {
                this.zzwc.zzag(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzwc.zzf(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzat(int i) {
        this.zzwc.zze(i, 3);
    }

    public final void zzau(int i) {
        this.zzwc.zze(i, 4);
    }

    public final void zzb(int i, long j) {
        this.zzwc.zzb(i, j);
    }

    public final void zzb(int i, Object obj, zzjs zzjs) {
        zzha zzha = this.zzwc;
        zzha.zze(i, 3);
        zzjs.zza((zzjc) obj, zzha.a);
        zzha.zze(i, 4);
    }

    public final void zzb(int i, List<zzgf> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzwc.zza(i, list.get(i2));
        }
    }

    public final void zzb(int i, List<?> list, zzjs zzjs) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, (Object) list.get(i2), zzjs);
        }
    }

    public final void zzb(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzwc.zze(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzha.zzao(list.get(i4).intValue());
            }
            this.zzwc.zzah(i3);
            while (i2 < list.size()) {
                this.zzwc.zzaj(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzwc.zzi(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzc(int i, long j) {
        this.zzwc.zzc(i, j);
    }

    public final void zzc(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzwc.zze(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzha.zze(list.get(i4).longValue());
            }
            this.zzwc.zzah(i3);
            while (i2 < list.size()) {
                this.zzwc.zzb(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzwc.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzc(int i, boolean z) {
        this.zzwc.zzc(i, z);
    }

    public final void zzd(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzwc.zze(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzha.zzf(list.get(i4).longValue());
            }
            this.zzwc.zzah(i3);
            while (i2 < list.size()) {
                this.zzwc.zzb(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzwc.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zze(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzwc.zze(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzha.zzh(list.get(i4).longValue());
            }
            this.zzwc.zzah(i3);
            while (i2 < list.size()) {
                this.zzwc.zzd(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzwc.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzf(int i, int i2) {
        this.zzwc.zzf(i, i2);
    }

    public final void zzf(int i, List<Float> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzwc.zze(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzha.zzb(list.get(i4).floatValue());
            }
            this.zzwc.zzah(i3);
            while (i2 < list.size()) {
                this.zzwc.zza(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzwc.zza(i, list.get(i2).floatValue());
            i2++;
        }
    }

    public final void zzg(int i, int i2) {
        this.zzwc.zzg(i, i2);
    }

    public final void zzg(int i, List<Double> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzwc.zze(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzha.zzb(list.get(i4).doubleValue());
            }
            this.zzwc.zzah(i3);
            while (i2 < list.size()) {
                this.zzwc.zza(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzwc.zza(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    public final void zzh(int i, int i2) {
        this.zzwc.zzh(i, i2);
    }

    public final void zzh(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzwc.zze(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzha.zzaq(list.get(i4).intValue());
            }
            this.zzwc.zzah(i3);
            while (i2 < list.size()) {
                this.zzwc.zzag(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzwc.zzf(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final int zzhl() {
        return zzhs.zzd.zzaay;
    }

    public final void zzi(int i, int i2) {
        this.zzwc.zzi(i, i2);
    }

    public final void zzi(int i, long j) {
        this.zzwc.zza(i, j);
    }

    public final void zzi(int i, List<Boolean> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzwc.zze(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzha.zzu(list.get(i4).booleanValue());
            }
            this.zzwc.zzah(i3);
            while (i2 < list.size()) {
                this.zzwc.zzt(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzwc.zzc(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    public final void zzj(int i, long j) {
        this.zzwc.zzc(i, j);
    }

    public final void zzj(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzwc.zze(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzha.zzam(list.get(i4).intValue());
            }
            this.zzwc.zzah(i3);
            while (i2 < list.size()) {
                this.zzwc.zzah(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzwc.zzg(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzk(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzwc.zze(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzha.zzap(list.get(i4).intValue());
            }
            this.zzwc.zzah(i3);
            while (i2 < list.size()) {
                this.zzwc.zzaj(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzwc.zzi(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzl(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzwc.zze(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzha.zzi(list.get(i4).longValue());
            }
            this.zzwc.zzah(i3);
            while (i2 < list.size()) {
                this.zzwc.zzd(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzwc.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzm(int i, List<Integer> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzwc.zze(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzha.zzan(list.get(i4).intValue());
            }
            this.zzwc.zzah(i3);
            while (i2 < list.size()) {
                this.zzwc.zzai(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzwc.zzh(i, list.get(i2).intValue());
            i2++;
        }
    }

    public final void zzn(int i, List<Long> list, boolean z) {
        int i2 = 0;
        if (z) {
            this.zzwc.zze(i, 2);
            int i3 = 0;
            for (int i4 = 0; i4 < list.size(); i4++) {
                i3 += zzha.zzg(list.get(i4).longValue());
            }
            this.zzwc.zzah(i3);
            while (i2 < list.size()) {
                this.zzwc.zzc(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzwc.zzb(i, list.get(i2).longValue());
            i2++;
        }
    }

    public final void zzp(int i, int i2) {
        this.zzwc.zzi(i, i2);
    }

    public final void zzq(int i, int i2) {
        this.zzwc.zzf(i, i2);
    }
}

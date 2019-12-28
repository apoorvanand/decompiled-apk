package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import java.util.Map;

final class zzbv extends zzbu<zzcg.zze> {
    zzbv() {
    }

    /* access modifiers changed from: package-private */
    public final int a(Map.Entry<?, ?> entry) {
        return ((zzcg.zze) entry.getKey()).a;
    }

    /* access modifiers changed from: package-private */
    public final zzby<zzcg.zze> a(Object obj) {
        return ((zzcg.zzd) obj).zzjv;
    }

    /* access modifiers changed from: package-private */
    public final void a(zzfr zzfr, Map.Entry<?, ?> entry) {
        zzcg.zze zze = (zzcg.zze) entry.getKey();
        switch (zzbw.a[zze.b.ordinal()]) {
            case 1:
                zzfr.zza(zze.a, ((Double) entry.getValue()).doubleValue());
                return;
            case 2:
                zzfr.zza(zze.a, ((Float) entry.getValue()).floatValue());
                return;
            case 3:
                zzfr.zzi(zze.a, ((Long) entry.getValue()).longValue());
                return;
            case 4:
                zzfr.zza(zze.a, ((Long) entry.getValue()).longValue());
                return;
            case 5:
                zzfr.zzc(zze.a, ((Integer) entry.getValue()).intValue());
                return;
            case 6:
                zzfr.zzc(zze.a, ((Long) entry.getValue()).longValue());
                return;
            case 7:
                zzfr.zzf(zze.a, ((Integer) entry.getValue()).intValue());
                return;
            case 8:
                zzfr.zzb(zze.a, ((Boolean) entry.getValue()).booleanValue());
                return;
            case 9:
                zzfr.zzd(zze.a, ((Integer) entry.getValue()).intValue());
                return;
            case 10:
                zzfr.zzm(zze.a, ((Integer) entry.getValue()).intValue());
                return;
            case 11:
                zzfr.zzj(zze.a, ((Long) entry.getValue()).longValue());
                return;
            case 12:
                zzfr.zze(zze.a, ((Integer) entry.getValue()).intValue());
                return;
            case 13:
                zzfr.zzb(zze.a, ((Long) entry.getValue()).longValue());
                return;
            case 14:
                zzfr.zzc(zze.a, ((Integer) entry.getValue()).intValue());
                return;
            case 15:
                zzfr.zza(zze.a, (zzbb) entry.getValue());
                return;
            case 16:
                zzfr.zza(zze.a, (String) entry.getValue());
                return;
            case 17:
                zzfr.zzb(zze.a, (Object) entry.getValue(), (zzef) zzea.zzcm().zze(entry.getValue().getClass()));
                return;
            case 18:
                zzfr.zza(zze.a, (Object) entry.getValue(), (zzef) zzea.zzcm().zze(entry.getValue().getClass()));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(Object obj, zzby<zzcg.zze> zzby) {
        ((zzcg.zzd) obj).zzjv = zzby;
    }

    /* access modifiers changed from: package-private */
    public final boolean a(zzdo zzdo) {
        return zzdo instanceof zzcg.zzd;
    }

    /* access modifiers changed from: package-private */
    public final zzby<zzcg.zze> b(Object obj) {
        zzby<zzcg.zze> a = a(obj);
        if (!a.isImmutable()) {
            return a;
        }
        zzby<zzcg.zze> zzby = (zzby) a.clone();
        a(obj, zzby);
        return zzby;
    }

    /* access modifiers changed from: package-private */
    public final void c(Object obj) {
        a(obj).zzv();
    }
}

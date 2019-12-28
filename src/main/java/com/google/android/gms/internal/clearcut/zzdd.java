package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;

final class zzdd implements zzeg {
    private static final zzdn zzlz = new zzde();
    private final zzdn zzly;

    public zzdd() {
        this(new zzdf(zzcf.zzay(), zzby()));
    }

    private zzdd(zzdn zzdn) {
        this.zzly = (zzdn) zzci.a(zzdn, "messageInfoFactory");
    }

    private static boolean zza(zzdm zzdm) {
        return zzdm.zzcf() == zzcg.zzg.zzkl;
    }

    private static zzdn zzby() {
        try {
            return (zzdn) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception unused) {
            return zzlz;
        }
    }

    public final <T> zzef<T> zzd(Class<T> cls) {
        zzeh.zzf(cls);
        zzdm zzb = this.zzly.zzb(cls);
        if (zzb.zzcg()) {
            return zzcg.class.isAssignableFrom(cls) ? zzdu.a(zzeh.zzdo(), zzbx.a(), zzb.zzch()) : zzdu.a(zzeh.zzdm(), zzbx.b(), zzb.zzch());
        }
        if (zzcg.class.isAssignableFrom(cls)) {
            if (zza(zzb)) {
                return zzds.a(cls, zzb, zzdy.b(), zzcy.b(), zzeh.zzdo(), zzbx.a(), zzdl.b());
            }
            return zzds.a(cls, zzb, zzdy.b(), zzcy.b(), zzeh.zzdo(), (zzbu<?>) null, zzdl.b());
        } else if (zza(zzb)) {
            return zzds.a(cls, zzb, zzdy.a(), zzcy.a(), zzeh.zzdm(), zzbx.b(), zzdl.a());
        } else {
            return zzds.a(cls, zzb, zzdy.a(), zzcy.a(), zzeh.zzdn(), (zzbu<?>) null, zzdl.a());
        }
    }
}

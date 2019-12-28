package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzfy;

final class zzgu implements zzhx {
    private static final zzhe zzyt = new zzgv();
    private final zzhe zzys;

    public zzgu() {
        this(new zzgw(zzfx.zzex(), zzfx()));
    }

    private zzgu(zzhe zzhe) {
        this.zzys = (zzhe) zzga.a(zzhe, "messageInfoFactory");
    }

    private static boolean zza(zzhd zzhd) {
        return zzhd.zzge() == zzfy.zzg.zzxf;
    }

    private static zzhe zzfx() {
        try {
            return (zzhe) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception unused) {
            return zzyt;
        }
    }

    public final <T> zzhw<T> zze(Class<T> cls) {
        zzhy.zzg(cls);
        zzhd zzc = this.zzys.zzc(cls);
        if (zzc.zzgf()) {
            return zzfy.class.isAssignableFrom(cls) ? zzhl.a(zzhy.zzgr(), zzfo.a(), zzc.zzgg()) : zzhl.a(zzhy.zzgp(), zzfo.b(), zzc.zzgg());
        }
        if (zzfy.class.isAssignableFrom(cls)) {
            if (zza(zzc)) {
                return zzhj.a(cls, zzc, zzhp.b(), zzgp.b(), zzhy.zzgr(), zzfo.a(), zzhc.b());
            }
            return zzhj.a(cls, zzc, zzhp.b(), zzgp.b(), zzhy.zzgr(), (zzfl<?>) null, zzhc.b());
        } else if (zza(zzc)) {
            return zzhj.a(cls, zzc, zzhp.a(), zzgp.a(), zzhy.zzgp(), zzfo.b(), zzhc.a());
        } else {
            return zzhj.a(cls, zzc, zzhp.a(), zzgp.a(), zzhy.zzgq(), (zzfl<?>) null, zzhc.a());
        }
    }
}

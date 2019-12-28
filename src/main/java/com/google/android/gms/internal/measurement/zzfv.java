package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzey;

final class zzfv implements zzha {
    private static final zzgf zzajx = new zzfy();
    private final zzgf zzajw;

    public zzfv() {
        this(new zzfx(zzew.zzua(), zzvj()));
    }

    private zzfv(zzgf zzgf) {
        this.zzajw = (zzgf) zzez.a(zzgf, "messageInfoFactory");
    }

    private static boolean zza(zzgg zzgg) {
        return zzgg.zzvr() == zzey.zzd.zzail;
    }

    private static zzgf zzvj() {
        try {
            return (zzgf) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception unused) {
            return zzajx;
        }
    }

    public final <T> zzgx<T> zze(Class<T> cls) {
        zzgz.zzg(cls);
        zzgg zzb = this.zzajw.zzb(cls);
        if (zzb.zzvs()) {
            return zzey.class.isAssignableFrom(cls) ? zzgo.a(zzgz.zzwe(), zzep.a(), zzb.zzvt()) : zzgo.a(zzgz.zzwc(), zzep.b(), zzb.zzvt());
        }
        if (zzey.class.isAssignableFrom(cls)) {
            if (zza(zzb)) {
                return zzgm.a(cls, zzb, zzgs.b(), zzfs.b(), zzgz.zzwe(), zzep.a(), zzgd.b());
            }
            return zzgm.a(cls, zzb, zzgs.b(), zzfs.b(), zzgz.zzwe(), (zzen<?>) null, zzgd.b());
        } else if (zza(zzb)) {
            return zzgm.a(cls, zzb, zzgs.a(), zzfs.a(), zzgz.zzwc(), zzep.b(), zzgd.a());
        } else {
            return zzgm.a(cls, zzb, zzgs.a(), zzfs.a(), zzgz.zzwd(), (zzen<?>) null, zzgd.a());
        }
    }
}

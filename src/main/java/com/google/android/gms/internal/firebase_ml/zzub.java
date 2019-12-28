package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zztc;

final class zzub implements zzvd {
    private static final zzul zzbof = new zzuc();
    private final zzul zzboe;

    public zzub() {
        this(new zzud(zztb.zzpl(), zzqn()));
    }

    private zzub(zzul zzul) {
        this.zzboe = (zzul) zzte.a(zzul, "messageInfoFactory");
    }

    private static boolean zza(zzuk zzuk) {
        return zzuk.zzqv() == zztc.zzf.zzbmr;
    }

    private static zzul zzqn() {
        try {
            return (zzul) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception unused) {
            return zzbof;
        }
    }

    public final <T> zzvc<T> zzk(Class<T> cls) {
        zzve.zzm(cls);
        zzuk zzi = this.zzboe.zzi(cls);
        if (zzi.zzqw()) {
            return zztc.class.isAssignableFrom(cls) ? zzus.a(zzve.zzri(), zzst.a(), zzi.zzqx()) : zzus.a(zzve.zzrg(), zzst.b(), zzi.zzqx());
        }
        if (zztc.class.isAssignableFrom(cls)) {
            if (zza(zzi)) {
                return zzuq.a(cls, zzi, zzuw.b(), zztw.b(), zzve.zzri(), zzst.a(), zzuj.b());
            }
            return zzuq.a(cls, zzi, zzuw.b(), zztw.b(), zzve.zzri(), (zzsr<?>) null, zzuj.b());
        } else if (zza(zzi)) {
            return zzuq.a(cls, zzi, zzuw.a(), zztw.a(), zzve.zzrg(), zzst.b(), zzuj.a());
        } else {
            return zzuq.a(cls, zzi, zzuw.a(), zztw.a(), zzve.zzrh(), (zzsr<?>) null, zzuj.a());
        }
    }
}

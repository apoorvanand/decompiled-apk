package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzhs;

final class zzip implements zzjr {
    private static final zziz zzacg = new zzis();
    private final zziz zzacf;

    public zzip() {
        this(new zzir(zzhq.zzib(), zzjh()));
    }

    private zzip(zziz zziz) {
        this.zzacf = (zziz) zzht.a(zziz, "messageInfoFactory");
    }

    private static boolean zza(zzja zzja) {
        return zzja.zzjo() == zzhs.zzd.zzaav;
    }

    private static zziz zzjh() {
        try {
            return (zziz) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke((Object) null, new Object[0]);
        } catch (Exception unused) {
            return zzacg;
        }
    }

    public final <T> zzjs<T> zze(Class<T> cls) {
        zzju.zzg(cls);
        zzja zzb = this.zzacf.zzb(cls);
        if (zzb.zzjp()) {
            return zzhs.class.isAssignableFrom(cls) ? zzjf.a(zzju.zzkf(), zzhj.a(), zzb.zzjq()) : zzjf.a(zzju.zzkd(), zzhj.b(), zzb.zzjq());
        }
        if (zzhs.class.isAssignableFrom(cls)) {
            if (zza(zzb)) {
                return zzjg.a(cls, zzb, zzjj.b(), zzim.b(), zzju.zzkf(), zzhj.a(), zzix.b());
            }
            return zzjg.a(cls, zzb, zzjj.b(), zzim.b(), zzju.zzkf(), (zzhh<?>) null, zzix.b());
        } else if (zza(zzb)) {
            return zzjg.a(cls, zzb, zzjj.a(), zzim.a(), zzju.zzkd(), zzhj.b(), zzix.a());
        } else {
            return zzjg.a(cls, zzb, zzjj.a(), zzim.a(), zzju.zzke(), (zzhh<?>) null, zzix.a());
        }
    }
}

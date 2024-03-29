package com.google.android.gms.flags;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class Singletons {
    private static Singletons zzl;
    private final FlagRegistry zzm = new FlagRegistry();
    private final zzb zzn = new zzb();

    static {
        Singletons singletons = new Singletons();
        synchronized (Singletons.class) {
            zzl = singletons;
        }
    }

    private Singletons() {
    }

    @KeepForSdk
    public static FlagRegistry flagRegistry() {
        return zzc().zzm;
    }

    private static Singletons zzc() {
        Singletons singletons;
        synchronized (Singletons.class) {
            singletons = zzl;
        }
        return singletons;
    }

    public static zzb zzd() {
        return zzc().zzn;
    }
}

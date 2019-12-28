package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzgt {
    private static final zzgt zzalc = new zzgt();
    private final zzha zzald = new zzfv();
    private final ConcurrentMap<Class<?>, zzgx<?>> zzale = new ConcurrentHashMap();

    private zzgt() {
    }

    public static zzgt zzvy() {
        return zzalc;
    }

    public final <T> zzgx<T> zzf(Class<T> cls) {
        zzez.a(cls, "messageType");
        zzgx<T> zzgx = (zzgx) this.zzale.get(cls);
        if (zzgx != null) {
            return zzgx;
        }
        zzgx<T> zze = this.zzald.zze(cls);
        zzez.a(cls, "messageType");
        zzez.a(zze, "schema");
        zzgx<T> putIfAbsent = this.zzale.putIfAbsent(cls, zze);
        return putIfAbsent != null ? putIfAbsent : zze;
    }

    public final <T> zzgx<T> zzw(T t) {
        return zzf(t.getClass());
    }
}

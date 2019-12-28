package com.google.android.gms.internal.firebase_auth;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzjo {
    private static final zzjo zzadn = new zzjo();
    private final zzjr zzado = new zzip();
    private final ConcurrentMap<Class<?>, zzjs<?>> zzadp = new ConcurrentHashMap();

    private zzjo() {
    }

    public static zzjo zzjv() {
        return zzadn;
    }

    public final <T> zzjs<T> zzf(Class<T> cls) {
        zzht.a(cls, "messageType");
        zzjs<T> zzjs = (zzjs) this.zzadp.get(cls);
        if (zzjs != null) {
            return zzjs;
        }
        zzjs<T> zze = this.zzado.zze(cls);
        zzht.a(cls, "messageType");
        zzht.a(zze, "schema");
        zzjs<T> putIfAbsent = this.zzadp.putIfAbsent(cls, zze);
        return putIfAbsent != null ? putIfAbsent : zze;
    }

    public final <T> zzjs<T> zzr(T t) {
        return zzf(t.getClass());
    }
}

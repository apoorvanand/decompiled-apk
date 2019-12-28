package com.google.android.gms.internal.firebase_ml;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzuz {
    private static final zzuz zzbpl = new zzuz();
    private final zzvd zzbpm = new zzub();
    private final ConcurrentMap<Class<?>, zzvc<?>> zzbpn = new ConcurrentHashMap();

    private zzuz() {
    }

    public static zzuz zzrc() {
        return zzbpl;
    }

    public final <T> zzvc<T> zzad(T t) {
        return zzl(t.getClass());
    }

    public final <T> zzvc<T> zzl(Class<T> cls) {
        zzte.a(cls, "messageType");
        zzvc<T> zzvc = (zzvc) this.zzbpn.get(cls);
        if (zzvc != null) {
            return zzvc;
        }
        zzvc<T> zzk = this.zzbpm.zzk(cls);
        zzte.a(cls, "messageType");
        zzte.a(zzk, "schema");
        zzvc<T> putIfAbsent = this.zzbpn.putIfAbsent(cls, zzk);
        return putIfAbsent != null ? putIfAbsent : zzk;
    }
}

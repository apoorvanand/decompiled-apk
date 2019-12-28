package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zztc;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzsp {
    static final zzsp a = new zzsp(true);
    private static volatile boolean zzbiz = false;
    private static final Class<?> zzbja = zzoy();
    private final Map<zzsq, zztc.zze<?, ?>> zzbjc;

    zzsp() {
        this.zzbjc = new HashMap();
    }

    private zzsp(boolean z) {
        this.zzbjc = Collections.emptyMap();
    }

    private static Class<?> zzoy() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzsp zzoz() {
        return zzso.zzox();
    }

    public final <ContainingType extends zzum> zztc.zze<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return this.zzbjc.get(new zzsq(containingtype, i));
    }
}

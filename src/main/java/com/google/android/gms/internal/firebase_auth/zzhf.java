package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzhs;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzhf {
    static final zzhf a = new zzhf(true);
    private static volatile boolean zzxb = false;
    private static final Class<?> zzxc = zzhp();
    private static volatile zzhf zzxd;
    private static volatile zzhf zzxe;
    private final Map<zza, zzhs.zze<?, ?>> zzxg;

    static final class zza {
        private final int number;
        private final Object object;

        zza(Object obj, int i) {
            this.object = obj;
            this.number = i;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return this.object == zza.object && this.number == zza.number;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.object) * 65535) + this.number;
        }
    }

    zzhf() {
        this.zzxg = new HashMap();
    }

    private zzhf(boolean z) {
        this.zzxg = Collections.emptyMap();
    }

    static zzhf a() {
        return zzhr.a(zzhf.class);
    }

    private static Class<?> zzhp() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzhf zzhq() {
        zzhf zzhf = zzxd;
        if (zzhf == null) {
            synchronized (zzhf.class) {
                zzhf = zzxd;
                if (zzhf == null) {
                    zzhf = zzhd.zzhn();
                    zzxd = zzhf;
                }
            }
        }
        return zzhf;
    }

    public static zzhf zzhr() {
        zzhf zzhf = zzxe;
        if (zzhf == null) {
            synchronized (zzhf.class) {
                zzhf = zzxe;
                if (zzhf == null) {
                    zzhf = zzhd.a();
                    zzxe = zzhf;
                }
            }
        }
        return zzhf;
    }

    public final <ContainingType extends zzjc> zzhs.zze<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return this.zzxg.get(new zza(containingtype, i));
    }
}

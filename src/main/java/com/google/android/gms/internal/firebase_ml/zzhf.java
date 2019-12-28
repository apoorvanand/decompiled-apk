package com.google.android.gms.internal.firebase_ml;

import java.lang.reflect.Field;
import java.util.Map;

public final class zzhf {
    private final Map<String, zzhg> zzyi = new zzhe();
    private final Map<Field, zzhg> zzyj = new zzhe();
    private final Object zzyk;

    public zzhf(Object obj) {
        this.zzyk = obj;
    }

    public final void zza(Field field, Class<?> cls, Object obj) {
        zzhg zzhg = this.zzyj.get(field);
        if (zzhg == null) {
            zzhg = new zzhg(cls);
            this.zzyj.put(field, zzhg);
        }
        zzky.checkArgument(cls == zzhg.a);
        zzhg.b.add(obj);
    }

    public final void zzgk() {
        for (Map.Entry next : this.zzyi.entrySet()) {
            ((Map) this.zzyk).put((String) next.getKey(), ((zzhg) next.getValue()).a());
        }
        for (Map.Entry next2 : this.zzyj.entrySet()) {
            zzhr.zza((Field) next2.getKey(), this.zzyk, ((zzhg) next2.getValue()).a());
        }
    }
}

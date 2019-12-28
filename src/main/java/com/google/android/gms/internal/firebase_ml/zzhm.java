package com.google.android.gms.internal.firebase_ml;

import java.util.AbstractMap;
import java.util.Set;

final class zzhm extends AbstractMap<String, Object> {
    final Object a;
    final zzhj b;

    zzhm(Object obj, boolean z) {
        this.a = obj;
        this.b = zzhj.zza(obj.getClass(), z);
        zzky.checkArgument(!this.b.isEnum());
    }

    public final boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    public final /* synthetic */ Set entrySet() {
        return new zzhp(this);
    }

    public final Object get(Object obj) {
        zzhr zzal;
        if ((obj instanceof String) && (zzal = this.b.zzal((String) obj)) != null) {
            return zzal.zzh(this.a);
        }
        return null;
    }

    public final /* synthetic */ Object put(Object obj, Object obj2) {
        String str = (String) obj;
        zzhr zzal = this.b.zzal(str);
        String valueOf = String.valueOf(str);
        zzky.checkNotNull(zzal, valueOf.length() != 0 ? "no field of key ".concat(valueOf) : new String("no field of key "));
        Object zzh = zzal.zzh(this.a);
        zzal.zzb(this.a, zzky.checkNotNull(obj2));
        return zzh;
    }
}

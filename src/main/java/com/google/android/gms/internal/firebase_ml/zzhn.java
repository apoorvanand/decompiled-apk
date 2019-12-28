package com.google.android.gms.internal.firebase_ml;

import java.util.Locale;
import java.util.Map;

final class zzhn implements Map.Entry<String, Object> {
    private Object zzzh;
    private final zzhr zzzi;
    private final /* synthetic */ zzhm zzzj;

    zzhn(zzhm zzhm, zzhr zzhr, Object obj) {
        this.zzzj = zzhm;
        this.zzzi = zzhr;
        this.zzzh = zzky.checkNotNull(obj);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return ((String) getKey()).equals(entry.getKey()) && getValue().equals(entry.getValue());
    }

    public final /* synthetic */ Object getKey() {
        String name = this.zzzi.getName();
        return this.zzzj.b.zzgm() ? name.toLowerCase(Locale.US) : name;
    }

    public final Object getValue() {
        return this.zzzh;
    }

    public final int hashCode() {
        return ((String) getKey()).hashCode() ^ getValue().hashCode();
    }

    public final Object setValue(Object obj) {
        Object obj2 = this.zzzh;
        this.zzzh = zzky.checkNotNull(obj);
        this.zzzi.zzb(this.zzzj.a, obj);
        return obj2;
    }
}

package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.Map;

final class zzbd<K, V> extends zzbc<Map.Entry<K, V>> {
    /* access modifiers changed from: private */
    public final transient int size;
    private final transient zzaz<K, V> zzhe;
    /* access modifiers changed from: private */
    public final transient Object[] zzhf;
    private final transient int zzhg = 0;

    zzbd(zzaz<K, V> zzaz, Object[] objArr, int i, int i2) {
        this.zzhe = zzaz;
        this.zzhf = objArr;
        this.size = i2;
    }

    /* access modifiers changed from: package-private */
    public final int a(Object[] objArr, int i) {
        return zzcd().a(objArr, i);
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            return value != null && value.equals(this.zzhe.get(key));
        }
    }

    /* access modifiers changed from: package-private */
    public final zzay<Map.Entry<K, V>> d() {
        return new zzbg(this);
    }

    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }

    public final int size() {
        return this.size;
    }

    public final zzbk<Map.Entry<K, V>> zzbz() {
        return (zzbk) zzcd().iterator();
    }
}

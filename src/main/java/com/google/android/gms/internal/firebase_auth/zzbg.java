package com.google.android.gms.internal.firebase_auth;

import java.util.AbstractMap;
import java.util.Map;

final class zzbg extends zzay<Map.Entry<K, V>> {
    private final /* synthetic */ zzbd zzhj;

    zzbg(zzbd zzbd) {
        this.zzhj = zzbd;
    }

    public final /* synthetic */ Object get(int i) {
        zzaj.zza(i, this.zzhj.size);
        Object[] b = this.zzhj.zzhf;
        int i2 = i * 2;
        zzbd zzbd = this.zzhj;
        Object obj = b[i2];
        Object[] b2 = zzbd.zzhf;
        zzbd zzbd2 = this.zzhj;
        return new AbstractMap.SimpleImmutableEntry(obj, b2[i2 + 1]);
    }

    public final int size() {
        return this.zzhj.size;
    }
}

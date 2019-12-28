package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

final class zzhd extends zzhj {
    private final /* synthetic */ zzhc zzalq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzhd(zzhc zzhc) {
        super(zzhc, (zzhb) null);
        this.zzalq = zzhc;
    }

    /* synthetic */ zzhd(zzhc zzhc, zzhb zzhb) {
        this(zzhc);
    }

    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzhe(this.zzalq, (zzhb) null);
    }
}

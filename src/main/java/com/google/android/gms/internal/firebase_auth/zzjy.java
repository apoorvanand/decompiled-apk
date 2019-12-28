package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.Map;

final class zzjy extends zzke {
    private final /* synthetic */ zzjt zzaeb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzjy(zzjt zzjt) {
        super(zzjt, (zzjw) null);
        this.zzaeb = zzjt;
    }

    /* synthetic */ zzjy(zzjt zzjt, zzjw zzjw) {
        this(zzjt);
    }

    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzjv(this.zzaeb, (zzjw) null);
    }
}

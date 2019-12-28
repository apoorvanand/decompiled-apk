package com.google.android.gms.internal.firebase_ml;

import java.util.Iterator;
import java.util.Map;

final class zzvi extends zzvo {
    private final /* synthetic */ zzvf zzbqa;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zzvi(zzvf zzvf) {
        super(zzvf, (zzvg) null);
        this.zzbqa = zzvf;
    }

    /* synthetic */ zzvi(zzvf zzvf, zzvg zzvg) {
        this(zzvf);
    }

    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzvh(this.zzbqa, (zzvg) null);
    }
}

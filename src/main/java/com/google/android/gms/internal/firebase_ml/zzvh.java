package com.google.android.gms.internal.firebase_ml;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzvh implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzbpz;
    private final /* synthetic */ zzvf zzbqa;

    private zzvh(zzvf zzvf) {
        this.zzbqa = zzvf;
        this.pos = this.zzbqa.zzbpu.size();
    }

    /* synthetic */ zzvh(zzvf zzvf, zzvg zzvg) {
        this(zzvf);
    }

    private final Iterator<Map.Entry<K, V>> zzrq() {
        if (this.zzbpz == null) {
            this.zzbpz = this.zzbqa.zzbpx.entrySet().iterator();
        }
        return this.zzbpz;
    }

    public final boolean hasNext() {
        int i = this.pos;
        return (i > 0 && i <= this.zzbqa.zzbpu.size()) || zzrq().hasNext();
    }

    public final /* synthetic */ Object next() {
        Object obj;
        if (zzrq().hasNext()) {
            obj = zzrq().next();
        } else {
            List b = this.zzbqa.zzbpu;
            int i = this.pos - 1;
            this.pos = i;
            obj = b.get(i);
        }
        return (Map.Entry) obj;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

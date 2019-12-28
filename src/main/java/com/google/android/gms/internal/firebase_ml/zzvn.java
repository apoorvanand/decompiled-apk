package com.google.android.gms.internal.firebase_ml;

import java.util.Iterator;
import java.util.Map;

final class zzvn implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzbpz;
    private final /* synthetic */ zzvf zzbqa;
    private boolean zzbqe;

    private zzvn(zzvf zzvf) {
        this.zzbqa = zzvf;
        this.pos = -1;
    }

    /* synthetic */ zzvn(zzvf zzvf, zzvg zzvg) {
        this(zzvf);
    }

    private final Iterator<Map.Entry<K, V>> zzrq() {
        if (this.zzbpz == null) {
            this.zzbpz = this.zzbqa.zzbpv.entrySet().iterator();
        }
        return this.zzbpz;
    }

    public final boolean hasNext() {
        return this.pos + 1 < this.zzbqa.zzbpu.size() || (!this.zzbqa.zzbpv.isEmpty() && zzrq().hasNext());
    }

    public final /* synthetic */ Object next() {
        this.zzbqe = true;
        int i = this.pos + 1;
        this.pos = i;
        return (Map.Entry) (i < this.zzbqa.zzbpu.size() ? this.zzbqa.zzbpu.get(this.pos) : zzrq().next());
    }

    public final void remove() {
        if (this.zzbqe) {
            this.zzbqe = false;
            this.zzbqa.zzro();
            if (this.pos < this.zzbqa.zzbpu.size()) {
                zzvf zzvf = this.zzbqa;
                int i = this.pos;
                this.pos = i - 1;
                Object unused = zzvf.zzdh(i);
                return;
            }
            zzrq().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}

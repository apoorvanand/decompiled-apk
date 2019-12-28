package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map;

final class zzih implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzaak;
    private final /* synthetic */ zzhz zzaal;
    private boolean zzaap;

    private zzih(zzhz zzhz) {
        this.zzaal = zzhz;
        this.pos = -1;
    }

    /* synthetic */ zzih(zzhz zzhz, zzia zzia) {
        this(zzhz);
    }

    private final Iterator<Map.Entry<K, V>> zzgz() {
        if (this.zzaak == null) {
            this.zzaak = this.zzaal.zzaag.entrySet().iterator();
        }
        return this.zzaak;
    }

    public final boolean hasNext() {
        return this.pos + 1 < this.zzaal.zzaaf.size() || (!this.zzaal.zzaag.isEmpty() && zzgz().hasNext());
    }

    public final /* synthetic */ Object next() {
        this.zzaap = true;
        int i = this.pos + 1;
        this.pos = i;
        return (Map.Entry) (i < this.zzaal.zzaaf.size() ? this.zzaal.zzaaf.get(this.pos) : zzgz().next());
    }

    public final void remove() {
        if (this.zzaap) {
            this.zzaap = false;
            this.zzaal.zzgx();
            if (this.pos < this.zzaal.zzaaf.size()) {
                zzhz zzhz = this.zzaal;
                int i = this.pos;
                this.pos = i - 1;
                Object unused = zzhz.zzbq(i);
                return;
            }
            zzgz().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}

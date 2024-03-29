package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzib implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzaak;
    private final /* synthetic */ zzhz zzaal;

    private zzib(zzhz zzhz) {
        this.zzaal = zzhz;
        this.pos = this.zzaal.zzaaf.size();
    }

    /* synthetic */ zzib(zzhz zzhz, zzia zzia) {
        this(zzhz);
    }

    private final Iterator<Map.Entry<K, V>> zzgz() {
        if (this.zzaak == null) {
            this.zzaak = this.zzaal.zzaai.entrySet().iterator();
        }
        return this.zzaak;
    }

    public final boolean hasNext() {
        int i = this.pos;
        return (i > 0 && i <= this.zzaal.zzaaf.size()) || zzgz().hasNext();
    }

    public final /* synthetic */ Object next() {
        Object obj;
        if (zzgz().hasNext()) {
            obj = zzgz().next();
        } else {
            List b = this.zzaal.zzaaf;
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

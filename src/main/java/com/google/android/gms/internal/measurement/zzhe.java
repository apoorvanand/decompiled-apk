package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzhe implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private final /* synthetic */ zzhc zzalq;
    private Iterator<Map.Entry<K, V>> zzalr;

    private zzhe(zzhc zzhc) {
        this.zzalq = zzhc;
        this.pos = this.zzalq.zzall.size();
    }

    /* synthetic */ zzhe(zzhc zzhc, zzhb zzhb) {
        this(zzhc);
    }

    private final Iterator<Map.Entry<K, V>> zzwm() {
        if (this.zzalr == null) {
            this.zzalr = this.zzalq.zzalo.entrySet().iterator();
        }
        return this.zzalr;
    }

    public final boolean hasNext() {
        int i = this.pos;
        return (i > 0 && i <= this.zzalq.zzall.size()) || zzwm().hasNext();
    }

    public final /* synthetic */ Object next() {
        Object obj;
        if (zzwm().hasNext()) {
            obj = zzwm().next();
        } else {
            List b = this.zzalq.zzall;
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

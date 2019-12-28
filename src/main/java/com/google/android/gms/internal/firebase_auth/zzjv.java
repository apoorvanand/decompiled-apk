package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzjv implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzaea;
    private final /* synthetic */ zzjt zzaeb;

    private zzjv(zzjt zzjt) {
        this.zzaeb = zzjt;
        this.pos = this.zzaeb.zzadr.size();
    }

    /* synthetic */ zzjv(zzjt zzjt, zzjw zzjw) {
        this(zzjt);
    }

    private final Iterator<Map.Entry<K, V>> zzki() {
        if (this.zzaea == null) {
            this.zzaea = this.zzaeb.zzadu.entrySet().iterator();
        }
        return this.zzaea;
    }

    public final boolean hasNext() {
        int i = this.pos;
        return (i > 0 && i <= this.zzaeb.zzadr.size()) || zzki().hasNext();
    }

    public final /* synthetic */ Object next() {
        Object obj;
        if (zzki().hasNext()) {
            obj = zzki().next();
        } else {
            List b = this.zzaeb.zzadr;
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

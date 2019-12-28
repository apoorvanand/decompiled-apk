package com.google.android.gms.internal.firebase_ml;

import java.util.Iterator;
import java.util.Map;

final class zzts<K> implements Iterator<Map.Entry<K, Object>> {
    private Iterator<Map.Entry<K, Object>> zzbns;

    public zzts(Iterator<Map.Entry<K, Object>> it) {
        this.zzbns = it;
    }

    public final boolean hasNext() {
        return this.zzbns.hasNext();
    }

    public final /* synthetic */ Object next() {
        Map.Entry next = this.zzbns.next();
        return next.getValue() instanceof zztp ? new zztr(next) : next;
    }

    public final void remove() {
        this.zzbns.remove();
    }
}

package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.Map;

final class zzii<K> implements Iterator<Map.Entry<K, Object>> {
    private Iterator<Map.Entry<K, Object>> zzaby;

    public zzii(Iterator<Map.Entry<K, Object>> it) {
        this.zzaby = it;
    }

    public final boolean hasNext() {
        return this.zzaby.hasNext();
    }

    public final /* synthetic */ Object next() {
        Map.Entry next = this.zzaby.next();
        return next.getValue() instanceof zzid ? new zzif(next) : next;
    }

    public final void remove() {
        this.zzaby.remove();
    }
}

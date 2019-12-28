package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

final class zzfo<K> implements Iterator<Map.Entry<K, Object>> {
    private Iterator<Map.Entry<K, Object>> zzajp;

    public zzfo(Iterator<Map.Entry<K, Object>> it) {
        this.zzajp = it;
    }

    public final boolean hasNext() {
        return this.zzajp.hasNext();
    }

    public final /* synthetic */ Object next() {
        Map.Entry next = this.zzajp.next();
        return next.getValue() instanceof zzfj ? new zzfl(next) : next;
    }

    public final void remove() {
        this.zzajp.remove();
    }
}

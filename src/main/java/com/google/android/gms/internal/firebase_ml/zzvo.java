package com.google.android.gms.internal.firebase_ml;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

class zzvo extends AbstractSet<Map.Entry<K, V>> {
    private final /* synthetic */ zzvf zzbqa;

    private zzvo(zzvf zzvf) {
        this.zzbqa = zzvf;
    }

    /* synthetic */ zzvo(zzvf zzvf, zzvg zzvg) {
        this(zzvf);
    }

    public /* synthetic */ boolean add(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zzbqa.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    public void clear() {
        this.zzbqa.clear();
    }

    public boolean contains(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.zzbqa.get(entry.getKey());
        Object value = entry.getValue();
        if (obj2 != value) {
            return obj2 != null && obj2.equals(value);
        }
        return true;
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return new zzvn(this.zzbqa, (zzvg) null);
    }

    public boolean remove(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zzbqa.remove(entry.getKey());
        return true;
    }

    public int size() {
        return this.zzbqa.size();
    }
}

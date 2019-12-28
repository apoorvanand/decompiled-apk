package com.google.android.gms.internal.firebase_auth;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

class zzke extends AbstractSet<Map.Entry<K, V>> {
    private final /* synthetic */ zzjt zzaeb;

    private zzke(zzjt zzjt) {
        this.zzaeb = zzjt;
    }

    /* synthetic */ zzke(zzjt zzjt, zzjw zzjw) {
        this(zzjt);
    }

    public /* synthetic */ boolean add(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zzaeb.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    public void clear() {
        this.zzaeb.clear();
    }

    public boolean contains(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.zzaeb.get(entry.getKey());
        Object value = entry.getValue();
        if (obj2 != value) {
            return obj2 != null && obj2.equals(value);
        }
        return true;
    }

    public Iterator<Map.Entry<K, V>> iterator() {
        return new zzkb(this.zzaeb, (zzjw) null);
    }

    public boolean remove(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zzaeb.remove(entry.getKey());
        return true;
    }

    public int size() {
        return this.zzaeb.size();
    }
}

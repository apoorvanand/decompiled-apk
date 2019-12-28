package com.google.android.gms.internal.firebase_auth;

import java.util.Map;

final class zzif<K> implements Map.Entry<K, Object> {
    private Map.Entry<K, zzid> zzabu;

    private zzif(Map.Entry<K, zzid> entry) {
        this.zzabu = entry;
    }

    public final K getKey() {
        return this.zzabu.getKey();
    }

    public final Object getValue() {
        if (this.zzabu.getValue() == null) {
            return null;
        }
        return zzid.zzja();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzjc) {
            return this.zzabu.getValue().zzj((zzjc) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzid zzjc() {
        return this.zzabu.getValue();
    }
}

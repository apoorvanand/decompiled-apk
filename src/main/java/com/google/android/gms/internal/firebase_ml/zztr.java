package com.google.android.gms.internal.firebase_ml;

import java.util.Map;

final class zztr<K> implements Map.Entry<K, Object> {
    private Map.Entry<K, zztp> zzbnr;

    private zztr(Map.Entry<K, zztp> entry) {
        this.zzbnr = entry;
    }

    public final K getKey() {
        return this.zzbnr.getKey();
    }

    public final Object getValue() {
        if (this.zzbnr.getValue() == null) {
            return null;
        }
        return zztp.zzqh();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzum) {
            return this.zzbnr.getValue().zzi((zzum) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zztp zzqi() {
        return this.zzbnr.getValue();
    }
}

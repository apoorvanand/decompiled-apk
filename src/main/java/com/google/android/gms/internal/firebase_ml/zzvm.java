package com.google.android.gms.internal.firebase_ml;

import java.util.Map;

final class zzvm implements Comparable<zzvm>, Map.Entry<K, V> {
    private V value;
    private final /* synthetic */ zzvf zzbqa;
    private final K zzbqd;

    zzvm(zzvf zzvf, K k, V v) {
        this.zzbqa = zzvf;
        this.zzbqd = k;
        this.value = v;
    }

    zzvm(zzvf zzvf, Map.Entry<K, V> entry) {
        this(zzvf, (Comparable) entry.getKey(), entry.getValue());
    }

    private static boolean equals(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public final /* synthetic */ int compareTo(Object obj) {
        return ((Comparable) getKey()).compareTo((Comparable) ((zzvm) obj).getKey());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return equals(this.zzbqd, entry.getKey()) && equals(this.value, entry.getValue());
    }

    public final /* synthetic */ Object getKey() {
        return this.zzbqd;
    }

    public final V getValue() {
        return this.value;
    }

    public final int hashCode() {
        K k = this.zzbqd;
        int i = 0;
        int hashCode = k == null ? 0 : k.hashCode();
        V v = this.value;
        if (v != null) {
            i = v.hashCode();
        }
        return hashCode ^ i;
    }

    public final V setValue(V v) {
        this.zzbqa.zzro();
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzbqd);
        String valueOf2 = String.valueOf(this.value);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(valueOf2).length());
        sb.append(valueOf);
        sb.append("=");
        sb.append(valueOf2);
        return sb.toString();
    }
}

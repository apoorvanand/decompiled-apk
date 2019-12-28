package com.google.android.gms.internal.firebase_auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public abstract class zzaz<K, V> implements Serializable, Map<K, V> {
    private static final Map.Entry<?, ?>[] zzgw = new Map.Entry[0];
    private transient zzbc<Map.Entry<K, V>> zzgx;
    private transient zzbc<K> zzgy;
    private transient zzav<V> zzgz;

    zzaz() {
    }

    public static <K, V> zzaz<K, V> zzb(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        zzat.a(k, v);
        zzat.a(k2, v2);
        zzat.a(k3, v3);
        zzat.a(k4, v4);
        return zzbe.a(4, new Object[]{k, v, k2, v2, k3, v3, k4, v4});
    }

    /* access modifiers changed from: package-private */
    public abstract zzbc<Map.Entry<K, V>> a();

    /* access modifiers changed from: package-private */
    public abstract zzbc<K> b();

    /* access modifiers changed from: package-private */
    public abstract zzav<V> c();

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean containsKey(@NullableDecl Object obj) {
        return get(obj) != null;
    }

    public boolean containsValue(@NullableDecl Object obj) {
        return ((zzav) values()).contains(obj);
    }

    public /* synthetic */ Set entrySet() {
        zzbc<Map.Entry<K, V>> zzbc = this.zzgx;
        if (zzbc != null) {
            return zzbc;
        }
        zzbc<Map.Entry<K, V>> a = a();
        this.zzgx = a;
        return a;
    }

    public boolean equals(@NullableDecl Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    public abstract V get(@NullableDecl Object obj);

    public final V getOrDefault(@NullableDecl Object obj, @NullableDecl V v) {
        V v2 = get(obj);
        return v2 != null ? v2 : v;
    }

    public int hashCode() {
        return zzbh.a((zzbc) entrySet());
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public /* synthetic */ Set keySet() {
        zzbc<K> zzbc = this.zzgy;
        if (zzbc != null) {
            return zzbc;
        }
        zzbc<K> b = b();
        this.zzgy = b;
        return b;
    }

    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        int size = size();
        if (size >= 0) {
            StringBuilder sb = new StringBuilder((int) Math.min(((long) size) << 3, 1073741824));
            sb.append('{');
            boolean z = true;
            for (Map.Entry entry : entrySet()) {
                if (!z) {
                    sb.append(", ");
                }
                z = false;
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
            }
            sb.append('}');
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf("size").length() + 40);
        sb2.append("size");
        sb2.append(" cannot be negative but was: ");
        sb2.append(size);
        throw new IllegalArgumentException(sb2.toString());
    }

    public /* synthetic */ Collection values() {
        zzav<V> zzav = this.zzgz;
        if (zzav != null) {
            return zzav;
        }
        zzav<V> c = c();
        this.zzgz = c;
        return c;
    }
}

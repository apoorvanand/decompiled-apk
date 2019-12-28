package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

final class zzbf<K> extends zzbc<K> {
    private final transient zzay<K> zzgu;
    private final transient zzaz<K, ?> zzhe;

    zzbf(zzaz<K, ?> zzaz, zzay<K> zzay) {
        this.zzhe = zzaz;
        this.zzgu = zzay;
    }

    /* access modifiers changed from: package-private */
    public final int a(Object[] objArr, int i) {
        return zzcd().a(objArr, i);
    }

    public final boolean contains(@NullableDecl Object obj) {
        return this.zzhe.get(obj) != null;
    }

    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }

    public final int size() {
        return this.zzhe.size();
    }

    public final zzbk<K> zzbz() {
        return (zzbk) zzcd().iterator();
    }

    public final zzay<K> zzcd() {
        return this.zzgu;
    }
}

package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public abstract class zzbc<E> extends zzav<E> implements Set<E> {
    @NullableDecl
    private transient zzay<E> zzhd;

    zzbc() {
    }

    /* access modifiers changed from: package-private */
    public zzay<E> d() {
        return zzay.a(toArray());
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        return zzbh.a(this, obj);
    }

    public int hashCode() {
        return zzbh.a(this);
    }

    public /* synthetic */ Iterator iterator() {
        return iterator();
    }

    public zzay<E> zzcd() {
        zzay<E> zzay = this.zzhd;
        if (zzay != null) {
            return zzay;
        }
        zzay<E> d = d();
        this.zzhd = d;
        return d;
    }
}

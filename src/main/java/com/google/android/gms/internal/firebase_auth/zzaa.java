package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

abstract class zzaa<T> implements Iterator<T> {
    private int zzgb = zzab.zzge;
    @NullableDecl
    private T zzgc;

    protected zzaa() {
    }

    /* access modifiers changed from: protected */
    public abstract T a();

    /* access modifiers changed from: protected */
    @NullableDecl
    public final T b() {
        this.zzgb = zzab.zzgf;
        return null;
    }

    public final boolean hasNext() {
        if (this.zzgb != zzab.zzgg) {
            switch (zzac.a[this.zzgb - 1]) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    this.zzgb = zzab.zzgg;
                    this.zzgc = a();
                    if (this.zzgb == zzab.zzgf) {
                        return false;
                    }
                    this.zzgb = zzab.zzgd;
                    return true;
            }
        } else {
            throw new IllegalStateException();
        }
    }

    public final T next() {
        if (hasNext()) {
            this.zzgb = zzab.zzge;
            T t = this.zzgc;
            this.zzgc = null;
            return t;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

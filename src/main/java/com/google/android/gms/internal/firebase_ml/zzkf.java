package com.google.android.gms.internal.firebase_ml;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

abstract class zzkf<T> implements Iterator<T> {
    private int zzaax = zzkh.zzabb;
    @NullableDecl
    private T zzaay;

    protected zzkf() {
    }

    /* access modifiers changed from: protected */
    public abstract T a();

    /* access modifiers changed from: protected */
    @NullableDecl
    public final T b() {
        this.zzaax = zzkh.zzabc;
        return null;
    }

    public final boolean hasNext() {
        zzky.checkState(this.zzaax != zzkh.zzabd);
        switch (zzkg.a[this.zzaax - 1]) {
            case 1:
                return true;
            case 2:
                return false;
            default:
                this.zzaax = zzkh.zzabd;
                this.zzaay = a();
                if (this.zzaax == zzkh.zzabc) {
                    return false;
                }
                this.zzaax = zzkh.zzaba;
                return true;
        }
    }

    public final T next() {
        if (hasNext()) {
            this.zzaax = zzkh.zzabb;
            T t = this.zzaay;
            this.zzaay = null;
            return t;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

package com.google.android.gms.internal.firebase_auth;

import java.util.NoSuchElementException;

abstract class zzau<E> extends zzbj<E> {
    private int position;
    private final int size;

    protected zzau(int i, int i2) {
        zzaj.zzb(i2, i);
        this.size = i;
        this.position = i2;
    }

    /* access modifiers changed from: protected */
    public abstract E a(int i);

    public final boolean hasNext() {
        return this.position < this.size;
    }

    public final boolean hasPrevious() {
        return this.position > 0;
    }

    public final E next() {
        if (hasNext()) {
            int i = this.position;
            this.position = i + 1;
            return a(i);
        }
        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return this.position;
    }

    public final E previous() {
        if (hasPrevious()) {
            int i = this.position - 1;
            this.position = i;
            return a(i);
        }
        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return this.position - 1;
    }
}

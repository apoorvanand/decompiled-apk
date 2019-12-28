package com.google.android.gms.internal.firebase_ml;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzii implements Iterator<T> {
    private int index = 0;
    private final int length = Array.getLength(this.zzaak.a);
    private final /* synthetic */ zzih zzaak;

    zzii(zzih zzih) {
        this.zzaak = zzih;
    }

    public final boolean hasNext() {
        return this.index < this.length;
    }

    public final T next() {
        if (hasNext()) {
            Object obj = this.zzaak.a;
            int i = this.index;
            this.index = i + 1;
            return Array.get(obj, i);
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
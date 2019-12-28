package com.google.android.gms.internal.firebase_ml;

import java.util.ListIterator;

final class zzvy implements ListIterator<String> {
    private ListIterator<String> zzbqk = this.zzbqm.zzbqj.listIterator(this.zzbql);
    private final /* synthetic */ int zzbql;
    private final /* synthetic */ zzvx zzbqm;

    zzvy(zzvx zzvx, int i) {
        this.zzbqm = zzvx;
        this.zzbql = i;
    }

    public final /* synthetic */ void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext() {
        return this.zzbqk.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zzbqk.hasPrevious();
    }

    public final /* synthetic */ Object next() {
        return this.zzbqk.next();
    }

    public final int nextIndex() {
        return this.zzbqk.nextIndex();
    }

    public final /* synthetic */ Object previous() {
        return this.zzbqk.previous();
    }

    public final int previousIndex() {
        return this.zzbqk.previousIndex();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}

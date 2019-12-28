package com.google.android.gms.internal.firebase_ml;

import java.util.Iterator;

final class zzvz implements Iterator<String> {
    private final /* synthetic */ zzvx zzbqm;
    private Iterator<String> zzbqn = this.zzbqm.zzbqj.iterator();

    zzvz(zzvx zzvx) {
        this.zzbqm = zzvx;
    }

    public final boolean hasNext() {
        return this.zzbqn.hasNext();
    }

    public final /* synthetic */ Object next() {
        return this.zzbqn.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

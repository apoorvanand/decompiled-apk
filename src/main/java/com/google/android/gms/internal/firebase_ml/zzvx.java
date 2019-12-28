package com.google.android.gms.internal.firebase_ml;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzvx extends AbstractList<String> implements zztv, RandomAccess {
    /* access modifiers changed from: private */
    public final zztv zzbqj;

    public zzvx(zztv zztv) {
        this.zzbqj = zztv;
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.zzbqj.get(i);
    }

    public final Object getRaw(int i) {
        return this.zzbqj.getRaw(i);
    }

    public final Iterator<String> iterator() {
        return new zzvz(this);
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzvy(this, i);
    }

    public final int size() {
        return this.zzbqj.size();
    }

    public final void zzc(zzru zzru) {
        throw new UnsupportedOperationException();
    }

    public final List<?> zzqj() {
        return this.zzbqj.zzqj();
    }

    public final zztv zzqk() {
        return this;
    }
}

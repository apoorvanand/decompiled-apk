package com.google.android.gms.internal.firebase_ml;

import java.util.ArrayList;
import java.util.List;

final class zzva<E> extends zzro<E> {
    private static final zzva<Object> zzbpo;
    private final List<E> zzbny;

    static {
        zzva<Object> zzva = new zzva<>(new ArrayList(0));
        zzbpo = zzva;
        zzva.zzoh();
    }

    zzva() {
        this(new ArrayList(10));
    }

    private zzva(List<E> list) {
        this.zzbny = list;
    }

    public static <E> zzva<E> zzrd() {
        return zzbpo;
    }

    public final void add(int i, E e) {
        a();
        this.zzbny.add(i, e);
        this.modCount++;
    }

    public final E get(int i) {
        return this.zzbny.get(i);
    }

    public final E remove(int i) {
        a();
        E remove = this.zzbny.remove(i);
        this.modCount++;
        return remove;
    }

    public final E set(int i, E e) {
        a();
        E e2 = this.zzbny.set(i, e);
        this.modCount++;
        return e2;
    }

    public final int size() {
        return this.zzbny.size();
    }

    public final /* synthetic */ zztl zzcb(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzbny);
            return new zzva(arrayList);
        }
        throw new IllegalArgumentException();
    }
}

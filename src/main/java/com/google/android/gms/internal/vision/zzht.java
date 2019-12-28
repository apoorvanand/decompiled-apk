package com.google.android.gms.internal.vision;

import java.util.ArrayList;
import java.util.List;

final class zzht<E> extends zzef<E> {
    private static final zzht<Object> zzzz;
    private final List<E> zzym;

    static {
        zzht<Object> zzht = new zzht<>();
        zzzz = zzht;
        zzht.zzci();
    }

    zzht() {
        this(new ArrayList(10));
    }

    private zzht(List<E> list) {
        this.zzym = list;
    }

    public static <E> zzht<E> zzgm() {
        return zzzz;
    }

    public final void add(int i, E e) {
        a();
        this.zzym.add(i, e);
        this.modCount++;
    }

    public final E get(int i) {
        return this.zzym.get(i);
    }

    public final E remove(int i) {
        a();
        E remove = this.zzym.remove(i);
        this.modCount++;
        return remove;
    }

    public final E set(int i, E e) {
        a();
        E e2 = this.zzym.set(i, e);
        this.modCount++;
        return e2;
    }

    public final int size() {
        return this.zzym.size();
    }

    public final /* synthetic */ zzge zzah(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzym);
            return new zzht(arrayList);
        }
        throw new IllegalArgumentException();
    }
}
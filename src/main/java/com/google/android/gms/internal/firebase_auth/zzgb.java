package com.google.android.gms.internal.firebase_auth;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

abstract class zzgb<E> extends AbstractList<E> implements zzhz<E> {
    private boolean zzvo = true;

    zzgb() {
    }

    /* access modifiers changed from: protected */
    public final void a() {
        if (!this.zzvo) {
            throw new UnsupportedOperationException();
        }
    }

    public void add(int i, E e) {
        a();
        super.add(i, e);
    }

    public boolean add(E e) {
        a();
        return super.add(e);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        a();
        return super.addAll(i, collection);
    }

    public boolean addAll(Collection<? extends E> collection) {
        a();
        return super.addAll(collection);
    }

    public void clear() {
        a();
        super.clear();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        if (!(obj instanceof RandomAccess)) {
            return super.equals(obj);
        }
        List list = (List) obj;
        int size = size();
        if (size != list.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    public E remove(int i) {
        a();
        return super.remove(i);
    }

    public boolean remove(Object obj) {
        a();
        return super.remove(obj);
    }

    public boolean removeAll(Collection<?> collection) {
        a();
        return super.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        a();
        return super.retainAll(collection);
    }

    public E set(int i, E e) {
        a();
        return super.set(i, e);
    }

    public boolean zzfx() {
        return this.zzvo;
    }

    public final void zzfy() {
        this.zzvo = false;
    }
}
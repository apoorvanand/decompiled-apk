package com.google.android.gms.internal.firebase_auth;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public abstract class zzav<E> extends AbstractCollection<E> implements Serializable {
    private static final Object[] zzgt = new Object[0];

    zzav() {
    }

    /* access modifiers changed from: package-private */
    public int a(Object[] objArr, int i) {
        zzbk zzbk = (zzbk) iterator();
        while (zzbk.hasNext()) {
            objArr[i] = zzbk.next();
            i++;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public Object[] a() {
        return null;
    }

    @Deprecated
    public final boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public int b() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public int c() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public abstract boolean contains(@NullableDecl Object obj);

    @Deprecated
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    public final Object[] toArray() {
        return toArray(zzgt);
    }

    public final <T> T[] toArray(T[] tArr) {
        zzaj.checkNotNull(tArr);
        int size = size();
        if (tArr.length < size) {
            Object[] a = a();
            if (a != null) {
                return Arrays.copyOfRange(a, b(), c(), tArr.getClass());
            }
            tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size);
        } else if (tArr.length > size) {
            tArr[size] = null;
        }
        a(tArr, 0);
        return tArr;
    }

    /* renamed from: zzbz */
    public abstract zzbk<E> iterator();

    public zzay<E> zzcd() {
        return isEmpty() ? zzay.zzce() : zzay.a(toArray());
    }
}

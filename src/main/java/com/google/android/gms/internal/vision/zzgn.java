package com.google.android.gms.internal.vision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zzgn extends zzef<String> implements zzgo, RandomAccess {
    private static final zzgn zzyk;
    private static final zzgo zzyl = zzyk;
    private final List<Object> zzym;

    static {
        zzgn zzgn = new zzgn();
        zzyk = zzgn;
        zzgn.zzci();
    }

    public zzgn() {
        this(10);
    }

    public zzgn(int i) {
        this((ArrayList<Object>) new ArrayList(i));
    }

    private zzgn(ArrayList<Object> arrayList) {
        this.zzym = arrayList;
    }

    private static String zzh(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof zzeo ? ((zzeo) obj).zzdk() : zzga.zzj((byte[]) obj);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        a();
        this.zzym.add(i, (String) obj);
        this.modCount++;
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        a();
        if (collection instanceof zzgo) {
            collection = ((zzgo) collection).zzft();
        }
        boolean addAll = this.zzym.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public final void clear() {
        a();
        this.zzym.clear();
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzym.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzeo) {
            zzeo zzeo = (zzeo) obj;
            String zzdk = zzeo.zzdk();
            if (zzeo.zzdl()) {
                this.zzym.set(i, zzdk);
            }
            return zzdk;
        }
        byte[] bArr = (byte[]) obj;
        String zzj = zzga.zzj(bArr);
        if (zzga.zzi(bArr)) {
            this.zzym.set(i, zzj);
        }
        return zzj;
    }

    public final Object getRaw(int i) {
        return this.zzym.get(i);
    }

    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public final /* synthetic */ Object remove(int i) {
        a();
        Object remove = this.zzym.remove(i);
        this.modCount++;
        return zzh(remove);
    }

    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        a();
        return zzh(this.zzym.set(i, (String) obj));
    }

    public final int size() {
        return this.zzym.size();
    }

    public final /* synthetic */ zzge zzah(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzym);
            return new zzgn((ArrayList<Object>) arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final void zzc(zzeo zzeo) {
        a();
        this.zzym.add(zzeo);
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ boolean zzch() {
        return super.zzch();
    }

    public final List<?> zzft() {
        return Collections.unmodifiableList(this.zzym);
    }

    public final zzgo zzfu() {
        return zzch() ? new zzir(this) : this;
    }
}

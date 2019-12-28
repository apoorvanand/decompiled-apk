package com.google.android.gms.internal.firebase_ml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class zztu extends zzro<String> implements zztv, RandomAccess {
    private static final zztu zzbnw;
    private static final zztv zzbnx = zzbnw;
    private final List<Object> zzbny;

    static {
        zztu zztu = new zztu();
        zzbnw = zztu;
        zztu.zzoh();
    }

    public zztu() {
        this(10);
    }

    public zztu(int i) {
        this((ArrayList<Object>) new ArrayList(i));
    }

    private zztu(ArrayList<Object> arrayList) {
        this.zzbny = arrayList;
    }

    private static String zzs(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof zzru ? ((zzru) obj).zzol() : zzte.zzk((byte[]) obj);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        a();
        this.zzbny.add(i, (String) obj);
        this.modCount++;
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        a();
        if (collection instanceof zztv) {
            collection = ((zztv) collection).zzqj();
        }
        boolean addAll = this.zzbny.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public final void clear() {
        a();
        this.zzbny.clear();
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.zzbny.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzru) {
            zzru zzru = (zzru) obj;
            String zzol = zzru.zzol();
            if (zzru.zzom()) {
                this.zzbny.set(i, zzol);
            }
            return zzol;
        }
        byte[] bArr = (byte[]) obj;
        String zzk = zzte.zzk(bArr);
        if (zzte.zzj(bArr)) {
            this.zzbny.set(i, zzk);
        }
        return zzk;
    }

    public final Object getRaw(int i) {
        return this.zzbny.get(i);
    }

    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public final /* synthetic */ Object remove(int i) {
        a();
        Object remove = this.zzbny.remove(i);
        this.modCount++;
        return zzs(remove);
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
        return zzs(this.zzbny.set(i, (String) obj));
    }

    public final int size() {
        return this.zzbny.size();
    }

    public final void zzc(zzru zzru) {
        a();
        this.zzbny.add(zzru);
        this.modCount++;
    }

    public final /* synthetic */ zztl zzcb(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.zzbny);
            return new zztu((ArrayList<Object>) arrayList);
        }
        throw new IllegalArgumentException();
    }

    public final /* bridge */ /* synthetic */ boolean zzog() {
        return super.zzog();
    }

    public final List<?> zzqj() {
        return Collections.unmodifiableList(this.zzbny);
    }

    public final zztv zzqk() {
        return zzog() ? new zzvx(this) : this;
    }
}

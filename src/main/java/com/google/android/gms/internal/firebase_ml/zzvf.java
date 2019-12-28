package com.google.android.gms.internal.firebase_ml;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzvf<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzbjg;
    private final int zzbpt;
    /* access modifiers changed from: private */
    public List<zzvm> zzbpu;
    /* access modifiers changed from: private */
    public Map<K, V> zzbpv;
    private volatile zzvo zzbpw;
    /* access modifiers changed from: private */
    public Map<K, V> zzbpx;
    private volatile zzvi zzbpy;

    private zzvf(int i) {
        this.zzbpt = i;
        this.zzbpu = Collections.emptyList();
        this.zzbpv = Collections.emptyMap();
        this.zzbpx = Collections.emptyMap();
    }

    /* synthetic */ zzvf(int i, zzvg zzvg) {
        this(i);
    }

    static <FieldDescriptorType extends zzsw<FieldDescriptorType>> zzvf<FieldDescriptorType, Object> a(int i) {
        return new zzvg(i);
    }

    private final int zza(K k) {
        int size = this.zzbpu.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) this.zzbpu.get(size).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            int compareTo2 = k.compareTo((Comparable) this.zzbpu.get(i2).getKey());
            if (compareTo2 < 0) {
                size = i2 - 1;
            } else if (compareTo2 <= 0) {
                return i2;
            } else {
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    /* access modifiers changed from: private */
    public final V zzdh(int i) {
        zzro();
        V value = this.zzbpu.remove(i).getValue();
        if (!this.zzbpv.isEmpty()) {
            Iterator it = zzrp().entrySet().iterator();
            this.zzbpu.add(new zzvm(this, (Map.Entry) it.next()));
            it.remove();
        }
        return value;
    }

    /* access modifiers changed from: private */
    public final void zzro() {
        if (this.zzbjg) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzrp() {
        zzro();
        if (this.zzbpv.isEmpty() && !(this.zzbpv instanceof TreeMap)) {
            this.zzbpv = new TreeMap();
            this.zzbpx = ((TreeMap) this.zzbpv).descendingMap();
        }
        return (SortedMap) this.zzbpv;
    }

    /* access modifiers changed from: package-private */
    public final Set<Map.Entry<K, V>> a() {
        if (this.zzbpy == null) {
            this.zzbpy = new zzvi(this, (zzvg) null);
        }
        return this.zzbpy;
    }

    public void clear() {
        zzro();
        if (!this.zzbpu.isEmpty()) {
            this.zzbpu.clear();
        }
        if (!this.zzbpv.isEmpty()) {
            this.zzbpv.clear();
        }
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzbpv.containsKey(comparable);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zzbpw == null) {
            this.zzbpw = new zzvo(this, (zzvg) null);
        }
        return this.zzbpw;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzvf)) {
            return super.equals(obj);
        }
        zzvf zzvf = (zzvf) obj;
        int size = size();
        if (size != zzvf.size()) {
            return false;
        }
        int zzrl = zzrl();
        if (zzrl != zzvf.zzrl()) {
            return entrySet().equals(zzvf.entrySet());
        }
        for (int i = 0; i < zzrl; i++) {
            if (!zzdg(i).equals(zzvf.zzdg(i))) {
                return false;
            }
        }
        if (zzrl != size) {
            return this.zzbpv.equals(zzvf.zzbpv);
        }
        return true;
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        return zza >= 0 ? this.zzbpu.get(zza).getValue() : this.zzbpv.get(comparable);
    }

    public int hashCode() {
        int zzrl = zzrl();
        int i = 0;
        for (int i2 = 0; i2 < zzrl; i2++) {
            i += this.zzbpu.get(i2).hashCode();
        }
        return this.zzbpv.size() > 0 ? i + this.zzbpv.hashCode() : i;
    }

    public final boolean isImmutable() {
        return this.zzbjg;
    }

    public V remove(Object obj) {
        zzro();
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        if (zza >= 0) {
            return zzdh(zza);
        }
        if (this.zzbpv.isEmpty()) {
            return null;
        }
        return this.zzbpv.remove(comparable);
    }

    public int size() {
        return this.zzbpu.size() + this.zzbpv.size();
    }

    /* renamed from: zza */
    public final V put(K k, V v) {
        zzro();
        int zza = zza(k);
        if (zza >= 0) {
            return this.zzbpu.get(zza).setValue(v);
        }
        zzro();
        if (this.zzbpu.isEmpty() && !(this.zzbpu instanceof ArrayList)) {
            this.zzbpu = new ArrayList(this.zzbpt);
        }
        int i = -(zza + 1);
        if (i >= this.zzbpt) {
            return zzrp().put(k, v);
        }
        int size = this.zzbpu.size();
        int i2 = this.zzbpt;
        if (size == i2) {
            zzvm remove = this.zzbpu.remove(i2 - 1);
            zzrp().put((Comparable) remove.getKey(), remove.getValue());
        }
        this.zzbpu.add(i, new zzvm(this, k, v));
        return null;
    }

    public final Map.Entry<K, V> zzdg(int i) {
        return this.zzbpu.get(i);
    }

    public void zzoh() {
        if (!this.zzbjg) {
            this.zzbpv = this.zzbpv.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzbpv);
            this.zzbpx = this.zzbpx.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzbpx);
            this.zzbjg = true;
        }
    }

    public final int zzrl() {
        return this.zzbpu.size();
    }

    public final Iterable<Map.Entry<K, V>> zzrm() {
        return this.zzbpv.isEmpty() ? zzvj.a() : this.zzbpv.entrySet();
    }
}

package com.google.android.gms.internal.firebase_ml;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class zzhs extends AbstractMap<String, Object> implements Cloneable {
    Map<String, Object> a;
    final zzhj b;

    final class zza implements Iterator<Map.Entry<String, Object>> {
        private boolean zzzw;
        private final Iterator<Map.Entry<String, Object>> zzzx;
        private final Iterator<Map.Entry<String, Object>> zzzy;

        zza(zzhs zzhs, zzhp zzhp) {
            this.zzzx = (zzho) zzhp.iterator();
            this.zzzy = zzhs.a.entrySet().iterator();
        }

        public final boolean hasNext() {
            return this.zzzx.hasNext() || this.zzzy.hasNext();
        }

        public final /* synthetic */ Object next() {
            Iterator<Map.Entry<String, Object>> it;
            if (!this.zzzw) {
                if (this.zzzx.hasNext()) {
                    it = this.zzzx;
                    return it.next();
                }
                this.zzzw = true;
            }
            it = this.zzzy;
            return it.next();
        }

        public final void remove() {
            if (this.zzzw) {
                this.zzzy.remove();
            }
            this.zzzx.remove();
        }
    }

    final class zzb extends AbstractSet<Map.Entry<String, Object>> {
        private final zzhp zzzz;

        zzb() {
            this.zzzz = (zzhp) new zzhm(zzhs.this, zzhs.this.b.zzgm()).entrySet();
        }

        public final void clear() {
            zzhs.this.a.clear();
            this.zzzz.clear();
        }

        public final Iterator<Map.Entry<String, Object>> iterator() {
            return new zza(zzhs.this, this.zzzz);
        }

        public final int size() {
            return zzhs.this.a.size() + this.zzzz.size();
        }
    }

    public enum zzc {
        IGNORE_CASE
    }

    public zzhs() {
        this(EnumSet.noneOf(zzc.class));
    }

    public zzhs(EnumSet<zzc> enumSet) {
        this.a = new zzhe();
        this.b = zzhj.zza(getClass(), enumSet.contains(zzc.IGNORE_CASE));
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return new zzb();
    }

    public final Object get(Object obj) {
        if (!(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        zzhr zzal = this.b.zzal(str);
        if (zzal != null) {
            return zzal.zzh(this);
        }
        if (this.b.zzgm()) {
            str = str.toLowerCase(Locale.US);
        }
        return this.a.get(str);
    }

    public final void putAll(Map<? extends String, ?> map) {
        for (Map.Entry next : map.entrySet()) {
            zzb((String) next.getKey(), next.getValue());
        }
    }

    public final Object remove(Object obj) {
        if (!(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        if (this.b.zzal(str) == null) {
            if (this.b.zzgm()) {
                str = str.toLowerCase(Locale.US);
            }
            return this.a.remove(str);
        }
        throw new UnsupportedOperationException();
    }

    public zzhs zzb(String str, Object obj) {
        zzhr zzal = this.b.zzal(str);
        if (zzal != null) {
            zzal.zzb(this, obj);
        } else {
            if (this.b.zzgm()) {
                str = str.toLowerCase(Locale.US);
            }
            this.a.put(str, obj);
        }
        return this;
    }

    /* renamed from: zzdr */
    public zzhs clone() {
        try {
            zzhs zzhs = (zzhs) super.clone();
            zzhl.zza((Object) this, (Object) zzhs);
            zzhs.a = (Map) zzhl.clone(this.a);
            return zzhs;
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }
    }

    /* renamed from: zzf */
    public final Object put(String str, Object obj) {
        zzhr zzal = this.b.zzal(str);
        if (zzal != null) {
            Object zzh = zzal.zzh(this);
            zzal.zzb(this, obj);
            return zzh;
        }
        if (this.b.zzgm()) {
            str = str.toLowerCase(Locale.US);
        }
        return this.a.put(str, obj);
    }

    public final zzhj zzgs() {
        return this.b;
    }
}

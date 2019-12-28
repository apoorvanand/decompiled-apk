package com.google.android.gms.internal.firebase_ml;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import java.util.WeakHashMap;

public final class zzhj {
    private static final Map<Class<?>, zzhj> zzyo = new WeakHashMap();
    private static final Map<Class<?>, zzhj> zzyp = new WeakHashMap();
    final List<String> a;
    private final Class<?> zzyq;
    private final boolean zzyr;
    private final IdentityHashMap<String, zzhr> zzys = new IdentityHashMap<>();

    private zzhj(Class<?> cls, boolean z) {
        this.zzyq = cls;
        this.zzyr = z;
        boolean z2 = !z || !cls.isEnum();
        String valueOf = String.valueOf(cls);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 31);
        sb.append("cannot ignore case on an enum: ");
        sb.append(valueOf);
        zzky.checkArgument(z2, sb.toString());
        TreeSet treeSet = new TreeSet(new zzhk(this));
        for (Field field : cls.getDeclaredFields()) {
            zzhr zza = zzhr.zza(field);
            if (zza != null) {
                String name = zza.getName();
                name = z ? name.toLowerCase(Locale.US).intern() : name;
                zzhr zzhr = this.zzys.get(name);
                boolean z3 = zzhr == null;
                Object[] objArr = new Object[4];
                objArr[0] = z ? "case-insensitive " : "";
                objArr[1] = name;
                objArr[2] = field;
                objArr[3] = zzhr == null ? null : zzhr.zzgp();
                if (z3) {
                    this.zzys.put(name, zza);
                    treeSet.add(name);
                } else {
                    throw new IllegalArgumentException(zzlg.zzb("two fields have the same %sname <%s>: %s and %s", objArr));
                }
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null) {
            zzhj zza2 = zza(superclass, z);
            treeSet.addAll(zza2.a);
            for (Map.Entry next : zza2.zzys.entrySet()) {
                String str = (String) next.getKey();
                if (!this.zzys.containsKey(str)) {
                    this.zzys.put(str, (zzhr) next.getValue());
                }
            }
        }
        this.a = treeSet.isEmpty() ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList(treeSet));
    }

    public static zzhj zza(Class<?> cls, boolean z) {
        zzhj zzhj;
        if (cls == null) {
            return null;
        }
        Map<Class<?>, zzhj> map = z ? zzyp : zzyo;
        synchronized (map) {
            zzhj = map.get(cls);
            if (zzhj == null) {
                zzhj = new zzhj(cls, z);
                map.put(cls, zzhj);
            }
        }
        return zzhj;
    }

    public static zzhj zzd(Class<?> cls) {
        return zza(cls, false);
    }

    public final boolean isEnum() {
        return this.zzyq.isEnum();
    }

    public final zzhr zzal(String str) {
        if (str != null) {
            if (this.zzyr) {
                str = str.toLowerCase(Locale.US);
            }
            str = str.intern();
        }
        return this.zzys.get(str);
    }

    public final boolean zzgm() {
        return this.zzyr;
    }

    public final Collection<zzhr> zzgn() {
        return Collections.unmodifiableCollection(this.zzys.values());
    }
}

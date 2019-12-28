package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzgz {
    private static final Class<?> zzalg = zzwf();
    private static final zzhp<?, ?> zzalh = zzt(false);
    private static final zzhp<?, ?> zzali = zzt(true);
    private static final zzhp<?, ?> zzalj = new zzhr();

    static int a(int i, Object obj, zzgx zzgx) {
        return obj instanceof zzfn ? zzee.zza(i, (zzfn) obj) : zzee.b(i, (zzgi) obj, zzgx);
    }

    static int a(int i, List<?> list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzbi = zzee.zzbi(i) * size;
        if (list instanceof zzfp) {
            zzfp zzfp = (zzfp) list;
            while (i2 < size) {
                Object zzbw = zzfp.zzbw(i2);
                zzbi += zzbw instanceof zzdp ? zzee.zzb((zzdp) zzbw) : zzee.zzds((String) zzbw);
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                zzbi += obj instanceof zzdp ? zzee.zzb((zzdp) obj) : zzee.zzds((String) obj);
                i2++;
            }
        }
        return zzbi;
    }

    static int a(int i, List<?> list, zzgx zzgx) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzbi = zzee.zzbi(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            zzbi += obj instanceof zzfn ? zzee.zza((zzfn) obj) : zzee.b((zzgi) obj, zzgx);
        }
        return zzbi;
    }

    static int a(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return a(list) + (list.size() * zzee.zzbi(i));
    }

    static int a(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfw) {
            zzfw zzfw = (zzfw) list;
            i = 0;
            while (i2 < size) {
                i += zzee.zzbq(zzfw.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzee.zzbq(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static <UT, UB> UB a(int i, int i2, UB ub, zzhp<UT, UB> zzhp) {
        if (ub == null) {
            ub = zzhp.a();
        }
        zzhp.a(ub, i, (long) i2);
        return ub;
    }

    static <UT, UB> UB a(int i, List<Integer> list, zzfe zzfe, UB ub, zzhp<UT, UB> zzhp) {
        UB ub2;
        if (zzfe == null) {
            return ub;
        }
        if (!(list instanceof RandomAccess)) {
            Iterator<Integer> it = list.iterator();
            loop1:
            while (true) {
                ub2 = ub;
                while (it.hasNext()) {
                    int intValue = it.next().intValue();
                    if (!zzfe.zzg(intValue)) {
                        ub = a(i, intValue, ub2, zzhp);
                        it.remove();
                    }
                }
                break loop1;
            }
        } else {
            int size = list.size();
            ub2 = ub;
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue2 = list.get(i3).intValue();
                if (zzfe.zzg(intValue2)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue2));
                    }
                    i2++;
                } else {
                    ub2 = a(i, intValue2, ub2, zzhp);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        }
        return ub2;
    }

    static <T, FT extends zzeq<FT>> void a(zzen<FT> zzen, T t, T t2) {
        zzeo<FT> a = zzen.a((Object) t2);
        if (!a.a.isEmpty()) {
            zzen.b(t).zza(a);
        }
    }

    static <T> void a(zzgb zzgb, T t, T t2, long j) {
        zzhv.a((Object) t, j, zzgb.zzb(zzhv.f(t, j), zzhv.f(t2, j)));
    }

    static <T, UT, UB> void a(zzhp<UT, UB> zzhp, T t, T t2) {
        zzhp.a((Object) t, zzhp.c(zzhp.b(t), zzhp.b(t2)));
    }

    static boolean a(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static int b(int i, List<zzdp> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzbi = size * zzee.zzbi(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzbi += zzee.zzb(list.get(i2));
        }
        return zzbi;
    }

    static int b(int i, List<zzgi> list, zzgx zzgx) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzee.c(i, list.get(i3), zzgx);
        }
        return i2;
    }

    static int b(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return b(list) + (size * zzee.zzbi(i));
    }

    static int b(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfw) {
            zzfw zzfw = (zzfw) list;
            i = 0;
            while (i2 < size) {
                i += zzee.zzbr(zzfw.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzee.zzbr(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int c(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return c(list) + (size * zzee.zzbi(i));
    }

    static int c(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfw) {
            zzfw zzfw = (zzfw) list;
            i = 0;
            while (i2 < size) {
                i += zzee.zzbs(zzfw.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzee.zzbs(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static int d(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return d(list) + (size * zzee.zzbi(i));
    }

    static int d(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfa) {
            zzfa zzfa = (zzfa) list;
            i = 0;
            while (i2 < size) {
                i += zzee.zzbo(zzfa.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzee.zzbo(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int e(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return e(list) + (size * zzee.zzbi(i));
    }

    static int e(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfa) {
            zzfa zzfa = (zzfa) list;
            i = 0;
            while (i2 < size) {
                i += zzee.zzbj(zzfa.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzee.zzbj(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int f(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return f(list) + (size * zzee.zzbi(i));
    }

    static int f(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfa) {
            zzfa zzfa = (zzfa) list;
            i = 0;
            while (i2 < size) {
                i += zzee.zzbk(zzfa.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzee.zzbk(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int g(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return g(list) + (size * zzee.zzbi(i));
    }

    static int g(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfa) {
            zzfa zzfa = (zzfa) list;
            i = 0;
            while (i2 < size) {
                i += zzee.zzbl(zzfa.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzee.zzbl(list.get(i2).intValue());
                i2++;
            }
        }
        return i;
    }

    static int h(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzee.zzj(i, 0);
    }

    static int h(List<?> list) {
        return list.size() << 2;
    }

    static int i(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzee.zzg(i, 0);
    }

    static int i(List<?> list) {
        return list.size() << 3;
    }

    static int j(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzee.zzc(i, true);
    }

    static int j(List<?> list) {
        return list.size();
    }

    public static void zza(int i, List<String> list, zzim zzim) {
        if (list != null && !list.isEmpty()) {
            zzim.zza(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzim zzim, zzgx zzgx) {
        if (list != null && !list.isEmpty()) {
            zzim.zza(i, list, zzgx);
        }
    }

    public static void zza(int i, List<Double> list, zzim zzim, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzim.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<zzdp> list, zzim zzim) {
        if (list != null && !list.isEmpty()) {
            zzim.zzb(i, list);
        }
    }

    public static void zzb(int i, List<?> list, zzim zzim, zzgx zzgx) {
        if (list != null && !list.isEmpty()) {
            zzim.zzb(i, list, zzgx);
        }
    }

    public static void zzb(int i, List<Float> list, zzim zzim, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzim.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzim zzim, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzim.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzim zzim, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzim.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzim zzim, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzim.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzim zzim, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzim.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzim zzim, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzim.zzl(i, list, z);
        }
    }

    public static void zzg(Class<?> cls) {
        Class<?> cls2;
        if (!zzey.class.isAssignableFrom(cls) && (cls2 = zzalg) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzh(int i, List<Integer> list, zzim zzim, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzim.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzim zzim, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzim.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzim zzim, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzim.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzim zzim, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzim.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzim zzim, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzim.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzim zzim, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzim.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzim zzim, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzim.zzi(i, list, z);
        }
    }

    private static zzhp<?, ?> zzt(boolean z) {
        try {
            Class<?> zzwg = zzwg();
            if (zzwg == null) {
                return null;
            }
            return (zzhp) zzwg.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }

    public static zzhp<?, ?> zzwc() {
        return zzalh;
    }

    public static zzhp<?, ?> zzwd() {
        return zzali;
    }

    public static zzhp<?, ?> zzwe() {
        return zzalj;
    }

    private static Class<?> zzwf() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzwg() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }
}

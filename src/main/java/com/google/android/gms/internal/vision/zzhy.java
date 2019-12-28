package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzhy {
    private static final Class<?> zzaaa = zzgs();
    private static final zzio<?, ?> zzaab = zzk(false);
    private static final zzio<?, ?> zzaac = zzk(true);
    private static final zzio<?, ?> zzaad = new zziq();

    static int a(int i, Object obj, zzhw zzhw) {
        return obj instanceof zzgm ? zzfe.zza(i, (zzgm) obj) : zzfe.b(i, (zzhf) obj, zzhw);
    }

    static int a(int i, List<?> list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzav = zzfe.zzav(i) * size;
        if (list instanceof zzgo) {
            zzgo zzgo = (zzgo) list;
            while (i2 < size) {
                Object raw = zzgo.getRaw(i2);
                zzav += raw instanceof zzeo ? zzfe.zzb((zzeo) raw) : zzfe.zzn((String) raw);
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                zzav += obj instanceof zzeo ? zzfe.zzb((zzeo) obj) : zzfe.zzn((String) obj);
                i2++;
            }
        }
        return zzav;
    }

    static int a(int i, List<?> list, zzhw zzhw) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzav = zzfe.zzav(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            zzav += obj instanceof zzgm ? zzfe.zza((zzgm) obj) : zzfe.b((zzhf) obj, zzhw);
        }
        return zzav;
    }

    static int a(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return a(list) + (list.size() * zzfe.zzav(i));
    }

    static int a(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            i = 0;
            while (i2 < size) {
                i += zzfe.zzh(zzgt.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzfe.zzh(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static <UT, UB> UB a(int i, int i2, UB ub, zzio<UT, UB> zzio) {
        if (ub == null) {
            ub = zzio.a();
        }
        zzio.a(ub, i, (long) i2);
        return ub;
    }

    static <UT, UB> UB a(int i, List<Integer> list, zzgc<?> zzgc, UB ub, zzio<UT, UB> zzio) {
        if (zzgc == null) {
            return ub;
        }
        int size = list.size();
        UB ub2 = ub;
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            int intValue = list.get(i3).intValue();
            if (zzgc.zzf(intValue) != null) {
                if (i3 != i2) {
                    list.set(i2, Integer.valueOf(intValue));
                }
                i2++;
            } else {
                ub2 = a(i, intValue, ub2, zzio);
            }
        }
        if (i2 != size) {
            list.subList(i2, size).clear();
        }
        return ub2;
    }

    static <UT, UB> UB a(int i, List<Integer> list, zzgd zzgd, UB ub, zzio<UT, UB> zzio) {
        UB ub2;
        if (zzgd == null) {
            return ub;
        }
        if (!(list instanceof RandomAccess)) {
            Iterator<Integer> it = list.iterator();
            loop1:
            while (true) {
                ub2 = ub;
                while (it.hasNext()) {
                    int intValue = it.next().intValue();
                    if (!zzgd.zzh(intValue)) {
                        ub = a(i, intValue, ub2, zzio);
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
                if (zzgd.zzh(intValue2)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue2));
                    }
                    i2++;
                } else {
                    ub2 = a(i, intValue2, ub2, zzio);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        }
        return ub2;
    }

    static <T, FT extends zzfr<FT>> void a(zzfl<FT> zzfl, T t, T t2) {
        zzfp<FT> a = zzfl.a((Object) t2);
        if (!a.a()) {
            zzfl.b(t).zza(a);
        }
    }

    static <T> void a(zzha zzha, T t, T t2, long j) {
        zziu.a((Object) t, j, zzha.zzb(zziu.f(t, j), zziu.f(t2, j)));
    }

    static <T, UT, UB> void a(zzio<UT, UB> zzio, T t, T t2) {
        zzio.a((Object) t, zzio.c(zzio.b(t), zzio.b(t2)));
    }

    static boolean a(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static int b(int i, List<zzeo> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzav = size * zzfe.zzav(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzav += zzfe.zzb(list.get(i2));
        }
        return zzav;
    }

    static int b(int i, List<zzhf> list, zzhw zzhw) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzfe.c(i, list.get(i3), zzhw);
        }
        return i2;
    }

    static int b(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return b(list) + (size * zzfe.zzav(i));
    }

    static int b(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            i = 0;
            while (i2 < size) {
                i += zzfe.zzi(zzgt.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzfe.zzi(list.get(i2).longValue());
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
        return c(list) + (size * zzfe.zzav(i));
    }

    static int c(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            i = 0;
            while (i2 < size) {
                i += zzfe.zzj(zzgt.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzfe.zzj(list.get(i2).longValue());
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
        return d(list) + (size * zzfe.zzav(i));
    }

    static int d(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            i = 0;
            while (i2 < size) {
                i += zzfe.zzbb(zzfz.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzfe.zzbb(list.get(i2).intValue());
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
        return e(list) + (size * zzfe.zzav(i));
    }

    static int e(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            i = 0;
            while (i2 < size) {
                i += zzfe.zzaw(zzfz.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzfe.zzaw(list.get(i2).intValue());
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
        return f(list) + (size * zzfe.zzav(i));
    }

    static int f(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            i = 0;
            while (i2 < size) {
                i += zzfe.zzax(zzfz.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzfe.zzax(list.get(i2).intValue());
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
        return g(list) + (size * zzfe.zzav(i));
    }

    static int g(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            i = 0;
            while (i2 < size) {
                i += zzfe.zzay(zzfz.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzfe.zzay(list.get(i2).intValue());
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
        return size * zzfe.zzl(i, 0);
    }

    static int h(List<?> list) {
        return list.size() << 2;
    }

    static int i(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzfe.zzg(i, 0);
    }

    static int i(List<?> list) {
        return list.size() << 3;
    }

    static int j(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzfe.zzc(i, true);
    }

    static int j(List<?> list) {
        return list.size();
    }

    public static void zza(int i, List<String> list, zzjj zzjj) {
        if (list != null && !list.isEmpty()) {
            zzjj.zza(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzjj zzjj, zzhw zzhw) {
        if (list != null && !list.isEmpty()) {
            zzjj.zza(i, list, zzhw);
        }
    }

    public static void zza(int i, List<Double> list, zzjj zzjj, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzjj.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<zzeo> list, zzjj zzjj) {
        if (list != null && !list.isEmpty()) {
            zzjj.zzb(i, list);
        }
    }

    public static void zzb(int i, List<?> list, zzjj zzjj, zzhw zzhw) {
        if (list != null && !list.isEmpty()) {
            zzjj.zzb(i, list, zzhw);
        }
    }

    public static void zzb(int i, List<Float> list, zzjj zzjj, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzjj.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzjj zzjj, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzjj.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzjj zzjj, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzjj.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzjj zzjj, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzjj.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzjj zzjj, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzjj.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzjj zzjj, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzjj.zzl(i, list, z);
        }
    }

    public static void zzg(Class<?> cls) {
        Class<?> cls2;
        if (!zzfy.class.isAssignableFrom(cls) && (cls2 = zzaaa) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static zzio<?, ?> zzgp() {
        return zzaab;
    }

    public static zzio<?, ?> zzgq() {
        return zzaac;
    }

    public static zzio<?, ?> zzgr() {
        return zzaad;
    }

    private static Class<?> zzgs() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzgt() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void zzh(int i, List<Integer> list, zzjj zzjj, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzjj.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzjj zzjj, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzjj.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzjj zzjj, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzjj.zzm(i, list, z);
        }
    }

    private static zzio<?, ?> zzk(boolean z) {
        try {
            Class<?> zzgt = zzgt();
            if (zzgt == null) {
                return null;
            }
            return (zzio) zzgt.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void zzk(int i, List<Integer> list, zzjj zzjj, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzjj.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzjj zzjj, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzjj.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzjj zzjj, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzjj.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzjj zzjj, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzjj.zzi(i, list, z);
        }
    }
}

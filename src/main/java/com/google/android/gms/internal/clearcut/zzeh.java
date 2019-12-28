package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzeh {
    private static final Class<?> zzoh = zzdp();
    private static final zzex<?, ?> zzoi = zzd(false);
    private static final zzex<?, ?> zzoj = zzd(true);
    private static final zzex<?, ?> zzok = new zzez();

    static int a(int i, Object obj, zzef zzef) {
        return obj instanceof zzcv ? zzbn.zza(i, (zzcv) obj) : zzbn.b(i, (zzdo) obj, zzef);
    }

    static int a(int i, List<?> list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzr = zzbn.zzr(i) * size;
        if (list instanceof zzcx) {
            zzcx zzcx = (zzcx) list;
            while (i2 < size) {
                Object raw = zzcx.getRaw(i2);
                zzr += raw instanceof zzbb ? zzbn.zzb((zzbb) raw) : zzbn.zzh((String) raw);
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                zzr += obj instanceof zzbb ? zzbn.zzb((zzbb) obj) : zzbn.zzh((String) obj);
                i2++;
            }
        }
        return zzr;
    }

    static int a(int i, List<?> list, zzef zzef) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzr = zzbn.zzr(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            zzr += obj instanceof zzcv ? zzbn.zza((zzcv) obj) : zzbn.b((zzdo) obj, zzef);
        }
        return zzr;
    }

    static int a(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return a(list) + (list.size() * zzbn.zzr(i));
    }

    static int a(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdc) {
            zzdc zzdc = (zzdc) list;
            i = 0;
            while (i2 < size) {
                i += zzbn.zze(zzdc.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbn.zze(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static <UT, UB> UB a(int i, List<Integer> list, zzck<?> zzck, UB ub, zzex<UT, UB> zzex) {
        UB ub2;
        if (zzck == null) {
            return ub;
        }
        if (!(list instanceof RandomAccess)) {
            Iterator<Integer> it = list.iterator();
            loop1:
            while (true) {
                ub2 = ub;
                while (it.hasNext()) {
                    int intValue = it.next().intValue();
                    if (zzck.zzb(intValue) == null) {
                        ub = zza(i, intValue, ub2, zzex);
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
                if (zzck.zzb(intValue2) != null) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue2));
                    }
                    i2++;
                } else {
                    ub2 = zza(i, intValue2, ub2, zzex);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        }
        return ub2;
    }

    static <T, FT extends zzca<FT>> void a(zzbu<FT> zzbu, T t, T t2) {
        zzby<FT> a = zzbu.a((Object) t2);
        if (!a.a()) {
            zzbu.b(t).zza(a);
        }
    }

    static <T> void a(zzdj zzdj, T t, T t2, long j) {
        zzfd.a((Object) t, j, zzdj.zzb(zzfd.f(t, j), zzfd.f(t2, j)));
    }

    static <T, UT, UB> void a(zzex<UT, UB> zzex, T t, T t2) {
        zzex.a((Object) t, zzex.c(zzex.a(t), zzex.a(t2)));
    }

    static boolean a(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static int b(int i, List<zzbb> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzr = size * zzbn.zzr(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzr += zzbn.zzb(list.get(i2));
        }
        return zzr;
    }

    static int b(int i, List<zzdo> list, zzef zzef) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzbn.c(i, list.get(i3), zzef);
        }
        return i2;
    }

    static int b(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return b(list) + (size * zzbn.zzr(i));
    }

    static int b(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdc) {
            zzdc zzdc = (zzdc) list;
            i = 0;
            while (i2 < size) {
                i += zzbn.zzf(zzdc.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbn.zzf(list.get(i2).longValue());
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
        return c(list) + (size * zzbn.zzr(i));
    }

    static int c(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdc) {
            zzdc zzdc = (zzdc) list;
            i = 0;
            while (i2 < size) {
                i += zzbn.zzg(zzdc.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbn.zzg(list.get(i2).longValue());
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
        return d(list) + (size * zzbn.zzr(i));
    }

    static int d(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzch) {
            zzch zzch = (zzch) list;
            i = 0;
            while (i2 < size) {
                i += zzbn.zzx(zzch.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbn.zzx(list.get(i2).intValue());
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
        return e(list) + (size * zzbn.zzr(i));
    }

    static int e(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzch) {
            zzch zzch = (zzch) list;
            i = 0;
            while (i2 < size) {
                i += zzbn.zzs(zzch.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbn.zzs(list.get(i2).intValue());
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
        return f(list) + (size * zzbn.zzr(i));
    }

    static int f(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzch) {
            zzch zzch = (zzch) list;
            i = 0;
            while (i2 < size) {
                i += zzbn.zzt(zzch.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbn.zzt(list.get(i2).intValue());
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
        return g(list) + (size * zzbn.zzr(i));
    }

    static int g(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzch) {
            zzch zzch = (zzch) list;
            i = 0;
            while (i2 < size) {
                i += zzbn.zzu(zzch.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbn.zzu(list.get(i2).intValue());
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
        return size * zzbn.zzj(i, 0);
    }

    static int h(List<?> list) {
        return list.size() << 2;
    }

    static int i(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbn.zzg(i, 0);
    }

    static int i(List<?> list) {
        return list.size() << 3;
    }

    static int j(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbn.zzc(i, true);
    }

    static int j(List<?> list) {
        return list.size();
    }

    private static <UT, UB> UB zza(int i, int i2, UB ub, zzex<UT, UB> zzex) {
        if (ub == null) {
            ub = zzex.a();
        }
        zzex.a(ub, i, (long) i2);
        return ub;
    }

    public static void zza(int i, List<String> list, zzfr zzfr) {
        if (list != null && !list.isEmpty()) {
            zzfr.zza(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzfr zzfr, zzef zzef) {
        if (list != null && !list.isEmpty()) {
            zzfr.zza(i, list, zzef);
        }
    }

    public static void zza(int i, List<Double> list, zzfr zzfr, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzfr.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<zzbb> list, zzfr zzfr) {
        if (list != null && !list.isEmpty()) {
            zzfr.zzb(i, list);
        }
    }

    public static void zzb(int i, List<?> list, zzfr zzfr, zzef zzef) {
        if (list != null && !list.isEmpty()) {
            zzfr.zzb(i, list, zzef);
        }
    }

    public static void zzb(int i, List<Float> list, zzfr zzfr, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzfr.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzfr zzfr, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzfr.zzc(i, list, z);
        }
    }

    public static boolean zzc(int i, int i2, int i3) {
        if (i2 < 40) {
            return true;
        }
        long j = (long) i3;
        return ((((long) i2) - ((long) i)) + 1) + 9 <= ((2 * j) + 3) + ((j + 3) * 3);
    }

    private static zzex<?, ?> zzd(boolean z) {
        try {
            Class<?> zzdq = zzdq();
            if (zzdq == null) {
                return null;
            }
            return (zzex) zzdq.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void zzd(int i, List<Long> list, zzfr zzfr, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzfr.zzd(i, list, z);
        }
    }

    public static zzex<?, ?> zzdm() {
        return zzoi;
    }

    public static zzex<?, ?> zzdn() {
        return zzoj;
    }

    public static zzex<?, ?> zzdo() {
        return zzok;
    }

    private static Class<?> zzdp() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzdq() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void zze(int i, List<Long> list, zzfr zzfr, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzfr.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzfr zzfr, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzfr.zze(i, list, z);
        }
    }

    public static void zzf(Class<?> cls) {
        Class<?> cls2;
        if (!zzcg.class.isAssignableFrom(cls) && (cls2 = zzoh) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzg(int i, List<Long> list, zzfr zzfr, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzfr.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzfr zzfr, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzfr.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzfr zzfr, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzfr.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzfr zzfr, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzfr.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzfr zzfr, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzfr.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzfr zzfr, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzfr.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzfr zzfr, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzfr.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzfr zzfr, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzfr.zzi(i, list, z);
        }
    }
}

package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzju {
    private static final Class<?> zzadw = zzkg();
    private static final zzkk<?, ?> zzadx = zzw(false);
    private static final zzkk<?, ?> zzady = zzw(true);
    private static final zzkk<?, ?> zzadz = new zzkm();

    static int a(int i, Object obj, zzjs zzjs) {
        return obj instanceof zzih ? zzha.zza(i, (zzih) obj) : zzha.b(i, (zzjc) obj, zzjs);
    }

    static int a(int i, List<?> list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzak = zzha.zzak(i) * size;
        if (list instanceof zzij) {
            zzij zzij = (zzij) list;
            while (i2 < size) {
                Object zzax = zzij.zzax(i2);
                zzak += zzax instanceof zzgf ? zzha.zzb((zzgf) zzax) : zzha.zzdj((String) zzax);
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                zzak += obj instanceof zzgf ? zzha.zzb((zzgf) obj) : zzha.zzdj((String) obj);
                i2++;
            }
        }
        return zzak;
    }

    static int a(int i, List<?> list, zzjs zzjs) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzak = zzha.zzak(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            zzak += obj instanceof zzih ? zzha.zza((zzih) obj) : zzha.a((zzjc) obj, zzjs);
        }
        return zzak;
    }

    static int a(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return a(list) + (list.size() * zzha.zzak(i));
    }

    static int a(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zziq) {
            zziq zziq = (zziq) list;
            i = 0;
            while (i2 < size) {
                i += zzha.zze(zziq.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzha.zze(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static <UT, UB> UB a(int i, int i2, UB ub, zzkk<UT, UB> zzkk) {
        if (ub == null) {
            ub = zzkk.a();
        }
        zzkk.a(ub, i, (long) i2);
        return ub;
    }

    static <UT, UB> UB a(int i, List<Integer> list, zzhy zzhy, UB ub, zzkk<UT, UB> zzkk) {
        UB ub2;
        if (zzhy == null) {
            return ub;
        }
        if (!(list instanceof RandomAccess)) {
            Iterator<Integer> it = list.iterator();
            loop1:
            while (true) {
                ub2 = ub;
                while (it.hasNext()) {
                    int intValue = it.next().intValue();
                    if (!zzhy.zzd(intValue)) {
                        ub = a(i, intValue, ub2, zzkk);
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
                if (zzhy.zzd(intValue2)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue2));
                    }
                    i2++;
                } else {
                    ub2 = a(i, intValue2, ub2, zzkk);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        }
        return ub2;
    }

    static <T, FT extends zzhk<FT>> void a(zzhh<FT> zzhh, T t, T t2) {
        zzhi<FT> a = zzhh.a((Object) t2);
        if (!a.a.isEmpty()) {
            zzhh.b(t).zza(a);
        }
    }

    static <T> void a(zziv zziv, T t, T t2, long j) {
        zzkq.a((Object) t, j, zziv.zzc(zzkq.f(t, j), zzkq.f(t2, j)));
    }

    static <T, UT, UB> void a(zzkk<UT, UB> zzkk, T t, T t2) {
        zzkk.a((Object) t, zzkk.c(zzkk.b(t), zzkk.b(t2)));
    }

    static boolean a(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static int b(int i, List<zzgf> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzak = size * zzha.zzak(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzak += zzha.zzb(list.get(i2));
        }
        return zzak;
    }

    static int b(int i, List<zzjc> list, zzjs zzjs) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzha.c(i, list.get(i3), zzjs);
        }
        return i2;
    }

    static int b(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return b(list) + (size * zzha.zzak(i));
    }

    static int b(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zziq) {
            zziq zziq = (zziq) list;
            i = 0;
            while (i2 < size) {
                i += zzha.zzf(zziq.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzha.zzf(list.get(i2).longValue());
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
        return c(list) + (size * zzha.zzak(i));
    }

    static int c(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zziq) {
            zziq zziq = (zziq) list;
            i = 0;
            while (i2 < size) {
                i += zzha.zzg(zziq.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzha.zzg(list.get(i2).longValue());
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
        return d(list) + (size * zzha.zzak(i));
    }

    static int d(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhu) {
            zzhu zzhu = (zzhu) list;
            i = 0;
            while (i2 < size) {
                i += zzha.zzaq(zzhu.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzha.zzaq(list.get(i2).intValue());
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
        return e(list) + (size * zzha.zzak(i));
    }

    static int e(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhu) {
            zzhu zzhu = (zzhu) list;
            i = 0;
            while (i2 < size) {
                i += zzha.zzal(zzhu.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzha.zzal(list.get(i2).intValue());
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
        return f(list) + (size * zzha.zzak(i));
    }

    static int f(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhu) {
            zzhu zzhu = (zzhu) list;
            i = 0;
            while (i2 < size) {
                i += zzha.zzam(zzhu.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzha.zzam(list.get(i2).intValue());
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
        return g(list) + (size * zzha.zzak(i));
    }

    static int g(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhu) {
            zzhu zzhu = (zzhu) list;
            i = 0;
            while (i2 < size) {
                i += zzha.zzan(zzhu.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzha.zzan(list.get(i2).intValue());
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
        return size * zzha.zzm(i, 0);
    }

    static int h(List<?> list) {
        return list.size() << 2;
    }

    static int i(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzha.zzg(i, 0);
    }

    static int i(List<?> list) {
        return list.size() << 3;
    }

    static int j(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzha.zzd(i, true);
    }

    static int j(List<?> list) {
        return list.size();
    }

    public static void zza(int i, List<String> list, zzlh zzlh) {
        if (list != null && !list.isEmpty()) {
            zzlh.zza(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzlh zzlh, zzjs zzjs) {
        if (list != null && !list.isEmpty()) {
            zzlh.zza(i, list, zzjs);
        }
    }

    public static void zza(int i, List<Double> list, zzlh zzlh, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzlh.zzg(i, list, z);
        }
    }

    public static void zzb(int i, List<zzgf> list, zzlh zzlh) {
        if (list != null && !list.isEmpty()) {
            zzlh.zzb(i, list);
        }
    }

    public static void zzb(int i, List<?> list, zzlh zzlh, zzjs zzjs) {
        if (list != null && !list.isEmpty()) {
            zzlh.zzb(i, list, zzjs);
        }
    }

    public static void zzb(int i, List<Float> list, zzlh zzlh, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzlh.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzlh zzlh, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzlh.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzlh zzlh, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzlh.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzlh zzlh, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzlh.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzlh zzlh, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzlh.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzlh zzlh, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzlh.zzl(i, list, z);
        }
    }

    public static void zzg(Class<?> cls) {
        Class<?> cls2;
        if (!zzhs.class.isAssignableFrom(cls) && (cls2 = zzadw) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzh(int i, List<Integer> list, zzlh zzlh, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzlh.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzlh zzlh, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzlh.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzlh zzlh, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzlh.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzlh zzlh, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzlh.zzb(i, list, z);
        }
    }

    public static zzkk<?, ?> zzkd() {
        return zzadx;
    }

    public static zzkk<?, ?> zzke() {
        return zzady;
    }

    public static zzkk<?, ?> zzkf() {
        return zzadz;
    }

    private static Class<?> zzkg() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzkh() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void zzl(int i, List<Integer> list, zzlh zzlh, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzlh.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzlh zzlh, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzlh.zzh(i, list, z);
        }
    }

    public static void zzn(int i, List<Boolean> list, zzlh zzlh, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzlh.zzi(i, list, z);
        }
    }

    private static zzkk<?, ?> zzw(boolean z) {
        try {
            Class<?> zzkh = zzkh();
            if (zzkh == null) {
                return null;
            }
            return (zzkk) zzkh.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }
}

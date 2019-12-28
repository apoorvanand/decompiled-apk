package com.google.android.gms.internal.firebase_ml;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zzve {
    private static final Class<?> zzbpp = zzrj();
    private static final zzvu<?, ?> zzbpq = zzaj(false);
    private static final zzvu<?, ?> zzbpr = zzaj(true);
    private static final zzvu<?, ?> zzbps = new zzvw();

    static int a(int i, Object obj, zzvc zzvc) {
        return obj instanceof zztt ? zzsj.zza(i, (zztt) obj) : zzsj.b(i, (zzum) obj, zzvc);
    }

    static int a(int i, List<?> list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int zzcl = zzsj.zzcl(i) * size;
        if (list instanceof zztv) {
            zztv zztv = (zztv) list;
            while (i2 < size) {
                Object raw = zztv.getRaw(i2);
                zzcl += raw instanceof zzru ? zzsj.zzb((zzru) raw) : zzsj.zzck((String) raw);
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                zzcl += obj instanceof zzru ? zzsj.zzb((zzru) obj) : zzsj.zzck((String) obj);
                i2++;
            }
        }
        return zzcl;
    }

    static int a(int i, List<?> list, zzvc zzvc) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzcl = zzsj.zzcl(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            zzcl += obj instanceof zztt ? zzsj.zza((zztt) obj) : zzsj.a((zzum) obj, zzvc);
        }
        return zzcl;
    }

    static int a(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return a(list) + (list.size() * zzsj.zzcl(i));
    }

    static int a(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzua) {
            zzua zzua = (zzua) list;
            i = 0;
            while (i2 < size) {
                i += zzsj.zzq(zzua.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzsj.zzq(list.get(i2).longValue());
                i2++;
            }
        }
        return i;
    }

    static <UT, UB> UB a(int i, List<Integer> list, zzth zzth, UB ub, zzvu<UT, UB> zzvu) {
        UB ub2;
        if (zzth == null) {
            return ub;
        }
        if (!(list instanceof RandomAccess)) {
            Iterator<Integer> it = list.iterator();
            loop1:
            while (true) {
                ub2 = ub;
                while (it.hasNext()) {
                    int intValue = it.next().intValue();
                    if (!zzth.zzb(intValue)) {
                        ub = zza(i, intValue, ub2, zzvu);
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
                if (zzth.zzb(intValue2)) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue2));
                    }
                    i2++;
                } else {
                    ub2 = zza(i, intValue2, ub2, zzvu);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
            }
        }
        return ub2;
    }

    static <T, FT extends zzsw<FT>> void a(zzsr<FT> zzsr, T t, T t2) {
        zzsu<FT> a = zzsr.a((Object) t2);
        if (!a.a()) {
            zzsr.b(t).zza(a);
        }
    }

    static <T> void a(zzuh zzuh, T t, T t2, long j) {
        zzwa.a((Object) t, j, zzuh.zzd(zzwa.f(t, j), zzwa.f(t2, j)));
    }

    static <T, UT, UB> void a(zzvu<UT, UB> zzvu, T t, T t2) {
        zzvu.a((Object) t, zzvu.c(zzvu.a(t), zzvu.a(t2)));
    }

    static boolean a(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    static int b(int i, List<zzru> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzcl = size * zzsj.zzcl(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzcl += zzsj.zzb(list.get(i2));
        }
        return zzcl;
    }

    static int b(int i, List<zzum> list, zzvc zzvc) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzsj.c(i, list.get(i3), zzvc);
        }
        return i2;
    }

    static int b(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return b(list) + (size * zzsj.zzcl(i));
    }

    static int b(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzua) {
            zzua zzua = (zzua) list;
            i = 0;
            while (i2 < size) {
                i += zzsj.zzr(zzua.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzsj.zzr(list.get(i2).longValue());
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
        return c(list) + (size * zzsj.zzcl(i));
    }

    static int c(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzua) {
            zzua zzua = (zzua) list;
            i = 0;
            while (i2 < size) {
                i += zzsj.zzs(zzua.getLong(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzsj.zzs(list.get(i2).longValue());
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
        return d(list) + (size * zzsj.zzcl(i));
    }

    static int d(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zztd) {
            zztd zztd = (zztd) list;
            i = 0;
            while (i2 < size) {
                i += zzsj.zzcr(zztd.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzsj.zzcr(list.get(i2).intValue());
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
        return e(list) + (size * zzsj.zzcl(i));
    }

    static int e(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zztd) {
            zztd zztd = (zztd) list;
            i = 0;
            while (i2 < size) {
                i += zzsj.zzcm(zztd.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzsj.zzcm(list.get(i2).intValue());
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
        return f(list) + (size * zzsj.zzcl(i));
    }

    static int f(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zztd) {
            zztd zztd = (zztd) list;
            i = 0;
            while (i2 < size) {
                i += zzsj.zzcn(zztd.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzsj.zzcn(list.get(i2).intValue());
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
        return g(list) + (size * zzsj.zzcl(i));
    }

    static int g(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zztd) {
            zztd zztd = (zztd) list;
            i = 0;
            while (i2 < size) {
                i += zzsj.zzco(zztd.getInt(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzsj.zzco(list.get(i2).intValue());
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
        return size * zzsj.zzl(i, 0);
    }

    static int h(List<?> list) {
        return list.size() << 2;
    }

    static int i(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzsj.zzg(i, 0);
    }

    static int i(List<?> list) {
        return list.size() << 3;
    }

    static int j(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzsj.zzc(i, true);
    }

    static int j(List<?> list) {
        return list.size();
    }

    private static <UT, UB> UB zza(int i, int i2, UB ub, zzvu<UT, UB> zzvu) {
        if (ub == null) {
            ub = zzvu.a();
        }
        zzvu.a(ub, i, (long) i2);
        return ub;
    }

    public static void zza(int i, List<String> list, zzwp zzwp) {
        if (list != null && !list.isEmpty()) {
            zzwp.zza(i, list);
        }
    }

    public static void zza(int i, List<?> list, zzwp zzwp, zzvc zzvc) {
        if (list != null && !list.isEmpty()) {
            zzwp.zza(i, list, zzvc);
        }
    }

    public static void zza(int i, List<Double> list, zzwp zzwp, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzwp.zzg(i, list, z);
        }
    }

    private static zzvu<?, ?> zzaj(boolean z) {
        try {
            Class<?> zzrk = zzrk();
            if (zzrk == null) {
                return null;
            }
            return (zzvu) zzrk.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void zzb(int i, List<zzru> list, zzwp zzwp) {
        if (list != null && !list.isEmpty()) {
            zzwp.zzb(i, list);
        }
    }

    public static void zzb(int i, List<?> list, zzwp zzwp, zzvc zzvc) {
        if (list != null && !list.isEmpty()) {
            zzwp.zzb(i, list, zzvc);
        }
    }

    public static void zzb(int i, List<Float> list, zzwp zzwp, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzwp.zzf(i, list, z);
        }
    }

    public static void zzc(int i, List<Long> list, zzwp zzwp, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzwp.zzc(i, list, z);
        }
    }

    public static void zzd(int i, List<Long> list, zzwp zzwp, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzwp.zzd(i, list, z);
        }
    }

    public static void zze(int i, List<Long> list, zzwp zzwp, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzwp.zzn(i, list, z);
        }
    }

    public static void zzf(int i, List<Long> list, zzwp zzwp, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzwp.zze(i, list, z);
        }
    }

    public static void zzg(int i, List<Long> list, zzwp zzwp, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzwp.zzl(i, list, z);
        }
    }

    public static void zzh(int i, List<Integer> list, zzwp zzwp, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzwp.zza(i, list, z);
        }
    }

    public static void zzi(int i, List<Integer> list, zzwp zzwp, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzwp.zzj(i, list, z);
        }
    }

    public static void zzj(int i, List<Integer> list, zzwp zzwp, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzwp.zzm(i, list, z);
        }
    }

    public static void zzk(int i, List<Integer> list, zzwp zzwp, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzwp.zzb(i, list, z);
        }
    }

    public static void zzl(int i, List<Integer> list, zzwp zzwp, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzwp.zzk(i, list, z);
        }
    }

    public static void zzm(int i, List<Integer> list, zzwp zzwp, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzwp.zzh(i, list, z);
        }
    }

    public static void zzm(Class<?> cls) {
        Class<?> cls2;
        if (!zztc.class.isAssignableFrom(cls) && (cls2 = zzbpp) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzn(int i, List<Boolean> list, zzwp zzwp, boolean z) {
        if (list != null && !list.isEmpty()) {
            zzwp.zzi(i, list, z);
        }
    }

    public static zzvu<?, ?> zzrg() {
        return zzbpq;
    }

    public static zzvu<?, ?> zzrh() {
        return zzbpr;
    }

    public static zzvu<?, ?> zzri() {
        return zzbps;
    }

    private static Class<?> zzrj() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzrk() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }
}

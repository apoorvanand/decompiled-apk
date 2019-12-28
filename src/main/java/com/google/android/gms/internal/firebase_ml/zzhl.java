package com.google.android.gms.internal.firebase_ml;

import com.facebook.appevents.AppEventsConstants;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public final class zzhl {
    private static final Boolean zzyu = new Boolean(true);
    private static final String zzyv = new String();
    private static final Character zzyw = new Character(0);
    private static final Byte zzyx = new Byte((byte) 0);
    private static final Short zzyy = new Short(0);
    private static final Integer zzyz = new Integer(0);
    private static final Float zzza = new Float(0.0f);
    private static final Long zzzb = new Long(0);
    private static final Double zzzc = new Double(0.0d);
    private static final BigInteger zzzd = new BigInteger(AppEventsConstants.EVENT_PARAM_VALUE_NO);
    private static final BigDecimal zzze = new BigDecimal(AppEventsConstants.EVENT_PARAM_VALUE_NO);
    private static final zzhq zzzf = new zzhq(0);
    private static final ConcurrentHashMap<Class<?>, Object> zzzg;

    static {
        ConcurrentHashMap<Class<?>, Object> concurrentHashMap = new ConcurrentHashMap<>();
        zzzg = concurrentHashMap;
        concurrentHashMap.put(Boolean.class, zzyu);
        zzzg.put(String.class, zzyv);
        zzzg.put(Character.class, zzyw);
        zzzg.put(Byte.class, zzyx);
        zzzg.put(Short.class, zzyy);
        zzzg.put(Integer.class, zzyz);
        zzzg.put(Float.class, zzza);
        zzzg.put(Long.class, zzzb);
        zzzg.put(Double.class, zzzc);
        zzzg.put(BigInteger.class, zzzd);
        zzzg.put(BigDecimal.class, zzze);
        zzzg.put(zzhq.class, zzzf);
    }

    public static <T> T clone(T t) {
        T t2;
        if (t == null || zza(t.getClass())) {
            return t;
        }
        if (t instanceof zzhs) {
            return (zzhs) ((zzhs) t).clone();
        }
        Class<?> cls = t.getClass();
        if (cls.isArray()) {
            t2 = Array.newInstance(cls.getComponentType(), Array.getLength(t));
        } else if (t instanceof zzhe) {
            t2 = (zzhe) ((zzhe) t).clone();
        } else if ("java.util.Arrays$ArrayList".equals(cls.getName())) {
            Object[] array = ((List) t).toArray();
            zza((Object) array, (Object) array);
            return Arrays.asList(array);
        } else {
            t2 = zzig.zzg(cls);
        }
        zza((Object) t, (Object) t2);
        return t2;
    }

    public static boolean isNull(Object obj) {
        return obj != null && obj == zzzg.get(obj.getClass());
    }

    public static Object zza(Type type, String str) {
        Class<BigDecimal> cls = type instanceof Class ? (Class) type : null;
        if (type == null || cls != null) {
            if (cls == Void.class) {
                return null;
            }
            if (str == null || cls == null || cls.isAssignableFrom(String.class)) {
                return str;
            }
            if (cls == Character.class || cls == Character.TYPE) {
                if (str.length() == 1) {
                    return Character.valueOf(str.charAt(0));
                }
                String valueOf = String.valueOf(cls);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 37);
                sb.append("expected type Character/char but got ");
                sb.append(valueOf);
                throw new IllegalArgumentException(sb.toString());
            } else if (cls == Boolean.class || cls == Boolean.TYPE) {
                return Boolean.valueOf(str);
            } else {
                if (cls == Byte.class || cls == Byte.TYPE) {
                    return Byte.valueOf(str);
                }
                if (cls == Short.class || cls == Short.TYPE) {
                    return Short.valueOf(str);
                }
                if (cls == Integer.class || cls == Integer.TYPE) {
                    return Integer.valueOf(str);
                }
                if (cls == Long.class || cls == Long.TYPE) {
                    return Long.valueOf(str);
                }
                if (cls == Float.class || cls == Float.TYPE) {
                    return Float.valueOf(str);
                }
                if (cls == Double.class || cls == Double.TYPE) {
                    return Double.valueOf(str);
                }
                if (cls == zzhq.class) {
                    return zzhq.zzam(str);
                }
                if (cls == BigInteger.class) {
                    return new BigInteger(str);
                }
                if (cls == BigDecimal.class) {
                    return new BigDecimal(str);
                }
                if (cls.isEnum()) {
                    if (zzhj.zzd(cls).a.contains(str)) {
                        return zzhj.zzd(cls).zzal(str).zzgr();
                    }
                    throw new IllegalArgumentException(String.format("given enum name %s not part of enumeration", new Object[]{str}));
                }
            }
        }
        String valueOf2 = String.valueOf(type);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 35);
        sb2.append("expected primitive class, but got: ");
        sb2.append(valueOf2);
        throw new IllegalArgumentException(sb2.toString());
    }

    public static Type zza(List<Type> list, Type type) {
        if (type instanceof WildcardType) {
            type = zzig.zza((WildcardType) type);
        }
        while (type instanceof TypeVariable) {
            Type zza = zzig.zza(list, (TypeVariable<?>) (TypeVariable) type);
            if (zza != null) {
                type = zza;
            }
            if (type instanceof TypeVariable) {
                type = ((TypeVariable) type).getBounds()[0];
            }
        }
        return type;
    }

    public static void zza(Object obj, Object obj2) {
        Object zzh;
        Class<?> cls = obj.getClass();
        boolean z = true;
        int i = 0;
        zzky.checkArgument(cls == obj2.getClass());
        if (cls.isArray()) {
            if (Array.getLength(obj) != Array.getLength(obj2)) {
                z = false;
            }
            zzky.checkArgument(z);
            for (Object clone : zzig.zzi(obj)) {
                Array.set(obj2, i, clone(clone));
                i++;
            }
        } else if (Collection.class.isAssignableFrom(cls)) {
            Collection<Object> collection = (Collection) obj;
            if (ArrayList.class.isAssignableFrom(cls)) {
                ((ArrayList) obj2).ensureCapacity(collection.size());
            }
            Collection collection2 = (Collection) obj2;
            for (Object clone2 : collection) {
                collection2.add(clone(clone2));
            }
        } else {
            boolean isAssignableFrom = zzhs.class.isAssignableFrom(cls);
            if (isAssignableFrom || !Map.class.isAssignableFrom(cls)) {
                zzhj zzd = isAssignableFrom ? ((zzhs) obj).b : zzhj.zzd(cls);
                for (String zzal : zzd.a) {
                    zzhr zzal2 = zzd.zzal(zzal);
                    if (!zzal2.zzgq() && ((!isAssignableFrom || !zzal2.isPrimitive()) && (zzh = zzal2.zzh(obj)) != null)) {
                        zzal2.zzb(obj2, clone(zzh));
                    }
                }
            } else if (zzhe.class.isAssignableFrom(cls)) {
                zzhe zzhe = (zzhe) obj2;
                zzhe zzhe2 = (zzhe) obj;
                int size = zzhe2.size();
                while (i < size) {
                    zzhe.set(i, clone(zzhe2.zzad(i)));
                    i++;
                }
            } else {
                Map map = (Map) obj2;
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    map.put((String) entry.getKey(), clone(entry.getValue()));
                }
            }
        }
    }

    public static boolean zza(Type type) {
        if (type instanceof WildcardType) {
            type = zzig.zza((WildcardType) type);
        }
        if (!(type instanceof Class)) {
            return false;
        }
        Class<Boolean> cls = (Class) type;
        return cls.isPrimitive() || cls == Character.class || cls == String.class || cls == Integer.class || cls == Long.class || cls == Short.class || cls == Byte.class || cls == Float.class || cls == Double.class || cls == BigInteger.class || cls == BigDecimal.class || cls == zzhq.class || cls == Boolean.class;
    }

    public static Collection<Object> zzb(Type type) {
        if (type instanceof WildcardType) {
            type = zzig.zza((WildcardType) type);
        }
        if (type instanceof ParameterizedType) {
            type = ((ParameterizedType) type).getRawType();
        }
        Class cls = type instanceof Class ? (Class) type : null;
        if (type == null || (type instanceof GenericArrayType) || (cls != null && (cls.isArray() || cls.isAssignableFrom(ArrayList.class)))) {
            return new ArrayList();
        }
        if (cls != null) {
            return cls.isAssignableFrom(HashSet.class) ? new HashSet() : cls.isAssignableFrom(TreeSet.class) ? new TreeSet() : (Collection) zzig.zzg(cls);
        }
        String valueOf = String.valueOf(type);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 39);
        sb.append("unable to create new instance of type: ");
        sb.append(valueOf);
        throw new IllegalArgumentException(sb.toString());
    }

    public static <T> T zze(Class<?> cls) {
        T t = zzzg.get(cls);
        if (t == null) {
            synchronized (zzzg) {
                t = zzzg.get(cls);
                if (t == null) {
                    int i = 0;
                    if (cls.isArray()) {
                        Class<?> cls2 = cls;
                        do {
                            cls2 = cls2.getComponentType();
                            i++;
                        } while (cls2.isArray());
                        t = Array.newInstance(cls2, new int[i]);
                    } else if (cls.isEnum()) {
                        zzhr zzal = zzhj.zzd(cls).zzal((String) null);
                        Object[] objArr = {cls};
                        if (zzal != null) {
                            t = zzal.zzgr();
                        } else {
                            throw new NullPointerException(zzlg.zzb("enum missing constant with @NullValue annotation: %s", objArr));
                        }
                    } else {
                        t = zzig.zzg(cls);
                    }
                    zzzg.put(cls, t);
                }
            }
        }
        return t;
    }

    public static Map<String, Object> zzf(Class<?> cls) {
        return (cls == null || cls.isAssignableFrom(zzhe.class)) ? new zzhe() : cls.isAssignableFrom(TreeMap.class) ? new TreeMap() : (Map) zzig.zzg(cls);
    }

    public static Map<String, Object> zzf(Object obj) {
        return (obj == null || isNull(obj)) ? Collections.emptyMap() : obj instanceof Map ? (Map) obj : new zzhm(obj, false);
    }

    public static boolean zzg(Object obj) {
        return obj == null || zza(obj.getClass());
    }
}

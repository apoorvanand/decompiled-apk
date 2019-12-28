package com.google.android.gms.internal.firebase_ml;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class zzig {
    public static Class<?> zza(ParameterizedType parameterizedType) {
        return (Class) parameterizedType.getRawType();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006f A[SYNTHETIC, Splitter:B:25:0x006f] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0081  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.IllegalArgumentException zza(java.lang.Exception r6, java.lang.Class<?> r7) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "unable to create new instance of class "
            r0.<init>(r1)
            java.lang.String r1 = r7.getName()
            r0.append(r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            boolean r2 = r7.isArray()
            r3 = 0
            if (r2 == 0) goto L_0x0020
            java.lang.String r7 = "because it is an array"
        L_0x001c:
            r1.add(r7)
            goto L_0x0078
        L_0x0020:
            boolean r2 = r7.isPrimitive()
            if (r2 == 0) goto L_0x0029
            java.lang.String r7 = "because it is primitive"
            goto L_0x001c
        L_0x0029:
            java.lang.Class<java.lang.Void> r2 = java.lang.Void.class
            if (r7 != r2) goto L_0x0030
            java.lang.String r7 = "because it is void"
            goto L_0x001c
        L_0x0030:
            int r2 = r7.getModifiers()
            boolean r2 = java.lang.reflect.Modifier.isInterface(r2)
            if (r2 == 0) goto L_0x0040
            java.lang.String r2 = "because it is an interface"
        L_0x003c:
            r1.add(r2)
            goto L_0x004d
        L_0x0040:
            int r2 = r7.getModifiers()
            boolean r2 = java.lang.reflect.Modifier.isAbstract(r2)
            if (r2 == 0) goto L_0x004d
            java.lang.String r2 = "because it is abstract"
            goto L_0x003c
        L_0x004d:
            java.lang.Class r2 = r7.getEnclosingClass()
            if (r2 == 0) goto L_0x0062
            int r2 = r7.getModifiers()
            boolean r2 = java.lang.reflect.Modifier.isStatic(r2)
            if (r2 != 0) goto L_0x0062
            java.lang.String r2 = "because it is not static"
            r1.add(r2)
        L_0x0062:
            int r2 = r7.getModifiers()
            boolean r2 = java.lang.reflect.Modifier.isPublic(r2)
            if (r2 != 0) goto L_0x006f
            java.lang.String r7 = "possibly because it is not public"
            goto L_0x001c
        L_0x006f:
            java.lang.Class[] r2 = new java.lang.Class[r3]     // Catch:{ NoSuchMethodException -> 0x0075 }
            r7.getConstructor(r2)     // Catch:{ NoSuchMethodException -> 0x0075 }
            goto L_0x0078
        L_0x0075:
            java.lang.String r7 = "because it has no accessible default constructor"
            goto L_0x001c
        L_0x0078:
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            int r7 = r1.size()
            r2 = 0
        L_0x007f:
            if (r3 >= r7) goto L_0x009b
            java.lang.Object r4 = r1.get(r3)
            int r3 = r3 + 1
            java.lang.String r4 = (java.lang.String) r4
            if (r2 == 0) goto L_0x0091
            java.lang.String r5 = " and"
            r0.append(r5)
            goto L_0x0092
        L_0x0091:
            r2 = 1
        L_0x0092:
            java.lang.String r5 = " "
            r0.append(r5)
            r0.append(r4)
            goto L_0x007f
        L_0x009b:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r7.<init>(r0, r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzig.zza(java.lang.Exception, java.lang.Class):java.lang.IllegalArgumentException");
    }

    public static Object zza(Collection<?> collection, Class<?> cls) {
        if (!cls.isPrimitive()) {
            return collection.toArray((Object[]) Array.newInstance(cls, collection.size()));
        }
        Object newInstance = Array.newInstance(cls, collection.size());
        int i = 0;
        for (Object obj : collection) {
            Array.set(newInstance, i, obj);
            i++;
        }
        return newInstance;
    }

    private static ParameterizedType zza(Type type, Class<?> cls) {
        Class<?> cls2;
        if (!(type instanceof Class) && !(type instanceof ParameterizedType)) {
            return null;
        }
        while (type != null && type != Object.class) {
            if (type instanceof Class) {
                cls2 = (Class) type;
            } else {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Class<?> zza = zza(parameterizedType);
                if (zza == cls) {
                    return parameterizedType;
                }
                if (cls.isInterface()) {
                    for (Type type2 : zza.getGenericInterfaces()) {
                        if (cls.isAssignableFrom(type2 instanceof Class ? (Class) type2 : zza((ParameterizedType) type2))) {
                            type = type2;
                            break;
                        }
                    }
                }
                cls2 = zza;
            }
            type = cls2.getGenericSuperclass();
        }
        return null;
    }

    private static Type zza(Type type, Class<?> cls, int i) {
        ParameterizedType zza = zza(type, cls);
        if (zza == null) {
            return null;
        }
        Type type2 = zza.getActualTypeArguments()[i];
        if (type2 instanceof TypeVariable) {
            Type zza2 = zza((List<Type>) Arrays.asList(new Type[]{type}), (TypeVariable<?>) (TypeVariable) type2);
            if (zza2 != null) {
                return zza2;
            }
        }
        return type2;
    }

    public static Type zza(WildcardType wildcardType) {
        Type[] lowerBounds = wildcardType.getLowerBounds();
        return lowerBounds.length != 0 ? lowerBounds[0] : wildcardType.getUpperBounds()[0];
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.reflect.TypeVariable, java.lang.reflect.TypeVariable<?>, java.lang.Object] */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        r5 = zza(r5, (java.lang.reflect.TypeVariable<?>) (java.lang.reflect.TypeVariable) r6);
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.reflect.Type zza(java.util.List<java.lang.reflect.Type> r5, java.lang.reflect.TypeVariable<?> r6) {
        /*
            java.lang.reflect.GenericDeclaration r0 = r6.getGenericDeclaration()
            boolean r1 = r0 instanceof java.lang.Class
            r2 = 0
            if (r1 == 0) goto L_0x004d
            r1 = r0
            java.lang.Class r1 = (java.lang.Class) r1
            int r3 = r5.size()
            r4 = r3
            r3 = r2
        L_0x0012:
            if (r3 != 0) goto L_0x0023
            int r4 = r4 + -1
            if (r4 < 0) goto L_0x0023
            java.lang.Object r3 = r5.get(r4)
            java.lang.reflect.Type r3 = (java.lang.reflect.Type) r3
            java.lang.reflect.ParameterizedType r3 = zza((java.lang.reflect.Type) r3, (java.lang.Class<?>) r1)
            goto L_0x0012
        L_0x0023:
            if (r3 == 0) goto L_0x004d
            java.lang.reflect.TypeVariable[] r0 = r0.getTypeParameters()
            r1 = 0
        L_0x002a:
            int r2 = r0.length
            if (r1 >= r2) goto L_0x0038
            r2 = r0[r1]
            boolean r2 = r2.equals(r6)
            if (r2 != 0) goto L_0x0038
            int r1 = r1 + 1
            goto L_0x002a
        L_0x0038:
            java.lang.reflect.Type[] r6 = r3.getActualTypeArguments()
            r6 = r6[r1]
            boolean r0 = r6 instanceof java.lang.reflect.TypeVariable
            if (r0 == 0) goto L_0x004c
            r0 = r6
            java.lang.reflect.TypeVariable r0 = (java.lang.reflect.TypeVariable) r0
            java.lang.reflect.Type r5 = zza((java.util.List<java.lang.reflect.Type>) r5, (java.lang.reflect.TypeVariable<?>) r0)
            if (r5 == 0) goto L_0x004c
            return r5
        L_0x004c:
            return r6
        L_0x004d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzig.zza(java.util.List, java.lang.reflect.TypeVariable):java.lang.reflect.Type");
    }

    public static boolean zza(Class<?> cls, Class<?> cls2) {
        return cls.isAssignableFrom(cls2) || cls2.isAssignableFrom(cls);
    }

    public static Class<?> zzb(List<Type> list, Type type) {
        if (type instanceof TypeVariable) {
            type = zza(list, (TypeVariable<?>) (TypeVariable) type);
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance(zzb(list, zzd(type)), 0).getClass();
        }
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return zza((ParameterizedType) type);
        }
        zzib.checkArgument(type == null, "wildcard type is not supported: %s", type);
        return Object.class;
    }

    public static boolean zzc(Type type) {
        if (!(type instanceof GenericArrayType)) {
            return (type instanceof Class) && ((Class) type).isArray();
        }
        return true;
    }

    public static Type zzd(Type type) {
        return type instanceof GenericArrayType ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
    }

    public static Type zze(Type type) {
        return zza(type, Iterable.class, 0);
    }

    public static Type zzf(Type type) {
        return zza(type, Map.class, 1);
    }

    public static <T> T zzg(Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (IllegalAccessException e) {
            throw zza((Exception) e, (Class<?>) cls);
        } catch (InstantiationException e2) {
            throw zza((Exception) e2, (Class<?>) cls);
        }
    }

    public static <T> Iterable<T> zzi(Object obj) {
        if (obj instanceof Iterable) {
            return (Iterable) obj;
        }
        Class<?> cls = obj.getClass();
        zzib.checkArgument(cls.isArray(), "not an array or Iterable: %s", cls);
        return !cls.getComponentType().isPrimitive() ? Arrays.asList((Object[]) obj) : new zzih(obj);
    }
}

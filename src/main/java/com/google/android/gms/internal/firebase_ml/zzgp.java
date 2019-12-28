package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.internal.firebase_ml.zzgr;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class zzgp {
    private static WeakHashMap<Class<?>, Field> zzwn = new WeakHashMap<>();
    private static final Lock zzwo = new ReentrantLock();

    /* JADX WARNING: Removed duplicated region for block: B:128:0x01bd A[Catch:{ IllegalArgumentException -> 0x043c }] */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x01c7 A[Catch:{ IllegalArgumentException -> 0x043c }] */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x01ef A[Catch:{ IllegalArgumentException -> 0x043c }] */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0242 A[Catch:{ IllegalArgumentException -> 0x043c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Object zza(java.lang.reflect.Field r20, java.lang.reflect.Type r21, java.util.ArrayList<java.lang.reflect.Type> r22, java.lang.Object r23, com.google.android.gms.internal.firebase_ml.zzgj r24, boolean r25) {
        /*
            r19 = this;
            r8 = r20
            r0 = r22
            r1 = r21
            java.lang.reflect.Type r9 = com.google.android.gms.internal.firebase_ml.zzhl.zza((java.util.List<java.lang.reflect.Type>) r0, (java.lang.reflect.Type) r1)
            boolean r1 = r9 instanceof java.lang.Class
            r10 = 0
            if (r1 == 0) goto L_0x0013
            r1 = r9
            java.lang.Class r1 = (java.lang.Class) r1
            goto L_0x0014
        L_0x0013:
            r1 = r10
        L_0x0014:
            boolean r2 = r9 instanceof java.lang.reflect.ParameterizedType
            if (r2 == 0) goto L_0x001f
            r1 = r9
            java.lang.reflect.ParameterizedType r1 = (java.lang.reflect.ParameterizedType) r1
            java.lang.Class r1 = com.google.android.gms.internal.firebase_ml.zzig.zza((java.lang.reflect.ParameterizedType) r1)
        L_0x001f:
            java.lang.Class<java.lang.Void> r2 = java.lang.Void.class
            if (r1 != r2) goto L_0x0027
            r19.zzfu()
            return r10
        L_0x0027:
            com.google.android.gms.internal.firebase_ml.zzgt r2 = r19.zzfs()
            int[] r3 = com.google.android.gms.internal.firebase_ml.zzgq.a     // Catch:{ IllegalArgumentException -> 0x043c }
            com.google.android.gms.internal.firebase_ml.zzgt r4 = r19.zzfs()     // Catch:{ IllegalArgumentException -> 0x043c }
            int r4 = r4.ordinal()     // Catch:{ IllegalArgumentException -> 0x043c }
            r3 = r3[r4]     // Catch:{ IllegalArgumentException -> 0x043c }
            r11 = 0
            r12 = 1
            switch(r3) {
                case 1: goto L_0x024c;
                case 2: goto L_0x01d1;
                case 3: goto L_0x01d1;
                case 4: goto L_0x024c;
                case 5: goto L_0x024c;
                case 6: goto L_0x01a1;
                case 7: goto L_0x01a1;
                case 8: goto L_0x00e4;
                case 9: goto L_0x00e4;
                case 10: goto L_0x008b;
                case 11: goto L_0x0040;
                default: goto L_0x003c;
            }     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x003c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x043c }
            goto L_0x0419
        L_0x0040:
            if (r1 == 0) goto L_0x0048
            boolean r2 = r1.isPrimitive()     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r2 != 0) goto L_0x0049
        L_0x0048:
            r11 = 1
        L_0x0049:
            java.lang.String r2 = "primitive number field but found a JSON null"
            com.google.android.gms.internal.firebase_ml.zzky.checkArgument(r11, r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 == 0) goto L_0x0082
            int r2 = r1.getModifiers()     // Catch:{ IllegalArgumentException -> 0x043c }
            r2 = r2 & 1536(0x600, float:2.152E-42)
            if (r2 == 0) goto L_0x0082
            java.lang.Class<java.util.Collection> r2 = java.util.Collection.class
            boolean r2 = com.google.android.gms.internal.firebase_ml.zzig.zza((java.lang.Class<?>) r1, (java.lang.Class<?>) r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r2 == 0) goto L_0x006d
            java.util.Collection r0 = com.google.android.gms.internal.firebase_ml.zzhl.zzb(r9)     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.Class r0 = r0.getClass()     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.Object r0 = com.google.android.gms.internal.firebase_ml.zzhl.zze(r0)     // Catch:{ IllegalArgumentException -> 0x043c }
            return r0
        L_0x006d:
            java.lang.Class<java.util.Map> r2 = java.util.Map.class
            boolean r2 = com.google.android.gms.internal.firebase_ml.zzig.zza((java.lang.Class<?>) r1, (java.lang.Class<?>) r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r2 == 0) goto L_0x0082
            java.util.Map r0 = com.google.android.gms.internal.firebase_ml.zzhl.zzf((java.lang.Class<?>) r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.Class r0 = r0.getClass()     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.Object r0 = com.google.android.gms.internal.firebase_ml.zzhl.zze(r0)     // Catch:{ IllegalArgumentException -> 0x043c }
            return r0
        L_0x0082:
            java.lang.Class r0 = com.google.android.gms.internal.firebase_ml.zzig.zzb(r0, r9)     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.Object r0 = com.google.android.gms.internal.firebase_ml.zzhl.zze(r0)     // Catch:{ IllegalArgumentException -> 0x043c }
            return r0
        L_0x008b:
            java.lang.String r0 = r19.getText()     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.String r0 = r0.trim()     // Catch:{ IllegalArgumentException -> 0x043c }
            java.util.Locale r2 = java.util.Locale.US     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.String r0 = r0.toLowerCase(r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.Class r2 = java.lang.Float.TYPE     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 == r2) goto L_0x00a9
            java.lang.Class<java.lang.Float> r2 = java.lang.Float.class
            if (r1 == r2) goto L_0x00a9
            java.lang.Class r2 = java.lang.Double.TYPE     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 == r2) goto L_0x00a9
            java.lang.Class<java.lang.Double> r2 = java.lang.Double.class
            if (r1 != r2) goto L_0x00c1
        L_0x00a9:
            java.lang.String r2 = "nan"
            boolean r2 = r0.equals(r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r2 != 0) goto L_0x00db
            java.lang.String r2 = "infinity"
            boolean r2 = r0.equals(r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r2 != 0) goto L_0x00db
            java.lang.String r2 = "-infinity"
            boolean r0 = r0.equals(r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r0 != 0) goto L_0x00db
        L_0x00c1:
            if (r1 == 0) goto L_0x00d5
            java.lang.Class<java.lang.Number> r0 = java.lang.Number.class
            boolean r0 = r0.isAssignableFrom(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r0 == 0) goto L_0x00d5
            if (r8 == 0) goto L_0x00d6
            java.lang.Class<com.google.android.gms.internal.firebase_ml.zzgs> r0 = com.google.android.gms.internal.firebase_ml.zzgs.class
            java.lang.annotation.Annotation r0 = r8.getAnnotation(r0)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r0 == 0) goto L_0x00d6
        L_0x00d5:
            r11 = 1
        L_0x00d6:
            java.lang.String r0 = "number field formatted as a JSON string must use the @JsonString annotation"
            com.google.android.gms.internal.firebase_ml.zzky.checkArgument(r11, r0)     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x00db:
            java.lang.String r0 = r19.getText()     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.Object r0 = com.google.android.gms.internal.firebase_ml.zzhl.zza((java.lang.reflect.Type) r9, (java.lang.String) r0)     // Catch:{ IllegalArgumentException -> 0x043c }
            return r0
        L_0x00e4:
            if (r8 == 0) goto L_0x00ee
            java.lang.Class<com.google.android.gms.internal.firebase_ml.zzgs> r0 = com.google.android.gms.internal.firebase_ml.zzgs.class
            java.lang.annotation.Annotation r0 = r8.getAnnotation(r0)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r0 != 0) goto L_0x00ef
        L_0x00ee:
            r11 = 1
        L_0x00ef:
            java.lang.String r0 = "number type formatted as a JSON number cannot use @JsonString annotation"
            com.google.android.gms.internal.firebase_ml.zzky.checkArgument(r11, r0)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 == 0) goto L_0x019c
            java.lang.Class<java.math.BigDecimal> r0 = java.math.BigDecimal.class
            boolean r0 = r1.isAssignableFrom(r0)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r0 == 0) goto L_0x0100
            goto L_0x019c
        L_0x0100:
            java.lang.Class<java.math.BigInteger> r0 = java.math.BigInteger.class
            if (r1 != r0) goto L_0x0109
            java.math.BigInteger r0 = r19.zzga()     // Catch:{ IllegalArgumentException -> 0x043c }
            return r0
        L_0x0109:
            java.lang.Class<java.lang.Double> r0 = java.lang.Double.class
            if (r1 == r0) goto L_0x0193
            java.lang.Class r0 = java.lang.Double.TYPE     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 != r0) goto L_0x0113
            goto L_0x0193
        L_0x0113:
            java.lang.Class<java.lang.Long> r0 = java.lang.Long.class
            if (r1 == r0) goto L_0x018a
            java.lang.Class r0 = java.lang.Long.TYPE     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 != r0) goto L_0x011d
            goto L_0x018a
        L_0x011d:
            java.lang.Class<java.lang.Float> r0 = java.lang.Float.class
            if (r1 == r0) goto L_0x0181
            java.lang.Class r0 = java.lang.Float.TYPE     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 != r0) goto L_0x0126
            goto L_0x0181
        L_0x0126:
            java.lang.Class<java.lang.Integer> r0 = java.lang.Integer.class
            if (r1 == r0) goto L_0x0178
            java.lang.Class r0 = java.lang.Integer.TYPE     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 != r0) goto L_0x012f
            goto L_0x0178
        L_0x012f:
            java.lang.Class<java.lang.Short> r0 = java.lang.Short.class
            if (r1 == r0) goto L_0x016f
            java.lang.Class r0 = java.lang.Short.TYPE     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 != r0) goto L_0x0138
            goto L_0x016f
        L_0x0138:
            java.lang.Class<java.lang.Byte> r0 = java.lang.Byte.class
            if (r1 == r0) goto L_0x0166
            java.lang.Class r0 = java.lang.Byte.TYPE     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 != r0) goto L_0x0141
            goto L_0x0166
        L_0x0141:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.String r1 = java.lang.String.valueOf(r9)     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.String r2 = java.lang.String.valueOf(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            int r2 = r2.length()     // Catch:{ IllegalArgumentException -> 0x043c }
            int r2 = r2 + 30
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x043c }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.String r2 = "expected numeric type but got "
            r3.append(r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            r3.append(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.String r1 = r3.toString()     // Catch:{ IllegalArgumentException -> 0x043c }
            r0.<init>(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            throw r0     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x0166:
            byte r0 = r19.zzfv()     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.Byte r0 = java.lang.Byte.valueOf(r0)     // Catch:{ IllegalArgumentException -> 0x043c }
            return r0
        L_0x016f:
            short r0 = r19.zzfw()     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.Short r0 = java.lang.Short.valueOf(r0)     // Catch:{ IllegalArgumentException -> 0x043c }
            return r0
        L_0x0178:
            int r0 = r19.getIntValue()     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ IllegalArgumentException -> 0x043c }
            return r0
        L_0x0181:
            float r0 = r19.zzfx()     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.Float r0 = java.lang.Float.valueOf(r0)     // Catch:{ IllegalArgumentException -> 0x043c }
            return r0
        L_0x018a:
            long r0 = r19.zzfy()     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.Long r0 = java.lang.Long.valueOf(r0)     // Catch:{ IllegalArgumentException -> 0x043c }
            return r0
        L_0x0193:
            double r0 = r19.zzfz()     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.Double r0 = java.lang.Double.valueOf(r0)     // Catch:{ IllegalArgumentException -> 0x043c }
            return r0
        L_0x019c:
            java.math.BigDecimal r0 = r19.zzgb()     // Catch:{ IllegalArgumentException -> 0x043c }
            return r0
        L_0x01a1:
            if (r9 == 0) goto L_0x01b4
            java.lang.Class r0 = java.lang.Boolean.TYPE     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 == r0) goto L_0x01b4
            if (r1 == 0) goto L_0x01b2
            java.lang.Class<java.lang.Boolean> r0 = java.lang.Boolean.class
            boolean r0 = r1.isAssignableFrom(r0)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r0 == 0) goto L_0x01b2
            goto L_0x01b4
        L_0x01b2:
            r0 = 0
            goto L_0x01b5
        L_0x01b4:
            r0 = 1
        L_0x01b5:
            java.lang.String r1 = "expected type Boolean or boolean but got %s"
            java.lang.Object[] r3 = new java.lang.Object[r12]     // Catch:{ IllegalArgumentException -> 0x043c }
            r3[r11] = r9     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r0 == 0) goto L_0x01c7
            com.google.android.gms.internal.firebase_ml.zzgt r0 = com.google.android.gms.internal.firebase_ml.zzgt.VALUE_TRUE     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r2 != r0) goto L_0x01c4
            java.lang.Boolean r0 = java.lang.Boolean.TRUE     // Catch:{ IllegalArgumentException -> 0x043c }
            return r0
        L_0x01c4:
            java.lang.Boolean r0 = java.lang.Boolean.FALSE     // Catch:{ IllegalArgumentException -> 0x043c }
            return r0
        L_0x01c7:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.String r1 = com.google.android.gms.internal.firebase_ml.zzlg.zzb(r1, r3)     // Catch:{ IllegalArgumentException -> 0x043c }
            r0.<init>(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            throw r0     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x01d1:
            boolean r13 = com.google.android.gms.internal.firebase_ml.zzig.zzc(r9)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r9 == 0) goto L_0x01e6
            if (r13 != 0) goto L_0x01e6
            if (r1 == 0) goto L_0x01e4
            java.lang.Class<java.util.Collection> r2 = java.util.Collection.class
            boolean r2 = com.google.android.gms.internal.firebase_ml.zzig.zza((java.lang.Class<?>) r1, (java.lang.Class<?>) r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r2 == 0) goto L_0x01e4
            goto L_0x01e6
        L_0x01e4:
            r2 = 0
            goto L_0x01e7
        L_0x01e6:
            r2 = 1
        L_0x01e7:
            java.lang.String r3 = "expected collection or array type but got %s"
            java.lang.Object[] r4 = new java.lang.Object[r12]     // Catch:{ IllegalArgumentException -> 0x043c }
            r4[r11] = r9     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r2 == 0) goto L_0x0242
            if (r24 == 0) goto L_0x01fa
            if (r8 != 0) goto L_0x01f4
            goto L_0x01fa
        L_0x01f4:
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError     // Catch:{ IllegalArgumentException -> 0x043c }
            r0.<init>()     // Catch:{ IllegalArgumentException -> 0x043c }
            throw r0     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x01fa:
            java.util.Collection r11 = com.google.android.gms.internal.firebase_ml.zzhl.zzb(r9)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r13 == 0) goto L_0x0205
            java.lang.reflect.Type r10 = com.google.android.gms.internal.firebase_ml.zzig.zzd(r9)     // Catch:{ IllegalArgumentException -> 0x043c }
            goto L_0x0213
        L_0x0205:
            if (r1 == 0) goto L_0x0213
            java.lang.Class<java.lang.Iterable> r2 = java.lang.Iterable.class
            boolean r1 = r2.isAssignableFrom(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 == 0) goto L_0x0213
            java.lang.reflect.Type r10 = com.google.android.gms.internal.firebase_ml.zzig.zze(r9)     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x0213:
            java.lang.reflect.Type r9 = com.google.android.gms.internal.firebase_ml.zzhl.zza((java.util.List<java.lang.reflect.Type>) r0, (java.lang.reflect.Type) r10)     // Catch:{ IllegalArgumentException -> 0x043c }
            com.google.android.gms.internal.firebase_ml.zzgt r1 = r19.zzgd()     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x021b:
            com.google.android.gms.internal.firebase_ml.zzgt r2 = com.google.android.gms.internal.firebase_ml.zzgt.END_ARRAY     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 == r2) goto L_0x0236
            r7 = 1
            r1 = r19
            r2 = r20
            r3 = r9
            r4 = r22
            r5 = r11
            r6 = r24
            java.lang.Object r1 = r1.zza(r2, r3, r4, r5, r6, r7)     // Catch:{ IllegalArgumentException -> 0x043c }
            r11.add(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            com.google.android.gms.internal.firebase_ml.zzgt r1 = r19.zzfr()     // Catch:{ IllegalArgumentException -> 0x043c }
            goto L_0x021b
        L_0x0236:
            if (r13 == 0) goto L_0x0241
            java.lang.Class r0 = com.google.android.gms.internal.firebase_ml.zzig.zzb(r0, r9)     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.Object r0 = com.google.android.gms.internal.firebase_ml.zzig.zza((java.util.Collection<?>) r11, (java.lang.Class<?>) r0)     // Catch:{ IllegalArgumentException -> 0x043c }
            return r0
        L_0x0241:
            return r11
        L_0x0242:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.String r1 = com.google.android.gms.internal.firebase_ml.zzlg.zzb(r3, r4)     // Catch:{ IllegalArgumentException -> 0x043c }
            r0.<init>(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            throw r0     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x024c:
            boolean r2 = com.google.android.gms.internal.firebase_ml.zzig.zzc(r9)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r2 != 0) goto L_0x0254
            r2 = 1
            goto L_0x0255
        L_0x0254:
            r2 = 0
        L_0x0255:
            java.lang.String r3 = "expected object or map type but got %s"
            java.lang.Object[] r4 = new java.lang.Object[r12]     // Catch:{ IllegalArgumentException -> 0x043c }
            r4[r11] = r9     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r2 == 0) goto L_0x040f
            if (r25 == 0) goto L_0x0265
            java.lang.reflect.Field r2 = zzc(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            r13 = r2
            goto L_0x0266
        L_0x0265:
            r13 = r10
        L_0x0266:
            if (r1 == 0) goto L_0x0271
            if (r24 != 0) goto L_0x026b
            goto L_0x0271
        L_0x026b:
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError     // Catch:{ IllegalArgumentException -> 0x043c }
            r0.<init>()     // Catch:{ IllegalArgumentException -> 0x043c }
            throw r0     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x0271:
            if (r1 == 0) goto L_0x027d
            java.lang.Class<java.util.Map> r2 = java.util.Map.class
            boolean r2 = com.google.android.gms.internal.firebase_ml.zzig.zza((java.lang.Class<?>) r1, (java.lang.Class<?>) r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r2 == 0) goto L_0x027d
            r2 = 1
            goto L_0x027e
        L_0x027d:
            r2 = 0
        L_0x027e:
            if (r13 == 0) goto L_0x0286
            com.google.android.gms.internal.firebase_ml.zzgk r3 = new com.google.android.gms.internal.firebase_ml.zzgk     // Catch:{ IllegalArgumentException -> 0x043c }
            r3.<init>()     // Catch:{ IllegalArgumentException -> 0x043c }
            goto L_0x0294
        L_0x0286:
            if (r2 != 0) goto L_0x0290
            if (r1 != 0) goto L_0x028b
            goto L_0x0290
        L_0x028b:
            java.lang.Object r3 = com.google.android.gms.internal.firebase_ml.zzig.zzg(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            goto L_0x0294
        L_0x0290:
            java.util.Map r3 = com.google.android.gms.internal.firebase_ml.zzhl.zzf((java.lang.Class<?>) r1)     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x0294:
            r14 = r3
            int r15 = r22.size()     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r9 == 0) goto L_0x029e
            r0.add(r9)     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x029e:
            if (r2 == 0) goto L_0x02c8
            java.lang.Class<com.google.android.gms.internal.firebase_ml.zzhs> r2 = com.google.android.gms.internal.firebase_ml.zzhs.class
            boolean r2 = r2.isAssignableFrom(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r2 != 0) goto L_0x02c8
            java.lang.Class<java.util.Map> r2 = java.util.Map.class
            boolean r1 = r2.isAssignableFrom(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 == 0) goto L_0x02b6
            java.lang.reflect.Type r1 = com.google.android.gms.internal.firebase_ml.zzig.zzf(r9)     // Catch:{ IllegalArgumentException -> 0x043c }
            r4 = r1
            goto L_0x02b7
        L_0x02b6:
            r4 = r10
        L_0x02b7:
            if (r4 == 0) goto L_0x02c8
            r3 = r14
            java.util.Map r3 = (java.util.Map) r3     // Catch:{ IllegalArgumentException -> 0x043c }
            r1 = r19
            r2 = r20
            r5 = r22
            r6 = r24
            r1.zza(r2, r3, r4, r5, r6)     // Catch:{ IllegalArgumentException -> 0x043c }
            return r14
        L_0x02c8:
            boolean r1 = r14 instanceof com.google.android.gms.internal.firebase_ml.zzgk     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 == 0) goto L_0x02d6
            r1 = r14
            com.google.android.gms.internal.firebase_ml.zzgk r1 = (com.google.android.gms.internal.firebase_ml.zzgk) r1     // Catch:{ IllegalArgumentException -> 0x043c }
            com.google.android.gms.internal.firebase_ml.zzgl r2 = r19.zzfq()     // Catch:{ IllegalArgumentException -> 0x043c }
            r1.zza(r2)     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x02d6:
            com.google.android.gms.internal.firebase_ml.zzgt r1 = r19.zzgd()     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.Class r2 = r14.getClass()     // Catch:{ IllegalArgumentException -> 0x043c }
            com.google.android.gms.internal.firebase_ml.zzhj r7 = com.google.android.gms.internal.firebase_ml.zzhj.zzd(r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.Class<com.google.android.gms.internal.firebase_ml.zzhs> r3 = com.google.android.gms.internal.firebase_ml.zzhs.class
            boolean r16 = r3.isAssignableFrom(r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r16 != 0) goto L_0x0307
            java.lang.Class<java.util.Map> r3 = java.util.Map.class
            boolean r3 = r3.isAssignableFrom(r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r3 == 0) goto L_0x0307
            r3 = r14
            java.util.Map r3 = (java.util.Map) r3     // Catch:{ IllegalArgumentException -> 0x043c }
            r4 = 0
            java.lang.reflect.Type r5 = com.google.android.gms.internal.firebase_ml.zzig.zzf(r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            r1 = r19
            r2 = r4
            r4 = r5
            r5 = r22
            r6 = r24
            r1.zza(r2, r3, r4, r5, r6)     // Catch:{ IllegalArgumentException -> 0x043c }
            goto L_0x0390
        L_0x0307:
            com.google.android.gms.internal.firebase_ml.zzgt r2 = com.google.android.gms.internal.firebase_ml.zzgt.FIELD_NAME     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 != r2) goto L_0x0390
            java.lang.String r6 = r19.getText()     // Catch:{ IllegalArgumentException -> 0x043c }
            r19.zzfr()     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r24 != 0) goto L_0x038a
            com.google.android.gms.internal.firebase_ml.zzhr r5 = r7.zzal(r6)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r5 == 0) goto L_0x035c
            boolean r1 = r5.zzgq()     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 == 0) goto L_0x032f
            boolean r1 = r5.isPrimitive()     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 == 0) goto L_0x0327
            goto L_0x032f
        L_0x0327:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.String r1 = "final array/object fields are not supported"
            r0.<init>(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            throw r0     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x032f:
            java.lang.reflect.Field r2 = r5.zzgp()     // Catch:{ IllegalArgumentException -> 0x043c }
            int r6 = r22.size()     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.reflect.Type r1 = r2.getGenericType()     // Catch:{ IllegalArgumentException -> 0x043c }
            r0.add(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.reflect.Type r3 = r5.getGenericType()     // Catch:{ IllegalArgumentException -> 0x043c }
            r17 = 1
            r1 = r19
            r4 = r22
            r10 = r5
            r5 = r14
            r11 = r6
            r6 = r24
            r18 = r7
            r7 = r17
            java.lang.Object r1 = r1.zza(r2, r3, r4, r5, r6, r7)     // Catch:{ IllegalArgumentException -> 0x043c }
            r0.remove(r11)     // Catch:{ IllegalArgumentException -> 0x043c }
            r10.zzb(r14, r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            goto L_0x037b
        L_0x035c:
            r18 = r7
            if (r16 == 0) goto L_0x0376
            r10 = r14
            com.google.android.gms.internal.firebase_ml.zzhs r10 = (com.google.android.gms.internal.firebase_ml.zzhs) r10     // Catch:{ IllegalArgumentException -> 0x043c }
            r2 = 0
            r3 = 0
            r7 = 1
            r1 = r19
            r4 = r22
            r5 = r14
            r11 = r6
            r6 = r24
            java.lang.Object r1 = r1.zza(r2, r3, r4, r5, r6, r7)     // Catch:{ IllegalArgumentException -> 0x043c }
            r10.zzb(r11, r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            goto L_0x037b
        L_0x0376:
            if (r24 != 0) goto L_0x0384
            r19.zzfu()     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x037b:
            com.google.android.gms.internal.firebase_ml.zzgt r1 = r19.zzfr()     // Catch:{ IllegalArgumentException -> 0x043c }
            r7 = r18
            r10 = 0
            r11 = 0
            goto L_0x0307
        L_0x0384:
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError     // Catch:{ IllegalArgumentException -> 0x043c }
            r0.<init>()     // Catch:{ IllegalArgumentException -> 0x043c }
            throw r0     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x038a:
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError     // Catch:{ IllegalArgumentException -> 0x043c }
            r0.<init>()     // Catch:{ IllegalArgumentException -> 0x043c }
            throw r0     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x0390:
            if (r9 == 0) goto L_0x0395
            r0.remove(r15)     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x0395:
            if (r13 != 0) goto L_0x0398
            return r14
        L_0x0398:
            r1 = r14
            com.google.android.gms.internal.firebase_ml.zzgk r1 = (com.google.android.gms.internal.firebase_ml.zzgk) r1     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.String r2 = r13.getName()     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r1 == 0) goto L_0x03a7
            r2 = 1
            goto L_0x03a8
        L_0x03a7:
            r2 = 0
        L_0x03a8:
            java.lang.String r3 = "No value specified for @JsonPolymorphicTypeMap field"
            com.google.android.gms.internal.firebase_ml.zzky.checkArgument(r2, r3)     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.String r1 = r1.toString()     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.Class<com.google.android.gms.internal.firebase_ml.zzgr> r2 = com.google.android.gms.internal.firebase_ml.zzgr.class
            java.lang.annotation.Annotation r2 = r13.getAnnotation(r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            com.google.android.gms.internal.firebase_ml.zzgr r2 = (com.google.android.gms.internal.firebase_ml.zzgr) r2     // Catch:{ IllegalArgumentException -> 0x043c }
            com.google.android.gms.internal.firebase_ml.zzgr$zza[] r2 = r2.zzge()     // Catch:{ IllegalArgumentException -> 0x043c }
            int r3 = r2.length     // Catch:{ IllegalArgumentException -> 0x043c }
            r4 = 0
        L_0x03bf:
            if (r4 >= r3) goto L_0x03d6
            r5 = r2[r4]     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.String r6 = r5.zzgf()     // Catch:{ IllegalArgumentException -> 0x043c }
            boolean r6 = r6.equals(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r6 == 0) goto L_0x03d3
            java.lang.Class r2 = r5.zzgg()     // Catch:{ IllegalArgumentException -> 0x043c }
            r3 = r2
            goto L_0x03d7
        L_0x03d3:
            int r4 = r4 + 1
            goto L_0x03bf
        L_0x03d6:
            r3 = 0
        L_0x03d7:
            if (r3 == 0) goto L_0x03da
            goto L_0x03db
        L_0x03da:
            r12 = 0
        L_0x03db:
            java.lang.String r2 = "No TypeDef annotation found with key: "
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            int r4 = r1.length()     // Catch:{ IllegalArgumentException -> 0x043c }
            if (r4 == 0) goto L_0x03ec
            java.lang.String r1 = r2.concat(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            goto L_0x03f1
        L_0x03ec:
            java.lang.String r1 = new java.lang.String     // Catch:{ IllegalArgumentException -> 0x043c }
            r1.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x03f1:
            com.google.android.gms.internal.firebase_ml.zzky.checkArgument(r12, r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            com.google.android.gms.internal.firebase_ml.zzgl r1 = r19.zzfq()     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.String r2 = r1.toString(r14)     // Catch:{ IllegalArgumentException -> 0x043c }
            com.google.android.gms.internal.firebase_ml.zzgp r1 = r1.zzaj(r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            r1.zzgc()     // Catch:{ IllegalArgumentException -> 0x043c }
            r5 = 0
            r6 = 0
            r7 = 0
            r2 = r20
            r4 = r22
            java.lang.Object r0 = r1.zza(r2, r3, r4, r5, r6, r7)     // Catch:{ IllegalArgumentException -> 0x043c }
            return r0
        L_0x040f:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.String r1 = com.google.android.gms.internal.firebase_ml.zzlg.zzb(r3, r4)     // Catch:{ IllegalArgumentException -> 0x043c }
            r0.<init>(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            throw r0     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x0419:
            java.lang.String r1 = java.lang.String.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.String r2 = java.lang.String.valueOf(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            int r2 = r2.length()     // Catch:{ IllegalArgumentException -> 0x043c }
            int r2 = r2 + 27
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x043c }
            r3.<init>(r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.String r2 = "unexpected JSON node type: "
            r3.append(r2)     // Catch:{ IllegalArgumentException -> 0x043c }
            r3.append(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            java.lang.String r1 = r3.toString()     // Catch:{ IllegalArgumentException -> 0x043c }
            r0.<init>(r1)     // Catch:{ IllegalArgumentException -> 0x043c }
            throw r0     // Catch:{ IllegalArgumentException -> 0x043c }
        L_0x043c:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r19.zzft()
            if (r2 == 0) goto L_0x0450
            java.lang.String r3 = "key "
            r1.append(r3)
            r1.append(r2)
        L_0x0450:
            if (r8 == 0) goto L_0x0461
            if (r2 == 0) goto L_0x0459
            java.lang.String r2 = ", "
            r1.append(r2)
        L_0x0459:
            java.lang.String r2 = "field "
            r1.append(r2)
            r1.append(r8)
        L_0x0461:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r1.toString()
            r2.<init>(r1, r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzgp.zza(java.lang.reflect.Field, java.lang.reflect.Type, java.util.ArrayList, java.lang.Object, com.google.android.gms.internal.firebase_ml.zzgj, boolean):java.lang.Object");
    }

    private final void zza(Field field, Map<String, Object> map, Type type, ArrayList<Type> arrayList, zzgj zzgj) {
        zzgt zzgd = zzgd();
        while (zzgd == zzgt.FIELD_NAME) {
            String text = getText();
            zzfr();
            if (zzgj == null) {
                map.put(text, zza(field, type, arrayList, map, zzgj, true));
                zzgd = zzfr();
            } else {
                throw new NoSuchMethodError();
            }
        }
    }

    private static Field zzc(Class<?> cls) {
        Field field = null;
        if (cls == null) {
            return null;
        }
        zzwo.lock();
        try {
            if (zzwn.containsKey(cls)) {
                return zzwn.get(cls);
            }
            for (zzhr zzgp : zzhj.zzd(cls).zzgn()) {
                Field zzgp2 = zzgp.zzgp();
                zzgr zzgr = (zzgr) zzgp2.getAnnotation(zzgr.class);
                if (zzgr != null) {
                    Object[] objArr = {cls};
                    if (field == null) {
                        boolean zza = zzhl.zza(zzgp2.getType());
                        Object[] objArr2 = {cls, zzgp2.getType()};
                        if (zza) {
                            zzgr.zza[] zzge = zzgr.zzge();
                            HashSet hashSet = new HashSet();
                            zzky.checkArgument(zzge.length > 0, "@JsonPolymorphicTypeMap must have at least one @TypeDef");
                            int length = zzge.length;
                            int i = 0;
                            while (i < length) {
                                zzgr.zza zza2 = zzge[i];
                                boolean add = hashSet.add(zza2.zzgf());
                                Object[] objArr3 = {zza2.zzgf()};
                                if (add) {
                                    i++;
                                } else {
                                    throw new IllegalArgumentException(zzlg.zzb("Class contains two @TypeDef annotations with identical key: %s", objArr3));
                                }
                            }
                            field = zzgp2;
                        } else {
                            throw new IllegalArgumentException(zzlg.zzb("Field which has the @JsonPolymorphicTypeMap, %s, is not a supported type: %s", objArr2));
                        }
                    } else {
                        throw new IllegalArgumentException(zzlg.zzb("Class contains more than one field with @JsonPolymorphicTypeMap annotation: %s", objArr));
                    }
                }
            }
            zzwn.put(cls, field);
            zzwo.unlock();
            return field;
        } finally {
            zzwo.unlock();
        }
    }

    private final zzgt zzgc() {
        zzgt zzfs = zzfs();
        if (zzfs == null) {
            zzfs = zzfr();
        }
        zzky.checkArgument(zzfs != null, "no JSON input found");
        return zzfs;
    }

    private final zzgt zzgd() {
        zzgt zzgc = zzgc();
        switch (zzgq.a[zzgc.ordinal()]) {
            case 1:
                zzgt zzfr = zzfr();
                zzky.checkArgument(zzfr == zzgt.FIELD_NAME || zzfr == zzgt.END_OBJECT, zzfr);
                return zzfr;
            case 2:
                return zzfr();
            default:
                return zzgc;
        }
    }

    public abstract void close();

    public abstract int getIntValue();

    public abstract String getText();

    public final <T> T zza(Class<T> cls, zzgj zzgj) {
        try {
            return zza(cls, false, (zzgj) null);
        } finally {
            close();
        }
    }

    public final Object zza(Type type, boolean z, zzgj zzgj) {
        try {
            if (!Void.class.equals(type)) {
                zzgc();
            }
            return zza((Field) null, type, new ArrayList(), (Object) null, (zzgj) null, true);
        } finally {
            if (z) {
                close();
            }
        }
    }

    public final String zza(Set<String> set) {
        zzgt zzgd = zzgd();
        while (zzgd == zzgt.FIELD_NAME) {
            String text = getText();
            zzfr();
            if (set.contains(text)) {
                return text;
            }
            zzfu();
            zzgd = zzfr();
        }
        return null;
    }

    public abstract zzgl zzfq();

    public abstract zzgt zzfr();

    public abstract zzgt zzfs();

    public abstract String zzft();

    public abstract zzgp zzfu();

    public abstract byte zzfv();

    public abstract short zzfw();

    public abstract float zzfx();

    public abstract long zzfy();

    public abstract double zzfz();

    public abstract BigInteger zzga();

    public abstract BigDecimal zzgb();
}

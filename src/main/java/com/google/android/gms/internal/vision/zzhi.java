package com.google.android.gms.internal.vision;

import java.util.List;
import java.util.Map;
import kotlin.text.Typography;

final class zzhi {
    static String a(zzhf zzhf, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zza(zzhf, sb, 0);
        return sb.toString();
    }

    static final void a(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object a : (List) obj) {
                a(sb, i, str, a);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry a2 : ((Map) obj).entrySet()) {
                a(sb, i, str, a2);
            }
        } else {
            sb.append(10);
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                sb.append(' ');
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(zzik.a(zzeo.zzl((String) obj)));
                sb.append(Typography.quote);
            } else if (obj instanceof zzeo) {
                sb.append(": \"");
                sb.append(zzik.a((zzeo) obj));
                sb.append(Typography.quote);
            } else if (obj instanceof zzfy) {
                sb.append(" {");
                zza((zzfy) obj, sb, i + 2);
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else if (obj instanceof Map.Entry) {
                sb.append(" {");
                Map.Entry entry = (Map.Entry) obj;
                int i4 = i + 2;
                a(sb, i4, "key", entry.getKey());
                a(sb, i4, "value", entry.getValue());
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else {
                sb.append(": ");
                sb.append(obj.toString());
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01f8, code lost:
        if (((java.lang.Boolean) r6).booleanValue() == false) goto L_0x01fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x020a, code lost:
        if (((java.lang.Integer) r6).intValue() == 0) goto L_0x01fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x021b, code lost:
        if (((java.lang.Float) r6).floatValue() == 0.0f) goto L_0x01fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x022d, code lost:
        if (((java.lang.Double) r6).doubleValue() == 0.0d) goto L_0x01fa;
     */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0260  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0262  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(com.google.android.gms.internal.vision.zzhf r12, java.lang.StringBuilder r13, int r14) {
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.util.TreeSet r2 = new java.util.TreeSet
            r2.<init>()
            java.lang.Class r3 = r12.getClass()
            java.lang.reflect.Method[] r3 = r3.getDeclaredMethods()
            int r4 = r3.length
            r5 = 0
            r6 = 0
        L_0x001a:
            if (r6 >= r4) goto L_0x0049
            r7 = r3[r6]
            java.lang.String r8 = r7.getName()
            r1.put(r8, r7)
            java.lang.Class[] r8 = r7.getParameterTypes()
            int r8 = r8.length
            if (r8 != 0) goto L_0x0046
            java.lang.String r8 = r7.getName()
            r0.put(r8, r7)
            java.lang.String r8 = r7.getName()
            java.lang.String r9 = "get"
            boolean r8 = r8.startsWith(r9)
            if (r8 == 0) goto L_0x0046
            java.lang.String r7 = r7.getName()
            r2.add(r7)
        L_0x0046:
            int r6 = r6 + 1
            goto L_0x001a
        L_0x0049:
            java.util.Iterator r2 = r2.iterator()
        L_0x004d:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x027b
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r4 = "get"
            java.lang.String r6 = ""
            java.lang.String r4 = r3.replaceFirst(r4, r6)
            java.lang.String r6 = "List"
            boolean r6 = r4.endsWith(r6)
            r7 = 1
            if (r6 == 0) goto L_0x00c7
            java.lang.String r6 = "OrBuilderList"
            boolean r6 = r4.endsWith(r6)
            if (r6 != 0) goto L_0x00c7
            java.lang.String r6 = "List"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L_0x00c7
            java.lang.String r6 = r4.substring(r5, r7)
            java.lang.String r6 = r6.toLowerCase()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            int r8 = r4.length()
            int r8 = r8 + -4
            java.lang.String r8 = r4.substring(r7, r8)
            java.lang.String r8 = java.lang.String.valueOf(r8)
            int r9 = r8.length()
            if (r9 == 0) goto L_0x009f
            java.lang.String r6 = r6.concat(r8)
            goto L_0x00a5
        L_0x009f:
            java.lang.String r8 = new java.lang.String
            r8.<init>(r6)
            r6 = r8
        L_0x00a5:
            java.lang.Object r8 = r0.get(r3)
            java.lang.reflect.Method r8 = (java.lang.reflect.Method) r8
            if (r8 == 0) goto L_0x00c7
            java.lang.Class r9 = r8.getReturnType()
            java.lang.Class<java.util.List> r10 = java.util.List.class
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x00c7
            java.lang.String r3 = zzq(r6)
            java.lang.Object[] r4 = new java.lang.Object[r5]
            java.lang.Object r4 = com.google.android.gms.internal.vision.zzfy.a((java.lang.reflect.Method) r8, (java.lang.Object) r12, (java.lang.Object[]) r4)
            a(r13, r14, r3, r4)
            goto L_0x004d
        L_0x00c7:
            java.lang.String r6 = "Map"
            boolean r6 = r4.endsWith(r6)
            if (r6 == 0) goto L_0x0137
            java.lang.String r6 = "Map"
            boolean r6 = r4.equals(r6)
            if (r6 != 0) goto L_0x0137
            java.lang.String r6 = r4.substring(r5, r7)
            java.lang.String r6 = r6.toLowerCase()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            int r8 = r4.length()
            int r8 = r8 + -3
            java.lang.String r8 = r4.substring(r7, r8)
            java.lang.String r8 = java.lang.String.valueOf(r8)
            int r9 = r8.length()
            if (r9 == 0) goto L_0x00fc
            java.lang.String r6 = r6.concat(r8)
            goto L_0x0102
        L_0x00fc:
            java.lang.String r8 = new java.lang.String
            r8.<init>(r6)
            r6 = r8
        L_0x0102:
            java.lang.Object r3 = r0.get(r3)
            java.lang.reflect.Method r3 = (java.lang.reflect.Method) r3
            if (r3 == 0) goto L_0x0137
            java.lang.Class r8 = r3.getReturnType()
            java.lang.Class<java.util.Map> r9 = java.util.Map.class
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x0137
            java.lang.Class<java.lang.Deprecated> r8 = java.lang.Deprecated.class
            boolean r8 = r3.isAnnotationPresent(r8)
            if (r8 != 0) goto L_0x0137
            int r8 = r3.getModifiers()
            boolean r8 = java.lang.reflect.Modifier.isPublic(r8)
            if (r8 == 0) goto L_0x0137
            java.lang.String r4 = zzq(r6)
            java.lang.Object[] r6 = new java.lang.Object[r5]
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzfy.a((java.lang.reflect.Method) r3, (java.lang.Object) r12, (java.lang.Object[]) r6)
            a(r13, r14, r4, r3)
            goto L_0x004d
        L_0x0137:
            java.lang.String r3 = "set"
            java.lang.String r6 = java.lang.String.valueOf(r4)
            int r8 = r6.length()
            if (r8 == 0) goto L_0x0148
            java.lang.String r3 = r3.concat(r6)
            goto L_0x014e
        L_0x0148:
            java.lang.String r6 = new java.lang.String
            r6.<init>(r3)
            r3 = r6
        L_0x014e:
            java.lang.Object r3 = r1.get(r3)
            java.lang.reflect.Method r3 = (java.lang.reflect.Method) r3
            if (r3 == 0) goto L_0x004d
            java.lang.String r3 = "Bytes"
            boolean r3 = r4.endsWith(r3)
            if (r3 == 0) goto L_0x0185
            java.lang.String r3 = "get"
            int r6 = r4.length()
            int r6 = r6 + -5
            java.lang.String r6 = r4.substring(r5, r6)
            java.lang.String r6 = java.lang.String.valueOf(r6)
            int r8 = r6.length()
            if (r8 == 0) goto L_0x0179
            java.lang.String r3 = r3.concat(r6)
            goto L_0x017f
        L_0x0179:
            java.lang.String r6 = new java.lang.String
            r6.<init>(r3)
            r3 = r6
        L_0x017f:
            boolean r3 = r0.containsKey(r3)
            if (r3 != 0) goto L_0x004d
        L_0x0185:
            java.lang.String r3 = r4.substring(r5, r7)
            java.lang.String r3 = r3.toLowerCase()
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r6 = r4.substring(r7)
            java.lang.String r6 = java.lang.String.valueOf(r6)
            int r8 = r6.length()
            if (r8 == 0) goto L_0x01a4
            java.lang.String r3 = r3.concat(r6)
            goto L_0x01aa
        L_0x01a4:
            java.lang.String r6 = new java.lang.String
            r6.<init>(r3)
            r3 = r6
        L_0x01aa:
            java.lang.String r6 = "get"
            java.lang.String r8 = java.lang.String.valueOf(r4)
            int r9 = r8.length()
            if (r9 == 0) goto L_0x01bb
            java.lang.String r6 = r6.concat(r8)
            goto L_0x01c1
        L_0x01bb:
            java.lang.String r8 = new java.lang.String
            r8.<init>(r6)
            r6 = r8
        L_0x01c1:
            java.lang.Object r6 = r0.get(r6)
            java.lang.reflect.Method r6 = (java.lang.reflect.Method) r6
            java.lang.String r8 = "has"
            java.lang.String r4 = java.lang.String.valueOf(r4)
            int r9 = r4.length()
            if (r9 == 0) goto L_0x01d8
            java.lang.String r4 = r8.concat(r4)
            goto L_0x01dd
        L_0x01d8:
            java.lang.String r4 = new java.lang.String
            r4.<init>(r8)
        L_0x01dd:
            java.lang.Object r4 = r0.get(r4)
            java.lang.reflect.Method r4 = (java.lang.reflect.Method) r4
            if (r6 == 0) goto L_0x004d
            java.lang.Object[] r8 = new java.lang.Object[r5]
            java.lang.Object r6 = com.google.android.gms.internal.vision.zzfy.a((java.lang.reflect.Method) r6, (java.lang.Object) r12, (java.lang.Object[]) r8)
            if (r4 != 0) goto L_0x0264
            boolean r4 = r6 instanceof java.lang.Boolean
            if (r4 == 0) goto L_0x01ff
            r4 = r6
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 != 0) goto L_0x01fd
        L_0x01fa:
            r4 = 1
            goto L_0x025e
        L_0x01fd:
            r4 = 0
            goto L_0x025e
        L_0x01ff:
            boolean r4 = r6 instanceof java.lang.Integer
            if (r4 == 0) goto L_0x020d
            r4 = r6
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            if (r4 != 0) goto L_0x01fd
            goto L_0x01fa
        L_0x020d:
            boolean r4 = r6 instanceof java.lang.Float
            if (r4 == 0) goto L_0x021e
            r4 = r6
            java.lang.Float r4 = (java.lang.Float) r4
            float r4 = r4.floatValue()
            r8 = 0
            int r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r4 != 0) goto L_0x01fd
            goto L_0x01fa
        L_0x021e:
            boolean r4 = r6 instanceof java.lang.Double
            if (r4 == 0) goto L_0x0230
            r4 = r6
            java.lang.Double r4 = (java.lang.Double) r4
            double r8 = r4.doubleValue()
            r10 = 0
            int r4 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r4 != 0) goto L_0x01fd
            goto L_0x01fa
        L_0x0230:
            boolean r4 = r6 instanceof java.lang.String
            if (r4 == 0) goto L_0x023b
            java.lang.String r4 = ""
        L_0x0236:
            boolean r4 = r6.equals(r4)
            goto L_0x025e
        L_0x023b:
            boolean r4 = r6 instanceof com.google.android.gms.internal.vision.zzeo
            if (r4 == 0) goto L_0x0242
            com.google.android.gms.internal.vision.zzeo r4 = com.google.android.gms.internal.vision.zzeo.zzrx
            goto L_0x0236
        L_0x0242:
            boolean r4 = r6 instanceof com.google.android.gms.internal.vision.zzhf
            if (r4 == 0) goto L_0x0250
            r4 = r6
            com.google.android.gms.internal.vision.zzhf r4 = (com.google.android.gms.internal.vision.zzhf) r4
            com.google.android.gms.internal.vision.zzhf r4 = r4.zzfb()
            if (r6 != r4) goto L_0x01fd
            goto L_0x01fa
        L_0x0250:
            boolean r4 = r6 instanceof java.lang.Enum
            if (r4 == 0) goto L_0x01fd
            r4 = r6
            java.lang.Enum r4 = (java.lang.Enum) r4
            int r4 = r4.ordinal()
            if (r4 != 0) goto L_0x01fd
            goto L_0x01fa
        L_0x025e:
            if (r4 != 0) goto L_0x0262
            r4 = 1
            goto L_0x0270
        L_0x0262:
            r4 = 0
            goto L_0x0270
        L_0x0264:
            java.lang.Object[] r7 = new java.lang.Object[r5]
            java.lang.Object r4 = com.google.android.gms.internal.vision.zzfy.a((java.lang.reflect.Method) r4, (java.lang.Object) r12, (java.lang.Object[]) r7)
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
        L_0x0270:
            if (r4 == 0) goto L_0x004d
            java.lang.String r3 = zzq(r3)
            a(r13, r14, r3, r6)
            goto L_0x004d
        L_0x027b:
            boolean r0 = r12 instanceof com.google.android.gms.internal.vision.zzfy.zzd
            if (r0 == 0) goto L_0x02bc
            r0 = r12
            com.google.android.gms.internal.vision.zzfy$zzd r0 = (com.google.android.gms.internal.vision.zzfy.zzd) r0
            com.google.android.gms.internal.vision.zzfp<com.google.android.gms.internal.vision.zzfy$zze> r0 = r0.zzwp
            java.util.Iterator r0 = r0.iterator()
        L_0x0288:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x02bc
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            com.google.android.gms.internal.vision.zzfy$zze r2 = (com.google.android.gms.internal.vision.zzfy.zze) r2
            int r2 = r2.b
            r3 = 13
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "["
            r4.append(r3)
            r4.append(r2)
            java.lang.String r2 = "]"
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            java.lang.Object r1 = r1.getValue()
            a(r13, r14, r2, r1)
            goto L_0x0288
        L_0x02bc:
            com.google.android.gms.internal.vision.zzfy r12 = (com.google.android.gms.internal.vision.zzfy) r12
            com.google.android.gms.internal.vision.zzip r0 = r12.zzwj
            if (r0 == 0) goto L_0x02c7
            com.google.android.gms.internal.vision.zzip r12 = r12.zzwj
            r12.a((java.lang.StringBuilder) r13, (int) r14)
        L_0x02c7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhi.zza(com.google.android.gms.internal.vision.zzhf, java.lang.StringBuilder, int):void");
    }

    private static final String zzq(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }
}

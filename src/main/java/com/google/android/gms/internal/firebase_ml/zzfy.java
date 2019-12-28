package com.google.android.gms.internal.firebase_ml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public final class zzfy {
    static final Map<Character, zzfz> a = new HashMap();

    static {
        zzfz.values();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0206 A[EDGE_INSN: B:86:0x0206->B:84:0x0206 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String zza(java.lang.String r12, java.lang.String r13, java.lang.Object r14, boolean r15) {
        /*
            java.lang.String r15 = "/"
            boolean r15 = r13.startsWith(r15)
            if (r15 == 0) goto L_0x002f
            com.google.android.gms.internal.firebase_ml.zzfg r15 = new com.google.android.gms.internal.firebase_ml.zzfg
            r15.<init>((java.lang.String) r12)
            r12 = 0
            r15.zzs(r12)
            java.lang.String r12 = r15.zzeg()
            java.lang.String r12 = java.lang.String.valueOf(r12)
            java.lang.String r13 = java.lang.String.valueOf(r13)
            int r15 = r13.length()
            if (r15 == 0) goto L_0x0028
            java.lang.String r12 = r12.concat(r13)
            goto L_0x0059
        L_0x0028:
            java.lang.String r13 = new java.lang.String
            r13.<init>(r12)
        L_0x002d:
            r12 = r13
            goto L_0x0059
        L_0x002f:
            java.lang.String r15 = "http://"
            boolean r15 = r13.startsWith(r15)
            if (r15 != 0) goto L_0x002d
            java.lang.String r15 = "https://"
            boolean r15 = r13.startsWith(r15)
            if (r15 == 0) goto L_0x0040
            goto L_0x002d
        L_0x0040:
            java.lang.String r12 = java.lang.String.valueOf(r12)
            java.lang.String r13 = java.lang.String.valueOf(r13)
            int r15 = r13.length()
            if (r15 == 0) goto L_0x0053
            java.lang.String r13 = r12.concat(r13)
            goto L_0x002d
        L_0x0053:
            java.lang.String r13 = new java.lang.String
            r13.<init>(r12)
            goto L_0x002d
        L_0x0059:
            java.util.Map r13 = zzb(r14)
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            int r15 = r12.length()
            r0 = 0
            r1 = 0
        L_0x0068:
            if (r1 >= r15) goto L_0x0206
            r2 = 123(0x7b, float:1.72E-43)
            int r2 = r12.indexOf(r2, r1)
            r3 = -1
            if (r2 != r3) goto L_0x007c
            java.lang.String r12 = r12.substring(r1)
            r14.append(r12)
            goto L_0x0206
        L_0x007c:
            java.lang.String r1 = r12.substring(r1, r2)
            r14.append(r1)
            r1 = 125(0x7d, float:1.75E-43)
            int r3 = r2 + 2
            int r1 = r12.indexOf(r1, r3)
            int r3 = r1 + 1
            int r2 = r2 + 1
            java.lang.String r1 = r12.substring(r2, r1)
            java.util.Map<java.lang.Character, com.google.android.gms.internal.firebase_ml.zzfz> r2 = a
            char r4 = r1.charAt(r0)
            java.lang.Character r4 = java.lang.Character.valueOf(r4)
            java.lang.Object r2 = r2.get(r4)
            com.google.android.gms.internal.firebase_ml.zzfz r2 = (com.google.android.gms.internal.firebase_ml.zzfz) r2
            if (r2 != 0) goto L_0x00a7
            com.google.android.gms.internal.firebase_ml.zzfz r2 = com.google.android.gms.internal.firebase_ml.zzfz.SIMPLE
        L_0x00a7:
            r4 = 44
            com.google.android.gms.internal.firebase_ml.zzki r4 = com.google.android.gms.internal.firebase_ml.zzki.zza(r4)
            com.google.android.gms.internal.firebase_ml.zzla r4 = com.google.android.gms.internal.firebase_ml.zzla.zza((com.google.android.gms.internal.firebase_ml.zzki) r4)
            java.util.List r1 = r4.zza((java.lang.CharSequence) r1)
            java.util.ListIterator r1 = r1.listIterator()
            r4 = 1
            r5 = 1
        L_0x00bb:
            boolean r6 = r1.hasNext()
            if (r6 == 0) goto L_0x0203
            java.lang.Object r6 = r1.next()
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r7 = "*"
            boolean r7 = r6.endsWith(r7)
            int r8 = r1.nextIndex()
            if (r8 != r4) goto L_0x00d8
            int r8 = r2.d()
            goto L_0x00d9
        L_0x00d8:
            r8 = 0
        L_0x00d9:
            int r9 = r6.length()
            if (r7 == 0) goto L_0x00e1
            int r9 = r9 + -1
        L_0x00e1:
            java.lang.String r6 = r6.substring(r8, r9)
            java.lang.Object r8 = r13.remove(r6)
            if (r8 == 0) goto L_0x00bb
            if (r5 != 0) goto L_0x00f5
            java.lang.String r9 = r2.b()
            r14.append(r9)
            goto L_0x00fd
        L_0x00f5:
            java.lang.String r5 = r2.a()
            r14.append(r5)
            r5 = 0
        L_0x00fd:
            boolean r9 = r8 instanceof java.util.Iterator
            if (r9 == 0) goto L_0x0109
            java.util.Iterator r8 = (java.util.Iterator) r8
        L_0x0103:
            java.lang.String r6 = zza((java.lang.String) r6, (java.util.Iterator<?>) r8, (boolean) r7, (com.google.android.gms.internal.firebase_ml.zzfz) r2)
            goto L_0x01fe
        L_0x0109:
            boolean r9 = r8 instanceof java.lang.Iterable
            if (r9 != 0) goto L_0x01f4
            java.lang.Class r9 = r8.getClass()
            boolean r9 = r9.isArray()
            if (r9 == 0) goto L_0x0119
            goto L_0x01f4
        L_0x0119:
            java.lang.Class r9 = r8.getClass()
            boolean r9 = r9.isEnum()
            r10 = 2
            if (r9 == 0) goto L_0x014e
            r7 = r8
            java.lang.Enum r7 = (java.lang.Enum) r7
            com.google.android.gms.internal.firebase_ml.zzhr r7 = com.google.android.gms.internal.firebase_ml.zzhr.zza((java.lang.Enum<?>) r7)
            java.lang.String r7 = r7.getName()
            if (r7 == 0) goto L_0x014b
            boolean r7 = r2.c()
            if (r7 == 0) goto L_0x0143
            java.lang.String r7 = "%s=%s"
            java.lang.Object[] r9 = new java.lang.Object[r10]
            r9[r0] = r6
            r9[r4] = r8
            java.lang.String r8 = java.lang.String.format(r7, r9)
        L_0x0143:
            java.lang.String r6 = r8.toString()
            java.lang.String r8 = com.google.android.gms.internal.firebase_ml.zzik.zzap(r6)
        L_0x014b:
            r6 = r8
            goto L_0x01fe
        L_0x014e:
            boolean r9 = com.google.android.gms.internal.firebase_ml.zzhl.zzg(r8)
            if (r9 != 0) goto L_0x01ca
            java.util.Map r8 = zzb(r8)
            boolean r9 = r8.isEmpty()
            if (r9 == 0) goto L_0x0162
            java.lang.String r6 = ""
            goto L_0x01fe
        L_0x0162:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            if (r7 == 0) goto L_0x0170
            java.lang.String r6 = r2.b()
            java.lang.String r7 = "="
            goto L_0x0188
        L_0x0170:
            java.lang.String r7 = ","
            java.lang.String r10 = ","
            boolean r11 = r2.c()
            if (r11 == 0) goto L_0x0186
            java.lang.String r6 = com.google.android.gms.internal.firebase_ml.zzik.zzap(r6)
            r9.append(r6)
            java.lang.String r6 = "="
            r9.append(r6)
        L_0x0186:
            r6 = r7
            r7 = r10
        L_0x0188:
            java.util.Set r8 = r8.entrySet()
            java.util.Iterator r8 = r8.iterator()
        L_0x0190:
            boolean r10 = r8.hasNext()
            if (r10 == 0) goto L_0x01c5
            java.lang.Object r10 = r8.next()
            java.util.Map$Entry r10 = (java.util.Map.Entry) r10
            java.lang.Object r11 = r10.getKey()
            java.lang.String r11 = (java.lang.String) r11
            java.lang.String r11 = r2.a(r11)
            java.lang.Object r10 = r10.getValue()
            java.lang.String r10 = r10.toString()
            java.lang.String r10 = r2.a(r10)
            r9.append(r11)
            r9.append(r7)
            r9.append(r10)
            boolean r10 = r8.hasNext()
            if (r10 == 0) goto L_0x0190
            r9.append(r6)
            goto L_0x0190
        L_0x01c5:
            java.lang.String r6 = r9.toString()
            goto L_0x01fe
        L_0x01ca:
            boolean r7 = r2.c()
            if (r7 == 0) goto L_0x01dc
            java.lang.String r7 = "%s=%s"
            java.lang.Object[] r9 = new java.lang.Object[r10]
            r9[r0] = r6
            r9[r4] = r8
            java.lang.String r8 = java.lang.String.format(r7, r9)
        L_0x01dc:
            boolean r6 = r2.e()
            if (r6 == 0) goto L_0x01eb
            java.lang.String r6 = r8.toString()
            java.lang.String r6 = com.google.android.gms.internal.firebase_ml.zzik.zzaq(r6)
            goto L_0x01fe
        L_0x01eb:
            java.lang.String r6 = r8.toString()
            java.lang.String r6 = com.google.android.gms.internal.firebase_ml.zzik.zzap(r6)
            goto L_0x01fe
        L_0x01f4:
            java.lang.Iterable r8 = com.google.android.gms.internal.firebase_ml.zzig.zzi(r8)
            java.util.Iterator r8 = r8.iterator()
            goto L_0x0103
        L_0x01fe:
            r14.append(r6)
            goto L_0x00bb
        L_0x0203:
            r1 = r3
            goto L_0x0068
        L_0x0206:
            java.util.Set r12 = r13.entrySet()
            com.google.android.gms.internal.firebase_ml.zzfg.a(r12, r14)
            java.lang.String r12 = r14.toString()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzfy.zza(java.lang.String, java.lang.String, java.lang.Object, boolean):java.lang.String");
    }

    private static String zza(String str, Iterator<?> it, boolean z, zzfz zzfz) {
        String str2;
        if (!it.hasNext()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            str2 = zzfz.b();
        } else {
            str2 = ",";
            if (zzfz.c()) {
                sb.append(zzik.zzap(str));
                sb.append("=");
            }
        }
        while (it.hasNext()) {
            if (z && zzfz.c()) {
                sb.append(zzik.zzap(str));
                sb.append("=");
            }
            sb.append(zzfz.a(it.next().toString()));
            if (it.hasNext()) {
                sb.append(str2);
            }
        }
        return sb.toString();
    }

    private static Map<String, Object> zzb(Object obj) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry next : zzhl.zzf(obj).entrySet()) {
            Object value = next.getValue();
            if (value != null && !zzhl.isNull(value)) {
                linkedHashMap.put((String) next.getKey(), value);
            }
        }
        return linkedHashMap;
    }
}

package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

final class zzuq<T> implements zzvc<T> {
    private static final int[] zzboq = new int[0];
    private static final Unsafe zzbor = zzwa.c();
    private final int[] zzbos;
    private final Object[] zzbot;
    private final int zzbou;
    private final int zzbov;
    private final zzum zzbow;
    private final boolean zzbox;
    private final boolean zzboy;
    private final boolean zzboz;
    private final boolean zzbpa;
    private final int[] zzbpb;
    private final int zzbpc;
    private final int zzbpd;
    private final zzuu zzbpe;
    private final zztw zzbpf;
    private final zzvu<?, ?> zzbpg;
    private final zzsr<?> zzbph;
    private final zzuh zzbpi;

    private zzuq(int[] iArr, Object[] objArr, int i, int i2, zzum zzum, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzuu zzuu, zztw zztw, zzvu<?, ?> zzvu, zzsr<?> zzsr, zzuh zzuh) {
        this.zzbos = iArr;
        this.zzbot = objArr;
        this.zzbou = i;
        this.zzbov = i2;
        this.zzboy = zzum instanceof zztc;
        this.zzboz = z;
        this.zzbox = zzsr != null && zzsr.a(zzum);
        this.zzbpa = false;
        this.zzbpb = iArr2;
        this.zzbpc = i3;
        this.zzbpd = i4;
        this.zzbpe = zzuu;
        this.zzbpf = zztw;
        this.zzbpg = zzvu;
        this.zzbph = zzsr;
        this.zzbow = zzum;
        this.zzbpi = zzuh;
    }

    /* JADX WARNING: Removed duplicated region for block: B:168:0x037d  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x03c9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.android.gms.internal.firebase_ml.zzuq<T> a(java.lang.Class<T> r35, com.google.android.gms.internal.firebase_ml.zzuk r36, com.google.android.gms.internal.firebase_ml.zzuu r37, com.google.android.gms.internal.firebase_ml.zztw r38, com.google.android.gms.internal.firebase_ml.zzvu<?, ?> r39, com.google.android.gms.internal.firebase_ml.zzsr<?> r40, com.google.android.gms.internal.firebase_ml.zzuh r41) {
        /*
            r0 = r36
            boolean r1 = r0 instanceof com.google.android.gms.internal.firebase_ml.zzvb
            if (r1 == 0) goto L_0x0444
            com.google.android.gms.internal.firebase_ml.zzvb r0 = (com.google.android.gms.internal.firebase_ml.zzvb) r0
            int r1 = r0.zzqv()
            int r2 = com.google.android.gms.internal.firebase_ml.zztc.zzf.zzbms
            r3 = 0
            r4 = 1
            if (r1 != r2) goto L_0x0014
            r11 = 1
            goto L_0x0015
        L_0x0014:
            r11 = 0
        L_0x0015:
            java.lang.String r1 = r0.a()
            int r2 = r1.length()
            char r5 = r1.charAt(r3)
            r7 = 55296(0xd800, float:7.7486E-41)
            if (r5 < r7) goto L_0x003f
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            r8 = r5
            r5 = 1
            r9 = 13
        L_0x002c:
            int r10 = r5 + 1
            char r5 = r1.charAt(r5)
            if (r5 < r7) goto L_0x003c
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r5 = r5 << r9
            r8 = r8 | r5
            int r9 = r9 + 13
            r5 = r10
            goto L_0x002c
        L_0x003c:
            int r5 = r5 << r9
            r5 = r5 | r8
            goto L_0x0040
        L_0x003f:
            r10 = 1
        L_0x0040:
            int r8 = r10 + 1
            char r9 = r1.charAt(r10)
            if (r9 < r7) goto L_0x005f
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x004c:
            int r12 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x005c
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r10
            r9 = r9 | r8
            int r10 = r10 + 13
            r8 = r12
            goto L_0x004c
        L_0x005c:
            int r8 = r8 << r10
            r9 = r9 | r8
            goto L_0x0060
        L_0x005f:
            r12 = r8
        L_0x0060:
            if (r9 != 0) goto L_0x006e
            int[] r8 = zzboq
            r15 = r8
            r8 = 0
            r9 = 0
            r10 = 0
            r13 = 0
            r14 = 0
            r16 = 0
            goto L_0x01a0
        L_0x006e:
            int r8 = r12 + 1
            char r9 = r1.charAt(r12)
            if (r9 < r7) goto L_0x008e
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x007a:
            int r12 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x008a
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r10
            r9 = r9 | r8
            int r10 = r10 + 13
            r8 = r12
            goto L_0x007a
        L_0x008a:
            int r8 = r8 << r10
            r8 = r8 | r9
            r9 = r8
            goto L_0x008f
        L_0x008e:
            r12 = r8
        L_0x008f:
            int r8 = r12 + 1
            char r10 = r1.charAt(r12)
            if (r10 < r7) goto L_0x00ae
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x009b:
            int r13 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x00ab
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r12
            r10 = r10 | r8
            int r12 = r12 + 13
            r8 = r13
            goto L_0x009b
        L_0x00ab:
            int r8 = r8 << r12
            r10 = r10 | r8
            goto L_0x00af
        L_0x00ae:
            r13 = r8
        L_0x00af:
            int r8 = r13 + 1
            char r12 = r1.charAt(r13)
            if (r12 < r7) goto L_0x00cf
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00bb:
            int r14 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x00cb
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r13
            r12 = r12 | r8
            int r13 = r13 + 13
            r8 = r14
            goto L_0x00bb
        L_0x00cb:
            int r8 = r8 << r13
            r8 = r8 | r12
            r12 = r8
            goto L_0x00d0
        L_0x00cf:
            r14 = r8
        L_0x00d0:
            int r8 = r14 + 1
            char r13 = r1.charAt(r14)
            if (r13 < r7) goto L_0x00f0
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x00dc:
            int r15 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x00ec
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r14
            r13 = r13 | r8
            int r14 = r14 + 13
            r8 = r15
            goto L_0x00dc
        L_0x00ec:
            int r8 = r8 << r14
            r8 = r8 | r13
            r13 = r8
            goto L_0x00f1
        L_0x00f0:
            r15 = r8
        L_0x00f1:
            int r8 = r15 + 1
            char r14 = r1.charAt(r15)
            if (r14 < r7) goto L_0x0113
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x00fd:
            int r16 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x010e
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r15
            r14 = r14 | r8
            int r15 = r15 + 13
            r8 = r16
            goto L_0x00fd
        L_0x010e:
            int r8 = r8 << r15
            r8 = r8 | r14
            r14 = r8
            r8 = r16
        L_0x0113:
            int r15 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r7) goto L_0x0136
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x011f:
            int r17 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r7) goto L_0x0131
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r16
            r8 = r8 | r15
            int r16 = r16 + 13
            r15 = r17
            goto L_0x011f
        L_0x0131:
            int r15 = r15 << r16
            r8 = r8 | r15
            r15 = r17
        L_0x0136:
            int r16 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r7) goto L_0x0162
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            r17 = 13
            r34 = r16
            r16 = r15
            r15 = r34
        L_0x0148:
            int r18 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r7) goto L_0x015b
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r17
            r16 = r16 | r15
            int r17 = r17 + 13
            r15 = r18
            goto L_0x0148
        L_0x015b:
            int r15 = r15 << r17
            r15 = r16 | r15
            r3 = r18
            goto L_0x0164
        L_0x0162:
            r3 = r16
        L_0x0164:
            int r16 = r3 + 1
            char r3 = r1.charAt(r3)
            if (r3 < r7) goto L_0x018f
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r17 = 13
            r34 = r16
            r16 = r3
            r3 = r34
        L_0x0176:
            int r18 = r3 + 1
            char r3 = r1.charAt(r3)
            if (r3 < r7) goto L_0x0189
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            int r3 = r3 << r17
            r16 = r16 | r3
            int r17 = r17 + 13
            r3 = r18
            goto L_0x0176
        L_0x0189:
            int r3 = r3 << r17
            r3 = r16 | r3
            r16 = r18
        L_0x018f:
            int r17 = r3 + r8
            int r15 = r17 + r15
            int[] r15 = new int[r15]
            int r17 = r9 << 1
            int r10 = r17 + r10
            r34 = r16
            r16 = r9
            r9 = r12
            r12 = r34
        L_0x01a0:
            sun.misc.Unsafe r6 = zzbor
            java.lang.Object[] r17 = r0.b()
            com.google.android.gms.internal.firebase_ml.zzum r18 = r0.zzqx()
            java.lang.Class r7 = r18.getClass()
            r18 = r10
            int r10 = r14 * 3
            int[] r10 = new int[r10]
            int r14 = r14 << r4
            java.lang.Object[] r14 = new java.lang.Object[r14]
            int r20 = r3 + r8
            r22 = r3
            r23 = r20
            r8 = 0
            r21 = 0
        L_0x01c0:
            if (r12 >= r2) goto L_0x041a
            int r24 = r12 + 1
            char r12 = r1.charAt(r12)
            r4 = 55296(0xd800, float:7.7486E-41)
            if (r12 < r4) goto L_0x01f4
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r26 = 13
            r34 = r24
            r24 = r12
            r12 = r34
        L_0x01d7:
            int r27 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r4) goto L_0x01ed
            r4 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r26
            r24 = r24 | r4
            int r26 = r26 + 13
            r12 = r27
            r4 = 55296(0xd800, float:7.7486E-41)
            goto L_0x01d7
        L_0x01ed:
            int r4 = r12 << r26
            r12 = r24 | r4
            r4 = r27
            goto L_0x01f6
        L_0x01f4:
            r4 = r24
        L_0x01f6:
            int r24 = r4 + 1
            char r4 = r1.charAt(r4)
            r26 = r2
            r2 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r2) goto L_0x022a
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r27 = 13
            r34 = r24
            r24 = r4
            r4 = r34
        L_0x020d:
            int r28 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r2) goto L_0x0223
            r2 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r27
            r24 = r24 | r2
            int r27 = r27 + 13
            r4 = r28
            r2 = 55296(0xd800, float:7.7486E-41)
            goto L_0x020d
        L_0x0223:
            int r2 = r4 << r27
            r4 = r24 | r2
            r2 = r28
            goto L_0x022c
        L_0x022a:
            r2 = r24
        L_0x022c:
            r24 = r3
            r3 = r4 & 255(0xff, float:3.57E-43)
            r27 = r11
            r11 = r4 & 1024(0x400, float:1.435E-42)
            if (r11 == 0) goto L_0x023b
            int r11 = r8 + 1
            r15[r8] = r21
            r8 = r11
        L_0x023b:
            r11 = 51
            r30 = r8
            if (r3 < r11) goto L_0x02e1
            int r11 = r2 + 1
            char r2 = r1.charAt(r2)
            r8 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r8) goto L_0x026a
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r32 = 13
        L_0x0250:
            int r33 = r11 + 1
            char r11 = r1.charAt(r11)
            if (r11 < r8) goto L_0x0265
            r8 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r32
            r2 = r2 | r8
            int r32 = r32 + 13
            r11 = r33
            r8 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0250
        L_0x0265:
            int r8 = r11 << r32
            r2 = r2 | r8
            r11 = r33
        L_0x026a:
            int r8 = r3 + -51
            r32 = r11
            r11 = 9
            if (r8 == r11) goto L_0x028e
            r11 = 17
            if (r8 != r11) goto L_0x0277
            goto L_0x028e
        L_0x0277:
            r11 = 12
            if (r8 != r11) goto L_0x028c
            r8 = r5 & 1
            r11 = 1
            if (r8 != r11) goto L_0x028c
            int r8 = r21 / 3
            int r8 = r8 << r11
            int r8 = r8 + r11
            int r11 = r18 + 1
            r18 = r17[r18]
            r14[r8] = r18
            r18 = r11
        L_0x028c:
            r11 = 1
            goto L_0x029b
        L_0x028e:
            int r8 = r21 / 3
            r11 = 1
            int r8 = r8 << r11
            int r8 = r8 + r11
            int r25 = r18 + 1
            r18 = r17[r18]
            r14[r8] = r18
            r18 = r25
        L_0x029b:
            int r2 = r2 << r11
            r8 = r17[r2]
            boolean r11 = r8 instanceof java.lang.reflect.Field
            if (r11 == 0) goto L_0x02a5
            java.lang.reflect.Field r8 = (java.lang.reflect.Field) r8
            goto L_0x02ad
        L_0x02a5:
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = zza((java.lang.Class<?>) r7, (java.lang.String) r8)
            r17[r2] = r8
        L_0x02ad:
            r11 = r9
            long r8 = r6.objectFieldOffset(r8)
            int r9 = (int) r8
            int r2 = r2 + 1
            r8 = r17[r2]
            r28 = r9
            boolean r9 = r8 instanceof java.lang.reflect.Field
            if (r9 == 0) goto L_0x02c0
            java.lang.reflect.Field r8 = (java.lang.reflect.Field) r8
            goto L_0x02c8
        L_0x02c0:
            java.lang.String r8 = (java.lang.String) r8
            java.lang.reflect.Field r8 = zza((java.lang.Class<?>) r7, (java.lang.String) r8)
            r17[r2] = r8
        L_0x02c8:
            long r8 = r6.objectFieldOffset(r8)
            int r2 = (int) r8
            r31 = r1
            r8 = r2
            r1 = r7
            r25 = r18
            r9 = r28
            r2 = 0
            r19 = 1
            r28 = r11
            r18 = r13
            r13 = r12
            r12 = r32
            goto L_0x03e2
        L_0x02e1:
            r11 = r9
            int r8 = r18 + 1
            r9 = r17[r18]
            java.lang.String r9 = (java.lang.String) r9
            java.lang.reflect.Field r9 = zza((java.lang.Class<?>) r7, (java.lang.String) r9)
            r18 = r13
            r13 = 9
            if (r3 == r13) goto L_0x0361
            r13 = 17
            if (r3 != r13) goto L_0x02f8
            goto L_0x0361
        L_0x02f8:
            r13 = 27
            if (r3 == r13) goto L_0x0350
            r13 = 49
            if (r3 != r13) goto L_0x0301
            goto L_0x0350
        L_0x0301:
            r13 = 12
            if (r3 == r13) goto L_0x033e
            r13 = 30
            if (r3 == r13) goto L_0x033e
            r13 = 44
            if (r3 != r13) goto L_0x030e
            goto L_0x033e
        L_0x030e:
            r13 = 50
            if (r3 != r13) goto L_0x033a
            int r13 = r22 + 1
            r15[r22] = r21
            int r22 = r21 / 3
            r25 = 1
            int r22 = r22 << 1
            int r28 = r8 + 1
            r8 = r17[r8]
            r14[r22] = r8
            r8 = r4 & 2048(0x800, float:2.87E-42)
            if (r8 == 0) goto L_0x0333
            int r22 = r22 + 1
            int r8 = r28 + 1
            r28 = r17[r28]
            r14[r22] = r28
            r28 = r11
            r22 = r13
            goto L_0x036e
        L_0x0333:
            r22 = r13
            r8 = r28
            r28 = r11
            goto L_0x036e
        L_0x033a:
            r28 = r11
            r11 = 1
            goto L_0x036e
        L_0x033e:
            r13 = r5 & 1
            r28 = r11
            r11 = 1
            if (r13 != r11) goto L_0x036e
            int r13 = r21 / 3
            int r13 = r13 << r11
            int r13 = r13 + r11
            int r25 = r8 + 1
            r8 = r17[r8]
            r14[r13] = r8
            goto L_0x035d
        L_0x0350:
            r28 = r11
            r11 = 1
            int r13 = r21 / 3
            int r13 = r13 << r11
            int r13 = r13 + r11
            int r25 = r8 + 1
            r8 = r17[r8]
            r14[r13] = r8
        L_0x035d:
            r13 = r12
            r8 = r25
            goto L_0x036f
        L_0x0361:
            r28 = r11
            r11 = 1
            int r13 = r21 / 3
            int r13 = r13 << r11
            int r13 = r13 + r11
            java.lang.Class r25 = r9.getType()
            r14[r13] = r25
        L_0x036e:
            r13 = r12
        L_0x036f:
            long r11 = r6.objectFieldOffset(r9)
            int r9 = (int) r11
            r11 = r5 & 1
            r12 = 1
            if (r11 != r12) goto L_0x03c9
            r11 = 17
            if (r3 > r11) goto L_0x03c9
            int r11 = r2 + 1
            char r2 = r1.charAt(r2)
            r12 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r12) goto L_0x03a3
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r19 = 13
        L_0x038c:
            int r29 = r11 + 1
            char r11 = r1.charAt(r11)
            if (r11 < r12) goto L_0x039e
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r19
            r2 = r2 | r11
            int r19 = r19 + 13
            r11 = r29
            goto L_0x038c
        L_0x039e:
            int r11 = r11 << r19
            r2 = r2 | r11
            r11 = r29
        L_0x03a3:
            r19 = 1
            int r25 = r16 << 1
            int r29 = r2 / 32
            int r25 = r25 + r29
            r12 = r17[r25]
            r31 = r1
            boolean r1 = r12 instanceof java.lang.reflect.Field
            if (r1 == 0) goto L_0x03b6
            java.lang.reflect.Field r12 = (java.lang.reflect.Field) r12
            goto L_0x03be
        L_0x03b6:
            java.lang.String r12 = (java.lang.String) r12
            java.lang.reflect.Field r12 = zza((java.lang.Class<?>) r7, (java.lang.String) r12)
            r17[r25] = r12
        L_0x03be:
            r1 = r7
            r25 = r8
            long r7 = r6.objectFieldOffset(r12)
            int r8 = (int) r7
            int r2 = r2 % 32
            goto L_0x03d3
        L_0x03c9:
            r31 = r1
            r1 = r7
            r25 = r8
            r19 = 1
            r11 = r2
            r2 = 0
            r8 = 0
        L_0x03d3:
            r7 = 18
            if (r3 < r7) goto L_0x03e1
            r7 = 49
            if (r3 > r7) goto L_0x03e1
            int r7 = r23 + 1
            r15[r23] = r9
            r23 = r7
        L_0x03e1:
            r12 = r11
        L_0x03e2:
            int r7 = r21 + 1
            r10[r21] = r13
            int r11 = r7 + 1
            r13 = r4 & 512(0x200, float:7.175E-43)
            if (r13 == 0) goto L_0x03ef
            r13 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x03f0
        L_0x03ef:
            r13 = 0
        L_0x03f0:
            r4 = r4 & 256(0x100, float:3.59E-43)
            if (r4 == 0) goto L_0x03f7
            r4 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x03f8
        L_0x03f7:
            r4 = 0
        L_0x03f8:
            r4 = r4 | r13
            int r3 = r3 << 20
            r3 = r3 | r4
            r3 = r3 | r9
            r10[r7] = r3
            int r21 = r11 + 1
            int r2 = r2 << 20
            r2 = r2 | r8
            r10[r11] = r2
            r7 = r1
            r13 = r18
            r3 = r24
            r18 = r25
            r2 = r26
            r11 = r27
            r9 = r28
            r8 = r30
            r1 = r31
            r4 = 1
            goto L_0x01c0
        L_0x041a:
            r24 = r3
            r28 = r9
            r27 = r11
            r18 = r13
            com.google.android.gms.internal.firebase_ml.zzuq r1 = new com.google.android.gms.internal.firebase_ml.zzuq
            com.google.android.gms.internal.firebase_ml.zzum r0 = r0.zzqx()
            r12 = 0
            r5 = r1
            r6 = r10
            r7 = r14
            r8 = r28
            r9 = r18
            r10 = r0
            r13 = r15
            r14 = r24
            r15 = r20
            r16 = r37
            r17 = r38
            r18 = r39
            r19 = r40
            r20 = r41
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return r1
        L_0x0444:
            com.google.android.gms.internal.firebase_ml.zzvp r0 = (com.google.android.gms.internal.firebase_ml.zzvp) r0
            r0.zzqv()
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzuq.a(java.lang.Class, com.google.android.gms.internal.firebase_ml.zzuk, com.google.android.gms.internal.firebase_ml.zzuu, com.google.android.gms.internal.firebase_ml.zztw, com.google.android.gms.internal.firebase_ml.zzvu, com.google.android.gms.internal.firebase_ml.zzsr, com.google.android.gms.internal.firebase_ml.zzuh):com.google.android.gms.internal.firebase_ml.zzuq");
    }

    private static <UT, UB> int zza(zzvu<UT, UB> zzvu, T t) {
        return zzvu.d(zzvu.a(t));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00a7, code lost:
        r3 = r11.zzbic;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0134, code lost:
        r3 = java.lang.Integer.valueOf(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0141, code lost:
        r3 = java.lang.Long.valueOf(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0145, code lost:
        r12.putObject(r1, r9, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0153, code lost:
        r12.putObject(r1, r9, r2);
        r2 = r4 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0164, code lost:
        r12.putObject(r1, r9, r2);
        r2 = r4 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0169, code lost:
        r12.putInt(r1, r13, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(T r17, byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, long r26, int r28, com.google.android.gms.internal.firebase_ml.zzrr r29) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = r19
            r2 = r21
            r8 = r22
            r5 = r23
            r9 = r26
            r6 = r28
            r11 = r29
            sun.misc.Unsafe r12 = zzbor
            int[] r7 = r0.zzbos
            int r13 = r6 + 2
            r7 = r7[r13]
            r13 = 1048575(0xfffff, float:1.469367E-39)
            r7 = r7 & r13
            long r13 = (long) r7
            r7 = 5
            r15 = 2
            switch(r25) {
                case 51: goto L_0x0159;
                case 52: goto L_0x0149;
                case 53: goto L_0x0139;
                case 54: goto L_0x0139;
                case 55: goto L_0x012c;
                case 56: goto L_0x0120;
                case 57: goto L_0x0115;
                case 58: goto L_0x00ff;
                case 59: goto L_0x00d1;
                case 60: goto L_0x00ab;
                case 61: goto L_0x00a1;
                case 62: goto L_0x012c;
                case 63: goto L_0x0073;
                case 64: goto L_0x0115;
                case 65: goto L_0x0120;
                case 66: goto L_0x0065;
                case 67: goto L_0x0057;
                case 68: goto L_0x0028;
                default: goto L_0x0026;
            }
        L_0x0026:
            goto L_0x016d
        L_0x0028:
            r7 = 3
            if (r5 != r7) goto L_0x016d
            r2 = r2 & -8
            r7 = r2 | 4
            com.google.android.gms.internal.firebase_ml.zzvc r2 = r0.zzcz(r6)
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r7
            r7 = r29
            int r2 = com.google.android.gms.internal.firebase_ml.zzrq.a((com.google.android.gms.internal.firebase_ml.zzvc) r2, (byte[]) r3, (int) r4, (int) r5, (int) r6, (com.google.android.gms.internal.firebase_ml.zzrr) r7)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x004b
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x004c
        L_0x004b:
            r15 = 0
        L_0x004c:
            if (r15 != 0) goto L_0x004f
            goto L_0x00a7
        L_0x004f:
            java.lang.Object r3 = r11.zzbic
            java.lang.Object r3 = com.google.android.gms.internal.firebase_ml.zzte.a((java.lang.Object) r15, (java.lang.Object) r3)
            goto L_0x0145
        L_0x0057:
            if (r5 != 0) goto L_0x016d
            int r2 = com.google.android.gms.internal.firebase_ml.zzrq.b(r3, r4, r11)
            long r3 = r11.zzbib
            long r3 = com.google.android.gms.internal.firebase_ml.zzsg.zzm(r3)
            goto L_0x0141
        L_0x0065:
            if (r5 != 0) goto L_0x016d
            int r2 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r11)
            int r3 = r11.zzbia
            int r3 = com.google.android.gms.internal.firebase_ml.zzsg.zzcg(r3)
            goto L_0x0134
        L_0x0073:
            if (r5 != 0) goto L_0x016d
            int r3 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r11)
            int r4 = r11.zzbia
            com.google.android.gms.internal.firebase_ml.zzth r5 = r0.zzdb(r6)
            if (r5 == 0) goto L_0x0097
            boolean r5 = r5.zzb(r4)
            if (r5 == 0) goto L_0x0088
            goto L_0x0097
        L_0x0088:
            com.google.android.gms.internal.firebase_ml.zzvv r1 = zzab(r17)
            long r4 = (long) r4
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r1.a((int) r2, (java.lang.Object) r4)
            r2 = r3
            goto L_0x016e
        L_0x0097:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            r12.putObject(r1, r9, r2)
            r2 = r3
            goto L_0x0169
        L_0x00a1:
            if (r5 != r15) goto L_0x016d
            int r2 = com.google.android.gms.internal.firebase_ml.zzrq.e(r3, r4, r11)
        L_0x00a7:
            java.lang.Object r3 = r11.zzbic
            goto L_0x0145
        L_0x00ab:
            if (r5 != r15) goto L_0x016d
            com.google.android.gms.internal.firebase_ml.zzvc r2 = r0.zzcz(r6)
            r5 = r20
            int r2 = com.google.android.gms.internal.firebase_ml.zzrq.a((com.google.android.gms.internal.firebase_ml.zzvc) r2, (byte[]) r3, (int) r4, (int) r5, (com.google.android.gms.internal.firebase_ml.zzrr) r11)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x00c2
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x00c3
        L_0x00c2:
            r15 = 0
        L_0x00c3:
            if (r15 != 0) goto L_0x00c9
            java.lang.Object r3 = r11.zzbic
            goto L_0x0145
        L_0x00c9:
            java.lang.Object r3 = r11.zzbic
            java.lang.Object r3 = com.google.android.gms.internal.firebase_ml.zzte.a((java.lang.Object) r15, (java.lang.Object) r3)
            goto L_0x0145
        L_0x00d1:
            if (r5 != r15) goto L_0x016d
            int r2 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r11)
            int r4 = r11.zzbia
            if (r4 != 0) goto L_0x00de
            java.lang.String r3 = ""
            goto L_0x0145
        L_0x00de:
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            r5 = r24 & r5
            if (r5 == 0) goto L_0x00f2
            int r5 = r2 + r4
            boolean r5 = com.google.android.gms.internal.firebase_ml.zzwc.zzf(r3, r2, r5)
            if (r5 == 0) goto L_0x00ed
            goto L_0x00f2
        L_0x00ed:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.f()
            throw r1
        L_0x00f2:
            java.lang.String r5 = new java.lang.String
            java.nio.charset.Charset r6 = com.google.android.gms.internal.firebase_ml.zzte.a
            r5.<init>(r3, r2, r4, r6)
            r12.putObject(r1, r9, r5)
            int r2 = r2 + r4
            goto L_0x0169
        L_0x00ff:
            if (r5 != 0) goto L_0x016d
            int r2 = com.google.android.gms.internal.firebase_ml.zzrq.b(r3, r4, r11)
            long r3 = r11.zzbib
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x010f
            r15 = 1
            goto L_0x0110
        L_0x010f:
            r15 = 0
        L_0x0110:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r15)
            goto L_0x0145
        L_0x0115:
            if (r5 != r7) goto L_0x016d
            int r2 = com.google.android.gms.internal.firebase_ml.zzrq.a(r18, r19)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L_0x0153
        L_0x0120:
            r2 = 1
            if (r5 != r2) goto L_0x016d
            long r2 = com.google.android.gms.internal.firebase_ml.zzrq.b(r18, r19)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            goto L_0x0164
        L_0x012c:
            if (r5 != 0) goto L_0x016d
            int r2 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r11)
            int r3 = r11.zzbia
        L_0x0134:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L_0x0145
        L_0x0139:
            if (r5 != 0) goto L_0x016d
            int r2 = com.google.android.gms.internal.firebase_ml.zzrq.b(r3, r4, r11)
            long r3 = r11.zzbib
        L_0x0141:
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
        L_0x0145:
            r12.putObject(r1, r9, r3)
            goto L_0x0169
        L_0x0149:
            if (r5 != r7) goto L_0x016d
            float r2 = com.google.android.gms.internal.firebase_ml.zzrq.d(r18, r19)
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
        L_0x0153:
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 4
            goto L_0x0169
        L_0x0159:
            r2 = 1
            if (r5 != r2) goto L_0x016d
            double r2 = com.google.android.gms.internal.firebase_ml.zzrq.c(r18, r19)
            java.lang.Double r2 = java.lang.Double.valueOf(r2)
        L_0x0164:
            r12.putObject(r1, r9, r2)
            int r2 = r4 + 8
        L_0x0169:
            r12.putInt(r1, r13, r8)
            goto L_0x016e
        L_0x016d:
            r2 = r4
        L_0x016e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzuq.zza(java.lang.Object, byte[], int, int, int, int, int, int, int, long, int, com.google.android.gms.internal.firebase_ml.zzrr):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:139:0x026a, code lost:
        if (r7.zzbib != 0) goto L_0x026c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x026c, code lost:
        r6 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x026e, code lost:
        r6 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x026f, code lost:
        r11.addBoolean(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0272, code lost:
        if (r4 >= r5) goto L_0x03d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0274, code lost:
        r6 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x027a, code lost:
        if (r2 != r7.zzbia) goto L_0x03d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x027c, code lost:
        r4 = com.google.android.gms.internal.firebase_ml.zzrq.b(r3, r6, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0284, code lost:
        if (r7.zzbib == 0) goto L_0x026e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:?, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:?, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:?, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:?, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0136, code lost:
        if (r4 == 0) goto L_0x0138;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0138, code lost:
        r11.add(com.google.android.gms.internal.firebase_ml.zzru.zzbig);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x013e, code lost:
        r11.add(com.google.android.gms.internal.firebase_ml.zzru.zzc(r3, r1, r4));
        r1 = r1 + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0146, code lost:
        if (r1 >= r5) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0148, code lost:
        r4 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r1, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x014e, code lost:
        if (r2 != r7.zzbia) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0150, code lost:
        r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7);
        r4 = r7.zzbia;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0156, code lost:
        if (r4 < 0) goto L_0x0164;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x015a, code lost:
        if (r4 > (r3.length - r1)) goto L_0x015f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x015c, code lost:
        if (r4 != 0) goto L_0x013e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0163, code lost:
        throw com.google.android.gms.internal.firebase_ml.zztm.a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0168, code lost:
        throw com.google.android.gms.internal.firebase_ml.zztm.b();
     */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x01ff  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01b5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(T r17, byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24, long r25, int r27, long r28, com.google.android.gms.internal.firebase_ml.zzrr r30) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = r19
            r5 = r20
            r2 = r21
            r6 = r23
            r8 = r24
            r9 = r28
            r7 = r30
            sun.misc.Unsafe r11 = zzbor
            java.lang.Object r11 = r11.getObject(r1, r9)
            com.google.android.gms.internal.firebase_ml.zztl r11 = (com.google.android.gms.internal.firebase_ml.zztl) r11
            boolean r12 = r11.zzog()
            r13 = 1
            if (r12 != 0) goto L_0x0036
            int r12 = r11.size()
            if (r12 != 0) goto L_0x002c
            r12 = 10
            goto L_0x002d
        L_0x002c:
            int r12 = r12 << r13
        L_0x002d:
            com.google.android.gms.internal.firebase_ml.zztl r11 = r11.zzcb(r12)
            sun.misc.Unsafe r12 = zzbor
            r12.putObject(r1, r9, r11)
        L_0x0036:
            r9 = 5
            r14 = 0
            r10 = 2
            switch(r27) {
                case 18: goto L_0x0395;
                case 19: goto L_0x0357;
                case 20: goto L_0x031f;
                case 21: goto L_0x031f;
                case 22: goto L_0x0305;
                case 23: goto L_0x02c6;
                case 24: goto L_0x0287;
                case 25: goto L_0x0236;
                case 26: goto L_0x018d;
                case 27: goto L_0x0173;
                case 28: goto L_0x0128;
                case 29: goto L_0x0305;
                case 30: goto L_0x00f0;
                case 31: goto L_0x0287;
                case 32: goto L_0x02c6;
                case 33: goto L_0x00b0;
                case 34: goto L_0x0070;
                case 35: goto L_0x0395;
                case 36: goto L_0x0357;
                case 37: goto L_0x031f;
                case 38: goto L_0x031f;
                case 39: goto L_0x0305;
                case 40: goto L_0x02c6;
                case 41: goto L_0x0287;
                case 42: goto L_0x0236;
                case 43: goto L_0x0305;
                case 44: goto L_0x00f0;
                case 45: goto L_0x0287;
                case 46: goto L_0x02c6;
                case 47: goto L_0x00b0;
                case 48: goto L_0x0070;
                case 49: goto L_0x003f;
                default: goto L_0x003d;
            }
        L_0x003d:
            goto L_0x03d3
        L_0x003f:
            r1 = 3
            if (r6 != r1) goto L_0x03d3
            com.google.android.gms.internal.firebase_ml.zzvc r1 = r0.zzcz(r8)
            r6 = r2 & -8
            r6 = r6 | 4
            r22 = r1
            r23 = r18
            r24 = r19
        L_0x0050:
            r25 = r20
            r26 = r6
            r27 = r30
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.a((com.google.android.gms.internal.firebase_ml.zzvc) r22, (byte[]) r23, (int) r24, (int) r25, (int) r26, (com.google.android.gms.internal.firebase_ml.zzrr) r27)
            java.lang.Object r8 = r7.zzbic
            r11.add(r8)
            if (r4 >= r5) goto L_0x03d3
            int r8 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7)
            int r9 = r7.zzbia
            if (r2 != r9) goto L_0x03d3
            r22 = r1
            r23 = r18
            r24 = r8
            goto L_0x0050
        L_0x0070:
            if (r6 != r10) goto L_0x0094
            com.google.android.gms.internal.firebase_ml.zzua r11 = (com.google.android.gms.internal.firebase_ml.zzua) r11
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7)
            int r2 = r7.zzbia
            int r2 = r2 + r1
        L_0x007b:
            if (r1 >= r2) goto L_0x008b
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.b(r3, r1, r7)
            long r4 = r7.zzbib
            long r4 = com.google.android.gms.internal.firebase_ml.zzsg.zzm(r4)
            r11.zzx(r4)
            goto L_0x007b
        L_0x008b:
            if (r1 != r2) goto L_0x008f
            goto L_0x03d4
        L_0x008f:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.a()
            throw r1
        L_0x0094:
            if (r6 != 0) goto L_0x03d3
            com.google.android.gms.internal.firebase_ml.zzua r11 = (com.google.android.gms.internal.firebase_ml.zzua) r11
        L_0x0098:
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.b(r3, r4, r7)
            long r8 = r7.zzbib
            long r8 = com.google.android.gms.internal.firebase_ml.zzsg.zzm(r8)
            r11.zzx(r8)
            if (r1 >= r5) goto L_0x03d4
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r1, r7)
            int r6 = r7.zzbia
            if (r2 != r6) goto L_0x03d4
            goto L_0x0098
        L_0x00b0:
            if (r6 != r10) goto L_0x00d4
            com.google.android.gms.internal.firebase_ml.zztd r11 = (com.google.android.gms.internal.firebase_ml.zztd) r11
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7)
            int r2 = r7.zzbia
            int r2 = r2 + r1
        L_0x00bb:
            if (r1 >= r2) goto L_0x00cb
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r1, r7)
            int r4 = r7.zzbia
            int r4 = com.google.android.gms.internal.firebase_ml.zzsg.zzcg(r4)
            r11.zzcy(r4)
            goto L_0x00bb
        L_0x00cb:
            if (r1 != r2) goto L_0x00cf
            goto L_0x03d4
        L_0x00cf:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.a()
            throw r1
        L_0x00d4:
            if (r6 != 0) goto L_0x03d3
            com.google.android.gms.internal.firebase_ml.zztd r11 = (com.google.android.gms.internal.firebase_ml.zztd) r11
        L_0x00d8:
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7)
            int r4 = r7.zzbia
            int r4 = com.google.android.gms.internal.firebase_ml.zzsg.zzcg(r4)
            r11.zzcy(r4)
            if (r1 >= r5) goto L_0x03d4
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r1, r7)
            int r6 = r7.zzbia
            if (r2 != r6) goto L_0x03d4
            goto L_0x00d8
        L_0x00f0:
            if (r6 != r10) goto L_0x00f7
            int r2 = com.google.android.gms.internal.firebase_ml.zzrq.a((byte[]) r3, (int) r4, (com.google.android.gms.internal.firebase_ml.zztl<?>) r11, (com.google.android.gms.internal.firebase_ml.zzrr) r7)
            goto L_0x0108
        L_0x00f7:
            if (r6 != 0) goto L_0x03d3
            r2 = r21
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r11
            r7 = r30
            int r2 = com.google.android.gms.internal.firebase_ml.zzrq.a((int) r2, (byte[]) r3, (int) r4, (int) r5, (com.google.android.gms.internal.firebase_ml.zztl<?>) r6, (com.google.android.gms.internal.firebase_ml.zzrr) r7)
        L_0x0108:
            com.google.android.gms.internal.firebase_ml.zztc r1 = (com.google.android.gms.internal.firebase_ml.zztc) r1
            com.google.android.gms.internal.firebase_ml.zzvv r3 = r1.zzbmc
            com.google.android.gms.internal.firebase_ml.zzvv r4 = com.google.android.gms.internal.firebase_ml.zzvv.zzru()
            if (r3 != r4) goto L_0x0113
            r3 = 0
        L_0x0113:
            com.google.android.gms.internal.firebase_ml.zzth r4 = r0.zzdb(r8)
            com.google.android.gms.internal.firebase_ml.zzvu<?, ?> r5 = r0.zzbpg
            r6 = r22
            java.lang.Object r3 = com.google.android.gms.internal.firebase_ml.zzve.a(r6, r11, r4, r3, r5)
            com.google.android.gms.internal.firebase_ml.zzvv r3 = (com.google.android.gms.internal.firebase_ml.zzvv) r3
            if (r3 == 0) goto L_0x0125
            r1.zzbmc = r3
        L_0x0125:
            r1 = r2
            goto L_0x03d4
        L_0x0128:
            if (r6 != r10) goto L_0x03d3
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7)
            int r4 = r7.zzbia
            if (r4 < 0) goto L_0x016e
            int r6 = r3.length
            int r6 = r6 - r1
            if (r4 > r6) goto L_0x0169
            if (r4 != 0) goto L_0x013e
        L_0x0138:
            com.google.android.gms.internal.firebase_ml.zzru r4 = com.google.android.gms.internal.firebase_ml.zzru.zzbig
            r11.add(r4)
            goto L_0x0146
        L_0x013e:
            com.google.android.gms.internal.firebase_ml.zzru r6 = com.google.android.gms.internal.firebase_ml.zzru.zzc(r3, r1, r4)
            r11.add(r6)
            int r1 = r1 + r4
        L_0x0146:
            if (r1 >= r5) goto L_0x03d4
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r1, r7)
            int r6 = r7.zzbia
            if (r2 != r6) goto L_0x03d4
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7)
            int r4 = r7.zzbia
            if (r4 < 0) goto L_0x0164
            int r6 = r3.length
            int r6 = r6 - r1
            if (r4 > r6) goto L_0x015f
            if (r4 != 0) goto L_0x013e
            goto L_0x0138
        L_0x015f:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.a()
            throw r1
        L_0x0164:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.b()
            throw r1
        L_0x0169:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.a()
            throw r1
        L_0x016e:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.b()
            throw r1
        L_0x0173:
            if (r6 != r10) goto L_0x03d3
            com.google.android.gms.internal.firebase_ml.zzvc r1 = r0.zzcz(r8)
            r22 = r1
            r23 = r21
            r24 = r18
            r25 = r19
            r26 = r20
            r27 = r11
            r28 = r30
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r22, r23, r24, r25, r26, r27, r28)
            goto L_0x03d4
        L_0x018d:
            if (r6 != r10) goto L_0x03d3
            r8 = 536870912(0x20000000, double:2.652494739E-315)
            long r8 = r25 & r8
            int r1 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r1 != 0) goto L_0x01da
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7)
            int r4 = r7.zzbia
            if (r4 < 0) goto L_0x01d5
            if (r4 != 0) goto L_0x01a8
        L_0x01a2:
            java.lang.String r4 = ""
            r11.add(r4)
            goto L_0x01b3
        L_0x01a8:
            java.lang.String r6 = new java.lang.String
            java.nio.charset.Charset r8 = com.google.android.gms.internal.firebase_ml.zzte.a
            r6.<init>(r3, r1, r4, r8)
        L_0x01af:
            r11.add(r6)
            int r1 = r1 + r4
        L_0x01b3:
            if (r1 >= r5) goto L_0x03d4
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r1, r7)
            int r6 = r7.zzbia
            if (r2 != r6) goto L_0x03d4
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7)
            int r4 = r7.zzbia
            if (r4 < 0) goto L_0x01d0
            if (r4 != 0) goto L_0x01c8
            goto L_0x01a2
        L_0x01c8:
            java.lang.String r6 = new java.lang.String
            java.nio.charset.Charset r8 = com.google.android.gms.internal.firebase_ml.zzte.a
            r6.<init>(r3, r1, r4, r8)
            goto L_0x01af
        L_0x01d0:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.b()
            throw r1
        L_0x01d5:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.b()
            throw r1
        L_0x01da:
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7)
            int r4 = r7.zzbia
            if (r4 < 0) goto L_0x0231
            if (r4 != 0) goto L_0x01ea
        L_0x01e4:
            java.lang.String r4 = ""
            r11.add(r4)
            goto L_0x01fd
        L_0x01ea:
            int r6 = r1 + r4
            boolean r8 = com.google.android.gms.internal.firebase_ml.zzwc.zzf(r3, r1, r6)
            if (r8 == 0) goto L_0x022c
            java.lang.String r8 = new java.lang.String
            java.nio.charset.Charset r9 = com.google.android.gms.internal.firebase_ml.zzte.a
            r8.<init>(r3, r1, r4, r9)
        L_0x01f9:
            r11.add(r8)
            r1 = r6
        L_0x01fd:
            if (r1 >= r5) goto L_0x03d4
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r1, r7)
            int r6 = r7.zzbia
            if (r2 != r6) goto L_0x03d4
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7)
            int r4 = r7.zzbia
            if (r4 < 0) goto L_0x0227
            if (r4 != 0) goto L_0x0212
            goto L_0x01e4
        L_0x0212:
            int r6 = r1 + r4
            boolean r8 = com.google.android.gms.internal.firebase_ml.zzwc.zzf(r3, r1, r6)
            if (r8 == 0) goto L_0x0222
            java.lang.String r8 = new java.lang.String
            java.nio.charset.Charset r9 = com.google.android.gms.internal.firebase_ml.zzte.a
            r8.<init>(r3, r1, r4, r9)
            goto L_0x01f9
        L_0x0222:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.f()
            throw r1
        L_0x0227:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.b()
            throw r1
        L_0x022c:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.f()
            throw r1
        L_0x0231:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.b()
            throw r1
        L_0x0236:
            r1 = 0
            if (r6 != r10) goto L_0x025e
            com.google.android.gms.internal.firebase_ml.zzrs r11 = (com.google.android.gms.internal.firebase_ml.zzrs) r11
            int r2 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7)
            int r4 = r7.zzbia
            int r4 = r4 + r2
        L_0x0242:
            if (r2 >= r4) goto L_0x0255
            int r2 = com.google.android.gms.internal.firebase_ml.zzrq.b(r3, r2, r7)
            long r5 = r7.zzbib
            int r8 = (r5 > r14 ? 1 : (r5 == r14 ? 0 : -1))
            if (r8 == 0) goto L_0x0250
            r5 = 1
            goto L_0x0251
        L_0x0250:
            r5 = 0
        L_0x0251:
            r11.addBoolean(r5)
            goto L_0x0242
        L_0x0255:
            if (r2 != r4) goto L_0x0259
            goto L_0x0125
        L_0x0259:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.a()
            throw r1
        L_0x025e:
            if (r6 != 0) goto L_0x03d3
            com.google.android.gms.internal.firebase_ml.zzrs r11 = (com.google.android.gms.internal.firebase_ml.zzrs) r11
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.b(r3, r4, r7)
            long r8 = r7.zzbib
            int r6 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r6 == 0) goto L_0x026e
        L_0x026c:
            r6 = 1
            goto L_0x026f
        L_0x026e:
            r6 = 0
        L_0x026f:
            r11.addBoolean(r6)
            if (r4 >= r5) goto L_0x03d3
            int r6 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7)
            int r8 = r7.zzbia
            if (r2 != r8) goto L_0x03d3
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.b(r3, r6, r7)
            long r8 = r7.zzbib
            int r6 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r6 == 0) goto L_0x026e
            goto L_0x026c
        L_0x0287:
            if (r6 != r10) goto L_0x02a7
            com.google.android.gms.internal.firebase_ml.zztd r11 = (com.google.android.gms.internal.firebase_ml.zztd) r11
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7)
            int r2 = r7.zzbia
            int r2 = r2 + r1
        L_0x0292:
            if (r1 >= r2) goto L_0x029e
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r1)
            r11.zzcy(r4)
            int r1 = r1 + 4
            goto L_0x0292
        L_0x029e:
            if (r1 != r2) goto L_0x02a2
            goto L_0x03d4
        L_0x02a2:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.a()
            throw r1
        L_0x02a7:
            if (r6 != r9) goto L_0x03d3
            com.google.android.gms.internal.firebase_ml.zztd r11 = (com.google.android.gms.internal.firebase_ml.zztd) r11
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r18, r19)
            r11.zzcy(r1)
        L_0x02b2:
            int r1 = r4 + 4
            if (r1 >= r5) goto L_0x03d4
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r1, r7)
            int r6 = r7.zzbia
            if (r2 != r6) goto L_0x03d4
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4)
            r11.zzcy(r1)
            goto L_0x02b2
        L_0x02c6:
            if (r6 != r10) goto L_0x02e6
            com.google.android.gms.internal.firebase_ml.zzua r11 = (com.google.android.gms.internal.firebase_ml.zzua) r11
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7)
            int r2 = r7.zzbia
            int r2 = r2 + r1
        L_0x02d1:
            if (r1 >= r2) goto L_0x02dd
            long r4 = com.google.android.gms.internal.firebase_ml.zzrq.b(r3, r1)
            r11.zzx(r4)
            int r1 = r1 + 8
            goto L_0x02d1
        L_0x02dd:
            if (r1 != r2) goto L_0x02e1
            goto L_0x03d4
        L_0x02e1:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.a()
            throw r1
        L_0x02e6:
            if (r6 != r13) goto L_0x03d3
            com.google.android.gms.internal.firebase_ml.zzua r11 = (com.google.android.gms.internal.firebase_ml.zzua) r11
            long r8 = com.google.android.gms.internal.firebase_ml.zzrq.b(r18, r19)
            r11.zzx(r8)
        L_0x02f1:
            int r1 = r4 + 8
            if (r1 >= r5) goto L_0x03d4
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r1, r7)
            int r6 = r7.zzbia
            if (r2 != r6) goto L_0x03d4
            long r8 = com.google.android.gms.internal.firebase_ml.zzrq.b(r3, r4)
            r11.zzx(r8)
            goto L_0x02f1
        L_0x0305:
            if (r6 != r10) goto L_0x030d
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a((byte[]) r3, (int) r4, (com.google.android.gms.internal.firebase_ml.zztl<?>) r11, (com.google.android.gms.internal.firebase_ml.zzrr) r7)
            goto L_0x03d4
        L_0x030d:
            if (r6 != 0) goto L_0x03d3
            r22 = r18
            r23 = r19
            r24 = r20
            r25 = r11
            r26 = r30
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a((int) r21, (byte[]) r22, (int) r23, (int) r24, (com.google.android.gms.internal.firebase_ml.zztl<?>) r25, (com.google.android.gms.internal.firebase_ml.zzrr) r26)
            goto L_0x03d4
        L_0x031f:
            if (r6 != r10) goto L_0x033f
            com.google.android.gms.internal.firebase_ml.zzua r11 = (com.google.android.gms.internal.firebase_ml.zzua) r11
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7)
            int r2 = r7.zzbia
            int r2 = r2 + r1
        L_0x032a:
            if (r1 >= r2) goto L_0x0336
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.b(r3, r1, r7)
            long r4 = r7.zzbib
            r11.zzx(r4)
            goto L_0x032a
        L_0x0336:
            if (r1 != r2) goto L_0x033a
            goto L_0x03d4
        L_0x033a:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.a()
            throw r1
        L_0x033f:
            if (r6 != 0) goto L_0x03d3
            com.google.android.gms.internal.firebase_ml.zzua r11 = (com.google.android.gms.internal.firebase_ml.zzua) r11
        L_0x0343:
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.b(r3, r4, r7)
            long r8 = r7.zzbib
            r11.zzx(r8)
            if (r1 >= r5) goto L_0x03d4
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r1, r7)
            int r6 = r7.zzbia
            if (r2 != r6) goto L_0x03d4
            goto L_0x0343
        L_0x0357:
            if (r6 != r10) goto L_0x0376
            com.google.android.gms.internal.firebase_ml.zzta r11 = (com.google.android.gms.internal.firebase_ml.zzta) r11
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7)
            int r2 = r7.zzbia
            int r2 = r2 + r1
        L_0x0362:
            if (r1 >= r2) goto L_0x036e
            float r4 = com.google.android.gms.internal.firebase_ml.zzrq.d(r3, r1)
            r11.zzt(r4)
            int r1 = r1 + 4
            goto L_0x0362
        L_0x036e:
            if (r1 != r2) goto L_0x0371
            goto L_0x03d4
        L_0x0371:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.a()
            throw r1
        L_0x0376:
            if (r6 != r9) goto L_0x03d3
            com.google.android.gms.internal.firebase_ml.zzta r11 = (com.google.android.gms.internal.firebase_ml.zzta) r11
            float r1 = com.google.android.gms.internal.firebase_ml.zzrq.d(r18, r19)
            r11.zzt(r1)
        L_0x0381:
            int r1 = r4 + 4
            if (r1 >= r5) goto L_0x03d4
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r1, r7)
            int r6 = r7.zzbia
            if (r2 != r6) goto L_0x03d4
            float r1 = com.google.android.gms.internal.firebase_ml.zzrq.d(r3, r4)
            r11.zzt(r1)
            goto L_0x0381
        L_0x0395:
            if (r6 != r10) goto L_0x03b4
            com.google.android.gms.internal.firebase_ml.zzsm r11 = (com.google.android.gms.internal.firebase_ml.zzsm) r11
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r4, r7)
            int r2 = r7.zzbia
            int r2 = r2 + r1
        L_0x03a0:
            if (r1 >= r2) goto L_0x03ac
            double r4 = com.google.android.gms.internal.firebase_ml.zzrq.c(r3, r1)
            r11.zze(r4)
            int r1 = r1 + 8
            goto L_0x03a0
        L_0x03ac:
            if (r1 != r2) goto L_0x03af
            goto L_0x03d4
        L_0x03af:
            com.google.android.gms.internal.firebase_ml.zztm r1 = com.google.android.gms.internal.firebase_ml.zztm.a()
            throw r1
        L_0x03b4:
            if (r6 != r13) goto L_0x03d3
            com.google.android.gms.internal.firebase_ml.zzsm r11 = (com.google.android.gms.internal.firebase_ml.zzsm) r11
            double r8 = com.google.android.gms.internal.firebase_ml.zzrq.c(r18, r19)
            r11.zze(r8)
        L_0x03bf:
            int r1 = r4 + 8
            if (r1 >= r5) goto L_0x03d4
            int r4 = com.google.android.gms.internal.firebase_ml.zzrq.a(r3, r1, r7)
            int r6 = r7.zzbia
            if (r2 != r6) goto L_0x03d4
            double r8 = com.google.android.gms.internal.firebase_ml.zzrq.c(r3, r4)
            r11.zze(r8)
            goto L_0x03bf
        L_0x03d3:
            r1 = r4
        L_0x03d4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzuq.zza(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.firebase_ml.zzrr):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v12, resolved type: byte} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <K, V> int zza(T r8, byte[] r9, int r10, int r11, int r12, long r13, com.google.android.gms.internal.firebase_ml.zzrr r15) {
        /*
            r7 = this;
            sun.misc.Unsafe r0 = zzbor
            java.lang.Object r12 = r7.zzda(r12)
            java.lang.Object r1 = r0.getObject(r8, r13)
            com.google.android.gms.internal.firebase_ml.zzuh r2 = r7.zzbpi
            boolean r2 = r2.zzw(r1)
            if (r2 == 0) goto L_0x0021
            com.google.android.gms.internal.firebase_ml.zzuh r2 = r7.zzbpi
            java.lang.Object r2 = r2.zzy(r12)
            com.google.android.gms.internal.firebase_ml.zzuh r3 = r7.zzbpi
            r3.zzd(r2, r1)
            r0.putObject(r8, r13, r2)
            r1 = r2
        L_0x0021:
            com.google.android.gms.internal.firebase_ml.zzuh r8 = r7.zzbpi
            com.google.android.gms.internal.firebase_ml.zzuf r8 = r8.zzz(r12)
            com.google.android.gms.internal.firebase_ml.zzuh r12 = r7.zzbpi
            java.util.Map r12 = r12.zzu(r1)
            int r10 = com.google.android.gms.internal.firebase_ml.zzrq.a(r9, r10, r15)
            int r13 = r15.zzbia
            if (r13 < 0) goto L_0x0094
            int r14 = r11 - r10
            if (r13 > r14) goto L_0x0094
            int r13 = r13 + r10
            K r14 = r8.zzbok
            V r0 = r8.zzbom
        L_0x003e:
            if (r10 >= r13) goto L_0x0089
            int r1 = r10 + 1
            byte r10 = r9[r10]
            if (r10 >= 0) goto L_0x004c
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a((int) r10, (byte[]) r9, (int) r1, (com.google.android.gms.internal.firebase_ml.zzrr) r15)
            int r10 = r15.zzbia
        L_0x004c:
            r2 = r1
            int r1 = r10 >>> 3
            r3 = r10 & 7
            switch(r1) {
                case 1: goto L_0x006f;
                case 2: goto L_0x0055;
                default: goto L_0x0054;
            }
        L_0x0054:
            goto L_0x0084
        L_0x0055:
            com.google.android.gms.internal.firebase_ml.zzwj r1 = r8.zzbol
            int r1 = r1.zzse()
            if (r3 != r1) goto L_0x0084
            com.google.android.gms.internal.firebase_ml.zzwj r4 = r8.zzbol
            V r10 = r8.zzbom
            java.lang.Class r5 = r10.getClass()
            r1 = r9
            r3 = r11
            r6 = r15
            int r10 = zza((byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.firebase_ml.zzwj) r4, (java.lang.Class<?>) r5, (com.google.android.gms.internal.firebase_ml.zzrr) r6)
            java.lang.Object r0 = r15.zzbic
            goto L_0x003e
        L_0x006f:
            com.google.android.gms.internal.firebase_ml.zzwj r1 = r8.zzboj
            int r1 = r1.zzse()
            if (r3 != r1) goto L_0x0084
            com.google.android.gms.internal.firebase_ml.zzwj r4 = r8.zzboj
            r5 = 0
            r1 = r9
            r3 = r11
            r6 = r15
            int r10 = zza((byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.firebase_ml.zzwj) r4, (java.lang.Class<?>) r5, (com.google.android.gms.internal.firebase_ml.zzrr) r6)
            java.lang.Object r14 = r15.zzbic
            goto L_0x003e
        L_0x0084:
            int r10 = com.google.android.gms.internal.firebase_ml.zzrq.a((int) r10, (byte[]) r9, (int) r2, (int) r11, (com.google.android.gms.internal.firebase_ml.zzrr) r15)
            goto L_0x003e
        L_0x0089:
            if (r10 != r13) goto L_0x008f
            r12.put(r14, r0)
            return r13
        L_0x008f:
            com.google.android.gms.internal.firebase_ml.zztm r8 = com.google.android.gms.internal.firebase_ml.zztm.e()
            throw r8
        L_0x0094:
            com.google.android.gms.internal.firebase_ml.zztm r8 = com.google.android.gms.internal.firebase_ml.zztm.a()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzuq.zza(java.lang.Object, byte[], int, int, int, long, com.google.android.gms.internal.firebase_ml.zzrr):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x004d, code lost:
        r2 = java.lang.Integer.valueOf(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0051, code lost:
        r6.zzbic = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x006e, code lost:
        r6.zzbic = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007b, code lost:
        r6.zzbic = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return r2 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return r2 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0042, code lost:
        r2 = java.lang.Long.valueOf(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zza(byte[] r1, int r2, int r3, com.google.android.gms.internal.firebase_ml.zzwj r4, java.lang.Class<?> r5, com.google.android.gms.internal.firebase_ml.zzrr r6) {
        /*
            int[] r0 = com.google.android.gms.internal.firebase_ml.zzur.a
            int r4 = r4.ordinal()
            r4 = r0[r4]
            switch(r4) {
                case 1: goto L_0x0085;
                case 2: goto L_0x0080;
                case 3: goto L_0x0073;
                case 4: goto L_0x0066;
                case 5: goto L_0x0066;
                case 6: goto L_0x005d;
                case 7: goto L_0x005d;
                case 8: goto L_0x0054;
                case 9: goto L_0x0047;
                case 10: goto L_0x0047;
                case 11: goto L_0x0047;
                case 12: goto L_0x003c;
                case 13: goto L_0x003c;
                case 14: goto L_0x002f;
                case 15: goto L_0x0024;
                case 16: goto L_0x0019;
                case 17: goto L_0x0013;
                default: goto L_0x000b;
            }
        L_0x000b:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "unsupported field type."
            r1.<init>(r2)
            throw r1
        L_0x0013:
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.d(r1, r2, r6)
            goto L_0x0099
        L_0x0019:
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.b(r1, r2, r6)
            long r2 = r6.zzbib
            long r2 = com.google.android.gms.internal.firebase_ml.zzsg.zzm(r2)
            goto L_0x0042
        L_0x0024:
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r1, r2, r6)
            int r2 = r6.zzbia
            int r2 = com.google.android.gms.internal.firebase_ml.zzsg.zzcg(r2)
            goto L_0x004d
        L_0x002f:
            com.google.android.gms.internal.firebase_ml.zzuz r4 = com.google.android.gms.internal.firebase_ml.zzuz.zzrc()
            com.google.android.gms.internal.firebase_ml.zzvc r4 = r4.zzl(r5)
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a((com.google.android.gms.internal.firebase_ml.zzvc) r4, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.firebase_ml.zzrr) r6)
            goto L_0x0099
        L_0x003c:
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.b(r1, r2, r6)
            long r2 = r6.zzbib
        L_0x0042:
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            goto L_0x0051
        L_0x0047:
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r1, r2, r6)
            int r2 = r6.zzbia
        L_0x004d:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
        L_0x0051:
            r6.zzbic = r2
            goto L_0x0099
        L_0x0054:
            float r1 = com.google.android.gms.internal.firebase_ml.zzrq.d(r1, r2)
            java.lang.Float r1 = java.lang.Float.valueOf(r1)
            goto L_0x006e
        L_0x005d:
            long r3 = com.google.android.gms.internal.firebase_ml.zzrq.b(r1, r2)
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            goto L_0x007b
        L_0x0066:
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.a(r1, r2)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
        L_0x006e:
            r6.zzbic = r1
            int r1 = r2 + 4
            goto L_0x0099
        L_0x0073:
            double r3 = com.google.android.gms.internal.firebase_ml.zzrq.c(r1, r2)
            java.lang.Double r1 = java.lang.Double.valueOf(r3)
        L_0x007b:
            r6.zzbic = r1
            int r1 = r2 + 8
            goto L_0x0099
        L_0x0080:
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.e(r1, r2, r6)
            goto L_0x0099
        L_0x0085:
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.b(r1, r2, r6)
            long r2 = r6.zzbib
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0093
            r2 = 1
            goto L_0x0094
        L_0x0093:
            r2 = 0
        L_0x0094:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            goto L_0x0051
        L_0x0099:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzuq.zza(byte[], int, int, com.google.android.gms.internal.firebase_ml.zzwj, java.lang.Class, com.google.android.gms.internal.firebase_ml.zzrr):int");
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzth zzth, UB ub, zzvu<UT, UB> zzvu) {
        zzuf<?, ?> zzz = this.zzbpi.zzz(zzda(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (!zzth.zzb(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzvu.a();
                }
                zzsc b = zzru.b(zzue.a(zzz, next.getKey(), next.getValue()));
                try {
                    zzue.a(b.zzoq(), zzz, next.getKey(), next.getValue());
                    zzvu.a(ub, i2, b.zzop());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    private static void zza(int i, Object obj, zzwp zzwp) {
        if (obj instanceof String) {
            zzwp.zzb(i, (String) obj);
        } else {
            zzwp.zza(i, (zzru) obj);
        }
    }

    private static <UT, UB> void zza(zzvu<UT, UB> zzvu, T t, zzwp zzwp) {
        zzvu.a(zzvu.a(t), zzwp);
    }

    private final <K, V> void zza(zzwp zzwp, int i, Object obj, int i2) {
        if (obj != null) {
            zzwp.zza(i, this.zzbpi.zzz(zzda(i2)), this.zzbpi.zzv(obj));
        }
    }

    private final void zza(T t, T t2, int i) {
        long zzdc = (long) (zzdc(i) & 1048575);
        if (zza(t2, i)) {
            Object f = zzwa.f(t, zzdc);
            Object f2 = zzwa.f(t2, zzdc);
            if (f != null && f2 != null) {
                zzwa.a((Object) t, zzdc, zzte.a(f, f2));
                zzb(t, i);
            } else if (f2 != null) {
                zzwa.a((Object) t, zzdc, f2);
                zzb(t, i);
            }
        }
    }

    private final boolean zza(T t, int i) {
        if (this.zzboz) {
            int zzdc = zzdc(i);
            long j = (long) (zzdc & 1048575);
            switch ((zzdc & 267386880) >>> 20) {
                case 0:
                    return zzwa.e(t, j) != 0.0d;
                case 1:
                    return zzwa.d(t, j) != 0.0f;
                case 2:
                    return zzwa.b(t, j) != 0;
                case 3:
                    return zzwa.b(t, j) != 0;
                case 4:
                    return zzwa.a((Object) t, j) != 0;
                case 5:
                    return zzwa.b(t, j) != 0;
                case 6:
                    return zzwa.a((Object) t, j) != 0;
                case 7:
                    return zzwa.c(t, j);
                case 8:
                    Object f = zzwa.f(t, j);
                    if (f instanceof String) {
                        return !((String) f).isEmpty();
                    }
                    if (f instanceof zzru) {
                        return !zzru.zzbig.equals(f);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzwa.f(t, j) != null;
                case 10:
                    return !zzru.zzbig.equals(zzwa.f(t, j));
                case 11:
                    return zzwa.a((Object) t, j) != 0;
                case 12:
                    return zzwa.a((Object) t, j) != 0;
                case 13:
                    return zzwa.a((Object) t, j) != 0;
                case 14:
                    return zzwa.b(t, j) != 0;
                case 15:
                    return zzwa.a((Object) t, j) != 0;
                case 16:
                    return zzwa.b(t, j) != 0;
                case 17:
                    return zzwa.f(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            int zzdd = zzdd(i);
            return (zzwa.a((Object) t, (long) (zzdd & 1048575)) & (1 << (zzdd >>> 20))) != 0;
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzwa.a((Object) t, (long) (zzdd(i2) & 1048575)) == i;
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        return this.zzboz ? zza(t, i) : (i2 & i3) != 0;
    }

    private static boolean zza(Object obj, int i, zzvc zzvc) {
        return zzvc.zzac(zzwa.f(obj, (long) (i & 1048575)));
    }

    private static zzvv zzab(Object obj) {
        zztc zztc = (zztc) obj;
        zzvv zzvv = zztc.zzbmc;
        if (zzvv != zzvv.zzru()) {
            return zzvv;
        }
        zzvv a = zzvv.a();
        zztc.zzbmc = a;
        return a;
    }

    private final void zzb(T t, int i) {
        if (!this.zzboz) {
            int zzdd = zzdd(i);
            long j = (long) (zzdd & 1048575);
            zzwa.a((Object) t, j, zzwa.a((Object) t, j) | (1 << (zzdd >>> 20)));
        }
    }

    private final void zzb(T t, int i, int i2) {
        zzwa.a((Object) t, (long) (zzdd(i2) & 1048575), i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0280, code lost:
        com.google.android.gms.internal.firebase_ml.zzve.zzj(r4, (java.util.List) r8.getObject(r1, r12), r2, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0290, code lost:
        com.google.android.gms.internal.firebase_ml.zzve.zzg(r4, (java.util.List) r8.getObject(r1, r12), r2, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x02a0, code lost:
        com.google.android.gms.internal.firebase_ml.zzve.zzl(r4, (java.util.List) r8.getObject(r1, r12), r2, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x02b0, code lost:
        com.google.android.gms.internal.firebase_ml.zzve.zzm(r4, (java.util.List) r8.getObject(r1, r12), r2, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x02c0, code lost:
        com.google.android.gms.internal.firebase_ml.zzve.zzi(r4, (java.util.List) r8.getObject(r1, r12), r2, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0479, code lost:
        r5 = r5 + 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0276, code lost:
        com.google.android.gms.internal.firebase_ml.zzve.zze(r4, r9, r2, r14);
     */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x0483  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r19, com.google.android.gms.internal.firebase_ml.zzwp r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            boolean r3 = r0.zzbox
            if (r3 == 0) goto L_0x0021
            com.google.android.gms.internal.firebase_ml.zzsr<?> r3 = r0.zzbph
            com.google.android.gms.internal.firebase_ml.zzsu r3 = r3.a((java.lang.Object) r1)
            boolean r5 = r3.a()
            if (r5 != 0) goto L_0x0021
            java.util.Iterator r3 = r3.iterator()
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x0023
        L_0x0021:
            r3 = 0
            r5 = 0
        L_0x0023:
            r6 = -1
            int[] r7 = r0.zzbos
            int r7 = r7.length
            sun.misc.Unsafe r8 = zzbor
            r10 = r5
            r5 = 0
            r11 = 0
        L_0x002c:
            if (r5 >= r7) goto L_0x047d
            int r12 = r0.zzdc(r5)
            int[] r13 = r0.zzbos
            r14 = r13[r5]
            r15 = 267386880(0xff00000, float:2.3665827E-29)
            r15 = r15 & r12
            int r15 = r15 >>> 20
            boolean r4 = r0.zzboz
            r16 = 1048575(0xfffff, float:1.469367E-39)
            if (r4 != 0) goto L_0x0062
            r4 = 17
            if (r15 > r4) goto L_0x0062
            int r4 = r5 + 2
            r4 = r13[r4]
            r13 = r4 & r16
            if (r13 == r6) goto L_0x0056
            r17 = r10
            long r9 = (long) r13
            int r11 = r8.getInt(r1, r9)
            goto L_0x0059
        L_0x0056:
            r17 = r10
            r13 = r6
        L_0x0059:
            int r4 = r4 >>> 20
            r6 = 1
            int r9 = r6 << r4
            r6 = r13
            r10 = r17
            goto L_0x0067
        L_0x0062:
            r17 = r10
            r10 = r17
            r9 = 0
        L_0x0067:
            if (r10 == 0) goto L_0x0086
            com.google.android.gms.internal.firebase_ml.zzsr<?> r4 = r0.zzbph
            int r4 = r4.a((java.util.Map.Entry<?, ?>) r10)
            if (r4 > r14) goto L_0x0086
            com.google.android.gms.internal.firebase_ml.zzsr<?> r4 = r0.zzbph
            r4.a(r2, r10)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0084
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            r10 = r4
            goto L_0x0067
        L_0x0084:
            r10 = 0
            goto L_0x0067
        L_0x0086:
            r4 = r12 & r16
            long r12 = (long) r4
            switch(r15) {
                case 0: goto L_0x046d;
                case 1: goto L_0x0460;
                case 2: goto L_0x0453;
                case 3: goto L_0x0446;
                case 4: goto L_0x0439;
                case 5: goto L_0x042c;
                case 6: goto L_0x041f;
                case 7: goto L_0x0412;
                case 8: goto L_0x0404;
                case 9: goto L_0x03f2;
                case 10: goto L_0x03e2;
                case 11: goto L_0x03d4;
                case 12: goto L_0x03c6;
                case 13: goto L_0x03b8;
                case 14: goto L_0x03aa;
                case 15: goto L_0x039c;
                case 16: goto L_0x038e;
                case 17: goto L_0x037c;
                case 18: goto L_0x036c;
                case 19: goto L_0x035c;
                case 20: goto L_0x034c;
                case 21: goto L_0x033c;
                case 22: goto L_0x032c;
                case 23: goto L_0x031c;
                case 24: goto L_0x030c;
                case 25: goto L_0x02fc;
                case 26: goto L_0x02ed;
                case 27: goto L_0x02da;
                case 28: goto L_0x02cb;
                case 29: goto L_0x02bb;
                case 30: goto L_0x02ab;
                case 31: goto L_0x029b;
                case 32: goto L_0x028b;
                case 33: goto L_0x027b;
                case 34: goto L_0x026b;
                case 35: goto L_0x025b;
                case 36: goto L_0x024b;
                case 37: goto L_0x023b;
                case 38: goto L_0x022b;
                case 39: goto L_0x021b;
                case 40: goto L_0x020b;
                case 41: goto L_0x01fb;
                case 42: goto L_0x01eb;
                case 43: goto L_0x01e4;
                case 44: goto L_0x01dd;
                case 45: goto L_0x01d6;
                case 46: goto L_0x01cf;
                case 47: goto L_0x01c8;
                case 48: goto L_0x01bb;
                case 49: goto L_0x01a8;
                case 50: goto L_0x019f;
                case 51: goto L_0x0190;
                case 52: goto L_0x0181;
                case 53: goto L_0x0172;
                case 54: goto L_0x0163;
                case 55: goto L_0x0154;
                case 56: goto L_0x0145;
                case 57: goto L_0x0136;
                case 58: goto L_0x0127;
                case 59: goto L_0x0118;
                case 60: goto L_0x0105;
                case 61: goto L_0x00f5;
                case 62: goto L_0x00e7;
                case 63: goto L_0x00d9;
                case 64: goto L_0x00cb;
                case 65: goto L_0x00bd;
                case 66: goto L_0x00af;
                case 67: goto L_0x00a1;
                case 68: goto L_0x008f;
                default: goto L_0x008c;
            }
        L_0x008c:
            r15 = 0
            goto L_0x0479
        L_0x008f:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008c
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.firebase_ml.zzvc r9 = r0.zzcz(r5)
            r2.zzb((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_ml.zzvc) r9)
            goto L_0x008c
        L_0x00a1:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008c
            long r12 = zzi(r1, r12)
            r2.zzb((int) r14, (long) r12)
            goto L_0x008c
        L_0x00af:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008c
            int r4 = zzh(r1, r12)
            r2.zzg(r14, r4)
            goto L_0x008c
        L_0x00bd:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008c
            long r12 = zzi(r1, r12)
            r2.zzj(r14, r12)
            goto L_0x008c
        L_0x00cb:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008c
            int r4 = zzh(r1, r12)
            r2.zzo(r14, r4)
            goto L_0x008c
        L_0x00d9:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008c
            int r4 = zzh(r1, r12)
            r2.zzp(r14, r4)
            goto L_0x008c
        L_0x00e7:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008c
            int r4 = zzh(r1, r12)
            r2.zzf(r14, r4)
            goto L_0x008c
        L_0x00f5:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008c
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.firebase_ml.zzru r4 = (com.google.android.gms.internal.firebase_ml.zzru) r4
            r2.zza((int) r14, (com.google.android.gms.internal.firebase_ml.zzru) r4)
            goto L_0x008c
        L_0x0105:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008c
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.firebase_ml.zzvc r9 = r0.zzcz(r5)
            r2.zza((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_ml.zzvc) r9)
            goto L_0x008c
        L_0x0118:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008c
            java.lang.Object r4 = r8.getObject(r1, r12)
            zza((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_ml.zzwp) r2)
            goto L_0x008c
        L_0x0127:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008c
            boolean r4 = zzj(r1, r12)
            r2.zzb((int) r14, (boolean) r4)
            goto L_0x008c
        L_0x0136:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008c
            int r4 = zzh(r1, r12)
            r2.zzh(r14, r4)
            goto L_0x008c
        L_0x0145:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008c
            long r12 = zzi(r1, r12)
            r2.zzc(r14, r12)
            goto L_0x008c
        L_0x0154:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008c
            int r4 = zzh(r1, r12)
            r2.zze(r14, r4)
            goto L_0x008c
        L_0x0163:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008c
            long r12 = zzi(r1, r12)
            r2.zza((int) r14, (long) r12)
            goto L_0x008c
        L_0x0172:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008c
            long r12 = zzi(r1, r12)
            r2.zzi(r14, r12)
            goto L_0x008c
        L_0x0181:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008c
            float r4 = zzg(r1, r12)
            r2.zza((int) r14, (float) r4)
            goto L_0x008c
        L_0x0190:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008c
            double r12 = zzf(r1, r12)
            r2.zza((int) r14, (double) r12)
            goto L_0x008c
        L_0x019f:
            java.lang.Object r4 = r8.getObject(r1, r12)
            r0.zza((com.google.android.gms.internal.firebase_ml.zzwp) r2, (int) r14, (java.lang.Object) r4, (int) r5)
            goto L_0x008c
        L_0x01a8:
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzvc r12 = r0.zzcz(r5)
            com.google.android.gms.internal.firebase_ml.zzve.zzb((int) r4, (java.util.List<?>) r9, (com.google.android.gms.internal.firebase_ml.zzwp) r2, (com.google.android.gms.internal.firebase_ml.zzvc) r12)
            goto L_0x008c
        L_0x01bb:
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r14 = 1
            goto L_0x0276
        L_0x01c8:
            r14 = 1
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            goto L_0x0280
        L_0x01cf:
            r14 = 1
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            goto L_0x0290
        L_0x01d6:
            r14 = 1
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            goto L_0x02a0
        L_0x01dd:
            r14 = 1
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            goto L_0x02b0
        L_0x01e4:
            r14 = 1
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            goto L_0x02c0
        L_0x01eb:
            r14 = 1
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzn(r4, r9, r2, r14)
            goto L_0x008c
        L_0x01fb:
            r14 = 1
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzk(r4, r9, r2, r14)
            goto L_0x008c
        L_0x020b:
            r14 = 1
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzf(r4, r9, r2, r14)
            goto L_0x008c
        L_0x021b:
            r14 = 1
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzh(r4, r9, r2, r14)
            goto L_0x008c
        L_0x022b:
            r14 = 1
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzd(r4, r9, r2, r14)
            goto L_0x008c
        L_0x023b:
            r14 = 1
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzc(r4, r9, r2, r14)
            goto L_0x008c
        L_0x024b:
            r14 = 1
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzb((int) r4, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.firebase_ml.zzwp) r2, (boolean) r14)
            goto L_0x008c
        L_0x025b:
            r14 = 1
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zza((int) r4, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.firebase_ml.zzwp) r2, (boolean) r14)
            goto L_0x008c
        L_0x026b:
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r14 = 0
        L_0x0276:
            com.google.android.gms.internal.firebase_ml.zzve.zze(r4, r9, r2, r14)
            goto L_0x008c
        L_0x027b:
            r14 = 0
            int[] r4 = r0.zzbos
            r4 = r4[r5]
        L_0x0280:
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzj(r4, r9, r2, r14)
            goto L_0x008c
        L_0x028b:
            r14 = 0
            int[] r4 = r0.zzbos
            r4 = r4[r5]
        L_0x0290:
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzg(r4, r9, r2, r14)
            goto L_0x008c
        L_0x029b:
            r14 = 0
            int[] r4 = r0.zzbos
            r4 = r4[r5]
        L_0x02a0:
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzl(r4, r9, r2, r14)
            goto L_0x008c
        L_0x02ab:
            r14 = 0
            int[] r4 = r0.zzbos
            r4 = r4[r5]
        L_0x02b0:
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzm(r4, r9, r2, r14)
            goto L_0x008c
        L_0x02bb:
            r14 = 0
            int[] r4 = r0.zzbos
            r4 = r4[r5]
        L_0x02c0:
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzi(r4, r9, r2, r14)
            goto L_0x008c
        L_0x02cb:
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzb(r4, r9, r2)
            goto L_0x008c
        L_0x02da:
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzvc r12 = r0.zzcz(r5)
            com.google.android.gms.internal.firebase_ml.zzve.zza((int) r4, (java.util.List<?>) r9, (com.google.android.gms.internal.firebase_ml.zzwp) r2, (com.google.android.gms.internal.firebase_ml.zzvc) r12)
            goto L_0x008c
        L_0x02ed:
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zza(r4, r9, r2)
            goto L_0x008c
        L_0x02fc:
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r15 = 0
            com.google.android.gms.internal.firebase_ml.zzve.zzn(r4, r9, r2, r15)
            goto L_0x0479
        L_0x030c:
            r15 = 0
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzk(r4, r9, r2, r15)
            goto L_0x0479
        L_0x031c:
            r15 = 0
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzf(r4, r9, r2, r15)
            goto L_0x0479
        L_0x032c:
            r15 = 0
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzh(r4, r9, r2, r15)
            goto L_0x0479
        L_0x033c:
            r15 = 0
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzd(r4, r9, r2, r15)
            goto L_0x0479
        L_0x034c:
            r15 = 0
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzc(r4, r9, r2, r15)
            goto L_0x0479
        L_0x035c:
            r15 = 0
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzb((int) r4, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.firebase_ml.zzwp) r2, (boolean) r15)
            goto L_0x0479
        L_0x036c:
            r15 = 0
            int[] r4 = r0.zzbos
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zza((int) r4, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.firebase_ml.zzwp) r2, (boolean) r15)
            goto L_0x0479
        L_0x037c:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0479
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.firebase_ml.zzvc r9 = r0.zzcz(r5)
            r2.zzb((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_ml.zzvc) r9)
            goto L_0x0479
        L_0x038e:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0479
            long r12 = r8.getLong(r1, r12)
            r2.zzb((int) r14, (long) r12)
            goto L_0x0479
        L_0x039c:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0479
            int r4 = r8.getInt(r1, r12)
            r2.zzg(r14, r4)
            goto L_0x0479
        L_0x03aa:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0479
            long r12 = r8.getLong(r1, r12)
            r2.zzj(r14, r12)
            goto L_0x0479
        L_0x03b8:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0479
            int r4 = r8.getInt(r1, r12)
            r2.zzo(r14, r4)
            goto L_0x0479
        L_0x03c6:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0479
            int r4 = r8.getInt(r1, r12)
            r2.zzp(r14, r4)
            goto L_0x0479
        L_0x03d4:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0479
            int r4 = r8.getInt(r1, r12)
            r2.zzf(r14, r4)
            goto L_0x0479
        L_0x03e2:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0479
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.firebase_ml.zzru r4 = (com.google.android.gms.internal.firebase_ml.zzru) r4
            r2.zza((int) r14, (com.google.android.gms.internal.firebase_ml.zzru) r4)
            goto L_0x0479
        L_0x03f2:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0479
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.firebase_ml.zzvc r9 = r0.zzcz(r5)
            r2.zza((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_ml.zzvc) r9)
            goto L_0x0479
        L_0x0404:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0479
            java.lang.Object r4 = r8.getObject(r1, r12)
            zza((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_ml.zzwp) r2)
            goto L_0x0479
        L_0x0412:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0479
            boolean r4 = com.google.android.gms.internal.firebase_ml.zzwa.c(r1, r12)
            r2.zzb((int) r14, (boolean) r4)
            goto L_0x0479
        L_0x041f:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0479
            int r4 = r8.getInt(r1, r12)
            r2.zzh(r14, r4)
            goto L_0x0479
        L_0x042c:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0479
            long r12 = r8.getLong(r1, r12)
            r2.zzc(r14, r12)
            goto L_0x0479
        L_0x0439:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0479
            int r4 = r8.getInt(r1, r12)
            r2.zze(r14, r4)
            goto L_0x0479
        L_0x0446:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0479
            long r12 = r8.getLong(r1, r12)
            r2.zza((int) r14, (long) r12)
            goto L_0x0479
        L_0x0453:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0479
            long r12 = r8.getLong(r1, r12)
            r2.zzi(r14, r12)
            goto L_0x0479
        L_0x0460:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0479
            float r4 = com.google.android.gms.internal.firebase_ml.zzwa.d(r1, r12)
            r2.zza((int) r14, (float) r4)
            goto L_0x0479
        L_0x046d:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x0479
            double r12 = com.google.android.gms.internal.firebase_ml.zzwa.e(r1, r12)
            r2.zza((int) r14, (double) r12)
        L_0x0479:
            int r5 = r5 + 3
            goto L_0x002c
        L_0x047d:
            r17 = r10
            r4 = r17
        L_0x0481:
            if (r4 == 0) goto L_0x0497
            com.google.android.gms.internal.firebase_ml.zzsr<?> r5 = r0.zzbph
            r5.a(r2, r4)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0495
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            goto L_0x0481
        L_0x0495:
            r4 = 0
            goto L_0x0481
        L_0x0497:
            com.google.android.gms.internal.firebase_ml.zzvu<?, ?> r3 = r0.zzbpg
            zza(r3, r1, (com.google.android.gms.internal.firebase_ml.zzwp) r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzuq.zzb(java.lang.Object, com.google.android.gms.internal.firebase_ml.zzwp):void");
    }

    private final void zzb(T t, T t2, int i) {
        int zzdc = zzdc(i);
        int i2 = this.zzbos[i];
        long j = (long) (zzdc & 1048575);
        if (zza(t2, i2, i)) {
            Object f = zzwa.f(t, j);
            Object f2 = zzwa.f(t2, j);
            if (f != null && f2 != null) {
                zzwa.a((Object) t, j, zzte.a(f, f2));
                zzb(t, i2, i);
            } else if (f2 != null) {
                zzwa.a((Object) t, j, f2);
                zzb(t, i2, i);
            }
        }
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza(t, i) == zza(t2, i);
    }

    private final zzvc zzcz(int i) {
        int i2 = (i / 3) << 1;
        zzvc zzvc = (zzvc) this.zzbot[i2];
        if (zzvc != null) {
            return zzvc;
        }
        zzvc zzl = zzuz.zzrc().zzl((Class) this.zzbot[i2 + 1]);
        this.zzbot[i2] = zzl;
        return zzl;
    }

    private final Object zzda(int i) {
        return this.zzbot[(i / 3) << 1];
    }

    private final zzth zzdb(int i) {
        return (zzth) this.zzbot[((i / 3) << 1) + 1];
    }

    private final int zzdc(int i) {
        return this.zzbos[i + 1];
    }

    private final int zzdd(int i) {
        return this.zzbos[i + 2];
    }

    private final int zzde(int i) {
        if (i < this.zzbou || i > this.zzbov) {
            return -1;
        }
        return zzs(i, 0);
    }

    private static <E> List<E> zze(Object obj, long j) {
        return (List) zzwa.f(obj, j);
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzwa.f(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzwa.f(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzwa.f(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzwa.f(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzwa.f(t, j)).booleanValue();
    }

    private final int zzr(int i, int i2) {
        if (i < this.zzbou || i > this.zzbov) {
            return -1;
        }
        return zzs(i, i2);
    }

    private final int zzs(int i, int i2) {
        int length = (this.zzbos.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzbos[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v31, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r26v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v27, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v31, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v32, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v33, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v35, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v37, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v43, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v38, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v39, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v33, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v33, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v35, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v36, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x02c4, code lost:
        r18 = r32;
        r22 = r6;
        r2 = r7;
        r6 = r8;
        r19 = r9;
        r27 = r10;
        r25 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0368, code lost:
        if (r0 == r15) goto L_0x03d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x03b1, code lost:
        if (r0 == r15) goto L_0x03d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0094, code lost:
        r7 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x012b, code lost:
        r10.putInt(r14, r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0173, code lost:
        r6 = r6 | r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0175, code lost:
        r3 = r8;
        r2 = r9;
        r1 = r11;
        r9 = r13;
        r11 = r34;
        r13 = r33;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01ce, code lost:
        r10.putObject(r14, r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0212, code lost:
        r6 = r6 | r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0214, code lost:
        r3 = r8;
        r2 = r9;
        r1 = r11;
        r9 = r13;
        r11 = r34;
        r13 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x023e, code lost:
        r32 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x02b8, code lost:
        r0 = r7 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x02ba, code lost:
        r6 = r6 | r22;
        r7 = r32;
        r3 = r8;
        r2 = r9;
        r1 = r11;
        r9 = r13;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(T r30, byte[] r31, int r32, int r33, int r34, com.google.android.gms.internal.firebase_ml.zzrr r35) {
        /*
            r29 = this;
            r15 = r29
            r14 = r30
            r12 = r31
            r13 = r33
            r11 = r34
            r9 = r35
            sun.misc.Unsafe r10 = zzbor
            r16 = 0
            r0 = r32
            r1 = -1
            r2 = 0
            r3 = 0
            r6 = 0
            r7 = -1
        L_0x0017:
            r17 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 >= r13) goto L_0x0475
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x002b
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.a((int) r0, (byte[]) r12, (int) r3, (com.google.android.gms.internal.firebase_ml.zzrr) r9)
            int r3 = r9.zzbia
            r4 = r0
            r5 = r3
            goto L_0x002d
        L_0x002b:
            r5 = r0
            r4 = r3
        L_0x002d:
            int r3 = r5 >>> 3
            r0 = r5 & 7
            r8 = 3
            if (r3 <= r1) goto L_0x003a
            int r2 = r2 / r8
            int r1 = r15.zzr(r3, r2)
            goto L_0x003e
        L_0x003a:
            int r1 = r15.zzde(r3)
        L_0x003e:
            r2 = r1
            r1 = -1
            if (r2 != r1) goto L_0x0051
            r25 = r3
            r2 = r4
            r22 = r6
            r18 = r7
            r27 = r10
            r7 = r11
            r19 = 0
            r6 = r5
            goto L_0x03d9
        L_0x0051:
            int[] r1 = r15.zzbos
            int r19 = r2 + 1
            r8 = r1[r19]
            r19 = 267386880(0xff00000, float:2.3665827E-29)
            r19 = r8 & r19
            int r11 = r19 >>> 20
            r19 = r5
            r5 = r8 & r17
            long r12 = (long) r5
            r5 = 17
            r20 = r8
            if (r11 > r5) goto L_0x02d2
            int r5 = r2 + 2
            r1 = r1[r5]
            int r5 = r1 >>> 20
            r8 = 1
            int r22 = r8 << r5
            r1 = r1 & r17
            if (r1 == r7) goto L_0x0083
            r5 = -1
            if (r7 == r5) goto L_0x007c
            long r8 = (long) r7
            r10.putInt(r14, r8, r6)
        L_0x007c:
            long r6 = (long) r1
            int r6 = r10.getInt(r14, r6)
            r7 = r1
            goto L_0x0084
        L_0x0083:
            r5 = -1
        L_0x0084:
            r1 = 5
            switch(r11) {
                case 0: goto L_0x02a0;
                case 1: goto L_0x0286;
                case 2: goto L_0x025d;
                case 3: goto L_0x025d;
                case 4: goto L_0x0242;
                case 5: goto L_0x021d;
                case 6: goto L_0x01fa;
                case 7: goto L_0x01d2;
                case 8: goto L_0x01ad;
                case 9: goto L_0x017f;
                case 10: goto L_0x015c;
                case 11: goto L_0x0242;
                case 12: goto L_0x012f;
                case 13: goto L_0x01fa;
                case 14: goto L_0x021d;
                case 15: goto L_0x0114;
                case 16: goto L_0x00e7;
                case 17: goto L_0x0097;
                default: goto L_0x0088;
            }
        L_0x0088:
            r9 = r2
            r11 = r3
            r32 = r7
            r8 = r19
            r12 = r31
            r13 = r35
            r19 = -1
        L_0x0094:
            r7 = r4
            goto L_0x02c4
        L_0x0097:
            r8 = 3
            if (r0 != r8) goto L_0x00da
            int r0 = r3 << 3
            r8 = r0 | 4
            com.google.android.gms.internal.firebase_ml.zzvc r0 = r15.zzcz(r2)
            r1 = r31
            r9 = r2
            r2 = r4
            r11 = r3
            r3 = r33
            r4 = r8
            r8 = r19
            r19 = -1
            r5 = r35
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.a((com.google.android.gms.internal.firebase_ml.zzvc) r0, (byte[]) r1, (int) r2, (int) r3, (int) r4, (com.google.android.gms.internal.firebase_ml.zzrr) r5)
            r1 = r6 & r22
            if (r1 != 0) goto L_0x00bd
            r5 = r35
            java.lang.Object r1 = r5.zzbic
            goto L_0x00c9
        L_0x00bd:
            r5 = r35
            java.lang.Object r1 = r10.getObject(r14, r12)
            java.lang.Object r2 = r5.zzbic
            java.lang.Object r1 = com.google.android.gms.internal.firebase_ml.zzte.a((java.lang.Object) r1, (java.lang.Object) r2)
        L_0x00c9:
            r10.putObject(r14, r12, r1)
            r6 = r6 | r22
            r3 = r8
            r2 = r9
            r1 = r11
            r11 = r34
            r12 = r31
            r13 = r33
            r9 = r5
            goto L_0x0017
        L_0x00da:
            r9 = r2
            r11 = r3
            r8 = r19
            r19 = -1
            r32 = r7
            r12 = r31
            r13 = r35
            goto L_0x0094
        L_0x00e7:
            r9 = r2
            r11 = r3
            r8 = r19
            r5 = r35
            r19 = -1
            if (r0 != 0) goto L_0x010f
            r2 = r12
            r12 = r31
            int r13 = com.google.android.gms.internal.firebase_ml.zzrq.b(r12, r4, r5)
            long r0 = r5.zzbib
            long r17 = com.google.android.gms.internal.firebase_ml.zzsg.zzm(r0)
            r0 = r10
            r1 = r30
            r32 = r13
            r13 = r5
            r4 = r17
            r0.putLong(r1, r2, r4)
            r6 = r6 | r22
            r0 = r32
            goto L_0x0175
        L_0x010f:
            r13 = r5
            r12 = r31
            goto L_0x023e
        L_0x0114:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r12 = r31
            r13 = r35
            r19 = -1
            if (r0 != 0) goto L_0x023e
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.a(r12, r4, r13)
            int r1 = r13.zzbia
            int r1 = com.google.android.gms.internal.firebase_ml.zzsg.zzcg(r1)
        L_0x012b:
            r10.putInt(r14, r2, r1)
            goto L_0x0173
        L_0x012f:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r12 = r31
            r13 = r35
            r19 = -1
            if (r0 != 0) goto L_0x023e
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.a(r12, r4, r13)
            int r1 = r13.zzbia
            com.google.android.gms.internal.firebase_ml.zzth r4 = r15.zzdb(r9)
            if (r4 == 0) goto L_0x012b
            boolean r4 = r4.zzb(r1)
            if (r4 == 0) goto L_0x014f
            goto L_0x012b
        L_0x014f:
            com.google.android.gms.internal.firebase_ml.zzvv r2 = zzab(r30)
            long r3 = (long) r1
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r2.a((int) r8, (java.lang.Object) r1)
            goto L_0x0175
        L_0x015c:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r1 = 2
            r12 = r31
            r13 = r35
            r19 = -1
            if (r0 != r1) goto L_0x023e
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.e(r12, r4, r13)
            java.lang.Object r1 = r13.zzbic
            r10.putObject(r14, r2, r1)
        L_0x0173:
            r6 = r6 | r22
        L_0x0175:
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r11 = r34
            r13 = r33
            goto L_0x0017
        L_0x017f:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r1 = 2
            r12 = r31
            r13 = r35
            r19 = -1
            if (r0 != r1) goto L_0x01a9
            com.google.android.gms.internal.firebase_ml.zzvc r0 = r15.zzcz(r9)
            r5 = r33
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.a((com.google.android.gms.internal.firebase_ml.zzvc) r0, (byte[]) r12, (int) r4, (int) r5, (com.google.android.gms.internal.firebase_ml.zzrr) r13)
            r1 = r6 & r22
            if (r1 != 0) goto L_0x019e
            java.lang.Object r1 = r13.zzbic
            goto L_0x01ce
        L_0x019e:
            java.lang.Object r1 = r10.getObject(r14, r2)
            java.lang.Object r4 = r13.zzbic
            java.lang.Object r1 = com.google.android.gms.internal.firebase_ml.zzte.a((java.lang.Object) r1, (java.lang.Object) r4)
            goto L_0x01ce
        L_0x01a9:
            r5 = r33
            goto L_0x023e
        L_0x01ad:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r1 = 2
            r5 = r33
            r12 = r31
            r13 = r35
            r19 = -1
            if (r0 != r1) goto L_0x023e
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r20 & r0
            if (r0 != 0) goto L_0x01c8
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.c(r12, r4, r13)
            goto L_0x01cc
        L_0x01c8:
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.d(r12, r4, r13)
        L_0x01cc:
            java.lang.Object r1 = r13.zzbic
        L_0x01ce:
            r10.putObject(r14, r2, r1)
            goto L_0x0212
        L_0x01d2:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r5 = r33
            r12 = r31
            r13 = r35
            r19 = -1
            if (r0 != 0) goto L_0x023e
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.b(r12, r4, r13)
            r32 = r0
            long r0 = r13.zzbib
            r20 = 0
            int r4 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1))
            if (r4 == 0) goto L_0x01f1
            r0 = 1
            goto L_0x01f2
        L_0x01f1:
            r0 = 0
        L_0x01f2:
            com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r14, (long) r2, (boolean) r0)
            r6 = r6 | r22
            r0 = r32
            goto L_0x0214
        L_0x01fa:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r5 = r33
            r12 = r31
            r13 = r35
            r19 = -1
            if (r0 != r1) goto L_0x023e
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.a(r12, r4)
            r10.putInt(r14, r2, r0)
            int r0 = r4 + 4
        L_0x0212:
            r6 = r6 | r22
        L_0x0214:
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r11 = r34
            r13 = r5
            goto L_0x0017
        L_0x021d:
            r9 = r2
            r11 = r3
            r2 = r12
            r8 = r19
            r1 = 1
            r5 = r33
            r12 = r31
            r13 = r35
            r19 = -1
            if (r0 != r1) goto L_0x023e
            long r17 = com.google.android.gms.internal.firebase_ml.zzrq.b(r12, r4)
            r0 = r10
            r1 = r30
            r32 = r7
            r7 = r4
            r4 = r17
            r0.putLong(r1, r2, r4)
            goto L_0x02b8
        L_0x023e:
            r32 = r7
            goto L_0x0094
        L_0x0242:
            r9 = r2
            r11 = r3
            r32 = r7
            r2 = r12
            r8 = r19
            r12 = r31
            r13 = r35
            r19 = -1
            r7 = r4
            if (r0 != 0) goto L_0x02c4
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.a(r12, r7, r13)
            int r1 = r13.zzbia
            r10.putInt(r14, r2, r1)
            goto L_0x02ba
        L_0x025d:
            r9 = r2
            r11 = r3
            r32 = r7
            r2 = r12
            r8 = r19
            r12 = r31
            r13 = r35
            r19 = -1
            r7 = r4
            if (r0 != 0) goto L_0x02c4
            int r7 = com.google.android.gms.internal.firebase_ml.zzrq.b(r12, r7, r13)
            long r4 = r13.zzbib
            r0 = r10
            r1 = r30
            r0.putLong(r1, r2, r4)
            r6 = r6 | r22
            r0 = r7
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            r11 = r34
            r7 = r32
            goto L_0x0326
        L_0x0286:
            r9 = r2
            r11 = r3
            r32 = r7
            r2 = r12
            r8 = r19
            r12 = r31
            r13 = r35
            r19 = -1
            r7 = r4
            if (r0 != r1) goto L_0x02c4
            float r0 = com.google.android.gms.internal.firebase_ml.zzrq.d(r12, r7)
            com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r14, (long) r2, (float) r0)
            int r0 = r7 + 4
            goto L_0x02ba
        L_0x02a0:
            r9 = r2
            r11 = r3
            r32 = r7
            r2 = r12
            r8 = r19
            r1 = 1
            r12 = r31
            r13 = r35
            r19 = -1
            r7 = r4
            if (r0 != r1) goto L_0x02c4
            double r0 = com.google.android.gms.internal.firebase_ml.zzrq.c(r12, r7)
            com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r14, (long) r2, (double) r0)
        L_0x02b8:
            int r0 = r7 + 8
        L_0x02ba:
            r6 = r6 | r22
            r7 = r32
            r3 = r8
            r2 = r9
            r1 = r11
            r9 = r13
            goto L_0x0324
        L_0x02c4:
            r18 = r32
            r22 = r6
            r2 = r7
            r6 = r8
            r19 = r9
            r27 = r10
            r25 = r11
            goto L_0x03b7
        L_0x02d2:
            r5 = r3
            r18 = r7
            r8 = r19
            r19 = -1
            r7 = r4
            r28 = r9
            r9 = r2
            r2 = r12
            r12 = r31
            r13 = r28
            r1 = 27
            if (r11 != r1) goto L_0x0337
            r1 = 2
            if (r0 != r1) goto L_0x032a
            java.lang.Object r0 = r10.getObject(r14, r2)
            com.google.android.gms.internal.firebase_ml.zztl r0 = (com.google.android.gms.internal.firebase_ml.zztl) r0
            boolean r1 = r0.zzog()
            if (r1 != 0) goto L_0x0307
            int r1 = r0.size()
            if (r1 != 0) goto L_0x02fe
            r1 = 10
            goto L_0x0300
        L_0x02fe:
            int r1 = r1 << 1
        L_0x0300:
            com.google.android.gms.internal.firebase_ml.zztl r0 = r0.zzcb(r1)
            r10.putObject(r14, r2, r0)
        L_0x0307:
            r11 = r0
            com.google.android.gms.internal.firebase_ml.zzvc r0 = r15.zzcz(r9)
            r1 = r8
            r2 = r31
            r3 = r7
            r4 = r33
            r7 = r5
            r5 = r11
            r22 = r6
            r6 = r35
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.a(r0, r1, r2, r3, r4, r5, r6)
            r1 = r7
            r3 = r8
            r2 = r9
            r9 = r13
            r7 = r18
            r6 = r22
        L_0x0324:
            r11 = r34
        L_0x0326:
            r13 = r33
            goto L_0x0017
        L_0x032a:
            r22 = r6
            r25 = r5
            r15 = r7
            r26 = r8
            r19 = r9
            r27 = r10
            goto L_0x03b4
        L_0x0337:
            r22 = r6
            r6 = r5
            r1 = 49
            if (r11 > r1) goto L_0x0386
            r5 = r20
            long r4 = (long) r5
            r1 = r0
            r0 = r29
            r32 = r1
            r1 = r30
            r23 = r2
            r2 = r31
            r3 = r7
            r20 = r4
            r4 = r33
            r5 = r8
            r25 = r6
            r15 = r7
            r7 = r32
            r26 = r8
            r8 = r9
            r19 = r9
            r27 = r10
            r9 = r20
            r12 = r23
            r14 = r35
            int r0 = r0.zza(r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (int) r6, (int) r7, (int) r8, (long) r9, (int) r11, (long) r12, (com.google.android.gms.internal.firebase_ml.zzrr) r14)
            if (r0 != r15) goto L_0x036c
            goto L_0x03d7
        L_0x036c:
            r12 = r31
            r7 = r18
            r2 = r19
            r6 = r22
            r1 = r25
            r3 = r26
        L_0x0378:
            r10 = r27
            r9 = r35
            r11 = r34
            r13 = r33
            r14 = r30
            r15 = r29
            goto L_0x0017
        L_0x0386:
            r32 = r0
            r23 = r2
            r25 = r6
            r15 = r7
            r26 = r8
            r19 = r9
            r27 = r10
            r5 = r20
            r0 = 50
            if (r11 != r0) goto L_0x03ba
            r7 = r32
            r0 = 2
            if (r7 != r0) goto L_0x03b4
            r0 = r29
            r1 = r30
            r2 = r31
            r3 = r15
            r4 = r33
            r5 = r19
            r6 = r23
            r8 = r35
            int r0 = r0.zza(r1, r2, r3, r4, r5, r6, r8)
            if (r0 != r15) goto L_0x036c
            goto L_0x03d7
        L_0x03b4:
            r2 = r15
        L_0x03b5:
            r6 = r26
        L_0x03b7:
            r7 = r34
            goto L_0x03d9
        L_0x03ba:
            r7 = r32
            r0 = r29
            r1 = r30
            r2 = r31
            r3 = r15
            r4 = r33
            r8 = r5
            r5 = r26
            r6 = r25
            r9 = r11
            r10 = r23
            r12 = r19
            r13 = r35
            int r0 = r0.zza(r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (int) r6, (int) r7, (int) r8, (int) r9, (long) r10, (int) r12, (com.google.android.gms.internal.firebase_ml.zzrr) r13)
            if (r0 != r15) goto L_0x0465
        L_0x03d7:
            r2 = r0
            goto L_0x03b5
        L_0x03d9:
            if (r6 != r7) goto L_0x03eb
            if (r7 != 0) goto L_0x03de
            goto L_0x03eb
        L_0x03de:
            r9 = r2
            r10 = r6
            r0 = r18
            r1 = r22
            r2 = -1
            r8 = r29
            r11 = r30
            goto L_0x0485
        L_0x03eb:
            r8 = r29
            boolean r0 = r8.zzbox
            if (r0 == 0) goto L_0x043d
            r9 = r35
            com.google.android.gms.internal.firebase_ml.zzsp r0 = r9.zzbid
            com.google.android.gms.internal.firebase_ml.zzsp r1 = com.google.android.gms.internal.firebase_ml.zzsp.zzoz()
            if (r0 == r1) goto L_0x043a
            com.google.android.gms.internal.firebase_ml.zzum r0 = r8.zzbow
            com.google.android.gms.internal.firebase_ml.zzsp r1 = r9.zzbid
            r10 = r25
            com.google.android.gms.internal.firebase_ml.zztc$zze r0 = r1.zza(r0, r10)
            if (r0 != 0) goto L_0x042a
            com.google.android.gms.internal.firebase_ml.zzvv r4 = zzab(r30)
            r0 = r6
            r1 = r31
            r3 = r33
            r5 = r35
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.a((int) r0, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.firebase_ml.zzvv) r4, (com.google.android.gms.internal.firebase_ml.zzrr) r5)
            r12 = r31
            r3 = r6
            r11 = r7
            r15 = r8
            r1 = r10
            r7 = r18
            r2 = r19
            r6 = r22
            r10 = r27
            r13 = r33
            r14 = r30
            goto L_0x0017
        L_0x042a:
            r11 = r30
            r0 = r11
            com.google.android.gms.internal.firebase_ml.zztc$zzd r0 = (com.google.android.gms.internal.firebase_ml.zztc.zzd) r0
            r0.a()
            com.google.android.gms.internal.firebase_ml.zzsu<java.lang.Object> r0 = r0.zzbmi
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        L_0x043a:
            r10 = r25
            goto L_0x0441
        L_0x043d:
            r10 = r25
            r9 = r35
        L_0x0441:
            r11 = r30
            com.google.android.gms.internal.firebase_ml.zzvv r4 = zzab(r30)
            r0 = r6
            r1 = r31
            r3 = r33
            r5 = r35
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.a((int) r0, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.firebase_ml.zzvv) r4, (com.google.android.gms.internal.firebase_ml.zzrr) r5)
            r12 = r31
            r3 = r6
            r15 = r8
            r1 = r10
            r14 = r11
            r2 = r19
            r6 = r22
            r10 = r27
            r13 = r33
            r11 = r7
            r7 = r18
            goto L_0x0017
        L_0x0465:
            r10 = r25
            r6 = r26
            r12 = r31
            r3 = r6
            r1 = r10
            r7 = r18
            r2 = r19
            r6 = r22
            goto L_0x0378
        L_0x0475:
            r22 = r6
            r18 = r7
            r27 = r10
            r7 = r11
            r11 = r14
            r8 = r15
            r9 = r0
            r10 = r3
            r0 = r18
            r1 = r22
            r2 = -1
        L_0x0485:
            if (r0 == r2) goto L_0x048d
            long r2 = (long) r0
            r0 = r27
            r0.putInt(r11, r2, r1)
        L_0x048d:
            r0 = 0
            int r1 = r8.zzbpc
            r5 = r0
            r12 = r1
        L_0x0492:
            int r0 = r8.zzbpd
            if (r12 >= r0) goto L_0x04c6
            int[] r0 = r8.zzbpb
            r1 = r0[r12]
            com.google.android.gms.internal.firebase_ml.zzvu<?, ?> r6 = r8.zzbpg
            int[] r0 = r8.zzbos
            r2 = r0[r1]
            int r0 = r8.zzdc(r1)
            r0 = r0 & r17
            long r3 = (long) r0
            java.lang.Object r0 = com.google.android.gms.internal.firebase_ml.zzwa.f(r11, r3)
            if (r0 != 0) goto L_0x04ae
            goto L_0x04c1
        L_0x04ae:
            com.google.android.gms.internal.firebase_ml.zzth r4 = r8.zzdb(r1)
            if (r4 != 0) goto L_0x04b5
            goto L_0x04c1
        L_0x04b5:
            com.google.android.gms.internal.firebase_ml.zzuh r3 = r8.zzbpi
            java.util.Map r3 = r3.zzu(r0)
            r0 = r29
            java.lang.Object r5 = r0.zza((int) r1, (int) r2, r3, (com.google.android.gms.internal.firebase_ml.zzth) r4, r5, r6)
        L_0x04c1:
            com.google.android.gms.internal.firebase_ml.zzvv r5 = (com.google.android.gms.internal.firebase_ml.zzvv) r5
            int r12 = r12 + 1
            goto L_0x0492
        L_0x04c6:
            if (r5 == 0) goto L_0x04cd
            com.google.android.gms.internal.firebase_ml.zzvu<?, ?> r0 = r8.zzbpg
            r0.b((java.lang.Object) r11, r5)
        L_0x04cd:
            if (r7 != 0) goto L_0x04d9
            r0 = r33
            if (r9 != r0) goto L_0x04d4
            goto L_0x04df
        L_0x04d4:
            com.google.android.gms.internal.firebase_ml.zztm r0 = com.google.android.gms.internal.firebase_ml.zztm.e()
            throw r0
        L_0x04d9:
            r0 = r33
            if (r9 > r0) goto L_0x04e0
            if (r10 != r7) goto L_0x04e0
        L_0x04df:
            return r9
        L_0x04e0:
            com.google.android.gms.internal.firebase_ml.zztm r0 = com.google.android.gms.internal.firebase_ml.zztm.e()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzuq.a(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.firebase_ml.zzrr):int");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005c, code lost:
        if (com.google.android.gms.internal.firebase_ml.zzve.a(com.google.android.gms.internal.firebase_ml.zzwa.f(r10, r6), com.google.android.gms.internal.firebase_ml.zzwa.f(r11, r6)) != false) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0070, code lost:
        if (com.google.android.gms.internal.firebase_ml.zzwa.b(r10, r6) == com.google.android.gms.internal.firebase_ml.zzwa.b(r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0082, code lost:
        if (com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0096, code lost:
        if (com.google.android.gms.internal.firebase_ml.zzwa.b(r10, r6) == com.google.android.gms.internal.firebase_ml.zzwa.b(r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a8, code lost:
        if (com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ba, code lost:
        if (com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00cc, code lost:
        if (com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e2, code lost:
        if (com.google.android.gms.internal.firebase_ml.zzve.a(com.google.android.gms.internal.firebase_ml.zzwa.f(r10, r6), com.google.android.gms.internal.firebase_ml.zzwa.f(r11, r6)) != false) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00f8, code lost:
        if (com.google.android.gms.internal.firebase_ml.zzve.a(com.google.android.gms.internal.firebase_ml.zzwa.f(r10, r6), com.google.android.gms.internal.firebase_ml.zzwa.f(r11, r6)) != false) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x010e, code lost:
        if (com.google.android.gms.internal.firebase_ml.zzve.a(com.google.android.gms.internal.firebase_ml.zzwa.f(r10, r6), com.google.android.gms.internal.firebase_ml.zzwa.f(r11, r6)) != false) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0120, code lost:
        if (com.google.android.gms.internal.firebase_ml.zzwa.c(r10, r6) == com.google.android.gms.internal.firebase_ml.zzwa.c(r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0132, code lost:
        if (com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0145, code lost:
        if (com.google.android.gms.internal.firebase_ml.zzwa.b(r10, r6) == com.google.android.gms.internal.firebase_ml.zzwa.b(r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0156, code lost:
        if (com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0169, code lost:
        if (com.google.android.gms.internal.firebase_ml.zzwa.b(r10, r6) == com.google.android.gms.internal.firebase_ml.zzwa.b(r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x017c, code lost:
        if (com.google.android.gms.internal.firebase_ml.zzwa.b(r10, r6) == com.google.android.gms.internal.firebase_ml.zzwa.b(r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0195, code lost:
        if (java.lang.Float.floatToIntBits(com.google.android.gms.internal.firebase_ml.zzwa.d(r10, r6)) == java.lang.Float.floatToIntBits(com.google.android.gms.internal.firebase_ml.zzwa.d(r11, r6))) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01b0, code lost:
        if (java.lang.Double.doubleToLongBits(com.google.android.gms.internal.firebase_ml.zzwa.e(r10, r6)) == java.lang.Double.doubleToLongBits(com.google.android.gms.internal.firebase_ml.zzwa.e(r11, r6))) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01b2, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0038, code lost:
        if (com.google.android.gms.internal.firebase_ml.zzve.a(com.google.android.gms.internal.firebase_ml.zzwa.f(r10, r6), com.google.android.gms.internal.firebase_ml.zzwa.f(r11, r6)) != false) goto L_0x01b3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(T r10, T r11) {
        /*
            r9 = this;
            int[] r0 = r9.zzbos
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            r3 = 1
            if (r2 >= r0) goto L_0x01ba
            int r4 = r9.zzdc(r2)
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r6 = r4 & r5
            long r6 = (long) r6
            r8 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = r4 & r8
            int r4 = r4 >>> 20
            switch(r4) {
                case 0: goto L_0x0198;
                case 1: goto L_0x017f;
                case 2: goto L_0x016c;
                case 3: goto L_0x0159;
                case 4: goto L_0x0148;
                case 5: goto L_0x0135;
                case 6: goto L_0x0124;
                case 7: goto L_0x0112;
                case 8: goto L_0x00fc;
                case 9: goto L_0x00e6;
                case 10: goto L_0x00d0;
                case 11: goto L_0x00be;
                case 12: goto L_0x00ac;
                case 13: goto L_0x009a;
                case 14: goto L_0x0086;
                case 15: goto L_0x0074;
                case 16: goto L_0x0060;
                case 17: goto L_0x004a;
                case 18: goto L_0x003c;
                case 19: goto L_0x003c;
                case 20: goto L_0x003c;
                case 21: goto L_0x003c;
                case 22: goto L_0x003c;
                case 23: goto L_0x003c;
                case 24: goto L_0x003c;
                case 25: goto L_0x003c;
                case 26: goto L_0x003c;
                case 27: goto L_0x003c;
                case 28: goto L_0x003c;
                case 29: goto L_0x003c;
                case 30: goto L_0x003c;
                case 31: goto L_0x003c;
                case 32: goto L_0x003c;
                case 33: goto L_0x003c;
                case 34: goto L_0x003c;
                case 35: goto L_0x003c;
                case 36: goto L_0x003c;
                case 37: goto L_0x003c;
                case 38: goto L_0x003c;
                case 39: goto L_0x003c;
                case 40: goto L_0x003c;
                case 41: goto L_0x003c;
                case 42: goto L_0x003c;
                case 43: goto L_0x003c;
                case 44: goto L_0x003c;
                case 45: goto L_0x003c;
                case 46: goto L_0x003c;
                case 47: goto L_0x003c;
                case 48: goto L_0x003c;
                case 49: goto L_0x003c;
                case 50: goto L_0x003c;
                case 51: goto L_0x001c;
                case 52: goto L_0x001c;
                case 53: goto L_0x001c;
                case 54: goto L_0x001c;
                case 55: goto L_0x001c;
                case 56: goto L_0x001c;
                case 57: goto L_0x001c;
                case 58: goto L_0x001c;
                case 59: goto L_0x001c;
                case 60: goto L_0x001c;
                case 61: goto L_0x001c;
                case 62: goto L_0x001c;
                case 63: goto L_0x001c;
                case 64: goto L_0x001c;
                case 65: goto L_0x001c;
                case 66: goto L_0x001c;
                case 67: goto L_0x001c;
                case 68: goto L_0x001c;
                default: goto L_0x001a;
            }
        L_0x001a:
            goto L_0x01b3
        L_0x001c:
            int r4 = r9.zzdd(r2)
            r4 = r4 & r5
            long r4 = (long) r4
            int r8 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r10, (long) r4)
            int r4 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r11, (long) r4)
            if (r8 != r4) goto L_0x01b2
            java.lang.Object r4 = com.google.android.gms.internal.firebase_ml.zzwa.f(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase_ml.zzwa.f(r11, r6)
            boolean r4 = com.google.android.gms.internal.firebase_ml.zzve.a((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01b3
            goto L_0x0197
        L_0x003c:
            java.lang.Object r3 = com.google.android.gms.internal.firebase_ml.zzwa.f(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.firebase_ml.zzwa.f(r11, r6)
            boolean r3 = com.google.android.gms.internal.firebase_ml.zzve.a((java.lang.Object) r3, (java.lang.Object) r4)
            goto L_0x01b3
        L_0x004a:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            java.lang.Object r4 = com.google.android.gms.internal.firebase_ml.zzwa.f(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase_ml.zzwa.f(r11, r6)
            boolean r4 = com.google.android.gms.internal.firebase_ml.zzve.a((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01b3
            goto L_0x01b2
        L_0x0060:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            long r4 = com.google.android.gms.internal.firebase_ml.zzwa.b(r10, r6)
            long r6 = com.google.android.gms.internal.firebase_ml.zzwa.b(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01b3
            goto L_0x0197
        L_0x0074:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            int r4 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r10, (long) r6)
            int r5 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r11, (long) r6)
            if (r4 == r5) goto L_0x01b3
            goto L_0x01b2
        L_0x0086:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            long r4 = com.google.android.gms.internal.firebase_ml.zzwa.b(r10, r6)
            long r6 = com.google.android.gms.internal.firebase_ml.zzwa.b(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01b3
            goto L_0x0197
        L_0x009a:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            int r4 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r10, (long) r6)
            int r5 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r11, (long) r6)
            if (r4 == r5) goto L_0x01b3
            goto L_0x01b2
        L_0x00ac:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            int r4 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r10, (long) r6)
            int r5 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r11, (long) r6)
            if (r4 == r5) goto L_0x01b3
            goto L_0x0197
        L_0x00be:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            int r4 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r10, (long) r6)
            int r5 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r11, (long) r6)
            if (r4 == r5) goto L_0x01b3
            goto L_0x01b2
        L_0x00d0:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            java.lang.Object r4 = com.google.android.gms.internal.firebase_ml.zzwa.f(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase_ml.zzwa.f(r11, r6)
            boolean r4 = com.google.android.gms.internal.firebase_ml.zzve.a((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01b3
            goto L_0x0197
        L_0x00e6:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            java.lang.Object r4 = com.google.android.gms.internal.firebase_ml.zzwa.f(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase_ml.zzwa.f(r11, r6)
            boolean r4 = com.google.android.gms.internal.firebase_ml.zzve.a((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01b3
            goto L_0x01b2
        L_0x00fc:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            java.lang.Object r4 = com.google.android.gms.internal.firebase_ml.zzwa.f(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase_ml.zzwa.f(r11, r6)
            boolean r4 = com.google.android.gms.internal.firebase_ml.zzve.a((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01b3
            goto L_0x0197
        L_0x0112:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            boolean r4 = com.google.android.gms.internal.firebase_ml.zzwa.c(r10, r6)
            boolean r5 = com.google.android.gms.internal.firebase_ml.zzwa.c(r11, r6)
            if (r4 == r5) goto L_0x01b3
            goto L_0x01b2
        L_0x0124:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            int r4 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r10, (long) r6)
            int r5 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r11, (long) r6)
            if (r4 == r5) goto L_0x01b3
            goto L_0x0197
        L_0x0135:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            long r4 = com.google.android.gms.internal.firebase_ml.zzwa.b(r10, r6)
            long r6 = com.google.android.gms.internal.firebase_ml.zzwa.b(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01b3
            goto L_0x01b2
        L_0x0148:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            int r4 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r10, (long) r6)
            int r5 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r11, (long) r6)
            if (r4 == r5) goto L_0x01b3
            goto L_0x0197
        L_0x0159:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            long r4 = com.google.android.gms.internal.firebase_ml.zzwa.b(r10, r6)
            long r6 = com.google.android.gms.internal.firebase_ml.zzwa.b(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01b3
            goto L_0x01b2
        L_0x016c:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            long r4 = com.google.android.gms.internal.firebase_ml.zzwa.b(r10, r6)
            long r6 = com.google.android.gms.internal.firebase_ml.zzwa.b(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01b3
            goto L_0x0197
        L_0x017f:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            float r4 = com.google.android.gms.internal.firebase_ml.zzwa.d(r10, r6)
            int r4 = java.lang.Float.floatToIntBits(r4)
            float r5 = com.google.android.gms.internal.firebase_ml.zzwa.d(r11, r6)
            int r5 = java.lang.Float.floatToIntBits(r5)
            if (r4 == r5) goto L_0x01b3
        L_0x0197:
            goto L_0x01b2
        L_0x0198:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            double r4 = com.google.android.gms.internal.firebase_ml.zzwa.e(r10, r6)
            long r4 = java.lang.Double.doubleToLongBits(r4)
            double r6 = com.google.android.gms.internal.firebase_ml.zzwa.e(r11, r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01b3
        L_0x01b2:
            r3 = 0
        L_0x01b3:
            if (r3 != 0) goto L_0x01b6
            return r1
        L_0x01b6:
            int r2 = r2 + 3
            goto L_0x0005
        L_0x01ba:
            com.google.android.gms.internal.firebase_ml.zzvu<?, ?> r0 = r9.zzbpg
            java.lang.Object r0 = r0.a(r10)
            com.google.android.gms.internal.firebase_ml.zzvu<?, ?> r2 = r9.zzbpg
            java.lang.Object r2 = r2.a(r11)
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x01cd
            return r1
        L_0x01cd:
            boolean r0 = r9.zzbox
            if (r0 == 0) goto L_0x01e2
            com.google.android.gms.internal.firebase_ml.zzsr<?> r0 = r9.zzbph
            com.google.android.gms.internal.firebase_ml.zzsu r10 = r0.a((java.lang.Object) r10)
            com.google.android.gms.internal.firebase_ml.zzsr<?> r0 = r9.zzbph
            com.google.android.gms.internal.firebase_ml.zzsu r11 = r0.a((java.lang.Object) r11)
            boolean r10 = r10.equals(r11)
            return r10
        L_0x01e2:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzuq.equals(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0061, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzwa.f(r9, r5);
        r2 = r2 * 53;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0093, code lost:
        r2 = r2 * 53;
        r3 = zzh(r9, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a8, code lost:
        r2 = r2 * 53;
        r3 = zzi(r9, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ce, code lost:
        if (r3 != null) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d1, code lost:
        r2 = r2 * 53;
        r3 = com.google.android.gms.internal.firebase_ml.zzwa.f(r9, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00d7, code lost:
        r3 = r3.hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e0, code lost:
        if (r3 != null) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e2, code lost:
        r7 = r3.hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e6, code lost:
        r2 = (r2 * 53) + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ea, code lost:
        r2 = r2 * 53;
        r3 = ((java.lang.String) com.google.android.gms.internal.firebase_ml.zzwa.f(r9, r5)).hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00fd, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzte.zzai(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0116, code lost:
        r3 = java.lang.Float.floatToIntBits(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0121, code lost:
        r3 = java.lang.Double.doubleToLongBits(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0125, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzte.zzw(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0129, code lost:
        r2 = r2 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x012a, code lost:
        r1 = r1 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int hashCode(T r9) {
        /*
            r8 = this;
            int[] r0 = r8.zzbos
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            if (r1 >= r0) goto L_0x012e
            int r3 = r8.zzdc(r1)
            int[] r4 = r8.zzbos
            r4 = r4[r1]
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r3
            long r5 = (long) r5
            r7 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = r3 & r7
            int r3 = r3 >>> 20
            r7 = 37
            switch(r3) {
                case 0: goto L_0x011b;
                case 1: goto L_0x0110;
                case 2: goto L_0x0109;
                case 3: goto L_0x0109;
                case 4: goto L_0x0102;
                case 5: goto L_0x0109;
                case 6: goto L_0x0102;
                case 7: goto L_0x00f7;
                case 8: goto L_0x00ea;
                case 9: goto L_0x00dc;
                case 10: goto L_0x00d1;
                case 11: goto L_0x0102;
                case 12: goto L_0x0102;
                case 13: goto L_0x0102;
                case 14: goto L_0x0109;
                case 15: goto L_0x0102;
                case 16: goto L_0x0109;
                case 17: goto L_0x00ca;
                case 18: goto L_0x00d1;
                case 19: goto L_0x00d1;
                case 20: goto L_0x00d1;
                case 21: goto L_0x00d1;
                case 22: goto L_0x00d1;
                case 23: goto L_0x00d1;
                case 24: goto L_0x00d1;
                case 25: goto L_0x00d1;
                case 26: goto L_0x00d1;
                case 27: goto L_0x00d1;
                case 28: goto L_0x00d1;
                case 29: goto L_0x00d1;
                case 30: goto L_0x00d1;
                case 31: goto L_0x00d1;
                case 32: goto L_0x00d1;
                case 33: goto L_0x00d1;
                case 34: goto L_0x00d1;
                case 35: goto L_0x00d1;
                case 36: goto L_0x00d1;
                case 37: goto L_0x00d1;
                case 38: goto L_0x00d1;
                case 39: goto L_0x00d1;
                case 40: goto L_0x00d1;
                case 41: goto L_0x00d1;
                case 42: goto L_0x00d1;
                case 43: goto L_0x00d1;
                case 44: goto L_0x00d1;
                case 45: goto L_0x00d1;
                case 46: goto L_0x00d1;
                case 47: goto L_0x00d1;
                case 48: goto L_0x00d1;
                case 49: goto L_0x00d1;
                case 50: goto L_0x00d1;
                case 51: goto L_0x00bd;
                case 52: goto L_0x00b0;
                case 53: goto L_0x00a2;
                case 54: goto L_0x009b;
                case 55: goto L_0x008d;
                case 56: goto L_0x0086;
                case 57: goto L_0x007f;
                case 58: goto L_0x0071;
                case 59: goto L_0x0069;
                case 60: goto L_0x005b;
                case 61: goto L_0x0053;
                case 62: goto L_0x004c;
                case 63: goto L_0x0045;
                case 64: goto L_0x003e;
                case 65: goto L_0x0036;
                case 66: goto L_0x002f;
                case 67: goto L_0x0027;
                case 68: goto L_0x0020;
                default: goto L_0x001e;
            }
        L_0x001e:
            goto L_0x012a
        L_0x0020:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x0061
        L_0x0027:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x00a8
        L_0x002f:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x004b
        L_0x0036:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x00a8
        L_0x003e:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x004b
        L_0x0045:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x012a
        L_0x004b:
            goto L_0x0093
        L_0x004c:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x0093
        L_0x0053:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x00d1
        L_0x005b:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x012a
        L_0x0061:
            java.lang.Object r3 = com.google.android.gms.internal.firebase_ml.zzwa.f(r9, r5)
            int r2 = r2 * 53
            goto L_0x00d7
        L_0x0069:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x00ea
        L_0x0071:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x012a
            int r2 = r2 * 53
            boolean r3 = zzj(r9, r5)
            goto L_0x00fd
        L_0x007f:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x0093
        L_0x0086:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x00a8
        L_0x008d:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x012a
        L_0x0093:
            int r2 = r2 * 53
            int r3 = zzh(r9, r5)
            goto L_0x0129
        L_0x009b:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x012a
            goto L_0x00a8
        L_0x00a2:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x012a
        L_0x00a8:
            int r2 = r2 * 53
            long r3 = zzi(r9, r5)
            goto L_0x0125
        L_0x00b0:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x012a
            int r2 = r2 * 53
            float r3 = zzg(r9, r5)
            goto L_0x0116
        L_0x00bd:
            boolean r3 = r8.zza(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x012a
            int r2 = r2 * 53
            double r3 = zzf(r9, r5)
            goto L_0x0121
        L_0x00ca:
            java.lang.Object r3 = com.google.android.gms.internal.firebase_ml.zzwa.f(r9, r5)
            if (r3 == 0) goto L_0x00e6
            goto L_0x00e2
        L_0x00d1:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.firebase_ml.zzwa.f(r9, r5)
        L_0x00d7:
            int r3 = r3.hashCode()
            goto L_0x0129
        L_0x00dc:
            java.lang.Object r3 = com.google.android.gms.internal.firebase_ml.zzwa.f(r9, r5)
            if (r3 == 0) goto L_0x00e6
        L_0x00e2:
            int r7 = r3.hashCode()
        L_0x00e6:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x012a
        L_0x00ea:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.firebase_ml.zzwa.f(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0129
        L_0x00f7:
            int r2 = r2 * 53
            boolean r3 = com.google.android.gms.internal.firebase_ml.zzwa.c(r9, r5)
        L_0x00fd:
            int r3 = com.google.android.gms.internal.firebase_ml.zzte.zzai(r3)
            goto L_0x0129
        L_0x0102:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r9, (long) r5)
            goto L_0x0129
        L_0x0109:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.firebase_ml.zzwa.b(r9, r5)
            goto L_0x0125
        L_0x0110:
            int r2 = r2 * 53
            float r3 = com.google.android.gms.internal.firebase_ml.zzwa.d(r9, r5)
        L_0x0116:
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0129
        L_0x011b:
            int r2 = r2 * 53
            double r3 = com.google.android.gms.internal.firebase_ml.zzwa.e(r9, r5)
        L_0x0121:
            long r3 = java.lang.Double.doubleToLongBits(r3)
        L_0x0125:
            int r3 = com.google.android.gms.internal.firebase_ml.zzte.zzw(r3)
        L_0x0129:
            int r2 = r2 + r3
        L_0x012a:
            int r1 = r1 + 3
            goto L_0x0005
        L_0x012e:
            int r2 = r2 * 53
            com.google.android.gms.internal.firebase_ml.zzvu<?, ?> r0 = r8.zzbpg
            java.lang.Object r0 = r0.a(r9)
            int r0 = r0.hashCode()
            int r2 = r2 + r0
            boolean r0 = r8.zzbox
            if (r0 == 0) goto L_0x014c
            int r2 = r2 * 53
            com.google.android.gms.internal.firebase_ml.zzsr<?> r0 = r8.zzbph
            com.google.android.gms.internal.firebase_ml.zzsu r9 = r0.a((java.lang.Object) r9)
            int r9 = r9.hashCode()
            int r2 = r2 + r9
        L_0x014c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzuq.hashCode(java.lang.Object):int");
    }

    public final T newInstance() {
        return this.zzbpe.newInstance(this.zzbow);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0385, code lost:
        r15.zzb(r9, com.google.android.gms.internal.firebase_ml.zzwa.f(r14, (long) (r8 & 1048575)), zzcz(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x03a0, code lost:
        r15.zzb(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x03b1, code lost:
        r15.zzg(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x03c2, code lost:
        r15.zzj(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x03d3, code lost:
        r15.zzo(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x03e4, code lost:
        r15.zzp(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x03f5, code lost:
        r15.zzf(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0400, code lost:
        r15.zza(r9, (com.google.android.gms.internal.firebase_ml.zzru) com.google.android.gms.internal.firebase_ml.zzwa.f(r14, (long) (r8 & 1048575)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0413, code lost:
        r15.zza(r9, com.google.android.gms.internal.firebase_ml.zzwa.f(r14, (long) (r8 & 1048575)), zzcz(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0428, code lost:
        zza(r9, com.google.android.gms.internal.firebase_ml.zzwa.f(r14, (long) (r8 & 1048575)), r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x043f, code lost:
        r15.zzb(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0450, code lost:
        r15.zzh(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0460, code lost:
        r15.zzc(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0470, code lost:
        r15.zze(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0480, code lost:
        r15.zza(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0490, code lost:
        r15.zzi(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x04a0, code lost:
        r15.zza(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x04b0, code lost:
        r15.zza(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:283:0x0843, code lost:
        r15.zzb(r10, com.google.android.gms.internal.firebase_ml.zzwa.f(r14, (long) (r9 & 1048575)), zzcz(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:287:0x085e, code lost:
        r15.zzb(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:291:0x086f, code lost:
        r15.zzg(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x0880, code lost:
        r15.zzj(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:299:0x0891, code lost:
        r15.zzo(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:303:0x08a2, code lost:
        r15.zzp(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x08b3, code lost:
        r15.zzf(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:310:0x08be, code lost:
        r15.zza(r10, (com.google.android.gms.internal.firebase_ml.zzru) com.google.android.gms.internal.firebase_ml.zzwa.f(r14, (long) (r9 & 1048575)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x08d1, code lost:
        r15.zza(r10, com.google.android.gms.internal.firebase_ml.zzwa.f(r14, (long) (r9 & 1048575)), zzcz(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:316:0x08e6, code lost:
        zza(r10, com.google.android.gms.internal.firebase_ml.zzwa.f(r14, (long) (r9 & 1048575)), r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:320:0x08fd, code lost:
        r15.zzb(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:0x090e, code lost:
        r15.zzh(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:328:0x091e, code lost:
        r15.zzc(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:332:0x092e, code lost:
        r15.zze(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:336:0x093e, code lost:
        r15.zza(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:340:0x094e, code lost:
        r15.zzi(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:344:0x095e, code lost:
        r15.zza(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x096e, code lost:
        r15.zza(r10, r11);
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x04b9  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x04f7  */
    /* JADX WARNING: Removed duplicated region for block: B:351:0x0977  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r14, com.google.android.gms.internal.firebase_ml.zzwp r15) {
        /*
            r13 = this;
            int r0 = r15.zzov()
            int r1 = com.google.android.gms.internal.firebase_ml.zztc.zzf.zzbmv
            r2 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 != r1) goto L_0x04cf
            com.google.android.gms.internal.firebase_ml.zzvu<?, ?> r0 = r13.zzbpg
            zza(r0, r14, (com.google.android.gms.internal.firebase_ml.zzwp) r15)
            boolean r0 = r13.zzbox
            if (r0 == 0) goto L_0x0030
            com.google.android.gms.internal.firebase_ml.zzsr<?> r0 = r13.zzbph
            com.google.android.gms.internal.firebase_ml.zzsu r0 = r0.a((java.lang.Object) r14)
            boolean r1 = r0.a()
            if (r1 != 0) goto L_0x0030
            java.util.Iterator r0 = r0.b()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0032
        L_0x0030:
            r0 = r3
            r1 = r0
        L_0x0032:
            int[] r7 = r13.zzbos
            int r7 = r7.length
            int r7 = r7 + -3
        L_0x0037:
            if (r7 < 0) goto L_0x04b7
            int r8 = r13.zzdc(r7)
            int[] r9 = r13.zzbos
            r9 = r9[r7]
        L_0x0041:
            if (r1 == 0) goto L_0x005f
            com.google.android.gms.internal.firebase_ml.zzsr<?> r10 = r13.zzbph
            int r10 = r10.a((java.util.Map.Entry<?, ?>) r1)
            if (r10 <= r9) goto L_0x005f
            com.google.android.gms.internal.firebase_ml.zzsr<?> r10 = r13.zzbph
            r10.a(r15, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x005d
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0041
        L_0x005d:
            r1 = r3
            goto L_0x0041
        L_0x005f:
            r10 = r8 & r2
            int r10 = r10 >>> 20
            switch(r10) {
                case 0: goto L_0x04a4;
                case 1: goto L_0x0494;
                case 2: goto L_0x0484;
                case 3: goto L_0x0474;
                case 4: goto L_0x0464;
                case 5: goto L_0x0454;
                case 6: goto L_0x0444;
                case 7: goto L_0x0433;
                case 8: goto L_0x0422;
                case 9: goto L_0x040d;
                case 10: goto L_0x03fa;
                case 11: goto L_0x03e9;
                case 12: goto L_0x03d8;
                case 13: goto L_0x03c7;
                case 14: goto L_0x03b6;
                case 15: goto L_0x03a5;
                case 16: goto L_0x0394;
                case 17: goto L_0x037f;
                case 18: goto L_0x036e;
                case 19: goto L_0x035d;
                case 20: goto L_0x034c;
                case 21: goto L_0x033b;
                case 22: goto L_0x032a;
                case 23: goto L_0x0319;
                case 24: goto L_0x0308;
                case 25: goto L_0x02f7;
                case 26: goto L_0x02e6;
                case 27: goto L_0x02d1;
                case 28: goto L_0x02c0;
                case 29: goto L_0x02af;
                case 30: goto L_0x029e;
                case 31: goto L_0x028d;
                case 32: goto L_0x027c;
                case 33: goto L_0x026b;
                case 34: goto L_0x025a;
                case 35: goto L_0x0249;
                case 36: goto L_0x0238;
                case 37: goto L_0x0227;
                case 38: goto L_0x0216;
                case 39: goto L_0x0205;
                case 40: goto L_0x01f4;
                case 41: goto L_0x01e3;
                case 42: goto L_0x01d2;
                case 43: goto L_0x01c1;
                case 44: goto L_0x01b0;
                case 45: goto L_0x019f;
                case 46: goto L_0x018e;
                case 47: goto L_0x017d;
                case 48: goto L_0x016c;
                case 49: goto L_0x0157;
                case 50: goto L_0x014c;
                case 51: goto L_0x013e;
                case 52: goto L_0x0130;
                case 53: goto L_0x0122;
                case 54: goto L_0x0114;
                case 55: goto L_0x0106;
                case 56: goto L_0x00f8;
                case 57: goto L_0x00ea;
                case 58: goto L_0x00dc;
                case 59: goto L_0x00d4;
                case 60: goto L_0x00cc;
                case 61: goto L_0x00c4;
                case 62: goto L_0x00b6;
                case 63: goto L_0x00a8;
                case 64: goto L_0x009a;
                case 65: goto L_0x008c;
                case 66: goto L_0x007e;
                case 67: goto L_0x0070;
                case 68: goto L_0x0068;
                default: goto L_0x0066;
            }
        L_0x0066:
            goto L_0x04b3
        L_0x0068:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b3
            goto L_0x0385
        L_0x0070:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            goto L_0x03a0
        L_0x007e:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            goto L_0x03b1
        L_0x008c:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            goto L_0x03c2
        L_0x009a:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            goto L_0x03d3
        L_0x00a8:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            goto L_0x03e4
        L_0x00b6:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            goto L_0x03f5
        L_0x00c4:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b3
            goto L_0x0400
        L_0x00cc:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b3
            goto L_0x0413
        L_0x00d4:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b3
            goto L_0x0428
        L_0x00dc:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = zzj(r14, r10)
            goto L_0x043f
        L_0x00ea:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            goto L_0x0450
        L_0x00f8:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            goto L_0x0460
        L_0x0106:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            goto L_0x0470
        L_0x0114:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            goto L_0x0480
        L_0x0122:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            goto L_0x0490
        L_0x0130:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = zzg(r14, r10)
            goto L_0x04a0
        L_0x013e:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = zzf(r14, r10)
            goto L_0x04b0
        L_0x014c:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            r13.zza((com.google.android.gms.internal.firebase_ml.zzwp) r15, (int) r9, (java.lang.Object) r8, (int) r7)
            goto L_0x04b3
        L_0x0157:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzvc r10 = r13.zzcz(r7)
            com.google.android.gms.internal.firebase_ml.zzve.zzb((int) r9, (java.util.List<?>) r8, (com.google.android.gms.internal.firebase_ml.zzwp) r15, (com.google.android.gms.internal.firebase_ml.zzvc) r10)
            goto L_0x04b3
        L_0x016c:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zze(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x017d:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzj(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x018e:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzg(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x019f:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzl(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01b0:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzm(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01c1:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzi(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01d2:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzn(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01e3:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzk(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x01f4:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzf(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0205:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzh(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0216:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzd(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0227:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzc(r9, r8, r15, r4)
            goto L_0x04b3
        L_0x0238:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzb((int) r9, (java.util.List<java.lang.Float>) r8, (com.google.android.gms.internal.firebase_ml.zzwp) r15, (boolean) r4)
            goto L_0x04b3
        L_0x0249:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zza((int) r9, (java.util.List<java.lang.Double>) r8, (com.google.android.gms.internal.firebase_ml.zzwp) r15, (boolean) r4)
            goto L_0x04b3
        L_0x025a:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zze(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x026b:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzj(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x027c:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzg(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x028d:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzl(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x029e:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzm(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x02af:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzi(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x02c0:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzb(r9, r8, r15)
            goto L_0x04b3
        L_0x02d1:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzvc r10 = r13.zzcz(r7)
            com.google.android.gms.internal.firebase_ml.zzve.zza((int) r9, (java.util.List<?>) r8, (com.google.android.gms.internal.firebase_ml.zzwp) r15, (com.google.android.gms.internal.firebase_ml.zzvc) r10)
            goto L_0x04b3
        L_0x02e6:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zza(r9, r8, r15)
            goto L_0x04b3
        L_0x02f7:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzn(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x0308:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzk(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x0319:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzf(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x032a:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzh(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x033b:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzd(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x034c:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzc(r9, r8, r15, r5)
            goto L_0x04b3
        L_0x035d:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zzb((int) r9, (java.util.List<java.lang.Float>) r8, (com.google.android.gms.internal.firebase_ml.zzwp) r15, (boolean) r5)
            goto L_0x04b3
        L_0x036e:
            int[] r9 = r13.zzbos
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_ml.zzve.zza((int) r9, (java.util.List<java.lang.Double>) r8, (com.google.android.gms.internal.firebase_ml.zzwp) r15, (boolean) r5)
            goto L_0x04b3
        L_0x037f:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b3
        L_0x0385:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            com.google.android.gms.internal.firebase_ml.zzvc r10 = r13.zzcz(r7)
            r15.zzb((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.firebase_ml.zzvc) r10)
            goto L_0x04b3
        L_0x0394:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.firebase_ml.zzwa.b(r14, r10)
        L_0x03a0:
            r15.zzb((int) r9, (long) r10)
            goto L_0x04b3
        L_0x03a5:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r14, (long) r10)
        L_0x03b1:
            r15.zzg(r9, r8)
            goto L_0x04b3
        L_0x03b6:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.firebase_ml.zzwa.b(r14, r10)
        L_0x03c2:
            r15.zzj(r9, r10)
            goto L_0x04b3
        L_0x03c7:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r14, (long) r10)
        L_0x03d3:
            r15.zzo(r9, r8)
            goto L_0x04b3
        L_0x03d8:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r14, (long) r10)
        L_0x03e4:
            r15.zzp(r9, r8)
            goto L_0x04b3
        L_0x03e9:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r14, (long) r10)
        L_0x03f5:
            r15.zzf(r9, r8)
            goto L_0x04b3
        L_0x03fa:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b3
        L_0x0400:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            com.google.android.gms.internal.firebase_ml.zzru r8 = (com.google.android.gms.internal.firebase_ml.zzru) r8
            r15.zza((int) r9, (com.google.android.gms.internal.firebase_ml.zzru) r8)
            goto L_0x04b3
        L_0x040d:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b3
        L_0x0413:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            com.google.android.gms.internal.firebase_ml.zzvc r10 = r13.zzcz(r7)
            r15.zza((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.firebase_ml.zzvc) r10)
            goto L_0x04b3
        L_0x0422:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b3
        L_0x0428:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r10)
            zza((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.firebase_ml.zzwp) r15)
            goto L_0x04b3
        L_0x0433:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = com.google.android.gms.internal.firebase_ml.zzwa.c(r14, r10)
        L_0x043f:
            r15.zzb((int) r9, (boolean) r8)
            goto L_0x04b3
        L_0x0444:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r14, (long) r10)
        L_0x0450:
            r15.zzh(r9, r8)
            goto L_0x04b3
        L_0x0454:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.firebase_ml.zzwa.b(r14, r10)
        L_0x0460:
            r15.zzc(r9, r10)
            goto L_0x04b3
        L_0x0464:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r14, (long) r10)
        L_0x0470:
            r15.zze(r9, r8)
            goto L_0x04b3
        L_0x0474:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.firebase_ml.zzwa.b(r14, r10)
        L_0x0480:
            r15.zza((int) r9, (long) r10)
            goto L_0x04b3
        L_0x0484:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.firebase_ml.zzwa.b(r14, r10)
        L_0x0490:
            r15.zzi(r9, r10)
            goto L_0x04b3
        L_0x0494:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = com.google.android.gms.internal.firebase_ml.zzwa.d(r14, r10)
        L_0x04a0:
            r15.zza((int) r9, (float) r8)
            goto L_0x04b3
        L_0x04a4:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b3
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = com.google.android.gms.internal.firebase_ml.zzwa.e(r14, r10)
        L_0x04b0:
            r15.zza((int) r9, (double) r10)
        L_0x04b3:
            int r7 = r7 + -3
            goto L_0x0037
        L_0x04b7:
            if (r1 == 0) goto L_0x04ce
            com.google.android.gms.internal.firebase_ml.zzsr<?> r14 = r13.zzbph
            r14.a(r15, r1)
            boolean r14 = r0.hasNext()
            if (r14 == 0) goto L_0x04cc
            java.lang.Object r14 = r0.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            r1 = r14
            goto L_0x04b7
        L_0x04cc:
            r1 = r3
            goto L_0x04b7
        L_0x04ce:
            return
        L_0x04cf:
            boolean r0 = r13.zzboz
            if (r0 == 0) goto L_0x0992
            boolean r0 = r13.zzbox
            if (r0 == 0) goto L_0x04ee
            com.google.android.gms.internal.firebase_ml.zzsr<?> r0 = r13.zzbph
            com.google.android.gms.internal.firebase_ml.zzsu r0 = r0.a((java.lang.Object) r14)
            boolean r1 = r0.a()
            if (r1 != 0) goto L_0x04ee
            java.util.Iterator r0 = r0.iterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x04f0
        L_0x04ee:
            r0 = r3
            r1 = r0
        L_0x04f0:
            int[] r7 = r13.zzbos
            int r7 = r7.length
            r8 = r1
            r1 = 0
        L_0x04f5:
            if (r1 >= r7) goto L_0x0975
            int r9 = r13.zzdc(r1)
            int[] r10 = r13.zzbos
            r10 = r10[r1]
        L_0x04ff:
            if (r8 == 0) goto L_0x051d
            com.google.android.gms.internal.firebase_ml.zzsr<?> r11 = r13.zzbph
            int r11 = r11.a((java.util.Map.Entry<?, ?>) r8)
            if (r11 > r10) goto L_0x051d
            com.google.android.gms.internal.firebase_ml.zzsr<?> r11 = r13.zzbph
            r11.a(r15, r8)
            boolean r8 = r0.hasNext()
            if (r8 == 0) goto L_0x051b
            java.lang.Object r8 = r0.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            goto L_0x04ff
        L_0x051b:
            r8 = r3
            goto L_0x04ff
        L_0x051d:
            r11 = r9 & r2
            int r11 = r11 >>> 20
            switch(r11) {
                case 0: goto L_0x0962;
                case 1: goto L_0x0952;
                case 2: goto L_0x0942;
                case 3: goto L_0x0932;
                case 4: goto L_0x0922;
                case 5: goto L_0x0912;
                case 6: goto L_0x0902;
                case 7: goto L_0x08f1;
                case 8: goto L_0x08e0;
                case 9: goto L_0x08cb;
                case 10: goto L_0x08b8;
                case 11: goto L_0x08a7;
                case 12: goto L_0x0896;
                case 13: goto L_0x0885;
                case 14: goto L_0x0874;
                case 15: goto L_0x0863;
                case 16: goto L_0x0852;
                case 17: goto L_0x083d;
                case 18: goto L_0x082c;
                case 19: goto L_0x081b;
                case 20: goto L_0x080a;
                case 21: goto L_0x07f9;
                case 22: goto L_0x07e8;
                case 23: goto L_0x07d7;
                case 24: goto L_0x07c6;
                case 25: goto L_0x07b5;
                case 26: goto L_0x07a4;
                case 27: goto L_0x078f;
                case 28: goto L_0x077e;
                case 29: goto L_0x076d;
                case 30: goto L_0x075c;
                case 31: goto L_0x074b;
                case 32: goto L_0x073a;
                case 33: goto L_0x0729;
                case 34: goto L_0x0718;
                case 35: goto L_0x0707;
                case 36: goto L_0x06f6;
                case 37: goto L_0x06e5;
                case 38: goto L_0x06d4;
                case 39: goto L_0x06c3;
                case 40: goto L_0x06b2;
                case 41: goto L_0x06a1;
                case 42: goto L_0x0690;
                case 43: goto L_0x067f;
                case 44: goto L_0x066e;
                case 45: goto L_0x065d;
                case 46: goto L_0x064c;
                case 47: goto L_0x063b;
                case 48: goto L_0x062a;
                case 49: goto L_0x0615;
                case 50: goto L_0x060a;
                case 51: goto L_0x05fc;
                case 52: goto L_0x05ee;
                case 53: goto L_0x05e0;
                case 54: goto L_0x05d2;
                case 55: goto L_0x05c4;
                case 56: goto L_0x05b6;
                case 57: goto L_0x05a8;
                case 58: goto L_0x059a;
                case 59: goto L_0x0592;
                case 60: goto L_0x058a;
                case 61: goto L_0x0582;
                case 62: goto L_0x0574;
                case 63: goto L_0x0566;
                case 64: goto L_0x0558;
                case 65: goto L_0x054a;
                case 66: goto L_0x053c;
                case 67: goto L_0x052e;
                case 68: goto L_0x0526;
                default: goto L_0x0524;
            }
        L_0x0524:
            goto L_0x0971
        L_0x0526:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0971
            goto L_0x0843
        L_0x052e:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            goto L_0x085e
        L_0x053c:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            goto L_0x086f
        L_0x054a:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            goto L_0x0880
        L_0x0558:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            goto L_0x0891
        L_0x0566:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            goto L_0x08a2
        L_0x0574:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            goto L_0x08b3
        L_0x0582:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0971
            goto L_0x08be
        L_0x058a:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0971
            goto L_0x08d1
        L_0x0592:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0971
            goto L_0x08e6
        L_0x059a:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = zzj(r14, r11)
            goto L_0x08fd
        L_0x05a8:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            goto L_0x090e
        L_0x05b6:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            goto L_0x091e
        L_0x05c4:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            goto L_0x092e
        L_0x05d2:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            goto L_0x093e
        L_0x05e0:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            goto L_0x094e
        L_0x05ee:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = zzg(r14, r11)
            goto L_0x095e
        L_0x05fc:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = zzf(r14, r11)
            goto L_0x096e
        L_0x060a:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            r13.zza((com.google.android.gms.internal.firebase_ml.zzwp) r15, (int) r10, (java.lang.Object) r9, (int) r1)
            goto L_0x0971
        L_0x0615:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzvc r11 = r13.zzcz(r1)
            com.google.android.gms.internal.firebase_ml.zzve.zzb((int) r10, (java.util.List<?>) r9, (com.google.android.gms.internal.firebase_ml.zzwp) r15, (com.google.android.gms.internal.firebase_ml.zzvc) r11)
            goto L_0x0971
        L_0x062a:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zze(r10, r9, r15, r4)
            goto L_0x0971
        L_0x063b:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzj(r10, r9, r15, r4)
            goto L_0x0971
        L_0x064c:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzg(r10, r9, r15, r4)
            goto L_0x0971
        L_0x065d:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzl(r10, r9, r15, r4)
            goto L_0x0971
        L_0x066e:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzm(r10, r9, r15, r4)
            goto L_0x0971
        L_0x067f:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzi(r10, r9, r15, r4)
            goto L_0x0971
        L_0x0690:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzn(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06a1:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzk(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06b2:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzf(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06c3:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzh(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06d4:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzd(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06e5:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzc(r10, r9, r15, r4)
            goto L_0x0971
        L_0x06f6:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzb((int) r10, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.firebase_ml.zzwp) r15, (boolean) r4)
            goto L_0x0971
        L_0x0707:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zza((int) r10, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.firebase_ml.zzwp) r15, (boolean) r4)
            goto L_0x0971
        L_0x0718:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zze(r10, r9, r15, r5)
            goto L_0x0971
        L_0x0729:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzj(r10, r9, r15, r5)
            goto L_0x0971
        L_0x073a:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzg(r10, r9, r15, r5)
            goto L_0x0971
        L_0x074b:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzl(r10, r9, r15, r5)
            goto L_0x0971
        L_0x075c:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzm(r10, r9, r15, r5)
            goto L_0x0971
        L_0x076d:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzi(r10, r9, r15, r5)
            goto L_0x0971
        L_0x077e:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzb(r10, r9, r15)
            goto L_0x0971
        L_0x078f:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzvc r11 = r13.zzcz(r1)
            com.google.android.gms.internal.firebase_ml.zzve.zza((int) r10, (java.util.List<?>) r9, (com.google.android.gms.internal.firebase_ml.zzwp) r15, (com.google.android.gms.internal.firebase_ml.zzvc) r11)
            goto L_0x0971
        L_0x07a4:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zza(r10, r9, r15)
            goto L_0x0971
        L_0x07b5:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzn(r10, r9, r15, r5)
            goto L_0x0971
        L_0x07c6:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzk(r10, r9, r15, r5)
            goto L_0x0971
        L_0x07d7:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzf(r10, r9, r15, r5)
            goto L_0x0971
        L_0x07e8:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzh(r10, r9, r15, r5)
            goto L_0x0971
        L_0x07f9:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzd(r10, r9, r15, r5)
            goto L_0x0971
        L_0x080a:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzc(r10, r9, r15, r5)
            goto L_0x0971
        L_0x081b:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zzb((int) r10, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.firebase_ml.zzwp) r15, (boolean) r5)
            goto L_0x0971
        L_0x082c:
            int[] r10 = r13.zzbos
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_ml.zzve.zza((int) r10, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.firebase_ml.zzwp) r15, (boolean) r5)
            goto L_0x0971
        L_0x083d:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0971
        L_0x0843:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            com.google.android.gms.internal.firebase_ml.zzvc r11 = r13.zzcz(r1)
            r15.zzb((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.firebase_ml.zzvc) r11)
            goto L_0x0971
        L_0x0852:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.firebase_ml.zzwa.b(r14, r11)
        L_0x085e:
            r15.zzb((int) r10, (long) r11)
            goto L_0x0971
        L_0x0863:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r14, (long) r11)
        L_0x086f:
            r15.zzg(r10, r9)
            goto L_0x0971
        L_0x0874:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.firebase_ml.zzwa.b(r14, r11)
        L_0x0880:
            r15.zzj(r10, r11)
            goto L_0x0971
        L_0x0885:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r14, (long) r11)
        L_0x0891:
            r15.zzo(r10, r9)
            goto L_0x0971
        L_0x0896:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r14, (long) r11)
        L_0x08a2:
            r15.zzp(r10, r9)
            goto L_0x0971
        L_0x08a7:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r14, (long) r11)
        L_0x08b3:
            r15.zzf(r10, r9)
            goto L_0x0971
        L_0x08b8:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0971
        L_0x08be:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            com.google.android.gms.internal.firebase_ml.zzru r9 = (com.google.android.gms.internal.firebase_ml.zzru) r9
            r15.zza((int) r10, (com.google.android.gms.internal.firebase_ml.zzru) r9)
            goto L_0x0971
        L_0x08cb:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0971
        L_0x08d1:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            com.google.android.gms.internal.firebase_ml.zzvc r11 = r13.zzcz(r1)
            r15.zza((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.firebase_ml.zzvc) r11)
            goto L_0x0971
        L_0x08e0:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0971
        L_0x08e6:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r11)
            zza((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.firebase_ml.zzwp) r15)
            goto L_0x0971
        L_0x08f1:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = com.google.android.gms.internal.firebase_ml.zzwa.c(r14, r11)
        L_0x08fd:
            r15.zzb((int) r10, (boolean) r9)
            goto L_0x0971
        L_0x0902:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r14, (long) r11)
        L_0x090e:
            r15.zzh(r10, r9)
            goto L_0x0971
        L_0x0912:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.firebase_ml.zzwa.b(r14, r11)
        L_0x091e:
            r15.zzc(r10, r11)
            goto L_0x0971
        L_0x0922:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r14, (long) r11)
        L_0x092e:
            r15.zze(r10, r9)
            goto L_0x0971
        L_0x0932:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.firebase_ml.zzwa.b(r14, r11)
        L_0x093e:
            r15.zza((int) r10, (long) r11)
            goto L_0x0971
        L_0x0942:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.firebase_ml.zzwa.b(r14, r11)
        L_0x094e:
            r15.zzi(r10, r11)
            goto L_0x0971
        L_0x0952:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = com.google.android.gms.internal.firebase_ml.zzwa.d(r14, r11)
        L_0x095e:
            r15.zza((int) r10, (float) r9)
            goto L_0x0971
        L_0x0962:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0971
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = com.google.android.gms.internal.firebase_ml.zzwa.e(r14, r11)
        L_0x096e:
            r15.zza((int) r10, (double) r11)
        L_0x0971:
            int r1 = r1 + 3
            goto L_0x04f5
        L_0x0975:
            if (r8 == 0) goto L_0x098c
            com.google.android.gms.internal.firebase_ml.zzsr<?> r1 = r13.zzbph
            r1.a(r15, r8)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x098a
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            r8 = r1
            goto L_0x0975
        L_0x098a:
            r8 = r3
            goto L_0x0975
        L_0x098c:
            com.google.android.gms.internal.firebase_ml.zzvu<?, ?> r0 = r13.zzbpg
            zza(r0, r14, (com.google.android.gms.internal.firebase_ml.zzwp) r15)
            return
        L_0x0992:
            r13.zzb(r14, (com.google.android.gms.internal.firebase_ml.zzwp) r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzuq.zza(java.lang.Object, com.google.android.gms.internal.firebase_ml.zzwp):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: byte} */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0090, code lost:
        if (r6 == 0) goto L_0x0109;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x009b, code lost:
        r1 = r11.zzbic;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x009d, code lost:
        r9.putObject(r14, r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f1, code lost:
        r2 = r4;
        r1 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0107, code lost:
        if (r6 == 0) goto L_0x0109;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0109, code lost:
        r0 = com.google.android.gms.internal.firebase_ml.zzrq.a(r12, r8, r11);
        r1 = r11.zzbia;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x010f, code lost:
        r9.putInt(r14, r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0120, code lost:
        r0.putLong(r1, r2, r4);
        r0 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x013f, code lost:
        r0 = r8 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0141, code lost:
        r1 = r7;
        r2 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0146, code lost:
        r24 = r7;
        r15 = r8;
        r18 = r9;
        r19 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01c8, code lost:
        if (r0 == r15) goto L_0x0216;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x01f5, code lost:
        if (r0 == r15) goto L_0x0216;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0214, code lost:
        if (r0 == r15) goto L_0x0216;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r28, byte[] r29, int r30, int r31, com.google.android.gms.internal.firebase_ml.zzrr r32) {
        /*
            r27 = this;
            r15 = r27
            r14 = r28
            r12 = r29
            r13 = r31
            r11 = r32
            boolean r0 = r15.zzboz
            if (r0 == 0) goto L_0x0243
            sun.misc.Unsafe r9 = zzbor
            r10 = -1
            r16 = 0
            r0 = r30
            r1 = -1
            r2 = 0
        L_0x0017:
            if (r0 >= r13) goto L_0x023a
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0029
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.a((int) r0, (byte[]) r12, (int) r3, (com.google.android.gms.internal.firebase_ml.zzrr) r11)
            int r3 = r11.zzbia
            r8 = r0
            r17 = r3
            goto L_0x002c
        L_0x0029:
            r17 = r0
            r8 = r3
        L_0x002c:
            int r7 = r17 >>> 3
            r6 = r17 & 7
            if (r7 <= r1) goto L_0x0039
            int r2 = r2 / 3
            int r0 = r15.zzr(r7, r2)
            goto L_0x003d
        L_0x0039:
            int r0 = r15.zzde(r7)
        L_0x003d:
            r4 = r0
            if (r4 != r10) goto L_0x004b
            r24 = r7
            r2 = r8
            r18 = r9
            r19 = 0
            r26 = -1
            goto L_0x0217
        L_0x004b:
            int[] r0 = r15.zzbos
            int r1 = r4 + 1
            r5 = r0[r1]
            r0 = 267386880(0xff00000, float:2.3665827E-29)
            r0 = r0 & r5
            int r3 = r0 >>> 20
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            long r1 = (long) r0
            r0 = 17
            r10 = 2
            if (r3 > r0) goto L_0x014e
            r0 = 1
            switch(r3) {
                case 0: goto L_0x0134;
                case 1: goto L_0x0125;
                case 2: goto L_0x0113;
                case 3: goto L_0x0113;
                case 4: goto L_0x0105;
                case 5: goto L_0x00f5;
                case 6: goto L_0x00e4;
                case 7: goto L_0x00ce;
                case 8: goto L_0x00bc;
                case 9: goto L_0x00a1;
                case 10: goto L_0x0094;
                case 11: goto L_0x0105;
                case 12: goto L_0x008e;
                case 13: goto L_0x00e4;
                case 14: goto L_0x00f5;
                case 15: goto L_0x007e;
                case 16: goto L_0x0066;
                default: goto L_0x0064;
            }
        L_0x0064:
            goto L_0x018a
        L_0x0066:
            if (r6 != 0) goto L_0x018a
            int r6 = com.google.android.gms.internal.firebase_ml.zzrq.b(r12, r8, r11)
            r19 = r1
            long r0 = r11.zzbib
            long r21 = com.google.android.gms.internal.firebase_ml.zzsg.zzm(r0)
            r0 = r9
            r2 = r19
            r1 = r28
            r10 = r4
            r4 = r21
            goto L_0x0120
        L_0x007e:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x0146
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.a(r12, r8, r11)
            int r1 = r11.zzbia
            int r1 = com.google.android.gms.internal.firebase_ml.zzsg.zzcg(r1)
            goto L_0x010f
        L_0x008e:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x0146
            goto L_0x0109
        L_0x0094:
            r2 = r1
            if (r6 != r10) goto L_0x018a
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.e(r12, r8, r11)
        L_0x009b:
            java.lang.Object r1 = r11.zzbic
        L_0x009d:
            r9.putObject(r14, r2, r1)
            goto L_0x00f1
        L_0x00a1:
            r2 = r1
            if (r6 != r10) goto L_0x018a
            com.google.android.gms.internal.firebase_ml.zzvc r0 = r15.zzcz(r4)
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.a((com.google.android.gms.internal.firebase_ml.zzvc) r0, (byte[]) r12, (int) r8, (int) r13, (com.google.android.gms.internal.firebase_ml.zzrr) r11)
            java.lang.Object r1 = r9.getObject(r14, r2)
            if (r1 != 0) goto L_0x00b5
            java.lang.Object r1 = r11.zzbic
            goto L_0x009d
        L_0x00b5:
            java.lang.Object r5 = r11.zzbic
            java.lang.Object r1 = com.google.android.gms.internal.firebase_ml.zzte.a((java.lang.Object) r1, (java.lang.Object) r5)
            goto L_0x009d
        L_0x00bc:
            r2 = r1
            if (r6 != r10) goto L_0x018a
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r5
            if (r0 != 0) goto L_0x00c9
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.c(r12, r8, r11)
            goto L_0x009b
        L_0x00c9:
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.d(r12, r8, r11)
            goto L_0x009b
        L_0x00ce:
            r2 = r1
            if (r6 != 0) goto L_0x018a
            int r1 = com.google.android.gms.internal.firebase_ml.zzrq.b(r12, r8, r11)
            long r5 = r11.zzbib
            r19 = 0
            int r8 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1))
            if (r8 == 0) goto L_0x00de
            goto L_0x00df
        L_0x00de:
            r0 = 0
        L_0x00df:
            com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r14, (long) r2, (boolean) r0)
            r0 = r1
            goto L_0x00f1
        L_0x00e4:
            r2 = r1
            r0 = 5
            if (r6 != r0) goto L_0x018a
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.a(r12, r8)
            r9.putInt(r14, r2, r0)
            int r0 = r8 + 4
        L_0x00f1:
            r2 = r4
            r1 = r7
            goto L_0x0143
        L_0x00f5:
            r2 = r1
            if (r6 != r0) goto L_0x018a
            long r5 = com.google.android.gms.internal.firebase_ml.zzrq.b(r12, r8)
            r0 = r9
            r1 = r28
            r10 = r4
            r4 = r5
            r0.putLong(r1, r2, r4)
            goto L_0x013f
        L_0x0105:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x0146
        L_0x0109:
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.a(r12, r8, r11)
            int r1 = r11.zzbia
        L_0x010f:
            r9.putInt(r14, r2, r1)
            goto L_0x0141
        L_0x0113:
            r2 = r1
            r10 = r4
            if (r6 != 0) goto L_0x0146
            int r6 = com.google.android.gms.internal.firebase_ml.zzrq.b(r12, r8, r11)
            long r4 = r11.zzbib
            r0 = r9
            r1 = r28
        L_0x0120:
            r0.putLong(r1, r2, r4)
            r0 = r6
            goto L_0x0141
        L_0x0125:
            r2 = r1
            r10 = r4
            r0 = 5
            if (r6 != r0) goto L_0x0146
            float r0 = com.google.android.gms.internal.firebase_ml.zzrq.d(r12, r8)
            com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r14, (long) r2, (float) r0)
            int r0 = r8 + 4
            goto L_0x0141
        L_0x0134:
            r2 = r1
            r10 = r4
            if (r6 != r0) goto L_0x0146
            double r0 = com.google.android.gms.internal.firebase_ml.zzrq.c(r12, r8)
            com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r14, (long) r2, (double) r0)
        L_0x013f:
            int r0 = r8 + 8
        L_0x0141:
            r1 = r7
            r2 = r10
        L_0x0143:
            r10 = -1
            goto L_0x0017
        L_0x0146:
            r24 = r7
            r15 = r8
            r18 = r9
            r19 = r10
            goto L_0x0191
        L_0x014e:
            r0 = 27
            if (r3 != r0) goto L_0x0195
            if (r6 != r10) goto L_0x018a
            java.lang.Object r0 = r9.getObject(r14, r1)
            com.google.android.gms.internal.firebase_ml.zztl r0 = (com.google.android.gms.internal.firebase_ml.zztl) r0
            boolean r3 = r0.zzog()
            if (r3 != 0) goto L_0x0172
            int r3 = r0.size()
            if (r3 != 0) goto L_0x0169
            r3 = 10
            goto L_0x016b
        L_0x0169:
            int r3 = r3 << 1
        L_0x016b:
            com.google.android.gms.internal.firebase_ml.zztl r0 = r0.zzcb(r3)
            r9.putObject(r14, r1, r0)
        L_0x0172:
            r5 = r0
            com.google.android.gms.internal.firebase_ml.zzvc r0 = r15.zzcz(r4)
            r1 = r17
            r2 = r29
            r3 = r8
            r19 = r4
            r4 = r31
            r6 = r32
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.a(r0, r1, r2, r3, r4, r5, r6)
            r1 = r7
            r2 = r19
            goto L_0x0143
        L_0x018a:
            r19 = r4
            r24 = r7
            r15 = r8
            r18 = r9
        L_0x0191:
            r26 = -1
            goto L_0x01f8
        L_0x0195:
            r19 = r4
            r0 = 49
            if (r3 > r0) goto L_0x01cb
            long r4 = (long) r5
            r0 = r27
            r20 = r1
            r1 = r28
            r2 = r29
            r10 = r3
            r3 = r8
            r22 = r4
            r4 = r31
            r5 = r17
            r30 = r6
            r6 = r7
            r24 = r7
            r7 = r30
            r15 = r8
            r8 = r19
            r18 = r9
            r25 = r10
            r26 = -1
            r9 = r22
            r11 = r25
            r12 = r20
            r14 = r32
            int r0 = r0.zza(r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (int) r6, (int) r7, (int) r8, (long) r9, (int) r11, (long) r12, (com.google.android.gms.internal.firebase_ml.zzrr) r14)
            if (r0 != r15) goto L_0x0227
            goto L_0x0216
        L_0x01cb:
            r20 = r1
            r25 = r3
            r30 = r6
            r24 = r7
            r15 = r8
            r18 = r9
            r26 = -1
            r0 = 50
            r9 = r25
            if (r9 != r0) goto L_0x01fa
            r7 = r30
            if (r7 != r10) goto L_0x01f8
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r15
            r4 = r31
            r5 = r19
            r6 = r20
            r8 = r32
            int r0 = r0.zza(r1, r2, r3, r4, r5, r6, r8)
            if (r0 != r15) goto L_0x0227
            goto L_0x0216
        L_0x01f8:
            r2 = r15
            goto L_0x0217
        L_0x01fa:
            r7 = r30
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r15
            r4 = r31
            r8 = r5
            r5 = r17
            r6 = r24
            r10 = r20
            r12 = r19
            r13 = r32
            int r0 = r0.zza(r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (int) r6, (int) r7, (int) r8, (int) r9, (long) r10, (int) r12, (com.google.android.gms.internal.firebase_ml.zzrr) r13)
            if (r0 != r15) goto L_0x0227
        L_0x0216:
            r2 = r0
        L_0x0217:
            com.google.android.gms.internal.firebase_ml.zzvv r4 = zzab(r28)
            r0 = r17
            r1 = r29
            r3 = r31
            r5 = r32
            int r0 = com.google.android.gms.internal.firebase_ml.zzrq.a((int) r0, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.firebase_ml.zzvv) r4, (com.google.android.gms.internal.firebase_ml.zzrr) r5)
        L_0x0227:
            r14 = r28
            r12 = r29
            r11 = r32
            r9 = r18
            r2 = r19
            r1 = r24
            r10 = -1
            r13 = r31
            r15 = r27
            goto L_0x0017
        L_0x023a:
            r4 = r13
            if (r0 != r4) goto L_0x023e
            return
        L_0x023e:
            com.google.android.gms.internal.firebase_ml.zztm r0 = com.google.android.gms.internal.firebase_ml.zztm.e()
            throw r0
        L_0x0243:
            r4 = r13
            r5 = 0
            r0 = r27
            r1 = r28
            r2 = r29
            r3 = r30
            r4 = r31
            r6 = r32
            r0.a(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzuq.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.firebase_ml.zzrr):void");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01d8, code lost:
        if (r0.zzbpa != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01e9, code lost:
        if (r0.zzbpa != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01fa, code lost:
        if (r0.zzbpa != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x020b, code lost:
        if (r0.zzbpa != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x020d, code lost:
        r2.putInt(r1, (long) r14, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0211, code lost:
        r3 = (com.google.android.gms.internal.firebase_ml.zzsj.zzcl(r3) + com.google.android.gms.internal.firebase_ml.zzsj.zzcn(r5)) + r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0296, code lost:
        r13 = r13 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x029f, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzsj.c(r3, (com.google.android.gms.internal.firebase_ml.zzum) com.google.android.gms.internal.firebase_ml.zzwa.f(r1, r5), zzcz(r12));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x02b8, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzf(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x02c7, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzk(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x02d2, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzh(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x02dd, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzm(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x02ec, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzn(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x02fb, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzj(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0306, code lost:
        r5 = com.google.android.gms.internal.firebase_ml.zzwa.f(r1, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x030a, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzc(r3, (com.google.android.gms.internal.firebase_ml.zzru) r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0317, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzve.a(r3, com.google.android.gms.internal.firebase_ml.zzwa.f(r1, r5), zzcz(r12));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0331, code lost:
        if ((r5 instanceof com.google.android.gms.internal.firebase_ml.zzru) != false) goto L_0x030a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0334, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzc(r3, (java.lang.String) r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0342, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzc(r3, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x034e, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzl(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x035a, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzg(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x036a, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzi(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x037a, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzsj.zze(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x038a, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzd(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x0396, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzb(r3, 0.0f);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x03a2, code lost:
        r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzb(r3, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x03aa, code lost:
        r12 = r12 + 3;
        r3 = 267386880;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x0417, code lost:
        if (zza(r1, r15, r3) != false) goto L_0x06b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x0437, code lost:
        if (zza(r1, r15, r3) != false) goto L_0x06e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x043f, code lost:
        if (zza(r1, r15, r3) != false) goto L_0x06f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x045f, code lost:
        if (zza(r1, r15, r3) != false) goto L_0x0716;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x0467, code lost:
        if (zza(r1, r15, r3) != false) goto L_0x0725;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x0477, code lost:
        if ((r4 instanceof com.google.android.gms.internal.firebase_ml.zzru) != false) goto L_0x071a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x047f, code lost:
        if (zza(r1, r15, r3) != false) goto L_0x074c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:273:0x0517, code lost:
        if (r0.zzbpa != false) goto L_0x05fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:277:0x0529, code lost:
        if (r0.zzbpa != false) goto L_0x05fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:281:0x053b, code lost:
        if (r0.zzbpa != false) goto L_0x05fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:285:0x054d, code lost:
        if (r0.zzbpa != false) goto L_0x05fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:289:0x055f, code lost:
        if (r0.zzbpa != false) goto L_0x05fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:293:0x0571, code lost:
        if (r0.zzbpa != false) goto L_0x05fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:297:0x0583, code lost:
        if (r0.zzbpa != false) goto L_0x05fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:301:0x0595, code lost:
        if (r0.zzbpa != false) goto L_0x05fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:305:0x05a6, code lost:
        if (r0.zzbpa != false) goto L_0x05fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:309:0x05b7, code lost:
        if (r0.zzbpa != false) goto L_0x05fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x05c8, code lost:
        if (r0.zzbpa != false) goto L_0x05fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:317:0x05d9, code lost:
        if (r0.zzbpa != false) goto L_0x05fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:321:0x05ea, code lost:
        if (r0.zzbpa != false) goto L_0x05fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:325:0x05fb, code lost:
        if (r0.zzbpa != false) goto L_0x05fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:326:0x05fd, code lost:
        r2.putInt(r1, (long) r11, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:327:0x0601, code lost:
        r9 = (com.google.android.gms.internal.firebase_ml.zzsj.zzcl(r15) + com.google.android.gms.internal.firebase_ml.zzsj.zzcn(r4)) + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:341:0x06ac, code lost:
        r5 = r5 + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:343:0x06ae, code lost:
        r13 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:345:0x06b7, code lost:
        if ((r12 & r18) != 0) goto L_0x06b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:346:0x06b9, code lost:
        r4 = com.google.android.gms.internal.firebase_ml.zzsj.c(r15, (com.google.android.gms.internal.firebase_ml.zzum) r2.getObject(r1, r9), zzcz(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:350:0x06d0, code lost:
        r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzf(r15, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:354:0x06dd, code lost:
        r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzk(r15, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:356:0x06e4, code lost:
        if ((r12 & r18) != 0) goto L_0x06e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:357:0x06e6, code lost:
        r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzh(r15, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:0x06ef, code lost:
        if ((r12 & r18) != 0) goto L_0x06f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:360:0x06f1, code lost:
        r9 = com.google.android.gms.internal.firebase_ml.zzsj.zzm(r15, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:361:0x06f6, code lost:
        r5 = r5 + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:365:0x0700, code lost:
        r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzn(r15, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:369:0x070d, code lost:
        r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzj(r15, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:371:0x0714, code lost:
        if ((r12 & r18) != 0) goto L_0x0716;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:372:0x0716, code lost:
        r4 = r2.getObject(r1, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:373:0x071a, code lost:
        r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzc(r15, (com.google.android.gms.internal.firebase_ml.zzru) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:375:0x0723, code lost:
        if ((r12 & r18) != 0) goto L_0x0725;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:376:0x0725, code lost:
        r4 = com.google.android.gms.internal.firebase_ml.zzve.a(r15, r2.getObject(r1, r9), zzcz(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ab, code lost:
        if ((r5 instanceof com.google.android.gms.internal.firebase_ml.zzru) != false) goto L_0x030a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:380:0x073d, code lost:
        if ((r4 instanceof com.google.android.gms.internal.firebase_ml.zzru) != false) goto L_0x071a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:381:0x0740, code lost:
        r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzc(r15, (java.lang.String) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:383:0x074a, code lost:
        if ((r12 & r18) != 0) goto L_0x074c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:384:0x074c, code lost:
        r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzc(r15, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:401:0x079c, code lost:
        r5 = r5 + r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:410:0x07be, code lost:
        r3 = r3 + 3;
        r9 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0127, code lost:
        if (r0.zzbpa != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0139, code lost:
        if (r0.zzbpa != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x014b, code lost:
        if (r0.zzbpa != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x015d, code lost:
        if (r0.zzbpa != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x016f, code lost:
        if (r0.zzbpa != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0181, code lost:
        if (r0.zzbpa != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0193, code lost:
        if (r0.zzbpa != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01a5, code lost:
        if (r0.zzbpa != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01b6, code lost:
        if (r0.zzbpa != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01c7, code lost:
        if (r0.zzbpa != false) goto L_0x020d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzaa(T r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            boolean r2 = r0.zzboz
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = 0
            r7 = 1
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r9 = 0
            r11 = 0
            if (r2 == 0) goto L_0x03b8
            sun.misc.Unsafe r2 = zzbor
            r12 = 0
            r13 = 0
        L_0x0016:
            int[] r14 = r0.zzbos
            int r14 = r14.length
            if (r12 >= r14) goto L_0x03b0
            int r14 = r0.zzdc(r12)
            r15 = r14 & r3
            int r15 = r15 >>> 20
            int[] r3 = r0.zzbos
            r3 = r3[r12]
            r14 = r14 & r8
            long r5 = (long) r14
            com.google.android.gms.internal.firebase_ml.zzsx r14 = com.google.android.gms.internal.firebase_ml.zzsx.DOUBLE_LIST_PACKED
            int r14 = r14.id()
            if (r15 < r14) goto L_0x0041
            com.google.android.gms.internal.firebase_ml.zzsx r14 = com.google.android.gms.internal.firebase_ml.zzsx.SINT64_LIST_PACKED
            int r14 = r14.id()
            if (r15 > r14) goto L_0x0041
            int[] r14 = r0.zzbos
            int r17 = r12 + 2
            r14 = r14[r17]
            r14 = r14 & r8
            goto L_0x0042
        L_0x0041:
            r14 = 0
        L_0x0042:
            switch(r15) {
                case 0: goto L_0x039c;
                case 1: goto L_0x0390;
                case 2: goto L_0x0380;
                case 3: goto L_0x0370;
                case 4: goto L_0x0360;
                case 5: goto L_0x0354;
                case 6: goto L_0x0348;
                case 7: goto L_0x033c;
                case 8: goto L_0x0325;
                case 9: goto L_0x0311;
                case 10: goto L_0x0300;
                case 11: goto L_0x02f1;
                case 12: goto L_0x02e2;
                case 13: goto L_0x02d7;
                case 14: goto L_0x02cc;
                case 15: goto L_0x02bd;
                case 16: goto L_0x02ae;
                case 17: goto L_0x0299;
                case 18: goto L_0x028e;
                case 19: goto L_0x0285;
                case 20: goto L_0x027c;
                case 21: goto L_0x0273;
                case 22: goto L_0x026a;
                case 23: goto L_0x028e;
                case 24: goto L_0x0285;
                case 25: goto L_0x0261;
                case 26: goto L_0x0258;
                case 27: goto L_0x024b;
                case 28: goto L_0x0242;
                case 29: goto L_0x0239;
                case 30: goto L_0x0230;
                case 31: goto L_0x0285;
                case 32: goto L_0x028e;
                case 33: goto L_0x0227;
                case 34: goto L_0x021d;
                case 35: goto L_0x01fd;
                case 36: goto L_0x01ec;
                case 37: goto L_0x01db;
                case 38: goto L_0x01ca;
                case 39: goto L_0x01b9;
                case 40: goto L_0x01a8;
                case 41: goto L_0x0197;
                case 42: goto L_0x0185;
                case 43: goto L_0x0173;
                case 44: goto L_0x0161;
                case 45: goto L_0x014f;
                case 46: goto L_0x013d;
                case 47: goto L_0x012b;
                case 48: goto L_0x0119;
                case 49: goto L_0x010b;
                case 50: goto L_0x00fb;
                case 51: goto L_0x00f3;
                case 52: goto L_0x00eb;
                case 53: goto L_0x00df;
                case 54: goto L_0x00d3;
                case 55: goto L_0x00c7;
                case 56: goto L_0x00bf;
                case 57: goto L_0x00b7;
                case 58: goto L_0x00af;
                case 59: goto L_0x009f;
                case 60: goto L_0x0097;
                case 61: goto L_0x008f;
                case 62: goto L_0x0083;
                case 63: goto L_0x0077;
                case 64: goto L_0x006f;
                case 65: goto L_0x0067;
                case 66: goto L_0x005b;
                case 67: goto L_0x004f;
                case 68: goto L_0x0047;
                default: goto L_0x0045;
            }
        L_0x0045:
            goto L_0x03aa
        L_0x0047:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x03aa
            goto L_0x029f
        L_0x004f:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x03aa
            long r5 = zzi(r1, r5)
            goto L_0x02b8
        L_0x005b:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x03aa
            int r5 = zzh(r1, r5)
            goto L_0x02c7
        L_0x0067:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x03aa
            goto L_0x02d2
        L_0x006f:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x03aa
            goto L_0x02dd
        L_0x0077:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x03aa
            int r5 = zzh(r1, r5)
            goto L_0x02ec
        L_0x0083:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x03aa
            int r5 = zzh(r1, r5)
            goto L_0x02fb
        L_0x008f:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x03aa
            goto L_0x0306
        L_0x0097:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x03aa
            goto L_0x0317
        L_0x009f:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x03aa
            java.lang.Object r5 = com.google.android.gms.internal.firebase_ml.zzwa.f(r1, r5)
            boolean r6 = r5 instanceof com.google.android.gms.internal.firebase_ml.zzru
            if (r6 == 0) goto L_0x0334
            goto L_0x0333
        L_0x00af:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x03aa
            goto L_0x0342
        L_0x00b7:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x03aa
            goto L_0x034e
        L_0x00bf:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x03aa
            goto L_0x035a
        L_0x00c7:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x03aa
            int r5 = zzh(r1, r5)
            goto L_0x036a
        L_0x00d3:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x03aa
            long r5 = zzi(r1, r5)
            goto L_0x037a
        L_0x00df:
            boolean r14 = r0.zza(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x03aa
            long r5 = zzi(r1, r5)
            goto L_0x038a
        L_0x00eb:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x03aa
            goto L_0x0396
        L_0x00f3:
            boolean r5 = r0.zza(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x03aa
            goto L_0x03a2
        L_0x00fb:
            com.google.android.gms.internal.firebase_ml.zzuh r14 = r0.zzbpi
            java.lang.Object r5 = com.google.android.gms.internal.firebase_ml.zzwa.f(r1, r5)
            java.lang.Object r6 = r0.zzda(r12)
            int r3 = r14.zzd(r3, r5, r6)
            goto L_0x0296
        L_0x010b:
            java.util.List r5 = zze((java.lang.Object) r1, (long) r5)
            com.google.android.gms.internal.firebase_ml.zzvc r6 = r0.zzcz(r12)
            int r3 = com.google.android.gms.internal.firebase_ml.zzve.b((int) r3, (java.util.List<com.google.android.gms.internal.firebase_ml.zzum>) r5, (com.google.android.gms.internal.firebase_ml.zzvc) r6)
            goto L_0x0296
        L_0x0119:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_ml.zzve.c(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzbpa
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x012b:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_ml.zzve.g(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzbpa
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x013d:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_ml.zzve.i(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzbpa
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x014f:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_ml.zzve.h(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzbpa
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0161:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_ml.zzve.d(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzbpa
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0173:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_ml.zzve.f(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzbpa
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0185:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_ml.zzve.j(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzbpa
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0197:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_ml.zzve.h(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzbpa
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01a8:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_ml.zzve.i(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzbpa
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01b9:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_ml.zzve.e(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzbpa
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01ca:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_ml.zzve.b(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzbpa
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01db:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_ml.zzve.a(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzbpa
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01ec:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_ml.zzve.h(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzbpa
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01fd:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_ml.zzve.i(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzbpa
            if (r6 == 0) goto L_0x0211
        L_0x020d:
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x0211:
            int r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzcl(r3)
            int r6 = com.google.android.gms.internal.firebase_ml.zzsj.zzcn(r5)
            int r3 = r3 + r6
            int r3 = r3 + r5
            goto L_0x0296
        L_0x021d:
            java.util.List r5 = zze((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.firebase_ml.zzve.c(r3, r5, r11)
            goto L_0x0296
        L_0x0227:
            java.util.List r5 = zze((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.firebase_ml.zzve.g(r3, r5, r11)
            goto L_0x0296
        L_0x0230:
            java.util.List r5 = zze((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.firebase_ml.zzve.d(r3, r5, r11)
            goto L_0x0296
        L_0x0239:
            java.util.List r5 = zze((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.firebase_ml.zzve.f(r3, r5, r11)
            goto L_0x0296
        L_0x0242:
            java.util.List r5 = zze((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.firebase_ml.zzve.b(r3, r5)
            goto L_0x0296
        L_0x024b:
            java.util.List r5 = zze((java.lang.Object) r1, (long) r5)
            com.google.android.gms.internal.firebase_ml.zzvc r6 = r0.zzcz(r12)
            int r3 = com.google.android.gms.internal.firebase_ml.zzve.a((int) r3, (java.util.List<?>) r5, (com.google.android.gms.internal.firebase_ml.zzvc) r6)
            goto L_0x0296
        L_0x0258:
            java.util.List r5 = zze((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.firebase_ml.zzve.a((int) r3, (java.util.List<?>) r5)
            goto L_0x0296
        L_0x0261:
            java.util.List r5 = zze((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.firebase_ml.zzve.j(r3, r5, r11)
            goto L_0x0296
        L_0x026a:
            java.util.List r5 = zze((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.firebase_ml.zzve.e(r3, r5, r11)
            goto L_0x0296
        L_0x0273:
            java.util.List r5 = zze((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.firebase_ml.zzve.b((int) r3, (java.util.List<java.lang.Long>) r5, (boolean) r11)
            goto L_0x0296
        L_0x027c:
            java.util.List r5 = zze((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.firebase_ml.zzve.a((int) r3, (java.util.List<java.lang.Long>) r5, (boolean) r11)
            goto L_0x0296
        L_0x0285:
            java.util.List r5 = zze((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.firebase_ml.zzve.h(r3, r5, r11)
            goto L_0x0296
        L_0x028e:
            java.util.List r5 = zze((java.lang.Object) r1, (long) r5)
            int r3 = com.google.android.gms.internal.firebase_ml.zzve.i(r3, r5, r11)
        L_0x0296:
            int r13 = r13 + r3
            goto L_0x03aa
        L_0x0299:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
        L_0x029f:
            java.lang.Object r5 = com.google.android.gms.internal.firebase_ml.zzwa.f(r1, r5)
            com.google.android.gms.internal.firebase_ml.zzum r5 = (com.google.android.gms.internal.firebase_ml.zzum) r5
            com.google.android.gms.internal.firebase_ml.zzvc r6 = r0.zzcz(r12)
            int r3 = com.google.android.gms.internal.firebase_ml.zzsj.c(r3, r5, r6)
            goto L_0x0296
        L_0x02ae:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
            long r5 = com.google.android.gms.internal.firebase_ml.zzwa.b(r1, r5)
        L_0x02b8:
            int r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzf((int) r3, (long) r5)
            goto L_0x0296
        L_0x02bd:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
            int r5 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r1, (long) r5)
        L_0x02c7:
            int r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzk(r3, r5)
            goto L_0x0296
        L_0x02cc:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x03aa
        L_0x02d2:
            int r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzh((int) r3, (long) r9)
            goto L_0x0296
        L_0x02d7:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x03aa
        L_0x02dd:
            int r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzm(r3, r11)
            goto L_0x0296
        L_0x02e2:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
            int r5 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r1, (long) r5)
        L_0x02ec:
            int r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzn(r3, r5)
            goto L_0x0296
        L_0x02f1:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
            int r5 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r1, (long) r5)
        L_0x02fb:
            int r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzj(r3, r5)
            goto L_0x0296
        L_0x0300:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
        L_0x0306:
            java.lang.Object r5 = com.google.android.gms.internal.firebase_ml.zzwa.f(r1, r5)
        L_0x030a:
            com.google.android.gms.internal.firebase_ml.zzru r5 = (com.google.android.gms.internal.firebase_ml.zzru) r5
            int r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzc((int) r3, (com.google.android.gms.internal.firebase_ml.zzru) r5)
            goto L_0x0296
        L_0x0311:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
        L_0x0317:
            java.lang.Object r5 = com.google.android.gms.internal.firebase_ml.zzwa.f(r1, r5)
            com.google.android.gms.internal.firebase_ml.zzvc r6 = r0.zzcz(r12)
            int r3 = com.google.android.gms.internal.firebase_ml.zzve.a((int) r3, (java.lang.Object) r5, (com.google.android.gms.internal.firebase_ml.zzvc) r6)
            goto L_0x0296
        L_0x0325:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
            java.lang.Object r5 = com.google.android.gms.internal.firebase_ml.zzwa.f(r1, r5)
            boolean r6 = r5 instanceof com.google.android.gms.internal.firebase_ml.zzru
            if (r6 == 0) goto L_0x0334
        L_0x0333:
            goto L_0x030a
        L_0x0334:
            java.lang.String r5 = (java.lang.String) r5
            int r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzc((int) r3, (java.lang.String) r5)
            goto L_0x0296
        L_0x033c:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x03aa
        L_0x0342:
            int r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzc((int) r3, (boolean) r7)
            goto L_0x0296
        L_0x0348:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x03aa
        L_0x034e:
            int r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzl(r3, r11)
            goto L_0x0296
        L_0x0354:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x03aa
        L_0x035a:
            int r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzg((int) r3, (long) r9)
            goto L_0x0296
        L_0x0360:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
            int r5 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r1, (long) r5)
        L_0x036a:
            int r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzi(r3, r5)
            goto L_0x0296
        L_0x0370:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
            long r5 = com.google.android.gms.internal.firebase_ml.zzwa.b(r1, r5)
        L_0x037a:
            int r3 = com.google.android.gms.internal.firebase_ml.zzsj.zze((int) r3, (long) r5)
            goto L_0x0296
        L_0x0380:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
            long r5 = com.google.android.gms.internal.firebase_ml.zzwa.b(r1, r5)
        L_0x038a:
            int r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzd((int) r3, (long) r5)
            goto L_0x0296
        L_0x0390:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x03aa
        L_0x0396:
            int r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzb((int) r3, (float) r4)
            goto L_0x0296
        L_0x039c:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x03aa
        L_0x03a2:
            r5 = 0
            int r3 = com.google.android.gms.internal.firebase_ml.zzsj.zzb((int) r3, (double) r5)
            goto L_0x0296
        L_0x03aa:
            int r12 = r12 + 3
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            goto L_0x0016
        L_0x03b0:
            com.google.android.gms.internal.firebase_ml.zzvu<?, ?> r2 = r0.zzbpg
            int r1 = zza(r2, r1)
            int r13 = r13 + r1
            return r13
        L_0x03b8:
            sun.misc.Unsafe r2 = zzbor
            r3 = -1
            r3 = 0
            r5 = 0
            r6 = -1
            r12 = 0
        L_0x03bf:
            int[] r13 = r0.zzbos
            int r13 = r13.length
            if (r3 >= r13) goto L_0x07c5
            int r13 = r0.zzdc(r3)
            int[] r14 = r0.zzbos
            r15 = r14[r3]
            r16 = 267386880(0xff00000, float:2.3665827E-29)
            r17 = r13 & r16
            int r4 = r17 >>> 20
            r11 = 17
            if (r4 > r11) goto L_0x03eb
            int r11 = r3 + 2
            r11 = r14[r11]
            r14 = r11 & r8
            int r18 = r11 >>> 20
            int r18 = r7 << r18
            if (r14 == r6) goto L_0x03e8
            long r9 = (long) r14
            int r12 = r2.getInt(r1, r9)
            goto L_0x03e9
        L_0x03e8:
            r14 = r6
        L_0x03e9:
            r6 = r14
            goto L_0x040b
        L_0x03eb:
            boolean r9 = r0.zzbpa
            if (r9 == 0) goto L_0x0408
            com.google.android.gms.internal.firebase_ml.zzsx r9 = com.google.android.gms.internal.firebase_ml.zzsx.DOUBLE_LIST_PACKED
            int r9 = r9.id()
            if (r4 < r9) goto L_0x0408
            com.google.android.gms.internal.firebase_ml.zzsx r9 = com.google.android.gms.internal.firebase_ml.zzsx.SINT64_LIST_PACKED
            int r9 = r9.id()
            if (r4 > r9) goto L_0x0408
            int[] r9 = r0.zzbos
            int r10 = r3 + 2
            r9 = r9[r10]
            r11 = r9 & r8
            goto L_0x0409
        L_0x0408:
            r11 = 0
        L_0x0409:
            r18 = 0
        L_0x040b:
            r9 = r13 & r8
            long r9 = (long) r9
            switch(r4) {
                case 0: goto L_0x07af;
                case 1: goto L_0x079f;
                case 2: goto L_0x078d;
                case 3: goto L_0x077d;
                case 4: goto L_0x076d;
                case 5: goto L_0x075e;
                case 6: goto L_0x0752;
                case 7: goto L_0x0748;
                case 8: goto L_0x0733;
                case 9: goto L_0x0721;
                case 10: goto L_0x0712;
                case 11: goto L_0x0705;
                case 12: goto L_0x06f8;
                case 13: goto L_0x06ed;
                case 14: goto L_0x06e2;
                case 15: goto L_0x06d5;
                case 16: goto L_0x06c8;
                case 17: goto L_0x06b5;
                case 18: goto L_0x06a1;
                case 19: goto L_0x0695;
                case 20: goto L_0x0689;
                case 21: goto L_0x067d;
                case 22: goto L_0x0671;
                case 23: goto L_0x06a1;
                case 24: goto L_0x0695;
                case 25: goto L_0x0665;
                case 26: goto L_0x065a;
                case 27: goto L_0x064b;
                case 28: goto L_0x0640;
                case 29: goto L_0x0634;
                case 30: goto L_0x0627;
                case 31: goto L_0x0695;
                case 32: goto L_0x06a1;
                case 33: goto L_0x061a;
                case 34: goto L_0x060d;
                case 35: goto L_0x05ed;
                case 36: goto L_0x05dc;
                case 37: goto L_0x05cb;
                case 38: goto L_0x05ba;
                case 39: goto L_0x05a9;
                case 40: goto L_0x0598;
                case 41: goto L_0x0587;
                case 42: goto L_0x0575;
                case 43: goto L_0x0563;
                case 44: goto L_0x0551;
                case 45: goto L_0x053f;
                case 46: goto L_0x052d;
                case 47: goto L_0x051b;
                case 48: goto L_0x0509;
                case 49: goto L_0x04f9;
                case 50: goto L_0x04e9;
                case 51: goto L_0x04db;
                case 52: goto L_0x04ce;
                case 53: goto L_0x04be;
                case 54: goto L_0x04ae;
                case 55: goto L_0x049e;
                case 56: goto L_0x0490;
                case 57: goto L_0x0483;
                case 58: goto L_0x047b;
                case 59: goto L_0x046b;
                case 60: goto L_0x0463;
                case 61: goto L_0x045b;
                case 62: goto L_0x044f;
                case 63: goto L_0x0443;
                case 64: goto L_0x043b;
                case 65: goto L_0x0433;
                case 66: goto L_0x0427;
                case 67: goto L_0x041b;
                case 68: goto L_0x0413;
                default: goto L_0x0411;
            }
        L_0x0411:
            goto L_0x06ad
        L_0x0413:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06ad
            goto L_0x06b9
        L_0x041b:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06ad
            long r9 = zzi(r1, r9)
            goto L_0x06d0
        L_0x0427:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06ad
            int r4 = zzh(r1, r9)
            goto L_0x06dd
        L_0x0433:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06ad
            goto L_0x06e6
        L_0x043b:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06ad
            goto L_0x06f1
        L_0x0443:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06ad
            int r4 = zzh(r1, r9)
            goto L_0x0700
        L_0x044f:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06ad
            int r4 = zzh(r1, r9)
            goto L_0x070d
        L_0x045b:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06ad
            goto L_0x0716
        L_0x0463:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06ad
            goto L_0x0725
        L_0x046b:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06ad
            java.lang.Object r4 = r2.getObject(r1, r9)
            boolean r9 = r4 instanceof com.google.android.gms.internal.firebase_ml.zzru
            if (r9 == 0) goto L_0x0740
            goto L_0x073f
        L_0x047b:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06ad
            goto L_0x074c
        L_0x0483:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06ad
            r4 = 0
            int r9 = com.google.android.gms.internal.firebase_ml.zzsj.zzl(r15, r4)
            goto L_0x06f6
        L_0x0490:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06ad
            r9 = 0
            int r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzg((int) r15, (long) r9)
            goto L_0x06ac
        L_0x049e:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06ad
            int r4 = zzh(r1, r9)
            int r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzi(r15, r4)
            goto L_0x06ac
        L_0x04ae:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06ad
            long r9 = zzi(r1, r9)
            int r4 = com.google.android.gms.internal.firebase_ml.zzsj.zze((int) r15, (long) r9)
            goto L_0x06ac
        L_0x04be:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06ad
            long r9 = zzi(r1, r9)
            int r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzd((int) r15, (long) r9)
            goto L_0x06ac
        L_0x04ce:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06ad
            r4 = 0
            int r9 = com.google.android.gms.internal.firebase_ml.zzsj.zzb((int) r15, (float) r4)
            goto L_0x06f6
        L_0x04db:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06ad
            r9 = 0
            int r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzb((int) r15, (double) r9)
            goto L_0x06ac
        L_0x04e9:
            com.google.android.gms.internal.firebase_ml.zzuh r4 = r0.zzbpi
            java.lang.Object r9 = r2.getObject(r1, r9)
            java.lang.Object r10 = r0.zzda(r3)
            int r4 = r4.zzd(r15, r9, r10)
            goto L_0x06ac
        L_0x04f9:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase_ml.zzvc r9 = r0.zzcz(r3)
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.b((int) r15, (java.util.List<com.google.android.gms.internal.firebase_ml.zzum>) r4, (com.google.android.gms.internal.firebase_ml.zzvc) r9)
            goto L_0x06ac
        L_0x0509:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.c(r4)
            if (r4 <= 0) goto L_0x06ad
            boolean r9 = r0.zzbpa
            if (r9 == 0) goto L_0x0601
            goto L_0x05fd
        L_0x051b:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.g(r4)
            if (r4 <= 0) goto L_0x06ad
            boolean r9 = r0.zzbpa
            if (r9 == 0) goto L_0x0601
            goto L_0x05fd
        L_0x052d:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.i(r4)
            if (r4 <= 0) goto L_0x06ad
            boolean r9 = r0.zzbpa
            if (r9 == 0) goto L_0x0601
            goto L_0x05fd
        L_0x053f:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.h(r4)
            if (r4 <= 0) goto L_0x06ad
            boolean r9 = r0.zzbpa
            if (r9 == 0) goto L_0x0601
            goto L_0x05fd
        L_0x0551:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.d(r4)
            if (r4 <= 0) goto L_0x06ad
            boolean r9 = r0.zzbpa
            if (r9 == 0) goto L_0x0601
            goto L_0x05fd
        L_0x0563:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.f(r4)
            if (r4 <= 0) goto L_0x06ad
            boolean r9 = r0.zzbpa
            if (r9 == 0) goto L_0x0601
            goto L_0x05fd
        L_0x0575:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.j(r4)
            if (r4 <= 0) goto L_0x06ad
            boolean r9 = r0.zzbpa
            if (r9 == 0) goto L_0x0601
            goto L_0x05fd
        L_0x0587:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.h(r4)
            if (r4 <= 0) goto L_0x06ad
            boolean r9 = r0.zzbpa
            if (r9 == 0) goto L_0x0601
            goto L_0x05fd
        L_0x0598:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.i(r4)
            if (r4 <= 0) goto L_0x06ad
            boolean r9 = r0.zzbpa
            if (r9 == 0) goto L_0x0601
            goto L_0x05fd
        L_0x05a9:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.e(r4)
            if (r4 <= 0) goto L_0x06ad
            boolean r9 = r0.zzbpa
            if (r9 == 0) goto L_0x0601
            goto L_0x05fd
        L_0x05ba:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.b(r4)
            if (r4 <= 0) goto L_0x06ad
            boolean r9 = r0.zzbpa
            if (r9 == 0) goto L_0x0601
            goto L_0x05fd
        L_0x05cb:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.a(r4)
            if (r4 <= 0) goto L_0x06ad
            boolean r9 = r0.zzbpa
            if (r9 == 0) goto L_0x0601
            goto L_0x05fd
        L_0x05dc:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.h(r4)
            if (r4 <= 0) goto L_0x06ad
            boolean r9 = r0.zzbpa
            if (r9 == 0) goto L_0x0601
            goto L_0x05fd
        L_0x05ed:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.i(r4)
            if (r4 <= 0) goto L_0x06ad
            boolean r9 = r0.zzbpa
            if (r9 == 0) goto L_0x0601
        L_0x05fd:
            long r9 = (long) r11
            r2.putInt(r1, r9, r4)
        L_0x0601:
            int r9 = com.google.android.gms.internal.firebase_ml.zzsj.zzcl(r15)
            int r10 = com.google.android.gms.internal.firebase_ml.zzsj.zzcn(r4)
            int r9 = r9 + r10
            int r9 = r9 + r4
            goto L_0x06f6
        L_0x060d:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            r11 = 0
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.c(r15, r4, r11)
            goto L_0x06ac
        L_0x061a:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.g(r15, r4, r11)
            goto L_0x06ac
        L_0x0627:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.d(r15, r4, r11)
            goto L_0x06ac
        L_0x0634:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.f(r15, r4, r11)
            goto L_0x06ac
        L_0x0640:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.b(r15, r4)
            goto L_0x06ac
        L_0x064b:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase_ml.zzvc r9 = r0.zzcz(r3)
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.a((int) r15, (java.util.List<?>) r4, (com.google.android.gms.internal.firebase_ml.zzvc) r9)
            goto L_0x06ac
        L_0x065a:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.a((int) r15, (java.util.List<?>) r4)
            goto L_0x06ac
        L_0x0665:
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            r11 = 0
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.j(r15, r4, r11)
            goto L_0x06ac
        L_0x0671:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.e(r15, r4, r11)
            goto L_0x06ac
        L_0x067d:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.b((int) r15, (java.util.List<java.lang.Long>) r4, (boolean) r11)
            goto L_0x06ac
        L_0x0689:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.a((int) r15, (java.util.List<java.lang.Long>) r4, (boolean) r11)
            goto L_0x06ac
        L_0x0695:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.h(r15, r4, r11)
            goto L_0x06ac
        L_0x06a1:
            r11 = 0
            java.lang.Object r4 = r2.getObject(r1, r9)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.i(r15, r4, r11)
        L_0x06ac:
            int r5 = r5 + r4
        L_0x06ad:
            r4 = 0
        L_0x06ae:
            r9 = 0
            r10 = 0
            r13 = 0
            goto L_0x07be
        L_0x06b5:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06ad
        L_0x06b9:
            java.lang.Object r4 = r2.getObject(r1, r9)
            com.google.android.gms.internal.firebase_ml.zzum r4 = (com.google.android.gms.internal.firebase_ml.zzum) r4
            com.google.android.gms.internal.firebase_ml.zzvc r9 = r0.zzcz(r3)
            int r4 = com.google.android.gms.internal.firebase_ml.zzsj.c(r15, r4, r9)
            goto L_0x06ac
        L_0x06c8:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06ad
            long r9 = r2.getLong(r1, r9)
        L_0x06d0:
            int r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzf((int) r15, (long) r9)
            goto L_0x06ac
        L_0x06d5:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06ad
            int r4 = r2.getInt(r1, r9)
        L_0x06dd:
            int r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzk(r15, r4)
            goto L_0x06ac
        L_0x06e2:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06ad
        L_0x06e6:
            r9 = 0
            int r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzh((int) r15, (long) r9)
            goto L_0x06ac
        L_0x06ed:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06ad
        L_0x06f1:
            r4 = 0
            int r9 = com.google.android.gms.internal.firebase_ml.zzsj.zzm(r15, r4)
        L_0x06f6:
            int r5 = r5 + r9
            goto L_0x06ad
        L_0x06f8:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06ad
            int r4 = r2.getInt(r1, r9)
        L_0x0700:
            int r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzn(r15, r4)
            goto L_0x06ac
        L_0x0705:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06ad
            int r4 = r2.getInt(r1, r9)
        L_0x070d:
            int r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzj(r15, r4)
            goto L_0x06ac
        L_0x0712:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06ad
        L_0x0716:
            java.lang.Object r4 = r2.getObject(r1, r9)
        L_0x071a:
            com.google.android.gms.internal.firebase_ml.zzru r4 = (com.google.android.gms.internal.firebase_ml.zzru) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzc((int) r15, (com.google.android.gms.internal.firebase_ml.zzru) r4)
            goto L_0x06ac
        L_0x0721:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06ad
        L_0x0725:
            java.lang.Object r4 = r2.getObject(r1, r9)
            com.google.android.gms.internal.firebase_ml.zzvc r9 = r0.zzcz(r3)
            int r4 = com.google.android.gms.internal.firebase_ml.zzve.a((int) r15, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_ml.zzvc) r9)
            goto L_0x06ac
        L_0x0733:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06ad
            java.lang.Object r4 = r2.getObject(r1, r9)
            boolean r9 = r4 instanceof com.google.android.gms.internal.firebase_ml.zzru
            if (r9 == 0) goto L_0x0740
        L_0x073f:
            goto L_0x071a
        L_0x0740:
            java.lang.String r4 = (java.lang.String) r4
            int r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzc((int) r15, (java.lang.String) r4)
            goto L_0x06ac
        L_0x0748:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06ad
        L_0x074c:
            int r4 = com.google.android.gms.internal.firebase_ml.zzsj.zzc((int) r15, (boolean) r7)
            goto L_0x06ac
        L_0x0752:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06ad
            r4 = 0
            int r9 = com.google.android.gms.internal.firebase_ml.zzsj.zzl(r15, r4)
            int r5 = r5 + r9
            goto L_0x06ae
        L_0x075e:
            r4 = 0
            r9 = r12 & r18
            if (r9 == 0) goto L_0x076a
            r13 = 0
            int r9 = com.google.android.gms.internal.firebase_ml.zzsj.zzg((int) r15, (long) r13)
            goto L_0x079c
        L_0x076a:
            r13 = 0
            goto L_0x079d
        L_0x076d:
            r4 = 0
            r13 = 0
            r11 = r12 & r18
            if (r11 == 0) goto L_0x079d
            int r9 = r2.getInt(r1, r9)
            int r9 = com.google.android.gms.internal.firebase_ml.zzsj.zzi(r15, r9)
            goto L_0x079c
        L_0x077d:
            r4 = 0
            r13 = 0
            r11 = r12 & r18
            if (r11 == 0) goto L_0x079d
            long r9 = r2.getLong(r1, r9)
            int r9 = com.google.android.gms.internal.firebase_ml.zzsj.zze((int) r15, (long) r9)
            goto L_0x079c
        L_0x078d:
            r4 = 0
            r13 = 0
            r11 = r12 & r18
            if (r11 == 0) goto L_0x079d
            long r9 = r2.getLong(r1, r9)
            int r9 = com.google.android.gms.internal.firebase_ml.zzsj.zzd((int) r15, (long) r9)
        L_0x079c:
            int r5 = r5 + r9
        L_0x079d:
            r9 = 0
            goto L_0x07ac
        L_0x079f:
            r4 = 0
            r13 = 0
            r9 = r12 & r18
            if (r9 == 0) goto L_0x079d
            r9 = 0
            int r10 = com.google.android.gms.internal.firebase_ml.zzsj.zzb((int) r15, (float) r9)
            int r5 = r5 + r10
        L_0x07ac:
            r10 = 0
            goto L_0x07be
        L_0x07af:
            r4 = 0
            r9 = 0
            r13 = 0
            r10 = r12 & r18
            if (r10 == 0) goto L_0x07ac
            r10 = 0
            int r15 = com.google.android.gms.internal.firebase_ml.zzsj.zzb((int) r15, (double) r10)
            int r5 = r5 + r15
        L_0x07be:
            int r3 = r3 + 3
            r9 = r13
            r4 = 0
            r11 = 0
            goto L_0x03bf
        L_0x07c5:
            com.google.android.gms.internal.firebase_ml.zzvu<?, ?> r2 = r0.zzbpg
            int r2 = zza(r2, r1)
            int r5 = r5 + r2
            boolean r2 = r0.zzbox
            if (r2 == 0) goto L_0x07db
            com.google.android.gms.internal.firebase_ml.zzsr<?> r2 = r0.zzbph
            com.google.android.gms.internal.firebase_ml.zzsu r1 = r2.a((java.lang.Object) r1)
            int r1 = r1.zzpe()
            int r5 = r5 + r1
        L_0x07db:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzuq.zzaa(java.lang.Object):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0104, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzac(T r14) {
        /*
            r13 = this;
            r0 = 0
            r1 = -1
            r1 = 0
            r2 = -1
            r3 = 0
        L_0x0005:
            int r4 = r13.zzbpc
            r5 = 1
            if (r1 >= r4) goto L_0x0108
            int[] r4 = r13.zzbpb
            r4 = r4[r1]
            int[] r6 = r13.zzbos
            r6 = r6[r4]
            int r7 = r13.zzdc(r4)
            boolean r8 = r13.zzboz
            r9 = 1048575(0xfffff, float:1.469367E-39)
            if (r8 != 0) goto L_0x0035
            int[] r8 = r13.zzbos
            int r10 = r4 + 2
            r8 = r8[r10]
            r10 = r8 & r9
            int r8 = r8 >>> 20
            int r8 = r5 << r8
            if (r10 == r2) goto L_0x0036
            sun.misc.Unsafe r2 = zzbor
            long r11 = (long) r10
            int r2 = r2.getInt(r14, r11)
            r3 = r2
            r2 = r10
            goto L_0x0036
        L_0x0035:
            r8 = 0
        L_0x0036:
            r10 = 268435456(0x10000000, float:2.5243549E-29)
            r10 = r10 & r7
            if (r10 == 0) goto L_0x003d
            r10 = 1
            goto L_0x003e
        L_0x003d:
            r10 = 0
        L_0x003e:
            if (r10 == 0) goto L_0x0047
            boolean r10 = r13.zza(r14, (int) r4, (int) r3, (int) r8)
            if (r10 != 0) goto L_0x0047
            return r0
        L_0x0047:
            r10 = 267386880(0xff00000, float:2.3665827E-29)
            r10 = r10 & r7
            int r10 = r10 >>> 20
            r11 = 9
            if (r10 == r11) goto L_0x00f3
            r11 = 17
            if (r10 == r11) goto L_0x00f3
            r8 = 27
            if (r10 == r8) goto L_0x00c7
            r8 = 60
            if (r10 == r8) goto L_0x00b6
            r8 = 68
            if (r10 == r8) goto L_0x00b6
            switch(r10) {
                case 49: goto L_0x00c7;
                case 50: goto L_0x0065;
                default: goto L_0x0063;
            }
        L_0x0063:
            goto L_0x0104
        L_0x0065:
            com.google.android.gms.internal.firebase_ml.zzuh r6 = r13.zzbpi
            r7 = r7 & r9
            long r7 = (long) r7
            java.lang.Object r7 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r7)
            java.util.Map r6 = r6.zzv(r7)
            boolean r7 = r6.isEmpty()
            if (r7 != 0) goto L_0x00b3
            java.lang.Object r4 = r13.zzda(r4)
            com.google.android.gms.internal.firebase_ml.zzuh r7 = r13.zzbpi
            com.google.android.gms.internal.firebase_ml.zzuf r4 = r7.zzz(r4)
            com.google.android.gms.internal.firebase_ml.zzwj r4 = r4.zzbol
            com.google.android.gms.internal.firebase_ml.zzwo r4 = r4.zzsd()
            com.google.android.gms.internal.firebase_ml.zzwo r7 = com.google.android.gms.internal.firebase_ml.zzwo.MESSAGE
            if (r4 != r7) goto L_0x00b3
            r4 = 0
            java.util.Collection r6 = r6.values()
            java.util.Iterator r6 = r6.iterator()
        L_0x0094:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x00b3
            java.lang.Object r7 = r6.next()
            if (r4 != 0) goto L_0x00ac
            com.google.android.gms.internal.firebase_ml.zzuz r4 = com.google.android.gms.internal.firebase_ml.zzuz.zzrc()
            java.lang.Class r8 = r7.getClass()
            com.google.android.gms.internal.firebase_ml.zzvc r4 = r4.zzl(r8)
        L_0x00ac:
            boolean r7 = r4.zzac(r7)
            if (r7 != 0) goto L_0x0094
            r5 = 0
        L_0x00b3:
            if (r5 != 0) goto L_0x0104
            return r0
        L_0x00b6:
            boolean r5 = r13.zza(r14, (int) r6, (int) r4)
            if (r5 == 0) goto L_0x0104
            com.google.android.gms.internal.firebase_ml.zzvc r4 = r13.zzcz(r4)
            boolean r4 = zza((java.lang.Object) r14, (int) r7, (com.google.android.gms.internal.firebase_ml.zzvc) r4)
            if (r4 != 0) goto L_0x0104
            return r0
        L_0x00c7:
            r6 = r7 & r9
            long r6 = (long) r6
            java.lang.Object r6 = com.google.android.gms.internal.firebase_ml.zzwa.f(r14, r6)
            java.util.List r6 = (java.util.List) r6
            boolean r7 = r6.isEmpty()
            if (r7 != 0) goto L_0x00f0
            com.google.android.gms.internal.firebase_ml.zzvc r4 = r13.zzcz(r4)
            r7 = 0
        L_0x00db:
            int r8 = r6.size()
            if (r7 >= r8) goto L_0x00f0
            java.lang.Object r8 = r6.get(r7)
            boolean r8 = r4.zzac(r8)
            if (r8 != 0) goto L_0x00ed
            r5 = 0
            goto L_0x00f0
        L_0x00ed:
            int r7 = r7 + 1
            goto L_0x00db
        L_0x00f0:
            if (r5 != 0) goto L_0x0104
            return r0
        L_0x00f3:
            boolean r5 = r13.zza(r14, (int) r4, (int) r3, (int) r8)
            if (r5 == 0) goto L_0x0104
            com.google.android.gms.internal.firebase_ml.zzvc r4 = r13.zzcz(r4)
            boolean r4 = zza((java.lang.Object) r14, (int) r7, (com.google.android.gms.internal.firebase_ml.zzvc) r4)
            if (r4 != 0) goto L_0x0104
            return r0
        L_0x0104:
            int r1 = r1 + 1
            goto L_0x0005
        L_0x0108:
            boolean r1 = r13.zzbox
            if (r1 == 0) goto L_0x0119
            com.google.android.gms.internal.firebase_ml.zzsr<?> r1 = r13.zzbph
            com.google.android.gms.internal.firebase_ml.zzsu r14 = r1.a((java.lang.Object) r14)
            boolean r14 = r14.isInitialized()
            if (r14 != 0) goto L_0x0119
            return r0
        L_0x0119:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzuq.zzac(java.lang.Object):boolean");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
        com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r7, r2, com.google.android.gms.internal.firebase_ml.zzwa.f(r8, r2));
        zzb(r7, r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0089, code lost:
        com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r7, r2, com.google.android.gms.internal.firebase_ml.zzwa.f(r8, r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b3, code lost:
        com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r7, r2, com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r8, r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00c8, code lost:
        com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r7, r2, com.google.android.gms.internal.firebase_ml.zzwa.b(r8, r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00eb, code lost:
        zzb(r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ee, code lost:
        r0 = r0 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zze(T r7, T r8) {
        /*
            r6 = this;
            if (r8 == 0) goto L_0x0105
            r0 = 0
        L_0x0003:
            int[] r1 = r6.zzbos
            int r1 = r1.length
            if (r0 >= r1) goto L_0x00f2
            int r1 = r6.zzdc(r0)
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r1
            long r2 = (long) r2
            int[] r4 = r6.zzbos
            r4 = r4[r0]
            r5 = 267386880(0xff00000, float:2.3665827E-29)
            r1 = r1 & r5
            int r1 = r1 >>> 20
            switch(r1) {
                case 0: goto L_0x00de;
                case 1: goto L_0x00d0;
                case 2: goto L_0x00c2;
                case 3: goto L_0x00bb;
                case 4: goto L_0x00ad;
                case 5: goto L_0x00a6;
                case 6: goto L_0x009f;
                case 7: goto L_0x0091;
                case 8: goto L_0x0083;
                case 9: goto L_0x007e;
                case 10: goto L_0x0077;
                case 11: goto L_0x0070;
                case 12: goto L_0x0069;
                case 13: goto L_0x0062;
                case 14: goto L_0x005a;
                case 15: goto L_0x0053;
                case 16: goto L_0x004b;
                case 17: goto L_0x007e;
                case 18: goto L_0x0044;
                case 19: goto L_0x0044;
                case 20: goto L_0x0044;
                case 21: goto L_0x0044;
                case 22: goto L_0x0044;
                case 23: goto L_0x0044;
                case 24: goto L_0x0044;
                case 25: goto L_0x0044;
                case 26: goto L_0x0044;
                case 27: goto L_0x0044;
                case 28: goto L_0x0044;
                case 29: goto L_0x0044;
                case 30: goto L_0x0044;
                case 31: goto L_0x0044;
                case 32: goto L_0x0044;
                case 33: goto L_0x0044;
                case 34: goto L_0x0044;
                case 35: goto L_0x0044;
                case 36: goto L_0x0044;
                case 37: goto L_0x0044;
                case 38: goto L_0x0044;
                case 39: goto L_0x0044;
                case 40: goto L_0x0044;
                case 41: goto L_0x0044;
                case 42: goto L_0x0044;
                case 43: goto L_0x0044;
                case 44: goto L_0x0044;
                case 45: goto L_0x0044;
                case 46: goto L_0x0044;
                case 47: goto L_0x0044;
                case 48: goto L_0x0044;
                case 49: goto L_0x0044;
                case 50: goto L_0x003d;
                case 51: goto L_0x002b;
                case 52: goto L_0x002b;
                case 53: goto L_0x002b;
                case 54: goto L_0x002b;
                case 55: goto L_0x002b;
                case 56: goto L_0x002b;
                case 57: goto L_0x002b;
                case 58: goto L_0x002b;
                case 59: goto L_0x002b;
                case 60: goto L_0x0026;
                case 61: goto L_0x001f;
                case 62: goto L_0x001f;
                case 63: goto L_0x001f;
                case 64: goto L_0x001f;
                case 65: goto L_0x001f;
                case 66: goto L_0x001f;
                case 67: goto L_0x001f;
                case 68: goto L_0x0026;
                default: goto L_0x001d;
            }
        L_0x001d:
            goto L_0x00ee
        L_0x001f:
            boolean r1 = r6.zza(r8, (int) r4, (int) r0)
            if (r1 == 0) goto L_0x00ee
            goto L_0x0031
        L_0x0026:
            r6.zzb(r7, r8, (int) r0)
            goto L_0x00ee
        L_0x002b:
            boolean r1 = r6.zza(r8, (int) r4, (int) r0)
            if (r1 == 0) goto L_0x00ee
        L_0x0031:
            java.lang.Object r1 = com.google.android.gms.internal.firebase_ml.zzwa.f(r8, r2)
            com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r7, (long) r2, (java.lang.Object) r1)
            r6.zzb(r7, (int) r4, (int) r0)
            goto L_0x00ee
        L_0x003d:
            com.google.android.gms.internal.firebase_ml.zzuh r1 = r6.zzbpi
            com.google.android.gms.internal.firebase_ml.zzve.a(r1, r7, r8, r2)
            goto L_0x00ee
        L_0x0044:
            com.google.android.gms.internal.firebase_ml.zztw r1 = r6.zzbpf
            r1.a(r7, r8, r2)
            goto L_0x00ee
        L_0x004b:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
            goto L_0x00c8
        L_0x0053:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
            goto L_0x006f
        L_0x005a:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
            goto L_0x00c8
        L_0x0062:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
            goto L_0x006f
        L_0x0069:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
        L_0x006f:
            goto L_0x00b3
        L_0x0070:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
            goto L_0x00b3
        L_0x0077:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
            goto L_0x0089
        L_0x007e:
            r6.zza(r7, r8, (int) r0)
            goto L_0x00ee
        L_0x0083:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
        L_0x0089:
            java.lang.Object r1 = com.google.android.gms.internal.firebase_ml.zzwa.f(r8, r2)
            com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r7, (long) r2, (java.lang.Object) r1)
            goto L_0x00eb
        L_0x0091:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
            boolean r1 = com.google.android.gms.internal.firebase_ml.zzwa.c(r8, r2)
            com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r7, (long) r2, (boolean) r1)
            goto L_0x00eb
        L_0x009f:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
            goto L_0x00b3
        L_0x00a6:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
            goto L_0x00c8
        L_0x00ad:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
        L_0x00b3:
            int r1 = com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r8, (long) r2)
            com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r7, (long) r2, (int) r1)
            goto L_0x00eb
        L_0x00bb:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
            goto L_0x00c8
        L_0x00c2:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
        L_0x00c8:
            long r4 = com.google.android.gms.internal.firebase_ml.zzwa.b(r8, r2)
            com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r7, (long) r2, (long) r4)
            goto L_0x00eb
        L_0x00d0:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
            float r1 = com.google.android.gms.internal.firebase_ml.zzwa.d(r8, r2)
            com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r7, (long) r2, (float) r1)
            goto L_0x00eb
        L_0x00de:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
            double r4 = com.google.android.gms.internal.firebase_ml.zzwa.e(r8, r2)
            com.google.android.gms.internal.firebase_ml.zzwa.a((java.lang.Object) r7, (long) r2, (double) r4)
        L_0x00eb:
            r6.zzb(r7, (int) r0)
        L_0x00ee:
            int r0 = r0 + 3
            goto L_0x0003
        L_0x00f2:
            boolean r0 = r6.zzboz
            if (r0 != 0) goto L_0x0104
            com.google.android.gms.internal.firebase_ml.zzvu<?, ?> r0 = r6.zzbpg
            com.google.android.gms.internal.firebase_ml.zzve.a(r0, r7, r8)
            boolean r0 = r6.zzbox
            if (r0 == 0) goto L_0x0104
            com.google.android.gms.internal.firebase_ml.zzsr<?> r0 = r6.zzbph
            com.google.android.gms.internal.firebase_ml.zzve.a(r0, r7, r8)
        L_0x0104:
            return
        L_0x0105:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzuq.zze(java.lang.Object, java.lang.Object):void");
    }

    public final void zzq(T t) {
        int i;
        int i2 = this.zzbpc;
        while (true) {
            i = this.zzbpd;
            if (i2 >= i) {
                break;
            }
            long zzdc = (long) (zzdc(this.zzbpb[i2]) & 1048575);
            Object f = zzwa.f(t, zzdc);
            if (f != null) {
                zzwa.a((Object) t, zzdc, this.zzbpi.zzx(f));
            }
            i2++;
        }
        int length = this.zzbpb.length;
        while (i < length) {
            this.zzbpf.a(t, (long) this.zzbpb[i]);
            i++;
        }
        this.zzbpg.b(t);
        if (this.zzbox) {
            this.zzbph.c(t);
        }
    }
}

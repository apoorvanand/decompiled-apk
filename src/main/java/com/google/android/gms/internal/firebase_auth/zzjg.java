package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

final class zzjg<T> implements zzjs<T> {
    private static final int[] zzacv = new int[0];
    private static final Unsafe zzacw = zzkq.c();
    private final zzjc zzacr;
    private final zzkk<?, ?> zzacs;
    private final boolean zzact;
    private final zzhh<?> zzacu;
    private final int[] zzacx;
    private final Object[] zzacy;
    private final int zzacz;
    private final int zzada;
    private final boolean zzadb;
    private final boolean zzadc;
    private final boolean zzadd;
    private final int[] zzade;
    private final int zzadf;
    private final int zzadg;
    private final zzjh zzadh;
    private final zzim zzadi;
    private final zziv zzadj;

    private zzjg(int[] iArr, Object[] objArr, int i, int i2, zzjc zzjc, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzjh zzjh, zzim zzim, zzkk<?, ?> zzkk, zzhh<?> zzhh, zziv zziv) {
        this.zzacx = iArr;
        this.zzacy = objArr;
        this.zzacz = i;
        this.zzada = i2;
        this.zzadb = zzjc instanceof zzhs;
        this.zzadc = z;
        this.zzact = zzhh != null && zzhh.a(zzjc);
        this.zzadd = false;
        this.zzade = iArr2;
        this.zzadf = i3;
        this.zzadg = i4;
        this.zzadh = zzjh;
        this.zzadi = zzim;
        this.zzacs = zzkk;
        this.zzacu = zzhh;
        this.zzacr = zzjc;
        this.zzadj = zziv;
    }

    /* JADX WARNING: Removed duplicated region for block: B:168:0x037d  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x03c9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.android.gms.internal.firebase_auth.zzjg<T> a(java.lang.Class<T> r35, com.google.android.gms.internal.firebase_auth.zzja r36, com.google.android.gms.internal.firebase_auth.zzjh r37, com.google.android.gms.internal.firebase_auth.zzim r38, com.google.android.gms.internal.firebase_auth.zzkk<?, ?> r39, com.google.android.gms.internal.firebase_auth.zzhh<?> r40, com.google.android.gms.internal.firebase_auth.zziv r41) {
        /*
            r0 = r36
            boolean r1 = r0 instanceof com.google.android.gms.internal.firebase_auth.zzjq
            if (r1 == 0) goto L_0x0444
            com.google.android.gms.internal.firebase_auth.zzjq r0 = (com.google.android.gms.internal.firebase_auth.zzjq) r0
            int r1 = r0.zzjo()
            int r2 = com.google.android.gms.internal.firebase_auth.zzhs.zzd.zzaaw
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
            int[] r8 = zzacv
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
            sun.misc.Unsafe r6 = zzacw
            java.lang.Object[] r17 = r0.b()
            com.google.android.gms.internal.firebase_auth.zzjc r18 = r0.zzjq()
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
            com.google.android.gms.internal.firebase_auth.zzjg r1 = new com.google.android.gms.internal.firebase_auth.zzjg
            com.google.android.gms.internal.firebase_auth.zzjc r0 = r0.zzjq()
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
            com.google.android.gms.internal.firebase_auth.zzkd r0 = (com.google.android.gms.internal.firebase_auth.zzkd) r0
            int r0 = r0.zzjo()
            int r1 = com.google.android.gms.internal.firebase_auth.zzhs.zzd.zzaaw
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzjg.a(java.lang.Class, com.google.android.gms.internal.firebase_auth.zzja, com.google.android.gms.internal.firebase_auth.zzjh, com.google.android.gms.internal.firebase_auth.zzim, com.google.android.gms.internal.firebase_auth.zzkk, com.google.android.gms.internal.firebase_auth.zzhh, com.google.android.gms.internal.firebase_auth.zziv):com.google.android.gms.internal.firebase_auth.zzjg");
    }

    private static <UT, UB> int zza(zzkk<UT, UB> zzkk, T t) {
        return zzkk.f(zzkk.b(t));
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzhy zzhy, UB ub, zzkk<UT, UB> zzkk) {
        zzit<?, ?> zzn = this.zzadj.zzn(zzaz(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (!zzhy.zzd(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzkk.a();
                }
                zzgn b = zzgf.b(zziu.a(zzn, next.getKey(), next.getValue()));
                try {
                    zziu.a(b.zzgh(), zzn, next.getKey(), next.getValue());
                    zzkk.a(ub, i2, b.zzgg());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzkk<UT, UB> zzkk) {
        zzhy zzba;
        int i2 = this.zzacx[i];
        Object f = zzkq.f(obj, (long) (zzbb(i) & 1048575));
        if (f == null || (zzba = zzba(i)) == null) {
            return ub;
        }
        return zza(i, i2, this.zzadj.zzi(f), zzba, ub, zzkk);
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

    private static void zza(int i, Object obj, zzlh zzlh) {
        if (obj instanceof String) {
            zzlh.zza(i, (String) obj);
        } else {
            zzlh.zza(i, (zzgf) obj);
        }
    }

    private static <UT, UB> void zza(zzkk<UT, UB> zzkk, T t, zzlh zzlh) {
        zzkk.a(zzkk.b(t), zzlh);
    }

    private final <K, V> void zza(zzlh zzlh, int i, Object obj, int i2) {
        if (obj != null) {
            zzlh.zza(i, this.zzadj.zzn(zzaz(i2)), this.zzadj.zzj(obj));
        }
    }

    private final void zza(Object obj, int i, zzjp zzjp) {
        long j;
        Object zzgq;
        if (zzbd(i)) {
            j = (long) (i & 1048575);
            zzgq = zzjp.zzgp();
        } else if (this.zzadb) {
            j = (long) (i & 1048575);
            zzgq = zzjp.readString();
        } else {
            j = (long) (i & 1048575);
            zzgq = zzjp.zzgq();
        }
        zzkq.a(obj, j, zzgq);
    }

    private final void zza(T t, T t2, int i) {
        long zzbb = (long) (zzbb(i) & 1048575);
        if (zza(t2, i)) {
            Object f = zzkq.f(t, zzbb);
            Object f2 = zzkq.f(t2, zzbb);
            if (f != null && f2 != null) {
                zzkq.a((Object) t, zzbb, zzht.a(f, f2));
                zzb(t, i);
            } else if (f2 != null) {
                zzkq.a((Object) t, zzbb, f2);
                zzb(t, i);
            }
        }
    }

    private final boolean zza(T t, int i) {
        if (this.zzadc) {
            int zzbb = zzbb(i);
            long j = (long) (zzbb & 1048575);
            switch ((zzbb & 267386880) >>> 20) {
                case 0:
                    return zzkq.e(t, j) != 0.0d;
                case 1:
                    return zzkq.d(t, j) != 0.0f;
                case 2:
                    return zzkq.b(t, j) != 0;
                case 3:
                    return zzkq.b(t, j) != 0;
                case 4:
                    return zzkq.a((Object) t, j) != 0;
                case 5:
                    return zzkq.b(t, j) != 0;
                case 6:
                    return zzkq.a((Object) t, j) != 0;
                case 7:
                    return zzkq.c(t, j);
                case 8:
                    Object f = zzkq.f(t, j);
                    if (f instanceof String) {
                        return !((String) f).isEmpty();
                    }
                    if (f instanceof zzgf) {
                        return !zzgf.zzvv.equals(f);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzkq.f(t, j) != null;
                case 10:
                    return !zzgf.zzvv.equals(zzkq.f(t, j));
                case 11:
                    return zzkq.a((Object) t, j) != 0;
                case 12:
                    return zzkq.a((Object) t, j) != 0;
                case 13:
                    return zzkq.a((Object) t, j) != 0;
                case 14:
                    return zzkq.b(t, j) != 0;
                case 15:
                    return zzkq.a((Object) t, j) != 0;
                case 16:
                    return zzkq.b(t, j) != 0;
                case 17:
                    return zzkq.f(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            int zzbc = zzbc(i);
            return (zzkq.a((Object) t, (long) (zzbc & 1048575)) & (1 << (zzbc >>> 20))) != 0;
        }
    }

    private final boolean zza(T t, int i, int i2) {
        return zzkq.a((Object) t, (long) (zzbc(i2) & 1048575)) == i;
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        return this.zzadc ? zza(t, i) : (i2 & i3) != 0;
    }

    private static boolean zza(Object obj, int i, zzjs zzjs) {
        return zzjs.zzp(zzkq.f(obj, (long) (i & 1048575)));
    }

    private final zzjs zzay(int i) {
        int i2 = (i / 3) << 1;
        zzjs zzjs = (zzjs) this.zzacy[i2];
        if (zzjs != null) {
            return zzjs;
        }
        zzjs zzf = zzjo.zzjv().zzf((Class) this.zzacy[i2 + 1]);
        this.zzacy[i2] = zzf;
        return zzf;
    }

    private final Object zzaz(int i) {
        return this.zzacy[(i / 3) << 1];
    }

    private final void zzb(T t, int i) {
        if (!this.zzadc) {
            int zzbc = zzbc(i);
            long j = (long) (zzbc & 1048575);
            zzkq.a((Object) t, j, zzkq.a((Object) t, j) | (1 << (zzbc >>> 20)));
        }
    }

    private final void zzb(T t, int i, int i2) {
        zzkq.a((Object) t, (long) (zzbc(i2) & 1048575), i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0282, code lost:
        com.google.android.gms.internal.firebase_auth.zzju.zzj(r4, (java.util.List) r8.getObject(r1, r12), r2, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0292, code lost:
        com.google.android.gms.internal.firebase_auth.zzju.zzg(r4, (java.util.List) r8.getObject(r1, r12), r2, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x02a2, code lost:
        com.google.android.gms.internal.firebase_auth.zzju.zzl(r4, (java.util.List) r8.getObject(r1, r12), r2, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x02b2, code lost:
        com.google.android.gms.internal.firebase_auth.zzju.zzm(r4, (java.util.List) r8.getObject(r1, r12), r2, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x02c2, code lost:
        com.google.android.gms.internal.firebase_auth.zzju.zzi(r4, (java.util.List) r8.getObject(r1, r12), r2, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x047b, code lost:
        r5 = r5 + 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0278, code lost:
        com.google.android.gms.internal.firebase_auth.zzju.zze(r4, r9, r2, r14);
     */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x0485  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r19, com.google.android.gms.internal.firebase_auth.zzlh r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            boolean r3 = r0.zzact
            if (r3 == 0) goto L_0x0023
            com.google.android.gms.internal.firebase_auth.zzhh<?> r3 = r0.zzacu
            com.google.android.gms.internal.firebase_auth.zzhi r3 = r3.a((java.lang.Object) r1)
            com.google.android.gms.internal.firebase_auth.zzjt<FieldDescriptorType, java.lang.Object> r5 = r3.a
            boolean r5 = r5.isEmpty()
            if (r5 != 0) goto L_0x0023
            java.util.Iterator r3 = r3.iterator()
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x0025
        L_0x0023:
            r3 = 0
            r5 = 0
        L_0x0025:
            r6 = -1
            int[] r7 = r0.zzacx
            int r7 = r7.length
            sun.misc.Unsafe r8 = zzacw
            r10 = r5
            r5 = 0
            r11 = 0
        L_0x002e:
            if (r5 >= r7) goto L_0x047f
            int r12 = r0.zzbb(r5)
            int[] r13 = r0.zzacx
            r14 = r13[r5]
            r15 = 267386880(0xff00000, float:2.3665827E-29)
            r15 = r15 & r12
            int r15 = r15 >>> 20
            boolean r4 = r0.zzadc
            r16 = 1048575(0xfffff, float:1.469367E-39)
            if (r4 != 0) goto L_0x0064
            r4 = 17
            if (r15 > r4) goto L_0x0064
            int r4 = r5 + 2
            r4 = r13[r4]
            r13 = r4 & r16
            if (r13 == r6) goto L_0x0058
            r17 = r10
            long r9 = (long) r13
            int r11 = r8.getInt(r1, r9)
            goto L_0x005b
        L_0x0058:
            r17 = r10
            r13 = r6
        L_0x005b:
            int r4 = r4 >>> 20
            r6 = 1
            int r9 = r6 << r4
            r6 = r13
            r10 = r17
            goto L_0x0069
        L_0x0064:
            r17 = r10
            r10 = r17
            r9 = 0
        L_0x0069:
            if (r10 == 0) goto L_0x0088
            com.google.android.gms.internal.firebase_auth.zzhh<?> r4 = r0.zzacu
            int r4 = r4.a((java.util.Map.Entry<?, ?>) r10)
            if (r4 > r14) goto L_0x0088
            com.google.android.gms.internal.firebase_auth.zzhh<?> r4 = r0.zzacu
            r4.a(r2, r10)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0086
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            r10 = r4
            goto L_0x0069
        L_0x0086:
            r10 = 0
            goto L_0x0069
        L_0x0088:
            r4 = r12 & r16
            long r12 = (long) r4
            switch(r15) {
                case 0: goto L_0x046f;
                case 1: goto L_0x0462;
                case 2: goto L_0x0455;
                case 3: goto L_0x0448;
                case 4: goto L_0x043b;
                case 5: goto L_0x042e;
                case 6: goto L_0x0421;
                case 7: goto L_0x0414;
                case 8: goto L_0x0406;
                case 9: goto L_0x03f4;
                case 10: goto L_0x03e4;
                case 11: goto L_0x03d6;
                case 12: goto L_0x03c8;
                case 13: goto L_0x03ba;
                case 14: goto L_0x03ac;
                case 15: goto L_0x039e;
                case 16: goto L_0x0390;
                case 17: goto L_0x037e;
                case 18: goto L_0x036e;
                case 19: goto L_0x035e;
                case 20: goto L_0x034e;
                case 21: goto L_0x033e;
                case 22: goto L_0x032e;
                case 23: goto L_0x031e;
                case 24: goto L_0x030e;
                case 25: goto L_0x02fe;
                case 26: goto L_0x02ef;
                case 27: goto L_0x02dc;
                case 28: goto L_0x02cd;
                case 29: goto L_0x02bd;
                case 30: goto L_0x02ad;
                case 31: goto L_0x029d;
                case 32: goto L_0x028d;
                case 33: goto L_0x027d;
                case 34: goto L_0x026d;
                case 35: goto L_0x025d;
                case 36: goto L_0x024d;
                case 37: goto L_0x023d;
                case 38: goto L_0x022d;
                case 39: goto L_0x021d;
                case 40: goto L_0x020d;
                case 41: goto L_0x01fd;
                case 42: goto L_0x01ed;
                case 43: goto L_0x01e6;
                case 44: goto L_0x01df;
                case 45: goto L_0x01d8;
                case 46: goto L_0x01d1;
                case 47: goto L_0x01ca;
                case 48: goto L_0x01bd;
                case 49: goto L_0x01aa;
                case 50: goto L_0x01a1;
                case 51: goto L_0x0192;
                case 52: goto L_0x0183;
                case 53: goto L_0x0174;
                case 54: goto L_0x0165;
                case 55: goto L_0x0156;
                case 56: goto L_0x0147;
                case 57: goto L_0x0138;
                case 58: goto L_0x0129;
                case 59: goto L_0x011a;
                case 60: goto L_0x0107;
                case 61: goto L_0x00f7;
                case 62: goto L_0x00e9;
                case 63: goto L_0x00db;
                case 64: goto L_0x00cd;
                case 65: goto L_0x00bf;
                case 66: goto L_0x00b1;
                case 67: goto L_0x00a3;
                case 68: goto L_0x0091;
                default: goto L_0x008e;
            }
        L_0x008e:
            r15 = 0
            goto L_0x047b
        L_0x0091:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008e
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.firebase_auth.zzjs r9 = r0.zzay(r5)
            r2.zzb((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_auth.zzjs) r9)
            goto L_0x008e
        L_0x00a3:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008e
            long r12 = zzi(r1, r12)
            r2.zzb((int) r14, (long) r12)
            goto L_0x008e
        L_0x00b1:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008e
            int r4 = zzh(r1, r12)
            r2.zzh(r14, r4)
            goto L_0x008e
        L_0x00bf:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008e
            long r12 = zzi(r1, r12)
            r2.zzj(r14, r12)
            goto L_0x008e
        L_0x00cd:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008e
            int r4 = zzh(r1, r12)
            r2.zzp(r14, r4)
            goto L_0x008e
        L_0x00db:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008e
            int r4 = zzh(r1, r12)
            r2.zzq(r14, r4)
            goto L_0x008e
        L_0x00e9:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008e
            int r4 = zzh(r1, r12)
            r2.zzg(r14, r4)
            goto L_0x008e
        L_0x00f7:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008e
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.firebase_auth.zzgf r4 = (com.google.android.gms.internal.firebase_auth.zzgf) r4
            r2.zza((int) r14, (com.google.android.gms.internal.firebase_auth.zzgf) r4)
            goto L_0x008e
        L_0x0107:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008e
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.firebase_auth.zzjs r9 = r0.zzay(r5)
            r2.zza((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_auth.zzjs) r9)
            goto L_0x008e
        L_0x011a:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008e
            java.lang.Object r4 = r8.getObject(r1, r12)
            zza((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_auth.zzlh) r2)
            goto L_0x008e
        L_0x0129:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008e
            boolean r4 = zzj(r1, r12)
            r2.zzc((int) r14, (boolean) r4)
            goto L_0x008e
        L_0x0138:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008e
            int r4 = zzh(r1, r12)
            r2.zzi((int) r14, (int) r4)
            goto L_0x008e
        L_0x0147:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008e
            long r12 = zzi(r1, r12)
            r2.zzc((int) r14, (long) r12)
            goto L_0x008e
        L_0x0156:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008e
            int r4 = zzh(r1, r12)
            r2.zzf(r14, r4)
            goto L_0x008e
        L_0x0165:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008e
            long r12 = zzi(r1, r12)
            r2.zza((int) r14, (long) r12)
            goto L_0x008e
        L_0x0174:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008e
            long r12 = zzi(r1, r12)
            r2.zzi((int) r14, (long) r12)
            goto L_0x008e
        L_0x0183:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008e
            float r4 = zzg(r1, r12)
            r2.zza((int) r14, (float) r4)
            goto L_0x008e
        L_0x0192:
            boolean r4 = r0.zza(r1, (int) r14, (int) r5)
            if (r4 == 0) goto L_0x008e
            double r12 = zzf(r1, r12)
            r2.zza((int) r14, (double) r12)
            goto L_0x008e
        L_0x01a1:
            java.lang.Object r4 = r8.getObject(r1, r12)
            r0.zza((com.google.android.gms.internal.firebase_auth.zzlh) r2, (int) r14, (java.lang.Object) r4, (int) r5)
            goto L_0x008e
        L_0x01aa:
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzjs r12 = r0.zzay(r5)
            com.google.android.gms.internal.firebase_auth.zzju.zzb((int) r4, (java.util.List<?>) r9, (com.google.android.gms.internal.firebase_auth.zzlh) r2, (com.google.android.gms.internal.firebase_auth.zzjs) r12)
            goto L_0x008e
        L_0x01bd:
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r14 = 1
            goto L_0x0278
        L_0x01ca:
            r14 = 1
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            goto L_0x0282
        L_0x01d1:
            r14 = 1
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            goto L_0x0292
        L_0x01d8:
            r14 = 1
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            goto L_0x02a2
        L_0x01df:
            r14 = 1
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            goto L_0x02b2
        L_0x01e6:
            r14 = 1
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            goto L_0x02c2
        L_0x01ed:
            r14 = 1
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzn(r4, r9, r2, r14)
            goto L_0x008e
        L_0x01fd:
            r14 = 1
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzk(r4, r9, r2, r14)
            goto L_0x008e
        L_0x020d:
            r14 = 1
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzf(r4, r9, r2, r14)
            goto L_0x008e
        L_0x021d:
            r14 = 1
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzh(r4, r9, r2, r14)
            goto L_0x008e
        L_0x022d:
            r14 = 1
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzd(r4, r9, r2, r14)
            goto L_0x008e
        L_0x023d:
            r14 = 1
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzc(r4, r9, r2, r14)
            goto L_0x008e
        L_0x024d:
            r14 = 1
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzb((int) r4, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.firebase_auth.zzlh) r2, (boolean) r14)
            goto L_0x008e
        L_0x025d:
            r14 = 1
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zza((int) r4, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.firebase_auth.zzlh) r2, (boolean) r14)
            goto L_0x008e
        L_0x026d:
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r14 = 0
        L_0x0278:
            com.google.android.gms.internal.firebase_auth.zzju.zze(r4, r9, r2, r14)
            goto L_0x008e
        L_0x027d:
            r14 = 0
            int[] r4 = r0.zzacx
            r4 = r4[r5]
        L_0x0282:
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzj(r4, r9, r2, r14)
            goto L_0x008e
        L_0x028d:
            r14 = 0
            int[] r4 = r0.zzacx
            r4 = r4[r5]
        L_0x0292:
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzg(r4, r9, r2, r14)
            goto L_0x008e
        L_0x029d:
            r14 = 0
            int[] r4 = r0.zzacx
            r4 = r4[r5]
        L_0x02a2:
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzl(r4, r9, r2, r14)
            goto L_0x008e
        L_0x02ad:
            r14 = 0
            int[] r4 = r0.zzacx
            r4 = r4[r5]
        L_0x02b2:
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzm(r4, r9, r2, r14)
            goto L_0x008e
        L_0x02bd:
            r14 = 0
            int[] r4 = r0.zzacx
            r4 = r4[r5]
        L_0x02c2:
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzi(r4, r9, r2, r14)
            goto L_0x008e
        L_0x02cd:
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzb(r4, r9, r2)
            goto L_0x008e
        L_0x02dc:
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzjs r12 = r0.zzay(r5)
            com.google.android.gms.internal.firebase_auth.zzju.zza((int) r4, (java.util.List<?>) r9, (com.google.android.gms.internal.firebase_auth.zzlh) r2, (com.google.android.gms.internal.firebase_auth.zzjs) r12)
            goto L_0x008e
        L_0x02ef:
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zza(r4, r9, r2)
            goto L_0x008e
        L_0x02fe:
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            r15 = 0
            com.google.android.gms.internal.firebase_auth.zzju.zzn(r4, r9, r2, r15)
            goto L_0x047b
        L_0x030e:
            r15 = 0
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzk(r4, r9, r2, r15)
            goto L_0x047b
        L_0x031e:
            r15 = 0
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzf(r4, r9, r2, r15)
            goto L_0x047b
        L_0x032e:
            r15 = 0
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzh(r4, r9, r2, r15)
            goto L_0x047b
        L_0x033e:
            r15 = 0
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzd(r4, r9, r2, r15)
            goto L_0x047b
        L_0x034e:
            r15 = 0
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzc(r4, r9, r2, r15)
            goto L_0x047b
        L_0x035e:
            r15 = 0
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzb((int) r4, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.firebase_auth.zzlh) r2, (boolean) r15)
            goto L_0x047b
        L_0x036e:
            r15 = 0
            int[] r4 = r0.zzacx
            r4 = r4[r5]
            java.lang.Object r9 = r8.getObject(r1, r12)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zza((int) r4, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.firebase_auth.zzlh) r2, (boolean) r15)
            goto L_0x047b
        L_0x037e:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x047b
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.firebase_auth.zzjs r9 = r0.zzay(r5)
            r2.zzb((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_auth.zzjs) r9)
            goto L_0x047b
        L_0x0390:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x047b
            long r12 = r8.getLong(r1, r12)
            r2.zzb((int) r14, (long) r12)
            goto L_0x047b
        L_0x039e:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x047b
            int r4 = r8.getInt(r1, r12)
            r2.zzh(r14, r4)
            goto L_0x047b
        L_0x03ac:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x047b
            long r12 = r8.getLong(r1, r12)
            r2.zzj(r14, r12)
            goto L_0x047b
        L_0x03ba:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x047b
            int r4 = r8.getInt(r1, r12)
            r2.zzp(r14, r4)
            goto L_0x047b
        L_0x03c8:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x047b
            int r4 = r8.getInt(r1, r12)
            r2.zzq(r14, r4)
            goto L_0x047b
        L_0x03d6:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x047b
            int r4 = r8.getInt(r1, r12)
            r2.zzg(r14, r4)
            goto L_0x047b
        L_0x03e4:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x047b
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.firebase_auth.zzgf r4 = (com.google.android.gms.internal.firebase_auth.zzgf) r4
            r2.zza((int) r14, (com.google.android.gms.internal.firebase_auth.zzgf) r4)
            goto L_0x047b
        L_0x03f4:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x047b
            java.lang.Object r4 = r8.getObject(r1, r12)
            com.google.android.gms.internal.firebase_auth.zzjs r9 = r0.zzay(r5)
            r2.zza((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_auth.zzjs) r9)
            goto L_0x047b
        L_0x0406:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x047b
            java.lang.Object r4 = r8.getObject(r1, r12)
            zza((int) r14, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_auth.zzlh) r2)
            goto L_0x047b
        L_0x0414:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x047b
            boolean r4 = com.google.android.gms.internal.firebase_auth.zzkq.c(r1, r12)
            r2.zzc((int) r14, (boolean) r4)
            goto L_0x047b
        L_0x0421:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x047b
            int r4 = r8.getInt(r1, r12)
            r2.zzi((int) r14, (int) r4)
            goto L_0x047b
        L_0x042e:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x047b
            long r12 = r8.getLong(r1, r12)
            r2.zzc((int) r14, (long) r12)
            goto L_0x047b
        L_0x043b:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x047b
            int r4 = r8.getInt(r1, r12)
            r2.zzf(r14, r4)
            goto L_0x047b
        L_0x0448:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x047b
            long r12 = r8.getLong(r1, r12)
            r2.zza((int) r14, (long) r12)
            goto L_0x047b
        L_0x0455:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x047b
            long r12 = r8.getLong(r1, r12)
            r2.zzi((int) r14, (long) r12)
            goto L_0x047b
        L_0x0462:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x047b
            float r4 = com.google.android.gms.internal.firebase_auth.zzkq.d(r1, r12)
            r2.zza((int) r14, (float) r4)
            goto L_0x047b
        L_0x046f:
            r15 = 0
            r4 = r11 & r9
            if (r4 == 0) goto L_0x047b
            double r12 = com.google.android.gms.internal.firebase_auth.zzkq.e(r1, r12)
            r2.zza((int) r14, (double) r12)
        L_0x047b:
            int r5 = r5 + 3
            goto L_0x002e
        L_0x047f:
            r17 = r10
            r4 = r17
        L_0x0483:
            if (r4 == 0) goto L_0x0499
            com.google.android.gms.internal.firebase_auth.zzhh<?> r5 = r0.zzacu
            r5.a(r2, r4)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0497
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            goto L_0x0483
        L_0x0497:
            r4 = 0
            goto L_0x0483
        L_0x0499:
            com.google.android.gms.internal.firebase_auth.zzkk<?, ?> r3 = r0.zzacs
            zza(r3, r1, (com.google.android.gms.internal.firebase_auth.zzlh) r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzjg.zzb(java.lang.Object, com.google.android.gms.internal.firebase_auth.zzlh):void");
    }

    private final void zzb(T t, T t2, int i) {
        int zzbb = zzbb(i);
        int i2 = this.zzacx[i];
        long j = (long) (zzbb & 1048575);
        if (zza(t2, i2, i)) {
            Object f = zzkq.f(t, j);
            Object f2 = zzkq.f(t2, j);
            if (f != null && f2 != null) {
                zzkq.a((Object) t, j, zzht.a(f, f2));
                zzb(t, i2, i);
            } else if (f2 != null) {
                zzkq.a((Object) t, j, f2);
                zzb(t, i2, i);
            }
        }
    }

    private final zzhy zzba(int i) {
        return (zzhy) this.zzacy[((i / 3) << 1) + 1];
    }

    private final int zzbb(int i) {
        return this.zzacx[i + 1];
    }

    private final int zzbc(int i) {
        return this.zzacx[i + 2];
    }

    private static boolean zzbd(int i) {
        return (i & 536870912) != 0;
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza(t, i) == zza(t2, i);
    }

    private static List<?> zze(Object obj, long j) {
        return (List) zzkq.f(obj, j);
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzkq.f(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzkq.f(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzkq.f(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzkq.f(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzkq.f(t, j)).booleanValue();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005c, code lost:
        if (com.google.android.gms.internal.firebase_auth.zzju.a(com.google.android.gms.internal.firebase_auth.zzkq.f(r10, r6), com.google.android.gms.internal.firebase_auth.zzkq.f(r11, r6)) != false) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0070, code lost:
        if (com.google.android.gms.internal.firebase_auth.zzkq.b(r10, r6) == com.google.android.gms.internal.firebase_auth.zzkq.b(r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0082, code lost:
        if (com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0096, code lost:
        if (com.google.android.gms.internal.firebase_auth.zzkq.b(r10, r6) == com.google.android.gms.internal.firebase_auth.zzkq.b(r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a8, code lost:
        if (com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ba, code lost:
        if (com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00cc, code lost:
        if (com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e2, code lost:
        if (com.google.android.gms.internal.firebase_auth.zzju.a(com.google.android.gms.internal.firebase_auth.zzkq.f(r10, r6), com.google.android.gms.internal.firebase_auth.zzkq.f(r11, r6)) != false) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00f8, code lost:
        if (com.google.android.gms.internal.firebase_auth.zzju.a(com.google.android.gms.internal.firebase_auth.zzkq.f(r10, r6), com.google.android.gms.internal.firebase_auth.zzkq.f(r11, r6)) != false) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x010e, code lost:
        if (com.google.android.gms.internal.firebase_auth.zzju.a(com.google.android.gms.internal.firebase_auth.zzkq.f(r10, r6), com.google.android.gms.internal.firebase_auth.zzkq.f(r11, r6)) != false) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0120, code lost:
        if (com.google.android.gms.internal.firebase_auth.zzkq.c(r10, r6) == com.google.android.gms.internal.firebase_auth.zzkq.c(r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0132, code lost:
        if (com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0145, code lost:
        if (com.google.android.gms.internal.firebase_auth.zzkq.b(r10, r6) == com.google.android.gms.internal.firebase_auth.zzkq.b(r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0156, code lost:
        if (com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r10, r6) == com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0169, code lost:
        if (com.google.android.gms.internal.firebase_auth.zzkq.b(r10, r6) == com.google.android.gms.internal.firebase_auth.zzkq.b(r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x017c, code lost:
        if (com.google.android.gms.internal.firebase_auth.zzkq.b(r10, r6) == com.google.android.gms.internal.firebase_auth.zzkq.b(r11, r6)) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0195, code lost:
        if (java.lang.Float.floatToIntBits(com.google.android.gms.internal.firebase_auth.zzkq.d(r10, r6)) == java.lang.Float.floatToIntBits(com.google.android.gms.internal.firebase_auth.zzkq.d(r11, r6))) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01b0, code lost:
        if (java.lang.Double.doubleToLongBits(com.google.android.gms.internal.firebase_auth.zzkq.e(r10, r6)) == java.lang.Double.doubleToLongBits(com.google.android.gms.internal.firebase_auth.zzkq.e(r11, r6))) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01b2, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0038, code lost:
        if (com.google.android.gms.internal.firebase_auth.zzju.a(com.google.android.gms.internal.firebase_auth.zzkq.f(r10, r6), com.google.android.gms.internal.firebase_auth.zzkq.f(r11, r6)) != false) goto L_0x01b3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(T r10, T r11) {
        /*
            r9 = this;
            int[] r0 = r9.zzacx
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            r3 = 1
            if (r2 >= r0) goto L_0x01ba
            int r4 = r9.zzbb(r2)
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
            int r4 = r9.zzbc(r2)
            r4 = r4 & r5
            long r4 = (long) r4
            int r8 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r10, (long) r4)
            int r4 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r11, (long) r4)
            if (r8 != r4) goto L_0x01b2
            java.lang.Object r4 = com.google.android.gms.internal.firebase_auth.zzkq.f(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzkq.f(r11, r6)
            boolean r4 = com.google.android.gms.internal.firebase_auth.zzju.a((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01b3
            goto L_0x0197
        L_0x003c:
            java.lang.Object r3 = com.google.android.gms.internal.firebase_auth.zzkq.f(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.firebase_auth.zzkq.f(r11, r6)
            boolean r3 = com.google.android.gms.internal.firebase_auth.zzju.a((java.lang.Object) r3, (java.lang.Object) r4)
            goto L_0x01b3
        L_0x004a:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            java.lang.Object r4 = com.google.android.gms.internal.firebase_auth.zzkq.f(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzkq.f(r11, r6)
            boolean r4 = com.google.android.gms.internal.firebase_auth.zzju.a((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01b3
            goto L_0x01b2
        L_0x0060:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            long r4 = com.google.android.gms.internal.firebase_auth.zzkq.b(r10, r6)
            long r6 = com.google.android.gms.internal.firebase_auth.zzkq.b(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01b3
            goto L_0x0197
        L_0x0074:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            int r4 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r10, (long) r6)
            int r5 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r11, (long) r6)
            if (r4 == r5) goto L_0x01b3
            goto L_0x01b2
        L_0x0086:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            long r4 = com.google.android.gms.internal.firebase_auth.zzkq.b(r10, r6)
            long r6 = com.google.android.gms.internal.firebase_auth.zzkq.b(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01b3
            goto L_0x0197
        L_0x009a:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            int r4 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r10, (long) r6)
            int r5 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r11, (long) r6)
            if (r4 == r5) goto L_0x01b3
            goto L_0x01b2
        L_0x00ac:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            int r4 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r10, (long) r6)
            int r5 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r11, (long) r6)
            if (r4 == r5) goto L_0x01b3
            goto L_0x0197
        L_0x00be:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            int r4 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r10, (long) r6)
            int r5 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r11, (long) r6)
            if (r4 == r5) goto L_0x01b3
            goto L_0x01b2
        L_0x00d0:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            java.lang.Object r4 = com.google.android.gms.internal.firebase_auth.zzkq.f(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzkq.f(r11, r6)
            boolean r4 = com.google.android.gms.internal.firebase_auth.zzju.a((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01b3
            goto L_0x0197
        L_0x00e6:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            java.lang.Object r4 = com.google.android.gms.internal.firebase_auth.zzkq.f(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzkq.f(r11, r6)
            boolean r4 = com.google.android.gms.internal.firebase_auth.zzju.a((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01b3
            goto L_0x01b2
        L_0x00fc:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            java.lang.Object r4 = com.google.android.gms.internal.firebase_auth.zzkq.f(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzkq.f(r11, r6)
            boolean r4 = com.google.android.gms.internal.firebase_auth.zzju.a((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01b3
            goto L_0x0197
        L_0x0112:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            boolean r4 = com.google.android.gms.internal.firebase_auth.zzkq.c(r10, r6)
            boolean r5 = com.google.android.gms.internal.firebase_auth.zzkq.c(r11, r6)
            if (r4 == r5) goto L_0x01b3
            goto L_0x01b2
        L_0x0124:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            int r4 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r10, (long) r6)
            int r5 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r11, (long) r6)
            if (r4 == r5) goto L_0x01b3
            goto L_0x0197
        L_0x0135:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            long r4 = com.google.android.gms.internal.firebase_auth.zzkq.b(r10, r6)
            long r6 = com.google.android.gms.internal.firebase_auth.zzkq.b(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01b3
            goto L_0x01b2
        L_0x0148:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            int r4 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r10, (long) r6)
            int r5 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r11, (long) r6)
            if (r4 == r5) goto L_0x01b3
            goto L_0x0197
        L_0x0159:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            long r4 = com.google.android.gms.internal.firebase_auth.zzkq.b(r10, r6)
            long r6 = com.google.android.gms.internal.firebase_auth.zzkq.b(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01b3
            goto L_0x01b2
        L_0x016c:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            long r4 = com.google.android.gms.internal.firebase_auth.zzkq.b(r10, r6)
            long r6 = com.google.android.gms.internal.firebase_auth.zzkq.b(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01b3
            goto L_0x0197
        L_0x017f:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            float r4 = com.google.android.gms.internal.firebase_auth.zzkq.d(r10, r6)
            int r4 = java.lang.Float.floatToIntBits(r4)
            float r5 = com.google.android.gms.internal.firebase_auth.zzkq.d(r11, r6)
            int r5 = java.lang.Float.floatToIntBits(r5)
            if (r4 == r5) goto L_0x01b3
        L_0x0197:
            goto L_0x01b2
        L_0x0198:
            boolean r4 = r9.zzc(r10, r11, r2)
            if (r4 == 0) goto L_0x01b2
            double r4 = com.google.android.gms.internal.firebase_auth.zzkq.e(r10, r6)
            long r4 = java.lang.Double.doubleToLongBits(r4)
            double r6 = com.google.android.gms.internal.firebase_auth.zzkq.e(r11, r6)
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
            com.google.android.gms.internal.firebase_auth.zzkk<?, ?> r0 = r9.zzacs
            java.lang.Object r0 = r0.b(r10)
            com.google.android.gms.internal.firebase_auth.zzkk<?, ?> r2 = r9.zzacs
            java.lang.Object r2 = r2.b(r11)
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x01cd
            return r1
        L_0x01cd:
            boolean r0 = r9.zzact
            if (r0 == 0) goto L_0x01e2
            com.google.android.gms.internal.firebase_auth.zzhh<?> r0 = r9.zzacu
            com.google.android.gms.internal.firebase_auth.zzhi r10 = r0.a((java.lang.Object) r10)
            com.google.android.gms.internal.firebase_auth.zzhh<?> r0 = r9.zzacu
            com.google.android.gms.internal.firebase_auth.zzhi r11 = r0.a((java.lang.Object) r11)
            boolean r10 = r10.equals(r11)
            return r10
        L_0x01e2:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzjg.equals(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0061, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzkq.f(r9, r5);
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
        r3 = com.google.android.gms.internal.firebase_auth.zzkq.f(r9, r5);
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
        r3 = ((java.lang.String) com.google.android.gms.internal.firebase_auth.zzkq.f(r9, r5)).hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00fd, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzht.zzv(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0116, code lost:
        r3 = java.lang.Float.floatToIntBits(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0121, code lost:
        r3 = java.lang.Double.doubleToLongBits(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0125, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzht.zzk(r3);
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
            int[] r0 = r8.zzacx
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            if (r1 >= r0) goto L_0x012e
            int r3 = r8.zzbb(r1)
            int[] r4 = r8.zzacx
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
            java.lang.Object r3 = com.google.android.gms.internal.firebase_auth.zzkq.f(r9, r5)
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
            java.lang.Object r3 = com.google.android.gms.internal.firebase_auth.zzkq.f(r9, r5)
            if (r3 == 0) goto L_0x00e6
            goto L_0x00e2
        L_0x00d1:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.firebase_auth.zzkq.f(r9, r5)
        L_0x00d7:
            int r3 = r3.hashCode()
            goto L_0x0129
        L_0x00dc:
            java.lang.Object r3 = com.google.android.gms.internal.firebase_auth.zzkq.f(r9, r5)
            if (r3 == 0) goto L_0x00e6
        L_0x00e2:
            int r7 = r3.hashCode()
        L_0x00e6:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x012a
        L_0x00ea:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.firebase_auth.zzkq.f(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0129
        L_0x00f7:
            int r2 = r2 * 53
            boolean r3 = com.google.android.gms.internal.firebase_auth.zzkq.c(r9, r5)
        L_0x00fd:
            int r3 = com.google.android.gms.internal.firebase_auth.zzht.zzv(r3)
            goto L_0x0129
        L_0x0102:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r9, (long) r5)
            goto L_0x0129
        L_0x0109:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.firebase_auth.zzkq.b(r9, r5)
            goto L_0x0125
        L_0x0110:
            int r2 = r2 * 53
            float r3 = com.google.android.gms.internal.firebase_auth.zzkq.d(r9, r5)
        L_0x0116:
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0129
        L_0x011b:
            int r2 = r2 * 53
            double r3 = com.google.android.gms.internal.firebase_auth.zzkq.e(r9, r5)
        L_0x0121:
            long r3 = java.lang.Double.doubleToLongBits(r3)
        L_0x0125:
            int r3 = com.google.android.gms.internal.firebase_auth.zzht.zzk(r3)
        L_0x0129:
            int r2 = r2 + r3
        L_0x012a:
            int r1 = r1 + 3
            goto L_0x0005
        L_0x012e:
            int r2 = r2 * 53
            com.google.android.gms.internal.firebase_auth.zzkk<?, ?> r0 = r8.zzacs
            java.lang.Object r0 = r0.b(r9)
            int r0 = r0.hashCode()
            int r2 = r2 + r0
            boolean r0 = r8.zzact
            if (r0 == 0) goto L_0x014c
            int r2 = r2 * 53
            com.google.android.gms.internal.firebase_auth.zzhh<?> r0 = r8.zzacu
            com.google.android.gms.internal.firebase_auth.zzhi r9 = r0.a((java.lang.Object) r9)
            int r9 = r9.hashCode()
            int r2 = r2 + r9
        L_0x014c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzjg.hashCode(java.lang.Object):int");
    }

    public final T newInstance() {
        return this.zzadh.newInstance(this.zzacr);
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final void zza(T r18, com.google.android.gms.internal.firebase_auth.zzjp r19, com.google.android.gms.internal.firebase_auth.zzhf r20) {
        /*
            r17 = this;
            r1 = r17
            r2 = r18
            r0 = r19
            r10 = r20
            if (r10 == 0) goto L_0x0545
            com.google.android.gms.internal.firebase_auth.zzkk<?, ?> r11 = r1.zzacs
            com.google.android.gms.internal.firebase_auth.zzhh<?> r12 = r1.zzacu
            r13 = 0
            r3 = r13
            r14 = r3
        L_0x0011:
            int r4 = r19.zzhg()     // Catch:{ all -> 0x052d }
            int r5 = r1.zzacz     // Catch:{ all -> 0x052d }
            r6 = -1
            if (r4 < r5) goto L_0x003e
            int r5 = r1.zzada     // Catch:{ all -> 0x052d }
            if (r4 > r5) goto L_0x003e
            r5 = 0
            int[] r7 = r1.zzacx     // Catch:{ all -> 0x052d }
            int r7 = r7.length     // Catch:{ all -> 0x052d }
            int r7 = r7 / 3
            int r7 = r7 + -1
        L_0x0026:
            if (r5 > r7) goto L_0x003e
            int r8 = r7 + r5
            int r8 = r8 >>> 1
            int r9 = r8 * 3
            int[] r15 = r1.zzacx     // Catch:{ all -> 0x052d }
            r15 = r15[r9]     // Catch:{ all -> 0x052d }
            if (r4 != r15) goto L_0x0036
            r6 = r9
            goto L_0x003e
        L_0x0036:
            if (r4 >= r15) goto L_0x003b
            int r7 = r8 + -1
            goto L_0x0026
        L_0x003b:
            int r5 = r8 + 1
            goto L_0x0026
        L_0x003e:
            if (r6 >= 0) goto L_0x00a6
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 != r5) goto L_0x005c
            int r0 = r1.zzadf
        L_0x0047:
            int r3 = r1.zzadg
            if (r0 >= r3) goto L_0x0056
            int[] r3 = r1.zzade
            r3 = r3[r0]
            java.lang.Object r14 = r1.zza((java.lang.Object) r2, (int) r3, r14, r11)
            int r0 = r0 + 1
            goto L_0x0047
        L_0x0056:
            if (r14 == 0) goto L_0x005b
            r11.b((java.lang.Object) r2, r14)
        L_0x005b:
            return
        L_0x005c:
            boolean r5 = r1.zzact     // Catch:{ all -> 0x052d }
            if (r5 != 0) goto L_0x0062
            r5 = r13
            goto L_0x0069
        L_0x0062:
            com.google.android.gms.internal.firebase_auth.zzjc r5 = r1.zzacr     // Catch:{ all -> 0x052d }
            java.lang.Object r4 = r12.a(r10, r5, r4)     // Catch:{ all -> 0x052d }
            r5 = r4
        L_0x0069:
            if (r5 == 0) goto L_0x0080
            if (r3 != 0) goto L_0x0071
            com.google.android.gms.internal.firebase_auth.zzhi r3 = r12.b(r2)     // Catch:{ all -> 0x052d }
        L_0x0071:
            r15 = r3
            r3 = r12
            r4 = r19
            r6 = r20
            r7 = r15
            r8 = r14
            r9 = r11
            java.lang.Object r14 = r3.a(r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x052d }
            r3 = r15
            goto L_0x0011
        L_0x0080:
            r11.a((com.google.android.gms.internal.firebase_auth.zzjp) r0)     // Catch:{ all -> 0x052d }
            if (r14 != 0) goto L_0x0089
            java.lang.Object r14 = r11.c(r2)     // Catch:{ all -> 0x052d }
        L_0x0089:
            boolean r4 = r11.a(r14, (com.google.android.gms.internal.firebase_auth.zzjp) r0)     // Catch:{ all -> 0x052d }
            if (r4 != 0) goto L_0x0011
            int r0 = r1.zzadf
        L_0x0091:
            int r3 = r1.zzadg
            if (r0 >= r3) goto L_0x00a0
            int[] r3 = r1.zzade
            r3 = r3[r0]
            java.lang.Object r14 = r1.zza((java.lang.Object) r2, (int) r3, r14, r11)
            int r0 = r0 + 1
            goto L_0x0091
        L_0x00a0:
            if (r14 == 0) goto L_0x00a5
            r11.b((java.lang.Object) r2, r14)
        L_0x00a5:
            return
        L_0x00a6:
            int r5 = r1.zzbb(r6)     // Catch:{ all -> 0x052d }
            r7 = 267386880(0xff00000, float:2.3665827E-29)
            r7 = r7 & r5
            int r7 = r7 >>> 20
            r8 = 1048575(0xfffff, float:1.469367E-39)
            switch(r7) {
                case 0: goto L_0x04dd;
                case 1: goto L_0x04d1;
                case 2: goto L_0x04c5;
                case 3: goto L_0x04b9;
                case 4: goto L_0x04ad;
                case 5: goto L_0x04a1;
                case 6: goto L_0x0495;
                case 7: goto L_0x0489;
                case 8: goto L_0x0484;
                case 9: goto L_0x0459;
                case 10: goto L_0x044e;
                case 11: goto L_0x0443;
                case 12: goto L_0x042c;
                case 13: goto L_0x0421;
                case 14: goto L_0x0416;
                case 15: goto L_0x040b;
                case 16: goto L_0x0400;
                case 17: goto L_0x03cf;
                case 18: goto L_0x03c5;
                case 19: goto L_0x03bb;
                case 20: goto L_0x03b1;
                case 21: goto L_0x03a7;
                case 22: goto L_0x039d;
                case 23: goto L_0x0393;
                case 24: goto L_0x0389;
                case 25: goto L_0x037f;
                case 26: goto L_0x035f;
                case 27: goto L_0x034e;
                case 28: goto L_0x0341;
                case 29: goto L_0x0337;
                case 30: goto L_0x0326;
                case 31: goto L_0x031c;
                case 32: goto L_0x0312;
                case 33: goto L_0x0308;
                case 34: goto L_0x02fe;
                case 35: goto L_0x02f1;
                case 36: goto L_0x02e4;
                case 37: goto L_0x02d7;
                case 38: goto L_0x02ca;
                case 39: goto L_0x02bd;
                case 40: goto L_0x02b0;
                case 41: goto L_0x02a3;
                case 42: goto L_0x0296;
                case 43: goto L_0x0289;
                case 44: goto L_0x0274;
                case 45: goto L_0x0267;
                case 46: goto L_0x025a;
                case 47: goto L_0x024d;
                case 48: goto L_0x0240;
                case 49: goto L_0x022e;
                case 50: goto L_0x01ec;
                case 51: goto L_0x01dd;
                case 52: goto L_0x01ce;
                case 53: goto L_0x01bf;
                case 54: goto L_0x01b0;
                case 55: goto L_0x01a1;
                case 56: goto L_0x0192;
                case 57: goto L_0x0183;
                case 58: goto L_0x0174;
                case 59: goto L_0x016f;
                case 60: goto L_0x0140;
                case 61: goto L_0x0136;
                case 62: goto L_0x0128;
                case 63: goto L_0x0107;
                case 64: goto L_0x00f9;
                case 65: goto L_0x00eb;
                case 66: goto L_0x00dd;
                case 67: goto L_0x00cf;
                case 68: goto L_0x00bd;
                default: goto L_0x00b5;
            }
        L_0x00b5:
            if (r14 != 0) goto L_0x04e9
            java.lang.Object r14 = r11.a()     // Catch:{ zzib -> 0x0506 }
            goto L_0x04e9
        L_0x00bd:
            r5 = r5 & r8
            long r7 = (long) r5     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzjs r5 = r1.zzay(r6)     // Catch:{ zzib -> 0x0506 }
            java.lang.Object r5 = r0.zzb(r5, r10)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r7, (java.lang.Object) r5)     // Catch:{ zzib -> 0x0506 }
        L_0x00ca:
            r1.zzb(r2, (int) r4, (int) r6)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x00cf:
            r5 = r5 & r8
            long r7 = (long) r5     // Catch:{ zzib -> 0x0506 }
            long r15 = r19.zzgw()     // Catch:{ zzib -> 0x0506 }
            java.lang.Long r5 = java.lang.Long.valueOf(r15)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r7, (java.lang.Object) r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x00ca
        L_0x00dd:
            r5 = r5 & r8
            long r7 = (long) r5     // Catch:{ zzib -> 0x0506 }
            int r5 = r19.zzgv()     // Catch:{ zzib -> 0x0506 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r7, (java.lang.Object) r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x00ca
        L_0x00eb:
            r5 = r5 & r8
            long r7 = (long) r5     // Catch:{ zzib -> 0x0506 }
            long r15 = r19.zzgu()     // Catch:{ zzib -> 0x0506 }
            java.lang.Long r5 = java.lang.Long.valueOf(r15)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r7, (java.lang.Object) r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x00ca
        L_0x00f9:
            r5 = r5 & r8
            long r7 = (long) r5     // Catch:{ zzib -> 0x0506 }
            int r5 = r19.zzgt()     // Catch:{ zzib -> 0x0506 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r7, (java.lang.Object) r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x00ca
        L_0x0107:
            int r7 = r19.zzgs()     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzhy r9 = r1.zzba(r6)     // Catch:{ zzib -> 0x0506 }
            if (r9 == 0) goto L_0x011e
            boolean r9 = r9.zzd(r7)     // Catch:{ zzib -> 0x0506 }
            if (r9 == 0) goto L_0x0118
            goto L_0x011e
        L_0x0118:
            java.lang.Object r14 = com.google.android.gms.internal.firebase_auth.zzju.a((int) r4, (int) r7, r14, r11)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x011e:
            r5 = r5 & r8
            long r8 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r7)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r8, (java.lang.Object) r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x00ca
        L_0x0128:
            r5 = r5 & r8
            long r7 = (long) r5     // Catch:{ zzib -> 0x0506 }
            int r5 = r19.zzgr()     // Catch:{ zzib -> 0x0506 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r7, (java.lang.Object) r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x00ca
        L_0x0136:
            r5 = r5 & r8
            long r7 = (long) r5     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzgf r5 = r19.zzgq()     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r7, (java.lang.Object) r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x00ca
        L_0x0140:
            boolean r7 = r1.zza(r2, (int) r4, (int) r6)     // Catch:{ zzib -> 0x0506 }
            if (r7 == 0) goto L_0x015d
            r5 = r5 & r8
            long r7 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzkq.f(r2, r7)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzjs r9 = r1.zzay(r6)     // Catch:{ zzib -> 0x0506 }
            java.lang.Object r9 = r0.zza(r9, r10)     // Catch:{ zzib -> 0x0506 }
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzht.a((java.lang.Object) r5, (java.lang.Object) r9)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r7, (java.lang.Object) r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x00ca
        L_0x015d:
            r5 = r5 & r8
            long r7 = (long) r5     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzjs r5 = r1.zzay(r6)     // Catch:{ zzib -> 0x0506 }
            java.lang.Object r5 = r0.zza(r5, r10)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r7, (java.lang.Object) r5)     // Catch:{ zzib -> 0x0506 }
            r1.zzb(r2, (int) r6)     // Catch:{ zzib -> 0x0506 }
            goto L_0x00ca
        L_0x016f:
            r1.zza((java.lang.Object) r2, (int) r5, (com.google.android.gms.internal.firebase_auth.zzjp) r0)     // Catch:{ zzib -> 0x0506 }
            goto L_0x00ca
        L_0x0174:
            r5 = r5 & r8
            long r7 = (long) r5     // Catch:{ zzib -> 0x0506 }
            boolean r5 = r19.zzgo()     // Catch:{ zzib -> 0x0506 }
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r7, (java.lang.Object) r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x00ca
        L_0x0183:
            r5 = r5 & r8
            long r7 = (long) r5     // Catch:{ zzib -> 0x0506 }
            int r5 = r19.zzgn()     // Catch:{ zzib -> 0x0506 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r7, (java.lang.Object) r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x00ca
        L_0x0192:
            r5 = r5 & r8
            long r7 = (long) r5     // Catch:{ zzib -> 0x0506 }
            long r15 = r19.zzgm()     // Catch:{ zzib -> 0x0506 }
            java.lang.Long r5 = java.lang.Long.valueOf(r15)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r7, (java.lang.Object) r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x00ca
        L_0x01a1:
            r5 = r5 & r8
            long r7 = (long) r5     // Catch:{ zzib -> 0x0506 }
            int r5 = r19.zzgl()     // Catch:{ zzib -> 0x0506 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r7, (java.lang.Object) r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x00ca
        L_0x01b0:
            r5 = r5 & r8
            long r7 = (long) r5     // Catch:{ zzib -> 0x0506 }
            long r15 = r19.zzgj()     // Catch:{ zzib -> 0x0506 }
            java.lang.Long r5 = java.lang.Long.valueOf(r15)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r7, (java.lang.Object) r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x00ca
        L_0x01bf:
            r5 = r5 & r8
            long r7 = (long) r5     // Catch:{ zzib -> 0x0506 }
            long r15 = r19.zzgk()     // Catch:{ zzib -> 0x0506 }
            java.lang.Long r5 = java.lang.Long.valueOf(r15)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r7, (java.lang.Object) r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x00ca
        L_0x01ce:
            r5 = r5 & r8
            long r7 = (long) r5     // Catch:{ zzib -> 0x0506 }
            float r5 = r19.readFloat()     // Catch:{ zzib -> 0x0506 }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r7, (java.lang.Object) r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x00ca
        L_0x01dd:
            r5 = r5 & r8
            long r7 = (long) r5     // Catch:{ zzib -> 0x0506 }
            double r15 = r19.readDouble()     // Catch:{ zzib -> 0x0506 }
            java.lang.Double r5 = java.lang.Double.valueOf(r15)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r7, (java.lang.Object) r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x00ca
        L_0x01ec:
            java.lang.Object r4 = r1.zzaz(r6)     // Catch:{ zzib -> 0x0506 }
            int r5 = r1.zzbb(r6)     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.lang.Object r7 = com.google.android.gms.internal.firebase_auth.zzkq.f(r2, r5)     // Catch:{ zzib -> 0x0506 }
            if (r7 != 0) goto L_0x0206
            com.google.android.gms.internal.firebase_auth.zziv r7 = r1.zzadj     // Catch:{ zzib -> 0x0506 }
            java.lang.Object r7 = r7.zzm(r4)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r5, (java.lang.Object) r7)     // Catch:{ zzib -> 0x0506 }
            goto L_0x021d
        L_0x0206:
            com.google.android.gms.internal.firebase_auth.zziv r8 = r1.zzadj     // Catch:{ zzib -> 0x0506 }
            boolean r8 = r8.zzk(r7)     // Catch:{ zzib -> 0x0506 }
            if (r8 == 0) goto L_0x021d
            com.google.android.gms.internal.firebase_auth.zziv r8 = r1.zzadj     // Catch:{ zzib -> 0x0506 }
            java.lang.Object r8 = r8.zzm(r4)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zziv r9 = r1.zzadj     // Catch:{ zzib -> 0x0506 }
            r9.zzc(r8, r7)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r5, (java.lang.Object) r8)     // Catch:{ zzib -> 0x0506 }
            r7 = r8
        L_0x021d:
            com.google.android.gms.internal.firebase_auth.zziv r5 = r1.zzadj     // Catch:{ zzib -> 0x0506 }
            java.util.Map r5 = r5.zzi(r7)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zziv r6 = r1.zzadj     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzit r4 = r6.zzn(r4)     // Catch:{ zzib -> 0x0506 }
            r0.zza(r5, r4, (com.google.android.gms.internal.firebase_auth.zzhf) r10)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x022e:
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzjs r6 = r1.zzay(r6)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzim r7 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r7.a(r2, r4)     // Catch:{ zzib -> 0x0506 }
            r0.zzb(r4, r6, r10)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x0240:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
        L_0x0248:
            r0.zzw(r4)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x024d:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
        L_0x0255:
            r0.zzv(r4)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x025a:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
        L_0x0262:
            r0.zzu(r4)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x0267:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
        L_0x026f:
            r0.zzt(r4)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x0274:
            com.google.android.gms.internal.firebase_auth.zzim r7 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r8 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r5 = r7.a(r2, r8)     // Catch:{ zzib -> 0x0506 }
            r0.zzs(r5)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzhy r6 = r1.zzba(r6)     // Catch:{ zzib -> 0x0506 }
        L_0x0283:
            java.lang.Object r14 = com.google.android.gms.internal.firebase_auth.zzju.a(r4, r5, r6, r14, r11)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x0289:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
        L_0x0291:
            r0.zzr(r4)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x0296:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
        L_0x029e:
            r0.zzo(r4)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x02a3:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
        L_0x02ab:
            r0.zzn(r4)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x02b0:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
        L_0x02b8:
            r0.zzm(r4)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x02bd:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
        L_0x02c5:
            r0.zzl(r4)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x02ca:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
        L_0x02d2:
            r0.zzj(r4)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x02d7:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
        L_0x02df:
            r0.zzk(r4)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x02e4:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
        L_0x02ec:
            r0.zzi(r4)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x02f1:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
        L_0x02f9:
            r0.zzh(r4)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x02fe:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0248
        L_0x0308:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0255
        L_0x0312:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0262
        L_0x031c:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x026f
        L_0x0326:
            com.google.android.gms.internal.firebase_auth.zzim r7 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r8 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r5 = r7.a(r2, r8)     // Catch:{ zzib -> 0x0506 }
            r0.zzs(r5)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzhy r6 = r1.zzba(r6)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0283
        L_0x0337:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0291
        L_0x0341:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
            r0.zzq(r4)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x034e:
            com.google.android.gms.internal.firebase_auth.zzjs r4 = r1.zzay(r6)     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzim r7 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            java.util.List r5 = r7.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
            r0.zza(r5, r4, (com.google.android.gms.internal.firebase_auth.zzhf) r10)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x035f:
            boolean r4 = zzbd(r5)     // Catch:{ zzib -> 0x0506 }
            if (r4 == 0) goto L_0x0372
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
            r0.zzp(r4)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x0372:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
            r0.readStringList(r4)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x037f:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x029e
        L_0x0389:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x02ab
        L_0x0393:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x02b8
        L_0x039d:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x02c5
        L_0x03a7:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x02d2
        L_0x03b1:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x02df
        L_0x03bb:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x02ec
        L_0x03c5:
            com.google.android.gms.internal.firebase_auth.zzim r4 = r1.zzadi     // Catch:{ zzib -> 0x0506 }
            r5 = r5 & r8
            long r5 = (long) r5     // Catch:{ zzib -> 0x0506 }
            java.util.List r4 = r4.a(r2, r5)     // Catch:{ zzib -> 0x0506 }
            goto L_0x02f9
        L_0x03cf:
            boolean r4 = r1.zza(r2, (int) r6)     // Catch:{ zzib -> 0x0506 }
            if (r4 == 0) goto L_0x03ed
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            java.lang.Object r7 = com.google.android.gms.internal.firebase_auth.zzkq.f(r2, r4)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzjs r6 = r1.zzay(r6)     // Catch:{ zzib -> 0x0506 }
            java.lang.Object r6 = r0.zzb(r6, r10)     // Catch:{ zzib -> 0x0506 }
            java.lang.Object r6 = com.google.android.gms.internal.firebase_auth.zzht.a((java.lang.Object) r7, (java.lang.Object) r6)     // Catch:{ zzib -> 0x0506 }
        L_0x03e8:
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r4, (java.lang.Object) r6)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x03ed:
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzjs r7 = r1.zzay(r6)     // Catch:{ zzib -> 0x0506 }
            java.lang.Object r7 = r0.zzb(r7, r10)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r4, (java.lang.Object) r7)     // Catch:{ zzib -> 0x0506 }
        L_0x03fb:
            r1.zzb(r2, (int) r6)     // Catch:{ zzib -> 0x0506 }
            goto L_0x0011
        L_0x0400:
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            long r7 = r19.zzgw()     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r4, (long) r7)     // Catch:{ zzib -> 0x0506 }
            goto L_0x03fb
        L_0x040b:
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            int r7 = r19.zzgv()     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r4, (int) r7)     // Catch:{ zzib -> 0x0506 }
            goto L_0x03fb
        L_0x0416:
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            long r7 = r19.zzgu()     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r4, (long) r7)     // Catch:{ zzib -> 0x0506 }
            goto L_0x03fb
        L_0x0421:
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            int r7 = r19.zzgt()     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r4, (int) r7)     // Catch:{ zzib -> 0x0506 }
            goto L_0x03fb
        L_0x042c:
            int r7 = r19.zzgs()     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzhy r9 = r1.zzba(r6)     // Catch:{ zzib -> 0x0506 }
            if (r9 == 0) goto L_0x043c
            boolean r9 = r9.zzd(r7)     // Catch:{ zzib -> 0x0506 }
            if (r9 == 0) goto L_0x0118
        L_0x043c:
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r4, (int) r7)     // Catch:{ zzib -> 0x0506 }
            goto L_0x03fb
        L_0x0443:
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            int r7 = r19.zzgr()     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r4, (int) r7)     // Catch:{ zzib -> 0x0506 }
            goto L_0x03fb
        L_0x044e:
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzgf r7 = r19.zzgq()     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r4, (java.lang.Object) r7)     // Catch:{ zzib -> 0x0506 }
            goto L_0x03fb
        L_0x0459:
            boolean r4 = r1.zza(r2, (int) r6)     // Catch:{ zzib -> 0x0506 }
            if (r4 == 0) goto L_0x0474
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            java.lang.Object r7 = com.google.android.gms.internal.firebase_auth.zzkq.f(r2, r4)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzjs r6 = r1.zzay(r6)     // Catch:{ zzib -> 0x0506 }
            java.lang.Object r6 = r0.zza(r6, r10)     // Catch:{ zzib -> 0x0506 }
            java.lang.Object r6 = com.google.android.gms.internal.firebase_auth.zzht.a((java.lang.Object) r7, (java.lang.Object) r6)     // Catch:{ zzib -> 0x0506 }
            goto L_0x03e8
        L_0x0474:
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzjs r7 = r1.zzay(r6)     // Catch:{ zzib -> 0x0506 }
            java.lang.Object r7 = r0.zza(r7, r10)     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r4, (java.lang.Object) r7)     // Catch:{ zzib -> 0x0506 }
            goto L_0x03fb
        L_0x0484:
            r1.zza((java.lang.Object) r2, (int) r5, (com.google.android.gms.internal.firebase_auth.zzjp) r0)     // Catch:{ zzib -> 0x0506 }
            goto L_0x03fb
        L_0x0489:
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            boolean r7 = r19.zzgo()     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r4, (boolean) r7)     // Catch:{ zzib -> 0x0506 }
            goto L_0x03fb
        L_0x0495:
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            int r7 = r19.zzgn()     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r4, (int) r7)     // Catch:{ zzib -> 0x0506 }
            goto L_0x03fb
        L_0x04a1:
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            long r7 = r19.zzgm()     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r4, (long) r7)     // Catch:{ zzib -> 0x0506 }
            goto L_0x03fb
        L_0x04ad:
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            int r7 = r19.zzgl()     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r4, (int) r7)     // Catch:{ zzib -> 0x0506 }
            goto L_0x03fb
        L_0x04b9:
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            long r7 = r19.zzgj()     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r4, (long) r7)     // Catch:{ zzib -> 0x0506 }
            goto L_0x03fb
        L_0x04c5:
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            long r7 = r19.zzgk()     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r4, (long) r7)     // Catch:{ zzib -> 0x0506 }
            goto L_0x03fb
        L_0x04d1:
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            float r7 = r19.readFloat()     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r4, (float) r7)     // Catch:{ zzib -> 0x0506 }
            goto L_0x03fb
        L_0x04dd:
            r4 = r5 & r8
            long r4 = (long) r4     // Catch:{ zzib -> 0x0506 }
            double r7 = r19.readDouble()     // Catch:{ zzib -> 0x0506 }
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r2, (long) r4, (double) r7)     // Catch:{ zzib -> 0x0506 }
            goto L_0x03fb
        L_0x04e9:
            boolean r4 = r11.a(r14, (com.google.android.gms.internal.firebase_auth.zzjp) r0)     // Catch:{ zzib -> 0x0506 }
            if (r4 != 0) goto L_0x0011
            int r0 = r1.zzadf
        L_0x04f1:
            int r3 = r1.zzadg
            if (r0 >= r3) goto L_0x0500
            int[] r3 = r1.zzade
            r3 = r3[r0]
            java.lang.Object r14 = r1.zza((java.lang.Object) r2, (int) r3, r14, r11)
            int r0 = r0 + 1
            goto L_0x04f1
        L_0x0500:
            if (r14 == 0) goto L_0x0505
            r11.b((java.lang.Object) r2, r14)
        L_0x0505:
            return
        L_0x0506:
            r11.a((com.google.android.gms.internal.firebase_auth.zzjp) r0)     // Catch:{ all -> 0x052d }
            if (r14 != 0) goto L_0x0510
            java.lang.Object r4 = r11.c(r2)     // Catch:{ all -> 0x052d }
            r14 = r4
        L_0x0510:
            boolean r4 = r11.a(r14, (com.google.android.gms.internal.firebase_auth.zzjp) r0)     // Catch:{ all -> 0x052d }
            if (r4 != 0) goto L_0x0011
            int r0 = r1.zzadf
        L_0x0518:
            int r3 = r1.zzadg
            if (r0 >= r3) goto L_0x0527
            int[] r3 = r1.zzade
            r3 = r3[r0]
            java.lang.Object r14 = r1.zza((java.lang.Object) r2, (int) r3, r14, r11)
            int r0 = r0 + 1
            goto L_0x0518
        L_0x0527:
            if (r14 == 0) goto L_0x052c
            r11.b((java.lang.Object) r2, r14)
        L_0x052c:
            return
        L_0x052d:
            r0 = move-exception
            int r3 = r1.zzadf
        L_0x0530:
            int r4 = r1.zzadg
            if (r3 >= r4) goto L_0x053f
            int[] r4 = r1.zzade
            r4 = r4[r3]
            java.lang.Object r14 = r1.zza((java.lang.Object) r2, (int) r4, r14, r11)
            int r3 = r3 + 1
            goto L_0x0530
        L_0x053f:
            if (r14 == 0) goto L_0x0544
            r11.b((java.lang.Object) r2, r14)
        L_0x0544:
            throw r0
        L_0x0545:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzjg.zza(java.lang.Object, com.google.android.gms.internal.firebase_auth.zzjp, com.google.android.gms.internal.firebase_auth.zzhf):void");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0387, code lost:
        r15.zzb(r9, com.google.android.gms.internal.firebase_auth.zzkq.f(r14, (long) (r8 & 1048575)), zzay(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x03a2, code lost:
        r15.zzb(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x03b3, code lost:
        r15.zzh(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x03c4, code lost:
        r15.zzj(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x03d5, code lost:
        r15.zzp(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x03e6, code lost:
        r15.zzq(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x03f7, code lost:
        r15.zzg(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0402, code lost:
        r15.zza(r9, (com.google.android.gms.internal.firebase_auth.zzgf) com.google.android.gms.internal.firebase_auth.zzkq.f(r14, (long) (r8 & 1048575)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0415, code lost:
        r15.zza(r9, com.google.android.gms.internal.firebase_auth.zzkq.f(r14, (long) (r8 & 1048575)), zzay(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x042a, code lost:
        zza(r9, com.google.android.gms.internal.firebase_auth.zzkq.f(r14, (long) (r8 & 1048575)), r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0441, code lost:
        r15.zzc(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0452, code lost:
        r15.zzi(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0462, code lost:
        r15.zzc(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0472, code lost:
        r15.zzf(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0482, code lost:
        r15.zza(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0492, code lost:
        r15.zzi(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x04a2, code lost:
        r15.zza(r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x04b2, code lost:
        r15.zza(r9, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:283:0x0847, code lost:
        r15.zzb(r10, com.google.android.gms.internal.firebase_auth.zzkq.f(r14, (long) (r9 & 1048575)), zzay(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:287:0x0862, code lost:
        r15.zzb(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:291:0x0873, code lost:
        r15.zzh(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x0884, code lost:
        r15.zzj(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:299:0x0895, code lost:
        r15.zzp(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:303:0x08a6, code lost:
        r15.zzq(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x08b7, code lost:
        r15.zzg(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:310:0x08c2, code lost:
        r15.zza(r10, (com.google.android.gms.internal.firebase_auth.zzgf) com.google.android.gms.internal.firebase_auth.zzkq.f(r14, (long) (r9 & 1048575)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x08d5, code lost:
        r15.zza(r10, com.google.android.gms.internal.firebase_auth.zzkq.f(r14, (long) (r9 & 1048575)), zzay(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:316:0x08ea, code lost:
        zza(r10, com.google.android.gms.internal.firebase_auth.zzkq.f(r14, (long) (r9 & 1048575)), r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:320:0x0901, code lost:
        r15.zzc(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:324:0x0912, code lost:
        r15.zzi(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:328:0x0922, code lost:
        r15.zzc(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:332:0x0932, code lost:
        r15.zzf(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:336:0x0942, code lost:
        r15.zza(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:340:0x0952, code lost:
        r15.zzi(r10, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:344:0x0962, code lost:
        r15.zza(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x0972, code lost:
        r15.zza(r10, r11);
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x04bb  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x04fb  */
    /* JADX WARNING: Removed duplicated region for block: B:351:0x097b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r14, com.google.android.gms.internal.firebase_auth.zzlh r15) {
        /*
            r13 = this;
            int r0 = r15.zzhl()
            int r1 = com.google.android.gms.internal.firebase_auth.zzhs.zzd.zzaaz
            r2 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 != r1) goto L_0x04d1
            com.google.android.gms.internal.firebase_auth.zzkk<?, ?> r0 = r13.zzacs
            zza(r0, r14, (com.google.android.gms.internal.firebase_auth.zzlh) r15)
            boolean r0 = r13.zzact
            if (r0 == 0) goto L_0x0032
            com.google.android.gms.internal.firebase_auth.zzhh<?> r0 = r13.zzacu
            com.google.android.gms.internal.firebase_auth.zzhi r0 = r0.a((java.lang.Object) r14)
            com.google.android.gms.internal.firebase_auth.zzjt<FieldDescriptorType, java.lang.Object> r1 = r0.a
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0032
            java.util.Iterator r0 = r0.a()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0034
        L_0x0032:
            r0 = r3
            r1 = r0
        L_0x0034:
            int[] r7 = r13.zzacx
            int r7 = r7.length
            int r7 = r7 + -3
        L_0x0039:
            if (r7 < 0) goto L_0x04b9
            int r8 = r13.zzbb(r7)
            int[] r9 = r13.zzacx
            r9 = r9[r7]
        L_0x0043:
            if (r1 == 0) goto L_0x0061
            com.google.android.gms.internal.firebase_auth.zzhh<?> r10 = r13.zzacu
            int r10 = r10.a((java.util.Map.Entry<?, ?>) r1)
            if (r10 <= r9) goto L_0x0061
            com.google.android.gms.internal.firebase_auth.zzhh<?> r10 = r13.zzacu
            r10.a(r15, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x005f
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0043
        L_0x005f:
            r1 = r3
            goto L_0x0043
        L_0x0061:
            r10 = r8 & r2
            int r10 = r10 >>> 20
            switch(r10) {
                case 0: goto L_0x04a6;
                case 1: goto L_0x0496;
                case 2: goto L_0x0486;
                case 3: goto L_0x0476;
                case 4: goto L_0x0466;
                case 5: goto L_0x0456;
                case 6: goto L_0x0446;
                case 7: goto L_0x0435;
                case 8: goto L_0x0424;
                case 9: goto L_0x040f;
                case 10: goto L_0x03fc;
                case 11: goto L_0x03eb;
                case 12: goto L_0x03da;
                case 13: goto L_0x03c9;
                case 14: goto L_0x03b8;
                case 15: goto L_0x03a7;
                case 16: goto L_0x0396;
                case 17: goto L_0x0381;
                case 18: goto L_0x0370;
                case 19: goto L_0x035f;
                case 20: goto L_0x034e;
                case 21: goto L_0x033d;
                case 22: goto L_0x032c;
                case 23: goto L_0x031b;
                case 24: goto L_0x030a;
                case 25: goto L_0x02f9;
                case 26: goto L_0x02e8;
                case 27: goto L_0x02d3;
                case 28: goto L_0x02c2;
                case 29: goto L_0x02b1;
                case 30: goto L_0x02a0;
                case 31: goto L_0x028f;
                case 32: goto L_0x027e;
                case 33: goto L_0x026d;
                case 34: goto L_0x025c;
                case 35: goto L_0x024b;
                case 36: goto L_0x023a;
                case 37: goto L_0x0229;
                case 38: goto L_0x0218;
                case 39: goto L_0x0207;
                case 40: goto L_0x01f6;
                case 41: goto L_0x01e5;
                case 42: goto L_0x01d4;
                case 43: goto L_0x01c3;
                case 44: goto L_0x01b2;
                case 45: goto L_0x01a1;
                case 46: goto L_0x0190;
                case 47: goto L_0x017f;
                case 48: goto L_0x016e;
                case 49: goto L_0x0159;
                case 50: goto L_0x014e;
                case 51: goto L_0x0140;
                case 52: goto L_0x0132;
                case 53: goto L_0x0124;
                case 54: goto L_0x0116;
                case 55: goto L_0x0108;
                case 56: goto L_0x00fa;
                case 57: goto L_0x00ec;
                case 58: goto L_0x00de;
                case 59: goto L_0x00d6;
                case 60: goto L_0x00ce;
                case 61: goto L_0x00c6;
                case 62: goto L_0x00b8;
                case 63: goto L_0x00aa;
                case 64: goto L_0x009c;
                case 65: goto L_0x008e;
                case 66: goto L_0x0080;
                case 67: goto L_0x0072;
                case 68: goto L_0x006a;
                default: goto L_0x0068;
            }
        L_0x0068:
            goto L_0x04b5
        L_0x006a:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b5
            goto L_0x0387
        L_0x0072:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            goto L_0x03a2
        L_0x0080:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            goto L_0x03b3
        L_0x008e:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            goto L_0x03c4
        L_0x009c:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            goto L_0x03d5
        L_0x00aa:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            goto L_0x03e6
        L_0x00b8:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            goto L_0x03f7
        L_0x00c6:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b5
            goto L_0x0402
        L_0x00ce:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b5
            goto L_0x0415
        L_0x00d6:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b5
            goto L_0x042a
        L_0x00de:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = zzj(r14, r10)
            goto L_0x0441
        L_0x00ec:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            goto L_0x0452
        L_0x00fa:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            goto L_0x0462
        L_0x0108:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            goto L_0x0472
        L_0x0116:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            goto L_0x0482
        L_0x0124:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            goto L_0x0492
        L_0x0132:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = zzg(r14, r10)
            goto L_0x04a2
        L_0x0140:
            boolean r10 = r13.zza(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = zzf(r14, r10)
            goto L_0x04b2
        L_0x014e:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            r13.zza((com.google.android.gms.internal.firebase_auth.zzlh) r15, (int) r9, (java.lang.Object) r8, (int) r7)
            goto L_0x04b5
        L_0x0159:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzjs r10 = r13.zzay(r7)
            com.google.android.gms.internal.firebase_auth.zzju.zzb((int) r9, (java.util.List<?>) r8, (com.google.android.gms.internal.firebase_auth.zzlh) r15, (com.google.android.gms.internal.firebase_auth.zzjs) r10)
            goto L_0x04b5
        L_0x016e:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zze(r9, r8, r15, r4)
            goto L_0x04b5
        L_0x017f:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzj(r9, r8, r15, r4)
            goto L_0x04b5
        L_0x0190:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzg(r9, r8, r15, r4)
            goto L_0x04b5
        L_0x01a1:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzl(r9, r8, r15, r4)
            goto L_0x04b5
        L_0x01b2:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzm(r9, r8, r15, r4)
            goto L_0x04b5
        L_0x01c3:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzi(r9, r8, r15, r4)
            goto L_0x04b5
        L_0x01d4:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzn(r9, r8, r15, r4)
            goto L_0x04b5
        L_0x01e5:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzk(r9, r8, r15, r4)
            goto L_0x04b5
        L_0x01f6:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzf(r9, r8, r15, r4)
            goto L_0x04b5
        L_0x0207:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzh(r9, r8, r15, r4)
            goto L_0x04b5
        L_0x0218:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzd(r9, r8, r15, r4)
            goto L_0x04b5
        L_0x0229:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzc(r9, r8, r15, r4)
            goto L_0x04b5
        L_0x023a:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzb((int) r9, (java.util.List<java.lang.Float>) r8, (com.google.android.gms.internal.firebase_auth.zzlh) r15, (boolean) r4)
            goto L_0x04b5
        L_0x024b:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zza((int) r9, (java.util.List<java.lang.Double>) r8, (com.google.android.gms.internal.firebase_auth.zzlh) r15, (boolean) r4)
            goto L_0x04b5
        L_0x025c:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zze(r9, r8, r15, r5)
            goto L_0x04b5
        L_0x026d:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzj(r9, r8, r15, r5)
            goto L_0x04b5
        L_0x027e:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzg(r9, r8, r15, r5)
            goto L_0x04b5
        L_0x028f:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzl(r9, r8, r15, r5)
            goto L_0x04b5
        L_0x02a0:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzm(r9, r8, r15, r5)
            goto L_0x04b5
        L_0x02b1:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzi(r9, r8, r15, r5)
            goto L_0x04b5
        L_0x02c2:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzb(r9, r8, r15)
            goto L_0x04b5
        L_0x02d3:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzjs r10 = r13.zzay(r7)
            com.google.android.gms.internal.firebase_auth.zzju.zza((int) r9, (java.util.List<?>) r8, (com.google.android.gms.internal.firebase_auth.zzlh) r15, (com.google.android.gms.internal.firebase_auth.zzjs) r10)
            goto L_0x04b5
        L_0x02e8:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zza(r9, r8, r15)
            goto L_0x04b5
        L_0x02f9:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzn(r9, r8, r15, r5)
            goto L_0x04b5
        L_0x030a:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzk(r9, r8, r15, r5)
            goto L_0x04b5
        L_0x031b:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzf(r9, r8, r15, r5)
            goto L_0x04b5
        L_0x032c:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzh(r9, r8, r15, r5)
            goto L_0x04b5
        L_0x033d:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzd(r9, r8, r15, r5)
            goto L_0x04b5
        L_0x034e:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzc(r9, r8, r15, r5)
            goto L_0x04b5
        L_0x035f:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zzb((int) r9, (java.util.List<java.lang.Float>) r8, (com.google.android.gms.internal.firebase_auth.zzlh) r15, (boolean) r5)
            goto L_0x04b5
        L_0x0370:
            int[] r9 = r13.zzacx
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.firebase_auth.zzju.zza((int) r9, (java.util.List<java.lang.Double>) r8, (com.google.android.gms.internal.firebase_auth.zzlh) r15, (boolean) r5)
            goto L_0x04b5
        L_0x0381:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b5
        L_0x0387:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            com.google.android.gms.internal.firebase_auth.zzjs r10 = r13.zzay(r7)
            r15.zzb((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.firebase_auth.zzjs) r10)
            goto L_0x04b5
        L_0x0396:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.firebase_auth.zzkq.b(r14, r10)
        L_0x03a2:
            r15.zzb((int) r9, (long) r10)
            goto L_0x04b5
        L_0x03a7:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r14, (long) r10)
        L_0x03b3:
            r15.zzh(r9, r8)
            goto L_0x04b5
        L_0x03b8:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.firebase_auth.zzkq.b(r14, r10)
        L_0x03c4:
            r15.zzj(r9, r10)
            goto L_0x04b5
        L_0x03c9:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r14, (long) r10)
        L_0x03d5:
            r15.zzp(r9, r8)
            goto L_0x04b5
        L_0x03da:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r14, (long) r10)
        L_0x03e6:
            r15.zzq(r9, r8)
            goto L_0x04b5
        L_0x03eb:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r14, (long) r10)
        L_0x03f7:
            r15.zzg(r9, r8)
            goto L_0x04b5
        L_0x03fc:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b5
        L_0x0402:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            com.google.android.gms.internal.firebase_auth.zzgf r8 = (com.google.android.gms.internal.firebase_auth.zzgf) r8
            r15.zza((int) r9, (com.google.android.gms.internal.firebase_auth.zzgf) r8)
            goto L_0x04b5
        L_0x040f:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b5
        L_0x0415:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            com.google.android.gms.internal.firebase_auth.zzjs r10 = r13.zzay(r7)
            r15.zza((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.firebase_auth.zzjs) r10)
            goto L_0x04b5
        L_0x0424:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b5
        L_0x042a:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r10)
            zza((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.firebase_auth.zzlh) r15)
            goto L_0x04b5
        L_0x0435:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = com.google.android.gms.internal.firebase_auth.zzkq.c(r14, r10)
        L_0x0441:
            r15.zzc((int) r9, (boolean) r8)
            goto L_0x04b5
        L_0x0446:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r14, (long) r10)
        L_0x0452:
            r15.zzi((int) r9, (int) r8)
            goto L_0x04b5
        L_0x0456:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.firebase_auth.zzkq.b(r14, r10)
        L_0x0462:
            r15.zzc((int) r9, (long) r10)
            goto L_0x04b5
        L_0x0466:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r14, (long) r10)
        L_0x0472:
            r15.zzf(r9, r8)
            goto L_0x04b5
        L_0x0476:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.firebase_auth.zzkq.b(r14, r10)
        L_0x0482:
            r15.zza((int) r9, (long) r10)
            goto L_0x04b5
        L_0x0486:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.firebase_auth.zzkq.b(r14, r10)
        L_0x0492:
            r15.zzi((int) r9, (long) r10)
            goto L_0x04b5
        L_0x0496:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = com.google.android.gms.internal.firebase_auth.zzkq.d(r14, r10)
        L_0x04a2:
            r15.zza((int) r9, (float) r8)
            goto L_0x04b5
        L_0x04a6:
            boolean r10 = r13.zza(r14, (int) r7)
            if (r10 == 0) goto L_0x04b5
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = com.google.android.gms.internal.firebase_auth.zzkq.e(r14, r10)
        L_0x04b2:
            r15.zza((int) r9, (double) r10)
        L_0x04b5:
            int r7 = r7 + -3
            goto L_0x0039
        L_0x04b9:
            if (r1 == 0) goto L_0x04d0
            com.google.android.gms.internal.firebase_auth.zzhh<?> r14 = r13.zzacu
            r14.a(r15, r1)
            boolean r14 = r0.hasNext()
            if (r14 == 0) goto L_0x04ce
            java.lang.Object r14 = r0.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            r1 = r14
            goto L_0x04b9
        L_0x04ce:
            r1 = r3
            goto L_0x04b9
        L_0x04d0:
            return
        L_0x04d1:
            boolean r0 = r13.zzadc
            if (r0 == 0) goto L_0x0996
            boolean r0 = r13.zzact
            if (r0 == 0) goto L_0x04f2
            com.google.android.gms.internal.firebase_auth.zzhh<?> r0 = r13.zzacu
            com.google.android.gms.internal.firebase_auth.zzhi r0 = r0.a((java.lang.Object) r14)
            com.google.android.gms.internal.firebase_auth.zzjt<FieldDescriptorType, java.lang.Object> r1 = r0.a
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x04f2
            java.util.Iterator r0 = r0.iterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x04f4
        L_0x04f2:
            r0 = r3
            r1 = r0
        L_0x04f4:
            int[] r7 = r13.zzacx
            int r7 = r7.length
            r8 = r1
            r1 = 0
        L_0x04f9:
            if (r1 >= r7) goto L_0x0979
            int r9 = r13.zzbb(r1)
            int[] r10 = r13.zzacx
            r10 = r10[r1]
        L_0x0503:
            if (r8 == 0) goto L_0x0521
            com.google.android.gms.internal.firebase_auth.zzhh<?> r11 = r13.zzacu
            int r11 = r11.a((java.util.Map.Entry<?, ?>) r8)
            if (r11 > r10) goto L_0x0521
            com.google.android.gms.internal.firebase_auth.zzhh<?> r11 = r13.zzacu
            r11.a(r15, r8)
            boolean r8 = r0.hasNext()
            if (r8 == 0) goto L_0x051f
            java.lang.Object r8 = r0.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            goto L_0x0503
        L_0x051f:
            r8 = r3
            goto L_0x0503
        L_0x0521:
            r11 = r9 & r2
            int r11 = r11 >>> 20
            switch(r11) {
                case 0: goto L_0x0966;
                case 1: goto L_0x0956;
                case 2: goto L_0x0946;
                case 3: goto L_0x0936;
                case 4: goto L_0x0926;
                case 5: goto L_0x0916;
                case 6: goto L_0x0906;
                case 7: goto L_0x08f5;
                case 8: goto L_0x08e4;
                case 9: goto L_0x08cf;
                case 10: goto L_0x08bc;
                case 11: goto L_0x08ab;
                case 12: goto L_0x089a;
                case 13: goto L_0x0889;
                case 14: goto L_0x0878;
                case 15: goto L_0x0867;
                case 16: goto L_0x0856;
                case 17: goto L_0x0841;
                case 18: goto L_0x0830;
                case 19: goto L_0x081f;
                case 20: goto L_0x080e;
                case 21: goto L_0x07fd;
                case 22: goto L_0x07ec;
                case 23: goto L_0x07db;
                case 24: goto L_0x07ca;
                case 25: goto L_0x07b9;
                case 26: goto L_0x07a8;
                case 27: goto L_0x0793;
                case 28: goto L_0x0782;
                case 29: goto L_0x0771;
                case 30: goto L_0x0760;
                case 31: goto L_0x074f;
                case 32: goto L_0x073e;
                case 33: goto L_0x072d;
                case 34: goto L_0x071c;
                case 35: goto L_0x070b;
                case 36: goto L_0x06fa;
                case 37: goto L_0x06e9;
                case 38: goto L_0x06d8;
                case 39: goto L_0x06c7;
                case 40: goto L_0x06b6;
                case 41: goto L_0x06a5;
                case 42: goto L_0x0694;
                case 43: goto L_0x0683;
                case 44: goto L_0x0672;
                case 45: goto L_0x0661;
                case 46: goto L_0x0650;
                case 47: goto L_0x063f;
                case 48: goto L_0x062e;
                case 49: goto L_0x0619;
                case 50: goto L_0x060e;
                case 51: goto L_0x0600;
                case 52: goto L_0x05f2;
                case 53: goto L_0x05e4;
                case 54: goto L_0x05d6;
                case 55: goto L_0x05c8;
                case 56: goto L_0x05ba;
                case 57: goto L_0x05ac;
                case 58: goto L_0x059e;
                case 59: goto L_0x0596;
                case 60: goto L_0x058e;
                case 61: goto L_0x0586;
                case 62: goto L_0x0578;
                case 63: goto L_0x056a;
                case 64: goto L_0x055c;
                case 65: goto L_0x054e;
                case 66: goto L_0x0540;
                case 67: goto L_0x0532;
                case 68: goto L_0x052a;
                default: goto L_0x0528;
            }
        L_0x0528:
            goto L_0x0975
        L_0x052a:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0975
            goto L_0x0847
        L_0x0532:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            goto L_0x0862
        L_0x0540:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            goto L_0x0873
        L_0x054e:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            goto L_0x0884
        L_0x055c:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            goto L_0x0895
        L_0x056a:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            goto L_0x08a6
        L_0x0578:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            goto L_0x08b7
        L_0x0586:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0975
            goto L_0x08c2
        L_0x058e:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0975
            goto L_0x08d5
        L_0x0596:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0975
            goto L_0x08ea
        L_0x059e:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = zzj(r14, r11)
            goto L_0x0901
        L_0x05ac:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            goto L_0x0912
        L_0x05ba:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            goto L_0x0922
        L_0x05c8:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            goto L_0x0932
        L_0x05d6:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            goto L_0x0942
        L_0x05e4:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            goto L_0x0952
        L_0x05f2:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = zzg(r14, r11)
            goto L_0x0962
        L_0x0600:
            boolean r11 = r13.zza(r14, (int) r10, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = zzf(r14, r11)
            goto L_0x0972
        L_0x060e:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            r13.zza((com.google.android.gms.internal.firebase_auth.zzlh) r15, (int) r10, (java.lang.Object) r9, (int) r1)
            goto L_0x0975
        L_0x0619:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzjs r11 = r13.zzay(r1)
            com.google.android.gms.internal.firebase_auth.zzju.zzb((int) r10, (java.util.List<?>) r9, (com.google.android.gms.internal.firebase_auth.zzlh) r15, (com.google.android.gms.internal.firebase_auth.zzjs) r11)
            goto L_0x0975
        L_0x062e:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zze(r10, r9, r15, r4)
            goto L_0x0975
        L_0x063f:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzj(r10, r9, r15, r4)
            goto L_0x0975
        L_0x0650:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzg(r10, r9, r15, r4)
            goto L_0x0975
        L_0x0661:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzl(r10, r9, r15, r4)
            goto L_0x0975
        L_0x0672:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzm(r10, r9, r15, r4)
            goto L_0x0975
        L_0x0683:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzi(r10, r9, r15, r4)
            goto L_0x0975
        L_0x0694:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzn(r10, r9, r15, r4)
            goto L_0x0975
        L_0x06a5:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzk(r10, r9, r15, r4)
            goto L_0x0975
        L_0x06b6:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzf(r10, r9, r15, r4)
            goto L_0x0975
        L_0x06c7:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzh(r10, r9, r15, r4)
            goto L_0x0975
        L_0x06d8:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzd(r10, r9, r15, r4)
            goto L_0x0975
        L_0x06e9:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzc(r10, r9, r15, r4)
            goto L_0x0975
        L_0x06fa:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzb((int) r10, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.firebase_auth.zzlh) r15, (boolean) r4)
            goto L_0x0975
        L_0x070b:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zza((int) r10, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.firebase_auth.zzlh) r15, (boolean) r4)
            goto L_0x0975
        L_0x071c:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zze(r10, r9, r15, r5)
            goto L_0x0975
        L_0x072d:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzj(r10, r9, r15, r5)
            goto L_0x0975
        L_0x073e:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzg(r10, r9, r15, r5)
            goto L_0x0975
        L_0x074f:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzl(r10, r9, r15, r5)
            goto L_0x0975
        L_0x0760:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzm(r10, r9, r15, r5)
            goto L_0x0975
        L_0x0771:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzi(r10, r9, r15, r5)
            goto L_0x0975
        L_0x0782:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzb(r10, r9, r15)
            goto L_0x0975
        L_0x0793:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzjs r11 = r13.zzay(r1)
            com.google.android.gms.internal.firebase_auth.zzju.zza((int) r10, (java.util.List<?>) r9, (com.google.android.gms.internal.firebase_auth.zzlh) r15, (com.google.android.gms.internal.firebase_auth.zzjs) r11)
            goto L_0x0975
        L_0x07a8:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zza(r10, r9, r15)
            goto L_0x0975
        L_0x07b9:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzn(r10, r9, r15, r5)
            goto L_0x0975
        L_0x07ca:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzk(r10, r9, r15, r5)
            goto L_0x0975
        L_0x07db:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzf(r10, r9, r15, r5)
            goto L_0x0975
        L_0x07ec:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzh(r10, r9, r15, r5)
            goto L_0x0975
        L_0x07fd:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzd(r10, r9, r15, r5)
            goto L_0x0975
        L_0x080e:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzc(r10, r9, r15, r5)
            goto L_0x0975
        L_0x081f:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zzb((int) r10, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.firebase_auth.zzlh) r15, (boolean) r5)
            goto L_0x0975
        L_0x0830:
            int[] r10 = r13.zzacx
            r10 = r10[r1]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.firebase_auth.zzju.zza((int) r10, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.firebase_auth.zzlh) r15, (boolean) r5)
            goto L_0x0975
        L_0x0841:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0975
        L_0x0847:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            com.google.android.gms.internal.firebase_auth.zzjs r11 = r13.zzay(r1)
            r15.zzb((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.firebase_auth.zzjs) r11)
            goto L_0x0975
        L_0x0856:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.firebase_auth.zzkq.b(r14, r11)
        L_0x0862:
            r15.zzb((int) r10, (long) r11)
            goto L_0x0975
        L_0x0867:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r14, (long) r11)
        L_0x0873:
            r15.zzh(r10, r9)
            goto L_0x0975
        L_0x0878:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.firebase_auth.zzkq.b(r14, r11)
        L_0x0884:
            r15.zzj(r10, r11)
            goto L_0x0975
        L_0x0889:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r14, (long) r11)
        L_0x0895:
            r15.zzp(r10, r9)
            goto L_0x0975
        L_0x089a:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r14, (long) r11)
        L_0x08a6:
            r15.zzq(r10, r9)
            goto L_0x0975
        L_0x08ab:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r14, (long) r11)
        L_0x08b7:
            r15.zzg(r10, r9)
            goto L_0x0975
        L_0x08bc:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0975
        L_0x08c2:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            com.google.android.gms.internal.firebase_auth.zzgf r9 = (com.google.android.gms.internal.firebase_auth.zzgf) r9
            r15.zza((int) r10, (com.google.android.gms.internal.firebase_auth.zzgf) r9)
            goto L_0x0975
        L_0x08cf:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0975
        L_0x08d5:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            com.google.android.gms.internal.firebase_auth.zzjs r11 = r13.zzay(r1)
            r15.zza((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.firebase_auth.zzjs) r11)
            goto L_0x0975
        L_0x08e4:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0975
        L_0x08ea:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r11)
            zza((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.firebase_auth.zzlh) r15)
            goto L_0x0975
        L_0x08f5:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = com.google.android.gms.internal.firebase_auth.zzkq.c(r14, r11)
        L_0x0901:
            r15.zzc((int) r10, (boolean) r9)
            goto L_0x0975
        L_0x0906:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r14, (long) r11)
        L_0x0912:
            r15.zzi((int) r10, (int) r9)
            goto L_0x0975
        L_0x0916:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.firebase_auth.zzkq.b(r14, r11)
        L_0x0922:
            r15.zzc((int) r10, (long) r11)
            goto L_0x0975
        L_0x0926:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r14, (long) r11)
        L_0x0932:
            r15.zzf(r10, r9)
            goto L_0x0975
        L_0x0936:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.firebase_auth.zzkq.b(r14, r11)
        L_0x0942:
            r15.zza((int) r10, (long) r11)
            goto L_0x0975
        L_0x0946:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.firebase_auth.zzkq.b(r14, r11)
        L_0x0952:
            r15.zzi((int) r10, (long) r11)
            goto L_0x0975
        L_0x0956:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = com.google.android.gms.internal.firebase_auth.zzkq.d(r14, r11)
        L_0x0962:
            r15.zza((int) r10, (float) r9)
            goto L_0x0975
        L_0x0966:
            boolean r11 = r13.zza(r14, (int) r1)
            if (r11 == 0) goto L_0x0975
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = com.google.android.gms.internal.firebase_auth.zzkq.e(r14, r11)
        L_0x0972:
            r15.zza((int) r10, (double) r11)
        L_0x0975:
            int r1 = r1 + 3
            goto L_0x04f9
        L_0x0979:
            if (r8 == 0) goto L_0x0990
            com.google.android.gms.internal.firebase_auth.zzhh<?> r1 = r13.zzacu
            r1.a(r15, r8)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x098e
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            r8 = r1
            goto L_0x0979
        L_0x098e:
            r8 = r3
            goto L_0x0979
        L_0x0990:
            com.google.android.gms.internal.firebase_auth.zzkk<?, ?> r0 = r13.zzacs
            zza(r0, r14, (com.google.android.gms.internal.firebase_auth.zzlh) r15)
            return
        L_0x0996:
            r13.zzb(r14, (com.google.android.gms.internal.firebase_auth.zzlh) r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzjg.zza(java.lang.Object, com.google.android.gms.internal.firebase_auth.zzlh):void");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
        com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r7, r2, com.google.android.gms.internal.firebase_auth.zzkq.f(r8, r2));
        zzb(r7, r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0089, code lost:
        com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r7, r2, com.google.android.gms.internal.firebase_auth.zzkq.f(r8, r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b3, code lost:
        com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r7, r2, com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r8, r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00c8, code lost:
        com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r7, r2, com.google.android.gms.internal.firebase_auth.zzkq.b(r8, r2));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00eb, code lost:
        zzb(r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ee, code lost:
        r0 = r0 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(T r7, T r8) {
        /*
            r6 = this;
            if (r8 == 0) goto L_0x0105
            r0 = 0
        L_0x0003:
            int[] r1 = r6.zzacx
            int r1 = r1.length
            if (r0 >= r1) goto L_0x00f2
            int r1 = r6.zzbb(r0)
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r2 & r1
            long r2 = (long) r2
            int[] r4 = r6.zzacx
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
            java.lang.Object r1 = com.google.android.gms.internal.firebase_auth.zzkq.f(r8, r2)
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r7, (long) r2, (java.lang.Object) r1)
            r6.zzb(r7, (int) r4, (int) r0)
            goto L_0x00ee
        L_0x003d:
            com.google.android.gms.internal.firebase_auth.zziv r1 = r6.zzadj
            com.google.android.gms.internal.firebase_auth.zzju.a((com.google.android.gms.internal.firebase_auth.zziv) r1, r7, r8, (long) r2)
            goto L_0x00ee
        L_0x0044:
            com.google.android.gms.internal.firebase_auth.zzim r1 = r6.zzadi
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
            java.lang.Object r1 = com.google.android.gms.internal.firebase_auth.zzkq.f(r8, r2)
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r7, (long) r2, (java.lang.Object) r1)
            goto L_0x00eb
        L_0x0091:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
            boolean r1 = com.google.android.gms.internal.firebase_auth.zzkq.c(r8, r2)
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r7, (long) r2, (boolean) r1)
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
            int r1 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r8, (long) r2)
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r7, (long) r2, (int) r1)
            goto L_0x00eb
        L_0x00bb:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
            goto L_0x00c8
        L_0x00c2:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
        L_0x00c8:
            long r4 = com.google.android.gms.internal.firebase_auth.zzkq.b(r8, r2)
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r7, (long) r2, (long) r4)
            goto L_0x00eb
        L_0x00d0:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
            float r1 = com.google.android.gms.internal.firebase_auth.zzkq.d(r8, r2)
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r7, (long) r2, (float) r1)
            goto L_0x00eb
        L_0x00de:
            boolean r1 = r6.zza(r8, (int) r0)
            if (r1 == 0) goto L_0x00ee
            double r4 = com.google.android.gms.internal.firebase_auth.zzkq.e(r8, r2)
            com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r7, (long) r2, (double) r4)
        L_0x00eb:
            r6.zzb(r7, (int) r0)
        L_0x00ee:
            int r0 = r0 + 3
            goto L_0x0003
        L_0x00f2:
            boolean r0 = r6.zzadc
            if (r0 != 0) goto L_0x0104
            com.google.android.gms.internal.firebase_auth.zzkk<?, ?> r0 = r6.zzacs
            com.google.android.gms.internal.firebase_auth.zzju.a(r0, r7, r8)
            boolean r0 = r6.zzact
            if (r0 == 0) goto L_0x0104
            com.google.android.gms.internal.firebase_auth.zzhh<?> r0 = r6.zzacu
            com.google.android.gms.internal.firebase_auth.zzju.a(r0, r7, r8)
        L_0x0104:
            return
        L_0x0105:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzjg.zzd(java.lang.Object, java.lang.Object):void");
    }

    public final void zzf(T t) {
        int i;
        int i2 = this.zzadf;
        while (true) {
            i = this.zzadg;
            if (i2 >= i) {
                break;
            }
            long zzbb = (long) (zzbb(this.zzade[i2]) & 1048575);
            Object f = zzkq.f(t, zzbb);
            if (f != null) {
                zzkq.a((Object) t, zzbb, this.zzadj.zzl(f));
            }
            i2++;
        }
        int length = this.zzade.length;
        while (i < length) {
            this.zzadi.b(t, (long) this.zzade[i]);
            i++;
        }
        this.zzacs.d(t);
        if (this.zzact) {
            this.zzacu.c(t);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0104, code lost:
        continue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzp(T r14) {
        /*
            r13 = this;
            r0 = 0
            r1 = -1
            r1 = 0
            r2 = -1
            r3 = 0
        L_0x0005:
            int r4 = r13.zzadf
            r5 = 1
            if (r1 >= r4) goto L_0x0108
            int[] r4 = r13.zzade
            r4 = r4[r1]
            int[] r6 = r13.zzacx
            r6 = r6[r4]
            int r7 = r13.zzbb(r4)
            boolean r8 = r13.zzadc
            r9 = 1048575(0xfffff, float:1.469367E-39)
            if (r8 != 0) goto L_0x0035
            int[] r8 = r13.zzacx
            int r10 = r4 + 2
            r8 = r8[r10]
            r10 = r8 & r9
            int r8 = r8 >>> 20
            int r8 = r5 << r8
            if (r10 == r2) goto L_0x0036
            sun.misc.Unsafe r2 = zzacw
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
            com.google.android.gms.internal.firebase_auth.zziv r6 = r13.zzadj
            r7 = r7 & r9
            long r7 = (long) r7
            java.lang.Object r7 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r7)
            java.util.Map r6 = r6.zzj(r7)
            boolean r7 = r6.isEmpty()
            if (r7 != 0) goto L_0x00b3
            java.lang.Object r4 = r13.zzaz(r4)
            com.google.android.gms.internal.firebase_auth.zziv r7 = r13.zzadj
            com.google.android.gms.internal.firebase_auth.zzit r4 = r7.zzn(r4)
            com.google.android.gms.internal.firebase_auth.zzlb r4 = r4.zzacm
            com.google.android.gms.internal.firebase_auth.zzle r4 = r4.zzkx()
            com.google.android.gms.internal.firebase_auth.zzle r7 = com.google.android.gms.internal.firebase_auth.zzle.MESSAGE
            if (r4 != r7) goto L_0x00b3
            r4 = 0
            java.util.Collection r6 = r6.values()
            java.util.Iterator r6 = r6.iterator()
        L_0x0094:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x00b3
            java.lang.Object r7 = r6.next()
            if (r4 != 0) goto L_0x00ac
            com.google.android.gms.internal.firebase_auth.zzjo r4 = com.google.android.gms.internal.firebase_auth.zzjo.zzjv()
            java.lang.Class r8 = r7.getClass()
            com.google.android.gms.internal.firebase_auth.zzjs r4 = r4.zzf(r8)
        L_0x00ac:
            boolean r7 = r4.zzp(r7)
            if (r7 != 0) goto L_0x0094
            r5 = 0
        L_0x00b3:
            if (r5 != 0) goto L_0x0104
            return r0
        L_0x00b6:
            boolean r5 = r13.zza(r14, (int) r6, (int) r4)
            if (r5 == 0) goto L_0x0104
            com.google.android.gms.internal.firebase_auth.zzjs r4 = r13.zzay(r4)
            boolean r4 = zza((java.lang.Object) r14, (int) r7, (com.google.android.gms.internal.firebase_auth.zzjs) r4)
            if (r4 != 0) goto L_0x0104
            return r0
        L_0x00c7:
            r6 = r7 & r9
            long r6 = (long) r6
            java.lang.Object r6 = com.google.android.gms.internal.firebase_auth.zzkq.f(r14, r6)
            java.util.List r6 = (java.util.List) r6
            boolean r7 = r6.isEmpty()
            if (r7 != 0) goto L_0x00f0
            com.google.android.gms.internal.firebase_auth.zzjs r4 = r13.zzay(r4)
            r7 = 0
        L_0x00db:
            int r8 = r6.size()
            if (r7 >= r8) goto L_0x00f0
            java.lang.Object r8 = r6.get(r7)
            boolean r8 = r4.zzp(r8)
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
            com.google.android.gms.internal.firebase_auth.zzjs r4 = r13.zzay(r4)
            boolean r4 = zza((java.lang.Object) r14, (int) r7, (com.google.android.gms.internal.firebase_auth.zzjs) r4)
            if (r4 != 0) goto L_0x0104
            return r0
        L_0x0104:
            int r1 = r1 + 1
            goto L_0x0005
        L_0x0108:
            boolean r1 = r13.zzact
            if (r1 == 0) goto L_0x0119
            com.google.android.gms.internal.firebase_auth.zzhh<?> r1 = r13.zzacu
            com.google.android.gms.internal.firebase_auth.zzhi r14 = r1.a((java.lang.Object) r14)
            boolean r14 = r14.isInitialized()
            if (r14 != 0) goto L_0x0119
            return r0
        L_0x0119:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzjg.zzp(java.lang.Object):boolean");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01d8, code lost:
        if (r0.zzadd != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01e9, code lost:
        if (r0.zzadd != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01fa, code lost:
        if (r0.zzadd != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x020b, code lost:
        if (r0.zzadd != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x020d, code lost:
        r2.putInt(r1, (long) r14, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0211, code lost:
        r3 = (com.google.android.gms.internal.firebase_auth.zzha.zzak(r3) + com.google.android.gms.internal.firebase_auth.zzha.zzam(r5)) + r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0296, code lost:
        r13 = r13 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x029f, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzha.c(r3, (com.google.android.gms.internal.firebase_auth.zzjc) com.google.android.gms.internal.firebase_auth.zzkq.f(r1, r5), zzay(r12));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x02b8, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzha.zzf(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x02c7, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzha.zzl(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x02d2, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzha.zzh(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x02dd, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzha.zzn(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x02ec, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzha.zzo(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x02fb, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzha.zzk(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0306, code lost:
        r5 = com.google.android.gms.internal.firebase_auth.zzkq.f(r1, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x030a, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzha.zzc(r3, (com.google.android.gms.internal.firebase_auth.zzgf) r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0317, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzju.a(r3, com.google.android.gms.internal.firebase_auth.zzkq.f(r1, r5), zzay(r12));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0331, code lost:
        if ((r5 instanceof com.google.android.gms.internal.firebase_auth.zzgf) != false) goto L_0x030a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0334, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzha.zzb(r3, (java.lang.String) r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0342, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzha.zzd(r3, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x034e, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzha.zzm(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x035a, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzha.zzg(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x036a, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzha.zzj(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x037a, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzha.zze(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x038a, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzha.zzd(r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x0396, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzha.zzb(r3, 0.0f);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x03a2, code lost:
        r3 = com.google.android.gms.internal.firebase_auth.zzha.zzb(r3, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x03aa, code lost:
        r12 = r12 + 3;
        r3 = 267386880;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x0417, code lost:
        if (zza(r1, r15, r3) != false) goto L_0x06d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x0437, code lost:
        if (zza(r1, r15, r3) != false) goto L_0x06fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x043f, code lost:
        if (zza(r1, r15, r3) != false) goto L_0x0708;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x045f, code lost:
        if (zza(r1, r15, r3) != false) goto L_0x072d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x0467, code lost:
        if (zza(r1, r15, r3) != false) goto L_0x073c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x0477, code lost:
        if ((r4 instanceof com.google.android.gms.internal.firebase_auth.zzgf) != false) goto L_0x0731;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:274:0x051c, code lost:
        if (r0.zzadd != false) goto L_0x0602;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:278:0x052e, code lost:
        if (r0.zzadd != false) goto L_0x0602;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:282:0x0540, code lost:
        if (r0.zzadd != false) goto L_0x0602;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:286:0x0552, code lost:
        if (r0.zzadd != false) goto L_0x0602;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:290:0x0564, code lost:
        if (r0.zzadd != false) goto L_0x0602;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:294:0x0576, code lost:
        if (r0.zzadd != false) goto L_0x0602;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:298:0x0588, code lost:
        if (r0.zzadd != false) goto L_0x0602;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:302:0x059a, code lost:
        if (r0.zzadd != false) goto L_0x0602;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:306:0x05ab, code lost:
        if (r0.zzadd != false) goto L_0x0602;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:310:0x05bc, code lost:
        if (r0.zzadd != false) goto L_0x0602;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:314:0x05cd, code lost:
        if (r0.zzadd != false) goto L_0x0602;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:318:0x05de, code lost:
        if (r0.zzadd != false) goto L_0x0602;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:322:0x05ef, code lost:
        if (r0.zzadd != false) goto L_0x0602;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:326:0x0600, code lost:
        if (r0.zzadd != false) goto L_0x0602;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:327:0x0602, code lost:
        r2.putInt(r1, (long) r11, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:328:0x0606, code lost:
        r8 = (com.google.android.gms.internal.firebase_auth.zzha.zzak(r15) + com.google.android.gms.internal.firebase_auth.zzha.zzam(r4)) + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:342:0x06b3, code lost:
        r5 = r5 + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:345:0x06c3, code lost:
        r5 = r5 + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x06c8, code lost:
        r13 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:350:0x06ce, code lost:
        if ((r12 & r18) != 0) goto L_0x06d0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:351:0x06d0, code lost:
        r4 = com.google.android.gms.internal.firebase_auth.zzha.c(r15, (com.google.android.gms.internal.firebase_auth.zzjc) r2.getObject(r1, r8), zzay(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:355:0x06e7, code lost:
        r4 = com.google.android.gms.internal.firebase_auth.zzha.zzf(r15, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:0x06f4, code lost:
        r4 = com.google.android.gms.internal.firebase_auth.zzha.zzl(r15, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:361:0x06fb, code lost:
        if ((r12 & r18) != 0) goto L_0x06fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:362:0x06fd, code lost:
        r4 = com.google.android.gms.internal.firebase_auth.zzha.zzh(r15, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:364:0x0706, code lost:
        if ((r12 & r18) != 0) goto L_0x0708;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:365:0x0708, code lost:
        r8 = com.google.android.gms.internal.firebase_auth.zzha.zzn(r15, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:366:0x070d, code lost:
        r5 = r5 + r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:370:0x0717, code lost:
        r4 = com.google.android.gms.internal.firebase_auth.zzha.zzo(r15, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:374:0x0724, code lost:
        r4 = com.google.android.gms.internal.firebase_auth.zzha.zzk(r15, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:376:0x072b, code lost:
        if ((r12 & r18) != 0) goto L_0x072d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:377:0x072d, code lost:
        r4 = r2.getObject(r1, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:378:0x0731, code lost:
        r4 = com.google.android.gms.internal.firebase_auth.zzha.zzc(r15, (com.google.android.gms.internal.firebase_auth.zzgf) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ab, code lost:
        if ((r5 instanceof com.google.android.gms.internal.firebase_auth.zzgf) != false) goto L_0x030a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:380:0x073a, code lost:
        if ((r12 & r18) != 0) goto L_0x073c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:381:0x073c, code lost:
        r4 = com.google.android.gms.internal.firebase_auth.zzju.a(r15, r2.getObject(r1, r8), zzay(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:385:0x0754, code lost:
        if ((r4 instanceof com.google.android.gms.internal.firebase_auth.zzgf) != false) goto L_0x0731;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:386:0x0757, code lost:
        r4 = com.google.android.gms.internal.firebase_auth.zzha.zzb(r15, (java.lang.String) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:407:0x07bd, code lost:
        r5 = r5 + r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:415:0x07e0, code lost:
        r3 = r3 + 3;
        r9 = r13;
        r7 = 1048575;
        r8 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0127, code lost:
        if (r0.zzadd != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0139, code lost:
        if (r0.zzadd != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x014b, code lost:
        if (r0.zzadd != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x015d, code lost:
        if (r0.zzadd != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x016f, code lost:
        if (r0.zzadd != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0181, code lost:
        if (r0.zzadd != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0193, code lost:
        if (r0.zzadd != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x01a5, code lost:
        if (r0.zzadd != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01b6, code lost:
        if (r0.zzadd != false) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01c7, code lost:
        if (r0.zzadd != false) goto L_0x020d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzq(T r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            boolean r2 = r0.zzadc
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = 0
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r8 = 1
            r9 = 0
            r11 = 0
            if (r2 == 0) goto L_0x03b8
            sun.misc.Unsafe r2 = zzacw
            r12 = 0
            r13 = 0
        L_0x0016:
            int[] r14 = r0.zzacx
            int r14 = r14.length
            if (r12 >= r14) goto L_0x03b0
            int r14 = r0.zzbb(r12)
            r15 = r14 & r3
            int r15 = r15 >>> 20
            int[] r3 = r0.zzacx
            r3 = r3[r12]
            r14 = r14 & r7
            long r5 = (long) r14
            com.google.android.gms.internal.firebase_auth.zzhn r14 = com.google.android.gms.internal.firebase_auth.zzhn.DOUBLE_LIST_PACKED
            int r14 = r14.id()
            if (r15 < r14) goto L_0x0041
            com.google.android.gms.internal.firebase_auth.zzhn r14 = com.google.android.gms.internal.firebase_auth.zzhn.SINT64_LIST_PACKED
            int r14 = r14.id()
            if (r15 > r14) goto L_0x0041
            int[] r14 = r0.zzacx
            int r17 = r12 + 2
            r14 = r14[r17]
            r14 = r14 & r7
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
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzkq.f(r1, r5)
            boolean r6 = r5 instanceof com.google.android.gms.internal.firebase_auth.zzgf
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
            com.google.android.gms.internal.firebase_auth.zziv r14 = r0.zzadj
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzkq.f(r1, r5)
            java.lang.Object r6 = r0.zzaz(r12)
            int r3 = r14.zzb(r3, r5, r6)
            goto L_0x0296
        L_0x010b:
            java.util.List r5 = zze(r1, r5)
            com.google.android.gms.internal.firebase_auth.zzjs r6 = r0.zzay(r12)
            int r3 = com.google.android.gms.internal.firebase_auth.zzju.b((int) r3, (java.util.List<com.google.android.gms.internal.firebase_auth.zzjc>) r5, (com.google.android.gms.internal.firebase_auth.zzjs) r6)
            goto L_0x0296
        L_0x0119:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_auth.zzju.c(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzadd
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x012b:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_auth.zzju.g(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzadd
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x013d:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_auth.zzju.i(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzadd
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x014f:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_auth.zzju.h(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzadd
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0161:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_auth.zzju.d(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzadd
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0173:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_auth.zzju.f(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzadd
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0185:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_auth.zzju.j(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzadd
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x0197:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_auth.zzju.h(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzadd
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01a8:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_auth.zzju.i(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzadd
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01b9:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_auth.zzju.e(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzadd
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01ca:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_auth.zzju.b(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzadd
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01db:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_auth.zzju.a(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzadd
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01ec:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_auth.zzju.h(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzadd
            if (r6 == 0) goto L_0x0211
            goto L_0x020d
        L_0x01fd:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.firebase_auth.zzju.i(r5)
            if (r5 <= 0) goto L_0x03aa
            boolean r6 = r0.zzadd
            if (r6 == 0) goto L_0x0211
        L_0x020d:
            long r14 = (long) r14
            r2.putInt(r1, r14, r5)
        L_0x0211:
            int r3 = com.google.android.gms.internal.firebase_auth.zzha.zzak(r3)
            int r6 = com.google.android.gms.internal.firebase_auth.zzha.zzam(r5)
            int r3 = r3 + r6
            int r3 = r3 + r5
            goto L_0x0296
        L_0x021d:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.firebase_auth.zzju.c(r3, r5, r11)
            goto L_0x0296
        L_0x0227:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.firebase_auth.zzju.g(r3, r5, r11)
            goto L_0x0296
        L_0x0230:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.firebase_auth.zzju.d(r3, r5, r11)
            goto L_0x0296
        L_0x0239:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.firebase_auth.zzju.f(r3, r5, r11)
            goto L_0x0296
        L_0x0242:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.firebase_auth.zzju.b(r3, r5)
            goto L_0x0296
        L_0x024b:
            java.util.List r5 = zze(r1, r5)
            com.google.android.gms.internal.firebase_auth.zzjs r6 = r0.zzay(r12)
            int r3 = com.google.android.gms.internal.firebase_auth.zzju.a((int) r3, (java.util.List<?>) r5, (com.google.android.gms.internal.firebase_auth.zzjs) r6)
            goto L_0x0296
        L_0x0258:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.firebase_auth.zzju.a((int) r3, (java.util.List<?>) r5)
            goto L_0x0296
        L_0x0261:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.firebase_auth.zzju.j(r3, r5, r11)
            goto L_0x0296
        L_0x026a:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.firebase_auth.zzju.e(r3, r5, r11)
            goto L_0x0296
        L_0x0273:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.firebase_auth.zzju.b((int) r3, (java.util.List<java.lang.Long>) r5, (boolean) r11)
            goto L_0x0296
        L_0x027c:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.firebase_auth.zzju.a((int) r3, (java.util.List<java.lang.Long>) r5, (boolean) r11)
            goto L_0x0296
        L_0x0285:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.firebase_auth.zzju.h(r3, r5, r11)
            goto L_0x0296
        L_0x028e:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.firebase_auth.zzju.i(r3, r5, r11)
        L_0x0296:
            int r13 = r13 + r3
            goto L_0x03aa
        L_0x0299:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
        L_0x029f:
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzkq.f(r1, r5)
            com.google.android.gms.internal.firebase_auth.zzjc r5 = (com.google.android.gms.internal.firebase_auth.zzjc) r5
            com.google.android.gms.internal.firebase_auth.zzjs r6 = r0.zzay(r12)
            int r3 = com.google.android.gms.internal.firebase_auth.zzha.c(r3, r5, r6)
            goto L_0x0296
        L_0x02ae:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
            long r5 = com.google.android.gms.internal.firebase_auth.zzkq.b(r1, r5)
        L_0x02b8:
            int r3 = com.google.android.gms.internal.firebase_auth.zzha.zzf((int) r3, (long) r5)
            goto L_0x0296
        L_0x02bd:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
            int r5 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r1, (long) r5)
        L_0x02c7:
            int r3 = com.google.android.gms.internal.firebase_auth.zzha.zzl(r3, r5)
            goto L_0x0296
        L_0x02cc:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x03aa
        L_0x02d2:
            int r3 = com.google.android.gms.internal.firebase_auth.zzha.zzh((int) r3, (long) r9)
            goto L_0x0296
        L_0x02d7:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x03aa
        L_0x02dd:
            int r3 = com.google.android.gms.internal.firebase_auth.zzha.zzn(r3, r11)
            goto L_0x0296
        L_0x02e2:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
            int r5 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r1, (long) r5)
        L_0x02ec:
            int r3 = com.google.android.gms.internal.firebase_auth.zzha.zzo(r3, r5)
            goto L_0x0296
        L_0x02f1:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
            int r5 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r1, (long) r5)
        L_0x02fb:
            int r3 = com.google.android.gms.internal.firebase_auth.zzha.zzk(r3, r5)
            goto L_0x0296
        L_0x0300:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
        L_0x0306:
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzkq.f(r1, r5)
        L_0x030a:
            com.google.android.gms.internal.firebase_auth.zzgf r5 = (com.google.android.gms.internal.firebase_auth.zzgf) r5
            int r3 = com.google.android.gms.internal.firebase_auth.zzha.zzc((int) r3, (com.google.android.gms.internal.firebase_auth.zzgf) r5)
            goto L_0x0296
        L_0x0311:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
        L_0x0317:
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzkq.f(r1, r5)
            com.google.android.gms.internal.firebase_auth.zzjs r6 = r0.zzay(r12)
            int r3 = com.google.android.gms.internal.firebase_auth.zzju.a((int) r3, (java.lang.Object) r5, (com.google.android.gms.internal.firebase_auth.zzjs) r6)
            goto L_0x0296
        L_0x0325:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
            java.lang.Object r5 = com.google.android.gms.internal.firebase_auth.zzkq.f(r1, r5)
            boolean r6 = r5 instanceof com.google.android.gms.internal.firebase_auth.zzgf
            if (r6 == 0) goto L_0x0334
        L_0x0333:
            goto L_0x030a
        L_0x0334:
            java.lang.String r5 = (java.lang.String) r5
            int r3 = com.google.android.gms.internal.firebase_auth.zzha.zzb((int) r3, (java.lang.String) r5)
            goto L_0x0296
        L_0x033c:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x03aa
        L_0x0342:
            int r3 = com.google.android.gms.internal.firebase_auth.zzha.zzd((int) r3, (boolean) r8)
            goto L_0x0296
        L_0x0348:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x03aa
        L_0x034e:
            int r3 = com.google.android.gms.internal.firebase_auth.zzha.zzm(r3, r11)
            goto L_0x0296
        L_0x0354:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x03aa
        L_0x035a:
            int r3 = com.google.android.gms.internal.firebase_auth.zzha.zzg((int) r3, (long) r9)
            goto L_0x0296
        L_0x0360:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
            int r5 = com.google.android.gms.internal.firebase_auth.zzkq.a((java.lang.Object) r1, (long) r5)
        L_0x036a:
            int r3 = com.google.android.gms.internal.firebase_auth.zzha.zzj(r3, r5)
            goto L_0x0296
        L_0x0370:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
            long r5 = com.google.android.gms.internal.firebase_auth.zzkq.b(r1, r5)
        L_0x037a:
            int r3 = com.google.android.gms.internal.firebase_auth.zzha.zze((int) r3, (long) r5)
            goto L_0x0296
        L_0x0380:
            boolean r14 = r0.zza(r1, (int) r12)
            if (r14 == 0) goto L_0x03aa
            long r5 = com.google.android.gms.internal.firebase_auth.zzkq.b(r1, r5)
        L_0x038a:
            int r3 = com.google.android.gms.internal.firebase_auth.zzha.zzd((int) r3, (long) r5)
            goto L_0x0296
        L_0x0390:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x03aa
        L_0x0396:
            int r3 = com.google.android.gms.internal.firebase_auth.zzha.zzb((int) r3, (float) r4)
            goto L_0x0296
        L_0x039c:
            boolean r5 = r0.zza(r1, (int) r12)
            if (r5 == 0) goto L_0x03aa
        L_0x03a2:
            r5 = 0
            int r3 = com.google.android.gms.internal.firebase_auth.zzha.zzb((int) r3, (double) r5)
            goto L_0x0296
        L_0x03aa:
            int r12 = r12 + 3
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            goto L_0x0016
        L_0x03b0:
            com.google.android.gms.internal.firebase_auth.zzkk<?, ?> r2 = r0.zzacs
            int r1 = zza(r2, r1)
            int r13 = r13 + r1
            return r13
        L_0x03b8:
            sun.misc.Unsafe r2 = zzacw
            r3 = -1
            r3 = 0
            r5 = 0
            r6 = -1
            r12 = 0
        L_0x03bf:
            int[] r13 = r0.zzacx
            int r13 = r13.length
            if (r3 >= r13) goto L_0x07eb
            int r13 = r0.zzbb(r3)
            int[] r14 = r0.zzacx
            r15 = r14[r3]
            r16 = 267386880(0xff00000, float:2.3665827E-29)
            r17 = r13 & r16
            int r4 = r17 >>> 20
            r11 = 17
            if (r4 > r11) goto L_0x03eb
            int r11 = r3 + 2
            r11 = r14[r11]
            r14 = r11 & r7
            int r18 = r11 >>> 20
            int r18 = r8 << r18
            if (r14 == r6) goto L_0x03e8
            long r8 = (long) r14
            int r12 = r2.getInt(r1, r8)
            goto L_0x03e9
        L_0x03e8:
            r14 = r6
        L_0x03e9:
            r6 = r14
            goto L_0x040b
        L_0x03eb:
            boolean r8 = r0.zzadd
            if (r8 == 0) goto L_0x0408
            com.google.android.gms.internal.firebase_auth.zzhn r8 = com.google.android.gms.internal.firebase_auth.zzhn.DOUBLE_LIST_PACKED
            int r8 = r8.id()
            if (r4 < r8) goto L_0x0408
            com.google.android.gms.internal.firebase_auth.zzhn r8 = com.google.android.gms.internal.firebase_auth.zzhn.SINT64_LIST_PACKED
            int r8 = r8.id()
            if (r4 > r8) goto L_0x0408
            int[] r8 = r0.zzacx
            int r9 = r3 + 2
            r8 = r8[r9]
            r11 = r8 & r7
            goto L_0x0409
        L_0x0408:
            r11 = 0
        L_0x0409:
            r18 = 0
        L_0x040b:
            r8 = r13 & r7
            long r8 = (long) r8
            switch(r4) {
                case 0: goto L_0x07d0;
                case 1: goto L_0x07bf;
                case 2: goto L_0x07ad;
                case 3: goto L_0x079c;
                case 4: goto L_0x078b;
                case 5: goto L_0x077b;
                case 6: goto L_0x076b;
                case 7: goto L_0x075f;
                case 8: goto L_0x074a;
                case 9: goto L_0x0738;
                case 10: goto L_0x0729;
                case 11: goto L_0x071c;
                case 12: goto L_0x070f;
                case 13: goto L_0x0704;
                case 14: goto L_0x06f9;
                case 15: goto L_0x06ec;
                case 16: goto L_0x06df;
                case 17: goto L_0x06cc;
                case 18: goto L_0x06b8;
                case 19: goto L_0x06a8;
                case 20: goto L_0x069c;
                case 21: goto L_0x0690;
                case 22: goto L_0x0684;
                case 23: goto L_0x0678;
                case 24: goto L_0x06a8;
                case 25: goto L_0x066c;
                case 26: goto L_0x0661;
                case 27: goto L_0x0652;
                case 28: goto L_0x0646;
                case 29: goto L_0x0639;
                case 30: goto L_0x062c;
                case 31: goto L_0x06a8;
                case 32: goto L_0x0678;
                case 33: goto L_0x061f;
                case 34: goto L_0x0612;
                case 35: goto L_0x05f2;
                case 36: goto L_0x05e1;
                case 37: goto L_0x05d0;
                case 38: goto L_0x05bf;
                case 39: goto L_0x05ae;
                case 40: goto L_0x059d;
                case 41: goto L_0x058c;
                case 42: goto L_0x057a;
                case 43: goto L_0x0568;
                case 44: goto L_0x0556;
                case 45: goto L_0x0544;
                case 46: goto L_0x0532;
                case 47: goto L_0x0520;
                case 48: goto L_0x050e;
                case 49: goto L_0x04fe;
                case 50: goto L_0x04ee;
                case 51: goto L_0x04e0;
                case 52: goto L_0x04d3;
                case 53: goto L_0x04c3;
                case 54: goto L_0x04b3;
                case 55: goto L_0x04a3;
                case 56: goto L_0x0495;
                case 57: goto L_0x0488;
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
            goto L_0x06c4
        L_0x0413:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06c4
            goto L_0x06d0
        L_0x041b:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06c4
            long r8 = zzi(r1, r8)
            goto L_0x06e7
        L_0x0427:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06c4
            int r4 = zzh(r1, r8)
            goto L_0x06f4
        L_0x0433:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06c4
            goto L_0x06fd
        L_0x043b:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06c4
            goto L_0x0708
        L_0x0443:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06c4
            int r4 = zzh(r1, r8)
            goto L_0x0717
        L_0x044f:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06c4
            int r4 = zzh(r1, r8)
            goto L_0x0724
        L_0x045b:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06c4
            goto L_0x072d
        L_0x0463:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06c4
            goto L_0x073c
        L_0x046b:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06c4
            java.lang.Object r4 = r2.getObject(r1, r8)
            boolean r8 = r4 instanceof com.google.android.gms.internal.firebase_auth.zzgf
            if (r8 == 0) goto L_0x0757
            goto L_0x0756
        L_0x047b:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06c4
            r4 = 1
            int r8 = com.google.android.gms.internal.firebase_auth.zzha.zzd((int) r15, (boolean) r4)
            goto L_0x070d
        L_0x0488:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06c4
            r4 = 0
            int r8 = com.google.android.gms.internal.firebase_auth.zzha.zzm(r15, r4)
            goto L_0x070d
        L_0x0495:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06c4
            r8 = 0
            int r4 = com.google.android.gms.internal.firebase_auth.zzha.zzg((int) r15, (long) r8)
            goto L_0x06c3
        L_0x04a3:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06c4
            int r4 = zzh(r1, r8)
            int r4 = com.google.android.gms.internal.firebase_auth.zzha.zzj(r15, r4)
            goto L_0x06c3
        L_0x04b3:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06c4
            long r8 = zzi(r1, r8)
            int r4 = com.google.android.gms.internal.firebase_auth.zzha.zze((int) r15, (long) r8)
            goto L_0x06c3
        L_0x04c3:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06c4
            long r8 = zzi(r1, r8)
            int r4 = com.google.android.gms.internal.firebase_auth.zzha.zzd((int) r15, (long) r8)
            goto L_0x06c3
        L_0x04d3:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06c4
            r4 = 0
            int r8 = com.google.android.gms.internal.firebase_auth.zzha.zzb((int) r15, (float) r4)
            goto L_0x070d
        L_0x04e0:
            boolean r4 = r0.zza(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x06c4
            r8 = 0
            int r4 = com.google.android.gms.internal.firebase_auth.zzha.zzb((int) r15, (double) r8)
            goto L_0x06c3
        L_0x04ee:
            com.google.android.gms.internal.firebase_auth.zziv r4 = r0.zzadj
            java.lang.Object r8 = r2.getObject(r1, r8)
            java.lang.Object r9 = r0.zzaz(r3)
            int r4 = r4.zzb(r15, r8, r9)
            goto L_0x06c3
        L_0x04fe:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase_auth.zzjs r8 = r0.zzay(r3)
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.b((int) r15, (java.util.List<com.google.android.gms.internal.firebase_auth.zzjc>) r4, (com.google.android.gms.internal.firebase_auth.zzjs) r8)
            goto L_0x06c3
        L_0x050e:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.c(r4)
            if (r4 <= 0) goto L_0x06c4
            boolean r8 = r0.zzadd
            if (r8 == 0) goto L_0x0606
            goto L_0x0602
        L_0x0520:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.g(r4)
            if (r4 <= 0) goto L_0x06c4
            boolean r8 = r0.zzadd
            if (r8 == 0) goto L_0x0606
            goto L_0x0602
        L_0x0532:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.i(r4)
            if (r4 <= 0) goto L_0x06c4
            boolean r8 = r0.zzadd
            if (r8 == 0) goto L_0x0606
            goto L_0x0602
        L_0x0544:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.h(r4)
            if (r4 <= 0) goto L_0x06c4
            boolean r8 = r0.zzadd
            if (r8 == 0) goto L_0x0606
            goto L_0x0602
        L_0x0556:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.d(r4)
            if (r4 <= 0) goto L_0x06c4
            boolean r8 = r0.zzadd
            if (r8 == 0) goto L_0x0606
            goto L_0x0602
        L_0x0568:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.f(r4)
            if (r4 <= 0) goto L_0x06c4
            boolean r8 = r0.zzadd
            if (r8 == 0) goto L_0x0606
            goto L_0x0602
        L_0x057a:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.j(r4)
            if (r4 <= 0) goto L_0x06c4
            boolean r8 = r0.zzadd
            if (r8 == 0) goto L_0x0606
            goto L_0x0602
        L_0x058c:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.h(r4)
            if (r4 <= 0) goto L_0x06c4
            boolean r8 = r0.zzadd
            if (r8 == 0) goto L_0x0606
            goto L_0x0602
        L_0x059d:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.i(r4)
            if (r4 <= 0) goto L_0x06c4
            boolean r8 = r0.zzadd
            if (r8 == 0) goto L_0x0606
            goto L_0x0602
        L_0x05ae:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.e(r4)
            if (r4 <= 0) goto L_0x06c4
            boolean r8 = r0.zzadd
            if (r8 == 0) goto L_0x0606
            goto L_0x0602
        L_0x05bf:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.b(r4)
            if (r4 <= 0) goto L_0x06c4
            boolean r8 = r0.zzadd
            if (r8 == 0) goto L_0x0606
            goto L_0x0602
        L_0x05d0:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.a(r4)
            if (r4 <= 0) goto L_0x06c4
            boolean r8 = r0.zzadd
            if (r8 == 0) goto L_0x0606
            goto L_0x0602
        L_0x05e1:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.h(r4)
            if (r4 <= 0) goto L_0x06c4
            boolean r8 = r0.zzadd
            if (r8 == 0) goto L_0x0606
            goto L_0x0602
        L_0x05f2:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.i(r4)
            if (r4 <= 0) goto L_0x06c4
            boolean r8 = r0.zzadd
            if (r8 == 0) goto L_0x0606
        L_0x0602:
            long r8 = (long) r11
            r2.putInt(r1, r8, r4)
        L_0x0606:
            int r8 = com.google.android.gms.internal.firebase_auth.zzha.zzak(r15)
            int r9 = com.google.android.gms.internal.firebase_auth.zzha.zzam(r4)
            int r8 = r8 + r9
            int r8 = r8 + r4
            goto L_0x070d
        L_0x0612:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            r10 = 0
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.c(r15, r4, r10)
            goto L_0x06b3
        L_0x061f:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.g(r15, r4, r10)
            goto L_0x06b3
        L_0x062c:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.d(r15, r4, r10)
            goto L_0x06b3
        L_0x0639:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.f(r15, r4, r10)
            goto L_0x06c3
        L_0x0646:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.b(r15, r4)
            goto L_0x06c3
        L_0x0652:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.firebase_auth.zzjs r8 = r0.zzay(r3)
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.a((int) r15, (java.util.List<?>) r4, (com.google.android.gms.internal.firebase_auth.zzjs) r8)
            goto L_0x06c3
        L_0x0661:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.a((int) r15, (java.util.List<?>) r4)
            goto L_0x06c3
        L_0x066c:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            r10 = 0
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.j(r15, r4, r10)
            goto L_0x06b3
        L_0x0678:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.i(r15, r4, r10)
            goto L_0x06b3
        L_0x0684:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.e(r15, r4, r10)
            goto L_0x06b3
        L_0x0690:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.b((int) r15, (java.util.List<java.lang.Long>) r4, (boolean) r10)
            goto L_0x06b3
        L_0x069c:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.a((int) r15, (java.util.List<java.lang.Long>) r4, (boolean) r10)
            goto L_0x06b3
        L_0x06a8:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.h(r15, r4, r10)
        L_0x06b3:
            int r5 = r5 + r4
            r4 = 1
        L_0x06b5:
            r7 = 0
            goto L_0x06c8
        L_0x06b8:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.i(r15, r4, r10)
        L_0x06c3:
            int r5 = r5 + r4
        L_0x06c4:
            r4 = 1
        L_0x06c5:
            r7 = 0
            r10 = 0
        L_0x06c8:
            r13 = 0
            goto L_0x07e0
        L_0x06cc:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06c4
        L_0x06d0:
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.firebase_auth.zzjc r4 = (com.google.android.gms.internal.firebase_auth.zzjc) r4
            com.google.android.gms.internal.firebase_auth.zzjs r8 = r0.zzay(r3)
            int r4 = com.google.android.gms.internal.firebase_auth.zzha.c(r15, r4, r8)
            goto L_0x06c3
        L_0x06df:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06c4
            long r8 = r2.getLong(r1, r8)
        L_0x06e7:
            int r4 = com.google.android.gms.internal.firebase_auth.zzha.zzf((int) r15, (long) r8)
            goto L_0x06c3
        L_0x06ec:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06c4
            int r4 = r2.getInt(r1, r8)
        L_0x06f4:
            int r4 = com.google.android.gms.internal.firebase_auth.zzha.zzl(r15, r4)
            goto L_0x06c3
        L_0x06f9:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06c4
        L_0x06fd:
            r8 = 0
            int r4 = com.google.android.gms.internal.firebase_auth.zzha.zzh((int) r15, (long) r8)
            goto L_0x06c3
        L_0x0704:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06c4
        L_0x0708:
            r4 = 0
            int r8 = com.google.android.gms.internal.firebase_auth.zzha.zzn(r15, r4)
        L_0x070d:
            int r5 = r5 + r8
            goto L_0x06c4
        L_0x070f:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06c4
            int r4 = r2.getInt(r1, r8)
        L_0x0717:
            int r4 = com.google.android.gms.internal.firebase_auth.zzha.zzo(r15, r4)
            goto L_0x06c3
        L_0x071c:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06c4
            int r4 = r2.getInt(r1, r8)
        L_0x0724:
            int r4 = com.google.android.gms.internal.firebase_auth.zzha.zzk(r15, r4)
            goto L_0x06c3
        L_0x0729:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06c4
        L_0x072d:
            java.lang.Object r4 = r2.getObject(r1, r8)
        L_0x0731:
            com.google.android.gms.internal.firebase_auth.zzgf r4 = (com.google.android.gms.internal.firebase_auth.zzgf) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzha.zzc((int) r15, (com.google.android.gms.internal.firebase_auth.zzgf) r4)
            goto L_0x06c3
        L_0x0738:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06c4
        L_0x073c:
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.firebase_auth.zzjs r8 = r0.zzay(r3)
            int r4 = com.google.android.gms.internal.firebase_auth.zzju.a((int) r15, (java.lang.Object) r4, (com.google.android.gms.internal.firebase_auth.zzjs) r8)
            goto L_0x06c3
        L_0x074a:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06c4
            java.lang.Object r4 = r2.getObject(r1, r8)
            boolean r8 = r4 instanceof com.google.android.gms.internal.firebase_auth.zzgf
            if (r8 == 0) goto L_0x0757
        L_0x0756:
            goto L_0x0731
        L_0x0757:
            java.lang.String r4 = (java.lang.String) r4
            int r4 = com.google.android.gms.internal.firebase_auth.zzha.zzb((int) r15, (java.lang.String) r4)
            goto L_0x06c3
        L_0x075f:
            r4 = r12 & r18
            if (r4 == 0) goto L_0x06c4
            r4 = 1
            int r8 = com.google.android.gms.internal.firebase_auth.zzha.zzd((int) r15, (boolean) r4)
            int r5 = r5 + r8
            goto L_0x06c5
        L_0x076b:
            r4 = 1
            r8 = r12 & r18
            if (r8 == 0) goto L_0x0778
            r10 = 0
            int r8 = com.google.android.gms.internal.firebase_auth.zzha.zzm(r15, r10)
            int r5 = r5 + r8
            goto L_0x06b5
        L_0x0778:
            r10 = 0
            goto L_0x06b5
        L_0x077b:
            r4 = 1
            r10 = 0
            r8 = r12 & r18
            if (r8 == 0) goto L_0x0788
            r13 = 0
            int r8 = com.google.android.gms.internal.firebase_auth.zzha.zzg((int) r15, (long) r13)
            goto L_0x07bd
        L_0x0788:
            r13 = 0
            goto L_0x07cd
        L_0x078b:
            r4 = 1
            r10 = 0
            r13 = 0
            r11 = r12 & r18
            if (r11 == 0) goto L_0x07cd
            int r8 = r2.getInt(r1, r8)
            int r8 = com.google.android.gms.internal.firebase_auth.zzha.zzj(r15, r8)
            goto L_0x07bd
        L_0x079c:
            r4 = 1
            r10 = 0
            r13 = 0
            r11 = r12 & r18
            if (r11 == 0) goto L_0x07cd
            long r8 = r2.getLong(r1, r8)
            int r8 = com.google.android.gms.internal.firebase_auth.zzha.zze((int) r15, (long) r8)
            goto L_0x07bd
        L_0x07ad:
            r4 = 1
            r10 = 0
            r13 = 0
            r11 = r12 & r18
            if (r11 == 0) goto L_0x07cd
            long r8 = r2.getLong(r1, r8)
            int r8 = com.google.android.gms.internal.firebase_auth.zzha.zzd((int) r15, (long) r8)
        L_0x07bd:
            int r5 = r5 + r8
            goto L_0x07cd
        L_0x07bf:
            r4 = 1
            r10 = 0
            r13 = 0
            r8 = r12 & r18
            if (r8 == 0) goto L_0x07cd
            r8 = 0
            int r9 = com.google.android.gms.internal.firebase_auth.zzha.zzb((int) r15, (float) r8)
            int r5 = r5 + r9
        L_0x07cd:
            r7 = 0
            goto L_0x07e0
        L_0x07d0:
            r4 = 1
            r8 = 0
            r10 = 0
            r13 = 0
            r9 = r12 & r18
            if (r9 == 0) goto L_0x07cd
            r7 = 0
            int r11 = com.google.android.gms.internal.firebase_auth.zzha.zzb((int) r15, (double) r7)
            int r5 = r5 + r11
        L_0x07e0:
            int r3 = r3 + 3
            r9 = r13
            r4 = 0
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r8 = 1
            r11 = 0
            goto L_0x03bf
        L_0x07eb:
            r10 = 0
            com.google.android.gms.internal.firebase_auth.zzkk<?, ?> r2 = r0.zzacs
            int r2 = zza(r2, r1)
            int r5 = r5 + r2
            boolean r2 = r0.zzact
            if (r2 == 0) goto L_0x0845
            com.google.android.gms.internal.firebase_auth.zzhh<?> r2 = r0.zzacu
            com.google.android.gms.internal.firebase_auth.zzhi r1 = r2.a((java.lang.Object) r1)
            r2 = 0
        L_0x07fe:
            com.google.android.gms.internal.firebase_auth.zzjt<FieldDescriptorType, java.lang.Object> r3 = r1.a
            int r3 = r3.zzjy()
            if (r10 >= r3) goto L_0x081e
            com.google.android.gms.internal.firebase_auth.zzjt<FieldDescriptorType, java.lang.Object> r3 = r1.a
            java.util.Map$Entry r3 = r3.zzbf(r10)
            java.lang.Object r4 = r3.getKey()
            com.google.android.gms.internal.firebase_auth.zzhk r4 = (com.google.android.gms.internal.firebase_auth.zzhk) r4
            java.lang.Object r3 = r3.getValue()
            int r3 = com.google.android.gms.internal.firebase_auth.zzhi.zzb((com.google.android.gms.internal.firebase_auth.zzhk<?>) r4, (java.lang.Object) r3)
            int r2 = r2 + r3
            int r10 = r10 + 1
            goto L_0x07fe
        L_0x081e:
            com.google.android.gms.internal.firebase_auth.zzjt<FieldDescriptorType, java.lang.Object> r1 = r1.a
            java.lang.Iterable r1 = r1.zzjz()
            java.util.Iterator r1 = r1.iterator()
        L_0x0828:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0844
            java.lang.Object r3 = r1.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            com.google.android.gms.internal.firebase_auth.zzhk r4 = (com.google.android.gms.internal.firebase_auth.zzhk) r4
            java.lang.Object r3 = r3.getValue()
            int r3 = com.google.android.gms.internal.firebase_auth.zzhi.zzb((com.google.android.gms.internal.firebase_auth.zzhk<?>) r4, (java.lang.Object) r3)
            int r2 = r2 + r3
            goto L_0x0828
        L_0x0844:
            int r5 = r5 + r2
        L_0x0845:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzjg.zzq(java.lang.Object):int");
    }
}

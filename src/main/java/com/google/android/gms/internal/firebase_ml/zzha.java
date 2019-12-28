package com.google.android.gms.internal.firebase_ml;

import com.google.common.base.Ascii;

public final class zzha extends zzhb {
    private static final byte[] zzxl = {Ascii.CR, 10};
    private static final byte[] zzxm = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] zzxn = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    private static final byte[] zzxo = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, Ascii.VT, Ascii.FF, Ascii.CR, Ascii.SO, Ascii.SI, Ascii.DLE, 17, Ascii.DC2, 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.EM, -1, -1, -1, -1, 63, -1, Ascii.SUB, Ascii.ESC, Ascii.FS, Ascii.GS, Ascii.RS, Ascii.US, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};
    private final byte[] zzxp;
    private final byte[] zzxq;
    private final byte[] zzxr;
    private final int zzxs;
    private final int zzxt;

    public zzha() {
        this(0);
    }

    private zzha(int i) {
        this(0, zzxl);
    }

    private zzha(int i, byte[] bArr) {
        this(0, bArr, false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x006b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzha(int r9, byte[] r10, boolean r11) {
        /*
            r8 = this;
            r0 = 0
            if (r10 != 0) goto L_0x0005
            r1 = 0
            goto L_0x0006
        L_0x0005:
            int r1 = r10.length
        L_0x0006:
            r2 = 4
            r3 = 3
            r8.<init>(r3, r2, r9, r1)
            byte[] r1 = zzxo
            r8.zzxq = r1
            r1 = 0
            r3 = 1
            if (r10 == 0) goto L_0x005d
            if (r10 == 0) goto L_0x002b
            int r4 = r10.length
            r5 = 0
        L_0x0017:
            if (r5 >= r4) goto L_0x002b
            byte r6 = r10[r5]
            byte r7 = r8.a
            if (r7 == r6) goto L_0x0029
            boolean r6 = r8.a(r6)
            if (r6 == 0) goto L_0x0026
            goto L_0x0029
        L_0x0026:
            int r5 = r5 + 1
            goto L_0x0017
        L_0x0029:
            r4 = 1
            goto L_0x002c
        L_0x002b:
            r4 = 0
        L_0x002c:
            if (r4 != 0) goto L_0x0040
            if (r9 <= 0) goto L_0x005d
            int r9 = r10.length
            int r9 = r9 + r2
            r8.zzxt = r9
            int r9 = r10.length
            byte[] r9 = new byte[r9]
            r8.zzxr = r9
            byte[] r9 = r8.zzxr
            int r1 = r10.length
            java.lang.System.arraycopy(r10, r0, r9, r0, r1)
            goto L_0x0061
        L_0x0040:
            java.lang.String r9 = com.google.android.gms.internal.firebase_ml.zzhd.zze(r10)
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r0 = "lineSeparator must not contain base64 characters: ["
            r11.<init>(r0)
            r11.append(r9)
            java.lang.String r9 = "]"
            r11.append(r9)
            java.lang.String r9 = r11.toString()
            r10.<init>(r9)
            throw r10
        L_0x005d:
            r8.zzxt = r2
            r8.zzxr = r1
        L_0x0061:
            int r9 = r8.zzxt
            int r9 = r9 - r3
            r8.zzxs = r9
            if (r11 == 0) goto L_0x006b
            byte[] r9 = zzxn
            goto L_0x006d
        L_0x006b:
            byte[] r9 = zzxm
        L_0x006d:
            r8.zzxp = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzha.<init>(int, byte[], boolean):void");
    }

    public static String zzc(byte[] bArr) {
        if (!(bArr == null || bArr.length == 0)) {
            zzha zzha = new zzha(0, zzxl, true);
            long zzd = zzha.zzd(bArr);
            if (zzd > 2147483647L) {
                throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + zzd + ") than the specified maximum size of 2147483647");
            } else if (!(bArr == null || bArr.length == 0)) {
                zzhc zzhc = new zzhc();
                zzha.a(bArr, 0, bArr.length, zzhc);
                zzha.a(bArr, 0, -1, zzhc);
                bArr = new byte[(zzhc.c - zzhc.d)];
                int length = bArr.length;
                if (zzhc.b != null) {
                    int min = Math.min(zzhc.b != null ? zzhc.c - zzhc.d : 0, length);
                    System.arraycopy(zzhc.b, zzhc.d, bArr, 0, min);
                    zzhc.d += min;
                    if (zzhc.d >= zzhc.c) {
                        zzhc.b = null;
                    }
                }
            }
        }
        return zzhd.zze(bArr);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v33, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v34, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(byte[] r8, int r9, int r10, com.google.android.gms.internal.firebase_ml.zzhc r11) {
        /*
            r7 = this;
            boolean r0 = r11.e
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            r0 = 0
            r1 = 1
            if (r10 >= 0) goto L_0x00df
            r11.e = r1
            int r8 = r11.g
            if (r8 != 0) goto L_0x0014
            int r8 = r7.b
            if (r8 != 0) goto L_0x0014
            return
        L_0x0014:
            int r8 = r7.zzxt
            byte[] r8 = r7.a(r8, r11)
            int r9 = r11.c
            int r10 = r11.g
            switch(r10) {
                case 0: goto L_0x00bc;
                case 1: goto L_0x007e;
                case 2: goto L_0x0037;
                default: goto L_0x0021;
            }
        L_0x0021:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Impossible modulus "
            r9.<init>(r10)
            int r10 = r11.g
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L_0x0037:
            int r10 = r11.c
            int r1 = r10 + 1
            r11.c = r1
            byte[] r1 = r7.zzxp
            int r2 = r11.a
            int r2 = r2 >> 10
            r2 = r2 & 63
            byte r1 = r1[r2]
            r8[r10] = r1
            int r10 = r11.c
            int r1 = r10 + 1
            r11.c = r1
            byte[] r1 = r7.zzxp
            int r2 = r11.a
            int r2 = r2 >> 4
            r2 = r2 & 63
            byte r1 = r1[r2]
            r8[r10] = r1
            int r10 = r11.c
            int r1 = r10 + 1
            r11.c = r1
            byte[] r1 = r7.zzxp
            int r2 = r11.a
            int r2 = r2 << 2
            r2 = r2 & 63
            byte r1 = r1[r2]
            r8[r10] = r1
            byte[] r10 = r7.zzxp
            byte[] r1 = zzxm
            if (r10 != r1) goto L_0x00bc
            int r10 = r11.c
            int r1 = r10 + 1
            r11.c = r1
            byte r1 = r7.a
            r8[r10] = r1
            goto L_0x00bc
        L_0x007e:
            int r10 = r11.c
            int r1 = r10 + 1
            r11.c = r1
            byte[] r1 = r7.zzxp
            int r2 = r11.a
            int r2 = r2 >> 2
            r2 = r2 & 63
            byte r1 = r1[r2]
            r8[r10] = r1
            int r10 = r11.c
            int r1 = r10 + 1
            r11.c = r1
            byte[] r1 = r7.zzxp
            int r2 = r11.a
            int r2 = r2 << 4
            r2 = r2 & 63
            byte r1 = r1[r2]
            r8[r10] = r1
            byte[] r10 = r7.zzxp
            byte[] r1 = zzxm
            if (r10 != r1) goto L_0x00bc
            int r10 = r11.c
            int r1 = r10 + 1
            r11.c = r1
            byte r1 = r7.a
            r8[r10] = r1
            int r10 = r11.c
            int r1 = r10 + 1
            r11.c = r1
            byte r1 = r7.a
            r8[r10] = r1
        L_0x00bc:
            int r10 = r11.f
            int r1 = r11.c
            int r1 = r1 - r9
            int r10 = r10 + r1
            r11.f = r10
            int r9 = r7.b
            if (r9 <= 0) goto L_0x00de
            int r9 = r11.f
            if (r9 <= 0) goto L_0x00de
            byte[] r9 = r7.zzxr
            int r10 = r11.c
            byte[] r1 = r7.zzxr
            int r1 = r1.length
            java.lang.System.arraycopy(r9, r0, r8, r10, r1)
            int r8 = r11.c
            byte[] r9 = r7.zzxr
            int r9 = r9.length
            int r8 = r8 + r9
            r11.c = r8
        L_0x00de:
            return
        L_0x00df:
            r2 = r9
            r9 = 0
        L_0x00e1:
            if (r9 >= r10) goto L_0x0172
            int r3 = r7.zzxt
            byte[] r3 = r7.a(r3, r11)
            int r4 = r11.g
            int r4 = r4 + r1
            int r4 = r4 % 3
            r11.g = r4
            int r4 = r2 + 1
            byte r2 = r8[r2]
            if (r2 >= 0) goto L_0x00f8
            int r2 = r2 + 256
        L_0x00f8:
            int r5 = r11.a
            int r5 = r5 << 8
            int r5 = r5 + r2
            r11.a = r5
            int r2 = r11.g
            if (r2 != 0) goto L_0x016d
            int r2 = r11.c
            int r5 = r2 + 1
            r11.c = r5
            byte[] r5 = r7.zzxp
            int r6 = r11.a
            int r6 = r6 >> 18
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r2] = r5
            int r2 = r11.c
            int r5 = r2 + 1
            r11.c = r5
            byte[] r5 = r7.zzxp
            int r6 = r11.a
            int r6 = r6 >> 12
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r2] = r5
            int r2 = r11.c
            int r5 = r2 + 1
            r11.c = r5
            byte[] r5 = r7.zzxp
            int r6 = r11.a
            int r6 = r6 >> 6
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r2] = r5
            int r2 = r11.c
            int r5 = r2 + 1
            r11.c = r5
            byte[] r5 = r7.zzxp
            int r6 = r11.a
            r6 = r6 & 63
            byte r5 = r5[r6]
            r3[r2] = r5
            int r2 = r11.f
            int r2 = r2 + 4
            r11.f = r2
            int r2 = r7.b
            if (r2 <= 0) goto L_0x016d
            int r2 = r7.b
            int r5 = r11.f
            if (r2 > r5) goto L_0x016d
            byte[] r2 = r7.zzxr
            int r5 = r11.c
            byte[] r6 = r7.zzxr
            int r6 = r6.length
            java.lang.System.arraycopy(r2, r0, r3, r5, r6)
            int r2 = r11.c
            byte[] r3 = r7.zzxr
            int r3 = r3.length
            int r2 = r2 + r3
            r11.c = r2
            r11.f = r0
        L_0x016d:
            int r9 = r9 + 1
            r2 = r4
            goto L_0x00e1
        L_0x0172:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzha.a(byte[], int, int, com.google.android.gms.internal.firebase_ml.zzhc):void");
    }

    /* access modifiers changed from: protected */
    public final boolean a(byte b) {
        if (b < 0) {
            return false;
        }
        byte[] bArr = this.zzxq;
        return b < bArr.length && bArr[b] != -1;
    }
}

package com.google.android.gms.internal.measurement;

final class zzie extends zzhz {
    zzie() {
    }

    private static int zza(byte[] bArr, int i, long j, int i2) {
        switch (i2) {
            case 0:
                return zzhy.zzch(i);
            case 1:
                return zzhy.zzr(i, zzhv.a(bArr, j));
            case 2:
                return zzhy.zzc(i, zzhv.a(bArr, j), zzhv.a(bArr, j + 1));
            default:
                throw new AssertionError();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0061, code lost:
        return -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00b9, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int a(int r16, byte[] r17, int r18, int r19) {
        /*
            r15 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r1 | r2
            int r4 = r0.length
            int r4 = r4 - r2
            r3 = r3 | r4
            r4 = 2
            r5 = 3
            r6 = 0
            if (r3 < 0) goto L_0x00ba
            long r7 = (long) r1
            long r1 = (long) r2
            long r1 = r1 - r7
            int r2 = (int) r1
            r1 = 16
            r9 = 1
            if (r2 >= r1) goto L_0x001c
            r1 = 0
            goto L_0x002e
        L_0x001c:
            r11 = r7
            r1 = 0
        L_0x001e:
            if (r1 >= r2) goto L_0x002d
            long r13 = r11 + r9
            byte r3 = com.google.android.gms.internal.measurement.zzhv.a((byte[]) r0, (long) r11)
            if (r3 >= 0) goto L_0x0029
            goto L_0x002e
        L_0x0029:
            int r1 = r1 + 1
            r11 = r13
            goto L_0x001e
        L_0x002d:
            r1 = r2
        L_0x002e:
            int r2 = r2 - r1
            long r11 = (long) r1
            long r7 = r7 + r11
        L_0x0031:
            r1 = 0
        L_0x0032:
            if (r2 <= 0) goto L_0x0040
            long r11 = r7 + r9
            byte r1 = com.google.android.gms.internal.measurement.zzhv.a((byte[]) r0, (long) r7)
            if (r1 < 0) goto L_0x0041
            int r2 = r2 + -1
            r7 = r11
            goto L_0x0032
        L_0x0040:
            r11 = r7
        L_0x0041:
            if (r2 != 0) goto L_0x0044
            return r6
        L_0x0044:
            int r2 = r2 + -1
            r3 = -32
            r7 = -65
            r8 = -1
            if (r1 >= r3) goto L_0x0062
            if (r2 != 0) goto L_0x0050
            return r1
        L_0x0050:
            int r2 = r2 + -1
            r3 = -62
            if (r1 < r3) goto L_0x0061
            long r13 = r11 + r9
            byte r1 = com.google.android.gms.internal.measurement.zzhv.a((byte[]) r0, (long) r11)
            if (r1 <= r7) goto L_0x005f
            goto L_0x0061
        L_0x005f:
            r7 = r13
            goto L_0x0031
        L_0x0061:
            return r8
        L_0x0062:
            r13 = -16
            if (r1 >= r13) goto L_0x008f
            if (r2 >= r4) goto L_0x006d
            int r0 = zza(r0, r1, r11, r2)
            return r0
        L_0x006d:
            int r2 = r2 + -2
            long r13 = r11 + r9
            byte r11 = com.google.android.gms.internal.measurement.zzhv.a((byte[]) r0, (long) r11)
            if (r11 > r7) goto L_0x008e
            r12 = -96
            if (r1 != r3) goto L_0x007d
            if (r11 < r12) goto L_0x008e
        L_0x007d:
            r3 = -19
            if (r1 != r3) goto L_0x0083
            if (r11 >= r12) goto L_0x008e
        L_0x0083:
            long r11 = r13 + r9
            byte r1 = com.google.android.gms.internal.measurement.zzhv.a((byte[]) r0, (long) r13)
            if (r1 <= r7) goto L_0x008c
            goto L_0x008e
        L_0x008c:
            r7 = r11
            goto L_0x0031
        L_0x008e:
            return r8
        L_0x008f:
            if (r2 >= r5) goto L_0x0096
            int r0 = zza(r0, r1, r11, r2)
            return r0
        L_0x0096:
            int r2 = r2 + -3
            long r13 = r11 + r9
            byte r3 = com.google.android.gms.internal.measurement.zzhv.a((byte[]) r0, (long) r11)
            if (r3 > r7) goto L_0x00b9
            int r1 = r1 << 28
            int r3 = r3 + 112
            int r1 = r1 + r3
            int r1 = r1 >> 30
            if (r1 != 0) goto L_0x00b9
            long r11 = r13 + r9
            byte r1 = com.google.android.gms.internal.measurement.zzhv.a((byte[]) r0, (long) r13)
            if (r1 > r7) goto L_0x00b9
            long r13 = r11 + r9
            byte r1 = com.google.android.gms.internal.measurement.zzhv.a((byte[]) r0, (long) r11)
            if (r1 <= r7) goto L_0x005f
        L_0x00b9:
            return r8
        L_0x00ba:
            java.lang.ArrayIndexOutOfBoundsException r3 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.Object[] r5 = new java.lang.Object[r5]
            int r0 = r0.length
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r5[r6] = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r18)
            r1 = 1
            r5[r1] = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r19)
            r5[r4] = r0
            java.lang.String r0 = "Array length=%d, index=%d, limit=%d"
            java.lang.String r0 = java.lang.String.format(r0, r5)
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzie.a(int, byte[], int, int):int");
    }

    /* access modifiers changed from: package-private */
    public final int a(CharSequence charSequence, byte[] bArr, int i, int i2) {
        long j;
        int i3;
        long j2;
        char charAt;
        CharSequence charSequence2 = charSequence;
        byte[] bArr2 = bArr;
        int i4 = i;
        int i5 = i2;
        long j3 = (long) i4;
        long j4 = ((long) i5) + j3;
        int length = charSequence.length();
        if (length > i5 || bArr2.length - i5 < i4) {
            char charAt2 = charSequence2.charAt(length - 1);
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(charAt2);
            sb.append(" at index ");
            sb.append(i4 + i5);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i6 = 0;
        while (i6 < length && (charAt = charSequence2.charAt(i6)) < 128) {
            zzhv.a(bArr2, j, (byte) charAt);
            i6++;
            j3 = 1 + j;
        }
        if (i6 == length) {
            return (int) j;
        }
        while (i6 < length) {
            char charAt3 = charSequence2.charAt(i6);
            if (charAt3 < 128 && j < j4) {
                j2 = j + 1;
            } else if (charAt3 < 2048 && j <= j4 - 2) {
                long j5 = j + 1;
                zzhv.a(bArr2, j, (byte) ((charAt3 >>> 6) | 960));
                j = j5 + 1;
                zzhv.a(bArr2, j5, (byte) ((charAt3 & '?') | 128));
                i6++;
            } else if ((charAt3 < 55296 || 57343 < charAt3) && j <= j4 - 3) {
                long j6 = j + 1;
                zzhv.a(bArr2, j, (byte) ((charAt3 >>> 12) | 480));
                j = j6 + 1;
                zzhv.a(bArr2, j6, (byte) (((charAt3 >>> 6) & 63) | 128));
                j2 = j + 1;
                charAt3 = (charAt3 & '?') | 128;
            } else if (j <= j4 - 4) {
                int i7 = i6 + 1;
                if (i7 != length) {
                    char charAt4 = charSequence2.charAt(i7);
                    if (Character.isSurrogatePair(charAt3, charAt4)) {
                        int codePoint = Character.toCodePoint(charAt3, charAt4);
                        long j7 = j + 1;
                        zzhv.a(bArr2, j, (byte) ((codePoint >>> 18) | 240));
                        long j8 = j7 + 1;
                        zzhv.a(bArr2, j7, (byte) (((codePoint >>> 12) & 63) | 128));
                        long j9 = j8 + 1;
                        zzhv.a(bArr2, j8, (byte) (((codePoint >>> 6) & 63) | 128));
                        j = j9 + 1;
                        zzhv.a(bArr2, j9, (byte) ((codePoint & 63) | 128));
                        i6 = i7;
                        i6++;
                    }
                } else {
                    i7 = i6;
                }
                throw new zzib(i7 - 1, length);
            } else if (55296 > charAt3 || charAt3 > 57343 || ((i3 = i6 + 1) != length && Character.isSurrogatePair(charAt3, charSequence2.charAt(i3)))) {
                StringBuilder sb2 = new StringBuilder(46);
                sb2.append("Failed writing ");
                sb2.append(charAt3);
                sb2.append(" at index ");
                sb2.append(j);
                throw new ArrayIndexOutOfBoundsException(sb2.toString());
            } else {
                throw new zzib(i6, length);
            }
            zzhv.a(bArr2, j, (byte) charAt3);
            j = j2;
            i6++;
        }
        return (int) j;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x003e A[LOOP:1: B:11:0x003e->B:37:0x00ff, LOOP_START, PHI: r4 r9 r10 r11 
  PHI: (r4v5 long) = (r4v4 long), (r4v7 long) binds: [B:8:0x0036, B:37:0x00ff] A[DONT_GENERATE, DONT_INLINE]
  PHI: (r9v3 int) = (r9v2 int), (r9v5 int) binds: [B:8:0x0036, B:37:0x00ff] A[DONT_GENERATE, DONT_INLINE]
  PHI: (r10v1 char) = (r10v0 char), (r10v2 char) binds: [B:8:0x0036, B:37:0x00ff] A[DONT_GENERATE, DONT_INLINE]
  PHI: (r11v2 long) = (r11v1 long), (r11v3 long) binds: [B:8:0x0036, B:37:0x00ff] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.CharSequence r22, java.nio.ByteBuffer r23) {
        /*
            r21 = this;
            r0 = r22
            r1 = r23
            long r2 = com.google.android.gms.internal.measurement.zzhv.a((java.nio.ByteBuffer) r23)
            int r4 = r23.position()
            long r4 = (long) r4
            long r4 = r4 + r2
            int r6 = r23.limit()
            long r6 = (long) r6
            long r6 = r6 + r2
            int r8 = r22.length()
            long r9 = (long) r8
            long r11 = r6 - r4
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 > 0) goto L_0x0149
            r9 = 0
        L_0x0020:
            r10 = 128(0x80, float:1.794E-43)
            r11 = 1
            if (r9 >= r8) goto L_0x0036
            char r13 = r0.charAt(r9)
            if (r13 >= r10) goto L_0x0036
            long r10 = r4 + r11
            byte r12 = (byte) r13
            com.google.android.gms.internal.measurement.zzhv.a((long) r4, (byte) r12)
            int r9 = r9 + 1
            r4 = r10
            goto L_0x0020
        L_0x0036:
            if (r9 != r8) goto L_0x003e
        L_0x0038:
            long r4 = r4 - r2
            int r0 = (int) r4
            r1.position(r0)
            return
        L_0x003e:
            if (r9 >= r8) goto L_0x0038
            char r13 = r0.charAt(r9)
            if (r13 >= r10) goto L_0x0055
            int r14 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r14 >= 0) goto L_0x0055
            long r14 = r4 + r11
            byte r13 = (byte) r13
            com.google.android.gms.internal.measurement.zzhv.a((long) r4, (byte) r13)
        L_0x0050:
            r4 = r11
        L_0x0051:
            r11 = 128(0x80, float:1.794E-43)
            goto L_0x00ff
        L_0x0055:
            r14 = 2048(0x800, float:2.87E-42)
            if (r13 >= r14) goto L_0x0078
            r14 = 2
            long r14 = r6 - r14
            int r16 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
            if (r16 > 0) goto L_0x0078
            long r14 = r4 + r11
            int r10 = r13 >>> 6
            r10 = r10 | 960(0x3c0, float:1.345E-42)
            byte r10 = (byte) r10
            com.google.android.gms.internal.measurement.zzhv.a((long) r4, (byte) r10)
            long r4 = r14 + r11
            r10 = r13 & 63
            r13 = 128(0x80, float:1.794E-43)
            r10 = r10 | r13
            byte r10 = (byte) r10
            com.google.android.gms.internal.measurement.zzhv.a((long) r14, (byte) r10)
            r14 = r4
            goto L_0x0050
        L_0x0078:
            r10 = 57343(0xdfff, float:8.0355E-41)
            r14 = 55296(0xd800, float:7.7486E-41)
            if (r13 < r14) goto L_0x0082
            if (r10 >= r13) goto L_0x00b1
        L_0x0082:
            r17 = 3
            long r17 = r6 - r17
            int r15 = (r4 > r17 ? 1 : (r4 == r17 ? 0 : -1))
            if (r15 > 0) goto L_0x00b1
            long r14 = r4 + r11
            int r10 = r13 >>> 12
            r10 = r10 | 480(0x1e0, float:6.73E-43)
            byte r10 = (byte) r10
            com.google.android.gms.internal.measurement.zzhv.a((long) r4, (byte) r10)
            long r4 = r14 + r11
            int r10 = r13 >>> 6
            r10 = r10 & 63
            r11 = 128(0x80, float:1.794E-43)
            r10 = r10 | r11
            byte r10 = (byte) r10
            com.google.android.gms.internal.measurement.zzhv.a((long) r14, (byte) r10)
            r14 = 1
            long r19 = r4 + r14
            r10 = r13 & 63
            r10 = r10 | r11
            byte r10 = (byte) r10
            com.google.android.gms.internal.measurement.zzhv.a((long) r4, (byte) r10)
            r14 = r19
            r4 = 1
            goto L_0x0051
        L_0x00b1:
            r11 = 4
            long r11 = r6 - r11
            int r15 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r15 > 0) goto L_0x0110
            int r10 = r9 + 1
            if (r10 == r8) goto L_0x0108
            char r9 = r0.charAt(r10)
            boolean r11 = java.lang.Character.isSurrogatePair(r13, r9)
            if (r11 == 0) goto L_0x0107
            int r9 = java.lang.Character.toCodePoint(r13, r9)
            r11 = 1
            long r13 = r4 + r11
            int r15 = r9 >>> 18
            r15 = r15 | 240(0xf0, float:3.36E-43)
            byte r15 = (byte) r15
            com.google.android.gms.internal.measurement.zzhv.a((long) r4, (byte) r15)
            long r4 = r13 + r11
            int r15 = r9 >>> 12
            r15 = r15 & 63
            r11 = 128(0x80, float:1.794E-43)
            r12 = r15 | 128(0x80, float:1.794E-43)
            byte r12 = (byte) r12
            com.google.android.gms.internal.measurement.zzhv.a((long) r13, (byte) r12)
            r12 = 1
            long r14 = r4 + r12
            int r16 = r9 >>> 6
            r12 = r16 & 63
            r12 = r12 | r11
            byte r12 = (byte) r12
            com.google.android.gms.internal.measurement.zzhv.a((long) r4, (byte) r12)
            r4 = 1
            long r12 = r14 + r4
            r9 = r9 & 63
            r9 = r9 | r11
            byte r9 = (byte) r9
            com.google.android.gms.internal.measurement.zzhv.a((long) r14, (byte) r9)
            r9 = r10
            r14 = r12
        L_0x00ff:
            int r9 = r9 + 1
            r11 = r4
            r4 = r14
            r10 = 128(0x80, float:1.794E-43)
            goto L_0x003e
        L_0x0107:
            r9 = r10
        L_0x0108:
            com.google.android.gms.internal.measurement.zzib r0 = new com.google.android.gms.internal.measurement.zzib
            int r9 = r9 + -1
            r0.<init>(r9, r8)
            throw r0
        L_0x0110:
            if (r14 > r13) goto L_0x0128
            if (r13 > r10) goto L_0x0128
            int r1 = r9 + 1
            if (r1 == r8) goto L_0x0122
            char r0 = r0.charAt(r1)
            boolean r0 = java.lang.Character.isSurrogatePair(r13, r0)
            if (r0 != 0) goto L_0x0128
        L_0x0122:
            com.google.android.gms.internal.measurement.zzib r0 = new com.google.android.gms.internal.measurement.zzib
            r0.<init>(r9, r8)
            throw r0
        L_0x0128:
            java.lang.ArrayIndexOutOfBoundsException r0 = new java.lang.ArrayIndexOutOfBoundsException
            r1 = 46
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r1)
            java.lang.String r1 = "Failed writing "
            r2.append(r1)
            r2.append(r13)
            java.lang.String r1 = " at index "
            r2.append(r1)
            r2.append(r4)
            java.lang.String r1 = r2.toString()
            r0.<init>(r1)
            throw r0
        L_0x0149:
            java.lang.ArrayIndexOutOfBoundsException r2 = new java.lang.ArrayIndexOutOfBoundsException
            int r8 = r8 + -1
            char r0 = r0.charAt(r8)
            int r1 = r23.limit()
            r3 = 37
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Failed writing "
            r4.append(r3)
            r4.append(r0)
            java.lang.String r0 = " at index "
            r4.append(r0)
            r4.append(r1)
            java.lang.String r0 = r4.toString()
            r2.<init>(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzie.a(java.lang.CharSequence, java.nio.ByteBuffer):void");
    }

    /* access modifiers changed from: package-private */
    public final String b(byte[] bArr, int i, int i2) {
        if ((i | i2 | ((bArr.length - i) - i2)) >= 0) {
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (r13 < i3) {
                byte a = zzhv.a(bArr, (long) r13);
                if (!zzia.zzd(a)) {
                    break;
                }
                i = r13 + 1;
                zzia.zza(a, cArr, i4);
                i4++;
            }
            int i5 = i4;
            while (r13 < i3) {
                int i6 = r13 + 1;
                byte a2 = zzhv.a(bArr, (long) r13);
                if (zzia.zzd(a2)) {
                    int i7 = i5 + 1;
                    zzia.zza(a2, cArr, i5);
                    while (i6 < i3) {
                        byte a3 = zzhv.a(bArr, (long) i6);
                        if (!zzia.zzd(a3)) {
                            break;
                        }
                        i6++;
                        zzia.zza(a3, cArr, i7);
                        i7++;
                    }
                    r13 = i6;
                    i5 = i7;
                } else if (zzia.zze(a2)) {
                    if (i6 < i3) {
                        int i8 = i6 + 1;
                        zzia.zza(a2, zzhv.a(bArr, (long) i6), cArr, i5);
                        r13 = i8;
                        i5++;
                    } else {
                        throw zzfi.i();
                    }
                } else if (zzia.zzf(a2)) {
                    if (i6 < i3 - 1) {
                        int i9 = i6 + 1;
                        int i10 = i9 + 1;
                        zzia.zza(a2, zzhv.a(bArr, (long) i6), zzhv.a(bArr, (long) i9), cArr, i5);
                        r13 = i10;
                        i5++;
                    } else {
                        throw zzfi.i();
                    }
                } else if (i6 < i3 - 2) {
                    int i11 = i6 + 1;
                    byte a4 = zzhv.a(bArr, (long) i6);
                    int i12 = i11 + 1;
                    zzia.zza(a2, a4, zzhv.a(bArr, (long) i11), zzhv.a(bArr, (long) i12), cArr, i5);
                    r13 = i12 + 1;
                    i5 = i5 + 1 + 1;
                } else {
                    throw zzfi.i();
                }
            }
            return new String(cArr, 0, i5);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
    }
}

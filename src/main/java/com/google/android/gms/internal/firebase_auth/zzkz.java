package com.google.android.gms.internal.firebase_auth;

final class zzkz extends zzku {
    zzkz() {
    }

    private static int zza(byte[] bArr, int i, long j, int i2) {
        switch (i2) {
            case 0:
                return zzkt.zzbh(i);
            case 1:
                return zzkt.zzs(i, zzkq.a(bArr, j));
            case 2:
                return zzkt.zzd(i, zzkq.a(bArr, j), zzkq.a(bArr, j + 1));
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
            byte r3 = com.google.android.gms.internal.firebase_auth.zzkq.a((byte[]) r0, (long) r11)
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
            byte r1 = com.google.android.gms.internal.firebase_auth.zzkq.a((byte[]) r0, (long) r7)
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
            byte r1 = com.google.android.gms.internal.firebase_auth.zzkq.a((byte[]) r0, (long) r11)
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
            byte r11 = com.google.android.gms.internal.firebase_auth.zzkq.a((byte[]) r0, (long) r11)
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
            byte r1 = com.google.android.gms.internal.firebase_auth.zzkq.a((byte[]) r0, (long) r13)
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
            byte r3 = com.google.android.gms.internal.firebase_auth.zzkq.a((byte[]) r0, (long) r11)
            if (r3 > r7) goto L_0x00b9
            int r1 = r1 << 28
            int r3 = r3 + 112
            int r1 = r1 + r3
            int r1 = r1 >> 30
            if (r1 != 0) goto L_0x00b9
            long r11 = r13 + r9
            byte r1 = com.google.android.gms.internal.firebase_auth.zzkq.a((byte[]) r0, (long) r13)
            if (r1 > r7) goto L_0x00b9
            long r13 = r11 + r9
            byte r1 = com.google.android.gms.internal.firebase_auth.zzkq.a((byte[]) r0, (long) r11)
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzkz.a(int, byte[], int, int):int");
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
            zzkq.a(bArr2, j, (byte) charAt);
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
                zzkq.a(bArr2, j, (byte) ((charAt3 >>> 6) | 960));
                j = j5 + 1;
                zzkq.a(bArr2, j5, (byte) ((charAt3 & '?') | 128));
                i6++;
            } else if ((charAt3 < 55296 || 57343 < charAt3) && j <= j4 - 3) {
                long j6 = j + 1;
                zzkq.a(bArr2, j, (byte) ((charAt3 >>> 12) | 480));
                j = j6 + 1;
                zzkq.a(bArr2, j6, (byte) (((charAt3 >>> 6) & 63) | 128));
                j2 = j + 1;
                charAt3 = (charAt3 & '?') | 128;
            } else if (j <= j4 - 4) {
                int i7 = i6 + 1;
                if (i7 != length) {
                    char charAt4 = charSequence2.charAt(i7);
                    if (Character.isSurrogatePair(charAt3, charAt4)) {
                        int codePoint = Character.toCodePoint(charAt3, charAt4);
                        long j7 = j + 1;
                        zzkq.a(bArr2, j, (byte) ((codePoint >>> 18) | 240));
                        long j8 = j7 + 1;
                        zzkq.a(bArr2, j7, (byte) (((codePoint >>> 12) & 63) | 128));
                        long j9 = j8 + 1;
                        zzkq.a(bArr2, j8, (byte) (((codePoint >>> 6) & 63) | 128));
                        j = j9 + 1;
                        zzkq.a(bArr2, j9, (byte) ((codePoint & 63) | 128));
                        i6 = i7;
                        i6++;
                    }
                } else {
                    i7 = i6;
                }
                throw new zzkw(i7 - 1, length);
            } else if (55296 > charAt3 || charAt3 > 57343 || ((i3 = i6 + 1) != length && Character.isSurrogatePair(charAt3, charSequence2.charAt(i3)))) {
                StringBuilder sb2 = new StringBuilder(46);
                sb2.append("Failed writing ");
                sb2.append(charAt3);
                sb2.append(" at index ");
                sb2.append(j);
                throw new ArrayIndexOutOfBoundsException(sb2.toString());
            } else {
                throw new zzkw(i6, length);
            }
            zzkq.a(bArr2, j, (byte) charAt3);
            j = j2;
            i6++;
        }
        return (int) j;
    }

    /* access modifiers changed from: package-private */
    public final String b(byte[] bArr, int i, int i2) {
        if ((i | i2 | ((bArr.length - i) - i2)) >= 0) {
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (r13 < i3) {
                byte a = zzkq.a(bArr, (long) r13);
                if (!zzkv.zzd(a)) {
                    break;
                }
                i = r13 + 1;
                zzkv.zza(a, cArr, i4);
                i4++;
            }
            int i5 = i4;
            while (r13 < i3) {
                int i6 = r13 + 1;
                byte a2 = zzkq.a(bArr, (long) r13);
                if (zzkv.zzd(a2)) {
                    int i7 = i5 + 1;
                    zzkv.zza(a2, cArr, i5);
                    while (i6 < i3) {
                        byte a3 = zzkq.a(bArr, (long) i6);
                        if (!zzkv.zzd(a3)) {
                            break;
                        }
                        i6++;
                        zzkv.zza(a3, cArr, i7);
                        i7++;
                    }
                    r13 = i6;
                    i5 = i7;
                } else if (zzkv.zze(a2)) {
                    if (i6 < i3) {
                        int i8 = i6 + 1;
                        zzkv.zza(a2, zzkq.a(bArr, (long) i6), cArr, i5);
                        r13 = i8;
                        i5++;
                    } else {
                        throw zzic.i();
                    }
                } else if (zzkv.zzf(a2)) {
                    if (i6 < i3 - 1) {
                        int i9 = i6 + 1;
                        int i10 = i9 + 1;
                        zzkv.zza(a2, zzkq.a(bArr, (long) i6), zzkq.a(bArr, (long) i9), cArr, i5);
                        r13 = i10;
                        i5++;
                    } else {
                        throw zzic.i();
                    }
                } else if (i6 < i3 - 2) {
                    int i11 = i6 + 1;
                    byte a4 = zzkq.a(bArr, (long) i6);
                    int i12 = i11 + 1;
                    zzkv.zza(a2, a4, zzkq.a(bArr, (long) i11), zzkq.a(bArr, (long) i12), cArr, i5);
                    r13 = i12 + 1;
                    i5 = i5 + 1 + 1;
                } else {
                    throw zzic.i();
                }
            }
            return new String(cArr, 0, i5);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
    }
}

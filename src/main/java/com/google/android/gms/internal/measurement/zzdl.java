package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;

final class zzdl {
    static int a(int i, byte[] bArr, int i2, int i3, zzdk zzdk) {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 5) {
                return i2 + 4;
            }
            switch (i4) {
                case 0:
                    return b(bArr, i2, zzdk);
                case 1:
                    return i2 + 8;
                case 2:
                    return a(bArr, i2, zzdk) + zzdk.zzada;
                case 3:
                    int i5 = (i & -8) | 4;
                    int i6 = 0;
                    while (i2 < i3) {
                        i2 = a(bArr, i2, zzdk);
                        i6 = zzdk.zzada;
                        if (i6 != i5) {
                            i2 = a(i6, bArr, i2, i3, zzdk);
                        } else if (i2 > i3 && i6 == i5) {
                            return i2;
                        } else {
                            throw zzfi.h();
                        }
                    }
                    if (i2 > i3) {
                    }
                    throw zzfi.h();
                default:
                    throw zzfi.d();
            }
        } else {
            throw zzfi.d();
        }
    }

    static int a(int i, byte[] bArr, int i2, int i3, zzff<?> zzff, zzdk zzdk) {
        zzfa zzfa = (zzfa) zzff;
        int a = a(bArr, i2, zzdk);
        while (true) {
            zzfa.zzbu(zzdk.zzada);
            if (a >= i3) {
                break;
            }
            int a2 = a(bArr, a, zzdk);
            if (i != zzdk.zzada) {
                break;
            }
            a = a(bArr, a2, zzdk);
        }
        return a;
    }

    static int a(int i, byte[] bArr, int i2, int i3, zzhs zzhs, zzdk zzdk) {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 != 5) {
                switch (i4) {
                    case 0:
                        int b = b(bArr, i2, zzdk);
                        zzhs.a(i, (Object) Long.valueOf(zzdk.zzadb));
                        return b;
                    case 1:
                        zzhs.a(i, (Object) Long.valueOf(b(bArr, i2)));
                        return i2 + 8;
                    case 2:
                        int a = a(bArr, i2, zzdk);
                        int i5 = zzdk.zzada;
                        if (i5 < 0) {
                            throw zzfi.b();
                        } else if (i5 <= bArr.length - a) {
                            zzhs.a(i, (Object) i5 == 0 ? zzdp.zzadh : zzdp.zzb(bArr, a, i5));
                            return a + i5;
                        } else {
                            throw zzfi.a();
                        }
                    case 3:
                        zzhs a2 = zzhs.a();
                        int i6 = (i & -8) | 4;
                        int i7 = 0;
                        while (true) {
                            if (i2 < i3) {
                                int a3 = a(bArr, i2, zzdk);
                                int i8 = zzdk.zzada;
                                if (i8 != i6) {
                                    i7 = i8;
                                    i2 = a(i8, bArr, a3, i3, a2, zzdk);
                                } else {
                                    i7 = i8;
                                    i2 = a3;
                                }
                            }
                        }
                        if (i2 > i3 || i7 != i6) {
                            throw zzfi.h();
                        }
                        zzhs.a(i, (Object) a2);
                        return i2;
                    default:
                        throw zzfi.d();
                }
            } else {
                zzhs.a(i, (Object) Integer.valueOf(a(bArr, i2)));
                return i2 + 4;
            }
        } else {
            throw zzfi.d();
        }
    }

    static int a(int i, byte[] bArr, int i2, zzdk zzdk) {
        int i3;
        int i4;
        int i5 = i & 127;
        int i6 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            i4 = b << 7;
        } else {
            int i7 = i5 | ((b & Byte.MAX_VALUE) << 7);
            int i8 = i6 + 1;
            byte b2 = bArr[i6];
            if (b2 >= 0) {
                i3 = b2 << Ascii.SO;
            } else {
                i5 = i7 | ((b2 & Byte.MAX_VALUE) << Ascii.SO);
                i6 = i8 + 1;
                byte b3 = bArr[i8];
                if (b3 >= 0) {
                    i4 = b3 << Ascii.NAK;
                } else {
                    i7 = i5 | ((b3 & Byte.MAX_VALUE) << Ascii.NAK);
                    i8 = i6 + 1;
                    byte b4 = bArr[i6];
                    if (b4 >= 0) {
                        i3 = b4 << Ascii.FS;
                    } else {
                        int i9 = i7 | ((b4 & Byte.MAX_VALUE) << Ascii.FS);
                        while (true) {
                            int i10 = i8 + 1;
                            if (bArr[i8] >= 0) {
                                zzdk.zzada = i9;
                                return i10;
                            }
                            i8 = i10;
                        }
                    }
                }
            }
            zzdk.zzada = i7 | i3;
            return i8;
        }
        zzdk.zzada = i5 | i4;
        return i6;
    }

    static int a(zzgx<?> zzgx, int i, byte[] bArr, int i2, int i3, zzff<?> zzff, zzdk zzdk) {
        int a = a((zzgx) zzgx, bArr, i2, i3, zzdk);
        while (true) {
            zzff.add(zzdk.zzadc);
            if (a >= i3) {
                break;
            }
            int a2 = a(bArr, a, zzdk);
            if (i != zzdk.zzada) {
                break;
            }
            a = a((zzgx) zzgx, bArr, a2, i3, zzdk);
        }
        return a;
    }

    static int a(zzgx zzgx, byte[] bArr, int i, int i2, int i3, zzdk zzdk) {
        zzgm zzgm = (zzgm) zzgx;
        Object newInstance = zzgm.newInstance();
        int a = zzgm.a(newInstance, bArr, i, i2, i3, zzdk);
        zzgm.zzj(newInstance);
        zzdk.zzadc = newInstance;
        return a;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int a(com.google.android.gms.internal.measurement.zzgx r6, byte[] r7, int r8, int r9, com.google.android.gms.internal.measurement.zzdk r10) {
        /*
            int r0 = r8 + 1
            byte r8 = r7[r8]
            if (r8 >= 0) goto L_0x000c
            int r0 = a((int) r8, (byte[]) r7, (int) r0, (com.google.android.gms.internal.measurement.zzdk) r10)
            int r8 = r10.zzada
        L_0x000c:
            r3 = r0
            if (r8 < 0) goto L_0x0025
            int r9 = r9 - r3
            if (r8 > r9) goto L_0x0025
            java.lang.Object r9 = r6.newInstance()
            int r8 = r8 + r3
            r0 = r6
            r1 = r9
            r2 = r7
            r4 = r8
            r5 = r10
            r0.zza(r1, r2, r3, r4, r5)
            r6.zzj(r9)
            r10.zzadc = r9
            return r8
        L_0x0025:
            com.google.android.gms.internal.measurement.zzfi r6 = com.google.android.gms.internal.measurement.zzfi.a()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzdl.a(com.google.android.gms.internal.measurement.zzgx, byte[], int, int, com.google.android.gms.internal.measurement.zzdk):int");
    }

    static int a(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
    }

    static int a(byte[] bArr, int i, zzdk zzdk) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return a((int) b, bArr, i2, zzdk);
        }
        zzdk.zzada = b;
        return i2;
    }

    static int a(byte[] bArr, int i, zzff<?> zzff, zzdk zzdk) {
        zzfa zzfa = (zzfa) zzff;
        int a = a(bArr, i, zzdk);
        int i2 = zzdk.zzada + a;
        while (a < i2) {
            a = a(bArr, a, zzdk);
            zzfa.zzbu(zzdk.zzada);
        }
        if (a == i2) {
            return a;
        }
        throw zzfi.a();
    }

    static int b(byte[] bArr, int i, zzdk zzdk) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzdk.zzadb = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j2 = (j & 127) | (((long) (b & Byte.MAX_VALUE)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b2 & Byte.MAX_VALUE)) << i4;
            int i6 = i5;
            b = b2;
            i3 = i6;
        }
        zzdk.zzadb = j2;
        return i3;
    }

    static long b(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    static double c(byte[] bArr, int i) {
        return Double.longBitsToDouble(b(bArr, i));
    }

    static int c(byte[] bArr, int i, zzdk zzdk) {
        int a = a(bArr, i, zzdk);
        int i2 = zzdk.zzada;
        if (i2 < 0) {
            throw zzfi.b();
        } else if (i2 == 0) {
            zzdk.zzadc = "";
            return a;
        } else {
            zzdk.zzadc = new String(bArr, a, i2, zzez.a);
            return a + i2;
        }
    }

    static float d(byte[] bArr, int i) {
        return Float.intBitsToFloat(a(bArr, i));
    }

    static int d(byte[] bArr, int i, zzdk zzdk) {
        int a = a(bArr, i, zzdk);
        int i2 = zzdk.zzada;
        if (i2 < 0) {
            throw zzfi.b();
        } else if (i2 == 0) {
            zzdk.zzadc = "";
            return a;
        } else {
            zzdk.zzadc = zzhy.a(bArr, a, i2);
            return a + i2;
        }
    }

    static int e(byte[] bArr, int i, zzdk zzdk) {
        int a = a(bArr, i, zzdk);
        int i2 = zzdk.zzada;
        if (i2 < 0) {
            throw zzfi.b();
        } else if (i2 > bArr.length - a) {
            throw zzfi.a();
        } else if (i2 == 0) {
            zzdk.zzadc = zzdp.zzadh;
            return a;
        } else {
            zzdk.zzadc = zzdp.zzb(bArr, a, i2);
            return a + i2;
        }
    }
}

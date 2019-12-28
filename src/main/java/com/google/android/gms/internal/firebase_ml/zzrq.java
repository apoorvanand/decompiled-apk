package com.google.android.gms.internal.firebase_ml;

import com.google.common.base.Ascii;

final class zzrq {
    static int a(int i, byte[] bArr, int i2, int i3, zzrr zzrr) {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 5) {
                return i2 + 4;
            }
            switch (i4) {
                case 0:
                    return b(bArr, i2, zzrr);
                case 1:
                    return i2 + 8;
                case 2:
                    return a(bArr, i2, zzrr) + zzrr.zzbia;
                case 3:
                    int i5 = (i & -8) | 4;
                    int i6 = 0;
                    while (i2 < i3) {
                        i2 = a(bArr, i2, zzrr);
                        i6 = zzrr.zzbia;
                        if (i6 != i5) {
                            i2 = a(i6, bArr, i2, i3, zzrr);
                        } else if (i2 > i3 && i6 == i5) {
                            return i2;
                        } else {
                            throw zztm.e();
                        }
                    }
                    if (i2 > i3) {
                    }
                    throw zztm.e();
                default:
                    throw zztm.c();
            }
        } else {
            throw zztm.c();
        }
    }

    static int a(int i, byte[] bArr, int i2, int i3, zztl<?> zztl, zzrr zzrr) {
        zztd zztd = (zztd) zztl;
        int a = a(bArr, i2, zzrr);
        while (true) {
            zztd.zzcy(zzrr.zzbia);
            if (a >= i3) {
                break;
            }
            int a2 = a(bArr, a, zzrr);
            if (i != zzrr.zzbia) {
                break;
            }
            a = a(bArr, a2, zzrr);
        }
        return a;
    }

    static int a(int i, byte[] bArr, int i2, int i3, zzvv zzvv, zzrr zzrr) {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 != 5) {
                switch (i4) {
                    case 0:
                        int b = b(bArr, i2, zzrr);
                        zzvv.a(i, (Object) Long.valueOf(zzrr.zzbib));
                        return b;
                    case 1:
                        zzvv.a(i, (Object) Long.valueOf(b(bArr, i2)));
                        return i2 + 8;
                    case 2:
                        int a = a(bArr, i2, zzrr);
                        int i5 = zzrr.zzbia;
                        if (i5 < 0) {
                            throw zztm.b();
                        } else if (i5 <= bArr.length - a) {
                            zzvv.a(i, (Object) i5 == 0 ? zzru.zzbig : zzru.zzc(bArr, a, i5));
                            return a + i5;
                        } else {
                            throw zztm.a();
                        }
                    case 3:
                        zzvv a2 = zzvv.a();
                        int i6 = (i & -8) | 4;
                        int i7 = 0;
                        while (true) {
                            if (i2 < i3) {
                                int a3 = a(bArr, i2, zzrr);
                                int i8 = zzrr.zzbia;
                                if (i8 != i6) {
                                    i7 = i8;
                                    i2 = a(i8, bArr, a3, i3, a2, zzrr);
                                } else {
                                    i7 = i8;
                                    i2 = a3;
                                }
                            }
                        }
                        if (i2 > i3 || i7 != i6) {
                            throw zztm.e();
                        }
                        zzvv.a(i, (Object) a2);
                        return i2;
                    default:
                        throw zztm.c();
                }
            } else {
                zzvv.a(i, (Object) Integer.valueOf(a(bArr, i2)));
                return i2 + 4;
            }
        } else {
            throw zztm.c();
        }
    }

    static int a(int i, byte[] bArr, int i2, zzrr zzrr) {
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
                                zzrr.zzbia = i9;
                                return i10;
                            }
                            i8 = i10;
                        }
                    }
                }
            }
            zzrr.zzbia = i7 | i3;
            return i8;
        }
        zzrr.zzbia = i5 | i4;
        return i6;
    }

    static int a(zzvc<?> zzvc, int i, byte[] bArr, int i2, int i3, zztl<?> zztl, zzrr zzrr) {
        int a = a((zzvc) zzvc, bArr, i2, i3, zzrr);
        while (true) {
            zztl.add(zzrr.zzbic);
            if (a >= i3) {
                break;
            }
            int a2 = a(bArr, a, zzrr);
            if (i != zzrr.zzbia) {
                break;
            }
            a = a((zzvc) zzvc, bArr, a2, i3, zzrr);
        }
        return a;
    }

    static int a(zzvc zzvc, byte[] bArr, int i, int i2, int i3, zzrr zzrr) {
        zzuq zzuq = (zzuq) zzvc;
        Object newInstance = zzuq.newInstance();
        int a = zzuq.a(newInstance, bArr, i, i2, i3, zzrr);
        zzuq.zzq(newInstance);
        zzrr.zzbic = newInstance;
        return a;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int a(com.google.android.gms.internal.firebase_ml.zzvc r6, byte[] r7, int r8, int r9, com.google.android.gms.internal.firebase_ml.zzrr r10) {
        /*
            int r0 = r8 + 1
            byte r8 = r7[r8]
            if (r8 >= 0) goto L_0x000c
            int r0 = a((int) r8, (byte[]) r7, (int) r0, (com.google.android.gms.internal.firebase_ml.zzrr) r10)
            int r8 = r10.zzbia
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
            r6.zzq(r9)
            r10.zzbic = r9
            return r8
        L_0x0025:
            com.google.android.gms.internal.firebase_ml.zztm r6 = com.google.android.gms.internal.firebase_ml.zztm.a()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzrq.a(com.google.android.gms.internal.firebase_ml.zzvc, byte[], int, int, com.google.android.gms.internal.firebase_ml.zzrr):int");
    }

    static int a(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
    }

    static int a(byte[] bArr, int i, zzrr zzrr) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return a((int) b, bArr, i2, zzrr);
        }
        zzrr.zzbia = b;
        return i2;
    }

    static int a(byte[] bArr, int i, zztl<?> zztl, zzrr zzrr) {
        zztd zztd = (zztd) zztl;
        int a = a(bArr, i, zzrr);
        int i2 = zzrr.zzbia + a;
        while (a < i2) {
            a = a(bArr, a, zzrr);
            zztd.zzcy(zzrr.zzbia);
        }
        if (a == i2) {
            return a;
        }
        throw zztm.a();
    }

    static int b(byte[] bArr, int i, zzrr zzrr) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzrr.zzbib = j;
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
        zzrr.zzbib = j2;
        return i3;
    }

    static long b(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    static double c(byte[] bArr, int i) {
        return Double.longBitsToDouble(b(bArr, i));
    }

    static int c(byte[] bArr, int i, zzrr zzrr) {
        int a = a(bArr, i, zzrr);
        int i2 = zzrr.zzbia;
        if (i2 < 0) {
            throw zztm.b();
        } else if (i2 == 0) {
            zzrr.zzbic = "";
            return a;
        } else {
            zzrr.zzbic = new String(bArr, a, i2, zzte.a);
            return a + i2;
        }
    }

    static float d(byte[] bArr, int i) {
        return Float.intBitsToFloat(a(bArr, i));
    }

    static int d(byte[] bArr, int i, zzrr zzrr) {
        int a = a(bArr, i, zzrr);
        int i2 = zzrr.zzbia;
        if (i2 < 0) {
            throw zztm.b();
        } else if (i2 == 0) {
            zzrr.zzbic = "";
            return a;
        } else {
            zzrr.zzbic = zzwc.a(bArr, a, i2);
            return a + i2;
        }
    }

    static int e(byte[] bArr, int i, zzrr zzrr) {
        int a = a(bArr, i, zzrr);
        int i2 = zzrr.zzbia;
        if (i2 < 0) {
            throw zztm.b();
        } else if (i2 > bArr.length - a) {
            throw zztm.a();
        } else if (i2 == 0) {
            zzrr.zzbic = zzru.zzbig;
            return a;
        } else {
            zzrr.zzbic = zzru.zzc(bArr, a, i2);
            return a + i2;
        }
    }
}

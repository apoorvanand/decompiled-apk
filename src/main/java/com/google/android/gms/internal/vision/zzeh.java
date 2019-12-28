package com.google.android.gms.internal.vision;

import com.google.common.base.Ascii;

final class zzeh {
    static int a(int i, byte[] bArr, int i2, int i3, zzei zzei) {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 5) {
                return i2 + 4;
            }
            switch (i4) {
                case 0:
                    return b(bArr, i2, zzei);
                case 1:
                    return i2 + 8;
                case 2:
                    return a(bArr, i2, zzei) + zzei.zzro;
                case 3:
                    int i5 = (i & -8) | 4;
                    int i6 = 0;
                    while (i2 < i3) {
                        i2 = a(bArr, i2, zzei);
                        i6 = zzei.zzro;
                        if (i6 != i5) {
                            i2 = a(i6, bArr, i2, i3, zzei);
                        } else if (i2 > i3 && i6 == i5) {
                            return i2;
                        } else {
                            throw zzgf.h();
                        }
                    }
                    if (i2 > i3) {
                    }
                    throw zzgf.h();
                default:
                    throw zzgf.d();
            }
        } else {
            throw zzgf.d();
        }
    }

    static int a(int i, byte[] bArr, int i2, int i3, zzge<?> zzge, zzei zzei) {
        zzfz zzfz = (zzfz) zzge;
        int a = a(bArr, i2, zzei);
        while (true) {
            zzfz.zzbg(zzei.zzro);
            if (a >= i3) {
                break;
            }
            int a2 = a(bArr, a, zzei);
            if (i != zzei.zzro) {
                break;
            }
            a = a(bArr, a2, zzei);
        }
        return a;
    }

    static int a(int i, byte[] bArr, int i2, int i3, zzip zzip, zzei zzei) {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 != 5) {
                switch (i4) {
                    case 0:
                        int b = b(bArr, i2, zzei);
                        zzip.a(i, (Object) Long.valueOf(zzei.zzrp));
                        return b;
                    case 1:
                        zzip.a(i, (Object) Long.valueOf(b(bArr, i2)));
                        return i2 + 8;
                    case 2:
                        int a = a(bArr, i2, zzei);
                        int i5 = zzei.zzro;
                        if (i5 < 0) {
                            throw zzgf.b();
                        } else if (i5 <= bArr.length - a) {
                            zzip.a(i, (Object) i5 == 0 ? zzeo.zzrx : zzeo.zzb(bArr, a, i5));
                            return a + i5;
                        } else {
                            throw zzgf.a();
                        }
                    case 3:
                        zzip a2 = zzip.a();
                        int i6 = (i & -8) | 4;
                        int i7 = 0;
                        while (true) {
                            if (i2 < i3) {
                                int a3 = a(bArr, i2, zzei);
                                int i8 = zzei.zzro;
                                if (i8 != i6) {
                                    i7 = i8;
                                    i2 = a(i8, bArr, a3, i3, a2, zzei);
                                } else {
                                    i7 = i8;
                                    i2 = a3;
                                }
                            }
                        }
                        if (i2 > i3 || i7 != i6) {
                            throw zzgf.h();
                        }
                        zzip.a(i, (Object) a2);
                        return i2;
                    default:
                        throw zzgf.d();
                }
            } else {
                zzip.a(i, (Object) Integer.valueOf(a(bArr, i2)));
                return i2 + 4;
            }
        } else {
            throw zzgf.d();
        }
    }

    static int a(int i, byte[] bArr, int i2, zzei zzei) {
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
                                zzei.zzro = i9;
                                return i10;
                            }
                            i8 = i10;
                        }
                    }
                }
            }
            zzei.zzro = i7 | i3;
            return i8;
        }
        zzei.zzro = i5 | i4;
        return i6;
    }

    static int a(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
    }

    static int a(byte[] bArr, int i, zzei zzei) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return a((int) b, bArr, i2, zzei);
        }
        zzei.zzro = b;
        return i2;
    }

    static int a(byte[] bArr, int i, zzge<?> zzge, zzei zzei) {
        zzfz zzfz = (zzfz) zzge;
        int a = a(bArr, i, zzei);
        int i2 = zzei.zzro + a;
        while (a < i2) {
            a = a(bArr, a, zzei);
            zzfz.zzbg(zzei.zzro);
        }
        if (a == i2) {
            return a;
        }
        throw zzgf.a();
    }

    static int b(byte[] bArr, int i, zzei zzei) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzei.zzrp = j;
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
        zzei.zzrp = j2;
        return i3;
    }

    static long b(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    static double c(byte[] bArr, int i) {
        return Double.longBitsToDouble(b(bArr, i));
    }

    static int c(byte[] bArr, int i, zzei zzei) {
        int a = a(bArr, i, zzei);
        int i2 = zzei.zzro;
        if (i2 < 0) {
            throw zzgf.b();
        } else if (i2 == 0) {
            zzei.zzrq = "";
            return a;
        } else {
            zzei.zzrq = new String(bArr, a, i2, zzga.a);
            return a + i2;
        }
    }

    static float d(byte[] bArr, int i) {
        return Float.intBitsToFloat(a(bArr, i));
    }

    static int d(byte[] bArr, int i, zzei zzei) {
        int a = a(bArr, i, zzei);
        int i2 = zzei.zzro;
        if (i2 < 0) {
            throw zzgf.b();
        } else if (i2 == 0) {
            zzei.zzrq = "";
            return a;
        } else {
            zzei.zzrq = zziw.a(bArr, a, i2);
            return a + i2;
        }
    }

    static int e(byte[] bArr, int i, zzei zzei) {
        int a = a(bArr, i, zzei);
        int i2 = zzei.zzro;
        if (i2 < 0) {
            throw zzgf.b();
        } else if (i2 > bArr.length - a) {
            throw zzgf.a();
        } else if (i2 == 0) {
            zzei.zzrq = zzeo.zzrx;
            return a;
        } else {
            zzei.zzrq = zzeo.zzb(bArr, a, i2);
            return a + i2;
        }
    }
}

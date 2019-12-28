package com.google.android.gms.internal.clearcut;

import com.google.common.base.Ascii;

final class zzax {
    static int a(int i, byte[] bArr, int i2, int i3, zzay zzay) {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 5) {
                return i2 + 4;
            }
            switch (i4) {
                case 0:
                    return b(bArr, i2, zzay);
                case 1:
                    return i2 + 8;
                case 2:
                    return a(bArr, i2, zzay) + zzay.zzfd;
                case 3:
                    int i5 = (i & -8) | 4;
                    int i6 = 0;
                    while (i2 < i3) {
                        i2 = a(bArr, i2, zzay);
                        i6 = zzay.zzfd;
                        if (i6 != i5) {
                            i2 = a(i6, bArr, i2, i3, zzay);
                        } else if (i2 > i3 && i6 == i5) {
                            return i2;
                        } else {
                            throw zzco.d();
                        }
                    }
                    if (i2 > i3) {
                    }
                    throw zzco.d();
                default:
                    throw zzco.b();
            }
        } else {
            throw zzco.b();
        }
    }

    static int a(int i, byte[] bArr, int i2, int i3, zzcn<?> zzcn, zzay zzay) {
        zzch zzch = (zzch) zzcn;
        int a = a(bArr, i2, zzay);
        while (true) {
            zzch.zzac(zzay.zzfd);
            if (a >= i3) {
                break;
            }
            int a2 = a(bArr, a, zzay);
            if (i != zzay.zzfd) {
                break;
            }
            a = a(bArr, a2, zzay);
        }
        return a;
    }

    static int a(int i, byte[] bArr, int i2, int i3, zzey zzey, zzay zzay) {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 != 5) {
                switch (i4) {
                    case 0:
                        int b = b(bArr, i2, zzay);
                        zzey.a(i, (Object) Long.valueOf(zzay.zzfe));
                        return b;
                    case 1:
                        zzey.a(i, (Object) Long.valueOf(b(bArr, i2)));
                        return i2 + 8;
                    case 2:
                        int a = a(bArr, i2, zzay);
                        int i5 = zzay.zzfd;
                        zzey.a(i, (Object) i5 == 0 ? zzbb.zzfi : zzbb.zzb(bArr, a, i5));
                        return a + i5;
                    case 3:
                        zzey a2 = zzey.a();
                        int i6 = (i & -8) | 4;
                        int i7 = 0;
                        while (true) {
                            if (i2 < i3) {
                                int a3 = a(bArr, i2, zzay);
                                int i8 = zzay.zzfd;
                                if (i8 != i6) {
                                    i7 = i8;
                                    i2 = a(i8, bArr, a3, i3, a2, zzay);
                                } else {
                                    i7 = i8;
                                    i2 = a3;
                                }
                            }
                        }
                        if (i2 > i3 || i7 != i6) {
                            throw zzco.d();
                        }
                        zzey.a(i, (Object) a2);
                        return i2;
                    default:
                        throw zzco.b();
                }
            } else {
                zzey.a(i, (Object) Integer.valueOf(a(bArr, i2)));
                return i2 + 4;
            }
        } else {
            throw zzco.b();
        }
    }

    static int a(int i, byte[] bArr, int i2, zzay zzay) {
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
                                zzay.zzfd = i9;
                                return i10;
                            }
                            i8 = i10;
                        }
                    }
                }
            }
            zzay.zzfd = i7 | i3;
            return i8;
        }
        zzay.zzfd = i5 | i4;
        return i6;
    }

    static int a(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
    }

    static int a(byte[] bArr, int i, zzay zzay) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return a((int) b, bArr, i2, zzay);
        }
        zzay.zzfd = b;
        return i2;
    }

    static int a(byte[] bArr, int i, zzcn<?> zzcn, zzay zzay) {
        zzch zzch = (zzch) zzcn;
        int a = a(bArr, i, zzay);
        int i2 = zzay.zzfd + a;
        while (a < i2) {
            a = a(bArr, a, zzay);
            zzch.zzac(zzay.zzfd);
        }
        if (a == i2) {
            return a;
        }
        throw zzco.a();
    }

    static int b(byte[] bArr, int i, zzay zzay) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzay.zzfe = j;
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
        zzay.zzfe = j2;
        return i3;
    }

    static long b(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    static double c(byte[] bArr, int i) {
        return Double.longBitsToDouble(b(bArr, i));
    }

    static int c(byte[] bArr, int i, zzay zzay) {
        int a = a(bArr, i, zzay);
        int i2 = zzay.zzfd;
        if (i2 == 0) {
            zzay.zzff = "";
            return a;
        }
        zzay.zzff = new String(bArr, a, i2, zzci.a);
        return a + i2;
    }

    static float d(byte[] bArr, int i) {
        return Float.intBitsToFloat(a(bArr, i));
    }

    static int d(byte[] bArr, int i, zzay zzay) {
        int a = a(bArr, i, zzay);
        int i2 = zzay.zzfd;
        if (i2 == 0) {
            zzay.zzff = "";
            return a;
        }
        int i3 = a + i2;
        if (zzff.zze(bArr, a, i3)) {
            zzay.zzff = new String(bArr, a, i2, zzci.a);
            return i3;
        }
        throw zzco.e();
    }

    static int e(byte[] bArr, int i, zzay zzay) {
        int a = a(bArr, i, zzay);
        int i2 = zzay.zzfd;
        if (i2 == 0) {
            zzay.zzff = zzbb.zzfi;
            return a;
        }
        zzay.zzff = zzbb.zzb(bArr, a, i2);
        return a + i2;
    }
}

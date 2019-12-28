package com.google.android.gms.internal.firebase_auth;

import com.google.common.base.Ascii;
import java.util.Arrays;

final class zzgt extends zzgr {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzwj;
    private int zzwk;
    private int zzwl;
    private int zzwm;
    private int zzwn;

    private zzgt(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzwn = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzwl = this.pos;
        this.zzwj = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0066, code lost:
        if (r2[r3] >= 0) goto L_0x0068;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzha() {
        /*
            r5 = this;
            int r0 = r5.pos
            int r1 = r5.limit
            if (r1 == r0) goto L_0x006b
            byte[] r2 = r5.buffer
            int r3 = r0 + 1
            byte r0 = r2[r0]
            if (r0 < 0) goto L_0x0011
            r5.pos = r3
            return r0
        L_0x0011:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L_0x006b
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x0022
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
            goto L_0x0068
        L_0x0022:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x002f
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L_0x002d:
            r1 = r3
            goto L_0x0068
        L_0x002f:
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x003d
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x0068
        L_0x003d:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L_0x002d
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x0068
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x002d
            int r1 = r3 + 1
            byte r3 = r2[r3]
            if (r3 >= 0) goto L_0x0068
            int r3 = r1 + 1
            byte r1 = r2[r1]
            if (r1 >= 0) goto L_0x002d
            int r1 = r3 + 1
            byte r2 = r2[r3]
            if (r2 < 0) goto L_0x006b
        L_0x0068:
            r5.pos = r1
            return r0
        L_0x006b:
            long r0 = r5.a()
            int r1 = (int) r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzgt.zzha():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b0, code lost:
        if (((long) r2[r0]) >= 0) goto L_0x00b4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zzhb() {
        /*
            r11 = this;
            int r0 = r11.pos
            int r1 = r11.limit
            if (r1 == r0) goto L_0x00b8
            byte[] r2 = r11.buffer
            int r3 = r0 + 1
            byte r0 = r2[r0]
            if (r0 < 0) goto L_0x0012
            r11.pos = r3
            long r0 = (long) r0
            return r0
        L_0x0012:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L_0x00b8
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x0025
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
        L_0x0022:
            long r2 = (long) r0
            goto L_0x00b5
        L_0x0025:
            int r3 = r1 + 1
            byte r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L_0x0036
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
            long r0 = (long) r0
            r9 = r0
            r1 = r3
            r2 = r9
            goto L_0x00b5
        L_0x0036:
            int r1 = r3 + 1
            byte r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L_0x0044
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x0022
        L_0x0044:
            long r3 = (long) r0
            int r0 = r1 + 1
            byte r1 = r2[r1]
            long r5 = (long) r1
            r1 = 28
            long r5 = r5 << r1
            long r3 = r3 ^ r5
            r5 = 0
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L_0x005b
            r1 = 266354560(0xfe03f80, double:1.315966377E-315)
        L_0x0057:
            long r2 = r3 ^ r1
            r1 = r0
            goto L_0x00b5
        L_0x005b:
            int r1 = r0 + 1
            byte r0 = r2[r0]
            long r7 = (long) r0
            r0 = 35
            long r7 = r7 << r0
            long r3 = r3 ^ r7
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x0070
            r5 = -34093383808(0xfffffff80fe03f80, double:NaN)
        L_0x006d:
            long r2 = r3 ^ r5
            goto L_0x00b5
        L_0x0070:
            int r0 = r1 + 1
            byte r1 = r2[r1]
            long r7 = (long) r1
            r1 = 42
            long r7 = r7 << r1
            long r3 = r3 ^ r7
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L_0x0083
            r1 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
            goto L_0x0057
        L_0x0083:
            int r1 = r0 + 1
            byte r0 = r2[r0]
            long r7 = (long) r0
            r0 = 49
            long r7 = r7 << r0
            long r3 = r3 ^ r7
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x0096
            r5 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
            goto L_0x006d
        L_0x0096:
            int r0 = r1 + 1
            byte r1 = r2[r1]
            long r7 = (long) r1
            r1 = 56
            long r7 = r7 << r1
            long r3 = r3 ^ r7
            r7 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
            long r3 = r3 ^ r7
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 >= 0) goto L_0x00b3
            int r1 = r0 + 1
            byte r0 = r2[r0]
            long r7 = (long) r0
            int r0 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r0 < 0) goto L_0x00b8
            goto L_0x00b4
        L_0x00b3:
            r1 = r0
        L_0x00b4:
            r2 = r3
        L_0x00b5:
            r11.pos = r1
            return r2
        L_0x00b8:
            long r0 = r11.a()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzgt.zzhb():long");
    }

    private final int zzhc() {
        int i = this.pos;
        if (this.limit - i >= 4) {
            byte[] bArr = this.buffer;
            this.pos = i + 4;
            return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
        }
        throw zzic.a();
    }

    private final long zzhd() {
        int i = this.pos;
        if (this.limit - i >= 8) {
            byte[] bArr = this.buffer;
            this.pos = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }
        throw zzic.a();
    }

    private final void zzhe() {
        this.limit += this.zzwk;
        int i = this.limit;
        int i2 = i - this.zzwl;
        int i3 = this.zzwn;
        if (i2 > i3) {
            this.zzwk = i2 - i3;
            this.limit = i - this.zzwk;
            return;
        }
        this.zzwk = 0;
    }

    private final byte zzhf() {
        int i = this.pos;
        if (i != this.limit) {
            byte[] bArr = this.buffer;
            this.pos = i + 1;
            return bArr[i];
        }
        throw zzic.a();
    }

    private final void zzx(int i) {
        if (i >= 0) {
            int i2 = this.limit;
            int i3 = this.pos;
            if (i <= i2 - i3) {
                this.pos = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw zzic.b();
        }
        throw zzic.a();
    }

    /* access modifiers changed from: package-private */
    public final long a() {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzhf = zzhf();
            j |= ((long) (zzhf & Byte.MAX_VALUE)) << i;
            if ((zzhf & 128) == 0) {
                return j;
            }
        }
        throw zzic.c();
    }

    public final double readDouble() {
        return Double.longBitsToDouble(zzhd());
    }

    public final float readFloat() {
        return Float.intBitsToFloat(zzhc());
    }

    public final String readString() {
        int zzha = zzha();
        if (zzha > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzha <= i - i2) {
                String str = new String(this.buffer, i2, zzha, zzht.a);
                this.pos += zzha;
                return str;
            }
        }
        if (zzha == 0) {
            return "";
        }
        if (zzha < 0) {
            throw zzic.b();
        }
        throw zzic.a();
    }

    public final int zzgi() {
        if (zzgy()) {
            this.zzwm = 0;
            return 0;
        }
        this.zzwm = zzha();
        int i = this.zzwm;
        if ((i >>> 3) != 0) {
            return i;
        }
        throw zzic.d();
    }

    public final long zzgj() {
        return zzhb();
    }

    public final long zzgk() {
        return zzhb();
    }

    public final int zzgl() {
        return zzha();
    }

    public final long zzgm() {
        return zzhd();
    }

    public final int zzgn() {
        return zzhc();
    }

    public final boolean zzgo() {
        return zzhb() != 0;
    }

    public final String zzgp() {
        int zzha = zzha();
        if (zzha > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzha <= i - i2) {
                String a = zzkt.a(this.buffer, i2, zzha);
                this.pos += zzha;
                return a;
            }
        }
        if (zzha == 0) {
            return "";
        }
        if (zzha <= 0) {
            throw zzic.b();
        }
        throw zzic.a();
    }

    public final zzgf zzgq() {
        byte[] bArr;
        int zzha = zzha();
        if (zzha > 0) {
            int i = this.limit;
            int i2 = this.pos;
            if (zzha <= i - i2) {
                zzgf zza = zzgf.zza(this.buffer, i2, zzha);
                this.pos += zzha;
                return zza;
            }
        }
        if (zzha == 0) {
            return zzgf.zzvv;
        }
        if (zzha > 0) {
            int i3 = this.limit;
            int i4 = this.pos;
            if (zzha <= i3 - i4) {
                this.pos = zzha + i4;
                bArr = Arrays.copyOfRange(this.buffer, i4, this.pos);
                return zzgf.a(bArr);
            }
        }
        if (zzha > 0) {
            throw zzic.a();
        } else if (zzha == 0) {
            bArr = zzht.EMPTY_BYTE_ARRAY;
            return zzgf.a(bArr);
        } else {
            throw zzic.b();
        }
    }

    public final int zzgr() {
        return zzha();
    }

    public final int zzgs() {
        return zzha();
    }

    public final int zzgt() {
        return zzhc();
    }

    public final long zzgu() {
        return zzhd();
    }

    public final int zzgv() {
        return zzw(zzha());
    }

    public final long zzgw() {
        return zza(zzhb());
    }

    public final boolean zzgy() {
        return this.pos == this.limit;
    }

    public final int zzgz() {
        return this.pos - this.zzwl;
    }

    public final void zzs(int i) {
        if (this.zzwm != i) {
            throw zzic.e();
        }
    }

    public final boolean zzt(int i) {
        int zzgi;
        int i2 = 0;
        switch (i & 7) {
            case 0:
                if (this.limit - this.pos >= 10) {
                    while (i2 < 10) {
                        byte[] bArr = this.buffer;
                        int i3 = this.pos;
                        this.pos = i3 + 1;
                        if (bArr[i3] < 0) {
                            i2++;
                        }
                    }
                    throw zzic.c();
                }
                while (i2 < 10) {
                    if (zzhf() < 0) {
                        i2++;
                    }
                }
                throw zzic.c();
                return true;
            case 1:
                zzx(8);
                return true;
            case 2:
                zzx(zzha());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzx(4);
                return true;
            default:
                throw zzic.f();
        }
        do {
            zzgi = zzgi();
            if (zzgi == 0 || !zzt(zzgi)) {
                zzs(((i >>> 3) << 3) | 4);
                return true;
            }
            zzgi = zzgi();
            zzs(((i >>> 3) << 3) | 4);
            return true;
        } while (!zzt(zzgi));
        zzs(((i >>> 3) << 3) | 4);
        return true;
    }

    public final int zzu(int i) {
        if (i >= 0) {
            int zzgz = i + zzgz();
            int i2 = this.zzwn;
            if (zzgz <= i2) {
                this.zzwn = zzgz;
                zzhe();
                return i2;
            }
            throw zzic.a();
        }
        throw zzic.b();
    }

    public final void zzv(int i) {
        this.zzwn = i;
        zzhe();
    }
}

package com.google.android.gms.internal.firebase_auth;

import com.google.common.base.Ascii;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

final class zzgw extends zzgr {
    private final byte[] buffer;
    private int pos;
    private int zzwk;
    private int zzwm;
    private int zzwn;
    private final InputStream zzwo;
    private int zzwp;
    private int zzwq;
    private zzgv zzwr;

    private zzgw(InputStream inputStream, int i) {
        super();
        this.zzwn = Integer.MAX_VALUE;
        this.zzwr = null;
        zzht.a(inputStream, "input");
        this.zzwo = inputStream;
        this.buffer = new byte[i];
        this.zzwp = 0;
        this.pos = 0;
        this.zzwq = 0;
    }

    private final byte[] zzaa(int i) {
        if (i == 0) {
            return zzht.EMPTY_BYTE_ARRAY;
        }
        if (i >= 0) {
            int i2 = this.zzwq + this.pos + i;
            if (i2 - this.c <= 0) {
                int i3 = this.zzwn;
                if (i2 <= i3) {
                    int i4 = this.zzwp - this.pos;
                    int i5 = i - i4;
                    if (i5 >= 4096 && i5 > this.zzwo.available()) {
                        return null;
                    }
                    byte[] bArr = new byte[i];
                    System.arraycopy(this.buffer, this.pos, bArr, 0, i4);
                    this.zzwq += this.zzwp;
                    this.pos = 0;
                    this.zzwp = 0;
                    while (i4 < bArr.length) {
                        int read = this.zzwo.read(bArr, i4, i - i4);
                        if (read != -1) {
                            this.zzwq += read;
                            i4 += read;
                        } else {
                            throw zzic.a();
                        }
                    }
                    return bArr;
                }
                zzx((i3 - this.zzwq) - this.pos);
                throw zzic.a();
            }
            throw zzic.g();
        }
        throw zzic.b();
    }

    private final List<byte[]> zzab(int i) {
        ArrayList arrayList = new ArrayList();
        while (i > 0) {
            byte[] bArr = new byte[Math.min(i, 4096)];
            int i2 = 0;
            while (i2 < bArr.length) {
                int read = this.zzwo.read(bArr, i2, bArr.length - i2);
                if (read != -1) {
                    this.zzwq += read;
                    i2 += read;
                } else {
                    throw zzic.a();
                }
            }
            i -= bArr.length;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    private final byte[] zzb(int i, boolean z) {
        byte[] zzaa = zzaa(i);
        if (zzaa != null) {
            return zzaa;
        }
        int i2 = this.pos;
        int i3 = this.zzwp;
        int i4 = i3 - i2;
        this.zzwq += i3;
        this.pos = 0;
        this.zzwp = 0;
        List<byte[]> zzab = zzab(i - i4);
        byte[] bArr = new byte[i];
        System.arraycopy(this.buffer, i2, bArr, 0, i4);
        for (byte[] next : zzab) {
            System.arraycopy(next, 0, bArr, i4, next.length);
            i4 += next.length;
        }
        return bArr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0066, code lost:
        if (r2[r3] >= 0) goto L_0x0068;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzha() {
        /*
            r5 = this;
            int r0 = r5.pos
            int r1 = r5.zzwp
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzgw.zzha():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b0, code lost:
        if (((long) r2[r0]) >= 0) goto L_0x00b4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zzhb() {
        /*
            r11 = this;
            int r0 = r11.pos
            int r1 = r11.zzwp
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzgw.zzhb():long");
    }

    private final int zzhc() {
        int i = this.pos;
        if (this.zzwp - i < 4) {
            zzy(4);
            i = this.pos;
        }
        byte[] bArr = this.buffer;
        this.pos = i + 4;
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
    }

    private final long zzhd() {
        int i = this.pos;
        if (this.zzwp - i < 8) {
            zzy(8);
            i = this.pos;
        }
        byte[] bArr = this.buffer;
        this.pos = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    private final void zzhe() {
        this.zzwp += this.zzwk;
        int i = this.zzwq;
        int i2 = this.zzwp;
        int i3 = i + i2;
        int i4 = this.zzwn;
        if (i3 > i4) {
            this.zzwk = i3 - i4;
            this.zzwp = i2 - this.zzwk;
            return;
        }
        this.zzwk = 0;
    }

    private final byte zzhf() {
        if (this.pos == this.zzwp) {
            zzy(1);
        }
        byte[] bArr = this.buffer;
        int i = this.pos;
        this.pos = i + 1;
        return bArr[i];
    }

    private final void zzx(int i) {
        int i2 = this.zzwp;
        int i3 = this.pos;
        if (i <= i2 - i3 && i >= 0) {
            this.pos = i3 + i;
        } else if (i >= 0) {
            int i4 = this.zzwq;
            int i5 = this.pos;
            int i6 = i4 + i5 + i;
            int i7 = this.zzwn;
            if (i6 <= i7) {
                this.zzwq = i4 + i5;
                int i8 = this.zzwp - i5;
                this.zzwp = 0;
                this.pos = 0;
                while (i8 < i) {
                    try {
                        long j = (long) (i - i8);
                        long skip = this.zzwo.skip(j);
                        int i9 = (skip > 0 ? 1 : (skip == 0 ? 0 : -1));
                        if (i9 >= 0 && skip <= j) {
                            if (i9 == 0) {
                                break;
                            }
                            i8 += (int) skip;
                        } else {
                            String valueOf = String.valueOf(this.zzwo.getClass());
                            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 92);
                            sb.append(valueOf);
                            sb.append("#skip returned invalid result: ");
                            sb.append(skip);
                            sb.append("\nThe InputStream implementation is buggy.");
                            throw new IllegalStateException(sb.toString());
                        }
                    } catch (Throwable th) {
                        this.zzwq += i8;
                        zzhe();
                        throw th;
                    }
                }
                this.zzwq += i8;
                zzhe();
                if (i8 < i) {
                    int i10 = this.zzwp;
                    int i11 = i10 - this.pos;
                    this.pos = i10;
                    while (true) {
                        zzy(1);
                        int i12 = i - i11;
                        int i13 = this.zzwp;
                        if (i12 > i13) {
                            i11 += i13;
                            this.pos = i13;
                        } else {
                            this.pos = i12;
                            return;
                        }
                    }
                }
            } else {
                zzx((i7 - i4) - i5);
                throw zzic.a();
            }
        } else {
            throw zzic.b();
        }
    }

    private final void zzy(int i) {
        if (zzz(i)) {
            return;
        }
        if (i > (this.c - this.zzwq) - this.pos) {
            throw zzic.g();
        }
        throw zzic.a();
    }

    private final boolean zzz(int i) {
        while (this.pos + i > this.zzwp) {
            int i2 = this.c;
            int i3 = this.zzwq;
            int i4 = this.pos;
            if (i > (i2 - i3) - i4 || i3 + i4 + i > this.zzwn) {
                return false;
            }
            if (i4 > 0) {
                int i5 = this.zzwp;
                if (i5 > i4) {
                    byte[] bArr = this.buffer;
                    System.arraycopy(bArr, i4, bArr, 0, i5 - i4);
                }
                this.zzwq += i4;
                this.zzwp -= i4;
                this.pos = 0;
            }
            InputStream inputStream = this.zzwo;
            byte[] bArr2 = this.buffer;
            int i6 = this.zzwp;
            int read = inputStream.read(bArr2, i6, Math.min(bArr2.length - i6, (this.c - this.zzwq) - this.zzwp));
            if (read == 0 || read < -1 || read > this.buffer.length) {
                String valueOf = String.valueOf(this.zzwo.getClass());
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 91);
                sb.append(valueOf);
                sb.append("#read(byte[]) returned invalid result: ");
                sb.append(read);
                sb.append("\nThe InputStream implementation is buggy.");
                throw new IllegalStateException(sb.toString());
            } else if (read <= 0) {
                return false;
            } else {
                this.zzwp += read;
                zzhe();
                if (this.zzwp >= i) {
                    return true;
                }
            }
        }
        StringBuilder sb2 = new StringBuilder(77);
        sb2.append("refillBuffer() called when ");
        sb2.append(i);
        sb2.append(" bytes were already available in buffer");
        throw new IllegalStateException(sb2.toString());
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
            int i = this.zzwp;
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
        if (zzha > this.zzwp) {
            return new String(zzb(zzha, false), zzht.a);
        }
        zzy(zzha);
        String str2 = new String(this.buffer, this.pos, zzha, zzht.a);
        this.pos += zzha;
        return str2;
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
        byte[] bArr;
        int zzha = zzha();
        int i = this.pos;
        int i2 = 0;
        if (zzha <= this.zzwp - i && zzha > 0) {
            bArr = this.buffer;
            this.pos = i + zzha;
            i2 = i;
        } else if (zzha == 0) {
            return "";
        } else {
            if (zzha <= this.zzwp) {
                zzy(zzha);
                bArr = this.buffer;
                this.pos = zzha;
            } else {
                bArr = zzb(zzha, false);
            }
        }
        return zzkt.a(bArr, i2, zzha);
    }

    public final zzgf zzgq() {
        int zzha = zzha();
        int i = this.zzwp;
        int i2 = this.pos;
        if (zzha <= i - i2 && zzha > 0) {
            zzgf zza = zzgf.zza(this.buffer, i2, zzha);
            this.pos += zzha;
            return zza;
        } else if (zzha == 0) {
            return zzgf.zzvv;
        } else {
            byte[] zzaa = zzaa(zzha);
            if (zzaa != null) {
                return zzgf.zza(zzaa);
            }
            int i3 = this.pos;
            int i4 = this.zzwp;
            int i5 = i4 - i3;
            this.zzwq += i4;
            this.pos = 0;
            this.zzwp = 0;
            List<byte[]> zzab = zzab(zzha - i5);
            byte[] bArr = new byte[zzha];
            System.arraycopy(this.buffer, i3, bArr, 0, i5);
            for (byte[] next : zzab) {
                System.arraycopy(next, 0, bArr, i5, next.length);
                i5 += next.length;
            }
            return zzgf.a(bArr);
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
        return this.pos == this.zzwp && !zzz(1);
    }

    public final int zzgz() {
        return this.zzwq + this.pos;
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
                if (this.zzwp - this.pos >= 10) {
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
            int i2 = i + this.zzwq + this.pos;
            int i3 = this.zzwn;
            if (i2 <= i3) {
                this.zzwn = i2;
                zzhe();
                return i3;
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

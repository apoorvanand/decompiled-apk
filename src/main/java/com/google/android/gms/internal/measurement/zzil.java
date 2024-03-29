package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;
import java.io.IOException;

public final class zzil {
    private final byte[] buffer;
    private int zzadp;
    private int zzadq = 64;
    private int zzadr = 67108864;
    private int zzady;
    private int zzaea;
    private int zzaeb = Integer.MAX_VALUE;
    private final int zzaog;
    private final int zzaoh;
    private int zzaoi;
    private int zzaoj;
    private zzeb zzaok;

    private zzil(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzaog = 0;
        int i3 = i2 + 0;
        this.zzaoi = i3;
        this.zzaoh = i3;
        this.zzaoj = 0;
    }

    private final void zzat(int i) {
        if (this.zzaea != i) {
            throw new zzit("Protocol message end-group tag did not match expected tag.");
        }
    }

    private final void zzay(int i) {
        if (i >= 0) {
            int i2 = this.zzaoj;
            int i3 = i2 + i;
            int i4 = this.zzaeb;
            if (i3 > i4) {
                zzay(i4 - i2);
                throw zzit.a();
            } else if (i <= this.zzaoi - i2) {
                this.zzaoj = i2 + i;
            } else {
                throw zzit.a();
            }
        } else {
            throw zzit.b();
        }
    }

    public static zzil zzj(byte[] bArr, int i, int i2) {
        return new zzil(bArr, 0, i2);
    }

    private final void zzte() {
        this.zzaoi += this.zzady;
        int i = this.zzaoi;
        int i2 = this.zzaeb;
        if (i > i2) {
            this.zzady = i - i2;
            this.zzaoi = i - this.zzady;
            return;
        }
        this.zzady = 0;
    }

    private final byte zztf() {
        int i = this.zzaoj;
        if (i != this.zzaoi) {
            byte[] bArr = this.buffer;
            this.zzaoj = i + 1;
            return bArr[i];
        }
        throw zzit.a();
    }

    /* access modifiers changed from: package-private */
    public final void a(int i, int i2) {
        int i3 = this.zzaoj;
        int i4 = this.zzaog;
        if (i > i3 - i4) {
            StringBuilder sb = new StringBuilder(50);
            sb.append("Position ");
            sb.append(i);
            sb.append(" is beyond current ");
            sb.append(i3 - i4);
            throw new IllegalArgumentException(sb.toString());
        } else if (i >= 0) {
            this.zzaoj = i4 + i;
            this.zzaea = i2;
        } else {
            StringBuilder sb2 = new StringBuilder(24);
            sb2.append("Bad position ");
            sb2.append(i);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public final int getPosition() {
        return this.zzaoj - this.zzaog;
    }

    public final String readString() {
        int zzta = zzta();
        if (zzta >= 0) {
            int i = this.zzaoi;
            int i2 = this.zzaoj;
            if (zzta <= i - i2) {
                String str = new String(this.buffer, i2, zzta, zziu.a);
                this.zzaoj += zzta;
                return str;
            }
            throw zzit.a();
        }
        throw zzit.b();
    }

    public final <T extends zzey<T, ?>> T zza(zzgr<T> zzgr) {
        try {
            if (this.zzaok == null) {
                this.zzaok = zzeb.zzd(this.buffer, this.zzaog, this.zzaoh);
            }
            int zzsx = this.zzaok.zzsx();
            int i = this.zzaoj - this.zzaog;
            if (zzsx <= i) {
                this.zzaok.zzay(i - zzsx);
                this.zzaok.zzav(this.zzadq - this.zzadp);
                T t = (zzey) this.zzaok.zza(zzgr, zzel.zztq());
                zzau(this.zzaea);
                return t;
            }
            throw new IOException(String.format("CodedInputStream read ahead of CodedInputByteBufferNano: %s > %s", new Object[]{Integer.valueOf(zzsx), Integer.valueOf(i)}));
        } catch (zzfi e) {
            throw new zzit("", e);
        }
    }

    public final void zza(zziw zziw) {
        int zzta = zzta();
        if (this.zzadp >= this.zzadq) {
            throw new zzit("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        } else if (zzta >= 0) {
            int i = zzta + this.zzaoj;
            int i2 = this.zzaeb;
            if (i <= i2) {
                this.zzaeb = i;
                zzte();
                this.zzadp++;
                zziw.zza(this);
                zzat(0);
                this.zzadp--;
                this.zzaeb = i2;
                zzte();
                return;
            }
            throw zzit.a();
        } else {
            throw zzit.b();
        }
    }

    public final boolean zzau(int i) {
        int zzsg;
        switch (i & 7) {
            case 0:
                zzta();
                return true;
            case 1:
                zztf();
                zztf();
                zztf();
                zztf();
                zztf();
                zztf();
                zztf();
                zztf();
                return true;
            case 2:
                zzay(zzta());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zztf();
                zztf();
                zztf();
                zztf();
                return true;
            default:
                throw new zzit("Protocol message tag had invalid wire type.");
        }
        do {
            zzsg = zzsg();
            if (zzsg == 0 || !zzau(zzsg)) {
                zzat(((i >>> 3) << 3) | 4);
                return true;
            }
            zzsg = zzsg();
            zzat(((i >>> 3) << 3) | 4);
            return true;
        } while (!zzau(zzsg));
        zzat(((i >>> 3) << 3) | 4);
        return true;
    }

    public final int zzsg() {
        if (this.zzaoj == this.zzaoi) {
            this.zzaea = 0;
            return 0;
        }
        this.zzaea = zzta();
        int i = this.zzaea;
        if (i != 0) {
            return i;
        }
        throw new zzit("Protocol message contained an invalid tag (zero).");
    }

    public final boolean zzsm() {
        return zzta() != 0;
    }

    public final byte[] zzt(int i, int i2) {
        if (i2 == 0) {
            return zzix.zzaph;
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(this.buffer, this.zzaog + i, bArr, 0, i2);
        return bArr;
    }

    public final int zzta() {
        int i;
        byte zztf = zztf();
        if (zztf >= 0) {
            return zztf;
        }
        byte b = zztf & Byte.MAX_VALUE;
        byte zztf2 = zztf();
        if (zztf2 >= 0) {
            i = zztf2 << 7;
        } else {
            b |= (zztf2 & Byte.MAX_VALUE) << 7;
            byte zztf3 = zztf();
            if (zztf3 >= 0) {
                i = zztf3 << Ascii.SO;
            } else {
                b |= (zztf3 & Byte.MAX_VALUE) << Ascii.SO;
                byte zztf4 = zztf();
                if (zztf4 >= 0) {
                    i = zztf4 << Ascii.NAK;
                } else {
                    byte b2 = b | ((zztf4 & Byte.MAX_VALUE) << Ascii.NAK);
                    byte zztf5 = zztf();
                    byte b3 = b2 | (zztf5 << Ascii.FS);
                    if (zztf5 >= 0) {
                        return b3;
                    }
                    for (int i2 = 0; i2 < 5; i2++) {
                        if (zztf() >= 0) {
                            return b3;
                        }
                    }
                    throw zzit.c();
                }
            }
        }
        return b | i;
    }

    public final long zztb() {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zztf = zztf();
            j |= ((long) (zztf & Byte.MAX_VALUE)) << i;
            if ((zztf & 128) == 0) {
                return j;
            }
        }
        throw zzit.c();
    }
}

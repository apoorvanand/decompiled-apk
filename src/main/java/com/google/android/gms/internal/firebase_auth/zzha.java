package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzha extends zzgg {
    private static final Logger logger = Logger.getLogger(zzha.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzww = zzkq.a();
    zzhc a;

    public static class zza extends IOException {
        zza() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        zza(java.lang.String r3, java.lang.Throwable r4) {
            /*
                r2 = this;
                java.lang.String r0 = "CodedOutputStream was writing to a flat byte array and ran out of space.: "
                java.lang.String r0 = java.lang.String.valueOf(r0)
                java.lang.String r3 = java.lang.String.valueOf(r3)
                int r1 = r3.length()
                if (r1 == 0) goto L_0x0015
                java.lang.String r3 = r0.concat(r3)
                goto L_0x001a
            L_0x0015:
                java.lang.String r3 = new java.lang.String
                r3.<init>(r0)
            L_0x001a:
                r2.<init>(r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_auth.zzha.zza.<init>(java.lang.String, java.lang.Throwable):void");
        }

        zza(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }
    }

    static class zzb extends zzha {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zzb(byte[] bArr, int i, int i2) {
            super();
            if (bArr != null) {
                int i3 = i2 + 0;
                if ((i2 | 0 | (bArr.length - i3)) >= 0) {
                    this.buffer = bArr;
                    this.offset = 0;
                    this.position = 0;
                    this.limit = i3;
                    return;
                }
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), 0, Integer.valueOf(i2)}));
            }
            throw new NullPointerException("buffer");
        }

        private final void write(byte[] bArr, int i, int i2) {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
            }
        }

        /* access modifiers changed from: package-private */
        public final void a(int i, zzjc zzjc, zzjs zzjs) {
            zze(i, 2);
            zzfx zzfx = (zzfx) zzjc;
            int a = zzfx.a();
            if (a == -1) {
                a = zzjs.zzq(zzfx);
                zzfx.a(a);
            }
            zzah(a);
            zzjs.zza(zzjc, this.a);
        }

        public final void zza(int i, long j) {
            zze(i, 0);
            zzb(j);
        }

        public final void zza(int i, zzgf zzgf) {
            zze(i, 2);
            zza(zzgf);
        }

        public final void zza(int i, zzjc zzjc) {
            zze(1, 3);
            zzg(2, i);
            zze(3, 2);
            zzc(zzjc);
            zze(1, 4);
        }

        public final void zza(int i, String str) {
            zze(i, 2);
            zzdi(str);
        }

        public final void zza(zzgf zzgf) {
            zzah(zzgf.size());
            zzgf.a((zzgg) this);
        }

        public final void zzag(int i) {
            if (i >= 0) {
                zzah(i);
            } else {
                zzb((long) i);
            }
        }

        public final void zzah(int i) {
            if (!zzha.zzww || zzge.a() || zzhi() < 5) {
                while ((i & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr[i2] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    bArr2[i3] = (byte) i;
                } catch (IndexOutOfBoundsException e) {
                    throw new zza(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
                }
            } else {
                if ((i & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i4 = this.position;
                    this.position = i4 + 1;
                    zzkq.a(bArr3, (long) i4, (byte) (i | 128));
                    i >>>= 7;
                    if ((i & -128) != 0) {
                        byte[] bArr4 = this.buffer;
                        int i5 = this.position;
                        this.position = i5 + 1;
                        zzkq.a(bArr4, (long) i5, (byte) (i | 128));
                        i >>>= 7;
                        if ((i & -128) != 0) {
                            byte[] bArr5 = this.buffer;
                            int i6 = this.position;
                            this.position = i6 + 1;
                            zzkq.a(bArr5, (long) i6, (byte) (i | 128));
                            i >>>= 7;
                            if ((i & -128) != 0) {
                                byte[] bArr6 = this.buffer;
                                int i7 = this.position;
                                this.position = i7 + 1;
                                zzkq.a(bArr6, (long) i7, (byte) (i | 128));
                                i >>>= 7;
                            }
                        }
                    }
                }
                byte[] bArr7 = this.buffer;
                int i8 = this.position;
                this.position = i8 + 1;
                zzkq.a(bArr7, (long) i8, (byte) i);
            }
        }

        public final void zzaj(int i) {
            try {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) i;
                byte[] bArr2 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr2[i3] = (byte) (i >> 8);
                byte[] bArr3 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr3[i4] = (byte) (i >> 16);
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr4[i5] = (byte) (i >>> 24);
            } catch (IndexOutOfBoundsException e) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }

        public final void zzb(int i, zzgf zzgf) {
            zze(1, 3);
            zzg(2, i);
            zza(3, zzgf);
            zze(1, 4);
        }

        public final void zzb(long j) {
            if (!zzha.zzww || zzhi() < 10) {
                while ((j & -128) != 0) {
                    byte[] bArr = this.buffer;
                    int i = this.position;
                    this.position = i + 1;
                    bArr[i] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                }
                try {
                    byte[] bArr2 = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr2[i2] = (byte) ((int) j);
                } catch (IndexOutOfBoundsException e) {
                    throw new zza(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
                }
            } else {
                while ((j & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    zzkq.a(bArr3, (long) i3, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                zzkq.a(bArr4, (long) i4, (byte) ((int) j));
            }
        }

        public final void zzb(byte[] bArr, int i, int i2) {
            write(bArr, i, i2);
        }

        public final void zzc(byte b) {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }

        public final void zzc(int i, long j) {
            zze(i, 1);
            zzd(j);
        }

        public final void zzc(int i, boolean z) {
            zze(i, 0);
            zzc(z ? (byte) 1 : 0);
        }

        public final void zzc(zzjc zzjc) {
            zzah(zzjc.zzik());
            zzjc.zzb(this);
        }

        public final void zzd(long j) {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) ((int) j);
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr2[i2] = (byte) ((int) (j >> 8));
                byte[] bArr3 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr3[i3] = (byte) ((int) (j >> 16));
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr4[i4] = (byte) ((int) (j >> 24));
                byte[] bArr5 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr5[i5] = (byte) ((int) (j >> 32));
                byte[] bArr6 = this.buffer;
                int i6 = this.position;
                this.position = i6 + 1;
                bArr6[i6] = (byte) ((int) (j >> 40));
                byte[] bArr7 = this.buffer;
                int i7 = this.position;
                this.position = i7 + 1;
                bArr7[i7] = (byte) ((int) (j >> 48));
                byte[] bArr8 = this.buffer;
                int i8 = this.position;
                this.position = i8 + 1;
                bArr8[i8] = (byte) ((int) (j >> 56));
            } catch (IndexOutOfBoundsException e) {
                throw new zza(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }

        public final void zzd(byte[] bArr, int i, int i2) {
            zzah(i2);
            write(bArr, 0, i2);
        }

        public final void zzdi(String str) {
            int i = this.position;
            try {
                int zzam = zzam(str.length() * 3);
                int zzam2 = zzam(str.length());
                if (zzam2 == zzam) {
                    this.position = i + zzam2;
                    int a = zzkt.a(str, this.buffer, this.position, zzhi());
                    this.position = i;
                    zzah((a - i) - zzam2);
                    this.position = a;
                    return;
                }
                zzah(zzkt.a((CharSequence) str));
                this.position = zzkt.a(str, this.buffer, this.position, zzhi());
            } catch (zzkw e) {
                this.position = i;
                a(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zza(e2);
            }
        }

        public final void zze(int i, int i2) {
            zzah((i << 3) | i2);
        }

        public final void zzf(int i, int i2) {
            zze(i, 0);
            zzag(i2);
        }

        public final void zzg(int i, int i2) {
            zze(i, 0);
            zzah(i2);
        }

        public final int zzhi() {
            return this.limit - this.position;
        }

        public final void zzi(int i, int i2) {
            zze(i, 5);
            zzaj(i2);
        }
    }

    private zzha() {
    }

    static int a(zzjc zzjc, zzjs zzjs) {
        zzfx zzfx = (zzfx) zzjc;
        int a2 = zzfx.a();
        if (a2 == -1) {
            a2 = zzjs.zzq(zzfx);
            zzfx.a(a2);
        }
        return zzam(a2) + a2;
    }

    static int b(int i, zzjc zzjc, zzjs zzjs) {
        return zzak(i) + a(zzjc, zzjs);
    }

    @Deprecated
    static int c(int i, zzjc zzjc, zzjs zzjs) {
        int zzak = zzak(i) << 1;
        zzfx zzfx = (zzfx) zzjc;
        int a2 = zzfx.a();
        if (a2 == -1) {
            a2 = zzjs.zzq(zzfx);
            zzfx.a(a2);
        }
        return zzak + a2;
    }

    public static int zza(int i, zzih zzih) {
        int zzak = zzak(i);
        int zzik = zzih.zzik();
        return zzak + zzam(zzik) + zzik;
    }

    public static int zza(zzih zzih) {
        int zzik = zzih.zzik();
        return zzam(zzik) + zzik;
    }

    public static int zzak(int i) {
        return zzam(i << 3);
    }

    public static int zzal(int i) {
        if (i >= 0) {
            return zzam(i);
        }
        return 10;
    }

    public static int zzam(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (i & -268435456) == 0 ? 4 : 5;
    }

    public static int zzan(int i) {
        return zzam(zzar(i));
    }

    public static int zzao(int i) {
        return 4;
    }

    public static int zzap(int i) {
        return 4;
    }

    public static int zzaq(int i) {
        return zzal(i);
    }

    private static int zzar(int i) {
        return (i >> 31) ^ (i << 1);
    }

    @Deprecated
    public static int zzas(int i) {
        return zzam(i);
    }

    public static int zzb(double d) {
        return 8;
    }

    public static int zzb(float f) {
        return 4;
    }

    public static int zzb(int i, double d) {
        return zzak(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzak(i) + 4;
    }

    public static int zzb(int i, zzih zzih) {
        return (zzak(1) << 1) + zzk(2, i) + zza(3, zzih);
    }

    public static int zzb(int i, zzjc zzjc) {
        return (zzak(1) << 1) + zzk(2, i) + zzak(3) + zzd(zzjc);
    }

    public static int zzb(int i, String str) {
        return zzak(i) + zzdj(str);
    }

    public static int zzb(zzgf zzgf) {
        int size = zzgf.size();
        return zzam(size) + size;
    }

    public static int zzc(int i, zzgf zzgf) {
        int zzak = zzak(i);
        int size = zzgf.size();
        return zzak + zzam(size) + size;
    }

    public static zzha zzc(byte[] bArr) {
        return new zzb(bArr, 0, bArr.length);
    }

    public static int zzd(int i, long j) {
        return zzak(i) + zzf(j);
    }

    public static int zzd(int i, zzgf zzgf) {
        return (zzak(1) << 1) + zzk(2, i) + zzc(3, zzgf);
    }

    public static int zzd(int i, boolean z) {
        return zzak(i) + 1;
    }

    public static int zzd(zzjc zzjc) {
        int zzik = zzjc.zzik();
        return zzam(zzik) + zzik;
    }

    public static int zzd(byte[] bArr) {
        int length = bArr.length;
        return zzam(length) + length;
    }

    public static int zzdj(String str) {
        int i;
        try {
            i = zzkt.a((CharSequence) str);
        } catch (zzkw unused) {
            i = str.getBytes(zzht.a).length;
        }
        return zzam(i) + i;
    }

    public static int zze(int i, long j) {
        return zzak(i) + zzf(j);
    }

    public static int zze(long j) {
        return zzf(j);
    }

    @Deprecated
    public static int zze(zzjc zzjc) {
        return zzjc.zzik();
    }

    public static int zzf(int i, long j) {
        return zzak(i) + zzf(zzj(j));
    }

    public static int zzf(long j) {
        int i;
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if ((-34359738368L & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if ((-2097152 & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & -16384) != 0 ? i + 1 : i;
    }

    public static int zzg(int i, long j) {
        return zzak(i) + 8;
    }

    public static int zzg(long j) {
        return zzf(zzj(j));
    }

    public static int zzh(int i, long j) {
        return zzak(i) + 8;
    }

    public static int zzh(long j) {
        return 8;
    }

    public static int zzi(long j) {
        return 8;
    }

    public static int zzj(int i, int i2) {
        return zzak(i) + zzal(i2);
    }

    private static long zzj(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int zzk(int i, int i2) {
        return zzak(i) + zzam(i2);
    }

    public static int zzl(int i, int i2) {
        return zzak(i) + zzam(zzar(i2));
    }

    public static int zzm(int i, int i2) {
        return zzak(i) + 4;
    }

    public static int zzn(int i, int i2) {
        return zzak(i) + 4;
    }

    public static int zzo(int i, int i2) {
        return zzak(i) + zzal(i2);
    }

    public static int zzu(boolean z) {
        return 1;
    }

    /* access modifiers changed from: package-private */
    public abstract void a(int i, zzjc zzjc, zzjs zzjs);

    /* access modifiers changed from: package-private */
    public final void a(String str, zzkw zzkw) {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzkw);
        byte[] bytes = str.getBytes(zzht.a);
        try {
            zzah(bytes.length);
            zzb(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zza(e);
        } catch (zza e2) {
            throw e2;
        }
    }

    public final void zza(double d) {
        zzd(Double.doubleToRawLongBits(d));
    }

    public final void zza(float f) {
        zzaj(Float.floatToRawIntBits(f));
    }

    public final void zza(int i, double d) {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zza(int i, float f) {
        zzi(i, Float.floatToRawIntBits(f));
    }

    public abstract void zza(int i, long j);

    public abstract void zza(int i, zzgf zzgf);

    public abstract void zza(int i, zzjc zzjc);

    public abstract void zza(int i, String str);

    public abstract void zza(zzgf zzgf);

    public abstract void zzag(int i);

    public abstract void zzah(int i);

    public final void zzai(int i) {
        zzah(zzar(i));
    }

    public abstract void zzaj(int i);

    public final void zzb(int i, long j) {
        zza(i, zzj(j));
    }

    public abstract void zzb(int i, zzgf zzgf);

    public abstract void zzb(long j);

    public abstract void zzc(byte b);

    public abstract void zzc(int i, long j);

    public abstract void zzc(int i, boolean z);

    public final void zzc(long j) {
        zzb(zzj(j));
    }

    public abstract void zzc(zzjc zzjc);

    public abstract void zzd(long j);

    /* access modifiers changed from: package-private */
    public abstract void zzd(byte[] bArr, int i, int i2);

    public abstract void zzdi(String str);

    public abstract void zze(int i, int i2);

    public abstract void zzf(int i, int i2);

    public abstract void zzg(int i, int i2);

    public final void zzh(int i, int i2) {
        zzg(i, zzar(i2));
    }

    public abstract int zzhi();

    public final void zzhj() {
        if (zzhi() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public abstract void zzi(int i, int i2);

    public final void zzt(boolean z) {
        zzc(z ? (byte) 1 : 0);
    }
}

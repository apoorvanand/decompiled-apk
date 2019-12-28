package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzsj extends zzrt {
    private static final Logger logger = Logger.getLogger(zzsj.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzbiu = zzwa.a();
    zzsl a;

    static class zza extends zzsj {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zza(byte[] bArr, int i, int i2) {
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
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
            }
        }

        /* access modifiers changed from: package-private */
        public final void a(int i, zzum zzum, zzvc zzvc) {
            zzd(i, 2);
            zzrl zzrl = (zzrl) zzum;
            int b = zzrl.b();
            if (b == -1) {
                b = zzvc.zzaa(zzrl);
                zzrl.a(b);
            }
            zzci(b);
            zzvc.zza(zzum, this.a);
        }

        public final void zza(int i, long j) {
            zzd(i, 0);
            zzn(j);
        }

        public final void zza(int i, zzru zzru) {
            zzd(i, 2);
            zza(zzru);
        }

        public final void zza(int i, zzum zzum) {
            zzd(1, 3);
            zzf(2, i);
            zzd(3, 2);
            zzb(zzum);
            zzd(1, 4);
        }

        public final void zza(zzru zzru) {
            zzci(zzru.size());
            zzru.a((zzrt) this);
        }

        public final void zzb(int i, zzru zzru) {
            zzd(1, 3);
            zzf(2, i);
            zza(3, zzru);
            zzd(1, 4);
        }

        public final void zzb(int i, String str) {
            zzd(i, 2);
            zzcj(str);
        }

        public final void zzb(int i, boolean z) {
            zzd(i, 0);
            zzd(z ? (byte) 1 : 0);
        }

        public final void zzb(zzum zzum) {
            zzci(zzum.zzpe());
            zzum.zzb(this);
        }

        public final void zzb(byte[] bArr, int i, int i2) {
            write(bArr, i, i2);
        }

        public final void zzc(int i, long j) {
            zzd(i, 1);
            zzp(j);
        }

        public final void zzch(int i) {
            if (i >= 0) {
                zzci(i);
            } else {
                zzn((long) i);
            }
        }

        public final void zzci(int i) {
            if (!zzsj.zzbiu || zzos() < 10) {
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
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
                }
            } else {
                while ((i & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i4 = this.position;
                    this.position = i4 + 1;
                    zzwa.a(bArr3, (long) i4, (byte) ((i & 127) | 128));
                    i >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                zzwa.a(bArr4, (long) i5, (byte) i);
            }
        }

        public final void zzcj(String str) {
            int i = this.position;
            try {
                int zzcn = zzcn(str.length() * 3);
                int zzcn2 = zzcn(str.length());
                if (zzcn2 == zzcn) {
                    this.position = i + zzcn2;
                    int a = zzwc.a(str, this.buffer, this.position, zzos());
                    this.position = i;
                    zzci((a - i) - zzcn2);
                    this.position = a;
                    return;
                }
                zzci(zzwc.a((CharSequence) str));
                this.position = zzwc.a(str, this.buffer, this.position, zzos());
            } catch (zzwg e) {
                this.position = i;
                a(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb(e2);
            }
        }

        public final void zzck(int i) {
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
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }

        public final void zzd(byte b) {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }

        public final void zzd(int i, int i2) {
            zzci((i << 3) | i2);
        }

        public final void zze(int i, int i2) {
            zzd(i, 0);
            zzch(i2);
        }

        public final void zze(byte[] bArr, int i, int i2) {
            zzci(i2);
            write(bArr, 0, i2);
        }

        public final void zzf(int i, int i2) {
            zzd(i, 0);
            zzci(i2);
        }

        public final void zzh(int i, int i2) {
            zzd(i, 5);
            zzck(i2);
        }

        public final void zzn(long j) {
            if (!zzsj.zzbiu || zzos() < 10) {
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
                    throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
                }
            } else {
                while ((j & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    zzwa.a(bArr3, (long) i3, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                zzwa.a(bArr4, (long) i4, (byte) ((int) j));
            }
        }

        public final int zzos() {
            return this.limit - this.position;
        }

        public final void zzp(long j) {
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
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }
    }

    public static class zzb extends IOException {
        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        zzb(java.lang.String r3, java.lang.Throwable r4) {
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzsj.zzb.<init>(java.lang.String, java.lang.Throwable):void");
        }

        zzb(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }
    }

    private zzsj() {
    }

    static int a(int i) {
        return zzcn(i) + i;
    }

    static int a(zzum zzum, zzvc zzvc) {
        zzrl zzrl = (zzrl) zzum;
        int b = zzrl.b();
        if (b == -1) {
            b = zzvc.zzaa(zzrl);
            zzrl.a(b);
        }
        return zzcn(b) + b;
    }

    static int b(int i, zzum zzum, zzvc zzvc) {
        return zzcl(i) + a(zzum, zzvc);
    }

    @Deprecated
    static int c(int i, zzum zzum, zzvc zzvc) {
        int zzcl = zzcl(i) << 1;
        zzrl zzrl = (zzrl) zzum;
        int b = zzrl.b();
        if (b == -1) {
            b = zzvc.zzaa(zzrl);
            zzrl.a(b);
        }
        return zzcl + b;
    }

    public static int zza(int i, zztt zztt) {
        int zzcl = zzcl(i);
        int zzpe = zztt.zzpe();
        return zzcl + zzcn(zzpe) + zzpe;
    }

    public static int zza(zztt zztt) {
        int zzpe = zztt.zzpe();
        return zzcn(zzpe) + zzpe;
    }

    public static int zzah(boolean z) {
        return 1;
    }

    public static int zzb(int i, double d) {
        return zzcl(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzcl(i) + 4;
    }

    public static int zzb(int i, zztt zztt) {
        return (zzcl(1) << 1) + zzj(2, i) + zza(3, zztt);
    }

    public static int zzb(int i, zzum zzum) {
        return (zzcl(1) << 1) + zzj(2, i) + zzcl(3) + zzc(zzum);
    }

    public static int zzb(zzru zzru) {
        int size = zzru.size();
        return zzcn(size) + size;
    }

    public static int zzc(int i, zzru zzru) {
        int zzcl = zzcl(i);
        int size = zzru.size();
        return zzcl + zzcn(size) + size;
    }

    public static int zzc(int i, String str) {
        return zzcl(i) + zzck(str);
    }

    public static int zzc(int i, boolean z) {
        return zzcl(i) + 1;
    }

    public static int zzc(zzum zzum) {
        int zzpe = zzum.zzpe();
        return zzcn(zzpe) + zzpe;
    }

    public static int zzck(String str) {
        int i;
        try {
            i = zzwc.a((CharSequence) str);
        } catch (zzwg unused) {
            i = str.getBytes(zzte.a).length;
        }
        return zzcn(i) + i;
    }

    public static int zzcl(int i) {
        return zzcn(i << 3);
    }

    public static int zzcm(int i) {
        if (i >= 0) {
            return zzcn(i);
        }
        return 10;
    }

    public static int zzcn(int i) {
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

    public static int zzco(int i) {
        return zzcn(zzct(i));
    }

    public static int zzcp(int i) {
        return 4;
    }

    public static int zzcq(int i) {
        return 4;
    }

    public static int zzcr(int i) {
        return zzcm(i);
    }

    private static int zzct(int i) {
        return (i >> 31) ^ (i << 1);
    }

    @Deprecated
    public static int zzcu(int i) {
        return zzcn(i);
    }

    public static int zzd(double d) {
        return 8;
    }

    public static int zzd(int i, long j) {
        return zzcl(i) + zzr(j);
    }

    public static int zzd(int i, zzru zzru) {
        return (zzcl(1) << 1) + zzj(2, i) + zzc(3, zzru);
    }

    @Deprecated
    public static int zzd(zzum zzum) {
        return zzum.zzpe();
    }

    public static int zze(int i, long j) {
        return zzcl(i) + zzr(j);
    }

    public static int zzf(int i, long j) {
        return zzcl(i) + zzr(zzv(j));
    }

    public static int zzg(int i, long j) {
        return zzcl(i) + 8;
    }

    public static int zzh(int i, long j) {
        return zzcl(i) + 8;
    }

    public static zzsj zzh(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zzi(int i, int i2) {
        return zzcl(i) + zzcm(i2);
    }

    public static int zzi(byte[] bArr) {
        int length = bArr.length;
        return zzcn(length) + length;
    }

    public static int zzj(int i, int i2) {
        return zzcl(i) + zzcn(i2);
    }

    public static int zzk(int i, int i2) {
        return zzcl(i) + zzcn(zzct(i2));
    }

    public static int zzl(int i, int i2) {
        return zzcl(i) + 4;
    }

    public static int zzm(int i, int i2) {
        return zzcl(i) + 4;
    }

    public static int zzn(int i, int i2) {
        return zzcl(i) + zzcm(i2);
    }

    public static int zzq(long j) {
        return zzr(j);
    }

    public static int zzr(long j) {
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

    public static int zzs(float f) {
        return 4;
    }

    public static int zzs(long j) {
        return zzr(zzv(j));
    }

    public static int zzt(long j) {
        return 8;
    }

    public static int zzu(long j) {
        return 8;
    }

    private static long zzv(long j) {
        return (j >> 63) ^ (j << 1);
    }

    /* access modifiers changed from: package-private */
    public abstract void a(int i, zzum zzum, zzvc zzvc);

    /* access modifiers changed from: package-private */
    public final void a(String str, zzwg zzwg) {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzwg);
        byte[] bytes = str.getBytes(zzte.a);
        try {
            zzci(bytes.length);
            zzb(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzb(e);
        } catch (zzb e2) {
            throw e2;
        }
    }

    public final void zza(int i, double d) {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zza(int i, float f) {
        zzh(i, Float.floatToRawIntBits(f));
    }

    public abstract void zza(int i, long j);

    public abstract void zza(int i, zzru zzru);

    public abstract void zza(int i, zzum zzum);

    public abstract void zza(zzru zzru);

    public final void zzag(boolean z) {
        zzd(z ? (byte) 1 : 0);
    }

    public final void zzb(int i, long j) {
        zza(i, zzv(j));
    }

    public abstract void zzb(int i, zzru zzru);

    public abstract void zzb(int i, String str);

    public abstract void zzb(int i, boolean z);

    public abstract void zzb(zzum zzum);

    public final void zzc(double d) {
        zzp(Double.doubleToRawLongBits(d));
    }

    public abstract void zzc(int i, long j);

    public abstract void zzch(int i);

    public abstract void zzci(int i);

    public final void zzcj(int i) {
        zzci(zzct(i));
    }

    public abstract void zzcj(String str);

    public abstract void zzck(int i);

    public abstract void zzd(byte b);

    public abstract void zzd(int i, int i2);

    public abstract void zze(int i, int i2);

    /* access modifiers changed from: package-private */
    public abstract void zze(byte[] bArr, int i, int i2);

    public abstract void zzf(int i, int i2);

    public final void zzg(int i, int i2) {
        zzf(i, zzct(i2));
    }

    public abstract void zzh(int i, int i2);

    public abstract void zzn(long j);

    public final void zzo(long j) {
        zzn(zzv(j));
    }

    public abstract int zzos();

    public final void zzot() {
        if (zzos() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public abstract void zzp(long j);

    public final void zzr(float f) {
        zzck(Float.floatToRawIntBits(f));
    }
}

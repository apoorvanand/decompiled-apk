package com.google.android.gms.internal.vision;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzfe extends zzen {
    private static final Logger logger = Logger.getLogger(zzfe.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzsr = zziu.a();
    zzfg a;

    static class zza extends zzfe {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zza(byte[] bArr, int i, int i2) {
            super();
            if (bArr != null) {
                int i3 = i + i2;
                if ((i | i2 | (bArr.length - i3)) >= 0) {
                    this.buffer = bArr;
                    this.offset = i;
                    this.position = i;
                    this.limit = i3;
                    return;
                }
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
            }
            throw new NullPointerException("buffer");
        }

        /* access modifiers changed from: package-private */
        public final void a(int i, zzhf zzhf, zzhw zzhw) {
            zzd(i, 2);
            zzec zzec = (zzec) zzhf;
            int b = zzec.b();
            if (b == -1) {
                b = zzhw.zzp(zzec);
                zzec.a(b);
            }
            zzas(b);
            zzhw.zza(zzhf, this.a);
        }

        /* access modifiers changed from: package-private */
        public final void a(zzhf zzhf, zzhw zzhw) {
            zzec zzec = (zzec) zzhf;
            int b = zzec.b();
            if (b == -1) {
                b = zzhw.zzp(zzec);
                zzec.a(b);
            }
            zzas(b);
            zzhw.zza(zzhf, this.a);
        }

        public void flush() {
        }

        public final void write(byte[] bArr, int i, int i2) {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
            }
        }

        public final void zza(int i, long j) {
            zzd(i, 0);
            zze(j);
        }

        public final void zza(int i, zzeo zzeo) {
            zzd(i, 2);
            zza(zzeo);
        }

        public final void zza(int i, zzhf zzhf) {
            zzd(i, 2);
            zzb(zzhf);
        }

        public final void zza(int i, String str) {
            zzd(i, 2);
            zzm(str);
        }

        public final void zza(zzeo zzeo) {
            zzas(zzeo.size());
            zzeo.a((zzen) this);
        }

        public final void zza(byte[] bArr, int i, int i2) {
            write(bArr, i, i2);
        }

        public final void zzar(int i) {
            if (i >= 0) {
                zzas(i);
            } else {
                zze((long) i);
            }
        }

        public final void zzas(int i) {
            if (!zzfe.zzsr || zzdz() < 10) {
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
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
                }
            } else {
                while ((i & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i4 = this.position;
                    this.position = i4 + 1;
                    zziu.a(bArr3, (long) i4, (byte) ((i & 127) | 128));
                    i >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                zziu.a(bArr4, (long) i5, (byte) i);
            }
        }

        public final void zzau(int i) {
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
                bArr4[i5] = i >> Ascii.CAN;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }

        public final void zzb(int i, zzeo zzeo) {
            zzd(1, 3);
            zzf(2, i);
            zza(3, zzeo);
            zzd(1, 4);
        }

        public final void zzb(int i, zzhf zzhf) {
            zzd(1, 3);
            zzf(2, i);
            zza(3, zzhf);
            zzd(1, 4);
        }

        public final void zzb(int i, boolean z) {
            zzd(i, 0);
            zzc(z ? (byte) 1 : 0);
        }

        public final void zzb(zzhf zzhf) {
            zzas(zzhf.zzeq());
            zzhf.zzb(this);
        }

        public final void zzc(byte b) {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }

        public final void zzc(int i, long j) {
            zzd(i, 1);
            zzg(j);
        }

        public final void zzd(int i, int i2) {
            zzas((i << 3) | i2);
        }

        public final int zzdz() {
            return this.limit - this.position;
        }

        public final void zze(int i, int i2) {
            zzd(i, 0);
            zzar(i2);
        }

        public final void zze(long j) {
            if (!zzfe.zzsr || zzdz() < 10) {
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
                    throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
                }
            } else {
                while ((j & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    zziu.a(bArr3, (long) i3, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                zziu.a(bArr4, (long) i4, (byte) ((int) j));
            }
        }

        public final int zzec() {
            return this.position - this.offset;
        }

        public final void zzf(int i, int i2) {
            zzd(i, 0);
            zzas(i2);
        }

        public final void zzf(byte[] bArr, int i, int i2) {
            zzas(i2);
            write(bArr, 0, i2);
        }

        public final void zzg(long j) {
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
                throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }

        public final void zzh(int i, int i2) {
            zzd(i, 5);
            zzau(i2);
        }

        public final void zzm(String str) {
            int i = this.position;
            try {
                int zzax = zzax(str.length() * 3);
                int zzax2 = zzax(str.length());
                if (zzax2 == zzax) {
                    this.position = i + zzax2;
                    int a = zziw.a(str, this.buffer, this.position, zzdz());
                    this.position = i;
                    zzas((a - i) - zzax2);
                    this.position = a;
                    return;
                }
                zzas(zziw.a((CharSequence) str));
                this.position = zziw.a(str, this.buffer, this.position, zzdz());
            } catch (zzja e) {
                this.position = i;
                a(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzc((Throwable) e2);
            }
        }
    }

    static final class zzb extends zza {
        private final ByteBuffer zzst;
        private int zzsu;

        zzb(ByteBuffer byteBuffer) {
            super(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            this.zzst = byteBuffer;
            this.zzsu = byteBuffer.position();
        }

        public final void flush() {
            this.zzst.position(this.zzsu + zzec());
        }
    }

    public static class zzc extends IOException {
        zzc() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        zzc(java.lang.String r3) {
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
                r2.<init>(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzfe.zzc.<init>(java.lang.String):void");
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        zzc(java.lang.String r3, java.lang.Throwable r4) {
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzfe.zzc.<init>(java.lang.String, java.lang.Throwable):void");
        }

        zzc(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }
    }

    static final class zzd extends zzfe {
        private final int zzsu;
        private final ByteBuffer zzsv;
        private final ByteBuffer zzsw;

        zzd(ByteBuffer byteBuffer) {
            super();
            this.zzsv = byteBuffer;
            this.zzsw = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzsu = byteBuffer.position();
        }

        private final void zzo(String str) {
            try {
                zziw.a((CharSequence) str, this.zzsw);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc((Throwable) e);
            }
        }

        /* access modifiers changed from: package-private */
        public final void a(int i, zzhf zzhf, zzhw zzhw) {
            zzd(i, 2);
            a(zzhf, zzhw);
        }

        /* access modifiers changed from: package-private */
        public final void a(zzhf zzhf, zzhw zzhw) {
            zzec zzec = (zzec) zzhf;
            int b = zzec.b();
            if (b == -1) {
                b = zzhw.zzp(zzec);
                zzec.a(b);
            }
            zzas(b);
            zzhw.zza(zzhf, this.a);
        }

        public final void flush() {
            this.zzsv.position(this.zzsw.position());
        }

        public final void write(byte[] bArr, int i, int i2) {
            try {
                this.zzsw.put(bArr, i, i2);
            } catch (IndexOutOfBoundsException e) {
                throw new zzc((Throwable) e);
            } catch (BufferOverflowException e2) {
                throw new zzc((Throwable) e2);
            }
        }

        public final void zza(int i, long j) {
            zzd(i, 0);
            zze(j);
        }

        public final void zza(int i, zzeo zzeo) {
            zzd(i, 2);
            zza(zzeo);
        }

        public final void zza(int i, zzhf zzhf) {
            zzd(i, 2);
            zzb(zzhf);
        }

        public final void zza(int i, String str) {
            zzd(i, 2);
            zzm(str);
        }

        public final void zza(zzeo zzeo) {
            zzas(zzeo.size());
            zzeo.a((zzen) this);
        }

        public final void zza(byte[] bArr, int i, int i2) {
            write(bArr, i, i2);
        }

        public final void zzar(int i) {
            if (i >= 0) {
                zzas(i);
            } else {
                zze((long) i);
            }
        }

        public final void zzas(int i) {
            while ((i & -128) != 0) {
                this.zzsw.put((byte) ((i & 127) | 128));
                i >>>= 7;
            }
            try {
                this.zzsw.put((byte) i);
            } catch (BufferOverflowException e) {
                throw new zzc((Throwable) e);
            }
        }

        public final void zzau(int i) {
            try {
                this.zzsw.putInt(i);
            } catch (BufferOverflowException e) {
                throw new zzc((Throwable) e);
            }
        }

        public final void zzb(int i, zzeo zzeo) {
            zzd(1, 3);
            zzf(2, i);
            zza(3, zzeo);
            zzd(1, 4);
        }

        public final void zzb(int i, zzhf zzhf) {
            zzd(1, 3);
            zzf(2, i);
            zza(3, zzhf);
            zzd(1, 4);
        }

        public final void zzb(int i, boolean z) {
            zzd(i, 0);
            zzc(z ? (byte) 1 : 0);
        }

        public final void zzb(zzhf zzhf) {
            zzas(zzhf.zzeq());
            zzhf.zzb(this);
        }

        public final void zzc(byte b) {
            try {
                this.zzsw.put(b);
            } catch (BufferOverflowException e) {
                throw new zzc((Throwable) e);
            }
        }

        public final void zzc(int i, long j) {
            zzd(i, 1);
            zzg(j);
        }

        public final void zzd(int i, int i2) {
            zzas((i << 3) | i2);
        }

        public final int zzdz() {
            return this.zzsw.remaining();
        }

        public final void zze(int i, int i2) {
            zzd(i, 0);
            zzar(i2);
        }

        public final void zze(long j) {
            while ((-128 & j) != 0) {
                this.zzsw.put((byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            try {
                this.zzsw.put((byte) ((int) j));
            } catch (BufferOverflowException e) {
                throw new zzc((Throwable) e);
            }
        }

        public final void zzf(int i, int i2) {
            zzd(i, 0);
            zzas(i2);
        }

        public final void zzf(byte[] bArr, int i, int i2) {
            zzas(i2);
            write(bArr, 0, i2);
        }

        public final void zzg(long j) {
            try {
                this.zzsw.putLong(j);
            } catch (BufferOverflowException e) {
                throw new zzc((Throwable) e);
            }
        }

        public final void zzh(int i, int i2) {
            zzd(i, 5);
            zzau(i2);
        }

        public final void zzm(String str) {
            int position = this.zzsw.position();
            try {
                int zzax = zzax(str.length() * 3);
                int zzax2 = zzax(str.length());
                if (zzax2 == zzax) {
                    int position2 = this.zzsw.position() + zzax2;
                    this.zzsw.position(position2);
                    zzo(str);
                    int position3 = this.zzsw.position();
                    this.zzsw.position(position);
                    zzas(position3 - position2);
                    this.zzsw.position(position3);
                    return;
                }
                zzas(zziw.a((CharSequence) str));
                zzo(str);
            } catch (zzja e) {
                this.zzsw.position(position);
                a(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzc((Throwable) e2);
            }
        }
    }

    static final class zze extends zzfe {
        private final ByteBuffer zzsv;
        private final ByteBuffer zzsw;
        private final long zzsx;
        private final long zzsy;
        private final long zzsz;
        private final long zzta = (this.zzsz - 10);
        private long zztb = this.zzsy;

        zze(ByteBuffer byteBuffer) {
            super();
            this.zzsv = byteBuffer;
            this.zzsw = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzsx = zziu.a(byteBuffer);
            this.zzsy = this.zzsx + ((long) byteBuffer.position());
            this.zzsz = this.zzsx + ((long) byteBuffer.limit());
        }

        private final void zzn(long j) {
            this.zzsw.position((int) (j - this.zzsx));
        }

        /* access modifiers changed from: package-private */
        public final void a(int i, zzhf zzhf, zzhw zzhw) {
            zzd(i, 2);
            a(zzhf, zzhw);
        }

        /* access modifiers changed from: package-private */
        public final void a(zzhf zzhf, zzhw zzhw) {
            zzec zzec = (zzec) zzhf;
            int b = zzec.b();
            if (b == -1) {
                b = zzhw.zzp(zzec);
                zzec.a(b);
            }
            zzas(b);
            zzhw.zza(zzhf, this.a);
        }

        public final void flush() {
            this.zzsv.position((int) (this.zztb - this.zzsx));
        }

        public final void write(byte[] bArr, int i, int i2) {
            if (bArr != null && i >= 0 && i2 >= 0 && bArr.length - i2 >= i) {
                long j = (long) i2;
                long j2 = this.zztb;
                if (this.zzsz - j >= j2) {
                    zziu.a(bArr, (long) i, j2, j);
                    this.zztb += j;
                    return;
                }
            }
            if (bArr == null) {
                throw new NullPointerException("value");
            }
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zztb), Long.valueOf(this.zzsz), Integer.valueOf(i2)}));
        }

        public final void zza(int i, long j) {
            zzd(i, 0);
            zze(j);
        }

        public final void zza(int i, zzeo zzeo) {
            zzd(i, 2);
            zza(zzeo);
        }

        public final void zza(int i, zzhf zzhf) {
            zzd(i, 2);
            zzb(zzhf);
        }

        public final void zza(int i, String str) {
            zzd(i, 2);
            zzm(str);
        }

        public final void zza(zzeo zzeo) {
            zzas(zzeo.size());
            zzeo.a((zzen) this);
        }

        public final void zza(byte[] bArr, int i, int i2) {
            write(bArr, i, i2);
        }

        public final void zzar(int i) {
            if (i >= 0) {
                zzas(i);
            } else {
                zze((long) i);
            }
        }

        public final void zzas(int i) {
            long j;
            if (this.zztb <= this.zzta) {
                while ((i & -128) != 0) {
                    long j2 = this.zztb;
                    this.zztb = j2 + 1;
                    zziu.a(j2, (byte) ((i & 127) | 128));
                    i >>>= 7;
                }
                j = this.zztb;
            } else {
                while (true) {
                    j = this.zztb;
                    if (j >= this.zzsz) {
                        throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(j), Long.valueOf(this.zzsz), 1}));
                    } else if ((i & -128) == 0) {
                        break;
                    } else {
                        this.zztb = j + 1;
                        zziu.a(j, (byte) ((i & 127) | 128));
                        i >>>= 7;
                    }
                }
            }
            this.zztb = 1 + j;
            zziu.a(j, (byte) i);
        }

        public final void zzau(int i) {
            this.zzsw.putInt((int) (this.zztb - this.zzsx), i);
            this.zztb += 4;
        }

        public final void zzb(int i, zzeo zzeo) {
            zzd(1, 3);
            zzf(2, i);
            zza(3, zzeo);
            zzd(1, 4);
        }

        public final void zzb(int i, zzhf zzhf) {
            zzd(1, 3);
            zzf(2, i);
            zza(3, zzhf);
            zzd(1, 4);
        }

        public final void zzb(int i, boolean z) {
            zzd(i, 0);
            zzc(z ? (byte) 1 : 0);
        }

        public final void zzb(zzhf zzhf) {
            zzas(zzhf.zzeq());
            zzhf.zzb(this);
        }

        public final void zzc(byte b) {
            long j = this.zztb;
            if (j < this.zzsz) {
                this.zztb = 1 + j;
                zziu.a(j, b);
                return;
            }
            throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(j), Long.valueOf(this.zzsz), 1}));
        }

        public final void zzc(int i, long j) {
            zzd(i, 1);
            zzg(j);
        }

        public final void zzd(int i, int i2) {
            zzas((i << 3) | i2);
        }

        public final int zzdz() {
            return (int) (this.zzsz - this.zztb);
        }

        public final void zze(int i, int i2) {
            zzd(i, 0);
            zzar(i2);
        }

        public final void zze(long j) {
            long j2;
            if (this.zztb <= this.zzta) {
                while ((j & -128) != 0) {
                    long j3 = this.zztb;
                    this.zztb = j3 + 1;
                    zziu.a(j3, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                j2 = this.zztb;
            } else {
                while (true) {
                    j2 = this.zztb;
                    if (j2 >= this.zzsz) {
                        throw new zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(j2), Long.valueOf(this.zzsz), 1}));
                    } else if ((j & -128) == 0) {
                        break;
                    } else {
                        this.zztb = j2 + 1;
                        zziu.a(j2, (byte) ((((int) j) & 127) | 128));
                        j >>>= 7;
                    }
                }
            }
            this.zztb = 1 + j2;
            zziu.a(j2, (byte) ((int) j));
        }

        public final void zzf(int i, int i2) {
            zzd(i, 0);
            zzas(i2);
        }

        public final void zzf(byte[] bArr, int i, int i2) {
            zzas(i2);
            write(bArr, 0, i2);
        }

        public final void zzg(long j) {
            this.zzsw.putLong((int) (this.zztb - this.zzsx), j);
            this.zztb += 8;
        }

        public final void zzh(int i, int i2) {
            zzd(i, 5);
            zzau(i2);
        }

        public final void zzm(String str) {
            long j = this.zztb;
            try {
                int zzax = zzax(str.length() * 3);
                int zzax2 = zzax(str.length());
                if (zzax2 == zzax) {
                    int i = ((int) (this.zztb - this.zzsx)) + zzax2;
                    this.zzsw.position(i);
                    zziw.a((CharSequence) str, this.zzsw);
                    int position = this.zzsw.position() - i;
                    zzas(position);
                    this.zztb += (long) position;
                    return;
                }
                int a = zziw.a((CharSequence) str);
                zzas(a);
                zzn(this.zztb);
                zziw.a((CharSequence) str, this.zzsw);
                this.zztb += (long) a;
            } catch (zzja e) {
                this.zztb = j;
                zzn(this.zztb);
                a(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzc((Throwable) e2);
            } catch (IndexOutOfBoundsException e3) {
                throw new zzc((Throwable) e3);
            }
        }
    }

    private zzfe() {
    }

    static int b(int i, zzhf zzhf, zzhw zzhw) {
        return zzav(i) + b(zzhf, zzhw);
    }

    static int b(zzhf zzhf, zzhw zzhw) {
        zzec zzec = (zzec) zzhf;
        int b = zzec.b();
        if (b == -1) {
            b = zzhw.zzp(zzec);
            zzec.a(b);
        }
        return zzax(b) + b;
    }

    @Deprecated
    static int c(int i, zzhf zzhf, zzhw zzhw) {
        int zzav = zzav(i) << 1;
        zzec zzec = (zzec) zzhf;
        int b = zzec.b();
        if (b == -1) {
            b = zzhw.zzp(zzec);
            zzec.a(b);
        }
        return zzav + b;
    }

    public static int zza(int i, zzgm zzgm) {
        int zzav = zzav(i);
        int zzeq = zzgm.zzeq();
        return zzav + zzax(zzeq) + zzeq;
    }

    public static int zza(zzgm zzgm) {
        int zzeq = zzgm.zzeq();
        return zzax(zzeq) + zzeq;
    }

    public static zzfe zza(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return new zzb(byteBuffer);
        }
        if (byteBuffer.isDirect() && !byteBuffer.isReadOnly()) {
            return zziu.b() ? new zze(byteBuffer) : new zzd(byteBuffer);
        }
        throw new IllegalArgumentException("ByteBuffer is read-only");
    }

    public static int zzav(int i) {
        return zzax(i << 3);
    }

    public static int zzaw(int i) {
        if (i >= 0) {
            return zzax(i);
        }
        return 10;
    }

    public static int zzax(int i) {
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

    public static int zzay(int i) {
        return zzax(zzbc(i));
    }

    public static int zzaz(int i) {
        return 4;
    }

    public static int zzb(double d) {
        return 8;
    }

    public static int zzb(int i, double d) {
        return zzav(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzav(i) + 4;
    }

    public static int zzb(int i, zzgm zzgm) {
        return (zzav(1) << 1) + zzj(2, i) + zza(3, zzgm);
    }

    public static int zzb(int i, String str) {
        return zzav(i) + zzn(str);
    }

    public static int zzb(zzeo zzeo) {
        int size = zzeo.size();
        return zzax(size) + size;
    }

    public static int zzba(int i) {
        return 4;
    }

    public static int zzbb(int i) {
        return zzaw(i);
    }

    private static int zzbc(int i) {
        return (i >> 31) ^ (i << 1);
    }

    @Deprecated
    public static int zzbd(int i) {
        return zzax(i);
    }

    public static int zzc(int i, zzeo zzeo) {
        int zzav = zzav(i);
        int size = zzeo.size();
        return zzav + zzax(size) + size;
    }

    public static int zzc(int i, zzhf zzhf) {
        return zzav(i) + zzc(zzhf);
    }

    public static int zzc(int i, boolean z) {
        return zzav(i) + 1;
    }

    public static int zzc(zzhf zzhf) {
        int zzeq = zzhf.zzeq();
        return zzax(zzeq) + zzeq;
    }

    public static int zzd(int i, long j) {
        return zzav(i) + zzi(j);
    }

    public static int zzd(int i, zzeo zzeo) {
        return (zzav(1) << 1) + zzj(2, i) + zzc(3, zzeo);
    }

    public static int zzd(int i, zzhf zzhf) {
        return (zzav(1) << 1) + zzj(2, i) + zzc(3, zzhf);
    }

    @Deprecated
    public static int zzd(zzhf zzhf) {
        return zzhf.zzeq();
    }

    public static int zze(int i, long j) {
        return zzav(i) + zzi(j);
    }

    public static int zzf(int i, long j) {
        return zzav(i) + zzi(zzm(j));
    }

    public static int zzg(float f) {
        return 4;
    }

    public static int zzg(int i, long j) {
        return zzav(i) + 8;
    }

    public static zzfe zzg(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zzh(int i, long j) {
        return zzav(i) + 8;
    }

    public static int zzh(long j) {
        return zzi(j);
    }

    public static int zzh(byte[] bArr) {
        int length = bArr.length;
        return zzax(length) + length;
    }

    public static int zzi(int i, int i2) {
        return zzav(i) + zzaw(i2);
    }

    public static int zzi(long j) {
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

    public static int zzi(boolean z) {
        return 1;
    }

    public static int zzj(int i, int i2) {
        return zzav(i) + zzax(i2);
    }

    public static int zzj(long j) {
        return zzi(zzm(j));
    }

    public static int zzk(int i, int i2) {
        return zzav(i) + zzax(zzbc(i2));
    }

    public static int zzk(long j) {
        return 8;
    }

    public static int zzl(int i, int i2) {
        return zzav(i) + 4;
    }

    public static int zzl(long j) {
        return 8;
    }

    public static int zzm(int i, int i2) {
        return zzav(i) + 4;
    }

    private static long zzm(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int zzn(int i, int i2) {
        return zzav(i) + zzaw(i2);
    }

    public static int zzn(String str) {
        int i;
        try {
            i = zziw.a((CharSequence) str);
        } catch (zzja unused) {
            i = str.getBytes(zzga.a).length;
        }
        return zzax(i) + i;
    }

    /* access modifiers changed from: package-private */
    public abstract void a(int i, zzhf zzhf, zzhw zzhw);

    /* access modifiers changed from: package-private */
    public abstract void a(zzhf zzhf, zzhw zzhw);

    /* access modifiers changed from: package-private */
    public final void a(String str, zzja zzja) {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzja);
        byte[] bytes = str.getBytes(zzga.a);
        try {
            zzas(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzc((Throwable) e);
        } catch (zzc e2) {
            throw e2;
        }
    }

    public abstract void flush();

    public abstract void write(byte[] bArr, int i, int i2);

    public final void zza(double d) {
        zzg(Double.doubleToRawLongBits(d));
    }

    public final void zza(int i, double d) {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zza(int i, float f) {
        zzh(i, Float.floatToRawIntBits(f));
    }

    public abstract void zza(int i, long j);

    public abstract void zza(int i, zzeo zzeo);

    public abstract void zza(int i, zzhf zzhf);

    public abstract void zza(int i, String str);

    public abstract void zza(zzeo zzeo);

    public abstract void zzar(int i);

    public abstract void zzas(int i);

    public final void zzat(int i) {
        zzas(zzbc(i));
    }

    public abstract void zzau(int i);

    public final void zzb(int i, long j) {
        zza(i, zzm(j));
    }

    public abstract void zzb(int i, zzeo zzeo);

    public abstract void zzb(int i, zzhf zzhf);

    public abstract void zzb(int i, boolean z);

    public abstract void zzb(zzhf zzhf);

    public abstract void zzc(byte b);

    public abstract void zzc(int i, long j);

    public abstract void zzd(int i, int i2);

    public abstract int zzdz();

    public abstract void zze(int i, int i2);

    public abstract void zze(long j);

    public final void zzea() {
        if (zzdz() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public final void zzf(float f) {
        zzau(Float.floatToRawIntBits(f));
    }

    public abstract void zzf(int i, int i2);

    public final void zzf(long j) {
        zze(zzm(j));
    }

    /* access modifiers changed from: package-private */
    public abstract void zzf(byte[] bArr, int i, int i2);

    public final void zzg(int i, int i2) {
        zzf(i, zzbc(i2));
    }

    public abstract void zzg(long j);

    public abstract void zzh(int i, int i2);

    public final void zzh(boolean z) {
        zzc(z ? (byte) 1 : 0);
    }

    public abstract void zzm(String str);
}

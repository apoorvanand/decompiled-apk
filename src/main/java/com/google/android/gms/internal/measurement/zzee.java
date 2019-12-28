package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzee extends zzdm {
    private static final Logger logger = Logger.getLogger(zzee.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzaec = zzhv.a();
    zzei a;

    static class zza extends zzee {
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
        public final void a(int i, zzgi zzgi, zzgx zzgx) {
            zzb(i, 2);
            zzdf zzdf = (zzdf) zzgi;
            int b = zzdf.b();
            if (b == -1) {
                b = zzgx.zzt(zzdf);
                zzdf.a(b);
            }
            zzbf(b);
            zzgx.zza(zzgi, this.a);
        }

        /* access modifiers changed from: package-private */
        public final void a(zzgi zzgi, zzgx zzgx) {
            zzdf zzdf = (zzdf) zzgi;
            int b = zzdf.b();
            if (b == -1) {
                b = zzgx.zzt(zzdf);
                zzdf.a(b);
            }
            zzbf(b);
            zzgx.zza(zzgi, this.a);
        }

        public void flush() {
        }

        public final void write(byte[] bArr, int i, int i2) {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
            }
        }

        public final void zza(int i, long j) {
            zzb(i, 0);
            zzbn(j);
        }

        public final void zza(int i, zzdp zzdp) {
            zzb(i, 2);
            zza(zzdp);
        }

        public final void zza(int i, zzgi zzgi) {
            zzb(i, 2);
            zzb(zzgi);
        }

        public final void zza(zzdp zzdp) {
            zzbf(zzdp.size());
            zzdp.a((zzdm) this);
        }

        public final void zza(byte[] bArr, int i, int i2) {
            write(bArr, i, i2);
        }

        public final void zzb(int i, int i2) {
            zzbf((i << 3) | i2);
        }

        public final void zzb(int i, zzdp zzdp) {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzdp);
            zzb(1, 4);
        }

        public final void zzb(int i, zzgi zzgi) {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzgi);
            zzb(1, 4);
        }

        public final void zzb(int i, String str) {
            zzb(i, 2);
            zzdr(str);
        }

        public final void zzb(int i, boolean z) {
            zzb(i, 0);
            zzc(z ? (byte) 1 : 0);
        }

        public final void zzb(zzgi zzgi) {
            zzbf(zzgi.zzuk());
            zzgi.zzb(this);
        }

        public final void zzbe(int i) {
            if (i >= 0) {
                zzbf(i);
            } else {
                zzbn((long) i);
            }
        }

        public final void zzbf(int i) {
            if (!zzee.zzaec || zzdi.a() || zztg() < 5) {
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
                if ((i & -128) != 0) {
                    byte[] bArr3 = this.buffer;
                    int i4 = this.position;
                    this.position = i4 + 1;
                    zzhv.a(bArr3, (long) i4, (byte) (i | 128));
                    i >>>= 7;
                    if ((i & -128) != 0) {
                        byte[] bArr4 = this.buffer;
                        int i5 = this.position;
                        this.position = i5 + 1;
                        zzhv.a(bArr4, (long) i5, (byte) (i | 128));
                        i >>>= 7;
                        if ((i & -128) != 0) {
                            byte[] bArr5 = this.buffer;
                            int i6 = this.position;
                            this.position = i6 + 1;
                            zzhv.a(bArr5, (long) i6, (byte) (i | 128));
                            i >>>= 7;
                            if ((i & -128) != 0) {
                                byte[] bArr6 = this.buffer;
                                int i7 = this.position;
                                this.position = i7 + 1;
                                zzhv.a(bArr6, (long) i7, (byte) (i | 128));
                                i >>>= 7;
                            }
                        }
                    }
                }
                byte[] bArr7 = this.buffer;
                int i8 = this.position;
                this.position = i8 + 1;
                zzhv.a(bArr7, (long) i8, (byte) i);
            }
        }

        public final void zzbh(int i) {
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

        public final void zzbn(long j) {
            if (!zzee.zzaec || zztg() < 10) {
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
                    zzhv.a(bArr3, (long) i3, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                zzhv.a(bArr4, (long) i4, (byte) ((int) j));
            }
        }

        public final void zzbp(long j) {
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

        public final void zzc(byte b) {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), 1}), e);
            }
        }

        public final void zzc(int i, int i2) {
            zzb(i, 0);
            zzbe(i2);
        }

        public final void zzc(int i, long j) {
            zzb(i, 1);
            zzbp(j);
        }

        public final void zzd(int i, int i2) {
            zzb(i, 0);
            zzbf(i2);
        }

        public final void zzdr(String str) {
            int i = this.position;
            try {
                int zzbk = zzbk(str.length() * 3);
                int zzbk2 = zzbk(str.length());
                if (zzbk2 == zzbk) {
                    this.position = i + zzbk2;
                    int a = zzhy.a(str, this.buffer, this.position, zztg());
                    this.position = i;
                    zzbf((a - i) - zzbk2);
                    this.position = a;
                    return;
                }
                zzbf(zzhy.a((CharSequence) str));
                this.position = zzhy.a(str, this.buffer, this.position, zztg());
            } catch (zzib e) {
                this.position = i;
                a(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzb((Throwable) e2);
            }
        }

        public final void zze(byte[] bArr, int i, int i2) {
            zzbf(i2);
            write(bArr, 0, i2);
        }

        public final void zzf(int i, int i2) {
            zzb(i, 5);
            zzbh(i2);
        }

        public final int zztg() {
            return this.limit - this.position;
        }

        public final int zztj() {
            return this.position - this.offset;
        }
    }

    public static class zzb extends IOException {
        zzb() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        zzb(java.lang.String r3) {
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzee.zzb.<init>(java.lang.String):void");
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzee.zzb.<init>(java.lang.String, java.lang.Throwable):void");
        }

        zzb(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }
    }

    static final class zzc extends zza {
        private final ByteBuffer zzaef;
        private int zzaeg;

        zzc(ByteBuffer byteBuffer) {
            super(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            this.zzaef = byteBuffer;
            this.zzaeg = byteBuffer.position();
        }

        public final void flush() {
            this.zzaef.position(this.zzaeg + zztj());
        }
    }

    static final class zzd extends zzee {
        private final ByteBuffer zzaeh;
        private final ByteBuffer zzaei;
        private final long zzaej;
        private final long zzaek;
        private final long zzael;
        private final long zzaem = (this.zzael - 10);
        private long zzaen = this.zzaek;

        zzd(ByteBuffer byteBuffer) {
            super();
            this.zzaeh = byteBuffer;
            this.zzaei = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzaej = zzhv.a(byteBuffer);
            this.zzaek = this.zzaej + ((long) byteBuffer.position());
            this.zzael = this.zzaej + ((long) byteBuffer.limit());
        }

        private final void zzbw(long j) {
            this.zzaei.position((int) (j - this.zzaej));
        }

        /* access modifiers changed from: package-private */
        public final void a(int i, zzgi zzgi, zzgx zzgx) {
            zzb(i, 2);
            a(zzgi, zzgx);
        }

        /* access modifiers changed from: package-private */
        public final void a(zzgi zzgi, zzgx zzgx) {
            zzdf zzdf = (zzdf) zzgi;
            int b = zzdf.b();
            if (b == -1) {
                b = zzgx.zzt(zzdf);
                zzdf.a(b);
            }
            zzbf(b);
            zzgx.zza(zzgi, this.a);
        }

        public final void flush() {
            this.zzaeh.position((int) (this.zzaen - this.zzaej));
        }

        public final void write(byte[] bArr, int i, int i2) {
            if (bArr != null && i >= 0 && i2 >= 0 && bArr.length - i2 >= i) {
                long j = (long) i2;
                long j2 = this.zzaen;
                if (this.zzael - j >= j2) {
                    zzhv.a(bArr, (long) i, j2, j);
                    this.zzaen += j;
                    return;
                }
            }
            if (bArr == null) {
                throw new NullPointerException("value");
            }
            throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(this.zzaen), Long.valueOf(this.zzael), Integer.valueOf(i2)}));
        }

        public final void zza(int i, long j) {
            zzb(i, 0);
            zzbn(j);
        }

        public final void zza(int i, zzdp zzdp) {
            zzb(i, 2);
            zza(zzdp);
        }

        public final void zza(int i, zzgi zzgi) {
            zzb(i, 2);
            zzb(zzgi);
        }

        public final void zza(zzdp zzdp) {
            zzbf(zzdp.size());
            zzdp.a((zzdm) this);
        }

        public final void zza(byte[] bArr, int i, int i2) {
            write(bArr, i, i2);
        }

        public final void zzb(int i, int i2) {
            zzbf((i << 3) | i2);
        }

        public final void zzb(int i, zzdp zzdp) {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzdp);
            zzb(1, 4);
        }

        public final void zzb(int i, zzgi zzgi) {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzgi);
            zzb(1, 4);
        }

        public final void zzb(int i, String str) {
            zzb(i, 2);
            zzdr(str);
        }

        public final void zzb(int i, boolean z) {
            zzb(i, 0);
            zzc(z ? (byte) 1 : 0);
        }

        public final void zzb(zzgi zzgi) {
            zzbf(zzgi.zzuk());
            zzgi.zzb(this);
        }

        public final void zzbe(int i) {
            if (i >= 0) {
                zzbf(i);
            } else {
                zzbn((long) i);
            }
        }

        public final void zzbf(int i) {
            long j;
            if (this.zzaen <= this.zzaem) {
                while ((i & -128) != 0) {
                    long j2 = this.zzaen;
                    this.zzaen = j2 + 1;
                    zzhv.a(j2, (byte) ((i & 127) | 128));
                    i >>>= 7;
                }
                j = this.zzaen;
            } else {
                while (true) {
                    j = this.zzaen;
                    if (j >= this.zzael) {
                        throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(j), Long.valueOf(this.zzael), 1}));
                    } else if ((i & -128) == 0) {
                        break;
                    } else {
                        this.zzaen = j + 1;
                        zzhv.a(j, (byte) ((i & 127) | 128));
                        i >>>= 7;
                    }
                }
            }
            this.zzaen = 1 + j;
            zzhv.a(j, (byte) i);
        }

        public final void zzbh(int i) {
            this.zzaei.putInt((int) (this.zzaen - this.zzaej), i);
            this.zzaen += 4;
        }

        public final void zzbn(long j) {
            long j2;
            if (this.zzaen <= this.zzaem) {
                while ((j & -128) != 0) {
                    long j3 = this.zzaen;
                    this.zzaen = j3 + 1;
                    zzhv.a(j3, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                j2 = this.zzaen;
            } else {
                while (true) {
                    j2 = this.zzaen;
                    if (j2 >= this.zzael) {
                        throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(j2), Long.valueOf(this.zzael), 1}));
                    } else if ((j & -128) == 0) {
                        break;
                    } else {
                        this.zzaen = j2 + 1;
                        zzhv.a(j2, (byte) ((((int) j) & 127) | 128));
                        j >>>= 7;
                    }
                }
            }
            this.zzaen = 1 + j2;
            zzhv.a(j2, (byte) ((int) j));
        }

        public final void zzbp(long j) {
            this.zzaei.putLong((int) (this.zzaen - this.zzaej), j);
            this.zzaen += 8;
        }

        public final void zzc(byte b) {
            long j = this.zzaen;
            if (j < this.zzael) {
                this.zzaen = 1 + j;
                zzhv.a(j, b);
                return;
            }
            throw new zzb(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Long.valueOf(j), Long.valueOf(this.zzael), 1}));
        }

        public final void zzc(int i, int i2) {
            zzb(i, 0);
            zzbe(i2);
        }

        public final void zzc(int i, long j) {
            zzb(i, 1);
            zzbp(j);
        }

        public final void zzd(int i, int i2) {
            zzb(i, 0);
            zzbf(i2);
        }

        public final void zzdr(String str) {
            long j = this.zzaen;
            try {
                int zzbk = zzbk(str.length() * 3);
                int zzbk2 = zzbk(str.length());
                if (zzbk2 == zzbk) {
                    int i = ((int) (this.zzaen - this.zzaej)) + zzbk2;
                    this.zzaei.position(i);
                    zzhy.a((CharSequence) str, this.zzaei);
                    int position = this.zzaei.position() - i;
                    zzbf(position);
                    this.zzaen += (long) position;
                    return;
                }
                int a = zzhy.a((CharSequence) str);
                zzbf(a);
                zzbw(this.zzaen);
                zzhy.a((CharSequence) str, this.zzaei);
                this.zzaen += (long) a;
            } catch (zzib e) {
                this.zzaen = j;
                zzbw(this.zzaen);
                a(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzb((Throwable) e2);
            } catch (IndexOutOfBoundsException e3) {
                throw new zzb((Throwable) e3);
            }
        }

        public final void zze(byte[] bArr, int i, int i2) {
            zzbf(i2);
            write(bArr, 0, i2);
        }

        public final void zzf(int i, int i2) {
            zzb(i, 5);
            zzbh(i2);
        }

        public final int zztg() {
            return (int) (this.zzael - this.zzaen);
        }
    }

    static final class zze extends zzee {
        private final int zzaeg;
        private final ByteBuffer zzaeh;
        private final ByteBuffer zzaei;

        zze(ByteBuffer byteBuffer) {
            super();
            this.zzaeh = byteBuffer;
            this.zzaei = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzaeg = byteBuffer.position();
        }

        private final void zzdt(String str) {
            try {
                zzhy.a((CharSequence) str, this.zzaei);
            } catch (IndexOutOfBoundsException e) {
                throw new zzb((Throwable) e);
            }
        }

        /* access modifiers changed from: package-private */
        public final void a(int i, zzgi zzgi, zzgx zzgx) {
            zzb(i, 2);
            a(zzgi, zzgx);
        }

        /* access modifiers changed from: package-private */
        public final void a(zzgi zzgi, zzgx zzgx) {
            zzdf zzdf = (zzdf) zzgi;
            int b = zzdf.b();
            if (b == -1) {
                b = zzgx.zzt(zzdf);
                zzdf.a(b);
            }
            zzbf(b);
            zzgx.zza(zzgi, this.a);
        }

        public final void flush() {
            this.zzaeh.position(this.zzaei.position());
        }

        public final void write(byte[] bArr, int i, int i2) {
            try {
                this.zzaei.put(bArr, i, i2);
            } catch (IndexOutOfBoundsException e) {
                throw new zzb((Throwable) e);
            } catch (BufferOverflowException e2) {
                throw new zzb((Throwable) e2);
            }
        }

        public final void zza(int i, long j) {
            zzb(i, 0);
            zzbn(j);
        }

        public final void zza(int i, zzdp zzdp) {
            zzb(i, 2);
            zza(zzdp);
        }

        public final void zza(int i, zzgi zzgi) {
            zzb(i, 2);
            zzb(zzgi);
        }

        public final void zza(zzdp zzdp) {
            zzbf(zzdp.size());
            zzdp.a((zzdm) this);
        }

        public final void zza(byte[] bArr, int i, int i2) {
            write(bArr, i, i2);
        }

        public final void zzb(int i, int i2) {
            zzbf((i << 3) | i2);
        }

        public final void zzb(int i, zzdp zzdp) {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzdp);
            zzb(1, 4);
        }

        public final void zzb(int i, zzgi zzgi) {
            zzb(1, 3);
            zzd(2, i);
            zza(3, zzgi);
            zzb(1, 4);
        }

        public final void zzb(int i, String str) {
            zzb(i, 2);
            zzdr(str);
        }

        public final void zzb(int i, boolean z) {
            zzb(i, 0);
            zzc(z ? (byte) 1 : 0);
        }

        public final void zzb(zzgi zzgi) {
            zzbf(zzgi.zzuk());
            zzgi.zzb(this);
        }

        public final void zzbe(int i) {
            if (i >= 0) {
                zzbf(i);
            } else {
                zzbn((long) i);
            }
        }

        public final void zzbf(int i) {
            while ((i & -128) != 0) {
                this.zzaei.put((byte) ((i & 127) | 128));
                i >>>= 7;
            }
            try {
                this.zzaei.put((byte) i);
            } catch (BufferOverflowException e) {
                throw new zzb((Throwable) e);
            }
        }

        public final void zzbh(int i) {
            try {
                this.zzaei.putInt(i);
            } catch (BufferOverflowException e) {
                throw new zzb((Throwable) e);
            }
        }

        public final void zzbn(long j) {
            while ((-128 & j) != 0) {
                this.zzaei.put((byte) ((((int) j) & 127) | 128));
                j >>>= 7;
            }
            try {
                this.zzaei.put((byte) ((int) j));
            } catch (BufferOverflowException e) {
                throw new zzb((Throwable) e);
            }
        }

        public final void zzbp(long j) {
            try {
                this.zzaei.putLong(j);
            } catch (BufferOverflowException e) {
                throw new zzb((Throwable) e);
            }
        }

        public final void zzc(byte b) {
            try {
                this.zzaei.put(b);
            } catch (BufferOverflowException e) {
                throw new zzb((Throwable) e);
            }
        }

        public final void zzc(int i, int i2) {
            zzb(i, 0);
            zzbe(i2);
        }

        public final void zzc(int i, long j) {
            zzb(i, 1);
            zzbp(j);
        }

        public final void zzd(int i, int i2) {
            zzb(i, 0);
            zzbf(i2);
        }

        public final void zzdr(String str) {
            int position = this.zzaei.position();
            try {
                int zzbk = zzbk(str.length() * 3);
                int zzbk2 = zzbk(str.length());
                if (zzbk2 == zzbk) {
                    int position2 = this.zzaei.position() + zzbk2;
                    this.zzaei.position(position2);
                    zzdt(str);
                    int position3 = this.zzaei.position();
                    this.zzaei.position(position);
                    zzbf(position3 - position2);
                    this.zzaei.position(position3);
                    return;
                }
                zzbf(zzhy.a((CharSequence) str));
                zzdt(str);
            } catch (zzib e) {
                this.zzaei.position(position);
                a(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzb((Throwable) e2);
            }
        }

        public final void zze(byte[] bArr, int i, int i2) {
            zzbf(i2);
            write(bArr, 0, i2);
        }

        public final void zzf(int i, int i2) {
            zzb(i, 5);
            zzbh(i2);
        }

        public final int zztg() {
            return this.zzaei.remaining();
        }
    }

    private zzee() {
    }

    static int b(int i, zzgi zzgi, zzgx zzgx) {
        return zzbi(i) + b(zzgi, zzgx);
    }

    static int b(zzgi zzgi, zzgx zzgx) {
        zzdf zzdf = (zzdf) zzgi;
        int b = zzdf.b();
        if (b == -1) {
            b = zzgx.zzt(zzdf);
            zzdf.a(b);
        }
        return zzbk(b) + b;
    }

    @Deprecated
    static int c(int i, zzgi zzgi, zzgx zzgx) {
        int zzbi = zzbi(i) << 1;
        zzdf zzdf = (zzdf) zzgi;
        int b = zzdf.b();
        if (b == -1) {
            b = zzgx.zzt(zzdf);
            zzdf.a(b);
        }
        return zzbi + b;
    }

    public static int zza(int i, zzfn zzfn) {
        int zzbi = zzbi(i);
        int zzuk = zzfn.zzuk();
        return zzbi + zzbk(zzuk) + zzuk;
    }

    public static int zza(zzfn zzfn) {
        int zzuk = zzfn.zzuk();
        return zzbk(zzuk) + zzuk;
    }

    public static zzee zza(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return new zzc(byteBuffer);
        }
        if (byteBuffer.isDirect() && !byteBuffer.isReadOnly()) {
            return zzhv.b() ? new zzd(byteBuffer) : new zze(byteBuffer);
        }
        throw new IllegalArgumentException("ByteBuffer is read-only");
    }

    public static int zzb(float f) {
        return 4;
    }

    public static int zzb(int i, double d) {
        return zzbi(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzbi(i) + 4;
    }

    public static int zzb(int i, zzfn zzfn) {
        return (zzbi(1) << 1) + zzh(2, i) + zza(3, zzfn);
    }

    public static int zzb(zzdp zzdp) {
        int size = zzdp.size();
        return zzbk(size) + size;
    }

    public static int zzbi(int i) {
        return zzbk(i << 3);
    }

    public static int zzbj(int i) {
        if (i >= 0) {
            return zzbk(i);
        }
        return 10;
    }

    public static int zzbk(int i) {
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

    public static int zzbl(int i) {
        return zzbk(zzbp(i));
    }

    public static int zzbm(int i) {
        return 4;
    }

    public static int zzbn(int i) {
        return 4;
    }

    public static int zzbo(int i) {
        return zzbj(i);
    }

    private static int zzbp(int i) {
        return (i >> 31) ^ (i << 1);
    }

    @Deprecated
    public static int zzbq(int i) {
        return zzbk(i);
    }

    public static int zzbq(long j) {
        return zzbr(j);
    }

    public static int zzbr(long j) {
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

    public static int zzbs(long j) {
        return zzbr(zzbv(j));
    }

    public static int zzbt(long j) {
        return 8;
    }

    public static int zzbu(long j) {
        return 8;
    }

    private static long zzbv(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int zzc(int i, zzdp zzdp) {
        int zzbi = zzbi(i);
        int size = zzdp.size();
        return zzbi + zzbk(size) + size;
    }

    public static int zzc(int i, zzgi zzgi) {
        return zzbi(i) + zzc(zzgi);
    }

    public static int zzc(int i, String str) {
        return zzbi(i) + zzds(str);
    }

    public static int zzc(int i, boolean z) {
        return zzbi(i) + 1;
    }

    public static int zzc(zzgi zzgi) {
        int zzuk = zzgi.zzuk();
        return zzbk(zzuk) + zzuk;
    }

    public static int zzd(int i, long j) {
        return zzbi(i) + zzbr(j);
    }

    public static int zzd(int i, zzdp zzdp) {
        return (zzbi(1) << 1) + zzh(2, i) + zzc(3, zzdp);
    }

    public static int zzd(int i, zzgi zzgi) {
        return (zzbi(1) << 1) + zzh(2, i) + zzc(3, zzgi);
    }

    @Deprecated
    public static int zzd(zzgi zzgi) {
        return zzgi.zzuk();
    }

    public static int zzds(String str) {
        int i;
        try {
            i = zzhy.a((CharSequence) str);
        } catch (zzib unused) {
            i = str.getBytes(zzez.a).length;
        }
        return zzbk(i) + i;
    }

    public static int zze(double d) {
        return 8;
    }

    public static int zze(int i, long j) {
        return zzbi(i) + zzbr(j);
    }

    public static int zzf(int i, long j) {
        return zzbi(i) + zzbr(zzbv(j));
    }

    public static zzee zzf(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zzg(int i, int i2) {
        return zzbi(i) + zzbj(i2);
    }

    public static int zzg(int i, long j) {
        return zzbi(i) + 8;
    }

    public static int zzg(byte[] bArr) {
        int length = bArr.length;
        return zzbk(length) + length;
    }

    public static int zzh(int i, int i2) {
        return zzbi(i) + zzbk(i2);
    }

    public static int zzh(int i, long j) {
        return zzbi(i) + 8;
    }

    public static int zzi(int i, int i2) {
        return zzbi(i) + zzbk(zzbp(i2));
    }

    public static int zzj(int i, int i2) {
        return zzbi(i) + 4;
    }

    public static int zzk(int i, int i2) {
        return zzbi(i) + 4;
    }

    public static int zzl(int i, int i2) {
        return zzbi(i) + zzbj(i2);
    }

    public static int zzr(boolean z) {
        return 1;
    }

    /* access modifiers changed from: package-private */
    public abstract void a(int i, zzgi zzgi, zzgx zzgx);

    /* access modifiers changed from: package-private */
    public abstract void a(zzgi zzgi, zzgx zzgx);

    /* access modifiers changed from: package-private */
    public final void a(String str, zzib zzib) {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzib);
        byte[] bytes = str.getBytes(zzez.a);
        try {
            zzbf(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzb((Throwable) e);
        } catch (zzb e2) {
            throw e2;
        }
    }

    public abstract void flush();

    public abstract void write(byte[] bArr, int i, int i2);

    public final void zza(float f) {
        zzbh(Float.floatToRawIntBits(f));
    }

    public final void zza(int i, double d) {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zza(int i, float f) {
        zzf(i, Float.floatToRawIntBits(f));
    }

    public abstract void zza(int i, long j);

    public abstract void zza(int i, zzdp zzdp);

    public abstract void zza(int i, zzgi zzgi);

    public abstract void zza(zzdp zzdp);

    public abstract void zzb(int i, int i2);

    public final void zzb(int i, long j) {
        zza(i, zzbv(j));
    }

    public abstract void zzb(int i, zzdp zzdp);

    public abstract void zzb(int i, zzgi zzgi);

    public abstract void zzb(int i, String str);

    public abstract void zzb(int i, boolean z);

    public abstract void zzb(zzgi zzgi);

    public abstract void zzbe(int i);

    public abstract void zzbf(int i);

    public final void zzbg(int i) {
        zzbf(zzbp(i));
    }

    public abstract void zzbh(int i);

    public abstract void zzbn(long j);

    public final void zzbo(long j) {
        zzbn(zzbv(j));
    }

    public abstract void zzbp(long j);

    public abstract void zzc(byte b);

    public abstract void zzc(int i, int i2);

    public abstract void zzc(int i, long j);

    public final void zzd(double d) {
        zzbp(Double.doubleToRawLongBits(d));
    }

    public abstract void zzd(int i, int i2);

    public abstract void zzdr(String str);

    public final void zze(int i, int i2) {
        zzd(i, zzbp(i2));
    }

    /* access modifiers changed from: package-private */
    public abstract void zze(byte[] bArr, int i, int i2);

    public abstract void zzf(int i, int i2);

    public final void zzq(boolean z) {
        zzc(z ? (byte) 1 : 0);
    }

    public abstract int zztg();

    public final void zzth() {
        if (zztg() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }
}

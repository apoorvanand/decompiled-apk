package com.google.android.gms.internal.vision;

import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.util.List;

final class zzel extends zzej {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private int tag;
    private final boolean zzrs = true;
    private final int zzrt;
    private int zzru;

    public zzel(ByteBuffer byteBuffer, boolean z) {
        super((zzek) null);
        this.buffer = byteBuffer.array();
        int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
        this.pos = arrayOffset;
        this.zzrt = arrayOffset;
        this.limit = byteBuffer.arrayOffset() + byteBuffer.limit();
    }

    private final byte readByte() {
        int i = this.pos;
        if (i != this.limit) {
            byte[] bArr = this.buffer;
            this.pos = i + 1;
            return bArr[i];
        }
        throw zzgf.a();
    }

    private final Object zza(zzjd zzjd, Class<?> cls, zzfk zzfk) {
        switch (zzek.a[zzjd.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzcu());
            case 2:
                return zzcw();
            case 3:
                return Double.valueOf(readDouble());
            case 4:
                return Integer.valueOf(zzcy());
            case 5:
                return Integer.valueOf(zzct());
            case 6:
                return Long.valueOf(zzcs());
            case 7:
                return Float.valueOf(readFloat());
            case 8:
                return Integer.valueOf(zzcr());
            case 9:
                return Long.valueOf(zzcq());
            case 10:
                return zza(cls, zzfk);
            case 11:
                return Integer.valueOf(zzcz());
            case 12:
                return Long.valueOf(zzda());
            case 13:
                return Integer.valueOf(zzdb());
            case 14:
                return Long.valueOf(zzdc());
            case 15:
                return zzg(true);
            case 16:
                return Integer.valueOf(zzcx());
            case 17:
                return Long.valueOf(zzcp());
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    private final void zza(List<String> list, boolean z) {
        int i;
        int i2;
        if ((this.tag & 7) != 2) {
            throw zzgf.f();
        } else if (!(list instanceof zzgo) || z) {
            do {
                list.add(zzg(z));
                if (!zzcm()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i;
        } else {
            zzgo zzgo = (zzgo) list;
            do {
                zzgo.zzc(zzcw());
                if (!zzcm()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i2;
        }
    }

    private final void zzaa(int i) {
        if (i < 0 || i > this.limit - this.pos) {
            throw zzgf.a();
        }
    }

    private final void zzab(int i) {
        if ((this.tag & 7) != i) {
            throw zzgf.f();
        }
    }

    private final void zzac(int i) {
        zzaa(i);
        if ((i & 7) != 0) {
            throw zzgf.h();
        }
    }

    private final void zzad(int i) {
        zzaa(i);
        if ((i & 3) != 0) {
            throw zzgf.h();
        }
    }

    private final void zzae(int i) {
        if (this.pos != i) {
            throw zzgf.a();
        }
    }

    private final <T> T zzb(zzhw<T> zzhw, zzfk zzfk) {
        int zzdd = zzdd();
        zzaa(zzdd);
        int i = this.limit;
        int i2 = this.pos + zzdd;
        this.limit = i2;
        try {
            T newInstance = zzhw.newInstance();
            zzhw.zza(newInstance, this, zzfk);
            zzhw.zze(newInstance);
            if (this.pos == i2) {
                return newInstance;
            }
            throw zzgf.h();
        } finally {
            this.limit = i;
        }
    }

    private final boolean zzcm() {
        return this.pos == this.limit;
    }

    private final <T> T zzd(zzhw<T> zzhw, zzfk zzfk) {
        int i = this.zzru;
        this.zzru = ((this.tag >>> 3) << 3) | 4;
        try {
            T newInstance = zzhw.newInstance();
            zzhw.zza(newInstance, this, zzfk);
            zzhw.zze(newInstance);
            if (this.tag == this.zzru) {
                return newInstance;
            }
            throw zzgf.h();
        } finally {
            this.zzru = i;
        }
    }

    private final int zzdd() {
        byte b;
        int i = this.pos;
        int i2 = this.limit;
        if (i2 != i) {
            byte[] bArr = this.buffer;
            int i3 = i + 1;
            byte b2 = bArr[i];
            if (b2 >= 0) {
                this.pos = i3;
                return b2;
            } else if (i2 - i3 < 9) {
                return (int) zzdf();
            } else {
                int i4 = i3 + 1;
                byte b3 = b2 ^ (bArr[i3] << 7);
                if (b3 < 0) {
                    b = b3 ^ Byte.MIN_VALUE;
                } else {
                    int i5 = i4 + 1;
                    byte b4 = b3 ^ (bArr[i4] << Ascii.SO);
                    if (b4 >= 0) {
                        b = b4 ^ 16256;
                    } else {
                        i4 = i5 + 1;
                        byte b5 = b4 ^ (bArr[i5] << Ascii.NAK);
                        if (b5 < 0) {
                            b = b5 ^ -2080896;
                        } else {
                            i5 = i4 + 1;
                            byte b6 = bArr[i4];
                            b = (b5 ^ (b6 << Ascii.FS)) ^ 266354560;
                            if (b6 < 0) {
                                i4 = i5 + 1;
                                if (bArr[i5] < 0) {
                                    i5 = i4 + 1;
                                    if (bArr[i4] < 0) {
                                        i4 = i5 + 1;
                                        if (bArr[i5] < 0) {
                                            i5 = i4 + 1;
                                            if (bArr[i4] < 0) {
                                                i4 = i5 + 1;
                                                if (bArr[i5] < 0) {
                                                    throw zzgf.c();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    i4 = i5;
                }
                this.pos = i4;
                return b;
            }
        } else {
            throw zzgf.a();
        }
    }

    private final long zzde() {
        long j;
        int i;
        long j2;
        long j3;
        byte b;
        int i2 = this.pos;
        int i3 = this.limit;
        if (i3 != i2) {
            byte[] bArr = this.buffer;
            int i4 = i2 + 1;
            byte b2 = bArr[i2];
            if (b2 >= 0) {
                this.pos = i4;
                return (long) b2;
            } else if (i3 - i4 < 9) {
                return zzdf();
            } else {
                int i5 = i4 + 1;
                byte b3 = b2 ^ (bArr[i4] << 7);
                if (b3 < 0) {
                    b = b3 ^ Byte.MIN_VALUE;
                } else {
                    int i6 = i5 + 1;
                    byte b4 = b3 ^ (bArr[i5] << Ascii.SO);
                    if (b4 >= 0) {
                        i = i6;
                        j = (long) (b4 ^ 16256);
                    } else {
                        i5 = i6 + 1;
                        byte b5 = b4 ^ (bArr[i6] << Ascii.NAK);
                        if (b5 < 0) {
                            b = b5 ^ -2080896;
                        } else {
                            long j4 = (long) b5;
                            int i7 = i5 + 1;
                            long j5 = j4 ^ (((long) bArr[i5]) << 28);
                            if (j5 >= 0) {
                                j3 = 266354560;
                            } else {
                                int i8 = i7 + 1;
                                long j6 = j5 ^ (((long) bArr[i7]) << 35);
                                if (j6 < 0) {
                                    j2 = -34093383808L;
                                } else {
                                    i7 = i8 + 1;
                                    j5 = j6 ^ (((long) bArr[i8]) << 42);
                                    if (j5 >= 0) {
                                        j3 = 4363953127296L;
                                    } else {
                                        i8 = i7 + 1;
                                        j6 = j5 ^ (((long) bArr[i7]) << 49);
                                        if (j6 < 0) {
                                            j2 = -558586000294016L;
                                        } else {
                                            int i9 = i8 + 1;
                                            long j7 = (j6 ^ (((long) bArr[i8]) << 56)) ^ 71499008037633920L;
                                            if (j7 < 0) {
                                                i = i9 + 1;
                                                if (((long) bArr[i9]) < 0) {
                                                    throw zzgf.c();
                                                }
                                            } else {
                                                i = i9;
                                            }
                                            j = j7;
                                        }
                                    }
                                }
                                j = j6 ^ j2;
                            }
                            j = j5 ^ j3;
                            i = i7;
                        }
                    }
                    this.pos = i;
                    return j;
                }
                j = (long) b;
                this.pos = i;
                return j;
            }
        } else {
            throw zzgf.a();
        }
    }

    private final long zzdf() {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte readByte = readByte();
            j |= ((long) (readByte & Byte.MAX_VALUE)) << i;
            if ((readByte & 128) == 0) {
                return j;
            }
        }
        throw zzgf.c();
    }

    private final int zzdg() {
        zzaa(4);
        return zzdi();
    }

    private final long zzdh() {
        zzaa(8);
        return zzdj();
    }

    private final int zzdi() {
        int i = this.pos;
        byte[] bArr = this.buffer;
        this.pos = i + 4;
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
    }

    private final long zzdj() {
        int i = this.pos;
        byte[] bArr = this.buffer;
        this.pos = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    private final String zzg(boolean z) {
        zzab(2);
        int zzdd = zzdd();
        if (zzdd == 0) {
            return "";
        }
        zzaa(zzdd);
        if (z) {
            byte[] bArr = this.buffer;
            int i = this.pos;
            if (!zziw.zzg(bArr, i, i + zzdd)) {
                throw zzgf.i();
            }
        }
        String str = new String(this.buffer, this.pos, zzdd, zzga.a);
        this.pos += zzdd;
        return str;
    }

    private final void zzz(int i) {
        zzaa(i);
        this.pos += i;
    }

    public final int getTag() {
        return this.tag;
    }

    public final double readDouble() {
        zzab(1);
        return Double.longBitsToDouble(zzdh());
    }

    public final float readFloat() {
        zzab(5);
        return Float.intBitsToFloat(zzdg());
    }

    public final String readString() {
        return zzg(false);
    }

    public final void readStringList(List<String> list) {
        zza(list, false);
    }

    public final <T> T zza(zzhw<T> zzhw, zzfk zzfk) {
        zzab(2);
        return zzb(zzhw, zzfk);
    }

    public final <T> T zza(Class<T> cls, zzfk zzfk) {
        zzab(2);
        return zzb(zzhs.zzgl().zzf(cls), zzfk);
    }

    public final void zza(List<Double> list) {
        int i;
        int i2;
        if (list instanceof zzfh) {
            zzfh zzfh = (zzfh) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzdd = zzdd();
                    zzac(zzdd);
                    int i3 = this.pos + zzdd;
                    while (this.pos < i3) {
                        zzfh.zzc(Double.longBitsToDouble(zzdj()));
                    }
                    return;
                default:
                    throw zzgf.f();
            }
            do {
                zzfh.zzc(readDouble());
                if (!zzcm()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzdd2 = zzdd();
                zzac(zzdd2);
                int i4 = this.pos + zzdd2;
                while (this.pos < i4) {
                    list.add(Double.valueOf(Double.longBitsToDouble(zzdj())));
                }
                return;
            default:
                throw zzgf.f();
        }
        do {
            list.add(Double.valueOf(readDouble()));
            if (!zzcm()) {
                i = this.pos;
            } else {
                return;
            }
        } while (zzdd() == this.tag);
        this.pos = i;
    }

    public final <T> void zza(List<T> list, zzhw<T> zzhw, zzfk zzfk) {
        int i;
        int i2 = this.tag;
        if ((i2 & 7) == 2) {
            do {
                list.add(zzb(zzhw, zzfk));
                if (!zzcm()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == i2);
            this.pos = i;
            return;
        }
        throw zzgf.f();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:15|16|(2:18|34)(3:28|19|20)) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004b, code lost:
        if (zzco() != false) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0055, code lost:
        throw new com.google.android.gms.internal.vision.zzgf("Unable to parse map entry.");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0047 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <K, V> void zza(java.util.Map<K, V> r6, com.google.android.gms.internal.vision.zzgy<K, V> r7, com.google.android.gms.internal.vision.zzfk r8) {
        /*
            r5 = this;
            r0 = 2
            r5.zzab(r0)
            int r0 = r5.zzdd()
            r5.zzaa(r0)
            int r1 = r5.limit
            int r2 = r5.pos
            int r2 = r2 + r0
            r5.limit = r2
            K r0 = r7.zzyw     // Catch:{ all -> 0x005c }
            V r2 = r7.zzgq     // Catch:{ all -> 0x005c }
        L_0x0016:
            int r3 = r5.zzcn()     // Catch:{ all -> 0x005c }
            r4 = 2147483647(0x7fffffff, float:NaN)
            if (r3 == r4) goto L_0x0056
            switch(r3) {
                case 1: goto L_0x0034;
                case 2: goto L_0x0027;
                default: goto L_0x0022;
            }
        L_0x0022:
            boolean r3 = r5.zzco()     // Catch:{ zzgg -> 0x0047 }
            goto L_0x003c
        L_0x0027:
            com.google.android.gms.internal.vision.zzjd r3 = r7.zzyx     // Catch:{ zzgg -> 0x0047 }
            V r4 = r7.zzgq     // Catch:{ zzgg -> 0x0047 }
            java.lang.Class r4 = r4.getClass()     // Catch:{ zzgg -> 0x0047 }
            java.lang.Object r2 = r5.zza((com.google.android.gms.internal.vision.zzjd) r3, (java.lang.Class<?>) r4, (com.google.android.gms.internal.vision.zzfk) r8)     // Catch:{ zzgg -> 0x0047 }
            goto L_0x0016
        L_0x0034:
            com.google.android.gms.internal.vision.zzjd r3 = r7.zzyv     // Catch:{ zzgg -> 0x0047 }
            r4 = 0
            java.lang.Object r0 = r5.zza((com.google.android.gms.internal.vision.zzjd) r3, (java.lang.Class<?>) r4, (com.google.android.gms.internal.vision.zzfk) r4)     // Catch:{ zzgg -> 0x0047 }
            goto L_0x0016
        L_0x003c:
            if (r3 == 0) goto L_0x003f
            goto L_0x0016
        L_0x003f:
            com.google.android.gms.internal.vision.zzgf r3 = new com.google.android.gms.internal.vision.zzgf     // Catch:{ zzgg -> 0x0047 }
            java.lang.String r4 = "Unable to parse map entry."
            r3.<init>(r4)     // Catch:{ zzgg -> 0x0047 }
            throw r3     // Catch:{ zzgg -> 0x0047 }
        L_0x0047:
            boolean r3 = r5.zzco()     // Catch:{ all -> 0x005c }
            if (r3 == 0) goto L_0x004e
            goto L_0x0016
        L_0x004e:
            com.google.android.gms.internal.vision.zzgf r6 = new com.google.android.gms.internal.vision.zzgf     // Catch:{ all -> 0x005c }
            java.lang.String r7 = "Unable to parse map entry."
            r6.<init>(r7)     // Catch:{ all -> 0x005c }
            throw r6     // Catch:{ all -> 0x005c }
        L_0x0056:
            r6.put(r0, r2)     // Catch:{ all -> 0x005c }
            r5.limit = r1
            return
        L_0x005c:
            r6 = move-exception
            r5.limit = r1
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzel.zza(java.util.Map, com.google.android.gms.internal.vision.zzgy, com.google.android.gms.internal.vision.zzfk):void");
    }

    public final <T> T zzb(Class<T> cls, zzfk zzfk) {
        zzab(3);
        return zzd(zzhs.zzgl().zzf(cls), zzfk);
    }

    public final void zzb(List<Float> list) {
        int i;
        int i2;
        if (list instanceof zzfv) {
            zzfv zzfv = (zzfv) list;
            int i3 = this.tag & 7;
            if (i3 == 2) {
                int zzdd = zzdd();
                zzad(zzdd);
                int i4 = this.pos + zzdd;
                while (this.pos < i4) {
                    zzfv.zzh(Float.intBitsToFloat(zzdi()));
                }
            } else if (i3 == 5) {
                do {
                    zzfv.zzh(readFloat());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else {
                throw zzgf.f();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 2) {
                int zzdd2 = zzdd();
                zzad(zzdd2);
                int i6 = this.pos + zzdd2;
                while (this.pos < i6) {
                    list.add(Float.valueOf(Float.intBitsToFloat(zzdi())));
                }
            } else if (i5 == 5) {
                do {
                    list.add(Float.valueOf(readFloat()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else {
                throw zzgf.f();
            }
        }
    }

    public final <T> void zzb(List<T> list, zzhw<T> zzhw, zzfk zzfk) {
        int i;
        int i2 = this.tag;
        if ((i2 & 7) == 3) {
            do {
                list.add(zzd(zzhw, zzfk));
                if (!zzcm()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == i2);
            this.pos = i;
            return;
        }
        throw zzgf.f();
    }

    public final <T> T zzc(zzhw<T> zzhw, zzfk zzfk) {
        zzab(3);
        return zzd(zzhw, zzfk);
    }

    public final void zzc(List<Long> list) {
        int i;
        int i2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzgt.zzp(zzcp());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzgt.zzp(zzde());
                }
                zzae(zzdd);
            } else {
                throw zzgf.f();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Long.valueOf(zzcp()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Long.valueOf(zzde()));
                }
                zzae(zzdd2);
            } else {
                throw zzgf.f();
            }
        }
    }

    public final int zzcn() {
        if (zzcm()) {
            return Integer.MAX_VALUE;
        }
        this.tag = zzdd();
        int i = this.tag;
        if (i == this.zzru) {
            return Integer.MAX_VALUE;
        }
        return i >>> 3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0025 A[LOOP:0: B:10:0x0025->B:13:0x0032, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzco() {
        /*
            r7 = this;
            boolean r0 = r7.zzcm()
            r1 = 0
            if (r0 != 0) goto L_0x007e
            int r0 = r7.tag
            int r2 = r7.zzru
            if (r0 != r2) goto L_0x000f
            goto L_0x007e
        L_0x000f:
            r3 = r0 & 7
            r4 = 5
            r5 = 4
            r6 = 1
            if (r3 == r4) goto L_0x007a
            switch(r3) {
                case 0: goto L_0x004d;
                case 1: goto L_0x004a;
                case 2: goto L_0x0042;
                case 3: goto L_0x001e;
                default: goto L_0x0019;
            }
        L_0x0019:
            com.google.android.gms.internal.vision.zzgg r0 = com.google.android.gms.internal.vision.zzgf.f()
            throw r0
        L_0x001e:
            int r0 = r0 >>> 3
            int r0 = r0 << 3
            r0 = r0 | r5
            r7.zzru = r0
        L_0x0025:
            int r0 = r7.zzcn()
            r1 = 2147483647(0x7fffffff, float:NaN)
            if (r0 == r1) goto L_0x0034
            boolean r0 = r7.zzco()
            if (r0 != 0) goto L_0x0025
        L_0x0034:
            int r0 = r7.tag
            int r1 = r7.zzru
            if (r0 != r1) goto L_0x003d
            r7.zzru = r2
            return r6
        L_0x003d:
            com.google.android.gms.internal.vision.zzgf r0 = com.google.android.gms.internal.vision.zzgf.h()
            throw r0
        L_0x0042:
            int r0 = r7.zzdd()
        L_0x0046:
            r7.zzz(r0)
            return r6
        L_0x004a:
            r0 = 8
            goto L_0x0046
        L_0x004d:
            int r0 = r7.limit
            int r2 = r7.pos
            int r0 = r0 - r2
            r3 = 10
            if (r0 < r3) goto L_0x0069
            byte[] r0 = r7.buffer
            r4 = r2
            r2 = 0
        L_0x005a:
            if (r2 >= r3) goto L_0x0069
            int r5 = r4 + 1
            byte r4 = r0[r4]
            if (r4 < 0) goto L_0x0065
            r7.pos = r5
            goto L_0x0074
        L_0x0065:
            int r2 = r2 + 1
            r4 = r5
            goto L_0x005a
        L_0x0069:
            if (r1 >= r3) goto L_0x0075
            byte r0 = r7.readByte()
            if (r0 >= 0) goto L_0x0074
            int r1 = r1 + 1
            goto L_0x0069
        L_0x0074:
            return r6
        L_0x0075:
            com.google.android.gms.internal.vision.zzgf r0 = com.google.android.gms.internal.vision.zzgf.c()
            throw r0
        L_0x007a:
            r7.zzz(r5)
            return r6
        L_0x007e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzel.zzco():boolean");
    }

    public final long zzcp() {
        zzab(0);
        return zzde();
    }

    public final long zzcq() {
        zzab(0);
        return zzde();
    }

    public final int zzcr() {
        zzab(0);
        return zzdd();
    }

    public final long zzcs() {
        zzab(1);
        return zzdh();
    }

    public final int zzct() {
        zzab(5);
        return zzdg();
    }

    public final boolean zzcu() {
        zzab(0);
        return zzdd() != 0;
    }

    public final String zzcv() {
        return zzg(true);
    }

    public final zzeo zzcw() {
        zzab(2);
        int zzdd = zzdd();
        if (zzdd == 0) {
            return zzeo.zzrx;
        }
        zzaa(zzdd);
        zzeo a = this.zzrs ? zzeo.a(this.buffer, this.pos, zzdd) : zzeo.zzb(this.buffer, this.pos, zzdd);
        this.pos += zzdd;
        return a;
    }

    public final int zzcx() {
        zzab(0);
        return zzdd();
    }

    public final int zzcy() {
        zzab(0);
        return zzdd();
    }

    public final int zzcz() {
        zzab(5);
        return zzdg();
    }

    public final void zzd(List<Long> list) {
        int i;
        int i2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzgt.zzp(zzcq());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzgt.zzp(zzde());
                }
                zzae(zzdd);
            } else {
                throw zzgf.f();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Long.valueOf(zzcq()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Long.valueOf(zzde()));
                }
                zzae(zzdd2);
            } else {
                throw zzgf.f();
            }
        }
    }

    public final long zzda() {
        zzab(1);
        return zzdh();
    }

    public final int zzdb() {
        zzab(0);
        return zzez.zzaq(zzdd());
    }

    public final long zzdc() {
        zzab(0);
        return zzez.zzd(zzde());
    }

    public final void zze(List<Integer> list) {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzfz.zzbg(zzcr());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzfz.zzbg(zzdd());
                }
                zzae(zzdd);
            } else {
                throw zzgf.f();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzcr()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Integer.valueOf(zzdd()));
                }
                zzae(zzdd2);
            } else {
                throw zzgf.f();
            }
        }
    }

    public final void zzf(List<Long> list) {
        int i;
        int i2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzdd = zzdd();
                    zzac(zzdd);
                    int i3 = this.pos + zzdd;
                    while (this.pos < i3) {
                        zzgt.zzp(zzdj());
                    }
                    return;
                default:
                    throw zzgf.f();
            }
            do {
                zzgt.zzp(zzcs());
                if (!zzcm()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzdd2 = zzdd();
                zzac(zzdd2);
                int i4 = this.pos + zzdd2;
                while (this.pos < i4) {
                    list.add(Long.valueOf(zzdj()));
                }
                return;
            default:
                throw zzgf.f();
        }
        do {
            list.add(Long.valueOf(zzcs()));
            if (!zzcm()) {
                i = this.pos;
            } else {
                return;
            }
        } while (zzdd() == this.tag);
        this.pos = i;
    }

    public final void zzg(List<Integer> list) {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i3 = this.tag & 7;
            if (i3 == 2) {
                int zzdd = zzdd();
                zzad(zzdd);
                int i4 = this.pos + zzdd;
                while (this.pos < i4) {
                    zzfz.zzbg(zzdi());
                }
            } else if (i3 == 5) {
                do {
                    zzfz.zzbg(zzct());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else {
                throw zzgf.f();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 2) {
                int zzdd2 = zzdd();
                zzad(zzdd2);
                int i6 = this.pos + zzdd2;
                while (this.pos < i6) {
                    list.add(Integer.valueOf(zzdi()));
                }
            } else if (i5 == 5) {
                do {
                    list.add(Integer.valueOf(zzct()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else {
                throw zzgf.f();
            }
        }
    }

    public final void zzh(List<Boolean> list) {
        int i;
        int i2;
        if (list instanceof zzem) {
            zzem zzem = (zzem) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzem.addBoolean(zzcu());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzem.addBoolean(zzdd() != 0);
                }
                zzae(zzdd);
            } else {
                throw zzgf.f();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Boolean.valueOf(zzcu()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Boolean.valueOf(zzdd() != 0));
                }
                zzae(zzdd2);
            } else {
                throw zzgf.f();
            }
        }
    }

    public final void zzi(List<String> list) {
        zza(list, true);
    }

    public final void zzj(List<zzeo> list) {
        int i;
        if ((this.tag & 7) == 2) {
            do {
                list.add(zzcw());
                if (!zzcm()) {
                    i = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i;
            return;
        }
        throw zzgf.f();
    }

    public final void zzk(List<Integer> list) {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzfz.zzbg(zzcx());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzfz.zzbg(zzdd());
                }
            } else {
                throw zzgf.f();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzcx()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Integer.valueOf(zzdd()));
                }
            } else {
                throw zzgf.f();
            }
        }
    }

    public final void zzl(List<Integer> list) {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzfz.zzbg(zzcy());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzfz.zzbg(zzdd());
                }
            } else {
                throw zzgf.f();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzcy()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Integer.valueOf(zzdd()));
                }
            } else {
                throw zzgf.f();
            }
        }
    }

    public final void zzm(List<Integer> list) {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i3 = this.tag & 7;
            if (i3 == 2) {
                int zzdd = zzdd();
                zzad(zzdd);
                int i4 = this.pos + zzdd;
                while (this.pos < i4) {
                    zzfz.zzbg(zzdi());
                }
            } else if (i3 == 5) {
                do {
                    zzfz.zzbg(zzcz());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else {
                throw zzgf.f();
            }
        } else {
            int i5 = this.tag & 7;
            if (i5 == 2) {
                int zzdd2 = zzdd();
                zzad(zzdd2);
                int i6 = this.pos + zzdd2;
                while (this.pos < i6) {
                    list.add(Integer.valueOf(zzdi()));
                }
            } else if (i5 == 5) {
                do {
                    list.add(Integer.valueOf(zzcz()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else {
                throw zzgf.f();
            }
        }
    }

    public final void zzn(List<Long> list) {
        int i;
        int i2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            switch (this.tag & 7) {
                case 1:
                    break;
                case 2:
                    int zzdd = zzdd();
                    zzac(zzdd);
                    int i3 = this.pos + zzdd;
                    while (this.pos < i3) {
                        zzgt.zzp(zzdj());
                    }
                    return;
                default:
                    throw zzgf.f();
            }
            do {
                zzgt.zzp(zzda());
                if (!zzcm()) {
                    i2 = this.pos;
                } else {
                    return;
                }
            } while (zzdd() == this.tag);
            this.pos = i2;
            return;
        }
        switch (this.tag & 7) {
            case 1:
                break;
            case 2:
                int zzdd2 = zzdd();
                zzac(zzdd2);
                int i4 = this.pos + zzdd2;
                while (this.pos < i4) {
                    list.add(Long.valueOf(zzdj()));
                }
                return;
            default:
                throw zzgf.f();
        }
        do {
            list.add(Long.valueOf(zzda()));
            if (!zzcm()) {
                i = this.pos;
            } else {
                return;
            }
        } while (zzdd() == this.tag);
        this.pos = i;
    }

    public final void zzo(List<Integer> list) {
        int i;
        int i2;
        if (list instanceof zzfz) {
            zzfz zzfz = (zzfz) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzfz.zzbg(zzdb());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzfz.zzbg(zzez.zzaq(zzdd()));
                }
            } else {
                throw zzgf.f();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(zzdb()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Integer.valueOf(zzez.zzaq(zzdd())));
                }
            } else {
                throw zzgf.f();
            }
        }
    }

    public final void zzp(List<Long> list) {
        int i;
        int i2;
        if (list instanceof zzgt) {
            zzgt zzgt = (zzgt) list;
            int i3 = this.tag & 7;
            if (i3 == 0) {
                do {
                    zzgt.zzp(zzdc());
                    if (!zzcm()) {
                        i2 = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i2;
            } else if (i3 == 2) {
                int zzdd = this.pos + zzdd();
                while (this.pos < zzdd) {
                    zzgt.zzp(zzez.zzd(zzde()));
                }
            } else {
                throw zzgf.f();
            }
        } else {
            int i4 = this.tag & 7;
            if (i4 == 0) {
                do {
                    list.add(Long.valueOf(zzdc()));
                    if (!zzcm()) {
                        i = this.pos;
                    } else {
                        return;
                    }
                } while (zzdd() == this.tag);
                this.pos = i;
            } else if (i4 == 2) {
                int zzdd2 = this.pos + zzdd();
                while (this.pos < zzdd2) {
                    list.add(Long.valueOf(zzez.zzd(zzde())));
                }
            } else {
                throw zzgf.f();
            }
        }
    }
}

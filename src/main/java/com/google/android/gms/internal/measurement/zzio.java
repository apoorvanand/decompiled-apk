package com.google.android.gms.internal.measurement;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzio {
    private final ByteBuffer zzaei;
    private zzee zzaol;
    private int zzaom;

    private zzio(ByteBuffer byteBuffer) {
        this.zzaei = byteBuffer;
        this.zzaei.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzio(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, 0, i2));
    }

    private static int zza(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 2048) {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) >= 65536) {
                                i2++;
                            } else {
                                StringBuilder sb = new StringBuilder(39);
                                sb.append("Unpaired surrogate at index ");
                                sb.append(i2);
                                throw new IllegalArgumentException(sb.toString());
                            }
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        StringBuilder sb2 = new StringBuilder(54);
        sb2.append("UTF-8 length does not fit in int: ");
        sb2.append(((long) i3) + 4294967296L);
        throw new IllegalArgumentException(sb2.toString());
    }

    public static int zzb(int i, zziw zziw) {
        int zzbi = zzbi(i);
        int zzuk = zziw.zzuk();
        return zzbi + zzbq(zzuk) + zzuk;
    }

    public static int zzbi(int i) {
        return zzbq(i << 3);
    }

    public static int zzbj(int i) {
        if (i >= 0) {
            return zzbq(i);
        }
        return 10;
    }

    public static int zzbq(int i) {
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

    public static int zzc(int i, String str) {
        int zzbi = zzbi(i);
        int zza = zza(str);
        return zzbi + zzbq(zza) + zza;
    }

    private final void zzcj(int i) {
        byte b = (byte) i;
        if (this.zzaei.hasRemaining()) {
            this.zzaei.put(b);
            return;
        }
        throw new zzin(this.zzaei.position(), this.zzaei.limit());
    }

    private static void zzd(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i;
        int i2;
        int i3;
        char charAt;
        if (!byteBuffer.isReadOnly()) {
            int i4 = 0;
            if (byteBuffer.hasArray()) {
                try {
                    byte[] array = byteBuffer.array();
                    int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
                    int remaining = byteBuffer.remaining();
                    int length = charSequence.length();
                    int i5 = remaining + arrayOffset;
                    while (i4 < length) {
                        int i6 = i4 + arrayOffset;
                        if (i6 >= i5 || (charAt = charSequence.charAt(i4)) >= 128) {
                            break;
                        }
                        array[i6] = (byte) charAt;
                        i4++;
                    }
                    if (i4 == length) {
                        i2 = arrayOffset + length;
                    } else {
                        i2 = arrayOffset + i4;
                        while (i4 < length) {
                            char charAt2 = charSequence.charAt(i4);
                            if (charAt2 < 128 && i2 < i5) {
                                i3 = i2 + 1;
                                array[i2] = (byte) charAt2;
                            } else if (charAt2 < 2048 && i2 <= i5 - 2) {
                                int i7 = i2 + 1;
                                array[i2] = (byte) ((charAt2 >>> 6) | 960);
                                i2 = i7 + 1;
                                array[i7] = (byte) ((charAt2 & '?') | 128);
                                i4++;
                            } else if ((charAt2 < 55296 || 57343 < charAt2) && i2 <= i5 - 3) {
                                int i8 = i2 + 1;
                                array[i2] = (byte) ((charAt2 >>> 12) | 480);
                                int i9 = i8 + 1;
                                array[i8] = (byte) (((charAt2 >>> 6) & 63) | 128);
                                i3 = i9 + 1;
                                array[i9] = (byte) ((charAt2 & '?') | 128);
                            } else if (i2 <= i5 - 4) {
                                int i10 = i4 + 1;
                                if (i10 != charSequence.length()) {
                                    char charAt3 = charSequence.charAt(i10);
                                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                                        int i11 = i2 + 1;
                                        array[i2] = (byte) ((codePoint >>> 18) | 240);
                                        int i12 = i11 + 1;
                                        array[i11] = (byte) (((codePoint >>> 12) & 63) | 128);
                                        int i13 = i12 + 1;
                                        array[i12] = (byte) (((codePoint >>> 6) & 63) | 128);
                                        i2 = i13 + 1;
                                        array[i13] = (byte) ((codePoint & 63) | 128);
                                        i4 = i10;
                                        i4++;
                                    } else {
                                        i4 = i10;
                                    }
                                }
                                StringBuilder sb = new StringBuilder(39);
                                sb.append("Unpaired surrogate at index ");
                                sb.append(i4 - 1);
                                throw new IllegalArgumentException(sb.toString());
                            } else {
                                StringBuilder sb2 = new StringBuilder(37);
                                sb2.append("Failed writing ");
                                sb2.append(charAt2);
                                sb2.append(" at index ");
                                sb2.append(i2);
                                throw new ArrayIndexOutOfBoundsException(sb2.toString());
                            }
                            i2 = i3;
                            i4++;
                        }
                    }
                    byteBuffer.position(i2 - byteBuffer.arrayOffset());
                } catch (ArrayIndexOutOfBoundsException e) {
                    BufferOverflowException bufferOverflowException = new BufferOverflowException();
                    bufferOverflowException.initCause(e);
                    throw bufferOverflowException;
                }
            } else {
                int length2 = charSequence.length();
                while (i4 < length2) {
                    char charAt4 = charSequence.charAt(i4);
                    if (charAt4 >= 128) {
                        if (charAt4 < 2048) {
                            i = (charAt4 >>> 6) | 960;
                        } else if (charAt4 < 55296 || 57343 < charAt4) {
                            byteBuffer.put((byte) ((charAt4 >>> 12) | 480));
                            i = ((charAt4 >>> 6) & 63) | 128;
                        } else {
                            int i14 = i4 + 1;
                            if (i14 != charSequence.length()) {
                                char charAt5 = charSequence.charAt(i14);
                                if (Character.isSurrogatePair(charAt4, charAt5)) {
                                    int codePoint2 = Character.toCodePoint(charAt4, charAt5);
                                    byteBuffer.put((byte) ((codePoint2 >>> 18) | 240));
                                    byteBuffer.put((byte) (((codePoint2 >>> 12) & 63) | 128));
                                    byteBuffer.put((byte) (((codePoint2 >>> 6) & 63) | 128));
                                    byteBuffer.put((byte) ((codePoint2 & 63) | 128));
                                    i4 = i14;
                                    i4++;
                                } else {
                                    i4 = i14;
                                }
                            }
                            StringBuilder sb3 = new StringBuilder(39);
                            sb3.append("Unpaired surrogate at index ");
                            sb3.append(i4 - 1);
                            throw new IllegalArgumentException(sb3.toString());
                        }
                        byteBuffer.put((byte) i);
                        charAt4 = (charAt4 & '?') | 128;
                    }
                    byteBuffer.put((byte) charAt4);
                    i4++;
                }
            }
        } else {
            throw new ReadOnlyBufferException();
        }
    }

    public static int zzg(int i, int i2) {
        return zzbi(i) + zzbj(i2);
    }

    public static zzio zzj(byte[] bArr) {
        return new zzio(bArr, 0, bArr.length);
    }

    public static zzio zzk(byte[] bArr, int i, int i2) {
        return new zzio(bArr, 0, i2);
    }

    public final void zza(int i, zziw zziw) {
        zzb(i, 2);
        if (zziw.b < 0) {
            zziw.zzuk();
        }
        zzck(zziw.b);
        zziw.zza(this);
    }

    public final void zzb(int i, int i2) {
        zzck((i << 3) | i2);
    }

    public final void zzb(int i, String str) {
        zzb(i, 2);
        try {
            int zzbq = zzbq(str.length());
            if (zzbq == zzbq(str.length() * 3)) {
                int position = this.zzaei.position();
                if (this.zzaei.remaining() >= zzbq) {
                    this.zzaei.position(position + zzbq);
                    zzd(str, this.zzaei);
                    int position2 = this.zzaei.position();
                    this.zzaei.position(position);
                    zzck((position2 - position) - zzbq);
                    this.zzaei.position(position2);
                    return;
                }
                throw new zzin(position + zzbq, this.zzaei.limit());
            }
            zzck(zza(str));
            zzd(str, this.zzaei);
        } catch (BufferOverflowException e) {
            zzin zzin = new zzin(this.zzaei.position(), this.zzaei.limit());
            zzin.initCause(e);
            throw zzin;
        }
    }

    public final void zzb(int i, boolean z) {
        zzb(i, 0);
        byte b = z ? (byte) 1 : 0;
        if (this.zzaei.hasRemaining()) {
            this.zzaei.put(b);
            return;
        }
        throw new zzin(this.zzaei.position(), this.zzaei.limit());
    }

    public final void zzbz(long j) {
        while ((-128 & j) != 0) {
            zzcj((((int) j) & 127) | 128);
            j >>>= 7;
        }
        zzcj((int) j);
    }

    public final void zzc(int i, int i2) {
        zzb(i, 0);
        if (i2 >= 0) {
            zzck(i2);
        } else {
            zzbz((long) i2);
        }
    }

    public final void zzck(int i) {
        while ((i & -128) != 0) {
            zzcj((i & 127) | 128);
            i >>>= 7;
        }
        zzcj(i);
    }

    public final void zze(int i, zzgi zzgi) {
        if (this.zzaol == null) {
            this.zzaol = zzee.zza(this.zzaei);
        } else {
            if (this.zzaom != this.zzaei.position()) {
                this.zzaol.write(this.zzaei.array(), this.zzaom, this.zzaei.position() - this.zzaom);
            }
            zzee zzee = this.zzaol;
            zzee.zza(i, zzgi);
            zzee.flush();
            this.zzaom = this.zzaei.position();
        }
        this.zzaom = this.zzaei.position();
        zzee zzee2 = this.zzaol;
        zzee2.zza(i, zzgi);
        zzee2.flush();
        this.zzaom = this.zzaei.position();
    }

    public final void zzk(byte[] bArr) {
        int length = bArr.length;
        if (this.zzaei.remaining() >= length) {
            this.zzaei.put(bArr, 0, length);
            return;
        }
        throw new zzin(this.zzaei.position(), this.zzaei.limit());
    }
}

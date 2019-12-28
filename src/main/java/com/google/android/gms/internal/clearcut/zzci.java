package com.google.android.gms.internal.clearcut;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzci {
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    static final Charset a = Charset.forName("UTF-8");
    public static final byte[] zzkt;
    private static final ByteBuffer zzku;
    private static final zzbk zzkv;

    static {
        byte[] bArr = new byte[0];
        zzkt = bArr;
        zzku = ByteBuffer.wrap(bArr);
        byte[] bArr2 = zzkt;
        zzkv = zzbk.a(bArr2, 0, bArr2.length, false);
    }

    static int a(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 = (i4 * 31) + bArr[i5];
        }
        return i4;
    }

    static <T> T a(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    static Object a(Object obj, Object obj2) {
        return ((zzdo) obj).zzbc().zza((zzdo) obj2).zzbi();
    }

    static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    static boolean a(zzdo zzdo) {
        return false;
    }

    public static int hashCode(byte[] bArr) {
        int length = bArr.length;
        int a2 = a(length, bArr, 0, length);
        if (a2 == 0) {
            return 1;
        }
        return a2;
    }

    public static int zzc(boolean z) {
        return z ? 1231 : 1237;
    }

    public static boolean zze(byte[] bArr) {
        return zzff.zze(bArr);
    }

    public static String zzf(byte[] bArr) {
        return new String(bArr, a);
    }

    public static int zzl(long j) {
        return (int) (j ^ (j >>> 32));
    }
}

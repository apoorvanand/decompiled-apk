package com.google.android.gms.internal.vision;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzga {
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    static final Charset a = Charset.forName("UTF-8");
    public static final byte[] zzxn;
    private static final ByteBuffer zzxo;
    private static final zzez zzxp = zzez.zzf(zzxn);

    static {
        byte[] bArr = new byte[0];
        zzxn = bArr;
        zzxo = ByteBuffer.wrap(bArr);
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
        return ((zzhf) obj).zzez().zza((zzhf) obj2).zzff();
    }

    static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    static boolean a(zzhf zzhf) {
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

    public static boolean zzi(byte[] bArr) {
        return zziw.zzi(bArr);
    }

    public static int zzj(boolean z) {
        return z ? 1231 : 1237;
    }

    public static String zzj(byte[] bArr) {
        return new String(bArr, a);
    }

    public static int zzo(long j) {
        return (int) (j ^ (j >>> 32));
    }
}

package com.google.android.gms.internal.firebase_ml;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzte {
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    static final Charset a = Charset.forName("UTF-8");
    public static final byte[] zzbmz;
    private static final ByteBuffer zzbna;
    private static final zzsg zzbnb;

    static {
        byte[] bArr = new byte[0];
        zzbmz = bArr;
        zzbna = ByteBuffer.wrap(bArr);
        byte[] bArr2 = zzbmz;
        zzbnb = zzsg.a(bArr2, 0, bArr2.length, false);
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
        return ((zzum) obj).zzpq().zza((zzum) obj2).zzpw();
    }

    static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    static boolean a(zzum zzum) {
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

    public static int zzai(boolean z) {
        return z ? 1231 : 1237;
    }

    public static boolean zzj(byte[] bArr) {
        return zzwc.zzj(bArr);
    }

    public static String zzk(byte[] bArr) {
        return new String(bArr, a);
    }

    public static int zzw(long j) {
        return (int) (j ^ (j >>> 32));
    }
}

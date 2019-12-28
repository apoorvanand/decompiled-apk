package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzez {
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    static final Charset a = Charset.forName("UTF-8");
    public static final byte[] zzair;
    private static final ByteBuffer zzais;
    private static final zzeb zzait;

    static {
        byte[] bArr = new byte[0];
        zzair = bArr;
        zzais = ByteBuffer.wrap(bArr);
        byte[] bArr2 = zzair;
        zzait = zzeb.a(bArr2, 0, bArr2.length, false);
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
        return ((zzgi) obj).zzuo().zza((zzgi) obj2).zzuf();
    }

    static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    static boolean a(zzgi zzgi) {
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

    public static int zzbx(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static boolean zzh(byte[] bArr) {
        return zzhy.zzh(bArr);
    }

    public static String zzi(byte[] bArr) {
        return new String(bArr, a);
    }

    public static int zzs(boolean z) {
        return z ? 1231 : 1237;
    }
}

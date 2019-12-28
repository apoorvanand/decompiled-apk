package com.google.android.gms.internal.firebase_ml;

final class zzrp {
    private static final Class<?> zzbhy = zzch("libcore.io.Memory");
    private static final boolean zzbhz = (zzch("org.robolectric.Robolectric") != null);

    static boolean a() {
        return zzbhy != null && !zzbhz;
    }

    static Class<?> b() {
        return zzbhy;
    }

    private static <T> Class<T> zzch(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}

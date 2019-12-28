package com.google.android.gms.internal.clearcut;

final class zzaw {
    private static final Class<?> zzfb = zze("libcore.io.Memory");
    private static final boolean zzfc = (zze("org.robolectric.Robolectric") != null);

    static boolean a() {
        return zzfb != null && !zzfc;
    }

    static Class<?> b() {
        return zzfb;
    }

    private static <T> Class<T> zze(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}

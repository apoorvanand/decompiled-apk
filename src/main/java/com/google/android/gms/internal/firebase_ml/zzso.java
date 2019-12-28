package com.google.android.gms.internal.firebase_ml;

final class zzso {
    private static final Class<?> zzbiy = zzow();

    private static Class<?> zzow() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzsp zzox() {
        Class<?> cls = zzbiy;
        if (cls != null) {
            try {
                return (zzsp) cls.getDeclaredMethod("getEmptyRegistry", new Class[0]).invoke((Object) null, new Object[0]);
            } catch (Exception unused) {
            }
        }
        return zzsp.a;
    }
}

package com.google.android.gms.internal.firebase_auth;

final class zzhj {
    private static final zzhh<?> zzxl = new zzhg();
    private static final zzhh<?> zzxm = zzhu();

    static zzhh<?> a() {
        return zzxl;
    }

    static zzhh<?> b() {
        zzhh<?> zzhh = zzxm;
        if (zzhh != null) {
            return zzhh;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    private static zzhh<?> zzhu() {
        try {
            return (zzhh) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}

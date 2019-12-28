package com.google.android.gms.internal.firebase_ml;

final class zzuw {
    private static final zzuu zzbpj = zzrb();
    private static final zzuu zzbpk = new zzuv();

    static zzuu a() {
        return zzbpj;
    }

    static zzuu b() {
        return zzbpk;
    }

    private static zzuu zzrb() {
        try {
            return (zzuu) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}

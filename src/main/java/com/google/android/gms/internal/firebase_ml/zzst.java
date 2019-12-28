package com.google.android.gms.internal.firebase_ml;

final class zzst {
    private static final zzsr<?> zzbjd = new zzss();
    private static final zzsr<?> zzbje = zzpa();

    static zzsr<?> a() {
        return zzbjd;
    }

    static zzsr<?> b() {
        zzsr<?> zzsr = zzbje;
        if (zzsr != null) {
            return zzsr;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    private static zzsr<?> zzpa() {
        try {
            return (zzsr) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}

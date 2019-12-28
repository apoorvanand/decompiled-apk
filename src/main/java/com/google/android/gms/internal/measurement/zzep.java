package com.google.android.gms.internal.measurement;

final class zzep {
    private static final zzen<?> zzafb = new zzem();
    private static final zzen<?> zzafc = zztt();

    static zzen<?> a() {
        return zzafb;
    }

    static zzen<?> b() {
        zzen<?> zzen = zzafc;
        if (zzen != null) {
            return zzen;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    private static zzen<?> zztt() {
        try {
            return (zzen) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}

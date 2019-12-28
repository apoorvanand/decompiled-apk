package com.google.android.gms.internal.measurement;

final class zzgs {
    private static final zzgq zzala = zzvx();
    private static final zzgq zzalb = new zzgp();

    static zzgq a() {
        return zzala;
    }

    static zzgq b() {
        return zzalb;
    }

    private static zzgq zzvx() {
        try {
            return (zzgq) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}

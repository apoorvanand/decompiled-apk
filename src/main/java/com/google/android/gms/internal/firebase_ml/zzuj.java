package com.google.android.gms.internal.firebase_ml;

final class zzuj {
    private static final zzuh zzboo = zzqu();
    private static final zzuh zzbop = new zzui();

    static zzuh a() {
        return zzboo;
    }

    static zzuh b() {
        return zzbop;
    }

    private static zzuh zzqu() {
        try {
            return (zzuh) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}

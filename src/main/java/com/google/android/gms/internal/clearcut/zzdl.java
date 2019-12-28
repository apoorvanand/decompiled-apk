package com.google.android.gms.internal.clearcut;

final class zzdl {
    private static final zzdj zzmf = zzce();
    private static final zzdj zzmg = new zzdk();

    static zzdj a() {
        return zzmf;
    }

    static zzdj b() {
        return zzmg;
    }

    private static zzdj zzce() {
        try {
            return (zzdj) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}

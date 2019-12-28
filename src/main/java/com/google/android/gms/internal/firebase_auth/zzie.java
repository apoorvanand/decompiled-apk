package com.google.android.gms.internal.firebase_auth;

public enum zzie {
    VOID(Void.class, Void.class, (Class<?>) null),
    INT(Integer.TYPE, Integer.class, 0),
    LONG(Long.TYPE, Long.class, 0L),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(0.0d)),
    BOOLEAN(Boolean.TYPE, Boolean.class, false),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzgf.class, zzgf.class, zzgf.zzvv),
    ENUM(Integer.TYPE, Integer.class, (Class<?>) null),
    MESSAGE(Object.class, Object.class, (Class<?>) null);
    
    private final Class<?> zzabq;
    private final Class<?> zzabr;
    private final Object zzabs;

    private zzie(Class<?> cls, Class<?> cls2, Object obj) {
        this.zzabq = cls;
        this.zzabr = cls2;
        this.zzabs = obj;
    }

    public final Class<?> zzjb() {
        return this.zzabr;
    }
}

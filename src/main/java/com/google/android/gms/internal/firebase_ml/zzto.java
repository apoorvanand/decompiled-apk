package com.google.android.gms.internal.firebase_ml;

public enum zzto {
    VOID(Void.class, Void.class, (Class<?>) null),
    INT(Integer.TYPE, Integer.class, 0),
    LONG(Long.TYPE, Long.class, 0L),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(0.0d)),
    BOOLEAN(Boolean.TYPE, Boolean.class, false),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzru.class, zzru.class, zzru.zzbig),
    ENUM(Integer.TYPE, Integer.class, (Class<?>) null),
    MESSAGE(Object.class, Object.class, (Class<?>) null);
    
    private final Class<?> zzbnn;
    private final Class<?> zzbno;
    private final Object zzbnp;

    private zzto(Class<?> cls, Class<?> cls2, Object obj) {
        this.zzbnn = cls;
        this.zzbno = cls2;
        this.zzbnp = obj;
    }

    public final Class<?> zzqg() {
        return this.zzbno;
    }
}

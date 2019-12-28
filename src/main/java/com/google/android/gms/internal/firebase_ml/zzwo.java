package com.google.android.gms.internal.firebase_ml;

public enum zzwo {
    INT(0),
    LONG(0L),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(0.0d)),
    BOOLEAN(false),
    STRING(""),
    BYTE_STRING(zzru.zzbig),
    ENUM((String) null),
    MESSAGE((String) null);
    
    private final Object zzbnp;

    private zzwo(Object obj) {
        this.zzbnp = obj;
    }
}

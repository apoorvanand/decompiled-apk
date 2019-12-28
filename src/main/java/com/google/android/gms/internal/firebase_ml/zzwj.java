package com.google.android.gms.internal.firebase_ml;

public enum zzwj {
    DOUBLE(zzwo.DOUBLE, 1),
    FLOAT(zzwo.FLOAT, 5),
    INT64(zzwo.LONG, 0),
    UINT64(zzwo.LONG, 0),
    INT32(zzwo.INT, 0),
    FIXED64(zzwo.LONG, 1),
    FIXED32(zzwo.INT, 5),
    BOOL(zzwo.BOOLEAN, 0),
    STRING(zzwo.STRING, 2),
    GROUP(zzwo.MESSAGE, 3),
    MESSAGE(zzwo.MESSAGE, 2),
    BYTES(zzwo.BYTE_STRING, 2),
    UINT32(zzwo.INT, 0),
    ENUM(zzwo.ENUM, 0),
    SFIXED32(zzwo.INT, 5),
    SFIXED64(zzwo.LONG, 1),
    SINT32(zzwo.INT, 0),
    SINT64(zzwo.LONG, 0);
    
    private final zzwo zzbsb;
    private final int zzbsc;

    private zzwj(zzwo zzwo, int i) {
        this.zzbsb = zzwo;
        this.zzbsc = i;
    }

    public final zzwo zzsd() {
        return this.zzbsb;
    }

    public final int zzse() {
        return this.zzbsc;
    }
}

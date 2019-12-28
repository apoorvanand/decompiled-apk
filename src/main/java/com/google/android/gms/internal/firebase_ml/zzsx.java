package com.google.android.gms.internal.firebase_ml;

import java.lang.reflect.Type;

public enum zzsx {
    DOUBLE(0, zzsz.SCALAR, zzto.DOUBLE),
    FLOAT(1, zzsz.SCALAR, zzto.FLOAT),
    INT64(2, zzsz.SCALAR, zzto.LONG),
    UINT64(3, zzsz.SCALAR, zzto.LONG),
    INT32(4, zzsz.SCALAR, zzto.INT),
    FIXED64(5, zzsz.SCALAR, zzto.LONG),
    FIXED32(6, zzsz.SCALAR, zzto.INT),
    BOOL(7, zzsz.SCALAR, zzto.BOOLEAN),
    STRING(8, zzsz.SCALAR, zzto.STRING),
    MESSAGE(9, zzsz.SCALAR, zzto.MESSAGE),
    BYTES(10, zzsz.SCALAR, zzto.BYTE_STRING),
    UINT32(11, zzsz.SCALAR, zzto.INT),
    ENUM(12, zzsz.SCALAR, zzto.ENUM),
    SFIXED32(13, zzsz.SCALAR, zzto.INT),
    SFIXED64(14, zzsz.SCALAR, zzto.LONG),
    SINT32(15, zzsz.SCALAR, zzto.INT),
    SINT64(16, zzsz.SCALAR, zzto.LONG),
    GROUP(17, zzsz.SCALAR, zzto.MESSAGE),
    DOUBLE_LIST(18, zzsz.VECTOR, zzto.DOUBLE),
    FLOAT_LIST(19, zzsz.VECTOR, zzto.FLOAT),
    INT64_LIST(20, zzsz.VECTOR, zzto.LONG),
    UINT64_LIST(21, zzsz.VECTOR, zzto.LONG),
    INT32_LIST(22, zzsz.VECTOR, zzto.INT),
    FIXED64_LIST(23, zzsz.VECTOR, zzto.LONG),
    FIXED32_LIST(24, zzsz.VECTOR, zzto.INT),
    BOOL_LIST(25, zzsz.VECTOR, zzto.BOOLEAN),
    STRING_LIST(26, zzsz.VECTOR, zzto.STRING),
    MESSAGE_LIST(27, zzsz.VECTOR, zzto.MESSAGE),
    BYTES_LIST(28, zzsz.VECTOR, zzto.BYTE_STRING),
    UINT32_LIST(29, zzsz.VECTOR, zzto.INT),
    ENUM_LIST(30, zzsz.VECTOR, zzto.ENUM),
    SFIXED32_LIST(31, zzsz.VECTOR, zzto.INT),
    SFIXED64_LIST(32, zzsz.VECTOR, zzto.LONG),
    SINT32_LIST(33, zzsz.VECTOR, zzto.INT),
    SINT64_LIST(34, zzsz.VECTOR, zzto.LONG),
    DOUBLE_LIST_PACKED(35, zzsz.PACKED_VECTOR, zzto.DOUBLE),
    FLOAT_LIST_PACKED(36, zzsz.PACKED_VECTOR, zzto.FLOAT),
    INT64_LIST_PACKED(37, zzsz.PACKED_VECTOR, zzto.LONG),
    UINT64_LIST_PACKED(38, zzsz.PACKED_VECTOR, zzto.LONG),
    INT32_LIST_PACKED(39, zzsz.PACKED_VECTOR, zzto.INT),
    FIXED64_LIST_PACKED(40, zzsz.PACKED_VECTOR, zzto.LONG),
    FIXED32_LIST_PACKED(41, zzsz.PACKED_VECTOR, zzto.INT),
    BOOL_LIST_PACKED(42, zzsz.PACKED_VECTOR, zzto.BOOLEAN),
    UINT32_LIST_PACKED(43, zzsz.PACKED_VECTOR, zzto.INT),
    ENUM_LIST_PACKED(44, zzsz.PACKED_VECTOR, zzto.ENUM),
    SFIXED32_LIST_PACKED(45, zzsz.PACKED_VECTOR, zzto.INT),
    SFIXED64_LIST_PACKED(46, zzsz.PACKED_VECTOR, zzto.LONG),
    SINT32_LIST_PACKED(47, zzsz.PACKED_VECTOR, zzto.INT),
    SINT64_LIST_PACKED(48, zzsz.PACKED_VECTOR, zzto.LONG),
    GROUP_LIST(49, zzsz.VECTOR, zzto.MESSAGE),
    MAP(50, zzsz.MAP, zzto.VOID);
    
    private static final zzsx[] zzblo = null;
    private static final Type[] zzblp = null;
    private final int id;
    private final zzto zzblk;
    private final zzsz zzbll;
    private final Class<?> zzblm;
    private final boolean zzbln;

    static {
        int i;
        zzblp = new Type[0];
        zzsx[] values = values();
        zzblo = new zzsx[values.length];
        for (zzsx zzsx : values) {
            zzblo[zzsx.id] = zzsx;
        }
    }

    private zzsx(int i, zzsz zzsz, zzto zzto) {
        Class<?> zzqg;
        this.id = i;
        this.zzbll = zzsz;
        this.zzblk = zzto;
        switch (zzsy.a[zzsz.ordinal()]) {
            case 1:
            case 2:
                zzqg = zzto.zzqg();
                break;
            default:
                zzqg = null;
                break;
        }
        this.zzblm = zzqg;
        boolean z = false;
        if (zzsz == zzsz.SCALAR) {
            switch (zzsy.b[zzto.ordinal()]) {
                case 1:
                case 2:
                case 3:
                    break;
                default:
                    z = true;
                    break;
            }
        }
        this.zzbln = z;
    }

    public final int id() {
        return this.id;
    }
}

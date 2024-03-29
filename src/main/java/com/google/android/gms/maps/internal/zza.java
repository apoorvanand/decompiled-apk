package com.google.android.gms.maps.internal;

public final class zza {
    public static byte zza(Boolean bool) {
        if (bool != null) {
            return bool.booleanValue() ? (byte) 1 : 0;
        }
        return -1;
    }

    public static Boolean zza(byte b) {
        switch (b) {
            case 0:
                return Boolean.FALSE;
            case 1:
                return Boolean.TRUE;
            default:
                return null;
        }
    }
}

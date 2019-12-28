package com.google.android.gms.internal.firebase_ml;

final class zzud implements zzul {
    private zzul[] zzbog;

    zzud(zzul... zzulArr) {
        this.zzbog = zzulArr;
    }

    public final boolean zzh(Class<?> cls) {
        for (zzul zzh : this.zzbog) {
            if (zzh.zzh(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzuk zzi(Class<?> cls) {
        for (zzul zzul : this.zzbog) {
            if (zzul.zzh(cls)) {
                return zzul.zzi(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }
}

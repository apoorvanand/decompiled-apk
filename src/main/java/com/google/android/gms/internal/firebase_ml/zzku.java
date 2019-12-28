package com.google.android.gms.internal.firebase_ml;

import java.io.Serializable;

public abstract class zzku<T> implements Serializable {
    zzku() {
    }

    public static <T> zzku<T> zzhq() {
        return zzke.a;
    }

    public static <T> zzku<T> zzk(T t) {
        return new zzkz(zzky.checkNotNull(t));
    }

    public abstract T get();

    public abstract boolean isPresent();
}

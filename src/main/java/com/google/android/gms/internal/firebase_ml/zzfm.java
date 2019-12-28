package com.google.android.gms.internal.firebase_ml;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

final class zzfm {
    final zzhf a;
    final StringBuilder b;
    final zzhj c;
    final List<Type> d;

    public zzfm(zzfl zzfl, StringBuilder sb) {
        Class<?> cls = zzfl.getClass();
        this.d = Arrays.asList(new Type[]{cls});
        this.c = zzhj.zza(cls, true);
        this.b = sb;
        this.a = new zzhf(zzfl);
    }
}

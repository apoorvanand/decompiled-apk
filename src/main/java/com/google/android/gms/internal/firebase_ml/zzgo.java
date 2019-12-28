package com.google.android.gms.internal.firebase_ml;

import java.util.Collection;
import java.util.HashSet;

public final class zzgo {
    final zzgl a;
    Collection<String> b = new HashSet();

    public zzgo(zzgl zzgl) {
        this.a = (zzgl) zzky.checkNotNull(zzgl);
    }

    public final zzgo zza(Collection<String> collection) {
        this.b = collection;
        return this;
    }

    public final zzgn zzfp() {
        return new zzgn(this);
    }
}

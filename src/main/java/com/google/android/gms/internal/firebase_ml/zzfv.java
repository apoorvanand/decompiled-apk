package com.google.android.gms.internal.firebase_ml;

import java.util.Arrays;
import java.util.logging.Logger;

public abstract class zzfv {
    static final Logger a = Logger.getLogger(zzfv.class.getName());
    private static final String[] zzvj;

    static {
        String[] strArr = {"DELETE", "GET", "POST", "PUT"};
        zzvj = strArr;
        Arrays.sort(strArr);
    }

    /* access modifiers changed from: protected */
    public abstract zzfw a(String str, String str2);

    public final zzfp zza(zzfq zzfq) {
        return new zzfp(this, zzfq);
    }

    public boolean zzag(String str) {
        return Arrays.binarySearch(zzvj, str) >= 0;
    }
}

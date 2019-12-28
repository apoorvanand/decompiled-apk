package com.google.android.gms.internal.firebase_ml;

import java.util.Map;

final class zzui implements zzuh {
    zzui() {
    }

    public final int zzd(int i, Object obj, Object obj2) {
        zzug zzug = (zzug) obj;
        zzue zzue = (zzue) obj2;
        int i2 = 0;
        if (zzug.isEmpty()) {
            return 0;
        }
        for (Map.Entry entry : zzug.entrySet()) {
            i2 += zzue.zzc(i, entry.getKey(), entry.getValue());
        }
        return i2;
    }

    public final Object zzd(Object obj, Object obj2) {
        zzug zzug = (zzug) obj;
        zzug zzug2 = (zzug) obj2;
        if (!zzug2.isEmpty()) {
            if (!zzug.isMutable()) {
                zzug = zzug.zzqq();
            }
            zzug.zza(zzug2);
        }
        return zzug;
    }

    public final Map<?, ?> zzu(Object obj) {
        return (zzug) obj;
    }

    public final Map<?, ?> zzv(Object obj) {
        return (zzug) obj;
    }

    public final boolean zzw(Object obj) {
        return !((zzug) obj).isMutable();
    }

    public final Object zzx(Object obj) {
        ((zzug) obj).zzoh();
        return obj;
    }

    public final Object zzy(Object obj) {
        return zzug.zzqp().zzqq();
    }

    public final zzuf<?, ?> zzz(Object obj) {
        return ((zzue) obj).a();
    }
}

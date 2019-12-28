package com.google.android.gms.internal.firebase_ml;

public final class zzfp {
    private final zzfv zzsy;
    private final zzfq zzva;

    zzfp(zzfv zzfv, zzfq zzfq) {
        this.zzsy = zzfv;
        this.zzva = zzfq;
    }

    public final zzfo zza(String str, zzfg zzfg, zzfh zzfh) {
        zzfo zzfo = new zzfo(this.zzsy, (String) null);
        zzfq zzfq = this.zzva;
        if (zzfq != null) {
            zzfq.zza(zzfo);
        }
        zzfo.zzad(str);
        zzfo.zza(zzfg);
        if (zzfh != null) {
            zzfo.zza(zzfh);
        }
        return zzfo;
    }
}

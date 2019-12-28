package com.google.android.gms.internal.firebase_ml;

final class zzeu implements zzfu {
    private final /* synthetic */ zzfu zztj;
    private final /* synthetic */ zzfo zztk;
    private final /* synthetic */ zzet zztl;

    zzeu(zzet zzet, zzfu zzfu, zzfo zzfo) {
        this.zztl = zzet;
        this.zztj = zzfu;
        this.zztk = zzfo;
    }

    public final void zzb(zzfr zzfr) {
        zzfu zzfu = this.zztj;
        if (zzfu != null) {
            zzfu.zzb(zzfr);
        }
        if (!zzfr.zzeu() && this.zztk.zzes()) {
            throw this.zztl.a(zzfr);
        }
    }
}

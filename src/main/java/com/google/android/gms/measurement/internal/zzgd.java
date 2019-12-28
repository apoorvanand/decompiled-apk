package com.google.android.gms.measurement.internal;

final class zzgd implements Runnable {
    private final /* synthetic */ zzn zzpg;
    private final /* synthetic */ zzfk zzph;

    zzgd(zzfk zzfk, zzn zzn) {
        this.zzph = zzfk;
        this.zzpg = zzn;
    }

    public final void run() {
        this.zzph.zzkz.d();
        this.zzph.zzkz.c(this.zzpg);
    }
}

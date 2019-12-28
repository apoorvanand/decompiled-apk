package com.google.android.gms.measurement.internal;

final class zzfn implements Runnable {
    private final /* synthetic */ zzn zzpg;
    private final /* synthetic */ zzfk zzph;

    zzfn(zzfk zzfk, zzn zzn) {
        this.zzph = zzfk;
        this.zzpg = zzn;
    }

    public final void run() {
        this.zzph.zzkz.d();
        this.zzph.zzkz.a(this.zzpg);
    }
}

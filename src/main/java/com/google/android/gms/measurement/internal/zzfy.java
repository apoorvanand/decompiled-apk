package com.google.android.gms.measurement.internal;

final class zzfy implements Runnable {
    private final /* synthetic */ zzn zzpg;
    private final /* synthetic */ zzfk zzph;
    private final /* synthetic */ zzjn zzpi;

    zzfy(zzfk zzfk, zzjn zzjn, zzn zzn) {
        this.zzph = zzfk;
        this.zzpi = zzjn;
        this.zzpg = zzn;
    }

    public final void run() {
        this.zzph.zzkz.d();
        this.zzph.zzkz.b(this.zzpi, this.zzpg);
    }
}

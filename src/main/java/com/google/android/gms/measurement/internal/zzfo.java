package com.google.android.gms.measurement.internal;

final class zzfo implements Runnable {
    private final /* synthetic */ zzq zzpf;
    private final /* synthetic */ zzfk zzph;

    zzfo(zzfk zzfk, zzq zzq) {
        this.zzph = zzfk;
        this.zzpf = zzq;
    }

    public final void run() {
        this.zzph.zzkz.d();
        this.zzph.zzkz.b(this.zzpf);
    }
}

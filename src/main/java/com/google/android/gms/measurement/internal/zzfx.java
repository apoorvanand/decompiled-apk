package com.google.android.gms.measurement.internal;

final class zzfx implements Runnable {
    private final /* synthetic */ zzai zzdm;
    private final /* synthetic */ zzn zzpg;
    private final /* synthetic */ zzfk zzph;

    zzfx(zzfk zzfk, zzai zzai, zzn zzn) {
        this.zzph = zzfk;
        this.zzdm = zzai;
        this.zzpg = zzn;
    }

    public final void run() {
        zzai a = this.zzph.a(this.zzdm, this.zzpg);
        this.zzph.zzkz.d();
        this.zzph.zzkz.a(a, this.zzpg);
    }
}

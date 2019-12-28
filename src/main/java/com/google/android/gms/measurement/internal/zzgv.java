package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzgv implements Runnable {
    private final /* synthetic */ zzgp zzpt;
    private final /* synthetic */ long zzqf;

    zzgv(zzgp zzgp, long j) {
        this.zzpt = zzgp;
        this.zzqf = j;
    }

    public final void run() {
        zzgp zzgp = this.zzpt;
        long j = this.zzqf;
        zzgp.zzo();
        zzgp.zzm();
        zzgp.j();
        zzgp.zzab().zzgr().zzao("Resetting analytics data (FE)");
        zzgp.zzv().c();
        if (zzgp.zzad().g(zzgp.zzr().c())) {
            zzgp.zzac().zzlo.set(j);
        }
        boolean isEnabled = zzgp.b.isEnabled();
        if (!zzgp.zzad().zzbp()) {
            zzgp.zzac().d(!isEnabled);
        }
        zzgp.zzs().d();
        zzgp.c = !isEnabled;
        this.zzpt.zzs().zza(new AtomicReference());
    }
}

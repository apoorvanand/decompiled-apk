package com.google.firebase.auth.api.internal;

final class zzes implements Runnable {
    private final /* synthetic */ zzev zzqp;
    private final /* synthetic */ zzep zzqq;

    zzes(zzep zzep, zzev zzev) {
        this.zzqq = zzep;
        this.zzqp = zzev;
    }

    public final void run() {
        synchronized (this.zzqq.zzqn.zzpv) {
            if (!this.zzqq.zzqn.zzpv.isEmpty()) {
                this.zzqp.zza(this.zzqq.zzqn.zzpv.get(0), new Object[0]);
            }
        }
    }
}

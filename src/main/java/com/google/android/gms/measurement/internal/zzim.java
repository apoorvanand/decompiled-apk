package com.google.android.gms.measurement.internal;

final class zzim implements Runnable {
    private final /* synthetic */ zzdx zzrr;
    private final /* synthetic */ zzin zzrs;

    zzim(zzin zzin, zzdx zzdx) {
        this.zzrs = zzin;
        this.zzrr = zzdx;
    }

    public final void run() {
        synchronized (this.zzrs) {
            boolean unused = this.zzrs.zzrt = false;
            if (!this.zzrs.a.isConnected()) {
                this.zzrs.a.zzab().zzgs().zzao("Connected to service");
                this.zzrs.a.a(this.zzrr);
            }
        }
    }
}

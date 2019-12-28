package com.google.android.gms.measurement.internal;

final class zzio implements Runnable {
    private final /* synthetic */ zzin zzrs;
    private final /* synthetic */ zzdx zzrv;

    zzio(zzin zzin, zzdx zzdx) {
        this.zzrs = zzin;
        this.zzrv = zzdx;
    }

    public final void run() {
        synchronized (this.zzrs) {
            boolean unused = this.zzrs.zzrt = false;
            if (!this.zzrs.a.isConnected()) {
                this.zzrs.a.zzab().zzgr().zzao("Connected to remote service");
                this.zzrs.a.a(this.zzrv);
            }
        }
    }
}

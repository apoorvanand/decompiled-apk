package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

final class zzhz implements Runnable {
    private final /* synthetic */ zzn zzpg;
    private final /* synthetic */ zzhv zzrd;

    zzhz(zzhv zzhv, zzn zzn) {
        this.zzrd = zzhv;
        this.zzpg = zzn;
    }

    public final void run() {
        zzdx d = this.zzrd.zzrf;
        if (d == null) {
            this.zzrd.zzab().zzgk().zzao("Failed to reset data on the service; null service");
            return;
        }
        try {
            d.zzd(this.zzpg);
        } catch (RemoteException e) {
            this.zzrd.zzab().zzgk().zza("Failed to reset data on the service", e);
        }
        this.zzrd.zzir();
    }
}

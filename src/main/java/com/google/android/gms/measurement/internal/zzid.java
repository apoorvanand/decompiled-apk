package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

final class zzid implements Runnable {
    private final /* synthetic */ zzhr zzqy;
    private final /* synthetic */ zzhv zzrd;

    zzid(zzhv zzhv, zzhr zzhr) {
        this.zzrd = zzhv;
        this.zzqy = zzhr;
    }

    public final void run() {
        long j;
        String str;
        String str2;
        String packageName;
        zzdx d = this.zzrd.zzrf;
        if (d == null) {
            this.zzrd.zzab().zzgk().zzao("Failed to send current screen to service");
            return;
        }
        try {
            if (this.zzqy == null) {
                j = 0;
                str = null;
                str2 = null;
                packageName = this.zzrd.getContext().getPackageName();
            } else {
                j = this.zzqy.zzqw;
                str = this.zzqy.zzqu;
                str2 = this.zzqy.zzqv;
                packageName = this.zzrd.getContext().getPackageName();
            }
            d.zza(j, str, str2, packageName);
            this.zzrd.zzir();
        } catch (RemoteException e) {
            this.zzrd.zzab().zzgk().zza("Failed to send current screen to the service", e);
        }
    }
}

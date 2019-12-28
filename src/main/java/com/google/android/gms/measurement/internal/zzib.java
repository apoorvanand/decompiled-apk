package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzp;

final class zzib implements Runnable {
    private final /* synthetic */ zzp zzdi;
    private final /* synthetic */ zzn zzpg;
    private final /* synthetic */ zzhv zzrd;

    zzib(zzhv zzhv, zzn zzn, zzp zzp) {
        this.zzrd = zzhv;
        this.zzpg = zzn;
        this.zzdi = zzp;
    }

    public final void run() {
        String str = null;
        try {
            zzdx d = this.zzrd.zzrf;
            if (d == null) {
                this.zzrd.zzab().zzgk().zzao("Failed to get app instance id");
            } else {
                str = d.zzc(this.zzpg);
                if (str != null) {
                    this.zzrd.zzq().a(str);
                    this.zzrd.zzac().zzlq.zzau(str);
                }
                this.zzrd.zzir();
            }
        } catch (RemoteException e) {
            this.zzrd.zzab().zzgk().zza("Failed to get app instance id", e);
        } catch (Throwable th) {
            this.zzrd.zzz().zzb(this.zzdi, (String) null);
            throw th;
        }
        this.zzrd.zzz().zzb(this.zzdi, str);
    }
}

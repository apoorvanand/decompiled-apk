package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

final class zzhy implements Runnable {
    private final /* synthetic */ zzn zzpg;
    private final /* synthetic */ zzhv zzrd;
    private final /* synthetic */ AtomicReference zzrl;

    zzhy(zzhv zzhv, AtomicReference atomicReference, zzn zzn) {
        this.zzrd = zzhv;
        this.zzrl = atomicReference;
        this.zzpg = zzn;
    }

    public final void run() {
        AtomicReference atomicReference;
        synchronized (this.zzrl) {
            try {
                zzdx d = this.zzrd.zzrf;
                if (d == null) {
                    this.zzrd.zzab().zzgk().zzao("Failed to get app instance id");
                    this.zzrl.notify();
                    return;
                }
                this.zzrl.set(d.zzc(this.zzpg));
                String str = (String) this.zzrl.get();
                if (str != null) {
                    this.zzrd.zzq().a(str);
                    this.zzrd.zzac().zzlq.zzau(str);
                }
                this.zzrd.zzir();
                atomicReference = this.zzrl;
                atomicReference.notify();
            } catch (RemoteException e) {
                try {
                    this.zzrd.zzab().zzgk().zza("Failed to get app instance id", e);
                    atomicReference = this.zzrl;
                } catch (Throwable th) {
                    this.zzrl.notify();
                    throw th;
                }
            }
        }
    }
}

package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

final class zzhw implements Runnable {
    private final /* synthetic */ boolean zzbi;
    private final /* synthetic */ zzn zzpg;
    private final /* synthetic */ zzhv zzrd;
    private final /* synthetic */ AtomicReference zzrl;

    zzhw(zzhv zzhv, AtomicReference atomicReference, zzn zzn, boolean z) {
        this.zzrd = zzhv;
        this.zzrl = atomicReference;
        this.zzpg = zzn;
        this.zzbi = z;
    }

    public final void run() {
        AtomicReference atomicReference;
        synchronized (this.zzrl) {
            try {
                zzdx d = this.zzrd.zzrf;
                if (d == null) {
                    this.zzrd.zzab().zzgk().zzao("Failed to get user properties");
                    this.zzrl.notify();
                    return;
                }
                this.zzrl.set(d.zza(this.zzpg, this.zzbi));
                this.zzrd.zzir();
                atomicReference = this.zzrl;
                atomicReference.notify();
            } catch (RemoteException e) {
                try {
                    this.zzrd.zzab().zzgk().zza("Failed to get user properties", e);
                    atomicReference = this.zzrl;
                } catch (Throwable th) {
                    this.zzrl.notify();
                    throw th;
                }
            }
        }
    }
}

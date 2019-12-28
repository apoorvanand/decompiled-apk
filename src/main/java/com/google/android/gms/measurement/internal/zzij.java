package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

final class zzij implements Runnable {
    private final /* synthetic */ String zzas;
    private final /* synthetic */ String zzdn;
    private final /* synthetic */ zzn zzpg;
    private final /* synthetic */ zzhv zzrd;
    private final /* synthetic */ AtomicReference zzrl;
    private final /* synthetic */ String zzx;

    zzij(zzhv zzhv, AtomicReference atomicReference, String str, String str2, String str3, zzn zzn) {
        this.zzrd = zzhv;
        this.zzrl = atomicReference;
        this.zzdn = str;
        this.zzx = str2;
        this.zzas = str3;
        this.zzpg = zzn;
    }

    public final void run() {
        AtomicReference atomicReference;
        AtomicReference atomicReference2;
        List<zzq> zzc;
        synchronized (this.zzrl) {
            try {
                zzdx d = this.zzrd.zzrf;
                if (d == null) {
                    this.zzrd.zzab().zzgk().zza("Failed to get conditional properties", zzef.a(this.zzdn), this.zzx, this.zzas);
                    this.zzrl.set(Collections.emptyList());
                    this.zzrl.notify();
                    return;
                }
                if (TextUtils.isEmpty(this.zzdn)) {
                    atomicReference2 = this.zzrl;
                    zzc = d.zza(this.zzx, this.zzas, this.zzpg);
                } else {
                    atomicReference2 = this.zzrl;
                    zzc = d.zzc(this.zzdn, this.zzx, this.zzas);
                }
                atomicReference2.set(zzc);
                this.zzrd.zzir();
                atomicReference = this.zzrl;
                atomicReference.notify();
            } catch (RemoteException e) {
                try {
                    this.zzrd.zzab().zzgk().zza("Failed to get conditional properties", zzef.a(this.zzdn), this.zzx, e);
                    this.zzrl.set(Collections.emptyList());
                    atomicReference = this.zzrl;
                } catch (Throwable th) {
                    this.zzrl.notify();
                    throw th;
                }
            }
        }
    }
}

package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;

final class zziy extends zzaa {
    private final /* synthetic */ zziw zzsi;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zziy(zziw zziw, zzgh zzgh) {
        super(zzgh);
        this.zzsi = zziw;
    }

    @WorkerThread
    public final void run() {
        this.zzsi.zzjc();
    }
}

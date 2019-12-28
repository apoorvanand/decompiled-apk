package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;

final class zziz extends zzaa {
    private final /* synthetic */ zziw zzsi;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zziz(zziw zziw, zzgh zzgh) {
        super(zzgh);
        this.zzsi = zziw;
    }

    @WorkerThread
    public final void run() {
        this.zzsi.d();
    }
}

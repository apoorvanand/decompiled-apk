package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;

final class zzir implements Runnable {
    private final /* synthetic */ zzin zzrs;

    zzir(zzin zzin) {
        this.zzrs = zzin;
    }

    public final void run() {
        zzhv zzhv = this.zzrs.a;
        Context context = this.zzrs.a.getContext();
        this.zzrs.a.zzae();
        zzhv.onServiceDisconnected(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}

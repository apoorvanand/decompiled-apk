package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzz;

final class zzbd extends zzz.zzb {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ zzz.zzc zzbw;
    private final /* synthetic */ Bundle zzbx;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbd(zzz.zzc zzc, Activity activity, Bundle bundle) {
        super(zzz.this);
        this.zzbw = zzc;
        this.val$activity = activity;
        this.zzbx = bundle;
    }

    /* access modifiers changed from: package-private */
    public final void zzf() {
        zzz.this.zzar.onActivityCreated(ObjectWrapper.wrap(this.val$activity), this.zzbx, this.b);
    }
}

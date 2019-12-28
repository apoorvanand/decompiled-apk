package com.google.android.gms.internal.measurement;

import android.app.Activity;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzz;

final class zzbe extends zzz.zzb {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ zzz.zzc zzbw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbe(zzz.zzc zzc, Activity activity) {
        super(zzz.this);
        this.zzbw = zzc;
        this.val$activity = activity;
    }

    /* access modifiers changed from: package-private */
    public final void zzf() {
        zzz.this.zzar.onActivityPaused(ObjectWrapper.wrap(this.val$activity), this.b);
    }
}

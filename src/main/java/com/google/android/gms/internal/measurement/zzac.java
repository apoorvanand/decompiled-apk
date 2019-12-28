package com.google.android.gms.internal.measurement;

import android.app.Activity;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzz;

final class zzac extends zzz.zzb {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ zzz zzaa;
    private final /* synthetic */ String zzax;
    private final /* synthetic */ String zzay;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzac(zzz zzz, Activity activity, String str, String str2) {
        super(zzz);
        this.zzaa = zzz;
        this.val$activity = activity;
        this.zzax = str;
        this.zzay = str2;
    }

    /* access modifiers changed from: package-private */
    public final void zzf() {
        this.zzaa.zzar.setCurrentScreen(ObjectWrapper.wrap(this.val$activity), this.zzax, this.zzay, this.a);
    }
}

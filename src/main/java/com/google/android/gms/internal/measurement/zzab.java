package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzz;

final class zzab extends zzz.zzb {
    private final /* synthetic */ zzz zzaa;
    private final /* synthetic */ String zzau;
    private final /* synthetic */ String zzav;
    private final /* synthetic */ Bundle zzaw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzab(zzz zzz, String str, String str2, Bundle bundle) {
        super(zzz);
        this.zzaa = zzz;
        this.zzau = str;
        this.zzav = str2;
        this.zzaw = bundle;
    }

    /* access modifiers changed from: package-private */
    public final void zzf() {
        this.zzaa.zzar.clearConditionalUserProperty(this.zzau, this.zzav, this.zzaw);
    }
}

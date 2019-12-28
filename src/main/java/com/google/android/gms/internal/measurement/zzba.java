package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzz;

final class zzba extends zzz.zzb {
    private final /* synthetic */ zzz zzaa;
    private final /* synthetic */ Bundle zzbj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzba(zzz zzz, Bundle bundle) {
        super(zzz);
        this.zzaa = zzz;
        this.zzbj = bundle;
    }

    /* access modifiers changed from: package-private */
    public final void zzf() {
        this.zzaa.zzar.setConditionalUserProperty(this.zzbj, this.a);
    }
}

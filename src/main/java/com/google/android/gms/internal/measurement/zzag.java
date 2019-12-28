package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzz;

final class zzag extends zzz.zzb {
    private final /* synthetic */ zzz zzaa;
    private final /* synthetic */ long zzba;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzag(zzz zzz, long j) {
        super(zzz);
        this.zzaa = zzz;
        this.zzba = j;
    }

    /* access modifiers changed from: package-private */
    public final void zzf() {
        this.zzaa.zzar.setSessionTimeoutDuration(this.zzba);
    }
}

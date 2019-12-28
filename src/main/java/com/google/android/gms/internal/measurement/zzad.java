package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzz;

final class zzad extends zzz.zzb {
    private final /* synthetic */ String val$id;
    private final /* synthetic */ zzz zzaa;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzad(zzz zzz, String str) {
        super(zzz);
        this.zzaa = zzz;
        this.val$id = str;
    }

    /* access modifiers changed from: package-private */
    public final void zzf() {
        this.zzaa.zzar.setUserId(this.val$id, this.a);
    }
}

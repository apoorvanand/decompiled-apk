package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.firebase.auth.internal.zzt;

final class zzy implements zzez<Void> {
    private final /* synthetic */ zzdm zzla;

    zzy(zzb zzb, zzdm zzdm) {
        this.zzla = zzdm;
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        this.zzla.zzea();
    }

    public final void zzbv(@Nullable String str) {
        this.zzla.onFailure(zzt.zzdc(str));
    }
}

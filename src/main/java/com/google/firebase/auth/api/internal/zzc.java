package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzeb;
import com.google.android.gms.internal.firebase_auth.zzes;
import com.google.android.gms.internal.firebase_auth.zzfu;
import com.google.firebase.auth.internal.zzt;
import com.google.firebase.auth.zzf;

final class zzc implements zzez<zzfu> {
    private final /* synthetic */ zzdm zzla;
    private final /* synthetic */ zzb zzle;

    zzc(zzb zzb, zzdm zzdm) {
        this.zzle = zzb;
        this.zzla = zzdm;
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        zzfu zzfu = (zzfu) obj;
        if (!zzfu.zzfd()) {
            this.zzle.zza(new zzes(zzfu.zzs(), zzfu.getIdToken(), Long.valueOf(zzfu.zzt()), "Bearer"), (String) null, (String) null, false, (zzf) null, this.zzla, this);
        } else if (this.zzle.zzlc.zzed().booleanValue()) {
            this.zzla.zza(new zzeb(zzfu.zzbb(), zzfu.zzbc(), (zzf) null));
        } else {
            zzbv("REQUIRES_SECOND_FACTOR_AUTH");
        }
    }

    public final void zzbv(@Nullable String str) {
        this.zzla.onFailure(zzt.zzdc(str));
    }
}

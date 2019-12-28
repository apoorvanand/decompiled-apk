package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzeg;
import com.google.android.gms.internal.firebase_auth.zzes;
import com.google.firebase.auth.internal.zzt;
import com.google.firebase.auth.zzf;

final class zze implements zzez<zzeg> {
    private final /* synthetic */ zzdm zzla;
    private final /* synthetic */ zzb zzle;

    zze(zzb zzb, zzdm zzdm) {
        this.zzle = zzb;
        this.zzla = zzdm;
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        zzeg zzeg = (zzeg) obj;
        this.zzle.zza(new zzes(zzeg.zzs(), zzeg.getIdToken(), Long.valueOf(zzeg.zzt()), "Bearer"), (String) null, (String) null, Boolean.valueOf(zzeg.isNewUser()), (zzf) null, this.zzla, this);
    }

    public final void zzbv(@Nullable String str) {
        this.zzla.onFailure(zzt.zzdc(str));
    }
}

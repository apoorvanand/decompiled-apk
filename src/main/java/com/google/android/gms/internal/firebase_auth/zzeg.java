package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.NonNull;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzdv;

public final class zzeg implements zzdv<zzeg, zzp.zze> {
    private String zzib;
    private String zzif;
    private String zzkh;
    private String zzrf;
    private boolean zzrg;
    private long zzrh;

    @NonNull
    public final String getIdToken() {
        return this.zzib;
    }

    public final boolean isNewUser() {
        return this.zzrg;
    }

    public final /* synthetic */ zzdv zza(zzjc zzjc) {
        if (zzjc instanceof zzp.zze) {
            zzp.zze zze = (zzp.zze) zzjc;
            this.zzrf = Strings.emptyToNull(zze.getLocalId());
            this.zzif = Strings.emptyToNull(zze.getEmail());
            this.zzib = Strings.emptyToNull(zze.getIdToken());
            this.zzkh = Strings.emptyToNull(zze.zzs());
            this.zzrg = zze.zzu();
            this.zzrh = zze.zzt();
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of EmailLinkSigninResponse.");
    }

    public final zzjm<zzp.zze> zzee() {
        return zzp.zze.zzm();
    }

    @NonNull
    public final String zzs() {
        return this.zzkh;
    }

    public final long zzt() {
        return this.zzrh;
    }
}

package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Status;

final class zzj extends zzc {
    private final /* synthetic */ zzi zzbk;

    zzj(zzi zzi) {
        this.zzbk = zzi;
    }

    public final void zzc(GoogleSignInAccount googleSignInAccount, Status status) {
        if (googleSignInAccount != null) {
            zzp.zzd(this.zzbk.a).zzc(this.zzbk.b, googleSignInAccount);
        }
        this.zzbk.setResult(new GoogleSignInResult(googleSignInAccount, status));
    }
}

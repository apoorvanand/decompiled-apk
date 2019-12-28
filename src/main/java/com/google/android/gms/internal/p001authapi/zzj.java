package com.google.android.gms.internal.p001authapi;

import android.content.Context;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

/* renamed from: com.google.android.gms.internal.auth-api.zzj  reason: invalid package */
final class zzj extends zzp<CredentialRequestResult> {
    private final /* synthetic */ CredentialRequest zzam;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzj(zzi zzi, GoogleApiClient googleApiClient, CredentialRequest credentialRequest) {
        super(googleApiClient);
        this.zzam = credentialRequest;
    }

    /* access modifiers changed from: protected */
    public final void a(Context context, zzw zzw) {
        zzw.zzc((zzu) new zzk(this), this.zzam);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return zzh.zzd(status);
    }
}

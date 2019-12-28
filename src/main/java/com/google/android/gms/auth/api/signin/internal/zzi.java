package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzi extends zzo<GoogleSignInResult> {
    final /* synthetic */ Context a;
    final /* synthetic */ GoogleSignInOptions b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzi(GoogleApiClient googleApiClient, Context context, GoogleSignInOptions googleSignInOptions) {
        super(googleApiClient);
        this.a = context;
        this.b = googleSignInOptions;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void a(Api.AnyClient anyClient) {
        ((zzu) ((zzg) anyClient).getService()).zzc(new zzj(this), this.b);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new GoogleSignInResult((GoogleSignInAccount) null, status);
    }
}

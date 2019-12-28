package com.google.android.gms.internal.p001authapi;

import android.content.Context;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* renamed from: com.google.android.gms.internal.auth-api.zzp  reason: invalid package */
abstract class zzp<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzr> {
    zzp(GoogleApiClient googleApiClient) {
        super(Auth.CREDENTIALS_API, googleApiClient);
    }

    /* access modifiers changed from: protected */
    public abstract void a(Context context, zzw zzw);

    /* access modifiers changed from: protected */
    public /* synthetic */ void a(Api.AnyClient anyClient) {
        zzr zzr = (zzr) anyClient;
        a(zzr.getContext(), (zzw) zzr.getService());
    }
}

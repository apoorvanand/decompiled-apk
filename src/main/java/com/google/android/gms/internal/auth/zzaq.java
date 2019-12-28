package com.google.android.gms.internal.auth;

import android.content.Context;
import com.google.android.gms.auth.api.AuthProxy;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

abstract class zzaq extends BaseImplementation.ApiMethodImpl<ProxyApi.SpatulaHeaderResult, zzak> {
    public zzaq(GoogleApiClient googleApiClient) {
        super(AuthProxy.API, googleApiClient);
    }

    protected static ProxyApi.SpatulaHeaderResult a(Status status) {
        return new zzax(status);
    }

    /* access modifiers changed from: protected */
    public abstract void a(Context context, zzan zzan);

    /* access modifiers changed from: protected */
    public /* synthetic */ void a(Api.AnyClient anyClient) {
        zzak zzak = (zzak) anyClient;
        a(zzak.getContext(), (zzan) zzak.getService());
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ Result createFailedResult(Status status) {
        return a(status);
    }
}

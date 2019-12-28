package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;
import java.util.Collections;

public final class zaav implements zabd {
    private final zabe zaft;

    public zaav(zabe zabe) {
        this.zaft = zabe;
    }

    public final void begin() {
        for (Api.Client disconnect : this.zaft.a.values()) {
            disconnect.disconnect();
        }
        this.zaft.d.c = Collections.emptySet();
    }

    public final void connect() {
        this.zaft.a();
    }

    public final boolean disconnect() {
        return true;
    }

    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t) {
        this.zaft.d.a.add(t);
        return t;
    }

    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    public final void onConnected(Bundle bundle) {
    }

    public final void onConnectionSuspended(int i) {
    }

    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }
}
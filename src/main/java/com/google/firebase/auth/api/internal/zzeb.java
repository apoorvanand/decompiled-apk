package com.google.firebase.auth.api.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;

final class zzeb extends Api.AbstractClientBuilder<zzdp, zzee> {
    zzeb() {
    }

    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzdo(context, looper, clientSettings, (zzee) obj, connectionCallbacks, onConnectionFailedListener);
    }
}

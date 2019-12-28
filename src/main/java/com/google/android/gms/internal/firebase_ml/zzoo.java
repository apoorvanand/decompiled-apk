package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.WorkerThread;
import com.google.android.gms.auth.api.AuthProxy;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.FirebaseMLException;
import java.util.concurrent.TimeUnit;

public final class zzoo implements zzmx<ResultType, zzom>, zznh {
    private final zzop zzauh;
    private final GoogleApiClient zzaui;
    private final /* synthetic */ zzon zzauj;

    zzoo(zzon zzon, FirebaseApp firebaseApp, boolean z) {
        this.zzauj = zzon;
        if (z) {
            this.zzaui = new GoogleApiClient.Builder(firebaseApp.getApplicationContext()).addApi(AuthProxy.API).build();
            this.zzaui.connect();
        } else {
            this.zzaui = null;
        }
        this.zzauh = zzop.zza(firebaseApp, z, this.zzaui);
    }

    public final void release() {
        GoogleApiClient googleApiClient = this.zzaui;
        if (googleApiClient != null) {
            googleApiClient.disconnect();
        }
    }

    @WorkerThread
    public final /* synthetic */ Object zza(zzna zzna) {
        zzom zzom = (zzom) zzna;
        return this.zzauj.zza(this.zzauh.zza(zzom), zzom.zzaue);
    }

    public final zznh zzkh() {
        return this;
    }

    @WorkerThread
    public final void zzkl() {
        GoogleApiClient googleApiClient = this.zzaui;
        if (googleApiClient != null && googleApiClient.blockingConnect(3, TimeUnit.SECONDS) != ConnectionResult.RESULT_SUCCESS) {
            throw new FirebaseMLException("Failed to contact Google Play services", 14);
        }
    }
}

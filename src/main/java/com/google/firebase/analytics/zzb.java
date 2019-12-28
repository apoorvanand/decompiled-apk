package com.google.firebase.analytics;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

final class zzb implements Callable<String> {
    private final /* synthetic */ FirebaseAnalytics zzaca;

    zzb(FirebaseAnalytics firebaseAnalytics) {
        this.zzaca = firebaseAnalytics;
    }

    public final /* synthetic */ Object call() {
        String zza = this.zzaca.zzi();
        if (zza != null) {
            return zza;
        }
        String appInstanceId = this.zzaca.zzl ? this.zzaca.zzabu.getAppInstanceId() : this.zzaca.zzj.zzq().zzy(120000);
        if (appInstanceId != null) {
            this.zzaca.zzbg(appInstanceId);
            return appInstanceId;
        }
        throw new TimeoutException();
    }
}

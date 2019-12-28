package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import java.util.Set;

public final class zze implements zza {
    private AppMeasurement zzacc;
    /* access modifiers changed from: private */
    public AnalyticsConnector.AnalyticsConnectorListener zzacj;
    private zzg zzacr = new zzg(this);

    public zze(AppMeasurement appMeasurement, AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener) {
        this.zzacj = analyticsConnectorListener;
        this.zzacc = appMeasurement;
        this.zzacc.registerOnMeasurementEventListener(this.zzacr);
    }

    public final void registerEventNames(Set<String> set) {
    }

    public final void unregisterEventNames() {
    }

    public final AnalyticsConnector.AnalyticsConnectorListener zzrr() {
        return this.zzacj;
    }
}

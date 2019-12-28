package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import java.util.HashSet;
import java.util.Set;

public final class zzc implements zza {
    private AppMeasurement zzacc;
    Set<String> zzaci;
    /* access modifiers changed from: private */
    public AnalyticsConnector.AnalyticsConnectorListener zzacj;
    private zzf zzack = new zzf(this);

    public zzc(AppMeasurement appMeasurement, AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener) {
        this.zzacj = analyticsConnectorListener;
        this.zzacc = appMeasurement;
        this.zzacc.registerOnMeasurementEventListener(this.zzack);
        this.zzaci = new HashSet();
    }

    public final void registerEventNames(Set<String> set) {
        this.zzaci.clear();
        Set<String> set2 = this.zzaci;
        HashSet hashSet = new HashSet();
        for (String next : set) {
            if (hashSet.size() >= 50) {
                break;
            } else if (zzd.zzdm(next) && zzd.zzdl(next)) {
                hashSet.add(zzd.zzdo(next));
            }
        }
        set2.addAll(hashSet);
    }

    public final void unregisterEventNames() {
        this.zzaci.clear();
    }

    public final AnalyticsConnector.AnalyticsConnectorListener zzrr() {
        return this.zzacj;
    }
}

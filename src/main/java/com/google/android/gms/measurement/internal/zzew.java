package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zzf;
import com.google.firebase.analytics.FirebaseAnalytics;

final class zzew implements Runnable {
    private final /* synthetic */ zzf zzmq;
    private final /* synthetic */ ServiceConnection zzmr;
    private final /* synthetic */ zzex zzms;

    zzew(zzex zzex, zzf zzf, ServiceConnection serviceConnection) {
        this.zzms = zzex;
        this.zzmq = zzf;
        this.zzmr = serviceConnection;
    }

    public final void run() {
        zzeh zzgk;
        String str;
        zzeu zzeu = this.zzms.a;
        String a = this.zzms.packageName;
        zzf zzf = this.zzmq;
        ServiceConnection serviceConnection = this.zzmr;
        Bundle a2 = zzeu.a(a, zzf);
        zzeu.a.zzaa().zzo();
        if (a2 != null) {
            long j = a2.getLong("install_begin_timestamp_seconds", 0) * 1000;
            if (j == 0) {
                zzgk = zzeu.a.zzab().zzgk();
                str = "Service response is missing Install Referrer install timestamp";
            } else {
                String string = a2.getString("install_referrer");
                if (string == null || string.isEmpty()) {
                    zzgk = zzeu.a.zzab().zzgk();
                    str = "No referrer defined in install referrer response";
                } else {
                    zzeu.a.zzab().zzgs().zza("InstallReferrer API result", string);
                    zzjs zzz = zzeu.a.zzz();
                    String valueOf = String.valueOf(string);
                    Bundle a3 = zzz.a(Uri.parse(valueOf.length() != 0 ? "?".concat(valueOf) : new String("?")));
                    if (a3 == null) {
                        zzgk = zzeu.a.zzab().zzgk();
                        str = "No campaign params defined in install referrer result";
                    } else {
                        String string2 = a3.getString(FirebaseAnalytics.Param.MEDIUM);
                        if (string2 != null && !"(not set)".equalsIgnoreCase(string2) && !"organic".equalsIgnoreCase(string2)) {
                            long j2 = a2.getLong("referrer_click_timestamp_seconds", 0) * 1000;
                            if (j2 == 0) {
                                zzgk = zzeu.a.zzab().zzgk();
                                str = "Install Referrer is missing click timestamp for ad campaign";
                            } else {
                                a3.putLong("click_timestamp", j2);
                            }
                        }
                        if (j == zzeu.a.zzac().zzlp.get()) {
                            zzeu.a.zzae();
                            zzgk = zzeu.a.zzab().zzgs();
                            str = "Campaign has already been logged";
                        } else {
                            zzeu.a.zzac().zzlp.set(j);
                            zzeu.a.zzae();
                            zzeu.a.zzab().zzgs().zza("Logging Install Referrer campaign from sdk with ", "referrer API");
                            a3.putString("_cis", "referrer API");
                            zzeu.a.zzq().logEvent("auto", "_cmp", a3);
                        }
                    }
                }
            }
            zzgk.zzao(str);
        }
        if (serviceConnection != null) {
            ConnectionTracker.getInstance().unbindService(zzeu.a.getContext(), serviceConnection);
        }
    }
}

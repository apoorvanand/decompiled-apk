package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzf;
import java.util.List;

public final class zzeu {
    final zzfj a;

    zzeu(zzfj zzfj) {
        this.a = zzfj;
    }

    @VisibleForTesting
    private final boolean zzhn() {
        try {
            PackageManagerWrapper packageManager = Wrappers.packageManager(this.a.getContext());
            if (packageManager != null) {
                return packageManager.getPackageInfo("com.android.vending", 128).versionCode >= 80837300;
            }
            this.a.zzab().zzgq().zzao("Failed to retrieve Package Manager to check Play Store compatibility");
            return false;
        } catch (Exception e) {
            this.a.zzab().zzgq().zza("Failed to retrieve Play Store version", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @Nullable
    @VisibleForTesting
    public final Bundle a(String str, zzf zzf) {
        this.a.zzaa().zzo();
        if (zzf == null) {
            this.a.zzab().zzgn().zzao("Attempting to use Install Referrer Service while it is not initialized");
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("package_name", str);
        try {
            Bundle zza = zzf.zza(bundle);
            if (zza != null) {
                return zza;
            }
            this.a.zzab().zzgk().zzao("Install Referrer Service returned a null response");
            return null;
        } catch (Exception e) {
            this.a.zzab().zzgk().zza("Exception occurred while retrieving the Install Referrer", e.getMessage());
            return null;
        }
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void a(String str) {
        if (str == null || str.isEmpty()) {
            this.a.zzab().zzgq().zzao("Install Referrer Reporter was called with invalid app package name");
            return;
        }
        this.a.zzaa().zzo();
        if (!zzhn()) {
            this.a.zzab().zzgq().zzao("Install Referrer Reporter is not available");
            return;
        }
        this.a.zzab().zzgq().zzao("Install Referrer Reporter is initializing");
        zzex zzex = new zzex(this, str);
        this.a.zzaa().zzo();
        Intent intent = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");
        intent.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));
        PackageManager packageManager = this.a.getContext().getPackageManager();
        if (packageManager == null) {
            this.a.zzab().zzgn().zzao("Failed to obtain Package Manager to verify binding conditions");
            return;
        }
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            this.a.zzab().zzgq().zzao("Play Service for fetching Install Referrer is unavailable on device");
            return;
        }
        ResolveInfo resolveInfo = queryIntentServices.get(0);
        if (resolveInfo.serviceInfo != null) {
            String str2 = resolveInfo.serviceInfo.packageName;
            if (resolveInfo.serviceInfo.name == null || !"com.android.vending".equals(str2) || !zzhn()) {
                this.a.zzab().zzgq().zzao("Play Store missing or incompatible. Version 8.3.73 or later required");
                return;
            }
            try {
                this.a.zzab().zzgq().zza("Install Referrer Service is", ConnectionTracker.getInstance().bindService(this.a.getContext(), new Intent(intent), zzex, 1) ? "available" : "not available");
            } catch (Exception e) {
                this.a.zzab().zzgk().zza("Exception occurred while binding to Install Referrer Service", e.getMessage());
            }
        }
    }
}
package com.google.android.gms.internal.firebase_ml;

import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.internal.GmsLogger;

final class zznj implements BackgroundDetector.BackgroundStateChangeListener {
    private final /* synthetic */ zzni zzapi;

    zznj(zzni zzni) {
        this.zzapi = zzni;
    }

    public final void onBackgroundStateChanged(boolean z) {
        GmsLogger a = zzni.zzaoj;
        StringBuilder sb = new StringBuilder(34);
        sb.append("Background state changed to: ");
        sb.append(z);
        a.v("ModelResourceManager", sb.toString());
        this.zzapi.zzapd.set(z ? 2000 : 300000);
        this.zzapi.zzkm();
    }
}

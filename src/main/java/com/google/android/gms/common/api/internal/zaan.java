package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import java.util.ArrayList;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

final class zaan extends zaau {
    final /* synthetic */ zaak a;
    private final Map<Api.Client, zaam> zagl;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zaan(zaak zaak, Map<Api.Client, zaam> map) {
        super(zaak, (zaal) null);
        this.a = zaak;
        this.zagl = map;
    }

    @GuardedBy("mLock")
    @WorkerThread
    public final void zaan() {
        GoogleApiAvailabilityCache googleApiAvailabilityCache = new GoogleApiAvailabilityCache(this.a.zaey);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Api.Client next : this.zagl.keySet()) {
            if (!next.requiresGooglePlayServices() || this.zagl.get(next).zaec) {
                arrayList2.add(next);
            } else {
                arrayList.add(next);
            }
        }
        int i = -1;
        int i2 = 0;
        if (!arrayList.isEmpty()) {
            ArrayList arrayList3 = arrayList;
            int size = arrayList3.size();
            while (i2 < size) {
                Object obj = arrayList3.get(i2);
                i2++;
                i = googleApiAvailabilityCache.getClientAvailability(this.a.mContext, (Api.Client) obj);
                if (i != 0) {
                    break;
                }
            }
        } else {
            ArrayList arrayList4 = arrayList2;
            int size2 = arrayList4.size();
            while (i2 < size2) {
                Object obj2 = arrayList4.get(i2);
                i2++;
                i = googleApiAvailabilityCache.getClientAvailability(this.a.mContext, (Api.Client) obj2);
                if (i == 0) {
                    break;
                }
            }
        }
        if (i != 0) {
            this.a.zaft.a((zabf) new zaao(this, this.a, new ConnectionResult(i, (PendingIntent) null)));
            return;
        }
        if (this.a.zagd && this.a.zagb != null) {
            this.a.zagb.connect();
        }
        for (Api.Client next2 : this.zagl.keySet()) {
            BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks = this.zagl.get(next2);
            if (!next2.requiresGooglePlayServices() || googleApiAvailabilityCache.getClientAvailability(this.a.mContext, next2) == 0) {
                next2.connect(connectionProgressReportCallbacks);
            } else {
                this.a.zaft.a((zabf) new zaap(this, this.a, connectionProgressReportCallbacks));
            }
        }
    }
}

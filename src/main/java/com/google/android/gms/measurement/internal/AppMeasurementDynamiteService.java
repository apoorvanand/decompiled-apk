package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzn;
import com.google.android.gms.internal.measurement.zzp;
import com.google.android.gms.internal.measurement.zzq;
import com.google.android.gms.internal.measurement.zzv;
import com.google.android.gms.internal.measurement.zzx;
import java.util.Map;

@DynamiteApi
public class AppMeasurementDynamiteService extends zzn {
    @VisibleForTesting
    zzfj a = null;
    private Map<Integer, zzgn> zzdk = new ArrayMap();

    class zza implements zzgn {
        private zzq zzdo;

        zza(zzq zzq) {
            this.zzdo = zzq;
        }

        public final void onEvent(String str, String str2, Bundle bundle, long j) {
            try {
                this.zzdo.onEvent(str, str2, bundle, j);
            } catch (RemoteException e) {
                AppMeasurementDynamiteService.this.a.zzab().zzgn().zza("Event listener threw exception", e);
            }
        }
    }

    class zzb implements zzgk {
        private zzq zzdo;

        zzb(zzq zzq) {
            this.zzdo = zzq;
        }

        public final void interceptEvent(String str, String str2, Bundle bundle, long j) {
            try {
                this.zzdo.onEvent(str, str2, bundle, j);
            } catch (RemoteException e) {
                AppMeasurementDynamiteService.this.a.zzab().zzgn().zza("Event interceptor threw exception", e);
            }
        }
    }

    private final void zza(zzp zzp, String str) {
        this.a.zzz().zzb(zzp, str);
    }

    private final void zzbi() {
        if (this.a == null) {
            throw new IllegalStateException("Attempting to perform action before initialize.");
        }
    }

    public void beginAdUnitExposure(String str, long j) {
        zzbi();
        this.a.zzp().beginAdUnitExposure(str, j);
    }

    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        zzbi();
        this.a.zzq().clearConditionalUserProperty(str, str2, bundle);
    }

    public void endAdUnitExposure(String str, long j) {
        zzbi();
        this.a.zzp().endAdUnitExposure(str, j);
    }

    public void generateEventId(zzp zzp) {
        zzbi();
        this.a.zzz().zza(zzp, this.a.zzz().zzjv());
    }

    public void getAppInstanceId(zzp zzp) {
        zzbi();
        this.a.zzaa().zza((Runnable) new zzh(this, zzp));
    }

    public void getCachedAppInstanceId(zzp zzp) {
        zzbi();
        zza(zzp, this.a.zzq().zzi());
    }

    public void getConditionalUserProperties(String str, String str2, zzp zzp) {
        zzbi();
        this.a.zzaa().zza((Runnable) new zzl(this, zzp, str, str2));
    }

    public void getCurrentScreenClass(zzp zzp) {
        zzbi();
        zza(zzp, this.a.zzq().getCurrentScreenClass());
    }

    public void getCurrentScreenName(zzp zzp) {
        zzbi();
        zza(zzp, this.a.zzq().getCurrentScreenName());
    }

    public void getDeepLink(zzp zzp) {
        zzbi();
        zzgp zzq = this.a.zzq();
        zzq.zzo();
        if (!zzq.zzad().zzd((String) null, zzak.zzjc)) {
            zzq.zzz().zzb(zzp, "");
        } else if (zzq.zzac().zzme.get() > 0) {
            zzq.zzz().zzb(zzp, "");
        } else {
            zzq.zzac().zzme.set(zzq.zzx().currentTimeMillis());
            zzq.b.zza(zzp);
        }
    }

    public void getGmpAppId(zzp zzp) {
        zzbi();
        zza(zzp, this.a.zzq().getGmpAppId());
    }

    public void getMaxUserProperties(String str, zzp zzp) {
        zzbi();
        this.a.zzq();
        Preconditions.checkNotEmpty(str);
        this.a.zzz().zza(zzp, 25);
    }

    public void getTestFlag(zzp zzp, int i) {
        zzbi();
        switch (i) {
            case 0:
                this.a.zzz().zzb(zzp, this.a.zzq().zzih());
                return;
            case 1:
                this.a.zzz().zza(zzp, this.a.zzq().zzii().longValue());
                return;
            case 2:
                zzjs zzz = this.a.zzz();
                double doubleValue = this.a.zzq().zzik().doubleValue();
                Bundle bundle = new Bundle();
                bundle.putDouble("r", doubleValue);
                try {
                    zzp.zzb(bundle);
                    return;
                } catch (RemoteException e) {
                    zzz.b.zzab().zzgn().zza("Error returning double value to wrapper", e);
                    return;
                }
            case 3:
                this.a.zzz().zza(zzp, this.a.zzq().zzij().intValue());
                return;
            case 4:
                this.a.zzz().zza(zzp, this.a.zzq().zzig().booleanValue());
                return;
            default:
                return;
        }
    }

    public void getUserProperties(String str, String str2, boolean z, zzp zzp) {
        zzbi();
        this.a.zzaa().zza((Runnable) new zzi(this, zzp, str, str2, z));
    }

    public void initForTests(Map map) {
        zzbi();
    }

    public void initialize(IObjectWrapper iObjectWrapper, zzx zzx, long j) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        zzfj zzfj = this.a;
        if (zzfj == null) {
            this.a = zzfj.zza(context, zzx);
        } else {
            zzfj.zzab().zzgn().zzao("Attempting to initialize multiple times");
        }
    }

    public void isDataCollectionEnabled(zzp zzp) {
        zzbi();
        this.a.zzaa().zza((Runnable) new zzk(this, zzp));
    }

    public void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) {
        zzbi();
        this.a.zzq().logEvent(str, str2, bundle, z, z2, j);
    }

    public void logEventAndBundle(String str, String str2, Bundle bundle, zzp zzp, long j) {
        zzbi();
        Preconditions.checkNotEmpty(str2);
        (bundle != null ? new Bundle(bundle) : new Bundle()).putString("_o", "app");
        this.a.zzaa().zza((Runnable) new zzj(this, zzp, new zzai(str2, new zzah(bundle), "app", j), str));
    }

    public void logHealthData(int i, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        zzbi();
        Object obj = null;
        Object unwrap = iObjectWrapper == null ? null : ObjectWrapper.unwrap(iObjectWrapper);
        Object unwrap2 = iObjectWrapper2 == null ? null : ObjectWrapper.unwrap(iObjectWrapper2);
        if (iObjectWrapper3 != null) {
            obj = ObjectWrapper.unwrap(iObjectWrapper3);
        }
        this.a.zzab().a(i, true, false, str, unwrap, unwrap2, obj);
    }

    public void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j) {
        zzbi();
        zzhj zzhj = this.a.zzq().a;
        if (zzhj != null) {
            this.a.zzq().zzif();
            zzhj.onActivityCreated((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
    }

    public void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j) {
        zzbi();
        zzhj zzhj = this.a.zzq().a;
        if (zzhj != null) {
            this.a.zzq().zzif();
            zzhj.onActivityDestroyed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityPaused(IObjectWrapper iObjectWrapper, long j) {
        zzbi();
        zzhj zzhj = this.a.zzq().a;
        if (zzhj != null) {
            this.a.zzq().zzif();
            zzhj.onActivityPaused((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityResumed(IObjectWrapper iObjectWrapper, long j) {
        zzbi();
        zzhj zzhj = this.a.zzq().a;
        if (zzhj != null) {
            this.a.zzq().zzif();
            zzhj.onActivityResumed((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzp zzp, long j) {
        zzbi();
        zzhj zzhj = this.a.zzq().a;
        Bundle bundle = new Bundle();
        if (zzhj != null) {
            this.a.zzq().zzif();
            zzhj.onActivitySaveInstanceState((Activity) ObjectWrapper.unwrap(iObjectWrapper), bundle);
        }
        try {
            zzp.zzb(bundle);
        } catch (RemoteException e) {
            this.a.zzab().zzgn().zza("Error returning bundle value to wrapper", e);
        }
    }

    public void onActivityStarted(IObjectWrapper iObjectWrapper, long j) {
        zzbi();
        zzhj zzhj = this.a.zzq().a;
        if (zzhj != null) {
            this.a.zzq().zzif();
            zzhj.onActivityStarted((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void onActivityStopped(IObjectWrapper iObjectWrapper, long j) {
        zzbi();
        zzhj zzhj = this.a.zzq().a;
        if (zzhj != null) {
            this.a.zzq().zzif();
            zzhj.onActivityStopped((Activity) ObjectWrapper.unwrap(iObjectWrapper));
        }
    }

    public void performAction(Bundle bundle, zzp zzp, long j) {
        zzbi();
        zzp.zzb((Bundle) null);
    }

    public void registerOnMeasurementEventListener(zzq zzq) {
        zzbi();
        zzgn zzgn = this.zzdk.get(Integer.valueOf(zzq.id()));
        if (zzgn == null) {
            zzgn = new zza(zzq);
            this.zzdk.put(Integer.valueOf(zzq.id()), zzgn);
        }
        this.a.zzq().zza(zzgn);
    }

    public void resetAnalyticsData(long j) {
        zzbi();
        this.a.zzq().resetAnalyticsData(j);
    }

    public void setConditionalUserProperty(Bundle bundle, long j) {
        zzbi();
        if (bundle == null) {
            this.a.zzab().zzgk().zzao("Conditional user property must not be null");
        } else {
            this.a.zzq().setConditionalUserProperty(bundle, j);
        }
    }

    public void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j) {
        zzbi();
        this.a.zzt().setCurrentScreen((Activity) ObjectWrapper.unwrap(iObjectWrapper), str, str2);
    }

    public void setDataCollectionEnabled(boolean z) {
        zzbi();
        this.a.zzq().zza(z);
    }

    public void setEventInterceptor(zzq zzq) {
        zzbi();
        zzgp zzq2 = this.a.zzq();
        zzb zzb2 = new zzb(zzq);
        zzq2.zzm();
        zzq2.j();
        zzq2.zzaa().zza((Runnable) new zzgu(zzq2, zzb2));
    }

    public void setInstanceIdProvider(zzv zzv) {
        zzbi();
    }

    public void setMeasurementEnabled(boolean z, long j) {
        zzbi();
        this.a.zzq().setMeasurementEnabled(z);
    }

    public void setMinimumSessionDuration(long j) {
        zzbi();
        this.a.zzq().setMinimumSessionDuration(j);
    }

    public void setSessionTimeoutDuration(long j) {
        zzbi();
        this.a.zzq().setSessionTimeoutDuration(j);
    }

    public void setUserId(String str, long j) {
        zzbi();
        this.a.zzq().zza((String) null, "_id", str, true, j);
    }

    public void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z, long j) {
        zzbi();
        this.a.zzq().zza(str, str2, ObjectWrapper.unwrap(iObjectWrapper), z, j);
    }

    public void unregisterOnMeasurementEventListener(zzq zzq) {
        zzbi();
        zzgn remove = this.zzdk.remove(Integer.valueOf(zzq.id()));
        if (remove == null) {
            remove = new zza(zzq);
        }
        this.a.zzq().zzb(remove);
    }
}

package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

public final class zzm extends zzb implements zzk {
    zzm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    public final void beginAdUnitExposure(String str, long j) {
        Parcel a = a();
        a.writeString(str);
        a.writeLong(j);
        b(23, a);
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        Parcel a = a();
        a.writeString(str);
        a.writeString(str2);
        zzd.zza(a, (Parcelable) bundle);
        b(9, a);
    }

    public final void endAdUnitExposure(String str, long j) {
        Parcel a = a();
        a.writeString(str);
        a.writeLong(j);
        b(24, a);
    }

    public final void generateEventId(zzp zzp) {
        Parcel a = a();
        zzd.zza(a, (IInterface) zzp);
        b(22, a);
    }

    public final void getAppInstanceId(zzp zzp) {
        Parcel a = a();
        zzd.zza(a, (IInterface) zzp);
        b(20, a);
    }

    public final void getCachedAppInstanceId(zzp zzp) {
        Parcel a = a();
        zzd.zza(a, (IInterface) zzp);
        b(19, a);
    }

    public final void getConditionalUserProperties(String str, String str2, zzp zzp) {
        Parcel a = a();
        a.writeString(str);
        a.writeString(str2);
        zzd.zza(a, (IInterface) zzp);
        b(10, a);
    }

    public final void getCurrentScreenClass(zzp zzp) {
        Parcel a = a();
        zzd.zza(a, (IInterface) zzp);
        b(17, a);
    }

    public final void getCurrentScreenName(zzp zzp) {
        Parcel a = a();
        zzd.zza(a, (IInterface) zzp);
        b(16, a);
    }

    public final void getDeepLink(zzp zzp) {
        Parcel a = a();
        zzd.zza(a, (IInterface) zzp);
        b(41, a);
    }

    public final void getGmpAppId(zzp zzp) {
        Parcel a = a();
        zzd.zza(a, (IInterface) zzp);
        b(21, a);
    }

    public final void getMaxUserProperties(String str, zzp zzp) {
        Parcel a = a();
        a.writeString(str);
        zzd.zza(a, (IInterface) zzp);
        b(6, a);
    }

    public final void getTestFlag(zzp zzp, int i) {
        Parcel a = a();
        zzd.zza(a, (IInterface) zzp);
        a.writeInt(i);
        b(38, a);
    }

    public final void getUserProperties(String str, String str2, boolean z, zzp zzp) {
        Parcel a = a();
        a.writeString(str);
        a.writeString(str2);
        zzd.writeBoolean(a, z);
        zzd.zza(a, (IInterface) zzp);
        b(5, a);
    }

    public final void initForTests(Map map) {
        Parcel a = a();
        a.writeMap(map);
        b(37, a);
    }

    public final void initialize(IObjectWrapper iObjectWrapper, zzx zzx, long j) {
        Parcel a = a();
        zzd.zza(a, (IInterface) iObjectWrapper);
        zzd.zza(a, (Parcelable) zzx);
        a.writeLong(j);
        b(1, a);
    }

    public final void isDataCollectionEnabled(zzp zzp) {
        Parcel a = a();
        zzd.zza(a, (IInterface) zzp);
        b(40, a);
    }

    public final void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) {
        Parcel a = a();
        a.writeString(str);
        a.writeString(str2);
        zzd.zza(a, (Parcelable) bundle);
        zzd.writeBoolean(a, z);
        zzd.writeBoolean(a, z2);
        a.writeLong(j);
        b(2, a);
    }

    public final void logEventAndBundle(String str, String str2, Bundle bundle, zzp zzp, long j) {
        Parcel a = a();
        a.writeString(str);
        a.writeString(str2);
        zzd.zza(a, (Parcelable) bundle);
        zzd.zza(a, (IInterface) zzp);
        a.writeLong(j);
        b(3, a);
    }

    public final void logHealthData(int i, String str, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        Parcel a = a();
        a.writeInt(i);
        a.writeString(str);
        zzd.zza(a, (IInterface) iObjectWrapper);
        zzd.zza(a, (IInterface) iObjectWrapper2);
        zzd.zza(a, (IInterface) iObjectWrapper3);
        b(33, a);
    }

    public final void onActivityCreated(IObjectWrapper iObjectWrapper, Bundle bundle, long j) {
        Parcel a = a();
        zzd.zza(a, (IInterface) iObjectWrapper);
        zzd.zza(a, (Parcelable) bundle);
        a.writeLong(j);
        b(27, a);
    }

    public final void onActivityDestroyed(IObjectWrapper iObjectWrapper, long j) {
        Parcel a = a();
        zzd.zza(a, (IInterface) iObjectWrapper);
        a.writeLong(j);
        b(28, a);
    }

    public final void onActivityPaused(IObjectWrapper iObjectWrapper, long j) {
        Parcel a = a();
        zzd.zza(a, (IInterface) iObjectWrapper);
        a.writeLong(j);
        b(29, a);
    }

    public final void onActivityResumed(IObjectWrapper iObjectWrapper, long j) {
        Parcel a = a();
        zzd.zza(a, (IInterface) iObjectWrapper);
        a.writeLong(j);
        b(30, a);
    }

    public final void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper, zzp zzp, long j) {
        Parcel a = a();
        zzd.zza(a, (IInterface) iObjectWrapper);
        zzd.zza(a, (IInterface) zzp);
        a.writeLong(j);
        b(31, a);
    }

    public final void onActivityStarted(IObjectWrapper iObjectWrapper, long j) {
        Parcel a = a();
        zzd.zza(a, (IInterface) iObjectWrapper);
        a.writeLong(j);
        b(25, a);
    }

    public final void onActivityStopped(IObjectWrapper iObjectWrapper, long j) {
        Parcel a = a();
        zzd.zza(a, (IInterface) iObjectWrapper);
        a.writeLong(j);
        b(26, a);
    }

    public final void performAction(Bundle bundle, zzp zzp, long j) {
        Parcel a = a();
        zzd.zza(a, (Parcelable) bundle);
        zzd.zza(a, (IInterface) zzp);
        a.writeLong(j);
        b(32, a);
    }

    public final void registerOnMeasurementEventListener(zzq zzq) {
        Parcel a = a();
        zzd.zza(a, (IInterface) zzq);
        b(35, a);
    }

    public final void resetAnalyticsData(long j) {
        Parcel a = a();
        a.writeLong(j);
        b(12, a);
    }

    public final void setConditionalUserProperty(Bundle bundle, long j) {
        Parcel a = a();
        zzd.zza(a, (Parcelable) bundle);
        a.writeLong(j);
        b(8, a);
    }

    public final void setCurrentScreen(IObjectWrapper iObjectWrapper, String str, String str2, long j) {
        Parcel a = a();
        zzd.zza(a, (IInterface) iObjectWrapper);
        a.writeString(str);
        a.writeString(str2);
        a.writeLong(j);
        b(15, a);
    }

    public final void setDataCollectionEnabled(boolean z) {
        Parcel a = a();
        zzd.writeBoolean(a, z);
        b(39, a);
    }

    public final void setEventInterceptor(zzq zzq) {
        Parcel a = a();
        zzd.zza(a, (IInterface) zzq);
        b(34, a);
    }

    public final void setInstanceIdProvider(zzv zzv) {
        Parcel a = a();
        zzd.zza(a, (IInterface) zzv);
        b(18, a);
    }

    public final void setMeasurementEnabled(boolean z, long j) {
        Parcel a = a();
        zzd.writeBoolean(a, z);
        a.writeLong(j);
        b(11, a);
    }

    public final void setMinimumSessionDuration(long j) {
        Parcel a = a();
        a.writeLong(j);
        b(13, a);
    }

    public final void setSessionTimeoutDuration(long j) {
        Parcel a = a();
        a.writeLong(j);
        b(14, a);
    }

    public final void setUserId(String str, long j) {
        Parcel a = a();
        a.writeString(str);
        a.writeLong(j);
        b(7, a);
    }

    public final void setUserProperty(String str, String str2, IObjectWrapper iObjectWrapper, boolean z, long j) {
        Parcel a = a();
        a.writeString(str);
        a.writeString(str2);
        zzd.zza(a, (IInterface) iObjectWrapper);
        zzd.writeBoolean(a, z);
        a.writeLong(j);
        b(4, a);
    }

    public final void unregisterOnMeasurementEventListener(zzq zzq) {
        Parcel a = a();
        zzd.zza(a, (IInterface) zzq);
        b(36, a);
    }
}

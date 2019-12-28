package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.GoogleMapOptions;

public final class zzj extends zza implements IMapFragmentDelegate {
    zzj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IMapFragmentDelegate");
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IGoogleMapDelegate getMap() {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.a()
            r1 = 1
            android.os.Parcel r0 = r4.a(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0011
            r1 = 0
            goto L_0x0025
        L_0x0011:
            java.lang.String r2 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.maps.internal.IGoogleMapDelegate
            if (r3 == 0) goto L_0x001f
            r1 = r2
            com.google.android.gms.maps.internal.IGoogleMapDelegate r1 = (com.google.android.gms.maps.internal.IGoogleMapDelegate) r1
            goto L_0x0025
        L_0x001f:
            com.google.android.gms.maps.internal.zzg r2 = new com.google.android.gms.maps.internal.zzg
            r2.<init>(r1)
            r1 = r2
        L_0x0025:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzj.getMap():com.google.android.gms.maps.internal.IGoogleMapDelegate");
    }

    public final void getMapAsync(zzap zzap) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzap);
        b(12, a);
    }

    public final boolean isReady() {
        Parcel a = a(11, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final void onCreate(Bundle bundle) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) bundle);
        b(3, a);
    }

    public final IObjectWrapper onCreateView(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, Bundle bundle) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        zzc.zza(a, (IInterface) iObjectWrapper2);
        zzc.zza(a, (Parcelable) bundle);
        Parcel a2 = a(4, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final void onDestroy() {
        b(8, a());
    }

    public final void onDestroyView() {
        b(7, a());
    }

    public final void onEnterAmbient(Bundle bundle) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) bundle);
        b(13, a);
    }

    public final void onExitAmbient() {
        b(14, a());
    }

    public final void onInflate(IObjectWrapper iObjectWrapper, GoogleMapOptions googleMapOptions, Bundle bundle) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        zzc.zza(a, (Parcelable) googleMapOptions);
        zzc.zza(a, (Parcelable) bundle);
        b(2, a);
    }

    public final void onLowMemory() {
        b(9, a());
    }

    public final void onPause() {
        b(6, a());
    }

    public final void onResume() {
        b(5, a());
    }

    public final void onSaveInstanceState(Bundle bundle) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) bundle);
        Parcel a2 = a(10, a);
        if (a2.readInt() != 0) {
            bundle.readFromParcel(a2);
        }
        a2.recycle();
    }

    public final void onStart() {
        b(15, a());
    }

    public final void onStop() {
        b(16, a());
    }
}

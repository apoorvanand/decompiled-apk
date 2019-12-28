package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;

public final class zzbw extends zza implements IStreetViewPanoramaViewDelegate {
    zzbw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate");
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate getStreetViewPanorama() {
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
            java.lang.String r2 = "com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate
            if (r3 == 0) goto L_0x001f
            r1 = r2
            com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate r1 = (com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate) r1
            goto L_0x0025
        L_0x001f:
            com.google.android.gms.maps.internal.zzbu r2 = new com.google.android.gms.maps.internal.zzbu
            r2.<init>(r1)
            r1 = r2
        L_0x0025:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.zzbw.getStreetViewPanorama():com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
    }

    public final void getStreetViewPanoramaAsync(zzbp zzbp) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzbp);
        b(9, a);
    }

    public final IObjectWrapper getView() {
        Parcel a = a(8, a());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a.readStrongBinder());
        a.recycle();
        return asInterface;
    }

    public final void onCreate(Bundle bundle) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) bundle);
        b(2, a);
    }

    public final void onDestroy() {
        b(5, a());
    }

    public final void onLowMemory() {
        b(6, a());
    }

    public final void onPause() {
        b(4, a());
    }

    public final void onResume() {
        b(3, a());
    }

    public final void onSaveInstanceState(Bundle bundle) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) bundle);
        Parcel a2 = a(7, a);
        if (a2.readInt() != 0) {
            bundle.readFromParcel(a2);
        }
        a2.recycle();
    }

    public final void onStart() {
        b(10, a());
    }

    public final void onStop() {
        b(11, a());
    }
}

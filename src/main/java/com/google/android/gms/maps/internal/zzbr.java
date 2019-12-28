package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

public final class zzbr extends zza implements IProjectionDelegate {
    zzbr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IProjectionDelegate");
    }

    public final LatLng fromScreenLocation(IObjectWrapper iObjectWrapper) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        Parcel a2 = a(1, a);
        LatLng latLng = (LatLng) zzc.zza(a2, LatLng.CREATOR);
        a2.recycle();
        return latLng;
    }

    public final VisibleRegion getVisibleRegion() {
        Parcel a = a(3, a());
        VisibleRegion visibleRegion = (VisibleRegion) zzc.zza(a, VisibleRegion.CREATOR);
        a.recycle();
        return visibleRegion;
    }

    public final IObjectWrapper toScreenLocation(LatLng latLng) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) latLng);
        Parcel a2 = a(2, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }
}

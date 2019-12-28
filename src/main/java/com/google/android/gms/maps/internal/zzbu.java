package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

public final class zzbu extends zza implements IStreetViewPanoramaDelegate {
    zzbu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate");
    }

    public final void animateTo(StreetViewPanoramaCamera streetViewPanoramaCamera, long j) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) streetViewPanoramaCamera);
        a.writeLong(j);
        b(9, a);
    }

    public final void enablePanning(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(2, a);
    }

    public final void enableStreetNames(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(4, a);
    }

    public final void enableUserNavigation(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(3, a);
    }

    public final void enableZoom(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(1, a);
    }

    public final StreetViewPanoramaCamera getPanoramaCamera() {
        Parcel a = a(10, a());
        StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera) zzc.zza(a, StreetViewPanoramaCamera.CREATOR);
        a.recycle();
        return streetViewPanoramaCamera;
    }

    public final StreetViewPanoramaLocation getStreetViewPanoramaLocation() {
        Parcel a = a(14, a());
        StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation) zzc.zza(a, StreetViewPanoramaLocation.CREATOR);
        a.recycle();
        return streetViewPanoramaLocation;
    }

    public final boolean isPanningGesturesEnabled() {
        Parcel a = a(6, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isStreetNamesEnabled() {
        Parcel a = a(8, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isUserNavigationEnabled() {
        Parcel a = a(7, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isZoomGesturesEnabled() {
        Parcel a = a(5, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final IObjectWrapper orientationToPoint(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) streetViewPanoramaOrientation);
        Parcel a2 = a(19, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final StreetViewPanoramaOrientation pointToOrientation(IObjectWrapper iObjectWrapper) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        Parcel a2 = a(18, a);
        StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation) zzc.zza(a2, StreetViewPanoramaOrientation.CREATOR);
        a2.recycle();
        return streetViewPanoramaOrientation;
    }

    public final void setOnStreetViewPanoramaCameraChangeListener(zzbh zzbh) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzbh);
        b(16, a);
    }

    public final void setOnStreetViewPanoramaChangeListener(zzbj zzbj) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzbj);
        b(15, a);
    }

    public final void setOnStreetViewPanoramaClickListener(zzbl zzbl) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzbl);
        b(17, a);
    }

    public final void setOnStreetViewPanoramaLongClickListener(zzbn zzbn) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzbn);
        b(20, a);
    }

    public final void setPosition(LatLng latLng) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) latLng);
        b(12, a);
    }

    public final void setPositionWithID(String str) {
        Parcel a = a();
        a.writeString(str);
        b(11, a);
    }

    public final void setPositionWithRadius(LatLng latLng, int i) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) latLng);
        a.writeInt(i);
        b(13, a);
    }

    public final void setPositionWithRadiusAndSource(LatLng latLng, int i, StreetViewSource streetViewSource) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) latLng);
        a.writeInt(i);
        zzc.zza(a, (Parcelable) streetViewSource);
        b(22, a);
    }

    public final void setPositionWithSource(LatLng latLng, StreetViewSource streetViewSource) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) latLng);
        zzc.zza(a, (Parcelable) streetViewSource);
        b(21, a);
    }
}

package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzb extends zza implements ICameraUpdateFactoryDelegate {
    zzb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
    }

    public final IObjectWrapper newCameraPosition(CameraPosition cameraPosition) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) cameraPosition);
        Parcel a2 = a(7, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper newLatLng(LatLng latLng) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) latLng);
        Parcel a2 = a(8, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper newLatLngBounds(LatLngBounds latLngBounds, int i) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) latLngBounds);
        a.writeInt(i);
        Parcel a2 = a(10, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper newLatLngBoundsWithSize(LatLngBounds latLngBounds, int i, int i2, int i3) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) latLngBounds);
        a.writeInt(i);
        a.writeInt(i2);
        a.writeInt(i3);
        Parcel a2 = a(11, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper newLatLngZoom(LatLng latLng, float f) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) latLng);
        a.writeFloat(f);
        Parcel a2 = a(9, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper scrollBy(float f, float f2) {
        Parcel a = a();
        a.writeFloat(f);
        a.writeFloat(f2);
        Parcel a2 = a(3, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper zoomBy(float f) {
        Parcel a = a();
        a.writeFloat(f);
        Parcel a2 = a(5, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper zoomByWithFocus(float f, int i, int i2) {
        Parcel a = a();
        a.writeFloat(f);
        a.writeInt(i);
        a.writeInt(i2);
        Parcel a2 = a(6, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper zoomIn() {
        Parcel a = a(1, a());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a.readStrongBinder());
        a.recycle();
        return asInterface;
    }

    public final IObjectWrapper zoomOut() {
        Parcel a = a(2, a());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a.readStrongBinder());
        a.recycle();
        return asInterface;
    }

    public final IObjectWrapper zoomTo(float f) {
        Parcel a = a();
        a.writeFloat(f);
        Parcel a2 = a(4, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }
}

package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public final class zzm extends zza implements zzk {
    zzm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IGroundOverlayDelegate");
    }

    public final float getBearing() {
        Parcel a = a(12, a());
        float readFloat = a.readFloat();
        a.recycle();
        return readFloat;
    }

    public final LatLngBounds getBounds() {
        Parcel a = a(10, a());
        LatLngBounds latLngBounds = (LatLngBounds) zzc.zza(a, LatLngBounds.CREATOR);
        a.recycle();
        return latLngBounds;
    }

    public final float getHeight() {
        Parcel a = a(8, a());
        float readFloat = a.readFloat();
        a.recycle();
        return readFloat;
    }

    public final String getId() {
        Parcel a = a(2, a());
        String readString = a.readString();
        a.recycle();
        return readString;
    }

    public final LatLng getPosition() {
        Parcel a = a(4, a());
        LatLng latLng = (LatLng) zzc.zza(a, LatLng.CREATOR);
        a.recycle();
        return latLng;
    }

    public final float getTransparency() {
        Parcel a = a(18, a());
        float readFloat = a.readFloat();
        a.recycle();
        return readFloat;
    }

    public final float getWidth() {
        Parcel a = a(7, a());
        float readFloat = a.readFloat();
        a.recycle();
        return readFloat;
    }

    public final float getZIndex() {
        Parcel a = a(14, a());
        float readFloat = a.readFloat();
        a.recycle();
        return readFloat;
    }

    public final boolean isClickable() {
        Parcel a = a(23, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isVisible() {
        Parcel a = a(16, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final void remove() {
        b(1, a());
    }

    public final void setBearing(float f) {
        Parcel a = a();
        a.writeFloat(f);
        b(11, a);
    }

    public final void setClickable(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(22, a);
    }

    public final void setDimensions(float f) {
        Parcel a = a();
        a.writeFloat(f);
        b(5, a);
    }

    public final void setPosition(LatLng latLng) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) latLng);
        b(3, a);
    }

    public final void setPositionFromBounds(LatLngBounds latLngBounds) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) latLngBounds);
        b(9, a);
    }

    public final void setTransparency(float f) {
        Parcel a = a();
        a.writeFloat(f);
        b(17, a);
    }

    public final void setVisible(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(15, a);
    }

    public final void setZIndex(float f) {
        Parcel a = a();
        a.writeFloat(f);
        b(13, a);
    }

    public final void zza(float f, float f2) {
        Parcel a = a();
        a.writeFloat(f);
        a.writeFloat(f2);
        b(6, a);
    }

    public final boolean zzb(zzk zzk) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzk);
        Parcel a2 = a(19, a);
        boolean zza = zzc.zza(a2);
        a2.recycle();
        return zza;
    }

    public final void zze(IObjectWrapper iObjectWrapper) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        b(24, a);
    }

    public final void zzf(IObjectWrapper iObjectWrapper) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        b(21, a);
    }

    public final int zzj() {
        Parcel a = a(20, a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final IObjectWrapper zzk() {
        Parcel a = a(25, a());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a.readStrongBinder());
        a.recycle();
        return asInterface;
    }
}

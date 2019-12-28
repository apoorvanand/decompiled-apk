package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

public final class zzv extends zza implements zzt {
    zzv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IMarkerDelegate");
    }

    public final float getAlpha() {
        Parcel a = a(26, a());
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

    public final float getRotation() {
        Parcel a = a(23, a());
        float readFloat = a.readFloat();
        a.recycle();
        return readFloat;
    }

    public final String getSnippet() {
        Parcel a = a(8, a());
        String readString = a.readString();
        a.recycle();
        return readString;
    }

    public final String getTitle() {
        Parcel a = a(6, a());
        String readString = a.readString();
        a.recycle();
        return readString;
    }

    public final float getZIndex() {
        Parcel a = a(28, a());
        float readFloat = a.readFloat();
        a.recycle();
        return readFloat;
    }

    public final void hideInfoWindow() {
        b(12, a());
    }

    public final boolean isDraggable() {
        Parcel a = a(10, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isFlat() {
        Parcel a = a(21, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isInfoWindowShown() {
        Parcel a = a(13, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isVisible() {
        Parcel a = a(15, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final void remove() {
        b(1, a());
    }

    public final void setAlpha(float f) {
        Parcel a = a();
        a.writeFloat(f);
        b(25, a);
    }

    public final void setAnchor(float f, float f2) {
        Parcel a = a();
        a.writeFloat(f);
        a.writeFloat(f2);
        b(19, a);
    }

    public final void setDraggable(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(9, a);
    }

    public final void setFlat(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(20, a);
    }

    public final void setInfoWindowAnchor(float f, float f2) {
        Parcel a = a();
        a.writeFloat(f);
        a.writeFloat(f2);
        b(24, a);
    }

    public final void setPosition(LatLng latLng) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) latLng);
        b(3, a);
    }

    public final void setRotation(float f) {
        Parcel a = a();
        a.writeFloat(f);
        b(22, a);
    }

    public final void setSnippet(String str) {
        Parcel a = a();
        a.writeString(str);
        b(7, a);
    }

    public final void setTitle(String str) {
        Parcel a = a();
        a.writeString(str);
        b(5, a);
    }

    public final void setVisible(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(14, a);
    }

    public final void setZIndex(float f) {
        Parcel a = a();
        a.writeFloat(f);
        b(27, a);
    }

    public final void showInfoWindow() {
        b(11, a());
    }

    public final void zze(IObjectWrapper iObjectWrapper) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        b(29, a);
    }

    public final void zzg(IObjectWrapper iObjectWrapper) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        b(18, a);
    }

    public final int zzj() {
        Parcel a = a(17, a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final boolean zzj(zzt zzt) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzt);
        Parcel a2 = a(16, a);
        boolean zza = zzc.zza(a2);
        a2.recycle();
        return zza;
    }

    public final IObjectWrapper zzk() {
        Parcel a = a(30, a());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a.readStrongBinder());
        a.recycle();
        return asInterface;
    }
}

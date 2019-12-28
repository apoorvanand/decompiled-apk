package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

public final class zzj extends zza implements zzh {
    zzj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ICircleDelegate");
    }

    public final LatLng getCenter() {
        Parcel a = a(4, a());
        LatLng latLng = (LatLng) zzc.zza(a, LatLng.CREATOR);
        a.recycle();
        return latLng;
    }

    public final int getFillColor() {
        Parcel a = a(12, a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final String getId() {
        Parcel a = a(2, a());
        String readString = a.readString();
        a.recycle();
        return readString;
    }

    public final double getRadius() {
        Parcel a = a(6, a());
        double readDouble = a.readDouble();
        a.recycle();
        return readDouble;
    }

    public final int getStrokeColor() {
        Parcel a = a(10, a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final List<PatternItem> getStrokePattern() {
        Parcel a = a(22, a());
        ArrayList<PatternItem> createTypedArrayList = a.createTypedArrayList(PatternItem.CREATOR);
        a.recycle();
        return createTypedArrayList;
    }

    public final float getStrokeWidth() {
        Parcel a = a(8, a());
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
        Parcel a = a(20, a());
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

    public final void setCenter(LatLng latLng) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) latLng);
        b(3, a);
    }

    public final void setClickable(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(19, a);
    }

    public final void setFillColor(int i) {
        Parcel a = a();
        a.writeInt(i);
        b(11, a);
    }

    public final void setRadius(double d) {
        Parcel a = a();
        a.writeDouble(d);
        b(5, a);
    }

    public final void setStrokeColor(int i) {
        Parcel a = a();
        a.writeInt(i);
        b(9, a);
    }

    public final void setStrokePattern(List<PatternItem> list) {
        Parcel a = a();
        a.writeTypedList(list);
        b(21, a);
    }

    public final void setStrokeWidth(float f) {
        Parcel a = a();
        a.writeFloat(f);
        b(7, a);
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

    public final boolean zzb(zzh zzh) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzh);
        Parcel a2 = a(17, a);
        boolean zza = zzc.zza(a2);
        a2.recycle();
        return zza;
    }

    public final void zze(IObjectWrapper iObjectWrapper) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        b(23, a);
    }

    public final int zzj() {
        Parcel a = a(18, a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final IObjectWrapper zzk() {
        Parcel a = a(24, a());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a.readStrongBinder());
        a.recycle();
        return asInterface;
    }
}

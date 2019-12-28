package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

public final class zzy extends zza implements zzw {
    zzy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IPolygonDelegate");
    }

    public final int getFillColor() {
        Parcel a = a(12, a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final List getHoles() {
        Parcel a = a(6, a());
        ArrayList zzb = zzc.zzb(a);
        a.recycle();
        return zzb;
    }

    public final String getId() {
        Parcel a = a(2, a());
        String readString = a.readString();
        a.recycle();
        return readString;
    }

    public final List<LatLng> getPoints() {
        Parcel a = a(4, a());
        ArrayList<LatLng> createTypedArrayList = a.createTypedArrayList(LatLng.CREATOR);
        a.recycle();
        return createTypedArrayList;
    }

    public final int getStrokeColor() {
        Parcel a = a(10, a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final int getStrokeJointType() {
        Parcel a = a(24, a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final List<PatternItem> getStrokePattern() {
        Parcel a = a(26, a());
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
        Parcel a = a(22, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isGeodesic() {
        Parcel a = a(18, a());
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

    public final void setClickable(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(21, a);
    }

    public final void setFillColor(int i) {
        Parcel a = a();
        a.writeInt(i);
        b(11, a);
    }

    public final void setGeodesic(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(17, a);
    }

    public final void setHoles(List list) {
        Parcel a = a();
        a.writeList(list);
        b(5, a);
    }

    public final void setPoints(List<LatLng> list) {
        Parcel a = a();
        a.writeTypedList(list);
        b(3, a);
    }

    public final void setStrokeColor(int i) {
        Parcel a = a();
        a.writeInt(i);
        b(9, a);
    }

    public final void setStrokeJointType(int i) {
        Parcel a = a();
        a.writeInt(i);
        b(23, a);
    }

    public final void setStrokePattern(List<PatternItem> list) {
        Parcel a = a();
        a.writeTypedList(list);
        b(25, a);
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

    public final boolean zzb(zzw zzw) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzw);
        Parcel a2 = a(19, a);
        boolean zza = zzc.zza(a2);
        a2.recycle();
        return zza;
    }

    public final void zze(IObjectWrapper iObjectWrapper) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        b(27, a);
    }

    public final int zzj() {
        Parcel a = a(20, a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final IObjectWrapper zzk() {
        Parcel a = a(28, a());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a.readStrongBinder());
        a.recycle();
        return asInterface;
    }
}

package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

public final class zzab extends zza implements zzz {
    zzab(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IPolylineDelegate");
    }

    public final int getColor() {
        Parcel a = a(8, a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final Cap getEndCap() {
        Parcel a = a(22, a());
        Cap cap = (Cap) zzc.zza(a, Cap.CREATOR);
        a.recycle();
        return cap;
    }

    public final String getId() {
        Parcel a = a(2, a());
        String readString = a.readString();
        a.recycle();
        return readString;
    }

    public final int getJointType() {
        Parcel a = a(24, a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final List<PatternItem> getPattern() {
        Parcel a = a(26, a());
        ArrayList<PatternItem> createTypedArrayList = a.createTypedArrayList(PatternItem.CREATOR);
        a.recycle();
        return createTypedArrayList;
    }

    public final List<LatLng> getPoints() {
        Parcel a = a(4, a());
        ArrayList<LatLng> createTypedArrayList = a.createTypedArrayList(LatLng.CREATOR);
        a.recycle();
        return createTypedArrayList;
    }

    public final Cap getStartCap() {
        Parcel a = a(20, a());
        Cap cap = (Cap) zzc.zza(a, Cap.CREATOR);
        a.recycle();
        return cap;
    }

    public final float getWidth() {
        Parcel a = a(6, a());
        float readFloat = a.readFloat();
        a.recycle();
        return readFloat;
    }

    public final float getZIndex() {
        Parcel a = a(10, a());
        float readFloat = a.readFloat();
        a.recycle();
        return readFloat;
    }

    public final boolean isClickable() {
        Parcel a = a(18, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isGeodesic() {
        Parcel a = a(14, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isVisible() {
        Parcel a = a(12, a());
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
        b(17, a);
    }

    public final void setColor(int i) {
        Parcel a = a();
        a.writeInt(i);
        b(7, a);
    }

    public final void setEndCap(Cap cap) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) cap);
        b(21, a);
    }

    public final void setGeodesic(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(13, a);
    }

    public final void setJointType(int i) {
        Parcel a = a();
        a.writeInt(i);
        b(23, a);
    }

    public final void setPattern(List<PatternItem> list) {
        Parcel a = a();
        a.writeTypedList(list);
        b(25, a);
    }

    public final void setPoints(List<LatLng> list) {
        Parcel a = a();
        a.writeTypedList(list);
        b(3, a);
    }

    public final void setStartCap(Cap cap) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) cap);
        b(19, a);
    }

    public final void setVisible(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(11, a);
    }

    public final void setWidth(float f) {
        Parcel a = a();
        a.writeFloat(f);
        b(5, a);
    }

    public final void setZIndex(float f) {
        Parcel a = a();
        a.writeFloat(f);
        b(9, a);
    }

    public final boolean zzb(zzz zzz) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzz);
        Parcel a2 = a(15, a);
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
        Parcel a = a(16, a());
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

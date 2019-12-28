package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;

public final class zzbx extends zza implements IUiSettingsDelegate {
    zzbx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IUiSettingsDelegate");
    }

    public final boolean isCompassEnabled() {
        Parcel a = a(10, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isIndoorLevelPickerEnabled() {
        Parcel a = a(17, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isMapToolbarEnabled() {
        Parcel a = a(19, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isMyLocationButtonEnabled() {
        Parcel a = a(11, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isRotateGesturesEnabled() {
        Parcel a = a(15, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isScrollGesturesEnabled() {
        Parcel a = a(12, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isScrollGesturesEnabledDuringRotateOrZoom() {
        Parcel a = a(21, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isTiltGesturesEnabled() {
        Parcel a = a(14, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isZoomControlsEnabled() {
        Parcel a = a(9, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean isZoomGesturesEnabled() {
        Parcel a = a(13, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final void setAllGesturesEnabled(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(8, a);
    }

    public final void setCompassEnabled(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(2, a);
    }

    public final void setIndoorLevelPickerEnabled(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(16, a);
    }

    public final void setMapToolbarEnabled(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(18, a);
    }

    public final void setMyLocationButtonEnabled(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(3, a);
    }

    public final void setRotateGesturesEnabled(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(7, a);
    }

    public final void setScrollGesturesEnabled(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(4, a);
    }

    public final void setScrollGesturesEnabledDuringRotateOrZoom(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(20, a);
    }

    public final void setTiltGesturesEnabled(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(6, a);
    }

    public final void setZoomControlsEnabled(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(1, a);
    }

    public final void setZoomGesturesEnabled(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(5, a);
    }
}

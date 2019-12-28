package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public final class zzae extends zza implements zzac {
    zzae(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
    }

    public final void clearTileCache() {
        b(2, a());
    }

    public final boolean getFadeIn() {
        Parcel a = a(11, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final String getId() {
        Parcel a = a(3, a());
        String readString = a.readString();
        a.recycle();
        return readString;
    }

    public final float getTransparency() {
        Parcel a = a(13, a());
        float readFloat = a.readFloat();
        a.recycle();
        return readFloat;
    }

    public final float getZIndex() {
        Parcel a = a(5, a());
        float readFloat = a.readFloat();
        a.recycle();
        return readFloat;
    }

    public final boolean isVisible() {
        Parcel a = a(7, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final void remove() {
        b(1, a());
    }

    public final void setFadeIn(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(10, a);
    }

    public final void setTransparency(float f) {
        Parcel a = a();
        a.writeFloat(f);
        b(12, a);
    }

    public final void setVisible(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(6, a);
    }

    public final void setZIndex(float f) {
        Parcel a = a();
        a.writeFloat(f);
        b(4, a);
    }

    public final boolean zza(zzac zzac) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzac);
        Parcel a2 = a(8, a);
        boolean zza = zzc.zza(a2);
        a2.recycle();
        return zza;
    }

    public final int zzj() {
        Parcel a = a(9, a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }
}

package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public final class zzs extends zza implements zzq {
    zzs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IIndoorLevelDelegate");
    }

    public final void activate() {
        b(3, a());
    }

    public final String getName() {
        Parcel a = a(1, a());
        String readString = a.readString();
        a.recycle();
        return readString;
    }

    public final String getShortName() {
        Parcel a = a(2, a());
        String readString = a.readString();
        a.recycle();
        return readString;
    }

    public final boolean zzb(zzq zzq) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzq);
        Parcel a2 = a(4, a);
        boolean zza = zzc.zza(a2);
        a2.recycle();
        return zza;
    }

    public final int zzj() {
        Parcel a = a(5, a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }
}

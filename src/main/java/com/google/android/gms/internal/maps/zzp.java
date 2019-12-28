package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import java.util.ArrayList;
import java.util.List;

public final class zzp extends zza implements zzn {
    zzp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
    }

    public final int getActiveLevelIndex() {
        Parcel a = a(1, a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final int getDefaultLevelIndex() {
        Parcel a = a(2, a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final List<IBinder> getLevels() {
        Parcel a = a(3, a());
        ArrayList<IBinder> createBinderArrayList = a.createBinderArrayList();
        a.recycle();
        return createBinderArrayList;
    }

    public final boolean isUnderground() {
        Parcel a = a(4, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }

    public final boolean zzb(zzn zzn) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzn);
        Parcel a2 = a(5, a);
        boolean zza = zzc.zza(a2);
        a2.recycle();
        return zza;
    }

    public final int zzj() {
        Parcel a = a(6, a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }
}

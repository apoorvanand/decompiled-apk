package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzs extends zzb implements zzq {
    zzs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
    }

    public final int id() {
        Parcel a = a(2, a());
        int readInt = a.readInt();
        a.recycle();
        return readInt;
    }

    public final void onEvent(String str, String str2, Bundle bundle, long j) {
        Parcel a = a();
        a.writeString(str);
        a.writeString(str2);
        zzd.zza(a, (Parcelable) bundle);
        a.writeLong(j);
        b(1, a);
    }
}

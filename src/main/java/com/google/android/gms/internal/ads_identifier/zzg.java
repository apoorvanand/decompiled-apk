package com.google.android.gms.internal.ads_identifier;

import android.os.IBinder;
import android.os.Parcel;

public final class zzg extends zza implements zze {
    zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
    }

    public final String getId() {
        Parcel a = a(1, a());
        String readString = a.readString();
        a.recycle();
        return readString;
    }

    public final boolean zzb(boolean z) {
        Parcel a = a();
        zzc.zza(a, true);
        Parcel a2 = a(2, a);
        boolean zza = zzc.zza(a2);
        a2.recycle();
        return zza;
    }

    public final boolean zzc() {
        Parcel a = a(6, a());
        boolean zza = zzc.zza(a);
        a.recycle();
        return zza;
    }
}

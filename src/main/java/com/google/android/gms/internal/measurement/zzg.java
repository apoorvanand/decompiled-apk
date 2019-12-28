package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzg extends zzb implements zzf {
    zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
    }

    public final Bundle zza(Bundle bundle) {
        Parcel a = a();
        zzd.zza(a, (Parcelable) bundle);
        Parcel a2 = a(1, a);
        Bundle bundle2 = (Bundle) zzd.zza(a2, Bundle.CREATOR);
        a2.recycle();
        return bundle2;
    }
}

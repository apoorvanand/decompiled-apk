package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzr extends zzb implements zzp {
    zzr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.api.internal.IBundleReceiver");
    }

    public final void zzb(Bundle bundle) {
        Parcel a = a();
        zzd.zza(a, (Parcelable) bundle);
        b(1, a);
    }
}

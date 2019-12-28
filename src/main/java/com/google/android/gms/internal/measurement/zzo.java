package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;

public abstract class zzo extends zza implements zzp {
    public zzo() {
        super("com.google.android.gms.measurement.api.internal.IBundleReceiver");
    }

    /* access modifiers changed from: protected */
    public final boolean a(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return false;
        }
        zzb((Bundle) zzd.zza(parcel, Bundle.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}

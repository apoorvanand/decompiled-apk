package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzc;

public final class zzl extends zza implements IGmsCallbacks {
    zzl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGmsCallbacks");
    }

    public final void onPostInitComplete(int i, IBinder iBinder, Bundle bundle) {
        Parcel a = a();
        a.writeInt(i);
        a.writeStrongBinder(iBinder);
        zzc.zza(a, (Parcelable) bundle);
        b(1, a);
    }

    public final void zza(int i, Bundle bundle) {
        Parcel a = a();
        a.writeInt(i);
        zzc.zza(a, (Parcelable) bundle);
        b(2, a);
    }

    public final void zza(int i, IBinder iBinder, zzb zzb) {
        Parcel a = a();
        a.writeInt(i);
        a.writeStrongBinder(iBinder);
        zzc.zza(a, (Parcelable) zzb);
        b(3, a);
    }
}

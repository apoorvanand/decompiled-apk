package com.google.android.gms.internal.clearcut;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.clearcut.zze;

public final class zzo extends zza implements zzn {
    zzo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.clearcut.internal.IClearcutLoggerService");
    }

    public final void zza(zzl zzl, zze zze) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzl);
        zzc.zza(a, (Parcelable) zze);
        a(1, a);
    }
}
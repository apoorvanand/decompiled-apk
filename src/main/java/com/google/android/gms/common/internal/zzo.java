package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.zzk;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzc;

public final class zzo extends zza implements zzm {
    zzo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }

    public final boolean zza(zzk zzk, IObjectWrapper iObjectWrapper) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) zzk);
        zzc.zza(a, (IInterface) iObjectWrapper);
        Parcel a2 = a(5, a);
        boolean zza = zzc.zza(a2);
        a2.recycle();
        return zza;
    }
}

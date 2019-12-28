package com.google.android.gms.vision.label.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.vision.zzc;

public final class zza extends com.google.android.gms.internal.vision.zza implements INativeImageLabeler {
    zza(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.label.internal.client.INativeImageLabeler");
    }

    public final zze[] zza(IObjectWrapper iObjectWrapper, LabelOptions labelOptions) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        zzc.zza(a, (Parcelable) labelOptions);
        Parcel a2 = a(1, a);
        zze[] zzeArr = (zze[]) a2.createTypedArray(zze.CREATOR);
        a2.recycle();
        return zzeArr;
    }

    public final void zzs() {
        b(2, a());
    }
}

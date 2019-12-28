package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzab extends zza implements zzaa {
    zzab(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
    }

    public final zzae[] zza(IObjectWrapper iObjectWrapper, zzn zzn, zzag zzag) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        zzc.zza(a, (Parcelable) zzn);
        zzc.zza(a, (Parcelable) zzag);
        Parcel a2 = a(3, a);
        zzae[] zzaeArr = (zzae[]) a2.createTypedArray(zzae.CREATOR);
        a2.recycle();
        return zzaeArr;
    }

    public final void zzs() {
        b(2, a());
    }
}

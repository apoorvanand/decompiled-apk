package com.google.android.gms.vision.face.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.vision.zza;
import com.google.android.gms.internal.vision.zzc;
import com.google.android.gms.internal.vision.zzn;

public final class zzi extends zza implements zzg {
    zzi(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
    }

    public final FaceParcel[] zzc(IObjectWrapper iObjectWrapper, zzn zzn) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        zzc.zza(a, (Parcelable) zzn);
        Parcel a2 = a(1, a);
        FaceParcel[] faceParcelArr = (FaceParcel[]) a2.createTypedArray(FaceParcel.CREATOR);
        a2.recycle();
        return faceParcelArr;
    }

    public final boolean zzd(int i) {
        Parcel a = a();
        a.writeInt(i);
        Parcel a2 = a(2, a);
        boolean zza = zzc.zza(a2);
        a2.recycle();
        return zza;
    }

    public final void zzn() {
        b(3, a());
    }
}

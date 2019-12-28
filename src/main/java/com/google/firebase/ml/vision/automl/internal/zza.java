package com.google.firebase.ml.vision.automl.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.firebase_ml.zzc;

public final class zza extends com.google.android.gms.internal.firebase_ml.zza implements IOnDeviceAutoMLImageLabeler {
    zza(IBinder iBinder) {
        super(iBinder, "com.google.firebase.ml.vision.automl.internal.IOnDeviceAutoMLImageLabeler");
    }

    public final void close() {
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken());
    }

    public final zze[] zza(IObjectWrapper iObjectWrapper, zzg zzg) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzg);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        zze[] zzeArr = (zze[]) transactAndReadException.createTypedArray(zze.CREATOR);
        transactAndReadException.recycle();
        return zzeArr;
    }

    public final void zzkl() {
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken());
    }
}

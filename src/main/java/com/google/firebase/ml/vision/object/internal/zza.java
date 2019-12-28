package com.google.firebase.ml.vision.object.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.firebase_ml.zzc;

public final class zza extends com.google.android.gms.internal.firebase_ml.zza implements IObjectDetector {
    zza(IBinder iBinder) {
        super(iBinder, "com.google.firebase.ml.vision.object.internal.IObjectDetector");
    }

    public final void start() {
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken());
    }

    public final void stop() {
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken());
    }

    public final zzh[] zza(IObjectWrapper iObjectWrapper, zze zze) {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zze);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        zzh[] zzhArr = (zzh[]) transactAndReadException.createTypedArray(zzh.CREATOR);
        transactAndReadException.recycle();
        return zzhArr;
    }
}

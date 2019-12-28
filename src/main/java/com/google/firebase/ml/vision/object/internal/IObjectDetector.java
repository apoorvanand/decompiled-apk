package com.google.firebase.ml.vision.object.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.firebase_ml.zzb;
import com.google.android.gms.internal.firebase_ml.zzc;

public interface IObjectDetector extends IInterface {

    public static abstract class zza extends zzb implements IObjectDetector {
        public zza() {
            super("com.google.firebase.ml.vision.object.internal.IObjectDetector");
        }

        /* access modifiers changed from: protected */
        public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case 1:
                    zzh[] zza = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zze) zzc.zza(parcel, zze.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(zza, 1);
                    break;
                case 2:
                    start();
                    break;
                case 3:
                    stop();
                    break;
                default:
                    return false;
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void start();

    void stop();

    zzh[] zza(IObjectWrapper iObjectWrapper, zze zze);
}

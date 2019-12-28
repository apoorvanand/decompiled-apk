package com.google.firebase.ml.vision.automl.internal;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.firebase_ml.zzb;
import com.google.android.gms.internal.firebase_ml.zzc;

public interface IOnDeviceAutoMLImageLabeler extends IInterface {

    public static abstract class zza extends zzb implements IOnDeviceAutoMLImageLabeler {
        public zza() {
            super("com.google.firebase.ml.vision.automl.internal.IOnDeviceAutoMLImageLabeler");
        }

        /* access modifiers changed from: protected */
        public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case 1:
                    zze[] zza = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzg) zzc.zza(parcel, zzg.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(zza, 1);
                    break;
                case 2:
                    zzkl();
                    break;
                case 3:
                    close();
                    break;
                default:
                    return false;
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void close();

    zze[] zza(IObjectWrapper iObjectWrapper, zzg zzg);

    void zzkl();
}

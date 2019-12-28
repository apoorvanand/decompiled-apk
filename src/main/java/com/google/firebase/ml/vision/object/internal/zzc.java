package com.google.firebase.ml.vision.object.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.firebase_ml.zzb;

public abstract class zzc extends zzb implements zzb {
    public zzc() {
        super("com.google.firebase.ml.vision.object.internal.IObjectDetectorCreator");
    }

    public static zzb asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.firebase.ml.vision.object.internal.IObjectDetectorCreator");
        return queryLocalInterface instanceof zzb ? (zzb) queryLocalInterface : new zzd(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return false;
        }
        IObjectDetector newObjectDetector = newObjectDetector((ObjectDetectorOptionsParcel) com.google.android.gms.internal.firebase_ml.zzc.zza(parcel, ObjectDetectorOptionsParcel.CREATOR));
        parcel2.writeNoException();
        com.google.android.gms.internal.firebase_ml.zzc.zza(parcel2, (IInterface) newObjectDetector);
        return true;
    }
}

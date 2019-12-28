package com.google.android.gms.vision.face.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.vision.zzb;
import com.google.android.gms.internal.vision.zzc;

public abstract class zzk extends zzb implements zzj {
    public zzk() {
        super("com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
    }

    public static zzj asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetectorCreator");
        return queryLocalInterface instanceof zzj ? (zzj) queryLocalInterface : new zzl(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean a(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return false;
        }
        zzg newFaceDetector = newFaceDetector(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zze) zzc.zza(parcel, zze.CREATOR));
        parcel2.writeNoException();
        zzc.zza(parcel2, (IInterface) newFaceDetector);
        return true;
    }
}

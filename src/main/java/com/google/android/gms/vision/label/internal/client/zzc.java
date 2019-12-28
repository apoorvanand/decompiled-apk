package com.google.android.gms.vision.label.internal.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.vision.zzb;

public abstract class zzc extends zzb implements zzb {
    public zzc() {
        super("com.google.android.gms.vision.label.internal.client.INativeImageLabelerCreator");
    }

    public static zzb asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.vision.label.internal.client.INativeImageLabelerCreator");
        return queryLocalInterface instanceof zzb ? (zzb) queryLocalInterface : new zzd(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean a(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 1) {
            return false;
        }
        INativeImageLabeler newImageLabeler = newImageLabeler(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (ImageLabelerOptions) com.google.android.gms.internal.vision.zzc.zza(parcel, ImageLabelerOptions.CREATOR));
        parcel2.writeNoException();
        com.google.android.gms.internal.vision.zzc.zza(parcel2, (IInterface) newImageLabeler);
        return true;
    }
}

package com.google.android.gms.vision.label.internal.client;

import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.vision.zzb;
import com.google.android.gms.internal.vision.zzc;

public interface INativeImageLabeler extends IInterface {

    public static abstract class zza extends zzb implements INativeImageLabeler {
        public zza() {
            super("com.google.android.gms.vision.label.internal.client.INativeImageLabeler");
        }

        /* access modifiers changed from: protected */
        public final boolean a(int i, Parcel parcel, Parcel parcel2, int i2) {
            switch (i) {
                case 1:
                    zze[] zza = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (LabelOptions) zzc.zza(parcel, LabelOptions.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(zza, 1);
                    break;
                case 2:
                    zzs();
                    parcel2.writeNoException();
                    break;
                default:
                    return false;
            }
            return true;
        }
    }

    zze[] zza(IObjectWrapper iObjectWrapper, LabelOptions labelOptions);

    void zzs();
}

package com.google.firebase.ml.vision.object.internal;

import android.os.IBinder;
import com.google.android.gms.internal.firebase_ml.zza;

public final class zzd extends zza implements zzb {
    zzd(IBinder iBinder) {
        super(iBinder, "com.google.firebase.ml.vision.object.internal.IObjectDetectorCreator");
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.firebase.ml.vision.object.internal.IObjectDetector newObjectDetector(com.google.firebase.ml.vision.object.internal.ObjectDetectorOptionsParcel r4) {
        /*
            r3 = this;
            android.os.Parcel r0 = r3.obtainAndWriteInterfaceToken()
            com.google.android.gms.internal.firebase_ml.zzc.zza((android.os.Parcel) r0, (android.os.Parcelable) r4)
            r4 = 1
            android.os.Parcel r4 = r3.transactAndReadException(r4, r0)
            android.os.IBinder r0 = r4.readStrongBinder()
            if (r0 != 0) goto L_0x0014
            r0 = 0
            goto L_0x0028
        L_0x0014:
            java.lang.String r1 = "com.google.firebase.ml.vision.object.internal.IObjectDetector"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.firebase.ml.vision.object.internal.IObjectDetector
            if (r2 == 0) goto L_0x0022
            r0 = r1
            com.google.firebase.ml.vision.object.internal.IObjectDetector r0 = (com.google.firebase.ml.vision.object.internal.IObjectDetector) r0
            goto L_0x0028
        L_0x0022:
            com.google.firebase.ml.vision.object.internal.zza r1 = new com.google.firebase.ml.vision.object.internal.zza
            r1.<init>(r0)
            r0 = r1
        L_0x0028:
            r4.recycle()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.ml.vision.object.internal.zzd.newObjectDetector(com.google.firebase.ml.vision.object.internal.ObjectDetectorOptionsParcel):com.google.firebase.ml.vision.object.internal.IObjectDetector");
    }
}

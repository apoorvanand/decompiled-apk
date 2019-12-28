package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.vision.barcode.Barcode;

public final class zzi extends zza implements zzh {
    zzi(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
    }

    public final Barcode[] zza(IObjectWrapper iObjectWrapper, zzn zzn) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        zzc.zza(a, (Parcelable) zzn);
        Parcel a2 = a(1, a);
        Barcode[] barcodeArr = (Barcode[]) a2.createTypedArray(Barcode.CREATOR);
        a2.recycle();
        return barcodeArr;
    }

    public final Barcode[] zzb(IObjectWrapper iObjectWrapper, zzn zzn) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        zzc.zza(a, (Parcelable) zzn);
        Parcel a2 = a(2, a);
        Barcode[] barcodeArr = (Barcode[]) a2.createTypedArray(Barcode.CREATOR);
        a2.recycle();
        return barcodeArr;
    }

    public final void zzn() {
        b(3, a());
    }
}

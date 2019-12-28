package com.google.android.gms.internal.maps;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzg extends zza implements zze {
    zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
    }

    public final IObjectWrapper zza(float f) {
        Parcel a = a();
        a.writeFloat(f);
        Parcel a2 = a(5, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper zza(int i) {
        Parcel a = a();
        a.writeInt(i);
        Parcel a2 = a(1, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper zza(Bitmap bitmap) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) bitmap);
        Parcel a2 = a(6, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper zza(String str) {
        Parcel a = a();
        a.writeString(str);
        Parcel a2 = a(2, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper zzb(String str) {
        Parcel a = a();
        a.writeString(str);
        Parcel a2 = a(3, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper zzc(String str) {
        Parcel a = a();
        a.writeString(str);
        Parcel a2 = a(7, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper zzi() {
        Parcel a = a(4, a());
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a.readStrongBinder());
        a.recycle();
        return asInterface;
    }
}

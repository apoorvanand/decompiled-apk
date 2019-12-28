package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzc;

public final class zzl extends zza implements zzk {
    zzl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
    }

    public final IObjectWrapper zza(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        a.writeString(str);
        a.writeInt(i);
        zzc.zza(a, (IInterface) iObjectWrapper2);
        Parcel a2 = a(2, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }

    public final IObjectWrapper zzb(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2) {
        Parcel a = a();
        zzc.zza(a, (IInterface) iObjectWrapper);
        a.writeString(str);
        a.writeInt(i);
        zzc.zza(a, (IInterface) iObjectWrapper2);
        Parcel a2 = a(3, a);
        IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(a2.readStrongBinder());
        a2.recycle();
        return asInterface;
    }
}

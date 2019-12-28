package com.google.android.gms.internal.auth;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.proxy.ProxyRequest;

public final class zzao extends zza implements zzan {
    zzao(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.internal.IAuthService");
    }

    public final void zza(zzal zzal) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzal);
        b(3, a);
    }

    public final void zza(zzal zzal, ProxyRequest proxyRequest) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzal);
        zzc.zza(a, (Parcelable) proxyRequest);
        b(1, a);
    }
}

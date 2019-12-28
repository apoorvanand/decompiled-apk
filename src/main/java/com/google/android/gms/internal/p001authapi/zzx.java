package com.google.android.gms.internal.p001authapi;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.credentials.CredentialRequest;

/* renamed from: com.google.android.gms.internal.auth-api.zzx  reason: invalid package */
public final class zzx extends zzc implements zzw {
    zzx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
    }

    public final void zzc(zzu zzu) {
        Parcel a = a();
        zze.zzc(a, (IInterface) zzu);
        a(4, a);
    }

    public final void zzc(zzu zzu, CredentialRequest credentialRequest) {
        Parcel a = a();
        zze.zzc(a, (IInterface) zzu);
        zze.zzc(a, (Parcelable) credentialRequest);
        a(1, a);
    }

    public final void zzc(zzu zzu, zzs zzs) {
        Parcel a = a();
        zze.zzc(a, (IInterface) zzu);
        zze.zzc(a, (Parcelable) zzs);
        a(3, a);
    }

    public final void zzc(zzu zzu, zzy zzy) {
        Parcel a = a();
        zze.zzc(a, (IInterface) zzu);
        zze.zzc(a, (Parcelable) zzy);
        a(2, a);
    }
}

package com.google.android.gms.auth.api.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.internal.p001authapi.zzc;
import com.google.android.gms.internal.p001authapi.zze;

public final class zzv extends zzc implements zzu {
    zzv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.signin.internal.ISignInService");
    }

    public final void zzc(zzs zzs, GoogleSignInOptions googleSignInOptions) {
        Parcel a = a();
        zze.zzc(a, (IInterface) zzs);
        zze.zzc(a, (Parcelable) googleSignInOptions);
        a(101, a);
    }

    public final void zzd(zzs zzs, GoogleSignInOptions googleSignInOptions) {
        Parcel a = a();
        zze.zzc(a, (IInterface) zzs);
        zze.zzc(a, (Parcelable) googleSignInOptions);
        a(102, a);
    }

    public final void zze(zzs zzs, GoogleSignInOptions googleSignInOptions) {
        Parcel a = a();
        zze.zzc(a, (IInterface) zzs);
        zze.zzc(a, (Parcelable) googleSignInOptions);
        a(103, a);
    }
}

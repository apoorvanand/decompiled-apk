package com.google.android.gms.internal.auth;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzaa extends zza implements zzz {
    zzaa(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferService");
    }

    public final void zza(zzx zzx, zzab zzab) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzx);
        zzc.zza(a, (Parcelable) zzab);
        b(9, a);
    }

    public final void zza(zzx zzx, zzad zzad) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzx);
        zzc.zza(a, (Parcelable) zzad);
        b(6, a);
    }

    public final void zza(zzx zzx, zzaf zzaf) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzx);
        zzc.zza(a, (Parcelable) zzaf);
        b(5, a);
    }

    public final void zza(zzx zzx, zzah zzah) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzx);
        zzc.zza(a, (Parcelable) zzah);
        b(8, a);
    }

    public final void zza(zzx zzx, zzv zzv) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zzx);
        zzc.zza(a, (Parcelable) zzv);
        b(7, a);
    }
}

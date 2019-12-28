package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.auth.zza;
import com.google.android.gms.internal.auth.zzc;

public final class zze extends zza implements zzc {
    zze(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.account.IWorkAccountService");
    }

    public final void zza(zza zza, Account account) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zza);
        zzc.zza(a, (Parcelable) account);
        b(3, a);
    }

    public final void zza(zza zza, String str) {
        Parcel a = a();
        zzc.zza(a, (IInterface) zza);
        a.writeString(str);
        b(2, a);
    }

    public final void zzb(boolean z) {
        Parcel a = a();
        zzc.writeBoolean(a, z);
        b(1, a);
    }
}

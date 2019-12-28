package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;

public final class zzg extends zza implements zze {
    zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.auth.IAuthManagerService");
    }

    public final Bundle zza(Account account) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) account);
        Parcel a2 = a(7, a);
        Bundle bundle = (Bundle) zzc.zza(a2, Bundle.CREATOR);
        a2.recycle();
        return bundle;
    }

    public final Bundle zza(Account account, String str, Bundle bundle) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) account);
        a.writeString(str);
        zzc.zza(a, (Parcelable) bundle);
        Parcel a2 = a(5, a);
        Bundle bundle2 = (Bundle) zzc.zza(a2, Bundle.CREATOR);
        a2.recycle();
        return bundle2;
    }

    public final Bundle zza(String str) {
        Parcel a = a();
        a.writeString(str);
        Parcel a2 = a(8, a);
        Bundle bundle = (Bundle) zzc.zza(a2, Bundle.CREATOR);
        a2.recycle();
        return bundle;
    }

    public final Bundle zza(String str, Bundle bundle) {
        Parcel a = a();
        a.writeString(str);
        zzc.zza(a, (Parcelable) bundle);
        Parcel a2 = a(2, a);
        Bundle bundle2 = (Bundle) zzc.zza(a2, Bundle.CREATOR);
        a2.recycle();
        return bundle2;
    }

    public final AccountChangeEventsResponse zza(AccountChangeEventsRequest accountChangeEventsRequest) {
        Parcel a = a();
        zzc.zza(a, (Parcelable) accountChangeEventsRequest);
        Parcel a2 = a(3, a);
        AccountChangeEventsResponse accountChangeEventsResponse = (AccountChangeEventsResponse) zzc.zza(a2, AccountChangeEventsResponse.CREATOR);
        a2.recycle();
        return accountChangeEventsResponse;
    }
}

package com.google.android.gms.internal.auth;

import android.accounts.Account;
import com.google.android.gms.auth.account.WorkAccountApi;
import com.google.android.gms.auth.account.zza;
import com.google.android.gms.auth.account.zzc;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

final class zzj extends BaseImplementation.ApiMethodImpl<WorkAccountApi.AddAccountResult, zzr> {
    private final /* synthetic */ String zzq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzj(zzh zzh, Api api, GoogleApiClient googleApiClient, String str) {
        super(api, googleApiClient);
        this.zzq = str;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void a(Api.AnyClient anyClient) {
        ((zzc) ((zzr) anyClient).getService()).zza((zza) new zzk(this), this.zzq);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Result createFailedResult(Status status) {
        return new zzo(status, (Account) null);
    }
}

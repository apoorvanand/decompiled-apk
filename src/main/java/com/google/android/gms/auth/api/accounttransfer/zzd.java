package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.auth.api.accounttransfer.AccountTransferClient;
import com.google.android.gms.internal.auth.zzaf;
import com.google.android.gms.internal.auth.zzx;
import com.google.android.gms.internal.auth.zzz;

final class zzd extends AccountTransferClient.zzc {
    private final /* synthetic */ zzaf zzao;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzd(AccountTransferClient accountTransferClient, zzaf zzaf) {
        super((zzc) null);
        this.zzao = zzaf;
    }

    /* access modifiers changed from: protected */
    public final void a(zzz zzz) {
        zzz.zza((zzx) this.a, this.zzao);
    }
}

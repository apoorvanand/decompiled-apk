package com.google.android.gms.internal.p000authapiphone;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api-phone.zzm  reason: invalid package */
abstract class zzm extends TaskApiCall<zzi, Void> {
    private TaskCompletionSource<Void> zzf;

    private zzm() {
    }

    /* synthetic */ zzm(zzk zzk) {
        this();
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ void a(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
        this.zzf = taskCompletionSource;
        a((zze) ((zzi) anyClient).getService());
    }

    /* access modifiers changed from: protected */
    public final void a(Status status) {
        TaskUtil.setResultOrApiException(status, this.zzf);
    }

    /* access modifiers changed from: protected */
    public abstract void a(zze zze);
}

package com.google.android.gms.auth.api.accounttransfer;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.auth.zzab;
import com.google.android.gms.internal.auth.zzad;
import com.google.android.gms.internal.auth.zzaf;
import com.google.android.gms.internal.auth.zzah;
import com.google.android.gms.internal.auth.zzs;
import com.google.android.gms.internal.auth.zzu;
import com.google.android.gms.internal.auth.zzv;
import com.google.android.gms.internal.auth.zzy;
import com.google.android.gms.internal.auth.zzz;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public class AccountTransferClient extends GoogleApi<zzn> {
    private static final Api.ClientKey<zzu> zzaj = new Api.ClientKey<>();
    private static final Api.AbstractClientBuilder<zzu, zzn> zzak = new zzc();
    private static final Api<zzn> zzal = new Api<>("AccountTransfer.ACCOUNT_TRANSFER_API", zzak, zzaj);

    private static class zza<T> extends zzs {
        private zzb<T> zzav;

        public zza(zzb<T> zzb) {
            this.zzav = zzb;
        }

        public final void onFailure(Status status) {
            this.zzav.a(status);
        }
    }

    private static abstract class zzb<T> extends TaskApiCall<zzu, T> {
        private TaskCompletionSource<T> zzaw;

        private zzb() {
        }

        /* synthetic */ zzb(zzc zzc) {
            this();
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ void a(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) {
            this.zzaw = taskCompletionSource;
            a((zzz) ((zzu) anyClient).getService());
        }

        /* access modifiers changed from: protected */
        public final void a(Status status) {
            AccountTransferClient.zza(this.zzaw, status);
        }

        /* access modifiers changed from: protected */
        public abstract void a(zzz zzz);

        /* access modifiers changed from: protected */
        public final void a(T t) {
            this.zzaw.setResult(t);
        }
    }

    private static abstract class zzc extends zzb<Void> {
        zzy a;

        private zzc() {
            super((zzc) null);
            this.a = new zzk(this);
        }

        /* synthetic */ zzc(zzc zzc) {
            this();
        }
    }

    AccountTransferClient(@NonNull Activity activity) {
        super(activity, zzal, null, new GoogleApi.Settings.Builder().setMapper(new ApiExceptionMapper()).build());
    }

    AccountTransferClient(@NonNull Context context) {
        super(context, zzal, null, new GoogleApi.Settings.Builder().setMapper(new ApiExceptionMapper()).build());
    }

    /* access modifiers changed from: private */
    public static void zza(TaskCompletionSource taskCompletionSource, Status status) {
        taskCompletionSource.setException(new AccountTransferException(status));
    }

    public Task<DeviceMetaData> getDeviceMetaData(String str) {
        Preconditions.checkNotNull(str);
        return doRead(new zzg(this, new zzv(str)));
    }

    public Task<Void> notifyCompletion(String str, int i) {
        Preconditions.checkNotNull(str);
        return doWrite(new zzj(this, new zzab(str, i)));
    }

    public Task<byte[]> retrieveData(String str) {
        Preconditions.checkNotNull(str);
        return doRead(new zze(this, new zzad(str)));
    }

    public Task<Void> sendData(String str, byte[] bArr) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(bArr);
        return doWrite(new zzd(this, new zzaf(str, bArr)));
    }

    public Task<Void> showUserChallenge(String str, PendingIntent pendingIntent) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(pendingIntent);
        return doWrite(new zzi(this, new zzah(str, pendingIntent)));
    }
}

package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zzcv;
import com.google.android.gms.internal.firebase_auth.zze;
import com.google.android.gms.internal.firebase_auth.zzfe;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.PhoneAuthProvider;

@VisibleForTesting
final class zzdl extends zzen<Void, PhoneAuthProvider.OnVerificationStateChangedCallbacks> {
    private final zzcv zzoq;

    public zzdl(zzfe zzfe) {
        super(8);
        Preconditions.checkNotNull(zzfe);
        this.zzoq = new zzcv(zzfe);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzag(zzdp zzdp, TaskCompletionSource taskCompletionSource) {
        this.zzpu = new zzeu(this, taskCompletionSource);
        if (this.zzqh) {
            zzdp.zzeb().zza(this.zzoq.zzdk(), (zzdu) this.zzpq);
        } else {
            zzdp.zzeb().zza(this.zzoq, (zzdu) this.zzpq);
        }
    }

    public final String zzdu() {
        return "verifyPhoneNumber";
    }

    public final TaskApiCall<zzdp, Void> zzdv() {
        Feature[] featureArr;
        TaskApiCall.Builder autoResolveMissingFeatures = TaskApiCall.builder().setAutoResolveMissingFeatures(false);
        if (this.zzqh) {
            featureArr = null;
        } else {
            featureArr = new Feature[]{zze.zzf};
        }
        return autoResolveMissingFeatures.setFeatures(featureArr).run(new zzdk(this)).build();
    }

    public final void zzdx() {
    }
}

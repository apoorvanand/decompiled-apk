package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zzdh;
import com.google.android.gms.internal.firebase_auth.zze;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzg;
import com.google.firebase.auth.internal.zzm;

@VisibleForTesting
final class zzbl extends zzen<AuthResult, zza> {
    private final EmailAuthCredential zzkn;

    public zzbl(EmailAuthCredential emailAuthCredential) {
        super(2);
        this.zzkn = (EmailAuthCredential) Preconditions.checkNotNull(emailAuthCredential, "credential cannot be null");
    }

    public final String zzdu() {
        return "linkEmailAuthCredential";
    }

    public final TaskApiCall<zzdp, AuthResult> zzdv() {
        Feature[] featureArr;
        TaskApiCall.Builder autoResolveMissingFeatures = TaskApiCall.builder().setAutoResolveMissingFeatures(false);
        if (this.zzqh) {
            featureArr = null;
        } else {
            featureArr = new Feature[]{zze.zzf};
        }
        return autoResolveMissingFeatures.setFeatures(featureArr).run(new zzbk(this)).build();
    }

    public final void zzdx() {
        zzm zza = zzap.zza(this.zzik, this.zzpz);
        ((zza) this.zzps).zza(this.zzpy, zza);
        zzc(new zzg(zza));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzi(zzdp zzdp, TaskCompletionSource taskCompletionSource) {
        this.zzpu = new zzeu(this, taskCompletionSource);
        zzdh zzdh = new zzdh(this.zzkn.zza(this.zzpr));
        if (this.zzqh) {
            zzdp.zzeb().zza(zzdh.zzdm(), (zzdu) this.zzpq);
        } else {
            zzdp.zzeb().zza(zzdh, (zzdu) this.zzpq);
        }
    }
}
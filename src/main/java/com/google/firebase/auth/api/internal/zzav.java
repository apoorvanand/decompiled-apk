package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zzbv;
import com.google.android.gms.internal.firebase_auth.zze;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zza;

@VisibleForTesting
final class zzav extends zzen<Void, zza> {
    private final zzbv zzmt;

    public zzav(String str, String str2, @Nullable String str3) {
        super(4);
        Preconditions.checkNotEmpty(str, "code cannot be null or empty");
        Preconditions.checkNotEmpty(str2, "new password cannot be null or empty");
        this.zzmt = new zzbv(str, str2, str3);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zzdp zzdp, TaskCompletionSource taskCompletionSource) {
        this.zzpu = new zzeu(this, taskCompletionSource);
        if (this.zzqh) {
            zzdp.zzeb().zzf(this.zzmt.zzcn(), this.zzmt.zzdg(), this.zzpq);
        } else {
            zzdp.zzeb().zza(this.zzmt, (zzdu) this.zzpq);
        }
    }

    public final String zzdu() {
        return "confirmPasswordReset";
    }

    public final TaskApiCall<zzdp, Void> zzdv() {
        Feature[] featureArr;
        TaskApiCall.Builder autoResolveMissingFeatures = TaskApiCall.builder().setAutoResolveMissingFeatures(false);
        if (this.zzqh) {
            featureArr = null;
        } else {
            featureArr = new Feature[]{zze.zzf};
        }
        return autoResolveMissingFeatures.setFeatures(featureArr).run(new zzau(this)).build();
    }

    public final void zzdx() {
        zzc(null);
    }
}
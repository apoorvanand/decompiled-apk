package com.google.firebase.auth.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zzdz;
import com.google.android.gms.internal.firebase_auth.zzeb;
import com.google.android.gms.internal.firebase_auth.zzec;
import com.google.android.gms.internal.firebase_auth.zzem;
import com.google.android.gms.internal.firebase_auth.zzes;
import com.google.android.gms.internal.firebase_auth.zzfd;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.internal.zzt;

@VisibleForTesting
final class zzep extends zzdx {
    final /* synthetic */ zzen zzqn;

    zzep(zzen zzen) {
        this.zzqn = zzen;
    }

    private final void zza(zzev zzev) {
        this.zzqn.zzpx.execute(new zzes(this, zzev));
    }

    private final void zzb(Status status, AuthCredential authCredential, @Nullable String str, @Nullable String str2) {
        this.zzqn.zzd(status);
        zzen zzen = this.zzqn;
        zzen.zzqd = authCredential;
        zzen.zzqe = str;
        zzen.zzqf = str2;
        if (zzen.zzpt != null) {
            this.zzqn.zzpt.zza(status);
        }
        this.zzqn.zzc(status);
    }

    public final void onFailure(Status status) {
        if (this.zzqn.zzpp == 8) {
            boolean unused = this.zzqn.zzqi = true;
            this.zzqn.zzqj = false;
            zza((zzev) new zzet(this, status));
            return;
        }
        this.zzqn.zzd(status);
        this.zzqn.zzc(status);
    }

    public final void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
        boolean z = this.zzqn.zzpp == 8;
        int i = this.zzqn.zzpp;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        boolean unused = this.zzqn.zzqi = true;
        this.zzqn.zzqj = true;
        zza((zzev) new zzer(this, phoneAuthCredential));
    }

    public final void zza(Status status, PhoneAuthCredential phoneAuthCredential) {
        boolean z = this.zzqn.zzpp == 2;
        int i = this.zzqn.zzpp;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzb(status, phoneAuthCredential, (String) null, (String) null);
    }

    public final void zza(zzdz zzdz) {
        zzb(zzdz.getStatus(), zzdz.zzdo(), zzdz.getEmail(), zzdz.zzba());
    }

    public final void zza(zzeb zzeb) {
        zzen zzen = this.zzqn;
        zzen.zzqg = zzeb;
        zzen.zzc(zzt.zzdc("REQUIRES_SECOND_FACTOR_AUTH"));
    }

    public final void zza(zzec zzec) {
        boolean z = this.zzqn.zzpp == 3;
        int i = this.zzqn.zzpp;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzen zzen = this.zzqn;
        zzen.zzqa = zzec;
        zzen.zzen();
    }

    public final void zza(zzes zzes, zzem zzem) {
        boolean z = this.zzqn.zzpp == 2;
        int i = this.zzqn.zzpp;
        StringBuilder sb = new StringBuilder(37);
        sb.append("Unexpected response type: ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzen zzen = this.zzqn;
        zzen.zzpy = zzes;
        zzen.zzpz = zzem;
        zzen.zzen();
    }

    public final void zza(@Nullable zzfd zzfd) {
        boolean z = this.zzqn.zzpp == 4;
        int i = this.zzqn.zzpp;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzen zzen = this.zzqn;
        zzen.zzqb = zzfd;
        zzen.zzen();
    }

    public final void zzb(zzes zzes) {
        boolean z = true;
        if (this.zzqn.zzpp != 1) {
            z = false;
        }
        int i = this.zzqn.zzpp;
        StringBuilder sb = new StringBuilder(37);
        sb.append("Unexpected response type: ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzen zzen = this.zzqn;
        zzen.zzpy = zzes;
        zzen.zzen();
    }

    public final void zzby(String str) {
        boolean z = this.zzqn.zzpp == 7;
        int i = this.zzqn.zzpp;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzen zzen = this.zzqn;
        zzen.zzqc = str;
        zzen.zzen();
    }

    public final void zzbz(String str) {
        boolean z = this.zzqn.zzpp == 8;
        int i = this.zzqn.zzpp;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        this.zzqn.zzjl = str;
        zza((zzev) new zzeo(this, str));
    }

    public final void zzca(String str) {
        boolean z = this.zzqn.zzpp == 8;
        int i = this.zzqn.zzpp;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzen zzen = this.zzqn;
        zzen.zzjl = str;
        boolean unused = zzen.zzqi = true;
        this.zzqn.zzqj = true;
        zza((zzev) new zzeq(this, str));
    }

    public final void zzdy() {
        boolean z = this.zzqn.zzpp == 5;
        int i = this.zzqn.zzpp;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        this.zzqn.zzen();
    }

    public final void zzdz() {
        boolean z = this.zzqn.zzpp == 6;
        int i = this.zzqn.zzpp;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        this.zzqn.zzen();
    }

    public final void zzea() {
        boolean z = this.zzqn.zzpp == 9;
        int i = this.zzqn.zzpp;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        this.zzqn.zzen();
    }
}

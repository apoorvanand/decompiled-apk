package com.google.firebase.auth.api.internal;

import android.os.IInterface;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.firebase_auth.zzdz;
import com.google.android.gms.internal.firebase_auth.zzeb;
import com.google.android.gms.internal.firebase_auth.zzec;
import com.google.android.gms.internal.firebase_auth.zzem;
import com.google.android.gms.internal.firebase_auth.zzes;
import com.google.android.gms.internal.firebase_auth.zzfd;
import com.google.firebase.auth.PhoneAuthCredential;

public interface zzdu extends IInterface {
    void onFailure(Status status);

    void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential);

    void zza(Status status, PhoneAuthCredential phoneAuthCredential);

    void zza(zzdz zzdz);

    void zza(zzeb zzeb);

    void zza(zzec zzec);

    void zza(zzes zzes, zzem zzem);

    void zza(zzfd zzfd);

    void zzb(zzes zzes);

    void zzby(String str);

    void zzbz(String str);

    void zzca(String str);

    void zzdy();

    void zzdz();

    void zzea();
}

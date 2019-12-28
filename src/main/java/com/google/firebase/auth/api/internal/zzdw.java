package com.google.firebase.auth.api.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.firebase_auth.zzb;
import com.google.android.gms.internal.firebase_auth.zzd;
import com.google.android.gms.internal.firebase_auth.zzdz;
import com.google.android.gms.internal.firebase_auth.zzeb;
import com.google.android.gms.internal.firebase_auth.zzec;
import com.google.android.gms.internal.firebase_auth.zzem;
import com.google.android.gms.internal.firebase_auth.zzes;
import com.google.android.gms.internal.firebase_auth.zzfd;
import com.google.firebase.auth.PhoneAuthCredential;

public final class zzdw extends zzb implements zzdu {
    zzdw(IBinder iBinder) {
        super(iBinder, "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
    }

    public final void onFailure(Status status) {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) status);
        zzb(5, zza);
    }

    public final void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) phoneAuthCredential);
        zzb(10, zza);
    }

    public final void zza(Status status, PhoneAuthCredential phoneAuthCredential) {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) status);
        zzd.zza(zza, (Parcelable) phoneAuthCredential);
        zzb(12, zza);
    }

    public final void zza(zzdz zzdz) {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzdz);
        zzb(14, zza);
    }

    public final void zza(zzeb zzeb) {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzeb);
        zzb(15, zza);
    }

    public final void zza(zzec zzec) {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzec);
        zzb(3, zza);
    }

    public final void zza(zzes zzes, zzem zzem) {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzes);
        zzd.zza(zza, (Parcelable) zzem);
        zzb(2, zza);
    }

    public final void zza(zzfd zzfd) {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzfd);
        zzb(4, zza);
    }

    public final void zzb(zzes zzes) {
        Parcel zza = zza();
        zzd.zza(zza, (Parcelable) zzes);
        zzb(1, zza);
    }

    public final void zzby(String str) {
        Parcel zza = zza();
        zza.writeString(str);
        zzb(8, zza);
    }

    public final void zzbz(String str) {
        Parcel zza = zza();
        zza.writeString(str);
        zzb(9, zza);
    }

    public final void zzca(String str) {
        Parcel zza = zza();
        zza.writeString(str);
        zzb(11, zza);
    }

    public final void zzdy() {
        zzb(6, zza());
    }

    public final void zzdz() {
        zzb(7, zza());
    }

    public final void zzea() {
        zzb(13, zza());
    }
}

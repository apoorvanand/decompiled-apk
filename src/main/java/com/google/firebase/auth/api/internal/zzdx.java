package com.google.firebase.auth.api.internal;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.firebase_auth.zza;
import com.google.android.gms.internal.firebase_auth.zzd;
import com.google.android.gms.internal.firebase_auth.zzdz;
import com.google.android.gms.internal.firebase_auth.zzeb;
import com.google.android.gms.internal.firebase_auth.zzec;
import com.google.android.gms.internal.firebase_auth.zzem;
import com.google.android.gms.internal.firebase_auth.zzes;
import com.google.android.gms.internal.firebase_auth.zzfd;
import com.google.firebase.auth.PhoneAuthCredential;

public abstract class zzdx extends zza implements zzdu {
    public zzdx() {
        super("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                zzb((zzes) zzd.zza(parcel, zzes.CREATOR));
                return true;
            case 2:
                zza((zzes) zzd.zza(parcel, zzes.CREATOR), (zzem) zzd.zza(parcel, zzem.CREATOR));
                return true;
            case 3:
                zza((zzec) zzd.zza(parcel, zzec.CREATOR));
                return true;
            case 4:
                zza((zzfd) zzd.zza(parcel, zzfd.CREATOR));
                return true;
            case 5:
                onFailure((Status) zzd.zza(parcel, Status.CREATOR));
                return true;
            case 6:
                zzdy();
                return true;
            case 7:
                zzdz();
                return true;
            case 8:
                zzby(parcel.readString());
                return true;
            case 9:
                zzbz(parcel.readString());
                return true;
            case 10:
                onVerificationCompleted((PhoneAuthCredential) zzd.zza(parcel, PhoneAuthCredential.CREATOR));
                return true;
            case 11:
                zzca(parcel.readString());
                return true;
            case 12:
                zza((Status) zzd.zza(parcel, Status.CREATOR), (PhoneAuthCredential) zzd.zza(parcel, PhoneAuthCredential.CREATOR));
                return true;
            case 13:
                zzea();
                return true;
            case 14:
                zza((zzdz) zzd.zza(parcel, zzdz.CREATOR));
                return true;
            case 15:
                zza((zzeb) zzd.zza(parcel, zzeb.CREATOR));
                return true;
            default:
                return false;
        }
    }
}

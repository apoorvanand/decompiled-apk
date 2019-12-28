package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.zzf;

@SafeParcelable.Class(creator = "OnFailedIdpSignInAidlResponseCreator")
public final class zzdz extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdz> CREATOR = new zzdy();
    @SafeParcelable.Field(getter = "getTenantId", id = 4)
    private final String zzhy;
    @SafeParcelable.Field(getter = "getEmail", id = 3)
    private final String zzif;
    @SafeParcelable.Field(getter = "getStatus", id = 1)
    private final Status zzkv;
    @SafeParcelable.Field(getter = "getDefaultOAuthCredential", id = 2)
    private final zzf zzkw;

    @SafeParcelable.Constructor
    public zzdz(@SafeParcelable.Param(id = 1) Status status, @SafeParcelable.Param(id = 2) zzf zzf, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) @Nullable String str2) {
        this.zzkv = status;
        this.zzkw = zzf;
        this.zzif = str;
        this.zzhy = str2;
    }

    public final String getEmail() {
        return this.zzif;
    }

    public final Status getStatus() {
        return this.zzkv;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzkv, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzkw, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzif, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzhy, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzba() {
        return this.zzhy;
    }

    public final zzf zzdo() {
        return this.zzkw;
    }
}

package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.ActionCodeSettings;

@SafeParcelable.Class(creator = "VerifyBeforeUpdateEmailAidlRequestCreator")
public final class zzdx extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdx> CREATOR = new zzdw();
    @SafeParcelable.Field(getter = "getIdToken", id = 1)
    private final String zzib;
    @SafeParcelable.Field(getter = "getActionCodeSettings", id = 3)
    private final ActionCodeSettings zzkk;
    @SafeParcelable.Field(getter = "getNewEmail", id = 2)
    private final String zzku;

    @SafeParcelable.Constructor
    public zzdx(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) ActionCodeSettings actionCodeSettings) {
        this.zzib = str;
        this.zzku = str2;
        this.zzkk = actionCodeSettings;
    }

    public final String getIdToken() {
        return this.zzib;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzib, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzku, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzkk, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzae() {
        return this.zzku;
    }

    public final ActionCodeSettings zzdj() {
        return this.zzkk;
    }
}

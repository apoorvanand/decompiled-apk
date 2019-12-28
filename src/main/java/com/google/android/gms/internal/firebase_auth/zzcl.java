package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "LinkFederatedCredentialAidlRequestCreator")
public final class zzcl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcl> CREATOR = new zzck();
    @SafeParcelable.Field(getter = "getCachedState", id = 1)
    private final String zzii;
    @SafeParcelable.Field(getter = "getVerifyAssertionRequest", id = 2)
    private final zzfm zzki;

    @SafeParcelable.Constructor
    public zzcl(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) zzfm zzfm) {
        this.zzii = str;
        this.zzki = zzfm;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzii, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzki, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzcp() {
        return this.zzii;
    }

    public final zzfm zzdh() {
        return this.zzki;
    }
}

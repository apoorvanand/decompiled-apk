package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.UserProfileChangeRequest;

@SafeParcelable.Class(creator = "UpdateProfileAidlRequestCreator")
public final class zzdv extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdv> CREATOR = new zzdu();
    @SafeParcelable.Field(getter = "getCachedState", id = 2)
    private final String zzii;
    @SafeParcelable.Field(getter = "getUserProfileChangeRequest", id = 1)
    private final UserProfileChangeRequest zzkt;

    @SafeParcelable.Constructor
    public zzdv(@SafeParcelable.Param(id = 1) UserProfileChangeRequest userProfileChangeRequest, @SafeParcelable.Param(id = 2) String str) {
        this.zzkt = userProfileChangeRequest;
        this.zzii = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzkt, i, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzii, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzcp() {
        return this.zzii;
    }

    public final UserProfileChangeRequest zzdn() {
        return this.zzkt;
    }
}

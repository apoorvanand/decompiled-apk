package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "UnlinkFederatedCredentialAidlRequestCreator")
public final class zzdt extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdt> CREATOR = new zzds();
    @SafeParcelable.Field(getter = "getCachedState", id = 2)
    private final String zzii;
    @SafeParcelable.Field(getter = "getProvider", id = 1)
    private final String zzks;

    @SafeParcelable.Constructor
    public zzdt(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2) {
        this.zzks = str;
        this.zzii = str2;
    }

    public final String getProvider() {
        return this.zzks;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzks, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzii, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzcp() {
        return this.zzii;
    }
}

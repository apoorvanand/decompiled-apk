package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "GetAccessTokenAidlRequestCreator")
public final class zzcf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcf> CREATOR = new zzce();
    @SafeParcelable.Field(getter = "getRefreshToken", id = 1)
    private final String zzkh;

    @SafeParcelable.Constructor
    public zzcf(@SafeParcelable.Param(id = 1) String str) {
        this.zzkh = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzkh, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzs() {
        return this.zzkh;
    }
}

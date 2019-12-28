package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "LinkEmailAuthCredentialAidlRequestCreator")
public final class zzcj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcj> CREATOR = new zzci();
    @SafeParcelable.Field(getter = "getEmail", id = 1)
    private final String zzif;
    @SafeParcelable.Field(getter = "getPassword", id = 2)
    private final String zzig;
    @SafeParcelable.Field(getter = "getCachedState", id = 3)
    private final String zzii;

    @SafeParcelable.Constructor
    public zzcj(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) String str3) {
        this.zzif = str;
        this.zzig = str2;
        this.zzii = str3;
    }

    public final String getEmail() {
        return this.zzif;
    }

    public final String getPassword() {
        return this.zzig;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzif, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzig, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzii, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzcp() {
        return this.zzii;
    }
}

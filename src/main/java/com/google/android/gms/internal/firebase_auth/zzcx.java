package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SetFirebaseUiVersionAidlRequestCreator")
public final class zzcx extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcx> CREATOR = new zzcw();
    @SafeParcelable.Field(getter = "getFirebaseUiVersion", id = 1)
    private final String zzkm;

    @SafeParcelable.Constructor
    public zzcx(@SafeParcelable.Param(id = 1) String str) {
        this.zzkm = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzkm, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzdl() {
        return this.zzkm;
    }
}

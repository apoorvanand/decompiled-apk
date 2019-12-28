package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ReloadAidlRequestCreator")
public final class zzcp extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcp> CREATOR = new zzco();
    @SafeParcelable.Field(getter = "getCachedState", id = 1)
    private final String zzii;

    @SafeParcelable.Constructor
    public zzcp(@SafeParcelable.Param(id = 1) String str) {
        this.zzii = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzii, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzcp() {
        return this.zzii;
    }
}

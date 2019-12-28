package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SignInWithCustomTokenAidlRequestCreator")
public final class zzdd extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdd> CREATOR = new zzdc();
    @SafeParcelable.Field(getter = "getTenantId", id = 2)
    @Nullable
    private final String zzhy;
    @SafeParcelable.Field(getter = "getToken", id = 1)
    private final String zzji;

    @SafeParcelable.Constructor
    public zzdd(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) @Nullable String str2) {
        this.zzji = str;
        this.zzhy = str2;
    }

    public final String getToken() {
        return this.zzji;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzji, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzhy, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final String zzba() {
        return this.zzhy;
    }
}

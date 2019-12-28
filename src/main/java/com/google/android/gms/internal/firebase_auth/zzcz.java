package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SignInAnonymouslyAidlRequestCreator")
public final class zzcz extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcz> CREATOR = new zzcy();
    @SafeParcelable.Field(getter = "getTenantId", id = 1)
    @Nullable
    private final String zzhy;

    @SafeParcelable.Constructor
    public zzcz(@SafeParcelable.Param(id = 1) @Nullable String str) {
        this.zzhy = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzhy, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final String zzba() {
        return this.zzhy;
    }
}

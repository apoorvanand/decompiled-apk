package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SendVerificationCodeAidlRequestCreator")
public final class zzcv extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcv> CREATOR = new zzcu();
    @SafeParcelable.Field(getter = "getSendVerificationCodeRequest", id = 1)
    private final zzfe zzkl;

    @SafeParcelable.Constructor
    public zzcv(@SafeParcelable.Param(id = 1) zzfe zzfe) {
        this.zzkl = zzfe;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzkl, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzfe zzdk() {
        return this.zzkl;
    }
}

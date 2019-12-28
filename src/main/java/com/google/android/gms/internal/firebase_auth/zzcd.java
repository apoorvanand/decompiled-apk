package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.PhoneAuthCredential;

@SafeParcelable.Class(creator = "FinalizeMfaSignInAidlRequestCreator")
public final class zzcd extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzcd> CREATOR = new zzcc();
    @SafeParcelable.Field(getter = "getPhoneAuthCredential", id = 1)
    private final PhoneAuthCredential zzke;
    @SafeParcelable.Field(getter = "getPendingCredential", id = 2)
    private final String zzkg;

    @SafeParcelable.Constructor
    public zzcd(@SafeParcelable.Param(id = 1) PhoneAuthCredential phoneAuthCredential, @SafeParcelable.Param(id = 2) String str) {
        this.zzke = phoneAuthCredential;
        this.zzkg = str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzke, i, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzkg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

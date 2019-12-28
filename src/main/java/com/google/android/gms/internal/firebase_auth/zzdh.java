package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.EmailAuthCredential;

@SafeParcelable.Class(creator = "SignInWithEmailLinkAidlRequestCreator")
public final class zzdh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdh> CREATOR = new zzdg();
    @SafeParcelable.Field(getter = "getCredential", id = 1)
    private final EmailAuthCredential zzkn;

    @SafeParcelable.Constructor
    public zzdh(@SafeParcelable.Param(id = 1) EmailAuthCredential emailAuthCredential) {
        this.zzkn = emailAuthCredential;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzkn, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final EmailAuthCredential zzdm() {
        return this.zzkn;
    }
}

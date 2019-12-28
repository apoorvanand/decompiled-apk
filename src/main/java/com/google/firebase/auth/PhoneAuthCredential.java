package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "PhoneAuthCredentialCreator")
public class PhoneAuthCredential extends AuthCredential implements Cloneable {
    public static final Parcelable.Creator<PhoneAuthCredential> CREATOR = new zzaa();
    @SafeParcelable.Field(getter = "getSessionInfo", id = 1)
    private String zzjl;
    @SafeParcelable.Field(getter = "getSmsCode", id = 2)
    private String zzjm;
    @SafeParcelable.Field(getter = "getHasVerificationProof", id = 3)
    private boolean zzjn;
    @SafeParcelable.Field(getter = "getPhoneNumber", id = 4)
    private String zzjo;
    @SafeParcelable.Field(getter = "getAutoCreate", id = 5)
    private boolean zzjp;
    @SafeParcelable.Field(getter = "getTemporaryProof", id = 6)
    private String zzjq;

    @SafeParcelable.Constructor
    PhoneAuthCredential(@SafeParcelable.Param(id = 1) @Nullable String str, @SafeParcelable.Param(id = 2) @Nullable String str2, @SafeParcelable.Param(id = 3) boolean z, @SafeParcelable.Param(id = 4) @Nullable String str3, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) @Nullable String str4) {
        Preconditions.checkArgument((z && !TextUtils.isEmpty(str3)) || (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) || (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)), "Cannot create PhoneAuthCredential without either verificationProof, sessionInfo, ortemprary proof.");
        this.zzjl = str;
        this.zzjm = str2;
        this.zzjn = z;
        this.zzjo = str3;
        this.zzjp = z2;
        this.zzjq = str4;
    }

    public /* synthetic */ Object clone() {
        return new PhoneAuthCredential(this.zzjl, getSmsCode(), this.zzjn, this.zzjo, this.zzjp, this.zzjq);
    }

    @NonNull
    public String getProvider() {
        return "phone";
    }

    public String getSignInMethod() {
        return "phone";
    }

    @Nullable
    public String getSmsCode() {
        return this.zzjm;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzjl, false);
        SafeParcelWriter.writeString(parcel, 2, getSmsCode(), false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzjn);
        SafeParcelWriter.writeString(parcel, 4, this.zzjo, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzjp);
        SafeParcelWriter.writeString(parcel, 6, this.zzjq, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final PhoneAuthCredential zzn(boolean z) {
        this.zzjp = false;
        return this;
    }
}

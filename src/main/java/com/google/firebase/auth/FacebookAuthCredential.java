package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzfm;

@SafeParcelable.Class(creator = "FacebookAuthCredentialCreator")
public class FacebookAuthCredential extends AuthCredential {
    public static final Parcelable.Creator<FacebookAuthCredential> CREATOR = new zzh();
    @SafeParcelable.Field(getter = "getAccessToken", id = 1)
    private final String zzic;

    @SafeParcelable.Constructor
    FacebookAuthCredential(@SafeParcelable.Param(id = 1) @NonNull String str) {
        this.zzic = Preconditions.checkNotEmpty(str);
    }

    public static zzfm zza(@NonNull FacebookAuthCredential facebookAuthCredential, @Nullable String str) {
        Preconditions.checkNotNull(facebookAuthCredential);
        return new zzfm((String) null, facebookAuthCredential.zzic, facebookAuthCredential.getProvider(), (String) null, (String) null, (String) null, str, (String) null);
    }

    public String getProvider() {
        return "facebook.com";
    }

    public String getSignInMethod() {
        return "facebook.com";
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzic, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

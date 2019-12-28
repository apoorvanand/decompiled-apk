package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzfm;

@SafeParcelable.Class(creator = "TwitterAuthCredentialCreator")
public class TwitterAuthCredential extends AuthCredential {
    public static final Parcelable.Creator<TwitterAuthCredential> CREATOR = new zzae();
    @SafeParcelable.Field(getter = "getToken", id = 1)
    private String zzji;
    @SafeParcelable.Field(getter = "getSecret", id = 2)
    private String zzjy;

    @SafeParcelable.Constructor
    TwitterAuthCredential(@SafeParcelable.Param(id = 1) @NonNull String str, @SafeParcelable.Param(id = 2) @NonNull String str2) {
        this.zzji = Preconditions.checkNotEmpty(str);
        this.zzjy = Preconditions.checkNotEmpty(str2);
    }

    public static zzfm zza(@NonNull TwitterAuthCredential twitterAuthCredential, @Nullable String str) {
        Preconditions.checkNotNull(twitterAuthCredential);
        return new zzfm((String) null, twitterAuthCredential.zzji, twitterAuthCredential.getProvider(), (String) null, twitterAuthCredential.zzjy, (String) null, str, (String) null);
    }

    public String getProvider() {
        return "twitter.com";
    }

    public String getSignInMethod() {
        return "twitter.com";
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzji, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzjy, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

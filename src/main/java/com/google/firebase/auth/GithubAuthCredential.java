package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzfm;

@SafeParcelable.Class(creator = "GithubAuthCredentialCreator")
public class GithubAuthCredential extends AuthCredential {
    public static final Parcelable.Creator<GithubAuthCredential> CREATOR = new zzs();
    @SafeParcelable.Field(getter = "getToken", id = 1)
    private String zzji;

    @SafeParcelable.Constructor
    GithubAuthCredential(@SafeParcelable.Param(id = 1) @NonNull String str) {
        this.zzji = Preconditions.checkNotEmpty(str);
    }

    public static zzfm zza(@NonNull GithubAuthCredential githubAuthCredential, @Nullable String str) {
        Preconditions.checkNotNull(githubAuthCredential);
        return new zzfm((String) null, githubAuthCredential.zzji, githubAuthCredential.getProvider(), (String) null, (String) null, (String) null, str, (String) null);
    }

    public String getProvider() {
        return "github.com";
    }

    public String getSignInMethod() {
        return "github.com";
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzji, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

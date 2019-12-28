package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzfm;

@SafeParcelable.Class(creator = "GoogleAuthCredentialCreator")
public class GoogleAuthCredential extends AuthCredential {
    public static final Parcelable.Creator<GoogleAuthCredential> CREATOR = new zzt();
    @SafeParcelable.Field(getter = "getIdToken", id = 1)
    private final String zzib;
    @SafeParcelable.Field(getter = "getAccessToken", id = 2)
    private final String zzic;

    @SafeParcelable.Constructor
    GoogleAuthCredential(@SafeParcelable.Param(id = 1) @Nullable String str, @SafeParcelable.Param(id = 2) @Nullable String str2) {
        if (str == null && str2 == null) {
            throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
        }
        this.zzib = zzb(str, "idToken");
        this.zzic = zzb(str2, "accessToken");
    }

    public static zzfm zza(@NonNull GoogleAuthCredential googleAuthCredential, @Nullable String str) {
        Preconditions.checkNotNull(googleAuthCredential);
        return new zzfm(googleAuthCredential.zzib, googleAuthCredential.zzic, googleAuthCredential.getProvider(), (String) null, (String) null, (String) null, str, (String) null);
    }

    private static String zzb(String str, String str2) {
        if (str == null || !TextUtils.isEmpty(str)) {
            return str;
        }
        throw new IllegalArgumentException(String.valueOf(str2).concat(" must not be empty"));
    }

    public String getProvider() {
        return "google.com";
    }

    public String getSignInMethod() {
        return "google.com";
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzib, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzic, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

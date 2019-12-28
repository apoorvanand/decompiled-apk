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

@SafeParcelable.Class(creator = "DefaultOAuthCredentialCreator")
public class zzf extends OAuthCredential {
    public static final Parcelable.Creator<zzf> CREATOR = new zze();
    @SafeParcelable.Field(getter = "getProvider", id = 1)
    @Nullable
    private final String zzia;
    @SafeParcelable.Field(getter = "getIdToken", id = 2)
    @Nullable
    private final String zzib;
    @SafeParcelable.Field(getter = "getAccessToken", id = 3)
    @Nullable
    private final String zzic;
    @SafeParcelable.Field(getter = "getWebSignInCredential", id = 4)
    @Nullable
    private final zzfm zzid;
    @SafeParcelable.Field(getter = "getPendingToken", id = 5)
    @Nullable
    private final String zzie;

    @SafeParcelable.Constructor
    zzf(@SafeParcelable.Param(id = 1) @NonNull String str, @SafeParcelable.Param(id = 2) @Nullable String str2, @SafeParcelable.Param(id = 3) @Nullable String str3, @SafeParcelable.Param(id = 4) @Nullable zzfm zzfm, @SafeParcelable.Param(id = 5) @Nullable String str4) {
        this.zzia = str;
        this.zzib = str2;
        this.zzic = str3;
        this.zzid = zzfm;
        this.zzie = str4;
    }

    public static zzfm zza(@NonNull zzf zzf, @Nullable String str) {
        Preconditions.checkNotNull(zzf);
        zzfm zzfm = zzf.zzid;
        if (zzfm != null) {
            return zzfm;
        }
        return new zzfm(zzf.getIdToken(), zzf.getAccessToken(), zzf.getProvider(), (String) null, (String) null, (String) null, str, zzf.zzie);
    }

    public static zzf zza(@NonNull zzfm zzfm) {
        Preconditions.checkNotNull(zzfm, "Must specify a non-null webSignInCredential");
        return new zzf((String) null, (String) null, (String) null, zzfm, (String) null);
    }

    public static zzf zza(String str, String str2, String str3) {
        return zzb(str, str2, str3, (String) null);
    }

    public static zzf zza(String str, String str2, String str3, String str4) {
        return zzb(str, str2, str3, str4);
    }

    private static zzf zzb(String str, String str2, String str3, @Nullable String str4) {
        Preconditions.checkNotEmpty(str, "Must specify a non-empty providerId");
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            return new zzf(str, str2, str3, (zzfm) null, str4);
        }
        throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
    }

    @Nullable
    public String getAccessToken() {
        return this.zzic;
    }

    @Nullable
    public String getIdToken() {
        return this.zzib;
    }

    public String getProvider() {
        return this.zzia;
    }

    public String getSignInMethod() {
        return this.zzia;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getProvider(), false);
        SafeParcelWriter.writeString(parcel, 2, getIdToken(), false);
        SafeParcelWriter.writeString(parcel, 3, getAccessToken(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzid, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzie, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
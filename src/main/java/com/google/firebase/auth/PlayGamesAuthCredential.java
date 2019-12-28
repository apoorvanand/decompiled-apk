package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzfm;

@SafeParcelable.Class(creator = "PlayGamesAuthCredentialCreator")
public class PlayGamesAuthCredential extends AuthCredential {
    public static final Parcelable.Creator<PlayGamesAuthCredential> CREATOR = new zzad();
    @SafeParcelable.Field(getter = "getServerAuthCode", id = 1)
    private final String zzjx;

    @SafeParcelable.Constructor
    PlayGamesAuthCredential(@SafeParcelable.Param(id = 1) @NonNull String str) {
        this.zzjx = Preconditions.checkNotEmpty(str);
    }

    public static zzfm zza(@NonNull PlayGamesAuthCredential playGamesAuthCredential, @Nullable String str) {
        Preconditions.checkNotNull(playGamesAuthCredential);
        return new zzfm((String) null, (String) null, playGamesAuthCredential.getProvider(), (String) null, (String) null, playGamesAuthCredential.zzjx, str, (String) null);
    }

    public String getProvider() {
        return "playgames.google.com";
    }

    public String getSignInMethod() {
        return "playgames.google.com";
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzjx, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

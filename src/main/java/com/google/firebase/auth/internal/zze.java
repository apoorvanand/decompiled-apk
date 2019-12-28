package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AdditionalUserInfo;
import java.util.Map;
import javax.annotation.Nullable;

@SafeParcelable.Class(creator = "DefaultAdditionalUserInfoCreator")
public final class zze implements AdditionalUserInfo {
    public static final Parcelable.Creator<zze> CREATOR = new zzd();
    @SafeParcelable.Field(getter = "getProviderId", id = 1)
    private final String zzia;
    @SafeParcelable.Field(getter = "isNewUser", id = 3)
    private boolean zzrg;
    @SafeParcelable.Field(getter = "getRawUserInfo", id = 2)
    private final String zzsd;
    private Map<String, Object> zztc;

    @SafeParcelable.Constructor
    public zze(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) boolean z) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        this.zzia = str;
        this.zzsd = str2;
        this.zztc = zzam.zzde(str2);
        this.zzrg = z;
    }

    public zze(boolean z) {
        this.zzrg = z;
        this.zzsd = null;
        this.zzia = null;
        this.zztc = null;
    }

    public final int describeContents() {
        return 0;
    }

    @Nullable
    public final Map<String, Object> getProfile() {
        return this.zztc;
    }

    @Nullable
    public final String getProviderId() {
        return this.zzia;
    }

    @Nullable
    public final String getUsername() {
        Map<String, Object> map;
        String str;
        if ("github.com".equals(this.zzia)) {
            map = this.zztc;
            str = FirebaseAnalytics.Event.LOGIN;
        } else if (!"twitter.com".equals(this.zzia)) {
            return null;
        } else {
            map = this.zztc;
            str = "screen_name";
        }
        return (String) map.get(str);
    }

    public final boolean isNewUser() {
        return this.zzrg;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getProviderId(), false);
        SafeParcelWriter.writeString(parcel, 2, this.zzsd, false);
        SafeParcelWriter.writeBoolean(parcel, 3, isNewUser());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

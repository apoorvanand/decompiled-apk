package com.google.android.gms.internal.firebase_auth;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ProviderUserInfoCreator")
@SafeParcelable.Reserved({1})
public final class zzew extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzew> CREATOR = new zzez();
    @SafeParcelable.Field(getter = "getProviderId", id = 5)
    private String zzia;
    @SafeParcelable.Field(getter = "getEmail", id = 8)
    private String zzif;
    @SafeParcelable.Field(getter = "getPhoneNumber", id = 7)
    private String zzjo;
    @SafeParcelable.Field(getter = "getDisplayName", id = 3)
    private String zzjv;
    @SafeParcelable.Field(getter = "getPhotoUrl", id = 4)
    private String zzkc;
    @SafeParcelable.Field(getter = "getFederatedId", id = 2)
    private String zzsc;
    @SafeParcelable.Field(getter = "getRawUserInfo", id = 6)
    private String zzsd;

    public zzew() {
    }

    @SafeParcelable.Constructor
    zzew(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String str3, @SafeParcelable.Param(id = 5) String str4, @SafeParcelable.Param(id = 6) String str5, @SafeParcelable.Param(id = 7) String str6, @SafeParcelable.Param(id = 8) String str7) {
        this.zzsc = str;
        this.zzjv = str2;
        this.zzkc = str3;
        this.zzia = str4;
        this.zzsd = str5;
        this.zzjo = str6;
        this.zzif = str7;
    }

    @Nullable
    public final String getDisplayName() {
        return this.zzjv;
    }

    @Nullable
    public final String getEmail() {
        return this.zzif;
    }

    public final String getPhoneNumber() {
        return this.zzjo;
    }

    @Nullable
    public final Uri getPhotoUri() {
        if (!TextUtils.isEmpty(this.zzkc)) {
            return Uri.parse(this.zzkc);
        }
        return null;
    }

    public final String getProviderId() {
        return this.zzia;
    }

    @Nullable
    public final String getRawUserInfo() {
        return this.zzsd;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzsc, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzjv, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzkc, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzia, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzsd, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzjo, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzif, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzbo() {
        return this.zzsc;
    }

    public final void zzco(String str) {
        this.zzsd = str;
    }
}

package com.google.firebase.auth.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zzbl;
import com.google.android.gms.internal.firebase_auth.zzem;
import com.google.android.gms.internal.firebase_auth.zzew;
import com.google.firebase.auth.UserInfo;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "DefaultAuthUserInfoCreator")
public final class zzi extends AbstractSafeParcelable implements UserInfo {
    public static final Parcelable.Creator<zzi> CREATOR = new zzh();
    @SafeParcelable.Field(getter = "getProviderId", id = 2)
    @NonNull
    private String zzia;
    @SafeParcelable.Field(getter = "getEmail", id = 5)
    @Nullable
    private String zzif;
    @SafeParcelable.Field(getter = "getPhoneNumber", id = 6)
    @Nullable
    private String zzjo;
    @SafeParcelable.Field(getter = "getDisplayName", id = 3)
    @Nullable
    private String zzjv;
    @Nullable
    private Uri zzjz;
    @SafeParcelable.Field(getter = "getPhotoUrlString", id = 4)
    @Nullable
    private String zzkc;
    @SafeParcelable.Field(getter = "isEmailVerified", id = 7)
    private boolean zzrp;
    @SafeParcelable.Field(getter = "getRawUserInfo", id = 8)
    @Nullable
    private String zzsd;
    @SafeParcelable.Field(getter = "getUid", id = 1)
    @NonNull
    private String zztg;

    public zzi(zzem zzem, String str) {
        Preconditions.checkNotNull(zzem);
        Preconditions.checkNotEmpty(str);
        this.zztg = Preconditions.checkNotEmpty(zzem.getLocalId());
        this.zzia = str;
        this.zzif = zzem.getEmail();
        this.zzjv = zzem.getDisplayName();
        Uri photoUri = zzem.getPhotoUri();
        if (photoUri != null) {
            this.zzkc = photoUri.toString();
            this.zzjz = photoUri;
        }
        this.zzrp = zzem.isEmailVerified();
        this.zzsd = null;
        this.zzjo = zzem.getPhoneNumber();
    }

    public zzi(zzew zzew) {
        Preconditions.checkNotNull(zzew);
        this.zztg = zzew.zzbo();
        this.zzia = Preconditions.checkNotEmpty(zzew.getProviderId());
        this.zzjv = zzew.getDisplayName();
        Uri photoUri = zzew.getPhotoUri();
        if (photoUri != null) {
            this.zzkc = photoUri.toString();
            this.zzjz = photoUri;
        }
        this.zzif = zzew.getEmail();
        this.zzjo = zzew.getPhoneNumber();
        this.zzrp = false;
        this.zzsd = zzew.getRawUserInfo();
    }

    @SafeParcelable.Constructor
    @VisibleForTesting
    public zzi(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 5) @Nullable String str3, @SafeParcelable.Param(id = 4) @Nullable String str4, @SafeParcelable.Param(id = 3) @Nullable String str5, @SafeParcelable.Param(id = 6) @Nullable String str6, @SafeParcelable.Param(id = 7) boolean z, @SafeParcelable.Param(id = 8) @Nullable String str7) {
        this.zztg = str;
        this.zzia = str2;
        this.zzif = str3;
        this.zzjo = str4;
        this.zzjv = str5;
        this.zzkc = str6;
        if (!TextUtils.isEmpty(this.zzkc)) {
            this.zzjz = Uri.parse(this.zzkc);
        }
        this.zzrp = z;
        this.zzsd = str7;
    }

    @Nullable
    public static zzi zzda(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new zzi(jSONObject.optString("userId"), jSONObject.optString("providerId"), jSONObject.optString("email"), jSONObject.optString("phoneNumber"), jSONObject.optString("displayName"), jSONObject.optString("photoUrl"), jSONObject.optBoolean("isEmailVerified"), jSONObject.optString("rawUserInfo"));
        } catch (JSONException e) {
            Log.d("DefaultAuthUserInfo", "Failed to unpack UserInfo from JSON");
            throw new zzbl((Throwable) e);
        }
    }

    @Nullable
    public final String getDisplayName() {
        return this.zzjv;
    }

    @Nullable
    public final String getEmail() {
        return this.zzif;
    }

    @Nullable
    public final String getPhoneNumber() {
        return this.zzjo;
    }

    @Nullable
    public final Uri getPhotoUrl() {
        if (!TextUtils.isEmpty(this.zzkc) && this.zzjz == null) {
            this.zzjz = Uri.parse(this.zzkc);
        }
        return this.zzjz;
    }

    @NonNull
    public final String getProviderId() {
        return this.zzia;
    }

    @Nullable
    public final String getRawUserInfo() {
        return this.zzsd;
    }

    @NonNull
    public final String getUid() {
        return this.zztg;
    }

    public final boolean isEmailVerified() {
        return this.zzrp;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getUid(), false);
        SafeParcelWriter.writeString(parcel, 2, getProviderId(), false);
        SafeParcelWriter.writeString(parcel, 3, getDisplayName(), false);
        SafeParcelWriter.writeString(parcel, 4, this.zzkc, false);
        SafeParcelWriter.writeString(parcel, 5, getEmail(), false);
        SafeParcelWriter.writeString(parcel, 6, getPhoneNumber(), false);
        SafeParcelWriter.writeBoolean(parcel, 7, isEmailVerified());
        SafeParcelWriter.writeString(parcel, 8, this.zzsd, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final String zzew() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("userId", this.zztg);
            jSONObject.putOpt("providerId", this.zzia);
            jSONObject.putOpt("displayName", this.zzjv);
            jSONObject.putOpt("photoUrl", this.zzkc);
            jSONObject.putOpt("email", this.zzif);
            jSONObject.putOpt("phoneNumber", this.zzjo);
            jSONObject.putOpt("isEmailVerified", Boolean.valueOf(this.zzrp));
            jSONObject.putOpt("rawUserInfo", this.zzsd);
            return jSONObject.toString();
        } catch (JSONException e) {
            Log.d("DefaultAuthUserInfo", "Failed to jsonify this object");
            throw new zzbl((Throwable) e);
        }
    }
}

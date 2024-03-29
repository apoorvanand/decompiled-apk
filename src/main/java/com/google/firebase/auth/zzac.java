package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzbl;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "PhoneMultiFactorInfoCreator")
public final class zzac extends zzx {
    public static final Parcelable.Creator<zzac> CREATOR = new zzab();
    @SafeParcelable.Field(getter = "getPhoneNumber", id = 4)
    private final String zzjo;
    @SafeParcelable.Field(getter = "getUid", id = 1)
    private final String zzju;
    @SafeParcelable.Field(getter = "getDisplayName", id = 2)
    @Nullable
    private final String zzjv;
    @SafeParcelable.Field(getter = "getEnrollmentTimestamp", id = 3)
    private final long zzjw;

    @SafeParcelable.Constructor
    public zzac(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) @Nullable String str2, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 4) String str3) {
        this.zzju = Preconditions.checkNotEmpty(str);
        this.zzjv = str2;
        this.zzjw = j;
        this.zzjo = Preconditions.checkNotEmpty(str3);
    }

    public static zzac zza(JSONObject jSONObject) {
        if (jSONObject.has("enrollmentTimestamp")) {
            return new zzac(jSONObject.optString("uid"), jSONObject.optString("displayName"), jSONObject.optLong("enrollmentTimestamp"), jSONObject.optString("phoneNumber"));
        }
        throw new IllegalArgumentException("An enrollment timestamp in seconds of UTC time since Unix epoch is required to build a PhoneMultiFactorInfo instance.");
    }

    @Nullable
    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("factorIdKey", "phone");
            jSONObject.putOpt("uid", this.zzju);
            jSONObject.putOpt("displayName", this.zzjv);
            jSONObject.putOpt("enrollmentTimestamp", Long.valueOf(this.zzjw));
            jSONObject.putOpt("phoneNumber", this.zzjo);
            return jSONObject;
        } catch (JSONException e) {
            Log.d("PhoneMultiFactorInfo", "Failed to jsonify this object");
            throw new zzbl((Throwable) e);
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzju, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzjv, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzjw);
        SafeParcelWriter.writeString(parcel, 4, this.zzjo, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

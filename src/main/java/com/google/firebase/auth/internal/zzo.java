package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.FirebaseUserMetadata;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "DefaultFirebaseUserMetadataCreator")
public final class zzo implements FirebaseUserMetadata {
    public static final Parcelable.Creator<zzo> CREATOR = new zzn();
    @SafeParcelable.Field(getter = "getCreationTimestamp", id = 2)
    private long zzrr;
    @SafeParcelable.Field(getter = "getLastSignInTimestamp", id = 1)
    private long zztr;

    @SafeParcelable.Constructor
    public zzo(@SafeParcelable.Param(id = 1) long j, @SafeParcelable.Param(id = 2) long j2) {
        this.zztr = j;
        this.zzrr = j2;
    }

    public static zzo zzb(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return new zzo(jSONObject.getLong("lastSignInTimestamp"), jSONObject.getLong("creationTimestamp"));
        } catch (JSONException unused) {
            return null;
        }
    }

    public final int describeContents() {
        return 0;
    }

    public final long getCreationTimestamp() {
        return this.zzrr;
    }

    public final long getLastSignInTimestamp() {
        return this.zztr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, getLastSignInTimestamp());
        SafeParcelWriter.writeLong(parcel, 2, getCreationTimestamp());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final JSONObject zzfg() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("lastSignInTimestamp", this.zztr);
            jSONObject.put("creationTimestamp", this.zzrr);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}

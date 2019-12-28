package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.Nullable;
import com.facebook.AccessToken;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.firebase_auth.zzlg;
import com.google.firebase.auth.api.internal.zzdv;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "GetTokenResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzes extends AbstractSafeParcelable implements zzdv<zzes, zzlg.zzb> {
    public static final Parcelable.Creator<zzes> CREATOR = new zzev();
    @SafeParcelable.Field(getter = "getAccessToken", id = 3)
    private String zzic;
    @SafeParcelable.Field(getter = "getRefreshToken", id = 2)
    private String zzkh;
    @SafeParcelable.Field(getter = "getExpiresIn", id = 4)
    private Long zzrv;
    @SafeParcelable.Field(getter = "getTokenType", id = 5)
    private String zzrw;
    @SafeParcelable.Field(getter = "getIssuedAt", id = 6)
    private Long zzrx;

    public zzes() {
        this.zzrx = Long.valueOf(System.currentTimeMillis());
    }

    public zzes(String str, String str2, Long l, String str3) {
        this(str, str2, l, str3, Long.valueOf(System.currentTimeMillis()));
    }

    @SafeParcelable.Constructor
    zzes(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) Long l, @SafeParcelable.Param(id = 5) String str3, @SafeParcelable.Param(id = 6) Long l2) {
        this.zzkh = str;
        this.zzic = str2;
        this.zzrv = l;
        this.zzrw = str3;
        this.zzrx = l2;
    }

    public static zzes zzcn(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            zzes zzes = new zzes();
            zzes.zzkh = jSONObject.optString("refresh_token", (String) null);
            zzes.zzic = jSONObject.optString("access_token", (String) null);
            zzes.zzrv = Long.valueOf(jSONObject.optLong(AccessToken.EXPIRES_IN_KEY));
            zzes.zzrw = jSONObject.optString("token_type", (String) null);
            zzes.zzrx = Long.valueOf(jSONObject.optLong("issued_at"));
            return zzes;
        } catch (JSONException e) {
            Log.d("GetTokenResponse", "Failed to read GetTokenResponse from JSONObject");
            throw new zzbl((Throwable) e);
        }
    }

    public final String getAccessToken() {
        return this.zzic;
    }

    public final boolean isValid() {
        return DefaultClock.getInstance().currentTimeMillis() + 300000 < this.zzrx.longValue() + (this.zzrv.longValue() * 1000);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzkh, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzic, false);
        SafeParcelWriter.writeLongObject(parcel, 4, Long.valueOf(zzt()), false);
        SafeParcelWriter.writeString(parcel, 5, this.zzrw, false);
        SafeParcelWriter.writeLongObject(parcel, 6, Long.valueOf(this.zzrx.longValue()), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* synthetic */ zzdv zza(zzjc zzjc) {
        if (zzjc instanceof zzlg.zzb) {
            zzlg.zzb zzb = (zzlg.zzb) zzjc;
            this.zzkh = Strings.emptyToNull(zzb.zzs());
            this.zzic = Strings.emptyToNull(zzb.getAccessToken());
            this.zzrv = Long.valueOf(zzb.zzt());
            this.zzrw = Strings.emptyToNull(zzb.zzeu());
            this.zzrx = Long.valueOf(System.currentTimeMillis());
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of GrantTokenResponse.");
    }

    public final void zzcm(String str) {
        this.zzkh = Preconditions.checkNotEmpty(str);
    }

    public final zzjm<zzlg.zzb> zzee() {
        return zzlg.zzb.zzm();
    }

    @Nullable
    public final String zzeu() {
        return this.zzrw;
    }

    public final long zzev() {
        return this.zzrx.longValue();
    }

    public final String zzew() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("refresh_token", this.zzkh);
            jSONObject.put("access_token", this.zzic);
            jSONObject.put(AccessToken.EXPIRES_IN_KEY, this.zzrv);
            jSONObject.put("token_type", this.zzrw);
            jSONObject.put("issued_at", this.zzrx);
            return jSONObject.toString();
        } catch (JSONException e) {
            Log.d("GetTokenResponse", "Failed to convert GetTokenResponse to JSON");
            throw new zzbl((Throwable) e);
        }
    }

    public final String zzs() {
        return this.zzkh;
    }

    public final long zzt() {
        Long l = this.zzrv;
        if (l == null) {
            return 0;
        }
        return l.longValue();
    }
}

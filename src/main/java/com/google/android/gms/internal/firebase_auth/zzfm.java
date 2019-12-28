package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzfd;

@SafeParcelable.Class(creator = "VerifyAssertionRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzfm extends AbstractSafeParcelable implements zzfd<zzp.C0009zzp> {
    public static final Parcelable.Creator<zzfm> CREATOR = new zzfp();
    @SafeParcelable.Field(getter = "getTenantId", id = 15)
    private String zzhy;
    @SafeParcelable.Field(getter = "getProviderId", id = 6)
    private String zzia;
    @SafeParcelable.Field(getter = "getIdToken", id = 4)
    private String zzib;
    @SafeParcelable.Field(getter = "getAccessToken", id = 5)
    private String zzic;
    @SafeParcelable.Field(getter = "getPendingToken", id = 17)
    @Nullable
    private String zzie;
    @SafeParcelable.Field(getter = "getEmail", id = 7)
    @Nullable
    private String zzif;
    @SafeParcelable.Field(getter = "getAutoCreate", id = 11)
    private boolean zzjp;
    @SafeParcelable.Field(getter = "getReturnSecureToken", id = 10)
    private boolean zzsj;
    @SafeParcelable.Field(getter = "getRequestUri", id = 2)
    private String zzsn;
    @SafeParcelable.Field(getter = "getCurrentIdToken", id = 3)
    private String zzso;
    @SafeParcelable.Field(getter = "getPostBody", id = 8)
    private String zzsp;
    @SafeParcelable.Field(getter = "getOauthTokenSecret", id = 9)
    private String zzsq;
    @SafeParcelable.Field(getter = "getAuthCode", id = 12)
    private String zzsr;
    @SafeParcelable.Field(getter = "getSessionId", id = 13)
    private String zzss;
    @SafeParcelable.Field(getter = "getIdpResponseUrl", id = 14)
    private String zzst;
    @SafeParcelable.Field(getter = "getReturnIdpCredential", id = 16)
    private boolean zzsu;

    public zzfm() {
        this.zzsj = true;
        this.zzjp = true;
    }

    public zzfm(@Nullable String str, @Nullable String str2, String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8) {
        this.zzsn = "http://localhost";
        this.zzib = str;
        this.zzic = str2;
        this.zzsq = str5;
        this.zzsr = str6;
        this.zzhy = str7;
        this.zzie = str8;
        this.zzsj = true;
        if (!TextUtils.isEmpty(this.zzib) || !TextUtils.isEmpty(this.zzic) || !TextUtils.isEmpty(this.zzsr)) {
            this.zzia = Preconditions.checkNotEmpty(str3);
            this.zzif = null;
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(this.zzib)) {
                sb.append("id_token=");
                sb.append(this.zzib);
                sb.append("&");
            }
            if (!TextUtils.isEmpty(this.zzic)) {
                sb.append("access_token=");
                sb.append(this.zzic);
                sb.append("&");
            }
            if (!TextUtils.isEmpty(this.zzif)) {
                sb.append("identifier=");
                sb.append(this.zzif);
                sb.append("&");
            }
            if (!TextUtils.isEmpty(this.zzsq)) {
                sb.append("oauth_token_secret=");
                sb.append(this.zzsq);
                sb.append("&");
            }
            if (!TextUtils.isEmpty(this.zzsr)) {
                sb.append("code=");
                sb.append(this.zzsr);
                sb.append("&");
            }
            sb.append("providerId=");
            sb.append(this.zzia);
            this.zzsp = sb.toString();
            this.zzjp = true;
            return;
        }
        throw new IllegalArgumentException("idToken, accessToken and authCode cannot all be null");
    }

    @SafeParcelable.Constructor
    zzfm(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String str3, @SafeParcelable.Param(id = 5) String str4, @SafeParcelable.Param(id = 6) String str5, @SafeParcelable.Param(id = 7) String str6, @SafeParcelable.Param(id = 8) String str7, @SafeParcelable.Param(id = 9) String str8, @SafeParcelable.Param(id = 10) boolean z, @SafeParcelable.Param(id = 11) boolean z2, @SafeParcelable.Param(id = 12) String str9, @SafeParcelable.Param(id = 13) String str10, @SafeParcelable.Param(id = 14) String str11, @SafeParcelable.Param(id = 15) String str12, @SafeParcelable.Param(id = 16) boolean z3, @SafeParcelable.Param(id = 17) String str13) {
        this.zzsn = str;
        this.zzso = str2;
        this.zzib = str3;
        this.zzic = str4;
        this.zzia = str5;
        this.zzif = str6;
        this.zzsp = str7;
        this.zzsq = str8;
        this.zzsj = z;
        this.zzjp = z2;
        this.zzsr = str9;
        this.zzss = str10;
        this.zzst = str11;
        this.zzhy = str12;
        this.zzsu = z3;
        this.zzie = str13;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzsn, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzso, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzib, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzic, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzia, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzif, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzsp, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzsq, false);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzsj);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzjp);
        SafeParcelWriter.writeString(parcel, 12, this.zzsr, false);
        SafeParcelWriter.writeString(parcel, 13, this.zzss, false);
        SafeParcelWriter.writeString(parcel, 14, this.zzst, false);
        SafeParcelWriter.writeString(parcel, 15, this.zzhy, false);
        SafeParcelWriter.writeBoolean(parcel, 16, this.zzsu);
        SafeParcelWriter.writeString(parcel, 17, this.zzie, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final zzfm zzcy(String str) {
        this.zzso = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzfm zzcz(@Nullable String str) {
        this.zzhy = str;
        return this;
    }

    public final /* synthetic */ zzjc zzeq() {
        zzp.C0009zzp.zza zzk = zzp.C0009zzp.zzat().zzi(this.zzsj).zzk(this.zzjp);
        String str = this.zzso;
        if (str != null) {
            zzk.zzbg(str);
        }
        String str2 = this.zzsn;
        if (str2 != null) {
            zzk.zzbd(str2);
        }
        String str3 = this.zzsp;
        if (str3 != null) {
            zzk.zzbe(str3);
        }
        String str4 = this.zzhy;
        if (str4 != null) {
            zzk.zzbh(str4);
        }
        String str5 = this.zzie;
        if (str5 != null) {
            zzk.zzbi(str5);
        }
        if (!TextUtils.isEmpty(this.zzss)) {
            zzk.zzbf(this.zzss);
        }
        if (!TextUtils.isEmpty(this.zzst)) {
            zzk.zzbd(this.zzst);
        }
        return (zzp.C0009zzp) ((zzhs) zzk.zzj(this.zzsu).zzih());
    }

    public final zzfm zzp(boolean z) {
        this.zzjp = false;
        return this;
    }

    public final zzfm zzq(boolean z) {
        this.zzsj = true;
        return this;
    }

    public final zzfm zzr(boolean z) {
        this.zzsu = z;
        return this;
    }
}

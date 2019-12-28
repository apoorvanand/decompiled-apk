package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.zzf;
import java.util.List;

@SafeParcelable.Class(creator = "DefaultAuthResultCreator")
public final class zzg implements AuthResult {
    public static final Parcelable.Creator<zzg> CREATOR = new zzf();
    @SafeParcelable.Field(getter = "getUser", id = 1)
    private zzm zztd;
    @SafeParcelable.Field(getter = "getAdditionalUserInfo", id = 2)
    private zze zzte;
    @SafeParcelable.Field(getter = "getOAuthCredential", id = 3)
    private zzf zztf;

    public zzg(zzm zzm) {
        this.zztd = (zzm) Preconditions.checkNotNull(zzm);
        List<zzi> zzff = this.zztd.zzff();
        this.zzte = null;
        for (int i = 0; i < zzff.size(); i++) {
            if (!TextUtils.isEmpty(zzff.get(i).getRawUserInfo())) {
                this.zzte = new zze(zzff.get(i).getProviderId(), zzff.get(i).getRawUserInfo(), zzm.isNewUser());
            }
        }
        if (this.zzte == null) {
            this.zzte = new zze(zzm.isNewUser());
        }
        this.zztf = zzm.zzdo();
    }

    @SafeParcelable.Constructor
    zzg(@SafeParcelable.Param(id = 1) zzm zzm, @SafeParcelable.Param(id = 2) zze zze, @SafeParcelable.Param(id = 3) zzf zzf) {
        this.zztd = zzm;
        this.zzte = zze;
        this.zztf = zzf;
    }

    public final int describeContents() {
        return 0;
    }

    @Nullable
    public final AdditionalUserInfo getAdditionalUserInfo() {
        return this.zzte;
    }

    public final AuthCredential getCredential() {
        return this.zztf;
    }

    @Nullable
    public final FirebaseUser getUser() {
        return this.zztd;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getUser(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getAdditionalUserInfo(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zztf, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

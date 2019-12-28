package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzdv;

@SafeParcelable.Class(creator = "VerifyCustomTokenResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzfq extends AbstractSafeParcelable implements zzdv<zzfq, zzp.zzs> {
    public static final Parcelable.Creator<zzfq> CREATOR = new zzft();
    @SafeParcelable.Field(getter = "getIdToken", id = 2)
    private String zzib;
    @SafeParcelable.Field(getter = "getRefreshToken", id = 3)
    private String zzkh;
    @SafeParcelable.Field(getter = "isNewUser", id = 5)
    private boolean zzrg;
    @SafeParcelable.Field(getter = "getExpiresIn", id = 4)
    private long zzrh;

    public zzfq() {
    }

    @SafeParcelable.Constructor
    zzfq(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) long j, @SafeParcelable.Param(id = 5) boolean z) {
        this.zzib = str;
        this.zzkh = str2;
        this.zzrh = j;
        this.zzrg = z;
    }

    public final String getIdToken() {
        return this.zzib;
    }

    public final boolean isNewUser() {
        return this.zzrg;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzib, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzkh, false);
        SafeParcelWriter.writeLong(parcel, 4, this.zzrh);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzrg);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* synthetic */ zzdv zza(zzjc zzjc) {
        if (zzjc instanceof zzp.zzs) {
            zzp.zzs zzs = (zzp.zzs) zzjc;
            this.zzib = Strings.emptyToNull(zzs.getIdToken());
            this.zzkh = Strings.emptyToNull(zzs.zzs());
            this.zzrh = zzs.zzt();
            this.zzrg = zzs.zzu();
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of VerifyCustomTokenResponse.");
    }

    public final zzjm<zzp.zzs> zzee() {
        return zzp.zzs.zzm();
    }

    @NonNull
    public final String zzs() {
        return this.zzkh;
    }

    public final long zzt() {
        return this.zzrh;
    }
}

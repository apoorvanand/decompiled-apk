package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzdv;

@SafeParcelable.Class(creator = "ResetPasswordResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzfd extends AbstractSafeParcelable implements zzdv<zzfd, zzp.zzj> {
    public static final Parcelable.Creator<zzfd> CREATOR = new zzff();
    @SafeParcelable.Field(getter = "getEmail", id = 2)
    private String zzif;
    @SafeParcelable.Field(getter = "getNewEmail", id = 3)
    private String zzku;
    @SafeParcelable.Field(getter = "getRequestType", id = 4)
    private String zzru;

    public zzfd() {
    }

    @SafeParcelable.Constructor
    zzfd(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) String str3) {
        this.zzif = str;
        this.zzku = str2;
        this.zzru = str3;
    }

    public final String getEmail() {
        return this.zzif;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzif, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzku, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzru, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* synthetic */ zzdv zza(zzjc zzjc) {
        String str;
        if (zzjc instanceof zzp.zzj) {
            zzp.zzj zzj = (zzp.zzj) zzjc;
            this.zzif = Strings.emptyToNull(zzj.getEmail());
            this.zzku = Strings.emptyToNull(zzj.zzae());
            switch (zzfc.a[zzj.zzaf().ordinal()]) {
                case 1:
                    str = "VERIFY_EMAIL";
                    break;
                case 2:
                    str = "PASSWORD_RESET";
                    break;
                default:
                    str = null;
                    break;
            }
            this.zzru = str;
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of ResetPasswordResponse.");
    }

    public final String zzae() {
        return this.zzku;
    }

    public final zzjm<zzp.zzj> zzee() {
        return zzp.zzj.zzm();
    }

    public final String zzey() {
        return this.zzru;
    }
}

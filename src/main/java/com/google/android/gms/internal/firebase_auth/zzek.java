package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzdv;
import java.util.List;

@SafeParcelable.Class(creator = "GetAccountInfoResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzek extends AbstractSafeParcelable implements zzdv<zzek, zzp.zzg> {
    public static final Parcelable.Creator<zzek> CREATOR = new zzen();
    @SafeParcelable.Field(getter = "getUserList", id = 2)
    private zzeo zzro;

    public zzek() {
    }

    @SafeParcelable.Constructor
    zzek(@SafeParcelable.Param(id = 2) zzeo zzeo) {
        this.zzro = zzeo == null ? new zzeo() : zzeo.zza(zzeo);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzro, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* synthetic */ zzdv zza(zzjc zzjc) {
        if (zzjc instanceof zzp.zzg) {
            zzp.zzg zzg = (zzp.zzg) zzjc;
            this.zzro = zzg.zzy() == 0 ? new zzeo() : zzeo.zza(zzg);
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of GetAccountInfoResponse.");
    }

    public final zzjm<zzp.zzg> zzee() {
        return zzp.zzg.zzm();
    }

    public final List<zzem> zzer() {
        return this.zzro.zzer();
    }
}

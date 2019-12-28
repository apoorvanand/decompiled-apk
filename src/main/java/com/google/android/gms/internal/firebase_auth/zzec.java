package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzdv;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "CreateAuthUriResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzec extends AbstractSafeParcelable implements zzdv<zzec, zzp.zzb> {
    public static final Parcelable.Creator<zzec> CREATOR = new zzef();
    @SafeParcelable.Field(getter = "getProviderId", id = 4)
    private String zzia;
    @SafeParcelable.Field(getter = "getAuthUri", id = 2)
    private String zzqy;
    @SafeParcelable.Field(getter = "isRegistered", id = 3)
    private boolean zzqz;
    @SafeParcelable.Field(getter = "isForExistingProvider", id = 5)
    private boolean zzra;
    @SafeParcelable.Field(getter = "getStringList", id = 6)
    private zzfk zzrb;
    @SafeParcelable.Field(getter = "getSignInMethods", id = 7)
    private List<String> zzrc;

    public zzec() {
        this.zzrb = zzfk.zzfa();
    }

    @SafeParcelable.Constructor
    public zzec(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) boolean z, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) zzfk zzfk, @SafeParcelable.Param(id = 7) List<String> list) {
        this.zzqy = str;
        this.zzqz = z;
        this.zzia = str2;
        this.zzra = z2;
        this.zzrb = zzfk == null ? zzfk.zzfa() : zzfk.zza(zzfk);
        this.zzrc = list;
    }

    @Nullable
    public final List<String> getSignInMethods() {
        return this.zzrc;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzqy, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzqz);
        SafeParcelWriter.writeString(parcel, 4, this.zzia, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzra);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzrb, i, false);
        SafeParcelWriter.writeStringList(parcel, 7, this.zzrc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* synthetic */ zzdv zza(zzjc zzjc) {
        if (zzjc instanceof zzp.zzb) {
            zzp.zzb zzb = (zzp.zzb) zzjc;
            this.zzqy = Strings.emptyToNull(zzb.zzf());
            this.zzqz = zzb.zzi();
            this.zzia = Strings.emptyToNull(zzb.getProviderId());
            this.zzra = zzb.zzj();
            this.zzrb = zzb.zzh() == 0 ? zzfk.zzfa() : new zzfk(1, new ArrayList(zzb.zzg()));
            this.zzrc = zzb.zzl() == 0 ? new ArrayList<>(0) : zzb.zzk();
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of CreateAuthUriResponse.");
    }

    public final zzjm<zzp.zzb> zzee() {
        return zzp.zzb.zzm();
    }
}

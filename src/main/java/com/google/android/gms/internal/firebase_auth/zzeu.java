package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "MfaInfoCreator")
public final class zzeu extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzeu> CREATOR = new zzex();
    @SafeParcelable.Field(getter = "getDisplayName", id = 3)
    private final String zzjv;
    @SafeParcelable.Field(getter = "getPhoneInfo", id = 1)
    @Nullable
    private final String zzrz;
    @SafeParcelable.Field(getter = "getMfaEnrollmentId", id = 2)
    @NonNull
    private final String zzsa;
    @SafeParcelable.Field(getter = "getEnrolledAtTimestampInSeconds", id = 4)
    private final long zzsb;

    @SafeParcelable.Constructor
    public zzeu(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) String str3, @SafeParcelable.Param(id = 4) long j) {
        this.zzrz = str;
        this.zzsa = Preconditions.checkNotEmpty(str2);
        this.zzjv = str3;
        this.zzsb = j;
    }

    public static zzeu zza(zzr zzr) {
        return new zzeu(zzr.zzbk(), zzr.zzbl(), zzr.getDisplayName(), zzr.zzbm().getSeconds());
    }

    public static List<zzeu> zzd(List<zzr> list) {
        if (list == null) {
            return zzay.zzce();
        }
        ArrayList arrayList = new ArrayList();
        for (zzr zza : list) {
            arrayList.add(zza(zza));
        }
        return arrayList;
    }

    public final String getDisplayName() {
        return this.zzjv;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzrz, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzsa, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzjv, false);
        SafeParcelWriter.writeLong(parcel, 4, this.zzsb);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final String zzbk() {
        return this.zzrz;
    }

    public final String zzbl() {
        return this.zzsa;
    }

    public final long zzex() {
        return this.zzsb;
    }
}

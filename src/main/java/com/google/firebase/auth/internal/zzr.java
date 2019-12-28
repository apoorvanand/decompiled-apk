package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.zzac;
import com.google.firebase.auth.zzx;
import com.google.firebase.auth.zzy;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "DefaultMultiFactorSessionCreator")
public final class zzr extends zzy {
    public static final Parcelable.Creator<zzr> CREATOR = new zzu();
    @SafeParcelable.Field(getter = "getIdToken", id = 1)
    @Nullable
    private String zzib;
    @SafeParcelable.Field(getter = "getPendingCredential", id = 2)
    @Nullable
    private String zzkg;
    @SafeParcelable.Field(getter = "getPhoneMultiFactorInfoList", id = 3)
    @Nullable
    private List<zzac> zzts;

    private zzr() {
    }

    @SafeParcelable.Constructor
    zzr(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) List<zzac> list) {
        this.zzib = str;
        this.zzkg = str2;
        this.zzts = list;
    }

    public static zzr zza(List<zzx> list, String str) {
        Preconditions.checkNotNull(list);
        Preconditions.checkNotEmpty(str);
        zzr zzr = new zzr();
        zzr.zzts = new ArrayList();
        for (zzx next : list) {
            if (next instanceof zzac) {
                zzr.zzts.add((zzac) next);
            }
        }
        zzr.zzkg = str;
        return zzr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzib, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzkg, false);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzts, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
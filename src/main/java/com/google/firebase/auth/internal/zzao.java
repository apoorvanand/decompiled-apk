package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzay;
import com.google.firebase.auth.zzac;
import com.google.firebase.auth.zzx;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "MultiFactorInfoListCreator")
public final class zzao extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzao> CREATOR = new zzar();
    @SafeParcelable.Field(getter = "getPhoneMultiFactorInfoList", id = 1)
    private final List<zzac> zzts;

    @SafeParcelable.Constructor
    zzao(@SafeParcelable.Param(id = 1) List<zzac> list) {
        this.zzts = list == null ? zzay.zzce() : list;
    }

    public static zzao zzf(List<zzx> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (zzx next : list) {
            if (next instanceof zzac) {
                arrayList.add((zzac) next);
            }
        }
        return new zzao(arrayList);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zzts, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final List<zzx> zzdp() {
        ArrayList arrayList = new ArrayList();
        for (zzac add : this.zzts) {
            arrayList.add(add);
        }
        return arrayList;
    }
}

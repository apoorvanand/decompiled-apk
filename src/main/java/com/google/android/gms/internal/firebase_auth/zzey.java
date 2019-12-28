package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SafeParcelable.Class(creator = "ProviderUserInfoListCreator")
@SafeParcelable.Reserved({1})
public final class zzey extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzey> CREATOR = new zzfb();
    @SafeParcelable.Field(getter = "getProviderUserInfos", id = 2)
    private List<zzew> zzse;

    public zzey() {
        this.zzse = new ArrayList();
    }

    @SafeParcelable.Constructor
    zzey(@SafeParcelable.Param(id = 2) List<zzew> list) {
        this.zzse = (list == null || list.isEmpty()) ? Collections.emptyList() : Collections.unmodifiableList(list);
    }

    public static zzey zza(zzey zzey) {
        List<zzew> list = zzey.zzse;
        zzey zzey2 = new zzey();
        if (list != null) {
            zzey2.zzse.addAll(list);
        }
        return zzey2;
    }

    public static zzey zze(List<zzu> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            zzu zzu = list.get(i);
            arrayList.add(new zzew(Strings.emptyToNull(zzu.zzbo()), Strings.emptyToNull(zzu.getDisplayName()), Strings.emptyToNull(zzu.zzam()), Strings.emptyToNull(zzu.getProviderId()), (String) null, Strings.emptyToNull(zzu.getPhoneNumber()), Strings.emptyToNull(zzu.getEmail())));
        }
        return new zzey(arrayList);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzse, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final List<zzew> zzes() {
        return this.zzse;
    }
}

package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.zzac;
import com.google.firebase.auth.zzf;
import com.google.firebase.auth.zzw;
import com.google.firebase.auth.zzx;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "DefaultMultiFactorResolverCreator")
public final class zzp extends zzw {
    public static final Parcelable.Creator<zzp> CREATOR = new zzs();
    @SafeParcelable.Field(getter = "getDefaultOAuthCredential", id = 4)
    private final zzf zzkw;
    @SafeParcelable.Field(getter = "getFirebaseAppName", id = 3)
    private final String zztj;
    @SafeParcelable.Field(getter = "getPhoneMultiFactorInfoList", id = 1)
    private final List<zzac> zzts = new ArrayList();
    @SafeParcelable.Field(getter = "getSession", id = 2)
    private final zzr zztt;

    @SafeParcelable.Constructor
    public zzp(@SafeParcelable.Param(id = 1) List<zzac> list, @SafeParcelable.Param(id = 2) zzr zzr, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) @Nullable zzf zzf) {
        for (zzx next : list) {
            if (next instanceof zzac) {
                this.zzts.add((zzac) next);
            }
        }
        this.zztt = (zzr) Preconditions.checkNotNull(zzr);
        this.zztj = Preconditions.checkNotEmpty(str);
        this.zzkw = zzf;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zzts, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zztt, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zztj, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzkw, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

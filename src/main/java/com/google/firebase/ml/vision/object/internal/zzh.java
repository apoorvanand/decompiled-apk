package com.google.firebase.ml.vision.object.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ObjectParcelCreator")
public final class zzh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzh> CREATOR = new zzi();
    @SafeParcelable.Field(id = 3)
    private final String label;
    @SafeParcelable.Field(id = 1)
    private final int[] zzaxb;
    @SafeParcelable.Field(id = 2)
    private final long zzaxc;
    @SafeParcelable.Field(id = 4)
    private final float zzaxd;

    @SafeParcelable.Constructor
    public zzh(@SafeParcelable.Param(id = 1) int[] iArr, @SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) float f) {
        this.zzaxb = iArr;
        this.zzaxc = j;
        this.label = str;
        this.zzaxd = f;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIntArray(parcel, 1, this.zzaxb, false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzaxc);
        SafeParcelWriter.writeString(parcel, 3, this.label, false);
        SafeParcelWriter.writeFloat(parcel, 4, this.zzaxd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

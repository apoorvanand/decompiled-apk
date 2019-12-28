package com.google.firebase.ml.vision.automl.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ImageMetadataParcelCreator")
public final class zzg extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzg> CREATOR = new zzh();
    @SafeParcelable.Field(id = 2)
    private final int height;
    @SafeParcelable.Field(id = 3)
    private final int id;
    @SafeParcelable.Field(id = 5)
    private final int rotation;
    @SafeParcelable.Field(id = 1)
    private final int width;
    @SafeParcelable.Field(id = 4)
    private final long zzatg;

    @SafeParcelable.Constructor
    public zzg(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) int i3, @SafeParcelable.Param(id = 4) long j, @SafeParcelable.Param(id = 5) int i4) {
        this.width = i;
        this.height = i2;
        this.id = i3;
        this.zzatg = j;
        this.rotation = i4;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.width);
        SafeParcelWriter.writeInt(parcel, 2, this.height);
        SafeParcelWriter.writeInt(parcel, 3, this.id);
        SafeParcelWriter.writeLong(parcel, 4, this.zzatg);
        SafeParcelWriter.writeInt(parcel, 5, this.rotation);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

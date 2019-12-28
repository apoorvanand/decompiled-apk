package com.google.firebase.ml.vision.object.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ObjectDetectorOptionsParcelCreator")
public class ObjectDetectorOptionsParcel extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ObjectDetectorOptionsParcel> CREATOR = new zzg();
    @SafeParcelable.Field(id = 1)
    public final int zzaww;
    @SafeParcelable.Field(id = 2)
    public final boolean zzawx;
    @SafeParcelable.Field(id = 3)
    public final boolean zzawy;

    @SafeParcelable.Constructor
    public ObjectDetectorOptionsParcel(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) boolean z2) {
        this.zzaww = i;
        this.zzawx = z;
        this.zzawy = z2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzaww);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzawx);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzawy);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

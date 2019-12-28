package com.google.firebase.ml.vision.automl.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "OnDeviceAutoMLImageLabelerOptionsParcelCreator")
public class OnDeviceAutoMLImageLabelerOptionsParcel extends AbstractSafeParcelable {
    public static final Parcelable.Creator<OnDeviceAutoMLImageLabelerOptionsParcel> CREATOR = new zzi();
    @SafeParcelable.Field(id = 2)
    public final String zzarf;
    @SafeParcelable.Field(id = 1)
    public final float zzath;
    @SafeParcelable.Field(id = 3)
    private final String zzati;

    @SafeParcelable.Constructor
    public OnDeviceAutoMLImageLabelerOptionsParcel(@SafeParcelable.Param(id = 1) float f, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2) {
        this.zzath = f;
        this.zzarf = str;
        this.zzati = str2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 1, this.zzath);
        SafeParcelWriter.writeString(parcel, 2, this.zzarf, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzati, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

package com.google.firebase.ml.vision.automl.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ImageLabelParcelCreator")
public final class zze extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zze> CREATOR = new zzf();
    @SafeParcelable.Field(id = 2)
    private final String text;
    @SafeParcelable.Field(id = 3)
    private final float zzasl;
    @SafeParcelable.Field(id = 1)
    private final String zzatf;

    @SafeParcelable.Constructor
    public zze(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) float f) {
        this.zzatf = str;
        this.text = str2;
        this.zzasl = f;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzatf, false);
        SafeParcelWriter.writeString(parcel, 2, this.text, false);
        SafeParcelWriter.writeFloat(parcel, 3, this.zzasl);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

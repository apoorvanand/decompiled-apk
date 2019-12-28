package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzf implements Parcelable.Creator<zzg> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzm zzm = null;
        zze zze = null;
        com.google.firebase.auth.zzf zzf = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    zzm = (zzm) SafeParcelReader.createParcelable(parcel, readHeader, zzm.CREATOR);
                    break;
                case 2:
                    zze = (zze) SafeParcelReader.createParcelable(parcel, readHeader, zze.CREATOR);
                    break;
                case 3:
                    zzf = (com.google.firebase.auth.zzf) SafeParcelReader.createParcelable(parcel, readHeader, com.google.firebase.auth.zzf.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzg(zzm, zze, zzf);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzg[i];
    }
}

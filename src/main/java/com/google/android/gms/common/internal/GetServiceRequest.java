package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class(creator = "GetServiceRequestCreator")
@SafeParcelable.Reserved({9})
public class GetServiceRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GetServiceRequest> CREATOR = new zzd();
    @SafeParcelable.Field(id = 4)
    String a;
    @SafeParcelable.Field(id = 5)
    IBinder b;
    @SafeParcelable.Field(id = 6)
    Scope[] c;
    @SafeParcelable.Field(id = 7)
    Bundle d;
    @SafeParcelable.Field(id = 8)
    Account e;
    @SafeParcelable.Field(id = 10)
    Feature[] f;
    @SafeParcelable.Field(id = 11)
    Feature[] g;
    @SafeParcelable.VersionField(id = 1)
    private final int version;
    @SafeParcelable.Field(id = 2)
    private final int zzdg;
    @SafeParcelable.Field(id = 3)
    private int zzdh;
    @SafeParcelable.Field(id = 12)
    private boolean zzdo;

    public GetServiceRequest(int i) {
        this.version = 4;
        this.zzdh = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.zzdg = i;
        this.zzdo = true;
    }

    @SafeParcelable.Constructor
    GetServiceRequest(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) int i3, @SafeParcelable.Param(id = 4) String str, @SafeParcelable.Param(id = 5) IBinder iBinder, @SafeParcelable.Param(id = 6) Scope[] scopeArr, @SafeParcelable.Param(id = 7) Bundle bundle, @SafeParcelable.Param(id = 8) Account account, @SafeParcelable.Param(id = 10) Feature[] featureArr, @SafeParcelable.Param(id = 11) Feature[] featureArr2, @SafeParcelable.Param(id = 12) boolean z) {
        this.version = i;
        this.zzdg = i2;
        this.zzdh = i3;
        if ("com.google.android.gms".equals(str)) {
            this.a = "com.google.android.gms";
        } else {
            this.a = str;
        }
        if (i < 2) {
            this.e = iBinder != null ? AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(iBinder)) : null;
        } else {
            this.b = iBinder;
            this.e = account;
        }
        this.c = scopeArr;
        this.d = bundle;
        this.f = featureArr;
        this.g = featureArr2;
        this.zzdo = z;
    }

    @KeepForSdk
    public Bundle getExtraArgs() {
        return this.d;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.version);
        SafeParcelWriter.writeInt(parcel, 2, this.zzdg);
        SafeParcelWriter.writeInt(parcel, 3, this.zzdh);
        SafeParcelWriter.writeString(parcel, 4, this.a, false);
        SafeParcelWriter.writeIBinder(parcel, 5, this.b, false);
        SafeParcelWriter.writeTypedArray(parcel, 6, this.c, i, false);
        SafeParcelWriter.writeBundle(parcel, 7, this.d, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.e, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 10, this.f, i, false);
        SafeParcelWriter.writeTypedArray(parcel, 11, this.g, i, false);
        SafeParcelWriter.writeBoolean(parcel, 12, this.zzdo);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}

package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Iterator;

@SafeParcelable.Class(creator = "EventParamsCreator")
@SafeParcelable.Reserved({1})
public final class zzah extends AbstractSafeParcelable implements Iterable<String> {
    public static final Parcelable.Creator<zzah> CREATOR = new zzaj();
    /* access modifiers changed from: private */
    @SafeParcelable.Field(getter = "z", id = 2)
    public final Bundle zzft;

    @SafeParcelable.Constructor
    zzah(@SafeParcelable.Param(id = 2) Bundle bundle) {
        this.zzft = bundle;
    }

    /* access modifiers changed from: package-private */
    public final Object a(String str) {
        return this.zzft.get(str);
    }

    /* access modifiers changed from: package-private */
    public final Long b(String str) {
        return Long.valueOf(this.zzft.getLong(str));
    }

    /* access modifiers changed from: package-private */
    public final Double c(String str) {
        return Double.valueOf(this.zzft.getDouble(str));
    }

    /* access modifiers changed from: package-private */
    public final String d(String str) {
        return this.zzft.getString(str);
    }

    public final Iterator<String> iterator() {
        return new zzag(this);
    }

    public final int size() {
        return this.zzft.size();
    }

    public final String toString() {
        return this.zzft.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, zzcv(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final Bundle zzcv() {
        return new Bundle(this.zzft);
    }
}

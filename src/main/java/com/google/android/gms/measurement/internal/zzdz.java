package com.google.android.gms.measurement.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzd;
import java.util.ArrayList;
import java.util.List;

public final class zzdz extends zzb implements zzdx {
    zzdz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    public final List<zzjn> zza(zzn zzn, boolean z) {
        Parcel a = a();
        zzd.zza(a, (Parcelable) zzn);
        zzd.writeBoolean(a, z);
        Parcel a2 = a(7, a);
        ArrayList<zzjn> createTypedArrayList = a2.createTypedArrayList(zzjn.CREATOR);
        a2.recycle();
        return createTypedArrayList;
    }

    public final List<zzq> zza(String str, String str2, zzn zzn) {
        Parcel a = a();
        a.writeString(str);
        a.writeString(str2);
        zzd.zza(a, (Parcelable) zzn);
        Parcel a2 = a(16, a);
        ArrayList<zzq> createTypedArrayList = a2.createTypedArrayList(zzq.CREATOR);
        a2.recycle();
        return createTypedArrayList;
    }

    public final List<zzjn> zza(String str, String str2, String str3, boolean z) {
        Parcel a = a();
        a.writeString(str);
        a.writeString(str2);
        a.writeString(str3);
        zzd.writeBoolean(a, z);
        Parcel a2 = a(15, a);
        ArrayList<zzjn> createTypedArrayList = a2.createTypedArrayList(zzjn.CREATOR);
        a2.recycle();
        return createTypedArrayList;
    }

    public final List<zzjn> zza(String str, String str2, boolean z, zzn zzn) {
        Parcel a = a();
        a.writeString(str);
        a.writeString(str2);
        zzd.writeBoolean(a, z);
        zzd.zza(a, (Parcelable) zzn);
        Parcel a2 = a(14, a);
        ArrayList<zzjn> createTypedArrayList = a2.createTypedArrayList(zzjn.CREATOR);
        a2.recycle();
        return createTypedArrayList;
    }

    public final void zza(long j, String str, String str2, String str3) {
        Parcel a = a();
        a.writeLong(j);
        a.writeString(str);
        a.writeString(str2);
        a.writeString(str3);
        b(10, a);
    }

    public final void zza(zzai zzai, zzn zzn) {
        Parcel a = a();
        zzd.zza(a, (Parcelable) zzai);
        zzd.zza(a, (Parcelable) zzn);
        b(1, a);
    }

    public final void zza(zzai zzai, String str, String str2) {
        Parcel a = a();
        zzd.zza(a, (Parcelable) zzai);
        a.writeString(str);
        a.writeString(str2);
        b(5, a);
    }

    public final void zza(zzjn zzjn, zzn zzn) {
        Parcel a = a();
        zzd.zza(a, (Parcelable) zzjn);
        zzd.zza(a, (Parcelable) zzn);
        b(2, a);
    }

    public final void zza(zzn zzn) {
        Parcel a = a();
        zzd.zza(a, (Parcelable) zzn);
        b(4, a);
    }

    public final void zza(zzq zzq, zzn zzn) {
        Parcel a = a();
        zzd.zza(a, (Parcelable) zzq);
        zzd.zza(a, (Parcelable) zzn);
        b(12, a);
    }

    public final byte[] zza(zzai zzai, String str) {
        Parcel a = a();
        zzd.zza(a, (Parcelable) zzai);
        a.writeString(str);
        Parcel a2 = a(9, a);
        byte[] createByteArray = a2.createByteArray();
        a2.recycle();
        return createByteArray;
    }

    public final void zzb(zzn zzn) {
        Parcel a = a();
        zzd.zza(a, (Parcelable) zzn);
        b(6, a);
    }

    public final void zzb(zzq zzq) {
        Parcel a = a();
        zzd.zza(a, (Parcelable) zzq);
        b(13, a);
    }

    public final String zzc(zzn zzn) {
        Parcel a = a();
        zzd.zza(a, (Parcelable) zzn);
        Parcel a2 = a(11, a);
        String readString = a2.readString();
        a2.recycle();
        return readString;
    }

    public final List<zzq> zzc(String str, String str2, String str3) {
        Parcel a = a();
        a.writeString(str);
        a.writeString(str2);
        a.writeString(str3);
        Parcel a2 = a(17, a);
        ArrayList<zzq> createTypedArrayList = a2.createTypedArrayList(zzq.CREATOR);
        a2.recycle();
        return createTypedArrayList;
    }

    public final void zzd(zzn zzn) {
        Parcel a = a();
        zzd.zza(a, (Parcelable) zzn);
        b(18, a);
    }
}

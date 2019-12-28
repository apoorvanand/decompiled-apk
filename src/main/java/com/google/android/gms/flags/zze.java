package com.google.android.gms.flags;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.flags.zza;
import com.google.android.gms.internal.flags.zzc;

public final class zze extends zza implements zzc {
    zze(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.flags.IFlagProvider");
    }

    public final boolean getBooleanFlagValue(String str, boolean z, int i) {
        Parcel a = a();
        a.writeString(str);
        zzc.writeBoolean(a, z);
        a.writeInt(i);
        Parcel a2 = a(2, a);
        boolean zza = zzc.zza(a2);
        a2.recycle();
        return zza;
    }

    public final int getIntFlagValue(String str, int i, int i2) {
        Parcel a = a();
        a.writeString(str);
        a.writeInt(i);
        a.writeInt(i2);
        Parcel a2 = a(3, a);
        int readInt = a2.readInt();
        a2.recycle();
        return readInt;
    }

    public final long getLongFlagValue(String str, long j, int i) {
        Parcel a = a();
        a.writeString(str);
        a.writeLong(j);
        a.writeInt(i);
        Parcel a2 = a(4, a);
        long readLong = a2.readLong();
        a2.recycle();
        return readLong;
    }

    public final String getStringFlagValue(String str, String str2, int i) {
        Parcel a = a();
        a.writeString(str);
        a.writeString(str2);
        a.writeInt(i);
        Parcel a2 = a(5, a);
        String readString = a2.readString();
        a2.recycle();
        return readString;
    }

    public final void init(IObjectWrapper iObjectWrapper) {
        Parcel a = a();
        zzc.zza(a, iObjectWrapper);
        b(1, a);
    }
}

package com.google.android.gms.internal.firebase_auth;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public class zzb implements IInterface {
    private final IBinder zzb;
    private final String zzc;

    protected zzb(IBinder iBinder, String str) {
        this.zzb = iBinder;
        this.zzc = str;
    }

    public IBinder asBinder() {
        return this.zzb;
    }

    /* access modifiers changed from: protected */
    public final Parcel zza() {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.zzc);
        return obtain;
    }

    /* access modifiers changed from: protected */
    public final void zza(int i, Parcel parcel) {
        Parcel obtain = Parcel.obtain();
        try {
            this.zzb.transact(i, parcel, obtain, 0);
            obtain.readException();
        } finally {
            parcel.recycle();
            obtain.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public final void zzb(int i, Parcel parcel) {
        try {
            this.zzb.transact(i, parcel, (Parcel) null, 1);
        } finally {
            parcel.recycle();
        }
    }
}

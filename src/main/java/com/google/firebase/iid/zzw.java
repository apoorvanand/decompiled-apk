package com.google.firebase.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;

public final class zzw implements zzu {
    private final IBinder zzbv;

    zzw(IBinder iBinder) {
        this.zzbv = iBinder;
    }

    public final IBinder asBinder() {
        return this.zzbv;
    }

    public final void send(Message message) {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken("com.google.android.gms.iid.IMessengerCompat");
        obtain.writeInt(1);
        message.writeToParcel(obtain, 0);
        try {
            this.zzbv.transact(1, obtain, (Parcel) null, 1);
        } finally {
            obtain.recycle();
        }
    }
}

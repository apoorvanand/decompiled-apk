package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.internal.base.zaa;
import com.google.android.gms.internal.base.zac;

public final class zag extends zaa implements zaf {
    zag(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.signin.internal.ISignInService");
    }

    public final void zaa(IAccountAccessor iAccountAccessor, int i, boolean z) {
        Parcel a = a();
        zac.zaa(a, (IInterface) iAccountAccessor);
        a.writeInt(i);
        zac.writeBoolean(a, z);
        b(9, a);
    }

    public final void zaa(zah zah, zad zad) {
        Parcel a = a();
        zac.zaa(a, (Parcelable) zah);
        zac.zaa(a, (IInterface) zad);
        b(12, a);
    }

    public final void zam(int i) {
        Parcel a = a();
        a.writeInt(i);
        b(7, a);
    }
}

package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    private static final Object zzdc = new Object();
    private static ClassLoader zzdd = null;
    private static Integer zzde = null;
    private boolean zzdf = false;

    private static ClassLoader zzp() {
        synchronized (zzdc) {
        }
        return null;
    }

    @KeepForSdk
    public void setShouldDowngrade(boolean z) {
        this.zzdf = z;
    }
}

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

@KeepForSdk
public class DataHolderResult implements Releasable, Result {
    @KeepForSdk
    protected final Status a;
    @KeepForSdk
    protected final DataHolder b;

    @KeepForSdk
    public Status getStatus() {
        return this.a;
    }

    @KeepForSdk
    public void release() {
        DataHolder dataHolder = this.b;
        if (dataHolder != null) {
            dataHolder.close();
        }
    }
}

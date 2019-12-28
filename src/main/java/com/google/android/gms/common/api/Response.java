package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Result;

public class Response<T extends Result> {
    private T zzap;

    public Response() {
    }

    protected Response(@NonNull T t) {
        this.zzap = t;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public T a() {
        return this.zzap;
    }

    public void setResult(@NonNull T t) {
        this.zzap = t;
    }
}

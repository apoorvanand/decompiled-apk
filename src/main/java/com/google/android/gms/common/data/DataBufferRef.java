package com.google.android.gms.common.data;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
public class DataBufferRef {
    @KeepForSdk
    protected final DataHolder a;
    @KeepForSdk
    protected int b;
    private int zaln;

    @KeepForSdk
    public DataBufferRef(DataHolder dataHolder, int i) {
        this.a = (DataHolder) Preconditions.checkNotNull(dataHolder);
        a(i);
    }

    /* access modifiers changed from: protected */
    public final void a(int i) {
        Preconditions.checkState(i >= 0 && i < this.a.getCount());
        this.b = i;
        this.zaln = this.a.getWindowIndex(this.b);
    }

    public boolean equals(Object obj) {
        if (obj instanceof DataBufferRef) {
            DataBufferRef dataBufferRef = (DataBufferRef) obj;
            return Objects.equal(Integer.valueOf(dataBufferRef.b), Integer.valueOf(this.b)) && Objects.equal(Integer.valueOf(dataBufferRef.zaln), Integer.valueOf(this.zaln)) && dataBufferRef.a == this.a;
        }
    }

    @KeepForSdk
    public boolean hasColumn(String str) {
        return this.a.hasColumn(str);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.b), Integer.valueOf(this.zaln), this.a);
    }

    @KeepForSdk
    public boolean isDataValid() {
        return !this.a.isClosed();
    }
}

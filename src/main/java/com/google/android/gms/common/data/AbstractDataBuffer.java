package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Iterator;

public abstract class AbstractDataBuffer<T> implements DataBuffer<T> {
    protected final DataHolder a;

    @KeepForSdk
    protected AbstractDataBuffer(DataHolder dataHolder) {
        this.a = dataHolder;
    }

    @Deprecated
    public final void close() {
        release();
    }

    public abstract T get(int i);

    public int getCount() {
        DataHolder dataHolder = this.a;
        if (dataHolder == null) {
            return 0;
        }
        return dataHolder.getCount();
    }

    public Bundle getMetadata() {
        return this.a.getMetadata();
    }

    @Deprecated
    public boolean isClosed() {
        DataHolder dataHolder = this.a;
        return dataHolder == null || dataHolder.isClosed();
    }

    public Iterator<T> iterator() {
        return new DataBufferIterator(this);
    }

    public void release() {
        DataHolder dataHolder = this.a;
        if (dataHolder != null) {
            dataHolder.close();
        }
    }

    public Iterator<T> singleRefIterator() {
        return new SingleRefDataBufferIterator(this);
    }
}

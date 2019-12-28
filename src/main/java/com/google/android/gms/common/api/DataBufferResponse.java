package com.google.android.gms.common.api;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataBuffer;
import java.util.Iterator;

@KeepForSdk
public class DataBufferResponse<T, R extends AbstractDataBuffer<T> & Result> extends Response<R> implements DataBuffer<T> {
    @KeepForSdk
    public DataBufferResponse() {
    }

    @KeepForSdk
    public DataBufferResponse(@NonNull R r) {
        super(r);
    }

    public void close() {
        ((AbstractDataBuffer) a()).close();
    }

    public T get(int i) {
        return ((AbstractDataBuffer) a()).get(i);
    }

    public int getCount() {
        return ((AbstractDataBuffer) a()).getCount();
    }

    public Bundle getMetadata() {
        return ((AbstractDataBuffer) a()).getMetadata();
    }

    public boolean isClosed() {
        return ((AbstractDataBuffer) a()).isClosed();
    }

    public Iterator<T> iterator() {
        return ((AbstractDataBuffer) a()).iterator();
    }

    public void release() {
        ((AbstractDataBuffer) a()).release();
    }

    public Iterator<T> singleRefIterator() {
        return ((AbstractDataBuffer) a()).singleRefIterator();
    }
}

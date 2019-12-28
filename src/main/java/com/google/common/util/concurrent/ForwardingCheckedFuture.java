package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.Exception;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
@Beta
public abstract class ForwardingCheckedFuture<V, X extends Exception> extends ForwardingListenableFuture<V> implements CheckedFuture<V, X> {

    @Beta
    public static abstract class SimpleForwardingCheckedFuture<V, X extends Exception> extends ForwardingCheckedFuture<V, X> {
        private final CheckedFuture<V, X> delegate;
    }

    /* access modifiers changed from: protected */
    public abstract CheckedFuture<V, X> b();

    @CanIgnoreReturnValue
    public V checkedGet() {
        return b().checkedGet();
    }

    @CanIgnoreReturnValue
    public V checkedGet(long j, TimeUnit timeUnit) {
        return b().checkedGet(j, timeUnit);
    }
}

package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.base.zap;

@KeepForSdk
public final class ListenerHolder<L> {
    private final zaa zajj;
    private volatile L zajk;
    private final ListenerKey<L> zajl;

    @KeepForSdk
    public static final class ListenerKey<L> {
        private final L zajk;
        private final String zajn;

        @KeepForSdk
        ListenerKey(L l, String str) {
            this.zajk = l;
            this.zajn = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ListenerKey)) {
                return false;
            }
            ListenerKey listenerKey = (ListenerKey) obj;
            return this.zajk == listenerKey.zajk && this.zajn.equals(listenerKey.zajn);
        }

        public final int hashCode() {
            return (System.identityHashCode(this.zajk) * 31) + this.zajn.hashCode();
        }
    }

    @KeepForSdk
    public interface Notifier<L> {
        @KeepForSdk
        void notifyListener(L l);

        @KeepForSdk
        void onNotifyListenerFailed();
    }

    private final class zaa extends zap {
        public zaa(Looper looper) {
            super(looper);
        }

        public final void handleMessage(Message message) {
            boolean z = true;
            if (message.what != 1) {
                z = false;
            }
            Preconditions.checkArgument(z);
            ListenerHolder.this.a((Notifier) message.obj);
        }
    }

    @KeepForSdk
    ListenerHolder(@NonNull Looper looper, @NonNull L l, @NonNull String str) {
        this.zajj = new zaa(looper);
        this.zajk = Preconditions.checkNotNull(l, "Listener must not be null");
        this.zajl = new ListenerKey<>(l, Preconditions.checkNotEmpty(str));
    }

    /* access modifiers changed from: package-private */
    @KeepForSdk
    public final void a(Notifier<? super L> notifier) {
        L l = this.zajk;
        if (l == null) {
            notifier.onNotifyListenerFailed();
            return;
        }
        try {
            notifier.notifyListener(l);
        } catch (RuntimeException e) {
            notifier.onNotifyListenerFailed();
            throw e;
        }
    }

    @KeepForSdk
    public final void clear() {
        this.zajk = null;
    }

    @NonNull
    @KeepForSdk
    public final ListenerKey<L> getListenerKey() {
        return this.zajl;
    }

    @KeepForSdk
    public final boolean hasListener() {
        return this.zajk != null;
    }

    @KeepForSdk
    public final void notifyListener(Notifier<? super L> notifier) {
        Preconditions.checkNotNull(notifier, "Notifier must not be null");
        this.zajj.sendMessage(this.zajj.obtainMessage(1, notifier));
    }
}

package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zah extends zad<Boolean> {
    private final ListenerHolder.ListenerKey<?> zact;

    public zah(ListenerHolder.ListenerKey<?> listenerKey, TaskCompletionSource<Boolean> taskCompletionSource) {
        super(4, taskCompletionSource);
        this.zact = listenerKey;
    }

    public final /* bridge */ /* synthetic */ void zaa(@NonNull Status status) {
        super.zaa(status);
    }

    public final /* bridge */ /* synthetic */ void zaa(@NonNull zaab zaab, boolean z) {
    }

    public final /* bridge */ /* synthetic */ void zaa(@NonNull RuntimeException runtimeException) {
        super.zaa(runtimeException);
    }

    @Nullable
    public final Feature[] zab(GoogleApiManager.zaa<?> zaa) {
        zabw zabw = zaa.zabk().get(this.zact);
        if (zabw == null) {
            return null;
        }
        return zabw.zajx.getRequiredFeatures();
    }

    public final boolean zac(GoogleApiManager.zaa<?> zaa) {
        zabw zabw = zaa.zabk().get(this.zact);
        return zabw != null && zabw.zajx.shouldAutoResolveMissingFeatures();
    }

    public final void zad(GoogleApiManager.zaa<?> zaa) {
        zabw remove = zaa.zabk().remove(this.zact);
        if (remove != null) {
            remove.zajy.a(zaa.zaab(), this.a);
            remove.zajx.clearListener();
            return;
        }
        this.a.trySetResult(false);
    }
}

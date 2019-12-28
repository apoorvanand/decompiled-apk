package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public final class zzom implements zzna {
    public final List<zzji> features;
    public final zzjl imageContext;
    public final byte[] zzaud;
    public final float zzaue;

    public zzom(@NonNull byte[] bArr, float f, @NonNull List<zzji> list, @Nullable zzjl zzjl) {
        this.zzaud = bArr;
        this.zzaue = f;
        this.features = list;
        this.imageContext = zzjl;
    }
}

package com.google.android.gms.internal.firebase_ml;

import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import java.io.Closeable;
import java.util.Collections;

public abstract class zzon<ResultType> implements Closeable {
    private final zzjl imageContext;
    protected final FirebaseApp zzaot;
    private final zznb zzara;
    private final zzoo zzauf;
    private final zzji zzaug;

    private zzon(@NonNull FirebaseApp firebaseApp, @NonNull zzji zzji, @Nullable zzjl zzjl, boolean z) {
        Preconditions.checkNotNull(firebaseApp, "FirebaseApp must not be null");
        Preconditions.checkNotNull(firebaseApp.getPersistenceKey(), "Firebase app name must not be null");
        this.zzaug = (zzji) Preconditions.checkNotNull(zzji);
        this.zzara = zznb.zza(firebaseApp);
        this.zzauf = new zzoo(this, firebaseApp, z);
        this.zzaot = firebaseApp;
        this.imageContext = zzjl;
    }

    protected zzon(@NonNull FirebaseApp firebaseApp, @NonNull String str, @NonNull zzjl zzjl, boolean z) {
        this(firebaseApp, new zzji().zzav(str).zzau(zzol.zzbc(1)), (zzjl) Preconditions.checkNotNull(zzjl, "ImageContext must not be null"), z);
    }

    protected zzon(@NonNull FirebaseApp firebaseApp, @NonNull String str, @NonNull FirebaseVisionCloudDetectorOptions firebaseVisionCloudDetectorOptions) {
        this(firebaseApp, new zzji().zza(Integer.valueOf(firebaseVisionCloudDetectorOptions.getMaxResults())).zzav(str).zzau(zzol.zzbc(firebaseVisionCloudDetectorOptions.getModelType())), (zzjl) null, firebaseVisionCloudDetectorOptions.isEnforceCertFingerprintMatch());
    }

    public void close() {
    }

    public final Task<ResultType> zza(@NonNull FirebaseVisionImage firebaseVisionImage) {
        Preconditions.checkNotNull(firebaseVisionImage, "Input image can not be null");
        Pair<byte[], Float> zzb = firebaseVisionImage.zzb(zzlv(), zzlw());
        if (zzb.first == null) {
            return Tasks.forException(new FirebaseMLException("Can not convert the image format", 3));
        }
        return this.zzara.zza(this.zzauf, new zzom((byte[]) zzb.first, ((Float) zzb.second).floatValue(), Collections.singletonList(this.zzaug), this.imageContext));
    }

    /* access modifiers changed from: protected */
    public abstract ResultType zza(@NonNull zzix zzix, float f);

    /* access modifiers changed from: protected */
    public abstract int zzlv();

    /* access modifiers changed from: protected */
    public abstract int zzlw();
}

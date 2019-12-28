package com.google.android.gms.internal.firebase_ml;

import android.content.Context;
import android.os.SystemClock;
import android.util.SparseArray;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.vision.text.FirebaseVisionText;

public final class zzph implements zzmx<FirebaseVisionText, zzoy>, zznh {
    @VisibleForTesting
    static boolean a = true;
    private final zznf zzapl;
    private zzot zzatv = new zzot();
    @GuardedBy("this")
    private TextRecognizer zzaxm;
    private final Context zzv;

    public zzph(@NonNull FirebaseApp firebaseApp) {
        Preconditions.checkNotNull(firebaseApp, "Firebase App can not be null");
        this.zzv = firebaseApp.getApplicationContext();
        this.zzapl = zznf.zza(firebaseApp, 1);
    }

    private final void zza(zzlv zzlv, long j, zzoy zzoy) {
        this.zzapl.zza((zzng) new zzpi(j, zzlv, zzoy), zzly.ON_DEVICE_TEXT_DETECT);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    @Nullable
    /* renamed from: zzd */
    public final synchronized FirebaseVisionText zza(@NonNull zzoy zzoy) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.zzaxm == null) {
            zza(zzlv.UNKNOWN_ERROR, elapsedRealtime, zzoy);
            throw new FirebaseMLException("Model source is unavailable. Please load the model resource first.", 13);
        } else if (this.zzaxm.isOperational()) {
            this.zzatv.zzb(zzoy);
            SparseArray<TextBlock> detect = this.zzaxm.detect(zzoy.zzaux);
            zza(zzlv.NO_ERROR, elapsedRealtime, zzoy);
            a = false;
            if (detect == null) {
                return null;
            }
            return new FirebaseVisionText(detect);
        } else {
            zza(zzlv.MODEL_NOT_DOWNLOADED, elapsedRealtime, zzoy);
            throw new FirebaseMLException("Waiting for the text recognition model to be downloaded. Please wait.", 14);
        }
    }

    @WorkerThread
    public final synchronized void release() {
        if (this.zzaxm != null) {
            this.zzaxm.release();
            this.zzaxm = null;
        }
        a = true;
    }

    public final zznh zzkh() {
        return this;
    }

    @WorkerThread
    public final synchronized void zzkl() {
        if (this.zzaxm == null) {
            this.zzaxm = new TextRecognizer.Builder(this.zzv).build();
        }
    }
}

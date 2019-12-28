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
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zzoj implements zzmx<List<FirebaseVisionBarcode>, zzoy>, zznh {
    @VisibleForTesting
    private static boolean zzarn = true;
    private final zznf zzapl;
    private final FirebaseVisionBarcodeDetectorOptions zzatt;
    @VisibleForTesting
    @GuardedBy("this")
    private BarcodeDetector zzatu;
    private zzot zzatv = new zzot();
    private final Context zzv;

    public zzoj(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseVisionBarcodeDetectorOptions firebaseVisionBarcodeDetectorOptions) {
        Preconditions.checkNotNull(firebaseApp, "FirebaseApp can not be null");
        Preconditions.checkNotNull(firebaseVisionBarcodeDetectorOptions, "FirebaseVisionBarcodeDetectorOptions can not be null");
        this.zzv = firebaseApp.getApplicationContext();
        this.zzatt = firebaseVisionBarcodeDetectorOptions;
        this.zzapl = zznf.zza(firebaseApp, 1);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final synchronized List<FirebaseVisionBarcode> zza(@NonNull zzoy zzoy) {
        ArrayList arrayList;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.zzatu == null) {
            zza(zzlv.UNKNOWN_ERROR, elapsedRealtime, zzoy, (List<FirebaseVisionBarcode>) null);
            throw new FirebaseMLException("Model source is unavailable. Please load the model resource first.", 13);
        } else if (this.zzatu.isOperational()) {
            this.zzatv.zzb(zzoy);
            SparseArray<Barcode> detect = this.zzatu.detect(zzoy.zzaux);
            arrayList = new ArrayList();
            for (int i = 0; i < detect.size(); i++) {
                Barcode barcode = detect.get(detect.keyAt(i));
                if (barcode != null) {
                    arrayList.add(new FirebaseVisionBarcode(barcode));
                }
            }
            zza(zzlv.NO_ERROR, elapsedRealtime, zzoy, arrayList);
            zzarn = false;
        } else {
            zza(zzlv.MODEL_NOT_DOWNLOADED, elapsedRealtime, zzoy, (List<FirebaseVisionBarcode>) null);
            throw new FirebaseMLException("Waiting for the barcode detection model to be downloaded. Please wait.", 14);
        }
        return arrayList;
    }

    private final void zza(zzlv zzlv, long j, @NonNull zzoy zzoy, @Nullable List<FirebaseVisionBarcode> list) {
        this.zzapl.zza((zzng) new zzok(this, j, zzlv, zzoy, list), zzly.ON_DEVICE_BARCODE_DETECT);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzlo.zzq.zza a(long j, zzlv zzlv, zzoy zzoy, List list) {
        zzlo.zzv.zzc zzb = zzlo.zzv.zzjf().zzc(zzlo.zzs.zziy().zzk(SystemClock.elapsedRealtime() - j).zzc(zzlv).zzz(zzarn).zzaa(true).zzab(true)).zzb(this.zzatt.zzlu()).zzb(zzou.zzc(zzoy));
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                FirebaseVisionBarcode firebaseVisionBarcode = (FirebaseVisionBarcode) it.next();
                arrayList.add(firebaseVisionBarcode.zzlr());
                arrayList2.add(firebaseVisionBarcode.zzls());
            }
            zzb.zzk(arrayList).zzl(arrayList2);
        }
        return zzlo.zzq.zziu().zzb(zzb);
    }

    @WorkerThread
    public final synchronized void release() {
        if (this.zzatu != null) {
            this.zzatu.release();
            this.zzatu = null;
        }
        zzarn = true;
    }

    public final zznh zzkh() {
        return this;
    }

    @WorkerThread
    public final synchronized void zzkl() {
        if (this.zzatu == null) {
            this.zzatu = new BarcodeDetector.Builder(this.zzv).setBarcodeFormats(this.zzatt.zzlt()).build();
        }
    }
}

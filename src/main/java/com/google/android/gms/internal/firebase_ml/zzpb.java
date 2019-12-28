package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.NonNull;
import com.google.android.gms.internal.firebase_ml.zzlo;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import java.util.ArrayList;
import java.util.List;

public final class zzpb extends zzon<List<FirebaseVisionImageLabel>> {
    public zzpb(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseVisionCloudDetectorOptions firebaseVisionCloudDetectorOptions) {
        super(firebaseApp, "LABEL_DETECTION", firebaseVisionCloudDetectorOptions);
        zznf.zza(firebaseApp, 1).zza(zzlo.zzq.zziu(), zzly.CLOUD_IMAGE_LABEL_CREATE);
    }

    public final Task<List<FirebaseVisionImageLabel>> detectInImage(@NonNull FirebaseVisionImage firebaseVisionImage) {
        zznf.zza(this.zzaot, 1).zza(zzlo.zzq.zziu(), zzly.CLOUD_IMAGE_LABEL_DETECT);
        return super.zza(firebaseVisionImage);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object zza(@NonNull zzix zzix, float f) {
        if (zzix.zzgx() == null) {
            return new ArrayList();
        }
        List<zzjh> zzgx = zzix.zzgx();
        ArrayList arrayList = new ArrayList();
        for (zzjh zza : zzgx) {
            FirebaseVisionImageLabel zza2 = FirebaseVisionImageLabel.zza(zza);
            if (zza2 != null) {
                arrayList.add(zza2);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final int zzlv() {
        return 640;
    }

    /* access modifiers changed from: protected */
    public final int zzlw() {
        return 480;
    }
}

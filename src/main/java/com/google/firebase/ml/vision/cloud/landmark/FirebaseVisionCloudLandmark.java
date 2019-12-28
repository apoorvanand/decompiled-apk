package com.google.firebase.ml.vision.cloud.landmark;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_ml.zzjh;
import com.google.android.gms.internal.firebase_ml.zzjq;
import com.google.android.gms.internal.firebase_ml.zzol;
import com.google.firebase.ml.vision.common.FirebaseVisionLatLng;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.Immutable;

@Immutable
public class FirebaseVisionCloudLandmark {
    private final List<FirebaseVisionLatLng> locations;
    private final String mid;
    private final float zzasl;
    private final String zzaur;
    private final Rect zzaus;

    private FirebaseVisionCloudLandmark(@Nullable String str, float f, @Nullable Rect rect, @Nullable String str2, @NonNull List<FirebaseVisionLatLng> list) {
        this.zzaus = rect;
        this.zzaur = str == null ? "" : str;
        this.mid = str2 == null ? "" : str2;
        this.locations = list;
        this.zzasl = Float.compare(f, 0.0f) >= 0 ? Float.compare(f, 1.0f) > 0 ? 1.0f : f : 0.0f;
    }

    @Nullable
    static FirebaseVisionCloudLandmark zza(@Nullable zzjh zzjh, float f) {
        ArrayList arrayList;
        if (zzjh == null) {
            return null;
        }
        float zza = zzol.zza(zzjh.zzhf());
        Rect zza2 = zzol.zza(zzjh.zzhe(), f);
        String description = zzjh.getDescription();
        String mid2 = zzjh.getMid();
        List<zzjq> locations2 = zzjh.getLocations();
        if (locations2 == null) {
            arrayList = new ArrayList();
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (zzjq next : locations2) {
                if (!(next.zzhi() == null || next.zzhi().zzhg() == null || next.zzhi().zzhh() == null)) {
                    arrayList2.add(new FirebaseVisionLatLng(next.zzhi().zzhg().doubleValue(), next.zzhi().zzhh().doubleValue()));
                }
            }
            arrayList = arrayList2;
        }
        return new FirebaseVisionCloudLandmark(description, zza, zza2, mid2, arrayList);
    }

    @Nullable
    public Rect getBoundingBox() {
        return this.zzaus;
    }

    public float getConfidence() {
        return this.zzasl;
    }

    public String getEntityId() {
        return this.mid;
    }

    public String getLandmark() {
        return this.zzaur;
    }

    public List<FirebaseVisionLatLng> getLocations() {
        return this.locations;
    }
}

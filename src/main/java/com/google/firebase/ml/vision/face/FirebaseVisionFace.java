package com.google.firebase.ml.vision.face;

import android.graphics.PointF;
import android.graphics.Rect;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_ml.zzkp;
import com.google.android.gms.internal.firebase_ml.zzkr;
import com.google.android.gms.vision.face.Contour;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import com.google.firebase.ml.vision.common.FirebaseVisionPoint;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceContour;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.concurrent.Immutable;

@Immutable
public class FirebaseVisionFace {
    public static final int INVALID_ID = -1;
    public static final float UNCOMPUTED_PROBABILITY = -1.0f;
    private Rect zzaus;
    private int zzavo = -1;
    private float zzavp = -1.0f;
    private float zzavq = -1.0f;
    private float zzavr = -1.0f;
    private float zzavs;
    private float zzavt;
    private final SparseArray<FirebaseVisionFaceLandmark> zzavu = new SparseArray<>();
    private final SparseArray<FirebaseVisionFaceContour> zzavv = new SparseArray<>();

    public FirebaseVisionFace(@NonNull Face face) {
        int i;
        PointF position = face.getPosition();
        this.zzaus = new Rect((int) position.x, (int) position.y, (int) (position.x + face.getWidth()), (int) (position.y + face.getHeight()));
        this.zzavo = face.getId();
        for (Landmark next : face.getLandmarks()) {
            if (zzbh(next.getType()) && next.getPosition() != null) {
                this.zzavu.put(next.getType(), new FirebaseVisionFaceLandmark(next.getType(), new FirebaseVisionPoint(Float.valueOf(next.getPosition().x), Float.valueOf(next.getPosition().y), (Float) null)));
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Contour> it = face.getContours().iterator();
        while (true) {
            boolean z = true;
            if (it.hasNext()) {
                Contour next2 = it.next();
                switch (next2.getType()) {
                    case 1:
                        i = 2;
                        break;
                    case 2:
                        i = 3;
                        break;
                    case 3:
                        i = 4;
                        break;
                    case 4:
                        i = 5;
                        break;
                    case 5:
                        i = 6;
                        break;
                    case 6:
                        i = 7;
                        break;
                    case 7:
                        i = 8;
                        break;
                    case 8:
                        i = 9;
                        break;
                    case 9:
                        i = 10;
                        break;
                    case 10:
                        i = 11;
                        break;
                    case 11:
                        i = 12;
                        break;
                    case 12:
                        i = 13;
                        break;
                    case 13:
                        i = 14;
                        break;
                    default:
                        i = -1;
                        break;
                }
                if ((i > 14 || i <= 0) ? false : z) {
                    PointF[] positions = next2.getPositions();
                    ArrayList arrayList2 = new ArrayList();
                    if (positions != null) {
                        for (PointF pointF : positions) {
                            arrayList2.add(new FirebaseVisionPoint(Float.valueOf(pointF.x), Float.valueOf(pointF.y), (Float) null));
                        }
                        this.zzavv.put(i, new FirebaseVisionFaceContour(i, arrayList2));
                        arrayList.addAll(arrayList2);
                    }
                }
            } else {
                this.zzavv.put(1, new FirebaseVisionFaceContour(1, arrayList));
                this.zzavs = face.getEulerY();
                this.zzavt = face.getEulerZ();
                this.zzavr = face.getIsSmilingProbability();
                this.zzavq = face.getIsLeftEyeOpenProbability();
                this.zzavp = face.getIsRightEyeOpenProbability();
                return;
            }
        }
    }

    private static boolean zzbh(@FirebaseVisionFaceLandmark.LandmarkType int i) {
        return i == 0 || i == 1 || i == 7 || i == 3 || i == 9 || i == 4 || i == 10 || i == 5 || i == 11 || i == 6;
    }

    public Rect getBoundingBox() {
        return this.zzaus;
    }

    public FirebaseVisionFaceContour getContour(@FirebaseVisionFaceContour.ContourType int i) {
        FirebaseVisionFaceContour firebaseVisionFaceContour = this.zzavv.get(i);
        return firebaseVisionFaceContour != null ? firebaseVisionFaceContour : new FirebaseVisionFaceContour(i, new ArrayList());
    }

    public float getHeadEulerAngleY() {
        return this.zzavs;
    }

    public float getHeadEulerAngleZ() {
        return this.zzavt;
    }

    @Nullable
    public FirebaseVisionFaceLandmark getLandmark(@FirebaseVisionFaceLandmark.LandmarkType int i) {
        return this.zzavu.get(i);
    }

    public float getLeftEyeOpenProbability() {
        return this.zzavq;
    }

    public float getRightEyeOpenProbability() {
        return this.zzavp;
    }

    public float getSmilingProbability() {
        return this.zzavr;
    }

    public int getTrackingId() {
        return this.zzavo;
    }

    public String toString() {
        zzkr zza = zzkp.zzaw("FirebaseVisionFace").zzh("boundingBox", this.zzaus).zzb("trackingId", this.zzavo).zza("rightEyeOpenProbability", this.zzavp).zza("leftEyeOpenProbability", this.zzavq).zza("smileProbability", this.zzavr).zza("eulerY", this.zzavs).zza("eulerZ", this.zzavt);
        zzkr zzaw = zzkp.zzaw("Landmarks");
        for (int i = 0; i <= 11; i++) {
            if (zzbh(i)) {
                StringBuilder sb = new StringBuilder(20);
                sb.append("landmark_");
                sb.append(i);
                zzaw.zzh(sb.toString(), getLandmark(i));
            }
        }
        zza.zzh("landmarks", zzaw.toString());
        zzkr zzaw2 = zzkp.zzaw("Contours");
        for (int i2 = 1; i2 <= 14; i2++) {
            StringBuilder sb2 = new StringBuilder(19);
            sb2.append("Contour_");
            sb2.append(i2);
            zzaw2.zzh(sb2.toString(), getContour(i2));
        }
        zza.zzh("contours", zzaw2.toString());
        return zza.toString();
    }

    public final void zza(SparseArray<FirebaseVisionFaceContour> sparseArray) {
        this.zzavv.clear();
        for (int i = 0; i < sparseArray.size(); i++) {
            this.zzavv.put(sparseArray.keyAt(i), sparseArray.valueAt(i));
        }
    }

    public final void zzbi(int i) {
        this.zzavo = -1;
    }

    public final SparseArray<FirebaseVisionFaceContour> zzma() {
        return this.zzavv;
    }
}

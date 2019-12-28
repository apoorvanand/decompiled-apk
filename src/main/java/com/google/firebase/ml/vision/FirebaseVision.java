package com.google.firebase.ml.vision;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zznl;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions;
import com.google.firebase.ml.vision.cloud.landmark.FirebaseVisionCloudLandmarkDetector;
import com.google.firebase.ml.vision.document.FirebaseVisionCloudDocumentRecognizerOptions;
import com.google.firebase.ml.vision.document.FirebaseVisionDocumentTextRecognizer;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import com.google.firebase.ml.vision.label.FirebaseVisionCloudImageLabelerOptions;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceImageLabelerOptions;
import com.google.firebase.ml.vision.object.zza;
import com.google.firebase.ml.vision.object.zzc;
import com.google.firebase.ml.vision.text.FirebaseVisionCloudTextRecognizerOptions;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import java.util.HashMap;
import java.util.Map;

public class FirebaseVision {
    private static final Map<String, FirebaseVision> zzaj = new HashMap();
    private static final FirebaseVisionCloudDetectorOptions zzasq = new FirebaseVisionCloudDetectorOptions.Builder().build();
    private static final FirebaseVisionFaceDetectorOptions zzasr = new FirebaseVisionFaceDetectorOptions.Builder().build();
    private static final FirebaseVisionBarcodeDetectorOptions zzass = new FirebaseVisionBarcodeDetectorOptions.Builder().build();
    private static final FirebaseVisionCloudTextRecognizerOptions zzast = new FirebaseVisionCloudTextRecognizerOptions.Builder().build();
    private static final FirebaseVisionCloudDocumentRecognizerOptions zzasu = new FirebaseVisionCloudDocumentRecognizerOptions.Builder().build();
    private static final FirebaseVisionOnDeviceImageLabelerOptions zzasv = new FirebaseVisionOnDeviceImageLabelerOptions.Builder().build();
    private static final FirebaseVisionCloudImageLabelerOptions zzasw = new FirebaseVisionCloudImageLabelerOptions.Builder().build();
    private static final zza zzasx = new zzc().zzmd();
    private final FirebaseApp zzarc;

    private FirebaseVision(FirebaseApp firebaseApp) {
        this.zzarc = firebaseApp;
    }

    public static FirebaseVision getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    public static FirebaseVision getInstance(@NonNull FirebaseApp firebaseApp) {
        FirebaseVision firebaseVision;
        Preconditions.checkNotNull(firebaseApp, "FirebaseApp can not be null");
        String persistenceKey = firebaseApp.getPersistenceKey();
        synchronized (zzaj) {
            firebaseVision = zzaj.get(persistenceKey);
            if (firebaseVision == null) {
                firebaseVision = new FirebaseVision(firebaseApp);
                zzaj.put(persistenceKey, firebaseVision);
            }
        }
        return firebaseVision;
    }

    public FirebaseVisionDocumentTextRecognizer getCloudDocumentTextRecognizer() {
        return FirebaseVisionDocumentTextRecognizer.zza(this.zzarc, zzasu);
    }

    public FirebaseVisionDocumentTextRecognizer getCloudDocumentTextRecognizer(@NonNull FirebaseVisionCloudDocumentRecognizerOptions firebaseVisionCloudDocumentRecognizerOptions) {
        return FirebaseVisionDocumentTextRecognizer.zza(this.zzarc, firebaseVisionCloudDocumentRecognizerOptions);
    }

    public FirebaseVisionImageLabeler getCloudImageLabeler() {
        return FirebaseVisionImageLabeler.zza(this.zzarc, zzasw);
    }

    public FirebaseVisionImageLabeler getCloudImageLabeler(@NonNull FirebaseVisionCloudImageLabelerOptions firebaseVisionCloudImageLabelerOptions) {
        return FirebaseVisionImageLabeler.zza(this.zzarc, firebaseVisionCloudImageLabelerOptions);
    }

    public FirebaseVisionTextRecognizer getCloudTextRecognizer() {
        return FirebaseVisionTextRecognizer.zza(this.zzarc, zzast, false);
    }

    public FirebaseVisionTextRecognizer getCloudTextRecognizer(FirebaseVisionCloudTextRecognizerOptions firebaseVisionCloudTextRecognizerOptions) {
        return FirebaseVisionTextRecognizer.zza(this.zzarc, firebaseVisionCloudTextRecognizerOptions, false);
    }

    public FirebaseVisionImageLabeler getOnDeviceImageLabeler() {
        return FirebaseVisionImageLabeler.zza(this.zzarc, zzasv);
    }

    public FirebaseVisionImageLabeler getOnDeviceImageLabeler(@NonNull FirebaseVisionOnDeviceImageLabelerOptions firebaseVisionOnDeviceImageLabelerOptions) {
        return FirebaseVisionImageLabeler.zza(this.zzarc, (FirebaseVisionOnDeviceImageLabelerOptions) Preconditions.checkNotNull(firebaseVisionOnDeviceImageLabelerOptions, "Please provide a valid FirebaseVisionOnDeviceImageLabelerOptions"));
    }

    public FirebaseVisionTextRecognizer getOnDeviceTextRecognizer() {
        return FirebaseVisionTextRecognizer.zza(this.zzarc, (FirebaseVisionCloudTextRecognizerOptions) null, true);
    }

    public FirebaseVisionBarcodeDetector getVisionBarcodeDetector() {
        return FirebaseVisionBarcodeDetector.zza(this.zzarc, zzass);
    }

    public FirebaseVisionBarcodeDetector getVisionBarcodeDetector(@NonNull FirebaseVisionBarcodeDetectorOptions firebaseVisionBarcodeDetectorOptions) {
        return FirebaseVisionBarcodeDetector.zza(this.zzarc, (FirebaseVisionBarcodeDetectorOptions) Preconditions.checkNotNull(firebaseVisionBarcodeDetectorOptions, "Please provide a valid FirebaseVisionBarcodeDetectorOptions"));
    }

    public FirebaseVisionCloudLandmarkDetector getVisionCloudLandmarkDetector() {
        return FirebaseVisionCloudLandmarkDetector.zza(this.zzarc, zzasq);
    }

    public FirebaseVisionCloudLandmarkDetector getVisionCloudLandmarkDetector(@NonNull FirebaseVisionCloudDetectorOptions firebaseVisionCloudDetectorOptions) {
        return FirebaseVisionCloudLandmarkDetector.zza(this.zzarc, firebaseVisionCloudDetectorOptions);
    }

    public FirebaseVisionFaceDetector getVisionFaceDetector() {
        return FirebaseVisionFaceDetector.zza(this.zzarc, zzasr);
    }

    public FirebaseVisionFaceDetector getVisionFaceDetector(@NonNull FirebaseVisionFaceDetectorOptions firebaseVisionFaceDetectorOptions) {
        Preconditions.checkNotNull(firebaseVisionFaceDetectorOptions, "Please provide a valid FirebaseVisionFaceDetectorOptions");
        return FirebaseVisionFaceDetector.zza(this.zzarc, firebaseVisionFaceDetectorOptions);
    }

    public boolean isStatsCollectionEnabled() {
        return zznl.zzc(this.zzarc);
    }

    public void setStatsCollectionEnabled(boolean z) {
        zznl.zza(this.zzarc, z);
    }
}

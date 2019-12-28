package co.apperto.fastqrreaderview.java.barcodescanning;

import android.util.Log;
import androidx.annotation.NonNull;
import co.apperto.fastqrreaderview.common.FrameMetadata;
import co.apperto.fastqrreaderview.java.VisionProcessorBase;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BarcodeScanningProcessor extends VisionProcessorBase<List<FirebaseVisionBarcode>> {
    private static final String TAG = "BarcodeScanProc";
    public OnCodeScanned callback;
    private final FirebaseVisionBarcodeDetector detector;

    public BarcodeScanningProcessor(ArrayList<Integer> arrayList) {
        this.detector = FirebaseVision.getInstance().getVisionBarcodeDetector(new FirebaseVisionBarcodeDetectorOptions.Builder().setBarcodeFormats(ArrayUtils.toPrimitiveArray(arrayList)[0], Arrays.copyOfRange(ArrayUtils.toPrimitiveArray(arrayList), 1, arrayList.size())).build());
    }

    /* access modifiers changed from: protected */
    public Task<List<FirebaseVisionBarcode>> a(FirebaseVisionImage firebaseVisionImage) {
        return this.detector.detectInImage(firebaseVisionImage);
    }

    /* access modifiers changed from: protected */
    public void a(@NonNull Exception exc) {
        Log.e(TAG, "Barcode detection failed " + exc);
    }

    /* access modifiers changed from: protected */
    public void a(@NonNull List<FirebaseVisionBarcode> list, @NonNull FrameMetadata frameMetadata) {
        for (int i = 0; i < list.size(); i++) {
            FirebaseVisionBarcode firebaseVisionBarcode = list.get(i);
            Log.d("BARCODE!", firebaseVisionBarcode.getRawValue());
            this.callback.onCodeScanned(firebaseVisionBarcode);
        }
    }

    public void stop() {
        try {
            this.detector.close();
        } catch (IOException e) {
            Log.e(TAG, "Exception thrown while trying to close Barcode Detector: " + e);
        }
    }
}

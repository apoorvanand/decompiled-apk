package co.apperto.fastqrreaderview.java;

import android.graphics.Bitmap;
import android.media.Image;
import androidx.annotation.NonNull;
import co.apperto.fastqrreaderview.common.FrameMetadata;
import co.apperto.fastqrreaderview.common.VisionImageProcessor;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class VisionProcessorBase<T> implements VisionImageProcessor {
    public final AtomicBoolean shouldThrottle = new AtomicBoolean(true);

    private void detectInVisionImage(FirebaseVisionImage firebaseVisionImage, final FrameMetadata frameMetadata) {
        a(firebaseVisionImage).addOnSuccessListener(new OnSuccessListener<T>() {
            public void onSuccess(T t) {
                VisionProcessorBase.this.shouldThrottle.set(false);
                VisionProcessorBase.this.a(t, frameMetadata);
            }
        }).addOnFailureListener(new OnFailureListener() {
            public void onFailure(@NonNull Exception exc) {
                VisionProcessorBase.this.shouldThrottle.set(false);
                VisionProcessorBase.this.a(exc);
            }
        });
        this.shouldThrottle.set(true);
    }

    /* access modifiers changed from: protected */
    public abstract Task<T> a(FirebaseVisionImage firebaseVisionImage);

    /* access modifiers changed from: protected */
    public abstract void a(@NonNull Exception exc);

    /* access modifiers changed from: protected */
    public abstract void a(@NonNull T t, @NonNull FrameMetadata frameMetadata);

    public void process(Bitmap bitmap) {
        if (!this.shouldThrottle.get()) {
            detectInVisionImage(FirebaseVisionImage.fromBitmap(bitmap), (FrameMetadata) null);
        }
    }

    public void process(Image image, int i) {
        if (!this.shouldThrottle.get()) {
            detectInVisionImage(FirebaseVisionImage.fromMediaImage(image, i), new FrameMetadata.Builder().setWidth(image.getWidth()).setHeight(image.getHeight()).build());
        }
    }

    public void process(ByteBuffer byteBuffer, FrameMetadata frameMetadata) {
        if (!this.shouldThrottle.get()) {
            detectInVisionImage(FirebaseVisionImage.fromByteBuffer(byteBuffer, new FirebaseVisionImageMetadata.Builder().setFormat(17).setWidth(frameMetadata.getWidth()).setHeight(frameMetadata.getHeight()).setRotation(frameMetadata.getRotation()).build()), frameMetadata);
        }
    }

    public void stop() {
    }
}

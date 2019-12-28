package co.apperto.fastqrreaderview.java.barcodescanning;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import co.apperto.fastqrreaderview.common.GraphicOverlay;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;

public class BarcodeGraphic extends GraphicOverlay.Graphic {
    private static final float STROKE_WIDTH = 4.0f;
    private static final int TEXT_COLOR = -1;
    private static final float TEXT_SIZE = 54.0f;
    private final FirebaseVisionBarcode barcode;
    private final Paint barcodePaint;
    private final Paint rectPaint;

    public void draw(Canvas canvas) {
        FirebaseVisionBarcode firebaseVisionBarcode = this.barcode;
        if (firebaseVisionBarcode != null) {
            RectF rectF = new RectF(firebaseVisionBarcode.getBoundingBox());
            rectF.left = translateX(rectF.left);
            rectF.top = translateY(rectF.top);
            rectF.right = translateX(rectF.right);
            rectF.bottom = translateY(rectF.bottom);
            canvas.drawRect(rectF, this.rectPaint);
            canvas.drawText(this.barcode.getRawValue(), rectF.left, rectF.bottom, this.barcodePaint);
            return;
        }
        throw new IllegalStateException("Attempting to draw a null barcode.");
    }
}

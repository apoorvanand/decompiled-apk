package co.apperto.fastqrreaderview.common;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import java.util.HashSet;
import java.util.Set;

public class GraphicOverlay extends View {
    /* access modifiers changed from: private */
    public int facing = 0;
    private Set<Graphic> graphics = new HashSet();
    /* access modifiers changed from: private */
    public float heightScaleFactor = 1.0f;
    private final Object lock = new Object();
    private int previewHeight;
    private int previewWidth;
    /* access modifiers changed from: private */
    public float widthScaleFactor = 1.0f;

    public static abstract class Graphic {
        private GraphicOverlay overlay;

        public Graphic(GraphicOverlay graphicOverlay) {
            this.overlay = graphicOverlay;
        }

        public abstract void draw(Canvas canvas);

        public Context getApplicationContext() {
            return this.overlay.getContext().getApplicationContext();
        }

        public void postInvalidate() {
            this.overlay.postInvalidate();
        }

        public float scaleX(float f) {
            return f * this.overlay.widthScaleFactor;
        }

        public float scaleY(float f) {
            return f * this.overlay.heightScaleFactor;
        }

        public float translateX(float f) {
            return this.overlay.facing == 1 ? ((float) this.overlay.getWidth()) - scaleX(f) : scaleX(f);
        }

        public float translateY(float f) {
            return scaleY(f);
        }
    }

    public GraphicOverlay(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void add(Graphic graphic) {
        synchronized (this.lock) {
            this.graphics.add(graphic);
        }
        postInvalidate();
    }

    public void clear() {
        synchronized (this.lock) {
            this.graphics.clear();
        }
        postInvalidate();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        synchronized (this.lock) {
            if (!(this.previewWidth == 0 || this.previewHeight == 0)) {
                this.widthScaleFactor = ((float) canvas.getWidth()) / ((float) this.previewWidth);
                this.heightScaleFactor = ((float) canvas.getHeight()) / ((float) this.previewHeight);
            }
            for (Graphic draw : this.graphics) {
                draw.draw(canvas);
            }
        }
    }

    public void remove(Graphic graphic) {
        synchronized (this.lock) {
            this.graphics.remove(graphic);
        }
        postInvalidate();
    }

    public void setCameraInfo(int i, int i2, int i3) {
        synchronized (this.lock) {
            this.previewWidth = i;
            this.previewHeight = i2;
            this.facing = i3;
        }
        postInvalidate();
    }
}

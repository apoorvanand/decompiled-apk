package co.apperto.fastqrreaderview.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.ViewGroup;
import com.google.android.gms.common.images.Size;
import java.io.IOException;

public class CameraSourcePreview extends ViewGroup {
    private static final String TAG = "MIDemoApp:Preview";
    SurfaceTexture a;
    private CameraSource cameraSource;
    private Context context;
    private GraphicOverlay overlay;
    private boolean startRequested = false;
    /* access modifiers changed from: private */
    public boolean surfaceAvailable = false;

    private class SurfaceCallback implements SurfaceHolder.Callback {
        private SurfaceCallback() {
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            boolean unused = CameraSourcePreview.this.surfaceAvailable = true;
            try {
                CameraSourcePreview.this.startIfReady();
            } catch (IOException e) {
                Log.e(CameraSourcePreview.TAG, "Could not start camera source.", e);
            }
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            boolean unused = CameraSourcePreview.this.surfaceAvailable = false;
        }
    }

    public CameraSourcePreview(Context context2, AttributeSet attributeSet, SurfaceTexture surfaceTexture) {
        super(context2, attributeSet);
        this.context = context2;
        this.a = surfaceTexture;
        this.surfaceAvailable = true;
        try {
            startIfReady();
        } catch (IOException e) {
            Log.e(TAG, "Could not start camera source.", e);
        }
    }

    private boolean isPortraitMode() {
        int i = this.context.getResources().getConfiguration().orientation;
        if (i == 2) {
            return false;
        }
        if (i == 1) {
            return true;
        }
        Log.d(TAG, "isPortraitMode returning false by default");
        return false;
    }

    /* access modifiers changed from: private */
    @SuppressLint({"MissingPermission"})
    public void startIfReady() {
        if (this.startRequested && this.surfaceAvailable) {
            this.cameraSource.start(this.a);
            if (this.overlay != null) {
                Size previewSize = this.cameraSource.getPreviewSize();
                int min = Math.min(previewSize.getWidth(), previewSize.getHeight());
                int max = Math.max(previewSize.getWidth(), previewSize.getHeight());
                if (isPortraitMode()) {
                    this.overlay.setCameraInfo(min, max, this.cameraSource.getCameraFacing());
                } else {
                    this.overlay.setCameraInfo(max, min, this.cameraSource.getCameraFacing());
                }
                this.overlay.clear();
            }
            this.startRequested = false;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        Size previewSize;
        CameraSource cameraSource2 = this.cameraSource;
        if (cameraSource2 == null || (previewSize = cameraSource2.getPreviewSize()) == null) {
            i6 = 320;
            i5 = 240;
        } else {
            i6 = previewSize.getWidth();
            i5 = previewSize.getHeight();
        }
        if (!isPortraitMode()) {
            int i7 = i6;
            i6 = i5;
            i5 = i7;
        }
        int i8 = i3 - i;
        int i9 = i4 - i2;
        float f = (float) i5;
        float f2 = (float) i6;
        int i10 = (int) ((((float) i8) / f) * f2);
        if (i10 > i9) {
            i8 = (int) ((((float) i9) / f2) * f);
            i10 = i9;
        }
        for (int i11 = 0; i11 < getChildCount(); i11++) {
            getChildAt(i11).layout(0, 0, i8, i10);
            Log.d(TAG, "Assigned view: " + i11);
        }
        try {
            startIfReady();
        } catch (IOException e) {
            Log.e(TAG, "Could not start camera source.", e);
        }
    }

    public void release() {
        CameraSource cameraSource2 = this.cameraSource;
        if (cameraSource2 != null) {
            cameraSource2.release();
            this.cameraSource = null;
        }
    }

    public void start(CameraSource cameraSource2) {
        if (cameraSource2 == null) {
            stop();
        }
        this.cameraSource = cameraSource2;
        if (this.cameraSource != null) {
            this.startRequested = true;
            startIfReady();
        }
    }

    public void start(CameraSource cameraSource2, GraphicOverlay graphicOverlay) {
        this.overlay = graphicOverlay;
        start(cameraSource2);
    }

    public void stop() {
        CameraSource cameraSource2 = this.cameraSource;
        if (cameraSource2 != null) {
            cameraSource2.stop();
        }
    }
}

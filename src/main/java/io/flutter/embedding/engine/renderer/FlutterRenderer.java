package io.flutter.embedding.engine.renderer;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.os.Handler;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.flutter.Log;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.view.TextureRegistry;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;

@TargetApi(16)
public class FlutterRenderer implements TextureRegistry {
    private static final String TAG = "FlutterRenderer";
    private final FlutterJNI flutterJNI;
    /* access modifiers changed from: private */
    public boolean hasRenderedFirstFrame = false;
    private final AtomicLong nextTextureId = new AtomicLong(0);
    private final OnFirstFrameRenderedListener onFirstFrameRenderedListener = new OnFirstFrameRenderedListener() {
        public void onFirstFrameRendered() {
            boolean unused = FlutterRenderer.this.hasRenderedFirstFrame = true;
        }
    };
    private RenderSurface renderSurface;

    public interface RenderSurface {
        void addOnFirstFrameRenderedListener(@NonNull OnFirstFrameRenderedListener onFirstFrameRenderedListener);

        void attachToRenderer(@NonNull FlutterRenderer flutterRenderer);

        void detachFromRenderer();

        void onFirstFrameRendered();

        void removeOnFirstFrameRenderedListener(@NonNull OnFirstFrameRenderedListener onFirstFrameRenderedListener);
    }

    final class SurfaceTextureRegistryEntry implements TextureRegistry.SurfaceTextureEntry {
        /* access modifiers changed from: private */
        public final long id;
        private SurfaceTexture.OnFrameAvailableListener onFrameListener = new SurfaceTexture.OnFrameAvailableListener() {
            public void onFrameAvailable(@NonNull SurfaceTexture surfaceTexture) {
                if (!SurfaceTextureRegistryEntry.this.released) {
                    FlutterRenderer.this.markTextureFrameAvailable(SurfaceTextureRegistryEntry.this.id);
                }
            }
        };
        /* access modifiers changed from: private */
        public boolean released;
        @NonNull
        private final SurfaceTexture surfaceTexture;

        SurfaceTextureRegistryEntry(long j, SurfaceTexture surfaceTexture2) {
            this.id = j;
            this.surfaceTexture = surfaceTexture2;
            if (Build.VERSION.SDK_INT >= 21) {
                this.surfaceTexture.setOnFrameAvailableListener(this.onFrameListener, new Handler());
            } else {
                this.surfaceTexture.setOnFrameAvailableListener(this.onFrameListener);
            }
        }

        public long id() {
            return this.id;
        }

        public void release() {
            if (!this.released) {
                Log.v(FlutterRenderer.TAG, "Releasing a SurfaceTexture (" + this.id + ").");
                this.surfaceTexture.release();
                FlutterRenderer.this.unregisterTexture(this.id);
                this.released = true;
            }
        }

        @NonNull
        public SurfaceTexture surfaceTexture() {
            return this.surfaceTexture;
        }
    }

    public static final class ViewportMetrics {
        public float devicePixelRatio = 1.0f;
        public int height = 0;
        public int paddingBottom = 0;
        public int paddingLeft = 0;
        public int paddingRight = 0;
        public int paddingTop = 0;
        public int systemGestureInsetBottom = 0;
        public int systemGestureInsetLeft = 0;
        public int systemGestureInsetRight = 0;
        public int systemGestureInsetTop = 0;
        public int viewInsetBottom = 0;
        public int viewInsetLeft = 0;
        public int viewInsetRight = 0;
        public int viewInsetTop = 0;
        public int width = 0;
    }

    public FlutterRenderer(@NonNull FlutterJNI flutterJNI2) {
        this.flutterJNI = flutterJNI2;
        this.flutterJNI.addOnFirstFrameRenderedListener(this.onFirstFrameRenderedListener);
    }

    /* access modifiers changed from: private */
    public void markTextureFrameAvailable(long j) {
        this.flutterJNI.markTextureFrameAvailable(j);
    }

    private void registerTexture(long j, @NonNull SurfaceTexture surfaceTexture) {
        this.flutterJNI.registerTexture(j, surfaceTexture);
    }

    /* access modifiers changed from: private */
    public void unregisterTexture(long j) {
        this.flutterJNI.unregisterTexture(j);
    }

    public void addOnFirstFrameRenderedListener(@NonNull OnFirstFrameRenderedListener onFirstFrameRenderedListener2) {
        this.flutterJNI.addOnFirstFrameRenderedListener(onFirstFrameRenderedListener2);
        if (this.hasRenderedFirstFrame) {
            onFirstFrameRenderedListener2.onFirstFrameRendered();
        }
    }

    public void attachToRenderSurface(@NonNull RenderSurface renderSurface2) {
        Log.v(TAG, "Attaching to RenderSurface.");
        if (this.renderSurface != null) {
            Log.v(TAG, "Already attached to a RenderSurface. Detaching from old one and attaching to new one.");
            detachFromRenderSurface();
        }
        this.renderSurface = renderSurface2;
        this.renderSurface.attachToRenderer(this);
        this.flutterJNI.setRenderSurface(renderSurface2);
    }

    @TargetApi(16)
    public TextureRegistry.SurfaceTextureEntry createSurfaceTexture() {
        Log.v(TAG, "Creating a SurfaceTexture.");
        SurfaceTexture surfaceTexture = new SurfaceTexture(0);
        surfaceTexture.detachFromGLContext();
        SurfaceTextureRegistryEntry surfaceTextureRegistryEntry = new SurfaceTextureRegistryEntry(this.nextTextureId.getAndIncrement(), surfaceTexture);
        Log.v(TAG, "New SurfaceTexture ID: " + surfaceTextureRegistryEntry.id());
        registerTexture(surfaceTextureRegistryEntry.id(), surfaceTexture);
        return surfaceTextureRegistryEntry;
    }

    public void detachFromRenderSurface() {
        Log.v(TAG, "Detaching from RenderSurface.");
        RenderSurface renderSurface2 = this.renderSurface;
        if (renderSurface2 != null) {
            renderSurface2.detachFromRenderer();
            this.renderSurface = null;
            surfaceDestroyed();
            this.flutterJNI.setRenderSurface((RenderSurface) null);
        }
    }

    public void dispatchPointerDataPacket(@NonNull ByteBuffer byteBuffer, int i) {
        this.flutterJNI.dispatchPointerDataPacket(byteBuffer, i);
    }

    public void dispatchSemanticsAction(int i, int i2, @Nullable ByteBuffer byteBuffer, int i3) {
        this.flutterJNI.dispatchSemanticsAction(i, i2, byteBuffer, i3);
    }

    public Bitmap getBitmap() {
        return this.flutterJNI.getBitmap();
    }

    public boolean hasRenderedFirstFrame() {
        return this.hasRenderedFirstFrame;
    }

    public boolean isAttachedTo(@NonNull RenderSurface renderSurface2) {
        return this.renderSurface == renderSurface2;
    }

    public boolean isSoftwareRenderingEnabled() {
        return FlutterJNI.nativeGetIsSoftwareRenderingEnabled();
    }

    public void removeOnFirstFrameRenderedListener(@NonNull OnFirstFrameRenderedListener onFirstFrameRenderedListener2) {
        this.flutterJNI.removeOnFirstFrameRenderedListener(onFirstFrameRenderedListener2);
    }

    public void setAccessibilityFeatures(int i) {
        this.flutterJNI.setAccessibilityFeatures(i);
    }

    public void setSemanticsEnabled(boolean z) {
        this.flutterJNI.setSemanticsEnabled(z);
    }

    public void setViewportMetrics(@NonNull ViewportMetrics viewportMetrics) {
        ViewportMetrics viewportMetrics2 = viewportMetrics;
        Log.v(TAG, "Setting viewport metrics\nSize: " + viewportMetrics2.width + " x " + viewportMetrics2.height + "\nPadding - L: " + viewportMetrics2.paddingLeft + ", T: " + viewportMetrics2.paddingTop + ", R: " + viewportMetrics2.paddingRight + ", B: " + viewportMetrics2.paddingBottom + "\nInsets - L: " + viewportMetrics2.viewInsetLeft + ", T: " + viewportMetrics2.viewInsetTop + ", R: " + viewportMetrics2.viewInsetRight + ", B: " + viewportMetrics2.viewInsetBottom + "\nSystem Gesture Insets - L: " + viewportMetrics2.systemGestureInsetLeft + ", T: " + viewportMetrics2.systemGestureInsetTop + ", R: " + viewportMetrics2.systemGestureInsetRight + ", B: " + viewportMetrics2.viewInsetBottom);
        this.flutterJNI.setViewportMetrics(viewportMetrics2.devicePixelRatio, viewportMetrics2.width, viewportMetrics2.height, viewportMetrics2.paddingTop, viewportMetrics2.paddingRight, viewportMetrics2.paddingBottom, viewportMetrics2.paddingLeft, viewportMetrics2.viewInsetTop, viewportMetrics2.viewInsetRight, viewportMetrics2.viewInsetBottom, viewportMetrics2.viewInsetLeft, viewportMetrics2.systemGestureInsetTop, viewportMetrics2.systemGestureInsetRight, viewportMetrics2.systemGestureInsetBottom, viewportMetrics2.systemGestureInsetLeft);
    }

    public void surfaceChanged(int i, int i2) {
        this.flutterJNI.onSurfaceChanged(i, i2);
    }

    public void surfaceCreated(@NonNull Surface surface) {
        this.flutterJNI.onSurfaceCreated(surface);
    }

    public void surfaceDestroyed() {
        this.flutterJNI.onSurfaceDestroyed();
    }
}

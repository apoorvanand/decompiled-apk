package io.flutter.embedding.android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.flutter.Log;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
import io.flutter.embedding.engine.renderer.OnFirstFrameRenderedListener;
import java.util.HashSet;
import java.util.Set;

public class FlutterSurfaceView extends SurfaceView implements FlutterRenderer.RenderSurface {
    private static final String TAG = "FlutterSurfaceView";
    @Nullable
    private FlutterRenderer flutterRenderer;
    /* access modifiers changed from: private */
    public boolean isAttachedToFlutterRenderer;
    /* access modifiers changed from: private */
    public boolean isSurfaceAvailableForRendering;
    @NonNull
    private Set<OnFirstFrameRenderedListener> onFirstFrameRenderedListeners;
    private final boolean renderTransparently;
    private final SurfaceHolder.Callback surfaceCallback;

    public FlutterSurfaceView(@NonNull Context context) {
        this(context, (AttributeSet) null, false);
    }

    public FlutterSurfaceView(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        this(context, attributeSet, false);
    }

    private FlutterSurfaceView(@NonNull Context context, @Nullable AttributeSet attributeSet, boolean z) {
        super(context, attributeSet);
        this.isSurfaceAvailableForRendering = false;
        this.isAttachedToFlutterRenderer = false;
        this.onFirstFrameRenderedListeners = new HashSet();
        this.surfaceCallback = new SurfaceHolder.Callback() {
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                Log.v(FlutterSurfaceView.TAG, "SurfaceHolder.Callback.surfaceChanged()");
                if (FlutterSurfaceView.this.isAttachedToFlutterRenderer) {
                    FlutterSurfaceView.this.changeSurfaceSize(i2, i3);
                }
            }

            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                Log.v(FlutterSurfaceView.TAG, "SurfaceHolder.Callback.surfaceCreated()");
                boolean unused = FlutterSurfaceView.this.isSurfaceAvailableForRendering = true;
                if (FlutterSurfaceView.this.isAttachedToFlutterRenderer) {
                    FlutterSurfaceView.this.connectSurfaceToRenderer();
                }
            }

            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
                Log.v(FlutterSurfaceView.TAG, "SurfaceHolder.Callback.surfaceDestroyed()");
                boolean unused = FlutterSurfaceView.this.isSurfaceAvailableForRendering = false;
                if (FlutterSurfaceView.this.isAttachedToFlutterRenderer) {
                    FlutterSurfaceView.this.disconnectSurfaceFromRenderer();
                }
            }
        };
        this.renderTransparently = z;
        init();
    }

    public FlutterSurfaceView(@NonNull Context context, boolean z) {
        this(context, (AttributeSet) null, z);
    }

    /* access modifiers changed from: private */
    public void changeSurfaceSize(int i, int i2) {
        if (this.flutterRenderer != null) {
            Log.v(TAG, "Notifying FlutterRenderer that Android surface size has changed to " + i + " x " + i2);
            this.flutterRenderer.surfaceChanged(i, i2);
            return;
        }
        throw new IllegalStateException("changeSurfaceSize() should only be called when flutterRenderer is non-null.");
    }

    /* access modifiers changed from: private */
    public void connectSurfaceToRenderer() {
        if (this.flutterRenderer == null || getHolder() == null) {
            throw new IllegalStateException("connectSurfaceToRenderer() should only be called when flutterRenderer and getHolder() are non-null.");
        }
        this.flutterRenderer.surfaceCreated(getHolder().getSurface());
    }

    /* access modifiers changed from: private */
    public void disconnectSurfaceFromRenderer() {
        FlutterRenderer flutterRenderer2 = this.flutterRenderer;
        if (flutterRenderer2 != null) {
            flutterRenderer2.surfaceDestroyed();
            return;
        }
        throw new IllegalStateException("disconnectSurfaceFromRenderer() should only be called when flutterRenderer is non-null.");
    }

    private void init() {
        if (this.renderTransparently) {
            getHolder().setFormat(-2);
            setZOrderOnTop(true);
        }
        getHolder().addCallback(this.surfaceCallback);
        setAlpha(0.0f);
    }

    public void addOnFirstFrameRenderedListener(@NonNull OnFirstFrameRenderedListener onFirstFrameRenderedListener) {
        this.onFirstFrameRenderedListeners.add(onFirstFrameRenderedListener);
    }

    public void attachToRenderer(@NonNull FlutterRenderer flutterRenderer2) {
        Log.v(TAG, "Attaching to FlutterRenderer.");
        if (this.flutterRenderer != null) {
            Log.v(TAG, "Already connected to a FlutterRenderer. Detaching from old one and attaching to new one.");
            this.flutterRenderer.detachFromRenderSurface();
        }
        this.flutterRenderer = flutterRenderer2;
        this.isAttachedToFlutterRenderer = true;
        if (this.isSurfaceAvailableForRendering) {
            Log.v(TAG, "Surface is available for rendering. Connecting FlutterRenderer to Android surface.");
            connectSurfaceToRenderer();
        }
    }

    public void detachFromRenderer() {
        if (this.flutterRenderer != null) {
            if (getWindowToken() != null) {
                Log.v(TAG, "Disconnecting FlutterRenderer from Android surface.");
                disconnectSurfaceFromRenderer();
            }
            setAlpha(0.0f);
            this.flutterRenderer = null;
            this.isAttachedToFlutterRenderer = false;
            return;
        }
        Log.w(TAG, "detachFromRenderer() invoked when no FlutterRenderer was attached.");
    }

    public void onFirstFrameRendered() {
        Log.v(TAG, "onFirstFrameRendered()");
        setAlpha(1.0f);
        for (OnFirstFrameRenderedListener onFirstFrameRendered : this.onFirstFrameRenderedListeners) {
            onFirstFrameRendered.onFirstFrameRendered();
        }
    }

    public void removeOnFirstFrameRenderedListener(@NonNull OnFirstFrameRenderedListener onFirstFrameRenderedListener) {
        this.onFirstFrameRenderedListeners.remove(onFirstFrameRenderedListener);
    }
}

package io.flutter.plugin.platform;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.view.Surface;
import android.view.View;
import android.view.ViewTreeObserver;
import com.facebook.internal.ServerProtocol;
import io.flutter.plugin.platform.SingleViewPresentation;
import io.flutter.view.TextureRegistry;

@TargetApi(20)
class VirtualDisplayController {
    private final AccessibilityEventsDelegate accessibilityEventsDelegate;
    private final Context context;
    private final int densityDpi;
    private final View.OnFocusChangeListener focusChangeListener;
    private SingleViewPresentation presentation;
    private Surface surface;
    private final TextureRegistry.SurfaceTextureEntry textureEntry;
    private VirtualDisplay virtualDisplay;

    @TargetApi(16)
    static class OneTimeOnDrawListener implements ViewTreeObserver.OnDrawListener {
        Runnable mOnDrawRunnable;
        final View mView;

        OneTimeOnDrawListener(View view, Runnable runnable) {
            this.mView = view;
            this.mOnDrawRunnable = runnable;
        }

        static void schedule(View view, Runnable runnable) {
            view.getViewTreeObserver().addOnDrawListener(new OneTimeOnDrawListener(view, runnable));
        }

        public void onDraw() {
            Runnable runnable = this.mOnDrawRunnable;
            if (runnable != null) {
                runnable.run();
                this.mOnDrawRunnable = null;
                this.mView.post(new Runnable() {
                    public void run() {
                        OneTimeOnDrawListener.this.mView.getViewTreeObserver().removeOnDrawListener(OneTimeOnDrawListener.this);
                    }
                });
            }
        }
    }

    private VirtualDisplayController(Context context2, AccessibilityEventsDelegate accessibilityEventsDelegate2, VirtualDisplay virtualDisplay2, PlatformViewFactory platformViewFactory, Surface surface2, TextureRegistry.SurfaceTextureEntry surfaceTextureEntry, View.OnFocusChangeListener onFocusChangeListener, int i, Object obj) {
        Context context3 = context2;
        this.context = context3;
        AccessibilityEventsDelegate accessibilityEventsDelegate3 = accessibilityEventsDelegate2;
        this.accessibilityEventsDelegate = accessibilityEventsDelegate3;
        this.textureEntry = surfaceTextureEntry;
        View.OnFocusChangeListener onFocusChangeListener2 = onFocusChangeListener;
        this.focusChangeListener = onFocusChangeListener2;
        this.surface = surface2;
        this.virtualDisplay = virtualDisplay2;
        this.densityDpi = context2.getResources().getDisplayMetrics().densityDpi;
        this.presentation = new SingleViewPresentation(context3, this.virtualDisplay.getDisplay(), platformViewFactory, accessibilityEventsDelegate3, i, obj, onFocusChangeListener2);
        this.presentation.show();
    }

    public static VirtualDisplayController create(Context context2, AccessibilityEventsDelegate accessibilityEventsDelegate2, PlatformViewFactory platformViewFactory, TextureRegistry.SurfaceTextureEntry surfaceTextureEntry, int i, int i2, int i3, Object obj, View.OnFocusChangeListener onFocusChangeListener) {
        int i4 = i;
        int i5 = i2;
        surfaceTextureEntry.surfaceTexture().setDefaultBufferSize(i4, i5);
        Surface surface2 = new Surface(surfaceTextureEntry.surfaceTexture());
        Context context3 = context2;
        VirtualDisplay createVirtualDisplay = ((DisplayManager) context2.getSystemService(ServerProtocol.DIALOG_PARAM_DISPLAY)).createVirtualDisplay("flutter-vd", i4, i5, context2.getResources().getDisplayMetrics().densityDpi, surface2, 0);
        if (createVirtualDisplay == null) {
            return null;
        }
        return new VirtualDisplayController(context2, accessibilityEventsDelegate2, createVirtualDisplay, platformViewFactory, surface2, surfaceTextureEntry, onFocusChangeListener, i3, obj);
    }

    public void dispose() {
        PlatformView view = this.presentation.getView();
        this.presentation.cancel();
        this.presentation.detachState();
        view.dispose();
        this.virtualDisplay.release();
        this.textureEntry.release();
    }

    public View getView() {
        SingleViewPresentation singleViewPresentation = this.presentation;
        if (singleViewPresentation == null) {
            return null;
        }
        return singleViewPresentation.getView().getView();
    }

    /* access modifiers changed from: package-private */
    public void onInputConnectionLocked() {
        SingleViewPresentation singleViewPresentation = this.presentation;
        if (singleViewPresentation != null && singleViewPresentation.getView() != null) {
            this.presentation.getView().onInputConnectionLocked();
        }
    }

    /* access modifiers changed from: package-private */
    public void onInputConnectionUnlocked() {
        SingleViewPresentation singleViewPresentation = this.presentation;
        if (singleViewPresentation != null && singleViewPresentation.getView() != null) {
            this.presentation.getView().onInputConnectionUnlocked();
        }
    }

    public void resize(int i, int i2, Runnable runnable) {
        boolean isFocused = getView().isFocused();
        SingleViewPresentation.PresentationState detachState = this.presentation.detachState();
        this.virtualDisplay.setSurface((Surface) null);
        this.virtualDisplay.release();
        this.textureEntry.surfaceTexture().setDefaultBufferSize(i, i2);
        this.virtualDisplay = ((DisplayManager) this.context.getSystemService(ServerProtocol.DIALOG_PARAM_DISPLAY)).createVirtualDisplay("flutter-vd", i, i2, this.densityDpi, this.surface, 0);
        final View view = getView();
        final Runnable runnable2 = runnable;
        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            public void onViewAttachedToWindow(View view) {
                OneTimeOnDrawListener.schedule(view, new Runnable() {
                    public void run() {
                        view.postDelayed(runnable2, 128);
                    }
                });
                view.removeOnAttachStateChangeListener(this);
            }

            public void onViewDetachedFromWindow(View view) {
            }
        });
        this.presentation = new SingleViewPresentation(this.context, this.virtualDisplay.getDisplay(), this.accessibilityEventsDelegate, detachState, this.focusChangeListener, isFocused);
        this.presentation.show();
    }
}

package io.flutter.embedding.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Build;
import android.os.LocaleList;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import io.flutter.Log;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
import io.flutter.embedding.engine.renderer.OnFirstFrameRenderedListener;
import io.flutter.plugin.editing.TextInputPlugin;
import io.flutter.view.AccessibilityBridge;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FlutterView extends FrameLayout {
    private static final String TAG = "FlutterView";
    @Nullable
    private AccessibilityBridge accessibilityBridge;
    @Nullable
    private AndroidKeyProcessor androidKeyProcessor;
    @Nullable
    private AndroidTouchProcessor androidTouchProcessor;
    /* access modifiers changed from: private */
    public boolean didRenderFirstFrame;
    @Nullable
    private FlutterEngine flutterEngine;
    @NonNull
    private final Set<FlutterEngineAttachmentListener> flutterEngineAttachmentListeners;
    private final AccessibilityBridge.OnAccessibilityChangeListener onAccessibilityChangeListener;
    private final OnFirstFrameRenderedListener onFirstFrameRenderedListener;
    /* access modifiers changed from: private */
    public final Set<OnFirstFrameRenderedListener> onFirstFrameRenderedListeners;
    @NonNull
    private RenderMode renderMode;
    @Nullable
    private FlutterRenderer.RenderSurface renderSurface;
    @Nullable
    private TextInputPlugin textInputPlugin;
    @Nullable
    private TransparencyMode transparencyMode;
    private final FlutterRenderer.ViewportMetrics viewportMetrics;

    @VisibleForTesting
    public interface FlutterEngineAttachmentListener {
        void onFlutterEngineAttachedToFlutterView(@NonNull FlutterEngine flutterEngine);

        void onFlutterEngineDetachedFromFlutterView();
    }

    public enum RenderMode {
        surface,
        texture
    }

    public enum TransparencyMode {
        opaque,
        transparent
    }

    public FlutterView(@NonNull Context context) {
        this(context, (AttributeSet) null, (RenderMode) null, (TransparencyMode) null);
    }

    public FlutterView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, (RenderMode) null, (TransparencyMode) null);
    }

    private FlutterView(@NonNull Context context, @Nullable AttributeSet attributeSet, @Nullable RenderMode renderMode2, @Nullable TransparencyMode transparencyMode2) {
        super(context, attributeSet);
        this.onFirstFrameRenderedListeners = new HashSet();
        this.flutterEngineAttachmentListeners = new HashSet();
        this.viewportMetrics = new FlutterRenderer.ViewportMetrics();
        this.onAccessibilityChangeListener = new AccessibilityBridge.OnAccessibilityChangeListener() {
            public void onAccessibilityChanged(boolean z, boolean z2) {
                FlutterView.this.resetWillNotDraw(z, z2);
            }
        };
        this.onFirstFrameRenderedListener = new OnFirstFrameRenderedListener() {
            public void onFirstFrameRendered() {
                boolean unused = FlutterView.this.didRenderFirstFrame = true;
                for (OnFirstFrameRenderedListener onFirstFrameRendered : FlutterView.this.onFirstFrameRenderedListeners) {
                    onFirstFrameRendered.onFirstFrameRendered();
                }
            }
        };
        this.renderMode = renderMode2 == null ? RenderMode.surface : renderMode2;
        this.transparencyMode = transparencyMode2 == null ? TransparencyMode.opaque : transparencyMode2;
        init();
    }

    public FlutterView(@NonNull Context context, @NonNull RenderMode renderMode2) {
        this(context, (AttributeSet) null, renderMode2, (TransparencyMode) null);
    }

    public FlutterView(@NonNull Context context, @NonNull RenderMode renderMode2, @NonNull TransparencyMode transparencyMode2) {
        this(context, (AttributeSet) null, renderMode2, transparencyMode2);
    }

    public FlutterView(@NonNull Context context, @NonNull TransparencyMode transparencyMode2) {
        this(context, (AttributeSet) null, RenderMode.surface, transparencyMode2);
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [io.flutter.embedding.android.FlutterTextureView] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void init() {
        /*
            r5 = this;
            java.lang.String r0 = "FlutterView"
            java.lang.String r1 = "Initializing FlutterView"
            io.flutter.Log.v(r0, r1)
            int[] r0 = io.flutter.embedding.android.FlutterView.AnonymousClass3.$SwitchMap$io$flutter$embedding$android$FlutterView$RenderMode
            io.flutter.embedding.android.FlutterView$RenderMode r1 = r5.renderMode
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 1
            switch(r0) {
                case 1: goto L_0x002c;
                case 2: goto L_0x0016;
                default: goto L_0x0015;
            }
        L_0x0015:
            goto L_0x0046
        L_0x0016:
            java.lang.String r0 = "FlutterView"
            java.lang.String r2 = "Internally using a FlutterTextureView."
            io.flutter.Log.v(r0, r2)
            io.flutter.embedding.android.FlutterTextureView r0 = new io.flutter.embedding.android.FlutterTextureView
            android.content.Context r2 = r5.getContext()
            r0.<init>(r2)
        L_0x0026:
            r5.renderSurface = r0
            r5.addView(r0)
            goto L_0x0046
        L_0x002c:
            java.lang.String r0 = "FlutterView"
            java.lang.String r2 = "Internally using a FlutterSurfaceView."
            io.flutter.Log.v(r0, r2)
            io.flutter.embedding.android.FlutterSurfaceView r0 = new io.flutter.embedding.android.FlutterSurfaceView
            android.content.Context r2 = r5.getContext()
            io.flutter.embedding.android.FlutterView$TransparencyMode r3 = r5.transparencyMode
            io.flutter.embedding.android.FlutterView$TransparencyMode r4 = io.flutter.embedding.android.FlutterView.TransparencyMode.transparent
            if (r3 != r4) goto L_0x0041
            r3 = 1
            goto L_0x0042
        L_0x0041:
            r3 = 0
        L_0x0042:
            r0.<init>((android.content.Context) r2, (boolean) r3)
            goto L_0x0026
        L_0x0046:
            r5.setFocusable(r1)
            r5.setFocusableInTouchMode(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.embedding.android.FlutterView.init():void");
    }

    /* access modifiers changed from: private */
    public void resetWillNotDraw(boolean z, boolean z2) {
        boolean z3 = false;
        if (!this.flutterEngine.getRenderer().isSoftwareRenderingEnabled() && !z && !z2) {
            z3 = true;
        }
        setWillNotDraw(z3);
    }

    private void sendLocalesToFlutter(@NonNull Configuration configuration) {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 24) {
            LocaleList locales = configuration.getLocales();
            int size = locales.size();
            for (int i = 0; i < size; i++) {
                arrayList.add(locales.get(i));
            }
        } else {
            arrayList.add(configuration.locale);
        }
        this.flutterEngine.getLocalizationChannel().sendLocales(arrayList);
    }

    private void sendUserSettingsToFlutter() {
        this.flutterEngine.getSettingsChannel().startMessage().setTextScaleFactor(getResources().getConfiguration().fontScale).setUse24HourFormat(DateFormat.is24HourFormat(getContext())).send();
    }

    private void sendViewportMetricsToFlutter() {
        if (!isAttachedToFlutterEngine()) {
            Log.w(TAG, "Tried to send viewport metrics from Android to Flutter but this FlutterView was not attached to a FlutterEngine.");
            return;
        }
        this.viewportMetrics.devicePixelRatio = getResources().getDisplayMetrics().density;
        this.flutterEngine.getRenderer().setViewportMetrics(this.viewportMetrics);
    }

    @VisibleForTesting
    public void addFlutterEngineAttachmentListener(@NonNull FlutterEngineAttachmentListener flutterEngineAttachmentListener) {
        this.flutterEngineAttachmentListeners.add(flutterEngineAttachmentListener);
    }

    public void addOnFirstFrameRenderedListener(@NonNull OnFirstFrameRenderedListener onFirstFrameRenderedListener2) {
        this.onFirstFrameRenderedListeners.add(onFirstFrameRenderedListener2);
    }

    public void attachToFlutterEngine(@NonNull FlutterEngine flutterEngine2) {
        Log.d(TAG, "Attaching to a FlutterEngine: " + flutterEngine2);
        if (isAttachedToFlutterEngine()) {
            if (flutterEngine2 == this.flutterEngine) {
                Log.d(TAG, "Already attached to this engine. Doing nothing.");
                return;
            } else {
                Log.d(TAG, "Currently attached to a different engine. Detaching and then attaching to new engine.");
                detachFromFlutterEngine();
            }
        }
        this.flutterEngine = flutterEngine2;
        FlutterRenderer renderer = this.flutterEngine.getRenderer();
        this.didRenderFirstFrame = renderer.hasRenderedFirstFrame();
        renderer.attachToRenderSurface(this.renderSurface);
        renderer.addOnFirstFrameRenderedListener(this.onFirstFrameRenderedListener);
        this.textInputPlugin = new TextInputPlugin(this, this.flutterEngine.getDartExecutor(), this.flutterEngine.getPlatformViewsController());
        this.androidKeyProcessor = new AndroidKeyProcessor(this.flutterEngine.getKeyEventChannel(), this.textInputPlugin);
        this.androidTouchProcessor = new AndroidTouchProcessor(this.flutterEngine.getRenderer());
        this.accessibilityBridge = new AccessibilityBridge(this, flutterEngine2.getAccessibilityChannel(), (AccessibilityManager) getContext().getSystemService("accessibility"), getContext().getContentResolver(), this.flutterEngine.getPlatformViewsController());
        this.accessibilityBridge.setOnAccessibilityChangeListener(this.onAccessibilityChangeListener);
        resetWillNotDraw(this.accessibilityBridge.isAccessibilityEnabled(), this.accessibilityBridge.isTouchExplorationEnabled());
        this.flutterEngine.getPlatformViewsController().attachAccessibilityBridge(this.accessibilityBridge);
        this.textInputPlugin.getInputMethodManager().restartInput(this);
        sendUserSettingsToFlutter();
        sendLocalesToFlutter(getResources().getConfiguration());
        sendViewportMetricsToFlutter();
        for (FlutterEngineAttachmentListener onFlutterEngineAttachedToFlutterView : this.flutterEngineAttachmentListeners) {
            onFlutterEngineAttachedToFlutterView.onFlutterEngineAttachedToFlutterView(flutterEngine2);
        }
        if (this.didRenderFirstFrame) {
            this.onFirstFrameRenderedListener.onFirstFrameRendered();
        }
    }

    public boolean checkInputConnectionProxy(View view) {
        FlutterEngine flutterEngine2 = this.flutterEngine;
        return flutterEngine2 != null ? flutterEngine2.getPlatformViewsController().checkInputConnectionProxy(view) : super.checkInputConnectionProxy(view);
    }

    public void detachFromFlutterEngine() {
        Log.d(TAG, "Detaching from a FlutterEngine: " + this.flutterEngine);
        if (!isAttachedToFlutterEngine()) {
            Log.d(TAG, "Not attached to an engine. Doing nothing.");
            return;
        }
        for (FlutterEngineAttachmentListener onFlutterEngineDetachedFromFlutterView : this.flutterEngineAttachmentListeners) {
            onFlutterEngineDetachedFromFlutterView.onFlutterEngineDetachedFromFlutterView();
        }
        this.flutterEngine.getPlatformViewsController().detachAccessibiltyBridge();
        this.accessibilityBridge.release();
        this.accessibilityBridge = null;
        this.textInputPlugin.getInputMethodManager().restartInput(this);
        this.textInputPlugin.destroy();
        FlutterRenderer renderer = this.flutterEngine.getRenderer();
        this.didRenderFirstFrame = false;
        renderer.removeOnFirstFrameRenderedListener(this.onFirstFrameRenderedListener);
        renderer.detachFromRenderSurface();
        this.flutterEngine = null;
    }

    /* access modifiers changed from: protected */
    public boolean fitSystemWindows(@NonNull Rect rect) {
        if (Build.VERSION.SDK_INT > 19) {
            return super.fitSystemWindows(rect);
        }
        this.viewportMetrics.paddingTop = rect.top;
        this.viewportMetrics.paddingRight = rect.right;
        FlutterRenderer.ViewportMetrics viewportMetrics2 = this.viewportMetrics;
        viewportMetrics2.paddingBottom = 0;
        viewportMetrics2.paddingLeft = rect.left;
        FlutterRenderer.ViewportMetrics viewportMetrics3 = this.viewportMetrics;
        viewportMetrics3.viewInsetTop = 0;
        viewportMetrics3.viewInsetRight = 0;
        viewportMetrics3.viewInsetBottom = rect.bottom;
        this.viewportMetrics.viewInsetLeft = 0;
        Log.v(TAG, "Updating window insets (fitSystemWindows()):\nStatus bar insets: Top: " + this.viewportMetrics.paddingTop + ", Left: " + this.viewportMetrics.paddingLeft + ", Right: " + this.viewportMetrics.paddingRight + "\nKeyboard insets: Bottom: " + this.viewportMetrics.viewInsetBottom + ", Left: " + this.viewportMetrics.viewInsetLeft + ", Right: " + this.viewportMetrics.viewInsetRight);
        sendViewportMetricsToFlutter();
        return true;
    }

    @Nullable
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        AccessibilityBridge accessibilityBridge2 = this.accessibilityBridge;
        if (accessibilityBridge2 == null || !accessibilityBridge2.isAccessibilityEnabled()) {
            return null;
        }
        return this.accessibilityBridge;
    }

    @VisibleForTesting
    @Nullable
    public FlutterEngine getAttachedFlutterEngine() {
        return this.flutterEngine;
    }

    public boolean hasRenderedFirstFrame() {
        return this.didRenderFirstFrame;
    }

    @VisibleForTesting
    public boolean isAttachedToFlutterEngine() {
        FlutterEngine flutterEngine2 = this.flutterEngine;
        return flutterEngine2 != null && flutterEngine2.getRenderer().isAttachedTo(this.renderSurface);
    }

    @RequiresApi(20)
    @SuppressLint({"InlinedApi", "NewApi"})
    @TargetApi(20)
    @NonNull
    public final WindowInsets onApplyWindowInsets(@NonNull WindowInsets windowInsets) {
        WindowInsets onApplyWindowInsets = super.onApplyWindowInsets(windowInsets);
        this.viewportMetrics.paddingTop = windowInsets.getSystemWindowInsetTop();
        this.viewportMetrics.paddingRight = windowInsets.getSystemWindowInsetRight();
        FlutterRenderer.ViewportMetrics viewportMetrics2 = this.viewportMetrics;
        viewportMetrics2.paddingBottom = 0;
        viewportMetrics2.paddingLeft = windowInsets.getSystemWindowInsetLeft();
        FlutterRenderer.ViewportMetrics viewportMetrics3 = this.viewportMetrics;
        viewportMetrics3.viewInsetTop = 0;
        viewportMetrics3.viewInsetRight = 0;
        viewportMetrics3.viewInsetBottom = windowInsets.getSystemWindowInsetBottom();
        this.viewportMetrics.viewInsetLeft = 0;
        if (Build.VERSION.SDK_INT >= 29) {
            Insets systemGestureInsets = windowInsets.getSystemGestureInsets();
            this.viewportMetrics.systemGestureInsetTop = systemGestureInsets.top;
            this.viewportMetrics.systemGestureInsetRight = systemGestureInsets.right;
            this.viewportMetrics.systemGestureInsetBottom = systemGestureInsets.bottom;
            this.viewportMetrics.systemGestureInsetLeft = systemGestureInsets.left;
        }
        Log.v(TAG, "Updating window insets (onApplyWindowInsets()):\nStatus bar insets: Top: " + this.viewportMetrics.paddingTop + ", Left: " + this.viewportMetrics.paddingLeft + ", Right: " + this.viewportMetrics.paddingRight + "\nKeyboard insets: Bottom: " + this.viewportMetrics.viewInsetBottom + ", Left: " + this.viewportMetrics.viewInsetLeft + ", Right: " + this.viewportMetrics.viewInsetRight + "System Gesture Insets - Left: " + this.viewportMetrics.systemGestureInsetLeft + ", Top: " + this.viewportMetrics.systemGestureInsetTop + ", Right: " + this.viewportMetrics.systemGestureInsetRight + ", Bottom: " + this.viewportMetrics.viewInsetBottom);
        sendViewportMetricsToFlutter();
        return onApplyWindowInsets;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.v(TAG, "Configuration changed. Sending locales and user settings to Flutter.");
        sendLocalesToFlutter(configuration);
        sendUserSettingsToFlutter();
    }

    @Nullable
    public InputConnection onCreateInputConnection(@NonNull EditorInfo editorInfo) {
        return !isAttachedToFlutterEngine() ? super.onCreateInputConnection(editorInfo) : this.textInputPlugin.createInputConnection(this, editorInfo);
    }

    public boolean onGenericMotionEvent(@NonNull MotionEvent motionEvent) {
        if (isAttachedToFlutterEngine() && this.androidTouchProcessor.onGenericMotionEvent(motionEvent)) {
            return true;
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public boolean onHoverEvent(@NonNull MotionEvent motionEvent) {
        return !isAttachedToFlutterEngine() ? super.onHoverEvent(motionEvent) : this.accessibilityBridge.onAccessibilityHoverEvent(motionEvent);
    }

    public boolean onKeyDown(int i, @NonNull KeyEvent keyEvent) {
        if (!isAttachedToFlutterEngine()) {
            return super.onKeyDown(i, keyEvent);
        }
        this.androidKeyProcessor.onKeyDown(keyEvent);
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, @NonNull KeyEvent keyEvent) {
        if (!isAttachedToFlutterEngine()) {
            return super.onKeyUp(i, keyEvent);
        }
        this.androidKeyProcessor.onKeyUp(keyEvent);
        return super.onKeyUp(i, keyEvent);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Log.v(TAG, "Size changed. Sending Flutter new viewport metrics. FlutterView was " + i3 + " x " + i4 + ", it is now " + i + " x " + i2);
        FlutterRenderer.ViewportMetrics viewportMetrics2 = this.viewportMetrics;
        viewportMetrics2.width = i;
        viewportMetrics2.height = i2;
        sendViewportMetricsToFlutter();
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        if (!isAttachedToFlutterEngine()) {
            return super.onTouchEvent(motionEvent);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            requestUnbufferedDispatch(motionEvent);
        }
        return this.androidTouchProcessor.onTouchEvent(motionEvent);
    }

    @VisibleForTesting
    public void removeFlutterEngineAttachmentListener(@NonNull FlutterEngineAttachmentListener flutterEngineAttachmentListener) {
        this.flutterEngineAttachmentListeners.remove(flutterEngineAttachmentListener);
    }

    public void removeOnFirstFrameRenderedListener(@NonNull OnFirstFrameRenderedListener onFirstFrameRenderedListener2) {
        this.onFirstFrameRenderedListeners.remove(onFirstFrameRenderedListener2);
    }
}

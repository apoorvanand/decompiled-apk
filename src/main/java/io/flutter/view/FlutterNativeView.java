package io.flutter.view;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import io.flutter.app.FlutterPluginRegistry;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
import io.flutter.embedding.engine.renderer.OnFirstFrameRenderedListener;
import io.flutter.plugin.common.BinaryMessenger;
import java.nio.ByteBuffer;

public class FlutterNativeView implements BinaryMessenger {
    private static final String TAG = "FlutterNativeView";
    private boolean applicationIsRunning;
    private final DartExecutor dartExecutor;
    private final Context mContext;
    private final FlutterJNI mFlutterJNI;
    /* access modifiers changed from: private */
    public FlutterView mFlutterView;
    /* access modifiers changed from: private */
    public final FlutterPluginRegistry mPluginRegistry;

    private final class EngineLifecycleListenerImpl implements FlutterEngine.EngineLifecycleListener {
        private EngineLifecycleListenerImpl() {
        }

        public void onPreEngineRestart() {
            if (FlutterNativeView.this.mFlutterView != null) {
                FlutterNativeView.this.mFlutterView.resetAccessibilityTree();
            }
            if (FlutterNativeView.this.mPluginRegistry != null) {
                FlutterNativeView.this.mPluginRegistry.onPreEngineRestart();
            }
        }
    }

    private final class RenderSurfaceImpl implements FlutterRenderer.RenderSurface {
        private RenderSurfaceImpl() {
        }

        public void addOnFirstFrameRenderedListener(@NonNull OnFirstFrameRenderedListener onFirstFrameRenderedListener) {
        }

        public void attachToRenderer(@NonNull FlutterRenderer flutterRenderer) {
        }

        public void detachFromRenderer() {
        }

        public void onFirstFrameRendered() {
            if (FlutterNativeView.this.mFlutterView != null) {
                FlutterNativeView.this.mFlutterView.onFirstFrame();
            }
        }

        public void removeOnFirstFrameRenderedListener(@NonNull OnFirstFrameRenderedListener onFirstFrameRenderedListener) {
        }

        public void updateCustomAccessibilityActions(ByteBuffer byteBuffer, String[] strArr) {
            if (FlutterNativeView.this.mFlutterView != null) {
                FlutterNativeView.this.mFlutterView.updateCustomAccessibilityActions(byteBuffer, strArr);
            }
        }

        public void updateSemantics(ByteBuffer byteBuffer, String[] strArr) {
            if (FlutterNativeView.this.mFlutterView != null) {
                FlutterNativeView.this.mFlutterView.updateSemantics(byteBuffer, strArr);
            }
        }
    }

    public FlutterNativeView(@NonNull Context context) {
        this(context, false);
    }

    public FlutterNativeView(@NonNull Context context, boolean z) {
        this.mContext = context;
        this.mPluginRegistry = new FlutterPluginRegistry(this, context);
        this.mFlutterJNI = new FlutterJNI();
        this.mFlutterJNI.setRenderSurface(new RenderSurfaceImpl());
        this.dartExecutor = new DartExecutor(this.mFlutterJNI, context.getAssets());
        this.mFlutterJNI.addEngineLifecycleListener(new EngineLifecycleListenerImpl());
        attach(this, z);
        assertAttached();
    }

    private void attach(FlutterNativeView flutterNativeView, boolean z) {
        this.mFlutterJNI.attachToNative(z);
        this.dartExecutor.onAttachedToJNI();
    }

    public static String getObservatoryUri() {
        return FlutterJNI.getObservatoryUri();
    }

    public void assertAttached() {
        if (!isAttached()) {
            throw new AssertionError("Platform view is not attached");
        }
    }

    public void attachViewAndActivity(FlutterView flutterView, Activity activity) {
        this.mFlutterView = flutterView;
        this.mPluginRegistry.attach(flutterView, activity);
    }

    public void destroy() {
        this.mPluginRegistry.destroy();
        this.dartExecutor.onDetachedFromJNI();
        this.mFlutterView = null;
        this.mFlutterJNI.detachFromNativeAndReleaseResources();
        this.applicationIsRunning = false;
    }

    public void detachFromFlutterView() {
        this.mPluginRegistry.detach();
        this.mFlutterView = null;
    }

    @NonNull
    public DartExecutor getDartExecutor() {
        return this.dartExecutor;
    }

    /* access modifiers changed from: package-private */
    public FlutterJNI getFlutterJNI() {
        return this.mFlutterJNI;
    }

    @NonNull
    public FlutterPluginRegistry getPluginRegistry() {
        return this.mPluginRegistry;
    }

    public boolean isApplicationRunning() {
        return this.applicationIsRunning;
    }

    public boolean isAttached() {
        return this.mFlutterJNI.isAttached();
    }

    public void runFromBundle(FlutterRunArguments flutterRunArguments) {
        if (flutterRunArguments.entrypoint != null) {
            assertAttached();
            if (!this.applicationIsRunning) {
                this.mFlutterJNI.runBundleAndSnapshotFromLibrary(flutterRunArguments.bundlePath, flutterRunArguments.entrypoint, flutterRunArguments.libraryPath, this.mContext.getResources().getAssets());
                this.applicationIsRunning = true;
                return;
            }
            throw new AssertionError("This Flutter engine instance is already running an application");
        }
        throw new AssertionError("An entrypoint must be specified");
    }

    @UiThread
    public void send(String str, ByteBuffer byteBuffer) {
        this.dartExecutor.send(str, byteBuffer);
    }

    @UiThread
    public void send(String str, ByteBuffer byteBuffer, BinaryMessenger.BinaryReply binaryReply) {
        if (!isAttached()) {
            Log.d(TAG, "FlutterView.send called on a detached view, channel=" + str);
            return;
        }
        this.dartExecutor.send(str, byteBuffer, binaryReply);
    }

    @UiThread
    public void setMessageHandler(String str, BinaryMessenger.BinaryMessageHandler binaryMessageHandler) {
        this.dartExecutor.setMessageHandler(str, binaryMessageHandler);
    }
}

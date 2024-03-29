package io.flutter.embedding.engine;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import io.flutter.Log;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.embedding.engine.plugins.PluginRegistry;
import io.flutter.embedding.engine.plugins.activity.ActivityControlSurface;
import io.flutter.embedding.engine.plugins.broadcastreceiver.BroadcastReceiverControlSurface;
import io.flutter.embedding.engine.plugins.contentprovider.ContentProviderControlSurface;
import io.flutter.embedding.engine.plugins.service.ServiceControlSurface;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
import io.flutter.embedding.engine.systemchannels.AccessibilityChannel;
import io.flutter.embedding.engine.systemchannels.KeyEventChannel;
import io.flutter.embedding.engine.systemchannels.LifecycleChannel;
import io.flutter.embedding.engine.systemchannels.LocalizationChannel;
import io.flutter.embedding.engine.systemchannels.NavigationChannel;
import io.flutter.embedding.engine.systemchannels.PlatformChannel;
import io.flutter.embedding.engine.systemchannels.SettingsChannel;
import io.flutter.embedding.engine.systemchannels.SystemChannel;
import io.flutter.embedding.engine.systemchannels.TextInputChannel;
import io.flutter.plugin.platform.PlatformViewsController;
import java.util.HashSet;
import java.util.Set;

public class FlutterEngine implements LifecycleOwner {
    private static final String TAG = "FlutterEngine";
    @NonNull
    private final AccessibilityChannel accessibilityChannel;
    @NonNull
    private final FlutterEngineAndroidLifecycle androidLifecycle;
    @NonNull
    private final DartExecutor dartExecutor;
    @NonNull
    private final EngineLifecycleListener engineLifecycleListener = new EngineLifecycleListener() {
        public void onPreEngineRestart() {
            Log.v(FlutterEngine.TAG, "onPreEngineRestart()");
            for (EngineLifecycleListener onPreEngineRestart : FlutterEngine.this.engineLifecycleListeners) {
                onPreEngineRestart.onPreEngineRestart();
            }
        }
    };
    /* access modifiers changed from: private */
    @NonNull
    public final Set<EngineLifecycleListener> engineLifecycleListeners = new HashSet();
    @NonNull
    private final FlutterJNI flutterJNI = new FlutterJNI();
    @NonNull
    private final KeyEventChannel keyEventChannel;
    @NonNull
    private final LifecycleChannel lifecycleChannel;
    @NonNull
    private final LocalizationChannel localizationChannel;
    @NonNull
    private final NavigationChannel navigationChannel;
    @NonNull
    private final PlatformChannel platformChannel;
    @NonNull
    private final PlatformViewsController platformViewsController;
    @NonNull
    private final FlutterEnginePluginRegistry pluginRegistry;
    @NonNull
    private final FlutterRenderer renderer;
    @NonNull
    private final SettingsChannel settingsChannel;
    @NonNull
    private final SystemChannel systemChannel;
    @NonNull
    private final TextInputChannel textInputChannel;

    public interface EngineLifecycleListener {
        void onPreEngineRestart();
    }

    public FlutterEngine(@NonNull Context context) {
        this.flutterJNI.addEngineLifecycleListener(this.engineLifecycleListener);
        attachToJni();
        this.dartExecutor = new DartExecutor(this.flutterJNI, context.getAssets());
        this.dartExecutor.onAttachedToJNI();
        this.renderer = new FlutterRenderer(this.flutterJNI);
        this.accessibilityChannel = new AccessibilityChannel(this.dartExecutor, this.flutterJNI);
        this.keyEventChannel = new KeyEventChannel(this.dartExecutor);
        this.lifecycleChannel = new LifecycleChannel(this.dartExecutor);
        this.localizationChannel = new LocalizationChannel(this.dartExecutor);
        this.navigationChannel = new NavigationChannel(this.dartExecutor);
        this.platformChannel = new PlatformChannel(this.dartExecutor);
        this.settingsChannel = new SettingsChannel(this.dartExecutor);
        this.systemChannel = new SystemChannel(this.dartExecutor);
        this.textInputChannel = new TextInputChannel(this.dartExecutor);
        this.platformViewsController = new PlatformViewsController();
        this.androidLifecycle = new FlutterEngineAndroidLifecycle(this);
        this.pluginRegistry = new FlutterEnginePluginRegistry(context.getApplicationContext(), this, this.androidLifecycle);
    }

    private void attachToJni() {
        Log.v(TAG, "Attaching to JNI.");
        this.flutterJNI.attachToNative(false);
        if (!isAttachedToJni()) {
            throw new RuntimeException("FlutterEngine failed to attach to its native Object reference.");
        }
    }

    private boolean isAttachedToJni() {
        return this.flutterJNI.isAttached();
    }

    public void addEngineLifecycleListener(@NonNull EngineLifecycleListener engineLifecycleListener2) {
        this.engineLifecycleListeners.add(engineLifecycleListener2);
    }

    public void destroy() {
        Log.d(TAG, "Destroying.");
        this.pluginRegistry.destroy();
        this.dartExecutor.onDetachedFromJNI();
        this.flutterJNI.removeEngineLifecycleListener(this.engineLifecycleListener);
        this.flutterJNI.detachFromNativeAndReleaseResources();
    }

    @NonNull
    public AccessibilityChannel getAccessibilityChannel() {
        return this.accessibilityChannel;
    }

    @NonNull
    public ActivityControlSurface getActivityControlSurface() {
        return this.pluginRegistry;
    }

    @NonNull
    public BroadcastReceiverControlSurface getBroadcastReceiverControlSurface() {
        return this.pluginRegistry;
    }

    @NonNull
    public ContentProviderControlSurface getContentProviderControlSurface() {
        return this.pluginRegistry;
    }

    @NonNull
    public DartExecutor getDartExecutor() {
        return this.dartExecutor;
    }

    @NonNull
    public KeyEventChannel getKeyEventChannel() {
        return this.keyEventChannel;
    }

    @NonNull
    public Lifecycle getLifecycle() {
        return this.androidLifecycle;
    }

    @NonNull
    public LifecycleChannel getLifecycleChannel() {
        return this.lifecycleChannel;
    }

    @NonNull
    public LocalizationChannel getLocalizationChannel() {
        return this.localizationChannel;
    }

    @NonNull
    public NavigationChannel getNavigationChannel() {
        return this.navigationChannel;
    }

    @NonNull
    public PlatformChannel getPlatformChannel() {
        return this.platformChannel;
    }

    @NonNull
    public PlatformViewsController getPlatformViewsController() {
        return this.platformViewsController;
    }

    @NonNull
    public PluginRegistry getPlugins() {
        return this.pluginRegistry;
    }

    @NonNull
    public FlutterRenderer getRenderer() {
        return this.renderer;
    }

    @NonNull
    public ServiceControlSurface getServiceControlSurface() {
        return this.pluginRegistry;
    }

    @NonNull
    public SettingsChannel getSettingsChannel() {
        return this.settingsChannel;
    }

    @NonNull
    public SystemChannel getSystemChannel() {
        return this.systemChannel;
    }

    @NonNull
    public TextInputChannel getTextInputChannel() {
        return this.textInputChannel;
    }

    public void removeEngineLifecycleListener(@NonNull EngineLifecycleListener engineLifecycleListener2) {
        this.engineLifecycleListeners.remove(engineLifecycleListener2);
    }
}

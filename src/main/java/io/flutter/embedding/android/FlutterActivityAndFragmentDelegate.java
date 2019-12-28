package io.flutter.embedding.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import io.flutter.Log;
import io.flutter.embedding.android.FlutterView;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.FlutterShellArgs;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.embedding.engine.renderer.OnFirstFrameRenderedListener;
import io.flutter.plugin.platform.PlatformPlugin;
import io.flutter.view.FlutterMain;
import java.util.Arrays;

final class FlutterActivityAndFragmentDelegate {
    private static final String TAG = "FlutterActivityAndFragmentDelegate";
    /* access modifiers changed from: private */
    @Nullable
    public FlutterEngine flutterEngine;
    @Nullable
    private FlutterSplashView flutterSplashView;
    /* access modifiers changed from: private */
    @Nullable
    public FlutterView flutterView;
    /* access modifiers changed from: private */
    @NonNull
    public Host host;
    private boolean isFlutterEngineFromHost;
    @NonNull
    private final OnFirstFrameRenderedListener onFirstFrameRenderedListener = new OnFirstFrameRenderedListener() {
        public void onFirstFrameRendered() {
            FlutterActivityAndFragmentDelegate.this.host.onFirstFrameRendered();
        }
    };
    @Nullable
    private PlatformPlugin platformPlugin;

    interface Host extends FlutterEngineConfigurator, FlutterEngineProvider, SplashScreenProvider {
        void configureFlutterEngine(@NonNull FlutterEngine flutterEngine);

        @Nullable
        Activity getActivity();

        @NonNull
        String getAppBundlePath();

        @Nullable
        String getCachedEngineId();

        @NonNull
        Context getContext();

        @NonNull
        String getDartEntrypointFunctionName();

        @NonNull
        FlutterShellArgs getFlutterShellArgs();

        @Nullable
        String getInitialRoute();

        @NonNull
        Lifecycle getLifecycle();

        @NonNull
        FlutterView.RenderMode getRenderMode();

        @NonNull
        FlutterView.TransparencyMode getTransparencyMode();

        void onFirstFrameRendered();

        @Nullable
        FlutterEngine provideFlutterEngine(@NonNull Context context);

        @Nullable
        PlatformPlugin providePlatformPlugin(@Nullable Activity activity, @NonNull FlutterEngine flutterEngine);

        @Nullable
        SplashScreen provideSplashScreen();

        boolean shouldAttachEngineToActivity();

        boolean shouldDestroyEngineWithHost();
    }

    FlutterActivityAndFragmentDelegate(@NonNull Host host2) {
        this.host = host2;
    }

    /* access modifiers changed from: private */
    public void doInitialFlutterViewRun() {
        if (this.host.getCachedEngineId() == null && !this.flutterEngine.getDartExecutor().isExecutingDart()) {
            Log.d(TAG, "Executing Dart entrypoint: " + this.host.getDartEntrypointFunctionName() + ", and sending initial route: " + this.host.getInitialRoute());
            if (this.host.getInitialRoute() != null) {
                this.flutterEngine.getNavigationChannel().setInitialRoute(this.host.getInitialRoute());
            }
            this.flutterEngine.getDartExecutor().executeDartEntrypoint(new DartExecutor.DartEntrypoint(this.host.getAppBundlePath(), this.host.getDartEntrypointFunctionName()));
        }
    }

    private void ensureAlive() {
        if (this.host == null) {
            throw new IllegalStateException("Cannot execute method on a destroyed FlutterActivityAndFragmentDelegate.");
        }
    }

    private void initializeFlutter(@NonNull Context context) {
        FlutterMain.ensureInitializationComplete(context.getApplicationContext(), this.host.getFlutterShellArgs().toArray());
    }

    private void setupFlutterEngine() {
        Log.d(TAG, "Setting up FlutterEngine.");
        String cachedEngineId = this.host.getCachedEngineId();
        if (cachedEngineId != null) {
            this.flutterEngine = FlutterEngineCache.getInstance().get(cachedEngineId);
            this.isFlutterEngineFromHost = true;
            if (this.flutterEngine == null) {
                throw new IllegalStateException("The requested cached FlutterEngine did not exist in the FlutterEngineCache: '" + cachedEngineId + "'");
            }
            return;
        }
        Host host2 = this.host;
        this.flutterEngine = host2.provideFlutterEngine(host2.getContext());
        if (this.flutterEngine != null) {
            this.isFlutterEngineFromHost = true;
            return;
        }
        Log.d(TAG, "No preferred FlutterEngine was provided. Creating a new FlutterEngine for this FlutterFragment.");
        this.flutterEngine = new FlutterEngine(this.host.getContext());
        this.isFlutterEngineFromHost = false;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public FlutterEngine getFlutterEngine() {
        return this.flutterEngine;
    }

    /* access modifiers changed from: package-private */
    public void onActivityResult(int i, int i2, Intent intent) {
        ensureAlive();
        if (this.flutterEngine != null) {
            Log.v(TAG, "Forwarding onActivityResult() to FlutterEngine:\nrequestCode: " + i + "\nresultCode: " + i2 + "\ndata: " + intent);
            this.flutterEngine.getActivityControlSurface().onActivityResult(i, i2, intent);
            return;
        }
        Log.w(TAG, "onActivityResult() invoked before FlutterFragment was attached to an Activity.");
    }

    /* access modifiers changed from: package-private */
    public void onAttach(@NonNull Context context) {
        ensureAlive();
        initializeFlutter(context);
        if (this.flutterEngine == null) {
            setupFlutterEngine();
        }
        Host host2 = this.host;
        this.platformPlugin = host2.providePlatformPlugin(host2.getActivity(), this.flutterEngine);
        if (this.host.shouldAttachEngineToActivity()) {
            Log.d(TAG, "Attaching FlutterEngine to the Activity that owns this Fragment.");
            this.flutterEngine.getActivityControlSurface().attachToActivity(this.host.getActivity(), this.host.getLifecycle());
        }
        this.host.configureFlutterEngine(this.flutterEngine);
    }

    /* access modifiers changed from: package-private */
    public void onBackPressed() {
        ensureAlive();
        if (this.flutterEngine != null) {
            Log.v(TAG, "Forwarding onBackPressed() to FlutterEngine.");
            this.flutterEngine.getNavigationChannel().popRoute();
            return;
        }
        Log.w(TAG, "Invoked onBackPressed() before FlutterFragment was attached to an Activity.");
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        int i;
        FlutterSplashView flutterSplashView2;
        Log.v(TAG, "Creating FlutterView.");
        ensureAlive();
        this.flutterView = new FlutterView(this.host.getActivity(), this.host.getRenderMode(), this.host.getTransparencyMode());
        this.flutterView.addOnFirstFrameRenderedListener(this.onFirstFrameRenderedListener);
        this.flutterSplashView = new FlutterSplashView(this.host.getContext());
        if (Build.VERSION.SDK_INT >= 17) {
            flutterSplashView2 = this.flutterSplashView;
            i = View.generateViewId();
        } else {
            flutterSplashView2 = this.flutterSplashView;
            i = 486947586;
        }
        flutterSplashView2.setId(i);
        this.flutterSplashView.displayFlutterViewWithSplash(this.flutterView, this.host.provideSplashScreen());
        return this.flutterSplashView;
    }

    /* access modifiers changed from: package-private */
    public void onDestroyView() {
        Log.v(TAG, "onDestroyView()");
        ensureAlive();
        this.flutterView.removeOnFirstFrameRenderedListener(this.onFirstFrameRenderedListener);
    }

    /* access modifiers changed from: package-private */
    public void onDetach() {
        Log.v(TAG, "onDetach()");
        ensureAlive();
        if (this.host.shouldAttachEngineToActivity()) {
            Log.d(TAG, "Detaching FlutterEngine from the Activity that owns this Fragment.");
            if (this.host.getActivity().isChangingConfigurations()) {
                this.flutterEngine.getActivityControlSurface().detachFromActivityForConfigChanges();
            } else {
                this.flutterEngine.getActivityControlSurface().detachFromActivity();
            }
        }
        PlatformPlugin platformPlugin2 = this.platformPlugin;
        if (platformPlugin2 != null) {
            platformPlugin2.destroy();
            this.platformPlugin = null;
        }
        if (this.host.shouldDestroyEngineWithHost()) {
            this.flutterEngine.destroy();
            if (this.host.getCachedEngineId() != null) {
                FlutterEngineCache.getInstance().remove(this.host.getCachedEngineId());
            }
            this.flutterEngine = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void onLowMemory() {
        Log.v(TAG, "Forwarding onLowMemory() to FlutterEngine.");
        ensureAlive();
        this.flutterEngine.getSystemChannel().sendMemoryPressureWarning();
    }

    /* access modifiers changed from: package-private */
    public void onNewIntent(@NonNull Intent intent) {
        ensureAlive();
        if (this.flutterEngine != null) {
            Log.v(TAG, "Forwarding onNewIntent() to FlutterEngine.");
            this.flutterEngine.getActivityControlSurface().onNewIntent(intent);
            return;
        }
        Log.w(TAG, "onNewIntent() invoked before FlutterFragment was attached to an Activity.");
    }

    /* access modifiers changed from: package-private */
    public void onPause() {
        Log.v(TAG, "onPause()");
        ensureAlive();
        this.flutterEngine.getLifecycleChannel().appIsInactive();
    }

    /* access modifiers changed from: package-private */
    public void onPostResume() {
        Log.v(TAG, "onPostResume()");
        ensureAlive();
        if (this.flutterEngine != null) {
            PlatformPlugin platformPlugin2 = this.platformPlugin;
            if (platformPlugin2 != null) {
                platformPlugin2.updateSystemUiOverlays();
                return;
            }
            return;
        }
        Log.w(TAG, "onPostResume() invoked before FlutterFragment was attached to an Activity.");
    }

    /* access modifiers changed from: package-private */
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        ensureAlive();
        if (this.flutterEngine != null) {
            Log.v(TAG, "Forwarding onRequestPermissionsResult() to FlutterEngine:\nrequestCode: " + i + "\npermissions: " + Arrays.toString(strArr) + "\ngrantResults: " + Arrays.toString(iArr));
            this.flutterEngine.getActivityControlSurface().onRequestPermissionsResult(i, strArr, iArr);
            return;
        }
        Log.w(TAG, "onRequestPermissionResult() invoked before FlutterFragment was attached to an Activity.");
    }

    /* access modifiers changed from: package-private */
    public void onResume() {
        Log.v(TAG, "onResume()");
        ensureAlive();
        this.flutterEngine.getLifecycleChannel().appIsResumed();
    }

    /* access modifiers changed from: package-private */
    public void onStart() {
        Log.v(TAG, "onStart()");
        ensureAlive();
        new Handler().post(new Runnable() {
            public void run() {
                Log.v(FlutterActivityAndFragmentDelegate.TAG, "Attaching FlutterEngine to FlutterView.");
                FlutterActivityAndFragmentDelegate.this.flutterView.attachToFlutterEngine(FlutterActivityAndFragmentDelegate.this.flutterEngine);
                FlutterActivityAndFragmentDelegate.this.doInitialFlutterViewRun();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void onStop() {
        Log.v(TAG, "onStop()");
        ensureAlive();
        this.flutterEngine.getLifecycleChannel().appIsPaused();
        this.flutterView.detachFromFlutterEngine();
    }

    /* access modifiers changed from: package-private */
    public void onTrimMemory(int i) {
        ensureAlive();
        if (this.flutterEngine == null) {
            Log.w(TAG, "onTrimMemory() invoked before FlutterFragment was attached to an Activity.");
        } else if (i == 10) {
            Log.v(TAG, "Forwarding onTrimMemory() to FlutterEngine. Level: " + i);
            this.flutterEngine.getSystemChannel().sendMemoryPressureWarning();
        }
    }

    /* access modifiers changed from: package-private */
    public void onUserLeaveHint() {
        ensureAlive();
        if (this.flutterEngine != null) {
            Log.v(TAG, "Forwarding onUserLeaveHint() to FlutterEngine.");
            this.flutterEngine.getActivityControlSurface().onUserLeaveHint();
            return;
        }
        Log.w(TAG, "onUserLeaveHint() invoked before FlutterFragment was attached to an Activity.");
    }

    /* access modifiers changed from: package-private */
    public void release() {
        this.host = null;
        this.flutterEngine = null;
        this.flutterView = null;
        this.platformPlugin = null;
    }
}

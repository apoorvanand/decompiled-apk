package io.flutter.embedding.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.google.common.primitives.Ints;
import io.flutter.Log;
import io.flutter.embedding.android.FlutterActivityAndFragmentDelegate;
import io.flutter.embedding.android.FlutterView;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterShellArgs;
import io.flutter.plugin.platform.PlatformPlugin;

public class FlutterActivity extends Activity implements LifecycleOwner, FlutterActivityAndFragmentDelegate.Host {
    protected static final String DART_ENTRYPOINT_META_DATA_KEY = "io.flutter.Entrypoint";
    protected static final String DEFAULT_BACKGROUND_MODE = BackgroundMode.opaque.name();
    protected static final String DEFAULT_DART_ENTRYPOINT = "main";
    protected static final String DEFAULT_INITIAL_ROUTE = "/";
    protected static final String EXTRA_BACKGROUND_MODE = "background_mode";
    protected static final String EXTRA_CACHED_ENGINE_ID = "cached_engine_id";
    protected static final String EXTRA_DART_ENTRYPOINT = "dart_entrypoint";
    protected static final String EXTRA_DESTROY_ENGINE_WITH_ACTIVITY = "destroy_engine_with_activity";
    protected static final String EXTRA_INITIAL_ROUTE = "initial_route";
    protected static final String INITIAL_ROUTE_META_DATA_KEY = "io.flutter.InitialRoute";
    protected static final String NORMAL_THEME_META_DATA_KEY = "io.flutter.embedding.android.NormalTheme";
    protected static final String SPLASH_SCREEN_META_DATA_KEY = "io.flutter.embedding.android.SplashScreenDrawable";
    private static final String TAG = "FlutterActivity";
    private FlutterActivityAndFragmentDelegate delegate;
    @NonNull
    private LifecycleRegistry lifecycle = new LifecycleRegistry(this);

    public enum BackgroundMode {
        opaque,
        transparent
    }

    public static class CachedEngineIntentBuilder {
        private final Class<? extends FlutterActivity> activityClass;
        private String backgroundMode = FlutterActivity.DEFAULT_BACKGROUND_MODE;
        private final String cachedEngineId;
        private boolean destroyEngineWithActivity = false;

        protected CachedEngineIntentBuilder(@NonNull Class<? extends FlutterActivity> cls, @NonNull String str) {
            this.activityClass = cls;
            this.cachedEngineId = str;
        }

        @NonNull
        public CachedEngineIntentBuilder backgroundMode(@NonNull BackgroundMode backgroundMode2) {
            this.backgroundMode = backgroundMode2.name();
            return this;
        }

        @NonNull
        public Intent build(@NonNull Context context) {
            return new Intent(context, this.activityClass).putExtra(FlutterActivity.EXTRA_CACHED_ENGINE_ID, this.cachedEngineId).putExtra(FlutterActivity.EXTRA_DESTROY_ENGINE_WITH_ACTIVITY, this.destroyEngineWithActivity).putExtra(FlutterActivity.EXTRA_BACKGROUND_MODE, this.backgroundMode);
        }

        public CachedEngineIntentBuilder destroyEngineWithActivity(boolean z) {
            this.destroyEngineWithActivity = z;
            return this;
        }
    }

    public static class NewEngineIntentBuilder {
        private final Class<? extends FlutterActivity> activityClass;
        private String backgroundMode = FlutterActivity.DEFAULT_BACKGROUND_MODE;
        private String dartEntrypoint = FlutterActivity.DEFAULT_DART_ENTRYPOINT;
        private String initialRoute = FlutterActivity.DEFAULT_INITIAL_ROUTE;

        protected NewEngineIntentBuilder(@NonNull Class<? extends FlutterActivity> cls) {
            this.activityClass = cls;
        }

        @NonNull
        public NewEngineIntentBuilder backgroundMode(@NonNull BackgroundMode backgroundMode2) {
            this.backgroundMode = backgroundMode2.name();
            return this;
        }

        @NonNull
        public Intent build(@NonNull Context context) {
            return new Intent(context, this.activityClass).putExtra(FlutterActivity.EXTRA_DART_ENTRYPOINT, this.dartEntrypoint).putExtra(FlutterActivity.EXTRA_INITIAL_ROUTE, this.initialRoute).putExtra(FlutterActivity.EXTRA_BACKGROUND_MODE, this.backgroundMode).putExtra(FlutterActivity.EXTRA_DESTROY_ENGINE_WITH_ACTIVITY, true);
        }

        @NonNull
        public NewEngineIntentBuilder dartEntrypoint(@NonNull String str) {
            this.dartEntrypoint = str;
            return this;
        }

        @NonNull
        public NewEngineIntentBuilder initialRoute(@NonNull String str) {
            this.initialRoute = str;
            return this;
        }
    }

    private void configureStatusBarForFullscreenFlutterExperience() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(Ints.MAX_POWER_OF_TWO);
            window.getDecorView().setSystemUiVisibility(PlatformPlugin.DEFAULT_SYSTEM_UI);
        }
    }

    private void configureWindowForTransparency() {
        if (getBackgroundMode() == BackgroundMode.transparent) {
            getWindow().setBackgroundDrawable(new ColorDrawable(0));
            getWindow().setFlags(512, 512);
        }
    }

    @NonNull
    public static Intent createDefaultIntent(@NonNull Context context) {
        return withNewEngine().build(context);
    }

    @NonNull
    private View createFlutterView() {
        return this.delegate.onCreateView((LayoutInflater) null, (ViewGroup) null, (Bundle) null);
    }

    @Nullable
    private Drawable getSplashScreenFromManifest() {
        try {
            Bundle bundle = getPackageManager().getActivityInfo(getComponentName(), 129).metaData;
            Integer valueOf = bundle != null ? Integer.valueOf(bundle.getInt(SPLASH_SCREEN_META_DATA_KEY)) : null;
            if (valueOf != null) {
                return Build.VERSION.SDK_INT > 21 ? getResources().getDrawable(valueOf.intValue(), getTheme()) : getResources().getDrawable(valueOf.intValue());
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private boolean isDebuggable() {
        return (getApplicationInfo().flags & 2) != 0;
    }

    private void switchLaunchThemeForNormalTheme() {
        try {
            ActivityInfo activityInfo = getPackageManager().getActivityInfo(getComponentName(), 128);
            if (activityInfo.metaData != null) {
                int i = activityInfo.metaData.getInt(NORMAL_THEME_META_DATA_KEY, -1);
                if (i != -1) {
                    setTheme(i);
                    return;
                }
                return;
            }
            Log.d(TAG, "Using the launch theme as normal theme.");
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e(TAG, "Could not read meta-data for FlutterActivity. Using the launch theme as normal theme.");
        }
    }

    public static CachedEngineIntentBuilder withCachedEngine(@NonNull String str) {
        return new CachedEngineIntentBuilder(FlutterActivity.class, str);
    }

    @NonNull
    public static NewEngineIntentBuilder withNewEngine() {
        return new NewEngineIntentBuilder(FlutterActivity.class);
    }

    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
    }

    @NonNull
    public Activity getActivity() {
        return this;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0016, code lost:
        r0 = getIntent().getDataString();
     */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getAppBundlePath() {
        /*
            r2 = this;
            boolean r0 = r2.isDebuggable()
            if (r0 == 0) goto L_0x0021
            java.lang.String r0 = "android.intent.action.RUN"
            android.content.Intent r1 = r2.getIntent()
            java.lang.String r1 = r1.getAction()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0021
            android.content.Intent r0 = r2.getIntent()
            java.lang.String r0 = r0.getDataString()
            if (r0 == 0) goto L_0x0021
            return r0
        L_0x0021:
            java.lang.String r0 = io.flutter.view.FlutterMain.findAppBundlePath()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.embedding.android.FlutterActivity.getAppBundlePath():java.lang.String");
    }

    /* access modifiers changed from: protected */
    @NonNull
    public BackgroundMode getBackgroundMode() {
        return getIntent().hasExtra(EXTRA_BACKGROUND_MODE) ? BackgroundMode.valueOf(getIntent().getStringExtra(EXTRA_BACKGROUND_MODE)) : BackgroundMode.opaque;
    }

    @Nullable
    public String getCachedEngineId() {
        return getIntent().getStringExtra(EXTRA_CACHED_ENGINE_ID);
    }

    @NonNull
    public Context getContext() {
        return this;
    }

    @NonNull
    public String getDartEntrypointFunctionName() {
        if (getIntent().hasExtra(EXTRA_DART_ENTRYPOINT)) {
            return getIntent().getStringExtra(EXTRA_DART_ENTRYPOINT);
        }
        try {
            Bundle bundle = getPackageManager().getActivityInfo(getComponentName(), 129).metaData;
            String string = bundle != null ? bundle.getString(DART_ENTRYPOINT_META_DATA_KEY) : null;
            return string != null ? string : DEFAULT_DART_ENTRYPOINT;
        } catch (PackageManager.NameNotFoundException unused) {
            return DEFAULT_DART_ENTRYPOINT;
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public FlutterEngine getFlutterEngine() {
        return this.delegate.getFlutterEngine();
    }

    @NonNull
    public FlutterShellArgs getFlutterShellArgs() {
        return FlutterShellArgs.fromIntent(getIntent());
    }

    @NonNull
    public String getInitialRoute() {
        if (getIntent().hasExtra(EXTRA_INITIAL_ROUTE)) {
            return getIntent().getStringExtra(EXTRA_INITIAL_ROUTE);
        }
        try {
            Bundle bundle = getPackageManager().getActivityInfo(getComponentName(), 129).metaData;
            String string = bundle != null ? bundle.getString(INITIAL_ROUTE_META_DATA_KEY) : null;
            return string != null ? string : DEFAULT_INITIAL_ROUTE;
        } catch (PackageManager.NameNotFoundException unused) {
            return DEFAULT_INITIAL_ROUTE;
        }
    }

    @NonNull
    public Lifecycle getLifecycle() {
        return this.lifecycle;
    }

    @NonNull
    public FlutterView.RenderMode getRenderMode() {
        return getBackgroundMode() == BackgroundMode.opaque ? FlutterView.RenderMode.surface : FlutterView.RenderMode.texture;
    }

    @NonNull
    public FlutterView.TransparencyMode getTransparencyMode() {
        return getBackgroundMode() == BackgroundMode.opaque ? FlutterView.TransparencyMode.opaque : FlutterView.TransparencyMode.transparent;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        this.delegate.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        this.delegate.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        switchLaunchThemeForNormalTheme();
        super.onCreate(bundle);
        this.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        this.delegate = new FlutterActivityAndFragmentDelegate(this);
        this.delegate.onAttach(this);
        configureWindowForTransparency();
        setContentView(createFlutterView());
        configureStatusBarForFullscreenFlutterExperience();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.delegate.onDestroyView();
        this.delegate.onDetach();
        this.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
    }

    public void onFirstFrameRendered() {
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(@NonNull Intent intent) {
        super.onNewIntent(intent);
        this.delegate.onNewIntent(intent);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.delegate.onPause();
        this.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
    }

    public void onPostResume() {
        super.onPostResume();
        this.delegate.onPostResume();
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        this.delegate.onRequestPermissionsResult(i, strArr, iArr);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        this.delegate.onResume();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START);
        this.delegate.onStart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.delegate.onStop();
        this.lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        this.delegate.onTrimMemory(i);
    }

    public void onUserLeaveHint() {
        this.delegate.onUserLeaveHint();
    }

    @Nullable
    public FlutterEngine provideFlutterEngine(@NonNull Context context) {
        return null;
    }

    @Nullable
    public PlatformPlugin providePlatformPlugin(@Nullable Activity activity, @NonNull FlutterEngine flutterEngine) {
        if (activity != null) {
            return new PlatformPlugin(getActivity(), flutterEngine.getPlatformChannel());
        }
        return null;
    }

    @Nullable
    public SplashScreen provideSplashScreen() {
        Drawable splashScreenFromManifest = getSplashScreenFromManifest();
        if (splashScreenFromManifest != null) {
            return new DrawableSplashScreen(splashScreenFromManifest);
        }
        return null;
    }

    public boolean shouldAttachEngineToActivity() {
        return true;
    }

    public boolean shouldDestroyEngineWithHost() {
        return getIntent().getBooleanExtra(EXTRA_DESTROY_ENGINE_WITH_ACTIVITY, false);
    }
}

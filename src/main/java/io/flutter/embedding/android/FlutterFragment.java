package io.flutter.embedding.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import io.flutter.Log;
import io.flutter.embedding.android.FlutterActivityAndFragmentDelegate;
import io.flutter.embedding.android.FlutterView;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterShellArgs;
import io.flutter.embedding.engine.renderer.OnFirstFrameRenderedListener;
import io.flutter.plugin.platform.PlatformPlugin;
import io.flutter.view.FlutterMain;

public class FlutterFragment extends Fragment implements FlutterActivityAndFragmentDelegate.Host {
    protected static final String ARG_APP_BUNDLE_PATH = "app_bundle_path";
    protected static final String ARG_CACHED_ENGINE_ID = "cached_engine_id";
    protected static final String ARG_DART_ENTRYPOINT = "dart_entrypoint";
    protected static final String ARG_DESTROY_ENGINE_WITH_FRAGMENT = "destroy_engine_with_fragment";
    protected static final String ARG_FLUTTERVIEW_RENDER_MODE = "flutterview_render_mode";
    protected static final String ARG_FLUTTERVIEW_TRANSPARENCY_MODE = "flutterview_transparency_mode";
    protected static final String ARG_FLUTTER_INITIALIZATION_ARGS = "initialization_args";
    protected static final String ARG_INITIAL_ROUTE = "initial_route";
    protected static final String ARG_SHOULD_ATTACH_ENGINE_TO_ACTIVITY = "should_attach_engine_to_activity";
    private static final String TAG = "FlutterFragment";
    private FlutterActivityAndFragmentDelegate delegate;
    private final OnFirstFrameRenderedListener onFirstFrameRenderedListener = new OnFirstFrameRenderedListener() {
        public void onFirstFrameRendered() {
            FlutterFragment.this.onFirstFrameRendered();
            FragmentActivity activity = FlutterFragment.this.getActivity();
            if (activity instanceof OnFirstFrameRenderedListener) {
                ((OnFirstFrameRenderedListener) activity).onFirstFrameRendered();
            }
        }
    };

    @interface ActivityCallThrough {
    }

    public static class CachedEngineFragmentBuilder {
        private boolean destroyEngineWithFragment;
        private final String engineId;
        private final Class<? extends FlutterFragment> fragmentClass;
        private FlutterView.RenderMode renderMode;
        private boolean shouldAttachEngineToActivity;
        private FlutterView.TransparencyMode transparencyMode;

        protected CachedEngineFragmentBuilder(@NonNull Class<? extends FlutterFragment> cls, @NonNull String str) {
            this.destroyEngineWithFragment = false;
            this.renderMode = FlutterView.RenderMode.surface;
            this.transparencyMode = FlutterView.TransparencyMode.transparent;
            this.shouldAttachEngineToActivity = true;
            this.fragmentClass = cls;
            this.engineId = str;
        }

        private CachedEngineFragmentBuilder(@NonNull String str) {
            this((Class<? extends FlutterFragment>) FlutterFragment.class, str);
        }

        @NonNull
        public <T extends FlutterFragment> T build() {
            try {
                T t = (FlutterFragment) this.fragmentClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                if (t != null) {
                    t.setArguments(createArgs());
                    return t;
                }
                throw new RuntimeException("The FlutterFragment subclass sent in the constructor (" + this.fragmentClass.getCanonicalName() + ") does not match the expected return type.");
            } catch (Exception e) {
                throw new RuntimeException("Could not instantiate FlutterFragment subclass (" + this.fragmentClass.getName() + ")", e);
            }
        }

        /* access modifiers changed from: protected */
        @NonNull
        public Bundle createArgs() {
            Bundle bundle = new Bundle();
            bundle.putString(FlutterFragment.ARG_CACHED_ENGINE_ID, this.engineId);
            bundle.putBoolean(FlutterFragment.ARG_DESTROY_ENGINE_WITH_FRAGMENT, this.destroyEngineWithFragment);
            FlutterView.RenderMode renderMode2 = this.renderMode;
            if (renderMode2 == null) {
                renderMode2 = FlutterView.RenderMode.surface;
            }
            bundle.putString(FlutterFragment.ARG_FLUTTERVIEW_RENDER_MODE, renderMode2.name());
            FlutterView.TransparencyMode transparencyMode2 = this.transparencyMode;
            if (transparencyMode2 == null) {
                transparencyMode2 = FlutterView.TransparencyMode.transparent;
            }
            bundle.putString(FlutterFragment.ARG_FLUTTERVIEW_TRANSPARENCY_MODE, transparencyMode2.name());
            bundle.putBoolean(FlutterFragment.ARG_SHOULD_ATTACH_ENGINE_TO_ACTIVITY, this.shouldAttachEngineToActivity);
            return bundle;
        }

        @NonNull
        public CachedEngineFragmentBuilder destroyEngineWithFragment(boolean z) {
            this.destroyEngineWithFragment = z;
            return this;
        }

        @NonNull
        public CachedEngineFragmentBuilder renderMode(@NonNull FlutterView.RenderMode renderMode2) {
            this.renderMode = renderMode2;
            return this;
        }

        @NonNull
        public CachedEngineFragmentBuilder shouldAttachEngineToActivity(boolean z) {
            this.shouldAttachEngineToActivity = z;
            return this;
        }

        @NonNull
        public CachedEngineFragmentBuilder transparencyMode(@NonNull FlutterView.TransparencyMode transparencyMode2) {
            this.transparencyMode = transparencyMode2;
            return this;
        }
    }

    public static class NewEngineFragmentBuilder {
        private String appBundlePath;
        private String dartEntrypoint;
        private final Class<? extends FlutterFragment> fragmentClass;
        private String initialRoute;
        private FlutterView.RenderMode renderMode;
        private FlutterShellArgs shellArgs;
        private boolean shouldAttachEngineToActivity;
        private FlutterView.TransparencyMode transparencyMode;

        public NewEngineFragmentBuilder() {
            this.dartEntrypoint = "main";
            this.initialRoute = "/";
            this.appBundlePath = null;
            this.shellArgs = null;
            this.renderMode = FlutterView.RenderMode.surface;
            this.transparencyMode = FlutterView.TransparencyMode.transparent;
            this.shouldAttachEngineToActivity = true;
            this.fragmentClass = FlutterFragment.class;
        }

        public NewEngineFragmentBuilder(@NonNull Class<? extends FlutterFragment> cls) {
            this.dartEntrypoint = "main";
            this.initialRoute = "/";
            this.appBundlePath = null;
            this.shellArgs = null;
            this.renderMode = FlutterView.RenderMode.surface;
            this.transparencyMode = FlutterView.TransparencyMode.transparent;
            this.shouldAttachEngineToActivity = true;
            this.fragmentClass = cls;
        }

        @NonNull
        public NewEngineFragmentBuilder appBundlePath(@NonNull String str) {
            this.appBundlePath = str;
            return this;
        }

        @NonNull
        public <T extends FlutterFragment> T build() {
            try {
                T t = (FlutterFragment) this.fragmentClass.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                if (t != null) {
                    t.setArguments(createArgs());
                    return t;
                }
                throw new RuntimeException("The FlutterFragment subclass sent in the constructor (" + this.fragmentClass.getCanonicalName() + ") does not match the expected return type.");
            } catch (Exception e) {
                throw new RuntimeException("Could not instantiate FlutterFragment subclass (" + this.fragmentClass.getName() + ")", e);
            }
        }

        /* access modifiers changed from: protected */
        @NonNull
        public Bundle createArgs() {
            Bundle bundle = new Bundle();
            bundle.putString(FlutterFragment.ARG_INITIAL_ROUTE, this.initialRoute);
            bundle.putString(FlutterFragment.ARG_APP_BUNDLE_PATH, this.appBundlePath);
            bundle.putString(FlutterFragment.ARG_DART_ENTRYPOINT, this.dartEntrypoint);
            FlutterShellArgs flutterShellArgs = this.shellArgs;
            if (flutterShellArgs != null) {
                bundle.putStringArray(FlutterFragment.ARG_FLUTTER_INITIALIZATION_ARGS, flutterShellArgs.toArray());
            }
            FlutterView.RenderMode renderMode2 = this.renderMode;
            if (renderMode2 == null) {
                renderMode2 = FlutterView.RenderMode.surface;
            }
            bundle.putString(FlutterFragment.ARG_FLUTTERVIEW_RENDER_MODE, renderMode2.name());
            FlutterView.TransparencyMode transparencyMode2 = this.transparencyMode;
            if (transparencyMode2 == null) {
                transparencyMode2 = FlutterView.TransparencyMode.transparent;
            }
            bundle.putString(FlutterFragment.ARG_FLUTTERVIEW_TRANSPARENCY_MODE, transparencyMode2.name());
            bundle.putBoolean(FlutterFragment.ARG_SHOULD_ATTACH_ENGINE_TO_ACTIVITY, this.shouldAttachEngineToActivity);
            bundle.putBoolean(FlutterFragment.ARG_DESTROY_ENGINE_WITH_FRAGMENT, true);
            return bundle;
        }

        @NonNull
        public NewEngineFragmentBuilder dartEntrypoint(@NonNull String str) {
            this.dartEntrypoint = str;
            return this;
        }

        @NonNull
        public NewEngineFragmentBuilder flutterShellArgs(@NonNull FlutterShellArgs flutterShellArgs) {
            this.shellArgs = flutterShellArgs;
            return this;
        }

        @NonNull
        public NewEngineFragmentBuilder initialRoute(@NonNull String str) {
            this.initialRoute = str;
            return this;
        }

        @NonNull
        public NewEngineFragmentBuilder renderMode(@NonNull FlutterView.RenderMode renderMode2) {
            this.renderMode = renderMode2;
            return this;
        }

        @NonNull
        public NewEngineFragmentBuilder shouldAttachEngineToActivity(boolean z) {
            this.shouldAttachEngineToActivity = z;
            return this;
        }

        @NonNull
        public NewEngineFragmentBuilder transparencyMode(@NonNull FlutterView.TransparencyMode transparencyMode2) {
            this.transparencyMode = transparencyMode2;
            return this;
        }
    }

    public FlutterFragment() {
        setArguments(new Bundle());
    }

    @NonNull
    public static FlutterFragment createDefault() {
        return new NewEngineFragmentBuilder().build();
    }

    @NonNull
    private Context getContextCompat() {
        return Build.VERSION.SDK_INT >= 23 ? getContext() : getActivity();
    }

    @NonNull
    public static CachedEngineFragmentBuilder withCachedEngine(@NonNull String str) {
        return new CachedEngineFragmentBuilder(str);
    }

    @NonNull
    public static NewEngineFragmentBuilder withNewEngine() {
        return new NewEngineFragmentBuilder();
    }

    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        FragmentActivity activity = getActivity();
        if (activity instanceof FlutterEngineConfigurator) {
            ((FlutterEngineConfigurator) activity).configureFlutterEngine(flutterEngine);
        }
    }

    @Nullable
    public /* bridge */ /* synthetic */ Activity getActivity() {
        return super.getActivity();
    }

    @NonNull
    public String getAppBundlePath() {
        return getArguments().getString(ARG_APP_BUNDLE_PATH, FlutterMain.findAppBundlePath());
    }

    @Nullable
    public String getCachedEngineId() {
        return getArguments().getString(ARG_CACHED_ENGINE_ID, (String) null);
    }

    @NonNull
    public String getDartEntrypointFunctionName() {
        return getArguments().getString(ARG_DART_ENTRYPOINT, "main");
    }

    @Nullable
    public FlutterEngine getFlutterEngine() {
        return this.delegate.getFlutterEngine();
    }

    @NonNull
    public FlutterShellArgs getFlutterShellArgs() {
        String[] stringArray = getArguments().getStringArray(ARG_FLUTTER_INITIALIZATION_ARGS);
        if (stringArray == null) {
            stringArray = new String[0];
        }
        return new FlutterShellArgs(stringArray);
    }

    @Nullable
    public String getInitialRoute() {
        return getArguments().getString(ARG_INITIAL_ROUTE);
    }

    @NonNull
    public FlutterView.RenderMode getRenderMode() {
        return FlutterView.RenderMode.valueOf(getArguments().getString(ARG_FLUTTERVIEW_RENDER_MODE, FlutterView.RenderMode.surface.name()));
    }

    @NonNull
    public FlutterView.TransparencyMode getTransparencyMode() {
        return FlutterView.TransparencyMode.valueOf(getArguments().getString(ARG_FLUTTERVIEW_TRANSPARENCY_MODE, FlutterView.TransparencyMode.transparent.name()));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.delegate.onActivityResult(i, i2, intent);
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.delegate = new FlutterActivityAndFragmentDelegate(this);
        this.delegate.onAttach(context);
    }

    @ActivityCallThrough
    public void onBackPressed() {
        this.delegate.onBackPressed();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return this.delegate.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.delegate.onDestroyView();
    }

    public void onDetach() {
        super.onDetach();
        this.delegate.onDetach();
        this.delegate.release();
        this.delegate = null;
    }

    public void onFirstFrameRendered() {
        FragmentActivity activity = getActivity();
        if (activity instanceof OnFirstFrameRenderedListener) {
            ((OnFirstFrameRenderedListener) activity).onFirstFrameRendered();
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.delegate.onLowMemory();
    }

    @ActivityCallThrough
    public void onNewIntent(@NonNull Intent intent) {
        this.delegate.onNewIntent(intent);
    }

    public void onPause() {
        super.onPause();
        this.delegate.onPause();
    }

    @ActivityCallThrough
    public void onPostResume() {
        this.delegate.onPostResume();
    }

    @ActivityCallThrough
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        this.delegate.onRequestPermissionsResult(i, strArr, iArr);
    }

    public void onResume() {
        super.onResume();
        this.delegate.onResume();
    }

    public void onStart() {
        super.onStart();
        this.delegate.onStart();
    }

    public void onStop() {
        super.onStop();
        this.delegate.onStop();
    }

    @ActivityCallThrough
    public void onTrimMemory(int i) {
        this.delegate.onTrimMemory(i);
    }

    @ActivityCallThrough
    public void onUserLeaveHint() {
        this.delegate.onUserLeaveHint();
    }

    @Nullable
    public FlutterEngine provideFlutterEngine(@NonNull Context context) {
        FragmentActivity activity = getActivity();
        if (!(activity instanceof FlutterEngineProvider)) {
            return null;
        }
        Log.d(TAG, "Deferring to attached Activity to provide a FlutterEngine.");
        return ((FlutterEngineProvider) activity).provideFlutterEngine(getContext());
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
        FragmentActivity activity = getActivity();
        if (activity instanceof SplashScreenProvider) {
            return ((SplashScreenProvider) activity).provideSplashScreen();
        }
        return null;
    }

    public boolean shouldAttachEngineToActivity() {
        return getArguments().getBoolean(ARG_SHOULD_ATTACH_ENGINE_TO_ACTIVITY);
    }

    public boolean shouldDestroyEngineWithHost() {
        return getArguments().getBoolean(ARG_DESTROY_ENGINE_WITH_FRAGMENT, false);
    }
}

package io.flutter.plugins.urllauncher;

import android.app.Activity;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.PluginRegistry;

public final class UrlLauncherPlugin implements FlutterPlugin, ActivityAware {
    private static final String TAG = "UrlLauncherPlugin";
    @Nullable
    private MethodCallHandlerImpl methodCallHandler;
    @Nullable
    private UrlLauncher urlLauncher;

    public static void registerWith(PluginRegistry.Registrar registrar) {
        new MethodCallHandlerImpl(new UrlLauncher(registrar.context(), registrar.activity())).startListening(registrar.messenger());
    }

    public void onAttachedToActivity(@NonNull ActivityPluginBinding activityPluginBinding) {
        if (this.methodCallHandler == null) {
            Log.wtf(TAG, "urlLauncher was never set.");
        } else {
            this.urlLauncher.setActivity(activityPluginBinding.getActivity());
        }
    }

    public void onAttachedToEngine(@NonNull FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.urlLauncher = new UrlLauncher(flutterPluginBinding.getApplicationContext(), (Activity) null);
        this.methodCallHandler = new MethodCallHandlerImpl(this.urlLauncher);
        this.methodCallHandler.startListening(flutterPluginBinding.getFlutterEngine().getDartExecutor());
    }

    public void onDetachedFromActivity() {
        if (this.methodCallHandler == null) {
            Log.wtf(TAG, "urlLauncher was never set.");
        } else {
            this.urlLauncher.setActivity((Activity) null);
        }
    }

    public void onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity();
    }

    public void onDetachedFromEngine(@NonNull FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        MethodCallHandlerImpl methodCallHandlerImpl = this.methodCallHandler;
        if (methodCallHandlerImpl == null) {
            Log.wtf(TAG, "Already detached from the engine.");
            return;
        }
        methodCallHandlerImpl.stopListening();
        this.methodCallHandler = null;
        this.urlLauncher = null;
    }

    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding activityPluginBinding) {
        onAttachedToActivity(activityPluginBinding);
    }
}

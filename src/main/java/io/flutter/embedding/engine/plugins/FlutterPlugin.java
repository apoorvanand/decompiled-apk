package io.flutter.embedding.engine.plugins;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import io.flutter.embedding.engine.FlutterEngine;

public interface FlutterPlugin {

    public static class FlutterPluginBinding implements LifecycleOwner {
        private final Context applicationContext;
        private final FlutterEngine flutterEngine;
        private final Lifecycle lifecycle;

        public FlutterPluginBinding(@NonNull Context context, @NonNull FlutterEngine flutterEngine2, @NonNull Lifecycle lifecycle2) {
            this.applicationContext = context;
            this.flutterEngine = flutterEngine2;
            this.lifecycle = lifecycle2;
        }

        @NonNull
        public Context getApplicationContext() {
            return this.applicationContext;
        }

        @NonNull
        public FlutterEngine getFlutterEngine() {
            return this.flutterEngine;
        }

        @NonNull
        public Lifecycle getLifecycle() {
            return this.lifecycle;
        }
    }

    void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding);

    void onDetachedFromEngine(@NonNull FlutterPluginBinding flutterPluginBinding);
}

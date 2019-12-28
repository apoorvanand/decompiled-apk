package io.flutter.plugins.googlemaps;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import io.flutter.plugin.common.PluginRegistry;
import java.util.concurrent.atomic.AtomicInteger;

public class GoogleMapsPlugin implements Application.ActivityLifecycleCallbacks {
    static final int CREATED = 1;
    static final int DESTROYED = 6;
    static final int PAUSED = 4;
    static final int RESUMED = 3;
    static final int STARTED = 2;
    static final int STOPPED = 5;
    private final int registrarActivityHashCode;
    private final AtomicInteger state = new AtomicInteger(0);

    private GoogleMapsPlugin(PluginRegistry.Registrar registrar) {
        this.registrarActivityHashCode = registrar.activity().hashCode();
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        if (registrar.activity() != null) {
            GoogleMapsPlugin googleMapsPlugin = new GoogleMapsPlugin(registrar);
            registrar.activity().getApplication().registerActivityLifecycleCallbacks(googleMapsPlugin);
            registrar.platformViewRegistry().registerViewFactory("plugins.flutter.io/google_maps", new GoogleMapFactory(googleMapsPlugin.state, registrar));
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (activity.hashCode() == this.registrarActivityHashCode) {
            this.state.set(1);
        }
    }

    public void onActivityDestroyed(Activity activity) {
        if (activity.hashCode() == this.registrarActivityHashCode) {
            activity.getApplication().unregisterActivityLifecycleCallbacks(this);
            this.state.set(6);
        }
    }

    public void onActivityPaused(Activity activity) {
        if (activity.hashCode() == this.registrarActivityHashCode) {
            this.state.set(4);
        }
    }

    public void onActivityResumed(Activity activity) {
        if (activity.hashCode() == this.registrarActivityHashCode) {
            this.state.set(3);
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        if (activity.hashCode() == this.registrarActivityHashCode) {
            this.state.set(2);
        }
    }

    public void onActivityStopped(Activity activity) {
        if (activity.hashCode() == this.registrarActivityHashCode) {
            this.state.set(5);
        }
    }
}

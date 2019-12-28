package com.github.adee42.keyboardvisibility;

import android.app.Activity;
import android.app.Application;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.PluginRegistry;

public class KeyboardVisibilityPlugin implements Application.ActivityLifecycleCallbacks, ViewTreeObserver.OnGlobalLayoutListener, EventChannel.StreamHandler {
    private static final String STREAM_CHANNEL_NAME = "github.com/adee42/flutter_keyboard_visibility";
    View a = null;
    EventChannel.EventSink b;
    PluginRegistry.Registrar c;
    boolean d;

    KeyboardVisibilityPlugin(PluginRegistry.Registrar registrar) {
        this.c = registrar;
        this.b = null;
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        EventChannel eventChannel = new EventChannel(registrar.messenger(), STREAM_CHANNEL_NAME);
        KeyboardVisibilityPlugin keyboardVisibilityPlugin = new KeyboardVisibilityPlugin(registrar);
        eventChannel.setStreamHandler(keyboardVisibilityPlugin);
        registrar.activity().getApplication().registerActivityLifecycleCallbacks(keyboardVisibilityPlugin);
    }

    private void unregisterListener() {
        View view = this.a;
        if (view != null) {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            this.a = null;
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
        unregisterListener();
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        try {
            this.a = ((ViewGroup) activity.findViewById(16908290)).getChildAt(0);
            this.a.getViewTreeObserver().addOnGlobalLayoutListener(this);
        } catch (Exception unused) {
        }
    }

    public void onActivityStopped(Activity activity) {
        unregisterListener();
    }

    public void onCancel(Object obj) {
        this.b = null;
    }

    public void onGlobalLayout() {
        Rect rect = new Rect();
        View view = this.a;
        if (view != null) {
            view.getWindowVisibleDisplayFrame(rect);
            boolean z = ((double) rect.height()) / ((double) this.a.getRootView().getHeight()) < 0.85d;
            if (z != this.d) {
                this.d = z;
                EventChannel.EventSink eventSink = this.b;
                if (eventSink != null) {
                    eventSink.success(Integer.valueOf(this.d ? 1 : 0));
                }
            }
        }
    }

    public void onListen(Object obj, EventChannel.EventSink eventSink) {
        this.b = eventSink;
        if (this.d) {
            eventSink.success(1);
        }
    }
}

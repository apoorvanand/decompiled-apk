package io.flutter.embedding.engine.plugins.activity;

import android.app.Activity;
import androidx.annotation.NonNull;
import io.flutter.plugin.common.PluginRegistry;

public interface ActivityPluginBinding {
    void addActivityResultListener(@NonNull PluginRegistry.ActivityResultListener activityResultListener);

    void addOnNewIntentListener(@NonNull PluginRegistry.NewIntentListener newIntentListener);

    void addOnUserLeaveHintListener(@NonNull PluginRegistry.UserLeaveHintListener userLeaveHintListener);

    void addRequestPermissionsResultListener(@NonNull PluginRegistry.RequestPermissionsResultListener requestPermissionsResultListener);

    @NonNull
    Activity getActivity();

    void removeActivityResultListener(@NonNull PluginRegistry.ActivityResultListener activityResultListener);

    void removeOnNewIntentListener(@NonNull PluginRegistry.NewIntentListener newIntentListener);

    void removeOnUserLeaveHintListener(@NonNull PluginRegistry.UserLeaveHintListener userLeaveHintListener);

    void removeRequestPermissionsResultListener(@NonNull PluginRegistry.RequestPermissionsResultListener requestPermissionsResultListener);
}

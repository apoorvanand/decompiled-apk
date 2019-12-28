package io.flutter.embedding.engine.plugins.service;

import android.app.Service;
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.service.ServiceAware;

public interface ServicePluginBinding {
    void addOnModeChangeListener(@NonNull ServiceAware.OnModeChangeListener onModeChangeListener);

    @NonNull
    Service getService();

    void removeOnModeChangeListener(@NonNull ServiceAware.OnModeChangeListener onModeChangeListener);
}

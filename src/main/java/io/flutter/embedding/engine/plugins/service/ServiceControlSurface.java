package io.flutter.embedding.engine.plugins.service;

import android.app.Service;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;

public interface ServiceControlSurface {
    void attachToService(@NonNull Service service, @NonNull Lifecycle lifecycle, boolean z);

    void detachFromService();

    void onMoveToBackground();

    void onMoveToForeground();
}

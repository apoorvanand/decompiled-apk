package io.flutter.embedding.engine.plugins.activity;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;

public interface ActivityControlSurface {
    void attachToActivity(@NonNull Activity activity, @NonNull Lifecycle lifecycle);

    void detachFromActivity();

    void detachFromActivityForConfigChanges();

    boolean onActivityResult(int i, int i2, @Nullable Intent intent);

    void onNewIntent(@NonNull Intent intent);

    boolean onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr);

    void onUserLeaveHint();
}

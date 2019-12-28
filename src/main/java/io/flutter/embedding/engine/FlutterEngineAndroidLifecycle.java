package io.flutter.embedding.engine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

final class FlutterEngineAndroidLifecycle extends LifecycleRegistry {
    private static final String TAG = "FlutterEngineAndroidLifecycle";
    @Nullable
    private Lifecycle backingLifecycle;
    @NonNull
    private final LifecycleObserver forwardingObserver = new DefaultLifecycleObserver() {
        public void onCreate(@NonNull LifecycleOwner lifecycleOwner) {
        }

        public void onDestroy(@NonNull LifecycleOwner lifecycleOwner) {
        }

        public void onPause(@NonNull LifecycleOwner lifecycleOwner) {
            FlutterEngineAndroidLifecycle.this.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
        }

        public void onResume(@NonNull LifecycleOwner lifecycleOwner) {
            FlutterEngineAndroidLifecycle.this.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
        }

        public void onStart(@NonNull LifecycleOwner lifecycleOwner) {
            FlutterEngineAndroidLifecycle.this.handleLifecycleEvent(Lifecycle.Event.ON_START);
        }

        public void onStop(@NonNull LifecycleOwner lifecycleOwner) {
            FlutterEngineAndroidLifecycle.this.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        }
    };
    private boolean isDestroyed = false;

    /* JADX WARNING: type inference failed for: r1v2, types: [io.flutter.embedding.engine.FlutterEngineAndroidLifecycle$1, androidx.lifecycle.LifecycleObserver] */
    FlutterEngineAndroidLifecycle(@NonNull LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    private void ensureNotDestroyed() {
        if (this.isDestroyed) {
            throw new IllegalStateException("Tried to invoke a method on a destroyed FlutterEngineAndroidLifecycle.");
        }
    }

    public void destroy() {
        ensureNotDestroyed();
        setBackingLifecycle((Lifecycle) null);
        markState(Lifecycle.State.DESTROYED);
        this.isDestroyed = true;
    }

    public void handleLifecycleEvent(@NonNull Lifecycle.Event event) {
        ensureNotDestroyed();
        super.handleLifecycleEvent(event);
    }

    public void setBackingLifecycle(@Nullable Lifecycle lifecycle) {
        ensureNotDestroyed();
        Lifecycle lifecycle2 = this.backingLifecycle;
        if (lifecycle2 != null) {
            lifecycle2.removeObserver(this.forwardingObserver);
        }
        handleLifecycleEvent(Lifecycle.Event.ON_STOP);
        this.backingLifecycle = lifecycle;
        if (this.backingLifecycle != null) {
            lifecycle.addObserver(this.forwardingObserver);
        }
    }
}

package io.flutter.plugins.googlesignin;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

public final class Executors {

    private static final class UiThreadExecutor implements Executor {
        private static final Handler UI_THREAD = new Handler(Looper.getMainLooper());

        private UiThreadExecutor() {
        }

        public void execute(Runnable runnable) {
            UI_THREAD.post(runnable);
        }
    }

    private Executors() {
    }

    public static Executor uiThreadExecutor() {
        return new UiThreadExecutor();
    }
}

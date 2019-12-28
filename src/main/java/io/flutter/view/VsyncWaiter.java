package io.flutter.view;

import android.view.Choreographer;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.FlutterJNI;

public class VsyncWaiter {
    private static VsyncWaiter instance;
    private final FlutterJNI.AsyncWaitForVsyncDelegate asyncWaitForVsyncDelegate = new FlutterJNI.AsyncWaitForVsyncDelegate() {
        public void asyncWaitForVsync(final long j) {
            Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
                public void doFrame(long j) {
                    FlutterJNI.nativeOnVsync(j, j + ((long) (1.0E9d / ((double) VsyncWaiter.this.windowManager.getDefaultDisplay().getRefreshRate()))), j);
                }
            });
        }
    };
    /* access modifiers changed from: private */
    @NonNull
    public final WindowManager windowManager;

    private VsyncWaiter(@NonNull WindowManager windowManager2) {
        this.windowManager = windowManager2;
    }

    @NonNull
    public static VsyncWaiter getInstance(@NonNull WindowManager windowManager2) {
        if (instance == null) {
            instance = new VsyncWaiter(windowManager2);
        }
        return instance;
    }

    public void init() {
        FlutterJNI.setAsyncWaitForVsyncDelegate(this.asyncWaitForVsyncDelegate);
        FlutterJNI.setRefreshRateFPS(this.windowManager.getDefaultDisplay().getRefreshRate());
    }
}

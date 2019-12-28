package io.flutter.plugins.googlesignin;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class BackgroundTaskRunner {
    private final ThreadPoolExecutor executor;

    public interface Callback<T> {
        void run(Future<T> future);
    }

    public BackgroundTaskRunner(int i) {
        int i2 = i;
        int i3 = i;
        this.executor = new ThreadPoolExecutor(i2, i3, 1, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    public <T> ListenableFuture<T> runInBackground(final Callable<T> callable) {
        final SettableFuture create = SettableFuture.create();
        this.executor.execute(new Runnable() {
            public void run() {
                if (!create.isCancelled()) {
                    try {
                        create.set(callable.call());
                    } catch (Throwable th) {
                        create.setException(th);
                    }
                }
            }
        });
        return create;
    }

    public <T> void runInBackground(Callable<T> callable, final Callback<T> callback) {
        final ListenableFuture<T> runInBackground = runInBackground(callable);
        runInBackground.addListener(new Runnable() {
            public void run() {
                callback.run(runInBackground);
            }
        }, Executors.uiThreadExecutor());
    }
}

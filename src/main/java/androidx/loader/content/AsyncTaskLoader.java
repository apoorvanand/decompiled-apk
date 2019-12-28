package androidx.loader.content;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.os.OperationCanceledException;
import androidx.core.util.TimeUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

public abstract class AsyncTaskLoader<D> extends Loader<D> {
    volatile AsyncTaskLoader<D>.LoadTask a;
    volatile AsyncTaskLoader<D>.LoadTask b;
    long c;
    long d;
    Handler e;
    private final Executor mExecutor;

    final class LoadTask extends ModernAsyncTask<Void, Void, D> implements Runnable {
        boolean a;
        private final CountDownLatch mDone = new CountDownLatch(1);

        LoadTask() {
        }

        /* access modifiers changed from: protected */
        public D a(Void... voidArr) {
            try {
                return AsyncTaskLoader.this.d();
            } catch (OperationCanceledException e) {
                if (isCancelled()) {
                    return null;
                }
                throw e;
            }
        }

        /* access modifiers changed from: protected */
        public void a(D d) {
            try {
                AsyncTaskLoader.this.b(this, d);
            } finally {
                this.mDone.countDown();
            }
        }

        /* access modifiers changed from: protected */
        public void b(D d) {
            try {
                AsyncTaskLoader.this.a(this, d);
            } finally {
                this.mDone.countDown();
            }
        }

        public void run() {
            this.a = false;
            AsyncTaskLoader.this.c();
        }

        public void waitForLoader() {
            try {
                this.mDone.await();
            } catch (InterruptedException unused) {
            }
        }
    }

    public AsyncTaskLoader(@NonNull Context context) {
        this(context, ModernAsyncTask.THREAD_POOL_EXECUTOR);
    }

    private AsyncTaskLoader(@NonNull Context context, @NonNull Executor executor) {
        super(context);
        this.d = -10000;
        this.mExecutor = executor;
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        cancelLoad();
        this.a = new LoadTask();
        c();
    }

    /* access modifiers changed from: package-private */
    public void a(AsyncTaskLoader<D>.LoadTask loadTask, D d2) {
        onCanceled(d2);
        if (this.b == loadTask) {
            rollbackContentChanged();
            this.d = SystemClock.uptimeMillis();
            this.b = null;
            deliverCancellation();
            c();
        }
    }

    /* access modifiers changed from: package-private */
    public void b(AsyncTaskLoader<D>.LoadTask loadTask, D d2) {
        if (this.a != loadTask) {
            a(loadTask, d2);
        } else if (isAbandoned()) {
            onCanceled(d2);
        } else {
            commitContentChanged();
            this.d = SystemClock.uptimeMillis();
            this.a = null;
            deliverResult(d2);
        }
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        if (this.a == null) {
            return false;
        }
        if (!this.r) {
            this.u = true;
        }
        if (this.b != null) {
            if (this.a.a) {
                this.a.a = false;
                this.e.removeCallbacks(this.a);
            }
            this.a = null;
            return false;
        } else if (this.a.a) {
            this.a.a = false;
            this.e.removeCallbacks(this.a);
            this.a = null;
            return false;
        } else {
            boolean cancel = this.a.cancel(false);
            if (cancel) {
                this.b = this.a;
                cancelLoadInBackground();
            }
            this.a = null;
            return cancel;
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        if (this.b == null && this.a != null) {
            if (this.a.a) {
                this.a.a = false;
                this.e.removeCallbacks(this.a);
            }
            if (this.c <= 0 || SystemClock.uptimeMillis() >= this.d + this.c) {
                this.a.executeOnExecutor(this.mExecutor, (Params[]) null);
                return;
            }
            this.a.a = true;
            this.e.postAtTime(this.a, this.d + this.c);
        }
    }

    public void cancelLoadInBackground() {
    }

    /* access modifiers changed from: protected */
    @Nullable
    public D d() {
        return loadInBackground();
    }

    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        if (this.a != null) {
            printWriter.print(str);
            printWriter.print("mTask=");
            printWriter.print(this.a);
            printWriter.print(" waiting=");
            printWriter.println(this.a.a);
        }
        if (this.b != null) {
            printWriter.print(str);
            printWriter.print("mCancellingTask=");
            printWriter.print(this.b);
            printWriter.print(" waiting=");
            printWriter.println(this.b.a);
        }
        if (this.c != 0) {
            printWriter.print(str);
            printWriter.print("mUpdateThrottle=");
            TimeUtils.formatDuration(this.c, printWriter);
            printWriter.print(" mLastLoadCompleteTime=");
            TimeUtils.formatDuration(this.d, SystemClock.uptimeMillis(), printWriter);
            printWriter.println();
        }
    }

    public boolean isLoadInBackgroundCanceled() {
        return this.b != null;
    }

    @Nullable
    public abstract D loadInBackground();

    public void onCanceled(@Nullable D d2) {
    }

    public void setUpdateThrottle(long j) {
        this.c = j;
        if (j != 0) {
            this.e = new Handler();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void waitForLoader() {
        AsyncTaskLoader<D>.LoadTask loadTask = this.a;
        if (loadTask != null) {
            loadTask.waitForLoader();
        }
    }
}

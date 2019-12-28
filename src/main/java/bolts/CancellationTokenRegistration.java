package bolts;

import java.io.Closeable;

public class CancellationTokenRegistration implements Closeable {
    private Runnable action;
    private boolean closed;
    private final Object lock = new Object();
    private CancellationTokenSource tokenSource;

    CancellationTokenRegistration(CancellationTokenSource cancellationTokenSource, Runnable runnable) {
        this.tokenSource = cancellationTokenSource;
        this.action = runnable;
    }

    private void throwIfClosed() {
        if (this.closed) {
            throw new IllegalStateException("Object already closed");
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        synchronized (this.lock) {
            throwIfClosed();
            this.action.run();
            close();
        }
    }

    public void close() {
        synchronized (this.lock) {
            if (!this.closed) {
                this.closed = true;
                this.tokenSource.a(this);
                this.tokenSource = null;
                this.action = null;
            }
        }
    }
}

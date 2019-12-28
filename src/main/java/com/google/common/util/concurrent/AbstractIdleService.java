package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@GwtIncompatible
@Beta
public abstract class AbstractIdleService implements Service {
    private final Service delegate = new DelegateService();
    /* access modifiers changed from: private */
    public final Supplier<String> threadNameSupplier = new ThreadNameSupplier();

    private final class DelegateService extends AbstractService {
        private DelegateService() {
        }

        /* access modifiers changed from: protected */
        public final void a() {
            MoreExecutors.a(AbstractIdleService.this.c(), (Supplier<String>) AbstractIdleService.this.threadNameSupplier).execute(new Runnable() {
                public void run() {
                    try {
                        AbstractIdleService.this.a();
                        DelegateService.this.c();
                    } catch (Throwable th) {
                        DelegateService.this.a(th);
                    }
                }
            });
        }

        /* access modifiers changed from: protected */
        public final void b() {
            MoreExecutors.a(AbstractIdleService.this.c(), (Supplier<String>) AbstractIdleService.this.threadNameSupplier).execute(new Runnable() {
                public void run() {
                    try {
                        AbstractIdleService.this.b();
                        DelegateService.this.d();
                    } catch (Throwable th) {
                        DelegateService.this.a(th);
                    }
                }
            });
        }

        public String toString() {
            return AbstractIdleService.this.toString();
        }
    }

    private final class ThreadNameSupplier implements Supplier<String> {
        private ThreadNameSupplier() {
        }

        public String get() {
            return AbstractIdleService.this.d() + " " + AbstractIdleService.this.state();
        }
    }

    protected AbstractIdleService() {
    }

    /* access modifiers changed from: protected */
    public abstract void a();

    public final void addListener(Service.Listener listener, Executor executor) {
        this.delegate.addListener(listener, executor);
    }

    public final void awaitRunning() {
        this.delegate.awaitRunning();
    }

    public final void awaitRunning(long j, TimeUnit timeUnit) {
        this.delegate.awaitRunning(j, timeUnit);
    }

    public final void awaitTerminated() {
        this.delegate.awaitTerminated();
    }

    public final void awaitTerminated(long j, TimeUnit timeUnit) {
        this.delegate.awaitTerminated(j, timeUnit);
    }

    /* access modifiers changed from: protected */
    public abstract void b();

    /* access modifiers changed from: protected */
    public Executor c() {
        return new Executor() {
            public void execute(Runnable runnable) {
                MoreExecutors.a((String) AbstractIdleService.this.threadNameSupplier.get(), runnable).start();
            }
        };
    }

    /* access modifiers changed from: protected */
    public String d() {
        return getClass().getSimpleName();
    }

    public final Throwable failureCause() {
        return this.delegate.failureCause();
    }

    public final boolean isRunning() {
        return this.delegate.isRunning();
    }

    @CanIgnoreReturnValue
    public final Service startAsync() {
        this.delegate.startAsync();
        return this;
    }

    public final Service.State state() {
        return this.delegate.state();
    }

    @CanIgnoreReturnValue
    public final Service stopAsync() {
        this.delegate.stopAsync();
        return this;
    }

    public String toString() {
        return d() + " [" + state() + "]";
    }
}

package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@GwtIncompatible
@Beta
public abstract class AbstractExecutionThreadService implements Service {
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(AbstractExecutionThreadService.class.getName());
    private final Service delegate = new AbstractService() {
        /* access modifiers changed from: protected */
        public final void a() {
            MoreExecutors.a(AbstractExecutionThreadService.this.e(), (Supplier<String>) new Supplier<String>() {
                public String get() {
                    return AbstractExecutionThreadService.this.f();
                }
            }).execute(new Runnable() {
                public void run() {
                    try {
                        AbstractExecutionThreadService.this.a();
                        AnonymousClass1.this.c();
                        if (AnonymousClass1.this.isRunning()) {
                            AbstractExecutionThreadService.this.b();
                        }
                        AbstractExecutionThreadService.this.c();
                        AnonymousClass1.this.d();
                    } catch (Throwable th) {
                        AnonymousClass1.this.a(th);
                    }
                }
            });
        }

        /* access modifiers changed from: protected */
        public void b() {
            AbstractExecutionThreadService.this.d();
        }

        public String toString() {
            return AbstractExecutionThreadService.this.toString();
        }
    };

    protected AbstractExecutionThreadService() {
    }

    /* access modifiers changed from: protected */
    public void a() {
    }

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
    public void c() {
    }

    /* access modifiers changed from: protected */
    public void d() {
    }

    /* access modifiers changed from: protected */
    public Executor e() {
        return new Executor() {
            public void execute(Runnable runnable) {
                MoreExecutors.a(AbstractExecutionThreadService.this.f(), runnable).start();
            }
        };
    }

    /* access modifiers changed from: protected */
    public String f() {
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
        return f() + " [" + state() + "]";
    }
}

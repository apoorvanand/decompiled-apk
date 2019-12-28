package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;
import javax.annotation.concurrent.GuardedBy;

@GwtIncompatible
@Beta
public abstract class AbstractScheduledService implements Service {
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(AbstractScheduledService.class.getName());
    /* access modifiers changed from: private */
    public final AbstractService delegate = new ServiceDelegate();

    @Beta
    public static abstract class CustomScheduler extends Scheduler {

        private class ReschedulableCallable extends ForwardingFuture<Void> implements Callable<Void> {
            final /* synthetic */ CustomScheduler a;
            @GuardedBy("lock")
            private Future<Void> currentFuture;
            private final ScheduledExecutorService executor;
            private final ReentrantLock lock;
            private final AbstractService service;
            private final Runnable wrappedRunnable;

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Future<Void> delegate() {
                throw new UnsupportedOperationException("Only cancel and isCancelled is supported by this future");
            }

            public Void call() {
                this.wrappedRunnable.run();
                reschedule();
                return null;
            }

            public boolean cancel(boolean z) {
                this.lock.lock();
                try {
                    return this.currentFuture.cancel(z);
                } finally {
                    this.lock.unlock();
                }
            }

            public boolean isCancelled() {
                this.lock.lock();
                try {
                    return this.currentFuture.isCancelled();
                } finally {
                    this.lock.unlock();
                }
            }

            public void reschedule() {
                try {
                    Schedule a2 = this.a.a();
                    Throwable th = null;
                    this.lock.lock();
                    try {
                        if (this.currentFuture == null || !this.currentFuture.isCancelled()) {
                            this.currentFuture = this.executor.schedule(this, a2.delay, a2.unit);
                        }
                    } catch (Throwable th2) {
                        this.lock.unlock();
                        throw th2;
                    }
                    this.lock.unlock();
                    if (th != null) {
                        this.service.a(th);
                    }
                } catch (Throwable th3) {
                    this.service.a(th3);
                }
            }
        }

        @Beta
        protected static final class Schedule {
            /* access modifiers changed from: private */
            public final long delay;
            /* access modifiers changed from: private */
            public final TimeUnit unit;

            public Schedule(long j, TimeUnit timeUnit) {
                this.delay = j;
                this.unit = (TimeUnit) Preconditions.checkNotNull(timeUnit);
            }
        }

        public CustomScheduler() {
            super();
        }

        /* access modifiers changed from: protected */
        public abstract Schedule a();
    }

    public static abstract class Scheduler {
        private Scheduler() {
        }

        public static Scheduler newFixedDelaySchedule(long j, long j2, TimeUnit timeUnit) {
            Preconditions.checkNotNull(timeUnit);
            Preconditions.checkArgument(j2 > 0, "delay must be > 0, found %s", j2);
            final long j3 = j;
            final long j4 = j2;
            final TimeUnit timeUnit2 = timeUnit;
            return new Scheduler() {
                public Future<?> schedule(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable) {
                    return scheduledExecutorService.scheduleWithFixedDelay(runnable, j3, j4, timeUnit2);
                }
            };
        }

        public static Scheduler newFixedRateSchedule(long j, long j2, TimeUnit timeUnit) {
            Preconditions.checkNotNull(timeUnit);
            Preconditions.checkArgument(j2 > 0, "period must be > 0, found %s", j2);
            final long j3 = j;
            final long j4 = j2;
            final TimeUnit timeUnit2 = timeUnit;
            return new Scheduler() {
                public Future<?> schedule(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable) {
                    return scheduledExecutorService.scheduleAtFixedRate(runnable, j3, j4, timeUnit2);
                }
            };
        }

        /* access modifiers changed from: package-private */
        public abstract Future<?> schedule(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable);
    }

    private final class ServiceDelegate extends AbstractService {
        /* access modifiers changed from: private */
        public volatile ScheduledExecutorService executorService;
        /* access modifiers changed from: private */
        public final ReentrantLock lock;
        /* access modifiers changed from: private */
        public volatile Future<?> runningTask;
        /* access modifiers changed from: private */
        public final Runnable task;

        class Task implements Runnable {
            Task() {
            }

            public void run() {
                ServiceDelegate.this.lock.lock();
                try {
                    if (ServiceDelegate.this.runningTask.isCancelled()) {
                        ServiceDelegate.this.lock.unlock();
                        return;
                    }
                    AbstractScheduledService.this.a();
                    ServiceDelegate.this.lock.unlock();
                } catch (Throwable th) {
                    ServiceDelegate.this.lock.unlock();
                    throw th;
                }
            }
        }

        private ServiceDelegate() {
            this.lock = new ReentrantLock();
            this.task = new Task();
        }

        /* access modifiers changed from: protected */
        public final void a() {
            this.executorService = MoreExecutors.a(AbstractScheduledService.this.e(), (Supplier<String>) new Supplier<String>() {
                public String get() {
                    return AbstractScheduledService.this.f() + " " + ServiceDelegate.this.state();
                }
            });
            this.executorService.execute(new Runnable() {
                public void run() {
                    ServiceDelegate.this.lock.lock();
                    try {
                        AbstractScheduledService.this.b();
                        Future unused = ServiceDelegate.this.runningTask = AbstractScheduledService.this.d().schedule(AbstractScheduledService.this.delegate, ServiceDelegate.this.executorService, ServiceDelegate.this.task);
                        ServiceDelegate.this.c();
                    } catch (Throwable th) {
                        ServiceDelegate.this.lock.unlock();
                        throw th;
                    }
                    ServiceDelegate.this.lock.unlock();
                }
            });
        }

        /* access modifiers changed from: protected */
        public final void b() {
            this.runningTask.cancel(false);
            this.executorService.execute(new Runnable() {
                public void run() {
                    try {
                        ServiceDelegate.this.lock.lock();
                        if (ServiceDelegate.this.state() != Service.State.STOPPING) {
                            ServiceDelegate.this.lock.unlock();
                            return;
                        }
                        AbstractScheduledService.this.c();
                        ServiceDelegate.this.lock.unlock();
                        ServiceDelegate.this.d();
                    } catch (Throwable th) {
                        ServiceDelegate.this.a(th);
                    }
                }
            });
        }

        public String toString() {
            return AbstractScheduledService.this.toString();
        }
    }

    protected AbstractScheduledService() {
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
    public void b() {
    }

    /* access modifiers changed from: protected */
    public void c() {
    }

    /* access modifiers changed from: protected */
    public abstract Scheduler d();

    /* access modifiers changed from: protected */
    public ScheduledExecutorService e() {
        final ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            public Thread newThread(Runnable runnable) {
                return MoreExecutors.a(AbstractScheduledService.this.f(), runnable);
            }
        });
        addListener(new Service.Listener() {
            public void failed(Service.State state, Throwable th) {
                newSingleThreadScheduledExecutor.shutdown();
            }

            public void terminated(Service.State state) {
                newSingleThreadScheduledExecutor.shutdown();
            }
        }, MoreExecutors.directExecutor());
        return newSingleThreadScheduledExecutor;
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

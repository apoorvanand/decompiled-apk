package com.google.common.util.concurrent;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenerCallQueue;
import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.Immutable;

@GwtIncompatible
@Beta
public abstract class AbstractService implements Service {
    private static final ListenerCallQueue.Callback<Service.Listener> RUNNING_CALLBACK = new ListenerCallQueue.Callback<Service.Listener>("running()") {
        /* access modifiers changed from: package-private */
        public void a(Service.Listener listener) {
            listener.running();
        }
    };
    private static final ListenerCallQueue.Callback<Service.Listener> STARTING_CALLBACK = new ListenerCallQueue.Callback<Service.Listener>("starting()") {
        /* access modifiers changed from: package-private */
        public void a(Service.Listener listener) {
            listener.starting();
        }
    };
    private static final ListenerCallQueue.Callback<Service.Listener> STOPPING_FROM_RUNNING_CALLBACK = stoppingCallback(Service.State.RUNNING);
    private static final ListenerCallQueue.Callback<Service.Listener> STOPPING_FROM_STARTING_CALLBACK = stoppingCallback(Service.State.STARTING);
    private static final ListenerCallQueue.Callback<Service.Listener> TERMINATED_FROM_NEW_CALLBACK = terminatedCallback(Service.State.NEW);
    private static final ListenerCallQueue.Callback<Service.Listener> TERMINATED_FROM_RUNNING_CALLBACK = terminatedCallback(Service.State.RUNNING);
    private static final ListenerCallQueue.Callback<Service.Listener> TERMINATED_FROM_STOPPING_CALLBACK = terminatedCallback(Service.State.STOPPING);
    private final Monitor.Guard hasReachedRunning = new HasReachedRunningGuard();
    private final Monitor.Guard isStartable = new IsStartableGuard();
    private final Monitor.Guard isStoppable = new IsStoppableGuard();
    private final Monitor.Guard isStopped = new IsStoppedGuard();
    @GuardedBy("monitor")
    private final List<ListenerCallQueue<Service.Listener>> listeners = Collections.synchronizedList(new ArrayList());
    /* access modifiers changed from: private */
    public final Monitor monitor = new Monitor();
    @GuardedBy("monitor")
    private volatile StateSnapshot snapshot = new StateSnapshot(Service.State.NEW);

    private final class HasReachedRunningGuard extends Monitor.Guard {
        HasReachedRunningGuard() {
            super(AbstractService.this.monitor);
        }

        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(Service.State.RUNNING) >= 0;
        }
    }

    private final class IsStartableGuard extends Monitor.Guard {
        IsStartableGuard() {
            super(AbstractService.this.monitor);
        }

        public boolean isSatisfied() {
            return AbstractService.this.state() == Service.State.NEW;
        }
    }

    private final class IsStoppableGuard extends Monitor.Guard {
        IsStoppableGuard() {
            super(AbstractService.this.monitor);
        }

        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(Service.State.RUNNING) <= 0;
        }
    }

    private final class IsStoppedGuard extends Monitor.Guard {
        IsStoppedGuard() {
            super(AbstractService.this.monitor);
        }

        public boolean isSatisfied() {
            return AbstractService.this.state().a();
        }
    }

    @Immutable
    private static final class StateSnapshot {
        final Service.State a;
        final boolean b;
        @Nullable
        final Throwable c;

        StateSnapshot(Service.State state) {
            this(state, false, (Throwable) null);
        }

        StateSnapshot(Service.State state, boolean z, @Nullable Throwable th) {
            boolean z2 = false;
            Preconditions.checkArgument(!z || state == Service.State.STARTING, "shudownWhenStartupFinishes can only be set if state is STARTING. Got %s instead.", (Object) state);
            Preconditions.checkArgument(!((th != null) ^ (state == Service.State.FAILED)) ? true : z2, "A failure cause should be set if and only if the state is failed.  Got %s and %s instead.", (Object) state, (Object) th);
            this.a = state;
            this.b = z;
            this.c = th;
        }

        /* access modifiers changed from: package-private */
        public Service.State a() {
            return (!this.b || this.a != Service.State.STARTING) ? this.a : Service.State.STOPPING;
        }

        /* access modifiers changed from: package-private */
        public Throwable b() {
            Preconditions.checkState(this.a == Service.State.FAILED, "failureCause() is only valid if the service has failed, service is %s", (Object) this.a);
            return this.c;
        }
    }

    protected AbstractService() {
    }

    @GuardedBy("monitor")
    private void checkCurrentState(Service.State state) {
        Service.State state2 = state();
        if (state2 == state) {
            return;
        }
        if (state2 == Service.State.FAILED) {
            throw new IllegalStateException("Expected the service " + this + " to be " + state + ", but the service has FAILED", failureCause());
        }
        throw new IllegalStateException("Expected the service " + this + " to be " + state + ", but was " + state2);
    }

    private void executeListeners() {
        if (!this.monitor.isOccupiedByCurrentThread()) {
            for (int i = 0; i < this.listeners.size(); i++) {
                this.listeners.get(i).a();
            }
        }
    }

    @GuardedBy("monitor")
    private void failed(final Service.State state, final Throwable th) {
        new ListenerCallQueue.Callback<Service.Listener>("failed({from = " + state + ", cause = " + th + "})") {
            /* access modifiers changed from: package-private */
            public void a(Service.Listener listener) {
                listener.failed(state, th);
            }
        }.a(this.listeners);
    }

    @GuardedBy("monitor")
    private void running() {
        RUNNING_CALLBACK.a(this.listeners);
    }

    @GuardedBy("monitor")
    private void starting() {
        STARTING_CALLBACK.a(this.listeners);
    }

    @GuardedBy("monitor")
    private void stopping(Service.State state) {
        ListenerCallQueue.Callback<Service.Listener> callback;
        if (state == Service.State.STARTING) {
            callback = STOPPING_FROM_STARTING_CALLBACK;
        } else if (state == Service.State.RUNNING) {
            callback = STOPPING_FROM_RUNNING_CALLBACK;
        } else {
            throw new AssertionError();
        }
        callback.a((Iterable<ListenerCallQueue<Service.Listener>>) this.listeners);
    }

    private static ListenerCallQueue.Callback<Service.Listener> stoppingCallback(final Service.State state) {
        return new ListenerCallQueue.Callback<Service.Listener>("stopping({from = " + state + "})") {
            /* access modifiers changed from: package-private */
            public void a(Service.Listener listener) {
                listener.stopping(state);
            }
        };
    }

    @GuardedBy("monitor")
    private void terminated(Service.State state) {
        ListenerCallQueue.Callback<Service.Listener> callback;
        int i = AnonymousClass6.a[state.ordinal()];
        if (i != 1) {
            switch (i) {
                case 3:
                    callback = TERMINATED_FROM_RUNNING_CALLBACK;
                    break;
                case 4:
                    callback = TERMINATED_FROM_STOPPING_CALLBACK;
                    break;
                default:
                    throw new AssertionError();
            }
        } else {
            callback = TERMINATED_FROM_NEW_CALLBACK;
        }
        callback.a((Iterable<ListenerCallQueue<Service.Listener>>) this.listeners);
    }

    private static ListenerCallQueue.Callback<Service.Listener> terminatedCallback(final Service.State state) {
        return new ListenerCallQueue.Callback<Service.Listener>("terminated({from = " + state + "})") {
            /* access modifiers changed from: package-private */
            public void a(Service.Listener listener) {
                listener.terminated(state);
            }
        };
    }

    /* access modifiers changed from: protected */
    public abstract void a();

    /* access modifiers changed from: protected */
    public final void a(Throwable th) {
        Preconditions.checkNotNull(th);
        this.monitor.enter();
        try {
            Service.State state = state();
            switch (state) {
                case NEW:
                case TERMINATED:
                    throw new IllegalStateException("Failed while in state:" + state, th);
                case STARTING:
                case RUNNING:
                case STOPPING:
                    this.snapshot = new StateSnapshot(Service.State.FAILED, false, th);
                    failed(state, th);
                    break;
                case FAILED:
                    break;
                default:
                    throw new AssertionError("Unexpected state: " + state);
            }
        } finally {
            this.monitor.leave();
            executeListeners();
        }
    }

    public final void addListener(Service.Listener listener, Executor executor) {
        Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions.checkNotNull(executor, "executor");
        this.monitor.enter();
        try {
            if (!state().a()) {
                this.listeners.add(new ListenerCallQueue(listener, executor));
            }
        } finally {
            this.monitor.leave();
        }
    }

    public final void awaitRunning() {
        this.monitor.enterWhenUninterruptibly(this.hasReachedRunning);
        try {
            checkCurrentState(Service.State.RUNNING);
        } finally {
            this.monitor.leave();
        }
    }

    public final void awaitRunning(long j, TimeUnit timeUnit) {
        if (this.monitor.enterWhenUninterruptibly(this.hasReachedRunning, j, timeUnit)) {
            try {
                checkCurrentState(Service.State.RUNNING);
            } finally {
                this.monitor.leave();
            }
        } else {
            throw new TimeoutException("Timed out waiting for " + this + " to reach the RUNNING state.");
        }
    }

    public final void awaitTerminated() {
        this.monitor.enterWhenUninterruptibly(this.isStopped);
        try {
            checkCurrentState(Service.State.TERMINATED);
        } finally {
            this.monitor.leave();
        }
    }

    public final void awaitTerminated(long j, TimeUnit timeUnit) {
        if (this.monitor.enterWhenUninterruptibly(this.isStopped, j, timeUnit)) {
            try {
                checkCurrentState(Service.State.TERMINATED);
            } finally {
                this.monitor.leave();
            }
        } else {
            throw new TimeoutException("Timed out waiting for " + this + " to reach a terminal state. " + "Current state: " + state());
        }
    }

    /* access modifiers changed from: protected */
    public abstract void b();

    /* access modifiers changed from: protected */
    public final void c() {
        this.monitor.enter();
        try {
            if (this.snapshot.a == Service.State.STARTING) {
                if (this.snapshot.b) {
                    this.snapshot = new StateSnapshot(Service.State.STOPPING);
                    b();
                } else {
                    this.snapshot = new StateSnapshot(Service.State.RUNNING);
                    running();
                }
                return;
            }
            IllegalStateException illegalStateException = new IllegalStateException("Cannot notifyStarted() when the service is " + this.snapshot.a);
            a((Throwable) illegalStateException);
            throw illegalStateException;
        } finally {
            this.monitor.leave();
            executeListeners();
        }
    }

    /* access modifiers changed from: protected */
    public final void d() {
        this.monitor.enter();
        try {
            Service.State state = this.snapshot.a;
            if (state != Service.State.STOPPING) {
                if (state != Service.State.RUNNING) {
                    IllegalStateException illegalStateException = new IllegalStateException("Cannot notifyStopped() when the service is " + state);
                    a((Throwable) illegalStateException);
                    throw illegalStateException;
                }
            }
            this.snapshot = new StateSnapshot(Service.State.TERMINATED);
            terminated(state);
        } finally {
            this.monitor.leave();
            executeListeners();
        }
    }

    public final Throwable failureCause() {
        return this.snapshot.b();
    }

    public final boolean isRunning() {
        return state() == Service.State.RUNNING;
    }

    @CanIgnoreReturnValue
    public final Service startAsync() {
        if (this.monitor.enterIf(this.isStartable)) {
            try {
                this.snapshot = new StateSnapshot(Service.State.STARTING);
                starting();
                a();
            } catch (Throwable th) {
                this.monitor.leave();
                executeListeners();
                throw th;
            }
            this.monitor.leave();
            executeListeners();
            return this;
        }
        throw new IllegalStateException("Service " + this + " has already been started");
    }

    public final Service.State state() {
        return this.snapshot.a();
    }

    @CanIgnoreReturnValue
    public final Service stopAsync() {
        if (this.monitor.enterIf(this.isStoppable)) {
            try {
                Service.State state = state();
                switch (state) {
                    case NEW:
                        this.snapshot = new StateSnapshot(Service.State.TERMINATED);
                        terminated(Service.State.NEW);
                        break;
                    case STARTING:
                        this.snapshot = new StateSnapshot(Service.State.STARTING, true, (Throwable) null);
                        stopping(Service.State.STARTING);
                        break;
                    case RUNNING:
                        this.snapshot = new StateSnapshot(Service.State.STOPPING);
                        stopping(Service.State.RUNNING);
                        b();
                        break;
                    case STOPPING:
                    case TERMINATED:
                    case FAILED:
                        throw new AssertionError("isStoppable is incorrectly implemented, saw: " + state);
                    default:
                        throw new AssertionError("Unexpected state: " + state);
                }
            } catch (Throwable th) {
                this.monitor.leave();
                executeListeners();
                throw th;
            }
            this.monitor.leave();
            executeListeners();
        }
        return this;
    }

    public String toString() {
        return getClass().getSimpleName() + " [" + state() + "]";
    }
}

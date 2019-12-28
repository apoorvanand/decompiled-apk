package com.google.common.util.concurrent;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Ordering;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.ListenerCallQueue;
import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.concurrent.GuardedBy;

@GwtIncompatible
@Beta
public final class ServiceManager {
    /* access modifiers changed from: private */
    public static final ListenerCallQueue.Callback<Listener> HEALTHY_CALLBACK = new ListenerCallQueue.Callback<Listener>("healthy()") {
        /* access modifiers changed from: package-private */
        public void a(Listener listener) {
            listener.healthy();
        }
    };
    /* access modifiers changed from: private */
    public static final ListenerCallQueue.Callback<Listener> STOPPED_CALLBACK = new ListenerCallQueue.Callback<Listener>("stopped()") {
        /* access modifiers changed from: package-private */
        public void a(Listener listener) {
            listener.stopped();
        }
    };
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(ServiceManager.class.getName());
    private final ImmutableList<Service> services;
    private final ServiceManagerState state;

    private static final class EmptyServiceManagerWarning extends Throwable {
        private EmptyServiceManagerWarning() {
        }
    }

    @Beta
    public static abstract class Listener {
        public void failure(Service service) {
        }

        public void healthy() {
        }

        public void stopped() {
        }
    }

    private static final class NoOpService extends AbstractService {
        private NoOpService() {
        }

        /* access modifiers changed from: protected */
        public void a() {
            c();
        }

        /* access modifiers changed from: protected */
        public void b() {
            d();
        }
    }

    private static final class ServiceListener extends Service.Listener {
        final Service a;
        final WeakReference<ServiceManagerState> b;

        ServiceListener(Service service, WeakReference<ServiceManagerState> weakReference) {
            this.a = service;
            this.b = weakReference;
        }

        public void failed(Service.State state, Throwable th) {
            ServiceManagerState serviceManagerState = (ServiceManagerState) this.b.get();
            if (serviceManagerState != null) {
                if (!(this.a instanceof NoOpService)) {
                    Logger a2 = ServiceManager.logger;
                    Level level = Level.SEVERE;
                    a2.log(level, "Service " + this.a + " has failed in the " + state + " state.", th);
                }
                serviceManagerState.a(this.a, state, Service.State.FAILED);
            }
        }

        public void running() {
            ServiceManagerState serviceManagerState = (ServiceManagerState) this.b.get();
            if (serviceManagerState != null) {
                serviceManagerState.a(this.a, Service.State.STARTING, Service.State.RUNNING);
            }
        }

        public void starting() {
            ServiceManagerState serviceManagerState = (ServiceManagerState) this.b.get();
            if (serviceManagerState != null) {
                serviceManagerState.a(this.a, Service.State.NEW, Service.State.STARTING);
                if (!(this.a instanceof NoOpService)) {
                    ServiceManager.logger.log(Level.FINE, "Starting {0}.", this.a);
                }
            }
        }

        public void stopping(Service.State state) {
            ServiceManagerState serviceManagerState = (ServiceManagerState) this.b.get();
            if (serviceManagerState != null) {
                serviceManagerState.a(this.a, state, Service.State.STOPPING);
            }
        }

        public void terminated(Service.State state) {
            ServiceManagerState serviceManagerState = (ServiceManagerState) this.b.get();
            if (serviceManagerState != null) {
                if (!(this.a instanceof NoOpService)) {
                    ServiceManager.logger.log(Level.FINE, "Service {0} has terminated. Previous state was: {1}", new Object[]{this.a, state});
                }
                serviceManagerState.a(this.a, state, Service.State.TERMINATED);
            }
        }
    }

    private static final class ServiceManagerState {
        final Monitor a = new Monitor();
        @GuardedBy("monitor")
        final SetMultimap<Service.State, Service> b = MultimapBuilder.enumKeys(Service.State.class).linkedHashSetValues().build();
        @GuardedBy("monitor")
        final Multiset<Service.State> c = this.b.keys();
        @GuardedBy("monitor")
        final Map<Service, Stopwatch> d = Maps.newIdentityHashMap();
        @GuardedBy("monitor")
        boolean e;
        @GuardedBy("monitor")
        boolean f;
        final int g;
        final Monitor.Guard h = new AwaitHealthGuard();
        final Monitor.Guard i = new StoppedGuard();
        @GuardedBy("monitor")
        final List<ListenerCallQueue<Listener>> j = Collections.synchronizedList(new ArrayList());

        final class AwaitHealthGuard extends Monitor.Guard {
            AwaitHealthGuard() {
                super(ServiceManagerState.this.a);
            }

            public boolean isSatisfied() {
                return ServiceManagerState.this.c.count(Service.State.RUNNING) == ServiceManagerState.this.g || ServiceManagerState.this.c.contains(Service.State.STOPPING) || ServiceManagerState.this.c.contains(Service.State.TERMINATED) || ServiceManagerState.this.c.contains(Service.State.FAILED);
            }
        }

        final class StoppedGuard extends Monitor.Guard {
            StoppedGuard() {
                super(ServiceManagerState.this.a);
            }

            public boolean isSatisfied() {
                return ServiceManagerState.this.c.count(Service.State.TERMINATED) + ServiceManagerState.this.c.count(Service.State.FAILED) == ServiceManagerState.this.g;
            }
        }

        ServiceManagerState(ImmutableCollection<Service> immutableCollection) {
            this.g = immutableCollection.size();
            this.b.putAll(Service.State.NEW, immutableCollection);
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.a.enter();
            try {
                if (!this.f) {
                    this.e = true;
                    return;
                }
                ArrayList newArrayList = Lists.newArrayList();
                UnmodifiableIterator<Service> it = d().values().iterator();
                while (it.hasNext()) {
                    Service next = it.next();
                    if (next.state() != Service.State.NEW) {
                        newArrayList.add(next);
                    }
                }
                throw new IllegalArgumentException("Services started transitioning asynchronously before the ServiceManager was constructed: " + newArrayList);
            } finally {
                this.a.leave();
            }
        }

        /* access modifiers changed from: package-private */
        public void a(long j2, TimeUnit timeUnit) {
            this.a.enter();
            try {
                if (this.a.waitForUninterruptibly(this.h, j2, timeUnit)) {
                    i();
                    return;
                }
                throw new TimeoutException("Timeout waiting for the services to become healthy. The following services have not started: " + Multimaps.filterKeys(this.b, Predicates.in(ImmutableSet.of(Service.State.NEW, Service.State.STARTING))));
            } finally {
                this.a.leave();
            }
        }

        /* access modifiers changed from: package-private */
        public void a(Service service) {
            this.a.enter();
            try {
                if (this.d.get(service) == null) {
                    this.d.put(service, Stopwatch.createStarted());
                }
            } finally {
                this.a.leave();
            }
        }

        /* access modifiers changed from: package-private */
        public void a(Service service, Service.State state, Service.State state2) {
            Preconditions.checkNotNull(service);
            Preconditions.checkArgument(state != state2);
            this.a.enter();
            try {
                this.f = true;
                if (this.e) {
                    Preconditions.checkState(this.b.remove(state, service), "Service %s not at the expected location in the state map %s", (Object) service, (Object) state);
                    Preconditions.checkState(this.b.put(state2, service), "Service %s in the state map unexpectedly at %s", (Object) service, (Object) state2);
                    Stopwatch stopwatch = this.d.get(service);
                    if (stopwatch == null) {
                        stopwatch = Stopwatch.createStarted();
                        this.d.put(service, stopwatch);
                    }
                    if (state2.compareTo(Service.State.RUNNING) >= 0 && stopwatch.isRunning()) {
                        stopwatch.stop();
                        if (!(service instanceof NoOpService)) {
                            ServiceManager.logger.log(Level.FINE, "Started {0} in {1}.", new Object[]{service, stopwatch});
                        }
                    }
                    if (state2 == Service.State.FAILED) {
                        b(service);
                    }
                    if (this.c.count(Service.State.RUNNING) == this.g) {
                        g();
                    } else if (this.c.count(Service.State.TERMINATED) + this.c.count(Service.State.FAILED) == this.g) {
                        f();
                    }
                    this.a.leave();
                    h();
                }
            } finally {
                this.a.leave();
                h();
            }
        }

        /* access modifiers changed from: package-private */
        public void a(Listener listener, Executor executor) {
            Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            Preconditions.checkNotNull(executor, "executor");
            this.a.enter();
            try {
                if (!this.i.isSatisfied()) {
                    this.j.add(new ListenerCallQueue(listener, executor));
                }
            } finally {
                this.a.leave();
            }
        }

        /* access modifiers changed from: package-private */
        public void b() {
            this.a.enterWhenUninterruptibly(this.h);
            try {
                i();
            } finally {
                this.a.leave();
            }
        }

        /* access modifiers changed from: package-private */
        public void b(long j2, TimeUnit timeUnit) {
            this.a.enter();
            try {
                if (!this.a.waitForUninterruptibly(this.i, j2, timeUnit)) {
                    throw new TimeoutException("Timeout waiting for the services to stop. The following services have not stopped: " + Multimaps.filterKeys(this.b, Predicates.not(Predicates.in(EnumSet.of(Service.State.TERMINATED, Service.State.FAILED)))));
                }
            } finally {
                this.a.leave();
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("monitor")
        public void b(final Service service) {
            new ListenerCallQueue.Callback<Listener>("failed({service=" + service + "})") {
                /* access modifiers changed from: package-private */
                public void a(Listener listener) {
                    listener.failure(service);
                }
            }.a(this.j);
        }

        /* access modifiers changed from: package-private */
        public void c() {
            this.a.enterWhenUninterruptibly(this.i);
            this.a.leave();
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: package-private */
        public ImmutableMultimap<Service.State, Service> d() {
            ImmutableSetMultimap.Builder builder = ImmutableSetMultimap.builder();
            this.a.enter();
            try {
                for (Map.Entry next : this.b.entries()) {
                    if (!(next.getValue() instanceof NoOpService)) {
                        builder.put(next);
                    }
                }
                this.a.leave();
                return builder.build();
            } catch (Throwable th) {
                this.a.leave();
                throw th;
            }
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: package-private */
        public ImmutableMap<Service, Long> e() {
            this.a.enter();
            try {
                ArrayList newArrayListWithCapacity = Lists.newArrayListWithCapacity(this.d.size());
                for (Map.Entry next : this.d.entrySet()) {
                    Service service = (Service) next.getKey();
                    Stopwatch stopwatch = (Stopwatch) next.getValue();
                    if (!stopwatch.isRunning() && !(service instanceof NoOpService)) {
                        newArrayListWithCapacity.add(Maps.immutableEntry(service, Long.valueOf(stopwatch.elapsed(TimeUnit.MILLISECONDS))));
                    }
                }
                this.a.leave();
                Collections.sort(newArrayListWithCapacity, Ordering.natural().onResultOf(new Function<Map.Entry<Service, Long>, Long>() {
                    public Long apply(Map.Entry<Service, Long> entry) {
                        return entry.getValue();
                    }
                }));
                return ImmutableMap.copyOf(newArrayListWithCapacity);
            } catch (Throwable th) {
                this.a.leave();
                throw th;
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("monitor")
        public void f() {
            ServiceManager.STOPPED_CALLBACK.a(this.j);
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("monitor")
        public void g() {
            ServiceManager.HEALTHY_CALLBACK.a(this.j);
        }

        /* access modifiers changed from: package-private */
        public void h() {
            Preconditions.checkState(!this.a.isOccupiedByCurrentThread(), "It is incorrect to execute listeners with the monitor held.");
            for (int i2 = 0; i2 < this.j.size(); i2++) {
                this.j.get(i2).a();
            }
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("monitor")
        public void i() {
            if (this.c.count(Service.State.RUNNING) != this.g) {
                throw new IllegalStateException("Expected to be healthy after starting. The following services are not running: " + Multimaps.filterKeys(this.b, Predicates.not(Predicates.equalTo(Service.State.RUNNING))));
            }
        }
    }

    public ServiceManager(Iterable<? extends Service> iterable) {
        ImmutableList<E> copyOf = ImmutableList.copyOf(iterable);
        if (copyOf.isEmpty()) {
            logger.log(Level.WARNING, "ServiceManager configured with no services.  Is your application configured properly?", new EmptyServiceManagerWarning());
            copyOf = ImmutableList.of(new NoOpService());
        }
        this.state = new ServiceManagerState(copyOf);
        this.services = copyOf;
        WeakReference weakReference = new WeakReference(this.state);
        UnmodifiableIterator<E> it = copyOf.iterator();
        while (it.hasNext()) {
            Service service = (Service) it.next();
            service.addListener(new ServiceListener(service, weakReference), MoreExecutors.directExecutor());
            Preconditions.checkArgument(service.state() == Service.State.NEW, "Can only manage NEW services, %s", (Object) service);
        }
        this.state.a();
    }

    public void addListener(Listener listener) {
        this.state.a(listener, MoreExecutors.directExecutor());
    }

    public void addListener(Listener listener, Executor executor) {
        this.state.a(listener, executor);
    }

    public void awaitHealthy() {
        this.state.b();
    }

    public void awaitHealthy(long j, TimeUnit timeUnit) {
        this.state.a(j, timeUnit);
    }

    public void awaitStopped() {
        this.state.c();
    }

    public void awaitStopped(long j, TimeUnit timeUnit) {
        this.state.b(j, timeUnit);
    }

    public boolean isHealthy() {
        UnmodifiableIterator<Service> it = this.services.iterator();
        while (it.hasNext()) {
            if (!it.next().isRunning()) {
                return false;
            }
        }
        return true;
    }

    public ImmutableMultimap<Service.State, Service> servicesByState() {
        return this.state.d();
    }

    @CanIgnoreReturnValue
    public ServiceManager startAsync() {
        UnmodifiableIterator<Service> it = this.services.iterator();
        while (it.hasNext()) {
            Service next = it.next();
            Service.State state2 = next.state();
            Preconditions.checkState(state2 == Service.State.NEW, "Service %s is %s, cannot start it.", (Object) next, (Object) state2);
        }
        UnmodifiableIterator<Service> it2 = this.services.iterator();
        while (it2.hasNext()) {
            Service next2 = it2.next();
            try {
                this.state.a(next2);
                next2.startAsync();
            } catch (IllegalStateException e) {
                Logger logger2 = logger;
                Level level = Level.WARNING;
                logger2.log(level, "Unable to start Service " + next2, e);
            }
        }
        return this;
    }

    public ImmutableMap<Service, Long> startupTimes() {
        return this.state.e();
    }

    @CanIgnoreReturnValue
    public ServiceManager stopAsync() {
        UnmodifiableIterator<Service> it = this.services.iterator();
        while (it.hasNext()) {
            it.next().stopAsync();
        }
        return this;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Class<?>) ServiceManager.class).add("services", (Object) Collections2.filter(this.services, Predicates.not(Predicates.instanceOf(NoOpService.class)))).toString();
    }
}

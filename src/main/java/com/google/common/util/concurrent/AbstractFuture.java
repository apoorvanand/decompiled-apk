package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import sun.misc.Unsafe;

@GwtCompatible(emulated = true)
public abstract class AbstractFuture<V> implements ListenableFuture<V> {
    /* access modifiers changed from: private */
    public static final AtomicHelper ATOMIC_HELPER;
    private static final boolean GENERATE_CANCELLATION_CAUSES = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    private static final Object NULL = new Object();
    private static final long SPIN_THRESHOLD_NANOS = 1000;
    private static final Logger log = Logger.getLogger(AbstractFuture.class.getName());
    /* access modifiers changed from: private */
    public volatile Listener listeners;
    /* access modifiers changed from: private */
    public volatile Object value;
    /* access modifiers changed from: private */
    public volatile Waiter waiters;

    private static abstract class AtomicHelper {
        private AtomicHelper() {
        }

        /* access modifiers changed from: package-private */
        public abstract void a(Waiter waiter, Waiter waiter2);

        /* access modifiers changed from: package-private */
        public abstract void a(Waiter waiter, Thread thread);

        /* access modifiers changed from: package-private */
        public abstract boolean a(AbstractFuture<?> abstractFuture, Listener listener, Listener listener2);

        /* access modifiers changed from: package-private */
        public abstract boolean a(AbstractFuture<?> abstractFuture, Waiter waiter, Waiter waiter2);

        /* access modifiers changed from: package-private */
        public abstract boolean a(AbstractFuture<?> abstractFuture, Object obj, Object obj2);
    }

    private static final class Cancellation {
        final boolean a;
        @Nullable
        final Throwable b;

        Cancellation(boolean z, @Nullable Throwable th) {
            this.a = z;
            this.b = th;
        }
    }

    private static final class Failure {
        static final Failure a = new Failure(new Throwable("Failure occurred while trying to finish a future.") {
            public synchronized Throwable fillInStackTrace() {
                return this;
            }
        });
        final Throwable b;

        Failure(Throwable th) {
            this.b = (Throwable) Preconditions.checkNotNull(th);
        }
    }

    private static final class Listener {
        static final Listener a = new Listener((Runnable) null, (Executor) null);
        final Runnable b;
        final Executor c;
        @Nullable
        Listener d;

        Listener(Runnable runnable, Executor executor) {
            this.b = runnable;
            this.c = executor;
        }
    }

    private static final class SafeAtomicHelper extends AtomicHelper {
        final AtomicReferenceFieldUpdater<Waiter, Thread> a;
        final AtomicReferenceFieldUpdater<Waiter, Waiter> b;
        final AtomicReferenceFieldUpdater<AbstractFuture, Waiter> c;
        final AtomicReferenceFieldUpdater<AbstractFuture, Listener> d;
        final AtomicReferenceFieldUpdater<AbstractFuture, Object> e;

        SafeAtomicHelper(AtomicReferenceFieldUpdater<Waiter, Thread> atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater<Waiter, Waiter> atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater<AbstractFuture, Waiter> atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater<AbstractFuture, Listener> atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater<AbstractFuture, Object> atomicReferenceFieldUpdater5) {
            super();
            this.a = atomicReferenceFieldUpdater;
            this.b = atomicReferenceFieldUpdater2;
            this.c = atomicReferenceFieldUpdater3;
            this.d = atomicReferenceFieldUpdater4;
            this.e = atomicReferenceFieldUpdater5;
        }

        /* access modifiers changed from: package-private */
        public void a(Waiter waiter, Waiter waiter2) {
            this.b.lazySet(waiter, waiter2);
        }

        /* access modifiers changed from: package-private */
        public void a(Waiter waiter, Thread thread) {
            this.a.lazySet(waiter, thread);
        }

        /* access modifiers changed from: package-private */
        public boolean a(AbstractFuture<?> abstractFuture, Listener listener, Listener listener2) {
            return this.d.compareAndSet(abstractFuture, listener, listener2);
        }

        /* access modifiers changed from: package-private */
        public boolean a(AbstractFuture<?> abstractFuture, Waiter waiter, Waiter waiter2) {
            return this.c.compareAndSet(abstractFuture, waiter, waiter2);
        }

        /* access modifiers changed from: package-private */
        public boolean a(AbstractFuture<?> abstractFuture, Object obj, Object obj2) {
            return this.e.compareAndSet(abstractFuture, obj, obj2);
        }
    }

    private static final class SetFuture<V> implements Runnable {
        final AbstractFuture<V> a;
        final ListenableFuture<? extends V> b;

        SetFuture(AbstractFuture<V> abstractFuture, ListenableFuture<? extends V> listenableFuture) {
            this.a = abstractFuture;
            this.b = listenableFuture;
        }

        public void run() {
            if (this.a.value == this) {
                if (AbstractFuture.ATOMIC_HELPER.a((AbstractFuture<?>) this.a, (Object) this, AbstractFuture.getFutureValue(this.b))) {
                    AbstractFuture.complete(this.a);
                }
            }
        }
    }

    private static final class SynchronizedHelper extends AtomicHelper {
        private SynchronizedHelper() {
            super();
        }

        /* access modifiers changed from: package-private */
        public void a(Waiter waiter, Waiter waiter2) {
            waiter.c = waiter2;
        }

        /* access modifiers changed from: package-private */
        public void a(Waiter waiter, Thread thread) {
            waiter.b = thread;
        }

        /* access modifiers changed from: package-private */
        public boolean a(AbstractFuture<?> abstractFuture, Listener listener, Listener listener2) {
            synchronized (abstractFuture) {
                if (abstractFuture.listeners != listener) {
                    return false;
                }
                Listener unused = abstractFuture.listeners = listener2;
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean a(AbstractFuture<?> abstractFuture, Waiter waiter, Waiter waiter2) {
            synchronized (abstractFuture) {
                if (abstractFuture.waiters != waiter) {
                    return false;
                }
                Waiter unused = abstractFuture.waiters = waiter2;
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean a(AbstractFuture<?> abstractFuture, Object obj, Object obj2) {
            synchronized (abstractFuture) {
                if (abstractFuture.value != obj) {
                    return false;
                }
                Object unused = abstractFuture.value = obj2;
                return true;
            }
        }
    }

    static abstract class TrustedFuture<V> extends AbstractFuture<V> {
        TrustedFuture() {
        }

        public final void addListener(Runnable runnable, Executor executor) {
            AbstractFuture.super.addListener(runnable, executor);
        }

        @CanIgnoreReturnValue
        public final boolean cancel(boolean z) {
            return AbstractFuture.super.cancel(z);
        }

        @CanIgnoreReturnValue
        public final V get() {
            return AbstractFuture.super.get();
        }

        @CanIgnoreReturnValue
        public final V get(long j, TimeUnit timeUnit) {
            return AbstractFuture.super.get(j, timeUnit);
        }

        public final boolean isCancelled() {
            return AbstractFuture.super.isCancelled();
        }

        public final boolean isDone() {
            return AbstractFuture.super.isDone();
        }
    }

    private static final class UnsafeAtomicHelper extends AtomicHelper {
        static final Unsafe a;
        static final long b;
        static final long c;
        static final long d;
        static final long e;
        static final long f;

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x005f, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x006b, code lost:
            throw new java.lang.RuntimeException("Could not initialize intrinsics", r0.getCause());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:3:?, code lost:
            r0 = (sun.misc.Unsafe) java.security.AccessController.doPrivileged(new com.google.common.util.concurrent.AbstractFuture.UnsafeAtomicHelper.AnonymousClass1());
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0005 */
        static {
            /*
                sun.misc.Unsafe r0 = sun.misc.Unsafe.getUnsafe()     // Catch:{ SecurityException -> 0x0005 }
                goto L_0x0010
            L_0x0005:
                com.google.common.util.concurrent.AbstractFuture$UnsafeAtomicHelper$1 r0 = new com.google.common.util.concurrent.AbstractFuture$UnsafeAtomicHelper$1     // Catch:{ PrivilegedActionException -> 0x005f }
                r0.<init>()     // Catch:{ PrivilegedActionException -> 0x005f }
                java.lang.Object r0 = java.security.AccessController.doPrivileged(r0)     // Catch:{ PrivilegedActionException -> 0x005f }
                sun.misc.Unsafe r0 = (sun.misc.Unsafe) r0     // Catch:{ PrivilegedActionException -> 0x005f }
            L_0x0010:
                java.lang.Class<com.google.common.util.concurrent.AbstractFuture> r1 = com.google.common.util.concurrent.AbstractFuture.class
                java.lang.String r2 = "waiters"
                java.lang.reflect.Field r2 = r1.getDeclaredField(r2)     // Catch:{ Exception -> 0x0055 }
                long r2 = r0.objectFieldOffset(r2)     // Catch:{ Exception -> 0x0055 }
                c = r2     // Catch:{ Exception -> 0x0055 }
                java.lang.String r2 = "listeners"
                java.lang.reflect.Field r2 = r1.getDeclaredField(r2)     // Catch:{ Exception -> 0x0055 }
                long r2 = r0.objectFieldOffset(r2)     // Catch:{ Exception -> 0x0055 }
                b = r2     // Catch:{ Exception -> 0x0055 }
                java.lang.String r2 = "value"
                java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ Exception -> 0x0055 }
                long r1 = r0.objectFieldOffset(r1)     // Catch:{ Exception -> 0x0055 }
                d = r1     // Catch:{ Exception -> 0x0055 }
                java.lang.Class<com.google.common.util.concurrent.AbstractFuture$Waiter> r1 = com.google.common.util.concurrent.AbstractFuture.Waiter.class
                java.lang.String r2 = "b"
                java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ Exception -> 0x0055 }
                long r1 = r0.objectFieldOffset(r1)     // Catch:{ Exception -> 0x0055 }
                e = r1     // Catch:{ Exception -> 0x0055 }
                java.lang.Class<com.google.common.util.concurrent.AbstractFuture$Waiter> r1 = com.google.common.util.concurrent.AbstractFuture.Waiter.class
                java.lang.String r2 = "c"
                java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ Exception -> 0x0055 }
                long r1 = r0.objectFieldOffset(r1)     // Catch:{ Exception -> 0x0055 }
                f = r1     // Catch:{ Exception -> 0x0055 }
                a = r0     // Catch:{ Exception -> 0x0055 }
                return
            L_0x0055:
                r0 = move-exception
                com.google.common.base.Throwables.throwIfUnchecked(r0)
                java.lang.RuntimeException r1 = new java.lang.RuntimeException
                r1.<init>(r0)
                throw r1
            L_0x005f:
                r0 = move-exception
                java.lang.RuntimeException r1 = new java.lang.RuntimeException
                java.lang.Throwable r0 = r0.getCause()
                java.lang.String r2 = "Could not initialize intrinsics"
                r1.<init>(r2, r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.AbstractFuture.UnsafeAtomicHelper.<clinit>():void");
        }

        private UnsafeAtomicHelper() {
            super();
        }

        /* access modifiers changed from: package-private */
        public void a(Waiter waiter, Waiter waiter2) {
            a.putObject(waiter, f, waiter2);
        }

        /* access modifiers changed from: package-private */
        public void a(Waiter waiter, Thread thread) {
            a.putObject(waiter, e, thread);
        }

        /* access modifiers changed from: package-private */
        public boolean a(AbstractFuture<?> abstractFuture, Listener listener, Listener listener2) {
            return a.compareAndSwapObject(abstractFuture, b, listener, listener2);
        }

        /* access modifiers changed from: package-private */
        public boolean a(AbstractFuture<?> abstractFuture, Waiter waiter, Waiter waiter2) {
            return a.compareAndSwapObject(abstractFuture, c, waiter, waiter2);
        }

        /* access modifiers changed from: package-private */
        public boolean a(AbstractFuture<?> abstractFuture, Object obj, Object obj2) {
            return a.compareAndSwapObject(abstractFuture, d, obj, obj2);
        }
    }

    private static final class Waiter {
        static final Waiter a = new Waiter(false);
        @Nullable
        volatile Thread b;
        @Nullable
        volatile Waiter c;

        Waiter() {
            AbstractFuture.ATOMIC_HELPER.a(this, Thread.currentThread());
        }

        Waiter(boolean z) {
        }

        /* access modifiers changed from: package-private */
        public void a() {
            Thread thread = this.b;
            if (thread != null) {
                this.b = null;
                LockSupport.unpark(thread);
            }
        }

        /* access modifiers changed from: package-private */
        public void a(Waiter waiter) {
            AbstractFuture.ATOMIC_HELPER.a(this, waiter);
        }
    }

    static {
        AtomicHelper atomicHelper;
        try {
            atomicHelper = new UnsafeAtomicHelper();
        } catch (Throwable th) {
            log.log(Level.SEVERE, "UnsafeAtomicHelper is broken!", th);
            log.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
            atomicHelper = new SynchronizedHelper();
        }
        ATOMIC_HELPER = atomicHelper;
        Class<LockSupport> cls = LockSupport.class;
    }

    protected AbstractFuture() {
    }

    private static CancellationException cancellationExceptionWithCause(@Nullable String str, @Nullable Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    private Listener clearListeners(Listener listener) {
        Listener listener2;
        do {
            listener2 = this.listeners;
        } while (!ATOMIC_HELPER.a((AbstractFuture<?>) this, listener2, Listener.a));
        Listener listener3 = listener2;
        Listener listener4 = listener;
        Listener listener5 = listener3;
        while (listener5 != null) {
            Listener listener6 = listener5.d;
            listener5.d = listener4;
            listener4 = listener5;
            listener5 = listener6;
        }
        return listener4;
    }

    /* access modifiers changed from: private */
    public static void complete(AbstractFuture<?> abstractFuture) {
        Listener listener = null;
        AbstractFuture<V> abstractFuture2 = abstractFuture;
        while (true) {
            abstractFuture2.releaseWaiters();
            abstractFuture2.a();
            Listener clearListeners = abstractFuture2.clearListeners(listener);
            while (true) {
                if (clearListeners != null) {
                    listener = clearListeners.d;
                    Runnable runnable = clearListeners.b;
                    if (runnable instanceof SetFuture) {
                        SetFuture setFuture = (SetFuture) runnable;
                        AbstractFuture<V> abstractFuture3 = setFuture.a;
                        if (abstractFuture3.value == setFuture) {
                            if (ATOMIC_HELPER.a((AbstractFuture<?>) abstractFuture3, (Object) setFuture, getFutureValue(setFuture.b))) {
                                abstractFuture2 = abstractFuture3;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        executeListener(runnable, clearListeners.c);
                    }
                    clearListeners = listener;
                } else {
                    return;
                }
            }
        }
    }

    private static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = log;
            Level level = Level.SEVERE;
            logger.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, e);
        }
    }

    private V getDoneValue(Object obj) {
        if (obj instanceof Cancellation) {
            throw cancellationExceptionWithCause("Task was cancelled.", ((Cancellation) obj).b);
        } else if (obj instanceof Failure) {
            throw new ExecutionException(((Failure) obj).b);
        } else if (obj == NULL) {
            return null;
        } else {
            return obj;
        }
    }

    /* access modifiers changed from: private */
    public static Object getFutureValue(ListenableFuture<?> listenableFuture) {
        if (listenableFuture instanceof TrustedFuture) {
            return ((AbstractFuture) listenableFuture).value;
        }
        try {
            Object done = Futures.getDone(listenableFuture);
            if (done == null) {
                done = NULL;
            }
            return done;
        } catch (ExecutionException e) {
            return new Failure(e.getCause());
        } catch (CancellationException e2) {
            return new Cancellation(false, e2);
        } catch (Throwable th) {
            return new Failure(th);
        }
    }

    private void releaseWaiters() {
        Waiter waiter;
        do {
            waiter = this.waiters;
        } while (!ATOMIC_HELPER.a((AbstractFuture<?>) this, waiter, Waiter.a));
        while (waiter != null) {
            waiter.a();
            waiter = waiter.c;
        }
    }

    private void removeWaiter(Waiter waiter) {
        waiter.b = null;
        while (true) {
            Waiter waiter2 = this.waiters;
            if (waiter2 != Waiter.a) {
                Waiter waiter3 = null;
                while (waiter2 != null) {
                    Waiter waiter4 = waiter2.c;
                    if (waiter2.b != null) {
                        waiter3 = waiter2;
                    } else if (waiter3 != null) {
                        waiter3.c = waiter4;
                        if (waiter3.b == null) {
                        }
                    } else if (!ATOMIC_HELPER.a((AbstractFuture<?>) this, waiter2, waiter4)) {
                    }
                    waiter2 = waiter4;
                }
                return;
            }
            return;
        }
    }

    /* access modifiers changed from: protected */
    @Beta
    public void a() {
    }

    /* access modifiers changed from: package-private */
    public final void a(@Nullable Future<?> future) {
        if ((future != null) && isCancelled()) {
            future.cancel(c());
        }
    }

    public void addListener(Runnable runnable, Executor executor) {
        Preconditions.checkNotNull(runnable, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        Listener listener = this.listeners;
        if (listener != Listener.a) {
            Listener listener2 = new Listener(runnable, executor);
            do {
                listener2.d = listener;
                if (!ATOMIC_HELPER.a((AbstractFuture<?>) this, listener, listener2)) {
                    listener = this.listeners;
                } else {
                    return;
                }
            } while (listener != Listener.a);
        }
        executeListener(runnable, executor);
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    /* access modifiers changed from: protected */
    public final boolean c() {
        Object obj = this.value;
        return (obj instanceof Cancellation) && ((Cancellation) obj).a;
    }

    @CanIgnoreReturnValue
    public boolean cancel(boolean z) {
        Object obj = this.value;
        if (!(obj == null) && !(obj instanceof SetFuture)) {
            return false;
        }
        Cancellation cancellation = new Cancellation(z, GENERATE_CANCELLATION_CAUSES ? new CancellationException("Future.cancel() was called.") : null);
        boolean z2 = false;
        Object obj2 = obj;
        AbstractFuture abstractFuture = this;
        while (true) {
            if (ATOMIC_HELPER.a((AbstractFuture<?>) abstractFuture, obj2, (Object) cancellation)) {
                if (z) {
                    abstractFuture.b();
                }
                complete(abstractFuture);
                if (!(obj2 instanceof SetFuture)) {
                    return true;
                }
                ListenableFuture<? extends V> listenableFuture = ((SetFuture) obj2).b;
                if (listenableFuture instanceof TrustedFuture) {
                    abstractFuture = (AbstractFuture) listenableFuture;
                    obj2 = abstractFuture.value;
                    if (!(obj2 == null) && !(obj2 instanceof SetFuture)) {
                        return true;
                    }
                    z2 = true;
                } else {
                    listenableFuture.cancel(z);
                    return true;
                }
            } else {
                obj2 = abstractFuture.value;
                if (!(obj2 instanceof SetFuture)) {
                    return z2;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final Throwable d() {
        return ((Failure) this.value).b;
    }

    @CanIgnoreReturnValue
    public V get() {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.value;
            if ((obj2 != null) && (!(obj2 instanceof SetFuture))) {
                return getDoneValue(obj2);
            }
            Waiter waiter = this.waiters;
            if (waiter != Waiter.a) {
                Waiter waiter2 = new Waiter();
                do {
                    waiter2.a(waiter);
                    if (ATOMIC_HELPER.a((AbstractFuture<?>) this, waiter, waiter2)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.value;
                            } else {
                                removeWaiter(waiter2);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof SetFuture))));
                        return getDoneValue(obj);
                    }
                    waiter = this.waiters;
                } while (waiter != Waiter.a);
            }
            return getDoneValue(this.value);
        }
        throw new InterruptedException();
    }

    @CanIgnoreReturnValue
    public V get(long j, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j);
        if (!Thread.interrupted()) {
            Object obj = this.value;
            if ((obj != null) && (!(obj instanceof SetFuture))) {
                return getDoneValue(obj);
            }
            long nanoTime = nanos > 0 ? System.nanoTime() + nanos : 0;
            if (nanos >= SPIN_THRESHOLD_NANOS) {
                Waiter waiter = this.waiters;
                if (waiter != Waiter.a) {
                    Waiter waiter2 = new Waiter();
                    do {
                        waiter2.a(waiter);
                        if (ATOMIC_HELPER.a((AbstractFuture<?>) this, waiter, waiter2)) {
                            do {
                                LockSupport.parkNanos(this, nanos);
                                if (!Thread.interrupted()) {
                                    Object obj2 = this.value;
                                    if ((obj2 != null) && (!(obj2 instanceof SetFuture))) {
                                        return getDoneValue(obj2);
                                    }
                                    nanos = nanoTime - System.nanoTime();
                                } else {
                                    removeWaiter(waiter2);
                                    throw new InterruptedException();
                                }
                            } while (nanos >= SPIN_THRESHOLD_NANOS);
                            removeWaiter(waiter2);
                        } else {
                            waiter = this.waiters;
                        }
                    } while (waiter != Waiter.a);
                }
                return getDoneValue(this.value);
            }
            while (nanos > 0) {
                Object obj3 = this.value;
                if ((obj3 != null) && (!(obj3 instanceof SetFuture))) {
                    return getDoneValue(obj3);
                }
                if (!Thread.interrupted()) {
                    nanos = nanoTime - System.nanoTime();
                } else {
                    throw new InterruptedException();
                }
            }
            throw new TimeoutException();
        }
        throw new InterruptedException();
    }

    public boolean isCancelled() {
        return this.value instanceof Cancellation;
    }

    public boolean isDone() {
        Object obj = this.value;
        boolean z = true;
        boolean z2 = obj != null;
        if (obj instanceof SetFuture) {
            z = false;
        }
        return z2 & z;
    }

    /* access modifiers changed from: protected */
    @CanIgnoreReturnValue
    public boolean set(@Nullable V v) {
        if (v == null) {
            v = NULL;
        }
        if (!ATOMIC_HELPER.a((AbstractFuture<?>) this, (Object) null, (Object) v)) {
            return false;
        }
        complete(this);
        return true;
    }

    /* access modifiers changed from: protected */
    @CanIgnoreReturnValue
    public boolean setException(Throwable th) {
        if (!ATOMIC_HELPER.a((AbstractFuture<?>) this, (Object) null, (Object) new Failure((Throwable) Preconditions.checkNotNull(th)))) {
            return false;
        }
        complete(this);
        return true;
    }

    /* access modifiers changed from: protected */
    @CanIgnoreReturnValue
    @Beta
    public boolean setFuture(ListenableFuture<? extends V> listenableFuture) {
        SetFuture setFuture;
        Failure failure;
        Preconditions.checkNotNull(listenableFuture);
        Object obj = this.value;
        if (obj == null) {
            if (listenableFuture.isDone()) {
                if (!ATOMIC_HELPER.a((AbstractFuture<?>) this, (Object) null, getFutureValue(listenableFuture))) {
                    return false;
                }
                complete(this);
                return true;
            }
            setFuture = new SetFuture(this, listenableFuture);
            if (ATOMIC_HELPER.a((AbstractFuture<?>) this, (Object) null, (Object) setFuture)) {
                try {
                    listenableFuture.addListener(setFuture, MoreExecutors.directExecutor());
                } catch (Throwable unused) {
                    failure = Failure.a;
                }
                return true;
            }
            obj = this.value;
        }
        if (obj instanceof Cancellation) {
            listenableFuture.cancel(((Cancellation) obj).a);
        }
        return false;
        ATOMIC_HELPER.a((AbstractFuture<?>) this, (Object) setFuture, (Object) failure);
        return true;
    }
}

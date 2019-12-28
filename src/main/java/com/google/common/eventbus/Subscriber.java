package com.google.common.eventbus;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.j2objc.annotations.Weak;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

class Subscriber {
    @VisibleForTesting
    final Object a;
    /* access modifiers changed from: private */
    @Weak
    public EventBus bus;
    private final Executor executor;
    private final Method method;

    @VisibleForTesting
    static final class SynchronizedSubscriber extends Subscriber {
        private SynchronizedSubscriber(EventBus eventBus, Object obj, Method method) {
            super(eventBus, obj, method);
        }

        /* access modifiers changed from: package-private */
        public void b(Object obj) {
            synchronized (this) {
                Subscriber.super.b(obj);
            }
        }
    }

    private Subscriber(EventBus eventBus, Object obj, Method method2) {
        this.bus = eventBus;
        this.a = Preconditions.checkNotNull(obj);
        this.method = method2;
        method2.setAccessible(true);
        this.executor = eventBus.a();
    }

    static Subscriber a(EventBus eventBus, Object obj, Method method2) {
        return isDeclaredThreadSafe(method2) ? new Subscriber(eventBus, obj, method2) : new SynchronizedSubscriber(eventBus, obj, method2);
    }

    /* access modifiers changed from: private */
    public SubscriberExceptionContext context(Object obj) {
        return new SubscriberExceptionContext(this.bus, obj, this.a, this.method);
    }

    private static boolean isDeclaredThreadSafe(Method method2) {
        return method2.getAnnotation(AllowConcurrentEvents.class) != null;
    }

    /* access modifiers changed from: package-private */
    public final void a(final Object obj) {
        this.executor.execute(new Runnable() {
            public void run() {
                try {
                    Subscriber.this.b(obj);
                } catch (InvocationTargetException e) {
                    Subscriber.this.bus.a(e.getCause(), Subscriber.this.context(obj));
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void b(Object obj) {
        try {
            this.method.invoke(this.a, new Object[]{Preconditions.checkNotNull(obj)});
        } catch (IllegalArgumentException e) {
            throw new Error("Method rejected target/argument: " + obj, e);
        } catch (IllegalAccessException e2) {
            throw new Error("Method became inaccessible: " + obj, e2);
        } catch (InvocationTargetException e3) {
            if (e3.getCause() instanceof Error) {
                throw ((Error) e3.getCause());
            }
            throw e3;
        }
    }

    public final boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Subscriber)) {
            return false;
        }
        Subscriber subscriber = (Subscriber) obj;
        return this.a == subscriber.a && this.method.equals(subscriber.method);
    }

    public final int hashCode() {
        return ((this.method.hashCode() + 31) * 31) + System.identityHashCode(this.a);
    }
}

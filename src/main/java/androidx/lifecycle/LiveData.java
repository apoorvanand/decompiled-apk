package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;
import java.util.Iterator;
import java.util.Map;

public abstract class LiveData<T> {
    static final Object c = new Object();
    final Object b = new Object();
    int d = 0;
    volatile Object e;
    private volatile Object mData;
    private boolean mDispatchInvalidated;
    private boolean mDispatchingValue;
    private SafeIterableMap<Observer<? super T>, LiveData<T>.ObserverWrapper> mObservers = new SafeIterableMap<>();
    private final Runnable mPostValueRunnable;
    private int mVersion;

    private class AlwaysActiveObserver extends LiveData<T>.ObserverWrapper {
        AlwaysActiveObserver(Observer<? super T> observer) {
            super(observer);
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return true;
        }
    }

    class LifecycleBoundObserver extends LiveData<T>.ObserverWrapper implements GenericLifecycleObserver {
        @NonNull
        final LifecycleOwner a;

        LifecycleBoundObserver(LifecycleOwner lifecycleOwner, @NonNull Observer<? super T> observer) {
            super(observer);
            this.a = lifecycleOwner;
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            return this.a.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
        }

        /* access modifiers changed from: package-private */
        public boolean a(LifecycleOwner lifecycleOwner) {
            return this.a == lifecycleOwner;
        }

        /* access modifiers changed from: package-private */
        public void b() {
            this.a.getLifecycle().removeObserver(this);
        }

        public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            if (this.a.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
                LiveData.this.removeObserver(this.c);
            } else {
                a(a());
            }
        }
    }

    private abstract class ObserverWrapper {
        final Observer<? super T> c;
        boolean d;
        int e = -1;

        ObserverWrapper(Observer<? super T> observer) {
            this.c = observer;
        }

        /* access modifiers changed from: package-private */
        public void a(boolean z) {
            if (z != this.d) {
                this.d = z;
                int i = 1;
                boolean z2 = LiveData.this.d == 0;
                LiveData liveData = LiveData.this;
                int i2 = liveData.d;
                if (!this.d) {
                    i = -1;
                }
                liveData.d = i2 + i;
                if (z2 && this.d) {
                    LiveData.this.a();
                }
                if (LiveData.this.d == 0 && !this.d) {
                    LiveData.this.c();
                }
                if (this.d) {
                    LiveData.this.a(this);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public abstract boolean a();

        /* access modifiers changed from: package-private */
        public boolean a(LifecycleOwner lifecycleOwner) {
            return false;
        }

        /* access modifiers changed from: package-private */
        public void b() {
        }
    }

    public LiveData() {
        Object obj = c;
        this.mData = obj;
        this.e = obj;
        this.mVersion = -1;
        this.mPostValueRunnable = new Runnable() {
            public void run() {
                Object obj;
                synchronized (LiveData.this.b) {
                    obj = LiveData.this.e;
                    LiveData.this.e = LiveData.c;
                }
                LiveData.this.setValue(obj);
            }
        };
    }

    private static void assertMainThread(String str) {
        if (!ArchTaskExecutor.getInstance().isMainThread()) {
            throw new IllegalStateException("Cannot invoke " + str + " on a background" + " thread");
        }
    }

    private void considerNotify(LiveData<T>.ObserverWrapper observerWrapper) {
        if (observerWrapper.d) {
            if (!observerWrapper.a()) {
                observerWrapper.a(false);
                return;
            }
            int i = observerWrapper.e;
            int i2 = this.mVersion;
            if (i < i2) {
                observerWrapper.e = i2;
                observerWrapper.c.onChanged(this.mData);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
    }

    /* access modifiers changed from: package-private */
    public void a(@Nullable LiveData<T>.ObserverWrapper observerWrapper) {
        if (this.mDispatchingValue) {
            this.mDispatchInvalidated = true;
            return;
        }
        this.mDispatchingValue = true;
        do {
            this.mDispatchInvalidated = false;
            if (observerWrapper == null) {
                SafeIterableMap<K, V>.IteratorWithAdditions iteratorWithAdditions = this.mObservers.iteratorWithAdditions();
                while (iteratorWithAdditions.hasNext()) {
                    considerNotify((ObserverWrapper) ((Map.Entry) iteratorWithAdditions.next()).getValue());
                    if (this.mDispatchInvalidated) {
                        break;
                    }
                }
            } else {
                considerNotify(observerWrapper);
                observerWrapper = null;
            }
        } while (this.mDispatchInvalidated);
        this.mDispatchingValue = false;
    }

    /* access modifiers changed from: package-private */
    public int b() {
        return this.mVersion;
    }

    /* access modifiers changed from: protected */
    public void c() {
    }

    @Nullable
    public T getValue() {
        T t = this.mData;
        if (t != c) {
            return t;
        }
        return null;
    }

    public boolean hasActiveObservers() {
        return this.d > 0;
    }

    public boolean hasObservers() {
        return this.mObservers.size() > 0;
    }

    @MainThread
    public void observe(@NonNull LifecycleOwner lifecycleOwner, @NonNull Observer<? super T> observer) {
        assertMainThread("observe");
        if (lifecycleOwner.getLifecycle().getCurrentState() != Lifecycle.State.DESTROYED) {
            LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(lifecycleOwner, observer);
            ObserverWrapper putIfAbsent = this.mObservers.putIfAbsent(observer, lifecycleBoundObserver);
            if (putIfAbsent != null && !putIfAbsent.a(lifecycleOwner)) {
                throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            } else if (putIfAbsent == null) {
                lifecycleOwner.getLifecycle().addObserver(lifecycleBoundObserver);
            }
        }
    }

    @MainThread
    public void observeForever(@NonNull Observer<? super T> observer) {
        assertMainThread("observeForever");
        AlwaysActiveObserver alwaysActiveObserver = new AlwaysActiveObserver(observer);
        ObserverWrapper putIfAbsent = this.mObservers.putIfAbsent(observer, alwaysActiveObserver);
        if (putIfAbsent != null && (putIfAbsent instanceof LifecycleBoundObserver)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        } else if (putIfAbsent == null) {
            alwaysActiveObserver.a(true);
        }
    }

    /* access modifiers changed from: protected */
    public void postValue(T t) {
        boolean z;
        synchronized (this.b) {
            z = this.e == c;
            this.e = t;
        }
        if (z) {
            ArchTaskExecutor.getInstance().postToMainThread(this.mPostValueRunnable);
        }
    }

    @MainThread
    public void removeObserver(@NonNull Observer<? super T> observer) {
        assertMainThread("removeObserver");
        ObserverWrapper remove = this.mObservers.remove(observer);
        if (remove != null) {
            remove.b();
            remove.a(false);
        }
    }

    @MainThread
    public void removeObservers(@NonNull LifecycleOwner lifecycleOwner) {
        assertMainThread("removeObservers");
        Iterator<Map.Entry<Observer<? super T>, LiveData<T>.ObserverWrapper>> it = this.mObservers.iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (((ObserverWrapper) next.getValue()).a(lifecycleOwner)) {
                removeObserver((Observer) next.getKey());
            }
        }
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void setValue(T t) {
        assertMainThread("setValue");
        this.mVersion++;
        this.mData = t;
        a((LiveData<T>.ObserverWrapper) null);
    }
}

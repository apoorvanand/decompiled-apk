package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Sets;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

@GwtCompatible(emulated = true)
abstract class AggregateFutureState {
    private static final AtomicHelper ATOMIC_HELPER;
    private static final Logger log = Logger.getLogger(AggregateFutureState.class.getName());
    /* access modifiers changed from: private */
    public volatile int remaining;
    /* access modifiers changed from: private */
    public volatile Set<Throwable> seenExceptions = null;

    private static abstract class AtomicHelper {
        private AtomicHelper() {
        }

        /* access modifiers changed from: package-private */
        public abstract int a(AggregateFutureState aggregateFutureState);

        /* access modifiers changed from: package-private */
        public abstract void a(AggregateFutureState aggregateFutureState, Set<Throwable> set, Set<Throwable> set2);
    }

    private static final class SafeAtomicHelper extends AtomicHelper {
        final AtomicReferenceFieldUpdater<AggregateFutureState, Set<Throwable>> a;
        final AtomicIntegerFieldUpdater<AggregateFutureState> b;

        SafeAtomicHelper(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
            super();
            this.a = atomicReferenceFieldUpdater;
            this.b = atomicIntegerFieldUpdater;
        }

        /* access modifiers changed from: package-private */
        public int a(AggregateFutureState aggregateFutureState) {
            return this.b.decrementAndGet(aggregateFutureState);
        }

        /* access modifiers changed from: package-private */
        public void a(AggregateFutureState aggregateFutureState, Set<Throwable> set, Set<Throwable> set2) {
            this.a.compareAndSet(aggregateFutureState, set, set2);
        }
    }

    private static final class SynchronizedAtomicHelper extends AtomicHelper {
        private SynchronizedAtomicHelper() {
            super();
        }

        /* access modifiers changed from: package-private */
        public int a(AggregateFutureState aggregateFutureState) {
            int c;
            synchronized (aggregateFutureState) {
                AggregateFutureState.b(aggregateFutureState);
                c = aggregateFutureState.remaining;
            }
            return c;
        }

        /* access modifiers changed from: package-private */
        public void a(AggregateFutureState aggregateFutureState, Set<Throwable> set, Set<Throwable> set2) {
            synchronized (aggregateFutureState) {
                if (aggregateFutureState.seenExceptions == set) {
                    Set unused = aggregateFutureState.seenExceptions = set2;
                }
            }
        }
    }

    static {
        AtomicHelper atomicHelper;
        try {
            atomicHelper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(AggregateFutureState.class, Set.class, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(AggregateFutureState.class, "remaining"));
        } catch (Throwable th) {
            log.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
            atomicHelper = new SynchronizedAtomicHelper();
        }
        ATOMIC_HELPER = atomicHelper;
    }

    AggregateFutureState(int i) {
        this.remaining = i;
    }

    static /* synthetic */ int b(AggregateFutureState aggregateFutureState) {
        int i = aggregateFutureState.remaining;
        aggregateFutureState.remaining = i - 1;
        return i;
    }

    /* access modifiers changed from: package-private */
    public abstract void a(Set<Throwable> set);

    /* access modifiers changed from: package-private */
    public final Set<Throwable> d() {
        Set<Throwable> set = this.seenExceptions;
        if (set != null) {
            return set;
        }
        Set newConcurrentHashSet = Sets.newConcurrentHashSet();
        a((Set<Throwable>) newConcurrentHashSet);
        ATOMIC_HELPER.a(this, (Set<Throwable>) null, newConcurrentHashSet);
        return this.seenExceptions;
    }

    /* access modifiers changed from: package-private */
    public final int e() {
        return ATOMIC_HELPER.a(this);
    }
}

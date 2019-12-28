package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.annotation.Nullable;

@GwtCompatible
abstract class AggregateFuture<InputT, OutputT> extends AbstractFuture.TrustedFuture<OutputT> {
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(AggregateFuture.class.getName());
    /* access modifiers changed from: private */
    public AggregateFuture<InputT, OutputT>.RunningState runningState;

    abstract class RunningState extends AggregateFutureState implements Runnable {
        private final boolean allMustSucceed;
        private final boolean collectsValues;
        /* access modifiers changed from: private */
        public ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures;

        RunningState(ImmutableCollection<? extends ListenableFuture<? extends InputT>> immutableCollection, boolean z, boolean z2) {
            super(immutableCollection.size());
            this.futures = (ImmutableCollection) Preconditions.checkNotNull(immutableCollection);
            this.allMustSucceed = z;
            this.collectsValues = z2;
        }

        /* access modifiers changed from: private */
        public void decrementCountAndMaybeComplete() {
            int e = e();
            Preconditions.checkState(e >= 0, "Less than 0 remaining futures");
            if (e == 0) {
                processCompleted();
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:10:0x0027  */
        /* JADX WARNING: Removed duplicated region for block: B:13:0x002e  */
        /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void handleException(java.lang.Throwable r7) {
            /*
                r6 = this;
                com.google.common.base.Preconditions.checkNotNull(r7)
                boolean r0 = r6.allMustSucceed
                r1 = 1
                r2 = 0
                if (r0 == 0) goto L_0x001e
                com.google.common.util.concurrent.AggregateFuture r0 = com.google.common.util.concurrent.AggregateFuture.this
                boolean r0 = r0.setException(r7)
                if (r0 == 0) goto L_0x0015
                r6.a()
                goto L_0x001f
            L_0x0015:
                java.util.Set r3 = r6.d()
                boolean r3 = com.google.common.util.concurrent.AggregateFuture.addCausalChain(r3, r7)
                goto L_0x0020
            L_0x001e:
                r0 = 0
            L_0x001f:
                r3 = 1
            L_0x0020:
                boolean r4 = r7 instanceof java.lang.Error
                boolean r5 = r6.allMustSucceed
                if (r0 != 0) goto L_0x0027
                goto L_0x0028
            L_0x0027:
                r1 = 0
            L_0x0028:
                r0 = r5 & r1
                r0 = r0 & r3
                r0 = r0 | r4
                if (r0 == 0) goto L_0x003e
                if (r4 == 0) goto L_0x0033
                java.lang.String r0 = "Input Future failed with Error"
                goto L_0x0035
            L_0x0033:
                java.lang.String r0 = "Got more than one input Future failure. Logging failures after the first"
            L_0x0035:
                java.util.logging.Logger r1 = com.google.common.util.concurrent.AggregateFuture.logger
                java.util.logging.Level r2 = java.util.logging.Level.SEVERE
                r1.log(r2, r0, r7)
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.AggregateFuture.RunningState.handleException(java.lang.Throwable):void");
        }

        /* access modifiers changed from: private */
        public void handleOneInputDone(int i, Future<? extends InputT> future) {
            Throwable th;
            boolean z;
            Object done;
            Preconditions.checkState(this.allMustSucceed || !AggregateFuture.this.isDone() || AggregateFuture.this.isCancelled(), "Future was done before all dependencies completed");
            try {
                Preconditions.checkState(future.isDone(), "Tried to set value from future which is not done");
                if (this.allMustSucceed) {
                    if (future.isCancelled()) {
                        RunningState unused = AggregateFuture.this.runningState = null;
                        AggregateFuture.this.cancel(false);
                        return;
                    }
                    done = Futures.getDone(future);
                    if (this.collectsValues) {
                        z = this.allMustSucceed;
                    } else {
                        return;
                    }
                } else if (this.collectsValues && !future.isCancelled()) {
                    z = this.allMustSucceed;
                    done = Futures.getDone(future);
                } else {
                    return;
                }
                a(z, i, done);
            } catch (ExecutionException e) {
                th = e.getCause();
                handleException(th);
            } catch (Throwable th2) {
                th = th2;
                handleException(th);
            }
        }

        /* access modifiers changed from: private */
        public void init() {
            if (this.futures.isEmpty()) {
                b();
            } else if (this.allMustSucceed) {
                final int i = 0;
                UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = this.futures.iterator();
                while (it.hasNext()) {
                    final ListenableFuture listenableFuture = (ListenableFuture) it.next();
                    listenableFuture.addListener(new Runnable() {
                        public void run() {
                            try {
                                RunningState.this.handleOneInputDone(i, listenableFuture);
                            } finally {
                                RunningState.this.decrementCountAndMaybeComplete();
                            }
                        }
                    }, MoreExecutors.directExecutor());
                    i++;
                }
            } else {
                UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it2 = this.futures.iterator();
                while (it2.hasNext()) {
                    ((ListenableFuture) it2.next()).addListener(this, MoreExecutors.directExecutor());
                }
            }
        }

        private void processCompleted() {
            if (this.collectsValues && (!this.allMustSucceed)) {
                int i = 0;
                UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = this.futures.iterator();
                while (it.hasNext()) {
                    handleOneInputDone(i, (ListenableFuture) it.next());
                    i++;
                }
            }
            b();
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.futures = null;
        }

        /* access modifiers changed from: package-private */
        public final void a(Set<Throwable> set) {
            if (!AggregateFuture.this.isCancelled()) {
                boolean unused = AggregateFuture.addCausalChain(set, AggregateFuture.this.d());
            }
        }

        /* access modifiers changed from: package-private */
        public abstract void a(boolean z, int i, @Nullable InputT inputt);

        /* access modifiers changed from: package-private */
        public abstract void b();

        /* access modifiers changed from: package-private */
        public void c() {
        }

        public final void run() {
            decrementCountAndMaybeComplete();
        }
    }

    AggregateFuture() {
    }

    /* access modifiers changed from: private */
    public static boolean addCausalChain(Set<Throwable> set, Throwable th) {
        while (th != null) {
            if (!set.add(th)) {
                return false;
            }
            th = th.getCause();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public final void a() {
        super.a();
        AggregateFuture<InputT, OutputT>.RunningState runningState2 = this.runningState;
        if (runningState2 != null) {
            this.runningState = null;
            ImmutableCollection a = runningState2.futures;
            boolean c = c();
            if (c()) {
                runningState2.c();
            }
            if (isCancelled() && (a != null)) {
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    ((ListenableFuture) it.next()).cancel(c);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void a(AggregateFuture<InputT, OutputT>.RunningState runningState2) {
        this.runningState = runningState2;
        runningState2.init();
    }
}

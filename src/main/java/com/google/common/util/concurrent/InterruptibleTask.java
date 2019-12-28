package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

@GwtCompatible(emulated = true)
abstract class InterruptibleTask implements Runnable {
    private static final AtomicHelper ATOMIC_HELPER;
    private static final Logger log = Logger.getLogger(InterruptibleTask.class.getName());
    private volatile boolean doneInterrupting;
    /* access modifiers changed from: private */
    public volatile Thread runner;

    private static abstract class AtomicHelper {
        private AtomicHelper() {
        }

        /* access modifiers changed from: package-private */
        public abstract boolean a(InterruptibleTask interruptibleTask, Thread thread, Thread thread2);
    }

    private static final class SafeAtomicHelper extends AtomicHelper {
        final AtomicReferenceFieldUpdater<InterruptibleTask, Thread> a;

        SafeAtomicHelper(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater) {
            super();
            this.a = atomicReferenceFieldUpdater;
        }

        /* access modifiers changed from: package-private */
        public boolean a(InterruptibleTask interruptibleTask, Thread thread, Thread thread2) {
            return this.a.compareAndSet(interruptibleTask, thread, thread2);
        }
    }

    private static final class SynchronizedAtomicHelper extends AtomicHelper {
        private SynchronizedAtomicHelper() {
            super();
        }

        /* access modifiers changed from: package-private */
        public boolean a(InterruptibleTask interruptibleTask, Thread thread, Thread thread2) {
            synchronized (interruptibleTask) {
                if (interruptibleTask.runner == thread) {
                    Thread unused = interruptibleTask.runner = thread2;
                }
            }
            return true;
        }
    }

    static {
        AtomicHelper atomicHelper;
        try {
            atomicHelper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(InterruptibleTask.class, Thread.class, "runner"));
        } catch (Throwable th) {
            log.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
            atomicHelper = new SynchronizedAtomicHelper();
        }
        ATOMIC_HELPER = atomicHelper;
    }

    InterruptibleTask() {
    }

    /* access modifiers changed from: package-private */
    public abstract void b();

    /* access modifiers changed from: package-private */
    public abstract boolean c();

    /* access modifiers changed from: package-private */
    public final void e() {
        Thread thread = this.runner;
        if (thread != null) {
            thread.interrupt();
        }
        this.doneInterrupting = true;
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final void run() {
        /*
            r3 = this;
            com.google.common.util.concurrent.InterruptibleTask$AtomicHelper r0 = ATOMIC_HELPER
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            r2 = 0
            boolean r0 = r0.a(r3, r2, r1)
            if (r0 != 0) goto L_0x000e
            return
        L_0x000e:
            r3.b()     // Catch:{ all -> 0x0020 }
            boolean r0 = r3.c()
            if (r0 == 0) goto L_0x001f
        L_0x0017:
            boolean r0 = r3.doneInterrupting
            if (r0 != 0) goto L_0x001f
            java.lang.Thread.yield()
            goto L_0x0017
        L_0x001f:
            return
        L_0x0020:
            r0 = move-exception
            boolean r1 = r3.c()
            if (r1 == 0) goto L_0x002f
        L_0x0027:
            boolean r1 = r3.doneInterrupting
            if (r1 != 0) goto L_0x002f
            java.lang.Thread.yield()
            goto L_0x0027
        L_0x002f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.InterruptibleTask.run():void");
    }
}

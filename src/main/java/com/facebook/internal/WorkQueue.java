package com.facebook.internal;

import com.facebook.FacebookSdk;
import java.util.concurrent.Executor;

public class WorkQueue {
    public static final int DEFAULT_MAX_CONCURRENT = 8;
    static final /* synthetic */ boolean a = (!WorkQueue.class.desiredAssertionStatus());
    private final Executor executor;
    private final int maxConcurrent;
    /* access modifiers changed from: private */
    public WorkNode pendingJobs;
    private int runningCount;
    private WorkNode runningJobs;
    /* access modifiers changed from: private */
    public final Object workLock;

    public interface WorkItem {
        boolean cancel();

        boolean isRunning();

        void moveToFront();
    }

    private class WorkNode implements WorkItem {
        static final /* synthetic */ boolean a = (!WorkQueue.class.desiredAssertionStatus());
        private final Runnable callback;
        private boolean isRunning;
        private WorkNode next;
        private WorkNode prev;

        WorkNode(Runnable runnable) {
            this.callback = runnable;
        }

        /* access modifiers changed from: package-private */
        public WorkNode a(WorkNode workNode) {
            if (!a && this.next == null) {
                throw new AssertionError();
            } else if (a || this.prev != null) {
                if (workNode == this && (workNode = this.next) == this) {
                    workNode = null;
                }
                WorkNode workNode2 = this.next;
                workNode2.prev = this.prev;
                this.prev.next = workNode2;
                this.prev = null;
                this.next = null;
                return workNode;
            } else {
                throw new AssertionError();
            }
        }

        /* access modifiers changed from: package-private */
        public WorkNode a(WorkNode workNode, boolean z) {
            if (!a && this.next != null) {
                throw new AssertionError();
            } else if (a || this.prev == null) {
                if (workNode == null) {
                    this.prev = this;
                    this.next = this;
                    workNode = this;
                } else {
                    this.next = workNode;
                    this.prev = workNode.prev;
                    WorkNode workNode2 = this.next;
                    this.prev.next = this;
                    workNode2.prev = this;
                }
                return z ? this : workNode;
            } else {
                throw new AssertionError();
            }
        }

        /* access modifiers changed from: package-private */
        public Runnable a() {
            return this.callback;
        }

        /* access modifiers changed from: package-private */
        public void a(boolean z) {
            this.isRunning = z;
        }

        /* access modifiers changed from: package-private */
        public WorkNode b() {
            return this.next;
        }

        /* access modifiers changed from: package-private */
        public void b(boolean z) {
            if (!a && this.prev.next != this) {
                throw new AssertionError();
            } else if (!a && this.next.prev != this) {
                throw new AssertionError();
            } else if (!a && isRunning() != z) {
                throw new AssertionError();
            }
        }

        public boolean cancel() {
            synchronized (WorkQueue.this.workLock) {
                if (isRunning()) {
                    return false;
                }
                WorkNode unused = WorkQueue.this.pendingJobs = a(WorkQueue.this.pendingJobs);
                return true;
            }
        }

        public boolean isRunning() {
            return this.isRunning;
        }

        public void moveToFront() {
            synchronized (WorkQueue.this.workLock) {
                if (!isRunning()) {
                    WorkNode unused = WorkQueue.this.pendingJobs = a(WorkQueue.this.pendingJobs);
                    WorkNode unused2 = WorkQueue.this.pendingJobs = a(WorkQueue.this.pendingJobs, true);
                }
            }
        }
    }

    public WorkQueue() {
        this(8);
    }

    public WorkQueue(int i) {
        this(i, FacebookSdk.getExecutor());
    }

    public WorkQueue(int i, Executor executor2) {
        this.workLock = new Object();
        this.runningJobs = null;
        this.runningCount = 0;
        this.maxConcurrent = i;
        this.executor = executor2;
    }

    private void execute(final WorkNode workNode) {
        this.executor.execute(new Runnable() {
            public void run() {
                try {
                    workNode.a().run();
                } finally {
                    WorkQueue.this.finishItemAndStartNew(workNode);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void finishItemAndStartNew(WorkNode workNode) {
        WorkNode workNode2;
        synchronized (this.workLock) {
            if (workNode != null) {
                this.runningJobs = workNode.a(this.runningJobs);
                this.runningCount--;
            }
            if (this.runningCount < this.maxConcurrent) {
                workNode2 = this.pendingJobs;
                if (workNode2 != null) {
                    this.pendingJobs = workNode2.a(this.pendingJobs);
                    this.runningJobs = workNode2.a(this.runningJobs, false);
                    this.runningCount++;
                    workNode2.a(true);
                }
            } else {
                workNode2 = null;
            }
        }
        if (workNode2 != null) {
            execute(workNode2);
        }
    }

    private void startItem() {
        finishItemAndStartNew((WorkNode) null);
    }

    public WorkItem addActiveWorkItem(Runnable runnable) {
        return addActiveWorkItem(runnable, true);
    }

    public WorkItem addActiveWorkItem(Runnable runnable, boolean z) {
        WorkNode workNode = new WorkNode(runnable);
        synchronized (this.workLock) {
            this.pendingJobs = workNode.a(this.pendingJobs, z);
        }
        startItem();
        return workNode;
    }

    public void validate() {
        synchronized (this.workLock) {
            int i = 0;
            if (this.runningJobs != null) {
                WorkNode workNode = this.runningJobs;
                do {
                    workNode.b(true);
                    i++;
                    workNode = workNode.b();
                } while (workNode != this.runningJobs);
            }
            if (!a) {
                if (this.runningCount != i) {
                    throw new AssertionError();
                }
            }
        }
    }
}

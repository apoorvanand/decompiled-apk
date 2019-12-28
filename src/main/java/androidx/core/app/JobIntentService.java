package androidx.core.app;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class JobIntentService extends Service {
    static final Object h = new Object();
    static final HashMap<ComponentName, WorkEnqueuer> i = new HashMap<>();
    CompatJobEngine a;
    WorkEnqueuer b;
    CommandProcessor c;
    boolean d = false;
    boolean e = false;
    boolean f = false;
    final ArrayList<CompatWorkItem> g;

    final class CommandProcessor extends AsyncTask<Void, Void, Void> {
        CommandProcessor() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            while (true) {
                GenericWorkItem c = JobIntentService.this.c();
                if (c == null) {
                    return null;
                }
                JobIntentService.this.a(c.getIntent());
                c.complete();
            }
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onCancelled(Void voidR) {
            JobIntentService.this.b();
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public void onPostExecute(Void voidR) {
            JobIntentService.this.b();
        }
    }

    interface CompatJobEngine {
        IBinder compatGetBinder();

        GenericWorkItem dequeueWork();
    }

    static final class CompatWorkEnqueuer extends WorkEnqueuer {
        boolean a;
        boolean b;
        private final Context mContext;
        private final PowerManager.WakeLock mLaunchWakeLock;
        private final PowerManager.WakeLock mRunWakeLock;

        CompatWorkEnqueuer(Context context, ComponentName componentName) {
            super(context, componentName);
            this.mContext = context.getApplicationContext();
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            this.mLaunchWakeLock = powerManager.newWakeLock(1, componentName.getClassName() + ":launch");
            this.mLaunchWakeLock.setReferenceCounted(false);
            this.mRunWakeLock = powerManager.newWakeLock(1, componentName.getClassName() + ":run");
            this.mRunWakeLock.setReferenceCounted(false);
        }

        /* access modifiers changed from: package-private */
        public void a(Intent intent) {
            Intent intent2 = new Intent(intent);
            intent2.setComponent(this.c);
            if (this.mContext.startService(intent2) != null) {
                synchronized (this) {
                    if (!this.a) {
                        this.a = true;
                        if (!this.b) {
                            this.mLaunchWakeLock.acquire(60000);
                        }
                    }
                }
            }
        }

        public void serviceProcessingFinished() {
            synchronized (this) {
                if (this.b) {
                    if (this.a) {
                        this.mLaunchWakeLock.acquire(60000);
                    }
                    this.b = false;
                    this.mRunWakeLock.release();
                }
            }
        }

        public void serviceProcessingStarted() {
            synchronized (this) {
                if (!this.b) {
                    this.b = true;
                    this.mRunWakeLock.acquire(600000);
                    this.mLaunchWakeLock.release();
                }
            }
        }

        public void serviceStartReceived() {
            synchronized (this) {
                this.a = false;
            }
        }
    }

    final class CompatWorkItem implements GenericWorkItem {
        final Intent a;
        final int b;

        CompatWorkItem(Intent intent, int i) {
            this.a = intent;
            this.b = i;
        }

        public void complete() {
            JobIntentService.this.stopSelf(this.b);
        }

        public Intent getIntent() {
            return this.a;
        }
    }

    interface GenericWorkItem {
        void complete();

        Intent getIntent();
    }

    @RequiresApi(26)
    static final class JobServiceEngineImpl extends JobServiceEngine implements CompatJobEngine {
        final JobIntentService a;
        final Object b = new Object();
        JobParameters c;

        final class WrapperWorkItem implements GenericWorkItem {
            final JobWorkItem a;

            WrapperWorkItem(JobWorkItem jobWorkItem) {
                this.a = jobWorkItem;
            }

            public void complete() {
                synchronized (JobServiceEngineImpl.this.b) {
                    if (JobServiceEngineImpl.this.c != null) {
                        JobServiceEngineImpl.this.c.completeWork(this.a);
                    }
                }
            }

            public Intent getIntent() {
                return this.a.getIntent();
            }
        }

        JobServiceEngineImpl(JobIntentService jobIntentService) {
            super(jobIntentService);
            this.a = jobIntentService;
        }

        public IBinder compatGetBinder() {
            return getBinder();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
            r1.getIntent().setExtrasClassLoader(r3.a.getClassLoader());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
            return new androidx.core.app.JobIntentService.JobServiceEngineImpl.WrapperWorkItem(r3, r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
            if (r1 == null) goto L_0x0026;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.core.app.JobIntentService.GenericWorkItem dequeueWork() {
            /*
                r3 = this;
                java.lang.Object r0 = r3.b
                monitor-enter(r0)
                android.app.job.JobParameters r1 = r3.c     // Catch:{ all -> 0x0027 }
                r2 = 0
                if (r1 != 0) goto L_0x000a
                monitor-exit(r0)     // Catch:{ all -> 0x0027 }
                return r2
            L_0x000a:
                android.app.job.JobParameters r1 = r3.c     // Catch:{ all -> 0x0027 }
                android.app.job.JobWorkItem r1 = r1.dequeueWork()     // Catch:{ all -> 0x0027 }
                monitor-exit(r0)     // Catch:{ all -> 0x0027 }
                if (r1 == 0) goto L_0x0026
                android.content.Intent r0 = r1.getIntent()
                androidx.core.app.JobIntentService r2 = r3.a
                java.lang.ClassLoader r2 = r2.getClassLoader()
                r0.setExtrasClassLoader(r2)
                androidx.core.app.JobIntentService$JobServiceEngineImpl$WrapperWorkItem r0 = new androidx.core.app.JobIntentService$JobServiceEngineImpl$WrapperWorkItem
                r0.<init>(r1)
                return r0
            L_0x0026:
                return r2
            L_0x0027:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0027 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.JobIntentService.JobServiceEngineImpl.dequeueWork():androidx.core.app.JobIntentService$GenericWorkItem");
        }

        public boolean onStartJob(JobParameters jobParameters) {
            this.c = jobParameters;
            this.a.a(false);
            return true;
        }

        public boolean onStopJob(JobParameters jobParameters) {
            boolean a2 = this.a.a();
            synchronized (this.b) {
                this.c = null;
            }
            return a2;
        }
    }

    @RequiresApi(26)
    static final class JobWorkEnqueuer extends WorkEnqueuer {
        private final JobInfo mJobInfo;
        private final JobScheduler mJobScheduler;

        JobWorkEnqueuer(Context context, ComponentName componentName, int i) {
            super(context, componentName);
            a(i);
            this.mJobInfo = new JobInfo.Builder(i, this.c).setOverrideDeadline(0).build();
            this.mJobScheduler = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }

        /* access modifiers changed from: package-private */
        public void a(Intent intent) {
            this.mJobScheduler.enqueue(this.mJobInfo, new JobWorkItem(intent));
        }
    }

    static abstract class WorkEnqueuer {
        final ComponentName c;
        boolean d;
        int e;

        WorkEnqueuer(Context context, ComponentName componentName) {
            this.c = componentName;
        }

        /* access modifiers changed from: package-private */
        public void a(int i) {
            if (!this.d) {
                this.d = true;
                this.e = i;
            } else if (this.e != i) {
                throw new IllegalArgumentException("Given job ID " + i + " is different than previous " + this.e);
            }
        }

        /* access modifiers changed from: package-private */
        public abstract void a(Intent intent);

        public void serviceProcessingFinished() {
        }

        public void serviceProcessingStarted() {
        }

        public void serviceStartReceived() {
        }
    }

    public JobIntentService() {
        this.g = Build.VERSION.SDK_INT >= 26 ? null : new ArrayList<>();
    }

    static WorkEnqueuer a(Context context, ComponentName componentName, boolean z, int i2) {
        WorkEnqueuer workEnqueuer;
        WorkEnqueuer workEnqueuer2 = i.get(componentName);
        if (workEnqueuer2 != null) {
            return workEnqueuer2;
        }
        if (Build.VERSION.SDK_INT < 26) {
            workEnqueuer = new CompatWorkEnqueuer(context, componentName);
        } else if (z) {
            workEnqueuer = new JobWorkEnqueuer(context, componentName, i2);
        } else {
            throw new IllegalArgumentException("Can't be here without a job id");
        }
        WorkEnqueuer workEnqueuer3 = workEnqueuer;
        i.put(componentName, workEnqueuer3);
        return workEnqueuer3;
    }

    public static void enqueueWork(@NonNull Context context, @NonNull ComponentName componentName, int i2, @NonNull Intent intent) {
        if (intent != null) {
            synchronized (h) {
                WorkEnqueuer a2 = a(context, componentName, true, i2);
                a2.a(i2);
                a2.a(intent);
            }
            return;
        }
        throw new IllegalArgumentException("work must not be null");
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Class cls, int i2, @NonNull Intent intent) {
        enqueueWork(context, new ComponentName(context, cls), i2, intent);
    }

    /* access modifiers changed from: protected */
    public abstract void a(@NonNull Intent intent);

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        if (this.c == null) {
            this.c = new CommandProcessor();
            WorkEnqueuer workEnqueuer = this.b;
            if (workEnqueuer != null && z) {
                workEnqueuer.serviceProcessingStarted();
            }
            this.c.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        CommandProcessor commandProcessor = this.c;
        if (commandProcessor != null) {
            commandProcessor.cancel(this.d);
        }
        this.e = true;
        return onStopCurrentWork();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        ArrayList<CompatWorkItem> arrayList = this.g;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.c = null;
                if (this.g != null && this.g.size() > 0) {
                    a(false);
                } else if (!this.f) {
                    this.b.serviceProcessingFinished();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public GenericWorkItem c() {
        CompatJobEngine compatJobEngine = this.a;
        if (compatJobEngine != null) {
            return compatJobEngine.dequeueWork();
        }
        synchronized (this.g) {
            if (this.g.size() <= 0) {
                return null;
            }
            GenericWorkItem remove = this.g.remove(0);
            return remove;
        }
    }

    public boolean isStopped() {
        return this.e;
    }

    public IBinder onBind(@NonNull Intent intent) {
        CompatJobEngine compatJobEngine = this.a;
        if (compatJobEngine != null) {
            return compatJobEngine.compatGetBinder();
        }
        return null;
    }

    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            this.a = new JobServiceEngineImpl(this);
            this.b = null;
            return;
        }
        this.a = null;
        this.b = a(this, new ComponentName(this, getClass()), false, 0);
    }

    public void onDestroy() {
        super.onDestroy();
        ArrayList<CompatWorkItem> arrayList = this.g;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f = true;
                this.b.serviceProcessingFinished();
            }
        }
    }

    public int onStartCommand(@Nullable Intent intent, int i2, int i3) {
        if (this.g == null) {
            return 2;
        }
        this.b.serviceStartReceived();
        synchronized (this.g) {
            ArrayList<CompatWorkItem> arrayList = this.g;
            if (intent == null) {
                intent = new Intent();
            }
            arrayList.add(new CompatWorkItem(intent, i3));
            a(true);
        }
        return 3;
    }

    public boolean onStopCurrentWork() {
        return true;
    }

    public void setInterruptIfStopped(boolean z) {
        this.d = z;
    }
}

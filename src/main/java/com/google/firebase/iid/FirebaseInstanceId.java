package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Subscriber;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;

public class FirebaseInstanceId {
    private static final long zzaq = TimeUnit.HOURS.toSeconds(8);
    private static zzax zzar;
    @GuardedBy("FirebaseInstanceId.class")
    @VisibleForTesting
    private static ScheduledThreadPoolExecutor zzas;
    private final Executor zzat;
    /* access modifiers changed from: private */
    public final FirebaseApp zzau;
    private final zzam zzav;
    private MessagingChannel zzaw;
    private final zzar zzax;
    private final zzba zzay;
    @GuardedBy("this")
    private boolean zzaz;
    private final zza zzba;

    private class zza {
        private final boolean zzbg = zzu();
        private final Subscriber zzbh;
        @GuardedBy("this")
        @Nullable
        private EventHandler<DataCollectionDefaultChange> zzbi;
        @GuardedBy("this")
        @Nullable
        private Boolean zzbj = zzt();

        zza(Subscriber subscriber) {
            this.zzbh = subscriber;
            if (this.zzbj == null && this.zzbg) {
                this.zzbi = new zzq(this);
                subscriber.subscribe(DataCollectionDefaultChange.class, this.zzbi);
            }
        }

        @Nullable
        private final Boolean zzt() {
            ApplicationInfo applicationInfo;
            Context applicationContext = FirebaseInstanceId.this.zzau.getApplicationContext();
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.google.firebase.messaging", 0);
            if (sharedPreferences.contains("auto_init")) {
                return Boolean.valueOf(sharedPreferences.getBoolean("auto_init", false));
            }
            try {
                PackageManager packageManager = applicationContext.getPackageManager();
                if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128)) == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey("firebase_messaging_auto_init_enabled")) {
                    return null;
                }
                return Boolean.valueOf(applicationInfo.metaData.getBoolean("firebase_messaging_auto_init_enabled"));
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }

        private final boolean zzu() {
            try {
                Class.forName("com.google.firebase.messaging.FirebaseMessaging");
                return true;
            } catch (ClassNotFoundException unused) {
                Context applicationContext = FirebaseInstanceId.this.zzau.getApplicationContext();
                Intent intent = new Intent("com.google.firebase.MESSAGING_EVENT");
                intent.setPackage(applicationContext.getPackageName());
                ResolveInfo resolveService = applicationContext.getPackageManager().resolveService(intent, 0);
                return (resolveService == null || resolveService.serviceInfo == null) ? false : true;
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
            return r1.zzbg && com.google.firebase.iid.FirebaseInstanceId.zza(r1.zzbk).isDataCollectionDefaultEnabled();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final synchronized boolean isEnabled() {
            /*
                r1 = this;
                monitor-enter(r1)
                java.lang.Boolean r0 = r1.zzbj     // Catch:{ all -> 0x0022 }
                if (r0 == 0) goto L_0x000d
                java.lang.Boolean r0 = r1.zzbj     // Catch:{ all -> 0x0022 }
                boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x0022 }
                monitor-exit(r1)
                return r0
            L_0x000d:
                boolean r0 = r1.zzbg     // Catch:{ all -> 0x0022 }
                if (r0 == 0) goto L_0x0020
                com.google.firebase.iid.FirebaseInstanceId r0 = com.google.firebase.iid.FirebaseInstanceId.this     // Catch:{ all -> 0x0022 }
                com.google.firebase.FirebaseApp r0 = r0.zzau     // Catch:{ all -> 0x0022 }
                boolean r0 = r0.isDataCollectionDefaultEnabled()     // Catch:{ all -> 0x0022 }
                if (r0 == 0) goto L_0x0020
                r0 = 1
            L_0x001e:
                monitor-exit(r1)
                return r0
            L_0x0020:
                r0 = 0
                goto L_0x001e
            L_0x0022:
                r0 = move-exception
                monitor-exit(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.FirebaseInstanceId.zza.isEnabled():boolean");
        }

        /* access modifiers changed from: package-private */
        public final synchronized void setEnabled(boolean z) {
            if (this.zzbi != null) {
                this.zzbh.unsubscribe(DataCollectionDefaultChange.class, this.zzbi);
                this.zzbi = null;
            }
            SharedPreferences.Editor edit = FirebaseInstanceId.this.zzau.getApplicationContext().getSharedPreferences("com.google.firebase.messaging", 0).edit();
            edit.putBoolean("auto_init", z);
            edit.apply();
            if (z) {
                FirebaseInstanceId.this.zzh();
            }
            this.zzbj = Boolean.valueOf(z);
        }
    }

    FirebaseInstanceId(FirebaseApp firebaseApp, Subscriber subscriber, UserAgentPublisher userAgentPublisher) {
        this(firebaseApp, new zzam(firebaseApp.getApplicationContext()), zzh.zze(), zzh.zze(), subscriber, userAgentPublisher);
    }

    private FirebaseInstanceId(FirebaseApp firebaseApp, zzam zzam, Executor executor, Executor executor2, Subscriber subscriber, UserAgentPublisher userAgentPublisher) {
        this.zzaz = false;
        if (zzam.zza(firebaseApp) != null) {
            synchronized (FirebaseInstanceId.class) {
                if (zzar == null) {
                    zzar = new zzax(firebaseApp.getApplicationContext());
                }
            }
            this.zzau = firebaseApp;
            this.zzav = zzam;
            if (this.zzaw == null) {
                MessagingChannel messagingChannel = (MessagingChannel) firebaseApp.get(MessagingChannel.class);
                this.zzaw = (messagingChannel == null || !messagingChannel.isAvailable()) ? new zzr(firebaseApp, zzam, executor, userAgentPublisher) : messagingChannel;
            }
            this.zzaw = this.zzaw;
            this.zzat = executor2;
            this.zzay = new zzba(zzar);
            this.zzba = new zza(subscriber);
            this.zzax = new zzar(executor);
            if (this.zzba.isEnabled()) {
                zzh();
                return;
            }
            return;
        }
        throw new IllegalStateException("FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID");
    }

    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    @Keep
    public static FirebaseInstanceId getInstance(@NonNull FirebaseApp firebaseApp) {
        return (FirebaseInstanceId) firebaseApp.get(FirebaseInstanceId.class);
    }

    private final synchronized void startSync() {
        if (!this.zzaz) {
            zza(0);
        }
    }

    private final Task<InstanceIdResult> zza(String str, String str2) {
        return Tasks.forResult(null).continueWithTask(this.zzat, new zzo(this, str, zzd(str2)));
    }

    private final <T> T zza(Task<T> task) {
        try {
            return Tasks.await(task, 30000, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IOException) {
                if ("INSTANCE_ID_RESET".equals(cause.getMessage())) {
                    zzn();
                }
                throw ((IOException) cause);
            } else if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else {
                throw new IOException(e);
            }
        } catch (InterruptedException | TimeoutException unused) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
    }

    static void zza(Runnable runnable, long j) {
        synchronized (FirebaseInstanceId.class) {
            if (zzas == null) {
                zzas = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("FirebaseInstanceId"));
            }
            zzas.schedule(runnable, j, TimeUnit.SECONDS);
        }
    }

    @Nullable
    @VisibleForTesting
    private static zzaw zzb(String str, String str2) {
        return zzar.zzb("", str, str2);
    }

    private static String zzd(String str) {
        return (str.isEmpty() || str.equalsIgnoreCase("fcm") || str.equalsIgnoreCase("gcm")) ? "*" : str;
    }

    /* access modifiers changed from: private */
    public final void zzh() {
        zzaw zzk = zzk();
        if (zzr() || zza(zzk) || this.zzay.zzaq()) {
            startSync();
        }
    }

    private static String zzj() {
        return zzam.zza(zzar.zzi("").getKeyPair());
    }

    static boolean zzm() {
        if (!Log.isLoggable("FirebaseInstanceId", 3)) {
            return Build.VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseInstanceId", 3);
        }
        return true;
    }

    @WorkerThread
    public void deleteInstanceId() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            zza(this.zzaw.deleteInstanceId(zzj()));
            zzn();
            return;
        }
        throw new IOException("MAIN_THREAD");
    }

    @WorkerThread
    public void deleteToken(String str, String str2) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            String zzd = zzd(str2);
            zza(this.zzaw.deleteToken(zzj(), zzaw.zzb(zzb(str, zzd)), str, zzd));
            zzar.zzc("", str, zzd);
            return;
        }
        throw new IOException("MAIN_THREAD");
    }

    public long getCreationTime() {
        return zzar.zzi("").getCreationTime();
    }

    @WorkerThread
    public String getId() {
        zzh();
        return zzj();
    }

    @NonNull
    public Task<InstanceIdResult> getInstanceId() {
        return zza(zzam.zza(this.zzau), "*");
    }

    @Deprecated
    @Nullable
    public String getToken() {
        zzaw zzk = zzk();
        if (this.zzaw.needsRefresh() || zza(zzk)) {
            startSync();
        }
        return zzaw.zzb(zzk);
    }

    @WorkerThread
    public String getToken(String str, String str2) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            return ((InstanceIdResult) zza(zza(str, str2))).getToken();
        }
        throw new IOException("MAIN_THREAD");
    }

    public final synchronized Task<Void> zza(String str) {
        Task<Void> zza2;
        zza2 = this.zzay.zza(str);
        startSync();
        return zza2;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Task zza(String str, String str2, Task task) {
        String zzj = zzj();
        zzaw zzb = zzb(str, str2);
        if (!this.zzaw.needsRefresh() && !zza(zzb)) {
            return Tasks.forResult(new zzy(zzj, zzb.zzbx));
        }
        return this.zzax.zza(str, str2, new zzn(this, zzj, zzaw.zzb(zzb), str, str2));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Task zza(String str, String str2, String str3, String str4) {
        return this.zzaw.getToken(str, str2, str3, str4).onSuccessTask(this.zzat, new zzp(this, str3, str4, str));
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zza(long j) {
        zza((Runnable) new zzaz(this, this.zzav, this.zzay, Math.min(Math.max(30, j << 1), zzaq)), j);
        this.zzaz = true;
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zza(boolean z) {
        this.zzaz = z;
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(@Nullable zzaw zzaw2) {
        return zzaw2 == null || zzaw2.zzg(this.zzav.zzad());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Task zzb(String str, String str2, String str3, String str4) {
        zzar.zza("", str, str2, str4, this.zzav.zzad());
        return Tasks.forResult(new zzy(str3, str4));
    }

    /* access modifiers changed from: package-private */
    public final void zzb(String str) {
        zzaw zzk = zzk();
        if (!zza(zzk)) {
            zza(this.zzaw.subscribeToTopic(zzj(), zzk.zzbx, str));
            return;
        }
        throw new IOException("token not available");
    }

    @VisibleForTesting
    public final void zzb(boolean z) {
        this.zzba.setEnabled(z);
    }

    /* access modifiers changed from: package-private */
    public final void zzc(String str) {
        zzaw zzk = zzk();
        if (!zza(zzk)) {
            zza(this.zzaw.unsubscribeFromTopic(zzj(), zzk.zzbx, str));
            return;
        }
        throw new IOException("token not available");
    }

    /* access modifiers changed from: package-private */
    public final FirebaseApp zzi() {
        return this.zzau;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public final zzaw zzk() {
        return zzb(zzam.zza(this.zzau), "*");
    }

    /* access modifiers changed from: package-private */
    public final String zzl() {
        return getToken(zzam.zza(this.zzau), "*");
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzn() {
        zzar.zzal();
        if (this.zzba.isEnabled()) {
            startSync();
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzo() {
        return this.zzaw.isAvailable();
    }

    /* access modifiers changed from: package-private */
    public final void zzp() {
        zzar.zzj("");
        startSync();
    }

    @VisibleForTesting
    public final boolean zzq() {
        return this.zzba.isEnabled();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzr() {
        return this.zzaw.needsRefresh();
    }
}

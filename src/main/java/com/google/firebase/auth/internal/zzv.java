package com.google.firebase.auth.internal;

import android.os.Handler;
import android.os.HandlerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zzj;
import com.google.firebase.FirebaseApp;

public final class zzv {
    /* access modifiers changed from: private */
    public static Logger zzjt = new Logger("TokenRefresher", "FirebaseAuth:");
    @VisibleForTesting
    private Handler handler;
    private final FirebaseApp zzik;
    @VisibleForTesting
    volatile long zztv;
    @VisibleForTesting
    volatile long zztw;
    @VisibleForTesting
    private long zztx;
    @VisibleForTesting
    private HandlerThread zzty = new HandlerThread("TokenRefresher", 10);
    @VisibleForTesting
    private Runnable zztz;

    public zzv(FirebaseApp firebaseApp) {
        zzjt.v("Initializing TokenRefresher", new Object[0]);
        this.zzik = (FirebaseApp) Preconditions.checkNotNull(firebaseApp);
        this.zzty.start();
        this.handler = new zzj(this.zzty.getLooper());
        this.zztz = new zzy(this, this.zzik.getName());
        this.zztx = 300000;
    }

    public final void cancel() {
        this.handler.removeCallbacks(this.zztz);
    }

    public final void zzfh() {
        Logger logger = zzjt;
        long j = this.zztv - this.zztx;
        StringBuilder sb = new StringBuilder(43);
        sb.append("Scheduling refresh for ");
        sb.append(j);
        logger.v(sb.toString(), new Object[0]);
        cancel();
        this.zztw = Math.max((this.zztv - DefaultClock.getInstance().currentTimeMillis()) - this.zztx, 0) / 1000;
        this.handler.postDelayed(this.zztz, this.zztw * 1000);
    }

    /* access modifiers changed from: package-private */
    public final void zzfi() {
        int i = (int) this.zztw;
        this.zztw = (i == 30 || i == 60 || i == 120 || i == 240 || i == 480) ? 2 * this.zztw : i != 960 ? 30 : 960;
        this.zztv = DefaultClock.getInstance().currentTimeMillis() + (this.zztw * 1000);
        Logger logger = zzjt;
        long j = this.zztv;
        StringBuilder sb = new StringBuilder(43);
        sb.append("Scheduling refresh for ");
        sb.append(j);
        logger.v(sb.toString(), new Object[0]);
        this.handler.postDelayed(this.zztz, this.zztw * 1000);
    }
}

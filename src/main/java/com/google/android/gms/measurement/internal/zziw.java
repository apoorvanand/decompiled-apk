package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzh;

public final class zziw extends zzg {
    private Handler handler;
    @VisibleForTesting
    private long zzse = zzx().elapsedRealtime();
    @VisibleForTesting
    private long zzsf = this.zzse;
    private final zzaa zzsg = new zziz(this, this.b);
    private final zzaa zzsh = new zziy(this, this.b);

    zziw(zzfj zzfj) {
        super(zzfj);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzaa(long j) {
        zzo();
        zziy();
        if (zzad().zze(zzr().c(), zzak.zzid)) {
            zzac().zzmd.set(false);
        }
        zzab().zzgs().zza("Activity resumed, time", Long.valueOf(j));
        this.zzse = j;
        this.zzsf = this.zzse;
        if (this.b.isEnabled()) {
            if (zzad().m(zzr().c())) {
                a(zzx().currentTimeMillis(), false);
                return;
            }
            this.zzsg.a();
            this.zzsh.a();
            if (zzac().a(zzx().currentTimeMillis())) {
                zzac().zzlw.set(true);
                zzac().zzmb.set(0);
            }
            if (zzac().zzlw.get()) {
                this.zzsg.zzv(Math.max(0, zzac().zzlu.get() - zzac().zzmb.get()));
            } else {
                this.zzsh.zzv(Math.max(0, 3600000 - zzac().zzmb.get()));
            }
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzab(long j) {
        zzo();
        zziy();
        if (zzad().zze(zzr().c(), zzak.zzid)) {
            zzac().zzmd.set(true);
        }
        this.zzsg.a();
        this.zzsh.a();
        zzab().zzgs().zza("Activity paused, time", Long.valueOf(j));
        if (this.zzse != 0) {
            zzac().zzmb.set(zzac().zzmb.get() + (j - this.zzse));
        }
    }

    @WorkerThread
    private final void zzac(long j) {
        zzo();
        zzab().zzgs().zza("Session started, time", Long.valueOf(zzx().elapsedRealtime()));
        Long valueOf = zzad().k(zzr().c()) ? Long.valueOf(j / 1000) : null;
        zzq().a("auto", "_sid", (Object) valueOf, j);
        zzac().zzlw.set(false);
        Bundle bundle = new Bundle();
        if (zzad().k(zzr().c())) {
            bundle.putLong("_sid", valueOf.longValue());
        }
        zzq().a("auto", "_s", j, bundle);
        zzac().zzma.set(j);
    }

    private final void zziy() {
        synchronized (this) {
            if (this.handler == null) {
                this.handler = new zzh(Looper.getMainLooper());
            }
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzjc() {
        zzo();
        zza(false, false);
        zzp().zzc(zzx().elapsedRealtime());
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void a(long j, boolean z) {
        zzo();
        zziy();
        this.zzsg.a();
        this.zzsh.a();
        if (zzac().a(j)) {
            zzac().zzlw.set(true);
            zzac().zzmb.set(0);
        }
        if (z && zzad().n(zzr().c())) {
            zzac().zzma.set(j);
        }
        if (zzac().zzlw.get()) {
            zzac(j);
        } else {
            this.zzsh.zzv(Math.max(0, 3600000 - zzac().zzmb.get()));
        }
    }

    /* access modifiers changed from: protected */
    public final boolean a() {
        return false;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void c() {
        zzo();
        this.zzsg.a();
        this.zzsh.a();
        this.zzse = 0;
        this.zzsf = this.zzse;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    @VisibleForTesting
    public final void d() {
        zzo();
        zzac(zzx().currentTimeMillis());
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final long e() {
        long elapsedRealtime = zzx().elapsedRealtime();
        long j = elapsedRealtime - this.zzsf;
        this.zzsf = elapsedRealtime;
        return j;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0094, code lost:
        if (r10 == false) goto L_0x00a1;
     */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(boolean r9, boolean r10) {
        /*
            r8 = this;
            r8.zzo()
            r8.j()
            com.google.android.gms.common.util.Clock r0 = r8.zzx()
            long r0 = r0.elapsedRealtime()
            com.google.android.gms.measurement.internal.zzeo r2 = r8.zzac()
            com.google.android.gms.measurement.internal.zzet r2 = r2.zzma
            com.google.android.gms.common.util.Clock r3 = r8.zzx()
            long r3 = r3.currentTimeMillis()
            r2.set(r3)
            long r2 = r8.zzse
            long r2 = r0 - r2
            if (r9 != 0) goto L_0x003e
            r4 = 1000(0x3e8, double:4.94E-321)
            int r9 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r9 >= 0) goto L_0x003e
            com.google.android.gms.measurement.internal.zzef r9 = r8.zzab()
            com.google.android.gms.measurement.internal.zzeh r9 = r9.zzgs()
            java.lang.String r10 = "Screen exposed for less than 1000 ms. Event not sent. time"
            java.lang.Long r0 = java.lang.Long.valueOf(r2)
            r9.zza(r10, r0)
            r9 = 0
            return r9
        L_0x003e:
            com.google.android.gms.measurement.internal.zzeo r9 = r8.zzac()
            com.google.android.gms.measurement.internal.zzet r9 = r9.zzmb
            r9.set(r2)
            com.google.android.gms.measurement.internal.zzef r9 = r8.zzab()
            com.google.android.gms.measurement.internal.zzeh r9 = r9.zzgs()
            java.lang.String r4 = "Recording user engagement, ms"
            java.lang.Long r5 = java.lang.Long.valueOf(r2)
            r9.zza(r4, r5)
            android.os.Bundle r9 = new android.os.Bundle
            r9.<init>()
            java.lang.String r4 = "_et"
            r9.putLong(r4, r2)
            com.google.android.gms.measurement.internal.zzhq r2 = r8.zzt()
            com.google.android.gms.measurement.internal.zzhr r2 = r2.zzin()
            r3 = 1
            com.google.android.gms.measurement.internal.zzhq.zza((com.google.android.gms.measurement.internal.zzhr) r2, (android.os.Bundle) r9, (boolean) r3)
            com.google.android.gms.measurement.internal.zzs r2 = r8.zzad()
            com.google.android.gms.measurement.internal.zzdy r4 = r8.zzr()
            java.lang.String r4 = r4.c()
            boolean r2 = r2.o(r4)
            if (r2 == 0) goto L_0x00a4
            com.google.android.gms.measurement.internal.zzs r2 = r8.zzad()
            com.google.android.gms.measurement.internal.zzdy r4 = r8.zzr()
            java.lang.String r4 = r4.c()
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzak.zzii
            boolean r2 = r2.zze(r4, r5)
            if (r2 == 0) goto L_0x0097
            if (r10 != 0) goto L_0x00a4
            goto L_0x00a1
        L_0x0097:
            if (r10 == 0) goto L_0x00a1
            java.lang.String r2 = "_fr"
            r4 = 1
            r9.putLong(r2, r4)
            goto L_0x00a4
        L_0x00a1:
            r8.e()
        L_0x00a4:
            com.google.android.gms.measurement.internal.zzs r2 = r8.zzad()
            com.google.android.gms.measurement.internal.zzdy r4 = r8.zzr()
            java.lang.String r4 = r4.c()
            com.google.android.gms.measurement.internal.zzdu<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzak.zzii
            boolean r2 = r2.zze(r4, r5)
            if (r2 == 0) goto L_0x00ba
            if (r10 != 0) goto L_0x00c5
        L_0x00ba:
            com.google.android.gms.measurement.internal.zzgp r10 = r8.zzq()
            java.lang.String r2 = "auto"
            java.lang.String r4 = "_e"
            r10.logEvent(r2, r4, r9)
        L_0x00c5:
            r8.zzse = r0
            com.google.android.gms.measurement.internal.zzaa r9 = r8.zzsh
            r9.a()
            com.google.android.gms.measurement.internal.zzaa r9 = r8.zzsh
            r0 = 0
            r4 = 3600000(0x36ee80, double:1.7786363E-317)
            com.google.android.gms.measurement.internal.zzeo r10 = r8.zzac()
            com.google.android.gms.measurement.internal.zzet r10 = r10.zzmb
            long r6 = r10.get()
            long r4 = r4 - r6
            long r0 = java.lang.Math.max(r0, r4)
            r9.zzv(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zziw.zza(boolean, boolean):boolean");
    }

    public final /* bridge */ /* synthetic */ zzfc zzaa() {
        return super.zzaa();
    }

    public final /* bridge */ /* synthetic */ zzef zzab() {
        return super.zzab();
    }

    public final /* bridge */ /* synthetic */ zzeo zzac() {
        return super.zzac();
    }

    public final /* bridge */ /* synthetic */ zzs zzad() {
        return super.zzad();
    }

    public final /* bridge */ /* synthetic */ zzr zzae() {
        return super.zzae();
    }

    public final /* bridge */ /* synthetic */ void zzl() {
        super.zzl();
    }

    public final /* bridge */ /* synthetic */ void zzm() {
        super.zzm();
    }

    public final /* bridge */ /* synthetic */ void zzn() {
        super.zzn();
    }

    public final /* bridge */ /* synthetic */ void zzo() {
        super.zzo();
    }

    public final /* bridge */ /* synthetic */ zza zzp() {
        return super.zzp();
    }

    public final /* bridge */ /* synthetic */ zzgp zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ zzdy zzr() {
        return super.zzr();
    }

    public final /* bridge */ /* synthetic */ zzhv zzs() {
        return super.zzs();
    }

    public final /* bridge */ /* synthetic */ zzhq zzt() {
        return super.zzt();
    }

    public final /* bridge */ /* synthetic */ zzeb zzu() {
        return super.zzu();
    }

    public final /* bridge */ /* synthetic */ zziw zzv() {
        return super.zzv();
    }

    public final /* bridge */ /* synthetic */ zzac zzw() {
        return super.zzw();
    }

    public final /* bridge */ /* synthetic */ Clock zzx() {
        return super.zzx();
    }

    public final /* bridge */ /* synthetic */ zzed zzy() {
        return super.zzy();
    }

    public final /* bridge */ /* synthetic */ zzjs zzz() {
        return super.zzz();
    }
}

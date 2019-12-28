package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.base.zap;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zal extends LifecycleCallback implements DialogInterface.OnCancelListener {
    protected volatile boolean a;
    protected final AtomicReference<zam> b;
    protected final GoogleApiAvailability c;
    private final Handler zadg;

    protected zal(LifecycleFragment lifecycleFragment) {
        this(lifecycleFragment, GoogleApiAvailability.getInstance());
    }

    @VisibleForTesting
    private zal(LifecycleFragment lifecycleFragment, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment);
        this.b = new AtomicReference<>((Object) null);
        this.zadg = new zap(Looper.getMainLooper());
        this.c = googleApiAvailability;
    }

    private static int zaa(@Nullable zam zam) {
        if (zam == null) {
            return -1;
        }
        return zam.a();
    }

    /* access modifiers changed from: protected */
    public abstract void a();

    /* access modifiers changed from: protected */
    public abstract void a(ConnectionResult connectionResult, int i);

    /* access modifiers changed from: protected */
    public final void c() {
        this.b.set((Object) null);
        a();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r4, int r5, android.content.Intent r6) {
        /*
            r3 = this;
            java.util.concurrent.atomic.AtomicReference<com.google.android.gms.common.api.internal.zam> r0 = r3.b
            java.lang.Object r0 = r0.get()
            com.google.android.gms.common.api.internal.zam r0 = (com.google.android.gms.common.api.internal.zam) r0
            r1 = 1
            r2 = 0
            switch(r4) {
                case 1: goto L_0x002e;
                case 2: goto L_0x000e;
                default: goto L_0x000d;
            }
        L_0x000d:
            goto L_0x0053
        L_0x000e:
            com.google.android.gms.common.GoogleApiAvailability r4 = r3.c
            android.app.Activity r5 = r3.getActivity()
            int r4 = r4.isGooglePlayServicesAvailable(r5)
            if (r4 != 0) goto L_0x001b
            goto L_0x001c
        L_0x001b:
            r1 = 0
        L_0x001c:
            if (r0 != 0) goto L_0x001f
            return
        L_0x001f:
            com.google.android.gms.common.ConnectionResult r5 = r0.b()
            int r5 = r5.getErrorCode()
            r6 = 18
            if (r5 != r6) goto L_0x0054
            if (r4 != r6) goto L_0x0054
            return
        L_0x002e:
            r4 = -1
            if (r5 != r4) goto L_0x0032
            goto L_0x0054
        L_0x0032:
            if (r5 != 0) goto L_0x0053
            r4 = 13
            if (r6 == 0) goto L_0x003e
            java.lang.String r5 = "<<ResolutionFailureErrorDetail>>"
            int r4 = r6.getIntExtra(r5, r4)
        L_0x003e:
            com.google.android.gms.common.api.internal.zam r5 = new com.google.android.gms.common.api.internal.zam
            com.google.android.gms.common.ConnectionResult r6 = new com.google.android.gms.common.ConnectionResult
            r1 = 0
            r6.<init>(r4, r1)
            int r4 = zaa(r0)
            r5.<init>(r6, r4)
            java.util.concurrent.atomic.AtomicReference<com.google.android.gms.common.api.internal.zam> r4 = r3.b
            r4.set(r5)
            r0 = r5
        L_0x0053:
            r1 = 0
        L_0x0054:
            if (r1 == 0) goto L_0x005a
            r3.c()
            return
        L_0x005a:
            if (r0 == 0) goto L_0x0067
            com.google.android.gms.common.ConnectionResult r4 = r0.b()
            int r5 = r0.a()
            r3.a(r4, r5)
        L_0x0067:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zal.onActivityResult(int, int, android.content.Intent):void");
    }

    public void onCancel(DialogInterface dialogInterface) {
        a(new ConnectionResult(13, (PendingIntent) null), zaa(this.b.get()));
        c();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.b.set(bundle.getBoolean("resolving_error", false) ? new zam(new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution")), bundle.getInt("failed_client_id", -1)) : null);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        zam zam = this.b.get();
        if (zam != null) {
            bundle.putBoolean("resolving_error", true);
            bundle.putInt("failed_client_id", zam.a());
            bundle.putInt("failed_status", zam.b().getErrorCode());
            bundle.putParcelable("failed_resolution", zam.b().getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        this.a = true;
    }

    public void onStop() {
        super.onStop();
        this.a = false;
    }

    public final void zab(ConnectionResult connectionResult, int i) {
        zam zam = new zam(connectionResult, i);
        if (this.b.compareAndSet((Object) null, zam)) {
            this.zadg.post(new zan(this, zam));
        }
    }
}

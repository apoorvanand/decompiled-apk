package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.DefaultClock;

@KeepName
public class FederatedSignInActivity extends FragmentActivity {
    private static Handler handler;
    private static final zzak zziu = zzak.zzfn();
    private static long zzuj;
    /* access modifiers changed from: private */
    public static Runnable zzul;
    private boolean zzuk = false;

    private final void zze(Status status) {
        zzuj = 0;
        this.zzuk = false;
        Intent intent = new Intent();
        zzaw.zza(intent, status);
        intent.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
        if (!LocalBroadcastManager.getInstance(this).sendBroadcast(intent)) {
            zzaq.zza(getApplicationContext(), status);
        } else {
            zziu.zza(this);
        }
        finish();
    }

    /* access modifiers changed from: private */
    public final void zzfm() {
        zzuj = 0;
        this.zzuk = false;
        Intent intent = new Intent();
        intent.putExtra("com.google.firebase.auth.internal.EXTRA_CANCELED", true);
        intent.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
        if (!LocalBroadcastManager.getInstance(this).sendBroadcast(intent)) {
            zzaq.zza((Context) this, zzt.zzdc("WEB_CONTEXT_CANCELED"));
        } else {
            zziu.zza(this);
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String action = getIntent().getAction();
        if ("com.google.firebase.auth.internal.SIGN_IN".equals(action) || "com.google.firebase.auth.internal.LINK".equals(action) || "com.google.firebase.auth.internal.REAUTHENTICATE".equals(action)) {
            long currentTimeMillis = DefaultClock.getInstance().currentTimeMillis();
            if (currentTimeMillis - zzuj < 30000) {
                Log.e("IdpSignInActivity", "Could not start operation - already in progress");
                return;
            }
            zzuj = currentTimeMillis;
            if (bundle != null) {
                this.zzuk = bundle.getBoolean("com.google.firebase.auth.internal.KEY_STARTED_SIGN_IN");
                return;
            }
            return;
        }
        String valueOf = String.valueOf(action);
        Log.e("IdpSignInActivity", valueOf.length() != 0 ? "Could not do operation - unknown action: ".concat(valueOf) : new String("Could not do operation - unknown action: "));
        zzfm();
    }

    public void onNewIntent(Intent intent) {
        Runnable runnable;
        super.onNewIntent(intent);
        Handler handler2 = handler;
        if (!(handler2 == null || (runnable = zzul) == null)) {
            handler2.removeCallbacks(runnable);
            zzul = null;
        }
        setIntent(intent);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00a8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onResume() {
        /*
            r7 = this;
            super.onResume()
            android.content.Intent r0 = r7.getIntent()
            java.lang.String r1 = "com.google.firebase.auth.internal.WEB_SIGN_IN_FAILED"
            java.lang.String r2 = r0.getAction()
            boolean r1 = r1.equals(r2)
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0030
            java.lang.String r1 = "IdpSignInActivity"
            java.lang.String r2 = "Web sign-in failed, finishing"
            android.util.Log.e(r1, r2)
            boolean r1 = com.google.firebase.auth.internal.zzaw.zzb(r0)
            if (r1 == 0) goto L_0x002a
            com.google.android.gms.common.api.Status r0 = com.google.firebase.auth.internal.zzaw.zzc(r0)
            r7.zze(r0)
            goto L_0x002d
        L_0x002a:
            r7.zzfm()
        L_0x002d:
            r2 = 1
            goto L_0x00a6
        L_0x0030:
            java.lang.String r1 = "com.google.firebase.auth.internal.OPERATION"
            boolean r1 = r0.hasExtra(r1)
            if (r1 == 0) goto L_0x00a6
            java.lang.String r1 = "com.google.firebase.auth.internal.VERIFY_ASSERTION_REQUEST"
            boolean r1 = r0.hasExtra(r1)
            if (r1 == 0) goto L_0x00a6
            java.lang.String r1 = "com.google.firebase.auth.internal.OPERATION"
            java.lang.String r1 = r0.getStringExtra(r1)
            java.lang.String r4 = "com.google.firebase.auth.internal.SIGN_IN"
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x005e
            java.lang.String r4 = "com.google.firebase.auth.internal.LINK"
            boolean r4 = r4.equals(r1)
            if (r4 != 0) goto L_0x005e
            java.lang.String r4 = "com.google.firebase.auth.internal.REAUTHENTICATE"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L_0x00a6
        L_0x005e:
            java.lang.String r4 = "com.google.firebase.auth.internal.VERIFY_ASSERTION_REQUEST"
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase_auth.zzfm> r5 = com.google.android.gms.internal.firebase_auth.zzfm.CREATOR
            com.google.android.gms.common.internal.safeparcel.SafeParcelable r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer.deserializeFromIntentExtra(r0, r4, r5)
            com.google.android.gms.internal.firebase_auth.zzfm r4 = (com.google.android.gms.internal.firebase_auth.zzfm) r4
            java.lang.String r5 = "com.google.firebase.auth.internal.EXTRA_TENANT_ID"
            java.lang.String r0 = r0.getStringExtra(r5)
            r4.zzcz(r0)
            r5 = 0
            zzuj = r5
            r7.zzuk = r2
            android.content.Intent r2 = new android.content.Intent
            r2.<init>()
            java.lang.String r5 = "com.google.firebase.auth.internal.VERIFY_ASSERTION_REQUEST"
            com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer.serializeToIntentExtra(r4, r2, r5)
            java.lang.String r5 = "com.google.firebase.auth.internal.OPERATION"
            r2.putExtra(r5, r1)
            java.lang.String r5 = "com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT"
            r2.setAction(r5)
            androidx.localbroadcastmanager.content.LocalBroadcastManager r5 = androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance(r7)
            boolean r2 = r5.sendBroadcast(r2)
            if (r2 != 0) goto L_0x009d
            android.content.Context r2 = r7.getApplicationContext()
            com.google.firebase.auth.internal.zzaq.zza(r2, r4, r1, r0)
            goto L_0x00a2
        L_0x009d:
            com.google.firebase.auth.internal.zzak r0 = zziu
            r0.zza(r7)
        L_0x00a2:
            r7.finish()
            goto L_0x002d
        L_0x00a6:
            if (r2 == 0) goto L_0x00a9
            return
        L_0x00a9:
            boolean r0 = r7.zzuk
            if (r0 != 0) goto L_0x00ec
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "com.google.firebase.auth.api.gms.ui.START_WEB_SIGN_IN"
            r0.<init>(r1)
            java.lang.String r1 = "com.google.android.gms"
            r0.setPackage(r1)
            android.content.Intent r1 = r7.getIntent()
            android.os.Bundle r1 = r1.getExtras()
            r0.putExtras(r1)
            java.lang.String r1 = "com.google.firebase.auth.internal.OPERATION"
            android.content.Intent r2 = r7.getIntent()
            java.lang.String r2 = r2.getAction()
            r0.putExtra(r1, r2)
            r1 = 40963(0xa003, float:5.7401E-41)
            r7.startActivityForResult(r0, r1)     // Catch:{ ActivityNotFoundException -> 0x00d8 }
            goto L_0x00e9
        L_0x00d8:
            java.lang.String r0 = "Could not launch web sign-in Intent. Google Play service is unavailable"
            java.lang.String r1 = "IdpSignInActivity"
            android.util.Log.w(r1, r0)
            com.google.android.gms.common.api.Status r1 = new com.google.android.gms.common.api.Status
            r2 = 17499(0x445b, float:2.4521E-41)
            r1.<init>(r2, r0)
            r7.zze(r1)
        L_0x00e9:
            r7.zzuk = r3
            return
        L_0x00ec:
            com.google.firebase.auth.internal.zzaj r0 = new com.google.firebase.auth.internal.zzaj
            r0.<init>(r7)
            zzul = r0
            android.os.Handler r0 = handler
            if (r0 != 0) goto L_0x00fe
            com.google.android.gms.internal.firebase_auth.zzj r0 = new com.google.android.gms.internal.firebase_auth.zzj
            r0.<init>()
            handler = r0
        L_0x00fe:
            android.os.Handler r0 = handler
            java.lang.Runnable r1 = zzul
            r2 = 800(0x320, double:3.953E-321)
            r0.postDelayed(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.internal.FederatedSignInActivity.onResume():void");
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("com.google.firebase.auth.internal.KEY_STARTED_SIGN_IN", this.zzuk);
    }
}

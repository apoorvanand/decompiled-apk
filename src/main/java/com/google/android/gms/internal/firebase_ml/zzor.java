package com.google.android.gms.internal.firebase_ml;

import android.content.Context;
import com.google.firebase.FirebaseApp;

final class zzor extends zziv {
    private final /* synthetic */ FirebaseApp zzauq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzor(zzop zzop, String str, FirebaseApp firebaseApp) {
        super(str);
        this.zzauq = firebaseApp;
    }

    /* access modifiers changed from: protected */
    public final void a(zziu<?> zziu) {
        super.a(zziu);
        Context applicationContext = this.zzauq.getApplicationContext();
        String packageName = applicationContext.getPackageName();
        zziu.zzdy().put("X-Android-Package", packageName);
        zziu.zzdy().put("X-Android-Cert", zzop.zza(applicationContext, packageName));
    }
}

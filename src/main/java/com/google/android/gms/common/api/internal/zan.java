package com.google.android.gms.common.api.internal;

import android.content.DialogInterface;
import androidx.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

final class zan implements Runnable {
    final /* synthetic */ zal a;
    private final zam zadj;

    zan(zal zal, zam zam) {
        this.a = zal;
        this.zadj = zam;
    }

    @MainThread
    public final void run() {
        if (this.a.a) {
            ConnectionResult b = this.zadj.b();
            if (b.hasResolution()) {
                this.a.mLifecycleFragment.startActivityForResult(GoogleApiActivity.zaa(this.a.getActivity(), b.getResolution(), this.zadj.a(), false), 1);
            } else if (this.a.c.isUserResolvableError(b.getErrorCode())) {
                this.a.c.zaa(this.a.getActivity(), this.a.mLifecycleFragment, b.getErrorCode(), 2, this.a);
            } else if (b.getErrorCode() == 18) {
                this.a.c.zaa(this.a.getActivity().getApplicationContext(), (zabr) new zao(this, GoogleApiAvailability.zaa(this.a.getActivity(), (DialogInterface.OnCancelListener) this.a)));
            } else {
                this.a.a(b, this.zadj.a());
            }
        }
    }
}

package com.google.android.gms.common.api.internal;

import android.app.Dialog;

final class zao extends zabr {
    private final /* synthetic */ Dialog zadl;
    private final /* synthetic */ zan zadm;

    zao(zan zan, Dialog dialog) {
        this.zadm = zan;
        this.zadl = dialog;
    }

    public final void zas() {
        this.zadm.a.c();
        if (this.zadl.isShowing()) {
            this.zadl.dismiss();
        }
    }
}
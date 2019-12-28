package com.google.firebase.auth.internal;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzfd;
import com.google.firebase.auth.ActionCodeResult;

public final class zzb implements ActionCodeResult {
    private final String zzif;
    private final int zzta;
    private final String zztb;

    public zzb(zzfd zzfd) {
        int i;
        this.zzif = TextUtils.isEmpty(zzfd.zzae()) ? zzfd.getEmail() : zzfd.zzae();
        this.zztb = zzfd.getEmail();
        if (!TextUtils.isEmpty(zzfd.zzey())) {
            if (zzfd.zzey().equals("PASSWORD_RESET")) {
                i = 0;
            } else if (zzfd.zzey().equals("VERIFY_EMAIL")) {
                i = 1;
            } else if (zzfd.zzey().equals("RECOVER_EMAIL")) {
                i = 2;
            } else if (zzfd.zzey().equals("EMAIL_SIGNIN")) {
                i = 4;
            } else {
                this.zzta = 3;
                return;
            }
            this.zzta = i;
            return;
        }
        this.zzta = 3;
    }

    @Nullable
    public final String getData(int i) {
        switch (i) {
            case 0:
                if (this.zzta == 4) {
                    return null;
                }
                return this.zzif;
            case 1:
                return this.zztb;
            default:
                return null;
        }
    }

    public final int getOperation() {
        return this.zzta;
    }
}

package com.google.android.gms.internal.firebase_auth;

import com.google.firebase.analytics.FirebaseAnalytics;

public abstract class zzae {
    protected zzae() {
    }

    /* access modifiers changed from: private */
    public static String zzb(char c) {
        char[] cArr = {'\\', 'u', 0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            cArr[5 - i] = "0123456789ABCDEF".charAt(c & 15);
            c = (char) (c >> 4);
        }
        return String.copyValueOf(cArr);
    }

    public int zza(CharSequence charSequence, int i) {
        int length = charSequence.length();
        zzaj.zza(i, length, FirebaseAnalytics.Param.INDEX);
        while (i < length) {
            if (zza(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public abstract boolean zza(char c);
}

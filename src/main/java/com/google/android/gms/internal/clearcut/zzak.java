package com.google.android.gms.internal.clearcut;

import android.content.SharedPreferences;
import android.util.Log;

final class zzak extends zzae<String> {
    zzak(zzao zzao, String str, String str2) {
        super(zzao, str, str2, (zzai) null);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzc */
    public final String a(SharedPreferences sharedPreferences) {
        try {
            return sharedPreferences.getString(this.a, (String) null);
        } catch (ClassCastException e) {
            String valueOf = String.valueOf(this.a);
            Log.e("PhenotypeFlag", valueOf.length() != 0 ? "Invalid string value in SharedPreferences for ".concat(valueOf) : new String("Invalid string value in SharedPreferences for "), e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object a(String str) {
        return str;
    }
}

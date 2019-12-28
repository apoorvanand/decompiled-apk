package com.facebook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.internal.NativeProtocol;

public class FacebookBroadcastReceiver extends BroadcastReceiver {
    /* access modifiers changed from: protected */
    public void a(String str, String str2, Bundle bundle) {
    }

    /* access modifiers changed from: protected */
    public void b(String str, String str2, Bundle bundle) {
    }

    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(NativeProtocol.EXTRA_PROTOCOL_CALL_ID);
        String stringExtra2 = intent.getStringExtra(NativeProtocol.EXTRA_PROTOCOL_ACTION);
        if (stringExtra != null && stringExtra2 != null) {
            Bundle extras = intent.getExtras();
            if (NativeProtocol.isErrorResult(intent)) {
                b(stringExtra, stringExtra2, extras);
            } else {
                a(stringExtra, stringExtra2, extras);
            }
        }
    }
}

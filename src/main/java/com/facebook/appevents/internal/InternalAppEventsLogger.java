package com.facebook.appevents.internal;

import android.content.Context;
import android.os.Bundle;
import com.facebook.AccessToken;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.Utility;
import java.math.BigDecimal;
import java.util.Currency;

class InternalAppEventsLogger extends AppEventsLogger {
    InternalAppEventsLogger(Context context) {
        this(Utility.getActivityName(context), (String) null, (AccessToken) null);
    }

    InternalAppEventsLogger(String str, String str2, AccessToken accessToken) {
        super(str, str2, accessToken);
    }

    /* access modifiers changed from: protected */
    public void a(String str, BigDecimal bigDecimal, Currency currency, Bundle bundle) {
        super.a(str, bigDecimal, currency, bundle);
    }

    /* access modifiers changed from: protected */
    public void a(BigDecimal bigDecimal, Currency currency, Bundle bundle) {
        super.a(bigDecimal, currency, bundle);
    }
}

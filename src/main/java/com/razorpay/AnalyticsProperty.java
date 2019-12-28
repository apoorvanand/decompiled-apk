package com.razorpay;

import org.json.JSONObject;
import proguard.annotation.Keep;
import proguard.annotation.KeepClassMembers;

@Keep
@KeepClassMembers
public class AnalyticsProperty {
    AnalyticsProperty$R$$r_ scope;
    Object value;

    AnalyticsProperty(int i, AnalyticsProperty$R$$r_ analyticsProperty$R$$r_) {
        this.value = Integer.valueOf(i);
        this.scope = analyticsProperty$R$$r_;
    }

    AnalyticsProperty(long j, AnalyticsProperty$R$$r_ analyticsProperty$R$$r_) {
        this.value = Long.valueOf(j);
        this.scope = analyticsProperty$R$$r_;
    }

    AnalyticsProperty(String str, AnalyticsProperty$R$$r_ analyticsProperty$R$$r_) {
        this.value = str;
        this.scope = analyticsProperty$R$$r_;
    }

    AnalyticsProperty(JSONObject jSONObject, AnalyticsProperty$R$$r_ analyticsProperty$R$$r_) {
        this.value = jSONObject;
        this.scope = analyticsProperty$R$$r_;
    }

    AnalyticsProperty(boolean z, AnalyticsProperty$R$$r_ analyticsProperty$R$$r_) {
        this.value = Boolean.valueOf(z);
        this.scope = analyticsProperty$R$$r_;
    }
}

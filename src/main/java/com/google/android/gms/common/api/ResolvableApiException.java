package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import androidx.annotation.NonNull;

public class ResolvableApiException extends ApiException {
    public ResolvableApiException(@NonNull Status status) {
        super(status);
    }

    public PendingIntent getResolution() {
        return this.a.getResolution();
    }

    public void startResolutionForResult(Activity activity, int i) {
        this.a.startResolutionForResult(activity, i);
    }
}

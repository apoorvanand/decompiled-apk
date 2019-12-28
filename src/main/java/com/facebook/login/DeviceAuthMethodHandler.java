package com.facebook.login;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.login.LoginClient;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;

class DeviceAuthMethodHandler extends LoginMethodHandler {
    public static final Parcelable.Creator<DeviceAuthMethodHandler> CREATOR = new Parcelable.Creator() {
        public DeviceAuthMethodHandler createFromParcel(Parcel parcel) {
            return new DeviceAuthMethodHandler(parcel);
        }

        public DeviceAuthMethodHandler[] newArray(int i) {
            return new DeviceAuthMethodHandler[i];
        }
    };
    private static ScheduledThreadPoolExecutor backgroundExecutor;

    protected DeviceAuthMethodHandler(Parcel parcel) {
        super(parcel);
    }

    DeviceAuthMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    public static synchronized ScheduledThreadPoolExecutor getBackgroundExecutor() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
        synchronized (DeviceAuthMethodHandler.class) {
            if (backgroundExecutor == null) {
                backgroundExecutor = new ScheduledThreadPoolExecutor(1);
            }
            scheduledThreadPoolExecutor = backgroundExecutor;
        }
        return scheduledThreadPoolExecutor;
    }

    private void showDialog(LoginClient.Request request) {
        DeviceAuthDialog b_ = b_();
        b_.show(this.b.a().getSupportFragmentManager(), "login_with_facebook");
        b_.startLogin(request);
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return "device_auth";
    }

    /* access modifiers changed from: package-private */
    public boolean a(LoginClient.Request request) {
        showDialog(request);
        return true;
    }

    /* access modifiers changed from: protected */
    public DeviceAuthDialog b_() {
        return new DeviceAuthDialog();
    }

    public int describeContents() {
        return 0;
    }

    public void onCancel() {
        this.b.a(LoginClient.Result.a(this.b.getPendingRequest(), "User canceled log in."));
    }

    public void onError(Exception exc) {
        this.b.a(LoginClient.Result.a(this.b.getPendingRequest(), (String) null, exc.getMessage()));
    }

    public void onSuccess(String str, String str2, String str3, Collection<String> collection, Collection<String> collection2, AccessTokenSource accessTokenSource, Date date, Date date2, Date date3) {
        this.b.a(LoginClient.Result.a(this.b.getPendingRequest(), new AccessToken(str, str2, str3, collection, collection2, accessTokenSource, date, date2, date3)));
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}

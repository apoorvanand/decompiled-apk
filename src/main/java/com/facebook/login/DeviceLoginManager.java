package com.facebook.login;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.facebook.login.LoginClient;
import java.util.Collection;

public class DeviceLoginManager extends LoginManager {
    private static volatile DeviceLoginManager instance;
    @Nullable
    private String deviceAuthTargetUserId;
    private Uri deviceRedirectUri;

    public static DeviceLoginManager getInstance() {
        if (instance == null) {
            synchronized (DeviceLoginManager.class) {
                if (instance == null) {
                    instance = new DeviceLoginManager();
                }
            }
        }
        return instance;
    }

    /* access modifiers changed from: protected */
    public LoginClient.Request a(Collection<String> collection) {
        LoginClient.Request a = super.a(collection);
        Uri deviceRedirectUri2 = getDeviceRedirectUri();
        if (deviceRedirectUri2 != null) {
            a.a(deviceRedirectUri2.toString());
        }
        String deviceAuthTargetUserId2 = getDeviceAuthTargetUserId();
        if (deviceAuthTargetUserId2 != null) {
            a.b(deviceAuthTargetUserId2);
        }
        return a;
    }

    @Nullable
    public String getDeviceAuthTargetUserId() {
        return this.deviceAuthTargetUserId;
    }

    public Uri getDeviceRedirectUri() {
        return this.deviceRedirectUri;
    }

    public void setDeviceAuthTargetUserId(@Nullable String str) {
        this.deviceAuthTargetUserId = str;
    }

    public void setDeviceRedirectUri(Uri uri) {
        this.deviceRedirectUri = uri;
    }
}

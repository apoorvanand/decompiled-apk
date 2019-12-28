package com.roughike.facebooklogin.facebooklogin;

import android.content.Intent;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

class FacebookLoginResultDelegate implements FacebookCallback<LoginResult>, PluginRegistry.ActivityResultListener {
    private static final String ERROR_LOGIN_IN_PROGRESS = "login_in_progress";
    private final CallbackManager callbackManager;
    private MethodChannel.Result pendingResult;

    FacebookLoginResultDelegate(CallbackManager callbackManager2) {
        this.callbackManager = callbackManager2;
    }

    private void finishWithResult(Object obj) {
        MethodChannel.Result result = this.pendingResult;
        if (result != null) {
            result.success(obj);
            this.pendingResult = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, MethodChannel.Result result) {
        if (this.pendingResult != null) {
            result.error(ERROR_LOGIN_IN_PROGRESS, str + " called while another Facebook login operation was in progress.", (Object) null);
        }
        this.pendingResult = result;
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        return this.callbackManager.onActivityResult(i, i2, intent);
    }

    public void onCancel() {
        finishWithResult(FacebookLoginResults.a);
    }

    public void onError(FacebookException facebookException) {
        finishWithResult(FacebookLoginResults.a(facebookException));
    }

    public void onSuccess(LoginResult loginResult) {
        finishWithResult(FacebookLoginResults.a(loginResult));
    }
}
